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


package com.radixdlt.api.core.generated.models;

import java.util.Objects;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.radixdlt.api.core.generated.models.BasicErrorResponse;
import com.radixdlt.api.core.generated.models.ErrorResponse;
import com.radixdlt.api.core.generated.models.ErrorResponseType;
import com.radixdlt.api.core.generated.models.TransactionSubmitErrorDetails;
import com.radixdlt.api.core.generated.models.TransactionSubmitErrorResponse;
import com.radixdlt.api.core.generated.models.TransactionSubmitErrorResponseAllOf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import com.radixdlt.api.core.generated.client.JSON;
/**
 * TransactionSubmitErrorResponse
 */
@JsonPropertyOrder({
  TransactionSubmitErrorResponse.JSON_PROPERTY_DETAILS
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@JsonIgnoreProperties(
  value = "error_type", // ignore manually set error_type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the error_type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "error_type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = BasicErrorResponse.class, name = "Basic"),
  @JsonSubTypes.Type(value = TransactionSubmitErrorResponse.class, name = "TransactionSubmit"),
})

public class TransactionSubmitErrorResponse extends ErrorResponse {
  public static final String JSON_PROPERTY_DETAILS = "details";
  private TransactionSubmitErrorDetails details;

  public TransactionSubmitErrorResponse() { 
  }

  public TransactionSubmitErrorResponse details(TransactionSubmitErrorDetails details) {
    this.details = details;
    return this;
  }

   /**
   * Get details
   * @return details
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DETAILS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public TransactionSubmitErrorDetails getDetails() {
    return details;
  }


  @JsonProperty(JSON_PROPERTY_DETAILS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDetails(TransactionSubmitErrorDetails details) {
    this.details = details;
  }


  /**
   * Return true if this TransactionSubmitErrorResponse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionSubmitErrorResponse transactionSubmitErrorResponse = (TransactionSubmitErrorResponse) o;
    return Objects.equals(this.details, transactionSubmitErrorResponse.details) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(details, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionSubmitErrorResponse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("Basic", BasicErrorResponse.class);
  mappings.put("TransactionSubmit", TransactionSubmitErrorResponse.class);
  mappings.put("TransactionSubmitErrorResponse", TransactionSubmitErrorResponse.class);
  JSON.registerDiscriminator(TransactionSubmitErrorResponse.class, "error_type", mappings);
}
}

