package com.github.deltaecho07.saferpay.models.paymentpage.asserting;

import com.github.deltaecho07.saferpay.models.ResponseHeader;
import jakarta.validation.constraints.NotNull;

public record PaymentPageAssertRespDto(
        @NotNull ResponseHeader ResponseHeader,
        @NotNull Transaction Transaction,
        @NotNull PaymentMeans PaymentMeans,
        @NotNull Liability Liability
        ) {
}
