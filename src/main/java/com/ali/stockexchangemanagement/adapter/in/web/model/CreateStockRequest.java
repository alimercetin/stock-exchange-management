package com.ali.stockexchangemanagement.adapter.in.web.model;

import com.ali.stockexchangemanagement.common.validation.PositivePrice;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder(toBuilder = true, setterPrefix = "with")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStockRequest {

    @NotEmpty(message = "Name may not be empty")
    private String name;

    private String description;

    @NotNull
    @PositivePrice
    private BigDecimal currentPrice;
}
