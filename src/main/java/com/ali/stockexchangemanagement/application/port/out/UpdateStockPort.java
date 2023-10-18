package com.ali.stockexchangemanagement.application.port.out;

import com.ali.stockexchangemanagement.application.domain.model.Stock;

public interface UpdateStockPort {

    void updateStock(Stock stock);

}
