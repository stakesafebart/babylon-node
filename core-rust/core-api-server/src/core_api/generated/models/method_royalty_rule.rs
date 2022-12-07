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
pub struct MethodRoyaltyRule {
    #[serde(rename = "method_name")]
    pub method_name: String,
    /// An integer between `0` and `2^32 - 1`, representing the number of cost units required to access this method.
    #[serde(rename = "royalty_rule")]
    pub royalty_rule: i64,
}

impl MethodRoyaltyRule {
    pub fn new(method_name: String, royalty_rule: i64) -> MethodRoyaltyRule {
        MethodRoyaltyRule {
            method_name,
            royalty_rule,
        }
    }
}

