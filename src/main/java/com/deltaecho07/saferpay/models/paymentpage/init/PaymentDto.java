package com.deltaecho07.saferpay.models.paymentpage.init;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record PaymentDto(
        @JsonProperty("Amount")
        @NotNull AmountDto Amount,
        @JsonProperty("OrderId")
        String OrderId,
        @JsonProperty("Description")
        @NotNull String Description
) {
}
