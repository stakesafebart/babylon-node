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
import com.radixdlt.api.core.generated.models.EcdsaSecp256k1PublicKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * NextEpoch
 */
@JsonPropertyOrder({
  NextEpoch.JSON_PROPERTY_EPOCH,
  NextEpoch.JSON_PROPERTY_VALIDATORS
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class NextEpoch {
  public static final String JSON_PROPERTY_EPOCH = "epoch";
  private Long epoch;

  public static final String JSON_PROPERTY_VALIDATORS = "validators";
  private List<EcdsaSecp256k1PublicKey> validators = new ArrayList<>();

  public NextEpoch() { 
  }

  public NextEpoch epoch(Long epoch) {
    this.epoch = epoch;
    return this;
  }

   /**
   * An integer between &#x60;0&#x60; and &#x60;10^10&#x60;
   * minimum: 0
   * maximum: 10000000000
   * @return epoch
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "An integer between `0` and `10^10`")
  @JsonProperty(JSON_PROPERTY_EPOCH)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Long getEpoch() {
    return epoch;
  }


  @JsonProperty(JSON_PROPERTY_EPOCH)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setEpoch(Long epoch) {
    this.epoch = epoch;
  }


  public NextEpoch validators(List<EcdsaSecp256k1PublicKey> validators) {
    this.validators = validators;
    return this;
  }

  public NextEpoch addValidatorsItem(EcdsaSecp256k1PublicKey validatorsItem) {
    this.validators.add(validatorsItem);
    return this;
  }

   /**
   * Get validators
   * @return validators
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_VALIDATORS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<EcdsaSecp256k1PublicKey> getValidators() {
    return validators;
  }


  @JsonProperty(JSON_PROPERTY_VALIDATORS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setValidators(List<EcdsaSecp256k1PublicKey> validators) {
    this.validators = validators;
  }


  /**
   * Return true if this NextEpoch object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NextEpoch nextEpoch = (NextEpoch) o;
    return Objects.equals(this.epoch, nextEpoch.epoch) &&
        Objects.equals(this.validators, nextEpoch.validators);
  }

  @Override
  public int hashCode() {
    return Objects.hash(epoch, validators);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NextEpoch {\n");
    sb.append("    epoch: ").append(toIndentedString(epoch)).append("\n");
    sb.append("    validators: ").append(toIndentedString(validators)).append("\n");
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

