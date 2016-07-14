package com.weilian.phonelive.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;
import com.weilian.phonelive.bean.OrderBean;
import com.weilian.phonelive.bean.PrivateChatUserBean;
import com.weilian.phonelive.bean.UserHomePageBean;
import com.weilian.phonelive.widget.AvatarView;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.List;
import okhttp3.Call;

public class HomePageActivity extends BaseActivity {
    AvatarView[] d = new AvatarView[3];
    private int e;
    private UserHomePageBean f;
    @BindView(R.id.ll_default_video)
    LinearLayout mDefaultVideoBg;
    @BindView(R.id.tv_home_page_menu_follow)
    TextView mFollowState;
    @BindView(R.id.ll_home_page_index)
    LinearLayout mHomeIndexPage;
    @BindView(R.id.ll_home_page_video)
    LinearLayout mHomeVideoPage;
    @BindView(R.id.tv_home_page_index_btn)
    TextView mPageIndexBtn;
    @BindView(R.id.tv_home_page_video_btn)
    TextView mPageVideoBtn;
    @BindView(R.id.tv_home_page_send_num)
    TextView mSendNum;
    @BindView(R.id.tv_home_page_black_state)
    TextView mTvBlackState;
    @BindView(R.id.tv_home_page_fans)
    TextView mUFansNum;
    @BindView(R.id.tv_home_page_follow)
    TextView mUFollowNum;
    @BindView(R.id.av_home_page_uhead)
    AvatarView mUHead;
    @BindView(R.id.iv_home_page_level)
    ImageView mULevel;
    @BindView(R.id.tv_home_page_uname)
    TextView mUNice;
    @BindView(R.id.tv_home_page_num)
    TextView mUNum;
    @BindView(R.id.iv_home_page_sex)
    ImageView mUSex;
    @BindView(R.id.tv_home_page_sign)
    TextView mUSign;
    @BindView(R.id.tv_home_page_sign2)
    TextView mUSign2;

    protected int c() {
        return R.layout.activity_home;
    }

    public void initView() {
        this.d[0] = (AvatarView) findViewById(R.id.av_home_page_order1);
        this.d[1] = (AvatarView) findViewById(R.id.av_home_page_order2);
        this.d[2] = (AvatarView) findViewById(R.id.av_home_page_order3);
    }

    public void initData() {
        this.e = getIntent().getIntExtra("uid", 0);
        ce.b.c(AppContext.c().i(), this.e, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

            public void a(String str) {
                String a = ce.a.a(str, HomePageActivity.this);
                if (a != null) {
                    f = (UserHomePageBean) new Gson().fromJson(a, UserHomePageBean.class);
                    h();
                }
            }
        });
    }

    private void h() {
        this.mSendNum.setText(getString(R.string.send) + "" + this.f.getConsumption());
        this.mUHead.setAvatarUrl(this.f.getAvatar());
        this.mUNice.setText(this.f.getUser_nicename());
        this.mUSex.setImageResource(this.f.getSex() == 1 ? R.drawable.global_male : R.drawable.global_female);
        this.mULevel.setImageResource(com.weilian.phonelive.ui.c.a[this.f.getLevel()]);
        this.mUFansNum.setText(getString(R.string.fans) + ":" + this.f.getFansnum());
        this.mUFollowNum.setText(getString(R.string.attention) + ":" + this.f.getAttentionnum());
        this.mUSign.setText(this.f.getSignature());
        this.mUSign2.setText(this.f.getSignature());
        this.mUNum.setText(this.f.getId() + "");
        this.mFollowState.setText(this.f.getIsattention() == 0 ? getString(R.string.follow2) : getString(R.string.alreadyfollow));
        this.mTvBlackState.setText(this.f.getIsblack() == 0 ? getString(R.string.pullblack) : getString(R.string.relieveblack));
        List coinrecord3 = this.f.getCoinrecord3();
        for (int i = 0; i < coinrecord3.size(); i++) {
            this.d[i].setAvatarUrl(((OrderBean) coinrecord3.get(i)).getAvatar());
        }
    }

    @OnClick({R.id.ll_home_page_menu_lahei, R.id.ll_home_page_menu_privatechat, R.id.tv_home_page_menu_follow, R.id.rl_home_pager_yi_order, R.id.tv_home_page_follow, R.id.tv_home_page_index_btn, R.id.tv_home_page_video_btn, R.id.iv_home_page_back, R.id.tv_home_page_fans})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_home_page_index_btn:
                this.mHomeIndexPage.setVisibility(View.VISIBLE);
                this.mHomeVideoPage.setVisibility(View.GONE);
                this.mPageIndexBtn.setTextColor(getResources().getColor(R.color.global));
                this.mPageVideoBtn.setTextColor(getResources().getColor(R.color.black));
                return;
            case R.id.tv_home_page_video_btn:
                this.mHomeIndexPage.setVisibility(View.GONE);
                this.mHomeVideoPage.setVisibility(View.VISIBLE);
                this.mPageIndexBtn.setTextColor(getResources().getColor(R.color.black));
                this.mPageVideoBtn.setTextColor(getResources().getColor(R.color.global));
                return;
            case R.id.tv_home_page_menu_follow:
                k();
                return;
            case R.id.ll_home_page_menu_privatechat:
                j();
                return;
            case R.id.ll_home_page_menu_lahei:
                i();
                return;
            case R.id.iv_home_page_back:
                finish();
                return;
            case R.id.tv_home_page_follow:
                w.d(this, this.e);
                return;
            case R.id.tv_home_page_fans:
                w.c((Context) this, this.e);
                return;
            case R.id.rl_home_pager_yi_order:
                w.e(this, this.e);
                return;
            default:
                return;
        }
    }

    private void i() {
        ce.b.j(AppContext.c().i(), this.e, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

            public void a(String str) {
                if (ce.a.a(str, HomePageActivity.this) != null) {
                    AppContext.a(HomePageActivity.this, f.getIsblack() == 0 ? "\u62c9\u9ed1\u6210\u529f" : "\u89e3\u9664\u62c9\u9ed1");
                    f.setIsblack(f.getIsblack() == 0 ? 1 : 0);
                    mTvBlackState.setText(f.getIsblack() == 0 ? getString(R.string.pullblack) : getString(R.string.relieveblack));
                }
            }
        });
    }

    private void j() {
        if (this.f != null) {
            ce.b.f(AppContext.c().i(), this.f.getId(), new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    a(response);
                }


                public void a(String str) {
                    String a = ce.a.a(str, HomePageActivity.this);
                    if (a != null) {
                        w.a(HomePageActivity.this, (PrivateChatUserBean) new Gson().fromJson(a, PrivateChatUserBean.class));
                    }
                }
            });
        }
    }

    private void k() {
        ce.b.b(AppContext.c().i(), this.e, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

            public void a(String str) {
                f.setIsattention(f.getIsattention() == 0 ? 1 : 0);
                mFollowState.setText(f.getIsattention() == 0 ? getString(R.string.follow2) : getString(R.string.alreadyfollow));
            }
        });
    }

    protected boolean b() {
        return false;
    }
}
