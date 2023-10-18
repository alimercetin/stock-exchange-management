package com.ali.stockexchangemanagement.application.port.in;

import com.ali.stockexchangemanagement.application.domain.model.StockExchange;

public interface GetStockExchangeUseCase {

    StockExchange getStockExchange(String exchangeName);
}
