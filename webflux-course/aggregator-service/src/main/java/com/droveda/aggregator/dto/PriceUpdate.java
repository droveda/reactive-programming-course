package com.droveda.aggregator.dto;

import com.droveda.aggregator.domain.Ticker;

import java.time.LocalDateTime;

public record PriceUpdate(
        Ticker ticker,
        Integer price,
        LocalDateTime time
) {
}
