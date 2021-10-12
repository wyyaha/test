package com.jdbcTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * jdbcTemplate最基本的用法
 */
public class demo2 {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate as = ac.getBean("jdbcTemplate", JdbcTemplate.class);

        as.execute("insert into account(name, money) values ('王呀', 200)");
    }
}

