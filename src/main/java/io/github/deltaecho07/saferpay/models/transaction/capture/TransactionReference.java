package io.github.deltaecho07.saferpay.models.transaction.capture;

import jakarta.validation.constraints.NotNull;

public record TransactionReference(@NotNull String TransactionId) {
}
