package com.weilian.phonelive.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import butterknife.ButterKnife;
import butterknife.BindView;
import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseFragment;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.ui.VideoPlayerActivity;
import com.weilian.phonelive.widget.LoadUrlImageView;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;

public class NewestFragment extends BaseFragment {
    List<UserBean> h = new ArrayList();
    private int i;
    @BindView(R.id.gv_newest)
    GridView mNewestLiveView;

    class a extends BaseAdapter {
        final /* synthetic */ NewestFragment a;

        class aHolder {
            public LoadUrlImageView a;
            final /* synthetic */ a b;

            aHolder(a aVar) {
                this.b = aVar;
            }
        }

        a(NewestFragment newestFragment) {
            this.a = newestFragment;
        }

        public int getCount() {
            return this.a.h.size();
        }

        public Object getItem(int i) {
            return this.a.h.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            aHolder aVar;
            if (view == null) {
                view = View.inflate(this.a.getActivity(), R.layout.item_newest_user, null);
                view.setLayoutParams(new LayoutParams(-1, this.a.i));
                aVar = new aHolder(this);
                aVar.a = (LoadUrlImageView) view.findViewById(R.id.iv_newest_item_user);
                aVar.a.getLayoutParams();
                view.setTag(aVar);
            } else {
                aVar = (aHolder) view.getTag();
            }
            aVar.a.setImageLoadUrl(((UserBean) this.a.h.get(i)).getAvatar());
            return view;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_newest, null);
        ButterKnife.bind(this, inflate);
        initData();
        a(inflate);
        return inflate;
    }

    public void initData() {
        f();
    }

    private void f() {
        ce.b.d(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }


            public void a(String str) {
                String a = ce.a.a(str, getActivity());
                if (a != null) {
                    try {
                        JSONArray jSONArray = new JSONArray(a);
                        Gson gson = new Gson();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            h.add(gson.fromJson(jSONArray.getString(i), UserBean.class));
                        }
                        g();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void g() {
        if (getActivity() != null) {
            this.i = getActivity().getWindowManager().getDefaultDisplay().getWidth() / 3;
            this.mNewestLiveView.setColumnWidth(this.i);
            this.mNewestLiveView.setAdapter(new a(this));
        }
    }

    public void a(View view) {
        this.mNewestLiveView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                UserBean userBean = (UserBean) h.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable(VideoPlayerActivity.C, userBean);
                w.c(getActivity(), bundle);
            }
        });
    }
}
