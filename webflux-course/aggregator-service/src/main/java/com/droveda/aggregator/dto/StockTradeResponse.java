package com.droveda.aggregator.dto;

import com.droveda.aggregator.domain.Ticker;
import com.droveda.aggregator.domain.TradeAction;

public record StockTradeResponse(
        Integer customerId,
        Ticker ticker,
        Integer price,
        Integer quantity,
        TradeAction action,
        Integer totalPrice,
        Integer balance
) {
}
