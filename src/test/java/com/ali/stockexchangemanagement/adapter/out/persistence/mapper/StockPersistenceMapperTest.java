package com.ali.stockexchangemanagement.adapter.out.persistence.mapper;

import com.ali.stockexchangemanagement.adapter.out.persistence.entity.StockEntity;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import org.junit.jupiter.api.Test;

import static com.ali.stockexchangemanagement.common.StockTestData.defaultStock;
import static com.ali.stockexchangemanagement.common.StockTestData.defaultStockEntity;
import static org.assertj.core.api.Assertions.assertThat;

class StockPersistenceMapperTest {

    @Test
    void testMappingFromStockToStockEntity() {
        Stock stock = defaultStock();

        StockEntity response = StockPersistenceMapper.toStockEntity(stock);

        assertThat(response.getName()).isEqualTo(stock.getName());
        assertThat(response.getCurrentPrice()).isEqualTo(stock.getCurrentPrice());
        assertThat(response.getDescription()).isEqualTo(stock.getDescription());
        assertThat(response.getId()).isEqualTo(stock.getId());
        assertThat(response.getLastUpdate()).isEqualTo(stock.getLastUpdate());
    }

    @Test
    void testMappingFromStockEntityToStock() {
        StockEntity entity = defaultStockEntity();

        Stock response = StockPersistenceMapper.toStock(entity);

        assertThat(response.getName()).isEqualTo(entity.getName());
        assertThat(response.getCurrentPrice()).isEqualTo(entity.getCurrentPrice());
        assertThat(response.getDescription()).isEqualTo(entity.getDescription());
        assertThat(response.getId()).isEqualTo(entity.getId());
        assertThat(response.getLastUpdate()).isEqualTo(entity.getLastUpdate());
    }
}
