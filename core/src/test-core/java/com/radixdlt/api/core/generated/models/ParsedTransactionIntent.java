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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.radixdlt.api.core.generated.models.ParsedTransactionIntentAllOf;
import com.radixdlt.api.core.generated.models.ParsedTransactionIntentAllOfIdentifiers;
import com.radixdlt.api.core.generated.models.ParsedTransactionType;
import com.radixdlt.api.core.generated.models.TransactionIntent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ParsedTransactionIntent
 */
@JsonPropertyOrder({
  ParsedTransactionIntent.JSON_PROPERTY_INTENT,
  ParsedTransactionIntent.JSON_PROPERTY_IDENTIFIERS
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ParsedTransactionIntent {
  public static final String JSON_PROPERTY_INTENT = "intent";
  private TransactionIntent intent;

  public static final String JSON_PROPERTY_IDENTIFIERS = "identifiers";
  private ParsedTransactionIntentAllOfIdentifiers identifiers;

  public ParsedTransactionIntent() { 
  }

  public ParsedTransactionIntent intent(TransactionIntent intent) {
    this.intent = intent;
    return this;
  }

   /**
   * Get intent
   * @return intent
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_INTENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public TransactionIntent getIntent() {
    return intent;
  }


  @JsonProperty(JSON_PROPERTY_INTENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIntent(TransactionIntent intent) {
    this.intent = intent;
  }


  public ParsedTransactionIntent identifiers(ParsedTransactionIntentAllOfIdentifiers identifiers) {
    this.identifiers = identifiers;
    return this;
  }

   /**
   * Get identifiers
   * @return identifiers
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_IDENTIFIERS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public ParsedTransactionIntentAllOfIdentifiers getIdentifiers() {
    return identifiers;
  }


  @JsonProperty(JSON_PROPERTY_IDENTIFIERS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setIdentifiers(ParsedTransactionIntentAllOfIdentifiers identifiers) {
    this.identifiers = identifiers;
  }


  /**
   * Return true if this ParsedTransactionIntent object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParsedTransactionIntent parsedTransactionIntent = (ParsedTransactionIntent) o;
    return Objects.equals(this.intent, parsedTransactionIntent.intent) &&
        Objects.equals(this.identifiers, parsedTransactionIntent.identifiers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(intent, identifiers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParsedTransactionIntent {\n");
    sb.append("    intent: ").append(toIndentedString(intent)).append("\n");
    sb.append("    identifiers: ").append(toIndentedString(identifiers)).append("\n");
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

}

