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
import com.radixdlt.api.core.generated.models.RoyaltyPayment;
import com.radixdlt.api.core.generated.models.VaultPayment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Fees paid
 */
@ApiModel(description = "Fees paid")
@JsonPropertyOrder({
  FeeSummary.JSON_PROPERTY_COST_UNIT_PRICE,
  FeeSummary.JSON_PROPERTY_TIP_PERCENTAGE,
  FeeSummary.JSON_PROPERTY_COST_UNIT_LIMIT,
  FeeSummary.JSON_PROPERTY_COST_UNITS_CONSUMED,
  FeeSummary.JSON_PROPERTY_XRD_TOTAL_EXECUTION_COST,
  FeeSummary.JSON_PROPERTY_XRD_TOTAL_ROYALTY_COST,
  FeeSummary.JSON_PROPERTY_XRD_TOTAL_TIPPED,
  FeeSummary.JSON_PROPERTY_XRD_VAULT_PAYMENTS,
  FeeSummary.JSON_PROPERTY_COST_UNIT_EXECUTION_BREAKDOWN,
  FeeSummary.JSON_PROPERTY_COST_UNIT_ROYALTY_BREAKDOWN
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class FeeSummary {
  public static final String JSON_PROPERTY_COST_UNIT_PRICE = "cost_unit_price";
  private String costUnitPrice;

  public static final String JSON_PROPERTY_TIP_PERCENTAGE = "tip_percentage";
  private Integer tipPercentage;

  public static final String JSON_PROPERTY_COST_UNIT_LIMIT = "cost_unit_limit";
  private Long costUnitLimit;

  public static final String JSON_PROPERTY_COST_UNITS_CONSUMED = "cost_units_consumed";
  private Long costUnitsConsumed;

  public static final String JSON_PROPERTY_XRD_TOTAL_EXECUTION_COST = "xrd_total_execution_cost";
  private String xrdTotalExecutionCost;

  public static final String JSON_PROPERTY_XRD_TOTAL_ROYALTY_COST = "xrd_total_royalty_cost";
  private String xrdTotalRoyaltyCost;

  public static final String JSON_PROPERTY_XRD_TOTAL_TIPPED = "xrd_total_tipped";
  private String xrdTotalTipped;

  public static final String JSON_PROPERTY_XRD_VAULT_PAYMENTS = "xrd_vault_payments";
  private List<VaultPayment> xrdVaultPayments = null;

  public static final String JSON_PROPERTY_COST_UNIT_EXECUTION_BREAKDOWN = "cost_unit_execution_breakdown";
  private Map<String, Long> costUnitExecutionBreakdown = new HashMap<>();

  public static final String JSON_PROPERTY_COST_UNIT_ROYALTY_BREAKDOWN = "cost_unit_royalty_breakdown";
  private List<RoyaltyPayment> costUnitRoyaltyBreakdown = new ArrayList<>();

  public FeeSummary() { 
  }

  public FeeSummary costUnitPrice(String costUnitPrice) {
    this.costUnitPrice = costUnitPrice;
    return this;
  }

   /**
   * The string-encoded decimal representing the XRD price of a single cost unit. A decimal is formed of some signed integer &#x60;m&#x60; of attos (&#x60;10^(-18)&#x60;) units, where &#x60;-2^(256 - 1) &lt;&#x3D; m &lt; 2^(256 - 1)&#x60;. 
   * @return costUnitPrice
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The string-encoded decimal representing the XRD price of a single cost unit. A decimal is formed of some signed integer `m` of attos (`10^(-18)`) units, where `-2^(256 - 1) <= m < 2^(256 - 1)`. ")
  @JsonProperty(JSON_PROPERTY_COST_UNIT_PRICE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getCostUnitPrice() {
    return costUnitPrice;
  }


  @JsonProperty(JSON_PROPERTY_COST_UNIT_PRICE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCostUnitPrice(String costUnitPrice) {
    this.costUnitPrice = costUnitPrice;
  }


  public FeeSummary tipPercentage(Integer tipPercentage) {
    this.tipPercentage = tipPercentage;
    return this;
  }

   /**
   * An integer between &#x60;0&#x60; and &#x60;255&#x60;, giving the validator tip as a percentage amount. A value of &#x60;1&#x60; corresponds to 1% of the fee.
   * minimum: 0
   * maximum: 255
   * @return tipPercentage
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "An integer between `0` and `255`, giving the validator tip as a percentage amount. A value of `1` corresponds to 1% of the fee.")
  @JsonProperty(JSON_PROPERTY_TIP_PERCENTAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getTipPercentage() {
    return tipPercentage;
  }


  @JsonProperty(JSON_PROPERTY_TIP_PERCENTAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTipPercentage(Integer tipPercentage) {
    this.tipPercentage = tipPercentage;
  }


  public FeeSummary costUnitLimit(Long costUnitLimit) {
    this.costUnitLimit = costUnitLimit;
    return this;
  }

   /**
   * An integer between &#x60;0&#x60; and &#x60;2^32 - 1&#x60;, representing the maximum amount of cost units available for the transaction execution.
   * minimum: 0
   * maximum: 4294967295
   * @return costUnitLimit
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "An integer between `0` and `2^32 - 1`, representing the maximum amount of cost units available for the transaction execution.")
  @JsonProperty(JSON_PROPERTY_COST_UNIT_LIMIT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Long getCostUnitLimit() {
    return costUnitLimit;
  }


  @JsonProperty(JSON_PROPERTY_COST_UNIT_LIMIT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCostUnitLimit(Long costUnitLimit) {
    this.costUnitLimit = costUnitLimit;
  }


  public FeeSummary costUnitsConsumed(Long costUnitsConsumed) {
    this.costUnitsConsumed = costUnitsConsumed;
    return this;
  }

   /**
   * An integer between &#x60;0&#x60; and &#x60;2^32 - 1&#x60;, representing the amount of cost units consumed by the transaction execution.
   * minimum: 0
   * maximum: 4294967295
   * @return costUnitsConsumed
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "An integer between `0` and `2^32 - 1`, representing the amount of cost units consumed by the transaction execution.")
  @JsonProperty(JSON_PROPERTY_COST_UNITS_CONSUMED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Long getCostUnitsConsumed() {
    return costUnitsConsumed;
  }


  @JsonProperty(JSON_PROPERTY_COST_UNITS_CONSUMED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCostUnitsConsumed(Long costUnitsConsumed) {
    this.costUnitsConsumed = costUnitsConsumed;
  }


  public FeeSummary xrdTotalExecutionCost(String xrdTotalExecutionCost) {
    this.xrdTotalExecutionCost = xrdTotalExecutionCost;
    return this;
  }

   /**
   * The string-encoded decimal representing the total amount of XRD burned in the transaction as part of execution costs. A decimal is formed of some signed integer &#x60;m&#x60; of attos (&#x60;10^(-18)&#x60;) units, where &#x60;-2^(256 - 1) &lt;&#x3D; m &lt; 2^(256 - 1)&#x60;. 
   * @return xrdTotalExecutionCost
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The string-encoded decimal representing the total amount of XRD burned in the transaction as part of execution costs. A decimal is formed of some signed integer `m` of attos (`10^(-18)`) units, where `-2^(256 - 1) <= m < 2^(256 - 1)`. ")
  @JsonProperty(JSON_PROPERTY_XRD_TOTAL_EXECUTION_COST)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getXrdTotalExecutionCost() {
    return xrdTotalExecutionCost;
  }


  @JsonProperty(JSON_PROPERTY_XRD_TOTAL_EXECUTION_COST)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setXrdTotalExecutionCost(String xrdTotalExecutionCost) {
    this.xrdTotalExecutionCost = xrdTotalExecutionCost;
  }


  public FeeSummary xrdTotalRoyaltyCost(String xrdTotalRoyaltyCost) {
    this.xrdTotalRoyaltyCost = xrdTotalRoyaltyCost;
    return this;
  }

   /**
   * The string-encoded decimal representing the total amount of XRD paid in royalties as part of the transaction. A decimal is formed of some signed integer &#x60;m&#x60; of attos (&#x60;10^(-18)&#x60;) units, where &#x60;-2^(256 - 1) &lt;&#x3D; m &lt; 2^(256 - 1)&#x60;. 
   * @return xrdTotalRoyaltyCost
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The string-encoded decimal representing the total amount of XRD paid in royalties as part of the transaction. A decimal is formed of some signed integer `m` of attos (`10^(-18)`) units, where `-2^(256 - 1) <= m < 2^(256 - 1)`. ")
  @JsonProperty(JSON_PROPERTY_XRD_TOTAL_ROYALTY_COST)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getXrdTotalRoyaltyCost() {
    return xrdTotalRoyaltyCost;
  }


  @JsonProperty(JSON_PROPERTY_XRD_TOTAL_ROYALTY_COST)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setXrdTotalRoyaltyCost(String xrdTotalRoyaltyCost) {
    this.xrdTotalRoyaltyCost = xrdTotalRoyaltyCost;
  }


  public FeeSummary xrdTotalTipped(String xrdTotalTipped) {
    this.xrdTotalTipped = xrdTotalTipped;
    return this;
  }

   /**
   * The string-encoded decimal representing the total amount of XRD tipped to validators in the transaction. A decimal is formed of some signed integer &#x60;m&#x60; of attos (&#x60;10^(-18)&#x60;) units, where &#x60;-2^(256 - 1) &lt;&#x3D; m &lt; 2^(256 - 1)&#x60;. 
   * @return xrdTotalTipped
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The string-encoded decimal representing the total amount of XRD tipped to validators in the transaction. A decimal is formed of some signed integer `m` of attos (`10^(-18)`) units, where `-2^(256 - 1) <= m < 2^(256 - 1)`. ")
  @JsonProperty(JSON_PROPERTY_XRD_TOTAL_TIPPED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getXrdTotalTipped() {
    return xrdTotalTipped;
  }


  @JsonProperty(JSON_PROPERTY_XRD_TOTAL_TIPPED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setXrdTotalTipped(String xrdTotalTipped) {
    this.xrdTotalTipped = xrdTotalTipped;
  }


  public FeeSummary xrdVaultPayments(List<VaultPayment> xrdVaultPayments) {
    this.xrdVaultPayments = xrdVaultPayments;
    return this;
  }

  public FeeSummary addXrdVaultPaymentsItem(VaultPayment xrdVaultPaymentsItem) {
    if (this.xrdVaultPayments == null) {
      this.xrdVaultPayments = new ArrayList<>();
    }
    this.xrdVaultPayments.add(xrdVaultPaymentsItem);
    return this;
  }

   /**
   * A summary of which vaults were used to pay the fee. This is only present if the transaction was committed. 
   * @return xrdVaultPayments
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A summary of which vaults were used to pay the fee. This is only present if the transaction was committed. ")
  @JsonProperty(JSON_PROPERTY_XRD_VAULT_PAYMENTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<VaultPayment> getXrdVaultPayments() {
    return xrdVaultPayments;
  }


  @JsonProperty(JSON_PROPERTY_XRD_VAULT_PAYMENTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setXrdVaultPayments(List<VaultPayment> xrdVaultPayments) {
    this.xrdVaultPayments = xrdVaultPayments;
  }


  public FeeSummary costUnitExecutionBreakdown(Map<String, Long> costUnitExecutionBreakdown) {
    this.costUnitExecutionBreakdown = costUnitExecutionBreakdown;
    return this;
  }

  public FeeSummary putCostUnitExecutionBreakdownItem(String key, Long costUnitExecutionBreakdownItem) {
    this.costUnitExecutionBreakdown.put(key, costUnitExecutionBreakdownItem);
    return this;
  }

   /**
   * A summary of where the execution cost went. 
   * @return costUnitExecutionBreakdown
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "A summary of where the execution cost went. ")
  @JsonProperty(JSON_PROPERTY_COST_UNIT_EXECUTION_BREAKDOWN)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Map<String, Long> getCostUnitExecutionBreakdown() {
    return costUnitExecutionBreakdown;
  }


  @JsonProperty(JSON_PROPERTY_COST_UNIT_EXECUTION_BREAKDOWN)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCostUnitExecutionBreakdown(Map<String, Long> costUnitExecutionBreakdown) {
    this.costUnitExecutionBreakdown = costUnitExecutionBreakdown;
  }


  public FeeSummary costUnitRoyaltyBreakdown(List<RoyaltyPayment> costUnitRoyaltyBreakdown) {
    this.costUnitRoyaltyBreakdown = costUnitRoyaltyBreakdown;
    return this;
  }

  public FeeSummary addCostUnitRoyaltyBreakdownItem(RoyaltyPayment costUnitRoyaltyBreakdownItem) {
    this.costUnitRoyaltyBreakdown.add(costUnitRoyaltyBreakdownItem);
    return this;
  }

   /**
   * A summary of where the royalties were paid to. 
   * @return costUnitRoyaltyBreakdown
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "A summary of where the royalties were paid to. ")
  @JsonProperty(JSON_PROPERTY_COST_UNIT_ROYALTY_BREAKDOWN)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<RoyaltyPayment> getCostUnitRoyaltyBreakdown() {
    return costUnitRoyaltyBreakdown;
  }


  @JsonProperty(JSON_PROPERTY_COST_UNIT_ROYALTY_BREAKDOWN)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCostUnitRoyaltyBreakdown(List<RoyaltyPayment> costUnitRoyaltyBreakdown) {
    this.costUnitRoyaltyBreakdown = costUnitRoyaltyBreakdown;
  }


  /**
   * Return true if this FeeSummary object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeeSummary feeSummary = (FeeSummary) o;
    return Objects.equals(this.costUnitPrice, feeSummary.costUnitPrice) &&
        Objects.equals(this.tipPercentage, feeSummary.tipPercentage) &&
        Objects.equals(this.costUnitLimit, feeSummary.costUnitLimit) &&
        Objects.equals(this.costUnitsConsumed, feeSummary.costUnitsConsumed) &&
        Objects.equals(this.xrdTotalExecutionCost, feeSummary.xrdTotalExecutionCost) &&
        Objects.equals(this.xrdTotalRoyaltyCost, feeSummary.xrdTotalRoyaltyCost) &&
        Objects.equals(this.xrdTotalTipped, feeSummary.xrdTotalTipped) &&
        Objects.equals(this.xrdVaultPayments, feeSummary.xrdVaultPayments) &&
        Objects.equals(this.costUnitExecutionBreakdown, feeSummary.costUnitExecutionBreakdown) &&
        Objects.equals(this.costUnitRoyaltyBreakdown, feeSummary.costUnitRoyaltyBreakdown);
  }

  @Override
  public int hashCode() {
    return Objects.hash(costUnitPrice, tipPercentage, costUnitLimit, costUnitsConsumed, xrdTotalExecutionCost, xrdTotalRoyaltyCost, xrdTotalTipped, xrdVaultPayments, costUnitExecutionBreakdown, costUnitRoyaltyBreakdown);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeeSummary {\n");
    sb.append("    costUnitPrice: ").append(toIndentedString(costUnitPrice)).append("\n");
    sb.append("    tipPercentage: ").append(toIndentedString(tipPercentage)).append("\n");
    sb.append("    costUnitLimit: ").append(toIndentedString(costUnitLimit)).append("\n");
    sb.append("    costUnitsConsumed: ").append(toIndentedString(costUnitsConsumed)).append("\n");
    sb.append("    xrdTotalExecutionCost: ").append(toIndentedString(xrdTotalExecutionCost)).append("\n");
    sb.append("    xrdTotalRoyaltyCost: ").append(toIndentedString(xrdTotalRoyaltyCost)).append("\n");
    sb.append("    xrdTotalTipped: ").append(toIndentedString(xrdTotalTipped)).append("\n");
    sb.append("    xrdVaultPayments: ").append(toIndentedString(xrdVaultPayments)).append("\n");
    sb.append("    costUnitExecutionBreakdown: ").append(toIndentedString(costUnitExecutionBreakdown)).append("\n");
    sb.append("    costUnitRoyaltyBreakdown: ").append(toIndentedString(costUnitRoyaltyBreakdown)).append("\n");
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

