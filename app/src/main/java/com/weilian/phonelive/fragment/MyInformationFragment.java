package com.weilian.phonelive.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.BindView;
import com.lzfutil.util.q;
import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.weilian.phonelive.ActivityManager;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseFragment;
import com.weilian.phonelive.bean.OrderBean;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.widget.AvatarView;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.List;
import okhttp3.Call;

public class MyInformationFragment extends BaseFragment {
    StringCallback h = new StringCallback() {

        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {
            a(response);
        }

        public void a(String str) {
            String a = ce.a.a(str, getActivity());
            if (a == null) {
                w.c(getActivity());
                getActivity().finish();
                return;
            }
            j = (UserBean) new Gson().fromJson(a, UserBean.class);
            AppContext.c().b(j);
            mLiveNum.setText(getString(R.string.live) + ":" + j.getLiverecordnum());
            mFollowNum.setText(getString(R.string.follow2) + ":" + j.getAttentionnum());
            mFansNum.setText(getString(R.string.fans) + ":" + j.getFansnum());
            mSendNum.setText(j.getConsumption());
            if (j.getCoinrecord3().size() > 0) {
                List coinrecord3 = j.getCoinrecord3();
                for (int i = 0; i < coinrecord3.size(); i++) {
                    l[i].setAvatarUrl(((OrderBean) coinrecord3.get(i)).getAvatar());
                }
            }
        }
    };
    private boolean i;
    private UserBean j;
    private AsyncTask<String, Void, UserBean> k;
    private AvatarView[] l;
    @BindView(R.id.tv_info_u_fans_num)
    TextView mFansNum;
    @BindView(R.id.tv_info_u_follow_num)
    TextView mFollowNum;
    @BindView(R.id.iv_avatar)
    AvatarView mIvAvatar;
    @BindView(R.id.iv_editInfo)
    ImageView mIvEditInfo;
    @BindView(R.id.iv_gender)
    ImageView mIvGender;
    @BindView(R.id.tv_info_u_live_num)
    TextView mLiveNum;
    @BindView(R.id.ll_loginout)
    LinearLayout mLoginOut;
    @BindView(R.id.tv_mes)
    View mMesView;
    @BindView(R.id.iv_info_private_core)
    ImageView mPrivateCore;
    @BindView(R.id.tv_send)
    TextView mSendNum;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_signature)
    TextView mTvSignature;
    @BindView(R.id.tv_id)
    TextView mUId;
    @BindView(R.id.ll_user_container)
    View mUserContainer;
    @BindView(R.id.rl_user_unlogin)
    View mUserUnLogin;
    @BindView(R.id.rootview)
    LinearLayout rootView;

    private void g() {
        if (this.i) {
            this.mUserContainer.setVisibility(View.GONE);
            this.mUserUnLogin.setVisibility(View.VISIBLE);
            return;
        }
        this.mUserContainer.setVisibility(View.VISIBLE);
        this.mUserUnLogin.setVisibility(View.GONE);
    }

    public void onResume() {
        super.onResume();
        f();
    }

    public void f() {
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_my_information, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        a(inflate);
        initData();
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(true);
    }

    public void onStart() {
        UserBean g = AppContext.c().g();
        this.j.setUser_nicename(g.getUser_nicename());
        this.j.setSignature(g.getSignature());
        super.onStart();
    }

    public void a(View view) {
        view.findViewById(R.id.ll_live).setOnClickListener(this);
        view.findViewById(R.id.ll_following).setOnClickListener(this);
        view.findViewById(R.id.ll_fans).setOnClickListener(this);
        view.findViewById(R.id.ll_profit).setOnClickListener(this);
        view.findViewById(R.id.ll_setting).setOnClickListener(this);
        view.findViewById(R.id.ll_level).setOnClickListener(this);
        view.findViewById(R.id.ll_diamonds).setOnClickListener(this);
        view.findViewById(R.id.rl_info_order_btn).setOnClickListener(this);
        this.mUserUnLogin.setOnClickListener(this);
        this.mLoginOut.setOnClickListener(this);
        this.mIvEditInfo.setOnClickListener(this);
        this.mPrivateCore.setOnClickListener(this);
        this.l = new AvatarView[3];
        this.l[0] = (AvatarView) view.findViewById(R.id.iv_info_order_no1);
        this.l[1] = (AvatarView) view.findViewById(R.id.iv_info_order_no2);
        this.l[2] = (AvatarView) view.findViewById(R.id.iv_info_order_no3);
    }

    private void h() {
        if (this.j != null) {
            this.mIvAvatar.setAvatarUrl(this.j.getAvatar());
            this.mTvName.setText(this.j.getUser_nicename());
            this.mIvGender.setImageResource(q.intV(Integer.valueOf(this.j.getSex())) != 2 ? R.drawable.global_male : R.drawable.global_female);
            this.mTvSignature.setText("".equals(this.j.getSignature()) ? getString(R.string.defaultsign) : this.j.getSignature());
            this.mUId.setText("ID:" + this.j.getId());
        }
    }

    protected void a(boolean z) {
        if (AppContext.c().k()) {
            this.i = false;
            i();
        } else {
            this.i = true;
        }
        g();
    }

    private void i() {
        ce.b.a(AppContext.c().i(), AppContext.c().j(), this.h);
    }

    private String j() {
        return "my_information" + AppContext.c().i();
    }

    public void onClick(View view) {
        Bundle bundle;
        switch (view.getId()) {
            case R.id.ll_profit:
                bundle = new Bundle();
                bundle.putString("votes", this.j.getVotes());
                w.b(getActivity(), bundle);
                return;
            case R.id.ll_level:
                w.a(getActivity(), AppContext.c().i());
                return;
            case R.id.ll_diamonds:
                bundle = new Bundle();
                bundle.putString("diamonds", this.j.getCoin());
                w.a(getActivity(), bundle);
                return;
            case R.id.ll_setting:
                w.f(getActivity());
                return;
            case R.id.ll_loginout:
                com.lzfutil.util.j.b(getActivity());
                getActivity().finish();
                return;
            case R.id.iv_info_private_core:
                w.g(getActivity(), this.j.getId());
                return;
            case R.id.ll_live:
                w.f(getActivity(), this.j.getId());
                return;
            case R.id.ll_following:
                w.d(getActivity(), this.j.getId());
                return;
            case R.id.ll_fans:
                w.c(getActivity(), this.j.getId());
                return;
            case R.id.rl_user_unlogin:
                ActivityManager.getAppManager().finishAllActivity();
                w.c(getActivity());
                getActivity().finish();
                return;
            case R.id.iv_editInfo:
                w.e(getActivity());
                return;
            case R.id.rl_info_order_btn:
                w.e(getActivity(), this.j.getId());
                return;
            default:
                return;
        }
    }

    public void initData() {
        this.j = AppContext.c().g();
        h();
    }
}
