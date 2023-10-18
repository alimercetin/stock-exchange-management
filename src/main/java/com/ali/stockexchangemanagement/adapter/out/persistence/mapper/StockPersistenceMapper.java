package com.ali.stockexchangemanagement.adapter.out.persistence.mapper;

import com.ali.stockexchangemanagement.adapter.out.persistence.entity.StockEntity;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StockPersistenceMapper {

    public StockEntity toStockEntity(Stock stock) {
        return StockEntity.builder()
                .withName(stock.getName())
                .withCurrentPrice(stock.getCurrentPrice())
                .withDescription(stock.getDescription())
                .withId(stock.getId())
                .withLastUpdate(stock.getLastUpdate())
                .build();
    }

    public Stock toStock(StockEntity stockEntity) {
        return Stock.builder()
                .withName(stockEntity.getName())
                .withLastUpdate(stockEntity.getLastUpdate())
                .withCurrentPrice(stockEntity.getCurrentPrice())
                .withDescription(stockEntity.getDescription())
                .withId(stockEntity.getId())
                .build();
    }
}
