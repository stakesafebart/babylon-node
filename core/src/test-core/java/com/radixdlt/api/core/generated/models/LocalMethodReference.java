/*
 * Babylon Core API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 0.1.0
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
import com.radixdlt.api.core.generated.models.LocalMethodReferenceType;
import com.radixdlt.api.core.generated.models.LocalNativeFunctionReference;
import com.radixdlt.api.core.generated.models.LocalNativeMethodReference;
import com.radixdlt.api.core.generated.models.LocalScryptoMethodReference;
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
@JsonDeserialize(using=LocalMethodReference.LocalMethodReferenceDeserializer.class)
@JsonSerialize(using = LocalMethodReference.LocalMethodReferenceSerializer.class)
public class LocalMethodReference extends AbstractOpenApiSchema {
    private static final Logger log = Logger.getLogger(LocalMethodReference.class.getName());

    public static class LocalMethodReferenceSerializer extends StdSerializer<LocalMethodReference> {
        public LocalMethodReferenceSerializer(Class<LocalMethodReference> t) {
            super(t);
        }

        public LocalMethodReferenceSerializer() {
            this(null);
        }

        @Override
        public void serialize(LocalMethodReference value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeObject(value.getActualInstance());
        }
    }

    public static class LocalMethodReferenceDeserializer extends StdDeserializer<LocalMethodReference> {
        public LocalMethodReferenceDeserializer() {
            this(LocalMethodReference.class);
        }

        public LocalMethodReferenceDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public LocalMethodReference deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode tree = jp.readValueAsTree();

            Object deserialized = null;
            Class<?> cls = JSON.getClassForElement(tree, LocalMethodReference.class);
            if (cls != null) {
                // When the OAS schema includes a discriminator, use the discriminator value to
                // discriminate the anyOf schemas.
                // Get the discriminator mapping value to get the class.
                deserialized = tree.traverse(jp.getCodec()).readValueAs(cls);
                LocalMethodReference ret = new LocalMethodReference();
                ret.setActualInstance(deserialized);
                return ret;
            }
            // deserialize LocalNativeFunctionReference
            try {
                deserialized = tree.traverse(jp.getCodec()).readValueAs(LocalNativeFunctionReference.class);
                LocalMethodReference ret = new LocalMethodReference();
                ret.setActualInstance(deserialized);
                return ret;
            } catch (Exception e) {
                // deserialization failed, continue, log to help debugging
                log.log(Level.FINER, "Input data does not match 'LocalMethodReference'", e);
            }

            // deserialize LocalNativeMethodReference
            try {
                deserialized = tree.traverse(jp.getCodec()).readValueAs(LocalNativeMethodReference.class);
                LocalMethodReference ret = new LocalMethodReference();
                ret.setActualInstance(deserialized);
                return ret;
            } catch (Exception e) {
                // deserialization failed, continue, log to help debugging
                log.log(Level.FINER, "Input data does not match 'LocalMethodReference'", e);
            }

            // deserialize LocalScryptoMethodReference
            try {
                deserialized = tree.traverse(jp.getCodec()).readValueAs(LocalScryptoMethodReference.class);
                LocalMethodReference ret = new LocalMethodReference();
                ret.setActualInstance(deserialized);
                return ret;
            } catch (Exception e) {
                // deserialization failed, continue, log to help debugging
                log.log(Level.FINER, "Input data does not match 'LocalMethodReference'", e);
            }

            throw new IOException(String.format("Failed deserialization for LocalMethodReference: no match found"));
        }

        /**
         * Handle deserialization of the 'null' value.
         */
        @Override
        public LocalMethodReference getNullValue(DeserializationContext ctxt) throws JsonMappingException {
            throw new JsonMappingException(ctxt.getParser(), "LocalMethodReference cannot be null");
        }
    }

    // store a list of schema names defined in anyOf
    public static final Map<String, Class<?>> schemas = new HashMap<String, Class<?>>();

    public LocalMethodReference() {
        super("anyOf", Boolean.FALSE);
    }

    public LocalMethodReference(LocalNativeFunctionReference o) {
        super("anyOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public LocalMethodReference(LocalNativeMethodReference o) {
        super("anyOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public LocalMethodReference(LocalScryptoMethodReference o) {
        super("anyOf", Boolean.FALSE);
        setActualInstance(o);
    }

    static {
        schemas.put("LocalNativeFunctionReference", LocalNativeFunctionReference.class);
        schemas.put("LocalNativeMethodReference", LocalNativeMethodReference.class);
        schemas.put("LocalScryptoMethodReference", LocalScryptoMethodReference.class);
        JSON.registerDescendants(LocalMethodReference.class, Collections.unmodifiableMap(schemas));
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("LocalNativeFunctionReference", LocalNativeFunctionReference.class);
        mappings.put("LocalNativeMethodReference", LocalNativeMethodReference.class);
        mappings.put("LocalScryptoMethodReference", LocalScryptoMethodReference.class);
        mappings.put("NativeFunction", LocalNativeFunctionReference.class);
        mappings.put("NativeMethod", LocalNativeMethodReference.class);
        mappings.put("ScryptoMethod", LocalScryptoMethodReference.class);
        mappings.put("LocalMethodReference", LocalMethodReference.class);
        JSON.registerDiscriminator(LocalMethodReference.class, "type", mappings);
    }

    @Override
    public Map<String, Class<?>> getSchemas() {
        return LocalMethodReference.schemas;
    }

    /**
     * Set the instance that matches the anyOf child schema, check
     * the instance parameter is valid against the anyOf child schemas:
     * LocalNativeFunctionReference, LocalNativeMethodReference, LocalScryptoMethodReference
     *
     * It could be an instance of the 'anyOf' schemas.
     * The anyOf child schemas may themselves be a composed schema (allOf, anyOf, anyOf).
     */
    @Override
    public void setActualInstance(Object instance) {
        if (JSON.isInstanceOf(LocalNativeFunctionReference.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(LocalNativeMethodReference.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(LocalScryptoMethodReference.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        throw new RuntimeException("Invalid instance type. Must be LocalNativeFunctionReference, LocalNativeMethodReference, LocalScryptoMethodReference");
    }

    /**
     * Get the actual instance, which can be the following:
     * LocalNativeFunctionReference, LocalNativeMethodReference, LocalScryptoMethodReference
     *
     * @return The actual instance (LocalNativeFunctionReference, LocalNativeMethodReference, LocalScryptoMethodReference)
     */
    @Override
    public Object getActualInstance() {
        return super.getActualInstance();
    }

    /**
     * Get the actual instance of `LocalNativeFunctionReference`. If the actual instance is not `LocalNativeFunctionReference`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `LocalNativeFunctionReference`
     * @throws ClassCastException if the instance is not `LocalNativeFunctionReference`
     */
    public LocalNativeFunctionReference getLocalNativeFunctionReference() throws ClassCastException {
        return (LocalNativeFunctionReference)super.getActualInstance();
    }

    /**
     * Get the actual instance of `LocalNativeMethodReference`. If the actual instance is not `LocalNativeMethodReference`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `LocalNativeMethodReference`
     * @throws ClassCastException if the instance is not `LocalNativeMethodReference`
     */
    public LocalNativeMethodReference getLocalNativeMethodReference() throws ClassCastException {
        return (LocalNativeMethodReference)super.getActualInstance();
    }

    /**
     * Get the actual instance of `LocalScryptoMethodReference`. If the actual instance is not `LocalScryptoMethodReference`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `LocalScryptoMethodReference`
     * @throws ClassCastException if the instance is not `LocalScryptoMethodReference`
     */
    public LocalScryptoMethodReference getLocalScryptoMethodReference() throws ClassCastException {
        return (LocalScryptoMethodReference)super.getActualInstance();
    }

}
