package com.zmy.java;

import java.util.List;

/**
 * @author Sam  Email:superdouble@yeah.net
 * @Description
 * @create 2022-03-07 16:55
 */
public class clazz {
    private Integer cid;
    private String cname;
    private String cdesc;

    private List<student> stus;

    public clazz() {
    }

    public clazz(Integer cid, String cname, String cdesc, List<student> stus) {
        this.cid = cid;
        this.cname = cname;
        this.cdesc = cdesc;
        this.stus = stus;
    }

    @Override
    public String toString() {
        return "班级id："+ cid +
                "\n班级名字：" + cname +
                "\n班级信息：" + cdesc +
                "\n学生总数：" + stus.size();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }

    public List<student> getStus() {
        return stus;
    }

    public void setStus(List<student> stus) {
        this.stus = stus;
    }
}
