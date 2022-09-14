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
pub struct VaultSubstateAllOf {
    #[serde(rename = "resource_amount")]
    pub resource_amount: Option<crate::core_api::generated::models::ResourceAmount>, // Using Option permits Default trait; Will always be Some in normal use
}

impl VaultSubstateAllOf {
    pub fn new(resource_amount: crate::core_api::generated::models::ResourceAmount) -> VaultSubstateAllOf {
        VaultSubstateAllOf {
            resource_amount: Option::Some(resource_amount),
        }
    }
}

