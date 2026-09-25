package com.bank.loan.domain.model;

import com.bank.loan.domain.exception.InvalidLoanException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal interestRate;

    @Column(nullable = false)
    private int termInMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status = LoanStatus.PENDING;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    protected Loan() {
    }

    public Loan(
            Client client,
            BigDecimal amount,
            BigDecimal interestRate,
            int termInMonths
    ) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidLoanException(
                    "Loan amount must be greater than zero"
            );
        }

        if (interestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidLoanException(
                    "Loan interest rate must be greater or equal than zero"
            );
        }

        if (termInMonths <= 0) {
            throw new InvalidLoanException(
                    "Loan term in months must be greater than zero"
            );
        }

        this.client = client;
        this.amount = amount;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
    }

    public void update(
            BigDecimal amount,
            BigDecimal interestRate,
            int termInMonths
    ) {

        if (status != LoanStatus.PENDING) {
            throw new InvalidLoanException(
                    "Only pending loans can be updated"
            );
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidLoanException(
                    "Loan amount must be greater than zero"
            );
        }

        if (interestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidLoanException(
                    "Interest rate cannot be negative"
            );
        }

        if (termInMonths <= 0) {
            throw new InvalidLoanException(
                    "Loan term must be greater than zero"
            );
        }

        this.amount = amount;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
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

    public LoanStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void approve() {

        if (status != LoanStatus.PENDING) {
            throw new InvalidLoanException(
                    "Only pending loans can be approved"
            );
        }

        status = LoanStatus.APPROVED;
    }

    public void reject() {

        if (status != LoanStatus.PENDING) {
            throw new InvalidLoanException(
                    "Only pending loans can be rejected"
            );
        }

        status = LoanStatus.REJECTED;
    }
}