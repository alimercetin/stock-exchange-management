package com.ali.stockexchangemanagement.adapter.in.web.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddStockForStockExchangeRequest {

    @NotNull(message = "Stock id is mandatory")
    private Long stockId;
}
