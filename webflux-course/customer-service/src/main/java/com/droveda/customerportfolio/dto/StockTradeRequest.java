package com.droveda.customerportfolio.dto;

import com.droveda.customerportfolio.domain.Ticker;
import com.droveda.customerportfolio.domain.TradeAction;

public record StockTradeRequest(
        Ticker ticker,
        Integer price,
        Integer quantity,
        TradeAction action
) {

    public Integer totalPrice() {
        return quantity * price;
    }

}
