package com.example.please.controller;

import com.example.please.entity.Account;
import com.example.please.entity.Client;
import com.example.please.entity.Payment;
import com.example.please.error.AccountNotFoundException;
import com.example.please.error.ControllerAdvisor;
import com.example.please.error.NoDateFoundException;
import com.example.please.error.UnprocessableEntityException;
import com.example.please.service.AccountService;
import com.example.please.service.ClientService;
import com.example.please.service.PaymentService;
import com.example.please.utils.PaymentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientRestController {

    Logger logger = LoggerFactory.getLogger(ClientRestController.class);

    @Autowired
    private ClientService clientService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ControllerAdvisor controllerAdvisor;

//    @Autowired
//    private ClientJdbcDto clientClientDto;
//
//
//    // костыль то я придумал, но достаю только один вложеный объект вместо массива
//    @GetMapping("/test")
//    public List<Account> get(){
//        List<Client> allClient = clientClientDto.findAll();
//
//        List<Account> collect = allClient.stream().flatMap(el -> el.getAccounts().stream()).collect(Collectors.toList());
//
//        List<Account> collect1 = collect.stream().map(el -> clientClientDto.findAccById(el.getId())).collect(Collectors.toList());
//
//        return collect1;
//    }


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/clients/{clientId}",
            consumes= MediaType.ALL_VALUE
            ,produces={"application/json", "application/xml"}
    )
    public ResponseEntity<Object> getClientsAccount(@PathVariable int clientId, WebRequest request){

        List<Account> allAcc  = accountService.findAll()
                    .stream()
                    .filter(el -> el.getClient().getId() == clientId)
                    .collect(Collectors.toList());


        if ( allAcc.isEmpty()){
            return controllerAdvisor.handlerExceptionAccNotFound(new AccountNotFoundException(clientId), request);
        }
        return new ResponseEntity(allAcc, HttpStatus.OK);
    }


    // create new Client into method signature transfer Client json or xml
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/clientAdd",
            consumes= MediaType.ALL_VALUE
            ,produces={"application/json", "application/xml"})
    public ResponseEntity addNewClient(@RequestBody Client client){
        try {
            clientService.save(client);

            return new ResponseEntity(
                    new HashMap<String, Integer>(){{
                        put("client_id",client.getId());}},
                    HttpStatus.CREATED);
        }catch (Exception e){
            return new ControllerAdvisor().handlerException(e);
        }
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
               if(paymentService.savePayment(payment)){
                   return new HttpEntity(new HashMap<String,Integer>()
                   {{
                       put("payment_id", payment.getId());
                   }});
               }
           } catch (NoSuchElementException e){
               return new ControllerAdvisor().handlerException(e);
           }
        return new ControllerAdvisor()
                .handlerException(new UnprocessableEntityException("недостаточно средст"));
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/clients/payments",
            consumes={"application/json", "application/xml"},
            produces={"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public Object addNewPayments(@RequestBody Payment... payment){

        List<ResponseEntity> paymentList = Arrays.stream(payment)
                .map(el -> {
                    try {
                        if(paymentService.savePayment(el)){
                            return ResponseEntity.ok().build();
                        }else {
                            return new ResponseEntity(
                             HttpStatus.UNPROCESSABLE_ENTITY
                            );
                        }
                    } catch (UnprocessableEntityException e) {
                        return new ControllerAdvisor().handlerException(e);
                    }})
                .collect(Collectors.toList());

        boolean b = paymentList
                .stream()
                .allMatch(el -> el.getStatusCode().value() == 422);

        return b ?
                        new UnprocessableEntityException(
                                "недостаточно средст у всех запросов") : paymentList;
    };


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET,
                    value = "/clients/payments/history",
                    consumes={"application/json", "application/xml"},
                    produces={"application/json", "application/xml"})
    public Object getHistoryPayments(@RequestBody Payment payment){

        List<PaymentDto> paymentDtoList = paymentService.findAll(payment);


        if(paymentDtoList.isEmpty())
           return new NoDateFoundException();

        return paymentDtoList;
    }

}
