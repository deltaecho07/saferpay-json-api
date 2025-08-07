package io.github.deltaecho07.saferpay.models.paymentpage.asserting;

import io.github.deltaecho07.saferpay.models.RequestHeader;
import jakarta.validation.constraints.NotNull;

public record PaymentPageAssertReqDto(
        @NotNull RequestHeader RequestHeader,
        @NotNull String Token) {
}
