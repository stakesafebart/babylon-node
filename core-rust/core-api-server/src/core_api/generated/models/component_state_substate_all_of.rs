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
pub struct ComponentStateSubstateAllOf {
    /// hex-encoded state data
    #[serde(rename = "state")]
    pub state: String,
}

impl ComponentStateSubstateAllOf {
    pub fn new(state: String) -> ComponentStateSubstateAllOf {
        ComponentStateSubstateAllOf {
            state,
        }
    }
}


