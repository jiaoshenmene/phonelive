package com.weilian.phonelive.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.hyphenate.chat.EMMessage;
import com.weilian.phonelive.R;

public abstract class PrivateChatPageBase extends BaseFragment {
    private BroadcastReceiver h;

    protected abstract void a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    protected abstract void a(EMMessage eMMessage);

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_private_chat, null);
        a(layoutInflater, viewGroup, bundle);
        ButterKnife.bind((Object) this, inflate);
        f();
        a(inflate);
        initData();
        return inflate;
    }

    private void f() {
        IntentFilter intentFilter = new IntentFilter(com.weilian.phonelive.c.b);
        if (this.h == null) {
            this.h = new BroadcastReceiver() {

                public void onReceive(Context context, Intent intent) {
                    a((EMMessage) intent.getParcelableExtra("cmd_value"));
                }
            };
        }
        getActivity().registerReceiver(this.h, intentFilter);
    }
}
