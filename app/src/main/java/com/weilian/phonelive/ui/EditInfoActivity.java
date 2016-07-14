package com.weilian.phonelive.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;

import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;
import com.weilian.phonelive.bean.UserBean;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;

public class EditInfoActivity extends BaseActivity {
    public static final String d = "EDITKEY";
    public static final String e = "EDITACTION";
    public static final String f = "EDITPROMP";
    public static final String g = "EDITDEFAULT";
    StringCallback h = new StringCallback() {

        @Override
        public void onError(Call call, Exception e, int id) {
            b(getString(R.string.editfail));
        }

        @Override
        public void onResponse(String response, int id) {
            a(response);
        }

        public void a(String str) {
            ce.a.a(str, EditInfoActivity.this);
            b(getString(R.string.editsuccess));
            AppContext c = AppContext.c();
            UserBean g = c.g();
            if (k.equals("user_nicename")) {
                g.setUser_nicename(l);
            } else if (k.equals("signature")) {
                g.setSignature(l);
            }
            c.b(g);
        }
    };
    private TextView i;
    private Intent j;
    private String k;
    private String l;
    @BindView(R.id.edit_input)
    EditText mInPutText;
    @BindView(R.id.iv_editInfo_clean)
    ImageView mInfoClean;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_prompt)
    TextView mPrompt;
    @BindView(R.id.tv_text)
    TextView mSaveInfo;

    protected void a(Bundle bundle) {
        super.a(bundle);
        this.j = getIntent();
    }

    public void initView() {
        this.mSaveInfo.setOnClickListener(this);
        this.mIvBack.setOnClickListener(this);
        this.mInfoClean.setOnClickListener(this);
    }

    public void initData() {
        if (this.j != null) {
            this.i.setText(this.j.getStringExtra(e));
            this.mPrompt.setText(this.j.getStringExtra(f));
            this.mInPutText.setText(this.j.getStringExtra(g));
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_editInfo_clean:
                this.mInPutText.setText("");
                return;
            case R.id.iv_back:
                finish();
                return;
            case R.id.tv_text:
                h();
                return;
            default:
                return;
        }
    }

    private void h() {
        this.k = this.j.getStringExtra(d);
        this.l = this.mInPutText.getText().toString();
        if (this.k.equals("user_nicename") && this.l.length() > 15) {
            AppContext.a((Activity) this, "\u6635\u79f0\u957f\u5ea6\u8d85\u8fc7\u9650\u5236");
        } else if (!this.k.equals("signature") || this.l.length() <= 20) {
            ce.b.a(this.k, this.l, AppContext.c().i(), AppContext.c().j(), this.h);
        } else {
            AppContext.a((Activity) this, "\u7b7e\u540d\u957f\u5ea6\u8d85\u8fc7\u9650\u5236");
        }
    }

    protected int c() {
        return R.layout.activity_edit;
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void a(ActionBar actionBar) {
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView((int) R.layout.view_actionbar_title);
        this.i = (TextView) actionBar.getCustomView().findViewById(R.id.tv_actionBarTitle);
    }
}
