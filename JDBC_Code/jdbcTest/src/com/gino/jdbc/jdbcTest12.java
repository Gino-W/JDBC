package com.gino.jdbc;

/**
 * @author ：Gino
 * @date ：Created in 2020/12/12 1:32
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
 * 两个任务：
 *      第一：测试DBUtil是否好用
 *      第二：模糊查询怎么写？
 */
public class jdbcTest12 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = DBUtil.getConnection();
            //获取预编译的数据库操作对象
            String sql = "select ename from emp where like '_?%'";
            //ps.setInt();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            DBUtil.close(conn, ps, rs);
        }
    }
}
