package com.example.please.controller.viewcontroller;

import com.example.please.entity.Client;
import com.example.please.entity.Role;
import com.example.please.service.inter.InterClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private InterClientService clientService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String mainPage(){
        return "start-page";
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addClient(Client client){
        Client clientFromDb = clientService.findByFirstnameAndLastName(client.getFirstName(),client.getLastName());

        if(clientFromDb != null){
            return "redirect:/registration";
        }
        client.setRoleSet(Collections.singleton(Role.CLIENT));
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientService.save(client);

        return "redirect:/login";
    }
}
