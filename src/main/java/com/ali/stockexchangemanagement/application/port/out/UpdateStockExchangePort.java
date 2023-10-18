package com.ali.stockexchangemanagement.application.port.out;

import com.ali.stockexchangemanagement.application.domain.model.StockExchange;

public interface UpdateStockExchangePort {

    void updateStockExchange(StockExchange stockExchange);
}
