package com.weilian.phonelive.ui;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.weilian.phonelive.bean.PrivateChatUserBean;
import com.weilian.phonelive.bean.UserBean;

public class e {
    public static EMMessage a(String str, PrivateChatUserBean privateChatUserBean, UserBean userBean) {
        EMMessage createTxtSendMessage = EMMessage.createTxtSendMessage(str, String.valueOf(privateChatUserBean.getId()));
        createTxtSendMessage.setAttribute("uhead", userBean.getAvatar());
        createTxtSendMessage.setAttribute("isfollow", privateChatUserBean.getIsattention2());
        createTxtSendMessage.setAttribute("uname", privateChatUserBean.getUser_nicename());
        EMClient.getInstance().chatManager().sendMessage(createTxtSendMessage);
        return createTxtSendMessage;
    }
}
