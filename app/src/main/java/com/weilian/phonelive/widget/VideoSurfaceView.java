package com.weilian.phonelive.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.MeasureSpec;

public class VideoSurfaceView extends SurfaceView {
    private int a;
    private int b;
    private int c;
    private int d;

    public VideoSurfaceView(Context context) {
        super(context);
    }

    public VideoSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        b(i, i2);
        setMeasuredDimension(this.c, this.d);
    }

    public void a(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    private void b(int i, int i2) {
        int defaultSize = View.getDefaultSize(this.a, i);
        int defaultSize2 = View.getDefaultSize(this.b, i2);
        if (this.a > 0 && this.b > 0) {
            defaultSize = MeasureSpec.getSize(i);
            defaultSize2 = MeasureSpec.getSize(i2);
            float f = ((float) this.a) / ((float) this.b);
            if ((f > ((float) defaultSize) / ((float) defaultSize2) ? 1 : null) != null) {
                defaultSize = (int) (((float) defaultSize2) * f);
            } else {
                defaultSize2 = (int) (((float) defaultSize) / f);
            }
        }
        this.c = defaultSize;
        this.d = defaultSize2;
    }
}
