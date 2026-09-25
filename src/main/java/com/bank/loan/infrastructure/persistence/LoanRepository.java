package com.bank.loan.infrastructure.persistence;

import com.bank.loan.domain.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("""
        SELECT l
        FROM Loan l
        JOIN FETCH l.client
        WHERE l.id = :id
    """)
    Optional<Loan> findByIdWithClient(@Param("id") Long id);

    List<Loan> findAllByOrderByCreatedAtDesc();
}
