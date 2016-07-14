package cd;

public final class b {
    private static final int a = 128;
    private static int bObj = 64;
    private static final int c = 24;
    private static final int d = 8;
    private static final int e = 16;
    private static final int f = 4;
    private static final int g = -128;
    private static char h = '=';
    private static byte[] iobj = new byte[a];
    private static char[] jobj = new char[bObj];

    static {
        int i;
        int i2 = 0;
        for (i = 0; i < a; i++) {
            iobj[i] = (byte) -1;
        }
        for (i = 90; i >= 65; i--) {
            iobj[i] = (byte) (i - 65);
        }
        for (i = 122; i >= 97; i--) {
            iobj[i] = (byte) ((i - 97) + 26);
        }
        for (i = 57; i >= 48; i--) {
            iobj[i] = (byte) ((i - 48) + 52);
        }
        iobj[43] = (byte) 62;
        iobj[47] = (byte) 63;
        for (i = 0; i <= 25; i++) {
            jobj[i] = (char) (i + 65);
        }
        int i3 = 26;
        i = 0;
        while (i3 <= 51) {
            jobj[i3] = (char) (i + 97);
            i3++;
            i++;
        }
        i = 52;
        while (i <= 61) {
            jobj[i] = (char) (i2 + 48);
            i++;
            i2++;
        }
        jobj[62] = '+';
        jobj[63] = '/';
    }

    private static boolean a(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    private static boolean b(char c) {
        return c == h;
    }

    private static boolean c(char c) {
        return c < '\u0080' && iobj[c] != (byte) -1;
    }

    public static String a(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length * d;
        if (length == 0) {
            return "";
        }
        Object obj = new byte[(i * 3)];
        int i2 = length % c;
        int i3 = length / c;
        char[] cArr = new char[((i2 != 0 ? i3 + 1 : i3) * f)];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i3) {
            length = i + 1;
            byte b = bArr[i];
            int i6 = length + 1;
            byte b2 = bArr[length];
            int i7 = i6 + 1;
            byte b3 = bArr[i6];
            byte b4 = (byte) (b2 & 15);
            byte b5 = (byte) (b & 3);
            if ((b & g) == 0) {
                i6 = (byte) (b >> 2);
            } else {
                byte b6 = (byte) ((b >> 2) ^ 192);
            }
            if ((b2 & g) == 0) {
                i = (byte) (b2 >> f);
            } else {
                b = (byte) ((b2 >> f) ^ 240);
            }
            length = (b3 & g) == 0 ? (byte) (b3 >> 6) : (byte) ((b3 >> 6) ^ 252);
            int i8 = i5 + 1;
            cArr[i5] = jobj[i6];
            i6 = i8 + 1;
            cArr[i8] = jobj[i | (b5 << f)];
            i5 = i6 + 1;
            cArr[i6] = jobj[length | (b4 << 2)];
            i = i5 + 1;
            cArr[i5] = jobj[b3 & 63];
            i4++;
            i5 = i;
            i = i7;
        }
        byte b7;
        byte b8;
        byte b6;
        if (i2 == d) {
            b7 = bArr[i];
            b8 = (byte) (b7 & 3);
            i = i5 + 1;
            cArr[i5] = jobj[(b7 & g) == 0 ? (byte) (b7 >> 2) : (byte) ((b7 >> 2) ^ 192)];
            length = i + 1;
            cArr[i] = jobj[b8 << f];
            i3 = length + 1;
            cArr[length] = h;
            length = i3 + 1;
            cArr[i3] = h;
        } else if (i2 == e) {
            b7 = bArr[i];
            bObj = bArr[i + 1];
            b6 = (byte) (bObj & 15);
            byte b9 = (byte) (b7 & 3);
            if ((b7 & g) == 0) {
                i3 = (byte) (b7 >> 2);
            } else {
                b8 = (byte) ((b7 >> 2) ^ 192);
            }
            length = (bObj & g) == 0 ? (byte) (bObj >> f) : (byte) ((bObj >> f) ^ 240);
            i = i5 + 1;
            cArr[i5] = jobj[i3];
            i3 = i + 1;
            cArr[i] = jobj[length | (b9 << f)];
            length = i3 + 1;
            cArr[i3] = jobj[b6 << 2];
            i3 = length + 1;
            cArr[length] = h;
        }
        return new String(cArr);
    }

    public static byte[] a(String str) {
        if (str == null) {
            return null;
        }

        byte b6;
        char[] toCharArray = str.toCharArray();
        int a = a(toCharArray);
        if (a % f != 0) {
            return null;
        }
        int i = a / f;
        if (i == 0) {
            return new byte[0];
        }
        byte[] obj = new byte[(i * 3)];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i - 1) {
            i5 = i2 + 1;
            char c = toCharArray[i2];
            if (!c(c)) {
                return null;
            }
            i2 = i5 + 1;
            char c2 = toCharArray[i5];
            if (!c(c2)) {
                return null;
            }
            int i6 = i2 + 1;
            char c3 = toCharArray[i2];
            if (!c(c3)) {
                return null;
            }
            i2 = i6 + 1;
            char c4 = toCharArray[i6];
            if (!c(c4)) {
                return null;
            }
            byte b = iobj[c];
            byte b2 = iobj[c2];
            byte b3 = iobj[c3];
            byte b4 = iobj[c4];
            int i7 = i3 + 1;
            obj[i3] = (byte) ((b << 2) | (b2 >> f));
            int i8 = i7 + 1;
            obj[i7] = (byte) (((b2 & 15) << f) | ((b3 >> 2) & 15));
            i3 = i8 + 1;
            obj[i8] = (byte) ((b3 << 6) | b4);
            i4++;
        }
        i = i2 + 1;
        char c5 = toCharArray[i2];
        if (!c(c5)) {
            return null;
        }
        i5 = i + 1;
        char c6 = toCharArray[i];
        if (!c(c6)) {
            return null;
        }
        byte b5 = iobj[c5];
        b6 = iobj[c6];
        byte [] r0;
        int i8 = i5 + 1;
        char c2 = toCharArray[i5];
        int i6 = i8 + 1;
        char c7 = toCharArray[i8];
        if (c(c2) && c(c7)) {
            byte b7 = iobj[c2];
            byte b8 = iobj[c7];
            i4 = i3 + 1;
            obj[i3] = (byte) ((b5 << 2) | (b6 >> f));
            i2 = i4 + 1;
            obj[i4] = (byte) (((b6 & 15) << f) | ((b7 >> 2) & 15));
            i3 = i2 + 1;
            obj[i2] = (byte) ((b7 << 6) | b8);
            return obj;
        } else if (b(c2) && b(c7)) {
            if ((b6 & 15) != 0) {
                return null;
            }
            r0 = new byte[((i4 * 3) + 1)];
            System.arraycopy(obj, 0, r0, 0, i4 * 3);
            r0[i3] = (byte) ((b5 << 2) | (b6 >> f));
            return r0;
        } else if (b(c2) || !b(c7)) {
            return null;
        } else {
            byte b9 = iobj[c2];
            if ((b9 & 3) != 0) {
                return null;
            }
            r0 = new byte[((i4 * 3) + 2)];
            System.arraycopy(obj, 0, r0, 0, i4 * 3);
            a = i3 + 1;
            r0[i3] = (byte) ((b5 << 2) | (b6 >> f));
            r0[a] = (byte) (((b6 & 15) << f) | ((b9 >> 2) & 15));
            return r0;
        }
    }

    private static int a(char[] cArr) {
        int i = 0;
        if (cArr != null) {
            int length = cArr.length;
            int i2 = 0;
            while (i2 < length) {
                int i3;
                if (a(cArr[i2])) {
                    i3 = i;
                } else {
                    i3 = i + 1;
                    cArr[i] = cArr[i2];
                }
                i2++;
                i = i3;
            }
        }
        return i;
    }
}
