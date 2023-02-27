package lt.tehcin.myProject.service;

import lt.tehcin.myProject.repository.ClientRepository;
import lt.tehcin.myProject.exception.ClientValidationException;
import lt.tehcin.myProject.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {

        return clientRepository.findAll();
    }

    public Optional<Client> getById(Long id) {

        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {

        if (existsByName(client.getName())) {
            throw new ClientValidationException("Client name must be unique", "name", "Name already exists", client.getName());
        }
        if (existsBySurname(client.getSurname())) {
            throw new ClientValidationException("Client surname must be unique", "surname", "Surname already exists", client.getSurname());
        }
        if (existsByDateOfBirth(client.getDateOfBirth())) {
            throw new ClientValidationException("Client date of bith must be unique", "date of birth", "Date of birth already exists", client.getSurname());
        }

        return clientRepository.save(client);
    }

    public boolean existsByName(String name) {
        return clientRepository.existsByName(name);
    }

    public boolean existsBySurname(String surname) {
        return clientRepository.existsBySurname(surname);
    }

    public boolean existsByDateOfBirth(String dateOfBirth) {
        return clientRepository.existsBySurname(dateOfBirth);
    }


    public boolean deleteById(Long id) {

        try {
            clientRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException exception) {
            return false;
        }
    }
}
