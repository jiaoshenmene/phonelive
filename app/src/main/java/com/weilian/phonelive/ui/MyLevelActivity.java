package com.weilian.phonelive.ui;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import butterknife.BindView;

import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;

public class MyLevelActivity extends BaseActivity {
    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;
    @BindView(R.id.wv_level)
    WebView mWbView;

    private class aWeb extends WebChromeClient {

        private aWeb() {
        }

        public void onProgressChanged(WebView webView, int i) {
            mProgressBar.setProgress(i);
            if (i == 100) {
                mProgressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(webView, i);
        }
    }

    protected int c() {
        return R.layout.activity_level;
    }

    public void initView() {
    }

    public void initData() {
        a(getString(R.string.myleve));
        this.mProgressBar.setMax(100);
        this.mWbView.setWebChromeClient(new aWeb());
        this.mWbView.getSettings().setJavaScriptEnabled(true);
        this.mWbView.loadUrl("http://www.51rexiu.com//appcmf/index.php?LoadedFrom=user&EVENT_PONG=level&Editor=index&uid=" + getIntent().getStringExtra("USER_ID"));
    }

    public void onClick(View view) {
    }

    protected boolean e() {
        return true;
    }
}
