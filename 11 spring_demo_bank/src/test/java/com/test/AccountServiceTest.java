package com.test;

import com.test.domain.Account;
import com.test.service.IAccountService;
import com.test.utils.TransactionManager;
import config.SpringConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/*
@Runwith 使用junit提供的注解把原有的main方法替换成spring提供的
@ContextConfiguration 告知spring的运行器，spring和ioc创建是基于xml还是注解，并且说明位置
   locations:使用classpath是基于xml,使用classes是基于注解
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringConfiguration.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {
   @Autowired
   @Qualifier("proxyAccountService")
    private IAccountService as;
   

//    @Before
//    public void init() {
//        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
//        ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        as = ac.getBean("accountService", IAccountService.class);
//    }

    @Test
    public void testFindAll () {
        List<Account> accounts= as.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
         }
    }

    @Test
    public void testFindOne () {
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave () {
        Account account = new Account();
        account.setName("李强都和");
        account.setMoney(20F);
        as.saveAccount(account);
    }

    @Test
    public void testUpdate () {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        Account account = as.findAccountById(4);
        account.setName("ddd");
        account.setMoney(12.3F);
        as.updateAccount(account);
    }

    @Test
    public void testDelete () {
        as.deleteAccount(4);
    }

    @Test
    public void testTransfer(){
        as.transfer("aaa", "bbb", 100F);
    }
}
