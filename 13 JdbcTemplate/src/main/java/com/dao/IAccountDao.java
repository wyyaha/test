package com.dao;

import com.domain.Account;

public interface IAccountDao {
    Account findAccountById(Integer accountId);

    Account findAccountByName(String name);

    void updateAccount(Account account);
}
