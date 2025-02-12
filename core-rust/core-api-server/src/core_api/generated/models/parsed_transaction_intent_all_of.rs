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
pub struct ParsedTransactionIntentAllOf {
    #[serde(rename = "intent", skip_serializing_if = "Option::is_none")]
    pub intent: Option<Box<crate::core_api::generated::models::TransactionIntent>>,
    #[serde(rename = "identifiers")]
    pub identifiers: Box<crate::core_api::generated::models::ParsedTransactionIntentAllOfIdentifiers>,
}

impl ParsedTransactionIntentAllOf {
    pub fn new(identifiers: crate::core_api::generated::models::ParsedTransactionIntentAllOfIdentifiers) -> ParsedTransactionIntentAllOf {
        ParsedTransactionIntentAllOf {
            intent: None,
            identifiers: Box::new(identifiers),
        }
    }
}


