package com.weilian.phonelive.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;
import com.weilian.phonelive.bean.SimpleBackPage;
import java.lang.ref.WeakReference;

public class ActionBarSimpleBackActivity extends BaseActivity {
    public static final String d = "BUNDLE_KEY_PAGE";
    public static final String e = "BUNDLE_KEY_ARGS";
    private static final String h = "FLAG_TAG";
    protected WeakReference<Fragment> f;
    protected int g = -1;

    protected int c() {
        return R.layout.activity_simple_fragment;
    }

    public void initView() {
    }

    public void initData() {
    }

    public void onClick(View view) {
    }

    protected boolean e() {
        return true;
    }

    protected void a(Bundle bundle) {
        super.a(bundle);
        if (this.g == -1) {
            this.g = getIntent().getIntExtra(d, 0);
        }
        a(this.g, getIntent());
    }

    protected void a(int i, Intent intent) {
        if (intent == null) {
            throw new RuntimeException("you must provide Editor page info to display");
        }
        SimpleBackPage pageByValue = SimpleBackPage.getPageByValue(i);
        if (pageByValue == null) {
            throw new IllegalArgumentException("can not find page by value:" + i);
        }
        b(pageByValue.getTitle());
        try {
            Fragment fragment = (Fragment) pageByValue.getClz().newInstance();
            Bundle bundleExtra = intent.getBundleExtra(e);
            if (bundleExtra != null) {
                fragment.setArguments(bundleExtra);
            }
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.container, fragment, h);
            beginTransaction.commitAllowingStateLoss();
            this.f = new WeakReference(fragment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("generate fragment error. by value:" + i);
        }
    }

    protected void onResume() {
        super.onResume();
    }
}
