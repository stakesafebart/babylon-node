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
import com.radixdlt.api.core.generated.models.AccessRuleReference;
import com.radixdlt.api.core.generated.models.LocalMethodReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * MethodAuthEntry
 */
@JsonPropertyOrder({
  MethodAuthEntry.JSON_PROPERTY_METHOD,
  MethodAuthEntry.JSON_PROPERTY_ACCESS_RULE_REFERENCE
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class MethodAuthEntry {
  public static final String JSON_PROPERTY_METHOD = "method";
  private LocalMethodReference method;

  public static final String JSON_PROPERTY_ACCESS_RULE_REFERENCE = "access_rule_reference";
  private AccessRuleReference accessRuleReference;

  public MethodAuthEntry() { 
  }

  public MethodAuthEntry method(LocalMethodReference method) {
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


  public MethodAuthEntry accessRuleReference(AccessRuleReference accessRuleReference) {
    this.accessRuleReference = accessRuleReference;
    return this;
  }

   /**
   * Get accessRuleReference
   * @return accessRuleReference
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_ACCESS_RULE_REFERENCE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public AccessRuleReference getAccessRuleReference() {
    return accessRuleReference;
  }


  @JsonProperty(JSON_PROPERTY_ACCESS_RULE_REFERENCE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setAccessRuleReference(AccessRuleReference accessRuleReference) {
    this.accessRuleReference = accessRuleReference;
  }


  /**
   * Return true if this MethodAuthEntry object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MethodAuthEntry methodAuthEntry = (MethodAuthEntry) o;
    return Objects.equals(this.method, methodAuthEntry.method) &&
        Objects.equals(this.accessRuleReference, methodAuthEntry.accessRuleReference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(method, accessRuleReference);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MethodAuthEntry {\n");
    sb.append("    method: ").append(toIndentedString(method)).append("\n");
    sb.append("    accessRuleReference: ").append(toIndentedString(accessRuleReference)).append("\n");
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

