package br;

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
        public int d = -1;
        public Bundle e;
    }

    public static boolean a(Context context, aInfo aVar) {
        if (context == null || aVar == null) {
            bt.a.a("MicroMsg.SDK.MMessageAct", "write fail, invalid argument");
            return false;
        } else if (c.a(aVar.a)) {
            bt.a.a("MicroMsg.SDK.MMessageAct", "write fail, invalid targetPkgName, targetPkgName = " + aVar.a);
            return false;
        } else {
            if (c.a(aVar.b)) {
                aVar.b = aVar.a + ".wxapi.WXEntryActivity";
            }
            bt.a.c("MicroMsg.SDK.MMessageAct", "write, targetPkgName = " + aVar.a + ", targetClassName = " + aVar.b);
            Intent intent = new Intent();
            intent.setClassName(aVar.a, aVar.b);
            if (aVar.e != null) {
                intent.putExtras(aVar.e);
            }
            String packageName = context.getPackageName();
            intent.putExtra(b.l, bv.a.h);
            intent.putExtra(b.k, packageName);
            intent.putExtra(b.m, aVar.c);
            intent.putExtra(b.n, bs.b.a(aVar.c, bv.a.h, packageName));
            if (aVar.d == -1) {
                intent.addFlags(268435456).addFlags(134217728);
            } else {
                intent.setFlags(aVar.d);
            }
            try {
                context.startActivity(intent);
                bt.a.c("MicroMsg.SDK.MMessageAct", "write mm message, intent=" + intent);
                return true;
            } catch (Exception e) {
                bt.a.a("MicroMsg.SDK.MMessageAct", "write fail, ex = %hostnameVerifier", e.getMessage());
                return false;
            }
        }
    }
}
