package com.bank.loan.domain.exception;

public class InvalidLoanException extends RuntimeException {

    public InvalidLoanException(String message) {
        super(message);
    }
}