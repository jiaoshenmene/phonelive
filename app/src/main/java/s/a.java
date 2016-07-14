package s;

import android.text.TextUtils;
import android.util.Log;

public final class a extends Exception {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private static final long d = 8374198311711795611L;
    private int e;

    public a() {
        this(null, null);
    }

    public a(String str) {
        this(str, null);
    }

    public a(String str, Throwable th) {
        super(str, th);
        this.e = a;
        a(str, th);
    }

    public a(Throwable th) {
        this(null, th);
    }

    public static void a(String str, Throwable th) {
        if (!TextUtils.isEmpty(str)) {
            Log.w("NetError", "NetError--" + str);
        }
        if (th != null) {
            try {
                Log.w("NetError", "NetError--" + th.getMessage());
            } catch (Exception e) {
            }
        }
    }

    public final int a() {
        return this.e;
    }

    public final void a(int i) {
        this.e = i;
    }
}
