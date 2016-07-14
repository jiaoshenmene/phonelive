package com.weilian.phonelive.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.BindView;
import cc.c;
import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseFragment;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.ui.VideoPlayerActivity;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AttentionFragment extends BaseFragment {
    List<UserBean> h = new ArrayList();
    private View i;
    @BindView(R.id.rl_attention_no_live)
    RelativeLayout mAttentionNoLiveShowView;
    @BindView(R.id.lv_attentions)
    ListView mLvAttentions;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mRefresh;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.i = layoutInflater.inflate(R.layout.fragment_attention, null);
        ButterKnife.bind((Object) this, this.i);
        a(this.i);
        initData();
        return this.i;
    }

    public void a(View view) {
        this.mRefresh.setColorSchemeColors(getResources().getColor(R.color.actionbarbackground));
        this.mRefresh.setOnRefreshListener(new OnRefreshListener() {

            public void onRefresh() {
                h.clear();
                initData();
            }
        });
    }

    public void initData() {
        if (AppContext.c().i() > 0) {
            ce.b.h(AppContext.c().i(), new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    a((String) response);
                }

                public void a(String str) {
                    int i = 0;
                    if (mRefresh.isRefreshing()) {
                        mRefresh.setRefreshing(false);
                    }
                    String a = ce.a.a(str, getActivity());
                    if (a != null) {
                        try {
                            JSONArray jSONArray = new JSONObject(a).getJSONArray("attentionlive");
                            if (jSONArray.length() != 0) {
                                Gson gson = new Gson();
                                while (i < jSONArray.length()) {
                                    h.add(gson.fromJson(jSONArray.getString(i), UserBean.class));
                                    i++;
                                }
                                f();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    private void f() {
        this.mAttentionNoLiveShowView.setVisibility(View.GONE);
        this.mLvAttentions.setVisibility(View.VISIBLE);
        this.mLvAttentions.setAdapter(new c(getActivity().getLayoutInflater(), this.h));
        this.mLvAttentions.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(VideoPlayerActivity.C, (Serializable) h.get(i));
                w.c(getActivity(), bundle);
            }
        });
    }
}
