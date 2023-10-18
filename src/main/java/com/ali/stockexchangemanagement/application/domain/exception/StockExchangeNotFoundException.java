package com.ali.stockexchangemanagement.application.domain.exception;

public class StockExchangeNotFoundException extends StockExchangeManagementException {

    public StockExchangeNotFoundException(Object... details) {
        super(ErrorCode.STOCK_EXCHANGE_NOT_FOUND, details);
    }
}
