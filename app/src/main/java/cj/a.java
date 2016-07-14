package cj;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.lzfutil.util.s;
import com.lzfutil.util.t;
import com.weilian.phonelive.R;

@TargetApi(11)
public class a {
    public static final long av = 5000;
    public static final long b = 3500;
    public static final long c = 2500;
    private long d = b;
    private af e;

    private class af extends FrameLayout {
        public ImageView a;
        public TextView b;
        public ImageView c;
        public TextView d;

        public af(a aVar, Context context) {
            this(aVar, context, null);
        }

        public af(a aVar, Context context, AttributeSet attributeSet) {
            this(aVar, context, attributeSet, 0);
        }

        public af(a aVar, Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            a();
        }

        private void a() {
            LayoutInflater.from(getContext()).inflate(R.layout.view_base_toast, this, true);
            this.d = (TextView) findViewById(R.id.title_tv);
            this.c = (ImageView) findViewById(R.id.icon_iv);
            this.b = (TextView) findViewById(R.id.title_tv);
            this.a = (ImageView) findViewById(R.id.icon_iv);
        }
    }

    public a(Activity activity) {
        a(activity);
    }

    public a(Activity activity, String str, int i, String str2, int i2, long j) {
        this.d = j;
        a(activity);
        b(str);
        c(i);
        a(str2);
        a(i2);
    }

    private void a(Activity activity) {
        this.e = new af(this, activity);
        b(81);
    }

    public long a() {
        return this.d;
    }

    public void a(String str) {
        this.e.b.setText(str);
    }

    public void a(int i) {
        this.e.a.setImageResource(i);
    }

    public void a(long j) {
        this.d = j;
    }

    public void b(int i) {
        if (i != 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = i;
            int a = (int) s.a(16.0f);
            layoutParams.setMargins(a, a, a, a);
            this.e.setLayoutParams(layoutParams);
        }
    }

    public void b(String str) {
        this.e.d.setText(str);
    }

    public void c(int i) {
        this.e.c.setImageResource(i);
    }

    public void b() {
        final ViewGroup viewGroup = (ViewGroup) ((Activity) this.e.getContext()).findViewById(R.id.action_bar_activity_content);
        if (viewGroup != null) {
            ObjectAnimator.ofFloat(this.e, "alpha", new float[]{0.0f}).setDuration(0).start();
            viewGroup.addView(this.e);
            ObjectAnimator.ofFloat(this.e, "alpha", new float[]{0.0f, 1.0f}).setDuration(167).start();
            this.e.postDelayed(new Runnable() {

                public void run() {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(a.this.e, "alpha", new float[]{1.0f, 0.0f});
                    ofFloat.setDuration(100);
                    ofFloat.addListener(new AnimatorListener() {

                        public void onAnimationStart(Animator animator) {
                        }

                        public void onAnimationRepeat(Animator animator) {
                        }

                        public void onAnimationEnd(Animator animator) {
                            viewGroup.removeView(a.this.e);
                        }

                        public void onAnimationCancel(Animator animator) {
                        }
                    });
                    ofFloat.start();
                }
            }, this.d);
            return;
        }
        t.b("Toast not shown! Content view is null!");
    }
}
