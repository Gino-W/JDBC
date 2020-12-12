package com.gino.jdbc;

import com.gino.jdbc.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ：Gino
 * @date ：Created in 2020/12/12 10:12
 * @description：
 * @modified By：
 * @version: $
 */
public class jdbcTest14_PL {
    public static void main(String[] args) {
        Connection conn =null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "update emp set sal = sal * 0.1 where job = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "MANAGER");

            int count = ps.executeUpdate();
            System.out.println(count);

            conn.commit();
        } catch (SQLException e) {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }
}
