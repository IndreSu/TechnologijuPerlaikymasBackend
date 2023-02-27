package lt.tehcin.myProject.ControllerTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.tehcin.myProject.controller.ClientController;
import lt.tehcin.myProject.dto.ClientDto;
import lt.tehcin.myProject.model.Client;

import lt.tehcin.myProject.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    @InjectMocks
    ClientController clientController;

    @Mock
    ClientService clientService;

    @Mock
    Client client;

    @Mock
    ClientDto clientDto;

    private static final long Id = 1;

    @Test
    public void viewClientByIdTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/1")
        ).andExpect(status().isOk()).andReturn();
        ClientDto result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<ClientDto>() {
        });
        Assertions.assertEquals(result.getName(), "Jonas","Get client by Id should return client with correct name");
    }

    @Test
    public void getAllClientsTest(){
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        when(clientService.getAllClients()).thenReturn(clients);
        Assertions.assertEquals(clientController.getAllClients().size(), clients.size());
    }


}
