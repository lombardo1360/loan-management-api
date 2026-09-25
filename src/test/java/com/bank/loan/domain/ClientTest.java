package com.bank.loan.domain;

import com.bank.loan.domain.model.Client;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void shouldReturnTrueWhenClientIsAdult() {

        Client client = new Client(
                1L,
                "123456",
                "Juan",
                "Perez",
                "juan@email.com",
                "3001234567",
                LocalDate.of(1995, 5, 20)
        );

        boolean result = client.isAdult();

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenClientIsMinor() {

        Client client = new Client(
                2L,
                "654321",
                "Pedro",
                "Gomez",
                "pedro@email.com",
                "3009876543",
                LocalDate.of(2015, 5, 20)
        );

        boolean result = client.isAdult();

        assertFalse(result);
    }
}