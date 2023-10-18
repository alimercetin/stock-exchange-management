package com.ali.stockexchangemanagement.adapter.out.persistence;

import com.ali.stockexchangemanagement.application.domain.model.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({StockPersistenceAdapter.class})
@AutoConfigureTestDatabase
class StockPersistenceAdapterTest {

    @Autowired
    private StockPersistenceAdapter adapterUnderTest;

    @Test
    void loadsStock() {
        Optional<Stock> stock = adapterUnderTest.loadStock(1L);

        assertThat(stock.isPresent()).isTrue();
        assertThat(stock.get().getCurrentPrice()).isEqualTo(BigDecimal.valueOf(322));
        assertThat(stock.get().getName()).isEqualTo("META");
        assertThat(stock.get().getDescription()).isEqualTo("Meta Platforms");
    }

    @Test
    void createsStock() {
        Stock stockToCreate = Stock.builder()
                .withCurrentPrice(BigDecimal.valueOf(11))
                .withDescription("Description")
                .withName("STOCK")
                .build();

        Stock stock = adapterUnderTest.createStock(stockToCreate);

        Optional<Stock> queriedStock = adapterUnderTest.loadStock(stock.getId());

        assertThat(queriedStock.isPresent()).isTrue();
        assertThat(queriedStock.get().getCurrentPrice()).isEqualTo(stockToCreate.getCurrentPrice());
        assertThat(queriedStock.get().getName()).isEqualTo(stockToCreate.getName());
        assertThat(queriedStock.get().getDescription()).isEqualTo(stockToCreate.getDescription());
    }

    @Test
    void updatesStock() {
        Stock stock = adapterUnderTest.loadStock(1L).get();
        stock.setCurrentPrice(BigDecimal.valueOf(42));

        adapterUnderTest.updateStock(stock);

        Optional<Stock> queriedStock = adapterUnderTest.loadStock(1L);

        assertThat(queriedStock.isPresent()).isTrue();
        assertThat(queriedStock.get().getCurrentPrice()).isEqualTo(BigDecimal.valueOf(42));
    }

    @Test
    void deletesStock() {
        Stock stockToCreate = Stock.builder()
                .withCurrentPrice(BigDecimal.valueOf(11))
                .withDescription("Description")
                .withName("STOCK")
                .build();

        Stock stockToDelete = adapterUnderTest.createStock(stockToCreate);

        adapterUnderTest.deleteStock(stockToDelete);

        Optional<Stock> queriedStock = adapterUnderTest.loadStock(stockToDelete.getId());

        assertThat(queriedStock.isEmpty()).isTrue();
    }

}