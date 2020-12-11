package com.bjpower.jdbc;


import java.sql.*;

/**
 * @author ：Gino
 * @date ：Created in 2020/12/7 11:07
 * @description：
 * @modified By：
 * @version: $
 */
public class TestBjpower {

    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","123456");
            //3、获取数据库操作对象
            stmt = conn.createStatement();
            //4、执行SQL语句
            String sql = "select ename from emp";
            //executeUpdate(insert delete update)
            rs = stmt.executeQuery(sql);//处理的是select
            //5、处理查询结果集
//            boolean res = rs.next();
//            System.out.println(res);

            while(rs.next()){
                String ename = rs.getString("ename");
                System.out.println(ename);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //6、释放资源
            try{
                if(rs != null){
                    rs.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            try{
                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}