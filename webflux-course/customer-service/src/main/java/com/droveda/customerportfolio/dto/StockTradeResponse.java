package com.droveda.customerportfolio.dto;

import com.droveda.customerportfolio.domain.Ticker;
import com.droveda.customerportfolio.domain.TradeAction;

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
