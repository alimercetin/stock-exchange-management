package com.ali.stockexchangemanagement.adapter.in.web;

import com.ali.stockexchangemanagement.adapter.in.web.model.ErrorResponse;
import com.ali.stockexchangemanagement.application.domain.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static com.ali.stockexchangemanagement.application.domain.exception.ErrorCode.CONSTRAINT_VALIDATION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(StockExchangeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStockExchangeNotFoundException(StockExchangeNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND).body(mapToErrorResponse(ex));
    }

    @ExceptionHandler(InvalidStockExchangeNameException.class)
    public ResponseEntity<ErrorResponse> handleInvalidStockExchangeNameException(InvalidStockExchangeNameException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(mapToErrorResponse(ex));
    }

    @ExceptionHandler(InvalidStockNameException.class)
    public ResponseEntity<ErrorResponse> handleStockDoesNotExistException(InvalidStockNameException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(mapToErrorResponse(ex));
    }

    @ExceptionHandler(StockAlreadyExistForStockExchangeException.class)
    public ResponseEntity<ErrorResponse> handleStockAlreadyExistForStockExchangeException(StockAlreadyExistForStockExchangeException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(mapToErrorResponse(ex));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(mapToErrorResponse(CONSTRAINT_VALIDATION, new String[]{ex.getMessage()}));
    }

    private ErrorResponse mapToErrorResponse(StockExchangeManagementException ex) {
        return mapToErrorResponse(ex.getErrorCode(), ex.getDetails(), ex.getTimestamp());
    }

    private ErrorResponse mapToErrorResponse(ErrorCode code, Object details) {
        return mapToErrorResponse(code, details, LocalDateTime.now());
    }

    private ErrorResponse mapToErrorResponse(ErrorCode code, Object details, LocalDateTime timestamp) {
        return ErrorResponse.builder()
                .timestamp(timestamp)
                .code(code.name())
                .message(code.getMessage())
                .details(details)
                .build();
    }
}
