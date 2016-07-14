package com.weilian.phonelive.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Scroller;
import java.util.LinkedList;
import java.util.Queue;

public class HorizontalListView extends AdapterView<ListAdapter> {
    public boolean a = true;
    protected ListAdapter b;
    protected int c;
    protected int d;
    protected Scroller e;
    private int f = -1;
    private int g = 0;
    private int h = Integer.MAX_VALUE;
    private int i = 0;
    private GestureDetector j;
    private Queue<View> k = new LinkedList();
    private OnItemSelectedListener l;
    private OnItemClickListener m;
    private OnItemLongClickListener n;
    private boolean o = false;
    private DataSetObserver p = new DataSetObserver() {

        public void onChanged() {
            synchronized (HorizontalListView.this) {
                o = true;
            }
            invalidate();
            requestLayout();
        }

        public void onInvalidated() {
            aFinish();
            invalidate();
            requestLayout();
        }
    };
    private OnGestureListener q = new SimpleOnGestureListener() {

        public boolean onDown(MotionEvent motionEvent) {
            return aFinish(motionEvent);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return aFinish(motionEvent, motionEvent2, f, f2);
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            synchronized (HorizontalListView.this) {
                HorizontalListView horizontalListView = HorizontalListView.this;
                horizontalListView.d += (int) f;
            }
            requestLayout();
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (a(motionEvent, childAt)) {
                    if (m != null) {
                        m.onItemClick(HorizontalListView.this, childAt, (f + 1) + i, b.getItemId((f + 1) + i));
                    }
                    if (l != null) {
                        l.onItemSelected(HorizontalListView.this, childAt, (f + 1) + i, b.getItemId((f + 1) + i));
                    }
                    return true;
                }
            }
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            int childCount = getChildCount();
            int i = 0;
            while (i < childCount) {
                View childAt = getChildAt(i);
                if (!a(motionEvent, childAt)) {
                    i++;
                } else if (n != null) {
                    n.onItemLongClick(HorizontalListView.this, childAt, (f + 1) + i, b.getItemId(i + (f + 1)));
                    return;
                } else {
                    return;
                }
            }
        }

        private boolean a(MotionEvent motionEvent, View view) {
            Rect rect = new Rect();
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int i = iArr[0];
            int width = view.getWidth() + i;
            int i2 = iArr[1];
            rect.set(i, i2, width, view.getHeight() + i2);
            return rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        }
    };

    public HorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    private synchronized void initView() {
        this.f = -1;
        this.g = 0;
        this.i = 0;
        this.c = 0;
        this.d = 0;
        this.h = Integer.MAX_VALUE;
        this.e = new Scroller(getContext());
        this.j = new GestureDetector(getContext(), this.q);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.l = onItemSelectedListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.m = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.n = onItemLongClickListener;
    }

    public ListAdapter getAdapter() {
        return this.b;
    }

    public View getSelectedView() {
        return null;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.b != null) {
            this.b.unregisterDataSetObserver(this.p);
        }
        this.b = listAdapter;
        this.b.registerDataSetObserver(this.p);
        aFinish();
    }

    private synchronized void aFinish() {
        initView();
        removeAllViewsInLayout();
        requestLayout();
    }

    public void setSelection(int i) {
    }

    private void aFinish(View view, int i) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -1);
        }
        addViewInLayout(view, i, layoutParams, true);
        view.measure(MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
    }

    protected synchronized void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.b != null) {
            int i5;
            if (this.o) {
                i5 = this.c;
                initView();
                removeAllViewsInLayout();
                this.d = i5;
                this.o = false;
            }
            if (this.e.computeScrollOffset()) {
                this.d = this.e.getCurrX();
            }
            if (this.d <= 0) {
                this.d = 0;
                this.e.forceFinished(true);
            }
            if (this.d >= this.h) {
                this.d = this.h;
                this.e.forceFinished(true);
            }
            i5 = this.c - this.d;
            c(i5);
            b(i5);
            d(i5);
            this.c = this.d;
            if (!this.e.isFinished()) {
                post(new Runnable() {

                    public void run() {
                        requestLayout();
                    }
                });
            }
        }
    }

    private void b(int i) {
        int right;
        int i2 = 0;
        View childAt = getChildAt(getChildCount() - 1);
        if (childAt != null) {
            right = childAt.getRight();
        } else {
            right = 0;
        }
        aFinish(right, i);
        childAt = getChildAt(0);
        if (childAt != null) {
            i2 = childAt.getLeft();
        }
        b(i2, i);
    }

    private void aFinish(int i, int i2) {
        while (i + i2 < getWidth() && this.g < this.b.getCount()) {
            View view = this.b.getView(this.g, (View) this.k.poll(), this);
            aFinish(view, -1);
            i += view.getMeasuredWidth();
            if (this.g == this.b.getCount() - 1) {
                this.h = (this.c + i) - getWidth();
            }
            if (this.h < 0) {
                this.h = 0;
            }
            this.g++;
        }
    }

    private void b(int i, int i2) {
        while (i + i2 > 0 && this.f >= 0) {
            View view = this.b.getView(this.f, (View) this.k.poll(), this);
            aFinish(view, 0);
            i -= view.getMeasuredWidth();
            this.f--;
            this.i -= view.getMeasuredWidth();
        }
    }

    private void c(int i) {
        View childAt = getChildAt(0);
        while (childAt != null && childAt.getRight() + i <= 0) {
            this.i += childAt.getMeasuredWidth();
            this.k.offer(childAt);
            removeViewInLayout(childAt);
            this.f++;
            childAt = getChildAt(0);
        }
        childAt = getChildAt(getChildCount() - 1);
        while (childAt != null && childAt.getLeft() + i >= getWidth()) {
            this.k.offer(childAt);
            removeViewInLayout(childAt);
            this.g--;
            childAt = getChildAt(getChildCount() - 1);
        }
    }

    private void d(int i) {
        if (getChildCount() > 0) {
            this.i += i;
            int i2 = this.i;
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                int measuredWidth = childAt.getMeasuredWidth();
                childAt.layout(i2, 0, i2 + measuredWidth, childAt.getMeasuredHeight());
                i2 += childAt.getPaddingRight() + measuredWidth;
            }
        }
    }

    public synchronized void aFinish(int i) {
        this.e.startScroll(this.d, 0, i - this.d, 0);
        requestLayout();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent) | this.j.onTouchEvent(motionEvent);
    }

    protected boolean aFinish(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        synchronized (this) {
            this.e.fling(this.d, 0, (int) (-f), 0, 0, this.h, 0, 0);
        }
        requestLayout();
        return true;
    }

    protected boolean aFinish(MotionEvent motionEvent) {
        this.e.forceFinished(true);
        return true;
    }
}
