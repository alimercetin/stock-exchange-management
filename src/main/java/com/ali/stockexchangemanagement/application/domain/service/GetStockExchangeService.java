package com.ali.stockexchangemanagement.application.domain.service;

import com.ali.stockexchangemanagement.application.domain.exception.StockExchangeNotFoundException;
import com.ali.stockexchangemanagement.application.domain.model.StockExchange;
import com.ali.stockexchangemanagement.application.port.in.GetStockExchangeUseCase;
import com.ali.stockexchangemanagement.application.port.out.LoadStockExchangePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
@Transactional
public class GetStockExchangeService implements GetStockExchangeUseCase {

    private final LoadStockExchangePort loadStockExchangePort;

    @Override
    public StockExchange getStockExchange(String exchangeName) {
        Optional<StockExchange> queriedStock = loadStockExchangePort.loadStockExchange(exchangeName);
        return queriedStock.orElseThrow(() -> new StockExchangeNotFoundException(exchangeName));
    }
}
