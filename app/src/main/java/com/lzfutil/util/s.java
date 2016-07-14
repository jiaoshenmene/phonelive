package com.lzfutil.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.PowerManager;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import com.hyphenate.util.HanziToPinyin.Token;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseApplication;
import java.io.File;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.UUID;

@TargetApi(14)
public class s {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static boolean d;
    public static boolean e;
    public static boolean f;
    public static float g = 0.0f;
    private static Boolean h = null;
    private static Boolean i = null;
    private static Boolean j = null;
    private static Integer k = null;
    private static int l = -1;

    static {
        boolean z;
        boolean z2 = true;
        if (VERSION.SDK_INT >= 14) {
            z = true;
        } else {
            z = false;
        }
        e = z;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        d = z;
        if (VERSION.SDK_INT >= 11) {
            z2 = false;
        }
        f = z2;
    }

    public static float a(float f) {
        return (((float) c().densityDpi) / 160.0f) * f;
    }

    public static int a(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int a() {
        if (k == null) {
            Integer valueOf = Integer.valueOf(BaseApplication.r().getResources().getConfiguration().screenLayout & 15);
            k = valueOf;
            k = Integer.valueOf(Math.max(valueOf.intValue(), a));
        }
        return k.intValue();
    }

    public static float b() {
        if (((double) g) == 0.0d) {
            g = c().density;
        }
        return g;
    }

    public static DisplayMetrics c() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) BaseApplication.r().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static float d() {
        return (float) c().heightPixels;
    }

    public static float e() {
        return (float) c().widthPixels;
    }

    public static int[] a(Activity activity) {
        int i;
        int[] iArr = new int[b];
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        if (VERSION.SDK_INT < 14 || VERSION.SDK_INT >= 17) {
            i = i3;
            i3 = i2;
        } else {
            try {
                i2 = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i3 = i2;
            } catch (Exception e) {
                int i4 = i3;
                i3 = i2;
                i = i4;
            }
        }
        if (VERSION.SDK_INT >= 17) {
            try {
                Point point = new Point();
                Class[] clsArr = new Class[a];
                clsArr[0] = Point.class;
                Method method = Display.class.getMethod("getRealSize", clsArr);
                Object[] objArr = new Object[a];
                objArr[0] = point;
                method.invoke(defaultDisplay, objArr);
                i3 = point.x;
                i = point.y;
            } catch (Exception e2) {
            }
        }
        iArr[0] = i3;
        iArr[a] = i;
        return iArr;
    }

    public static int f() {
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            return BaseApplication.r().getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String g() {
        String string = BaseApplication.t().getString("udid", "");
        if (string.length() != 0) {
            return string;
        }
        Editor edit = BaseApplication.t().edit();
        Object[] objArr = new Object[a];
        objArr[0] = UUID.randomUUID();
        string = String.format("%hostnameVerifier", objArr);
        edit.putString("udid", string);
        edit.commit();
        return string;
    }

    public static boolean h() {
        boolean z = false;
        if (h == null) {
            boolean z2;
            if ((BaseApplication.r().getResources().getConfiguration().screenLayout & 15) >= c) {
                z2 = true;
            } else {
                z2 = false;
            }
            Boolean valueOf = Boolean.valueOf(z2);
            h = valueOf;
            if (!valueOf.booleanValue()) {
                if (b() > 1.5f) {
                    z = true;
                }
                h = Boolean.valueOf(z);
            }
        }
        return h.booleanValue();
    }

    public static final boolean i() {
        if (i == null) {
            PackageManager packageManager = BaseApplication.r().getPackageManager();
            boolean hasSystemFeature = packageManager.hasSystemFeature("android.hardware.camera.front");
            boolean hasSystemFeature2 = packageManager.hasSystemFeature("android.hardware.camera");
            if (hasSystemFeature || hasSystemFeature2) {
                hasSystemFeature2 = true;
            } else {
                hasSystemFeature2 = false;
            }
            i = Boolean.valueOf(hasSystemFeature2);
        }
        return i.booleanValue();
    }

    public static boolean a(Context context) {
        if (f) {
            return true;
        }
        if (e) {
            return ViewConfiguration.get(context).hasPermanentMenuKey();
        }
        return false;
    }

    public static boolean j() {
        if (((ConnectivityManager) BaseApplication.r().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null) {
            return true;
        }
        return false;
    }

    public static boolean a(Activity activity, String str) {
        try {
            Intent intent = new Intent();
            intent.setPackage("com.android.vending");
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("market://details?id=" + str));
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean a(String str) {
        try {
            if (BaseApplication.r().getPackageManager().getPackageInfo(str, 0) != null) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            t.b(e.getMessage());
            return false;
        }
    }

    public static void a(View view) {
        if (f && view != null) {
            view.setPadding(view.getWidth(), 0, 0, 0);
        }
    }

    public static void b(View view) {
        if (view != null) {
            ((InputMethodManager) BaseApplication.r().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean k() {
        if (BaseApplication.r().getResources().getConfiguration().orientation == b) {
            return true;
        }
        return false;
    }

    public static boolean l() {
        if (BaseApplication.r().getResources().getConfiguration().orientation != a) {
            return false;
        }
        return true;
    }

    public static boolean m() {
        if (j == null) {
            boolean z;
            if ((BaseApplication.r().getResources().getConfiguration().screenLayout & 15) >= c) {
                z = true;
            } else {
                z = false;
            }
            j = Boolean.valueOf(z);
        }
        return j.booleanValue();
    }

    public static float b(float f) {
        return f / (((float) c().densityDpi) / 160.0f);
    }

    public static void c(View view) {
        if (f && view != null) {
            view.setPadding(0, 0, 0, 0);
        }
    }

    public static void a(Dialog dialog) {
        dialog.getWindow().setSoftInputMode(4);
    }

    public static void d(View view) {
        ((InputMethodManager) BaseApplication.r().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, b);
    }

    public static void e(View view) {
        ((InputMethodManager) BaseApplication.r().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0, b);
    }

    public static boolean n() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String o() {
        return BaseApplication.r().getResources().getConfiguration().locale.getLanguage() + "-" + BaseApplication.r().getResources().getConfiguration().locale.getCountry();
    }

    public static boolean p() {
        if (BaseApplication.r().getResources().getConfiguration().locale.getCountry().equalsIgnoreCase("CN")) {
            return true;
        }
        return false;
    }

    public static String a(double d, double d2) {
        double d3 = d / d2;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(b);
        return percentInstance.format(d3);
    }

    public static String b(double d, double d2) {
        double d3 = d / d2;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(0);
        return percentInstance.format(d3);
    }

    public static void a(Context context, String str) {
        if (b(context)) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("market://details?id=" + str));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
                return;
            }
            return;
        }
        BaseApplication.f("\u4f60\u624b\u673a\u4e2d\u6ca1\u6709\u5b89\u88c5\u5e94\u7528\u5e02\u573a\uff01");
    }

    public static boolean b(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            return true;
        }
        return false;
    }

    public static void c(Context context) {
        if (context != null) {
            String packageName = context.getPackageName();
            try {
                a(context, packageName);
            } catch (Exception e) {
                try {
                    context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://market.android.com/details?id=" + packageName)));
                } catch (Exception e2) {
                }
            }
        }
    }

    public static void b(Activity activity) {
        LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.flags |= AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT;
        activity.getWindow().setAttributes(attributes);
        activity.getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
    }

    public static void c(Activity activity) {
        LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.flags &= -1025;
        activity.getWindow().setAttributes(attributes);
        activity.getWindow().clearFlags(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
    }

    public static PackageInfo b(String str) {
        try {
            return BaseApplication.r().getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e) {
            t.b(e.getMessage());
            return null;
        }
    }

    public static int q() {
        int i = 0;
        try {
            return BaseApplication.r().getPackageManager().getPackageInfo(BaseApplication.r().getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }

    public static int c(String str) {
        int i = 0;
        try {
            return BaseApplication.r().getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }

    public static String r() {
        String str = "";
        try {
            return BaseApplication.r().getPackageManager().getPackageInfo(BaseApplication.r().getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public static boolean s() {
        return ((PowerManager) BaseApplication.r().getSystemService(Context.POWER_SERVICE)).isScreenOn();
    }

    public static void a(Context context, File file) {
        if (file != null && file.exists()) {
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setAction("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }

    public static Intent a(File file) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        return intent;
    }

    public static void b(Context context, String str) {
        context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)));
    }

    public static void a(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str2));
        intent.putExtra("sms_body", str);
        context.startActivity(intent);
    }

    public static void d(Context context) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void e(Context context) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:"));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void f(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
        intent.setFlags(885260288);
        context.startActivity(intent);
    }

    public static String t() {
        return ((TelephonyManager) BaseApplication.r().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    public static String u() {
        return Build.MODEL;
    }

    public static void c(Context context, String str) {
        Intent launchIntentForPackage = BaseApplication.r().getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            launchIntentForPackage = new Intent(str);
        } else {
            t.c("Action:" + launchIntentForPackage.getAction());
        }
        context.startActivity(launchIntentForPackage);
    }

    public static boolean b(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(str, str2));
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean v() {
        NetworkInfo[] allNetworkInfo = ((ConnectivityManager) BaseApplication.r().getSystemService(Context.CONNECTIVITY_SERVICE)).getAllNetworkInfo();
        boolean z = false;
        for (int i = 0; i < allNetworkInfo.length; i += a) {
            if (allNetworkInfo[i].getState() == State.CONNECTED) {
                if (allNetworkInfo[i].getType() == 0) {
                    z = false;
                }
                if (allNetworkInfo[i].getType() == a) {
                    z = true;
                }
            }
        }
        return z;
    }

    public static void d(Context context, String str) {
        if (a(str)) {
            context.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + str)));
        }
    }

    public static void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            ((ClipboardManager) BaseApplication.r().getSystemService(Context.CLIPBOARD_SERVICE)).setText(str);
            BaseApplication.c(R.string.copy_success);
        }
    }

    public static void a(Context context, String str, String str2, String... strArr) {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("message/rfc822");
            intent.putExtra("android.intent.extra.EMAIL", strArr);
            intent.putExtra("android.intent.extra.SUBJECT", str);
            intent.putExtra("android.intent.extra.TEXT", str2);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int w() {
        int i = 38;
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            i = BaseApplication.r().getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int g(Context context) {
        int i = 0;
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(16843499, typedValue, true)) {
            i = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }
        if (i == 0 && context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }
        return i;
    }

    public static boolean d(Activity activity) {
        if ((activity.getWindow().getAttributes().flags & AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT) == AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT) {
            return false;
        }
        return true;
    }

    public static void a(Activity activity, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "\u5206\u4eab\uff1a" + str);
        intent.putExtra("android.intent.extra.TEXT", str + Token.SEPARATOR + str2);
        activity.startActivity(Intent.createChooser(intent, "\u9009\u62e9\u5206\u4eab"));
    }

    public static int x() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) AppContext.c().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 0;
        }
        int i;
        int type = activeNetworkInfo.getType();
        if (type == 0) {
            String extraInfo = activeNetworkInfo.getExtraInfo();
            if (!q.f(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    i = c;
                } else {
                    i = b;
                }
            }
            i = 0;
        } else {
            if (type == a) {
                i = a;
            }
            i = 0;
        }
        return i;
    }
}
