package com.weilian.phonelive.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import butterknife.BindView;
import cc.f;
import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;
import com.weilian.phonelive.bean.UserBean;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;

public class FansActivity extends BaseActivity {
    private List<UserBean> d;
    @BindView(R.id.lv_fans)
    ListView mFansView;
    @BindView(R.id.sr_refresh)
    SwipeRefreshLayout mRefreshLayout;

    protected int c() {
        return R.layout.activity_fans;
    }

    public void initView() {
        this.mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.actionbarbackground));
        this.mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

            public void onRefresh() {
                d.clear();
                initData();
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void initData() {
        a(getResources().getString(R.string.fans));
        ce.b.d(getIntent().getIntExtra("uid", 0), AppContext.c().i(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

            public void a(String str) {
                String a = ce.a.a(str, FansActivity.this);
                if (a != null) {
                    try {
                        JSONArray jSONArray = new JSONArray(a);
                        if (jSONArray.length() > 0) {
                            Gson gson = new Gson();
                            d = new ArrayList();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                d.add(gson.fromJson(jSONArray.getString(i), UserBean.class));
                            }
                            h();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void h() {
        this.mFansView.setAdapter(new f(this.d));
        this.mFansView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                w.b(FansActivity.this, ((UserBean) d.get(i)).getId());
            }
        });
    }

    public void onClick(View view) {
    }

    protected boolean b() {
        return true;
    }

    protected boolean e() {
        return true;
    }
}
