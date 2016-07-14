package com.weilian.phonelive.bean;

public class MusicBean {
    private String artistname;
    private String bitrate_fee;
    private String control;
    private String encrypted_songid;
    private String has_mv;
    private String songid;
    private String songname;
    private String yyr_artist;

    public String getBitrate_fee() {
        return this.bitrate_fee;
    }

    public void setBitrate_fee(String str) {
        this.bitrate_fee = str;
    }

    public String getYyr_artist() {
        return this.yyr_artist;
    }

    public void setYyr_artist(String str) {
        this.yyr_artist = str;
    }

    public String getSongname() {
        return this.songname;
    }

    public void setSongname(String str) {
        this.songname = str;
    }

    public String getArtistname() {
        return this.artistname;
    }

    public void setArtistname(String str) {
        this.artistname = str;
    }

    public String getControl() {
        return this.control;
    }

    public void setControl(String str) {
        this.control = str;
    }

    public String getSongid() {
        return this.songid;
    }

    public void setSongid(String str) {
        this.songid = str;
    }

    public String getHas_mv() {
        return this.has_mv;
    }

    public void setHas_mv(String str) {
        this.has_mv = str;
    }

    public String getEncrypted_songid() {
        return this.encrypted_songid;
    }

    public void setEncrypted_songid(String str) {
        this.encrypted_songid = str;
    }
}
