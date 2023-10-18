package com.ali.stockexchangemanagement.application.domain.exception;

public enum ErrorCode {

    STOCK_EXCHANGE_NOT_FOUND("Stock exchange not found!"),
    CONSTRAINT_VALIDATION("Request is not valid!"),
    ALREADY_EXISTING_STOCK("Stock already exists for the Stock Exchange!"),
    NONEXISTING_STOCK_EXCHANGE("Stock exchange with requested name does not exist!"),
    NONEXISTING_STOCK("Stock with requested id does not exist!");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
