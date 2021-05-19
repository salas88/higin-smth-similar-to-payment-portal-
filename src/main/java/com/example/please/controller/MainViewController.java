package com.example.please.controller;

import com.example.please.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class MainViewController {



    private String baseUri = "http://localhost:8080/api/";

    @GetMapping("/client")
    public String clientAccount(Model theModel, @RequestParam("id") int clientId){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Account[]> responseEntity =
                restTemplate.getForEntity(baseUri + "clients/" + clientId, Account[].class);
        Account[] accountArray = responseEntity.getBody();


        theModel.addAttribute("account", accountArray);

        return "account";
    }

}
