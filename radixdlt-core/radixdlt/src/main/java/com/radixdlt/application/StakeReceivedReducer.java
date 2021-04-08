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

package com.radixdlt.application;

import com.google.inject.Inject;
import com.radixdlt.atommodel.tokens.StakedTokensParticle;
import com.radixdlt.consensus.bft.Self;
import com.radixdlt.engine.StateReducer;
import com.radixdlt.fees.NativeToken;
import com.radixdlt.identifiers.RRI;
import com.radixdlt.identifiers.RadixAddress;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Reduces radix engine to stake received
 */
public final class StakeReceivedReducer implements StateReducer<StakeReceived, StakedTokensParticle> {
	private final RRI tokenRRI;
	private final RadixAddress address;

	@Inject
	public StakeReceivedReducer(
		@NativeToken RRI tokenRRI,
		@Self RadixAddress address
	) {
		this.tokenRRI = Objects.requireNonNull(tokenRRI);
		this.address = Objects.requireNonNull(address);
	}

	@Override
	public Class<StakeReceived> stateClass() {
		return StakeReceived.class;
	}

	@Override
	public Class<StakedTokensParticle> particleClass() {
		return StakedTokensParticle.class;
	}

	@Override
	public Supplier<StakeReceived> initial() {
		return StakeReceived::new;
	}

	@Override
	public BiFunction<StakeReceived, StakedTokensParticle, StakeReceived> outputReducer() {
		return (stakes, p) -> {
			if (p.getDelegateAddress().equals(address) && p.getTokDefRef().equals(tokenRRI)) {
				stakes.addStake(p.getAddress(), p.getAmount());
			}
			return stakes;
		};
	}

	@Override
	public BiFunction<StakeReceived, StakedTokensParticle, StakeReceived> inputReducer() {
		return (stakes, p) -> {
			if (p.getDelegateAddress().equals(address) && p.getTokDefRef().equals(tokenRRI)) {
				stakes.removeStake(p.getAddress(), p.getAmount());
			}
			return stakes;
		};
	}
}