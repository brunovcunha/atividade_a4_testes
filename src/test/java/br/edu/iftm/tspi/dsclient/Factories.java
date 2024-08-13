package br.edu.iftm.tspi.dsclient;

import com.github.javafaker.Faker;

import br.edu.iftm.tspi.dsclient.entities.Client;

public class Factories {

    private static Faker faker = new Faker();

    public static Client client() {
        return new Client(
                null,
                faker.lordOfTheRings().character(),
                faker.number().digits(11),
                faker.number().randomDouble(3, 1000, 100000),
                faker.date().birthday().toInstant(),
                faker.number().randomDigit());
    }

}
