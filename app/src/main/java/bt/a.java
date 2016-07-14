package bt;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.Process;

public final class a {
    public static int aVal = 6;
    private static aInterface b;
    private static aInterface c;
    private static final String d;

    public interface aInterface {
        int a();

        void a(String str, String str2);

        void b(String str, String str2);

        void c(String str, String str2);
    }

    static {
        aInterface bVar = new b();
        b = bVar;
        c = bVar;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("VERSION.RELEASE:[" + VERSION.RELEASE);
        stringBuilder.append("] VERSION.CODENAME:[" + VERSION.CODENAME);
        stringBuilder.append("] VERSION.INCREMENTAL:[" + VERSION.INCREMENTAL);
        stringBuilder.append("] BOARD:[" + Build.BOARD);
        stringBuilder.append("] DEVICE:[" + Build.DEVICE);
        stringBuilder.append("] DISPLAY:[" + Build.DISPLAY);
        stringBuilder.append("] FINGERPRINT:[" + Build.FINGERPRINT);
        stringBuilder.append("] HOST:[" + Build.HOST);
        stringBuilder.append("] MANUFACTURER:[" + Build.MANUFACTURER);
        stringBuilder.append("] MODEL:[" + Build.MODEL);
        stringBuilder.append("] PRODUCT:[" + Build.PRODUCT);
        stringBuilder.append("] TAGS:[" + Build.TAGS);
        stringBuilder.append("] TYPE:[" + Build.TYPE);
        stringBuilder.append("] USER:[" + Build.USER + "]");
        d = stringBuilder.toString();
    }

    public static void a(String str, String str2) {
        a(str, str2, new Object[]{});
    }

    public static void a(String str, String str2, Object... objArr) {
        if (c != null && c.a() <= 4) {
            String format = objArr == null ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = "";
            }
            aInterface aVar = c;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.c(str, format);
        }
    }

    public static void b(String str, String str2) {
        if (c != null && c.a() <= 2) {
            if (str2 == null) {
                str2 = "";
            }
            aInterface aVar = c;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.a(str, str2);
        }
    }

    public static void c(String str, String str2) {
        if (c != null && c.a() <= 1) {
            if (str2 == null) {
                str2 = "";
            }
            aInterface aVar = c;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.b(str, str2);
        }
    }
}
