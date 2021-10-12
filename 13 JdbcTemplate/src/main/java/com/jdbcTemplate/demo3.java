package com.jdbcTemplate;

import com.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * jdbcTemplate最基本的用法
 */
public class demo3 {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate as = ac.getBean("jdbcTemplate", JdbcTemplate.class);

//        as.execute("insert into account(name, money) values ('王呀', 200)");
//        as.update("insert into account(name, money) values(?, ?)", "李哈", 1000);
//        as.update("update account set name=? where id=?", "李哈哈", 14);
//        as.update("delete from account where id=?", 14);
//        List<Account> accounts = as.query("select * from account where money>?",new AccountRowMapper(),100);
        //也可以用spring提供的方法
//        List<Account> accounts = as.query("select * from account where money>?",new BeanPropertyRowMapper<Account>(Account.class),100);
//        for (Account account: accounts) {
//            System.out.println(account);
//        }
        Integer a = as.queryForObject("select count(*) from account where money>?",Integer.class ,100);
        System.out.println(a);
    }
}

/**
 * 定义Account的封装策略
 */
class AccountRowMapper implements RowMapper<Account>{
    /**
     * 把结果集中的数据封装到Account中，由spring把Account加到集合中
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}

