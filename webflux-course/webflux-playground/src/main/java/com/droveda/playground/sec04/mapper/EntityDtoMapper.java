package com.droveda.playground.sec04.mapper;

import com.droveda.playground.sec04.dto.CustomerDto;
import com.droveda.playground.sec04.entity.Customer;

public class EntityDtoMapper {

    public static Customer toEntity(CustomerDto dto) {
        var customer = new Customer();
        customer.setName(dto.name());
        customer.setEmail(dto.email());
        customer.setId(dto.id());

        return customer;
    }

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getName(), customer.getEmail());
    }

}
