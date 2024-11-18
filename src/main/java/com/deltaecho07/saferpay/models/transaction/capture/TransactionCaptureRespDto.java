package com.deltaecho07.saferpay.models.transaction.capture;

import com.deltaecho07.saferpay.models.ResponseHeader;
import jakarta.validation.constraints.NotNull;

public record TransactionCaptureRespDto(
        @NotNull ResponseHeader ResponseHeader,
        String CaptureId,
        @NotNull CaptureStatus Status,
        String Date
        ) {
}
