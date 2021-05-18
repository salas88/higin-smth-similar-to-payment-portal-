package com.example.please.service.inter;

import com.example.please.entity.Account;

import java.util.Optional;

public interface InterAccountService {
    Optional<Account> findById(int theId);
    void saveAccount(Account account);
}
