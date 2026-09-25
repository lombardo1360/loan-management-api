package com.bank.loan.application.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class UpdateLoanRequest {

    @Positive
    private BigDecimal amount;

    @PositiveOrZero
    private BigDecimal interestRate;

    @Positive
    private int termInMonths;

    public UpdateLoanRequest() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public int getTermInMonths() {
        return termInMonths;
    }
}