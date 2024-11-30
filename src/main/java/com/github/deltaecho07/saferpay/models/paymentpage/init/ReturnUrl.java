package com.github.deltaecho07.saferpay.models.paymentpage.init;

import jakarta.validation.constraints.NotNull;

public record ReturnUrl(@NotNull String Url) {
}
