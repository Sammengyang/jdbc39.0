package com.zmy.java;

/**
 * @author Sam  Email:superdouble@yeah.net
 * @Description
 * @create 2022-03-04 17:30
 */
public class sam {
    private int id;
    private String name;
    private String psd;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }
}
