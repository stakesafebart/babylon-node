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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Key addresses for this network.
 */
@ApiModel(description = "Key addresses for this network.")
@JsonPropertyOrder({
  NetworkConfigurationResponseWellKnownAddresses.JSON_PROPERTY_ACCOUNT_PACKAGE,
  NetworkConfigurationResponseWellKnownAddresses.JSON_PROPERTY_FAUCET,
  NetworkConfigurationResponseWellKnownAddresses.JSON_PROPERTY_EPOCH_MANAGER,
  NetworkConfigurationResponseWellKnownAddresses.JSON_PROPERTY_CLOCK,
  NetworkConfigurationResponseWellKnownAddresses.JSON_PROPERTY_ECDSA_SECP256K1,
  NetworkConfigurationResponseWellKnownAddresses.JSON_PROPERTY_EDDSA_ED25519,
  NetworkConfigurationResponseWellKnownAddresses.JSON_PROPERTY_XRD
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class NetworkConfigurationResponseWellKnownAddresses {
  public static final String JSON_PROPERTY_ACCOUNT_PACKAGE = "account_package";
  private String accountPackage;

  public static final String JSON_PROPERTY_FAUCET = "faucet";
  private String faucet;

  public static final String JSON_PROPERTY_EPOCH_MANAGER = "epoch_manager";
  private String epochManager;

  public static final String JSON_PROPERTY_CLOCK = "clock";
  private String clock;

  public static final String JSON_PROPERTY_ECDSA_SECP256K1 = "ecdsa_secp256k1";
  private String ecdsaSecp256k1;

  public static final String JSON_PROPERTY_EDDSA_ED25519 = "eddsa_ed25519";
  private String eddsaEd25519;

  public static final String JSON_PROPERTY_XRD = "xrd";
  private String xrd;

  public NetworkConfigurationResponseWellKnownAddresses() { 
  }

  public NetworkConfigurationResponseWellKnownAddresses accountPackage(String accountPackage) {
    this.accountPackage = accountPackage;
    return this;
  }

   /**
   * Get accountPackage
   * @return accountPackage
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_ACCOUNT_PACKAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getAccountPackage() {
    return accountPackage;
  }


  @JsonProperty(JSON_PROPERTY_ACCOUNT_PACKAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setAccountPackage(String accountPackage) {
    this.accountPackage = accountPackage;
  }


  public NetworkConfigurationResponseWellKnownAddresses faucet(String faucet) {
    this.faucet = faucet;
    return this;
  }

   /**
   * Get faucet
   * @return faucet
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_FAUCET)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getFaucet() {
    return faucet;
  }


  @JsonProperty(JSON_PROPERTY_FAUCET)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setFaucet(String faucet) {
    this.faucet = faucet;
  }


  public NetworkConfigurationResponseWellKnownAddresses epochManager(String epochManager) {
    this.epochManager = epochManager;
    return this;
  }

   /**
   * Get epochManager
   * @return epochManager
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_EPOCH_MANAGER)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getEpochManager() {
    return epochManager;
  }


  @JsonProperty(JSON_PROPERTY_EPOCH_MANAGER)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setEpochManager(String epochManager) {
    this.epochManager = epochManager;
  }


  public NetworkConfigurationResponseWellKnownAddresses clock(String clock) {
    this.clock = clock;
    return this;
  }

   /**
   * Get clock
   * @return clock
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_CLOCK)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getClock() {
    return clock;
  }


  @JsonProperty(JSON_PROPERTY_CLOCK)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setClock(String clock) {
    this.clock = clock;
  }


  public NetworkConfigurationResponseWellKnownAddresses ecdsaSecp256k1(String ecdsaSecp256k1) {
    this.ecdsaSecp256k1 = ecdsaSecp256k1;
    return this;
  }

   /**
   * Get ecdsaSecp256k1
   * @return ecdsaSecp256k1
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_ECDSA_SECP256K1)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getEcdsaSecp256k1() {
    return ecdsaSecp256k1;
  }


  @JsonProperty(JSON_PROPERTY_ECDSA_SECP256K1)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setEcdsaSecp256k1(String ecdsaSecp256k1) {
    this.ecdsaSecp256k1 = ecdsaSecp256k1;
  }


  public NetworkConfigurationResponseWellKnownAddresses eddsaEd25519(String eddsaEd25519) {
    this.eddsaEd25519 = eddsaEd25519;
    return this;
  }

   /**
   * Get eddsaEd25519
   * @return eddsaEd25519
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_EDDSA_ED25519)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getEddsaEd25519() {
    return eddsaEd25519;
  }


  @JsonProperty(JSON_PROPERTY_EDDSA_ED25519)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setEddsaEd25519(String eddsaEd25519) {
    this.eddsaEd25519 = eddsaEd25519;
  }


  public NetworkConfigurationResponseWellKnownAddresses xrd(String xrd) {
    this.xrd = xrd;
    return this;
  }

   /**
   * Get xrd
   * @return xrd
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_XRD)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getXrd() {
    return xrd;
  }


  @JsonProperty(JSON_PROPERTY_XRD)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setXrd(String xrd) {
    this.xrd = xrd;
  }


  /**
   * Return true if this NetworkConfigurationResponse_well_known_addresses object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NetworkConfigurationResponseWellKnownAddresses networkConfigurationResponseWellKnownAddresses = (NetworkConfigurationResponseWellKnownAddresses) o;
    return Objects.equals(this.accountPackage, networkConfigurationResponseWellKnownAddresses.accountPackage) &&
        Objects.equals(this.faucet, networkConfigurationResponseWellKnownAddresses.faucet) &&
        Objects.equals(this.epochManager, networkConfigurationResponseWellKnownAddresses.epochManager) &&
        Objects.equals(this.clock, networkConfigurationResponseWellKnownAddresses.clock) &&
        Objects.equals(this.ecdsaSecp256k1, networkConfigurationResponseWellKnownAddresses.ecdsaSecp256k1) &&
        Objects.equals(this.eddsaEd25519, networkConfigurationResponseWellKnownAddresses.eddsaEd25519) &&
        Objects.equals(this.xrd, networkConfigurationResponseWellKnownAddresses.xrd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountPackage, faucet, epochManager, clock, ecdsaSecp256k1, eddsaEd25519, xrd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NetworkConfigurationResponseWellKnownAddresses {\n");
    sb.append("    accountPackage: ").append(toIndentedString(accountPackage)).append("\n");
    sb.append("    faucet: ").append(toIndentedString(faucet)).append("\n");
    sb.append("    epochManager: ").append(toIndentedString(epochManager)).append("\n");
    sb.append("    clock: ").append(toIndentedString(clock)).append("\n");
    sb.append("    ecdsaSecp256k1: ").append(toIndentedString(ecdsaSecp256k1)).append("\n");
    sb.append("    eddsaEd25519: ").append(toIndentedString(eddsaEd25519)).append("\n");
    sb.append("    xrd: ").append(toIndentedString(xrd)).append("\n");
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

