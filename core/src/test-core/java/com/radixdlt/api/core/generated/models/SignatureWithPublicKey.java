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
import com.radixdlt.api.core.generated.models.EcdsaSecp256k1SignatureWithPublicKey;
import com.radixdlt.api.core.generated.models.EddsaEd25519SignatureWithPublicKey;
import com.radixdlt.api.core.generated.models.PublicKeyType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import com.radixdlt.api.core.generated.client.JSON;
/**
 * SignatureWithPublicKey
 */
@JsonPropertyOrder({
  SignatureWithPublicKey.JSON_PROPERTY_KEY_TYPE
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@JsonIgnoreProperties(
  value = "key_type", // ignore manually set key_type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the key_type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "key_type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = EcdsaSecp256k1SignatureWithPublicKey.class, name = "EcdsaSecp256k1"),
  @JsonSubTypes.Type(value = EcdsaSecp256k1SignatureWithPublicKey.class, name = "EcdsaSecp256k1SignatureWithPublicKey"),
  @JsonSubTypes.Type(value = EddsaEd25519SignatureWithPublicKey.class, name = "EddsaEd25519"),
  @JsonSubTypes.Type(value = EddsaEd25519SignatureWithPublicKey.class, name = "EddsaEd25519SignatureWithPublicKey"),
})

public class SignatureWithPublicKey {
  public static final String JSON_PROPERTY_KEY_TYPE = "key_type";
  private PublicKeyType keyType;

  public SignatureWithPublicKey() { 
  }

  public SignatureWithPublicKey keyType(PublicKeyType keyType) {
    this.keyType = keyType;
    return this;
  }

   /**
   * Get keyType
   * @return keyType
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_KEY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public PublicKeyType getKeyType() {
    return keyType;
  }


  @JsonProperty(JSON_PROPERTY_KEY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setKeyType(PublicKeyType keyType) {
    this.keyType = keyType;
  }


  /**
   * Return true if this SignatureWithPublicKey object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignatureWithPublicKey signatureWithPublicKey = (SignatureWithPublicKey) o;
    return Objects.equals(this.keyType, signatureWithPublicKey.keyType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SignatureWithPublicKey {\n");
    sb.append("    keyType: ").append(toIndentedString(keyType)).append("\n");
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
  mappings.put("EcdsaSecp256k1", EcdsaSecp256k1SignatureWithPublicKey.class);
  mappings.put("EcdsaSecp256k1SignatureWithPublicKey", EcdsaSecp256k1SignatureWithPublicKey.class);
  mappings.put("EddsaEd25519", EddsaEd25519SignatureWithPublicKey.class);
  mappings.put("EddsaEd25519SignatureWithPublicKey", EddsaEd25519SignatureWithPublicKey.class);
  mappings.put("SignatureWithPublicKey", SignatureWithPublicKey.class);
  JSON.registerDiscriminator(SignatureWithPublicKey.class, "key_type", mappings);
}
}

