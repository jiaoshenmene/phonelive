package com.weilian.phonelive.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.BindView;
import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseFragment;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.ui.VideoPlayerActivity;
import com.weilian.phonelive.viewpagerfragment.IndexPagerFragment;
import com.weilian.phonelive.widget.AvatarView;
import com.weilian.phonelive.widget.LoadUrlImageView;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;

public class HotFragment extends BaseFragment implements OnRefreshListener {
    private static final int k = 20;
    private List<UserBean> h = new ArrayList();
    private LayoutInflater i;
    private aAdapter j;
    private int l = 0;
    private boolean m = true;
    @BindView(R.id.ll_hot_default_layout)
    LinearLayout mDefaultLayoutView;
    @BindView(R.id.lv_live_room)
    ListView mListUserRoom;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private StringCallback n = new StringCallback() {

        @Override
        public void onError(Call call, Exception e, int id) {
            AppContext.a(getActivity(), "\u83b7\u53d6\u6570\u636e\u5931\u8d25\u8bf7\u5237\u65b0\u91cd\u8bd5~");
            mSwipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onResponse(String response, int id) {
            a(response);
        }

        public void a(String str) {
            mSwipeRefreshLayout.setRefreshing(false);
            h.clear();
            String a = ce.a.a(str, getActivity());
            if (a != null) {
                try {
                    JSONArray jSONArray = new JSONArray(a);
                    if (jSONArray != null && jSONArray.length() > 0) {
                        for (int i = 0; i < jSONArray.length(); i++) {
                            h.add((UserBean) new Gson().fromJson(jSONArray.getJSONObject(i).toString(), UserBean.class));
                        }
                        f();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private class aAdapter extends BaseAdapter {
        final /* synthetic */ HotFragment a;

        private aAdapter(HotFragment hotFragment) {
            this.a = hotFragment;
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
            bHolder bVar;
            if (view == null) {
                view = this.a.i.inflate(R.layout.item_hot_user, null);
                bVar = new bHolder();
                bVar.a = (TextView) view.findViewById(R.id.tv_live_nick);
                bVar.b = (TextView) view.findViewById(R.id.tv_live_local);
                bVar.c = (TextView) view.findViewById(R.id.tv_live_usernum);
                bVar.f = (AvatarView) view.findViewById(R.id.iv_live_user_head);
                bVar.e = (LoadUrlImageView) view.findViewById(R.id.iv_live_user_pic);
                bVar.d = (TextView) view.findViewById(R.id.tv_hot_room_title);
                view.setTag(bVar);
            } else {
                bVar = (bHolder) view.getTag();
            }
            UserBean userBean = (UserBean) this.a.h.get(i);
            bVar.a.setText(userBean.getUser_nicename());
            bVar.b.setText(userBean.getCity());
            bVar.e.setImageLoadUrl(userBean.getAvatar());
            bVar.f.setAvatarUrl(userBean.getAvatar());
            bVar.c.setText(String.valueOf(userBean.getNums()));
            if (userBean.getTitle() != null) {
                bVar.d.setVisibility(View.VISIBLE);
                bVar.d.setText(userBean.getTitle());
            }
            return view;
        }
    }

    private class bHolder {
        public TextView a;
        public TextView b;
        public TextView c;
        public TextView d;
        public LoadUrlImageView e;
        public AvatarView f;

    }


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_index_hot, null);
        ButterKnife.bind((Object) this, inflate);
        this.i = layoutInflater;
        initView();
        initData();
        return inflate;
    }

    private void initView() {
        this.mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.actionbarbackground));
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mListUserRoom.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                UserBean userBean = (UserBean) h.get(i - 1);
                Bundle bundle = new Bundle();
                bundle.putSerializable(VideoPlayerActivity.C, userBean);
                w.c(getActivity(), bundle);
            }
        });
    }

    public void initData() {
        this.j = new aAdapter(this);
        ce.b.b(this.n);
        this.mListUserRoom.addHeaderView(this.i.inflate(R.layout.view_hot_rollpic, null));
    }

    private void f() {
        this.mDefaultLayoutView.setVisibility(View.GONE);
        this.mListUserRoom.setVisibility(View.VISIBLE);
        if (this.mSwipeRefreshLayout.isRefreshing()) {
            this.j.notifyDataSetChanged();
        } else {
            this.mListUserRoom.setAdapter(this.j);
        }
    }

    public void onResume() {
        super.onResume();
        if (IndexPagerFragment.h != 0 || !IndexPagerFragment.i.equals("")) {
            a(IndexPagerFragment.h, IndexPagerFragment.i);
        }
    }

    public void a(int i, String str) {
        ce.b.d(i, str, this.n);
    }

    public void onRefresh() {
        ce.b.d(IndexPagerFragment.h, IndexPagerFragment.i, this.n);
    }
}
