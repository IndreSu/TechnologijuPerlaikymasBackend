package lt.tehcin.myProject.ControllerTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.tehcin.myProject.controller.InventoryController;
import lt.tehcin.myProject.dto.InventoryDto;
import lt.tehcin.myProject.model.Inventory;
import lt.tehcin.myProject.service.InventoryService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    @InjectMocks
    InventoryController inventoryController;

    @Mock
    InventoryService inventoryService;

    @Mock
    Inventory inventory;

    @Mock
    InventoryDto inventoryDto;

    private static final long Id = 1;

    @Test
    public void viewInventoryByIdTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory/1")
        ).andExpect(status().isOk()).andReturn();
        InventoryDto result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<InventoryDto>() {
        });
        Assertions.assertEquals(result.getName(), "","Get inventory by Id should return inventory with correct name");
    }

    @Test
    public void getAllInventoryTest(){
        List<Inventory> inventories = new ArrayList<>();
        inventories.add(inventory);
        when(inventoryService.getAllInventory()).thenReturn(inventories);
        assertEquals(inventoryController.getAllInventory().size(), inventories.size());
    }

}
