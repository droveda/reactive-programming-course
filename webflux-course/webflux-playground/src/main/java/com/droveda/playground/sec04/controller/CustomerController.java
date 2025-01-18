package com.droveda.playground.sec04.controller;

import com.droveda.playground.sec04.dto.CustomerDto;
import com.droveda.playground.sec04.exceptions.ApplicationExceptions;
import com.droveda.playground.sec04.service.CustomerService;
import com.droveda.playground.sec04.validator.RequestValidator;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<CustomerDto> allCustomers() {
        return this.service.getAllCustomers();
    }

    @GetMapping("paginated")
    public Mono<List<CustomerDto>> allCustomersPaginated(@RequestParam(defaultValue = "1") Integer page,
                                                         @RequestParam(defaultValue = "3") Integer size) {
        return this.service.getAllCustomers(page, size)
                .collectList();
    }

    @GetMapping("{id}")
    public Mono<CustomerDto> getCustomer(@PathVariable Integer id) {
        return this.service.getCustomerById(id)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id));
    }

    @PostMapping
    public Mono<CustomerDto> saveCustomer(@RequestBody Mono<CustomerDto> mono) {
        return mono.transform(RequestValidator.validate())
                .as(this.service::saveCustomer);
    }

    @PutMapping("{id}")
    public Mono<CustomerDto> updateCustomer(@PathVariable Integer id, @RequestBody Mono<CustomerDto> mono) {
        return mono.transform(RequestValidator.validate())
                .as(validDto -> service.updateCustomer(id, validDto))
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id));
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteCustomer(@PathVariable Integer id) {
        return service.deleteCustomerById(id)
                .filter(b -> b)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id))
                .then();
    }
}
