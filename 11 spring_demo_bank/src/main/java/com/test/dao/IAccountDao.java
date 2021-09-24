package com.test.dao;

import com.test.domain.Account;

import java.util.List;

public interface IAccountDao {
    public List<Account> findAllAccount ();

    public Account findAccountById(Integer accountId);

    public void saveAccount(Account account);

    public void updateAccount(Account account);

    public void deleteAccount(Integer accountId);

    Account findAccountByName(String accountName);
}
