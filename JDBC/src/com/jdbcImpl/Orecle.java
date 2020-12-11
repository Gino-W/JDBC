package com.jdbcImpl;

import com.jdbc.JDBC;

public class Orecle implements JDBC {
    @Override
    public void getSQLConnection() {
        System.out.println("成功连接Oracle数据库……");
    }
}
