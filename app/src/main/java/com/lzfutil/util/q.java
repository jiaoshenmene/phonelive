package com.lzfutil.util;

import android.text.format.Time;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class q {
    private static final Pattern a = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private static final Pattern b = Pattern.compile(".*?(gif|jpeg|png|jpg|bmp)");
    private static final Pattern c = Pattern.compile("^(https|http)://.*?$(net|com|.com.cn|org|me|)");
    private static final ThreadLocal<SimpleDateFormat> d = new ThreadLocal<SimpleDateFormat>() {
        protected /* synthetic */ SimpleDateFormat initialValue() {
            return a();
        }

        protected SimpleDateFormat a() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> e = new ThreadLocal<SimpleDateFormat>() {
        protected /* synthetic */ SimpleDateFormat initialValue() {
            return a();
        }

        protected SimpleDateFormat a() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static Date a(String str) {
        return a(str, (SimpleDateFormat) d.get());
    }

    public static Date a(String str, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String a(Date date) {
        return ((SimpleDateFormat) d.get()).format(date);
    }

    public static String b(String str) {
        Date a;
        if (v.a()) {
            a = a(str);
        } else {
            a = v.a(a(str), TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());
        }
        if (a == null) {
            return "Unknown";
        }
        String str2 = "";
        Calendar instance = Calendar.getInstance();
        int timeInMillis;
        if (((SimpleDateFormat) e.get()).format(instance.getTime()).equals(((SimpleDateFormat) e.get()).format(a))) {
            timeInMillis = (int) ((instance.getTimeInMillis() - a.getTime()) / 3600000);
            if (timeInMillis == 0) {
                return Math.max((instance.getTimeInMillis() - a.getTime()) / 60000, 1) + "\u5206\u949f\u524d";
            }
            return timeInMillis + "\u5c0f\u65f6\u524d";
        }
        timeInMillis = (int) ((instance.getTimeInMillis() / 86400000) - (a.getTime() / 86400000));
        if (timeInMillis == 0) {
            timeInMillis = (int) ((instance.getTimeInMillis() - a.getTime()) / 3600000);
            if (timeInMillis == 0) {
                return Math.max((instance.getTimeInMillis() - a.getTime()) / 60000, 1) + "\u5206\u949f\u524d";
            }
            return timeInMillis + "\u5c0f\u65f6\u524d";
        } else if (timeInMillis == 1) {
            return "\u6628\u5929";
        } else {
            if (timeInMillis == 2) {
                return "\u524d\u5929 ";
            }
            if (timeInMillis > 2 && timeInMillis < 31) {
                return timeInMillis + "\u5929\u524d";
            }
            if (timeInMillis >= 31 && timeInMillis <= 62) {
                return "\u4e00\u4e2a\u6708\u524d";
            }
            if (timeInMillis > 62 && timeInMillis <= 93) {
                return "2\u4e2a\u6708\u524d";
            }
            if (timeInMillis <= 93 || timeInMillis > 124) {
                return ((SimpleDateFormat) e.get()).format(a);
            }
            return "3\u4e2a\u6708\u524d";
        }
    }

    public static String c(String str) {
        String str2 = "";
        if (f(str)) {
            return "";
        }
        String[] strArr = new String[]{"\u661f\u671f\u65e5", "\u661f\u671f\u4e00", "\u661f\u671f\u4e8c", "\u661f\u671f\u4e09", "\u661f\u671f\u56db", "\u661f\u671f\u4e94", "\u661f\u671f\u516d"};
        String m = m("MM-dd");
        int a = intV(m.substring(3));
        int a2 = intV(m.substring(0, 2));
        int a3 = intV(str.substring(5, 7));
        int a4 = intV(str.substring(8, 10));
        Date date = new Date(intV(str.substring(0, 4)), a3 - 1, a4 - 1);
        if (a4 == a && a3 == a2) {
            return "\u4eca\u5929 / " + strArr[b(new Date())];
        }
        if (a4 == a + 1 && a3 == a2) {
            return "\u6628\u5929 / " + strArr[(b(new Date()) + 6) % 7];
        }
        if (a3 < 10) {
            str2 = "0";
        }
        str2 = str2 + a3 + "/";
        if (a4 < 10) {
            str2 = str2 + "0";
        }
        return str2 + a4 + " / " + strArr[b(date)];
    }

    public static String d(String str) {
        String str2 = "";
        if (f(str)) {
            return "";
        }
        Date a = a(str);
        if (a == null) {
            return str;
        }
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) e.get();
        if (b(a.getTime())) {
            simpleDateFormat.applyPattern(a(a.getTime()) ? "\u4e0a\u5348 hh:mm" : "\u4e0b\u5348 hh:mm");
            str2 = simpleDateFormat.format(a);
        } else if (c(a.getTime())) {
            simpleDateFormat.applyPattern(a(a.getTime()) ? "\u6628\u5929 \u4e0a\u5348 hh:mm" : "\u6628\u5929 \u4e0b\u5348 hh:mm");
            str2 = simpleDateFormat.format(a);
        } else if (d(a.getTime())) {
            simpleDateFormat.applyPattern(a(a.getTime()) ? "MM-dd \u4e0a\u5348 hh:mm" : "MM-dd \u4e0b\u5348 hh:mm");
            str2 = simpleDateFormat.format(a);
        } else {
            simpleDateFormat.applyPattern(a(a.getTime()) ? "yyyy-MM-dd \u4e0a\u5348 hh:mm" : "yyyy-MM-dd \u4e0b\u5348 hh:mm");
            str2 = simpleDateFormat.format(a);
        }
        return str2;
    }

    public static boolean a(long j) {
        Time time = new Time();
        time.set(j);
        int i = time.hour;
        return i >= 0 && i < 12;
    }

    public static boolean b(long j) {
        Time time = new Time();
        time.set(j);
        int i = time.year;
        int i2 = time.month;
        int i3 = time.monthDay;
        time.set(System.currentTimeMillis());
        return i == time.year && i2 == time.month && i3 == time.monthDay;
    }

    public static boolean c(long j) {
        Time time = new Time();
        time.set(j);
        int i = time.year;
        int i2 = time.month;
        int i3 = time.monthDay;
        time.set(System.currentTimeMillis());
        if (i == time.year && i2 == time.month && time.monthDay - i3 == 1) {
            return true;
        }
        return false;
    }

    public static boolean d(long j) {
        Time time = new Time();
        time.set(j);
        int i = time.year;
        time.set(System.currentTimeMillis());
        return i == time.year;
    }

    public static int b(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(Calendar.DAY_OF_WEEK) - 1;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public static boolean e(String str) {
        Date a = a(str);
        Date date = new Date();
        if (a == null || !((SimpleDateFormat) e.get()).format(date).equals(((SimpleDateFormat) e.get()).format(a))) {
            return false;
        }
        return true;
    }

    public static long a() {
        return Long.parseLong(((SimpleDateFormat) e.get()).format(Calendar.getInstance().getTime()).replace("-", ""));
    }

    public static String b() {
        return ((SimpleDateFormat) d.get()).format(Calendar.getInstance().getTime());
    }

    public static long a(String str, String str2) {
        long time;
        try {
            time = ((SimpleDateFormat) d.get()).parse(str2).getTime() - ((SimpleDateFormat) d.get()).parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            time = 0;
        }
        return time / 1000;
    }

    public static boolean f(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != ' ' && charAt != '\t' && charAt != '\r' && charAt != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean g(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        return a.matcher(str).matches();
    }

    public static boolean h(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        return b.matcher(str).matches();
    }

    public static boolean i(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        return c.matcher(str).matches();
    }

    public static int a(String str, int i) {
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
        }
        return i;
    }

    public static int intV(Object obj) {
        if (obj == null) {
            return 0;
        }
        return a(obj.toString(), 0);
    }

    public static long j(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean k(String str) {
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception e) {
            return false;
        }
    }

    public static String l(String str) {
        return str == null ? "" : str;
    }

    public static String a(InputStream inputStream) {
        StringBuffer stringBuffer = new StringBuffer();
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                stringBuffer.append(readLine + "<br>");
            }
        } catch (Exception e2) {

            e2.printStackTrace();
        } finally {
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e3) {
            }
        }
        return stringBuffer.toString();
    }

    public static String a(int i, int i2, String str) {
        if (str == null) {
            return "";
        }
        int i3;
        int i4;
        int length = str.length();
        if (i < 0) {
            i3 = 0;
        } else {
            i3 = i;
        }
        if (i3 > length) {
            i4 = length;
        } else {
            i4 = i3;
        }
        if (i2 < 0) {
            i2 = 1;
        }
        i3 = i4 + i2;
        if (i3 <= length) {
            length = i3;
        }
        return str.substring(i4, length);
    }

    public static int c() {
        return c(new Date());
    }

    public static int c(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setFirstDayOfWeek(Calendar.TUESDAY);
        instance.setTime(date);
        int i = instance.get(Calendar.WEEK_OF_YEAR) - 1;
        if (i == 0) {
            i = 52;
        }
        return i > 0 ? i : 1;
    }

    public static int[] d() {
        int[] iArr = new int[3];
        String[] split = m("yyyy-MM-dd").split("-");
        for (int i = 0; i < 3; i++) {
            try {
                iArr[i] = Integer.parseInt(split[i]);
            } catch (Exception e) {
                iArr[i] = 0;
            }
        }
        return iArr;
    }

    public static String m(String str) {
        return new SimpleDateFormat(str).format(new Date());
    }
}
