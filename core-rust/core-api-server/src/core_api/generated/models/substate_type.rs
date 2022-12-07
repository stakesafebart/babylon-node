/*
 * Babylon Core API
 *
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 0.1.0
 * 
 * Generated by: https://openapi-generator.tech
 */


/// 
#[derive(Clone, Copy, Debug, Eq, PartialEq, Ord, PartialOrd, Hash, serde::Serialize, serde::Deserialize)]
pub enum SubstateType {
    #[serde(rename = "Metadata")]
    Metadata,
    #[serde(rename = "AccessRulesChain")]
    AccessRulesChain,
    #[serde(rename = "GlobalAddress")]
    GlobalAddress,
    #[serde(rename = "ComponentInfo")]
    ComponentInfo,
    #[serde(rename = "ComponentState")]
    ComponentState,
    #[serde(rename = "ComponentRoyaltyConfig")]
    ComponentRoyaltyConfig,
    #[serde(rename = "ComponentRoyaltyAccumulator")]
    ComponentRoyaltyAccumulator,
    #[serde(rename = "PackageInfo")]
    PackageInfo,
    #[serde(rename = "PackageRoyaltyConfig")]
    PackageRoyaltyConfig,
    #[serde(rename = "PackageRoyaltyAccumulator")]
    PackageRoyaltyAccumulator,
    #[serde(rename = "ResourceManager")]
    ResourceManager,
    #[serde(rename = "EpochManager")]
    EpochManager,
    #[serde(rename = "ClockCurrentMinute")]
    ClockCurrentMinute,
    #[serde(rename = "KeyValueStoreEntry")]
    KeyValueStoreEntry,
    #[serde(rename = "NonFungibleStoreEntry")]
    NonFungibleStoreEntry,
    #[serde(rename = "Vault")]
    Vault,

}

impl ToString for SubstateType {
    fn to_string(&self) -> String {
        match self {
            Self::Metadata => String::from("Metadata"),
            Self::AccessRulesChain => String::from("AccessRulesChain"),
            Self::GlobalAddress => String::from("GlobalAddress"),
            Self::ComponentInfo => String::from("ComponentInfo"),
            Self::ComponentState => String::from("ComponentState"),
            Self::ComponentRoyaltyConfig => String::from("ComponentRoyaltyConfig"),
            Self::ComponentRoyaltyAccumulator => String::from("ComponentRoyaltyAccumulator"),
            Self::PackageInfo => String::from("PackageInfo"),
            Self::PackageRoyaltyConfig => String::from("PackageRoyaltyConfig"),
            Self::PackageRoyaltyAccumulator => String::from("PackageRoyaltyAccumulator"),
            Self::ResourceManager => String::from("ResourceManager"),
            Self::EpochManager => String::from("EpochManager"),
            Self::ClockCurrentMinute => String::from("ClockCurrentMinute"),
            Self::KeyValueStoreEntry => String::from("KeyValueStoreEntry"),
            Self::NonFungibleStoreEntry => String::from("NonFungibleStoreEntry"),
            Self::Vault => String::from("Vault"),
        }
    }
}

impl Default for SubstateType {
    fn default() -> SubstateType {
        Self::Metadata
    }
}




