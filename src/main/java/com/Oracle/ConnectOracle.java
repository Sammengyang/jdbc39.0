package com.Oracle;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Sam  Email:superdouble@yeah.net
 * @Description
 * @create 2022-03-07 11:57
 */
public class ConnectOracle {

    String url = "jdbc:oracle:thin:@localhost:3306:orcl";
    String name = "scott";
    String password = "123456";
    Connection conn = null;
    public Connection GetConnect(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url,name,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    @Test
    public void testOrcale(){
        conn = GetConnect();
        System.out.println(conn);
    }
}
