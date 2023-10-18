package com.ali.stockexchangemanagement.adapter.out.persistence;

import com.ali.stockexchangemanagement.adapter.out.persistence.entity.StockExchangeEntity;
import com.ali.stockexchangemanagement.adapter.out.persistence.mapper.StockExchangePersistenceMapper;
import com.ali.stockexchangemanagement.application.domain.model.StockExchange;
import com.ali.stockexchangemanagement.application.port.out.LoadStockExchangePort;
import com.ali.stockexchangemanagement.application.port.out.UpdateStockExchangePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
class StockExchangePersistenceAdapter implements
        LoadStockExchangePort,
        UpdateStockExchangePort {

    private final StockExchangeRepository stockExchangeRepository;

    @Override
    public void updateStockExchange(StockExchange stockExchange) {
        StockExchangeEntity entity = StockExchangePersistenceMapper.toStockExchangeEntity(stockExchange);
        stockExchangeRepository.save(entity);
    }

    @Override
    public Optional<StockExchange> loadStockExchange(String name) {
        Optional<StockExchangeEntity> entity = stockExchangeRepository.findByName(name);
        return entity.map(StockExchangePersistenceMapper::toStockExchange);
    }
}
