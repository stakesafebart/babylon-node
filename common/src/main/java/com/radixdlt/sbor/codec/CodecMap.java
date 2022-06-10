/* Copyright 2021 Radix Publishing Ltd incorporated in Jersey (Channel Islands).
 *
 * Licensed under the Radix License, Version 1.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at:
 *
 * radixfoundation.org/licenses/LICENSE-v1
 *
 * The Licensor hereby grants permission for the Canonical version of the Work to be
 * published, distributed and used under or by reference to the Licensor’s trademark
 * Radix ® and use of any unregistered trade names, logos or get-up.
 *
 * The Licensor provides the Work (and each Contributor provides its Contributions) on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
 * including, without limitation, any warranties or conditions of TITLE, NON-INFRINGEMENT,
 * MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Whilst the Work is capable of being deployed, used and adopted (instantiated) to create
 * a distributed ledger it is your responsibility to test and validate the code, together
 * with all logic and performance of that code under all foreseeable scenarios.
 *
 * The Licensor does not make or purport to make and hereby excludes liability for all
 * and any representation, warranty or undertaking in any form whatsoever, whether express
 * or implied, to any entity or person, including any representation, warranty or
 * undertaking, as to the functionality security use, value or other characteristics of
 * any distributed ledger nor in respect the functioning or value of any tokens which may
 * be created stored or transferred using the Work. The Licensor does not warrant that the
 * Work or any use of the Work complies with any law or regulation in any territory where
 * it may be implemented or used or that it will be appropriate for any specific purpose.
 *
 * Neither the licensor nor any current or former employees, officers, directors, partners,
 * trustees, representatives, agents, advisors, contractors, or volunteers of the Licensor
 * shall be liable for any direct or indirect, special, incidental, consequential or other
 * losses of any kind, in tort, contract or otherwise (including but not limited to loss
 * of revenue, income or profits, or loss of use or data, or loss of reputation, or loss
 * of any economic or other opportunity of whatsoever nature or howsoever arising), arising
 * out of or in connection with (without limitation of any use, misuse, of any ledger system
 * or use made or its functionality or any performance or operation of any code or protocol
 * caused by bugs or programming or logic errors or otherwise);
 *
 * A. any offer, purchase, holding, use, sale, exchange or transmission of any
 * cryptographic keys, tokens or assets created, exchanged, stored or arising from any
 * interaction with the Work;
 *
 * B. any failure in a transmission or loss of any token or assets keys or other digital
 * artefacts due to errors in transmission;
 *
 * C. bugs, hacks, logic errors or faults in the Work or any communication;
 *
 * D. system software or apparatus including but not limited to losses caused by errors
 * in holding or transmitting tokens by any third-party;
 *
 * E. breaches or failure of security including hacker attacks, loss or disclosure of
 * password, loss of private key, unauthorised use or misuse of such passwords or keys;
 *
 * F. any losses including loss of anticipated savings or other benefits resulting from
 * use of the Work or any changes to the Work (however implemented).
 *
 * You are solely responsible for; testing, validating and evaluation of all operation
 * logic, functionality, security and appropriateness of using the Work for any commercial
 * or non-commercial purpose and for any reproduction or redistribution by You of the
 * Work. You assume all risks associated with Your use of the Work and the exercise of
 * permissions under this License.
 */

package com.radixdlt.sbor.codec;

import com.google.common.reflect.TypeToken;
import com.radixdlt.lang.EitherTypeCodec;
import com.radixdlt.lang.OptionTypeCodec;
import com.radixdlt.lang.Unit;
import com.radixdlt.sbor.codec.constants.TypeId;
import com.radixdlt.sbor.exceptions.SborCodecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The CodecMap registers default strategies to encode/decode a type.
 *
 * <p>You can register codecsMap for:
 *
 * <ul>
 *   <li>A class object - this captures types without their generic parameters
 *   <li>A concrete TypeToken - this is specific to all the given generic parameters
 * </ul>
 *
 * <p>If multiple codecsMap are registered against the same object/TypeToken, the latest to be
 * registered is used.
 *
 * <p>You can also register a codec creator, which allows automatic creation of codecsMap for
 * explicit type parameters of a given class. This works well with types such as Option&lt;T&rt;,
 * where you may wish to decode into an Option&lt;String&rt; without registering a codec for
 * Option&lt;String&rt; explicitly. The generated codecsMap are cached against their explicit
 * TypeToken.
 *
 * <p>Finally, you can also register a class object codec and codec creators for a sealed class and
 * all its subclasses in one go - this is to provide easy support for ADTs (abstract data types).
 */
@SuppressWarnings({
  "rawtypes",
  "unchecked",
  "UnusedReturnValue",
  "unused"
}) // This class is required to play fast and loose with generics
public final class CodecMap {
  /**
   * Codecs can be registered on the static CodecMap.DEFAULT which is used by SborCoder.DEFAULT. It
   * is recommended to do this in the static constructor of a class being encoded/decoded. It is
   * safe to register twice - the latest registration will apply.
   */
  private static final CodecMap DEFAULT = new CodecMap().registerCoreCodecs();

  public static final CodecResolver DEFAULT_RESOLVER = DEFAULT.resolver;

  public static void withDefault(Consumer<CodecMap> registerCodec) {
    registerCodec.accept(DEFAULT);
  }

  public final CodecResolver resolver = new CodecResolver();

  private final Map<Class, Codec> ClassCodecCache = new HashMap<>();
  private final Map<TypeToken, Codec> typedCodecCache = new HashMap<>();

  private final Map<Class, ClassCodecCreator> classCodecCreators = new HashMap<>();
  private final Map<Class, TypedCodecCreator> typedCodecCreators = new HashMap<>();

  private TypeId sborTypeIdForArrayType = TypeId.TYPE_ARRAY;

  public CodecMap registerCoreCodecs() {
    storeCreated(Unit.class, new CoreTypeCodec.UnitCodec());
    storeCreated(String.class, new CoreTypeCodec.StringCodec());

    storeCreated(Boolean.class, new CoreTypeCodec.BooleanCodec());
    storeCreated(boolean.class, new CoreTypeCodec.BooleanCodec());

    storeCreated(Byte.class, new CoreTypeCodec.ByteCodec());
    storeCreated(byte.class, new CoreTypeCodec.ByteCodec());

    storeCreated(Short.class, new CoreTypeCodec.ShortCodec());
    storeCreated(short.class, new CoreTypeCodec.ShortCodec());

    storeCreated(Integer.class, new CoreTypeCodec.IntegerCodec());
    storeCreated(int.class, new CoreTypeCodec.IntegerCodec());

    storeCreated(Long.class, new CoreTypeCodec.LongCodec());
    storeCreated(long.class, new CoreTypeCodec.LongCodec());

    storeCreated(byte[].class, new CoreTypeCodec.ByteArrayCodec());
    storeCreated(short[].class, new CoreTypeCodec.ShortArrayCodec());
    storeCreated(int[].class, new CoreTypeCodec.IntegerArrayCodec());
    storeCreated(long[].class, new CoreTypeCodec.LongArrayCodec());

    OptionTypeCodec.registerWith(this);
    EitherTypeCodec.registerWith(this);

    sborTypeIdForArrayType = TypeId.TYPE_ARRAY; // Used for auto-array codec creation
    CollectionCodec.registerListToMapTo(this, TypeId.TYPE_VEC);
    CollectionCodec.registerArrayListToMapTo(this, TypeId.TYPE_VEC);
    CollectionCodec.registerSetToMapTo(this, TypeId.TYPE_HASH_SET);
    CollectionCodec.registerHashSetToMapTo(this, TypeId.TYPE_HASH_SET);
    CollectionCodec.registerTreeSetToMapTo(this, TypeId.TYPE_TREE_SET);

    return this;
  }

  public <T> CodecMap register(Class<T> clazz, ClassCodecCreator codecCreator) {
    synchronized (classCodecCreators) {
      classCodecCreators.put(clazz, codecCreator);
    }
    return this;
  }

  public <T> CodecMap registerForSealedClassAndSubclasses(
      Class<T> clazz, ClassCodecCreator codecCreator) {
    if (!clazz.isSealed()) {
      throw new SborCodecException(
          String.format(
              "The class object %s is not sealed, so cannot be passed into "
                  + "registerForSealedClassAndSubclasses.",
              clazz));
    }

    classCodecCreators.put(clazz, codecCreator);
    var implementers = clazz.getPermittedSubclasses();
    Arrays.stream(implementers)
        .forEach(
            subClass -> {
              synchronized (classCodecCreators) {
                classCodecCreators.put(subClass, codecCreator);
              }
            });
    return this;
  }

  public <T> CodecMap registerForGeneric(Class<T> clazz, TypedCodecCreator codecCreator) {
    synchronized (typedCodecCreators) {
      typedCodecCreators.put(clazz, codecCreator);
    }
    return this;
  }

  public <T> CodecMap registerForGenericSealedClassAndSubclasses(
      Class<T> clazz, TypedCodecCreator codecCreator) {
    if (!clazz.isSealed()) {
      throw new SborCodecException(
          String.format(
              "The class object %s is not sealed, so cannot be passed into "
                  + "registerForGenericSealedClassAndSubclasses.",
              clazz));
    }

    synchronized (typedCodecCreators) {
      typedCodecCreators.put(clazz, codecCreator);
    }

    var implementers = clazz.getPermittedSubclasses();
    Arrays.stream(implementers)
        .forEach(
            subClass -> {
              synchronized (typedCodecCreators) {
                typedCodecCreators.put(subClass, codecCreator);
              }
            });
    return this;
  }

  private <T> CodecMap storeCreated(Class<T> clazz, Codec<T> codec) {
    synchronized (ClassCodecCache) {
      ClassCodecCache.put(clazz, codec);
      typedCodecCache.put(TypeToken.of(clazz), codec);
    }
    return this;
  }

  private <T> CodecMap storeCreated(TypeToken<T> type, Codec<T> codec) {
    synchronized (typedCodecCache) {
      typedCodecCache.put(type, codec);
    }
    return this;
  }

  @FunctionalInterface
  public interface ClassCodecCreator<T> {
    Codec<T> create(CodecResolver codecs);
  }

  @FunctionalInterface
  public interface TypedCodecCreator<T> {
    Codec<T> create(CodecResolver codecs, TypeToken<? extends T> type);
  }

  public class CodecResolver {
    /*
     * NB - NOT a JavaDoc on purpose, just a comment.
     * The parameter type below should really be TypeToken<? extends T> or TypeToken<T>,
     * but we explicitly don't put this. That would allow TypeToken<> to be valid for
     * callers, but that causes a Compiler Null Pointer Exception, detailed below.
     * It is likely related to https://bugs.openjdk.org/browse/JDK-8262095
     * The error message is copied out below to assist anyone who hits this error
     * and greps the codebase for it looking for help:
     * "An exception has occurred in the compiler (17).
     * Please file a bug against the Java compiler via the Java bug reporting page
     * (http://bugreport.java.com) after checking the Bug Database (http://bugs.java.com) for
     * duplicates. Include your program, the following diagnostic, and the parameters passed to the
     * Java compiler in your report. Thank you."
     * Sometimes the exception appears to be accompanied by
     * "Cannot invoke getThrownTypes because tree.meth.type is null"
     */
    public <T> Codec<T> of(TypeToken<?> type) {
      // First - let's try to find a pre-registered codec for this explicit type literal
      var codec = typedCodecCache.get(type);
      if (codec != null) {
        return codec;
      }

      // Next - if it's an array, we need special handling...
      if (type.isArray()) {
        var newCodec = (Codec) createArrayCodec(type);
        storeCreated(type, newCodec); // Cache the codec for future use
        return newCodec;
      }

      // Failing that - let's see if we can create one with a codec creator
      var rawType = type.getRawType();
      var codecCreator = typedCodecCreators.get(rawType);
      if (codecCreator != null) {
        var newCodec = codecCreator.create(this, type);
        storeCreated(type, newCodec); // Cache the codec for future use
        return newCodec;
      }

      throw new SborCodecException(
          String.format(
              "The type token %s itself has no SBOR codec, and its raw type class %s has no codec"
                  + " creator registered.",
              type, rawType));
    }

    public <T> Codec<T> of(Class<T> clazz) {
      var codec = ClassCodecCache.get(clazz);

      if (codec != null) {
        return codec;
      }

      if (clazz.isArray()) {
        var newCodec = (Codec) createArrayCodec(clazz);
        storeCreated(clazz, newCodec);
        return newCodec;
      }

      var classCodecCreator = classCodecCreators.get(clazz);
      if (classCodecCreator != null) {
        var newCodec = classCodecCreator.create(this);
        storeCreated(clazz, newCodec);
        return newCodec;
      }

      // We are in a fatal failure case here - so let's try to be helpful
      var typedCodecCreator = typedCodecCreators.get(clazz);
      if (typedCodecCreator != null) {
        throw new SborCodecException(
            String.format(
                "The class %s has no registered class codec creator, BUT it does have a registered"
                    + " generic codec creator. You should pass an explicit generic type, new"
                    + " TypeToken<X<Y,Z>>() {} instead.",
                clazz));
      }

      throw new SborCodecException(
          String.format(
              "The class %s has no registered class or generic typed codec creator.", clazz));
    }

    // NB - Arrays have to be handled separately because they're special types in Java
    private <T> Codec<T> createArrayCodec(TypeToken<T> arrayType) {
      var componentType = arrayType.getComponentType();
      assert componentType != null; // Because we're being passed an array type
      var componentClass = componentType.getRawType();
      var componentCodec = of(componentType);

      return CollectionCodec.forArray(
          (Class) componentClass, (Codec) componentCodec, sborTypeIdForArrayType);
    }

    // NB - Arrays have to be handled separately because they're special types in Java
    private <T> Codec<T> createArrayCodec(Class<T> arrayClass) {
      var componentClass = arrayClass.getComponentType();
      var componentType = TypeToken.of(componentClass);
      var componentCodec = of(componentType);

      return CollectionCodec.forArray(
          (Class) componentClass, (Codec) componentCodec, sborTypeIdForArrayType);
    }
  }
}
