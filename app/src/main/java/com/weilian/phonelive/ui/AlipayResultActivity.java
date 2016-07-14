package com.weilian.phonelive.ui;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.ksyun.media.player.stats.StatConstant;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;

public class AlipayResultActivity extends BaseActivity {
    @BindView(R.id.tv_alipaypay_result)
    TextView mAliPayResult;

    protected int c() {
        return R.layout.activity_alipay_result;
    }

    public void initView() {
    }

    protected boolean e() {
        return true;
    }

    public void initData() {
        a(getString(R.string.payresult));
        if (getIntent().getIntExtra("result", 0) == 1) {
            this.mAliPayResult.setText(StatConstant.PLAY_STATUS_OK);
        } else {
            this.mAliPayResult.setText("no");
        }
    }

    public void onClick(View view) {
    }
}
