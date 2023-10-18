package com.ali.stockexchangemanagement.application.domain.service;

import com.ali.stockexchangemanagement.application.domain.exception.InvalidStockNameException;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import com.ali.stockexchangemanagement.application.port.in.UpdateStockPriceUseCase;
import com.ali.stockexchangemanagement.application.port.out.LoadStockPort;
import com.ali.stockexchangemanagement.application.port.out.UpdateStockPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Transactional
public class UpdateStockPriceService implements UpdateStockPriceUseCase {

    private final LoadStockPort loadStockPort;
    private final UpdateStockPort updateStockPort;

    @Override
    public void updateStockPrice(Long id, BigDecimal price) {
        Stock stockToUpdate = getStock(id);
        stockToUpdate.setCurrentPrice(price);
        updateStockPort.updateStock(stockToUpdate);
    }

    private Stock getStock(Long id) {
        Optional<Stock> queriedStock = loadStockPort.loadStock(id);
        return queriedStock.orElseThrow(() -> new InvalidStockNameException(id));
    }
}
