package com.weilian.phonelive.ui;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import butterknife.ButterKnife;
import butterknife.BindView;
import com.lzfutil.util.f;
import com.lzfutil.util.j;
import com.lzfutil.util.t;
import com.lzfutil.util.w;
import com.lzfutil.util.Update;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.weilian.phonelive.ActivityManager;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.widget.MyFragmentTabHost;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.List;
import java.util.Set;
import okhttp3.Call;
import com.alipay.sdk.cons.GlobalConstants;

public class MainActivity extends ActionBarActivity implements OnClickListener, OnTouchListener, OnTabChangeListener, ch.b {
    EMMessageListener a = new EMMessageListener() {

        public void onMessageReceived(List<EMMessage> list) {
            for (EMMessage eMMessage : list) {
            }
        }

        public void onCmdMessageReceived(List<EMMessage> list) {
        }

        public void onMessageReadAckReceived(List<EMMessage> list) {
        }

        public void onMessageDeliveryAckReceived(List<EMMessage> list) {
        }

        public void onMessageChanged(EMMessage eMMessage, Object obj) {
        }
    };
    private f b;
    @BindView(R.id.iv_live_start)
    View mLiveStart;
    @BindView(android.R.id.tabhost)
    MyFragmentTabHost mTabHost;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        ButterKnife.bind((Activity) this);
        initView();
        initData();
        ActivityManager.getAppManager().addActivity((Activity) this);
    }

    public void initView() {
        this.mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        if (VERSION.SDK_INT > 10) {
            this.mTabHost.getTabWidget().setShowDividers(0);
        }
        getSupportActionBar().hide();
        a();
        this.mLiveStart.setOnClickListener(this);
        this.mTabHost.setCurrentTab(100);
        this.mTabHost.setOnTabChangedListener(this);
        this.mTabHost.setNoTabChangedTag(GlobalConstants.e);
    }

    private void a() {
        d[] values = d.values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            d dVar = values[i];
            TabSpec newTabSpec = this.mTabHost.newTabSpec(String.valueOf(dVar.b()));
            View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_indicator, null);
            ((ImageView) inflate.findViewById(R.id.tab_img)).setImageDrawable(getResources().getDrawable(dVar.c()));
            newTabSpec.setIndicator(inflate);
            newTabSpec.setContent(new TabContentFactory() {

                public View createTabContent(String str) {
                    return new View(MainActivity.this);
                }
            });
            this.mTabHost.addTab(newTabSpec, dVar.d(), null);
            this.mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }
    }

    public void initData() {
        this.b = new f(this);
        c();
        b();
        d();
    }

    private void b() {
        JPushInterface.setAlias(this, AppContext.c().i() + "PUSH", new TagAliasCallback() {

            public void gotResult(int i, String str, Set<String> set) {
                t.c(i + "I" + str + "S");
            }
        });
    }

    private void c() {
        j.a(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }


            public void a(String str) {
                String a = ce.a.a(str, MainActivity.this);
                if (a != null && a.equals(ce.a.retCode)) {
                    AppContext.a(MainActivity.this, "\u767b\u9646\u8fc7\u671f,\u8bf7\u91cd\u65b0\u767b\u9646");
                    w.c(MainActivity.this);
                }
            }
        });
    }

    private void d() {
        new Update(this, false).a();
    }

    protected void onStop() {
        EMClient.getInstance().chatManager().removeMessageListener(a);
        super.onStop();
    }

    protected void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(a);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_live_start:
                w.g(this);
                return;
            default:
                return;
        }
    }

    public void onTabChanged(String str) {
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
