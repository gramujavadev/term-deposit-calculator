package com.guttula.term_deposit_calculator.dto;

import com.guttula.term_deposit_calculator.model.InterestFrequency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TermDepositRequest(
        @NotNull(message = "Initial deposit is required")
        @DecimalMin(value = "0.01", message = "Initial deposit must be greater than zero")
        BigDecimal depositAmount,

        @NotNull(message = "Annual interest rate is required")
        @DecimalMin(value = "0.0", message = "Interest rate cannot be negative")
        BigDecimal annualInterestRate,

        @NotNull(message = "Investment term is required")
        @Positive(message = "Investment term must be a positive number of years")
        Integer termInYears,

        @NotNull(message = "Interest frequency is required")
        InterestFrequency interestFrequency
) {
}
