package io.github.deltaecho07.saferpay.models.transaction.capture;

import io.github.deltaecho07.saferpay.models.RequestHeader;
import jakarta.validation.constraints.NotNull;

public record TransactionCaptureReqDto(
        @NotNull RequestHeader RequestHeader,
        @NotNull TransactionReference TransactionReference
        ) {
}
