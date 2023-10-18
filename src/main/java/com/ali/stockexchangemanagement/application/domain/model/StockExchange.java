package com.ali.stockexchangemanagement.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import static com.ali.stockexchangemanagement.common.Constants.REQUIRED_STOCK_COUNT_FOR_LIVENESS;

@Builder(toBuilder = true, setterPrefix = "with")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockExchange {


    private Long id;

    private String name;

    private String description;

    private boolean liveInMarket;

    @Builder.Default
    private Set<Stock> stocks = new HashSet<>();

    public boolean getLiveInMarket() {
        return stocks != null && stocks.size() >= REQUIRED_STOCK_COUNT_FOR_LIVENESS;
    }
}
