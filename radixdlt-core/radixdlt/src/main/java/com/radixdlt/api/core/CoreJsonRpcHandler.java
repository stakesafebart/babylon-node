/*
 * Copyright 2021 Radix Publishing Ltd incorporated in Jersey (Channel Islands).
 * Licensed under the Radix License, Version 1.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at:
 *
 * radixfoundation.org/licenses/LICENSE-v1
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

package com.radixdlt.api.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radixdlt.api.core.model.CoreApiErrorCode;
import com.radixdlt.api.core.model.CoreApiException;
import com.radixdlt.api.core.openapitools.JSON;
import com.radixdlt.api.core.openapitools.model.InvalidJsonError;
import com.radixdlt.api.core.openapitools.model.UnexpectedError;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public abstract class CoreJsonRpcHandler<T, U> implements HttpHandler {
	private static final String CONTENT_TYPE_JSON = "application/json";
	private static final long DEFAULT_MAX_REQUEST_SIZE = 1024L * 1024L;

	private final Class<T> requestClass;
	private final ObjectMapper objectMapper;

	public CoreJsonRpcHandler(Class<T> requestClass) {
		this.requestClass = requestClass;
		this.objectMapper = JSON.getDefault().getMapper();
	}

	public abstract U handleRequest(T request) throws CoreApiException;

	@Override
	public final void handleRequest(HttpServerExchange exchange) throws Exception {
		if (exchange.isInIoThread()) {
			exchange.dispatch(this);
			return;
		}

		exchange.setMaxEntitySize(DEFAULT_MAX_REQUEST_SIZE);
		exchange.startBlocking();
		exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);

		T request;
		try {
			request = objectMapper.readValue(exchange.getInputStream(), requestClass);
		} catch (JsonMappingException | JsonParseException e) {
			var errorResponse = handleParseException(e);
			exchange.setStatusCode(500);
			exchange.getResponseSender().send(objectMapper.writeValueAsString(errorResponse));
			return;
		}

		U response;
		try {
			response = handleRequest(request);
		} catch (CoreApiException e) {
			var errorResponse = e.toError();
			exchange.setStatusCode(500);
			exchange.getResponseSender().send(objectMapper.writeValueAsString(errorResponse));
			return;
		}

		exchange.setStatusCode(200);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		exchange.getResponseSender().send(objectMapper.writeValueAsString(response));
	}

	public UnexpectedError handleParseException(Exception e) {
		return new UnexpectedError()
			.code(CoreApiErrorCode.BAD_REQUEST.getErrorCode())
			.message(CoreApiErrorCode.BAD_REQUEST.getMessage())
			.details(new InvalidJsonError()
				.cause(e.getMessage())
				.type("InvalidJsonError")
			);
	}
}
