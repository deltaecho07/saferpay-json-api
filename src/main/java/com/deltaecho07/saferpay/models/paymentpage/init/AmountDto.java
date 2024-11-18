package com.deltaecho07.saferpay.models.paymentpage.init;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record AmountDto(
        @JsonProperty("Value")
        @NotNull String Value,
        @JsonProperty("CurrencyCode")
        @NotNull String CurrencyCode) {
}
