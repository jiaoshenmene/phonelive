package com.lzfutil.util;

import android.app.Activity;
import com.weilian.phonelive.AppContext;
import java.util.Locale;

public class f {
    public static String a = "\u518d\u6309\u4e00\u6b21\u9000\u51fa\u7a0b\u5e8f";
    public static String b = "Press again to exit the program";
    private int c;
    private long d;
    private String e;
    private Activity f;

    public f(Activity activity, String str, int i) {
        this.f = activity;
        this.e = str;
        this.c = i;
    }

    public f(Activity activity, String str) {
        this(activity, str, 2000);
    }

    public f(Activity activity) {
        this(activity, Locale.CHINA.equals(Locale.getDefault()) ? a : b, 2000);
    }

    public boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - this.d < ((long) this.c);
        this.d = currentTimeMillis;
        if (!z) {
            AppContext.a(this.f, this.e);
        }
        return z;
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(String str) {
        this.e = str;
    }
}
