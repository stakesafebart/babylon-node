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
import com.radixdlt.api.core.generated.models.DataStruct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Only present if the non fungible isn&#39;t deleted. NOTE: Currently there is no schema for mutable/immutable data, and it&#39;s not even guaranteed to be SBOR-encoded. But from scrypto, it likely always will be. For now, immutable_data / mutable_data is optional, and only included if the data is valid SBOR. When the payload is validated, &#x60;immutable_data_raw_hex&#x60; / &#x60;mutable_data_raw_hex&#x60; will be removed. 
 */
@ApiModel(description = "Only present if the non fungible isn't deleted. NOTE: Currently there is no schema for mutable/immutable data, and it's not even guaranteed to be SBOR-encoded. But from scrypto, it likely always will be. For now, immutable_data / mutable_data is optional, and only included if the data is valid SBOR. When the payload is validated, `immutable_data_raw_hex` / `mutable_data_raw_hex` will be removed. ")
@JsonPropertyOrder({
  NonFungibleData.JSON_PROPERTY_IMMUTABLE_DATA,
  NonFungibleData.JSON_PROPERTY_IMMUTABLE_DATA_RAW_HEX,
  NonFungibleData.JSON_PROPERTY_MUTABLE_DATA,
  NonFungibleData.JSON_PROPERTY_MUTABLE_DATA_RAW_HEX
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class NonFungibleData {
  public static final String JSON_PROPERTY_IMMUTABLE_DATA = "immutable_data";
  private DataStruct immutableData;

  public static final String JSON_PROPERTY_IMMUTABLE_DATA_RAW_HEX = "immutable_data_raw_hex";
  private String immutableDataRawHex;

  public static final String JSON_PROPERTY_MUTABLE_DATA = "mutable_data";
  private DataStruct mutableData;

  public static final String JSON_PROPERTY_MUTABLE_DATA_RAW_HEX = "mutable_data_raw_hex";
  private String mutableDataRawHex;

  public NonFungibleData() { 
  }

  public NonFungibleData immutableData(DataStruct immutableData) {
    this.immutableData = immutableData;
    return this;
  }

   /**
   * Get immutableData
   * @return immutableData
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_IMMUTABLE_DATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public DataStruct getImmutableData() {
    return immutableData;
  }


  @JsonProperty(JSON_PROPERTY_IMMUTABLE_DATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setImmutableData(DataStruct immutableData) {
    this.immutableData = immutableData;
  }


  public NonFungibleData immutableDataRawHex(String immutableDataRawHex) {
    this.immutableDataRawHex = immutableDataRawHex;
    return this;
  }

   /**
   * The hex-encoded raw bytes of the immutable data of the NF. 
   * @return immutableDataRawHex
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The hex-encoded raw bytes of the immutable data of the NF. ")
  @JsonProperty(JSON_PROPERTY_IMMUTABLE_DATA_RAW_HEX)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getImmutableDataRawHex() {
    return immutableDataRawHex;
  }


  @JsonProperty(JSON_PROPERTY_IMMUTABLE_DATA_RAW_HEX)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setImmutableDataRawHex(String immutableDataRawHex) {
    this.immutableDataRawHex = immutableDataRawHex;
  }


  public NonFungibleData mutableData(DataStruct mutableData) {
    this.mutableData = mutableData;
    return this;
  }

   /**
   * Get mutableData
   * @return mutableData
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_MUTABLE_DATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public DataStruct getMutableData() {
    return mutableData;
  }


  @JsonProperty(JSON_PROPERTY_MUTABLE_DATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMutableData(DataStruct mutableData) {
    this.mutableData = mutableData;
  }


  public NonFungibleData mutableDataRawHex(String mutableDataRawHex) {
    this.mutableDataRawHex = mutableDataRawHex;
    return this;
  }

   /**
   * The hex-encoded raw bytes of the mutadata data of the NF. 
   * @return mutableDataRawHex
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The hex-encoded raw bytes of the mutadata data of the NF. ")
  @JsonProperty(JSON_PROPERTY_MUTABLE_DATA_RAW_HEX)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getMutableDataRawHex() {
    return mutableDataRawHex;
  }


  @JsonProperty(JSON_PROPERTY_MUTABLE_DATA_RAW_HEX)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setMutableDataRawHex(String mutableDataRawHex) {
    this.mutableDataRawHex = mutableDataRawHex;
  }


  /**
   * Return true if this NonFungibleData object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NonFungibleData nonFungibleData = (NonFungibleData) o;
    return Objects.equals(this.immutableData, nonFungibleData.immutableData) &&
        Objects.equals(this.immutableDataRawHex, nonFungibleData.immutableDataRawHex) &&
        Objects.equals(this.mutableData, nonFungibleData.mutableData) &&
        Objects.equals(this.mutableDataRawHex, nonFungibleData.mutableDataRawHex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(immutableData, immutableDataRawHex, mutableData, mutableDataRawHex);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NonFungibleData {\n");
    sb.append("    immutableData: ").append(toIndentedString(immutableData)).append("\n");
    sb.append("    immutableDataRawHex: ").append(toIndentedString(immutableDataRawHex)).append("\n");
    sb.append("    mutableData: ").append(toIndentedString(mutableData)).append("\n");
    sb.append("    mutableDataRawHex: ").append(toIndentedString(mutableDataRawHex)).append("\n");
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

