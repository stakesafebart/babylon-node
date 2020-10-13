/*
 * (C) Copyright 2020 Radix DLT Ltd
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
 */

package com.radixdlt.integration.distributed.simulation;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Named;
import com.radixdlt.integration.distributed.simulation.network.SimulationNetwork;
import com.radixdlt.integration.distributed.simulation.network.SimulationNetwork.LatencyProvider;
import com.radixdlt.integration.distributed.simulation.network.SimulationNetwork.MessageInTransit;
import java.util.Set;
import java.util.function.Predicate;

public class SimulationNetworkModule extends AbstractModule {
	@Override
	protected void configure() {
		Multibinder.newSetBinder(binder(), new TypeLiteral<Predicate<MessageInTransit>>() { });
		bind(SimulationNetwork.class).in(Scopes.SINGLETON);
	}

	@Provides
	@Singleton
	private LatencyProvider latencyProvider(
		@Named("base") LatencyProvider base,
		Set<Predicate<MessageInTransit>> droppers
	) {
		return msg -> {
			if (droppers.stream().anyMatch(f -> f.test(msg))) {
				return -1; // -1 Drops the message
			}

			return base.nextLatency(msg);
		};
	}
}
