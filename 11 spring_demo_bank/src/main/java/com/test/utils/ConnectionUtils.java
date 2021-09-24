package com.test.utils;

import javax.sql.DataSource;
import java.sql.Connection;

/*
连接的工具类，用于从数据源中获取一个连接，实现和线程的绑定
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*
        获取当前线程的连接
    */
    public Connection getThreadConnection() {
        try {
            Connection conn = tl.get();
            if (conn == null) {
                //从数据源获取一个连接，并且存入threadlocal
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection() {
        tl.remove();
    }
}
