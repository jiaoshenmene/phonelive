package bt;

import android.util.Log;
import bt.a.aInterface;

final class b implements bt.a.aInterface {
    b() {
    }

    public final int a() {
        return bt.a.aVal;
    }

    public final void a(String str, String str2) {
        if ( a.aVal <= 2) {
            Log.i(str, str2);
        }
    }

    public final void b(String str, String str2) {
        if (a.aVal <= 1) {
            Log.d(str, str2);
        }
    }

    public final void c(String str, String str2) {
        if (a.aVal <= 4) {
            Log.e(str, str2);
        }
    }
}
