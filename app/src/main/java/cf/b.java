package cf;

import android.content.Context;
import android.os.Environment;
import java.io.File;

public class b {
    public static void a(Context context) {
        b(context.getCacheDir());
        b(context.getFilesDir());
    }

    public static void b(Context context) {
        b(new File("/data/data/" + context.getPackageName() + "/databases"));
    }

    public static void c(Context context) {
        b(new File("/data/data/" + context.getPackageName() + "/shared_prefs"));
    }

    public static void a(Context context, String str) {
        context.deleteDatabase(str);
    }

    public static void d(Context context) {
        b(context.getFilesDir());
    }

    public static void e(Context context) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            b(context.getExternalCacheDir());
        }
    }

    public static void a(String str) {
        b(new File(str));
    }

    public static void a(File file) {
        b(file);
    }

    public static void a(Context context, String... strArr) {
        a(context);
        e(context);
        b(context);
        c(context);
        d(context);
        for (String a : strArr) {
            a(a);
        }
    }

    private static void b(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    b(file2);
                }
                file2.delete();
            }
        }
    }
}
