package com.weilian.phonelive.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;

public class WebViewActivity extends BaseActivity implements OnClickListener {
    private WebView d = null;
    private String e;
    @BindView(R.id.iv_webview_chat_back)
    ImageView mBack;
    @BindView(R.id.tv_webview_chat_title)
    TextView mTitle;

    protected int c() {
        return R.layout.activity_webview;
    }

    public void initView() {
        this.mBack.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                if (d == null) {
                    return;
                }
                if (d.canGoBack()) {
                    d.goBack();
                } else {
                    finish();
                }
            }
        });
        this.d = (WebView) findViewById(R.id.webView);
        WebSettings settings = this.d.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDisplayZoomControls(true);
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        WebChromeClient anonymousClass2 = new WebChromeClient() {

            public void onReceivedTitle(WebView webView, String str) {
                super.onReceivedTitle(webView, str);
                mTitle.setText(str);
            }
        };
        this.d.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                mTitle.setText(webView.getTitle());
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith("tel:")) {
                    if (d.canGoBack()) {
                        d.goBack();
                    } else {
                        d.clearHistory();
                        finish();
                    }
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } else if (str.startsWith("close:")) {
                    finish();
                } else if (!str.startsWith("login:")) {
                    webView.loadUrl(str);
                } else if (d.canGoBack()) {
                    d.goBack();
                } else {
                    d.clearHistory();
                    finish();
                }
                return false;
            }
        });
        this.d.setWebChromeClient(anonymousClass2);
    }

    public void initData() {
        this.e = getIntent().getBundleExtra("URL_INFO").getString("url");
        this.d.loadUrl(this.e);
    }

    public void onClick(View view) {
    }

    protected boolean e() {
        return true;
    }

    protected boolean b() {
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.d.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.d.goBack();
        return true;
    }
}
