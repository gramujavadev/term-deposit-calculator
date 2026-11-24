package com.guttula.term_deposit_calculator.controller;

import com.guttula.term_deposit_calculator.dto.TermDepositRequest;
import com.guttula.term_deposit_calculator.dto.TermDepositResponse;
import com.guttula.term_deposit_calculator.service.TermDepositCalculatorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/term-deposits")
public class TermDepositController {

    private final TermDepositCalculatorService calculatorService;

    public TermDepositController(TermDepositCalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public TermDepositResponse calculate(@Valid @RequestBody TermDepositRequest request) {
        return calculatorService.calculate(request);
    }
}