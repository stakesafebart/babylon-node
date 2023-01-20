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
import com.radixdlt.api.core.generated.models.AccessRuleNode;
import com.radixdlt.api.core.generated.models.AccessRuleNodeBase;
import com.radixdlt.api.core.generated.models.AccessRuleNodeType;
import com.radixdlt.api.core.generated.models.AnyOfAccessRuleNodeAllOf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * AllOfAccessRuleNode
 */
@JsonPropertyOrder({
  AllOfAccessRuleNode.JSON_PROPERTY_TYPE,
  AllOfAccessRuleNode.JSON_PROPERTY_ACCESS_RULES
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class AllOfAccessRuleNode {
  public static final String JSON_PROPERTY_TYPE = "type";
  private AccessRuleNodeType type;

  public static final String JSON_PROPERTY_ACCESS_RULES = "access_rules";
  private List<AccessRuleNode> accessRules = new ArrayList<>();

  public AllOfAccessRuleNode() { 
  }

  public AllOfAccessRuleNode type(AccessRuleNodeType type) {
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


  public AllOfAccessRuleNode accessRules(List<AccessRuleNode> accessRules) {
    this.accessRules = accessRules;
    return this;
  }

  public AllOfAccessRuleNode addAccessRulesItem(AccessRuleNode accessRulesItem) {
    this.accessRules.add(accessRulesItem);
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

  public List<AccessRuleNode> getAccessRules() {
    return accessRules;
  }


  @JsonProperty(JSON_PROPERTY_ACCESS_RULES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setAccessRules(List<AccessRuleNode> accessRules) {
    this.accessRules = accessRules;
  }


  /**
   * Return true if this AllOfAccessRuleNode object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AllOfAccessRuleNode allOfAccessRuleNode = (AllOfAccessRuleNode) o;
    return Objects.equals(this.type, allOfAccessRuleNode.type) &&
        Objects.equals(this.accessRules, allOfAccessRuleNode.accessRules);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, accessRules);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AllOfAccessRuleNode {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

