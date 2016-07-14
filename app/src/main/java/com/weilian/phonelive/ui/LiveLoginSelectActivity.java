package com.weilian.phonelive.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.lzfutil.util.j;
import com.lzfutil.util.w;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import com.google.gson.Gson;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;
import com.weilian.phonelive.bean.UserBean;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.HashMap;
import okhttp3.Call;

public class LiveLoginSelectActivity extends BaseActivity implements PlatformActionListener {
    StringCallback d = new StringCallback() {

        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {

            a((String) response);
        }


        public void a(String str) {
            String a = ce.a.a(str, LiveLoginSelectActivity.this);
            if (a != null) {
                AppContext.c().a((UserBean) new Gson().fromJson(a, UserBean.class));
                w.d(LiveLoginSelectActivity.this);
                j.a().a(LiveLoginSelectActivity.this);
                LiveLoginSelectActivity.this.finish();
                System.gc();
            }
        }
    };
    private String[] e = new String[]{QQ.NAME, Wechat.NAME, SinaWeibo.NAME};
    private String f;
    private Bitmap g;
    @BindView(R.id.iv_select_login_bg)
    ImageView mBg;

    protected int c() {
        return R.layout.activity_show_login;
    }

    public void initView() {
        getSupportActionBar().hide();
        this.g = null;
        this.g = BitmapFactory.decodeResource(getResources(), R.drawable.live_show_login_bg);
        this.mBg.setImageBitmap(this.g);
    }

    public void initData() {
    }

    @OnClick({R.id.iv_qqlogin, R.id.iv_sllogin, R.id.iv_wxlogin, R.id.iv_mblogin})
    public void onClick(View view) {
        int id = view.getId();
        ShareSDK.initSDK(this);
        switch (id) {
            case R.id.iv_qqlogin:
                this.f = "qq";
                d(this.e[0]);
                return;
            case R.id.iv_sllogin:
                this.f = "sina";
                AppContext.a((Activity) this, "\u5fae\u535a");
                d(this.e[2]);
                return;
            case R.id.iv_wxlogin:
                this.f = "wx";
                AppContext.a((Activity) this, "\u5fae\u4fe1");
                d(this.e[1]);
                return;
            case R.id.iv_mblogin:
                w.b(this);
                finish();
                return;
            default:
                return;
        }
    }

    private void h() {
    }

    private void d(String str) {
        Platform platform = ShareSDK.getPlatform(str);
        platform.showUser(null);
        platform.SSOSetting(false);
        platform.setPlatformActionListener(this);
        platform.authorize();
        platform.removeAccount(true);
    }

    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (i == 8) {
            ce.b.a(this.f, platform.getDb(), this.d);
        }
    }

    public void onError(Platform platform, int i, Throwable th) {
    }

    public void onCancel(Platform platform, int i) {
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.g != null) {
            this.g.recycle();
        }
    }
}
