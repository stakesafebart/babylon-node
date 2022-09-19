use state_manager::{IntentHash, PayloadHash};

use crate::core_api::*;

#[allow(dead_code)]
pub fn to_api_intent_hash(intent_hash: &IntentHash) -> String {
    to_hex(intent_hash.0)
}

pub fn to_api_payload_hash(payload_hash: &PayloadHash) -> String {
    to_hex(payload_hash.0)
}

pub fn extract_intent_hash(intent_hash_str: String) -> Result<IntentHash, ExtractionError> {
    let intent_hash_bytes = from_hex(intent_hash_str)?;
    Ok(IntentHash(
        intent_hash_bytes
            .try_into()
            .map_err(|_| ExtractionError::InvalidHash)?,
    ))
}

#[allow(dead_code)]
pub fn extract_payload_hash(intent_hash_str: String) -> Result<PayloadHash, ExtractionError> {
    let intent_hash_bytes = from_hex(intent_hash_str)?;
    Ok(PayloadHash(
        intent_hash_bytes
            .try_into()
            .map_err(|_| ExtractionError::InvalidHash)?,
    ))
}