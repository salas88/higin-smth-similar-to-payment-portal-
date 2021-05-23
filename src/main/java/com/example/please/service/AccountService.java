package com.example.please.service;

import com.example.please.entity.Account;
import com.example.please.error.AccountNotFoundException;
import com.example.please.error.ClientNotFoundException;
import com.example.please.error.NoDateFoundException;
import com.example.please.repo.AccountRepo;
import com.example.please.service.inter.InterAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements InterAccountService {
    
    @Autowired
    private AccountRepo accountRepo;
    
    @Override
    public Account findById(int theId) {
        return accountRepo.findById(theId).orElseThrow(() -> new ClientNotFoundException(theId));
    }

    @Override
    public void saveAccount(Account account) {
        accountRepo.save(account);
    }

    @Override
    public List<Account> findAll() {

       List<Account> accounts = accountRepo.findAll();
       if(accounts.isEmpty()){
           throw new NoDateFoundException();
       }
       return accounts;
    }
}
