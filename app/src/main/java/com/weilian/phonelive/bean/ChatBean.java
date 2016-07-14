package com.weilian.phonelive.bean;

import android.text.SpannableStringBuilder;

public class ChatBean extends UserBean {
    private SpannableStringBuilder sendChatMsg;
    private SpannableStringBuilder userNick;

    public SpannableStringBuilder getUserNick() {
        return this.userNick;
    }

    public void setUserNick(SpannableStringBuilder spannableStringBuilder) {
        this.userNick = spannableStringBuilder;
    }

    public SpannableStringBuilder getSendChatMsg() {
        return this.sendChatMsg;
    }

    public void setSendChatMsg(SpannableStringBuilder spannableStringBuilder) {
        this.sendChatMsg = spannableStringBuilder;
    }
}
