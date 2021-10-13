package com.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 此类用于抽取dao中的重复代码
 */
public class JdbcDaoSupport {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        if (jdbcTemplate == null) {
            jdbcTemplate = creteJdbcTemplate(dataSource);
        }
    }

    private JdbcTemplate creteJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
