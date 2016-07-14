package com.weilian.phonelive.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.BindView;
import cc.a;
import com.lzfutil.util.t;
import com.google.gson.Gson;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseFragment;
import com.weilian.phonelive.bean.BlackBean;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;

public class BlackListFragment extends BaseFragment {
    private List<BlackBean> h = new ArrayList();
    private a iObj;
    private StringCallback j = new StringCallback() {

        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {
            a((String) response);
        }


        public void a(String str) {
            String a = ce.a.a(str, getActivity());
            if (a != null) {
                t.c(a);
                try {
                    JSONArray jSONArray = new JSONArray(a);
                    if (jSONArray.length() > 0) {
                        Gson gson = new Gson();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            h.add(gson.fromJson(jSONArray.getString(i), BlackBean.class));
                        }
                        f();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    @BindView(R.id.lv_black_list)
    ListView mLvBlackList;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_black_list, null);
        ButterKnife.bind((Object) this, inflate);
        a(inflate);
        initData();
        return inflate;
    }

    public void a(View view) {
    }

    public void initData() {
        AppContext.a(getActivity(), "\u957f\u6309\u53ef\u89e3\u9664\u62c9\u9ed1");
        this.iObj = new a(this.h);
        this.mLvBlackList.setAdapter(this.iObj);
        this.mLvBlackList.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                c(i);
                return false;
            }
        });
        a(false);
    }

    private void c(final int i) {
        ce.b.j(AppContext.c().i(), ((BlackBean) this.h.get(i)).getId(), new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a((String) response);
            }


            public void a(String str) {
                AppContext.a(getActivity(), "\u89e3\u9664\u62c9\u9ed1\u6210\u529f");
                h.remove(i);
                iObj.notifyDataSetChanged();
            }
        });
    }

    protected void a(boolean z) {
        ce.b.i(AppContext.c().i(), this.j);
    }

    private void f() {
        this.iObj.notifyDataSetChanged();
    }
}
