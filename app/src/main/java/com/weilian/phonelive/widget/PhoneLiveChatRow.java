package com.weilian.phonelive.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.Status;
import com.hyphenate.chat.adapter.EMAError;
import com.hyphenate.util.DateUtils;
import com.weilian.phonelive.R;

import java.util.Date;
import org.kymjs.kjframe.http.HttpStatus;

public abstract class PhoneLiveChatRow extends LinearLayout {
    protected Context a;
    protected Activity b;
    protected EMMessage c;
    protected int d;
    protected BaseAdapter e;
    protected LayoutInflater f;
    protected EMCallBack g;
    protected TextView h;
    private AvatarView i;

    protected abstract void c();

    protected abstract void d();

    protected abstract void e();

    protected abstract void f();

    protected abstract void g();

    public PhoneLiveChatRow(Context context, EMMessage eMMessage, int i, BaseAdapter baseAdapter) {
        super(context);
        this.a = context;
        this.b = (Activity) context;
        this.c = eMMessage;
        this.d = i;
        this.e = baseAdapter;
        this.f = LayoutInflater.from(context);
        initView();
    }

    private void initView() {
        c();
        this.i = (AvatarView) findViewById(R.id.av_message_head);
        d();
    }

    public void a(EMMessage eMMessage, int i) {
        this.c = eMMessage;
        this.d = i;
        h();
        f();
    }

    private void h() {
        TextView textView = (TextView) findViewById(R.id.timestamp);
        if (textView == null) {
            return;
        }
        if (this.d == 0) {
            textView.setText(DateUtils.getTimestampString(new Date(this.c.getMsgTime())));
            textView.setVisibility(View.VISIBLE);
            return;
        }
        EMMessage eMMessage = (EMMessage) this.e.getItem(this.d - 1);
        if (eMMessage == null || !DateUtils.isCloseEnough(this.c.getMsgTime(), eMMessage.getMsgTime())) {
            textView.setText(DateUtils.getTimestampString(new Date(this.c.getMsgTime())));
            textView.setVisibility(View.VISIBLE);
            return;
        }
        textView.setVisibility(View.GONE);
    }

    protected void a() {
        if (this.g == null) {
            this.g = new EMCallBack() {

                public void onSuccess() {
                    b();
                }

                public void onProgress(final int i, String str) {
                    b.runOnUiThread(new Runnable() {

                        public void run() {
                            if (h != null) {
                                h.setText(i + "%");
                            }
                        }
                    });
                }

                public void onError(int i, String str) {
                    b();
                }
            };
        }
        this.c.setMessageStatusCallback(this.g);
    }

    protected void b() {
        this.b.runOnUiThread(new Runnable() {

            public void run() {
                if (c.status() == Status.FAIL && c.getError() != HttpStatus.SC_NOT_IMPLEMENTED && c.getError() == EMAError.GROUP_NOT_JOINED) {
                }
                e();
            }
        });
    }
}
