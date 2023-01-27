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

package com.radixdlt.cli;

import com.radixdlt.addressing.Addressing;
import com.radixdlt.crypto.ECDSASecp256k1PublicKey;
import com.radixdlt.crypto.ECKeyPair;
import com.radixdlt.networks.Network;
import com.radixdlt.utils.Bytes;
import com.radixdlt.utils.PrivateKeys;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.security.Security;
import java.util.HashSet;
import java.util.stream.IntStream;
import org.apache.commons.cli.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.json.JSONObject;

/** Generates the universe (genesis commit) for the Olympia Radix Engine */
public final class GenerateUniverses {
  private GenerateUniverses() {}

  public static void main(String[] args) throws Exception {
    Security.insertProviderAt(new BouncyCastleProvider(), 1);

    Options options = new Options();
    options.addOption("h", "help", false, "Show usage information (this message)");
    options.addOption("p", "public-keys", true, "Specify validator keys");
    options.addOption("v", "validator-count", true, "Specify number of validators to generate");

    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, args);
    if (!cmd.getArgList().isEmpty()) {
      System.err.println("Extra arguments: " + String.join(" ", cmd.getArgList()));
      usage(options);
      return;
    }

    if (cmd.hasOption('h')) {
      usage(options);
      return;
    }

    var validatorKeys = new HashSet<ECDSASecp256k1PublicKey>();
    if (cmd.getOptionValue("p") != null) {
      var hexKeys = cmd.getOptionValue("p").split(",");
      for (var hexKey : hexKeys) {
        validatorKeys.add(ECDSASecp256k1PublicKey.fromHex(hexKey));
      }
    }
    final int validatorsCount =
        cmd.getOptionValue("v") != null ? Integer.parseInt(cmd.getOptionValue("v")) : 0;
    var generatedValidatorKeys = PrivateKeys.numeric(6).limit(validatorsCount).toList();
    generatedValidatorKeys.stream().map(ECKeyPair::getPublicKey).forEach(validatorKeys::add);
    IntStream.range(0, generatedValidatorKeys.size())
        .forEach(
            i -> {
              System.out.format(
                  "export RADIXDLT_VALIDATOR_%s_PRIVKEY=%s%n",
                  i, Bytes.toBase64String(generatedValidatorKeys.get(i).getPrivateKey()));
              System.out.format(
                  "export RADIXDLT_VALIDATOR_%s_PUBKEY=%s%n",
                  i,
                  Addressing.ofNetwork(Network.LOCALNET)
                      .encodeNodeAddress(generatedValidatorKeys.get(i).getPublicKey()));
            });

    final var genesisTxnBuilder = new StringBuilder();
    for (var key : validatorKeys) {
      genesisTxnBuilder.append(Hex.toHexString(key.getCompressedBytes()));
    }

    final var genesisTxn = genesisTxnBuilder.toString();

    if (validatorsCount > 0) {
      System.out.format("export RADIXDLT_GENESIS_TXN=%s%n", genesisTxn);
    } else {
      try (var writer = new BufferedWriter(new FileWriter("genesis.json"))) {

        writer.write(new JSONObject().put("genesis", genesisTxn).toString());
      }
    }
  }

  private static void usage(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp(GenerateUniverses.class.getSimpleName(), options, true);
  }
}
