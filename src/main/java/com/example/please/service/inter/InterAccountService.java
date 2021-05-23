package com.example.please.service.inter;

import com.example.please.entity.Account;

import java.util.List;
import java.util.Optional;

public interface InterAccountService {
    Account findById(int theId);
    void saveAccount(Account account);
    List<Account> findAll();
}
