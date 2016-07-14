package com.weilian.phonelive.widget;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.weilian.phonelive.R;

public class PaySelectView extends RelativeLayout {
    public PaySelectView(Context context) {
        super(context);
    }

    public PaySelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PaySelectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onDraw(Canvas canvas) {
        BitmapFactory.decodeResource(getResources(), R.drawable.pay_choose);
    }
}
