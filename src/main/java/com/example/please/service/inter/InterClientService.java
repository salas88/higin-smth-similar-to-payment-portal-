package com.example.please.service.inter;

import com.example.please.entity.Client;

import java.util.Optional;

public interface InterClientService {
    Client findById(int theId);
    void save(Client client);
    Client findByClientName(String name);
    Client findByFirstnameAndLastName(String first, String last);
}
