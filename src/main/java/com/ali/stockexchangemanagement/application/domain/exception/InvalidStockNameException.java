package com.ali.stockexchangemanagement.application.domain.exception;

public class InvalidStockNameException extends StockExchangeManagementException {

    public InvalidStockNameException(Object... details) {
        super(ErrorCode.NONEXISTING_STOCK, details);
    }
}
