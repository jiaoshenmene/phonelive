package com.lzfutil.util;

import android.support.v4.view.MotionEventCompat;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class c {
    public static final String a = "DES/CBC/PKCS5Padding";

    public static String a(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        try {
            Key generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher instance = Cipher.getInstance(a);
            instance.init(1, generateSecret, new IvParameterSpec("12345678".getBytes()));
            return a(instance.doFinal(str2.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static String b(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        try {
            Key generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher instance = Cipher.getInstance(a);
            instance.init(2, generateSecret, new IvParameterSpec("12345678".getBytes()));
            return new String(instance.doFinal(b(str2.getBytes())));
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (bArr != null && i < bArr.length) {
            String toHexString = Integer.toHexString(bArr[i] & MotionEventCompat.ACTION_MASK);
            if (toHexString.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(toHexString);
            i++;
        }
        return stringBuilder.toString().toUpperCase();
    }

    private static byte[] b(byte[] bArr) {
        if (bArr.length % 2 != 0) {
            throw new IllegalArgumentException();
        }
        byte[] bArr2 = new byte[(bArr.length / 2)];
        for (int i = 0; i < bArr.length; i += 2) {
            bArr2[i / 2] = (byte) Integer.parseInt(new String(bArr, i, 2), 16);
        }
        return bArr2;
    }
}
