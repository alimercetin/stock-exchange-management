package com.ali.stockexchangemanagement.adapter.out.persistence;

import com.ali.stockexchangemanagement.adapter.out.persistence.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
}
