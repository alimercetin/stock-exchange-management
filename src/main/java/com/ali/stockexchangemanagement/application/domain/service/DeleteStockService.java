package com.ali.stockexchangemanagement.application.domain.service;

import com.ali.stockexchangemanagement.application.domain.exception.InvalidStockNameException;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import com.ali.stockexchangemanagement.application.port.in.DeleteStockUseCase;
import com.ali.stockexchangemanagement.application.port.out.DeleteStockPort;
import com.ali.stockexchangemanagement.application.port.out.LoadStockPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
@Transactional
public class DeleteStockService implements DeleteStockUseCase {

    private final LoadStockPort loadStockPort;
    private final DeleteStockPort deleteStockPort;

    @Override
    public void deleteStock(Long id) {
        Stock stockToDelete = getStock(id);
        deleteStockPort.deleteStock(stockToDelete);
    }

    private Stock getStock(Long id) {
        Optional<Stock> queriedStock = loadStockPort.loadStock(id);
        return queriedStock.orElseThrow(() -> new InvalidStockNameException(id));
    }
}
