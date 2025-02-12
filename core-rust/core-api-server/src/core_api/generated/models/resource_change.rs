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
pub struct ResourceChange {
    /// The Bech32m-encoded human readable version of the resource address
    #[serde(rename = "resource_address")]
    pub resource_address: String,
    #[serde(rename = "component_entity")]
    pub component_entity: Box<crate::core_api::generated::models::EntityReference>,
    #[serde(rename = "vault_entity")]
    pub vault_entity: Box<crate::core_api::generated::models::EntityReference>,
    /// The string-encoded decimal representing the XRD amount put or taken from the vault. A decimal is formed of some signed integer `m` of attos (`10^(-18)`) units, where `-2^(256 - 1) <= m < 2^(256 - 1)`. 
    #[serde(rename = "amount")]
    pub amount: String,
}

impl ResourceChange {
    pub fn new(resource_address: String, component_entity: crate::core_api::generated::models::EntityReference, vault_entity: crate::core_api::generated::models::EntityReference, amount: String) -> ResourceChange {
        ResourceChange {
            resource_address,
            component_entity: Box::new(component_entity),
            vault_entity: Box::new(vault_entity),
            amount,
        }
    }
}


