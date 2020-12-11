package com.gino.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author ：Gino
 * @date ：Created in 2020/12/3 11:32
 * @description：
 * @modified By：
 * @version: $login-1.0
 *
 */

/**
 *  模拟用户登录
 *          1.需求：
 *              模拟用户登录功能的实现
 *          2.业务描述：
 *              程序运行的时候，提供一个输入的入口，可以让用户输入用户名和密码。
 *              之后提交信息，Java程序收集到用户信息
 *              Java程序连接数据库验证用户名和密码是否合法
 *                  合法：显示登陆成功
 *                  不合法：显示登陆失败
 *          3.数据的准备：
 *              在实际开发中，表的设计会使用专业的建模工具，使用PD工具来进行数据库的表的设计（参见userLogin.sql脚本）
 *          4.当前程序存在的问题
 *              用户名：dasd
 *              密码：dasd' or '1'='1
 *              登陆成功
 *              SQL注入（安全隐患，黑客经常使用）
*           5.产生SQL注入的原因是什么？
 *              用户输入的信息中包含SQL语句的关键字，并且这些关键字参与SQL语句的编译过程
 *              导致SQL语句的原意被扭曲，进而达到SQL注入
 */
public class jdbcTest06 {
    public static void main(String[] args) {
        //初始化一个界面
        Map<String,String> userLoginInfo  = initUI();
        //验证用户名和密码
        boolean loginSuccess = login(userLoginInfo);
        //返回验证结果
        System.out.println(loginSuccess ? "登陆成功" : "登陆失败");
    }

    private static boolean login(Map<String, String> userLoginInfo) {
        //打标记
        boolean loginSuccess = false;

        //JDBC代码
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/userlogin";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url , user, password);

            stmt = conn.createStatement();

            String sql = "select * from t_user where loginName = '"+ userLoginInfo.get("loginName") +"' and loginPwd = '"+ userLoginInfo.get("loginPwd") +"'";
            rs = stmt.executeQuery(sql);

            if(rs.next()){
                loginSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(rs != null){
                    rs.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                if(conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return loginSuccess;
    }


    /**
     * 初始化用户界面
     * @return  用户输入的用户名和密码等登录信息
     */
    private static Map<String, String> initUI() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("用户名：");
        String loginName = scanner.nextLine();

        System.out.println("密码：");
        String loginPwd = scanner.nextLine();

        Map<String,String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName", loginName);
        userLoginInfo.put("loginPwd", loginPwd);

        return userLoginInfo;
    }

}
