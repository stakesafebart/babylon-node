/*
 * Babylon Core API
 *
 * This API is exposed by the Babylon Radix node to give clients access to the Radix Engine, Mempool and State in the node. It is intended for use by node-runners on a private network, and is not intended to be exposed publicly. Heavy load may impact the node's function.  If you require queries against historical ledger state, you may also wish to consider using the [Gateway API](https://betanet-gateway.redoc.ly/). 
 *
 * The version of the OpenAPI document: 0.1.0
 * 
 * Generated by: https://openapi-generator.tech
 */




#[derive(Clone, Debug, PartialEq, Default, serde::Serialize, serde::Deserialize)]
pub struct DeletedSubstateVersionRef {
    #[serde(rename = "substate_id")]
    pub substate_id: Box<crate::core_api::generated::models::SubstateId>,
    /// The hex-encoded single-SHA256 hash of the substate data bytes
    #[serde(rename = "substate_data_hash")]
    pub substate_data_hash: String,
    /// An integer between `0` and `10^13`, counting the number of times the substate was updated
    #[serde(rename = "version")]
    pub version: i64,
}

impl DeletedSubstateVersionRef {
    pub fn new(substate_id: crate::core_api::generated::models::SubstateId, substate_data_hash: String, version: i64) -> DeletedSubstateVersionRef {
        DeletedSubstateVersionRef {
            substate_id: Box::new(substate_id),
            substate_data_hash,
            version,
        }
    }
}


