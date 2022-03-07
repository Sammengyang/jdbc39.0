package com.zmy.java;

/**
 * @author Sam  Email:superdouble@yeah.net
 * @Description
 * @create 2022-03-04 17:30
 */
public class sam {
    int id;
    String name;
    String psd;

    public sam() {
    }

    public sam(int id, String name, String psd) {
        this.id = id;
        this.name = name;
        this.psd = psd;
    }

    @Override
    public String toString() {
        return "sam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", psd='" + psd + '\'' +
                '}';
    }
}
