package com.guttula.term_deposit_calculator.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

abstract class AbstractCompoundInterestStrategy implements InterestCalculationStrategy {
    private static final int SCALE = 16;
    protected BigDecimal compound(BigDecimal principal, BigDecimal annualRate, int periodsPerYear, int years) {
        BigDecimal periodicRate = annualRate.divide(BigDecimal.valueOf(periodsPerYear), SCALE, RoundingMode.HALF_UP);
        int periods = Math.multiplyExact(periodsPerYear, years);
        return principal.multiply(BigDecimal.ONE.add(periodicRate).pow(periods));
    }
}
