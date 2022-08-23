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

package com.radixdlt.api.core.routes.transaction;

import com.google.inject.Inject;
import com.radixdlt.api.core.CoreApiCommon;
import com.radixdlt.api.core.CoreJsonRpcHandler;
import com.radixdlt.api.core.exceptions.CoreApiException;
import com.radixdlt.api.core.generated.models.FeeSummary;
import com.radixdlt.api.core.generated.models.PreviewError;
import com.radixdlt.api.core.generated.models.TransactionPreviewRequest;
import com.radixdlt.api.core.generated.models.TransactionPreviewResponse;
import com.radixdlt.api.core.generated.models.TransactionPreviewResponseLogs;
import com.radixdlt.api.core.generated.models.TransactionStatusFailed;
import com.radixdlt.api.core.generated.models.TransactionStatusRejected;
import com.radixdlt.api.core.generated.models.TransactionStatusSucceeded;
import com.radixdlt.rev2.ComponentAddress;
import com.radixdlt.rev2.PackageAddress;
import com.radixdlt.rev2.ResourceAddress;
import com.radixdlt.rev2.TransactionStatus;
import com.radixdlt.statecomputer.RustStateComputer;
import com.radixdlt.statecomputer.preview.PreviewFlags;
import com.radixdlt.statecomputer.preview.PreviewRequest;
import com.radixdlt.utils.UInt32;
import com.radixdlt.utils.UInt64;
import java.util.Objects;
import org.bouncycastle.util.encoders.Hex;

public final class TransactionPreviewHandler
    extends CoreJsonRpcHandler<TransactionPreviewRequest, TransactionPreviewResponse> {
  private final CoreApiCommon coreApiCommon;
  private final RustStateComputer stateComputer;

  @Inject
  TransactionPreviewHandler(CoreApiCommon coreApiCommon, RustStateComputer stateComputer) {
    super(TransactionPreviewRequest.class);
    this.coreApiCommon = Objects.requireNonNull(coreApiCommon);
    this.stateComputer = Objects.requireNonNull(stateComputer);
  }

  @Override
  public TransactionPreviewResponse handleRequest(TransactionPreviewRequest request)
      throws CoreApiException {
    coreApiCommon.verifyNetwork(request.getNetworkIdentifier());

    final var previewRequest =
        new PreviewRequest(
            Hex.decode(request.getManifest()),
            UInt32.fromNonNegativeInt(request.getCostUnitLimit()),
            UInt32.fromNonNegativeInt(request.getTipPercentage()),
            UInt64.fromNonNegativeLong(request.getNonce()),
            request.getSignerPublicKeys().stream().map(Hex::decode).toList(),
            new PreviewFlags(request.getFlags().getUnlimitedLoan()));

    final var result = this.stateComputer.preview(previewRequest);

    if (result.isSuccess()) {
      final var previewResult = result.unwrap();
      final var fee = previewResult.transactionFee();
      final var apiLogs =
          previewResult.logs().stream()
              .map(
                  log ->
                      new TransactionPreviewResponseLogs()
                          .level(log.first().toString())
                          .message(log.last()))
              .toList();

      final var apiStatus =
          switch (previewResult.transactionStatus()) {
            case TransactionStatus.Rejected rejected -> new TransactionStatusRejected()
                .type(TransactionStatusRejected.class.getSimpleName());
            case TransactionStatus.Succeeded succeeded -> new TransactionStatusSucceeded()
                .output(succeeded.output().stream().map(Hex::toHexString).toList())
                .type(TransactionStatusSucceeded.class.getSimpleName());
            case TransactionStatus.Failed failed -> new TransactionStatusFailed()
                .message(failed.message())
                .type(TransactionStatusFailed.class.getSimpleName());
          };

      return new TransactionPreviewResponse()
          .transactionStatus(apiStatus)
          .transactionFee(
              new FeeSummary()
                  .loanFullyRepaid(fee.loanFullyRepaid())
                  .costUnitLimit(fee.costUnitLimit().toString())
                  .costUnitsConsumed(fee.costUnitsConsumed().toString())
                  .costUnitPrice(fee.costUnitPrice().toString())
                  .tipPercentage(fee.tipPercentage().toString())
                  .xrdBurned(fee.burned().toString())
                  .xrdTipped(fee.tipped().toString()))
          .logs(apiLogs)
          .newPackageAddresses(
              previewResult.newPackageAddresses().stream()
                  .map(PackageAddress::toHexString)
                  .toList())
          .newComponentAddresses(
              previewResult.newComponentAddresses().stream()
                  .map(ComponentAddress::toHexString)
                  .toList())
          .newResourceAddresses(
              previewResult.newResourceAddresses().stream()
                  .map(ResourceAddress::toHexString)
                  .toList());
    } else {
      throw CoreApiException.badRequest(
          new PreviewError()
              .message(result.unwrapError().message())
              .type(PreviewError.class.getSimpleName()));
    }
  }
}