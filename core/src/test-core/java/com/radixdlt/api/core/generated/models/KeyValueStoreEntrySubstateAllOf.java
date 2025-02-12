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
 * KeyValueStoreEntrySubstateAllOf
 */
@JsonPropertyOrder({
  KeyValueStoreEntrySubstateAllOf.JSON_PROPERTY_KEY_HEX,
  KeyValueStoreEntrySubstateAllOf.JSON_PROPERTY_IS_DELETED,
  KeyValueStoreEntrySubstateAllOf.JSON_PROPERTY_DATA_STRUCT
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class KeyValueStoreEntrySubstateAllOf {
  public static final String JSON_PROPERTY_KEY_HEX = "key_hex";
  private String keyHex;

  public static final String JSON_PROPERTY_IS_DELETED = "is_deleted";
  private Boolean isDeleted;

  public static final String JSON_PROPERTY_DATA_STRUCT = "data_struct";
  private DataStruct dataStruct;

  public KeyValueStoreEntrySubstateAllOf() { 
  }

  public KeyValueStoreEntrySubstateAllOf keyHex(String keyHex) {
    this.keyHex = keyHex;
    return this;
  }

   /**
   * The hex-encoded bytes of its key
   * @return keyHex
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The hex-encoded bytes of its key")
  @JsonProperty(JSON_PROPERTY_KEY_HEX)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getKeyHex() {
    return keyHex;
  }


  @JsonProperty(JSON_PROPERTY_KEY_HEX)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setKeyHex(String keyHex) {
    this.keyHex = keyHex;
  }


  public KeyValueStoreEntrySubstateAllOf isDeleted(Boolean isDeleted) {
    this.isDeleted = isDeleted;
    return this;
  }

   /**
   * Get isDeleted
   * @return isDeleted
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_IS_DELETED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Boolean getIsDeleted() {
    return isDeleted;
  }


  @JsonProperty(JSON_PROPERTY_IS_DELETED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setIsDeleted(Boolean isDeleted) {
    this.isDeleted = isDeleted;
  }


  public KeyValueStoreEntrySubstateAllOf dataStruct(DataStruct dataStruct) {
    this.dataStruct = dataStruct;
    return this;
  }

   /**
   * Get dataStruct
   * @return dataStruct
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DATA_STRUCT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public DataStruct getDataStruct() {
    return dataStruct;
  }


  @JsonProperty(JSON_PROPERTY_DATA_STRUCT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDataStruct(DataStruct dataStruct) {
    this.dataStruct = dataStruct;
  }


  /**
   * Return true if this KeyValueStoreEntrySubstate_allOf object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeyValueStoreEntrySubstateAllOf keyValueStoreEntrySubstateAllOf = (KeyValueStoreEntrySubstateAllOf) o;
    return Objects.equals(this.keyHex, keyValueStoreEntrySubstateAllOf.keyHex) &&
        Objects.equals(this.isDeleted, keyValueStoreEntrySubstateAllOf.isDeleted) &&
        Objects.equals(this.dataStruct, keyValueStoreEntrySubstateAllOf.dataStruct);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyHex, isDeleted, dataStruct);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KeyValueStoreEntrySubstateAllOf {\n");
    sb.append("    keyHex: ").append(toIndentedString(keyHex)).append("\n");
    sb.append("    isDeleted: ").append(toIndentedString(isDeleted)).append("\n");
    sb.append("    dataStruct: ").append(toIndentedString(dataStruct)).append("\n");
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

