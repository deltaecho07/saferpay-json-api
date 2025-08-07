package io.github.deltaecho07.saferpay.models.paymentpage.asserting;

import jakarta.validation.constraints.NotNull;

public record Liability(
        @NotNull Boolean LiabilityShift,
        @NotNull LiableEntity LiableEntity,
        @NotNull ThreeDs ThreeDs
) {
}
