package com.weilian.phonelive.ui;

import android.view.View;
import butterknife.OnClick;
import com.lzfutil.util.w;

import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;

public class SettingActivity extends BaseActivity {
    protected int c() {
        return R.layout.activity_setting;
    }

    public void initView() {
    }

    public void initData() {
        a(getString(R.string.setting));
    }

    @OnClick({R.id.ll_push_manage, R.id.ll_about, R.id.ll_feedback, R.id.ll_blank_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_push_manage:
                w.k(this);
                return;
            case R.id.ll_blank_list:
                w.j(this);
                return;
            default:
                return;
        }
    }

    protected boolean e() {
        return true;
    }
}
