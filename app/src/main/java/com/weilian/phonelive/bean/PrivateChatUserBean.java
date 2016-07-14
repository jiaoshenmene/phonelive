package com.weilian.phonelive.bean;

public class PrivateChatUserBean extends UserBean {
    private int isattention2;
    private String lastMessage;
    private boolean unreadMessage;

    public int getIsattention2() {
        return this.isattention2;
    }

    public void setIsattention2(int i) {
        this.isattention2 = i;
    }

    public boolean isUnreadMessage() {
        return this.unreadMessage;
    }

    public void setUnreadMessage(boolean z) {
        this.unreadMessage = z;
    }

    public String getLastMessage() {
        return this.lastMessage;
    }

    public void setLastMessage(String str) {
        this.lastMessage = str;
    }
}
