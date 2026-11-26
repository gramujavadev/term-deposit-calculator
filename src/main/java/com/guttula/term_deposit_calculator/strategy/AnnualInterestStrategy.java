package com.guttula.term_deposit_calculator.strategy;

import com.guttula.term_deposit_calculator.model.InterestFrequency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AnnualInterestStrategy extends AbstractCompoundInterestStrategy {
    @Override public InterestFrequency frequency() { return InterestFrequency.ANNUALLY; }
    @Override public BigDecimal calculate(BigDecimal principal, BigDecimal annualRate, int termInYears) {
        return compound(principal, annualRate, 1, termInYears);
    }
}
