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
pub struct ValidatorSubstateAllOf {
    /// The Bech32m-encoded human readable version of the component address
    #[serde(rename = "epoch_manager_address")]
    pub epoch_manager_address: String,
    /// The Bech32m-encoded human readable version of the component address
    #[serde(rename = "validator_address")]
    pub validator_address: String,
    #[serde(rename = "public_key")]
    pub public_key: Box<crate::core_api::generated::models::EcdsaSecp256k1PublicKey>,
    #[serde(rename = "stake_vault")]
    pub stake_vault: Box<crate::core_api::generated::models::EntityReference>,
    #[serde(rename = "unstake_vault")]
    pub unstake_vault: Box<crate::core_api::generated::models::EntityReference>,
    /// The Bech32m-encoded human readable version of the resource address
    #[serde(rename = "liquid_stake_unit_resource_address")]
    pub liquid_stake_unit_resource_address: String,
    /// The Bech32m-encoded human readable version of the resource address
    #[serde(rename = "unstake_claim_token_resource_address")]
    pub unstake_claim_token_resource_address: String,
    #[serde(rename = "is_registered")]
    pub is_registered: bool,
}

impl ValidatorSubstateAllOf {
    pub fn new(epoch_manager_address: String, validator_address: String, public_key: crate::core_api::generated::models::EcdsaSecp256k1PublicKey, stake_vault: crate::core_api::generated::models::EntityReference, unstake_vault: crate::core_api::generated::models::EntityReference, liquid_stake_unit_resource_address: String, unstake_claim_token_resource_address: String, is_registered: bool) -> ValidatorSubstateAllOf {
        ValidatorSubstateAllOf {
            epoch_manager_address,
            validator_address,
            public_key: Box::new(public_key),
            stake_vault: Box::new(stake_vault),
            unstake_vault: Box::new(unstake_vault),
            liquid_stake_unit_resource_address,
            unstake_claim_token_resource_address,
            is_registered,
        }
    }
}


