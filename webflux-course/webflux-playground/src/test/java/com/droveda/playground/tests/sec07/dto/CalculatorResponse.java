package com.droveda.playground.tests.sec07.dto;

public record CalculatorResponse(
        Integer first,
        Integer second,
        String operation,
        Double result
) {
}
