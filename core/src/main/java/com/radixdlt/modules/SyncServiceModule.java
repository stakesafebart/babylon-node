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

package com.radixdlt.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.radixdlt.consensus.BFTConfiguration;
import com.radixdlt.consensus.HashVerifier;
import com.radixdlt.consensus.LedgerProof;
import com.radixdlt.crypto.Hasher;
import com.radixdlt.environment.*;
import com.radixdlt.ledger.CommittedTransactionsWithProof;
import com.radixdlt.ledger.LedgerUpdate;
import com.radixdlt.monitoring.Metrics;
import com.radixdlt.p2p.NodeId;
import com.radixdlt.p2p.PeerControl;
import com.radixdlt.store.LastProof;
import com.radixdlt.sync.LocalSyncService;
import com.radixdlt.sync.LocalSyncService.InvalidSyncResponseHandler;
import com.radixdlt.sync.LocalSyncService.VerifiedSyncResponseHandler;
import com.radixdlt.sync.RemoteSyncService;
import com.radixdlt.sync.SyncRelayConfig;
import com.radixdlt.sync.SyncState;
import com.radixdlt.sync.messages.local.SyncCheckTrigger;
import com.radixdlt.sync.messages.remote.StatusRequest;
import com.radixdlt.sync.messages.remote.SyncRequest;
import com.radixdlt.sync.validation.RemoteSyncResponseSignaturesVerifier;
import com.radixdlt.sync.validation.RemoteSyncResponseValidatorSetVerifier;
import java.time.Duration;

/** Module which manages synchronization of committed transactions across nodes */
public class SyncServiceModule extends AbstractModule {
  private final SyncRelayConfig syncRelayConfig;

  public SyncServiceModule(SyncRelayConfig syncRelayConfig) {
    this.syncRelayConfig = syncRelayConfig;
  }

  @Override
  public void configure() {
    bind(SyncRelayConfig.class).toInstance(this.syncRelayConfig);
    bind(LocalSyncService.class).in(Scopes.SINGLETON);
    bind(RemoteSyncService.class).in(Scopes.SINGLETON);
  }

  @Provides
  private SyncState initialSyncState(@LastProof LedgerProof currentHeader) {
    return SyncState.IdleState.init(currentHeader);
  }

  @ProvidesIntoSet
  private RemoteEventProcessorOnRunner<?, ?> syncRequestEventProcessor(
      RemoteSyncService remoteSyncService) {
    return new RemoteEventProcessorOnRunner<>(
        Runners.SYNC,
        NodeId.class,
        SyncRequest.class,
        remoteSyncService.syncRequestEventProcessor());
  }

  @ProvidesIntoSet
  private RemoteEventProcessorOnRunner<?, ?> statusRequestEventProcessor(
      RemoteSyncService remoteSyncService) {
    return new RemoteEventProcessorOnRunner<>(
        Runners.SYNC,
        NodeId.class,
        StatusRequest.class,
        remoteSyncService.statusRequestEventProcessor());
  }

  @ProvidesIntoSet
  private EventProcessorOnRunner<?> ledgerUpdateEventProcessor(
      RemoteSyncService remoteSyncService) {
    return new EventProcessorOnRunner<>(
        Runners.SYNC, LedgerUpdate.class, remoteSyncService.ledgerUpdateEventProcessor());
  }

  @Provides
  private InvalidSyncResponseHandler invalidSyncResponseHandler(
      Metrics metrics, PeerControl peerControl) {
    return (sender, resp) -> {
      peerControl.banPeer(sender, Duration.ofMinutes(10), "Received invalid sync response");
      metrics.sync().invalidResponsesReceived().inc();
    };
  }

  @Provides
  private VerifiedSyncResponseHandler verifiedSyncResponseHandler(
      EventDispatcher<CommittedTransactionsWithProof>
          syncedCommittedTransactionsWithProofDispatcher) {
    return resp -> {
      var txnsAndProof = resp.getTransactionsWithProofDto();
      // TODO: Stateful ledger header verification:
      // TODO: -verify rootHash matches
      var nextHeader =
          new LedgerProof(
              txnsAndProof.getTail().getOpaque(),
              txnsAndProof.getTail().getLedgerHeader(),
              txnsAndProof.getTail().getSignatures());

      var verified =
          CommittedTransactionsWithProof.create(txnsAndProof.getTransactions(), nextHeader);

      syncedCommittedTransactionsWithProofDispatcher.dispatch(verified);
    };
  }

  @Provides
  private RemoteSyncResponseValidatorSetVerifier validatorSetVerifier(
      BFTConfiguration initialConfiguration) {
    return new RemoteSyncResponseValidatorSetVerifier(initialConfiguration.getValidatorSet());
  }

  @Provides
  private RemoteSyncResponseSignaturesVerifier signaturesVerifier(
      Hasher hasher, HashVerifier hashVerifier) {
    return new RemoteSyncResponseSignaturesVerifier(hasher, hashVerifier);
  }

  @Provides
  @Singleton
  private EventProducer<SyncCheckTrigger> eventProducer(
      ScheduledEventDispatcher<SyncCheckTrigger> dispatcher, SyncRelayConfig syncRelayConfig) {
    return new EventProducer<>(
        SyncCheckTrigger::create, dispatcher, syncRelayConfig.syncCheckInterval());
  }

  @ProvidesIntoSet
  private StartProcessorOnRunner syncCheckStart(EventProducer<SyncCheckTrigger> dispatcher) {
    return new StartProcessorOnRunner(Runners.SYNC, dispatcher::start);
  }

  @ProvidesIntoSet
  private EventProcessorOnRunner<?> processor(EventProducer<SyncCheckTrigger> eventProducer) {
    return new EventProcessorOnRunner<>(Runners.SYNC, SyncCheckTrigger.class, eventProducer);
  }
}
