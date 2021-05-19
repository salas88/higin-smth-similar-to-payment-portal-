package com.example.please.repo;

import com.example.please.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    Client findByFirstName(String name);
    Client findByFirstNameAndLastName(String firstname, String lastname);
}
