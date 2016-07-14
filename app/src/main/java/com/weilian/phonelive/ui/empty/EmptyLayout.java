package com.weilian.phonelive.ui.empty;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lzfutil.util.s;
import com.weilian.phonelive.R;

public class EmptyLayout extends LinearLayout implements OnClickListener {
    public static final int a = 4;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 5;
    public static final int f = 6;
    public ImageView g;
    private ProgressBar h;
    private boolean i = true;
    private final Context j;
    private OnClickListener k;
    private int l;
    private RelativeLayout m;
    private String n = "";
    private TextView o;

    public EmptyLayout(Context context) {
        super(context);
        this.j = context;
        f();
    }

    public EmptyLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = context;
        f();
    }

    private void f() {
        View inflate = View.inflate(this.j, R.layout.view_error_layout, null);
        this.g = (ImageView) inflate.findViewById(R.id.img_error_layout);
        this.o = (TextView) inflate.findViewById(R.id.tv_error_layout);
        this.m = (RelativeLayout) inflate.findViewById(R.id.pageerrLayout);
        this.h = (ProgressBar) inflate.findViewById(R.id.animProgress);
        setBackgroundColor(-1);
        setOnClickListener(this);
        this.g.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                if (i && k != null) {
                    k.onClick(view);
                }
            }
        });
        addView(inflate);
        a(this.j);
    }

    public void a(Context context) {
    }

    public void a() {
        this.l = a;
        setVisibility(View.GONE);
    }

    public int getErrorState() {
        return this.l;
    }

    public boolean b() {
        return this.l == b;
    }

    public boolean c() {
        return this.l == c;
    }

    public void onClick(View view) {
        if (this.i && this.k != null) {
            this.k.onClick(view);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        d();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void d() {
    }

    public void setDayNight(boolean z) {
    }

    public void setErrorMessage(String str) {
        this.o.setText(str);
    }

    public void setErrorImag(int i) {
        try {
            this.g.setImageResource(i);
        } catch (Exception e) {
        }
    }

    public void setErrorType(int i) {
        setVisibility(View.VISIBLE);
        switch (i) {
            case b /*1*/:
                this.l = b;
                if (s.j()) {
                    this.o.setText(R.string.error_view_load_error_click_to_refresh);
                    this.g.setBackgroundResource(R.drawable.pagefailed_bg);
                } else {
                    this.o.setText(R.string.error_view_network_error_click_to_refresh);
                    this.g.setBackgroundResource(R.drawable.page_icon_network);
                }
                this.g.setVisibility(View.VISIBLE);
                this.h.setVisibility(View.GONE);
                this.i = true;
                return;
            case c /*2*/:
                this.l = c;
                this.h.setVisibility(View.VISIBLE);
                this.g.setVisibility(View.GONE);
                this.o.setText(R.string.error_view_loading);
                this.i = false;
                return;
            case d /*3*/:
                this.l = d;
                this.g.setBackgroundResource(R.drawable.page_icon_empty);
                this.g.setVisibility(View.VISIBLE);
                this.h.setVisibility(View.GONE);
                e();
                this.i = true;
                return;
            case a /*4*/:
                setVisibility(View.GONE);
                return;
            case e /*5*/:
                this.l = e;
                this.g.setBackgroundResource(R.drawable.page_icon_empty);
                this.g.setVisibility(View.VISIBLE);
                this.h.setVisibility(View.GONE);
                e();
                this.i = true;
                return;
            default:
                return;
        }
    }

    public void setNoDataContent(String str) {
        this.n = str;
    }

    public void setOnLayoutClickListener(OnClickListener onClickListener) {
        this.k = onClickListener;
    }

    public void e() {
        if (this.n.equals("")) {
            this.o.setText(R.string.error_view_no_data);
        } else {
            this.o.setText(this.n);
        }
    }

    public void setVisibility(int i) {
        if (i == View.GONE) {
            this.l = a;
        }
        super.setVisibility(i);
    }
}
