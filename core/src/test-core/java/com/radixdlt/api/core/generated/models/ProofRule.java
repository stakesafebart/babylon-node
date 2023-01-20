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
import com.radixdlt.api.core.generated.models.AllOfProofRule;
import com.radixdlt.api.core.generated.models.AmountOfProofRule;
import com.radixdlt.api.core.generated.models.AnyOfProofRule;
import com.radixdlt.api.core.generated.models.CountOfProofRule;
import com.radixdlt.api.core.generated.models.DynamicAmount;
import com.radixdlt.api.core.generated.models.DynamicCount;
import com.radixdlt.api.core.generated.models.DynamicResourceDescriptor;
import com.radixdlt.api.core.generated.models.DynamicResourceDescriptorList;
import com.radixdlt.api.core.generated.models.ProofRuleType;
import com.radixdlt.api.core.generated.models.RequireProofRule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.radixdlt.api.core.generated.client.JSON;

@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@JsonDeserialize(using=ProofRule.ProofRuleDeserializer.class)
@JsonSerialize(using = ProofRule.ProofRuleSerializer.class)
public class ProofRule extends AbstractOpenApiSchema {
    private static final Logger log = Logger.getLogger(ProofRule.class.getName());

    public static class ProofRuleSerializer extends StdSerializer<ProofRule> {
        public ProofRuleSerializer(Class<ProofRule> t) {
            super(t);
        }

        public ProofRuleSerializer() {
            this(null);
        }

        @Override
        public void serialize(ProofRule value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeObject(value.getActualInstance());
        }
    }

    public static class ProofRuleDeserializer extends StdDeserializer<ProofRule> {
        public ProofRuleDeserializer() {
            this(ProofRule.class);
        }

        public ProofRuleDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public ProofRule deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode tree = jp.readValueAsTree();

            Object deserialized = null;
            Class<?> cls = JSON.getClassForElement(tree, ProofRule.class);
            if (cls != null) {
                // When the OAS schema includes a discriminator, use the discriminator value to
                // discriminate the anyOf schemas.
                // Get the discriminator mapping value to get the class.
                deserialized = tree.traverse(jp.getCodec()).readValueAs(cls);
                ProofRule ret = new ProofRule();
                ret.setActualInstance(deserialized);
                return ret;
            }
            // deserialize AllOfProofRule
            try {
                deserialized = tree.traverse(jp.getCodec()).readValueAs(AllOfProofRule.class);
                ProofRule ret = new ProofRule();
                ret.setActualInstance(deserialized);
                return ret;
            } catch (Exception e) {
                // deserialization failed, continue, log to help debugging
                log.log(Level.FINER, "Input data does not match 'ProofRule'", e);
            }

            // deserialize AmountOfProofRule
            try {
                deserialized = tree.traverse(jp.getCodec()).readValueAs(AmountOfProofRule.class);
                ProofRule ret = new ProofRule();
                ret.setActualInstance(deserialized);
                return ret;
            } catch (Exception e) {
                // deserialization failed, continue, log to help debugging
                log.log(Level.FINER, "Input data does not match 'ProofRule'", e);
            }

            // deserialize AnyOfProofRule
            try {
                deserialized = tree.traverse(jp.getCodec()).readValueAs(AnyOfProofRule.class);
                ProofRule ret = new ProofRule();
                ret.setActualInstance(deserialized);
                return ret;
            } catch (Exception e) {
                // deserialization failed, continue, log to help debugging
                log.log(Level.FINER, "Input data does not match 'ProofRule'", e);
            }

            // deserialize CountOfProofRule
            try {
                deserialized = tree.traverse(jp.getCodec()).readValueAs(CountOfProofRule.class);
                ProofRule ret = new ProofRule();
                ret.setActualInstance(deserialized);
                return ret;
            } catch (Exception e) {
                // deserialization failed, continue, log to help debugging
                log.log(Level.FINER, "Input data does not match 'ProofRule'", e);
            }

            // deserialize RequireProofRule
            try {
                deserialized = tree.traverse(jp.getCodec()).readValueAs(RequireProofRule.class);
                ProofRule ret = new ProofRule();
                ret.setActualInstance(deserialized);
                return ret;
            } catch (Exception e) {
                // deserialization failed, continue, log to help debugging
                log.log(Level.FINER, "Input data does not match 'ProofRule'", e);
            }

            throw new IOException(String.format("Failed deserialization for ProofRule: no match found"));
        }

        /**
         * Handle deserialization of the 'null' value.
         */
        @Override
        public ProofRule getNullValue(DeserializationContext ctxt) throws JsonMappingException {
            throw new JsonMappingException(ctxt.getParser(), "ProofRule cannot be null");
        }
    }

    // store a list of schema names defined in anyOf
    public static final Map<String, Class<?>> schemas = new HashMap<String, Class<?>>();

    public ProofRule() {
        super("anyOf", Boolean.FALSE);
    }

    public ProofRule(AllOfProofRule o) {
        super("anyOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public ProofRule(AmountOfProofRule o) {
        super("anyOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public ProofRule(AnyOfProofRule o) {
        super("anyOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public ProofRule(CountOfProofRule o) {
        super("anyOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public ProofRule(RequireProofRule o) {
        super("anyOf", Boolean.FALSE);
        setActualInstance(o);
    }

    static {
        schemas.put("AllOfProofRule", AllOfProofRule.class);
        schemas.put("AmountOfProofRule", AmountOfProofRule.class);
        schemas.put("AnyOfProofRule", AnyOfProofRule.class);
        schemas.put("CountOfProofRule", CountOfProofRule.class);
        schemas.put("RequireProofRule", RequireProofRule.class);
        JSON.registerDescendants(ProofRule.class, Collections.unmodifiableMap(schemas));
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("AllOf", AllOfProofRule.class);
        mappings.put("AllOfProofRule", AllOfProofRule.class);
        mappings.put("AmountOf", AmountOfProofRule.class);
        mappings.put("AmountOfProofRule", AmountOfProofRule.class);
        mappings.put("AnyOf", AnyOfProofRule.class);
        mappings.put("AnyOfProofRule", AnyOfProofRule.class);
        mappings.put("CountOf", CountOfProofRule.class);
        mappings.put("CountOfProofRule", CountOfProofRule.class);
        mappings.put("Require", RequireProofRule.class);
        mappings.put("RequireProofRule", RequireProofRule.class);
        mappings.put("ProofRule", ProofRule.class);
        JSON.registerDiscriminator(ProofRule.class, "type", mappings);
    }

    @Override
    public Map<String, Class<?>> getSchemas() {
        return ProofRule.schemas;
    }

    /**
     * Set the instance that matches the anyOf child schema, check
     * the instance parameter is valid against the anyOf child schemas:
     * AllOfProofRule, AmountOfProofRule, AnyOfProofRule, CountOfProofRule, RequireProofRule
     *
     * It could be an instance of the 'anyOf' schemas.
     * The anyOf child schemas may themselves be a composed schema (allOf, anyOf, anyOf).
     */
    @Override
    public void setActualInstance(Object instance) {
        if (JSON.isInstanceOf(AllOfProofRule.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(AmountOfProofRule.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(AnyOfProofRule.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(CountOfProofRule.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(RequireProofRule.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        throw new RuntimeException("Invalid instance type. Must be AllOfProofRule, AmountOfProofRule, AnyOfProofRule, CountOfProofRule, RequireProofRule");
    }

    /**
     * Get the actual instance, which can be the following:
     * AllOfProofRule, AmountOfProofRule, AnyOfProofRule, CountOfProofRule, RequireProofRule
     *
     * @return The actual instance (AllOfProofRule, AmountOfProofRule, AnyOfProofRule, CountOfProofRule, RequireProofRule)
     */
    @Override
    public Object getActualInstance() {
        return super.getActualInstance();
    }

    /**
     * Get the actual instance of `AllOfProofRule`. If the actual instance is not `AllOfProofRule`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `AllOfProofRule`
     * @throws ClassCastException if the instance is not `AllOfProofRule`
     */
    public AllOfProofRule getAllOfProofRule() throws ClassCastException {
        return (AllOfProofRule)super.getActualInstance();
    }

    /**
     * Get the actual instance of `AmountOfProofRule`. If the actual instance is not `AmountOfProofRule`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `AmountOfProofRule`
     * @throws ClassCastException if the instance is not `AmountOfProofRule`
     */
    public AmountOfProofRule getAmountOfProofRule() throws ClassCastException {
        return (AmountOfProofRule)super.getActualInstance();
    }

    /**
     * Get the actual instance of `AnyOfProofRule`. If the actual instance is not `AnyOfProofRule`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `AnyOfProofRule`
     * @throws ClassCastException if the instance is not `AnyOfProofRule`
     */
    public AnyOfProofRule getAnyOfProofRule() throws ClassCastException {
        return (AnyOfProofRule)super.getActualInstance();
    }

    /**
     * Get the actual instance of `CountOfProofRule`. If the actual instance is not `CountOfProofRule`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `CountOfProofRule`
     * @throws ClassCastException if the instance is not `CountOfProofRule`
     */
    public CountOfProofRule getCountOfProofRule() throws ClassCastException {
        return (CountOfProofRule)super.getActualInstance();
    }

    /**
     * Get the actual instance of `RequireProofRule`. If the actual instance is not `RequireProofRule`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `RequireProofRule`
     * @throws ClassCastException if the instance is not `RequireProofRule`
     */
    public RequireProofRule getRequireProofRule() throws ClassCastException {
        return (RequireProofRule)super.getActualInstance();
    }

}

