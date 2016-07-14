package com.weilian.phonelive.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.ButterKnife;
import ch.b;
import ch.d;
import com.lzfutil.util.q;
import com.lzfutil.util.s;

import com.devspark.appmsg.AppMsg;
import com.weilian.phonelive.R;

public abstract class BaseActivity extends ActionBarActivity implements OnClickListener, b, d {
    public static final String a = "INTENT_ACTION_EXIT_APP";
    protected LayoutInflater b;
    protected ActionBar c;
    private boolean d;
    private ProgressDialog e;
    private TextView f;

//    native int init(Context context);

    protected void onDestroy() {
        super.onDestroy();
        s.b(getCurrentFocus());
//        ButterKnife.unBind(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!b()) {
            supportRequestWindowFeature(1);
        }
        a();
        if (c() != 0) {
            setContentView(c());
        }
        this.c = getSupportActionBar();
        this.b = getLayoutInflater();
        if (b()) {
            a(this.c);
        }
        ButterKnife.bind((Activity) this);
        a(bundle);
        initView();
        initData();
        this.d = true;
    }

    protected void a() {
    }

    protected boolean b() {
        return true;
    }

    protected int c() {
        return 0;
    }

    protected View a(int i) {
        return this.b.inflate(i, null);
    }

    protected int d() {
        return R.string.app_name;
    }

    protected boolean e() {
        return false;
    }

    protected void a(Bundle bundle) {
    }

    protected void a(ActionBar actionBar) {
        if (actionBar != null) {
            if (e()) {
                this.c.setDisplayHomeAsUpEnabled(true);
                this.c.setHomeButtonEnabled(true);
                return;
            }
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayUseLogoEnabled(false);
            int d = d();
            if (d != 0) {
                actionBar.setTitle(d);
            }
        }
    }

    public void b(int i) {
        if (i != 0) {
            a(getString(i));
        }
    }

    public void a(String str) {
        if (q.f(str)) {
            str = getString(R.string.app_name);
        }
        if (b() && this.c != null) {
            if (this.f != null) {
                this.f.setText(str);
            }
            this.c.setTitle(str);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    public void a(int i, int i2, int i3) {
        a(getString(i), i2, i3);
    }

    public void b(String str) {
        AppMsg.makeText((Activity) this, (CharSequence) str, new AppMsg.Style(1000, R.drawable.toast_background)).show();
    }

    public void a(String str, int i, int i2) {
        cj.a aVar = new cj.a(this);
        aVar.b(str);
        aVar.c(i);
        aVar.b(i2);
        aVar.b();
    }

    public ProgressDialog f() {
        return c((int) R.string.loading);
    }

    public ProgressDialog c(int i) {
        return c(getString(i));
    }

    public ProgressDialog c(String str) {
        if (!this.d) {
            return null;
        }
        if (this.e == null) {
            this.e = com.lzfutil.util.e.a(this, str);
        }
        if (this.e != null) {
            this.e.setMessage(str);
            this.e.show();
        }
        return this.e;
    }

    public void g() {
        if (this.d && this.e != null) {
            try {
                this.e.dismiss();
                this.e = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }
}
