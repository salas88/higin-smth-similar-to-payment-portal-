package com.example.please.service;

import com.example.please.entity.Client;
import com.example.please.repo.ClientRepo;
import com.example.please.service.inter.InterClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService implements InterClientService, UserDetailsService {

    @Autowired
    private ClientRepo clientRepo;

    public Optional<Client> findById(int theId){
        return clientRepo.findById(theId);
    }

    public void save(Client client){
        clientRepo.save(client);
    }

    @Override
    public Client findByClientName(String name) {
        return clientRepo.findByFirstName(name);
    }

    @Override
    public Client findByFirstnameAndLastName(String first, String last) {
        return clientRepo.findByFirstNameAndLastName(first,last);
    }


    @Override
    public UserDetails loadUserByUsername(String firstname) throws UsernameNotFoundException {
        return clientRepo.findByFirstName(firstname);
    }

}
