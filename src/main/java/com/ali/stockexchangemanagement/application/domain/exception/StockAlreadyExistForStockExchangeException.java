package com.ali.stockexchangemanagement.application.domain.exception;

public class StockAlreadyExistForStockExchangeException extends StockExchangeManagementException {

    public StockAlreadyExistForStockExchangeException(Object... details) {
        super(ErrorCode.ALREADY_EXISTING_STOCK, details);
    }
}
