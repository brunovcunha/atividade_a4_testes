package com.iftm.client.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.iftm.client.entities.Client;

@DataJpaTest
public class ClientRepositoryTest {
    
    @Autowired
    private ClientRepository repository;

    @Test
    public void testFindClientByDateBetween() {
        Instant dataInicio = Instant.parse("1996-12-23T07:00:00Z");
        Instant dataTermino = Instant.now();
        List<Client> clients = repository.findClientByBirthDateBetween(dataInicio, dataTermino);

        assertEquals(4, clients.size());

    }
}
