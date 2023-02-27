package lt.tehcin.myProject.ServiseTests;

import lt.tehcin.myProject.repository.ClientRepository;
import lt.tehcin.myProject.service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientServiceTest {
    @InjectMocks
    ClientService clientService;
    @Mock
    ClientRepository clientRepository;

    private static final long Id = 1;
    @Test
    public void viewClientByIdTest(){
        clientService.getById(Id);
        verify(clientRepository).findById(Id);
    }

    @Test
    private void getAllClientsTests() {
        clientService.getAllClients();
        verify(clientRepository).findAll();
    }
}

