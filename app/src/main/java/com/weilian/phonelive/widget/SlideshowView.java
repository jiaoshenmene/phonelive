package com.weilian.phonelive.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.weilian.phonelive.R;
import com.weilian.phonelive.bean.RollPicBean;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;

public class SlideshowView extends FrameLayout {
    private static final int b = 2;
    private static final int c = 5;
    private static final boolean d = true;
    private ImageLoader a;
    private String[] e;
    private List<ImageView> f;
    private List<View> g;
    private ViewPager h;
    private int iVal;
    private ScheduledExecutorService j;
    private Context k;
    private Handler l;
    private StringCallback m;

    private class aListener implements OnPageChangeListener {
        boolean a;

        private aListener() {
            this.a = false;
        }

        public void onPageScrollStateChanged(int i) {
            switch (i) {
                case 0:
                    if (h.getCurrentItem() == h.getAdapter().getCount() - 1 && !this.a) {
                        h.setCurrentItem(0);
                        return;
                    } else if (h.getCurrentItem() == 0 && !this.a) {
                        h.setCurrentItem(h.getAdapter().getCount() - 1);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    this.a = false;
                    return;
                case 2:
                    this.a = SlideshowView.d;
                    return;
                default:
                    return;
            }
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            iVal = i;
            for (int i2 = 0; i2 < g.size(); i2++) {
                if (i2 == i) {
                    ((View) g.get(i)).setBackgroundResource(R.drawable.dot_focus);
                } else {
                    ((View) g.get(i2)).setBackgroundResource(R.drawable.dot_blur);
                }
            }
        }
    }

    private class bAdapter extends PagerAdapter {

        private bAdapter() {
        }

        public void destroyItem(View view, int i, Object obj) {
            ((ViewPager) view).removeView((View) f.get(i));
        }

        public Object instantiateItem(View view, int i) {
            ImageView imageView = (ImageView) f.get(i);
            a.displayImage(imageView.getTag() + "", imageView);
            ((ViewPager) view).addView((View) f.get(i));
            return f.get(i);
        }

        public int getCount() {
            return f.size();
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj ? SlideshowView.d : false;
        }

        public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        }

        public Parcelable saveState() {
            return null;
        }

        public void startUpdate(View view) {
        }

        public void finishUpdate(View view) {
        }
    }

    private class cRunnable implements Runnable {

        private cRunnable() {
        }

        public void run() {
            synchronized (h) {
                iVal = (iVal + 1) % f.size();
                l.obtainMessage().sendToTarget();
            }
        }
    }

    public SlideshowView(Context context) {
        this(context, null);
    }

    public SlideshowView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlideshowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = ImageLoader.getInstance();
        this.iVal = 0;
        this.l = new Handler() {

            public void handleMessage(Message message) {
                super.handleMessage(message);
                h.setCurrentItem(iVal);
            }
        };
        this.m = new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }


            public void a(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(ce.a.a(str));
                    e = new String[jSONArray.length()];
                    if (jSONArray != null && jSONArray.length() > 0) {
                        for (int i = 0; i < jSONArray.length(); i++) {
                            e[i] = ((RollPicBean) new Gson().fromJson(jSONArray.getJSONObject(i).toString(), RollPicBean.class)).getSlide_pic();
                        }
                        b(k);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        this.k = context;
        a(context);
        initData();
        a();
    }

    private void a() {
        this.j = Executors.newSingleThreadScheduledExecutor();
        this.j.scheduleAtFixedRate(new cRunnable(), 1, 4, TimeUnit.SECONDS);
    }

    private void b() {
        this.j.shutdown();
    }

    private void initData() {
        this.f = new ArrayList();
        this.g = new ArrayList();
        ce.b.a(this.m);
    }

    private void b(Context context) {
        if (this.e != null && this.e.length != 0) {
            LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, d);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dotLayout);
            linearLayout.removeAllViews();
            for (int i = 0; i < this.e.length; i++) {
                ImageView imageView = new ImageView(context);
                imageView.setTag(this.e[i]);
                if (i == 0) {
                    imageView.setScaleType(ScaleType.FIT_XY);
                }
                this.f.add(imageView);
                View imageView2 = new ImageView(context);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.leftMargin = 4;
                layoutParams.rightMargin = 4;
                linearLayout.addView(imageView2, layoutParams);
                this.g.add(imageView2);
            }
            this.h = (ViewPager) findViewById(R.id.viewPager);
            this.h.setFocusable(d);
            this.h.setAdapter(new bAdapter());
            this.h.setOnPageChangeListener(new aListener());
        }
    }

    private void c() {
        for (int i = 0; i < b; i++) {
            Drawable drawable = ((ImageView) this.f.get(i)).getDrawable();
            if (drawable != null) {
                drawable.setCallback(null);
            }
        }
    }

    public static void a(Context context) {
//        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
//        config.threadPriority(Thread.NORM_PRIORITY - 2);
//        config.denyCacheImageMultipleSizesInMemory();
//        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
//        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
//        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(context)
                .threadPriority(3).denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs().build());
    }
}
