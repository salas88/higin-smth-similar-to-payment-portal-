package com.example.please.controller;

import com.example.please.entity.Account;
import com.example.please.entity.Client;
import com.example.please.entity.Payment;
import com.example.please.error.ClientNotFoundException;
import com.example.please.error.ClientRestExceptionHandler;
import com.example.please.service.ClientService;
import com.example.please.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private PaymentService paymentService;

    //check this method for bad request
    @PostMapping("/add")
    public ResponseEntity addNewClient(@RequestBody Client client){

        try {
            clientService.save(client);

            return new ResponseEntity(new HashMap<String, Integer>(){{
                                            put("client_id",client.getId());}}
                    ,                 HttpStatus.CREATED);
        }catch (Exception e){
            return new ClientRestExceptionHandler().handlerException(e);
        }


    }

    @GetMapping("/clients/{clientId}")
    public List<Account> getClientsAccount(@PathVariable int clientId){
        Optional<Client> client = clientService.findById(clientId);

        if(client.isEmpty()){
            throw new ClientNotFoundException("Client is not found with id: " + clientId);
        }

        return client.get().getAccounts();
    }


    @PostMapping("/clients/payments")
    public List<ResponseEntity> addNewPayment(@RequestBody Payment... payment){



            List<ResponseEntity> lists =  Arrays.stream(payment).map( el -> {
                try{
                    if(paymentService.createPayment(el)){
                        return new ResponseEntity(new HashMap<String,Integer>(){{
                            put("payment_id", el.getId());}},
                                HttpStatus.CREATED);
                    } else{
                        return new ResponseEntity(new HashMap<String, String>(){{
                            put("account_id",el.getSourceAccId().toString());
                            put("name_client", clientService.findById(el.getSourceAccId()).get().getFirstName());
                            put("message", "Недостаточно средств");}},
                                HttpStatus.PAYMENT_REQUIRED);
                    }
                } catch (Exception e){
                    return new ClientRestExceptionHandler().handlerException(e);
                }

            }).collect(Collectors.toList());

        return lists;
    };

}
