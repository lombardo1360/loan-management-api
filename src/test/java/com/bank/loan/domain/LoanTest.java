package com.bank.loan.domain;

import com.bank.loan.domain.model.Loan;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoanTest {

    @Test
    void shouldThrowExceptionWhenLoanAmountIsNegative() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Loan(
                        1L,
                        new BigDecimal("-1500"),
                        new BigDecimal("2000"),
                        12
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenLoanInterestRateIsNegative() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Loan(
                        1L,
                        new BigDecimal("2000"),
                        new BigDecimal("-5"),
                        12
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenTermInMonthsIsZero() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Loan(
                        1L,
                        new BigDecimal("1500"),
                        new BigDecimal("2000"),
            0
                )
        );
    }
}
