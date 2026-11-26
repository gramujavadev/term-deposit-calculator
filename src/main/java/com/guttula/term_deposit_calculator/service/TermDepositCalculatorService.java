package com.guttula.term_deposit_calculator.service;

import com.guttula.term_deposit_calculator.dto.TermDepositRequest;
import com.guttula.term_deposit_calculator.dto.TermDepositResponse;
import com.guttula.term_deposit_calculator.factory.InterestStrategyResolver;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TermDepositCalculatorService {

    private static final int MONEY_SCALE = 2;
    private static final int RATE_SCALE = 16;
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private final InterestStrategyResolver strategyResolver;

    public TermDepositCalculatorService(InterestStrategyResolver strategyResolver) {
        this.strategyResolver = strategyResolver;
    }

    public TermDepositResponse calculate(TermDepositRequest request) {
        BigDecimal rate = request.annualInterestRate().divide(HUNDRED, RATE_SCALE, RoundingMode.HALF_UP);
        BigDecimal rawBalance = strategyResolver.resolve(request.interestFrequency())
                .calculate(request.depositAmount(), rate, request.termInYears());
        BigDecimal finalBalance = rawBalance.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        BigDecimal principal = request.depositAmount().setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        return new TermDepositResponse(principal, request.annualInterestRate(), request.termInYears(),
                request.interestFrequency(), finalBalance.subtract(principal), finalBalance);
    }
}
