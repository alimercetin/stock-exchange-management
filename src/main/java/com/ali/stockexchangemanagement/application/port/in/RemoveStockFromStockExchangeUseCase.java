package com.ali.stockexchangemanagement.application.port.in;

public interface RemoveStockFromStockExchangeUseCase {

    void removeStock(String exchangeName, Long stockId);
}
