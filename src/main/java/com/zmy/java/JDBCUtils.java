package com.zmy.java;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author Sam  Email:superdouble@yeah.net
 * @Description
 * @create 2022-03-04 11:42
 */
public class JDBCUtils {
    static String url = "jdbc:mysql://localhost:3306/javatest?rewriteBatchedStatements=true";
    static String user = "root";
    static String password = "123456";

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnect(){
        Connection con = null;
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 对书库进行增删改操作
     * @param sql
     * @param args
     * @throws Exception
     */
    public static void Update(String sql,Object ... args) throws Exception{
        // 获取连接
        Connection conn = getConnect();
        // 预编译sql语句
        PreparedStatement ps = conn.prepareStatement(sql);
        // 填充占位符
        for (int i = 0; i < args.length; i++) {
            ps.setString(i+1, String.valueOf(args[i]));
        }
        // 执行sql语句
        ps.executeUpdate();
        closeResource(conn,ps);
    }

    /**
     *  查询数据库中一条数据
     * @param tClass
     * @param sql
     * @param args
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T>T Query(Class<T> tClass,String sql,Object...args) throws Exception {
        // 获取连接
        Connection conn = getConnect();
        // 预编译sql语句
        PreparedStatement ps = conn.prepareStatement(sql);
        // 填充占位符
        for (int i = 0; i < args.length; i++) {
            ps.setString(i + 1, String.valueOf(args[i]));
        }
        //获取结果集
        ResultSet resultSet = ps.executeQuery();
        // 获取元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        // 获取数据的列数
        int count = metaData.getColumnCount();
        T t = null;
        if (resultSet.next()) {
            t = tClass.newInstance();
            for (int i = 0; i < count; i++) {
                // 获取每列的数据
                Object colvalue = resultSet.getObject(i + 1);
                String colName = metaData.getColumnLabel(i + 1);
                // 通过反射 给user对象指colName属性赋值
                Field field = tClass.getDeclaredField(colName);
                field.setAccessible(true);
                field.set(t, colvalue);
            }
        }
        // 关闭数据库
        closeResource(conn,ps,resultSet);
        return t;
    }

    /**
     * 关闭资源
     * @param con
     * @param statement
     */
    public static void closeResource(Connection con,Statement statement ){
        try {
            if (statement!=null)
                statement.close();
            if (con!=null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭资源
     * @param con
     * @param statement
     * @param resultSet
     */
    public static void closeResource(Connection con,Statement statement,ResultSet resultSet){
        try {
            if (resultSet!=null)
                resultSet.close();
            if (statement!=null)
                statement.close();
            if (con!=null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
