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
import com.radixdlt.api.core.generated.models.LedgerTransaction;
import com.radixdlt.api.core.generated.models.LedgerTransactionType;
import com.radixdlt.api.core.generated.models.NotarizedTransaction;
import com.radixdlt.api.core.generated.models.SystemLedgerTransaction;
import com.radixdlt.api.core.generated.models.UserLedgerTransaction;
import com.radixdlt.api.core.generated.models.UserLedgerTransactionAllOf;
import com.radixdlt.api.core.generated.models.ValidatorLedgerTransaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import com.radixdlt.api.core.generated.client.JSON;
/**
 * UserLedgerTransaction
 */
@JsonPropertyOrder({
  UserLedgerTransaction.JSON_PROPERTY_NOTARIZED_TRANSACTION
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@JsonIgnoreProperties(
  value = "type", // ignore manually set type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = SystemLedgerTransaction.class, name = "System"),
  @JsonSubTypes.Type(value = UserLedgerTransaction.class, name = "User"),
  @JsonSubTypes.Type(value = ValidatorLedgerTransaction.class, name = "Validator"),
})

public class UserLedgerTransaction extends LedgerTransaction {
  public static final String JSON_PROPERTY_NOTARIZED_TRANSACTION = "notarized_transaction";
  private NotarizedTransaction notarizedTransaction;

  public UserLedgerTransaction() { 
  }

  public UserLedgerTransaction notarizedTransaction(NotarizedTransaction notarizedTransaction) {
    this.notarizedTransaction = notarizedTransaction;
    return this;
  }

   /**
   * Get notarizedTransaction
   * @return notarizedTransaction
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_NOTARIZED_TRANSACTION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public NotarizedTransaction getNotarizedTransaction() {
    return notarizedTransaction;
  }


  @JsonProperty(JSON_PROPERTY_NOTARIZED_TRANSACTION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setNotarizedTransaction(NotarizedTransaction notarizedTransaction) {
    this.notarizedTransaction = notarizedTransaction;
  }


  /**
   * Return true if this UserLedgerTransaction object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserLedgerTransaction userLedgerTransaction = (UserLedgerTransaction) o;
    return Objects.equals(this.notarizedTransaction, userLedgerTransaction.notarizedTransaction) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notarizedTransaction, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserLedgerTransaction {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    notarizedTransaction: ").append(toIndentedString(notarizedTransaction)).append("\n");
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
  mappings.put("System", SystemLedgerTransaction.class);
  mappings.put("User", UserLedgerTransaction.class);
  mappings.put("Validator", ValidatorLedgerTransaction.class);
  mappings.put("UserLedgerTransaction", UserLedgerTransaction.class);
  JSON.registerDiscriminator(UserLedgerTransaction.class, "type", mappings);
}
}

