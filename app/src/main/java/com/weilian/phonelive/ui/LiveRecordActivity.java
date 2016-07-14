package com.weilian.phonelive.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;

import com.google.gson.Gson;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;
import com.weilian.phonelive.bean.LiveRecordBean;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LiveRecordActivity extends BaseActivity {
    ArrayList<LiveRecordBean> d = new ArrayList();
    @BindView(R.id.lv_live_record)
    ListView mLiveRecordList;

    class a extends BaseAdapter {
        final /* synthetic */ LiveRecordActivity a;

        class aHolder {
            public TextView a;
            public TextView b;
            public TextView c;
            final /* synthetic */ a d;

            aHolder(a aVar) {
                this.d = aVar;
            }
        }

        a(LiveRecordActivity liveRecordActivity) {
            this.a = liveRecordActivity;
        }

        public int getCount() {
            return this.a.d.size();
        }

        public Object getItem(int i) {
            return this.a.d.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            aHolder aVar;
            if (view == null) {
                view = View.inflate(this.a, R.layout.item_live_record, null);
                aVar = new aHolder(this);
                aVar.b = (TextView) view.findViewById(R.id.tv_item_live_record_num);
                aVar.a = (TextView) view.findViewById(R.id.tv_item_live_record_time);
                aVar.c = (TextView) view.findViewById(R.id.tv_item_live_record_title);
                view.setTag(aVar);
            } else {
                aVar = (aHolder) view.getTag();
            }
            LiveRecordBean liveRecordBean = (LiveRecordBean) this.a.d.get(i);
            aVar.b.setText(liveRecordBean.getNums());
            aVar.c.setText(liveRecordBean.getTitle());
            aVar.a.setText(liveRecordBean.getDatetime());
            return view;
        }
    }

    protected int c() {
        return R.layout.activity_live_record;
    }

    public void initView() {
    }

    public void initData() {
        a(getString(R.string.liverecord));
        ce.b.f(getIntent().getIntExtra("uid", 0), new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }


            public void a(String str) {
                String a = ce.a.a(str, LiveRecordActivity.this);
                if (a != null) {
                    try {
                        JSONArray jSONArray = new JSONObject(a).getJSONArray("list");
                        if (jSONArray.length() > 0) {
                            Gson gson = new Gson();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                d.add(gson.fromJson(jSONArray.getString(i), LiveRecordBean.class));
                            }
                        }
                        h();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void h() {
        this.mLiveRecordList.setAdapter(new a(this));
    }

    public void onClick(View view) {
    }

    protected boolean e() {
        return true;
    }
}
