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
pub struct NetworkConfigurationResponse {
    #[serde(rename = "version")]
    pub version: Box<crate::core_api::generated::models::NetworkConfigurationResponseVersion>,
    /// The logical name of the network
    #[serde(rename = "network")]
    pub network: String,
    /// The network suffix used for Bech32m HRPs used for addressing.
    #[serde(rename = "network_hrp_suffix")]
    pub network_hrp_suffix: String,
}

impl NetworkConfigurationResponse {
    pub fn new(version: crate::core_api::generated::models::NetworkConfigurationResponseVersion, network: String, network_hrp_suffix: String) -> NetworkConfigurationResponse {
        NetworkConfigurationResponse {
            version: Box::new(version),
            network,
            network_hrp_suffix,
        }
    }
}

