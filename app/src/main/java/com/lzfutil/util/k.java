package com.lzfutil.util;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class k {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void a(String[] strArr) {
        System.out.println(a("/init.rc"));
    }

    public static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            stringBuilder.append(a[(bArr[i] & 240) >>> 4]);
            stringBuilder.append(a[bArr[i] & 15]);
        }
        return stringBuilder.toString();
    }

    public static String a(String str) {
        byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
        try {
            InputStream fileInputStream = new FileInputStream(str);
            MessageDigest instance = MessageDigest.getInstance("MD5");
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                instance.update(bArr, 0, read);
            }
            fileInputStream.close();
            String a = a(instance.digest());
            if (TextUtils.isEmpty(a)) {
                return "";
            }
            return a;
        } catch (Exception e) {
            System.out.println("error");
            return "";
        }
    }
}
