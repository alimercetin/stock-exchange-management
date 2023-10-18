package com.ali.stockexchangemanagement.application.domain.exception;

public class InvalidStockExchangeNameException extends StockExchangeManagementException {

    public InvalidStockExchangeNameException(Object... details) {
        super(ErrorCode.NONEXISTING_STOCK_EXCHANGE, details);
    }
}
