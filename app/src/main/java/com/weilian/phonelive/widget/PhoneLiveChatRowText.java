package com.weilian.phonelive.widget;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.ChatType;
import com.hyphenate.chat.EMMessage.Direct;
import com.hyphenate.chat.EMMessage.Status;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import com.weilian.phonelive.R;

public class PhoneLiveChatRowText extends PhoneLiveChatRow {
    private TextView i;
    private AvatarView j;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Status.values().length];

        static {
            try {
                a[Status.CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Status.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Status.FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Status.INPROGRESS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public PhoneLiveChatRowText(Context context, EMMessage eMMessage, int i, BaseAdapter baseAdapter) {
        super(context, eMMessage, i, baseAdapter);
    }

    protected void c() {
        this.f.inflate(this.c.direct() == Direct.RECEIVE ? R.layout.item_message_left : R.layout.item_message_right, this);
    }

    protected void d() {
        this.i = (TextView) findViewById(R.id.tv_message_text);
        this.j = (AvatarView) findViewById(R.id.av_message_head);
    }

    protected void e() {
    }

    protected void f() {
        this.i.setText(((EMTextMessageBody) this.c.getBody()).getMessage());
        try {
            this.j.setAvatarUrl(this.c.getStringAttribute("uhead"));
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        h();
    }

    protected void h() {
        if (this.c.direct() == Direct.SEND) {
            a();
            switch (AnonymousClass1.a[this.c.status().ordinal()]) {
            }
        } else if (!this.c.isAcked() && this.c.getChatType() == ChatType.Chat) {
            try {
                EMClient.getInstance().chatManager().ackMessageRead(this.c.getFrom(), this.c.getMsgId());
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }
    }

    protected void g() {
    }
}
