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

import com.radixdlt.api.core.generated.models.BasicErrorResponse;
import com.radixdlt.api.core.generated.models.StateAccessControllerRequest;
import com.radixdlt.api.core.generated.models.StateAccessControllerResponse;
import com.radixdlt.api.core.generated.models.StateClockRequest;
import com.radixdlt.api.core.generated.models.StateClockResponse;
import com.radixdlt.api.core.generated.models.StateComponentRequest;
import com.radixdlt.api.core.generated.models.StateComponentResponse;
import com.radixdlt.api.core.generated.models.StateEpochRequest;
import com.radixdlt.api.core.generated.models.StateEpochResponse;
import com.radixdlt.api.core.generated.models.StateNonFungibleRequest;
import com.radixdlt.api.core.generated.models.StateNonFungibleResponse;
import com.radixdlt.api.core.generated.models.StatePackageRequest;
import com.radixdlt.api.core.generated.models.StatePackageResponse;
import com.radixdlt.api.core.generated.models.StateResourceRequest;
import com.radixdlt.api.core.generated.models.StateResourceResponse;
import com.radixdlt.api.core.generated.models.StateValidatorRequest;
import com.radixdlt.api.core.generated.models.StateValidatorResponse;

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
public class StateApi {
  private final HttpClient memberVarHttpClient;
  private final ObjectMapper memberVarObjectMapper;
  private final String memberVarBaseUri;
  private final Consumer<HttpRequest.Builder> memberVarInterceptor;
  private final Duration memberVarReadTimeout;
  private final Consumer<HttpResponse<InputStream>> memberVarResponseInterceptor;
  private final Consumer<HttpResponse<String>> memberVarAsyncResponseInterceptor;

  public StateApi() {
    this(new ApiClient());
  }

  public StateApi(ApiClient apiClient) {
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
   * Get Access Controller Details
   * Reads the access controller&#39;s substate/s from the top of the current ledger. 
   * @param stateAccessControllerRequest  (required)
   * @return StateAccessControllerResponse
   * @throws ApiException if fails to make API call
   */
  public StateAccessControllerResponse stateAccessControllerPost(StateAccessControllerRequest stateAccessControllerRequest) throws ApiException {
    ApiResponse<StateAccessControllerResponse> localVarResponse = stateAccessControllerPostWithHttpInfo(stateAccessControllerRequest);
    return localVarResponse.getData();
  }

  /**
   * Get Access Controller Details
   * Reads the access controller&#39;s substate/s from the top of the current ledger. 
   * @param stateAccessControllerRequest  (required)
   * @return ApiResponse&lt;StateAccessControllerResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<StateAccessControllerResponse> stateAccessControllerPostWithHttpInfo(StateAccessControllerRequest stateAccessControllerRequest) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = stateAccessControllerPostRequestBuilder(stateAccessControllerRequest);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("stateAccessControllerPost", localVarResponse);
        }
        return new ApiResponse<StateAccessControllerResponse>(
          localVarResponse.statusCode(),
          localVarResponse.headers().map(),
          memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<StateAccessControllerResponse>() {}) // closes the InputStream
          
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

  private HttpRequest.Builder stateAccessControllerPostRequestBuilder(StateAccessControllerRequest stateAccessControllerRequest) throws ApiException {
    // verify the required parameter 'stateAccessControllerRequest' is set
    if (stateAccessControllerRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'stateAccessControllerRequest' when calling stateAccessControllerPost");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/state/access-controller";

    localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(stateAccessControllerRequest);
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
   * Get Clock Details
   * Reads the clock&#39;s substate/s from the top of the current ledger. 
   * @param stateClockRequest  (required)
   * @return StateClockResponse
   * @throws ApiException if fails to make API call
   */
  public StateClockResponse stateClockPost(StateClockRequest stateClockRequest) throws ApiException {
    ApiResponse<StateClockResponse> localVarResponse = stateClockPostWithHttpInfo(stateClockRequest);
    return localVarResponse.getData();
  }

  /**
   * Get Clock Details
   * Reads the clock&#39;s substate/s from the top of the current ledger. 
   * @param stateClockRequest  (required)
   * @return ApiResponse&lt;StateClockResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<StateClockResponse> stateClockPostWithHttpInfo(StateClockRequest stateClockRequest) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = stateClockPostRequestBuilder(stateClockRequest);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("stateClockPost", localVarResponse);
        }
        return new ApiResponse<StateClockResponse>(
          localVarResponse.statusCode(),
          localVarResponse.headers().map(),
          memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<StateClockResponse>() {}) // closes the InputStream
          
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

  private HttpRequest.Builder stateClockPostRequestBuilder(StateClockRequest stateClockRequest) throws ApiException {
    // verify the required parameter 'stateClockRequest' is set
    if (stateClockRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'stateClockRequest' when calling stateClockPost");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/state/clock";

    localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(stateClockRequest);
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
   * Get Component Details
   * Reads the component&#39;s substate/s from the top of the current ledger. Also recursively extracts vault balance totals from the component&#39;s entity subtree. 
   * @param stateComponentRequest  (required)
   * @return StateComponentResponse
   * @throws ApiException if fails to make API call
   */
  public StateComponentResponse stateComponentPost(StateComponentRequest stateComponentRequest) throws ApiException {
    ApiResponse<StateComponentResponse> localVarResponse = stateComponentPostWithHttpInfo(stateComponentRequest);
    return localVarResponse.getData();
  }

  /**
   * Get Component Details
   * Reads the component&#39;s substate/s from the top of the current ledger. Also recursively extracts vault balance totals from the component&#39;s entity subtree. 
   * @param stateComponentRequest  (required)
   * @return ApiResponse&lt;StateComponentResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<StateComponentResponse> stateComponentPostWithHttpInfo(StateComponentRequest stateComponentRequest) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = stateComponentPostRequestBuilder(stateComponentRequest);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("stateComponentPost", localVarResponse);
        }
        return new ApiResponse<StateComponentResponse>(
          localVarResponse.statusCode(),
          localVarResponse.headers().map(),
          memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<StateComponentResponse>() {}) // closes the InputStream
          
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

  private HttpRequest.Builder stateComponentPostRequestBuilder(StateComponentRequest stateComponentRequest) throws ApiException {
    // verify the required parameter 'stateComponentRequest' is set
    if (stateComponentRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'stateComponentRequest' when calling stateComponentPost");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/state/component";

    localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(stateComponentRequest);
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
   * Get Epoch Details
   * Reads the epoch manager&#39;s substate/s from the top of the current ledger. 
   * @param stateEpochRequest  (required)
   * @return StateEpochResponse
   * @throws ApiException if fails to make API call
   */
  public StateEpochResponse stateEpochPost(StateEpochRequest stateEpochRequest) throws ApiException {
    ApiResponse<StateEpochResponse> localVarResponse = stateEpochPostWithHttpInfo(stateEpochRequest);
    return localVarResponse.getData();
  }

  /**
   * Get Epoch Details
   * Reads the epoch manager&#39;s substate/s from the top of the current ledger. 
   * @param stateEpochRequest  (required)
   * @return ApiResponse&lt;StateEpochResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<StateEpochResponse> stateEpochPostWithHttpInfo(StateEpochRequest stateEpochRequest) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = stateEpochPostRequestBuilder(stateEpochRequest);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("stateEpochPost", localVarResponse);
        }
        return new ApiResponse<StateEpochResponse>(
          localVarResponse.statusCode(),
          localVarResponse.headers().map(),
          memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<StateEpochResponse>() {}) // closes the InputStream
          
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

  private HttpRequest.Builder stateEpochPostRequestBuilder(StateEpochRequest stateEpochRequest) throws ApiException {
    // verify the required parameter 'stateEpochRequest' is set
    if (stateEpochRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'stateEpochRequest' when calling stateEpochPost");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/state/epoch";

    localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(stateEpochRequest);
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
   * Get Non-Fungible Details
   * Reads the data associated with a single Non-Fungible Unit under a Non-Fungible Resource. 
   * @param stateNonFungibleRequest  (required)
   * @return StateNonFungibleResponse
   * @throws ApiException if fails to make API call
   */
  public StateNonFungibleResponse stateNonFungiblePost(StateNonFungibleRequest stateNonFungibleRequest) throws ApiException {
    ApiResponse<StateNonFungibleResponse> localVarResponse = stateNonFungiblePostWithHttpInfo(stateNonFungibleRequest);
    return localVarResponse.getData();
  }

  /**
   * Get Non-Fungible Details
   * Reads the data associated with a single Non-Fungible Unit under a Non-Fungible Resource. 
   * @param stateNonFungibleRequest  (required)
   * @return ApiResponse&lt;StateNonFungibleResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<StateNonFungibleResponse> stateNonFungiblePostWithHttpInfo(StateNonFungibleRequest stateNonFungibleRequest) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = stateNonFungiblePostRequestBuilder(stateNonFungibleRequest);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("stateNonFungiblePost", localVarResponse);
        }
        return new ApiResponse<StateNonFungibleResponse>(
          localVarResponse.statusCode(),
          localVarResponse.headers().map(),
          memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<StateNonFungibleResponse>() {}) // closes the InputStream
          
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

  private HttpRequest.Builder stateNonFungiblePostRequestBuilder(StateNonFungibleRequest stateNonFungibleRequest) throws ApiException {
    // verify the required parameter 'stateNonFungibleRequest' is set
    if (stateNonFungibleRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'stateNonFungibleRequest' when calling stateNonFungiblePost");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/state/non-fungible";

    localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(stateNonFungibleRequest);
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
   * Get Package Details
   * Reads the package&#39;s substate/s from the top of the current ledger. 
   * @param statePackageRequest  (required)
   * @return StatePackageResponse
   * @throws ApiException if fails to make API call
   */
  public StatePackageResponse statePackagePost(StatePackageRequest statePackageRequest) throws ApiException {
    ApiResponse<StatePackageResponse> localVarResponse = statePackagePostWithHttpInfo(statePackageRequest);
    return localVarResponse.getData();
  }

  /**
   * Get Package Details
   * Reads the package&#39;s substate/s from the top of the current ledger. 
   * @param statePackageRequest  (required)
   * @return ApiResponse&lt;StatePackageResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<StatePackageResponse> statePackagePostWithHttpInfo(StatePackageRequest statePackageRequest) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = statePackagePostRequestBuilder(statePackageRequest);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("statePackagePost", localVarResponse);
        }
        return new ApiResponse<StatePackageResponse>(
          localVarResponse.statusCode(),
          localVarResponse.headers().map(),
          memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<StatePackageResponse>() {}) // closes the InputStream
          
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

  private HttpRequest.Builder statePackagePostRequestBuilder(StatePackageRequest statePackageRequest) throws ApiException {
    // verify the required parameter 'statePackageRequest' is set
    if (statePackageRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'statePackageRequest' when calling statePackagePost");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/state/package";

    localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(statePackageRequest);
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
   * Get Resource Details
   * Reads the resource manager&#39;s substate/s from the top of the current ledger. 
   * @param stateResourceRequest  (required)
   * @return StateResourceResponse
   * @throws ApiException if fails to make API call
   */
  public StateResourceResponse stateResourcePost(StateResourceRequest stateResourceRequest) throws ApiException {
    ApiResponse<StateResourceResponse> localVarResponse = stateResourcePostWithHttpInfo(stateResourceRequest);
    return localVarResponse.getData();
  }

  /**
   * Get Resource Details
   * Reads the resource manager&#39;s substate/s from the top of the current ledger. 
   * @param stateResourceRequest  (required)
   * @return ApiResponse&lt;StateResourceResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<StateResourceResponse> stateResourcePostWithHttpInfo(StateResourceRequest stateResourceRequest) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = stateResourcePostRequestBuilder(stateResourceRequest);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("stateResourcePost", localVarResponse);
        }
        return new ApiResponse<StateResourceResponse>(
          localVarResponse.statusCode(),
          localVarResponse.headers().map(),
          memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<StateResourceResponse>() {}) // closes the InputStream
          
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

  private HttpRequest.Builder stateResourcePostRequestBuilder(StateResourceRequest stateResourceRequest) throws ApiException {
    // verify the required parameter 'stateResourceRequest' is set
    if (stateResourceRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'stateResourceRequest' when calling stateResourcePost");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/state/resource";

    localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(stateResourceRequest);
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
   * Get Validator Details
   * Reads the validator&#39;s substate/s from the top of the current ledger. 
   * @param stateValidatorRequest  (required)
   * @return StateValidatorResponse
   * @throws ApiException if fails to make API call
   */
  public StateValidatorResponse stateValidatorPost(StateValidatorRequest stateValidatorRequest) throws ApiException {
    ApiResponse<StateValidatorResponse> localVarResponse = stateValidatorPostWithHttpInfo(stateValidatorRequest);
    return localVarResponse.getData();
  }

  /**
   * Get Validator Details
   * Reads the validator&#39;s substate/s from the top of the current ledger. 
   * @param stateValidatorRequest  (required)
   * @return ApiResponse&lt;StateValidatorResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<StateValidatorResponse> stateValidatorPostWithHttpInfo(StateValidatorRequest stateValidatorRequest) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = stateValidatorPostRequestBuilder(stateValidatorRequest);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("stateValidatorPost", localVarResponse);
        }
        return new ApiResponse<StateValidatorResponse>(
          localVarResponse.statusCode(),
          localVarResponse.headers().map(),
          memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<StateValidatorResponse>() {}) // closes the InputStream
          
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

  private HttpRequest.Builder stateValidatorPostRequestBuilder(StateValidatorRequest stateValidatorRequest) throws ApiException {
    // verify the required parameter 'stateValidatorRequest' is set
    if (stateValidatorRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'stateValidatorRequest' when calling stateValidatorPost");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/state/validator";

    localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(stateValidatorRequest);
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
