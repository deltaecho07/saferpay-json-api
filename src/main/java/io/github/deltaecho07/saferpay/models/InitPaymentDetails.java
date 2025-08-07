package io.github.deltaecho07.saferpay.models;

import io.github.deltaecho07.saferpay.models.paymentpage.init.ReturnUrl;
import jakarta.validation.constraints.NotNull;


public record InitPaymentDetails(
        @NotNull Amount amount,
        @NotNull ReturnUrl returnUrl,
        @NotNull String orderId,
        @NotNull String description
        ) {
}
