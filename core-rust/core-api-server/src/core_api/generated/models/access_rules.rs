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
pub struct AccessRules {
    #[serde(rename = "method_auth")]
    pub method_auth: Vec<crate::core_api::generated::models::MethodAuthEntry>,
    #[serde(rename = "grouped_auth")]
    pub grouped_auth: Vec<crate::core_api::generated::models::GroupedAuthEntry>,
    #[serde(rename = "default_auth")]
    pub default_auth: Option<crate::core_api::generated::models::AccessRule>, // Using Option permits Default trait; Will always be Some in normal use
    #[serde(rename = "method_auth_mutability")]
    pub method_auth_mutability: Vec<crate::core_api::generated::models::MethodAuthMutabilityEntry>,
    #[serde(rename = "grouped_auth_mutability")]
    pub grouped_auth_mutability: Vec<crate::core_api::generated::models::GroupedAuthEntry>,
    #[serde(rename = "default_auth_mutability")]
    pub default_auth_mutability: Option<crate::core_api::generated::models::AccessRule>, // Using Option permits Default trait; Will always be Some in normal use
}

impl AccessRules {
    pub fn new(method_auth: Vec<crate::core_api::generated::models::MethodAuthEntry>, grouped_auth: Vec<crate::core_api::generated::models::GroupedAuthEntry>, default_auth: crate::core_api::generated::models::AccessRule, method_auth_mutability: Vec<crate::core_api::generated::models::MethodAuthMutabilityEntry>, grouped_auth_mutability: Vec<crate::core_api::generated::models::GroupedAuthEntry>, default_auth_mutability: crate::core_api::generated::models::AccessRule) -> AccessRules {
        AccessRules {
            method_auth,
            grouped_auth,
            default_auth: Option::Some(default_auth),
            method_auth_mutability,
            grouped_auth_mutability,
            default_auth_mutability: Option::Some(default_auth_mutability),
        }
    }
}


