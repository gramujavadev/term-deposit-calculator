package com.guttula.term_deposit_calculator.service;

import com.guttula.term_deposit_calculator.dto.TermDepositRequest;
import com.guttula.term_deposit_calculator.dto.TermDepositResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TermDepositCalculatorService {

    private static final int MONEY_SCALE = 2;
    private static final int CALCULATION_SCALE = 12;
    private static final BigDecimal HUNDRED = new BigDecimal("100");

    public TermDepositResponse calculate(TermDepositRequest request) {
        BigDecimal principal = request.depositAmount();
        // API accepts the rate as a percentage, e.g. 1.10 means 1.10%.
        BigDecimal annualRate = request.annualInterestRate().divide(HUNDRED, CALCULATION_SCALE,
                RoundingMode.HALF_UP);

        BigDecimal finalBalance = switch (request.interestFrequency()) {
            case MONTHLY -> compound(principal, annualRate, 12, request.termInYears());
            case QUARTERLY -> compound(principal, annualRate, 4, request.termInYears());
            case ANNUALLY -> compound(principal, annualRate, 1, request.termInYears());
            // The exercise's example ($10,000 at 1.10% for 3 years = $10,330)
            // establishes simple interest when interest is paid at maturity.
            case AT_MATURITY -> principal.multiply(
                    BigDecimal.ONE.add(annualRate.multiply(BigDecimal.valueOf(request.termInYears()))));
        };

        finalBalance = finalBalance.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        BigDecimal interestEarned = finalBalance.subtract(principal.setScale(MONEY_SCALE, RoundingMode.HALF_UP))
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);

        return new TermDepositResponse(
                principal.setScale(MONEY_SCALE, RoundingMode.HALF_UP),
                request.annualInterestRate(),
                request.termInYears(),
                request.interestFrequency(),
                interestEarned,
                finalBalance
        );
    }

    private BigDecimal compound(BigDecimal principal, BigDecimal annualRate,
                                int periodsPerYear, int years) {
        BigDecimal periodicRate = annualRate.divide(
                BigDecimal.valueOf(periodsPerYear), CALCULATION_SCALE, RoundingMode.HALF_UP);

        int totalPeriods = periodsPerYear * years;
        BigDecimal factor = BigDecimal.ONE.add(periodicRate);
        BigDecimal growthFactor = factor.pow(totalPeriods);

        return principal.multiply(growthFactor);
    }
}
