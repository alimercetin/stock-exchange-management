package com.ali.stockexchangemanagement.application.domain.service;

import com.ali.stockexchangemanagement.application.domain.exception.StockExchangeNotFoundException;
import com.ali.stockexchangemanagement.application.domain.model.StockExchange;
import com.ali.stockexchangemanagement.application.port.out.LoadStockExchangePort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.ali.stockexchangemanagement.common.StockExchangeTestData.savedStockExchange;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;

class GetStockExchangeServiceTest {

    private final LoadStockExchangePort loadStockExchangePort =
            Mockito.mock(LoadStockExchangePort.class);

    private final GetStockExchangeService service =
            new GetStockExchangeService(loadStockExchangePort);

    @Test
    void getStockExchangeSucceeds() {
        StockExchange stockExchange = savedStockExchange();

        given(loadStockExchangePort.loadStockExchange(eq(stockExchange.getName())))
                .willReturn(Optional.of(stockExchange));

        StockExchange response = service.getStockExchange(stockExchange.getName());

        assertThat(response).isEqualTo(stockExchange);
    }

    @Test
    void givenStockExchangeDoesNotExist_thenThrowsStockExchangeNotFoundException() {
        StockExchange stockExchange = savedStockExchange();

        given(loadStockExchangePort.loadStockExchange(eq(stockExchange.getName())))
                .willReturn(Optional.empty());

        assertThrows(StockExchangeNotFoundException.class, () ->
                service.getStockExchange(stockExchange.getName()));
    }

}
