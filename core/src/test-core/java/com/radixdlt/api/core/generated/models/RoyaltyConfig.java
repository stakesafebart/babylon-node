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
import com.radixdlt.api.core.generated.models.MethodRoyaltyRule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * RoyaltyConfig
 */
@JsonPropertyOrder({
  RoyaltyConfig.JSON_PROPERTY_METHOD_RULES,
  RoyaltyConfig.JSON_PROPERTY_DEFAULT_RULE
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class RoyaltyConfig {
  public static final String JSON_PROPERTY_METHOD_RULES = "method_rules";
  private List<MethodRoyaltyRule> methodRules = new ArrayList<>();

  public static final String JSON_PROPERTY_DEFAULT_RULE = "default_rule";
  private Long defaultRule;

  public RoyaltyConfig() { 
  }

  public RoyaltyConfig methodRules(List<MethodRoyaltyRule> methodRules) {
    this.methodRules = methodRules;
    return this;
  }

  public RoyaltyConfig addMethodRulesItem(MethodRoyaltyRule methodRulesItem) {
    this.methodRules.add(methodRulesItem);
    return this;
  }

   /**
   * The royalty rules by method
   * @return methodRules
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The royalty rules by method")
  @JsonProperty(JSON_PROPERTY_METHOD_RULES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<MethodRoyaltyRule> getMethodRules() {
    return methodRules;
  }


  @JsonProperty(JSON_PROPERTY_METHOD_RULES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setMethodRules(List<MethodRoyaltyRule> methodRules) {
    this.methodRules = methodRules;
  }


  public RoyaltyConfig defaultRule(Long defaultRule) {
    this.defaultRule = defaultRule;
    return this;
  }

   /**
   * An integer between &#x60;0&#x60; and &#x60;2^32 - 1&#x60;, representing the number of cost units required to access this method.
   * minimum: 0
   * maximum: 4294967295
   * @return defaultRule
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "An integer between `0` and `2^32 - 1`, representing the number of cost units required to access this method.")
  @JsonProperty(JSON_PROPERTY_DEFAULT_RULE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Long getDefaultRule() {
    return defaultRule;
  }


  @JsonProperty(JSON_PROPERTY_DEFAULT_RULE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setDefaultRule(Long defaultRule) {
    this.defaultRule = defaultRule;
  }


  /**
   * Return true if this RoyaltyConfig object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoyaltyConfig royaltyConfig = (RoyaltyConfig) o;
    return Objects.equals(this.methodRules, royaltyConfig.methodRules) &&
        Objects.equals(this.defaultRule, royaltyConfig.defaultRule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(methodRules, defaultRule);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoyaltyConfig {\n");
    sb.append("    methodRules: ").append(toIndentedString(methodRules)).append("\n");
    sb.append("    defaultRule: ").append(toIndentedString(defaultRule)).append("\n");
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

