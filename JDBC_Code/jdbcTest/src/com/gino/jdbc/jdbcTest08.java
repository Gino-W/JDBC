package com.gino.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.Scanner;

/**
 * @author ：Gino
 * @date ：Created in 2020/12/11 11:13
 * @description：
 * @modified By：
 * @version: $
 */
public class jdbcTest08 {
    public static void main(String[] args) {
        //用户在控制台输入desc就是降序，asc就是升序
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入desc就是降序，asc标识升序");
        System.out.print("请输入:");
        String keyWords = scanner.nextLine();

        //执行sql
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();

            String sql = "select ename from emp order by ename " + keyWords;
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                System.out.println(rs.getString("ename"));

            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
