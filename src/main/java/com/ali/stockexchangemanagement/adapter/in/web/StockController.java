package com.ali.stockexchangemanagement.adapter.in.web;

import com.ali.stockexchangemanagement.adapter.in.web.mapper.StockRestMapper;
import com.ali.stockexchangemanagement.adapter.in.web.model.CreateStockRequest;
import com.ali.stockexchangemanagement.adapter.in.web.model.UpdateStockPriceRequest;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import com.ali.stockexchangemanagement.application.port.in.CreateStockUseCase;
import com.ali.stockexchangemanagement.application.port.in.DeleteStockUseCase;
import com.ali.stockexchangemanagement.application.port.in.UpdateStockPriceUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/stocks", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
class StockController {

    private final CreateStockUseCase createStockUseCase;
    private final DeleteStockUseCase deleteStockUseCase;
    private final UpdateStockPriceUseCase updateStockPriceUseCase;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    Stock createStock(@RequestBody @Valid CreateStockRequest createStockRequest) {

        Stock stock = StockRestMapper.toStock(createStockRequest);
        return createStockUseCase.createStock(stock);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteStock(@PathVariable("id") final Long id) {
        deleteStockUseCase.deleteStock(id);
    }

    @PutMapping("{id}/price")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateStockPrice(
            @PathVariable("id") final Long id,
            @RequestBody @Valid UpdateStockPriceRequest request) {
        updateStockPriceUseCase.updateStockPrice(id, request.getNewPrice());
    }

}
