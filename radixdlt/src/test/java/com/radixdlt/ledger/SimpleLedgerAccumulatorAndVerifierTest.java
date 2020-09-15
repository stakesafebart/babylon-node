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

package com.radixdlt.ledger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.radixdlt.consensus.Command;
import com.radixdlt.consensus.Hasher;
import com.radixdlt.crypto.Hash;
import org.junit.Before;
import org.junit.Test;

public class SimpleLedgerAccumulatorAndVerifierTest {
	private SimpleLedgerAccumulatorAndVerifier accumulatorAndVerifier;

	@Before
	public void setup() {
		Hasher hasher = new Hasher() {
			@Override
			public Hash hash(Object o) {
				throw new UnsupportedOperationException();
			}

			@Override
			public Hash hashBytes(byte[] bytes) {
				return Hash.of(bytes);
			}
		};

		accumulatorAndVerifier = new SimpleLedgerAccumulatorAndVerifier(hasher);
	}

	@Test
	public void when_accumulate__then_should_verify() {
		Command command = mock(Command.class);
		when(command.getHash()).thenReturn(Hash.ZERO_HASH);
		AccumulatorState headState = new AccumulatorState(345, Hash.ZERO_HASH);
		AccumulatorState nextState = accumulatorAndVerifier.accumulate(headState, command);
		assertThat(accumulatorAndVerifier.verify(headState, ImmutableList.of(command), nextState)).isTrue();
	}
}