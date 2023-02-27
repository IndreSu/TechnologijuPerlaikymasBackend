package lt.tehcin.myProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.tehcin.myProject.model.Client;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

    private String name;

    private Double weight;

    private Integer sector;

    private Client client;


}
