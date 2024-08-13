package br.edu.iftm.tspi.dsclient.resources;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.edu.iftm.tspi.dsclient.Factories;
import br.edu.iftm.tspi.dsclient.services.ClientService;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService service;

    @Test
    void testDelete() {

    }

    @Test
    void testFindAll() {

    }

    @Test
    void testFindById() {

    }

    @Test
    void testInsert() {

        var client = Factories.client();
        System.out.println(client.toString());

    }

    @Test
    void testUpdate() {

    }
}
