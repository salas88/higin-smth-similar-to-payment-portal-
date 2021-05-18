package com.example.please.service;

import com.example.please.entity.Account;
import com.example.please.repo.AccountRepo;
import com.example.please.service.inter.InterAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements InterAccountService {
    
    @Autowired
    private AccountRepo accountRepo;
    
    @Override
    public Optional<Account> findById(int theId) {
        return accountRepo.findById(theId);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepo.save(account);
    }
}
