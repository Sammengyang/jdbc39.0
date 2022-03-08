package com.zmy.java;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam  Email:superdouble@yeah.net
 * @Description
 * @create 2022-03-04 11:42
 */
public class JDBCUtils {
    static String url = "jdbc:mysql://localhost:3306/javatest?rewriteBatchedStatements=true";
    static String user = "root";
    static String password = "123456";
    static Connection con = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    /**
     * 获取数据库连接
     * @return
     */
    public Connection getConnect(){
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
    public void Update(String sql,Object ... args) throws Exception{
        // 获取连接
        getConnect();
        // 预编译sql语句
        PreparedStatement ps = con.prepareStatement(sql);
        // 填充占位符
        for (int i = 0; i < args.length; i++) {
            ps.setString(i+1, String.valueOf(args[i]));
        }
        // 执行sql语句
        ps.executeUpdate();
        closeResource(con,ps);
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
    public <T>T Query(Class<T> tClass,String sql,Object...args) throws Exception {
        // 获取连接
        getConnect();
        // 预编译sql语句
        PreparedStatement ps = con.prepareStatement(sql);
        // 填充占位符
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
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
            return t;
        }
        // 关闭数据库
        closeResource(con,ps,resultSet);
        return null;
    }

    // 查询数据
    public void  getAll(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 建立链接
            getConnect();
            String sql = "select * from sam";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 执行sql语句
            rs = ps.executeQuery();
            // 遍历结果集
            while (rs.next()){
                System.out.println(rs.getInt("id")+":"+rs.getString("name")+":"+rs.getString("psd"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeResource(conn,ps,rs);
    }

    /**
     * 查询数据库
     * @return
     */
    public List<sam> getList(){
        List<sam> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            getConnect();
            String sql = "select * from sam";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                sam sam = new sam(rs.getInt("id"), rs.getString("name"), rs.getString("psd"));
                list.add(sam);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeResource(conn,ps,rs);
        return list;
    }

    /**
     * 向数据库添加一行数据
     * @param sam
     */
    public void addOne(sam sam){
        try {
            getConnect();
            String sql = "insert into sam values("+sam.id+",'"+sam.name+"',"+sam.psd+");";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeResource(con,ps);
    }

    /**
     * 对sam表进行增删改
     * @param sql
     * @param args
     */
    public void UpdateSam(String sql,Object ...args){
        try {
            getConnect();
            ps = con.prepareStatement(sql);
            for (int i =0;i<args.length;i++){
                ps.setString(i+1, String.valueOf(args[i]));
            }
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeResource(con,ps);
    }

    /*    方法一
        查询所有班级及班内的所有学生
        1. 查询所有班级，把班级放在list中
        2. 遍历班级集合，获取每个班级编号cid
        3. 根据班级id查询班级内对应的学生
        3. 将学生添加到对应班级的集合stus中
        4. 返回班级list
     */
    public List<clazz> QueryCla(){
        List<clazz> classList = new ArrayList();
        String sqlclass = "select * from clazz";
        try {
            // 获取链接
            getConnect();
            // 预编译sql语句
            ps = con.prepareStatement(sqlclass);
            // 执行
            ResultSet rs = ps.executeQuery();
            // 获区班级信息，并放入集合中
            while (rs.next()){
                clazz clazz = new clazz(rs.getInt("cid"),rs.getString("cname"),
                        rs.getString("cdesc"),null);
                classList.add(clazz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classList;
    }
    // 查询学生
    public List QeryStu(){
        List<clazz> classList = QueryCla();
        for (int i = 0; i < classList.size(); i++) {
            Integer cid = classList.get(i).getCid();
            List<student> students = getStuByCid(cid);
            // 将学生集合赋值到班级stus集合属性
            classList.get(i).setStus(students);
        }
        closeResource(con,ps,rs);
        return classList;
    }

    /**
     * 通过cid查询到该班的所有学生
     * @param cid
     * @return
     */
    public List<student> getStuByCid(Integer cid){
        List<student> students = null;
        try {
            // 查询学生表
            String sqlstu = "select * from student where cid=?";
            // 预编译sql语句
            ps = con.prepareStatement(sqlstu);
            // 创建集合暂存每个班级内的学生
            students = new ArrayList<>();
            // 填充占位符
            ps.setObject(1, cid);
            // 执行sql语句
            rs = ps.executeQuery();
            while (rs.next()){
                student student = new student(
                        rs.getInt("sid"),
                        rs.getString("sname"),
                        rs.getString("sex"),
                        rs.getDate("birthday"),
                        rs.getInt("cid")
                );
                // 将学生添加到学生的集合中
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    /*  方法二
        多表联查，获取学生信息以及班级信息
     */
    public List<VO> getStuAndCla(){
        List<VO> stulist = new ArrayList<>();
        getConnect();
        String sql = "select sid,sname,sex,cid,cdesc from " +
                "student s,clazz c where s.cid=c.cid";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                VO vo = new VO(
                  rs.getInt(1),
                  rs.getString(2),
                  rs.getString(3),
                  rs.getInt(4),
                  rs.getString(5)
                );
                stulist.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stulist;
    }



















    /**
     * 关闭资源
     * @param con
     * @param statement
     */
    public void closeResource(Connection con,Statement statement ){
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
    public void closeResource(Connection con,Statement statement,ResultSet resultSet){
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
