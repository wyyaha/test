package com.test.factory;

import com.test.domain.Account;
import com.test.service.IAccountService;
import com.test.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class BeanFactory {
    private IAccountService accountService;

    private TransactionManager tsManager;

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public final void setTsManager(TransactionManager tsManager) {
        this.tsManager = tsManager;
    }

    public IAccountService getAccountService() {
        return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object rtValue = null;
                try {
                    tsManager.beginTransaction();
                    rtValue = method.invoke(accountService, args);
//                    List<Account> accounts = accountDao.findAllAccount();
                    tsManager.commit();
                    return rtValue;
                }catch (Exception e) {
                    tsManager.rollback();
                    throw new RuntimeException(e);
                } finally {
                    tsManager.release();
                }
            }
        });
    }
}
