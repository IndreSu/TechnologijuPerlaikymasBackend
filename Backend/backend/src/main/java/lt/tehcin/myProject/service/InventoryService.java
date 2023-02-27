package lt.tehcin.myProject.service;

import lt.tehcin.myProject.exception.ClientValidationException;
import lt.tehcin.myProject.repository.InventoryRepository;
import lt.tehcin.myProject.repository.ClientRepository;
import lt.tehcin.myProject.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ClientRepository clientRepository;

    public InventoryService(InventoryRepository inventoryRepository,
                            ClientRepository clientRepository) {
        this.inventoryRepository = inventoryRepository;
        this.clientRepository = clientRepository;
    }

    public List<Inventory> getAllInventory() {

        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getById(Long id) {
        return inventoryRepository.findById(id);
    }

    public Inventory addInventory(Inventory inventory) {

        return inventoryRepository.save(inventory);
    }


    public Inventory addInventoryToClient(Long inventoryId, Long clientId) {
        var existingInventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ClientValidationException("Inventory does not exist",
                "id", "Inventory not found", inventoryId.toString()));

        var existingClient = clientRepository.findById(clientId).orElseThrow(() -> new ClientValidationException("Client does not exist",
                "id", "Client not found", clientId.toString()));

        existingInventory.setClient(existingClient);

        return inventoryRepository.save(existingInventory);
    }

    public boolean deleteById(Long id) {
        try {
            inventoryRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException exception) {
            return false;
        }
    }

}
