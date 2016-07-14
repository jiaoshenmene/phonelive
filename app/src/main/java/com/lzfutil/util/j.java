package com.lzfutil.util;

import android.content.Context;
import ce.b;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.weilian.phonelive.AppContext;
import com.zhy.http.okhttp.callback.StringCallback;

public class j {
    private static j a = null;

    public static j a() {
        if (a == null) {
            a = new j();
        }
        return a;
    }

    public void a(Context context) {
        new Thread() {

            public void run() {
                int i = AppContext.c().i();
                try {
                    EMClient.getInstance().createAccount(String.valueOf(i), i + "weilian");
                    t.c("\u6ce8\u518c\u6210\u529f");
                } catch (HyphenateException e) {
                    t.c("\u6ce8\u518c\u5931\u8d25" + e.getErrorCode());
                    e.printStackTrace();
                }
                EMClient.getInstance().login(String.valueOf(i), i + "weilian", new EMCallBack() {

                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        t.c("\u767b\u9646\u804a\u5929\u670d\u52a1\u5668\u6210\u529f\uff01");
                    }

                    public void onProgress(int i, String str) {
                    }

                    public void onError(int i, String str) {
                        t.c("\u767b\u9646\u804a\u5929\u670d\u52a1\u5668\u5931\u8d25\uff01" + str);
                    }
                });
            }
        }.start();
        p.a(context, "isOpenPush", Boolean.valueOf(true));
    }

    public static void b(Context context) {
        EMClient.getInstance().logout(true);
        AppContext.c().l();
        w.c(context);
    }

    public static void a(StringCallback stringCallback) {
        b.f(AppContext.c().i(), AppContext.c().j(), stringCallback);
    }
}
