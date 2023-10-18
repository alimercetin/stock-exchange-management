package com.ali.stockexchangemanagement.adapter.in.web;

import com.ali.stockexchangemanagement.adapter.in.web.model.AddStockForStockExchangeRequest;
import com.ali.stockexchangemanagement.application.domain.model.StockExchange;
import com.ali.stockexchangemanagement.application.port.in.AddStockForStockExchangeUseCase;
import com.ali.stockexchangemanagement.application.port.in.GetStockExchangeUseCase;
import com.ali.stockexchangemanagement.application.port.in.RemoveStockFromStockExchangeUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/stock-exchanges", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
class StockExchangeController {

    private final GetStockExchangeUseCase getStockExchangeUseCase;
    private final AddStockForStockExchangeUseCase addStockForStockExchangeUseCase;
    private final RemoveStockFromStockExchangeUseCase removeStockFromStockExchangeUseCase;

    @GetMapping("{name}")
    @ResponseStatus(code = HttpStatus.OK)
    StockExchange getStockExchange(@PathVariable("name") final String name) {
        return getStockExchangeUseCase.getStockExchange(name);
    }

    @DeleteMapping("{name}/stock/{stock-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeStockFromStockExchange(
            @PathVariable("name") final String name,
            @PathVariable("stock-id") final Long stockId) {
        removeStockFromStockExchangeUseCase.removeStock(name, stockId);
    }

    @PostMapping("{name}/stock")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addStockForStockExchange(
            @RequestBody @Valid AddStockForStockExchangeRequest request,
            @PathVariable("name") final String name) {
        addStockForStockExchangeUseCase.addStock(name, request.getStockId());
    }

}
