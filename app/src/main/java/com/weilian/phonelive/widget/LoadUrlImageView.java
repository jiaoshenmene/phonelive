package com.weilian.phonelive.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.weilian.phonelive.R;

import org.kymjs.kjframe.Core;
import org.kymjs.kjframe.bitmap.BitmapCallBack;
import org.kymjs.kjframe.utils.StringUtils;

public class LoadUrlImageView extends ImageView {
    private Activity a;

    public LoadUrlImageView(Context context) {
        super(context);
        a(context);
    }

    public LoadUrlImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.a = (Activity) context;
    }

    public LoadUrlImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void setImageLoadUrl(String str) {
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
                a.runOnUiThread(new Runnable() {

                    public void run() {
                        setImageResource(R.drawable.null_blacklist);
                    }
                });
                setImageResource(R.drawable.null_blacklist);
            }
        });
    }
}
