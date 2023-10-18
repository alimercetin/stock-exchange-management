package com.ali.stockexchangemanagement.application.port.in;

import com.ali.stockexchangemanagement.application.domain.model.Stock;

public interface CreateStockUseCase {

    Stock createStock(Stock stock);
}
