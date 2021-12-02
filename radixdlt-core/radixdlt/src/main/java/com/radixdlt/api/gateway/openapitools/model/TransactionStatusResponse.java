/*
 * Radix Gateway API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 0.9.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.radixdlt.api.gateway.openapitools.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;


/**
 * TransactionStatusResponse
 */
@JsonPropertyOrder({
  TransactionStatusResponse.JSON_PROPERTY_LEDGER_STATE,
  TransactionStatusResponse.JSON_PROPERTY_TRANSACTION
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-12-02T13:25:58.096142-06:00[America/Chicago]")
public class TransactionStatusResponse {
  public static final String JSON_PROPERTY_LEDGER_STATE = "ledger_state";
  private LedgerState ledgerState;

  public static final String JSON_PROPERTY_TRANSACTION = "transaction";
  private AccountTransaction transaction;


  public TransactionStatusResponse ledgerState(LedgerState ledgerState) {
    this.ledgerState = ledgerState;
    return this;
  }

   /**
   * Get ledgerState
   * @return ledgerState
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_LEDGER_STATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public LedgerState getLedgerState() {
    return ledgerState;
  }


  @JsonProperty(JSON_PROPERTY_LEDGER_STATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLedgerState(LedgerState ledgerState) {
    this.ledgerState = ledgerState;
  }


  public TransactionStatusResponse transaction(AccountTransaction transaction) {
    this.transaction = transaction;
    return this;
  }

   /**
   * Get transaction
   * @return transaction
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_TRANSACTION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public AccountTransaction getTransaction() {
    return transaction;
  }


  @JsonProperty(JSON_PROPERTY_TRANSACTION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTransaction(AccountTransaction transaction) {
    this.transaction = transaction;
  }


  /**
   * Return true if this TransactionStatusResponse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionStatusResponse transactionStatusResponse = (TransactionStatusResponse) o;
    return Objects.equals(this.ledgerState, transactionStatusResponse.ledgerState) &&
        Objects.equals(this.transaction, transactionStatusResponse.transaction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ledgerState, transaction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionStatusResponse {\n");
    sb.append("    ledgerState: ").append(toIndentedString(ledgerState)).append("\n");
    sb.append("    transaction: ").append(toIndentedString(transaction)).append("\n");
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

