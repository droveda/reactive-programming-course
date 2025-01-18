package com.droveda.aggregator.dto;

import com.droveda.aggregator.domain.Ticker;

public record Holding(
        Ticker ticker,
        Integer quantity
) {
}
