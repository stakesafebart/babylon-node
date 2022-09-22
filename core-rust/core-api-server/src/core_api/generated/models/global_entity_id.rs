/*
 * Babylon Core API
 *
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 0.1.0
 * 
 * Generated by: https://openapi-generator.tech
 */




#[derive(Clone, Debug, PartialEq, Default, serde::Serialize, serde::Deserialize)]
pub struct GlobalEntityId {
    #[serde(rename = "entity_type")]
    pub entity_type: crate::core_api::generated::models::EntityType,
    /// The hex-encoded bytes of the entity address
    #[serde(rename = "entity_address_hex")]
    pub entity_address_hex: String,
    /// The hex-encoded bytes of the entity's global address. This is currently the same as entity_address, but may change in future.
    #[serde(rename = "global_address_hex")]
    pub global_address_hex: String,
    /// The Bech32m-encoded human readable version of the entity's global address
    #[serde(rename = "global_address")]
    pub global_address: String,
}

impl GlobalEntityId {
    pub fn new(entity_type: crate::core_api::generated::models::EntityType, entity_address_hex: String, global_address_hex: String, global_address: String) -> GlobalEntityId {
        GlobalEntityId {
            entity_type,
            entity_address_hex,
            global_address_hex,
            global_address,
        }
    }
}


