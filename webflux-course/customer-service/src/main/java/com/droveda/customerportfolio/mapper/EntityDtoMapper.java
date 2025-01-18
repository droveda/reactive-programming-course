package com.droveda.customerportfolio.mapper;

import com.droveda.customerportfolio.domain.Ticker;
import com.droveda.customerportfolio.dto.CustomerInformation;
import com.droveda.customerportfolio.dto.Holding;
import com.droveda.customerportfolio.dto.StockTradeRequest;
import com.droveda.customerportfolio.dto.StockTradeResponse;
import com.droveda.customerportfolio.entity.Customer;
import com.droveda.customerportfolio.entity.PortfolioItem;

import java.util.List;

public class EntityDtoMapper {

    public static CustomerInformation toCustomerInformation(Customer customer, List<PortfolioItem> items) {

        var holdings = items.stream()
                .map(i -> new Holding(i.getTicker(), i.getQuantity()))
                .toList();

        return new CustomerInformation(
                customer.getId(),
                customer.getName(),
                customer.getBalance(),
                holdings);

    }

    public static PortfolioItem toPortfolioItem(Integer customerId, Ticker ticker) {
        var pi = new PortfolioItem();
        pi.setCustomerId(customerId);
        pi.setTicker(ticker);
        pi.setQuantity(0);

        return pi;
    }

    public static StockTradeResponse toStockTradeResponse(StockTradeRequest request, Integer customerId, Integer balance) {
        return new StockTradeResponse(customerId,
                request.ticker(),
                request.price(),
                request.quantity(),
                request.action(),
                request.totalPrice(),
                balance);
    }

}
