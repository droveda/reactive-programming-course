package com.droveda.aggregator.dto;

import com.droveda.aggregator.domain.Ticker;
import com.droveda.aggregator.domain.TradeAction;

public record StockTradeRequest(
        Ticker ticker,
        Integer price,
        Integer quantity,
        TradeAction action
) {

}
