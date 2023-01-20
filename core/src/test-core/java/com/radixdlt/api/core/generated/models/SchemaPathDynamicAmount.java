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
import com.radixdlt.api.core.generated.models.DynamicAmountBase;
import com.radixdlt.api.core.generated.models.DynamicAmountType;
import com.radixdlt.api.core.generated.models.SchemaPathDynamicAmountAllOf;
import com.radixdlt.api.core.generated.models.SchemaSubpath;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * SchemaPathDynamicAmount
 */
@JsonPropertyOrder({
  SchemaPathDynamicAmount.JSON_PROPERTY_TYPE,
  SchemaPathDynamicAmount.JSON_PROPERTY_SCHEMA_PATH
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class SchemaPathDynamicAmount {
  public static final String JSON_PROPERTY_TYPE = "type";
  private DynamicAmountType type;

  public static final String JSON_PROPERTY_SCHEMA_PATH = "schema_path";
  private List<SchemaSubpath> schemaPath = new ArrayList<>();

  public SchemaPathDynamicAmount() { 
  }

  public SchemaPathDynamicAmount type(DynamicAmountType type) {
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

  public DynamicAmountType getType() {
    return type;
  }


  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setType(DynamicAmountType type) {
    this.type = type;
  }


  public SchemaPathDynamicAmount schemaPath(List<SchemaSubpath> schemaPath) {
    this.schemaPath = schemaPath;
    return this;
  }

  public SchemaPathDynamicAmount addSchemaPathItem(SchemaSubpath schemaPathItem) {
    this.schemaPath.add(schemaPathItem);
    return this;
  }

   /**
   * Get schemaPath
   * @return schemaPath
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_SCHEMA_PATH)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<SchemaSubpath> getSchemaPath() {
    return schemaPath;
  }


  @JsonProperty(JSON_PROPERTY_SCHEMA_PATH)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setSchemaPath(List<SchemaSubpath> schemaPath) {
    this.schemaPath = schemaPath;
  }


  /**
   * Return true if this SchemaPathDynamicAmount object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SchemaPathDynamicAmount schemaPathDynamicAmount = (SchemaPathDynamicAmount) o;
    return Objects.equals(this.type, schemaPathDynamicAmount.type) &&
        Objects.equals(this.schemaPath, schemaPathDynamicAmount.schemaPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, schemaPath);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SchemaPathDynamicAmount {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    schemaPath: ").append(toIndentedString(schemaPath)).append("\n");
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

