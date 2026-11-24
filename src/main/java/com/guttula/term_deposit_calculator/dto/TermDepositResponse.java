package com.guttula.term_deposit_calculator.dto;

import com.guttula.term_deposit_calculator.model.InterestFrequency;

import java.math.BigDecimal;

public record TermDepositResponse(
        BigDecimal initialDeposit,
        BigDecimal annualInterestRate,
        Integer termInYears,
        InterestFrequency interestFrequency,
        BigDecimal interestEarned,
        BigDecimal finalBalance
) {
}
