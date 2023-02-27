package lt.tehcin.myProject.dto.mapper;

import lt.tehcin.myProject.dto.ClientDto;
import lt.tehcin.myProject.model.Client;

public class ClientMapper {

        public static ClientDto toClientDto(Client client) {
            var clientDto = new ClientDto();

            clientDto.setName(client.getName());
            clientDto.setSurname(client.getSurname());
            clientDto.setDateOfBirth(client.getDateOfBirth());
            clientDto.setType(client.getType());


            return clientDto;
        }

        public static Client toClient(ClientDto clientDto) {
            var client = new Client();

            client.setName(clientDto.getName());
            client.setSurname(clientDto.getSurname());
            client.setDateOfBirth(clientDto.getDateOfBirth());
            client.setType(clientDto.getType());

            return client;
        }
}
