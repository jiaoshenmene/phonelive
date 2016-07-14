package com.weilian.phonelive.base;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.h;
import com.astuetz.PagerSlidingTabStrip;
import com.weilian.phonelive.R;

public abstract class BaseViewPagerFragment extends BaseFragment {
    protected PagerSlidingTabStrip h;
    protected ViewPager i;
    protected h j;

    protected abstract void a(View view, h hVar, ViewPager viewPager);

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.base_viewpage_fragment, null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.h = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        this.i = (ViewPager) view.findViewById(R.id.pager);
        this.j = new h(getChildFragmentManager(), this.i);
        f();
        a(view, this.j, this.i);
        this.h.setViewPager(this.i);
        this.h.setDividerColor(getResources().getColor(R.color.global));
        this.h.setIndicatorColor(getResources().getColor(R.color.backgroudcolor));
        this.h.setTextColor(-1);
        this.h.setTextSize((int) getResources().getDimension(R.dimen.text_size_15));
        this.h.setIndicatorHeight(10);
        this.h.setUnderlineColorResource(R.color.global);
    }

    protected void f() {
    }
}
