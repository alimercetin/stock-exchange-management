package com.ali.stockexchangemanagement.application.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Builder(toBuilder = true, setterPrefix = "with")
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Stock {

    private Long id;

    @NonNull
    private String name;

    private String description;

    @Setter
    @NonNull
    private BigDecimal currentPrice;

    private Instant lastUpdate;

}
