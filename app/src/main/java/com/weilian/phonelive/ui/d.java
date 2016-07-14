package com.weilian.phonelive.ui;

import com.weilian.phonelive.R;
import com.weilian.phonelive.fragment.MyInformationFragment;
import com.weilian.phonelive.viewpagerfragment.IndexPagerFragment;

public enum d {
    INDEX(0, R.drawable.btn_tab_hot_background, 0, IndexPagerFragment.class),
    LIVE(1, R.drawable.btn_tab_live_background, 1, StartLiveActivity.class),
    HOME(2, R.drawable.btn_tab_home_background, 2, MyInformationFragment.class);
    
    private int d;
    private int e;
    private int f;
    private Class<?> g;

    private d(int i, int i2, int i3, Class<?> cls) {
        this.d = i;
        this.f = i2;
        this.e = i3;
        this.g = cls;
    }

    public int a() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public int b() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public int c() {
        return this.f;
    }

    public void c(int i) {
        this.f = i;
    }

    public Class<?> d() {
        return this.g;
    }

    public void a(Class<?> cls) {
        this.g = cls;
    }
}
