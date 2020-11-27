package com.radixdlt.sanitytestsuite.scenario;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.radixdlt.sanitytestsuite.SanityTestSuiteTestLoader;
import com.radixdlt.sanitytestsuite.model.SanityTestSuiteRoot;
import com.radixdlt.sanitytestsuite.model.SanityTestVector;
import com.radixdlt.sanitytestsuite.model.UnknownTestVector;
import com.radixdlt.utils.JSONFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public abstract class SanityTestScenarioRunner<Vector extends SanityTestVector> {

	private static final Logger log = LogManager.getLogger();

	public abstract String testScenarioIdentifier();

	public abstract TypeToken<Vector> typeOfVector();


	public abstract void doRunTestVector(Vector testVector) throws AssertionError;

	public void executeTest(SanityTestSuiteRoot.SanityTestSuite.SanityTestScenario scenario) {
		assertEquals(testScenarioIdentifier(), scenario.identifier);


		for (int testVectorIndex = 0; testVectorIndex < scenario.tests.vectors.size(); ++testVectorIndex) {
			UnknownTestVector untypedVector = scenario.tests.vectors.get(testVectorIndex);
			Vector testVector = cast(untypedVector, this.typeOfVector());
			try {
				doRunTestVector(testVector);
			} catch (AssertionError e) {
				throw new AssertionError(String.format("Failing test vector index: %d, vector: %s", testVectorIndex, prettyJsonStringFromObject(testVector)) , e);
			}
		}
	}

	protected static byte[] sha256Hash(byte[] bytes) {
		MessageDigest hasher = null;
		try {
			hasher = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new AssertionError("Failed to run test, found no hasher", e);
		}
		hasher.update(bytes);
		return hasher.digest();
	}

	private static final String prettyJsonStringFromObject(Object object) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Double.class, new SanityTestSuiteTestLoader.DoubleSerializer()).setPrettyPrinting().create();
		String jsonStringPretty = JSONFormatter.sortPrettyPrintJSONString(gson.toJson(object));

		return jsonStringPretty;
	}

	private static <T> T cast(Object object, TypeToken<T> typeToken) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Double.class, new SanityTestSuiteTestLoader.DoubleSerializer()).setPrettyPrinting().create();
		String jsonFromObj = gson.toJson(object);
		return gson.fromJson(jsonFromObj, typeToken.getType());
	}

}