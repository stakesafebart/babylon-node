package com.radixdlt.client.application.translate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.radixdlt.client.application.actions.DataStore;
import com.radixdlt.client.application.objects.Data;
import com.radixdlt.client.core.atoms.Atom;
import com.radixdlt.client.core.atoms.AtomBuilder;
import com.radixdlt.client.core.atoms.DataParticle;
import com.radixdlt.client.core.atoms.EncryptorParticle;
import com.radixdlt.client.core.atoms.Payload;
import com.radixdlt.client.core.crypto.EncryptedPrivateKey;
import com.radixdlt.client.core.crypto.Encryptor;
import io.reactivex.Completable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DataStoreTranslator {
	private static final DataStoreTranslator INSTANCE = new DataStoreTranslator();
	private static final JsonParser parser = new JsonParser();

	public static DataStoreTranslator getInstance() {
		return INSTANCE;
	}

	private DataStoreTranslator() {
	}

	// TODO: figure out correct method signature here (return Single<AtomBuilder> instead?)
	public Completable translate(DataStore dataStore, AtomBuilder atomBuilder) {
		Payload payload = new Payload(dataStore.getData().getBytes());
		String application = (String) dataStore.getData().getMetaData().get("application");

		atomBuilder.setDataParticle(new DataParticle(payload, application));
		Encryptor encryptor = dataStore.getData().getEncryptor();
		if (encryptor != null) {
			JsonArray protectorsJson = new JsonArray();
			encryptor.getProtectors().stream().map(EncryptedPrivateKey::base64).forEach(protectorsJson::add);

			Payload encryptorPayload = new Payload(protectorsJson.toString().getBytes(StandardCharsets.UTF_8));
			DataParticle encryptorParticle = new DataParticle(encryptorPayload, "encryptor");
			atomBuilder.setEncryptorParticle(encryptorParticle);
		}
		dataStore.getAddresses().forEach(atomBuilder::addDestination);

		return Completable.complete();
	}

	public Optional<Data> fromAtom(Atom atom) {
		if (atom.getDataParticle() == null) {
			return Optional.empty();
		}

		// TODO: don't pass in maps, utilize a metadata builder?
		Map<String, Object> metaData = new HashMap<>();
		metaData.put("timestamp", atom.getTimestamp());
		metaData.put("signatures", atom.getSignatures());
		metaData.compute("application", (k, v) -> atom.getDataParticle().getMetaData("application"));
		metaData.put("encrypted", atom.getEncryptor() != null);

		final Encryptor encryptor;
		if (atom.getEncryptor() != null) {
			JsonArray protectorsJson = parser.parse(atom.getEncryptor().getBytes().toUtf8()).getAsJsonArray();
			List<EncryptedPrivateKey> protectors = new ArrayList<>();
			protectorsJson.forEach(protectorJson -> protectors.add(EncryptedPrivateKey.fromBase64(protectorJson.getAsString())));
			encryptor = new Encryptor(protectors);
		} else {
			encryptor = null;
		}

		return Optional.of(Data.raw(atom.getDataParticle().getBytes().getBytes(), metaData, encryptor));
	}
}
