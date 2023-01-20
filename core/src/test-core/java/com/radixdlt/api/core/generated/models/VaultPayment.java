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
import com.radixdlt.api.core.generated.models.EntityReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * VaultPayment
 */
@JsonPropertyOrder({
  VaultPayment.JSON_PROPERTY_VAULT_ENTITY,
  VaultPayment.JSON_PROPERTY_XRD_AMOUNT
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class VaultPayment {
  public static final String JSON_PROPERTY_VAULT_ENTITY = "vault_entity";
  private EntityReference vaultEntity;

  public static final String JSON_PROPERTY_XRD_AMOUNT = "xrd_amount";
  private String xrdAmount;

  public VaultPayment() { 
  }

  public VaultPayment vaultEntity(EntityReference vaultEntity) {
    this.vaultEntity = vaultEntity;
    return this;
  }

   /**
   * Get vaultEntity
   * @return vaultEntity
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_VAULT_ENTITY)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public EntityReference getVaultEntity() {
    return vaultEntity;
  }


  @JsonProperty(JSON_PROPERTY_VAULT_ENTITY)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setVaultEntity(EntityReference vaultEntity) {
    this.vaultEntity = vaultEntity;
  }


  public VaultPayment xrdAmount(String xrdAmount) {
    this.xrdAmount = xrdAmount;
    return this;
  }

   /**
   * The string-encoded decimal representing the amount of fee in XRD paid by this vault. A decimal is formed of some signed integer &#x60;m&#x60; of attos (&#x60;10^(-18)&#x60;) units, where &#x60;-2^(256 - 1) &lt;&#x3D; m &lt; 2^(256 - 1)&#x60;. 
   * @return xrdAmount
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The string-encoded decimal representing the amount of fee in XRD paid by this vault. A decimal is formed of some signed integer `m` of attos (`10^(-18)`) units, where `-2^(256 - 1) <= m < 2^(256 - 1)`. ")
  @JsonProperty(JSON_PROPERTY_XRD_AMOUNT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getXrdAmount() {
    return xrdAmount;
  }


  @JsonProperty(JSON_PROPERTY_XRD_AMOUNT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setXrdAmount(String xrdAmount) {
    this.xrdAmount = xrdAmount;
  }


  /**
   * Return true if this VaultPayment object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VaultPayment vaultPayment = (VaultPayment) o;
    return Objects.equals(this.vaultEntity, vaultPayment.vaultEntity) &&
        Objects.equals(this.xrdAmount, vaultPayment.xrdAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vaultEntity, xrdAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VaultPayment {\n");
    sb.append("    vaultEntity: ").append(toIndentedString(vaultEntity)).append("\n");
    sb.append("    xrdAmount: ").append(toIndentedString(xrdAmount)).append("\n");
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

