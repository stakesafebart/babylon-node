/*
 *  (C) Copyright 2020 Radix DLT Ltd
 *
 *  Radix DLT Ltd licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except in
 *  compliance with the License.  You may obtain a copy of the
 *  License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *  either express or implied.  See the License for the specific
 *  language governing permissions and limitations under the License.
 */

package com.radixdlt.consensus;

import com.google.inject.Inject;
import com.radixdlt.consensus.safety.QuorumRequirements;
import com.radixdlt.crypto.DefaultSignatures;
import com.radixdlt.crypto.Signature;
import com.radixdlt.crypto.Signatures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Manages the BFT Vertex chain
 */
public final class VertexStore {
	private final Set<Vertex> vertices = new HashSet<>();
	private final Map<VertexMetadata, Signatures> pendingSignatures = new HashMap<>();
	private QuorumCertificate highestQC = null;

	@Inject
	public VertexStore() {
	}

	public void syncToQC(QuorumCertificate qc) {
		if (qc == null) {
			return;
		}

		if (highestQC == null || highestQC.getRound().compareTo(qc.getRound()) < 0) {
			highestQC = qc;
		}
	}

	public Optional<QuorumCertificate> insertVote(Vote vote, QuorumRequirements quorumRequirements) {
		Signature signature = vote.getSignature().orElseThrow(() -> new IllegalArgumentException("vote is missing signature"));
		Signatures signatures = pendingSignatures.getOrDefault(vote.getVertexMetadata(), DefaultSignatures.emptySignatures());

		// try to add the signature to form a QC if permitted by the requirements
		if (quorumRequirements.acceptsVoteBy(vote.getAuthor().getUID())) {
			signatures = signatures.concatenate(vote.getAuthor(), signature);
		} else {
			// there is no meaningful inaction here, so better let the caller know
			throw new IllegalArgumentException("vote " + vote + " was not accepted into QC");
		}

		// try to form a QC with the added signature according to the requirements
		if (signatures.count() >= quorumRequirements.numRequiredVotes()) {
			// if QC could be formed, remove pending and return formed QC
			pendingSignatures.remove(vote.getVertexMetadata());
			QuorumCertificate qc = new QuorumCertificate(vote.getVertexMetadata(), signatures);
			return Optional.of(qc);
		} else {
			// if no QC could be formed, update pending and return nothing
			pendingSignatures.put(vote.getVertexMetadata(), signatures);
			return Optional.empty();
		}
	}

	public void insertVertex(Vertex vertex) {
		this.syncToQC(vertex.getQC());
		vertices.add(vertex);
	}

	public Optional<QuorumCertificate> getHighestQC() {
		return Optional.ofNullable(this.highestQC);
	}
}
