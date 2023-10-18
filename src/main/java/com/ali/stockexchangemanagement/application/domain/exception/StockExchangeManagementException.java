package com.ali.stockexchangemanagement.application.domain.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StockExchangeManagementException extends RuntimeException {
    private final LocalDateTime timestamp;
    private final ErrorCode errorCode;
    private final transient Object[] details;

    public StockExchangeManagementException(ErrorCode errorCode, Object... details) {
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }
}
