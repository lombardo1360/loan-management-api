package com.bank.loan.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class CreateLoanRequest {
    @NotNull
    private Long clientId;
    @Positive
    private BigDecimal amount;
    @PositiveOrZero
    private BigDecimal interestRate;
    @Positive
    private int termInMonths;

    public CreateLoanRequest(){};

    public CreateLoanRequest(BigDecimal amount, Long clientId, BigDecimal interestRate, int termInMonths) {
        this.amount = amount;
        this.clientId = clientId;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(int termInMonths) {
        this.termInMonths = termInMonths;
    }
}
