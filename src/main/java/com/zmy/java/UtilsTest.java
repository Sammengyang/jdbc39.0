package com.zmy.java;

import org.junit.Test;

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
        String sql = "INSERT INTO sam VALUES(?,?,?)";
        try {
            JDBCUtils.Update(sql,1,"zmy","123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库删除数据
     */
    @Test
    public void test2(){
        String sql = "delete  from sam where id=?";
        try {
            JDBCUtils.Update(sql,4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 数据库修改数据
     */
    @Test
    public void test3(){
        String sql = "update sam set name=? where id=?";
        try {
            JDBCUtils.Update(sql,"sam",1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test4(){
        String sql = "select id,name,psd from sam where id=?";
        try {
            sam sam1 = JDBCUtils.Query(sam.class, sql, 1);
            System.out.println(sam1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
