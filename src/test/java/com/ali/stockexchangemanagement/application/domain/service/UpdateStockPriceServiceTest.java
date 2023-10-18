package com.ali.stockexchangemanagement.application.domain.service;

import com.ali.stockexchangemanagement.application.domain.exception.InvalidStockNameException;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import com.ali.stockexchangemanagement.application.port.out.LoadStockPort;
import com.ali.stockexchangemanagement.application.port.out.UpdateStockPort;
import com.ali.stockexchangemanagement.common.StockTestData;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

class UpdateStockPriceServiceTest {

    private final LoadStockPort loadStockPort =
            Mockito.mock(LoadStockPort.class);

    private final UpdateStockPort updateStockPort =
            Mockito.mock(UpdateStockPort.class);

    private final UpdateStockPriceService updateStockPriceService =
            new UpdateStockPriceService(loadStockPort, updateStockPort);

    @Test
    void updateSucceeds() {
        Stock stock = StockTestData.savedStock();
        BigDecimal updatedPrice = BigDecimal.valueOf(123L);
        ArgumentCaptor<Stock> stockCaptor = ArgumentCaptor.forClass(Stock.class);

        given(loadStockPort.loadStock(eq(stock.getId())))
                .willReturn(Optional.of(stock));

        updateStockPriceService.updateStockPrice(stock.getId(), updatedPrice);

        then(updateStockPort).should().updateStock(stockCaptor.capture());

        assertThat(stockCaptor.getValue().getCurrentPrice()).isEqualTo(updatedPrice);
        assertThat(stockCaptor.getValue().getId()).isEqualTo(stock.getId());
        assertThat(stockCaptor.getValue().getName()).isEqualTo(stock.getName());
        assertThat(stockCaptor.getValue().getDescription()).isEqualTo(stock.getDescription());
        assertThat(stockCaptor.getValue().getLastUpdate()).isEqualTo(stock.getLastUpdate());
    }

    @Test
    void givenStockDoesNotExist_thenThrowsStockDoesNotExistException() {
        Stock stock = StockTestData.savedStock();
        BigDecimal updatedPrice = BigDecimal.valueOf(123L);

        given(loadStockPort.loadStock(eq(stock.getId())))
                .willReturn(Optional.empty());

        assertThrows(InvalidStockNameException.class, () ->
                updateStockPriceService.updateStockPrice(stock.getId(), updatedPrice));

        then(updateStockPort).should(times(0)).updateStock(any());
    }
}
