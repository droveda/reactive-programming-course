package com.droveda.customerportfolio.controller;

import com.droveda.customerportfolio.dto.CustomerInformation;
import com.droveda.customerportfolio.dto.StockTradeRequest;
import com.droveda.customerportfolio.dto.StockTradeResponse;
import com.droveda.customerportfolio.service.CustomerService;
import com.droveda.customerportfolio.service.TradeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;
    private final TradeService tradeService;

    public CustomerController(CustomerService customerService, TradeService tradeService) {
        this.customerService = customerService;
        this.tradeService = tradeService;
    }

    @GetMapping("/{customerId}")
    public Mono<CustomerInformation> getCustomerInformation(@PathVariable Integer customerId) {
        return this.customerService.getCustomerInformation(customerId);
    }

    @PostMapping("/{customerId}/trade")
    public Mono<StockTradeResponse> trade(@PathVariable Integer customerId, @RequestBody Mono<StockTradeRequest> mono) {
        return mono.flatMap(req -> tradeService.trade(customerId, req));
    }

}
