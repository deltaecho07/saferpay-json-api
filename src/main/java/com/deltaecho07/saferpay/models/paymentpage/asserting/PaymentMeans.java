package com.deltaecho07.saferpay.models.paymentpage.asserting;

import jakarta.validation.constraints.NotNull;

public record PaymentMeans(
        @NotNull Brand Brand,
        @NotNull String DisplayText,
        Card Card
) {
}
