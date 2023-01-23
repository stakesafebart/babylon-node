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
pub struct StateNonFungibleRequest {
    /// The logical name of the network
    #[serde(rename = "network")]
    pub network: String,
    /// The Bech32m-encoded human readable version of the resource's global address
    #[serde(rename = "resource_address")]
    pub resource_address: String,
    /// The simple string representation of the non-fungible id. For string id types, this is simply the string itself; for integer types, this is the integer as a decimal; and for the bytes id type, this is the lower case hex representation. A non-fungible resource has a fixed `NonFungibleIdType`, so this representation uniquely identifies this non-fungible under the given resource address. 
    #[serde(rename = "non_fungible_id")]
    pub non_fungible_id: String,
}

impl StateNonFungibleRequest {
    pub fn new(network: String, resource_address: String, non_fungible_id: String) -> StateNonFungibleRequest {
        StateNonFungibleRequest {
            network,
            resource_address,
            non_fungible_id,
        }
    }
}

