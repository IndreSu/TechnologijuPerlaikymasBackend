package lt.tehcin.myProject.dto.mapper;

import lt.tehcin.myProject.dto.InventoryDto;
import lt.tehcin.myProject.model.Inventory;

public class InventoryMapper {

    public static InventoryDto toInventoryDto(Inventory inventory) {
        var inventoryDto = new InventoryDto();

        inventoryDto.setName(inventory.getName());
        inventoryDto.setWeight(inventory.getWeight());
        inventoryDto.setSector(inventory.getSector());
        inventoryDto.setClient(inventory.getClient());

        return inventoryDto;
    }

    public static Inventory toInventory(InventoryDto inventoryDto) {
        var inventory = new Inventory();

        inventory.setName(inventoryDto.getName());
        inventory.setWeight(inventoryDto.getWeight());
        inventory.setSector(inventoryDto.getSector());
        inventory.setClient(inventoryDto.getClient());

        return inventory;
    }
}
