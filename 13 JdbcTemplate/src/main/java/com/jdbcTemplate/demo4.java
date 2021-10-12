package com.jdbcTemplate;

import com.dao.IAccountDao;
import com.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class demo4 {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountDao accountDao = ac.getBean("accountDao", IAccountDao.class);
        System.out.println(accountDao.findAccountById(1));
        System.out.println(accountDao.findAccountByName("ccc"));
        Account account = new Account();
        account.setName("段哈");
        account.setMoney(6000f);
        account.setId(13);
        accountDao.updateAccount(account);
    }

}
