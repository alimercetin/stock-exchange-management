package com.ali.stockexchangemanagement.adapter.in.web.model;

import com.ali.stockexchangemanagement.common.validation.PositivePrice;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStockPriceRequest {
    @NotNull
    @PositivePrice
    private BigDecimal newPrice;
}
