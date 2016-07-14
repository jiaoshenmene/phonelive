package com.weilian.phonelive.ui;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;
import com.weilian.phonelive.bean.ProfitBean;
import com.weilian.phonelive.bean.UserBean;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;

public class ProfitActivity extends BaseActivity {
    private TextView d;
    private ProfitBean e;
    private UserBean f;
    @BindView(R.id.tv_profit_canwithdraw)
    TextView mCanwithDraw;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_text)
    TextView mSaveInfo;
    @BindView(R.id.tv_votes)
    TextView mVotes;
    @BindView(R.id.tv_profit_withdraw)
    TextView mWithDraw;
    @BindView(R.id.TextView)
    TextView text;

    protected int c() {
        return R.layout.activity_profit;
    }

    public void initView() {
        this.mIvBack.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initData() {
        this.f = AppContext.c().g();
        this.d.setText(getString(R.string.myprofit));
        this.mSaveInfo.setVisibility(View.GONE);
        this.mSaveInfo.setText("\u63d0\u73b0\u8bb0\u5f55");
        this.mVotes.setText(getIntent().getBundleExtra("USERINFO").getString("votes"));
        ce.b.b(AppContext.c().i(), AppContext.c().j(), new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

            public void a(String str) {
                String a = ce.a.a(str, ProfitActivity.this);
                if (a != null) {
                    e = (ProfitBean) new Gson().fromJson(a, ProfitBean.class);
                    h();
                }
            }
        });
    }

    private void h() {
        this.mCanwithDraw.setText(this.e.getCanwithdraw());
        this.mWithDraw.setText(this.e.getWithdraw());
        this.mVotes.setText(this.e.getVotes());
    }

    @OnClick({R.id.ll_profit_cash, R.id.TextView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_profit_cash:
                ce.b.c(this.f.getId(), this.f.getToken(), new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        a(response);
                    }


                    public void a(String str) {
                        String a = ce.a.a(str, ProfitActivity.this);
                        if (a != null) {
                            AppContext.a(ProfitActivity.this, a);
                            initData();
                        }
                    }
                });
                return;
            case R.id.TextView:
                w.a(this, "http://www.51rexiu.com//appcmf/index.php?LoadedFrom=portal&EVENT_PONG=page&Editor=newslist", "");
                return;
            default:
                return;
        }
    }

    protected void a(ActionBar actionBar) {
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView((int) R.layout.view_actionbar_title);
        this.d = (TextView) actionBar.getCustomView().findViewById(R.id.tv_actionBarTitle);
    }
}
