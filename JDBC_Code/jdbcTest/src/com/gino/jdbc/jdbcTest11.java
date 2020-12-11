package com.gino.jdbc;

/**
 * @author ：Gino
 * @date ：Created in 2020/12/11 22:32
 * @description：转账
 * @modified By：
 * @version: $
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * SQL脚本：
 *      drop table if exists t_act;
 *      create table t_act(
 *          actno int,
 *          balance double(7,2)
 *      );
 *      insert into t_act(actno,balance) values (111,20000);
 *      insert into t_act(actno,balance) values (222,0);
 *      commit;
 *      select * from t_act;
 *
 *      重点三行代码：
 *          conn.setAutoCommit(false);
 *          conn.commit();
 *          conn.rollback();
 *
 *
 */
public class jdbcTest11 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取数据库连接
            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, user, password);
            //将自动提交方式修改为手动提交，开启事务
            conn.setAutoCommit(false);
            //3、获取预编译的数据库操作对象
            String sql = "update t_act set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);

            //给?号传值
            ps.setDouble(1, 10000);
            ps.setInt(2, 111);
            int count = ps.executeUpdate();

            //设置异常
            String s = null;
            s.toString();

            //第二次给?传值
            ps.setDouble(1, 10000);
            ps.setInt(2,222);
            count += ps.executeUpdate();

            System.out.println(count == 2 ? "转账成功" : "转账失败");

            //程序能走到这里，则同时提交事务，同时成功则成功提交，其中有一个失败则全部失败
            conn.commit();
        }catch(Exception e){
            //为了数据的安全性，失败后手动回滚事务
            if(conn != null){
                try {
                    conn.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
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






                     
























