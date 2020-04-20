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

package com.radixdlt.consensus;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.radixdlt.counters.SystemCounters.CounterType;
import com.radixdlt.crypto.ECKeyPair;
import com.radixdlt.middleware2.network.TestEventCoordinatorNetwork;
import io.reactivex.rxjava3.core.Observable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.Condition;

public class BFTTest {
	private final long time;
	private final TimeUnit timeUnit;
	private final List<ECKeyPair> allNodes;
	private final BFTTestNetwork bftNetwork;
	private final List<Observable<Object>> assertions = new ArrayList<>();
	private static final int MINIMUM_NETWORK_LATENCY = 10;
	// 6 times max latency should be less than BFTTestNetwork.TEST_PACEMAKER_TIMEOUT
	// so we don't get unwanted pacemaker timeouts
	private static final int MAXIMUM_NETWORK_LATENCY = 160;

	public BFTTest(int numNodes, long time, TimeUnit timeUnit) {
		this.time = time;
		this.timeUnit = timeUnit;
		this.allNodes = Stream.generate(ECKeyPair::generateNew)
			.limit(numNodes)
			.collect(Collectors.toList());
		this.bftNetwork = createNetwork(allNodes);
	}

	static BFTTestNetwork createNetwork(List<ECKeyPair> nodes) {
		return new BFTTestNetwork(
			nodes,
			TestEventCoordinatorNetwork.orderedRandomlyLatent(MINIMUM_NETWORK_LATENCY, MAXIMUM_NETWORK_LATENCY)
		);
	}

	public void assertLiveness() {
		// there should be a new highest QC every once in a while to ensure progress
		// the minimum latency per round is determined using the network latency and a tolerance
		int worstCaseLatencyPerRound = bftNetwork.getMaximumNetworkLatency() * 2; // base latency: two rounds in the normal case
		// a round can consist of 6 * MTT
		double trips = 6.0;
		int maximumLatencyPerRound = (int) (worstCaseLatencyPerRound * trips);
		AtomicReference<View> highestQCView = new AtomicReference<>(View.genesis());
		Observable<Object> progressCheck = Observable.interval(maximumLatencyPerRound, maximumLatencyPerRound, TimeUnit.MILLISECONDS)
			.map(i -> allNodes.stream()
				.map(bftNetwork::getVertexStore)
				.map(VertexStore::getHighestQC)
				.map(QuorumCertificate::getView)
				.max(View::compareTo)
				.get()) // there must be some max highest QC unless allNodes is empty
			.doOnNext(view -> assertThat(view)
				.satisfies(new Condition<>(v -> v.compareTo(highestQCView.get()) > 0,
					"The highest highestQC %s increased since last highestQC %s after %d ms", view, highestQCView.get(), maximumLatencyPerRound)))
			.doOnNext(highestQCView::set)
			.doOnNext(newHighestQCView -> System.out.println("Progressed to new highest QC view " + highestQCView))
			.map(o -> o);
		this.assertions.add(progressCheck);
	}

	public void assertSafety() {
		// correct nodes should all get the same commits in the same order
		Observable<Object> correctCommitCheck = Observable.zip(
			allNodes.stream()
				.map(bftNetwork::getVertexStore)
				.map(VertexStore::lastCommittedVertex)
				.collect(Collectors.toList()),
			Arrays::stream)
			.map(committedVertices -> committedVertices.distinct().collect(Collectors.toList()))
			.doOnNext(committedVertices -> assertThat(committedVertices).hasSize(1))
			.map(vertices -> (Vertex) vertices.get(0))
			.map(o -> o);
		this.assertions.add(correctCommitCheck);
	}

	public void assertNoTimeouts() {
		// correct nodes should get no timeouts since max latency is smaller than timeout
		Observable<Object> correctTimeoutCheck = Observable.interval(1, TimeUnit.SECONDS)
			.flatMapIterable(i -> allNodes)
			.doOnNext(cn -> AssertionsForClassTypes.assertThat(bftNetwork.getCounters(cn).get(CounterType.CONSENSUS_TIMEOUT))
				.satisfies(new Condition<>(c -> c == 0,
					"Timeout counter is zero in correct node %s", cn.getPublicKey().euid())))
			.map(o -> o);
		this.assertions.add(correctTimeoutCheck);
	}

	public void assertNoSyncExceptions() {
		Observable<Object> syncExceptionCheck = Observable.interval(1, TimeUnit.SECONDS)
			.flatMapIterable(i -> allNodes)
			.doOnNext(cn -> AssertionsForClassTypes.assertThat(bftNetwork.getCounters(cn).get(CounterType.CONSENSUS_SYNC_EXCEPTION))
				.satisfies(new Condition<>(c -> c == 0,
					"Sync Exception counter is zero in correct node %s", cn.getPublicKey().euid())))
			.map(o -> o);
		this.assertions.add(syncExceptionCheck);
	}

	public void assertAllProposalsHaveDirectParents() {
		// TODO all? proposals should be direct
		List<Observable<Vertex>> correctProposals = allNodes.stream()
			.map(ECKeyPair::getPublicKey)
			.map(bftNetwork.getUnderlyingNetwork()::getNetworkRx)
			.map(EventCoordinatorNetworkRx::consensusEvents)
			.map(p -> p.ofType(Proposal.class).map(Proposal::getVertex))
			.collect(Collectors.toList());
		Observable<Object> directProposalsCheck = Observable.merge(correctProposals)
			.doOnNext(v -> AssertionsForClassTypes.assertThat(v)
				.satisfies(new Condition<>(vtx -> vtx.getView().equals(vtx.getParentView().next()),
					"Vertex %s at %s has direct parent",
					bftNetwork.getProposerElection().getProposer(v.getParentView()).euid(), v.getParentView())))
			.map(o -> o);

		this.assertions.add(directProposalsCheck);
	}

	public void run() {
		Observable.mergeArray(bftNetwork.processBFT(), Observable.merge(this.assertions))
			.take(time, timeUnit)
			.blockingSubscribe();
	}
}
