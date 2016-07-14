package by;

import android.os.Bundle;

import com.tencent.mm.sdk.modelbase.BaseReq;

public class PayReq extends BaseReq {
    private static final String l = "MicroMsg.PaySdk.PayReq";
    private static final int m = 1024;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public a k;

    public static class a {
        public static final int a = -1;
        public String b;
        public int c = a;

        public void a(Bundle bundle) {
            bundle.putString("_wxapi_payoptions_callback_classname", this.b);
            bundle.putInt("_wxapi_payoptions_callback_flags", this.c);
        }

        public void b(Bundle bundle) {
            this.b = bundle.getString("_wxapi_payoptions_callback_classname");
            this.c = bundle.getInt("_wxapi_payoptions_callback_flags", a);
        }
    }

    public int getType() {
        return 5;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putString("_wxapi_payreq_appid", this.c);
        bundle.putString("_wxapi_payreq_partnerid", this.d);
        bundle.putString("_wxapi_payreq_prepayid", this.e);
        bundle.putString("_wxapi_payreq_noncestr", this.f);
        bundle.putString("_wxapi_payreq_timestamp", this.g);
        bundle.putString("_wxapi_payreq_packagevalue", this.h);
        bundle.putString("_wxapi_payreq_sign", this.i);
        bundle.putString("_wxapi_payreq_extdata", this.j);
        if (this.k != null) {
            this.k.a(bundle);
        }
    }

    public void checkArgs(Bundle bundle) {
        super.fromBundle(bundle);
        this.c = bundle.getString("_wxapi_payreq_appid");
        this.d = bundle.getString("_wxapi_payreq_partnerid");
        this.e = bundle.getString("_wxapi_payreq_prepayid");
        this.f = bundle.getString("_wxapi_payreq_noncestr");
        this.g = bundle.getString("_wxapi_payreq_timestamp");
        this.h = bundle.getString("_wxapi_payreq_packagevalue");
        this.i = bundle.getString("_wxapi_payreq_sign");
        this.j = bundle.getString("_wxapi_payreq_extdata");
        this.k = new a();
        this.k.b(bundle);
    }

    public boolean checkArgs() {
        if (this.c == null || this.c.length() == 0) {
            bt.a.a(l, "checkArgs fail, invalid appId");
            return false;
        } else if (this.d == null || this.d.length() == 0) {
            bt.a.a(l, "checkArgs fail, invalid partnerId");
            return false;
        } else if (this.e == null || this.e.length() == 0) {
            bt.a.a(l, "checkArgs fail, invalid prepayId");
            return false;
        } else if (this.f == null || this.f.length() == 0) {
            bt.a.a(l, "checkArgs fail, invalid nonceStr");
            return false;
        } else if (this.g == null || this.g.length() == 0) {
            bt.a.a(l, "checkArgs fail, invalid timeStamp");
            return false;
        } else if (this.h == null || this.h.length() == 0) {
            bt.a.a(l, "checkArgs fail, invalid packageValue");
            return false;
        } else if (this.i == null || this.i.length() == 0) {
            bt.a.a(l, "checkArgs fail, invalid sign");
            return false;
        } else if (this.j == null || this.j.length() <= m) {
            return true;
        } else {
            bt.a.a(l, "checkArgs fail, extData length too long");
            return false;
        }
    }
}
