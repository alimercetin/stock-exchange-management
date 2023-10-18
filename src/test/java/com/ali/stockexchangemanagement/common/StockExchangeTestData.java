package com.ali.stockexchangemanagement.common;

import com.ali.stockexchangemanagement.adapter.out.persistence.entity.StockExchangeEntity;
import com.ali.stockexchangemanagement.application.domain.model.StockExchange;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ali.stockexchangemanagement.common.StockTestData.savedStock;

public class StockExchangeTestData {

    public static StockExchange defaultStockExchange() {
        return StockExchange.builder()
                .withId(null)
                .withName("XAMS")
                .withDescription("Amsterdam")
                .build();
    }

    public static StockExchange savedStockExchange() {
        return StockExchange.builder()
                .withId(1L)
                .withName("XAMS")
                .withDescription("Amsterdam")
                .withStocks(Stream.of(savedStock()).collect(Collectors.toCollection(HashSet::new)))
                .build();
    }

    public static StockExchangeEntity defaultStockExchangeEntity() {
        return StockExchangeEntity.builder()
                .withId(null)
                .withName("XAMS")
                .withDescription("Amsterdam")
                .build();
    }
}
