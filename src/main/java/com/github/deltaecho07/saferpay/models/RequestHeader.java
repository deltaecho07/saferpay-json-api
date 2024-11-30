package com.github.deltaecho07.saferpay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record RequestHeader(
        @JsonProperty("SpecVersion")
        @NotNull String SpecVersion,
        @JsonProperty("CustomerId")
        @NotNull String CustomerId,
        @JsonProperty("RequestId")
        @NotNull String RequestId,
        @JsonProperty("RetryIndicator")
        @NotNull Integer RetryIndicator) {
}
