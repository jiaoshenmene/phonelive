package com.weilian.phonelive.bean;

import java.io.Serializable;
import java.util.List;

public class UserBean implements Serializable {
    private int attentionnum;
    private String avatar;
    private String birthday;
    private String city;
    private String coin;
    private List<OrderBean> coinrecord3;
    private String consumption;
    private String create_time;
    private String expiretime;
    private String fansnum;
    private int id = 0;
    private int isattention;
    private String last_login_ip;
    private String last_login_time;
    private int level;
    private int liverecordnum;
    private String mobile;
    private String nums;
    private String province;
    private String score;
    private int sex;
    private String signature;
    private String title;
    private String token;
    private int uType;
    private String user_activation_key;
    private String user_email;
    private String user_login;
    private String user_nicename;
    private String user_pass;
    private String user_status;
    private int user_type;
    private String user_url;
    private String votes;

    public int getuType() {
        return this.uType;
    }

    public void setuType(int i) {
        this.uType = i;
    }

    public List<OrderBean> getCoinrecord3() {
        return this.coinrecord3;
    }

    public void setCoinrecord3(List<OrderBean> list) {
        this.coinrecord3 = list;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getNums() {
        return this.nums;
    }

    public void setNums(String str) {
        this.nums = str;
    }

    public String getConsumption() {
        return this.consumption;
    }

    public void setConsumption(String str) {
        this.consumption = str;
    }

    public int getAttentionnum() {
        return this.attentionnum;
    }

    public void setAttentionnum(int i) {
        this.attentionnum = i;
    }

    public int getLiverecordnum() {
        return this.liverecordnum;
    }

    public void setLiverecordnum(int i) {
        this.liverecordnum = i;
    }

    public String getFansnum() {
        return this.fansnum;
    }

    public void setFansnum(String str) {
        this.fansnum = str;
    }

    public int getIsattention() {
        return this.isattention;
    }

    public void setIsattention(int i) {
        this.isattention = i;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getVotes() {
        return this.votes;
    }

    public void setVotes(String str) {
        this.votes = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getUser_login() {
        return this.user_login;
    }

    public void setUser_login(String str) {
        this.user_login = str;
    }

    public String getUser_nicename() {
        return this.user_nicename;
    }

    public void setUser_nicename(String str) {
        this.user_nicename = str;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public String getLast_login_time() {
        return this.last_login_time;
    }

    public void setLast_login_time(String str) {
        this.last_login_time = str;
    }

    public String getUser_status() {
        return this.user_status;
    }

    public void setUser_status(String str) {
        this.user_status = str;
    }

    public String getUser_email() {
        return this.user_email;
    }

    public void setUser_email(String str) {
        this.user_email = str;
    }

    public String getUser_url() {
        return this.user_url;
    }

    public void setUser_url(String str) {
        this.user_url = str;
    }

    public String getUser_pass() {
        return this.user_pass;
    }

    public void setUser_pass(String str) {
        this.user_pass = str;
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

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }

    public String getLast_login_ip() {
        return this.last_login_ip;
    }

    public void setLast_login_ip(String str) {
        this.last_login_ip = str;
    }

    public String getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(String str) {
        this.create_time = str;
    }

    public String getUser_activation_key() {
        return this.user_activation_key;
    }

    public void setUser_activation_key(String str) {
        this.user_activation_key = str;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String str) {
        this.score = str;
    }

    public int getUser_type() {
        return this.user_type;
    }

    public void setUser_type(int i) {
        this.user_type = i;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getCoin() {
        return this.coin;
    }

    public void setCoin(String str) {
        this.coin = str;
    }

    public String getExpiretime() {
        return this.expiretime;
    }

    public void setExpiretime(String str) {
        this.expiretime = str;
    }
}
