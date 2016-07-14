package com.weilian.phonelive.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import ck.ChatProcess;

import com.weilian.phonelive.R;
import com.weilian.phonelive.bean.UserBean;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;

public class BottomMenuView extends FrameLayout implements OnClickListener {
    private LayoutInflater a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private UserBean g;
    private UserBean h;
    private int i = 0;
    private Activity j;
    private ChatProcess k;
    private bo.a l;

    public BottomMenuView(Context context) {
        super(context);
        this.a = LayoutInflater.from(context);
        initView();
    }

    public BottomMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public void a(UserBean userBean, UserBean userBean2, int i, Activity activity, ChatProcess aVar, bo.a aVar2) {
        this.g = userBean;
        this.h = userBean2;
        this.i = i;
        this.j = activity;
        this.l = aVar2;
        this.k = aVar;
        if (this.h.getuType() == 40) {
            this.b.setText("\u5220\u9664\u7ba1\u7406");
        }
    }

    public void setIsEmcee(boolean z) {
        if (z) {
            this.c.setVisibility(View.GONE);
            return;
        }
        this.b.setVisibility(View.GONE);
        this.f.setVisibility(View.GONE);
    }

    private void initView() {
        this.a.inflate(R.layout.view_manage_menu, this);
        this.b = (TextView) findViewById(R.id.tv_manage_set_manage);
        this.d = (TextView) findViewById(R.id.tv_manage_shutup);
        this.e = (TextView) findViewById(R.id.tv_manage_cancel);
        this.f = (TextView) findViewById(R.id.tv_manage_manage_list);
        this.c = (TextView) findViewById(R.id.tv_manage_set_report);
        this.b.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_manage_set_manage:
                b();
                return;
            case R.id.tv_manage_shutup:
                a();
                return;
            case R.id.tv_manage_cancel:
                if (this.l != null) {
                    this.l.c();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void a() {
        if (this.i != 0 && this.g != null && this.h != null && this.j != null && this.k != null) {
            ce.b.a(this.i, this.h.getId(), this.g.getId(), new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    a(response);
                }


                public void a(String str) {
                    if (ce.a.a(str, j) != null) {
                        k.a(g, h);
                        l.c();
                    }
                }
            });
        }
    }

    private void b() {
        if (this.i != 0 && this.g != null && this.h != null && this.j != null && this.k != null) {
            ce.b.g(this.i, this.h.getId(), new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    a(response);
                }


                public void a(String str) {
                    if (ce.a.a(str, j) != null) {
                        if (h.getuType() == 30) {
                            k.b(g, h);
                        }
                        l.c();
                    }
                }
            });
        }
    }
}
