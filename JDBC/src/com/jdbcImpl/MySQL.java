package com.jdbcImpl;

import com.jdbc.JDBC;

public class MySQL implements JDBC {
    @Override
    public void getSQLConnection() {
        System.out.println("成功连接MySQL数据库……");
    }
}
