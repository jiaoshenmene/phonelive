package com.weilian.phonelive.ui;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.lzfutil.util.j;
import com.lzfutil.util.s;
import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;
import com.weilian.phonelive.base.BaseApplication;
import com.weilian.phonelive.bean.UserBean;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;

public class PhoneLoginActivity extends BaseActivity {
    Runnable d = new Runnable() {

        public void run() {
            e = e - 1;
            mBtnSendCode.setText("\u91cd\u65b0\u83b7\u53d6\u9a8c\u8bc1\u7801(" + e + ")");
            if (e == 0) {
                i.removeCallbacks(d);
                mBtnSendCode.setText("\u53d1\u9001\u9a8c\u8bc1\u7801");
                mBtnSendCode.setEnabled(true);
                e = 30;
                return;
            }
            i.postDelayed(this, 1000);
        }
    };
    private int e = 30;
    private String f = "";
    private String g = "";
    private final StringCallback h = new StringCallback() {

        @Override
        public void onError(Call call, Exception e, int id) {
            BaseApplication.f("\u7f51\u7edc\u8bf7\u6c42\u51fa\u9519!");
        }

        @Override
        public void onResponse(String response, int id) {
            a(response);
        }


        public void a(String str) {
            g();
            String a = ce.a.a(str, PhoneLoginActivity.this);
            if (a != null) {
                AppContext.c().a((UserBean) new Gson().fromJson(a, UserBean.class));
                w.d(PhoneLoginActivity.this);
                j.a().a(PhoneLoginActivity.this);
                i.removeCallbacks(d);
                finish();
            }
        }
    };
    private Handler i = new Handler() {

        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    };
    @BindView(R.id.btn_phone_login_send_code)
    Button mBtnSendCode;
    @BindView(R.id.et_logincode)
    EditText mEtCode;
    @BindView(R.id.et_loginphone)
    EditText mEtUserPhone;

    protected int c() {
        return R.layout.activity_login;
    }

    public void initView() {
        this.mBtnSendCode.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                h();
            }
        });
    }

    private void h() {
        this.f = this.mEtUserPhone.getText().toString();
        if (this.f.equals("") || this.f.length() != 11) {
            AppContext.a((Activity) this, getString(R.string.plasecheckyounumiscorrect));
            return;
        }
        AppContext.a((Activity) this, getString(R.string.codehasbeensend));
        ce.b.a(this.f);
        this.mBtnSendCode.setEnabled(false);
        this.mBtnSendCode.setTextColor(getResources().getColor(R.color.white));
        this.mBtnSendCode.setText("\u91cd\u65b0\u83b7\u53d6\u9a8c\u8bc1\u7801(" + this.e + ")");
        this.i.postDelayed(this.d, 1000);
    }

    public void initData() {
        a("\u4f7f\u7528\u624b\u673a\u53f7\u7801\u767b\u9646");
    }

    @OnClick({R.id.btn_dologin})
    public void onClick(View view) {
        if (!i()) {
            this.f = this.mEtUserPhone.getText().toString();
            this.g = this.mEtCode.getText().toString();
            c((int) R.string.loading);
            ce.b.a(this.f, this.g, this.h);
        }
    }

    private boolean i() {
        if (!s.j()) {
            BaseApplication.d(R.string.tip_no_internet);
            return true;
        } else if (this.mEtUserPhone.length() == 0) {
            this.mEtUserPhone.setError("\u8bf7\u8f93\u5165\u624b\u673a\u53f7\u7801/\u7528\u6237\u540d");
            this.mEtUserPhone.requestFocus();
            return true;
        } else if (this.mEtCode.length() != 0) {
            return false;
        } else {
            this.mEtCode.setError("\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801");
            this.mEtCode.requestFocus();
            return true;
        }
    }

    protected boolean e() {
        return true;
    }
}
