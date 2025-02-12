/*
 * Babylon Core API
 *
 * This API is exposed by the Babylon Radix node to give clients access to the Radix Engine, Mempool and State in the node. It is intended for use by node-runners on a private network, and is not intended to be exposed publicly. Heavy load may impact the node's function.  If you require queries against historical ledger state, you may also wish to consider using the [Gateway API](https://betanet-gateway.redoc.ly/). 
 *
 * The version of the OpenAPI document: 0.2.0
 * 
 * Generated by: https://openapi-generator.tech
 */




#[derive(Clone, Debug, PartialEq, Default, serde::Serialize, serde::Deserialize)]
pub struct SubstateId {
    #[serde(rename = "entity_type")]
    pub entity_type: crate::core_api::generated::models::EntityType,
    /// The hex-encoded bytes of the entity id
    #[serde(rename = "entity_id_hex")]
    pub entity_id_hex: String,
    #[serde(rename = "substate_type")]
    pub substate_type: crate::core_api::generated::models::SubstateType,
    #[serde(rename = "substate_key_type")]
    pub substate_key_type: crate::core_api::generated::models::SubstateKeyType,
    /// The hex-encoded bytes of the substate key, under the entity
    #[serde(rename = "substate_key_hex")]
    pub substate_key_hex: String,
}

impl SubstateId {
    pub fn new(entity_type: crate::core_api::generated::models::EntityType, entity_id_hex: String, substate_type: crate::core_api::generated::models::SubstateType, substate_key_type: crate::core_api::generated::models::SubstateKeyType, substate_key_hex: String) -> SubstateId {
        SubstateId {
            entity_type,
            entity_id_hex,
            substate_type,
            substate_key_type,
            substate_key_hex,
        }
    }
}


