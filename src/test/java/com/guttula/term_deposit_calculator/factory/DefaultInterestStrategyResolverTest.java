package com.guttula.term_deposit_calculator.factory;

import com.guttula.term_deposit_calculator.model.InterestFrequency;
import com.guttula.term_deposit_calculator.strategy.AnnualInterestStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DefaultInterestStrategyResolverTest {
    @Test
    void findsStrategyByFrequency() {
        var strategy = new AnnualInterestStrategy();
        var resolver = new DefaultInterestStrategyResolver(List.of(strategy));
        assertSame(strategy, resolver.resolve(InterestFrequency.ANNUALLY));
    }
    @Test void reportsMissingStrategy() {
        var resolver = new DefaultInterestStrategyResolver(List.of());
        assertThrows(IllegalArgumentException.class, () -> resolver.resolve(InterestFrequency.MONTHLY));
    }
}
