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
import com.radixdlt.api.core.generated.models.AccessRule;
import com.radixdlt.api.core.generated.models.LocalMethodReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * MethodAuthMutabilityEntry
 */
@JsonPropertyOrder({
  MethodAuthMutabilityEntry.JSON_PROPERTY_METHOD,
  MethodAuthMutabilityEntry.JSON_PROPERTY_ACCESS_RULE
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class MethodAuthMutabilityEntry {
  public static final String JSON_PROPERTY_METHOD = "method";
  private LocalMethodReference method;

  public static final String JSON_PROPERTY_ACCESS_RULE = "access_rule";
  private AccessRule accessRule;

  public MethodAuthMutabilityEntry() { 
  }

  public MethodAuthMutabilityEntry method(LocalMethodReference method) {
    this.method = method;
    return this;
  }

   /**
   * Get method
   * @return method
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_METHOD)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public LocalMethodReference getMethod() {
    return method;
  }


  @JsonProperty(JSON_PROPERTY_METHOD)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setMethod(LocalMethodReference method) {
    this.method = method;
  }


  public MethodAuthMutabilityEntry accessRule(AccessRule accessRule) {
    this.accessRule = accessRule;
    return this;
  }

   /**
   * Get accessRule
   * @return accessRule
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_ACCESS_RULE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public AccessRule getAccessRule() {
    return accessRule;
  }


  @JsonProperty(JSON_PROPERTY_ACCESS_RULE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setAccessRule(AccessRule accessRule) {
    this.accessRule = accessRule;
  }


  /**
   * Return true if this MethodAuthMutabilityEntry object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MethodAuthMutabilityEntry methodAuthMutabilityEntry = (MethodAuthMutabilityEntry) o;
    return Objects.equals(this.method, methodAuthMutabilityEntry.method) &&
        Objects.equals(this.accessRule, methodAuthMutabilityEntry.accessRule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(method, accessRule);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MethodAuthMutabilityEntry {\n");
    sb.append("    method: ").append(toIndentedString(method)).append("\n");
    sb.append("    accessRule: ").append(toIndentedString(accessRule)).append("\n");
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

