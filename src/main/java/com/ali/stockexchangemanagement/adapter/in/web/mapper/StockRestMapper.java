package com.ali.stockexchangemanagement.adapter.in.web.mapper;

import com.ali.stockexchangemanagement.adapter.in.web.model.CreateStockRequest;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StockRestMapper {

    public Stock toStock(CreateStockRequest request) {
        return Stock.builder()
                .withName(request.getName())
                .withCurrentPrice(request.getCurrentPrice())
                .withDescription(request.getDescription())
                .build();
    }
}
