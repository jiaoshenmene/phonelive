package com.weilian.phonelive.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;
import com.weilian.phonelive.bean.OrderBean;
import com.weilian.phonelive.widget.CircleImageView;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.Core;

public class DedicateOrderActivity extends BaseActivity {
    private ArrayList<OrderBean> d = new ArrayList();
    private String e;
    @BindView(R.id.lv_order)
    ListView mOrderListView;
    @BindView(R.id.tv_order_total)
    TextView mOrderTotal;

    class a extends BaseAdapter {
        final /* synthetic */ DedicateOrderActivity a;

        class aHolder {
            public CircleImageView a;
            public ImageView b;
            public ImageView c;
            public TextView d;
            public TextView e;
            public TextView f;
            final /* synthetic */ a g;

            aHolder(a aVar) {
                this.g = aVar;
            }
        }

        a(DedicateOrderActivity dedicateOrderActivity) {
            this.a = dedicateOrderActivity;
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
            View inflate;
            if (i == 0) {
                inflate = View.inflate(this.a, R.layout.view_order_top1, null);
            } else if (i == 1) {
                inflate = View.inflate(this.a, R.layout.view_order_top2, null);
            } else if (i == 2) {
                inflate = View.inflate(this.a, R.layout.view_order_top3, null);
            } else {
                inflate = View.inflate(this.a, R.layout.item_order_user, null);
            }
            aHolder aVar = new aHolder(this);
            aVar.a = (CircleImageView) inflate.findViewById(R.id.ci_order_item_u_head);
            aVar.c = (ImageView) inflate.findViewById(R.id.tv_order_item_u_level);
            aVar.b = (ImageView) inflate.findViewById(R.id.iv_order_item_u_sex);
            aVar.d = (TextView) inflate.findViewById(R.id.tv_order_item_u_name);
            aVar.e = (TextView) inflate.findViewById(R.id.tv_order_item_u_gx);
            aVar.f = (TextView) inflate.findViewById(R.id.tv_order_item_u_no);
            inflate.setTag(aVar);
            OrderBean orderBean = (OrderBean) this.a.d.get(i);
            Core.getKJBitmap().display(aVar.a, orderBean.getAvatar());
            aVar.c.setImageResource(com.weilian.phonelive.ui.c.a[orderBean.getLevel()]);
            aVar.b.setImageResource(orderBean.getSex() == 1 ? R.drawable.global_male : R.drawable.global_female);
            aVar.d.setText(orderBean.getUser_nicename());
            aVar.e.setText(orderBean.getTotal());
            if (i > 2) {
                aVar.f.setText("No." + (i + 1));
            }
            return inflate;
        }
    }

    protected int c() {
        return R.layout.activity_order;
    }

    public void initView() {
    }

    public void initData() {
        a(getString(R.string.yporder));
        ce.b.e(getIntent().getIntExtra("uid", 0), new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a((String) response);
            }

            public void a(String str) {
                String a = ce.a.a(str, DedicateOrderActivity.this);
                if (a != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(a);
                        Gson gson = new Gson();
                        JSONArray jSONArray = jSONObject.getJSONArray("list");
                        e = jSONObject.getString("total");
                        for (int i = 0; i < jSONArray.length(); i++) {
                            OrderBean orderBean = (OrderBean) gson.fromJson(jSONArray.getString(i), OrderBean.class);
                            orderBean.setOrderNo(String.valueOf(i));
                            d.add(orderBean);
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
        this.mOrderTotal.setText(this.e + getString(R.string.yingpiao));
        if (this.d.size() > 0) {
            this.mOrderListView.setAdapter(new a(this));
            this.mOrderListView.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    w.b(DedicateOrderActivity.this, ((OrderBean) d.get(i)).getUid());
                }
            });
        }
    }

    public void onClick(View view) {
    }

    protected boolean e() {
        return true;
    }
}
