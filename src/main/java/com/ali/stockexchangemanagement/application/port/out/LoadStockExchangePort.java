package com.ali.stockexchangemanagement.application.port.out;

import com.ali.stockexchangemanagement.application.domain.model.StockExchange;

import java.util.Optional;

public interface LoadStockExchangePort {

    Optional<StockExchange> loadStockExchange(String name);
}
