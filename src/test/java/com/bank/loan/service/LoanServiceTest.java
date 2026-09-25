package com.bank.loan.service;

import com.bank.loan.application.service.LoanService;
import com.bank.loan.domain.model.Loan;
import com.bank.loan.application.dto.CreateLoanRequest;
import com.bank.loan.repository.ClientRepository;
import com.bank.loan.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.bank.loan.domain.model.Client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private LoanRepository loanRepository;

    private LoanService loanService;
    @BeforeEach
    void setUp() {
        loanService = new LoanService(
                clientRepository,
                loanRepository
        );
    }

    @Test
    void shouldCreateLoanForAdultClient() {

        Client client = new Client(
                1L,
                "123456",
                "Juan",
                "Perez",
                "juan@email.com",
                "3001234567",
                LocalDate.of(1995, 5, 20)
        );

        when(clientRepository.findById(1L))
                .thenReturn(Optional.of(client));

        when(loanRepository.save(any(Loan.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateLoanRequest request = new CreateLoanRequest(
                new BigDecimal("10000000"),
                1L,
                new BigDecimal("2.5"),
                12
        );
        Loan result = loanService.createLoan(request);

        assertNotNull(result);
    }
}