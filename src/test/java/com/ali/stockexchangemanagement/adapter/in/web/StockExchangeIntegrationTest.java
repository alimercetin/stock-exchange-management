package com.ali.stockexchangemanagement.adapter.in.web;

import com.ali.stockexchangemanagement.adapter.in.web.model.AddStockForStockExchangeRequest;
import com.ali.stockexchangemanagement.adapter.in.web.model.ErrorResponse;
import com.ali.stockexchangemanagement.application.domain.model.StockExchange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static com.ali.stockexchangemanagement.application.domain.exception.ErrorCode.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class StockExchangeIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getStockExchangeSucceeds() {
        String stockExchangeName = "XAMS";

        ResponseEntity<StockExchange> response = restTemplate.getForEntity(
                "/api/v1/stock-exchanges/{name}",
                StockExchange.class,
                stockExchangeName);

        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(stockExchangeName);
    }

    @Test
    void givenStockExchangeDoesNotExist_thenReturnsErrorResponse() {
        ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(
                "/api/v1/stock-exchanges/{name}",
                ErrorResponse.class,
                "NONEXISTING");

        then(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        assertThat(response.getBody().getCode()).isEqualTo(STOCK_EXCHANGE_NOT_FOUND.name());
        assertThat(response.getBody().getMessage()).isEqualTo(STOCK_EXCHANGE_NOT_FOUND.getMessage());
    }

    @Test
    void deleteStockFromStockExchangeSucceeds() {
        String stockExchangeName = "XAMS";
        Long stockId = 1L;

        final var response = restTemplate.exchange(
                "/api/v1/stock-exchanges/{name}/stock/{stock-id}",
                HttpMethod.DELETE,
                new HttpEntity<>(null, defaultHttpHeaders()),
                Void.class,
                stockExchangeName, stockId);

        then(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void addStockForStockExchangeSucceeds() {
        String stockExchangeName = "XAMS";

        AddStockForStockExchangeRequest request = AddStockForStockExchangeRequest.builder()
                .stockId(6L)
                .build();

        HttpEntity<AddStockForStockExchangeRequest> httpEntity = new HttpEntity<>(request, defaultHttpHeaders());

        final var response = restTemplate.exchange(
                "/api/v1/stock-exchanges/{name}/stock",
                HttpMethod.POST,
                httpEntity,
                Void.class,
                stockExchangeName);

        then(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void givenInvalidStockId_thenReturnsErrorResponse() {
        String stockExchangeName = "XAMS";

        AddStockForStockExchangeRequest request = AddStockForStockExchangeRequest.builder()
                .stockId(9999L)
                .build();

        HttpEntity<AddStockForStockExchangeRequest> httpEntity = new HttpEntity<>(request, defaultHttpHeaders());

        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "/api/v1/stock-exchanges/{name}/stock",
                HttpMethod.POST,
                httpEntity,
                ErrorResponse.class,
                stockExchangeName);


        then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        assertThat(response.getBody().getCode()).isEqualTo(NONEXISTING_STOCK.name());
        assertThat(response.getBody().getMessage()).isEqualTo(NONEXISTING_STOCK.getMessage());
    }

    @Test
    void givenInvalidStockExchangeName_thenReturnsErrorResponse() {
        String stockExchangeName = "NONEXISTING";

        AddStockForStockExchangeRequest request = AddStockForStockExchangeRequest.builder()
                .stockId(1L)
                .build();

        HttpEntity<AddStockForStockExchangeRequest> httpEntity = new HttpEntity<>(request, defaultHttpHeaders());

        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "/api/v1/stock-exchanges/{name}/stock",
                HttpMethod.POST,
                httpEntity,
                ErrorResponse.class,
                stockExchangeName);


        then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        assertThat(response.getBody().getCode()).isEqualTo(NONEXISTING_STOCK_EXCHANGE.name());
        assertThat(response.getBody().getMessage()).isEqualTo(NONEXISTING_STOCK_EXCHANGE.getMessage());
    }

    @Test
    void givenStockExchangeAlreadyHasStock_thenReturnsErrorResponse() {
        String stockExchangeName = "XAMS";

        AddStockForStockExchangeRequest request = AddStockForStockExchangeRequest.builder()
                .stockId(1L)
                .build();

        HttpEntity<AddStockForStockExchangeRequest> httpEntity = new HttpEntity<>(request, defaultHttpHeaders());

        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "/api/v1/stock-exchanges/{name}/stock",
                HttpMethod.POST,
                httpEntity,
                ErrorResponse.class,
                stockExchangeName);


        then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        assertThat(response.getBody().getCode()).isEqualTo(ALREADY_EXISTING_STOCK.name());
        assertThat(response.getBody().getMessage()).isEqualTo(ALREADY_EXISTING_STOCK.getMessage());
    }

    private HttpHeaders defaultHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }

}
