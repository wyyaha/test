package com.test.service;

import com.test.domain.Account;

import java.util.List;

public interface IAccountService {
    public List<Account> findAllAccount ();

    public Account findAccountById(Integer accountId);

    public void saveAccount(Account account);

    public void updateAccount(Account account);

    public void deleteAccount(Integer accountId);
}
