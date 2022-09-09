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
pub struct ComponentInfoSubstateAllOf {
    /// Package address, Bech32m-encoded.
    #[serde(rename = "package_address")]
    pub package_address: String,
    #[serde(rename = "blueprint_name")]
    pub blueprint_name: String,
}

impl ComponentInfoSubstateAllOf {
    pub fn new(package_address: String, blueprint_name: String) -> ComponentInfoSubstateAllOf {
        ComponentInfoSubstateAllOf {
            package_address,
            blueprint_name,
        }
    }
}


