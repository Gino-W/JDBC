package com.jdbcImpl;

import com.jdbc.JDBC;

public class SQLServer implements JDBC {
    @Override
    public void getSQLConnection() {
        System.out.println("成功连接SQLServer数据库……");
    }
}
