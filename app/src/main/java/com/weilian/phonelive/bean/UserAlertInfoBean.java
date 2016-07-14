package com.weilian.phonelive.bean;

public class UserAlertInfoBean {
    private int attention;
    private int consumption;
    private UserBean contribute;
    private int fans;
    private int votestotal;

    public int getAttention() {
        return this.attention;
    }

    public void setAttention(int i) {
        this.attention = i;
    }

    public int getVotestotal() {
        return this.votestotal;
    }

    public void setVotestotal(int i) {
        this.votestotal = i;
    }

    public int getConsumption() {
        return this.consumption;
    }

    public void setConsumption(int i) {
        this.consumption = i;
    }

    public UserBean getContribute() {
        return this.contribute;
    }

    public void setContribute(UserBean userBean) {
        this.contribute = userBean;
    }

    public int getFans() {
        return this.fans;
    }

    public void setFans(int i) {
        this.fans = i;
    }
}
