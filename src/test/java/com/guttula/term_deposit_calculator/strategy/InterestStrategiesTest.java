package com.guttula.term_deposit_calculator.strategy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InterestStrategiesTest {
    private static final BigDecimal P = new BigDecimal("10000");
    private static final BigDecimal R = new BigDecimal("0.011");
    private void money(String expected, BigDecimal actual) {
        assertTrue(new BigDecimal(expected).compareTo(actual.setScale(2, RoundingMode.HALF_UP)) == 0,
                () -> "Expected " + expected + " but was " + actual);
    }
    @Test void atMaturity() { money("10330.00", new AtMaturityInterestStrategy().calculate(P,R,3)); }
    @Test void annually() { money("10333.64", new AnnualInterestStrategy().calculate(P,R,3)); }
    @Test void quarterly() { money("10335.04", new QuarterlyInterestStrategy().calculate(P,R,3)); }
    @Test void monthly() { money("10335.35", new MonthlyInterestStrategy().calculate(P,R,3)); }
}

