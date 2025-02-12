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

package com.radixdlt.integration.steady_state.deterministic.consensus;

import com.google.common.collect.ImmutableMap;
import com.google.inject.AbstractModule;
import com.radixdlt.consensus.*;
import com.radixdlt.consensus.bft.BFTInsertUpdate;
import com.radixdlt.consensus.bft.BFTValidatorId;
import com.radixdlt.consensus.bft.RoundUpdate;
import com.radixdlt.consensus.liveness.ScheduledLocalTimeout;
import com.radixdlt.crypto.ECDSASecp256k1Signature;
import com.radixdlt.environment.deterministic.network.ControlledMessage;
import com.radixdlt.environment.deterministic.network.MessageMutator;
import com.radixdlt.harness.deterministic.DeterministicTest;
import com.radixdlt.harness.deterministic.DeterministicTest.DeterministicManualExecutor;
import com.radixdlt.harness.deterministic.PhysicalNodeConfig;
import com.radixdlt.modules.FunctionalRadixNodeModule;
import com.radixdlt.modules.FunctionalRadixNodeModule.ConsensusConfig;
import com.radixdlt.modules.FunctionalRadixNodeModule.LedgerConfig;
import com.radixdlt.modules.FunctionalRadixNodeModule.SafetyRecoveryConfig;
import com.radixdlt.modules.StateComputerConfig;
import com.radixdlt.modules.StateComputerConfig.MockedMempoolConfig;
import com.radixdlt.utils.KeyComparator;
import com.radixdlt.utils.Pair;
import java.util.Map;
import java.util.Optional;
import org.junit.Test;

public class DifferentTimestampsCauseTimeoutTest {
  @Test
  public void when_four_nodes_receive_qcs_with_same_timestamps__quorum_is_achieved() {
    final int numValidatorNodes = 4;
    var nodeConfigs =
        PhysicalNodeConfig.createBasicBatchWithOrder(numValidatorNodes, KeyComparator.instance());
    DeterministicManualExecutor executor =
        DeterministicTest.builder()
            .addPhysicalNodes(nodeConfigs)
            .messageMutator(mutateProposalsBy(0))
            .functionalNodeModule(
                new FunctionalRadixNodeModule(
                    false,
                    SafetyRecoveryConfig.mocked(),
                    ConsensusConfig.of(),
                    LedgerConfig.stateComputerNoSync(
                        StateComputerConfig.mockedNoEpochs(
                            numValidatorNodes, MockedMempoolConfig.noMempool()))))
            .createExecutor();

    executor.start();
    executeTwoRounds(executor);

    executor.processNext(3, 3, RoundUpdate.class);
    executor.processNext(3, 3, Proposal.class);
    executor.processNext(3, 3, BFTInsertUpdate.class);
    executor.processNext(3, 0, Proposal.class);
    executor.processNext(0, 0, RoundUpdate.class);
    executor.processNext(0, 0, BFTInsertUpdate.class);
    executor.processNext(3, 1, Proposal.class);
    executor.processNext(1, 1, RoundUpdate.class);
    executor.processNext(1, 1, BFTInsertUpdate.class);
    executor.processNext(3, 2, Proposal.class);
    executor.processNext(2, 2, RoundUpdate.class);
    executor.processNext(2, 2, BFTInsertUpdate.class);
  }

  @Test
  public void when_four_nodes_receive_qcs_with_different_timestamps__quorum_is_not_achieved() {
    final int numValidatorNodes = 4;
    var nodeConfigs =
        PhysicalNodeConfig.createBasicBatchWithOrder(numValidatorNodes, KeyComparator.instance());

    // TODO: this test isn't exactly right and should be updated so that
    // TODO: byzantine node sends different sets of valid QCs to each node
    DeterministicManualExecutor executor =
        DeterministicTest.builder()
            .overrideWithIncorrectModule(
                new AbstractModule() {
                  @Override
                  protected void configure() {
                    bind(HashVerifier.class).toInstance((pubKey, hash, sig) -> true);
                    bind(HashSigner.class).toInstance(h -> ECDSASecp256k1Signature.zeroSignature());
                  }
                })
            .addPhysicalNodes(nodeConfigs)
            .messageMutator(mutateProposalsBy(1))
            .functionalNodeModule(
                new FunctionalRadixNodeModule(
                    false,
                    SafetyRecoveryConfig.mocked(),
                    ConsensusConfig.of(),
                    LedgerConfig.stateComputerNoSync(
                        StateComputerConfig.mockedNoEpochs(
                            numValidatorNodes, MockedMempoolConfig.noMempool()))))
            .createExecutor();

    executor.start();

    executeTwoRounds(executor);

    // Timeouts from nodes
    executor.processNext(0, 0, ScheduledLocalTimeout.class);
    executor.processNext(1, 1, ScheduledLocalTimeout.class);
    executor.processNext(2, 2, ScheduledLocalTimeout.class);
    executor.processNext(3, 3, ScheduledLocalTimeout.class);
  }

  private void executeTwoRounds(DeterministicManualExecutor executor) {
    // Proposal here has genesis qc, which has no timestamps
    executor.processNext(1, 1, Proposal.class);
    executor.processNext(1, 1, BFTInsertUpdate.class);
    executor.processNext(1, 0, Proposal.class);
    executor.processNext(0, 0, BFTInsertUpdate.class);
    executor.processNext(1, 2, Proposal.class);
    executor.processNext(2, 2, BFTInsertUpdate.class);
    executor.processNext(1, 3, Proposal.class);
    executor.processNext(3, 3, BFTInsertUpdate.class);

    executor.processNext(2, 2, Vote.class); // Messages to self first
    executor.processNext(1, 2, Vote.class); // Leader votes early as it sees proposal early
    executor.processNext(0, 2, Vote.class);
    executor.processNext(3, 2, Vote.class);

    // Proposal here should have timestamps from previous round
    // They are mutated as required by the test
    executor.processNext(2, 2, RoundUpdate.class);
    executor.processNext(2, 2, Proposal.class);
    executor.processNext(2, 2, BFTInsertUpdate.class);
    executor.processNext(2, 0, Proposal.class);
    executor.processNext(0, 0, RoundUpdate.class);
    executor.processNext(0, 0, BFTInsertUpdate.class);
    executor.processNext(2, 1, Proposal.class);
    executor.processNext(1, 1, RoundUpdate.class);
    executor.processNext(1, 1, BFTInsertUpdate.class);
    executor.processNext(2, 3, Proposal.class);
    executor.processNext(3, 3, RoundUpdate.class);
    executor.processNext(3, 3, BFTInsertUpdate.class);

    executor.processNext(3, 3, Vote.class);
    executor.processNext(2, 3, Vote.class);
    executor.processNext(0, 3, Vote.class);
    executor.processNext(1, 3, Vote.class);
  }

  private MessageMutator mutateProposalsBy(int factor) {
    return (message, queue) -> {
      ControlledMessage messageToUse = message;
      Object msg = message.message();
      if (msg instanceof Proposal) {
        Proposal p = (Proposal) msg;
        int receiverIndex = message.channelId().receiverIndex();
        messageToUse =
            new ControlledMessage(
                message.origin(),
                message.channelId(),
                mutateProposal(p, receiverIndex * factor),
                message.typeLiteral(),
                message.arrivalTime());
      }
      queue.add(messageToUse);
      return true;
    };
  }

  private Proposal mutateProposal(Proposal p, int destination) {
    QuorumCertificate committedQC = p.highQC().highestCommittedQC();
    Vertex vertex = p.getVertex();
    ECDSASecp256k1Signature signature = p.getSignature();

    return new Proposal(
        mutateVertex(vertex, destination), committedQC, signature, Optional.empty());
  }

  private Vertex mutateVertex(Vertex v, int destination) {
    var qc = v.getQCToParent();
    var round = v.getRound();
    var txns = v.getTransactions();
    var proposer = v.getProposer();

    return Vertex.create(mutateQC(qc, destination), round, txns, proposer, v.proposerTimestamp());
  }

  private QuorumCertificate mutateQC(QuorumCertificate qc, int destination) {
    TimestampedECDSASignatures signatures = qc.getTimestampedSignatures();
    VoteData voteData = qc.getVoteData();

    return new QuorumCertificate(voteData, mutateTimestampedSignatures(signatures, destination));
  }

  private TimestampedECDSASignatures mutateTimestampedSignatures(
      TimestampedECDSASignatures signatures, int destination) {
    Map<BFTValidatorId, TimestampedECDSASignature> sigs = signatures.getSignatures();
    return new TimestampedECDSASignatures(
        sigs.entrySet().stream()
            .map(e -> Pair.of(e.getKey(), mutateTimestampedSignature(e.getValue(), destination)))
            .collect(ImmutableMap.toImmutableMap(Pair::getFirst, Pair::getSecond)));
  }

  private TimestampedECDSASignature mutateTimestampedSignature(
      TimestampedECDSASignature signature, int destination) {
    long timestamp = signature.timestamp();
    ECDSASecp256k1Signature sig = signature.signature();

    return TimestampedECDSASignature.from(timestamp + destination, sig);
  }
}
