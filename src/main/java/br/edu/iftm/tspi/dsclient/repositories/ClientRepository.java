package br.edu.iftm.tspi.dsclient.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.dsclient.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findClientByBirthDateBetween(Instant dataInicio, Instant dataTermino);

    List<Client> findByNameStartingWith(String prefix);

    List<Client> findByNameEndingWith(String suffix);

    List<Client> findByBirthDateBetween(Instant startDate, Instant endDate);

    List<Client> findByIncomeBetween(Double minIncome, Double maxIncome);

    List<Client> findByName(String name);

}
