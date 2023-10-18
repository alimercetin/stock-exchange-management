package com.ali.stockexchangemanagement.application.domain.service;

import com.ali.stockexchangemanagement.application.domain.model.Stock;
import com.ali.stockexchangemanagement.application.port.out.CreateStockPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.ali.stockexchangemanagement.common.StockTestData.defaultStock;
import static com.ali.stockexchangemanagement.common.StockTestData.savedStock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;

class CreateStockServiceTest {

    private final CreateStockPort createStockPort =
            Mockito.mock(CreateStockPort.class);

    private final CreateStockService createStockService =
            new CreateStockService(createStockPort);

    @Test
    void creationSucceeds() {
        Stock stock = defaultStock();
        Stock savedStock = savedStock();

        given(createStockPort.createStock(eq(stock)))
                .willReturn(savedStock);

        Stock response = createStockService.createStock(stock);

        assertThat(response).isEqualTo(savedStock);
    }

}
