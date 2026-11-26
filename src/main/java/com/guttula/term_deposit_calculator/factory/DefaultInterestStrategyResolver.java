package com.guttula.term_deposit_calculator.factory;

import com.guttula.term_deposit_calculator.model.InterestFrequency;
import com.guttula.term_deposit_calculator.strategy.InterestCalculationStrategy;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class DefaultInterestStrategyResolver implements InterestStrategyResolver {
    private final Map<InterestFrequency, InterestCalculationStrategy> strategies;
    public DefaultInterestStrategyResolver(List<InterestCalculationStrategy> strategyList) {
        Map<InterestFrequency, InterestCalculationStrategy> map = new EnumMap<>(InterestFrequency.class);
        for (InterestCalculationStrategy strategy : strategyList) {
            if (map.put(strategy.frequency(), strategy) != null)
                throw new IllegalStateException("Duplicate strategy for " + strategy.frequency());
        }
        strategies = Map.copyOf(map);
    }
    @Override public InterestCalculationStrategy resolve(InterestFrequency frequency) {
        InterestCalculationStrategy strategy = strategies.get(frequency);
        if (strategy == null) throw new IllegalArgumentException("No strategy configured for " + frequency);
        return strategy;
    }

}
