package com.zmy.java;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Sam  Email:superdouble@yeah.net
 * @Description
 * @create 2022-03-04 11:33
 */
public class FirstConnect {
    public static void main(String[] args) {
        // 1. 加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 2. 建立连接
        /**
         * url: 数据库的地址
         * name:数据库的用户名
         * password:数据库密码
         */
        String url = "jdbc:mysql://localhost:3306/javatest?rewriteBatchedStatements=true";
        String name = "root";
        String password = "123456";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,name,password);
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test1(){
        Connection connect = JDBCUtils.getConnect();
        System.out.println(connect);
    }
}
