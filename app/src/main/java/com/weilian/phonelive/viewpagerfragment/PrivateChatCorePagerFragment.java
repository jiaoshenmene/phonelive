package com.weilian.phonelive.viewpagerfragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import butterknife.OnClick;
import cc.h;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseViewPagerFragment;
import com.weilian.phonelive.fragment.FollowPrivateChatFragment;
import com.weilian.phonelive.fragment.NotFollowPrivateChatFragment;

public class PrivateChatCorePagerFragment extends BaseViewPagerFragment {
    protected void a(View view, h hVar, ViewPager viewPager) {
        ((ImageView) view.findViewById(R.id.iv_private_chat_back)).setOnClickListener(this);
        initData();
        Bundle bundle = new Bundle();
        bundle.putInt("ACTION", 1);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("ACTION", 0);
        hVar.a(getString(R.string.friends), "friends", FollowPrivateChatFragment.class, bundle);
        hVar.a(getString(R.string.nofollow), "nofollow", NotFollowPrivateChatFragment.class, bundle2);
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(hVar);
    }

    public void initData() {
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.iv_private_chat_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_private_chat_back:
                AppContext.a(getActivity(), "7");
                getActivity().finish();
                return;
            default:
                return;
        }
    }
}
