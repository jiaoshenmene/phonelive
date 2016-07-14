package com.weilian.phonelive.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import butterknife.ButterKnife;
import butterknife.BindView;
import com.lzfutil.util.p;
import cn.jpush.android.api.JPushInterface;

import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseFragment;

public class PushManageFragment extends BaseFragment {
    private final String h = "isOpenPush";
    @BindView(R.id.ib_push_manage_start_or_close)
    ImageButton mBtnStartOrClosePush;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_push_manage, null);
        ButterKnife.bind((Object) this, inflate);
        initData();
        a(inflate);
        return inflate;
    }

    public void initData() {
    }

    public void a(View view) {
        a(p.c(getActivity(), "isOpenPush"), this.mBtnStartOrClosePush);
        this.mBtnStartOrClosePush.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                f();
            }
        });
    }

    private void a(boolean z, ImageButton imageButton) {
        imageButton.setImageResource(z ? R.drawable.global_switch_on : R.drawable.global_switch_off);
    }

    private void f() {
        boolean z = false;
        boolean c = p.c(getActivity(), "isOpenPush");
        p.a(getActivity(), "isOpenPush", Boolean.valueOf(!c));
        if (!c) {
            z = true;
        }
        a(z, this.mBtnStartOrClosePush);
        if (c) {
            JPushInterface.stopPush(getActivity());
        } else {
            JPushInterface.resumePush(getActivity());
        }
    }
}
