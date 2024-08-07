package br.edu.iftm.tspi.dsclient.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.edu.iftm.tspi.dsclient.entities.Client;
import jakarta.persistence.EntityNotFoundException;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository repository;

    // Bruno Vieira
    @Test
    @DisplayName("Esse teste verifica se o metodo findByName retorna um cliente com um nome existente na base de dados")
    public void testFindByName() {
        List<Client> clients = repository.findByName("Conceição Evaristo");

        assertEquals(1, clients.size());
        assertEquals("Conceição Evaristo", clients.get(0).getName());
    }

    // Bruno Vieira
    @Test
    @DisplayName("Testar a busca de cliente por nome específico não existente")
    public void testFindByNameNonExisting() {
        List<Client> clients = repository.findByName("NonExisting Name");

        assertEquals(0, clients.size());
    }

    // Bruno Vieira
    @Test
    @DisplayName("Testar a exclusao de um cliente por id")
    public void testDeleteClienteExistente() {

        Long clienteId = (long) 1;

        repository.deleteById(clienteId);

        Optional<Client> clienteDeletado = repository.findById(clienteId);
        assertFalse(clienteDeletado.isPresent());
    }

    // Bruno Vieira
    @Test
    @DisplayName("Testa o findAll para retornar todos os clientes que estão na base de dados")
    public void testFindAll() {

        List<Client> clientes = repository.findAll();

        assertNotNull(clientes);
        assertEquals(12, clientes.size());
    }

    @Test
    public void testFindClientByDateBetween() {
        Instant dataInicio = Instant.parse("1996-12-23T07:00:00Z");
        Instant dataTermino = Instant.now();
        List<Client> clients = repository.findClientByBirthDateBetween(dataInicio, dataTermino);

        assertEquals(4, clients.size());

    }

    @Test
    public void testFindByNameStartingWithExistingPrefix() {
        List<Client> clients = repository.findByNameStartingWith("Conceição");
        assertEquals(1, clients.size());
        assertEquals("Conceição Evaristo", clients.get(0).getName());
    }

    @Test
    public void testFindByNameStartingWithNonExistingPrefix() {
        List<Client> clients = repository.findByNameStartingWith("NonExisting");
        assertEquals(0, clients.size());
    }

    @Test
    public void testFindByNameEndingWithExistingSuffix() {
        List<Client> clients = repository.findByNameEndingWith("Silva");
        assertEquals(1, clients.size());
        assertEquals("Conceição Silva", clients.get(0).getName());
    }

    @Test
    public void testFindByNameEndingWithNonExistingSuffix() {
        List<Client> clients = repository.findByNameEndingWith("NonExisting");
        assertEquals(0, clients.size());
    }

    @Test
    public void testFindByAgeSpecific() {
        Client client1 = new Client(1L, "Lázaro Ramos", "10619244881", 2500.0, Instant.parse("1996-12-23T07:00:00Z"),
                2);
        repository.save(client1);

        Instant now = Instant.now();
        Instant birthDateFrom = now.minus(27, ChronoUnit.YEARS);
        Instant birthDateTo = now.minus(26, ChronoUnit.YEARS);

        List<Client> clients = repository.findByBirthDateBetween(birthDateFrom, birthDateTo);
        assertEquals(1, clients.size());
        assertEquals("Lázaro Ramos", clients.get(0).getName());
    }

    @Test
    public void testFindByAgeRange() {
        Client client1 = new Client(1L, "Djamila Ribeiro", "10619244884", 4500.0, Instant.parse("1975-11-10T07:00:00Z"),
                1);
        Client client2 = new Client(2L, "Gilberto Gil", "10419344882", 2500.0, Instant.parse("1949-05-05T07:00:00Z"),
                4);

        repository.saveAll(List.of(client1, client2));

        Instant now = Instant.now();
        Instant birthDateFrom = now.minus(80, ChronoUnit.YEARS);
        Instant birthDateTo = now.minus(40, ChronoUnit.YEARS);

        List<Client> clients = repository.findByBirthDateBetween(birthDateFrom, birthDateTo);
        assertEquals(2, clients.size());
        assertTrue(clients.stream().anyMatch(c -> c.getName().equals("Djamila Ribeiro")));
        assertTrue(clients.stream().anyMatch(c -> c.getName().equals("Gilberto Gil")));
    }

    @Test
    public void testFindByIncomeBetweenExistingRange() {
        List<Client> clients = repository.findByIncomeBetween(1500.0, 5000.0);
        assertEquals(3, clients.size());
        assertEquals("Conceição Evaristo", clients.get(0).getName());
        assertEquals("Lázaro Ramos", clients.get(1).getName());
        assertEquals("Clarice Lispector", clients.get(2).getName());
    }

    @Test
    public void testFindByIncomeBetweenNonExistingRange() {
        List<Client> clients = repository.findByIncomeBetween(8000.0, 10000.0);
        assertEquals(1, clients.size());
    }

}
