package com.weilian.phonelive.widget.music;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PointF;
import android.support.v4.view.InputDeviceCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;
import org.kymjs.kjframe.http.Request.HttpMethod;

public class LrcView extends View implements ILrcView {
    public static final String a = "LrcView";
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    private boolean A = false;
    private List<LrcRow> e;
    private int f = 10;
    private int g = b;
    private int h = InputDeviceCompat.SOURCE_ANY;
    private int i = -1;
    private int j = -16711681;
    private int k = -16711681;
    private int l = 15;
    private int m = 13;
    private int n = 18;
    private int o = 23;
    private int p = 15;
    private int q = 35;
    private int r = 10;
    private int s = b;
    private int t = b;
    private a u;
    private String v = "Downloading lrc...";
    private Paint w = new Paint(c);
    private float x;
    private PointF y = new PointF();
    private PointF z = new PointF();

    public LrcView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.w.setTextSize((float) this.o);
    }

    public void setListener(a aVar) {
        this.u = aVar;
    }

    public void setLoadingTipText(String str) {
        this.v = str;
    }

    protected void onDraw(Canvas canvas) {
        int height = getHeight();
        int width = getWidth();
        if (this.e != null && this.e.size() != 0) {
            int i = width / d;
            String str = ((LrcRow) this.e.get(this.g)).c;
            int i2 = (height / d) - this.o;
            this.w.setColor(this.h);
            this.w.setTextSize((float) this.o);
            this.w.setTextAlign(Align.CENTER);
            canvas.drawText(str, (float) i, (float) i2, this.w);
            if (this.t == c) {
                this.w.setColor(this.j);
                canvas.drawLine((float) this.s, (float) i2, (float) (width - this.s), (float) i2, this.w);
                this.w.setColor(this.k);
                this.w.setTextSize((float) this.l);
                this.w.setTextAlign(Align.LEFT);
                canvas.drawText(((LrcRow) this.e.get(this.g)).d, 0.0f, (float) i2, this.w);
            }
            this.w.setColor(this.i);
            this.w.setTextSize((float) this.o);
            this.w.setTextAlign(Align.CENTER);
            int i3 = (i2 - this.r) - this.o;
            int i4 = this.g - 1;
            while (i3 > (-this.o) && i4 >= 0) {
                canvas.drawText(((LrcRow) this.e.get(i4)).c, (float) i, (float) i3, this.w);
                i3 -= this.r + this.o;
                i4--;
            }
            i3 = (this.r + i2) + this.o;
            i4 = this.g + c;
            while (i3 < height && i4 < this.e.size()) {
                canvas.drawText(((LrcRow) this.e.get(i4)).c, (float) i, (float) i3, this.w);
                i3 += this.r + this.o;
                i4 += c;
            }
        } else if (this.v != null) {
            this.w.setColor(this.h);
            this.w.setTextSize((float) this.o);
            this.w.setTextAlign(Align.CENTER);
            canvas.drawText(this.v, (float) (width / d), (float) ((height / d) - this.o), this.w);
        }
    }

    public void a(int i, boolean z) {
        if (this.e != null && i >= 0 && i <= this.e.size()) {
            LrcRow dVar = (LrcRow) this.e.get(i);
            this.g = i;
            invalidate();
            if (this.u != null && z) {
                this.u.a(i, dVar);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.e == null || this.e.size() == 0) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case b /*0*/:
                Log.d(a, "down,mLastMotionY:" + this.x);
                this.x = motionEvent.getY();
                this.A = true;
                invalidate();
                return true;
            case c /*1*/:
            case HttpMethod.DELETE /*3*/:
                if (this.t == c) {
                    a(this.g, true);
                }
                this.t = b;
                invalidate();
                return true;
            case d /*2*/:
                if (motionEvent.getPointerCount() == d) {
                    Log.d(a, "two move");
                    a(motionEvent);
                    return true;
                }
                Log.d(a, "one move");
                if (this.t == d) {
                    return true;
                }
                b(motionEvent);
                return true;
            default:
                return true;
        }
    }

    private void a(MotionEvent motionEvent) {
        if (this.t == c) {
            this.t = d;
            Log.d(a, "two move but teaking ...change mode");
            return;
        }
        if (this.A) {
            this.t = d;
            invalidate();
            this.A = false;
            setTwoPointerLocation(motionEvent);
        }
        int c = c(motionEvent);
        Log.d(a, "scaleSize:" + c);
        if (c != 0) {
            setNewFontSize(c);
            invalidate();
        }
        setTwoPointerLocation(motionEvent);
    }

    private void b(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        float f = y - this.x;
        if (Math.abs(f) >= ((float) this.f)) {
            this.t = c;
            int abs = Math.abs(((int) f) / this.o);
            Log.d(a, "move new hightlightrow : " + this.g + " offsetY: " + f + " rowOffset:" + abs);
            if (f < 0.0f) {
                this.g += abs;
            } else if (f > 0.0f) {
                this.g -= abs;
            }
            this.g = Math.max(b, this.g);
            this.g = Math.min(this.g, this.e.size() - 1);
            if (abs > 0) {
                this.x = y;
                invalidate();
            }
        }
    }

    private void setTwoPointerLocation(MotionEvent motionEvent) {
        this.y.x = motionEvent.getX(b);
        this.y.y = motionEvent.getY(b);
        this.z.x = motionEvent.getX(c);
        this.z.y = motionEvent.getY(c);
    }

    private void setNewFontSize(int i) {
        this.o += i;
        this.l += i;
        this.o = Math.max(this.o, this.p);
        this.o = Math.min(this.o, this.q);
        this.l = Math.max(this.l, this.m);
        this.l = Math.min(this.l, this.n);
    }

    private int c(MotionEvent motionEvent) {
        int i = c;
        Log.d(a, "scaleSize getScale");
        float x = motionEvent.getX(b);
        float y = motionEvent.getY(b);
        float x2 = motionEvent.getX(c);
        float y2 = motionEvent.getY(c);
        float abs = Math.abs(this.y.x - this.z.x);
        x = Math.abs(x2 - x);
        x2 = Math.abs(this.y.y - this.z.y);
        y = Math.abs(y2 - y);
        y2 = Math.max(Math.abs(x - abs), Math.abs(y - x2));
        if (y2 == Math.abs(x - abs)) {
            if (x <= abs) {
                i = b;
            }
        } else if (y <= x2) {
            i = b;
        }
        Log.d(a, "scaleSize maxOffset:" + y2);
        if (i != 0) {
            return (int) (y2 / 10.0f);
        }
        return -((int) (y2 / 10.0f));
    }

    public void setLrc(List<LrcRow> list) {
        this.e = list;
        invalidate();
    }

    public void a(long j) {
        if (this.e != null && this.e.size() != 0 && this.t == 0) {
            Log.d(a, "seekLrcToTime:" + j);
            int i = b;
            while (i < this.e.size()) {
                LrcRow dVar = (LrcRow) this.e.get(i);
                LrcRow dVar2 = i + c == this.e.size() ? null : (LrcRow) this.e.get(i + c);
                if ((j < dVar.b || dVar2 == null || j >= dVar2.b) && (j <= dVar.b || dVar2 != null)) {
                    i += c;
                } else {
                    a(i, false);
                    return;
                }
            }
        }
    }
}
