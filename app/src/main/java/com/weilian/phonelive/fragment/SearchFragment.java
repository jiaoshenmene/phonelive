package com.weilian.phonelive.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;
import cc.f;
import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseFragment;
import com.weilian.phonelive.bean.UserBean;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;

public class SearchFragment extends BaseFragment {
    private List<UserBean> h = new ArrayList();
    @BindView(R.id.lv_search)
    ListView mLvSearch;
    @BindView(R.id.et_search_input)
    EditText mSearchKey;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_search_index, null);
        ButterKnife.bind((Object) this, inflate);
        a(inflate);
        initData();
        return inflate;
    }

    public void a(View view) {
        this.mLvSearch.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                w.b(getActivity(), ((UserBean) h.get(i)).getId());
            }
        });
    }

    public void initData() {
    }

    @OnClick({R.id.iv_private_chat_back, R.id.tv_search_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_private_chat_back:
                getActivity().finish();
                return;
            case R.id.tv_search_btn:
                f();
                return;
            default:
        }
        return;
    }

    private void f() {
        e();
        String trim = this.mSearchKey.getText().toString().trim();
        if (!trim.equals("")) {
            ce.b.a(trim, new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    d();
                }

                @Override
                public void onResponse(String response, int id) {
                    a(response);
                }

                public void a(String str) {
                    d();
                    String a = ce.a.a(str, getActivity());
                    if (a != null) {
                        Gson gson = new Gson();
                        try {
                            JSONArray jSONArray = new JSONArray(a);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                h.add(gson.fromJson(jSONArray.getString(i), UserBean.class));
                            }
                            g();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, AppContext.c().i());
        }
    }

    private void g() {
        this.mLvSearch.setAdapter(new f(this.h));
    }
}
