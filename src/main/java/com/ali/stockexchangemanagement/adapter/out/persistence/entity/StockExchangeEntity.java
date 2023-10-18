package com.ali.stockexchangemanagement.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stock_exchange")
@Builder(toBuilder = true, setterPrefix = "with")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockExchangeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "STOCK_EXCHANGE_STOCKS",
            joinColumns = @JoinColumn(name = "STOCK_EXCHANGE_ID"),
            inverseJoinColumns = @JoinColumn(name = "STOCK_ID"))
    private Set<StockEntity> stocks = new HashSet<>();
}
