package com.lzfutil.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Map;

public class p {
    private static SharedPreferences c(Context context) {
        return context.getSharedPreferences(p.class.getSimpleName(), 0);
    }

    private static Editor d(Context context) {
        return c(context).edit();
    }

    public static void a(Context context, String str, Object obj) {
        Editor d = d(context);
        if (obj instanceof String) {
            d.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            d.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            d.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            d.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            d.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof String[]) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] strArr = (String[]) obj;
            for (int i = 0; i < strArr.length; i++) {
                if (i != 0) {
                    stringBuilder.append(":");
                }
                stringBuilder.append(strArr[i]);
            }
            d.putString(str, stringBuilder.toString());
        }
        d.commit();
    }

    public static String a(Context context, String str, String str2) {
        return c(context).getString(str, str2);
    }

    public static String a(Context context, String str) {
        return c(context).getString(str, "");
    }

    public static int b(Context context, String str) {
        return c(context).getInt(str, 0);
    }

    public static boolean c(Context context, String str) {
        return c(context).getBoolean(str, true);
    }

    public static float d(Context context, String str) {
        return c(context).getFloat(str, 0.0f);
    }

    public static long e(Context context, String str) {
        return c(context).getLong(str, 0);
    }

    public static String[] f(Context context, String str) {
        return a(context, str).split(":");
    }

    public static void g(Context context, String str) {
        Editor d = d(context);
        d.remove(str);
        d.commit();
    }

    public static void a(Context context) {
        Editor d = d(context);
        d.clear();
        d.commit();
    }

    public static boolean h(Context context, String str) {
        return c(context).contains(str);
    }

    public static Map<String, ?> b(Context context) {
        return c(context).getAll();
    }
}
