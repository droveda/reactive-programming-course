package com.droveda.playground.sec05.service;

import com.droveda.playground.sec05.dto.CustomerDto;
import com.droveda.playground.sec05.mapper.EntityDtoMapper;
import com.droveda.playground.sec05.repository.CustomerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Flux<CustomerDto> getAllCustomers() {
        return repository.findAll()
                .map(EntityDtoMapper::toDto);
    }

    public Flux<CustomerDto> getAllCustomers(Integer page, Integer size) {
        return repository.findBy(PageRequest.of(page - 1, size))
                .map(EntityDtoMapper::toDto);
    }

    public Mono<CustomerDto> getCustomerById(Integer id) {
        return repository.findById(id)
                .map(EntityDtoMapper::toDto);
    }

    public Mono<CustomerDto> saveCustomer(Mono<CustomerDto> mono) {
        return mono.map(EntityDtoMapper::toEntity) // we should do input validation, let's worry about this later
                .flatMap(repository::save)
                .map(EntityDtoMapper::toDto);
    }

    public Mono<CustomerDto> updateCustomer(Integer id, Mono<CustomerDto> mono) {
        return repository.findById(id)
                .flatMap(entity -> mono)
                .map(EntityDtoMapper::toEntity)
                .doOnNext(c -> c.setId(id)) // this is safe
                .flatMap(repository::save)
                .map(EntityDtoMapper::toDto);
    }

    public Mono<Boolean> deleteCustomerById(Integer id) {
        return this.repository.deleteCustomerById(id);
    }
}
