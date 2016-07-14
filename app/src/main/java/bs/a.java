package bs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import bt.c;
import bv.b;

public final class a {

    public static class aInfo {
        public String a;
        public String b;
        public String c;
        public Bundle d;
    }

    public static boolean a(Context context, aInfo aVar) {
        if (context == null || aVar == null) {
            bt.a.a("MicroMsg.SDK.MMessage", "write fail, invalid argument");
            return false;
        } else if (c.a(aVar.b)) {
            bt.a.a("MicroMsg.SDK.MMessage", "write fail, action is null");
            return false;
        } else {
            String str = null;
            if (!c.a(aVar.a)) {
                str = aVar.a + ".permission.MM_MESSAGE";
            }
            Intent intent = new Intent(aVar.b);
            if (aVar.d != null) {
                intent.putExtras(aVar.d);
            }
            String packageName = context.getPackageName();
            intent.putExtra(b.l, bv.a.h);
            intent.putExtra(b.k, packageName);
            intent.putExtra(b.m, aVar.c);
            intent.putExtra(b.n, bs.b.a(aVar.c, bv.a.h, packageName));
            context.sendBroadcast(intent, str);
            bt.a.c("MicroMsg.SDK.MMessage", "write mm message, intent=" + intent + ", perm=" + str);
            return true;
        }
    }
}
