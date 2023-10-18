package com.ali.stockexchangemanagement.adapter.in.web.mapper;

import com.ali.stockexchangemanagement.adapter.in.web.model.CreateStockRequest;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import org.junit.jupiter.api.Test;

import static com.ali.stockexchangemanagement.common.StockTestData.defaultCreateStockRequest;
import static org.assertj.core.api.Assertions.assertThat;

class StockRestMapperTest {

    @Test
    void testMappingFromCreateStockRequestToStock() {
        CreateStockRequest request = defaultCreateStockRequest();

        Stock response = StockRestMapper.toStock(request);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getDescription()).isEqualTo(request.getDescription());
        assertThat(response.getCurrentPrice()).isEqualTo(request.getCurrentPrice());
    }
}
