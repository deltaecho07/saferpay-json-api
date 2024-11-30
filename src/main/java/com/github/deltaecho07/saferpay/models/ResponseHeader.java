package com.github.deltaecho07.saferpay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ResponseHeader(
        @JsonProperty("SpecVersion")
        @NotNull String SpecVersion,
        @JsonProperty("RequestId")
        @NotNull String RequestId
) {
}
