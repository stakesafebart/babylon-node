/* Copyright 2021 Radix Publishing Ltd incorporated in Jersey (Channel Islands).
 *
 * Licensed under the Radix License, Version 1.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at:
 *
 * radixfoundation.org/licenses/LICENSE-v1
 *
 * The Licensor hereby grants permission for the Canonical version of the Work to be
 * published, distributed and used under or by reference to the Licensor’s trademark
 * Radix ® and use of any unregistered trade names, logos or get-up.
 *
 * The Licensor provides the Work (and each Contributor provides its Contributions) on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
 * including, without limitation, any warranties or conditions of TITLE, NON-INFRINGEMENT,
 * MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Whilst the Work is capable of being deployed, used and adopted (instantiated) to create
 * a distributed ledger it is your responsibility to test and validate the code, together
 * with all logic and performance of that code under all foreseeable scenarios.
 *
 * The Licensor does not make or purport to make and hereby excludes liability for all
 * and any representation, warranty or undertaking in any form whatsoever, whether express
 * or implied, to any entity or person, including any representation, warranty or
 * undertaking, as to the functionality security use, value or other characteristics of
 * any distributed ledger nor in respect the functioning or value of any tokens which may
 * be created stored or transferred using the Work. The Licensor does not warrant that the
 * Work or any use of the Work complies with any law or regulation in any territory where
 * it may be implemented or used or that it will be appropriate for any specific purpose.
 *
 * Neither the licensor nor any current or former employees, officers, directors, partners,
 * trustees, representatives, agents, advisors, contractors, or volunteers of the Licensor
 * shall be liable for any direct or indirect, special, incidental, consequential or other
 * losses of any kind, in tort, contract or otherwise (including but not limited to loss
 * of revenue, income or profits, or loss of use or data, or loss of reputation, or loss
 * of any economic or other opportunity of whatsoever nature or howsoever arising), arising
 * out of or in connection with (without limitation of any use, misuse, of any ledger system
 * or use made or its functionality or any performance or operation of any code or protocol
 * caused by bugs or programming or logic errors or otherwise);
 *
 * A. any offer, purchase, holding, use, sale, exchange or transmission of any
 * cryptographic keys, tokens or assets created, exchanged, stored or arising from any
 * interaction with the Work;
 *
 * B. any failure in a transmission or loss of any token or assets keys or other digital
 * artefacts due to errors in transmission;
 *
 * C. bugs, hacks, logic errors or faults in the Work or any communication;
 *
 * D. system software or apparatus including but not limited to losses caused by errors
 * in holding or transmitting tokens by any third-party;
 *
 * E. breaches or failure of security including hacker attacks, loss or disclosure of
 * password, loss of private key, unauthorised use or misuse of such passwords or keys;
 *
 * F. any losses including loss of anticipated savings or other benefits resulting from
 * use of the Work or any changes to the Work (however implemented).
 *
 * You are solely responsible for; testing, validating and evaluation of all operation
 * logic, functionality, security and appropriateness of using the Work for any commercial
 * or non-commercial purpose and for any reproduction or redistribution by You of the
 * Work. You assume all risks associated with Your use of the Work and the exercise of
 * permissions under this License.
 */

package com.radixdlt.integration.targeted.rev2.mempool;

import static com.radixdlt.environment.deterministic.network.MessageSelector.firstSelector;
import static com.radixdlt.harness.predicates.NodesPredicate.*;

import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.radixdlt.environment.EventDispatcher;
import com.radixdlt.harness.deterministic.DeterministicTest;
import com.radixdlt.harness.deterministic.PhysicalNodeConfig;
import com.radixdlt.harness.simulation.application.TransactionGenerator;
import com.radixdlt.mempool.MempoolAdd;
import com.radixdlt.mempool.MempoolRelayConfig;
import com.radixdlt.modules.FunctionalRadixNodeModule;
import com.radixdlt.modules.FunctionalRadixNodeModule.ConsensusConfig;
import com.radixdlt.modules.FunctionalRadixNodeModule.LedgerConfig;
import com.radixdlt.modules.FunctionalRadixNodeModule.SafetyRecoveryConfig;
import com.radixdlt.modules.StateComputerConfig;
import com.radixdlt.networks.Network;
import com.radixdlt.rev2.Decimal;
import com.radixdlt.rev2.NetworkDefinition;
import com.radixdlt.rev2.REV2TransactionGenerator;
import com.radixdlt.statemanager.REv2DatabaseConfig;
import com.radixdlt.sync.SyncRelayConfig;
import com.radixdlt.transaction.TransactionBuilder;
import com.radixdlt.transactions.RawNotarizedTransaction;
import com.radixdlt.utils.UInt64;
import org.junit.Test;

public final class REv2MempoolRelayerTest {
  private final int MEMPOOL_SIZE = 100;
  private final TransactionGenerator<RawNotarizedTransaction> transactionGenerator =
      new REV2TransactionGenerator(NetworkDefinition.INT_TEST_NET);

  private DeterministicTest createTest() {
    return DeterministicTest.builder()
        .addPhysicalNodes(PhysicalNodeConfig.createBatch(20, true))
        .messageSelector(firstSelector())
        .functionalNodeModule(
            new FunctionalRadixNodeModule(
                false,
                SafetyRecoveryConfig.mocked(),
                ConsensusConfig.of(1000),
                LedgerConfig.stateComputerWithSyncRelay(
                    StateComputerConfig.rev2(
                        Network.INTEGRATIONTESTNET.getId(),
                        TransactionBuilder.createGenesisWithNumValidators(
                            1, Decimal.of(1), UInt64.fromNonNegativeLong(100000)),
                        REv2DatabaseConfig.inMemory(),
                        StateComputerConfig.REV2ProposerConfig.mempool(
                            0, 0, MEMPOOL_SIZE, new MempoolRelayConfig(0, 100))),
                    SyncRelayConfig.of(5000, 10, 3000L))));
  }

  @Test
  public void relayer_fills_mempool_of_all_nodes() {
    try (var test = createTest()) {
      test.startAllNodes();

      // Arrange: Fill node1 mempool
      var mempoolDispatcher =
          test.getInstance(1, Key.get(new TypeLiteral<EventDispatcher<MempoolAdd>>() {}));
      for (int i = 0; i < MEMPOOL_SIZE; i++) {
        mempoolDispatcher.dispatch(MempoolAdd.create(transactionGenerator.nextTransaction()));
      }

      // Run all nodes except validator node0
      test.runUntilState(
          n -> allHaveExactMempoolCount(MEMPOOL_SIZE).test(n.subList(1, n.size())),
          10000,
          m -> m.channelId().senderIndex() != 0 && m.channelId().receiverIndex() != 0);
    }
  }
}
