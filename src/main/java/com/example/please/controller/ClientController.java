package com.example.please.controller;

import com.example.please.entity.Account;
import com.example.please.entity.Client;
import com.example.please.entity.Payment;
import com.example.please.error.ClientNotFoundException;
import com.example.please.error.ClientRestExceptionHandler;
import com.example.please.error.PaymentRequiredException;
import com.example.please.service.ClientService;
import com.example.please.service.PaymentService;
import com.example.please.utils.EntityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private PaymentService paymentService;


    @PostMapping("/add")
    public ResponseEntity addNewClient(@RequestBody Client client){

        try {
            clientService.save(client);

            return new ResponseEntity(
                    new HashMap<String, Integer>(){{
                        put("client_id",client.getId());}},
                    HttpStatus.CREATED);
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

    // I think this method is done
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/clients/payment",
            consumes={"application/json", "application/xml"},
            produces={"application/json", "application/xml"})
    public HttpEntity addPayment(@RequestBody Payment payment){
           try {
               if(paymentService.createPayment(payment)){
                   return new HttpEntity(new HashMap<String,Integer>()
                   {{
                       put("payment_id", payment.getId());
                   }});
               }
           } catch (NoSuchElementException e){
               return new ClientRestExceptionHandler().handlerException(e);
           }
        return new ClientRestExceptionHandler().handlerException(new PaymentRequiredException("недостаточно средст"));
    }


    @PostMapping("/clients/payments")
    @ResponseStatus(HttpStatus.OK)
    public Object addNewPayments(@RequestBody Payment... payment){

        List<ResponseEntity> paymentList = Arrays.stream(payment)
                .map(el -> {
                    try {
                        if(paymentService.createPayment(el)){
                            return ResponseEntity.ok().build();
                        }else {
                            return new ResponseEntity(
                             HttpStatus.UNPROCESSABLE_ENTITY
                            );
                        }
                    } catch (PaymentRequiredException e) {
                        return new ClientRestExceptionHandler().handlerException(e);
                    }})
                .collect(Collectors.toList());

        boolean b = paymentList
                .stream()
                .allMatch(el -> el.getStatusCode().value() == 402);

        return b ?
                new ClientRestExceptionHandler().handlerException(
                        new PaymentRequiredException(
                                "недостаточно средст у всех запросов")) : paymentList;
    };

}
