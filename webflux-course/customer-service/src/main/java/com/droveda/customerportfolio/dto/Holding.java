package com.droveda.customerportfolio.dto;

import com.droveda.customerportfolio.domain.Ticker;

public record Holding(
        Ticker ticker,
        Integer quantity
) {
}
