package com.github.deltaecho07.saferpay.models.paymentpage.asserting;

import jakarta.validation.constraints.NotNull;

public record ThreeDs(
        @NotNull Boolean Authenticated,
        @NotNull String Xid
) {
}
