package com.weilian.phonelive.ui;

public enum b {
    CHANG_NICK("user_nicename"),
    CHANG_SIGN("signature");
    
    String c;

    private b(String str) {
        this.c = str;
    }

    public String a() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }
}
