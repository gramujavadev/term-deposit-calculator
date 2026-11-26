package com.guttula.term_deposit_calculator.factory;

import com.guttula.term_deposit_calculator.model.InterestFrequency;
import com.guttula.term_deposit_calculator.strategy.InterestCalculationStrategy;

public interface InterestStrategyResolver {
    InterestCalculationStrategy resolve(InterestFrequency frequency);
}

