package com.ali.stockexchangemanagement.application.port.out;

import com.ali.stockexchangemanagement.application.domain.model.Stock;

import java.util.Optional;

public interface LoadStockPort {

    Optional<Stock> loadStock(Long id);
}
