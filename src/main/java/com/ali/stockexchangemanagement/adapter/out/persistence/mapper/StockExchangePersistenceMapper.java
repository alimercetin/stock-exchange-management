package com.ali.stockexchangemanagement.adapter.out.persistence.mapper;

import com.ali.stockexchangemanagement.adapter.out.persistence.entity.StockExchangeEntity;
import com.ali.stockexchangemanagement.application.domain.model.StockExchange;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class StockExchangePersistenceMapper {

    public StockExchangeEntity toStockExchangeEntity(StockExchange stockExchange) {
        return StockExchangeEntity.builder()
                .withName(stockExchange.getName())
                .withDescription(stockExchange.getDescription())
                .withId(stockExchange.getId())
                .withStocks(stockExchange.getStocks().stream().map(StockPersistenceMapper::toStockEntity).collect(Collectors.toSet()))
                .build();
    }

    public StockExchange toStockExchange(StockExchangeEntity stockExchangeEntity) {
        return StockExchange.builder()
                .withName(stockExchangeEntity.getName())
                .withDescription(stockExchangeEntity.getDescription())
                .withId(stockExchangeEntity.getId())
                .withStocks(stockExchangeEntity.getStocks().stream().map(StockPersistenceMapper::toStock).collect(Collectors.toSet()))
                .build();
    }
}
