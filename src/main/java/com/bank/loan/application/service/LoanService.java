package com.bank.loan.application.service;

import com.bank.loan.application.dto.UpdateLoanRequest;
import com.bank.loan.domain.exception.InvalidLoanException;
import com.bank.loan.domain.model.Client;
import com.bank.loan.domain.model.Loan;
import com.bank.loan.application.dto.CreateLoanRequest;
import com.bank.loan.application.dto.LoanResponse;
import com.bank.loan.domain.exception.ClientNotFoundException;
import com.bank.loan.domain.exception.LoanNotFoundException;
import com.bank.loan.domain.model.LoanStatus;
import com.bank.loan.infrastructure.persistence.ClientRepository;
import com.bank.loan.infrastructure.persistence.LoanRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoanService {

    private final ClientRepository clientRepository;
    private final LoanRepository loanRepository;

    public LoanService(
            ClientRepository clientRepository,
            LoanRepository loanRepository) {
        this.clientRepository = clientRepository;
        this.loanRepository = loanRepository;
    }

    @Transactional
    public Loan createLoan(CreateLoanRequest loanRequest) {
        Client client = this.clientRepository
                .findById(loanRequest.getClientId())
                .orElseThrow(() ->
                    new ClientNotFoundException("Client not found with id: "
                                        + loanRequest.getClientId()
                    )
                );

        if (!client.isAdult()){
            throw new InvalidLoanException(
                    "Client is not adult"
            );
        }

        Loan loan = new Loan(
                client,
                loanRequest.getAmount(),
                loanRequest.getInterestRate(),
                loanRequest.getTermInMonths()
        );

        return this.loanRepository.save(loan);
    }

    @Transactional(readOnly = true)
    public List<LoanResponse> getAllLoans() {

        return loanRepository
                .findAllByOrderByCreatedAtDesc()
                .stream()
                .map(loan -> new LoanResponse(
                        loan.getId(),
                        loan.getAmount(),
                        loan.getInterestRate(),
                        loan.getTermInMonths(),
                        loan.getStatus(),
                        loan.getCreatedAt(),
                        loan.getClient().getId()
                ))
                .toList();
    }


    @Transactional
    public Loan updateLoan(
            Long id,
            UpdateLoanRequest request
    ) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() ->
                        new LoanNotFoundException(
                                "Loan not found with id: " + id
                        )
                );

        loan.update(
                request.getAmount(),
                request.getInterestRate(),
                request.getTermInMonths()
        );

        return loanRepository.save(loan);
    }

    @Cacheable(
            value = "loanStatus",
            key = "#id"
    )
    @Transactional(readOnly = true)
    public LoanResponse getLoanById(Long id) {
        Loan loan=  this.loanRepository
                    .findByIdWithClient(id)
                    .orElseThrow(() ->
                            new LoanNotFoundException("Loan not found with id: "
                                    + id
                            )
                    );

        return new LoanResponse(
                loan.getId(),
                loan.getAmount(),
                loan.getInterestRate(),
                loan.getTermInMonths(),
                loan.getStatus(),
                loan.getCreatedAt(),
                loan.getClient().getId()
        );
    }

    @CacheEvict(
            value = "loanStatus",
            key = "#id"
    )
    @Transactional
    public Loan approveLoan(Long id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() ->
                        new LoanNotFoundException(
                                "Loan not found with id: " + id
                        )
                );

        loan.approve();

        return loanRepository.save(loan);
    }

    @CacheEvict(
            value = "loanStatus",
            key = "#id"
    )
    @Transactional
    public Loan rejectLoan(Long id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() ->
                        new LoanNotFoundException(
                                "Loan not found with id: " + id
                        )
                );

        loan.reject();

        return loanRepository.save(loan);
    }

    @Transactional
    public void deleteLoan(Long id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() ->
                        new LoanNotFoundException(
                                "Loan not found with id: " + id
                        )
                );

        if (loan.getStatus() != LoanStatus.PENDING) {
            throw new InvalidLoanException(
                    "Only pending loans can be deleted"
            );
        }

        loanRepository.delete(loan);
    }
}
