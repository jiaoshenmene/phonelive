package com.weilian.phonelive.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TabWidget;
import android.widget.TextView;

public class BadgeView extends TextView {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    private static final int f = 5;
    private static final int g = 5;
    private static final int h = 8;
    private static final int i = 2;
    private static final int j = Color.parseColor("#CCFF0000");
    private static final int k = -1;
    private static Animation l;
    private static Animation m;
    private Context n;
    private View o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private ShapeDrawable u;
    private int v;

    public BadgeView(Context context) {
        this(context, (AttributeSet) null, 16842884);
    }

    public BadgeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public BadgeView(Context context, View view) {
        this(context, null, 16842884, view, j);
    }

    public BadgeView(Context context, TabWidget tabWidget, int i) {
        this(context, null, 16842884, tabWidget, i);
    }

    public BadgeView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, null, j);
    }

    public BadgeView(Context context, AttributeSet attributeSet, int i, View view, int i2) {
        super(context, attributeSet, i);
        a(context, view, i2);
    }

    private void a(Context context, View view, int i) {
        this.n = context;
        this.o = view;
        this.v = i;
        this.p = i;
        this.q = c((int) g);
        this.r = this.q;
        this.s = j;
        setTypeface(Typeface.DEFAULT_BOLD);
        int c = c((int) g);
        setPadding(c, j, c, j);
        setTextColor(k);
        l = new AlphaAnimation(0.0f, 1.0f);
        l.setInterpolator(new DecelerateInterpolator());
        l.setDuration(200);
        m = new AlphaAnimation(1.0f, 0.0f);
        m.setInterpolator(new AccelerateInterpolator());
        m.setDuration(200);
        this.t = false;
        if (this.o != null) {
            a(this.o);
        } else {
            a();
        }
    }

    private void a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        ViewParent parent = view.getParent();
        FrameLayout frameLayout = new FrameLayout(this.n);
        if (view instanceof TabWidget) {
            View childTabViewAt = ((TabWidget) view).getChildTabViewAt(this.v);
            this.o = childTabViewAt;
            ((ViewGroup) childTabViewAt).addView(frameLayout, new LayoutParams(k, k));
            setVisibility(h);
            frameLayout.addView(this);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        int indexOfChild = viewGroup.indexOfChild(view);
        viewGroup.removeView(view);
        viewGroup.addView(frameLayout, indexOfChild, layoutParams);
        frameLayout.addView(view);
        setVisibility(h);
        frameLayout.addView(this);
        viewGroup.invalidate();
    }

    public void a() {
        a(false, null);
    }

    public void a(boolean z) {
        a(z, l);
    }

    public void a(Animation animation) {
        a(true, animation);
    }

    public void b() {
        b(false, null);
    }

    public void b(boolean z) {
        b(z, m);
    }

    public void b(Animation animation) {
        b(true, animation);
    }

    public void c() {
        a(false, null, null);
    }

    public void c(boolean z) {
        a(z, l, m);
    }

    public void a(Animation animation, Animation animation2) {
        a(true, animation, animation2);
    }

    private void a(boolean z, Animation animation) {
        if (getBackground() == null) {
            if (this.u == null) {
                this.u = getDefaultBackground();
            }
            setBackgroundDrawable(this.u);
        }
        d();
        if (z) {
            startAnimation(animation);
        }
        setVisibility(j);
        this.t = true;
    }

    private void b(boolean z, Animation animation) {
        setVisibility(h);
        if (z) {
            startAnimation(animation);
        }
        this.t = false;
    }

    private void a(boolean z, Animation animation, Animation animation2) {
        boolean z2 = true;
        if (this.t) {
            if (!z || animation2 == null) {
                z2 = false;
            }
            b(z2, animation2);
            return;
        }
        if (!z || animation == null) {
            z2 = false;
        }
        a(z2, animation);
    }

    public int a(int i) {
        int i2 = j;
        CharSequence text = getText();
        if (text != null) {
            try {
                i2 = Integer.parseInt(text.toString());
            } catch (NumberFormatException e) {
            }
        }
        i2 += i;
        setText(String.valueOf(i2));
        return i2;
    }

    public int b(int i) {
        return a(-i);
    }

    private ShapeDrawable getDefaultBackground() {
        int c = c((int) h);
        float[] fArr = new float[h];
        fArr[j] = (float) c;
        fArr[a] = (float) c;
        fArr[i] = (float) c;
        fArr[c] = (float) c;
        fArr[d] = (float) c;
        fArr[g] = (float) c;
        fArr[6] = (float) c;
        fArr[7] = (float) c;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, null, null));
        shapeDrawable.getPaint().setColor(this.s);
        return shapeDrawable;
    }

    private void d() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        switch (this.p) {
            case a /*1*/:
                layoutParams.gravity = 51;
                layoutParams.setMargins(this.q, this.r, j, j);
                break;
            case i /*2*/:
                layoutParams.gravity = 53;
                layoutParams.setMargins(j, this.r, this.q, j);
                break;
            case c /*3*/:
                layoutParams.gravity = 83;
                layoutParams.setMargins(this.q, j, j, this.r);
                break;
            case d /*4*/:
                layoutParams.gravity = 85;
                layoutParams.setMargins(j, j, this.q, this.r);
                break;
            case g /*5*/:
                layoutParams.gravity = 17;
                layoutParams.setMargins(j, j, j, j);
                break;
        }
        setLayoutParams(layoutParams);
    }

    public View getTarget() {
        return this.o;
    }

    public boolean isShown() {
        return this.t;
    }

    public int getBadgePosition() {
        return this.p;
    }

    public void setBadgePosition(int i) {
        this.p = i;
    }

    public int getHorizontalBadgeMargin() {
        return this.q;
    }

    public int getVerticalBadgeMargin() {
        return this.r;
    }

    public void setBadgeMargin(int i) {
        this.q = i;
        this.r = i;
    }

    public void a(int i, int i2) {
        this.q = i;
        this.r = i2;
    }

    public int getBadgeBackgroundColor() {
        return this.s;
    }

    public void setBadgeBackgroundColor(int i) {
        this.s = i;
        this.u = getDefaultBackground();
    }

    private int c(int i) {
        return (int) TypedValue.applyDimension(a, (float) i, getResources().getDisplayMetrics());
    }
}
