package com.droveda.customerportfolio.service;

import com.droveda.customerportfolio.dto.CustomerInformation;
import com.droveda.customerportfolio.entity.Customer;
import com.droveda.customerportfolio.exceptions.ApplicationExceptions;
import com.droveda.customerportfolio.mapper.EntityDtoMapper;
import com.droveda.customerportfolio.repository.CustomerRepository;
import com.droveda.customerportfolio.repository.PortfolioItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PortfolioItemRepository portfolioItemRepository;

    public CustomerService(CustomerRepository customerRepository, PortfolioItemRepository portfolioItemRepository) {
        this.customerRepository = customerRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }

    public Mono<CustomerInformation> getCustomerInformation(Integer customerId) {
        return customerRepository.findById(customerId)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(customerId))
                .flatMap(this::buildCustomerInformation);

    }

    private Mono<CustomerInformation> buildCustomerInformation(Customer customer) {
        return this.portfolioItemRepository.findAllByCustomerId(customer.getId())
                .collectList()
                .map(list -> EntityDtoMapper.toCustomerInformation(customer, list));
    }

}