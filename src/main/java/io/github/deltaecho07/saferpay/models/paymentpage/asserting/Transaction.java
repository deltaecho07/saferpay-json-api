package io.github.deltaecho07.saferpay.models.paymentpage.asserting;

import io.github.deltaecho07.saferpay.models.paymentpage.init.AmountDto;
import jakarta.validation.constraints.NotNull;

public record Transaction(
        @NotNull TransactionType Type,
        @NotNull TransactionStatus Status,
        @NotNull String Id,
        String CaptureId,
        String Date,
        AmountDto Amount,
        String OrderId,
        String AcquirerName,
        String AcquirerReference,
        String SixTransactionReference,
        String ApprovalCode
        ) {
}
