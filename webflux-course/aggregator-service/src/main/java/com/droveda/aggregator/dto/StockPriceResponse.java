package com.droveda.aggregator.dto;

import com.droveda.aggregator.domain.Ticker;

public record StockPriceResponse(
        Ticker ticker,
        Integer price
) {
}
