package com.deltaecho07.saferpay.models.paymentpage.init;

import com.deltaecho07.saferpay.models.RequestHeader;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record InitializePaymentPageReqDto(
        @JsonProperty("RequestHeader")
        @NotNull RequestHeader RequestHeader,
        @JsonProperty("TerminalId")
        @NotNull String TerminalId,
        @JsonProperty("Payment")
        @NotNull PaymentDto Payment,
        @JsonProperty("ReturnUrl")
        @NotNull ReturnUrl ReturnUrl
) {
}
