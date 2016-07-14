package com.weilian.phonelive.bean;

public class GiftBean {
    private String gifticon;
    private String gifticon_mini;
    private String giftname;
    private int id;
    private int needcoin;
    private int orderno;
    private int sid;
    private int type;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getSid() {
        return this.sid;
    }

    public void setSid(int i) {
        this.sid = i;
    }

    public String getGiftname() {
        return this.giftname;
    }

    public void setGiftname(String str) {
        this.giftname = str;
    }

    public int getNeedcoin() {
        return this.needcoin;
    }

    public void setNeedcoin(int i) {
        this.needcoin = i;
    }

    public String getGifticon_mini() {
        return this.gifticon_mini;
    }

    public void setGifticon_mini(String str) {
        this.gifticon_mini = str;
    }

    public String getGifticon() {
        return this.gifticon;
    }

    public void setGifticon(String str) {
        this.gifticon = str;
    }

    public int getOrderno() {
        return this.orderno;
    }

    public void setOrderno(int i) {
        this.orderno = i;
    }
}
