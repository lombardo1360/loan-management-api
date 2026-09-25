package com.bank.loan.application.dto;

import com.bank.loan.domain.model.LoanStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanResponse {
    private Long id;
    private BigDecimal amount;
    private BigDecimal interestRate;
    private int termInMonths;
    private LoanStatus status;
    private LocalDateTime createdAt;
    private Long clientId;

    public LoanResponse(Long id, BigDecimal amount, BigDecimal interestRate, int termInMonths, LoanStatus status, LocalDateTime createdAt, Long clientId) {
        this.id = id;
        this.amount = amount;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.status = status;
        this.createdAt = createdAt;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
