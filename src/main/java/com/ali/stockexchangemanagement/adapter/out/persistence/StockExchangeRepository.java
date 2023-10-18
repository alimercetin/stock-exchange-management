package com.ali.stockexchangemanagement.adapter.out.persistence;

import com.ali.stockexchangemanagement.adapter.out.persistence.entity.StockExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockExchangeRepository extends JpaRepository<StockExchangeEntity, Long> {

    Optional<StockExchangeEntity> findByName(String name);

}
