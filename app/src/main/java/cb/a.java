package cb;

import android.app.Activity;

import com.ksyun.media.player.stats.StatConstant;
import com.weilian.phonelive.AppContext;
import com.zhy.http.okhttp.callback.StringCallback;

import by.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import okhttp3.Call;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    IWXAPI a;
    final String b = "wxff35f1442deb1bd1";
    private Activity c;

    public a(Activity activity) {
        this.c = activity;
        this.a.registerApp("wxff35f1442deb1bd1");
    }

    public void a(String str, String str2) {
        ce.b.e(AppContext.c().i(), str, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }


            public void a(String str) {
                String a = ce.a.a(str, c);
                if (a != null) {
                    a(a);
                }
            }
        });
    }

    private void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            PayReq aVar = new by.PayReq();
            aVar.c = jSONObject.getString(StatConstant.APP_ID);
            aVar.d = jSONObject.getString("partnerid");
            aVar.e = jSONObject.getString("prepayid");
            aVar.h = "Sign=WXPay";
            aVar.f = jSONObject.getString("noncestr");
            aVar.g = jSONObject.getString("timestamp");
            aVar.i = jSONObject.getString("sign");
            if (this.a.sendReq(aVar)) {
                AppContext.a(this.c, "\u5fae\u4fe1\u652f\u4ed8");
            } else {
                AppContext.a(this.c, "\u8bf7\u67e5\u770b\u60a8\u662f\u5426\u5b89\u88c5\u5fae\u4fe1");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
