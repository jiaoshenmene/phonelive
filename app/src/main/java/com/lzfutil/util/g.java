package com.lzfutil.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import com.ksyun.media.player.KSYMediaMeta;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class g {

    public enum a {
        SUCCESS,
        EXITS,
        ERROR
    }

    public static void a(Context context, String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes());
            openFileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String a(Context context, String str) {
        try {
            return a(context.openFileInput(str));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(InputStream inputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return byteArrayOutputStream.toString();
                }
            }
        } catch (IOException e) {
            Log.i("FileTest", e.getMessage());
            return null;
        }
    }

    public static File a(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(str, str2 + str2);
    }

    public static boolean a(byte[] bArr, String str, String str2) {
        FileOutputStream fileOutputStream = null;
        Exception e;
        Throwable th;
        boolean equals = Environment.getExternalStorageState().equals("mounted");
        String str3 = "";
        if (equals) {
            str3 = Environment.getExternalStorageDirectory() + File.separator + str + File.separator;
        }
        File file = new File(str3);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            fileOutputStream = new FileOutputStream(new File(str3 + str2));
            fileOutputStream.write(bArr);
            try {
                fileOutputStream.close();
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                return true;
            }
        } catch (Exception e5) {
            e = e5;
            if(fileOutputStream!=null) try {
                fileOutputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            fileOutputStream = null;
            e.printStackTrace();
            return false;
        }
    }

    public static String a(String str) {
        if (q.f(str)) {
            return "";
        }
        return str.substring(str.lastIndexOf(File.separator) + 1);
    }

    public static String b(String str) {
        if (q.f(str)) {
            return "";
        }
        return str.substring(str.lastIndexOf(File.separator) + 1, str.lastIndexOf(46));
    }

    public static String c(String str) {
        if (q.f(str)) {
            return "";
        }
        return str.substring(str.lastIndexOf(46) + 1);
    }

    public static long d(String str) {
        File file = new File(str);
        if (file == null || !file.exists()) {
            return 0;
        }
        return file.length();
    }

    public static String a(long j) {
        if (j <= 0) {
            return "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        float f = ((float) j) / 1024.0f;
        if (f >= 1024.0f) {
            return decimalFormat.format((double) (f / 1024.0f)) + "M";
        }
        return decimalFormat.format((double) f) + "K";
    }

    public static String b(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String str = "";
        if (j < KSYMediaMeta.AV_CH_SIDE_RIGHT) {
            return decimalFormat.format((double) j) + "B";
        }
        if (j < 1048576) {
            return decimalFormat.format(((double) j) / 1024.0d) + "KB";
        }
        if (j < KSYMediaMeta.AV_CH_STEREO_RIGHT) {
            return decimalFormat.format(((double) j) / 1048576.0d) + "MB";
        }
        return decimalFormat.format(((double) j) / 1.073741824E9d) + "G";
    }

    public static long a(File file) {
        long j = 0;
        if (file != null && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    j += file2.length();
                } else if (file2.isDirectory()) {
                    j = (j + file2.length()) + a(file2);
                }
            }
        }
        return j;
    }

    public long b(File file) {
        File[] listFiles = file.listFiles();
        long length = (long) listFiles.length;
        long j = length;
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                j = (j + b(file2)) - 1;
            }
        }
        return j;
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read();
            if (read != -1) {
                byteArrayOutputStream.write(read);
            } else {
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return toByteArray;
            }
        }
    }

    public static boolean e(String str) {
        if (str.equals("")) {
            return false;
        }
        return new File(Environment.getExternalStorageDirectory().toString() + str).exists();
    }

    public static boolean f(String str) {
        return new File(str).exists();
    }

    public static long a() {
        long j = 0;
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return -1;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / KSYMediaMeta.AV_CH_SIDE_RIGHT;
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }
    }

    public static boolean g(String str) {
        if (str.equals("")) {
            return false;
        }
        new File(Environment.getExternalStorageDirectory().toString() + str).mkdir();
        return true;
    }

    public static boolean b() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    public static boolean c() {
        return System.getenv().containsKey("SECONDARY_STORAGE");
    }

    public static boolean h(String str) {
        SecurityManager securityManager = new SecurityManager();
        if (str.equals("")) {
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory().toString() + str);
        securityManager.checkDelete(file.toString());
        if (!file.isDirectory()) {
            return false;
        }
        String[] list = file.list();
        int i = 0;
        while (i < list.length) {
            try {
                new File(file.toString() + "/" + list[i].toString()).delete();
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        file.delete();
        Log.i("lzf", "DirectoryManager deleteDirectory:"+str);
        return true;
    }

    public static boolean i(String str) {
        SecurityManager securityManager = new SecurityManager();
        if (str.equals("")) {
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory().toString() + str);
        securityManager.checkDelete(file.toString());
        if (!file.isFile()) {
            return false;
        }
        try {
            Log.i("lzf", "DirectoryManager deleteFile:"+str);
            file.delete();
            return true;
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int j(String str) {
        File file = new File(str);
        if (!file.canWrite()) {
            return 1;
        }
        if (file.list() != null && file.list().length > 0) {
            return 2;
        }
        if (file.delete()) {
            return 0;
        }
        return 3;
    }

    public static boolean b(String str, String str2) {
        return new File(str).renameTo(new File(str2));
    }

    public static boolean k(String str) {
        SecurityManager securityManager = new SecurityManager();
        File file = new File(str);
        securityManager.checkDelete(str);
        if (!file.isFile()) {
            return false;
        }
        Log.i("lzf","DirectoryManager deleteFile:"+str);
        file.delete();
        return true;
    }

    public static void l(String str) {
        List<File> n = n(str);
        if (!n.isEmpty()) {
            for (File file : n) {
                if (file.isDirectory()) {
                    l(file.getAbsolutePath());
                } else {
                    file.delete();
                }
            }
        }
    }

    public static String d() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String e() {
        return (String) System.getenv().get("SECONDARY_STORAGE");
    }

    public static List<String> m(String str) {
        List<String> arrayList = new ArrayList();
        SecurityManager securityManager = new SecurityManager();
        File file = new File(str);
        securityManager.checkRead(str);
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory() && !file2.getName().startsWith(".")) {
                    arrayList.add(file2.getAbsolutePath());
                }
            }
        }
        return arrayList;
    }

    public static List<File> n(String str) {
        List<File> arrayList = new ArrayList();
        SecurityManager securityManager = new SecurityManager();
        File file = new File(str);
        securityManager.checkRead(str);
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                arrayList.add(file2);
            } else {
                m(file2.getAbsolutePath());
            }
        }
        return arrayList;
    }

    public static a o(String str) {
        File file = new File(str);
        if (file.exists()) {
            return a.EXITS;
        }
        if (file.mkdir()) {
            return a.SUCCESS;
        }
        return a.ERROR;
    }

    public static String p(String str) {
        return str.substring(str.lastIndexOf(File.separator) + 1, str.length());
    }

    public static String b(Context context, String str) {
        String str2 = context.getCacheDir().getAbsolutePath() + "/" + str + "/";
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2;
    }
}
