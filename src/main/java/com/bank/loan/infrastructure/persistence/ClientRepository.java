package com.bank.loan.infrastructure.persistence;

import com.bank.loan.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
