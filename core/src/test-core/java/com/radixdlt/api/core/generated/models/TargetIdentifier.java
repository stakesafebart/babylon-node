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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.radixdlt.api.core.generated.models.BlueprintFunctionTargetIdentifier;
import com.radixdlt.api.core.generated.models.ComponentMethodTargetIdentifier;
import com.radixdlt.api.core.generated.models.TargetIdentifierType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import com.radixdlt.api.core.generated.client.JSON;
/**
 * TargetIdentifier
 */
@JsonPropertyOrder({
  TargetIdentifier.JSON_PROPERTY_TYPE
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@JsonIgnoreProperties(
  value = "type", // ignore manually set type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = BlueprintFunctionTargetIdentifier.class, name = "BlueprintFunctionTargetIdentifier"),
  @JsonSubTypes.Type(value = ComponentMethodTargetIdentifier.class, name = "ComponentMethodTargetIdentifier"),
  @JsonSubTypes.Type(value = BlueprintFunctionTargetIdentifier.class, name = "Function"),
  @JsonSubTypes.Type(value = ComponentMethodTargetIdentifier.class, name = "Method"),
})

public class TargetIdentifier {
  public static final String JSON_PROPERTY_TYPE = "type";
  private TargetIdentifierType type;

  public TargetIdentifier() { 
  }

  public TargetIdentifier type(TargetIdentifierType type) {
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

  public TargetIdentifierType getType() {
    return type;
  }


  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setType(TargetIdentifierType type) {
    this.type = type;
  }


  /**
   * Return true if this TargetIdentifier object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TargetIdentifier targetIdentifier = (TargetIdentifier) o;
    return Objects.equals(this.type, targetIdentifier.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TargetIdentifier {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("BlueprintFunctionTargetIdentifier", BlueprintFunctionTargetIdentifier.class);
  mappings.put("ComponentMethodTargetIdentifier", ComponentMethodTargetIdentifier.class);
  mappings.put("Function", BlueprintFunctionTargetIdentifier.class);
  mappings.put("Method", ComponentMethodTargetIdentifier.class);
  mappings.put("TargetIdentifier", TargetIdentifier.class);
  JSON.registerDiscriminator(TargetIdentifier.class, "type", mappings);
}
}

