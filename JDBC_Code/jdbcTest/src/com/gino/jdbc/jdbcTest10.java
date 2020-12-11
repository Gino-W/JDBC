package com.gino.jdbc;

/**
 * @author ：Gino
 * @date ：Created in 2020/12/11 22:04
 * @description：
 * @modified By：
 * @version: $
 */

import java.sql.*;

/**
 * JDBC事务机制：
 *      1、JDBC中的事务是自动提交的，什么是自动提交？
 *          只要执行任意一条DML语句，则自动提交一次。这是JDBC默认的事务行为
 *          但是在实际业务中，通常都是N条语句共同联合才能完成的，必须保证他们这些DML语句
 *          在同一事务中同时成功或同时失败
 *      2、以下程序先来验证一下，JDBC事务是否是自动提交机制!
 */
public class jdbcTest10 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取数据库连接
            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            String user = "";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
            //3、获取数据库操作对象
            //第一次给占位符传值
            String sql = "update dept dname = ? where deptno = ?";
            ps =  conn.prepareStatement(sql);
            ps.setString(1, "X部门");
            ps.setInt(2, 40);
            //4、执行SQL语句
            int count = ps.executeUpdate();//执行第一条UPDATE语句
            System.out.println(count);


            //第二次给占位符传值
            ps =  conn.prepareStatement(sql);
            ps.setString(1, "Y部门");
            ps.setInt(2, 30);
            count = ps.executeUpdate();//执行第二条UPDATE语句
            System.out.println(count);




        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //6、释放资源
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
