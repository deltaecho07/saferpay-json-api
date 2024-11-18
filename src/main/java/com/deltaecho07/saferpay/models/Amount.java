package com.deltaecho07.saferpay.models;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Currency;

public record Amount(
        @NotNull BigDecimal value,
        @NotNull Currency currency) {
}
