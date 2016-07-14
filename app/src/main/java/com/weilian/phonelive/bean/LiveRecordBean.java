package com.weilian.phonelive.bean;

public class LiveRecordBean {
    private String datetime;
    private String endtime;
    private int islive;
    private String nums;
    private String showid;
    private String starttime;
    private String title;
    private int uid;

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String str) {
        this.datetime = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int i) {
        this.uid = i;
    }

    public String getNums() {
        return this.nums;
    }

    public void setNums(String str) {
        this.nums = str;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public void setEndtime(String str) {
        this.endtime = str;
    }

    public String getStarttime() {
        return this.starttime;
    }

    public void setStarttime(String str) {
        this.starttime = str;
    }

    public int getIslive() {
        return this.islive;
    }

    public void setIslive(int i) {
        this.islive = i;
    }

    public String getShowid() {
        return this.showid;
    }

    public void setShowid(String str) {
        this.showid = str;
    }
}
