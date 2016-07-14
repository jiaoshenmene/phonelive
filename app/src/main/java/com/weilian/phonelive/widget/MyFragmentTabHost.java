package com.weilian.phonelive.widget;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

import com.lzfutil.util.w;
import com.weilian.phonelive.AppContext;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;
import com.alipay.sdk.cons.GlobalConstants;

public class MyFragmentTabHost extends FragmentTabHost {
    private String a;
    private String b = GlobalConstants.e;
    private Context c;

    public MyFragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
    }

    public void onTabChanged(String str) {
        if (str.equals(this.b)) {
            setCurrentTabByTag(this.a);
            ce.b.k(AppContext.c().i(), new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    a(response);
                }


                public void a(String str) {
                    if (ce.a.a(str) != null) {
                        w.g(c);
                    }
                }
            });
            return;
        }
        super.onTabChanged(str);
        this.a = str;
    }

    public void setNoTabChangedTag(String str) {
        this.b = str;
    }
}
