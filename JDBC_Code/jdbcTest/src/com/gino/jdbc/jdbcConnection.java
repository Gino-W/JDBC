package com.gino.jdbc;

/**
 * @author ：Gino
 * @date ：Created in 2020/12/3 1:12
 * @description：模拟用户登录
 * @modified By：
 * @version: 1.0$
 */

import java.sql.*;
import java.util.ResourceBundle;


public class jdbcConnection {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("JDBC");

        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;


        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            stmt = conn.createStatement();

            String sql = "select loginName from t_user";
            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                String loginName = rs.getString("loginName");
                System.out.println(loginName);
            }

        } catch (ClassNotFoundException | SQLException e) {
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
