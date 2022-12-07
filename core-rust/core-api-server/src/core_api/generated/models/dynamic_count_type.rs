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
pub enum DynamicCountType {
    #[serde(rename = "Count")]
    Count,
    #[serde(rename = "SchemaPath")]
    SchemaPath,

}

impl ToString for DynamicCountType {
    fn to_string(&self) -> String {
        match self {
            Self::Count => String::from("Count"),
            Self::SchemaPath => String::from("SchemaPath"),
        }
    }
}

impl Default for DynamicCountType {
    fn default() -> DynamicCountType {
        Self::Count
    }
}



