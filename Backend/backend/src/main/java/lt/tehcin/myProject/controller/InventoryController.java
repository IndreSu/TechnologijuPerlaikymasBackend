package lt.tehcin.myProject.controller;

import lt.tehcin.myProject.dto.InventoryDto;
import lt.tehcin.myProject.dto.mapper.InventoryMapper;
import lt.tehcin.myProject.model.Inventory;
import lt.tehcin.myProject.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@Validated
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    public Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    InventoryService inventoryService;


    public InventoryController(InventoryService inventoryService) {

        this.inventoryService = inventoryService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Inventory> getAllInventory() {

        return inventoryService.getAllInventory();
    }

    @GetMapping("/{inventoryId}")
    public Optional<Inventory> getInventory(@PathVariable Long inventoryId) {

        return inventoryService.getById(inventoryId);
    }

    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody InventoryDto inventoryDto) {

      return ResponseEntity.ok(inventoryService.addInventory(InventoryMapper.toInventory(inventoryDto)));
}
    @PostMapping("/{inventoryId}/addToClient")
    @ResponseBody
    public Inventory addInventoryToClient(@PathVariable Long inventoryId, @RequestParam Long clientId) {
        return inventoryService.addInventoryToClient(inventoryId, clientId);
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable Long inventoryId) {
        boolean deleted = inventoryService.deleteById(inventoryId);

        if(deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
