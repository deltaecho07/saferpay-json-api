package io.github.deltaecho07.saferpay.models.transaction.cancel;

import io.github.deltaecho07.saferpay.models.ResponseHeader;
import jakarta.validation.constraints.NotNull;

public record TransactionCancelRespDto(
        @NotNull ResponseHeader ResponseHeader,
        @NotNull String TransactionId,
        String OrderId,
        String Date
        ) {
}
