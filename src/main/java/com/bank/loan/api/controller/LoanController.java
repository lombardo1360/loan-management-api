package com.bank.loan.api.controller;

import com.bank.loan.application.dto.CreateLoanRequest;
import com.bank.loan.application.dto.LoanResponse;
import com.bank.loan.application.dto.UpdateLoanRequest;
import com.bank.loan.application.service.LoanService;
import com.bank.loan.domain.model.Loan;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(
            @Valid @RequestBody CreateLoanRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(loanService.createLoan(request));
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoans() {

        return ResponseEntity.ok(
                loanService.getAllLoans()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getLoanById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                loanService.getLoanById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(
            @PathVariable Long id,
            @Valid @RequestBody UpdateLoanRequest request
    ) {

        return ResponseEntity.ok(
                loanService.updateLoan(id, request)
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLoan(
            @PathVariable Long id
    ) {

        loanService.deleteLoan(id);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Loan> approveLoan(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                loanService.approveLoan(id)
        );
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Loan> rejectLoan(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                loanService.rejectLoan(id)
        );
    }
}