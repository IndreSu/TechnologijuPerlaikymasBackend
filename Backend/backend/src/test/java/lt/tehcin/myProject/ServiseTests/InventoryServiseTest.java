package lt.tehcin.myProject.ServiseTests;

import lt.tehcin.myProject.model.Inventory;
import lt.tehcin.myProject.repository.InventoryRepository;
import lt.tehcin.myProject.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryServiseTest {

    @InjectMocks
    InventoryService inventoryService;
    @Mock
    InventoryRepository inventoryRepository;

    private static final long Id = 1;
    @Test
    public void viewInventoryByIdTest(){
        inventoryService.getById(Id);
        verify(inventoryRepository).findById(Id);
    }

    @Test
    private void getAllInventoryTests(){
        inventoryService.getAllInventory();
        verify(inventoryRepository).findAll();
    }

    @Test
    public void saveInventory(){
        Inventory inventory = mock(Inventory.class);
        inventoryService.addInventory(inventory);
        verify(inventoryRepository).save(inventory);
    }
}
