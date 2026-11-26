package com.guttula.term_deposit_calculator.strategy;

import com.guttula.term_deposit_calculator.model.InterestFrequency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AtMaturityInterestStrategy implements InterestCalculationStrategy {
    @Override public InterestFrequency frequency() { return InterestFrequency.AT_MATURITY; }
    @Override public BigDecimal calculate(BigDecimal principal, BigDecimal annualRate, int termInYears) {
        // Simple interest matches the exercise example: 10000 at 1.10% for 3 years = 10330.
        return principal.multiply(BigDecimal.ONE.add(annualRate.multiply(BigDecimal.valueOf(termInYears))));
    }
}