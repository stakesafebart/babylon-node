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

package com.radixdlt.harness.simulation;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.util.Modules;
import com.radixdlt.application.tokens.Amount;
import com.radixdlt.consensus.LedgerProof;
import com.radixdlt.consensus.MockedConsensusRecoveryModule;
import com.radixdlt.consensus.bft.*;
import com.radixdlt.consensus.bft.Round;
import com.radixdlt.crypto.ECKeyPair;
import com.radixdlt.crypto.ECPublicKey;
import com.radixdlt.environment.rx.RxEnvironmentModule;
import com.radixdlt.harness.simulation.TestInvariant.TestInvariantError;
import com.radixdlt.harness.simulation.application.BFTValidatorSetNodeSelector;
import com.radixdlt.harness.simulation.application.EpochsNodeSelector;
import com.radixdlt.harness.simulation.application.LocalMempoolPeriodicSubmitter;
import com.radixdlt.harness.simulation.application.NodeSelector;
import com.radixdlt.harness.simulation.application.TransactionGenerator;
import com.radixdlt.harness.simulation.monitors.NodeEvents;
import com.radixdlt.harness.simulation.monitors.SimulationNodeEventsModule;
import com.radixdlt.harness.simulation.network.SimulationNetwork;
import com.radixdlt.harness.simulation.network.SimulationNodes;
import com.radixdlt.ledger.*;
import com.radixdlt.mempool.MempoolRelayConfig;
import com.radixdlt.messaging.TestMessagingModule;
import com.radixdlt.modules.FunctionalRadixNodeModule;
import com.radixdlt.modules.FunctionalRadixNodeModule.ConsensusConfig;
import com.radixdlt.modules.FunctionalRadixNodeModule.LedgerConfig;
import com.radixdlt.modules.MockedCryptoModule;
import com.radixdlt.modules.MockedKeyModule;
import com.radixdlt.modules.StateComputerConfig;
import com.radixdlt.monitoring.SystemCounters;
import com.radixdlt.monitoring.SystemCountersImpl;
import com.radixdlt.networks.Addressing;
import com.radixdlt.networks.Network;
import com.radixdlt.p2p.TestP2PModule;
import com.radixdlt.rev1.EpochMaxRound;
import com.radixdlt.rev1.checkpoint.Genesis;
import com.radixdlt.rev1.checkpoint.MockedGenesisModule;
import com.radixdlt.rev1.forks.ForksEpochStore;
import com.radixdlt.rev1.forks.InMemoryForksEpochStoreModule;
import com.radixdlt.rev1.forks.NoOpForksEpochStore;
import com.radixdlt.rev1.modules.RadixEngineModule;
import com.radixdlt.rev2.modules.MockedPersistenceStoreModule;
import com.radixdlt.store.InMemoryCommittedReaderModule;
import com.radixdlt.store.InMemoryRadixEngineStoreModule;
import com.radixdlt.sync.SyncRelayConfig;
import com.radixdlt.sync.TransactionsAndProofReader;
import com.radixdlt.transactions.RawTransaction;
import com.radixdlt.utils.DurationParser;
import com.radixdlt.utils.Pair;
import com.radixdlt.utils.PrivateKeys;
import com.radixdlt.utils.UInt256;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** High level BFT Simulation Test Runner */
public final class SimulationTest {
  private static final String ENVIRONMENT_VAR_NAME =
      "TEST_DURATION"; // Same as used by regression test suite
  private static final Duration DEFAULT_TEST_DURATION = Duration.ofSeconds(30);

  public interface SimulationNetworkActor {
    void start(SimulationNodes.RunningNetwork network);

    void stop();
  }

  private final ImmutableList<ECKeyPair> initialNodes;
  private final SimulationNetwork simulationNetwork;
  private final Module testModule;
  private final Module baseNodeModule;
  private final ImmutableMultimap<ECPublicKey, Module> overrideModules;

  private SimulationTest(
      ImmutableList<ECKeyPair> initialNodes,
      SimulationNetwork simulationNetwork,
      Module baseNodeModule,
      ImmutableMultimap<ECPublicKey, Module> overrideModules,
      Module testModule) {
    this.initialNodes = initialNodes;
    this.simulationNetwork = simulationNetwork;
    this.baseNodeModule = baseNodeModule;
    this.overrideModules = overrideModules;
    this.testModule = testModule;
  }

  public static class Builder {
    private ImmutableList<ECKeyPair> initialNodes = ImmutableList.of(ECKeyPair.generateNew());
    private FunctionalRadixNodeModule functionalNodeModule =
        new FunctionalRadixNodeModule(false, ConsensusConfig.of(), LedgerConfig.mocked());

    private Module initialNodesModule;
    private final ImmutableList.Builder<Module> testModules = ImmutableList.builder();
    private final ImmutableList.Builder<Module> modules = ImmutableList.builder();
    private final ImmutableMultimap.Builder<ECPublicKey, Module> overrideModules =
        ImmutableMultimap.builder();
    private Module networkModule;
    private ImmutableMap<ECPublicKey, ImmutableList<ECPublicKey>> addressBookNodes;

    private List<BFTNode> bftNodes;
    private Function<Long, IntStream> epochToNodeIndexMapper;

    private Builder() {}

    public Builder addOverrideModuleToInitialNodes(
        Function<ImmutableList<ECKeyPair>, ImmutableList<ECPublicKey>> nodesSelector,
        Function<List<BFTNode>, Module> overrideModule) {
      final var nodes = nodesSelector.apply(this.initialNodes);
      nodes.forEach(node -> overrideModules.put(node, overrideModule.apply(this.bftNodes)));
      return this;
    }

    public Builder addOverrideModuleToAllInitialNodes(Module overrideModule) {
      addOverrideModuleToInitialNodes(
          nodes ->
              nodes.stream().map(ECKeyPair::getPublicKey).collect(ImmutableList.toImmutableList()),
          nodes -> overrideModule);
      return this;
    }

    public Builder networkModules(Module... networkModules) {
      this.networkModule = Modules.combine(networkModules);
      return this;
    }

    public Builder addNetworkModule(Module networkModule) {
      this.networkModule = Modules.combine(this.networkModule, networkModule);
      return this;
    }

    /**
     * A mapping from a node index to a list of other nodes indices. If key is not present, then
     * address book for that node contains all other nodes.
     */
    public Builder addressBook(
        Function<ImmutableList<ECKeyPair>, ImmutableMap<ECPublicKey, ImmutableList<ECPublicKey>>>
            addressBookNodesFn) {
      this.addressBookNodes = addressBookNodesFn.apply(this.initialNodes);
      return this;
    }

    /**
     * Setup the test with nodes.
     *
     * @param initialStakes iterator of nodes initial stakes; if initialStakes.length < numNodes the
     *     last element is repeated for the remaining nodes
     */
    public Builder numNodes(int numNodes, Iterable<UInt256> initialStakes) {
      this.initialNodes =
          PrivateKeys.numeric(1).limit(numNodes).collect(ImmutableList.toImmutableList());

      final var stakesIterator = repeatLast(initialStakes);
      final var initialStakesMap =
          initialNodes.stream()
              .collect(
                  ImmutableMap.toImmutableMap(ECKeyPair::getPublicKey, k -> stakesIterator.next()));

      final var bftNodes =
          initialStakesMap.keySet().stream()
              .map(BFTNode::create)
              .collect(ImmutableList.toImmutableList());
      final var validators =
          initialStakesMap.entrySet().stream()
              .map(e -> BFTValidator.from(BFTNode.create(e.getKey()), e.getValue()))
              .collect(ImmutableList.toImmutableList());

      this.bftNodes = bftNodes;

      this.initialNodesModule =
          new AbstractModule() {
            @Override
            protected void configure() {
              // FIXME This list is injected in at least 2 places: NetworkDroppers and
              // NetworkLatencies. Maybe we could make this more explicit?
              bind(new TypeLiteral<ImmutableList<BFTNode>>() {}).toInstance(bftNodes);
            }
          };

      return this;
    }

    public Builder numNodes(int numNodes) {
      return numNodes(numNodes, ImmutableList.of(UInt256.ONE));
    }

    public Builder ledgerAndEpochs(
        ConsensusConfig consensusConfig,
        Round epochMaxRound,
        Function<Long, IntStream> epochToNodeIndexMapper) {
      this.functionalNodeModule =
          new FunctionalRadixNodeModule(
              true,
              consensusConfig,
              LedgerConfig.stateComputerMockedSync(
                  StateComputerConfig.mocked(
                      new StateComputerConfig.MockedMempoolConfig.NoMempool())));
      this.epochToNodeIndexMapper = epochToNodeIndexMapper;
      this.modules.add(
          new AbstractModule() {
            @Override
            protected void configure() {
              bind(Round.class).annotatedWith(EpochMaxRound.class).toInstance(epochMaxRound);
            }
          });

      return this;
    }

    public Builder functionalNodeModule(FunctionalRadixNodeModule functionalNodeModule) {
      this.functionalNodeModule = functionalNodeModule;
      return this;
    }

    public Builder ledgerAndSync(ConsensusConfig consensusConfig, SyncRelayConfig syncRelayConfig) {
      this.functionalNodeModule =
          new FunctionalRadixNodeModule(
              false,
              consensusConfig,
              LedgerConfig.stateComputerWithSyncRelay(
                  StateComputerConfig.mocked(
                      new StateComputerConfig.MockedMempoolConfig.NoMempool()),
                  syncRelayConfig));
      return this;
    }

    public Builder fullFunctionNodes(
        ConsensusConfig consensusConfig, SyncRelayConfig syncRelayConfig, int mempoolSize) {
      this.functionalNodeModule =
          new FunctionalRadixNodeModule(
              true,
              consensusConfig,
              LedgerConfig.stateComputerWithSyncRelay(
                  StateComputerConfig.rev1(mempoolSize), syncRelayConfig));

      return this;
    }

    public Builder ledgerAndEpochsAndSync(
        ConsensusConfig consensusConfig,
        Round epochMaxRound,
        Function<Long, IntStream> epochToNodeIndexMapper,
        SyncRelayConfig syncRelayConfig) {
      this.functionalNodeModule =
          new FunctionalRadixNodeModule(
              true,
              consensusConfig,
              LedgerConfig.stateComputerWithSyncRelay(
                  StateComputerConfig.mocked(
                      new StateComputerConfig.MockedMempoolConfig.NoMempool()),
                  syncRelayConfig));
      this.epochToNodeIndexMapper = epochToNodeIndexMapper;
      modules.add(
          new AbstractModule() {
            @Override
            protected void configure() {
              bind(Round.class).annotatedWith(EpochMaxRound.class).toInstance(epochMaxRound);
            }
          });
      return this;
    }

    public Builder ledgerAndMempool(ConsensusConfig consensusConfig) {
      this.functionalNodeModule =
          new FunctionalRadixNodeModule(
              false,
              consensusConfig,
              LedgerConfig.stateComputerNoSync(
                  StateComputerConfig.mocked(
                      new StateComputerConfig.MockedMempoolConfig.LocalOnly(10))));
      this.modules.add(MempoolRelayConfig.of(10).asModule());
      return this;
    }

    public Builder ledgerAndRadixEngineWithEpochMaxRound(ConsensusConfig consensusConfig) {
      this.functionalNodeModule =
          new FunctionalRadixNodeModule(
              true,
              consensusConfig,
              LedgerConfig.stateComputerMockedSync(StateComputerConfig.rev1(100)));
      this.modules.add(
          new AbstractModule() {
            @Override
            protected void configure() {
              bind(new TypeLiteral<List<BFTNode>>() {}).toInstance(List.of());
              install(MempoolRelayConfig.of(10).asModule());
            }

            @Provides
            TransactionsAndProofReader committedReader() {
              return new TransactionsAndProofReader() {
                @Override
                public CommittedTransactionsWithProof getTransactions(DtoLedgerProof start) {
                  return null;
                }

                @Override
                public Optional<LedgerProof> getEpochProof(long epoch) {
                  return Optional.empty();
                }

                @Override
                public Optional<LedgerProof> getLastProof() {
                  return Optional.empty();
                }
              };
            }
          });
      this.modules.add(new InMemoryForksEpochStoreModule());

      return this;
    }

    public Builder addRadixEngineConfigModules(Module... modules) {
      this.modules.add(modules);
      this.testModules.add(modules);
      return this;
    }

    public Builder addNodeModule(Module module) {
      this.modules.add(module);
      return this;
    }

    public Builder addTestModules(Module... modules) {
      this.testModules.add(modules);
      return this;
    }

    public Builder addMempoolSubmissionsSteadyState(
        Class<? extends TransactionGenerator> txnGeneratorClass) {
      NodeSelector nodeSelector =
          this.functionalNodeModule.supportsEpochs()
              ? new EpochsNodeSelector()
              : new BFTValidatorSetNodeSelector();
      this.testModules.add(
          new AbstractModule() {
            @Override
            public void configure() {
              var multibinder = Multibinder.newSetBinder(binder(), SimulationNetworkActor.class);
              multibinder.addBinding().to(LocalMempoolPeriodicSubmitter.class);
              bind(txnGeneratorClass).in(Scopes.SINGLETON);
              bind(TransactionGenerator.class).to(txnGeneratorClass);
            }

            @Provides
            @Singleton
            LocalMempoolPeriodicSubmitter mempoolSubmittor(
                TransactionGenerator transactionGenerator) {
              return new LocalMempoolPeriodicSubmitter(transactionGenerator, nodeSelector);
            }
          });

      return this;
    }

    public Builder addActor(Class<? extends SimulationNetworkActor> c) {
      this.testModules.add(
          new AbstractModule() {
            @Override
            public void configure() {
              Multibinder.newSetBinder(binder(), SimulationNetworkActor.class).addBinding().to(c);
            }
          });
      return this;
    }

    public SimulationTest build() {
      final NodeEvents nodeEvents = new NodeEvents();

      // Config
      modules.add(
          new AbstractModule() {
            @Override
            public void configure() {
              bind(SystemCounters.class).to(SystemCountersImpl.class).in(Scopes.SINGLETON);
              bind(Addressing.class).toInstance(Addressing.ofNetwork(Network.INTEGRATIONTESTNET));
              bind(NodeEvents.class).toInstance(nodeEvents);
            }
          });
      modules.add(new MockedSystemModule());
      modules.add(new MockedKeyModule());
      modules.add(new MockedCryptoModule());
      TestP2PModule.Builder mockedP2PModuleBuilder = new TestP2PModule.Builder();
      // There are two ways we can define the peers a node is aware of. 1 - Using all nodes in the
      // network. 2
      // - Creating an address book.
      if (this.addressBookNodes != null) {
        mockedP2PModuleBuilder.withPeersByNode(this.addressBookNodes);
      } else {
        mockedP2PModuleBuilder.withAllNodes(bftNodes);
      }
      modules.add(mockedP2PModuleBuilder.build());

      modules.add(new TestMessagingModule.Builder().withDefaultRateLimit().build());
      // Functional
      modules.add(this.functionalNodeModule);

      // Persistence
      if (this.functionalNodeModule.supportsREv1()) {
        modules.add(new InMemoryRadixEngineStoreModule());
        modules.add(
            new MockedGenesisModule(
                initialNodes.stream().map(ECKeyPair::getPublicKey).collect(Collectors.toSet()),
                Amount.ofTokens(1000000),
                Amount.ofTokens(10000)));

        // FIXME: A bit of a hack
        testModules.add(
            new AbstractModule() {
              public void configure() {
                install(new RadixEngineModule());
                install(new InMemoryRadixEngineStoreModule());
                install(new MockedCryptoModule());
                install(
                    new MockedGenesisModule(
                        initialNodes.stream()
                            .map(ECKeyPair::getPublicKey)
                            .collect(Collectors.toSet()),
                        Amount.ofTokens(1000000),
                        Amount.ofTokens(10000)));
                bind(LedgerAccumulator.class).to(SimpleLedgerAccumulatorAndVerifier.class);
                bind(SystemCounters.class).toInstance(new SystemCountersImpl());
                bind(TransactionsAndProofReader.class).toInstance(new NoOpCommittedReader());
                bind(ForksEpochStore.class).toInstance(new NoOpForksEpochStore());
              }

              @Genesis
              @Provides
              RawTransaction genesis(@Genesis CommittedTransactionsWithProof txnsAndProof) {
                return txnsAndProof.getTransactions().get(0);
              }
            });
      } else if (this.functionalNodeModule.supportsREv2()) {
        var initialVset = initialNodes.stream().map(e -> BFTNode.create(e.getPublicKey())).toList();
        modules.add(
            new AbstractModule() {
              @Override
              protected void configure() {
                var validatorSet =
                    BFTValidatorSet.from(
                        initialVset.stream().map(n -> BFTValidator.from(n, UInt256.ONE)));
                bind(BFTValidatorSet.class).toInstance(validatorSet);
              }
            });

      } else {
        var initialVset = initialNodes.stream().map(e -> BFTNode.create(e.getPublicKey())).toList();
        MockedConsensusRecoveryModule.Builder mockedConsensusRecoveryModuleBuilder;
        if (this.epochToNodeIndexMapper != null) {
          mockedConsensusRecoveryModuleBuilder = new MockedConsensusRecoveryModule.Builder(true);
          mockedConsensusRecoveryModuleBuilder.withEpochNodeIndexesMapping(
              this.epochToNodeIndexMapper);
        } else {
          mockedConsensusRecoveryModuleBuilder = new MockedConsensusRecoveryModule.Builder();
        }
        mockedConsensusRecoveryModuleBuilder.withNodes(initialVset);
        modules.add(mockedConsensusRecoveryModuleBuilder.build());
      }

      modules.add(new MockedPersistenceStoreModule());

      // Testing
      modules.add(new SimulationNodeEventsModule());
      testModules.add(
          new AbstractModule() {
            @Override
            protected void configure() {
              Multibinder.newSetBinder(binder(), SimulationNetworkActor.class);
              bind(Key.get(new TypeLiteral<List<ECKeyPair>>() {})).toInstance(initialNodes);
              bind(NodeEvents.class).toInstance(nodeEvents);
            }
          });

      // Nodes
      final SimulationNetwork simulationNetwork =
          Guice.createInjector(initialNodesModule, new SimulationNetworkModule(), networkModule)
              .getInstance(SimulationNetwork.class);

      // Runners
      modules.add(new RxEnvironmentModule());
      if (this.functionalNodeModule.supportsSync() && !this.functionalNodeModule.supportsREv2()) {
        modules.add(new InMemoryCommittedReaderModule());
        modules.add(new InMemoryForksEpochStoreModule());
      }

      return new SimulationTest(
          initialNodes,
          simulationNetwork,
          Modules.combine(modules.build()),
          overrideModules.build(),
          Modules.combine(testModules.build()));
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  private Observable<Pair<Monitor, Optional<TestInvariantError>>> runChecks(
      Set<SimulationNetworkActor> runners,
      Map<Monitor, TestInvariant> checkers,
      SimulationNodes.RunningNetwork runningNetwork,
      Duration duration) {
    var assertions =
        checkers.keySet().stream()
            .map(
                name -> {
                  TestInvariant check = checkers.get(name);
                  return Pair.of(
                      name,
                      check
                          .check(runningNetwork)
                          .map(e -> Pair.of(name, e))
                          .publish()
                          .autoConnect(2));
                })
            .toList();

    var firstErrorSignal =
        Observable.merge(assertions.stream().map(Pair::getSecond).toList())
            .firstOrError()
            .map(Pair::getFirst);

    var results =
        assertions.stream()
            .map(
                assertion ->
                    assertion
                        .getSecond()
                        .takeUntil(
                            firstErrorSignal.flatMapObservable(
                                name ->
                                    !assertion.getFirst().equals(name)
                                        ? Observable.just(name)
                                        : Observable.never()))
                        .takeUntil(
                            Observable.timer(duration.get(ChronoUnit.SECONDS), TimeUnit.SECONDS))
                        .map(e -> Optional.of(e.getSecond()))
                        .first(Optional.empty())
                        .map(result -> Pair.of(assertion.getFirst(), result)))
            .toList();

    return Single.merge(results)
        .toObservable()
        .doOnSubscribe(d -> runners.forEach(r -> r.start(runningNetwork)));
  }

  /**
   * Runs the test for time configured via environment variable. If environment variable is missing
   * then default duration is used. Returns either once the duration has passed or if a check has
   * failed. Returns a map from the check name to the result.
   *
   * @return map of check results
   */
  public RunningSimulationTest run() {
    return run(getConfiguredDuration(), ImmutableMap.of());
  }

  public RunningSimulationTest run(Duration duration) {
    return run(duration, ImmutableMap.of());
  }

  /**
   * Get test duration.
   *
   * @return configured test duration.
   */
  public static Duration getConfiguredDuration() {
    return Optional.ofNullable(System.getenv(ENVIRONMENT_VAR_NAME))
        .flatMap(DurationParser::parse)
        .orElse(DEFAULT_TEST_DURATION);
  }

  public ImmutableList<ECKeyPair> getInitialNodes() {
    return initialNodes;
  }

  /**
   * Runs the test for a given time. Returns either once the duration has passed or if a check has
   * failed. Returns a map from the check name to the result.
   *
   * @param duration duration to run test for
   * @param disabledModuleRunners a list of disabled module runners by node key
   * @return test results
   */
  public RunningSimulationTest run(
      Duration duration, ImmutableMap<BFTNode, ImmutableSet<String>> disabledModuleRunners) {
    Injector testInjector = Guice.createInjector(testModule);
    var runners =
        testInjector.getInstance(Key.get(new TypeLiteral<Set<SimulationNetworkActor>>() {}));
    var checkers =
        testInjector.getInstance(Key.get(new TypeLiteral<Map<Monitor, TestInvariant>>() {}));

    SimulationNodes bftNetwork =
        new SimulationNodes(initialNodes, simulationNetwork, baseNodeModule, overrideModules);
    SimulationNodes.RunningNetwork runningNetwork = bftNetwork.start(disabledModuleRunners);

    final var resultObservable =
        runChecks(runners, checkers, runningNetwork, duration)
            .doFinally(
                () -> {
                  runners.forEach(SimulationNetworkActor::stop);
                  runningNetwork.stop();
                });
    return new RunningSimulationTest(resultObservable, runningNetwork);
  }

  private static <T> Iterator<T> repeatLast(Iterable<T> iterable) {
    final var iterator = iterable.iterator();
    if (!iterator.hasNext()) {
      throw new IllegalArgumentException("Can't repeat an empty iterable");
    }
    return new Iterator<>() {
      T lastValue = null;

      @Override
      public boolean hasNext() {
        return true;
      }

      @Override
      public T next() {
        if (iterator.hasNext()) {
          this.lastValue = iterator.next();
        }
        return this.lastValue;
      }
    };
  }

  public static final class RunningSimulationTest {

    private final Observable<Pair<Monitor, Optional<TestInvariantError>>> resultObservable;
    private final SimulationNodes.RunningNetwork network;

    private RunningSimulationTest(
        Observable<Pair<Monitor, Optional<TestInvariantError>>> resultObservable,
        SimulationNodes.RunningNetwork network) {
      this.resultObservable = resultObservable;
      this.network = network;
    }

    public SimulationNodes.RunningNetwork getNetwork() {
      return network;
    }

    public List<Injector> getNodeInjectors() {
      return network.getNodes().stream().map(network::getNodeInjector).collect(Collectors.toList());
    }

    public Map<Monitor, Optional<TestInvariantError>> awaitCompletion() {
      return this.resultObservable
          .blockingStream()
          .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }
  }
}
