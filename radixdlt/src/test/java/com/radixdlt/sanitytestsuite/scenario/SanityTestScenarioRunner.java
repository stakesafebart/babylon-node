/*
 * (C) Copyright 2020 Radix DLT Ltd
 *
 * Radix DLT Ltd licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the
 * License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.  See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.radixdlt.sanitytestsuite.scenario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radixdlt.sanitytestsuite.model.SanityTestSuiteRoot;
import com.radixdlt.sanitytestsuite.model.SanityTestVector;
import com.radixdlt.sanitytestsuite.model.UnknownTestVector;
import com.radixdlt.utils.JSONFormatter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public abstract class SanityTestScenarioRunner<TestVector extends SanityTestVector> {

	public abstract String testScenarioIdentifier();

	public abstract TypeReference<TestVector> testVectorTypeReference();

	public abstract void doRunTestVector(TestVector testVector) throws AssertionError;

	public void executeTest(SanityTestSuiteRoot.SanityTestSuite.SanityTestScenario scenario) {
		assertEquals(testScenarioIdentifier(), scenario.identifier);

		for (int testVectorIndex = 0; testVectorIndex < scenario.tests.vectors.size(); ++testVectorIndex) {
			UnknownTestVector untypedVector = scenario.tests.vectors.get(testVectorIndex);

			ObjectMapper mapper = new ObjectMapper();
			String unknownTestVectorJSONString = null;
			try {
				unknownTestVectorJSONString = mapper.writeValueAsString(untypedVector);
			} catch (JsonProcessingException e) {
				throw new IllegalStateException("Failed to write JSON from unknown type");
			}

			TestVector testVector = null;
			try {
				testVector = mapper.readValue(unknownTestVectorJSONString, this.testVectorTypeReference());
			} catch (JsonProcessingException e) {
				throw new IllegalStateException("Failed to cast, error: " + e);
			}

			try {
				doRunTestVector(testVector);
			} catch (AssertionError e) {
				String msg = String.format(
					"Failing test vector index: %d, vector: %s",
					testVectorIndex,
					JSONFormatter.sortPrettyPrintObject(testVector)
				);
				throw new AssertionError(msg, e);
			}
		}
	}

	public static byte[] sha256Hash(byte[] bytes) {
		MessageDigest hasher = null;
		try {
			hasher = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new AssertionError("Failed to run test, found no hasher", e);
		}
		hasher.update(bytes);
		return hasher.digest();
	}
}