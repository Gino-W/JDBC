package com.gino.jdbc;

import javax.xml.transform.Result;
import java.lang.instrument.ClassDefinition;
import java.sql.*;

/**
 * @author ：Gino
 * @date ：Created in 2020/12/11 21:31
 * @description：PrepareStatement完成----insert,update,delete
 * @modified By：
 * @version: $
 */

/**
 * PrepareStatement 先预编译SQL语句框子
 */
public class jdbcTest09 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //搭架子
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取数据库连接对象
            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, user, password);
            //3、获取数据库操作对象(搭建SQL语句框子)

            //insert语句
//            String sql = "insert into dept(DEPTNO,DNAME,LOC) values (?,?,?)";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, 50);
//            ps.setString(2, "人事部");
//            ps.setString(3, "郑州");

            //update语句
//            String sql = "update dept set DNAME = ?,LOC = ? where DEPTNO = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, "销售部");
//            ps.setString(2, "北京");
//            ps.setInt(3, 50);

            //删除语句
            String sql = "delete from dept where DEPTNO = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 50);



            //4、执行SQL语句
            int count = ps.executeUpdate();
            System.out.println("改变" + count + "行结果");

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
