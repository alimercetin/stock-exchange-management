package com.ali.stockexchangemanagement.adapter.out.persistence;

import com.ali.stockexchangemanagement.application.domain.model.Stock;
import com.ali.stockexchangemanagement.application.domain.model.StockExchange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({StockExchangePersistenceAdapter.class})
@AutoConfigureTestDatabase
class StockExchangePersistenceAdapterTest {

    @Autowired
    private StockExchangePersistenceAdapter adapterUnderTest;

    @Test
    void loadsStockExchange() {
        Optional<StockExchange> stockExchange = adapterUnderTest.loadStockExchange("XAMS");

        assertThat(stockExchange.isPresent()).isTrue();
        assertThat(stockExchange.get().getId()).isEqualTo(1);
        assertThat(stockExchange.get().getDescription()).isEqualTo("Amsterdam");
    }

    @Test
    void updatesStockExchange() {
        StockExchange stockExchange = adapterUnderTest.loadStockExchange("XAMS").get();
        Stock stockToRemove = stockExchange.getStocks().stream().findFirst().get();

        stockExchange.getStocks().remove(stockToRemove);

        adapterUnderTest.updateStockExchange(stockExchange);

        Optional<StockExchange> queriedStockExchange = adapterUnderTest.loadStockExchange("XAMS");

        assertThat(queriedStockExchange.isPresent()).isTrue();
        assertThat(queriedStockExchange.get().getStocks()).doesNotContain(stockToRemove);
    }

}