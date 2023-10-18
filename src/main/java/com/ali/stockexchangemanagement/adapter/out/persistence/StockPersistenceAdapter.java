package com.ali.stockexchangemanagement.adapter.out.persistence;

import com.ali.stockexchangemanagement.adapter.out.persistence.entity.StockEntity;
import com.ali.stockexchangemanagement.adapter.out.persistence.mapper.StockPersistenceMapper;
import com.ali.stockexchangemanagement.application.domain.model.Stock;
import com.ali.stockexchangemanagement.application.port.out.CreateStockPort;
import com.ali.stockexchangemanagement.application.port.out.DeleteStockPort;
import com.ali.stockexchangemanagement.application.port.out.LoadStockPort;
import com.ali.stockexchangemanagement.application.port.out.UpdateStockPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
class StockPersistenceAdapter implements
        LoadStockPort,
        CreateStockPort,
        DeleteStockPort,
        UpdateStockPort {

    private final StockRepository stockRepository;

    @Override
    public Stock createStock(Stock stock) {
        StockEntity stockEntity = StockPersistenceMapper.toStockEntity(stock);
        StockEntity savedEntity = stockRepository.save(stockEntity);
        return StockPersistenceMapper.toStock(savedEntity);
    }

    @Override
    public void deleteStock(Stock stock) {
        StockEntity stockEntity = StockPersistenceMapper.toStockEntity(stock);
        stockRepository.delete(stockEntity);
    }

    @Override
    public void updateStock(Stock stock) {
        StockEntity stockEntity = StockPersistenceMapper.toStockEntity(stock);
        stockRepository.save(stockEntity);
    }

    @Override
    public Optional<Stock> loadStock(Long id) {
        Optional<StockEntity> stockEntity = stockRepository.findById(id);
        return stockEntity.map(StockPersistenceMapper::toStock);
    }
}
