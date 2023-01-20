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
import com.radixdlt.api.core.generated.models.DeletedSubstateVersionRef;
import com.radixdlt.api.core.generated.models.GlobalEntityAssignment;
import com.radixdlt.api.core.generated.models.NewSubstateVersion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Transaction state updates (only present if status is Succeeded or Failed)
 */
@ApiModel(description = "Transaction state updates (only present if status is Succeeded or Failed)")
@JsonPropertyOrder({
  StateUpdates.JSON_PROPERTY_CREATED_SUBSTATES,
  StateUpdates.JSON_PROPERTY_UPDATED_SUBSTATES,
  StateUpdates.JSON_PROPERTY_DELETED_SUBSTATES,
  StateUpdates.JSON_PROPERTY_NEW_GLOBAL_ENTITIES
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class StateUpdates {
  public static final String JSON_PROPERTY_CREATED_SUBSTATES = "created_substates";
  private List<NewSubstateVersion> createdSubstates = new ArrayList<>();

  public static final String JSON_PROPERTY_UPDATED_SUBSTATES = "updated_substates";
  private List<NewSubstateVersion> updatedSubstates = new ArrayList<>();

  public static final String JSON_PROPERTY_DELETED_SUBSTATES = "deleted_substates";
  private List<DeletedSubstateVersionRef> deletedSubstates = new ArrayList<>();

  public static final String JSON_PROPERTY_NEW_GLOBAL_ENTITIES = "new_global_entities";
  private List<GlobalEntityAssignment> newGlobalEntities = new ArrayList<>();

  public StateUpdates() { 
  }

  public StateUpdates createdSubstates(List<NewSubstateVersion> createdSubstates) {
    this.createdSubstates = createdSubstates;
    return this;
  }

  public StateUpdates addCreatedSubstatesItem(NewSubstateVersion createdSubstatesItem) {
    this.createdSubstates.add(createdSubstatesItem);
    return this;
  }

   /**
   * Get createdSubstates
   * @return createdSubstates
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_CREATED_SUBSTATES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<NewSubstateVersion> getCreatedSubstates() {
    return createdSubstates;
  }


  @JsonProperty(JSON_PROPERTY_CREATED_SUBSTATES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCreatedSubstates(List<NewSubstateVersion> createdSubstates) {
    this.createdSubstates = createdSubstates;
  }


  public StateUpdates updatedSubstates(List<NewSubstateVersion> updatedSubstates) {
    this.updatedSubstates = updatedSubstates;
    return this;
  }

  public StateUpdates addUpdatedSubstatesItem(NewSubstateVersion updatedSubstatesItem) {
    this.updatedSubstates.add(updatedSubstatesItem);
    return this;
  }

   /**
   * Get updatedSubstates
   * @return updatedSubstates
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_UPDATED_SUBSTATES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<NewSubstateVersion> getUpdatedSubstates() {
    return updatedSubstates;
  }


  @JsonProperty(JSON_PROPERTY_UPDATED_SUBSTATES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setUpdatedSubstates(List<NewSubstateVersion> updatedSubstates) {
    this.updatedSubstates = updatedSubstates;
  }


  public StateUpdates deletedSubstates(List<DeletedSubstateVersionRef> deletedSubstates) {
    this.deletedSubstates = deletedSubstates;
    return this;
  }

  public StateUpdates addDeletedSubstatesItem(DeletedSubstateVersionRef deletedSubstatesItem) {
    this.deletedSubstates.add(deletedSubstatesItem);
    return this;
  }

   /**
   * Get deletedSubstates
   * @return deletedSubstates
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_DELETED_SUBSTATES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<DeletedSubstateVersionRef> getDeletedSubstates() {
    return deletedSubstates;
  }


  @JsonProperty(JSON_PROPERTY_DELETED_SUBSTATES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setDeletedSubstates(List<DeletedSubstateVersionRef> deletedSubstates) {
    this.deletedSubstates = deletedSubstates;
  }


  public StateUpdates newGlobalEntities(List<GlobalEntityAssignment> newGlobalEntities) {
    this.newGlobalEntities = newGlobalEntities;
    return this;
  }

  public StateUpdates addNewGlobalEntitiesItem(GlobalEntityAssignment newGlobalEntitiesItem) {
    this.newGlobalEntities.add(newGlobalEntitiesItem);
    return this;
  }

   /**
   * Get newGlobalEntities
   * @return newGlobalEntities
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_NEW_GLOBAL_ENTITIES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<GlobalEntityAssignment> getNewGlobalEntities() {
    return newGlobalEntities;
  }


  @JsonProperty(JSON_PROPERTY_NEW_GLOBAL_ENTITIES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setNewGlobalEntities(List<GlobalEntityAssignment> newGlobalEntities) {
    this.newGlobalEntities = newGlobalEntities;
  }


  /**
   * Return true if this StateUpdates object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StateUpdates stateUpdates = (StateUpdates) o;
    return Objects.equals(this.createdSubstates, stateUpdates.createdSubstates) &&
        Objects.equals(this.updatedSubstates, stateUpdates.updatedSubstates) &&
        Objects.equals(this.deletedSubstates, stateUpdates.deletedSubstates) &&
        Objects.equals(this.newGlobalEntities, stateUpdates.newGlobalEntities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdSubstates, updatedSubstates, deletedSubstates, newGlobalEntities);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StateUpdates {\n");
    sb.append("    createdSubstates: ").append(toIndentedString(createdSubstates)).append("\n");
    sb.append("    updatedSubstates: ").append(toIndentedString(updatedSubstates)).append("\n");
    sb.append("    deletedSubstates: ").append(toIndentedString(deletedSubstates)).append("\n");
    sb.append("    newGlobalEntities: ").append(toIndentedString(newGlobalEntities)).append("\n");
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

