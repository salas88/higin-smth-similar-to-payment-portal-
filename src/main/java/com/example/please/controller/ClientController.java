package com.example.please.controller;
import com.example.please.entity.Account;
import com.example.please.entity.Client;
import com.example.please.error.ClientNotFoundException;
import com.example.please.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService;

    //check this method for bad request
    @PostMapping("/add")
    public ResponseEntity addNewClient(@RequestBody Client client){
        clientService.save(client);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/clients")
    public List<Client>getAllClients(){
        List<Client> clients = clientService.findAll();
        return clients;
    }

    @GetMapping("/clients/{clientId}")
    public List<Account> getClientsAccount(@PathVariable int clientId){
        Optional<Client> client = clientService.findById(clientId);

        if(client.isEmpty()){
            throw new ClientNotFoundException("Client is not found " + clientId);
        }

        return client.get().getAccounts();

    }
}
