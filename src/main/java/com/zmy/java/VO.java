package com.zmy.java;

import java.util.Date;

/**
 * @author Sam  Email:superdouble@yeah.net
 * @Description 该类根据前端页面表格需求创建
 * @create 2022-03-08 10:56
 */
public class VO {
    private Integer sid;
    private String sname;
    private String sex;
    private Integer cid;
    private String cdesc;

    public VO() {
    }

    public VO(Integer sid, String sname, String sex, Integer cid, String cdesc) {
        this.sid = sid;
        this.sname = sname;
        this.sex = sex;
        this.cid = cid;
        this.cdesc = cdesc;
    }

    @Override
    public String toString() {
        return "VO{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", cid=" + cid +
                ", cdesc='" + cdesc + '\'' +
                '}';
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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }
}
