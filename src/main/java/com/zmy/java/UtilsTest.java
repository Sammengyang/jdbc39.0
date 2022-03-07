package com.zmy.java;

import org.junit.Test;

import java.util.List;

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
    public void test1(){
        JDBCUtils jdbcUtils = new JDBCUtils();
        String sql = "INSERT INTO sam VALUES(?,?,?)";
        try {
            jdbcUtils.Update(sql,1,"zmy","123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库删除数据
     */
    @Test
    public void test2(){
        JDBCUtils jdbcUtils = new JDBCUtils();
        String sql = "delete  from sam where id=?";
        try {
            jdbcUtils.Update(sql,4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 数据库修改数据
     */
    @Test
    public void test3(){
        JDBCUtils jdbcUtils = new JDBCUtils();
        String sql = "update sam set name=? where id=?";
        try {
            jdbcUtils.Update(sql,"sam",1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test4(){
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
    public void testGetAll(){
        JDBCUtils jdbcUtils = new JDBCUtils();
        jdbcUtils.getAll();
    }

    @Test
    public void testGetList(){
        JDBCUtils jdbcUtils = new JDBCUtils();
        List<sam> list = jdbcUtils.getList();
        for (int i = 0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }
    }

    /**
     * 测试向数据库添加一行数据
     */
    @Test
    public void TestAddOne(){
        JDBCUtils jdbcUtils = new JDBCUtils();
        jdbcUtils.addOne(new sam(100,"sss","123456"));
    }

    @Test
    public void testUpdateSam(){
        JDBCUtils jdbcUtils = new JDBCUtils();
        String sql = "insert into sam value (?,?,?)";
        jdbcUtils.UpdateSam(sql,"111","zmy","123456");
    }
}
