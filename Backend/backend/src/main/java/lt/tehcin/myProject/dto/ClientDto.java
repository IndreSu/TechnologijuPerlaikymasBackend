package lt.tehcin.myProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.tehcin.myProject.model.ClientType;
//import lt.tehcin.myProject.model.ClientType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private String name;

    private String surname;

    private String dateOfBirth;

    private ClientType type;


}
