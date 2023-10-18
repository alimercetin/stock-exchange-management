package com.ali.stockexchangemanagement.application.domain.service;

import com.ali.stockexchangemanagement.application.domain.exception.InvalidStockExchangeNameException;
import com.ali.stockexchangemanagement.application.domain.exception.InvalidStockNameException;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import com.ali.stockexchangemanagement.application.domain.model.StockExchange;
import com.ali.stockexchangemanagement.application.port.in.RemoveStockFromStockExchangeUseCase;
import com.ali.stockexchangemanagement.application.port.out.LoadStockExchangePort;
import com.ali.stockexchangemanagement.application.port.out.LoadStockPort;
import com.ali.stockexchangemanagement.application.port.out.UpdateStockExchangePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
@Transactional
public class RemoveStockFromStockExchangeService implements RemoveStockFromStockExchangeUseCase {

    private final LoadStockExchangePort loadStockExchangePort;
    private final LoadStockPort loadStockPort;
    private final UpdateStockExchangePort updateStockExchangePort;

    @Override
    public void removeStock(String exchangeName, Long stockId) {
        StockExchange exchange = getStockExchange(exchangeName);
        Stock stock = getStock(stockId);
        exchange.getStocks().removeIf(it -> it.getId().equals(stock.getId()));
        updateStockExchangePort.updateStockExchange(exchange);
    }

    private Stock getStock(Long stockId) {
        Optional<Stock> queriedStock = loadStockPort.loadStock(stockId);
        return queriedStock.orElseThrow(() -> new InvalidStockNameException(stockId));
    }

    private StockExchange getStockExchange(String exchangeName) {
        Optional<StockExchange> queriedStock = loadStockExchangePort.loadStockExchange(exchangeName);
        return queriedStock.orElseThrow(() -> new InvalidStockExchangeNameException(exchangeName));
    }
}
