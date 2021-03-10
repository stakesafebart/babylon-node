/*
 * (C) Copyright 2021 Radix DLT Ltd
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
package org.radix.api.http;

import org.json.JSONObject;
import org.junit.Test;
import org.radix.api.jsonrpc.RadixJsonRpcServer;
import org.radix.api.services.SystemService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import io.undertow.io.Sender;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.util.HeaderMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RpcControllerTest {
	private final SystemService systemService = new SystemService(null, null, null, null);
	private final RadixJsonRpcServer jsonRpcServer = new RadixJsonRpcServer(null, null, systemService, null);
	private final RadixHttpWebsocketHandler websocketHandler = mock(RadixHttpWebsocketHandler.class);
	private final RpcController rpcController = new RpcController(jsonRpcServer, websocketHandler);

	@Test
	public void routesAreConfigured() {
		var handler = mock(RoutingHandler.class);

		rpcController.configureRoutes(handler);

		verify(handler).post(eq("/rpc"), any());
		verify(handler).post(eq("/rpc/"), any());
		verify(handler).get(eq("/rpc"), any());
	}

	@Test
	public void rpcRequestIsHandled() throws InterruptedException {
		var latch = new CountDownLatch(1);
		var arg = new AtomicReference<String>();
		var exchange = mock(HttpServerExchange.class);
		var sender = mock(Sender.class);

		when(exchange.isInIoThread()).thenReturn(true);
		when(exchange.getResponseHeaders()).thenReturn(mock(HeaderMap.class));
		when(exchange.getResponseSender()).thenReturn(sender);
		doAnswer(invocation -> {
			arg.set(invocation.getArgument(0, String.class));
			latch.countDown();
			return null;
		}).when(sender).send(anyString());

		when(exchange.getInputStream()).thenReturn(asStream("{\"id\":321, \"method\":\"Ping\"}"));
		when(exchange.dispatch(any(Runnable.class))).thenAnswer(invocation -> {
			var runnable = invocation.getArgument(0, Runnable.class);
			runnable.run();
			return exchange;
		});

		rpcController.handleRpc(exchange);

		latch.await();

		assertNotNull(arg.get());

		var obj = new JSONObject(arg.get());
		assertNotNull(obj);
		assertEquals(321, obj.getInt("id"));

		var result = obj.getJSONObject("result");
		assertNotNull(result);
		assertEquals("pong", result.getString("response"));
	}

	private static InputStream asStream(final String text) {
		return new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
	}
}