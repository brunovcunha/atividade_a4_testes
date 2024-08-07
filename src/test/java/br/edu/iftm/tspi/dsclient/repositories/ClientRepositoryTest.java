package br.edu.iftm.tspi.dsclient.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.edu.iftm.tspi.dsclient.entities.Client;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository repository;

    // João
    @Test
    public void testFindExistingNameIgnoringCase() {
        List<Client> clients = repository.findByNameContainingIgnoreCase("conceição evaristo");
        assertEquals(1, clients.size());
        assertEquals("Conceição Evaristo", clients.get(0).getName());
    }

    @Test
    public void testFindInexistingNameIgnoringCase() {
        List<Client> clients = repository.findByNameContainingIgnoreCase("João Vitorino");
        assertEquals(0, clients.size());
    }

    @Test
    public void testFindListOfExistingNamesIgnoringCase() {
        List<Client> clients = repository.findByNameContainingIgnoreCase("ra");
        assertEquals(3, clients.size());
        assertEquals("Lázaro Ramos", clients.get(0).getName());
        assertEquals("Jose Saramago", clients.get(1).getName());
        assertEquals("Yuval Noah Harari", clients.get(2).getName());
    }

    @Test
    public void testFindNonExistingNameIgnoringCase() {
        List<Client> clients = repository.findByNameContainingIgnoreCase("");
        assertEquals(12, clients.size());
    }

    // Fim - João
    
}
