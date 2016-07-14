package com.weilian.phonelive.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.http.HttpStatus;

public class MyDiamondsActivity extends BaseActivity {
    private RelativeLayout d;
    private RelativeLayout e;
    private List<b> f;
    private TextView g;
    private TextView h;
    private final int i = 1;
    private final int j = 2;
    private int k = 1;
    private View l;
    private cd.a m;
    @BindView(R.id.lv_select_num_list)
    ListView mSelectNumListItem;
    private int[] n;
    private int[] o;
    private cb.a p;

    private class aAdapter extends BaseAdapter {
        final /* synthetic */ MyDiamondsActivity a;

        private class aHolder {
            TextView a;
            TextView b;
            TextView c;

            private aHolder() {
            }
        }

        private aAdapter(MyDiamondsActivity myDiamondsActivity) {
            this.a = myDiamondsActivity;
        }

        public int getCount() {
            return this.a.f.size();
        }

        public Object getItem(int i) {
            return this.a.f.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            aHolder aVar;
            b bVar = (b) this.a.f.get(i);
            if (view == null) {
                view = this.a.getLayoutInflater().inflate(R.layout.item_select_num, null);
                aHolder aVar2 = new aHolder();
                aVar2.a = (TextView) view.findViewById(R.id.tv_diamondsnum);
                aVar2.b = (TextView) view.findViewById(R.id.tv_price_explain);
                aVar2.c = (TextView) view.findViewById(R.id.bt_preice_text);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (aHolder) view.getTag();
            }
            aVar.a.setText(bVar.c() + "");
            aVar.b.setText(bVar.b());
            aVar.c.setText(bVar.d());
            return view;
        }
    }

    private class b {
        public int a;
        public String b;
        public int c;
        public String d;

        private b( int i, String str, int i2, String str2) {
            this.a = i;
            this.b = str;
            this.c = i2;
            this.d = str2;
        }

        public int a() {
            return this.a;
        }

        public void a(int i) {
            this.a = i;
        }

        public String b() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
        }

        public int c() {
            return this.c;
        }

        public void b(int i) {
            this.c = i;
        }

        public String d() {
            return this.d;
        }

        public void b(String str) {
            this.d = str;
        }
    }

    protected int c() {
        return R.layout.activity_diamonds;
    }

    public void initView() {
        this.l = getLayoutInflater().inflate(R.layout.view_diamonds_head, null);
        this.d = (RelativeLayout) this.l.findViewById(R.id.rl_wxpay);
        this.e = (RelativeLayout) this.l.findViewById(R.id.rl_alipay);
        this.g = (TextView) this.l.findViewById(R.id.tv_payname);
        this.h = (TextView) this.l.findViewById(R.id.tv_coin);
        this.mSelectNumListItem.addHeaderView(this.l);
        a(this.d, 0);
        a(this.e, 8);
        a(this.d);
        this.d.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                k = 1;
                a(d);
            }
        });
        this.e.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                k = 2;
                a(e);
            }
        });
        this.mSelectNumListItem.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (k == 1) {
                    AppContext.a(MyDiamondsActivity.this, "\u8bf7\u6682\u65f6\u4f7f\u7528\u652f\u4ed8\u5b9d\u652f\u4ed8\u3002\u3002\u3002");
                } else if (k == 2) {
                    m.a(String.valueOf(n[i - 1]), String.valueOf(o[i - 1]));
                } else {
                    p.a(String.valueOf(n[i - 1]), String.valueOf(o[i - 1]));
                }
            }
        });
    }

    public void initData() {
        h();
        this.m = new cd.a(this);
        a(getString(R.string.mydiamonds));
        this.o = new int[]{20, 60, HttpStatus.SC_MULTIPLE_CHOICES, 980, 2980, 5880, 15980};
        String[] strArr = new String[]{"\u65b0\u4eba\u793c\u5305,\u4ec51\u6b21\u673a\u4f1a", "", "", "\u8d60\u900110\u94bb\u77f3", "\u8d60\u900190\u94bb\u77f3", "\u8d60\u9001300\u94bb\u77f3", "\u8d60\u90011120\u94bb\u77f3"};
        this.n = new int[]{1, 6, 30, 98, 298, 588, 1598};
        this.f = new ArrayList();
        for (int i = 0; i < this.n.length; i++) {
            this.f.add(new b(n[i], strArr[i], this.o[i], this.n[i] + ".00"));
        }
        this.mSelectNumListItem.setAdapter(new aAdapter(this));
        this.h.setText(getIntent().getBundleExtra("USERINFO").getString("diamonds"));
    }

    private void h() {
        ce.b.j(AppContext.c().i(), new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

            public void a(String str) {
                String a = ce.a.a(str, MyDiamondsActivity.this);
                if (a != null) {
                    d(a);
                }
            }
        });
    }

    private void d(String str) {
        try {
            this.h.setText(new JSONObject(str).getString("coin"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
    }

    private void a(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.actionbarbackground));
        relativeLayout.getChildAt(1).setVisibility(View.VISIBLE);
        this.g.setText(getString(R.string.paymode) + (this.k == 1 ? getString(R.string.wxpay) : getString(R.string.alipay)));
        if (this.k == 1) {
            this.e.setBackgroundColor(getResources().getColor(R.color.white));
        } else {
            this.d.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    private ImageView a(RelativeLayout relativeLayout, int i) {
        ImageView imageView = new ImageView(this);
        relativeLayout.addView(imageView);
        imageView.setVisibility(i);
        imageView.setImageResource(R.drawable.pay_choose);
        LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
        layoutParams.width = 60;
        layoutParams.height = 60;
        layoutParams.addRule(11, -1);
        layoutParams.addRule(12, -1);
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    protected boolean e() {
        return true;
    }

    public void a(boolean z, String str) {
        if (z) {
            this.h.setText((Integer.parseInt(this.h.getText().toString()) + Integer.parseInt(str)) + "");
        }
    }
}
