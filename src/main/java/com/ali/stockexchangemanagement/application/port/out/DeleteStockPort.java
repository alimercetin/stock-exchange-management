package com.ali.stockexchangemanagement.application.port.out;

import com.ali.stockexchangemanagement.application.domain.model.Stock;

public interface DeleteStockPort {

    void deleteStock(Stock stock);
}
