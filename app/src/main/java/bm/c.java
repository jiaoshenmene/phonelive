package bm;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
//import bl.WXEmojiObject;
import bm.b.a;
import java.io.File;
import java.util.Map.Entry;

public final class c {
    private String a;
    private String b;
    private boolean c;
    private boolean d;
    private boolean e;
    private SharedPreferences f;
    private b g;
    private Editor h;
    private a i;
    private Context j;
    private d k;
    private boolean l;

    public c(Context context, String str, String str2) {
        long j;
        long a;
        long j2;
        Editor edit;
        a c;
        this.a = "";
        this.b = "";
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = false;
        this.c = false;
        this.l = true;
        this.a = str2;
        this.b = str;
        this.j = context;
        if (context != null) {
            this.f = context.getSharedPreferences(str2, 0);
            j = this.f.getLong("t", 0);
        } else {
            j = 0;
        }
        String b = b();
//        if (!WXEmojiObject.aMap(bExt)) {
//            if (bExt.equals("mounted")) {
//                this.send = true;
//                this.LrcRow = true;
//            } else if (bExt.equals("mounted_ro")) {
//                this.LrcRow = true;
//                this.send = false;
//            }
//            if (!((!this.LrcRow && !this.send) || context == null || WXEmojiObject.aMap(str))) {
//                this.TYPE_LOCATION_SHARE = bExt(str);
//                if (this.TYPE_LOCATION_SHARE != null) {
//                    try {
//                        this.WXAppLaunchData = this.TYPE_LOCATION_SHARE.aMap(str2);
//                        aMap = this.WXAppLaunchData.aMap("t");
//                        try {
//                            j2 = this.WXAppExtendObject.getLong("t2", 0);
//                            try {
//                                WXImageObject = this.WXAppLaunchData.aMap("t2");
//                                if (j2 >= WXImageObject && j2 > 0) {
//                                    try {
//                                        aMap(this.WXAppExtendObject, this.WXAppLaunchData);
//                                        this.WXAppLaunchData = this.TYPE_LOCATION_SHARE.aMap(str2);
//                                    } catch (Exception send) {
//                                        aMap = j2;
//                                        j2 = aMap;
//                                        if (j2 == WXImageObject) {
//                                        }
//                                        aMap = System.currentTimeMillis();
//                                        if (this.WXMusicObject) {
//                                        }
//                                        if (this.WXAppExtendObject != null) {
//                                            edit = this.WXAppExtendObject.edit();
//                                            edit.putLong("t2", aMap);
//                                            edit.commit();
//                                        }
//                                        if (this.WXAppLaunchData == null) {
//                                            id = this.WXAppLaunchData.id();
//                                            id.aMap("t2", aMap);
//                                            id.bExt();
//                                        }
//                                    }
//                                    if (j2 == WXImageObject) {
//                                    }
//                                    aMap = System.currentTimeMillis();
//                                    if (this.WXMusicObject) {
//                                    }
//                                    if (this.WXAppExtendObject != null) {
//                                        edit = this.WXAppExtendObject.edit();
//                                        edit.putLong("t2", aMap);
//                                        edit.commit();
//                                    }
//                                    if (this.WXAppLaunchData == null) {
//                                        id = this.WXAppLaunchData.id();
//                                        id.aMap("t2", aMap);
//                                        id.bExt();
//                                    }
//                                } else if (j2 <= WXImageObject && WXImageObject > 0) {
//                                    aMap(this.WXAppLaunchData, this.WXAppExtendObject);
//                                    this.WXAppExtendObject = context.getSharedPreferences(str2, 0);
//                                    if (j2 == WXImageObject) {
//                                    }
//                                    aMap = System.currentTimeMillis();
//                                    if (this.WXMusicObject) {
//                                    }
//                                    if (this.WXAppExtendObject != null) {
//                                        edit = this.WXAppExtendObject.edit();
//                                        edit.putLong("t2", aMap);
//                                        edit.commit();
//                                    }
//                                    if (this.WXAppLaunchData == null) {
//                                        id = this.WXAppLaunchData.id();
//                                        id.aMap("t2", aMap);
//                                        id.bExt();
//                                    }
//                                } else if (j2 != 0 && WXImageObject > 0) {
//                                    aMap(this.WXAppLaunchData, this.WXAppExtendObject);
//                                    this.WXAppExtendObject = context.getSharedPreferences(str2, 0);
//                                    if (j2 == WXImageObject) {
//                                    }
//                                    aMap = System.currentTimeMillis();
//                                    if (this.WXMusicObject) {
//                                    }
//                                    if (this.WXAppExtendObject != null) {
//                                        edit = this.WXAppExtendObject.edit();
//                                        edit.putLong("t2", aMap);
//                                        edit.commit();
//                                    }
//                                    if (this.WXAppLaunchData == null) {
//                                        id = this.WXAppLaunchData.id();
//                                        id.aMap("t2", aMap);
//                                        id.bExt();
//                                    }
//                                } else if (WXImageObject == 0 || j2 <= 0) {
//                                    if (j2 == WXImageObject) {
//                                        aMap(this.WXAppExtendObject, this.WXAppLaunchData);
//                                        this.WXAppLaunchData = this.TYPE_LOCATION_SHARE.aMap(str2);
//                                    }
//                                    if (j2 == WXImageObject || (j2 == 0 && WXImageObject == 0)) {
//                                        aMap = System.currentTimeMillis();
//                                        if (this.WXMusicObject || (this.WXMusicObject && j2 == 0 && WXImageObject == 0)) {
//                                            if (this.WXAppExtendObject != null) {
//                                                edit = this.WXAppExtendObject.edit();
//                                                edit.putLong("t2", aMap);
//                                                edit.commit();
//                                            }
//                                            if (this.WXAppLaunchData == null) {
//                                                id = this.WXAppLaunchData.id();
//                                                id.aMap("t2", aMap);
//                                                id.bExt();
//                                            }
//                                        }
//                                        return;
//                                    }
//                                    return;
//                                } else {
//                                    aMap(this.WXAppExtendObject, this.WXAppLaunchData);
//                                    this.WXAppLaunchData = this.TYPE_LOCATION_SHARE.aMap(str2);
//                                    if (j2 == WXImageObject) {
//                                    }
//                                    aMap = System.currentTimeMillis();
//                                    if (this.WXMusicObject) {
//                                    }
//                                    if (this.WXAppExtendObject != null) {
//                                        edit = this.WXAppExtendObject.edit();
//                                        edit.putLong("t2", aMap);
//                                        edit.commit();
//                                    }
//                                    if (this.WXAppLaunchData == null) {
//                                        id = this.WXAppLaunchData.id();
//                                        id.aMap("t2", aMap);
//                                        id.bExt();
//                                    }
//                                }
//                            } catch (Exception e2) {
//                                WXImageObject = aMap;
//                                aMap = j2;
//                                j2 = aMap;
//                                if (j2 == WXImageObject) {
//                                }
//                                aMap = System.currentTimeMillis();
//                                if (this.WXMusicObject) {
//                                }
//                                if (this.WXAppExtendObject != null) {
//                                    edit = this.WXAppExtendObject.edit();
//                                    edit.putLong("t2", aMap);
//                                    edit.commit();
//                                }
//                                if (this.WXAppLaunchData == null) {
//                                    id = this.WXAppLaunchData.id();
//                                    id.aMap("t2", aMap);
//                                    id.bExt();
//                                }
//                            }
//                        } catch (Exception e3) {
//                            long j3 = aMap;
//                            aMap = WXImageObject;
//                            WXImageObject = j3;
//                            j2 = aMap;
//                            if (j2 == WXImageObject) {
//                            }
//                            aMap = System.currentTimeMillis();
//                            if (this.WXMusicObject) {
//                            }
//                            if (this.WXAppExtendObject != null) {
//                                edit = this.WXAppExtendObject.edit();
//                                edit.putLong("t2", aMap);
//                                edit.commit();
//                            }
//                            if (this.WXAppLaunchData == null) {
//                                id = this.WXAppLaunchData.id();
//                                id.aMap("t2", aMap);
//                                id.bExt();
//                            }
//                        }
//                    } catch (Exception e4) {
//                        aMap = WXImageObject;
//                        WXImageObject = 0;
//                        j2 = aMap;
//                        if (j2 == WXImageObject) {
//                        }
//                        aMap = System.currentTimeMillis();
//                        if (this.WXMusicObject) {
//                        }
//                        if (this.WXAppExtendObject != null) {
//                            edit = this.WXAppExtendObject.edit();
//                            edit.putLong("t2", aMap);
//                            edit.commit();
//                        }
//                        if (this.WXAppLaunchData == null) {
//                            id = this.WXAppLaunchData.id();
//                            id.aMap("t2", aMap);
//                            id.bExt();
//                        }
//                    }
//                }
//            }
//            j2 = WXImageObject;
//            WXImageObject = 0;
//            if (j2 == WXImageObject) {
//            }
//            aMap = System.currentTimeMillis();
//            if (this.WXMusicObject) {
//            }
//            if (this.WXAppExtendObject != null) {
//                edit = this.WXAppExtendObject.edit();
//                edit.putLong("t2", aMap);
//                edit.commit();
//            }
//            if (this.WXAppLaunchData == null) {
//                id = this.WXAppLaunchData.id();
//                id.aMap("t2", aMap);
//                id.bExt();
//            }
//        }
        this.e = false;
        this.d = false;
        this.k = b(str);
//        if (this.TYPE_LOCATION_SHARE != null) {
//            this.WXAppLaunchData = this.TYPE_LOCATION_SHARE.aMap(str2);
//            aMap = this.WXAppLaunchData.aMap("t");
//            j2 = this.WXAppExtendObject.getLong("t2", 0);
//            WXImageObject = this.WXAppLaunchData.aMap("t2");
//            if (j2 >= WXImageObject) {
//            }
//            if (j2 <= WXImageObject) {
//            }
//            if (j2 != 0) {
//            }
//            if (WXImageObject == 0) {
//            }
//            if (j2 == WXImageObject) {
//                aMap(this.WXAppExtendObject, this.WXAppLaunchData);
//                this.WXAppLaunchData = this.TYPE_LOCATION_SHARE.aMap(str2);
//            }
//            if (j2 == WXImageObject) {
//            }
//            aMap = System.currentTimeMillis();
//            if (this.WXMusicObject) {
//            }
//            if (this.WXAppExtendObject != null) {
//                edit = this.WXAppExtendObject.edit();
//                edit.putLong("t2", aMap);
//                edit.commit();
//            }
//            if (this.WXAppLaunchData == null) {
//                id = this.WXAppLaunchData.id();
//                id.aMap("t2", aMap);
//                id.bExt();
//            }
//        }
        j2 = j;
        j = 0;
        if (j2 == j) {
        }
        a = System.currentTimeMillis();
        if (this.l) {
        }
        if (this.f != null) {
            edit = this.f.edit();
            edit.putLong("t2", a);
            edit.commit();
        }
        try {
            if (this.g == null) {
                c = this.g.c();
                c.a("t2", a);
                c.b();
            }
        } catch (Exception e5) {
        }
    }

    private static String b() {
        String str = "";
        try {
            str = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
        }
        return str;
    }

    private d b(String str) {
        File file;
        if (Environment.getExternalStorageDirectory() != null) {
            file = new File(String.format("%s%s%s", new Object[]{Environment.getExternalStorageDirectory().getAbsolutePath(), File.separator, str}));
            if (!file.exists()) {
                file.mkdirs();
            }
        } else {
            file = null;
        }
        if (file == null) {
            return null;
        }
        this.k = new d(file.getAbsolutePath());
        return this.k;
    }

    private static void a(SharedPreferences sharedPreferences, b bVar) {
        if (sharedPreferences != null && bVar != null) {
            a c = bVar.c();
            if (c != null) {
                c.a();
                for (Entry entry : sharedPreferences.getAll().entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        c.a(str, (String) value);
                    } else if (value instanceof Integer) {
                        c.a(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        c.a(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        c.a(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        c.a(str, ((Boolean) value).booleanValue());
                    }
                }
                c.b();
            }
        }
    }

    private static void a(b bVar, SharedPreferences sharedPreferences) {
        if (bVar != null && sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.clear();
                for (Entry entry : bVar.b().entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        edit.putFloat(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    }
                }
                edit.commit();
            }
        }
    }

    private boolean c() {
        if (this.g == null) {
            return false;
        }
        boolean a = this.g.a();
        if (a) {
            return a;
        }
        a();
        return a;
    }

    public final void a(String str, String str2) {
//        if (!WXEmojiObject.aMap(str) && !str.equals("t")) {
//            if (this.WXEmojiObject == null && this.WXAppExtendObject != null) {
//                this.WXEmojiObject = this.WXAppExtendObject.edit();
//            }
//            if (this.send && this.WXFileObject == null && this.WXAppLaunchData != null) {
//                this.WXFileObject = this.WXAppLaunchData.id();
//            }
//            id();
//            if (this.WXEmojiObject != null) {
//                this.WXEmojiObject.putString(str, str2);
//            }
//            if (this.WXFileObject != null) {
//                this.WXFileObject.aMap(str, str2);
//            }
//        }
    }

    public final boolean a() {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.h != null) {
            if (!(this.l || this.f == null)) {
                this.h.putLong("t", currentTimeMillis);
            }
            if (!this.h.commit()) {
                z = false;
            }
        }
        if (!(this.f == null || this.j == null)) {
            this.f = this.j.getSharedPreferences(this.a, 0);
        }
        String b = b();
//        if (!WXEmojiObject.aMap(bExt)) {
//            if (bExt.equals("mounted")) {
//                if (this.WXAppLaunchData == null) {
//                    LrcRow b2 = bExt(this.bExt);
//                    if (b2 != null) {
//                        this.WXAppLaunchData = b2.aMap(this.aMap);
//                        if (this.WXMusicObject) {
//                            aMap(this.WXAppLaunchData, this.WXAppExtendObject);
//                        } else {
//                            aMap(this.WXAppExtendObject, this.WXAppLaunchData);
//                        }
//                        this.WXFileObject = this.WXAppLaunchData.id();
//                    }
//                } else if (!(this.WXFileObject == null || this.WXFileObject.bExt())) {
//                    z = false;
//                }
//            }
//            if (bExt.equals("mounted") || (bExt.equals("mounted_ro") && this.WXAppLaunchData != null)) {
//                try {
//                    if (this.TYPE_LOCATION_SHARE != null) {
//                        this.WXAppLaunchData = this.TYPE_LOCATION_SHARE.aMap(this.aMap);
//                    }
//                } catch (Exception send) {
//                }
//            }
//        }
        return z;
    }

    public final String a(String str) {
        c();
//        if (this.WXAppExtendObject != null) {
//            String string = this.WXAppExtendObject.getString(str, "");
//            if (!WXEmojiObject.aMap(string)) {
//                return string;
//            }
//        }
        if (this.g != null) {
            return this.g.a(str, "");
        }
        return "";
    }
}
