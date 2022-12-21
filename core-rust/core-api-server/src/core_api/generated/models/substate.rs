/*
 * Babylon Core API
 *
 * This API is exposed by the Babylon Radix node to give clients access to the Radix Engine, Mempool and State in the node. It is intended for use by node-runners on a private network, and is not intended to be exposed publicly. Heavy load may impact the node's function.  If you require queries against historical ledger state, you may also wish to consider using the [Gateway API](https://betanet-gateway.redoc.ly/). 
 *
 * The version of the OpenAPI document: 0.1.0
 * 
 * Generated by: https://openapi-generator.tech
 */



#[derive(Clone, Debug, PartialEq, serde::Serialize, serde::Deserialize)]
#[serde(tag = "substate_type")]
pub enum Substate {
    #[serde(rename="AccessRulesChain")]
    AccessRulesChainSubstate {
        /// The layers of access rules applied. 
        #[serde(rename = "chain")]
        chain: Vec<crate::core_api::generated::models::AccessRules>,
    },
    #[serde(rename="ClockCurrentMinute")]
    ClockCurrentMinuteSubstate {
        /// An integer between `0` and `10^14`, marking the ledger unix timestamp in ms (rounded down to the current minute)
        #[serde(rename = "timestamp_ms_rounded_down_to_minute")]
        timestamp_ms_rounded_down_to_minute: i64,
    },
    #[serde(rename="ComponentInfo")]
    ComponentInfoSubstate {
        /// The Bech32m-encoded human readable version of the package address
        #[serde(rename = "package_address")]
        package_address: String,
        #[serde(rename = "blueprint_name")]
        blueprint_name: String,
    },
    #[serde(rename="ComponentRoyaltyAccumulator")]
    ComponentRoyaltyAccumulatorSubstate {
        /// The string-encoded decimal representing the amount of XRD available for claim in the royalty accumulator. A decimal is formed of some signed integer `m` of attos (`10^(-18)`) units, where `-2^(256 - 1) <= m < 2^(256 - 1)`. 
        #[serde(rename = "xrd_amount")]
        xrd_amount: String,
    },
    #[serde(rename="ComponentRoyaltyConfig")]
    ComponentRoyaltyConfigSubstate {
        #[serde(rename = "royalty_config")]
        royalty_config: Box<crate::core_api::generated::models::RoyaltyConfig>,
    },
    #[serde(rename="ComponentState")]
    ComponentStateSubstate {
        #[serde(rename = "data_struct")]
        data_struct: Box<crate::core_api::generated::models::DataStruct>,
    },
    #[serde(rename="EpochManager")]
    EpochManagerSubstate {
        /// An integer between `0` and `10^10`, marking the current epoch
        #[serde(rename = "epoch")]
        epoch: i64,
        /// An integer between `0` and `10^10`, marking the current round in an epoch
        #[serde(rename = "round")]
        round: i64,
        /// An integer between `0` and `10^10`, specifying the number of rounds per epoch
        #[serde(rename = "rounds_per_epoch")]
        rounds_per_epoch: i64,
    },
    #[serde(rename="GlobalAddress")]
    GlobalAddressSubstate {
        #[serde(rename = "target_entity")]
        target_entity: Box<crate::core_api::generated::models::GlobalEntityAssignment>,
    },
    #[serde(rename="KeyValueStoreEntry")]
    KeyValueStoreEntrySubstate {
        /// The hex-encoded bytes of its key
        #[serde(rename = "key_hex")]
        key_hex: String,
        #[serde(rename = "is_deleted")]
        is_deleted: bool,
        #[serde(rename = "data_struct", skip_serializing_if = "Option::is_none")]
        data_struct: Option<Box<crate::core_api::generated::models::DataStruct>>,
    },
    #[serde(rename="Metadata")]
    MetadataSubstate {
        #[serde(rename = "metadata")]
        metadata: Vec<crate::core_api::generated::models::MetadataSubstateAllOfMetadata>,
    },
    #[serde(rename="NonFungibleStoreEntry")]
    NonFungibleStoreEntrySubstate {
        #[serde(rename = "non_fungible_id")]
        non_fungible_id: Box<crate::core_api::generated::models::NonFungibleId>,
        #[serde(rename = "non_fungible_data", skip_serializing_if = "Option::is_none")]
        non_fungible_data: Option<Box<crate::core_api::generated::models::NonFungibleData>>,
        #[serde(rename = "is_deleted")]
        is_deleted: bool,
    },
    #[serde(rename="PackageInfo")]
    PackageInfoSubstate {
        /// The hex-encoded package code
        #[serde(rename = "code_hex")]
        code_hex: String,
        /// A map from the blueprint name to BlueprintData
        #[serde(rename = "blueprints")]
        blueprints: ::std::collections::HashMap<String, crate::core_api::generated::models::BlueprintData>,
    },
    #[serde(rename="PackageRoyaltyAccumulator")]
    PackageRoyaltyAccumulatorSubstate {
        /// The string-encoded decimal representing the amount of XRD available for claim in the royalty accumulator. A decimal is formed of some signed integer `m` of attos (`10^(-18)`) units, where `-2^(256 - 1) <= m < 2^(256 - 1)`. 
        #[serde(rename = "xrd_amount")]
        xrd_amount: String,
    },
    #[serde(rename="PackageRoyaltyConfig")]
    PackageRoyaltyConfigSubstate {
        #[serde(rename = "blueprint_royalties")]
        blueprint_royalties: Vec<crate::core_api::generated::models::BlueprintRoyaltyConfig>,
    },
    #[serde(rename="ResourceManager")]
    ResourceManagerSubstate {
        #[serde(rename = "resource_type")]
        resource_type: crate::core_api::generated::models::ResourceType,
        #[serde(rename = "fungible_divisibility", skip_serializing_if = "Option::is_none")]
        fungible_divisibility: Option<i32>,
        #[serde(rename = "non_fungible_id_type", skip_serializing_if = "Option::is_none")]
        non_fungible_id_type: Option<crate::core_api::generated::models::NonFungibleIdType>,
        /// The string-encoded decimal representing the total supply of this resource. A decimal is formed of some signed integer `m` of attos (`10^(-18)`) units, where `-2^(256 - 1) <= m < 2^(256 - 1)`. 
        #[serde(rename = "total_supply")]
        total_supply: String,
        #[serde(rename = "owned_non_fungible_store", skip_serializing_if = "Option::is_none")]
        owned_non_fungible_store: Option<Box<crate::core_api::generated::models::EntityReference>>,
    },
    #[serde(rename="ValidatorSet")]
    ValidatorSetSubstate {
        #[serde(rename = "validator_set")]
        validator_set: Vec<crate::core_api::generated::models::EcdsaSecp256k1PublicKey>,
    },
    #[serde(rename="Vault")]
    VaultSubstate {
        #[serde(rename = "resource_amount")]
        resource_amount: Box<crate::core_api::generated::models::ResourceAmount>,
    },
}




