package com.ali.stockexchangemanagement.application.port.in;

public interface AddStockForStockExchangeUseCase {

    void addStock(String exchangeName, Long stockId);
}
