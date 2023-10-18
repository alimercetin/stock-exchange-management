package com.ali.stockexchangemanagement.application.domain.service;

import com.ali.stockexchangemanagement.application.domain.exception.InvalidStockNameException;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import com.ali.stockexchangemanagement.application.port.out.DeleteStockPort;
import com.ali.stockexchangemanagement.application.port.out.LoadStockPort;
import com.ali.stockexchangemanagement.common.StockTestData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

class DeleteStockServiceTest {

    private final LoadStockPort loadStockPort =
            Mockito.mock(LoadStockPort.class);

    private final DeleteStockPort deleteStockPort =
            Mockito.mock(DeleteStockPort.class);

    private final DeleteStockService deleteStockService =
            new DeleteStockService(loadStockPort, deleteStockPort);

    @Test
    void updateSucceeds() {
        Stock stock = StockTestData.savedStock();

        given(loadStockPort.loadStock(eq(stock.getId())))
                .willReturn(Optional.of(stock));

        deleteStockService.deleteStock(stock.getId());

        then(deleteStockPort).should().deleteStock(stock);
    }

    @Test
    void givenStockDoesNotExist_thenThrowsInvalidStockNameException() {
        Stock stock = StockTestData.savedStock();

        given(loadStockPort.loadStock(eq(stock.getId())))
                .willReturn(Optional.empty());

        assertThrows(InvalidStockNameException.class, () ->
                deleteStockService.deleteStock(stock.getId()));

        then(deleteStockPort).should(times(0)).deleteStock(any());
    }

}
