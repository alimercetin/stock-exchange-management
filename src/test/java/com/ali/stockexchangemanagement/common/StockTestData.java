package com.ali.stockexchangemanagement.common;

import com.ali.stockexchangemanagement.adapter.in.web.model.CreateStockRequest;
import com.ali.stockexchangemanagement.adapter.out.persistence.entity.StockEntity;
import com.ali.stockexchangemanagement.application.domain.model.Stock;

import java.math.BigDecimal;
import java.time.Instant;

public class StockTestData {

    public static Stock defaultStock() {
        return Stock.builder()
                .withId(null)
                .withName("META")
                .withDescription("Meta Platforms")
                .withCurrentPrice(BigDecimal.valueOf(322L))
                .withLastUpdate(null)
                .build();
    }

    public static Stock savedStock() {
        return Stock.builder()
                .withId(1L)
                .withName("META")
                .withDescription("Meta Platforms")
                .withCurrentPrice(BigDecimal.valueOf(322L))
                .withLastUpdate(Instant.now())
                .build();
    }

    public static CreateStockRequest defaultCreateStockRequest() {
        return CreateStockRequest.builder()
                .withName("NAME")
                .withDescription("description")
                .withCurrentPrice(BigDecimal.valueOf(11))
                .build();
    }

    public static StockEntity defaultStockEntity() {
        return StockEntity.builder()
                .withId(null)
                .withName("META")
                .withDescription("Meta Platforms")
                .withCurrentPrice(BigDecimal.valueOf(322L))
                .withLastUpdate(null)
                .build();
    }
}
