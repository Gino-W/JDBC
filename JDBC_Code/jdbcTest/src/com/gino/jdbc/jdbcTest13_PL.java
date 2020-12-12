package com.gino.jdbc;

/**
 * @author ：Gino
 * @date ：Created in 2020/12/12 10:12
 * @description：
 * @modified By：
 * @version: $
 */

import com.gino.jdbc.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *这个程序开启一个事务，这个事务专门进行查询，并且使用行级锁/悲观锁，被锁住相关记录
 */
public class jdbcTest13_PL {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            //关闭自动提交事务
            conn.setAutoCommit(false);

            String sql = "select ename,job,sal from emp where job = ? for update";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "MANAGER");

            rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("ename") + "," +
                        rs.getString("job") + "," +
                        rs.getDouble("sal"));
            }

            //手动提交事务
            conn.commit();

        } catch (SQLException throwables) {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }
    }
}
