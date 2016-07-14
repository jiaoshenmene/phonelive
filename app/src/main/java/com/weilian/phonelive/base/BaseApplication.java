package com.weilian.phonelive.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ksy.recordlib.service.streamer.RecorderConstants;
import com.weilian.phonelive.R;

public class BaseApplication extends Application {
    private static String a = "creativelocker.pref";
    private static String b = "last_refresh_time.pref";
    private static String c = "";
    private static long d;
    private static boolean e = true;
    static Context f;
    static Resources g;

    static {
        if (VERSION.SDK_INT >= 9) {
        }
    }

    public void onCreate() {
        super.onCreate();
        f = getApplicationContext();
        g = f.getResources();
    }

    public static synchronized BaseApplication r() {
        BaseApplication baseApplication;
        synchronized (BaseApplication.class) {
            baseApplication = (BaseApplication) f;
        }
        return baseApplication;
    }

    public static Resources s() {
        return g;
    }

    @TargetApi(9)
    public static void a(Editor editor) {
        if (e) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static void a(String str, int i) {
        Editor edit = t().edit();
        edit.putInt(str, i);
        a(edit);
    }

    public static void a(String str, boolean z) {
        Editor edit = t().edit();
        edit.putBoolean(str, z);
        a(edit);
    }

    public static void b(String str, String str2) {
        Editor edit = t().edit();
        edit.putString(str, str2);
        a(edit);
    }

    public static boolean b(String str, boolean z) {
        return t().getBoolean(str, z);
    }

    public static String c(String str, String str2) {
        return t().getString(str, str2);
    }

    public static int b(String str, int i) {
        return t().getInt(str, i);
    }

    public static long a(String str, long j) {
        return t().getLong(str, j);
    }

    public static float a(String str, float f) {
        return t().getFloat(str, f);
    }

    @TargetApi(11)
    public static SharedPreferences t() {
        return r().getSharedPreferences(a, 4);
    }

    @TargetApi(11)
    public static SharedPreferences e(String str) {
        return r().getSharedPreferences(str, 4);
    }

    public static int[] u() {
        return new int[]{t().getInt("screen_width", RecorderConstants.TARGET_VIDEO_WIDTH), t().getInt("screen_height", 854)};
    }

    public static void a(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Editor edit = t().edit();
        edit.putInt("screen_width", displayMetrics.widthPixels);
        edit.putInt("screen_height", displayMetrics.heightPixels);
        edit.putFloat("density", displayMetrics.density);
        edit.commit();
    }

    public static String b(int i) {
        return g.getString(i);
    }

    public static String a(int i, Object... objArr) {
        return g.getString(i, objArr);
    }

    public static void c(int i) {
        a(i, 1, 0);
    }

    public static void f(String str) {
        a(str, 1, 0, 80);
    }

    public static void a(int i, int i2) {
        a(i, 1, i2);
    }

    public static void c(String str, int i) {
        a(str, 1, i, 80);
    }

    public static void d(int i) {
        a(i, 0, 0);
    }

    public static void g(String str) {
        a(str, 0, 0, 80);
    }

    public static void b(int i, Object... objArr) {
        a(i, 0, 0, 80, objArr);
    }

    public static void a(int i, int i2, int i3) {
        a(i, i2, i3, 80);
    }

    public static void a(int i, int i2, int i3, int i4) {
        a(r().getString(i), i2, i3, i4);
    }

    public static void a(int i, int i2, int i3, int i4, Object... objArr) {
        a(r().getString(i, objArr), i2, i3, i4);
    }

    public static void a(String str, int i, int i2, int i3) {
        if (str != null && !str.equalsIgnoreCase("")) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!str.equalsIgnoreCase(c) || Math.abs(currentTimeMillis - d) > 2000) {
                View inflate = LayoutInflater.from(r()).inflate(R.layout.view_toast, null);
                ((TextView) inflate.findViewById(R.id.title_tv)).setText(str);
                if (i2 != 0) {
                    ((ImageView) inflate.findViewById(R.id.icon_iv)).setImageResource(i2);
                    ((ImageView) inflate.findViewById(R.id.icon_iv)).setVisibility(View.VISIBLE);
                }
                Toast toast = new Toast(r());
                toast.setView(inflate);
                if (i3 == 17) {
                    toast.setGravity(i3, 0, 0);
                } else {
                    toast.setGravity(i3, 0, 35);
                }
                toast.setDuration(i);
                toast.show();
                c = str;
                d = System.currentTimeMillis();
            }
        }
    }
}
