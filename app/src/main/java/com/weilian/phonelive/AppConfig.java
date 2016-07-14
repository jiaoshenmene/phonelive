package com.weilian.phonelive;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class AppConfig {
    public static final String SVR_URL = "http://www.51rexiu.com/";
    public static final String b = "rtmp://xp.taianweb.com/5showcam/";
    public static final String CHAT_URL = "http://www.51rexiu.com:19967";
    public static final String d = "cookie";
    public static final String e = "APP_UNIQUEID";
    public static final String f = "KEY_LOAD_IMAGE";
    public static final String g = "KEY_NOTIFICATION_ACCEPT";
    public static final String h = "KEY_NOTIFICATION_SOUND";
    public static final String i = "KEY_NOTIFICATION_VIBRATION";
    public static final String j = "KEY_NOTIFICATION_DISABLE_WHEN_EXIT";
    public static final String k = "KEY_CHECK_UPDATE";
    public static final String l = "KEY_DOUBLE_CLICK_EXIT";
    public static final String m = "KEY_TWEET_DRAFT";
    public static final String n = "KEY_NOTE_DRAFT";
    public static final String o = "KEY_FRIST_START";
    public static final String p = "night_mode_switch";
    public static final String q = "100942993";
    public static final String r = (Environment.getExternalStorageDirectory() + File.separator + "phoneLive" + File.separator + "live_img" + File.separator);
    public static final String s = (Environment.getExternalStorageDirectory() + File.separator + "phoneLive" + File.separator + "download" + File.separator);
    public static final String t = (Environment.getExternalStorageDirectory() + File.separator + "phoneLive" + File.separator + "music" + File.separator);
    private static final String u = "config";
    private static AppConfig w;
    private Context v;

    public static AppConfig a(Context context) {
        if (w == null) {
            w = new AppConfig();
            w.v = context;
        }
        return w;
    }

    public static SharedPreferences b(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String a(String str) {
        Properties a = a();
        return a != null ? a.getProperty(str) : null;
    }

    public Properties a() {
        FileInputStream fileInputStream = null;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(this.v.getDir(u, 0).getPath() + File.separator + u);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (Exception e2) {
            try {
                if (fileInputStream != null) fileInputStream.close();
            } catch (Exception e3) {
            }

        }
        return properties;
    }

    private void b(Properties properties) {
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream2 = new FileOutputStream(new File(this.v.getDir(u, 0), u));
            properties.store(fileOutputStream2, null);
            fileOutputStream2.flush();
            fileOutputStream2.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (fileOutputStream2 != null) fileOutputStream2.close();
            } catch (Exception e4) {
            }
        }
    }

    public void a(Properties properties) {
        Properties a = a();
        a.putAll(properties);
        b(a);
    }

    public void a(String str, String str2) {
        Properties a = a();
        a.setProperty(str, str2);
        b(a);
    }

    public void a(String... strArr) {
        Properties a = a();
        for (Object remove : strArr) {
            a.remove(remove);
        }
        b(a);
    }
}
