/*
 * Babylon Core API
 * This API is exposed by the Babylon Radix node to give clients access to the Radix Engine, Mempool and State in the node. It is intended for use by node-runners on a private network, and is not intended to be exposed publicly. Heavy load may impact the node's function.  If you require queries against historical ledger state, you may also wish to consider using the [Gateway API](https://betanet-gateway.redoc.ly/). 
 *
 * The version of the OpenAPI document: 0.2.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.radixdlt.api.core.generated.api;

import com.radixdlt.api.core.generated.client.ApiClient;
import com.radixdlt.api.core.generated.client.ApiException;
import com.radixdlt.api.core.generated.client.ApiResponse;
import com.radixdlt.api.core.generated.client.Pair;

import com.radixdlt.api.core.generated.models.ErrorResponse;
import com.radixdlt.api.core.generated.models.MempoolListRequest;
import com.radixdlt.api.core.generated.models.MempoolListResponse;
import com.radixdlt.api.core.generated.models.MempoolTransactionRequest;
import com.radixdlt.api.core.generated.models.MempoolTransactionResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class MempoolApi {
  private final HttpClient memberVarHttpClient;
  private final ObjectMapper memberVarObjectMapper;
  private final String memberVarBaseUri;
  private final Consumer<HttpRequest.Builder> memberVarInterceptor;
  private final Duration memberVarReadTimeout;
  private final Consumer<HttpResponse<InputStream>> memberVarResponseInterceptor;
  private final Consumer<HttpResponse<String>> memberVarAsyncResponseInterceptor;

  public MempoolApi() {
    this(new ApiClient());
  }

  public MempoolApi(ApiClient apiClient) {
    memberVarHttpClient = apiClient.getHttpClient();
    memberVarObjectMapper = apiClient.getObjectMapper();
    memberVarBaseUri = apiClient.getBaseUri();
    memberVarInterceptor = apiClient.getRequestInterceptor();
    memberVarReadTimeout = apiClient.getReadTimeout();
    memberVarResponseInterceptor = apiClient.getResponseInterceptor();
    memberVarAsyncResponseInterceptor = apiClient.getAsyncResponseInterceptor();
  }

  protected ApiException getApiException(String operationId, HttpResponse<InputStream> response) throws IOException {
    String body = response.body() == null ? null : new String(response.body().readAllBytes());
    String message = formatExceptionMessage(operationId, response.statusCode(), body);
    return new ApiException(response.statusCode(), message, response.headers(), body);
  }

  private String formatExceptionMessage(String operationId, int statusCode, String body) {
    if (body == null || body.isEmpty()) {
      body = "[no body]";
    }
    return operationId + " call failed with: " + statusCode + " - " + body;
  }

  /**
   * Get Mempool List
   * Returns the hashes of all the transactions currently in the mempool
   * @param mempoolListRequest  (required)
   * @return MempoolListResponse
   * @throws ApiException if fails to make API call
   */
  public MempoolListResponse mempoolListPost(MempoolListRequest mempoolListRequest) throws ApiException {
    ApiResponse<MempoolListResponse> localVarResponse = mempoolListPostWithHttpInfo(mempoolListRequest);
    return localVarResponse.getData();
  }

  /**
   * Get Mempool List
   * Returns the hashes of all the transactions currently in the mempool
   * @param mempoolListRequest  (required)
   * @return ApiResponse&lt;MempoolListResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<MempoolListResponse> mempoolListPostWithHttpInfo(MempoolListRequest mempoolListRequest) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = mempoolListPostRequestBuilder(mempoolListRequest);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("mempoolListPost", localVarResponse);
        }
        return new ApiResponse<MempoolListResponse>(
          localVarResponse.statusCode(),
          localVarResponse.headers().map(),
          memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<MempoolListResponse>() {}) // closes the InputStream
          
        );
      } finally {
      }
    } catch (IOException e) {
      throw new ApiException(e);
    }
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ApiException(e);
    }
  }

  private HttpRequest.Builder mempoolListPostRequestBuilder(MempoolListRequest mempoolListRequest) throws ApiException {
    // verify the required parameter 'mempoolListRequest' is set
    if (mempoolListRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'mempoolListRequest' when calling mempoolListPost");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/mempool/list";

    localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(mempoolListRequest);
      localVarRequestBuilder.method("POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }
  /**
   * Get Mempool Transaction
   * Returns the payload of a transaction currently in the mempool
   * @param mempoolTransactionRequest  (required)
   * @return MempoolTransactionResponse
   * @throws ApiException if fails to make API call
   */
  public MempoolTransactionResponse mempoolTransactionPost(MempoolTransactionRequest mempoolTransactionRequest) throws ApiException {
    ApiResponse<MempoolTransactionResponse> localVarResponse = mempoolTransactionPostWithHttpInfo(mempoolTransactionRequest);
    return localVarResponse.getData();
  }

  /**
   * Get Mempool Transaction
   * Returns the payload of a transaction currently in the mempool
   * @param mempoolTransactionRequest  (required)
   * @return ApiResponse&lt;MempoolTransactionResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<MempoolTransactionResponse> mempoolTransactionPostWithHttpInfo(MempoolTransactionRequest mempoolTransactionRequest) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = mempoolTransactionPostRequestBuilder(mempoolTransactionRequest);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("mempoolTransactionPost", localVarResponse);
        }
        return new ApiResponse<MempoolTransactionResponse>(
          localVarResponse.statusCode(),
          localVarResponse.headers().map(),
          memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<MempoolTransactionResponse>() {}) // closes the InputStream
          
        );
      } finally {
      }
    } catch (IOException e) {
      throw new ApiException(e);
    }
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ApiException(e);
    }
  }

  private HttpRequest.Builder mempoolTransactionPostRequestBuilder(MempoolTransactionRequest mempoolTransactionRequest) throws ApiException {
    // verify the required parameter 'mempoolTransactionRequest' is set
    if (mempoolTransactionRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'mempoolTransactionRequest' when calling mempoolTransactionPost");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/mempool/transaction";

    localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(mempoolTransactionRequest);
      localVarRequestBuilder.method("POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }
}
