package com.lzfutil.util;

import com.hyphenate.util.HanziToPinyin.Token;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class r {
    public static final String a = "VLC/Util/Strings";

    public static String a(String str) {
        if (!str.endsWith("/") || str.length() <= 1) {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }

    static boolean a(String[] strArr, String str) {
        for (String startsWith : strArr) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    public static String a(long j) {
        return a(j, false);
    }

    public static String b(long j) {
        return a(j, true);
    }

    static String a(long j, boolean z) {
        Object obj;
        if (j < 0) {
            obj = 1;
        } else {
            obj = null;
        }
        long abs = Math.abs(j) / 1000;
        int i = (int) (abs % 60);
        abs /= 60;
        int i2 = (int) (abs % 60);
        abs /= 60;
        int i3 = (int) abs;
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        decimalFormat.applyPattern("00");
        if (z) {
            if (abs > 0) {
                String str;
                StringBuilder stringBuilder = new StringBuilder();
                if (obj != null) {
                    str = "-";
                } else {
                    str = "";
                }
                return stringBuilder.append(str).append(i3).append("protocol").append(decimalFormat.format((long) i2)).append("min").toString();
            } else if (i2 > 0) {
                return (obj != null ? "-" : "") + i2 + "min";
            } else {
                return (obj != null ? "-" : "") + i + "s";
            }
        } else if (abs > 0) {
            return (obj != null ? "-" : "") + i3 + ":" + decimalFormat.format((long) i2) + ":" + decimalFormat.format((long) i);
        } else {
            return (obj != null ? "-" : "") + i2 + ":" + decimalFormat.format((long) i);
        }
    }

    public static boolean a(String str, String str2) {
        if (str == null) {
            return str2 == null;
        } else {
            return str.equals(str2);
        }
    }

    public static String a(float f) {
        return String.format(Locale.US, "%.2fx", new Object[]{Float.valueOf(f)});
    }

    public static String c(long j) {
        if (j <= 0) {
            return "0";
        }
        int log10 = (int) (Math.log10((double) j) / Math.log10(1024.0d));
        return new DecimalFormat("#,##0.#").format(((double) j) / Math.pow(1024.0d, (double) log10)) + Token.SEPARATOR + new String[]{"B", "kB", "MB", "GB", "TB"}[log10];
    }

    public static String b(String str) {
        if (str == null) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf > -1) {
            return str.substring(lastIndexOf + 1);
        }
        return str;
    }
}
