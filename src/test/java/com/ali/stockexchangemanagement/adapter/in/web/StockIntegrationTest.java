package com.ali.stockexchangemanagement.adapter.in.web;

import com.ali.stockexchangemanagement.adapter.in.web.model.CreateStockRequest;
import com.ali.stockexchangemanagement.adapter.in.web.model.ErrorResponse;
import com.ali.stockexchangemanagement.adapter.in.web.model.UpdateStockPriceRequest;
import com.ali.stockexchangemanagement.adapter.out.persistence.StockRepository;
import com.ali.stockexchangemanagement.adapter.out.persistence.entity.StockEntity;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.util.Optional;

import static com.ali.stockexchangemanagement.application.domain.exception.ErrorCode.NONEXISTING_STOCK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class StockIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StockRepository repository;

    @Test
    void createStockSucceeds() {

        CreateStockRequest request = CreateStockRequest.builder()
                .withName("NEWSTOCK")
                .withDescription("New Stock to test")
                .withCurrentPrice(BigDecimal.valueOf(111L))
                .build();

        HttpEntity<CreateStockRequest> httpEntity = new HttpEntity<>(request, defaultHttpHeaders());

        ResponseEntity<Stock> response = restTemplate.postForEntity(
                "/api/v1/stocks",
                httpEntity,
                Stock.class);

        then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Stock createdStock = response.getBody();

        assertThat(createdStock).isNotNull();
        assertThat(createdStock.getId()).isNotNull();
        assertThat(createdStock.getLastUpdate()).isNotNull();
        assertThat(createdStock.getName()).isEqualTo(request.getName());
        assertThat(createdStock.getDescription()).isEqualTo(request.getDescription());
        assertThat(createdStock.getCurrentPrice()).isEqualTo(request.getCurrentPrice());
    }

    @Test
    void updateStockPriceSucceeds() {
        Long stockId = 1L;

        UpdateStockPriceRequest request = UpdateStockPriceRequest.builder()
                .newPrice(BigDecimal.valueOf(99999L))
                .build();

        HttpEntity<UpdateStockPriceRequest> httpEntity = new HttpEntity<>(request, defaultHttpHeaders());

        restTemplate.put(
                "/api/v1/stocks/{id}/price",
                httpEntity, stockId);


        Optional<StockEntity> stock = repository.findById(stockId);

        assertThat(stock).isPresent();
        assertThat(stock.get().getCurrentPrice()).isEqualTo(request.getNewPrice());
    }

    @Test
    void deleteStockSucceeds() {
        Long stockId = 1L;

        restTemplate.delete("/api/v1/stocks/{id}", stockId);

        Optional<StockEntity> stock = repository.findById(stockId);

        assertThat(stock).isEmpty();
    }

    @Test
    void givenStockDoesNotExist_thenReturnsErrorResponse() {
        Long nonexistingStockId = 9999L;

        UpdateStockPriceRequest request = UpdateStockPriceRequest.builder()
                .newPrice(BigDecimal.valueOf(99999L))
                .build();

        HttpEntity<UpdateStockPriceRequest> httpEntity = new HttpEntity<>(request, defaultHttpHeaders());


        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "/api/v1/stocks/{id}/price",
                HttpMethod.PUT,
                httpEntity,
                ErrorResponse.class,
                nonexistingStockId);


        then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        assertThat(response.getBody().getCode()).isEqualTo(NONEXISTING_STOCK.name());
        assertThat(response.getBody().getMessage()).isEqualTo(NONEXISTING_STOCK.getMessage());
    }

    private HttpHeaders defaultHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }

}
