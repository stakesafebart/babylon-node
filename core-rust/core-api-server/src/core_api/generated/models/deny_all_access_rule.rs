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
pub struct DenyAllAccessRule {
    #[serde(rename = "type")]
    pub _type: crate::core_api::generated::models::AccessRuleType,
}

impl DenyAllAccessRule {
    pub fn new(_type: crate::core_api::generated::models::AccessRuleType) -> DenyAllAccessRule {
        DenyAllAccessRule {
            _type,
        }
    }
}

