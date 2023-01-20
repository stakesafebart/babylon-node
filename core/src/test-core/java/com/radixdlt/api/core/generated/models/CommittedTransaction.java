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
import com.radixdlt.api.core.generated.models.LedgerTransaction;
import com.radixdlt.api.core.generated.models.TransactionReceipt;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * CommittedTransaction
 */
@JsonPropertyOrder({
  CommittedTransaction.JSON_PROPERTY_STATE_VERSION,
  CommittedTransaction.JSON_PROPERTY_ACCUMULATOR_HASH,
  CommittedTransaction.JSON_PROPERTY_LEDGER_TRANSACTION,
  CommittedTransaction.JSON_PROPERTY_RECEIPT
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class CommittedTransaction {
  public static final String JSON_PROPERTY_STATE_VERSION = "state_version";
  private Long stateVersion;

  public static final String JSON_PROPERTY_ACCUMULATOR_HASH = "accumulator_hash";
  private String accumulatorHash;

  public static final String JSON_PROPERTY_LEDGER_TRANSACTION = "ledger_transaction";
  private LedgerTransaction ledgerTransaction;

  public static final String JSON_PROPERTY_RECEIPT = "receipt";
  private TransactionReceipt receipt;

  public CommittedTransaction() { 
  }

  public CommittedTransaction stateVersion(Long stateVersion) {
    this.stateVersion = stateVersion;
    return this;
  }

   /**
   * An integer between &#x60;1&#x60; and &#x60;10^13&#x60;, giving the resultant state version after the transaction has been committed
   * minimum: 1
   * maximum: 100000000000000
   * @return stateVersion
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "An integer between `1` and `10^13`, giving the resultant state version after the transaction has been committed")
  @JsonProperty(JSON_PROPERTY_STATE_VERSION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Long getStateVersion() {
    return stateVersion;
  }


  @JsonProperty(JSON_PROPERTY_STATE_VERSION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setStateVersion(Long stateVersion) {
    this.stateVersion = stateVersion;
  }


  public CommittedTransaction accumulatorHash(String accumulatorHash) {
    this.accumulatorHash = accumulatorHash;
    return this;
  }

   /**
   * The hex-encoded transaction accumulator hash. This hash captures the order of all transactions on ledger. This hash is &#x60;ACC_{N+1} &#x3D; SHA256(SHA256(CONCAT(ACC_N, LEDGER_HASH_{N})))&#x60;, starting with &#x60;ACC_0 &#x3D; 000..000&#x60; the pre-genesis accumulator. 
   * @return accumulatorHash
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The hex-encoded transaction accumulator hash. This hash captures the order of all transactions on ledger. This hash is `ACC_{N+1} = SHA256(SHA256(CONCAT(ACC_N, LEDGER_HASH_{N})))`, starting with `ACC_0 = 000..000` the pre-genesis accumulator. ")
  @JsonProperty(JSON_PROPERTY_ACCUMULATOR_HASH)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getAccumulatorHash() {
    return accumulatorHash;
  }


  @JsonProperty(JSON_PROPERTY_ACCUMULATOR_HASH)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setAccumulatorHash(String accumulatorHash) {
    this.accumulatorHash = accumulatorHash;
  }


  public CommittedTransaction ledgerTransaction(LedgerTransaction ledgerTransaction) {
    this.ledgerTransaction = ledgerTransaction;
    return this;
  }

   /**
   * Get ledgerTransaction
   * @return ledgerTransaction
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_LEDGER_TRANSACTION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public LedgerTransaction getLedgerTransaction() {
    return ledgerTransaction;
  }


  @JsonProperty(JSON_PROPERTY_LEDGER_TRANSACTION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLedgerTransaction(LedgerTransaction ledgerTransaction) {
    this.ledgerTransaction = ledgerTransaction;
  }


  public CommittedTransaction receipt(TransactionReceipt receipt) {
    this.receipt = receipt;
    return this;
  }

   /**
   * Get receipt
   * @return receipt
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_RECEIPT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public TransactionReceipt getReceipt() {
    return receipt;
  }


  @JsonProperty(JSON_PROPERTY_RECEIPT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setReceipt(TransactionReceipt receipt) {
    this.receipt = receipt;
  }


  /**
   * Return true if this CommittedTransaction object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommittedTransaction committedTransaction = (CommittedTransaction) o;
    return Objects.equals(this.stateVersion, committedTransaction.stateVersion) &&
        Objects.equals(this.accumulatorHash, committedTransaction.accumulatorHash) &&
        Objects.equals(this.ledgerTransaction, committedTransaction.ledgerTransaction) &&
        Objects.equals(this.receipt, committedTransaction.receipt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stateVersion, accumulatorHash, ledgerTransaction, receipt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommittedTransaction {\n");
    sb.append("    stateVersion: ").append(toIndentedString(stateVersion)).append("\n");
    sb.append("    accumulatorHash: ").append(toIndentedString(accumulatorHash)).append("\n");
    sb.append("    ledgerTransaction: ").append(toIndentedString(ledgerTransaction)).append("\n");
    sb.append("    receipt: ").append(toIndentedString(receipt)).append("\n");
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

