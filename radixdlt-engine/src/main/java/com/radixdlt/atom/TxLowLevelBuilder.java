/*
 * (C) Copyright 2021 Radix DLT Ltd
 *
 * Radix DLT Ltd licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 */

package com.radixdlt.atom;

import com.google.common.hash.HashCode;
import com.radixdlt.constraintmachine.REInstruction;
import com.radixdlt.constraintmachine.Particle;
import com.radixdlt.constraintmachine.ShutdownAllIndex;
import com.radixdlt.constraintmachine.SubstateSerialization;
import com.radixdlt.crypto.ECDSASignature;
import com.radixdlt.crypto.HashUtils;
import com.radixdlt.utils.Ints;
import com.radixdlt.utils.UInt256;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Low level builder class for transactions
 */
public final class TxLowLevelBuilder {
	private final ByteArrayOutputStream blobStream;
	private final Map<Integer, LocalSubstate> localUpParticles = new HashMap<>();
	private final Set<SubstateId> remoteDownSubstate = new HashSet<>();
	private final SubstateSerialization serialization;
	private int instructionIndex = 0;

	TxLowLevelBuilder(SubstateSerialization serialization, ByteArrayOutputStream blobStream) {
		this.serialization = serialization;
		this.blobStream = blobStream;
	}

	public static TxLowLevelBuilder newBuilder(byte[] blob) {
		var blobStream = new ByteArrayOutputStream();
		try {
			blobStream.write(blob);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to write data.");
		}
		// TODO: Cleanup null serialization, but works for now as only used for client side signing
		return new TxLowLevelBuilder(null, blobStream);
	}

	public static TxLowLevelBuilder newBuilder(SubstateSerialization serialization) {
		return new TxLowLevelBuilder(serialization, new ByteArrayOutputStream());
	}

	public Set<SubstateId> remoteDownSubstate() {
		return remoteDownSubstate;
	}

	public List<LocalSubstate> localUpSubstate() {
		return new ArrayList<>(localUpParticles.values());
	}

	// TODO: Remove array copies
	private byte[] varLengthData(byte[] bytes) {
		if (bytes.length > 255) {
			throw new IllegalArgumentException();
		}
		var data = new byte[1 + bytes.length];
		System.arraycopy(bytes, 0, data, 1, bytes.length);
		data[0] = (byte) bytes.length;
		return data;
	}

	private void instruction(REInstruction.REMicroOp op, byte[] data) {
		blobStream.write(op.opCode());
		try {
			blobStream.write(data);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to write data.");
		}
		this.instructionIndex++;
	}

	public TxLowLevelBuilder message(byte[] bytes) {
		instruction(REInstruction.REMicroOp.MSG, varLengthData(bytes));
		return this;
	}

	public TxLowLevelBuilder message(String message) {
		var bytes = message.getBytes(StandardCharsets.UTF_8);
		return message(bytes);
	}

	public TxLowLevelBuilder up(Particle particle) {
		Objects.requireNonNull(particle, "particle is required");
		this.localUpParticles.put(instructionIndex, LocalSubstate.create(instructionIndex, particle));
		var bytes = serialization.serialize(particle);
		instruction(REInstruction.REMicroOp.UP, bytes);
		return this;
	}

	public TxLowLevelBuilder virtualDown(Particle particle) {
		Objects.requireNonNull(particle, "particle is required");
		var bytes = serialization.serialize(particle);
		instruction(REInstruction.REMicroOp.VDOWN, bytes);
		return this;
	}

	public TxLowLevelBuilder virtualDown(Particle particle, byte[] arg) {
		Objects.requireNonNull(particle, "particle is required");
		var bytes = serialization.serialize(particle);
		var buf = ByteBuffer.allocate(bytes.length + 1 + arg.length);
		buf.put(bytes);
		buf.put((byte) arg.length); // arg length
		buf.put(arg);
		instruction(REInstruction.REMicroOp.VDOWNARG, buf.array());
		this.remoteDownSubstate.add(SubstateId.ofVirtualSubstate(bytes));
		return this;
	}

	public TxLowLevelBuilder localDown(int index) {
		var particle = localUpParticles.remove(index);
		if (particle == null) {
			throw new IllegalStateException("Local particle does not exist: " + index);
		}
		instruction(REInstruction.REMicroOp.LDOWN, Ints.toByteArray(index));
		return this;
	}

	public TxLowLevelBuilder down(SubstateId substateId) {
		instruction(REInstruction.REMicroOp.DOWN, substateId.asBytes());
		this.remoteDownSubstate.add(substateId);
		return this;
	}

	public TxLowLevelBuilder downAll(Byte typeByte) {
		instruction(REInstruction.REMicroOp.DOWNALL, new byte[] {typeByte});
		return this;
	}

	public TxLowLevelBuilder downAll(ShutdownAllIndex index) {
		var buf = ByteBuffer.allocate(1 + index.getPrefix().length);
		buf.put((byte) index.getPrefix().length);
		buf.put(index.getPrefix());
		instruction(REInstruction.REMicroOp.DOWNINDEX, buf.array());
		return this;
	}

	public TxLowLevelBuilder end() {
		instruction(REInstruction.REMicroOp.END, new byte[0]);
		return this;
	}

	public TxLowLevelBuilder payFee(UInt256 amount) {
		var data = new byte[2 + UInt256.BYTES];
		data[0] = UInt256.BYTES + 1;
		data[1] = 0;
		amount.toByteArray(data, 2);
		instruction(REInstruction.REMicroOp.SYSCALL, data);
		return this;
	}

	public TxLowLevelBuilder disableResourceAllocAndDestroy() {
		var data = new byte[] {0, 1};
		instruction(REInstruction.REMicroOp.HEADER, data);
		return this;
	}

	public byte[] blob() {
		return blobStream.toByteArray();
	}

	public HashCode hashToSign() {
		return HashUtils.sha256(blob()); // this is a double hash
	}

	public TxLowLevelBuilder sig(ECDSASignature signature) {
		instruction(REInstruction.REMicroOp.SIG, REFieldSerialization.serializeSignature(signature));
		return this;
	}

	public Txn build() {
		return Txn.create(blobStream.toByteArray());
	}
}