package com.example.please.service;

import com.example.please.entity.Account;
import com.example.please.entity.Payment;
import com.example.please.repo.AccountRepo;
import com.example.please.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PaymentRepo paymentRepo;

    public boolean createPayment(Payment payment) throws NoSuchElementException{

        Account accountSource = accountRepo.findById(payment.getSourceAccId()).get();
        Account accountDest = accountRepo.findById(payment.getDestAccId()).get();
        Double accountSourceBalance = accountSource.getBalance();
        Double accountDestBalance = accountDest.getBalance();
        Double amount = payment.getAmount();

            if(accountSourceBalance >= amount){
               accountSource.setBalance((accountSourceBalance-amount));
               accountDest.setBalance((accountDestBalance+amount));
               accountRepo.save(accountSource);
               accountRepo.save(accountDest);
               paymentRepo.save(payment);
               return true;
            } else
                return false;

    }
}
