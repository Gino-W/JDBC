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
 *
 * 悲观锁：
 *      悲观锁主要用于保护数据的完整性。当多个事务并发执行时，某个事务对数据应用了锁，则
 *      其他事务只能等该事务执行完了，才能进行对该数据进行修改操作。
 *
 *
 *      行锁与表锁:
 *          当执行 select ... for update时，将会把数据锁住，因此，我们需要注意一下锁的级别。MySQL InnoDB 默认为行级锁。当查询语句指定了主键时，MySQL会执行「行级锁」，否则MySQL会执行「表锁」。
 *          常见情况如下：
 *          若明确指明主键，且结果集有数据，行锁；
 *          若明确指明主键，结果集无数据，则无锁；
 *          若无主键，且非主键字段无索引，则表锁；
 *          若使用主键但主键不明确，则使用表锁；
 *
 *
 * 使用场景：超市避免超卖
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

            //断点，是程序在事务进行中卡顿，此时运行另一程序，修改数据，显示失败
            //再继续此程序，直至完成，另一程序成功执行
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
