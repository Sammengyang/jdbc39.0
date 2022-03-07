package com.zmy.java;

import java.util.Date;

/**
 * @author Sam  Email:superdouble@yeah.net
 * @Description
 * @create 2022-03-07 16:52
 */
public class student {
    private Integer sid;
    private String sname;
    private String sex;
    private Date birthday;
    private Integer cid;

    private clazz clazz;

    public student() {
    }

    public student(Integer sid, String sname, String sex, Date birthday, Integer cid, com.zmy.java.clazz clazz) {
        this.sid = sid;
        this.sname = sname;
        this.sex = sex;
        this.birthday = birthday;
        this.cid = cid;
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return sid + "\t" + sname + "\t\t" + sex + "\t\t" + birthday+ "\t\t" + cid;

    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public com.zmy.java.clazz getClazz() {
        return clazz;
    }

    public void setClazz(com.zmy.java.clazz clazz) {
        this.clazz = clazz;
    }
}
