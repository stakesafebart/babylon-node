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
import com.radixdlt.api.core.generated.models.DynamicCount;
import com.radixdlt.api.core.generated.models.DynamicResourceDescriptorList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * CountOfProofRuleAllOf
 */
@JsonPropertyOrder({
  CountOfProofRuleAllOf.JSON_PROPERTY_COUNT,
  CountOfProofRuleAllOf.JSON_PROPERTY_LIST
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class CountOfProofRuleAllOf {
  public static final String JSON_PROPERTY_COUNT = "count";
  private DynamicCount count;

  public static final String JSON_PROPERTY_LIST = "list";
  private DynamicResourceDescriptorList _list;

  public CountOfProofRuleAllOf() { 
  }

  public CountOfProofRuleAllOf count(DynamicCount count) {
    this.count = count;
    return this;
  }

   /**
   * Get count
   * @return count
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_COUNT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public DynamicCount getCount() {
    return count;
  }


  @JsonProperty(JSON_PROPERTY_COUNT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCount(DynamicCount count) {
    this.count = count;
  }


  public CountOfProofRuleAllOf _list(DynamicResourceDescriptorList _list) {
    this._list = _list;
    return this;
  }

   /**
   * Get _list
   * @return _list
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_LIST)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public DynamicResourceDescriptorList getList() {
    return _list;
  }


  @JsonProperty(JSON_PROPERTY_LIST)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setList(DynamicResourceDescriptorList _list) {
    this._list = _list;
  }


  /**
   * Return true if this CountOfProofRule_allOf object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CountOfProofRuleAllOf countOfProofRuleAllOf = (CountOfProofRuleAllOf) o;
    return Objects.equals(this.count, countOfProofRuleAllOf.count) &&
        Objects.equals(this._list, countOfProofRuleAllOf._list);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, _list);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CountOfProofRuleAllOf {\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    _list: ").append(toIndentedString(_list)).append("\n");
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

