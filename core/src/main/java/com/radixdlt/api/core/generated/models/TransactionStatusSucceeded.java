/*
 * Babylon Core API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 0.1.0
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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.radixdlt.api.core.generated.models.TransactionStatus;
import com.radixdlt.api.core.generated.models.TransactionStatusFailed;
import com.radixdlt.api.core.generated.models.TransactionStatusRejected;
import com.radixdlt.api.core.generated.models.TransactionStatusSucceeded;
import com.radixdlt.api.core.generated.models.TransactionStatusSucceededAllOf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import com.radixdlt.api.common.JSON;
/**
 * TransactionStatusSucceeded
 */
@JsonPropertyOrder({
  TransactionStatusSucceeded.JSON_PROPERTY_OUTPUT
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = TransactionStatusFailed.class, name = "TransactionStatusFailed"),
  @JsonSubTypes.Type(value = TransactionStatusRejected.class, name = "TransactionStatusRejected"),
  @JsonSubTypes.Type(value = TransactionStatusSucceeded.class, name = "TransactionStatusSucceeded"),
})

public class TransactionStatusSucceeded extends TransactionStatus {
  public static final String JSON_PROPERTY_OUTPUT = "output";
  private List<String> output = new ArrayList<>();


  public TransactionStatusSucceeded output(List<String> output) {
    this.output = output;
    return this;
  }

  public TransactionStatusSucceeded addOutputItem(String outputItem) {
    this.output.add(outputItem);
    return this;
  }

   /**
   * Get output
   * @return output
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_OUTPUT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<String> getOutput() {
    return output;
  }


  @JsonProperty(JSON_PROPERTY_OUTPUT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setOutput(List<String> output) {
    this.output = output;
  }


  /**
   * Return true if this TransactionStatusSucceeded object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionStatusSucceeded transactionStatusSucceeded = (TransactionStatusSucceeded) o;
    return Objects.equals(this.output, transactionStatusSucceeded.output) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(output, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionStatusSucceeded {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    output: ").append(toIndentedString(output)).append("\n");
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
  mappings.put("TransactionStatusFailed", TransactionStatusFailed.class);
  mappings.put("TransactionStatusRejected", TransactionStatusRejected.class);
  mappings.put("TransactionStatusSucceeded", TransactionStatusSucceeded.class);
  mappings.put("TransactionStatusSucceeded", TransactionStatusSucceeded.class);
  JSON.registerDiscriminator(TransactionStatusSucceeded.class, "type", mappings);
}
}

