package by;

import android.os.Bundle;

public class PayResp extends com.tencent.mm.sdk.modelbase.BaseResp {
    public String e;
    public String f;
    public String g;

    public PayResp(Bundle bundle) {
        fromBundle(bundle);
    }

    public int getType() {
        return 5;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putString("_wxapi_payresp_prepayid", this.e);
        bundle.putString("_wxapi_payresp_returnkey", this.f);
        bundle.putString("_wxapi_payresp_extdata", this.g);
    }

    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.e = bundle.getString("_wxapi_payresp_prepayid");
        this.f = bundle.getString("_wxapi_payresp_returnkey");
        this.g = bundle.getString("_wxapi_payresp_extdata");
    }

    public boolean checkArgs() {
        return true;
    }
}
