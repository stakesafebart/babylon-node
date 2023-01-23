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
import com.radixdlt.api.core.generated.models.Substate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * StatePackageResponse
 */
@JsonPropertyOrder({
  StatePackageResponse.JSON_PROPERTY_INFO,
  StatePackageResponse.JSON_PROPERTY_ROYALTY_CONFIG,
  StatePackageResponse.JSON_PROPERTY_ROYALTY_ACCUMULATOR,
  StatePackageResponse.JSON_PROPERTY_METADATA,
  StatePackageResponse.JSON_PROPERTY_ACCESS_RULES
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class StatePackageResponse {
  public static final String JSON_PROPERTY_INFO = "info";
  private Substate info;

  public static final String JSON_PROPERTY_ROYALTY_CONFIG = "royalty_config";
  private Substate royaltyConfig;

  public static final String JSON_PROPERTY_ROYALTY_ACCUMULATOR = "royalty_accumulator";
  private Substate royaltyAccumulator;

  public static final String JSON_PROPERTY_METADATA = "metadata";
  private Substate metadata;

  public static final String JSON_PROPERTY_ACCESS_RULES = "access_rules";
  private Substate accessRules;

  public StatePackageResponse() { 
  }

  public StatePackageResponse info(Substate info) {
    this.info = info;
    return this;
  }

   /**
   * Get info
   * @return info
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_INFO)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Substate getInfo() {
    return info;
  }


  @JsonProperty(JSON_PROPERTY_INFO)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setInfo(Substate info) {
    this.info = info;
  }


  public StatePackageResponse royaltyConfig(Substate royaltyConfig) {
    this.royaltyConfig = royaltyConfig;
    return this;
  }

   /**
   * Get royaltyConfig
   * @return royaltyConfig
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_ROYALTY_CONFIG)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Substate getRoyaltyConfig() {
    return royaltyConfig;
  }


  @JsonProperty(JSON_PROPERTY_ROYALTY_CONFIG)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setRoyaltyConfig(Substate royaltyConfig) {
    this.royaltyConfig = royaltyConfig;
  }


  public StatePackageResponse royaltyAccumulator(Substate royaltyAccumulator) {
    this.royaltyAccumulator = royaltyAccumulator;
    return this;
  }

   /**
   * Get royaltyAccumulator
   * @return royaltyAccumulator
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_ROYALTY_ACCUMULATOR)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Substate getRoyaltyAccumulator() {
    return royaltyAccumulator;
  }


  @JsonProperty(JSON_PROPERTY_ROYALTY_ACCUMULATOR)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setRoyaltyAccumulator(Substate royaltyAccumulator) {
    this.royaltyAccumulator = royaltyAccumulator;
  }


  public StatePackageResponse metadata(Substate metadata) {
    this.metadata = metadata;
    return this;
  }

   /**
   * Get metadata
   * @return metadata
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_METADATA)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Substate getMetadata() {
    return metadata;
  }


  @JsonProperty(JSON_PROPERTY_METADATA)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setMetadata(Substate metadata) {
    this.metadata = metadata;
  }


  public StatePackageResponse accessRules(Substate accessRules) {
    this.accessRules = accessRules;
    return this;
  }

   /**
   * Get accessRules
   * @return accessRules
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_ACCESS_RULES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Substate getAccessRules() {
    return accessRules;
  }


  @JsonProperty(JSON_PROPERTY_ACCESS_RULES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setAccessRules(Substate accessRules) {
    this.accessRules = accessRules;
  }


  /**
   * Return true if this StatePackageResponse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatePackageResponse statePackageResponse = (StatePackageResponse) o;
    return Objects.equals(this.info, statePackageResponse.info) &&
        Objects.equals(this.royaltyConfig, statePackageResponse.royaltyConfig) &&
        Objects.equals(this.royaltyAccumulator, statePackageResponse.royaltyAccumulator) &&
        Objects.equals(this.metadata, statePackageResponse.metadata) &&
        Objects.equals(this.accessRules, statePackageResponse.accessRules);
  }

  @Override
  public int hashCode() {
    return Objects.hash(info, royaltyConfig, royaltyAccumulator, metadata, accessRules);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StatePackageResponse {\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("    royaltyConfig: ").append(toIndentedString(royaltyConfig)).append("\n");
    sb.append("    royaltyAccumulator: ").append(toIndentedString(royaltyAccumulator)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    accessRules: ").append(toIndentedString(accessRules)).append("\n");
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
