package com.example.please.controller.viewcontroller;

import com.example.please.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainViewController {



    private String baseUri = "http://localhost:8080/api/";

    @GetMapping("/client")
    public String clientAccount(Model theModel, @RequestParam("id") int clientId){

        ResponseEntity<Account[]> responseEntity =
                new RestTemplate().getForEntity(baseUri + "clients/" + clientId, Account[].class);
        Account[] accountArray = responseEntity.getBody();


        theModel.addAttribute("account", accountArray);

        return "account";
    }

}
