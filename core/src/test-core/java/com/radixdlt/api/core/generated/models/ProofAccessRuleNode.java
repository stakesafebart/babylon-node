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
import com.radixdlt.api.core.generated.models.AccessRuleNodeBase;
import com.radixdlt.api.core.generated.models.AccessRuleNodeType;
import com.radixdlt.api.core.generated.models.ProofAccessRuleNodeAllOf;
import com.radixdlt.api.core.generated.models.ProofRule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ProofAccessRuleNode
 */
@JsonPropertyOrder({
  ProofAccessRuleNode.JSON_PROPERTY_TYPE,
  ProofAccessRuleNode.JSON_PROPERTY_PROOF_RULE
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ProofAccessRuleNode {
  public static final String JSON_PROPERTY_TYPE = "type";
  private AccessRuleNodeType type;

  public static final String JSON_PROPERTY_PROOF_RULE = "proof_rule";
  private ProofRule proofRule;

  public ProofAccessRuleNode() { 
  }

  public ProofAccessRuleNode type(AccessRuleNodeType type) {
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

  public AccessRuleNodeType getType() {
    return type;
  }


  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setType(AccessRuleNodeType type) {
    this.type = type;
  }


  public ProofAccessRuleNode proofRule(ProofRule proofRule) {
    this.proofRule = proofRule;
    return this;
  }

   /**
   * Get proofRule
   * @return proofRule
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_PROOF_RULE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public ProofRule getProofRule() {
    return proofRule;
  }


  @JsonProperty(JSON_PROPERTY_PROOF_RULE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setProofRule(ProofRule proofRule) {
    this.proofRule = proofRule;
  }


  /**
   * Return true if this ProofAccessRuleNode object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProofAccessRuleNode proofAccessRuleNode = (ProofAccessRuleNode) o;
    return Objects.equals(this.type, proofAccessRuleNode.type) &&
        Objects.equals(this.proofRule, proofAccessRuleNode.proofRule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, proofRule);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProofAccessRuleNode {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    proofRule: ").append(toIndentedString(proofRule)).append("\n");
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
