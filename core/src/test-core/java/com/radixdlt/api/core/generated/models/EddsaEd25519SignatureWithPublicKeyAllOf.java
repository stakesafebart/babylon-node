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
import com.radixdlt.api.core.generated.models.EddsaEd25519PublicKey;
import com.radixdlt.api.core.generated.models.EddsaEd25519Signature;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * The EdDSA public key and signature
 */
@ApiModel(description = "The EdDSA public key and signature")
@JsonPropertyOrder({
  EddsaEd25519SignatureWithPublicKeyAllOf.JSON_PROPERTY_PUBLIC_KEY,
  EddsaEd25519SignatureWithPublicKeyAllOf.JSON_PROPERTY_SIGNATURE
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class EddsaEd25519SignatureWithPublicKeyAllOf {
  public static final String JSON_PROPERTY_PUBLIC_KEY = "public_key";
  private EddsaEd25519PublicKey publicKey;

  public static final String JSON_PROPERTY_SIGNATURE = "signature";
  private EddsaEd25519Signature signature;

  public EddsaEd25519SignatureWithPublicKeyAllOf() { 
  }

  public EddsaEd25519SignatureWithPublicKeyAllOf publicKey(EddsaEd25519PublicKey publicKey) {
    this.publicKey = publicKey;
    return this;
  }

   /**
   * Get publicKey
   * @return publicKey
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_PUBLIC_KEY)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public EddsaEd25519PublicKey getPublicKey() {
    return publicKey;
  }


  @JsonProperty(JSON_PROPERTY_PUBLIC_KEY)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setPublicKey(EddsaEd25519PublicKey publicKey) {
    this.publicKey = publicKey;
  }


  public EddsaEd25519SignatureWithPublicKeyAllOf signature(EddsaEd25519Signature signature) {
    this.signature = signature;
    return this;
  }

   /**
   * Get signature
   * @return signature
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_SIGNATURE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public EddsaEd25519Signature getSignature() {
    return signature;
  }


  @JsonProperty(JSON_PROPERTY_SIGNATURE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setSignature(EddsaEd25519Signature signature) {
    this.signature = signature;
  }


  /**
   * Return true if this EddsaEd25519SignatureWithPublicKey_allOf object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EddsaEd25519SignatureWithPublicKeyAllOf eddsaEd25519SignatureWithPublicKeyAllOf = (EddsaEd25519SignatureWithPublicKeyAllOf) o;
    return Objects.equals(this.publicKey, eddsaEd25519SignatureWithPublicKeyAllOf.publicKey) &&
        Objects.equals(this.signature, eddsaEd25519SignatureWithPublicKeyAllOf.signature);
  }

  @Override
  public int hashCode() {
    return Objects.hash(publicKey, signature);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EddsaEd25519SignatureWithPublicKeyAllOf {\n");
    sb.append("    publicKey: ").append(toIndentedString(publicKey)).append("\n");
    sb.append("    signature: ").append(toIndentedString(signature)).append("\n");
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

