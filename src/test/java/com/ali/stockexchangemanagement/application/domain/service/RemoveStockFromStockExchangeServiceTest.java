package com.ali.stockexchangemanagement.application.domain.service;

import com.ali.stockexchangemanagement.application.domain.exception.InvalidStockExchangeNameException;
import com.ali.stockexchangemanagement.application.domain.exception.InvalidStockNameException;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import com.ali.stockexchangemanagement.application.domain.model.StockExchange;
import com.ali.stockexchangemanagement.application.port.out.LoadStockExchangePort;
import com.ali.stockexchangemanagement.application.port.out.LoadStockPort;
import com.ali.stockexchangemanagement.application.port.out.UpdateStockExchangePort;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static com.ali.stockexchangemanagement.common.StockExchangeTestData.defaultStockExchange;
import static com.ali.stockexchangemanagement.common.StockExchangeTestData.savedStockExchange;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

class RemoveStockFromStockExchangeServiceTest {

    private final LoadStockPort loadStockPort =
            Mockito.mock(LoadStockPort.class);

    private final LoadStockExchangePort loadStockExchangePort =
            Mockito.mock(LoadStockExchangePort.class);

    private final UpdateStockExchangePort updateStockExchangePort =
            Mockito.mock(UpdateStockExchangePort.class);

    private final RemoveStockFromStockExchangeService service =
            new RemoveStockFromStockExchangeService(loadStockExchangePort, loadStockPort, updateStockExchangePort);

    @Test
    void removeStockForStockExchangeSucceeds() {
        StockExchange stockExchange = savedStockExchange();
        Stock stock = stockExchange.getStocks().stream().findFirst().get();
        ArgumentCaptor<StockExchange> stockExchangeCaptor = ArgumentCaptor.forClass(StockExchange.class);

        given(loadStockPort.loadStock(eq(stock.getId())))
                .willReturn(Optional.of(stock));

        given(loadStockExchangePort.loadStockExchange(eq(stockExchange.getName())))
                .willReturn(Optional.of(stockExchange));

        service.removeStock(stockExchange.getName(), stock.getId());

        then(updateStockExchangePort).should(times(1))
                .updateStockExchange(stockExchangeCaptor.capture());

        assertThat(stockExchangeCaptor.getValue().getStocks()).doesNotContain(stock);
    }

    @Test
    void givenStockDoesNotExist_thenThrowsInvalidStockNameException() {
        Long stockId = 1L;
        StockExchange stockExchange = defaultStockExchange();

        given(loadStockPort.loadStock(eq(stockId)))
                .willReturn(Optional.empty());

        given(loadStockExchangePort.loadStockExchange(eq(stockExchange.getName())))
                .willReturn(Optional.of(stockExchange));

        assertThrows(InvalidStockNameException.class, () ->
                service.removeStock(stockExchange.getName(), stockId));

        then(updateStockExchangePort).should(times(0)).updateStockExchange(any());
    }

    @Test
    void givenStockExchangeDoesNotExist_thenThrowsInvalidStockExchangeNameException() {
        Long stockId = 1L;
        String stockExchangeName = "STOCKEXCHANGE";

        given(loadStockExchangePort.loadStockExchange(eq(stockExchangeName)))
                .willReturn(Optional.empty());

        assertThrows(InvalidStockExchangeNameException.class, () ->
                service.removeStock(stockExchangeName, stockId));

        then(updateStockExchangePort).should(times(0)).updateStockExchange(any());
        then(loadStockPort).should(times(0)).loadStock(any());
    }

}
