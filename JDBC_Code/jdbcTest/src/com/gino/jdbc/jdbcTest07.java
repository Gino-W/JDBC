package com.gino.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author ：Gino
 * @date ：Created in 2020/12/11 0:23
 * @description：
 * @modified By：
 * @version: $login-1.1
 */

/**
 * 1.解决SQL注入问题
 *      只要用户提供的信息不参与SQL语句的编译过程，问题就解决了
 *      即使用户提供的信息中含有SQL语句的关键字，但是没有参与编译，不起作用
 *      要想用户信息不参与SQL语句的编译，那么必须使用java.sql.PreparedStatement
 *      PreparedStatement接口继承了java.sql.Statement
 *      PreparedStatement是属于预编译的数据库操作对象
 *      PreparedStatement的原理：预先对SQL语句的框架进行编译，然后再给SQL语句传'值'
 * 2.测试结果：
 *      用户名：as
 *      密码：as' or '1' = '1
 *      登陆失败
 * 3.解决SQL注入的关键是什么？
 *      用户提供的信息中即使含有SQL关键字，但不起作用
 * 4.PreparedStatement与Statement的对比：
 *      ---Statement存在SQL注入问题，PreparedStatement解决了SQL注入问题
 *      ---Statement是编译一次执行一次，PreparedStatement是编译一次，可以执行N次，PreparedStatement效率较高一些
 *      ---PreparedStatement会在编译阶段做类型的安全检查
 *
 *      综上所述，PreparedStatement使用较多，只有极少数的情况下需要使用Statement
 * 5.什么情况下必须使用Statement？
 *      业务方面要求必须支持SQL注入的时候
 *      Statement支持SQL注入，凡是业务方面要求是需要进行SQL语句拼接的，必须使用Statement
 *
 */
public class jdbcTest07 {

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

        //单独定义变量
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPwd");

        //JDBC代码
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取连接
            String url = "jdbc:mysql://localhost:3306/userlogin";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url , user, password);
            //3、获取预编译的数据库操作对象
                //  ？代表占位符
            String sql = "select * from t_user where loginName = ? and loginPwd = ?";
                // 程序执行到此处，会发送SQL语句框子给DBMS，然后DBMS进行SQL语句的预先编译
            ps = conn.prepareStatement(sql);
                //给占位符传值（第一个问号下标是1，第二个问号下标是二，JDBC中所有下标从1开始）
            ps.setString(1,loginName);
            ps.setString(2,loginPwd);
            //4、执行SQL语句

            rs = ps.executeQuery();
            //5、处理结果集
            if(rs.next()){
                loginSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6、释放资源
            try{
                if(rs != null){
                    rs.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                if(ps != null){
                    ps.close();
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
