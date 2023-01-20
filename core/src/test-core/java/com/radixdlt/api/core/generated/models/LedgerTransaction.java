/*
 * Babylon Core API
 * This API is exposed by the Babylon Radix node to give clients access to the Radix Engine, Mempool and State in the node. It is intended for use by node-runners on a private network, and is not intended to be exposed publicly. Heavy load may impact the node's function.  If you require queries against historical ledger state, you may also wish to consider using the [Gateway API](https://betanet-gateway.redoc.ly/). 
 *
 * The version of the OpenAPI document: 0.2.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.radixdlt.api.core.generated.models;

import java.util.Objects;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.radixdlt.api.core.generated.models.LedgerTransactionType;
import com.radixdlt.api.core.generated.models.NotarizedTransaction;
import com.radixdlt.api.core.generated.models.SystemLedgerTransaction;
import com.radixdlt.api.core.generated.models.SystemTransaction;
import com.radixdlt.api.core.generated.models.UserLedgerTransaction;
import com.radixdlt.api.core.generated.models.ValidatorLedgerTransaction;
import com.radixdlt.api.core.generated.models.ValidatorTransaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.radixdlt.api.core.generated.client.JSON;

@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@JsonDeserialize(using = LedgerTransaction.LedgerTransactionDeserializer.class)
@JsonSerialize(using = LedgerTransaction.LedgerTransactionSerializer.class)
public class LedgerTransaction extends AbstractOpenApiSchema {
    private static final Logger log = Logger.getLogger(LedgerTransaction.class.getName());

    public static class LedgerTransactionSerializer extends StdSerializer<LedgerTransaction> {
        public LedgerTransactionSerializer(Class<LedgerTransaction> t) {
            super(t);
        }

        public LedgerTransactionSerializer() {
            this(null);
        }

        @Override
        public void serialize(LedgerTransaction value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeObject(value.getActualInstance());
        }
    }

    public static class LedgerTransactionDeserializer extends StdDeserializer<LedgerTransaction> {
        public LedgerTransactionDeserializer() {
            this(LedgerTransaction.class);
        }

        public LedgerTransactionDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public LedgerTransaction deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode tree = jp.readValueAsTree();
            Object deserialized = null;
            LedgerTransaction newLedgerTransaction = new LedgerTransaction();
            Map<String,Object> result2 = tree.traverse(jp.getCodec()).readValueAs(new TypeReference<Map<String, Object>>() {});
            String discriminatorValue = (String)result2.get("type");
            switch (discriminatorValue) {
                case "System":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(SystemLedgerTransaction.class);
                    newLedgerTransaction.setActualInstance(deserialized);
                    return newLedgerTransaction;
                case "SystemLedgerTransaction":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(SystemLedgerTransaction.class);
                    newLedgerTransaction.setActualInstance(deserialized);
                    return newLedgerTransaction;
                case "User":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(UserLedgerTransaction.class);
                    newLedgerTransaction.setActualInstance(deserialized);
                    return newLedgerTransaction;
                case "UserLedgerTransaction":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(UserLedgerTransaction.class);
                    newLedgerTransaction.setActualInstance(deserialized);
                    return newLedgerTransaction;
                case "Validator":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(ValidatorLedgerTransaction.class);
                    newLedgerTransaction.setActualInstance(deserialized);
                    return newLedgerTransaction;
                case "ValidatorLedgerTransaction":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(ValidatorLedgerTransaction.class);
                    newLedgerTransaction.setActualInstance(deserialized);
                    return newLedgerTransaction;
                default:
                    log.log(Level.WARNING, String.format("Failed to lookup discriminator value `%s` for LedgerTransaction. Possible values: System SystemLedgerTransaction User UserLedgerTransaction Validator ValidatorLedgerTransaction", discriminatorValue));
            }

            boolean typeCoercion = ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS);
            int match = 0;
            JsonToken token = tree.traverse(jp.getCodec()).nextToken();
            // deserialize SystemLedgerTransaction
            try {
                boolean attemptParsing = true;
                // ensure that we respect type coercion as set on the client ObjectMapper
                if (SystemLedgerTransaction.class.equals(Integer.class) || SystemLedgerTransaction.class.equals(Long.class) || SystemLedgerTransaction.class.equals(Float.class) || SystemLedgerTransaction.class.equals(Double.class) || SystemLedgerTransaction.class.equals(Boolean.class) || SystemLedgerTransaction.class.equals(String.class)) {
                    attemptParsing = typeCoercion;
                    if (!attemptParsing) {
                        attemptParsing |= ((SystemLedgerTransaction.class.equals(Integer.class) || SystemLedgerTransaction.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                        attemptParsing |= ((SystemLedgerTransaction.class.equals(Float.class) || SystemLedgerTransaction.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                        attemptParsing |= (SystemLedgerTransaction.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                        attemptParsing |= (SystemLedgerTransaction.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                    }
                }
                if (attemptParsing) {
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(SystemLedgerTransaction.class);
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'SystemLedgerTransaction'");
                }
            } catch (Exception e) {
                // deserialization failed, continue
                log.log(Level.FINER, "Input data does not match schema 'SystemLedgerTransaction'", e);
            }

            // deserialize UserLedgerTransaction
            try {
                boolean attemptParsing = true;
                // ensure that we respect type coercion as set on the client ObjectMapper
                if (UserLedgerTransaction.class.equals(Integer.class) || UserLedgerTransaction.class.equals(Long.class) || UserLedgerTransaction.class.equals(Float.class) || UserLedgerTransaction.class.equals(Double.class) || UserLedgerTransaction.class.equals(Boolean.class) || UserLedgerTransaction.class.equals(String.class)) {
                    attemptParsing = typeCoercion;
                    if (!attemptParsing) {
                        attemptParsing |= ((UserLedgerTransaction.class.equals(Integer.class) || UserLedgerTransaction.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                        attemptParsing |= ((UserLedgerTransaction.class.equals(Float.class) || UserLedgerTransaction.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                        attemptParsing |= (UserLedgerTransaction.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                        attemptParsing |= (UserLedgerTransaction.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                    }
                }
                if (attemptParsing) {
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(UserLedgerTransaction.class);
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'UserLedgerTransaction'");
                }
            } catch (Exception e) {
                // deserialization failed, continue
                log.log(Level.FINER, "Input data does not match schema 'UserLedgerTransaction'", e);
            }

            // deserialize ValidatorLedgerTransaction
            try {
                boolean attemptParsing = true;
                // ensure that we respect type coercion as set on the client ObjectMapper
                if (ValidatorLedgerTransaction.class.equals(Integer.class) || ValidatorLedgerTransaction.class.equals(Long.class) || ValidatorLedgerTransaction.class.equals(Float.class) || ValidatorLedgerTransaction.class.equals(Double.class) || ValidatorLedgerTransaction.class.equals(Boolean.class) || ValidatorLedgerTransaction.class.equals(String.class)) {
                    attemptParsing = typeCoercion;
                    if (!attemptParsing) {
                        attemptParsing |= ((ValidatorLedgerTransaction.class.equals(Integer.class) || ValidatorLedgerTransaction.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                        attemptParsing |= ((ValidatorLedgerTransaction.class.equals(Float.class) || ValidatorLedgerTransaction.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                        attemptParsing |= (ValidatorLedgerTransaction.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                        attemptParsing |= (ValidatorLedgerTransaction.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                    }
                }
                if (attemptParsing) {
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(ValidatorLedgerTransaction.class);
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'ValidatorLedgerTransaction'");
                }
            } catch (Exception e) {
                // deserialization failed, continue
                log.log(Level.FINER, "Input data does not match schema 'ValidatorLedgerTransaction'", e);
            }

            if (match == 1) {
                LedgerTransaction ret = new LedgerTransaction();
                ret.setActualInstance(deserialized);
                return ret;
            }
            throw new IOException(String.format("Failed deserialization for LedgerTransaction: %d classes match result, expected 1", match));
        }

        /**
         * Handle deserialization of the 'null' value.
         */
        @Override
        public LedgerTransaction getNullValue(DeserializationContext ctxt) throws JsonMappingException {
            throw new JsonMappingException(ctxt.getParser(), "LedgerTransaction cannot be null");
        }
    }

    // store a list of schema names defined in oneOf
    public static final Map<String, Class<?>> schemas = new HashMap<>();

    public LedgerTransaction() {
        super("oneOf", Boolean.FALSE);
    }

    public LedgerTransaction(SystemLedgerTransaction o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public LedgerTransaction(UserLedgerTransaction o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public LedgerTransaction(ValidatorLedgerTransaction o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    static {
        schemas.put("SystemLedgerTransaction", SystemLedgerTransaction.class);
        schemas.put("UserLedgerTransaction", UserLedgerTransaction.class);
        schemas.put("ValidatorLedgerTransaction", ValidatorLedgerTransaction.class);
        JSON.registerDescendants(LedgerTransaction.class, Collections.unmodifiableMap(schemas));
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("System", SystemLedgerTransaction.class);
        mappings.put("SystemLedgerTransaction", SystemLedgerTransaction.class);
        mappings.put("User", UserLedgerTransaction.class);
        mappings.put("UserLedgerTransaction", UserLedgerTransaction.class);
        mappings.put("Validator", ValidatorLedgerTransaction.class);
        mappings.put("ValidatorLedgerTransaction", ValidatorLedgerTransaction.class);
        mappings.put("LedgerTransaction", LedgerTransaction.class);
        JSON.registerDiscriminator(LedgerTransaction.class, "type", mappings);
    }

    @Override
    public Map<String, Class<?>> getSchemas() {
        return LedgerTransaction.schemas;
    }

    /**
     * Set the instance that matches the oneOf child schema, check
     * the instance parameter is valid against the oneOf child schemas:
     * SystemLedgerTransaction, UserLedgerTransaction, ValidatorLedgerTransaction
     *
     * It could be an instance of the 'oneOf' schemas.
     * The oneOf child schemas may themselves be a composed schema (allOf, anyOf, oneOf).
     */
    @Override
    public void setActualInstance(Object instance) {
        if (JSON.isInstanceOf(SystemLedgerTransaction.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(UserLedgerTransaction.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(ValidatorLedgerTransaction.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        throw new RuntimeException("Invalid instance type. Must be SystemLedgerTransaction, UserLedgerTransaction, ValidatorLedgerTransaction");
    }

    /**
     * Get the actual instance, which can be the following:
     * SystemLedgerTransaction, UserLedgerTransaction, ValidatorLedgerTransaction
     *
     * @return The actual instance (SystemLedgerTransaction, UserLedgerTransaction, ValidatorLedgerTransaction)
     */
    @Override
    public Object getActualInstance() {
        return super.getActualInstance();
    }

    /**
     * Get the actual instance of `SystemLedgerTransaction`. If the actual instance is not `SystemLedgerTransaction`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `SystemLedgerTransaction`
     * @throws ClassCastException if the instance is not `SystemLedgerTransaction`
     */
    public SystemLedgerTransaction getSystemLedgerTransaction() throws ClassCastException {
        return (SystemLedgerTransaction)super.getActualInstance();
    }

    /**
     * Get the actual instance of `UserLedgerTransaction`. If the actual instance is not `UserLedgerTransaction`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `UserLedgerTransaction`
     * @throws ClassCastException if the instance is not `UserLedgerTransaction`
     */
    public UserLedgerTransaction getUserLedgerTransaction() throws ClassCastException {
        return (UserLedgerTransaction)super.getActualInstance();
    }

    /**
     * Get the actual instance of `ValidatorLedgerTransaction`. If the actual instance is not `ValidatorLedgerTransaction`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `ValidatorLedgerTransaction`
     * @throws ClassCastException if the instance is not `ValidatorLedgerTransaction`
     */
    public ValidatorLedgerTransaction getValidatorLedgerTransaction() throws ClassCastException {
        return (ValidatorLedgerTransaction)super.getActualInstance();
    }

}

