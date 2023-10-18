package com.ali.stockexchangemanagement.application.domain.service;

import com.ali.stockexchangemanagement.application.domain.model.Stock;
import com.ali.stockexchangemanagement.application.port.in.CreateStockUseCase;
import com.ali.stockexchangemanagement.application.port.out.CreateStockPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Transactional
public class CreateStockService implements CreateStockUseCase {

    private final CreateStockPort createStockPort;

    @Override
    public Stock createStock(Stock stock) {
        return createStockPort.createStock(stock);
    }
}
