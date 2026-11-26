package com.guttula.term_deposit_calculator.service;

import com.guttula.term_deposit_calculator.dto.TermDepositRequest;
import com.guttula.term_deposit_calculator.dto.TermDepositResponse;
import com.guttula.term_deposit_calculator.factory.InterestStrategyResolver;
import com.guttula.term_deposit_calculator.model.InterestFrequency;
import com.guttula.term_deposit_calculator.strategy.InterestCalculationStrategy;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class TermDepositCalculatorServiceTest {
    private final InterestStrategyResolver resolver = mock(InterestStrategyResolver.class);
    private final InterestCalculationStrategy strategy = mock(InterestCalculationStrategy.class);
    private final TermDepositCalculatorService service = new TermDepositCalculatorService(resolver);
    @Test
    void delegatesToResolvedStrategyAndReturnsRoundedAmounts() {
        when(resolver.resolve(InterestFrequency.AT_MATURITY)).thenReturn(strategy);
        when(strategy.calculate(any(), any(), anyInt())).thenReturn(new BigDecimal("10330.000"));
        TermDepositResponse result = service.calculate(new TermDepositRequest(
                new BigDecimal("10000"), new BigDecimal("1.10"), 3, InterestFrequency.AT_MATURITY));
        assertMoney("10330.00", result.finalBalance());
        assertMoney("330.00", result.interestEarned());
        verify(resolver).resolve(InterestFrequency.AT_MATURITY);
        verify(strategy).calculate(eq(new BigDecimal("10000")), any(BigDecimal.class), eq(3));
    }
    private void assertMoney(String expected, BigDecimal actual) {
        assertTrue(new BigDecimal(expected).compareTo(actual) == 0);
    }
}
