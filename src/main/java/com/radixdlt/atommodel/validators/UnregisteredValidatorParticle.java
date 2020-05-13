package com.radixdlt.atommodel.validators;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radixdlt.constraintmachine.Particle;
import com.radixdlt.identifiers.RadixAddress;
import com.radixdlt.serialization.DsonOutput;
import com.radixdlt.serialization.SerializerId2;

@SerializerId2("radix.particles.unregistered_validator")
public class UnregisteredValidatorParticle extends Particle {
	@JsonProperty("address")
	@DsonOutput(DsonOutput.Output.ALL)
	private RadixAddress address;

	@JsonProperty("nonce")
	@DsonOutput(DsonOutput.Output.ALL)
	private long nonce;

	private UnregisteredValidatorParticle() {
		// for serializer
	}

	public UnregisteredValidatorParticle(RadixAddress address, long nonce) {
		super(address.euid());
		this.address = address;
		this.nonce = nonce;
	}

	public RadixAddress getAddress() {
		return address;
	}

	public long getNonce() {
		return nonce;
	}

	@Override
	public String toString() {
		return String.format("%s[%s]", getClass().getSimpleName(), getAddress());
	}
}
