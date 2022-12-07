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
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.radixdlt.api.core.generated.models.AmountOfProofRuleAllOf;
import com.radixdlt.api.core.generated.models.DynamicAmount;
import com.radixdlt.api.core.generated.models.DynamicResourceDescriptor;
import com.radixdlt.api.core.generated.models.ProofRuleBase;
import com.radixdlt.api.core.generated.models.ProofRuleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * AmountOfProofRule
 */
@JsonPropertyOrder({
  AmountOfProofRule.JSON_PROPERTY_TYPE,
  AmountOfProofRule.JSON_PROPERTY_AMOUNT,
  AmountOfProofRule.JSON_PROPERTY_RESOURCE
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class AmountOfProofRule {
  public static final String JSON_PROPERTY_TYPE = "type";
  private ProofRuleType type;

  public static final String JSON_PROPERTY_AMOUNT = "amount";
  private DynamicAmount amount;

  public static final String JSON_PROPERTY_RESOURCE = "resource";
  private DynamicResourceDescriptor resource;

  public AmountOfProofRule() { 
  }

  public AmountOfProofRule type(ProofRuleType type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public ProofRuleType getType() {
    return type;
  }


  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setType(ProofRuleType type) {
    this.type = type;
  }


  public AmountOfProofRule amount(DynamicAmount amount) {
    this.amount = amount;
    return this;
  }

   /**
   * Get amount
   * @return amount
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_AMOUNT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public DynamicAmount getAmount() {
    return amount;
  }


  @JsonProperty(JSON_PROPERTY_AMOUNT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setAmount(DynamicAmount amount) {
    this.amount = amount;
  }


  public AmountOfProofRule resource(DynamicResourceDescriptor resource) {
    this.resource = resource;
    return this;
  }

   /**
   * Get resource
   * @return resource
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_RESOURCE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public DynamicResourceDescriptor getResource() {
    return resource;
  }


  @JsonProperty(JSON_PROPERTY_RESOURCE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setResource(DynamicResourceDescriptor resource) {
    this.resource = resource;
  }


  /**
   * Return true if this AmountOfProofRule object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AmountOfProofRule amountOfProofRule = (AmountOfProofRule) o;
    return Objects.equals(this.type, amountOfProofRule.type) &&
        Objects.equals(this.amount, amountOfProofRule.amount) &&
        Objects.equals(this.resource, amountOfProofRule.resource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, amount, resource);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AmountOfProofRule {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
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
