package com.lzfutil.util;

import android.util.Log;

public class t {
    public static final String a = "WEIPENG";
    public static boolean b = true;

    public static final void a(String str) {
        if (b) {
            Log.d(a, str);
        }
    }

    public static final void b(String str) {
        if (b) {
            Log.e(a,  str);
        }
    }

    public static final void c(String str) {
        if (b) {
            Log.i(a, str);
        }
    }

    public static final void a(String str, String str2) {
        if (b) {
            Log.i(str, str2);
        }
    }

    public static final void d(String str) {
        if (b) {
            Log.v(a, str);
        }
    }

    public static final void e(String str) {
        if (b) {
            Log.w(a, str);
        }
    }
}
