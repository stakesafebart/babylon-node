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

package com.radixdlt.mempool;

import com.google.common.collect.ImmutableList;
import com.google.inject.Singleton;
import com.radixdlt.environment.EventProcessor;
import com.radixdlt.environment.RemoteEventDispatcher;
import com.radixdlt.monitoring.Metrics;
import com.radixdlt.p2p.NodeId;
import com.radixdlt.p2p.PeersView;
import com.radixdlt.transactions.RawNotarizedTransaction;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.inject.Inject;

/** Relays transactions from the local mempool to node neighbors. */
@Singleton
public final class MempoolRelayer {
  // These two specify the transaction limits for a single relay message (MempoolAdd)
  public static final int MAX_RELAY_MSG_NUM_TXNS = 10;
  public static final int MAX_RELAY_MSG_TOTAL_TXN_PAYLOAD_SIZE = 2 * 1024 * 1024;

  // A limit of transaction bytes transferred during a single relaying event summed up for all
  // chosen peers.
  // For example, if the relay message size is 1 MiB and this is set to 3 MiB then we can only
  // relay this message to a maximum of 3 peers (given that maxPeers is also greater than or equal
  // 3).
  // This prevents the mempool relayer from excessive bandwidth use.
  public static final int MAX_TXN_BYTES_RELAYED_AT_ONCE_TO_ALL_PEERS = 6 * 1024 * 1024;

  private final PeersView peersView;
  private final RemoteEventDispatcher<NodeId, MempoolAdd> remoteEventDispatcher;

  private final Metrics metrics;

  private final MempoolReader<RawNotarizedTransaction> mempoolRelayReader;

  private final int maxPeers;

  @Inject
  public MempoolRelayer(
      MempoolReader<RawNotarizedTransaction> mempoolRelayReader,
      RemoteEventDispatcher<NodeId, MempoolAdd> remoteEventDispatcher,
      PeersView peersView,
      @MempoolRelayMaxPeers int maxPeers,
      Metrics metrics) {

    this.mempoolRelayReader = mempoolRelayReader;
    this.remoteEventDispatcher = Objects.requireNonNull(remoteEventDispatcher);
    this.peersView = Objects.requireNonNull(peersView);
    this.maxPeers = maxPeers;
    this.metrics = Objects.requireNonNull(metrics);
  }

  public EventProcessor<MempoolAddSuccess> mempoolAddSuccessEventProcessor() {
    return mempoolAddSuccess -> {
      final var ignorePeers =
          mempoolAddSuccess.getOrigin().map(ImmutableList::of).orElse(ImmutableList.of());
      relayTransactions(ImmutableList.of(mempoolAddSuccess.getTxn()), ignorePeers);
    };
  }

  public EventProcessor<MempoolRelayTrigger> mempoolRelayTriggerEventProcessor() {
    return ev -> {
      final var transactions =
          this.mempoolRelayReader.getTransactionsToRelay(
              MAX_RELAY_MSG_NUM_TXNS, MAX_RELAY_MSG_TOTAL_TXN_PAYLOAD_SIZE);
      if (!transactions.isEmpty()) {
        relayTransactions(transactions, ImmutableList.of());
      }
    };
  }

  private void relayTransactions(
      List<RawNotarizedTransaction> transactions, ImmutableList<NodeId> ignorePeers) {
    final var mempoolAddMsg = MempoolAdd.create(transactions);

    final var totalTxnPayloadSizeInRelayMsg =
        transactions.stream().map(tx -> tx.getPayload().length).reduce(0, Integer::sum);

    final var maxPeersByTotalTransferSize =
        MAX_TXN_BYTES_RELAYED_AT_ONCE_TO_ALL_PEERS / totalTxnPayloadSizeInRelayMsg;
    final var maxPeersToRelayTo = Math.min(maxPeers, maxPeersByTotalTransferSize);

    final var peers =
        this.peersView.peers().map(PeersView.PeerInfo::getNodeId).collect(Collectors.toList());

    peers.removeAll(ignorePeers);
    Collections.shuffle(peers);
    peers.stream()
        .limit(maxPeersToRelayTo)
        .forEach(
            peer -> {
              metrics.v1Mempool().relaysSent().inc(transactions.size());
              this.remoteEventDispatcher.dispatch(peer, mempoolAddMsg);
            });
  }
}
