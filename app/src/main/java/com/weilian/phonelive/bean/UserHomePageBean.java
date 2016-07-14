package com.weilian.phonelive.bean;

import java.util.List;

public class UserHomePageBean {
    private int attentionnum;
    private String avatar;
    private String city;
    private List<OrderBean> coinrecord3;
    private String consumption;
    private String experience;
    private int fansnum;
    private int id;
    private int isattention;
    private int isblack;
    private int isrecommend;
    private int level;
    private int liverecordnum;
    private String province;
    private int sex;
    private String signature;
    private String user_nicename;
    private String votestotal;

    public int getIsblack() {
        return this.isblack;
    }

    public void setIsblack(int i) {
        this.isblack = i;
    }

    public List<OrderBean> getCoinrecord3() {
        return this.coinrecord3;
    }

    public void setCoinrecord3(List<OrderBean> list) {
        this.coinrecord3 = list;
    }

    public int getIsattention() {
        return this.isattention;
    }

    public void setIsattention(int i) {
        this.isattention = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getLiverecordnum() {
        return this.liverecordnum;
    }

    public void setLiverecordnum(int i) {
        this.liverecordnum = i;
    }

    public int getFansnum() {
        return this.fansnum;
    }

    public void setFansnum(int i) {
        this.fansnum = i;
    }

    public int getAttentionnum() {
        return this.attentionnum;
    }

    public void setAttentionnum(int i) {
        this.attentionnum = i;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getIsrecommend() {
        return this.isrecommend;
    }

    public void setIsrecommend(int i) {
        this.isrecommend = i;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getVotestotal() {
        return this.votestotal;
    }

    public void setVotestotal(String str) {
        this.votestotal = str;
    }

    public String getConsumption() {
        return this.consumption;
    }

    public void setConsumption(String str) {
        this.consumption = str;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String str) {
        this.experience = str;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String str) {
        this.signature = str;
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
}
