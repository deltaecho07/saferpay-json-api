package com.deltaecho07.saferpay.models.paymentpage.asserting;

import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

public record Card(
        @NotNull String MaskedNumber,
        @NotNull BigInteger ExpYear,
        @NotNull BigInteger ExpMonth,
        String HolderName,
        String CountryCode
) {
}
