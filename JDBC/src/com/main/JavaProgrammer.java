package com.main;

import com.jdbc.JDBC;
import com.jdbcImpl.MySQL;

import java.util.ResourceBundle;

/**
 * @author ：Gino
 * @date ：Created in 2020/11/29 17:01
 * @description：JDBC实现原理
 * @modified By：
 * @version: $
 */
public class JavaProgrammer {
    public static void main(String[] args) throws Exception {
        //创建对象的方法
//        JDBC jdbc = new MySQL();
//        jdbc.getSQLConnection();

        //创建对象可以利用反射机制
//        Class c = Class.forName("com.jdbcImpl.MySQL");
//        JDBC jdbc = (JDBC)c.newInstance();
//        jdbc.getSQLConnection();

        ResourceBundle bundle = ResourceBundle.getBundle("JDBC");
        String className = bundle.getString("SQLName");
        Class c = Class.forName(className);
        JDBC jdbc = (JDBC)c.newInstance();
        jdbc.getSQLConnection();
    }
}
