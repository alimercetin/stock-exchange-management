package com.ali.stockexchangemanagement.adapter.out.persistence.mapper;

import com.ali.stockexchangemanagement.adapter.out.persistence.entity.StockExchangeEntity;
import com.ali.stockexchangemanagement.application.domain.model.StockExchange;
import org.junit.jupiter.api.Test;

import static com.ali.stockexchangemanagement.common.StockExchangeTestData.defaultStockExchange;
import static com.ali.stockexchangemanagement.common.StockExchangeTestData.defaultStockExchangeEntity;
import static org.assertj.core.api.Assertions.assertThat;

class StockExchangePersistenceMapperTest {

    @Test
    void testMappingFromStockExchangeToStockExchangeEntity() {
        StockExchange stockExchange = defaultStockExchange();

        StockExchangeEntity response = StockExchangePersistenceMapper.toStockExchangeEntity(stockExchange);

        assertThat(response.getName()).isEqualTo(stockExchange.getName());
        assertThat(response.getDescription()).isEqualTo(stockExchange.getDescription());
        assertThat(response.getId()).isEqualTo(stockExchange.getId());
    }

    @Test
    void testMappingFromStockExchangeEntityToStockExchange() {
        StockExchangeEntity entity = defaultStockExchangeEntity();

        StockExchange response = StockExchangePersistenceMapper.toStockExchange(entity);

        assertThat(response.getName()).isEqualTo(entity.getName());
        assertThat(response.getDescription()).isEqualTo(entity.getDescription());
        assertThat(response.getId()).isEqualTo(entity.getId());
    }
}
