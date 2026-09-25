package com.bank.loan.api.exception;

import com.bank.loan.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleClientNotFound(
            ClientNotFoundException ex
    ) {
        return new ErrorResponse(
                404,
                ex.getMessage()
        );
    }

    @ExceptionHandler(LoanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleLoanNotFound(
            LoanNotFoundException ex
    ) {
        return new ErrorResponse(
                404,
                ex.getMessage()
        );
    }

    @ExceptionHandler(InvalidLoanException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidLoan(
            InvalidLoanException ex
    ) {
        return new ErrorResponse(
                400,
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(
            MethodArgumentNotValidException ex
    ) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error ->
                        error.getField() + ": " + error.getDefaultMessage()
                )
                .orElse("Validation error");

        return new ErrorResponse(
                400,
                message
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(
            UserNotFoundException ex
    ) {
        return new ErrorResponse(
                404,
                ex.getMessage()
        );
    }

    @ExceptionHandler(InvalidRoleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidRole(
            InvalidRoleException ex
    ) {
        return new ErrorResponse(
                400,
                ex.getMessage()
        );
    }
}