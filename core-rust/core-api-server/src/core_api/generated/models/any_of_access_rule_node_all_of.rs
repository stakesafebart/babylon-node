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
pub struct AnyOfAccessRuleNodeAllOf {
    #[serde(rename = "access_rules")]
    pub access_rules: Vec<crate::core_api::generated::models::AccessRuleNode>,
}

impl AnyOfAccessRuleNodeAllOf {
    pub fn new(access_rules: Vec<crate::core_api::generated::models::AccessRuleNode>) -> AnyOfAccessRuleNodeAllOf {
        AnyOfAccessRuleNodeAllOf {
            access_rules,
        }
    }
}

