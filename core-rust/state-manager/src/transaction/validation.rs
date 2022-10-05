use crate::transaction::types::Transaction;
use crate::transaction::ValidatorTransaction;
use scrypto::buffer::scrypto_decode;
use transaction::errors::TransactionValidationError;
use transaction::model::{NotarizedTransaction, Validated};
use transaction::validation::{
    NotarizedTransactionValidator, TestIntentHashManager, TransactionValidator,
};
use transaction::validation::{ValidationConfig, MAX_PAYLOAD_SIZE};

pub struct UserTransactionValidator {
    pub base_validation_config: ValidationConfig,
    pub intent_hash_manager: TestIntentHashManager,
}

impl UserTransactionValidator {
    /// Checks the Payload max size, and SBOR decodes to a NotarizedTransaction if the size is okay
    pub fn parse_unvalidated_user_transaction_from_slice(
        transaction_payload: &[u8],
    ) -> Result<NotarizedTransaction, TransactionValidationError> {
        if transaction_payload.len() > MAX_PAYLOAD_SIZE {
            return Err(TransactionValidationError::TransactionTooLarge);
        }

        let transaction: NotarizedTransaction = scrypto_decode(transaction_payload)
            .map_err(TransactionValidationError::DeserializationError)?;

        Ok(transaction)
    }

    /// Performs static validation only
    pub fn parse_and_validate_user_transaction_slice(
        &self,
        epoch: u64, // Temporary
        transaction_payload: &[u8],
    ) -> Result<Validated<NotarizedTransaction>, TransactionValidationError> {
        let notarized_transaction =
            Self::parse_unvalidated_user_transaction_from_slice(transaction_payload)?;
        self.validate_user_transaction(epoch, notarized_transaction)
    }

    /// Performs static validation only
    pub fn validate_user_transaction(
        &self,
        epoch: u64, // Temporary
        transaction: NotarizedTransaction,
    ) -> Result<Validated<NotarizedTransaction>, TransactionValidationError> {
        let mut config = self.base_validation_config;
        config.current_epoch = epoch;
        let validator = NotarizedTransactionValidator::new(config);

        validator.validate(transaction, &self.intent_hash_manager)
    }
}

pub struct CommittedTransactionValidator {
    pub base_validation_config: ValidationConfig,
    pub intent_hash_manager: TestIntentHashManager,
}

impl CommittedTransactionValidator {
    pub fn parse_unvalidated_transaction_from_slice(
        transaction_payload: &[u8],
    ) -> Result<Transaction, TransactionValidationError> {
        let transaction: Transaction = scrypto_decode(transaction_payload)
            .map_err(TransactionValidationError::DeserializationError)?;

        Ok(transaction)
    }

    pub fn parse_and_validate_transaction_slice(
        &self,
        epoch: u64, // Temporary
        transaction_payload: &[u8],
    ) -> Result<Validated<Transaction>, TransactionValidationError> {
        // TODO: Need a good way to do payload transaction size here
        let transaction = Self::parse_unvalidated_transaction_from_slice(transaction_payload)?;
        self.validate_transaction(epoch, transaction)
    }

    fn validate_transaction(
        &self,
        epoch: u64, // Temporary
        transaction: Transaction,
    ) -> Result<Validated<Transaction>, TransactionValidationError> {
        let mut config = self.base_validation_config;
        config.current_epoch = epoch;
        let validator = NotarizedTransactionValidator::new(config);
        match transaction {
            Transaction::User(notarized_transaction) => validator
                .validate(notarized_transaction, &self.intent_hash_manager)
                .map(|validated| Validated {
                    transaction: Transaction::User(validated.transaction),
                    transaction_hash: validated.transaction_hash,
                    instructions: validated.instructions,
                    initial_proofs: validated.initial_proofs,
                    cost_unit_limit: validated.cost_unit_limit,
                    tip_percentage: validated.tip_percentage,
                    blobs: validated.blobs,
                }),
            Transaction::Validator(validator_transaction) => {
                let validated: Validated<ValidatorTransaction> = validator_transaction.into();
                Ok(Validated {
                    transaction: Transaction::Validator(validated.transaction),
                    transaction_hash: validated.transaction_hash,
                    instructions: validated.instructions,
                    initial_proofs: validated.initial_proofs,
                    cost_unit_limit: validated.cost_unit_limit,
                    tip_percentage: validated.tip_percentage,
                    blobs: validated.blobs,
                })
            }
        }
    }
}