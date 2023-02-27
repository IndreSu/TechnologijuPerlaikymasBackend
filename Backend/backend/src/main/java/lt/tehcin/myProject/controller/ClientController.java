package lt.tehcin.myProject.controller;

import lt.tehcin.myProject.dto.ClientDto;
import lt.tehcin.myProject.dto.mapper.ClientMapper;
import lt.tehcin.myProject.model.Client;
import lt.tehcin.myProject.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Validated
@RequestMapping("/api/v1/clients")
public class ClientController {

    public Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    ClientService clientService;

    public ClientController(ClientService clientService)
    {
        this.clientService = clientService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ClientDto> getAllClients() {

        return clientService.getAllClients().stream().map(ClientMapper::toClientDto).collect(toList());
    }

    @GetMapping("/{clientId}")
    public Optional<Client> getClient(@PathVariable Long clientId)
    {

        return clientService.getById(clientId);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientDto clientDto) {

        return ResponseEntity.ok(clientService.createClient(ClientMapper.toClient(clientDto)));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long clientId) {

        boolean deleted = clientService.deleteById(clientId);

        if(deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
