package com.zmy.java;

import org.junit.Test;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

/**
 * @author Sam  Email:superdouble@yeah.net
 * @Description
 * @create 2022-03-04 17:22
 */
public class UtilsTest {
    /**
     * 数据库添加数据
     */
    @Test
    public void test1() {
        JDBCUtils jdbcUtils = new JDBCUtils();
        String sql = "INSERT INTO sam VALUES(?,?,?)";
        Scanner sc = new Scanner(System.in);
        System.out.println("输入账号:");
        int id = sc.nextInt();
        System.out.println("输入用户名:");
        String name = sc.next();
        System.out.println("请输入用密码:");
        String psd = sc.next();
        try {
            jdbcUtils.Update(sql, id, name,psd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库删除数据
     */
    @Test
    public void test2() {
        JDBCUtils jdbcUtils = new JDBCUtils();
        String sql = "delete  from sam where id=?";
        try {
            jdbcUtils.Update(sql, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库修改数据
     */
    @Test
    public void test3() {
        JDBCUtils jdbcUtils = new JDBCUtils();
        String sql = "update sam set name=? where id=?";
        try {
            jdbcUtils.Update(sql, "sam", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试查询
     */
    @Test
    public void test4() {
        JDBCUtils jdbcUtils = new JDBCUtils();
        String sql = "select id,name,psd from sam where id=?";
        try {
            sam sam1 = jdbcUtils.Query(sam.class, sql, 1);
            System.out.println(sam1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAll() {
        JDBCUtils jdbcUtils = new JDBCUtils();
        jdbcUtils.getAll();
    }

    @Test
    public void testGetList() {
        JDBCUtils jdbcUtils = new JDBCUtils();
        List<sam> list = jdbcUtils.getList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    /**
     * 测试向数据库添加一行数据
     */
    @Test
    public void TestAddOne() {
        JDBCUtils jdbcUtils = new JDBCUtils();
        jdbcUtils.addOne(new sam(100, "sss", "123456"));
    }

    @Test
    public void testUpdateSam() {
        JDBCUtils jdbcUtils = new JDBCUtils();
        String sql = "insert into sam value (?,?,?)";
        jdbcUtils.UpdateSam(sql, "111", "zmy", "123456");
    }

    /**
     * 模拟登录
     */
    @Test
    public void login() {
        JDBCUtils jdbcUtils = new JDBCUtils();
        Scanner sc = new Scanner(System.in);
        String sql = "select id,name,psd from sam where name=? and psd=?";
        System.out.println("输入用户名：");
        String Name = sc.next();
        System.out.println("输入用密码：");
        String Psd = sc.next();
        sam query = null;
        try {
            query = jdbcUtils.Query(sam.class, sql, Name, Psd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (query != null) {
            System.out.println("登录成功！ ");
            System.out.println(query.toString());
        }else{
            System.out.println("请先注册");
        }

    }
}
