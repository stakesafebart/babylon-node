/*
 * Babylon Core API
 *
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 0.1.0
 * 
 * Generated by: https://openapi-generator.tech
 */



#[derive(Clone, Debug, PartialEq, serde::Serialize, serde::Deserialize)]
#[serde(tag = "key_type")]
pub enum SignatureWithPublicKey {
    #[serde(rename="EcdsaSecp256k1")]
    EcdsaSecp256k1SignatureWithPublicKey {
        #[serde(rename = "recoverable_signature")]
        recoverable_signature: Box<crate::core_api::generated::models::EcdsaSecp256k1Signature>,
    },
    #[serde(rename="EddsaEd25519")]
    EddsaEd25519SignatureWithPublicKey {
        #[serde(rename = "public_key")]
        public_key: Box<crate::core_api::generated::models::EddsaEd25519PublicKey>,
        #[serde(rename = "signature")]
        signature: Box<crate::core_api::generated::models::EddsaEd25519Signature>,
    },
}



