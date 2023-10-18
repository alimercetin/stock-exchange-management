package com.ali.stockexchangemanagement.application.port.in;

import java.math.BigDecimal;

public interface UpdateStockPriceUseCase {

    void updateStockPrice(Long id, BigDecimal price);
}
