package com.github.deltaecho07.saferpay.models.paymentpage.init;

import com.github.deltaecho07.saferpay.models.ResponseHeader;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InitializePaymentPageRespDto(
        @JsonProperty("ResponseHeader")
        @NotNull ResponseHeader ResponseHeader,
        @JsonProperty("TerminalId")
        @NotNull String Token,
        @JsonProperty("Expiration")
        @NotNull String Expiration,
        @JsonProperty("RedirectUrl")
        @NotNull String RedirectUrl
) {
}
