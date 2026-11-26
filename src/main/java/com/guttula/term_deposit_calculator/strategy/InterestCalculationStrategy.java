package com.guttula.term_deposit_calculator.strategy;

import com.guttula.term_deposit_calculator.model.InterestFrequency;

import java.math.BigDecimal;

/** annualRate is a decimal fraction: 1.10% is passed as 0.011. */
public interface InterestCalculationStrategy {
    InterestFrequency frequency();
    BigDecimal calculate(BigDecimal principal, BigDecimal annualRate, int termInYears);
}
