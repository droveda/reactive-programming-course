package com.droveda.aggregator.controller;

import com.droveda.aggregator.dto.CustomerInformation;
import com.droveda.aggregator.dto.StockTradeResponse;
import com.droveda.aggregator.dto.TradeRequest;
import com.droveda.aggregator.service.CustomerPortfolioService;
import com.droveda.aggregator.validator.RequestValidator;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
public class CustomerPortfolioController {

    private final CustomerPortfolioService customerPortfolioService;

    public CustomerPortfolioController(CustomerPortfolioService customerPortfolioService) {
        this.customerPortfolioService = customerPortfolioService;
    }

    @GetMapping("/{customerId}")
    public Mono<CustomerInformation> getCustomerInformation(@PathVariable Integer customerId) {
        return customerPortfolioService.getCustomerInformation(customerId);
    }

    @PostMapping("/{customerId}/trade")
    public Mono<StockTradeResponse> trade(@PathVariable Integer customerId, @RequestBody Mono<TradeRequest> mono) {
        return mono.transform(RequestValidator.validate())
                .flatMap(req -> customerPortfolioService.trade(customerId, req));
    }

}
