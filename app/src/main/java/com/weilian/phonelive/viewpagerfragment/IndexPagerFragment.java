package com.weilian.phonelive.viewpagerfragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;
import cc.h;
import ch.f;
import com.lzfutil.util.w;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.base.BaseFragment;
import com.weilian.phonelive.fragment.AttentionFragment;
import com.weilian.phonelive.fragment.HotFragment;
import com.weilian.phonelive.fragment.NewestFragment;
import com.weilian.phonelive.widget.PagerSlidingTabStrip;
import com.weilian.phonelive.R;

public class IndexPagerFragment extends BaseFragment {
    public static int h = 0;
    public static String i = "";
    private View j;
    private h k;
    @BindView(R.id.iv_hot_select_region)
    ImageView mRegion;
    @BindView(R.id.mviewpager)
    ViewPager pager;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.j == null) {
            this.j = layoutInflater.inflate(R.layout.fragment_hot, null);
            ButterKnife.bind((Object) this, this.j);
            initView();
        } else {
            this.tabs.setViewPager(this.pager);
        }
        return this.j;
    }

    @OnClick({R.id.iv_hot_search, R.id.iv_hot_private_chat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_hot_search:
                w.i(getActivity());
                return;
            case R.id.iv_hot_private_chat:
                int i = AppContext.c().i();
                if (i > 0) {
                    w.g(getActivity(), i);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void initView() {
        this.k = new h(getFragmentManager(), this.pager);
        this.k.a(getString(R.string.attention), "gz", AttentionFragment.class, f());
        this.k.a(getString(R.string.hot), "rm", HotFragment.class, f());
        this.k.a(getString(R.string.daren), "dr", NewestFragment.class, f());
        this.pager.setAdapter(this.k);
        this.pager.setOffscreenPageLimit(2);
        this.tabs.setViewPager(this.pager);
        this.tabs.setUnderlineColor(getResources().getColor(R.color.global));
        this.tabs.setDividerColor(getResources().getColor(R.color.global));
        this.tabs.setIndicatorColor(getResources().getColor(R.color.backgroudcolor));
        this.tabs.setTextColor(-1);
        this.tabs.setTextSize((int) getResources().getDimension(R.dimen.text_size_12));
        this.tabs.setSelectedTextColor(-1);
        this.tabs.setIndicatorHeight(2);
//        this.tabs.setZoomMax(0.0f);
        this.tabs.setPagerSlidingListen(new f() {

            public void a(View view, int i, int i2) {
                if (i == i2 && i2 == 1) {
                    w.h(getActivity());
                }
            }
        });
        this.pager.setCurrentItem(1);
        this.tabs.setOnPageChangeListener(new OnPageChangeListener() {

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                mRegion.setVisibility(1 == i ? View.VISIBLE : View.GONE);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private Bundle f() {
        return new Bundle();
    }
}
