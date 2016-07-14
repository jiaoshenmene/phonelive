package com.weilian.phonelive.widget;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.weilian.phonelive.R;

import org.kymjs.kjframe.Core;
import org.kymjs.kjframe.bitmap.BitmapCallBack;
import org.kymjs.kjframe.utils.StringUtils;

public class AvatarView extends CircleImageView {
    public static final String a = "_[0-9]{1,3}";
    public static final String b = "_100";
    public static final String c = "_200";
    private int d;
    private String e;
    private Activity f;

    public AvatarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public AvatarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public AvatarView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.f = (Activity) context;
        setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                if (!TextUtils.isEmpty(e)) {
                }
            }
        });
    }

    public void a(int i, String str) {
        this.d = i;
        this.e = str;
    }

    public void setAvatarUrl(String str) {
        if (StringUtils.isEmpty((CharSequence) str)) {
            setImageResource(R.drawable.null_blacklist);
            return;
        }
        String substring;
        int indexOf = str.indexOf(63);
        if (indexOf > 0) {
            substring = str.substring(0, indexOf);
        } else {
            substring = str;
        }
        Core.getKJBitmap().display(this, substring, R.drawable.null_blacklist, 0, 0, new BitmapCallBack() {

            public void onFailure(Exception exception) {
                super.onFailure(exception);
                f.runOnUiThread(new Runnable() {

                    public void run() {
                        setImageResource(R.drawable.null_blacklist);
                    }
                });
                setImageResource(R.drawable.null_blacklist);
            }
        });
    }

    public static String a(String str) {
        return str;
    }

    public static String b(String str) {
        if (str == null) {
            return "";
        }
        return str.replaceAll(a, b);
    }

    public static String c(String str) {
        if (str == null) {
            return "";
        }
        return str.replaceAll(a, c);
    }
}
