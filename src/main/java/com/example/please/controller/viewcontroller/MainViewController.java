package com.example.please.controller.viewcontroller;

import com.example.please.entity.Account;
import com.example.please.entity.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainViewController {

    Logger logger = LoggerFactory.getLogger(MainViewController.class);
    private String baseUri = "http://localhost:8080/api/";

    @GetMapping("/client/profile")
    public String mainPage(@AuthenticationPrincipal Client client,
                           Model theModel){

        theModel.addAttribute("firstName", client.getFirstName());
        theModel.addAttribute("lastName", client.getLastName());

        return "main-page";
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/client/accounts")
    public String clientAccount(Model theModel,
                                @RequestParam("id") int clientId,
                                @RequestHeader Map<String, String> header
                               ){
        String urlRequest = baseUri + "clients/" + clientId;

        RestTemplate restTemplate = new RestTemplate();
        List<Account> accounts;

        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(urlRequest, Object[].class);

        logger.info(responseEntity.getStatusCode().toString());

        try {

            Object[] objects = responseEntity.getBody();
            ObjectMapper mapper = new ObjectMapper();
            accounts = Arrays.stream(objects)
                    .map(ob -> mapper.convertValue(ob, Account.class))
                    .collect(Collectors.toList());
            theModel.addAttribute("account", accounts);
        } catch ( HttpStatusCodeException exception){

        }

        return "client/account";
    }



}
