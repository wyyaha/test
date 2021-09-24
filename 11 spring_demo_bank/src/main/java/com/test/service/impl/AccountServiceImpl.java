package com.test.service.impl;

import com.test.dao.IAccountDao;
import com.test.domain.Account;
import com.test.service.IAccountService;
import com.test.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
//    @Autowired
    private IAccountDao accountDao;

    private TransactionManager tsManager;
    public void setTsManager(TransactionManager tsManager) {
        this.tsManager = tsManager;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
//        try {
//            tsManager.beginTransaction();
            List<Account> accounts = accountDao.findAllAccount();
//            tsManager.commit();
            return accounts;
//        }catch (Exception e) {
//            tsManager.rollback();
//            throw new RuntimeException(e);
//        } finally {
//            tsManager.release();
//        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
//        try {
//            tsManager.beginTransaction();
            Account source = accountDao.findAccountByName(sourceName);
            Account target = accountDao.findAccountByName(targetName);
            source.setMoney(source.getMoney()-money);
            target.setMoney(target.getMoney()+money);
            accountDao.updateAccount(source);
//            int i = 1/0;
            accountDao.updateAccount(target);
//            tsManager.commit();
//        }catch (Exception e) {
//            tsManager.rollback();
//            e.printStackTrace();
////            throw new RuntimeException(e);
//        } finally {
//            tsManager.release();
//        }


    }
}
