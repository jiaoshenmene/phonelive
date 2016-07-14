package com.weilian.phonelive.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseFragment;
import com.weilian.phonelive.bean.AreaBean;
import com.weilian.phonelive.viewpagerfragment.IndexPagerFragment;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;

public class SelectAreaFragment extends BaseFragment {
    List<AreaBean> h = new ArrayList();
    @BindView(R.id.iv_choice_all)
    ImageView mIconAll;
    @BindView(R.id.iv_choice_femal)
    ImageView mIconFemal;
    @BindView(R.id.iv_choice_male)
    ImageView mIconMale;
    @BindView(R.id.lv_area)
    ListView mLvArea;
    @BindView(R.id.tv_choice_all)
    TextView mTextAll;
    @BindView(R.id.tv_choice_femal)
    TextView mTextFemal;
    @BindView(R.id.tv_choice_male)
    TextView mTextMale;

    class a extends BaseAdapter {
        final /* synthetic */ SelectAreaFragment a;

        class aHolder {
            TextView a;
            TextView b;
            final /* synthetic */ a c;

            aHolder(a aVar) {
                this.c = aVar;
            }
        }

        a(SelectAreaFragment selectAreaFragment) {
            this.a = selectAreaFragment;
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
                view = View.inflate(this.a.getActivity(), R.layout.item_hot_area, null);
                aVar = new aHolder(this);
                aVar.a = (TextView) view.findViewById(R.id.tv_area);
                aVar.b = (TextView) view.findViewById(R.id.tv_area_live_num);
                view.setTag(aVar);
            } else {
                aVar = (aHolder) view.getTag();
            }
            AreaBean areaBean = (AreaBean) this.a.h.get(i);
            if (IndexPagerFragment.i.equals(areaBean.getProvince())) {
                aVar.b.setText(areaBean.getTotal() + "  \u221a");
            } else if (IndexPagerFragment.i.equals("") && areaBean.getProvince().equals("\u70ed\u95e8")) {
                aVar.b.setText(areaBean.getTotal() + "  \u221a");
            } else {
                aVar.b.setText(areaBean.getTotal() + "");
            }
            aVar.a.setText(areaBean.getProvince());
            return view;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_area_class, null);
        ButterKnife.bind((Object) this, inflate);
        a(inflate);
        initData();
        return inflate;
    }

    public void initData() {
        ce.b.f(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a((String) response);
            }


            public void onError(Call call, Exception exception) {
            }

            public void a(String str) {
                String a = ce.a.a(str, getActivity());
                if (a != null) {
                    try {
                        JSONArray jSONArray = new JSONArray(a);
                        Gson gson = new Gson();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            h.add(gson.fromJson(jSONArray.getString(i), AreaBean.class));
                        }
                        f();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void f() {
        this.mLvArea.setAdapter(new a(this));
    }

    @OnClick({R.id.iv_choice_femal, R.id.iv_choice_all, R.id.iv_choice_male, R.id.btn_area_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_choice_femal:
                c(2);
                return;
            case R.id.iv_choice_all:
                c(0);
                return;
            case R.id.iv_choice_male:
                c(1);
                return;
            case R.id.btn_area_complete:
                getActivity().finish();
                return;
            default:
                return;
        }
    }

    private void c(int i) {
        if (i == 2) {
            IndexPagerFragment.h = 2;
            this.mIconFemal.setImageResource(R.drawable.choice_sex_femal);
            this.mIconMale.setImageResource(R.drawable.choice_sex_un_male);
            this.mIconAll.setImageResource(R.drawable.choice_sex_un_all);
            this.mTextFemal.setTextColor(getContext().getResources().getColor(R.color.global));
            this.mTextMale.setTextColor(getContext().getResources().getColor(R.color.home_page_text_color));
            this.mTextAll.setTextColor(getContext().getResources().getColor(R.color.home_page_text_color));
        } else if (i == 0) {
            IndexPagerFragment.h = 0;
            this.mIconAll.setImageResource(R.drawable.choice_sex_all);
            this.mIconMale.setImageResource(R.drawable.choice_sex_un_male);
            this.mIconFemal.setImageResource(R.drawable.choice_sex_un_femal);
            this.mTextAll.setTextColor(getContext().getResources().getColor(R.color.global));
            this.mTextMale.setTextColor(getContext().getResources().getColor(R.color.home_page_text_color));
            this.mTextFemal.setTextColor(getContext().getResources().getColor(R.color.home_page_text_color));
        } else {
            IndexPagerFragment.h = 1;
            this.mIconMale.setImageResource(R.drawable.choice_sex_male);
            this.mIconAll.setImageResource(R.drawable.choice_sex_un_all);
            this.mIconFemal.setImageResource(R.drawable.choice_sex_un_femal);
            this.mTextMale.setTextColor(getContext().getResources().getColor(R.color.global));
            this.mTextMale.setTextColor(getContext().getResources().getColor(R.color.home_page_text_color));
            this.mTextAll.setTextColor(getContext().getResources().getColor(R.color.home_page_text_color));
        }
    }

    public void a(View view) {
        c(IndexPagerFragment.h);
        this.mLvArea.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                IndexPagerFragment.i = ((AreaBean) h.get(i)).getProvince();
                getActivity().finish();
            }
        });
    }
}
