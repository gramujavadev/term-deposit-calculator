package com.guttula.term_deposit_calculator;

import com.guttula.term_deposit_calculator.dto.TermDepositRequest;
import com.guttula.term_deposit_calculator.dto.TermDepositResponse;
import com.guttula.term_deposit_calculator.model.InterestFrequency;
import com.guttula.term_deposit_calculator.service.TermDepositCalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TermDepositCalculatorApplicationTests {

	private final TermDepositCalculatorService service = new TermDepositCalculatorService();

	@Test
	void calculatesAtMaturityUsingExerciseExample() {
		TermDepositResponse response = service.calculate(new TermDepositRequest(
				new BigDecimal("10000"), new BigDecimal("1.10"), 3, InterestFrequency.AT_MATURITY));

		assertEquals(new BigDecimal("10330.00"), response.finalBalance());
		assertEquals(new BigDecimal("330.00"), response.interestEarned());
	}

	@Test
	void calculatesAnnualCompounding() {
		TermDepositResponse response = service.calculate(new TermDepositRequest(
				new BigDecimal("10000"), new BigDecimal("1.10"), 3, InterestFrequency.ANNUALLY));

		assertEquals(new BigDecimal("10333.64"), response.finalBalance());
	}

	@Test
	void calculatesMonthlyCompounding() {
		TermDepositResponse response = service.calculate(new TermDepositRequest(
				new BigDecimal("10000"), new BigDecimal("1.10"), 3, InterestFrequency.MONTHLY));

		assertEquals(new BigDecimal("10335.35"), response.finalBalance());
	}

}
