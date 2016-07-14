package com.weilian.phonelive.bean;

public class OrderBean {
    private String avatar;
    private int level;
    private String orderNo;
    private int sex;
    private String total;
    private int uid;
    private String user_nicename;

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String str) {
        this.orderNo = str;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String str) {
        this.total = str;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int i) {
        this.sex = i;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getUser_nicename() {
        return this.user_nicename;
    }

    public void setUser_nicename(String str) {
        this.user_nicename = str;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int i) {
        this.uid = i;
    }
}
