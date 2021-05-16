package com.example.please.service;

import com.example.please.entity.Client;
import com.example.please.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    public List<Client> findAll(){
        return clientRepo.findAll();
    }

    public Optional<Client> findById(int theId){
        return clientRepo.findById(theId);
    }

    public void save(Client client){
        clientRepo.save(client);
    }

}
