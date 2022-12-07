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
pub struct SchemaPathDynamicAmountAllOf {
    #[serde(rename = "schema_path")]
    pub schema_path: Vec<crate::core_api::generated::models::SchemaSubpath>,
}

impl SchemaPathDynamicAmountAllOf {
    pub fn new(schema_path: Vec<crate::core_api::generated::models::SchemaSubpath>) -> SchemaPathDynamicAmountAllOf {
        SchemaPathDynamicAmountAllOf {
            schema_path,
        }
    }
}

