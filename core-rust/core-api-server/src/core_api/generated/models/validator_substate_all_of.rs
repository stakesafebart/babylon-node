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
    #[serde(rename = "key")]
    pub key: Box<crate::core_api::generated::models::EcdsaSecp256k1PublicKey>,
    #[serde(rename = "stake_vault")]
    pub stake_vault: Box<crate::core_api::generated::models::EntityReference>,
    #[serde(rename = "unstake_vault")]
    pub unstake_vault: Box<crate::core_api::generated::models::EntityReference>,
    /// The Bech32m-encoded human readable version of the resource address
    #[serde(rename = "liquidity_token")]
    pub liquidity_token: String,
    /// The Bech32m-encoded human readable version of the resource address
    #[serde(rename = "unstake_nft_address")]
    pub unstake_nft_address: String,
    #[serde(rename = "is_registered")]
    pub is_registered: bool,
}

impl ValidatorSubstateAllOf {
    pub fn new(epoch_manager_address: String, validator_address: String, key: crate::core_api::generated::models::EcdsaSecp256k1PublicKey, stake_vault: crate::core_api::generated::models::EntityReference, unstake_vault: crate::core_api::generated::models::EntityReference, liquidity_token: String, unstake_nft_address: String, is_registered: bool) -> ValidatorSubstateAllOf {
        ValidatorSubstateAllOf {
            epoch_manager_address,
            validator_address,
            key: Box::new(key),
            stake_vault: Box::new(stake_vault),
            unstake_vault: Box::new(unstake_vault),
            liquidity_token,
            unstake_nft_address,
            is_registered,
        }
    }
}


