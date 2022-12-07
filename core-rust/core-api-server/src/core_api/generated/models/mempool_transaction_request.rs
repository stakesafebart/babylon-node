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
pub struct MempoolTransactionRequest {
    /// The logical name of the network
    #[serde(rename = "network")]
    pub network: String,
    /// The hex-encoded notarized transaction hash. This is known as the Notarized Transaction Hash, Payload Hash or User Payload Hash. This hash is `SHA256(SHA256(compiled_notarized_transaction))`
    #[serde(rename = "payload_hash")]
    pub payload_hash: String,
}

impl MempoolTransactionRequest {
    pub fn new(network: String, payload_hash: String) -> MempoolTransactionRequest {
        MempoolTransactionRequest {
            network,
            payload_hash,
        }
    }
}

