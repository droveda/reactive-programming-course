package com.droveda.aggregator.dto;

import com.droveda.aggregator.domain.Ticker;
import com.droveda.aggregator.domain.TradeAction;

public record TradeRequest(
        Ticker ticker,
        TradeAction action,
        Integer quantity
) {
}
