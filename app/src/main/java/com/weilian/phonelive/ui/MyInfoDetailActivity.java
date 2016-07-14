package com.weilian.phonelive.ui;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.widget.AvatarView;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;

public class MyInfoDetailActivity extends BaseActivity {
    private UserBean d;
    MyInfoDetailActivity ins = null;
    private final StringCallback e = new StringCallback() {

        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {
            a(response);
        }


        public void a(String str) {
            if (ce.a.a(str, ins) != null) {
                d = (UserBean) new Gson().fromJson(ce.a.a(str, ins), UserBean.class);
                i();
            }
        }
    };
    @BindView(R.id.rl_userHead)
    RelativeLayout mRlUserHead;
    @BindView(R.id.rl_userNick)
    RelativeLayout mRlUserNick;
    @BindView(R.id.rl_userSign)
    RelativeLayout mRlUserSign;
    @BindView(R.id.av_userHead)
    AvatarView mUserHead;
    @BindView(R.id.tv_userNick)
    TextView mUserNick;
    @BindView(R.id.tv_userSign)
    TextView mUserSign;

    protected int c() {
        return R.layout.activity_myinfo_detail;
    }

    public void initView() {
        this.mRlUserNick.setOnClickListener(this);
        this.mRlUserSign.setOnClickListener(this);
        this.mRlUserHead.setOnClickListener(this);
    }

    public void initData() {
        b((int) R.string.editInfo);
        h();
    }

    private void h() {
        ce.b.a(AppContext.c().i(), AppContext.c().j(), this.e);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_userHead:
                w.a(this, this.d.getAvatar());
                return;
            case R.id.rl_userNick:
                w.a(this, "\u4fee\u6539\u6635\u79f0", getString(R.string.editnickpromp), this.d.getUser_nicename(), com.weilian.phonelive.ui.b.CHANG_NICK);
                return;
            case R.id.rl_userSign:
                w.a(this, "\u4fee\u6539\u7b7e\u540d", getString(R.string.editsignpromp), this.d.getSignature(), com.weilian.phonelive.ui.b.CHANG_SIGN);
                return;
            default:
                return;
        }
    }

    protected void onRestart() {
        this.d = AppContext.c().g();
        i();
        super.onRestart();
    }

    protected boolean e() {
        return true;
    }

    protected void onStart() {
        ins = this;
        if (this.d != null) {
            i();
        }
        super.onStart();
    }

    private void i() {
        this.mUserNick.setText(this.d.getUser_nicename());
        this.mUserSign.setText(this.d.getSignature());
        this.mUserHead.setAvatarUrl(this.d.getAvatar());
    }
}
