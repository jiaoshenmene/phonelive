package cd;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.ui.MyDiamondsActivity;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import okhttp3.Call;

public class a {
    public static final String aTag = "alipay-sdk";
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private String b = "http://101.201.112.35/public/appcmf/alipay_app/notify_url.php";
    private String f;
    private MyDiamondsActivity g;
    private String h;
    private Handler iObj = new Handler() {

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    d dVar = new d((String) message.obj);
                    dVar.toString();
                    if (TextUtils.equals(dVar.a(), "9000")) {
                        AppContext.a(g, "\u652f\u4ed8\u6210\u529f");
                        g.a(true, h);
                        return;
                    } else if (TextUtils.equals(dVar.a(), "8000")) {
                        AppContext.a(g, "\u652f\u4ed8\u7ed3\u679c\u786e\u8ba4\u4e2d");
                        return;
                    } else {
                        AppContext.a(g, "\u652f\u4ed8\u5931\u8d25");
                        return;
                    }
                case 2:
                    Toast.makeText(g, "\u68c0\u67e5\u7ed3\u679c\u4e3a\uff1a" + message.obj, Toast.LENGTH_SHORT).show();
                    return;
                case 3:
                    b((String) message.obj);
                    return;
                default:
                    return;
            }
        }
    };

    public a(MyDiamondsActivity myDiamondsActivity) {
        this.g = myDiamondsActivity;
    }

    public void a(String str, String str2) {
        this.h = str2;
        final String str3 = str2 + "\u94bb\u77f3";
        final String str4 = str2 + "\u94bb\u77f3";
        String str5 = "0.01";
        int i = AppContext.c().i();
        final String str6 = this.b;
        ce.b.g(i, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                AppContext.a(g, "\u652f\u4ed8\u5931\u8d25");
            }

            @Override
            public void onResponse(String response, int id) {
                aEmit(response);
            }


            public void aEmit(String str) {
                String a = ce.a.a(str, g);
                if (a != null) {
                    f = a.trim();
                    String a2 = a(f, str3, str4, "0.01", str6);
                    a = a(a2);
                    try {
                        a = URLEncoder.encode(a, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    a = a2 + "&sign=\"" + a + "\"&" + a();
                    Message message = new Message();
                    message.what = e;
                    message.obj = a;
                    iObj.sendMessage(message);
                    return;
                }
                AppContext.a(g, "\u652f\u4ed8\u5931\u8d25");
            }
        });
    }

    private void b(final String str) {
        new Thread(new Runnable() {

            public void run()  {

                String a = str; //new com.alipay.sdk.app.statistic.aFinish() ProcessAndDisplayImageTask().DisplayBitmapTask(LoadedFrom).aFinish(str);
                Message message = new Message();
                message.what = c;
                message.obj = a;
                iObj.sendMessage(message);
            }
        }).start();
    }

    public String a(String str, String str2, String str3, String str4, String str5) {
        return (((((((((("partner=\"2088411851435855\"" + "&seller_id=\"zqm@taianweb.com\"") + "&out_trade_no=\"" + str + "\"") + "&subject=\"" + str2 + "\"") + "&body=\"" + str3 + "\"") + "&total_fee=\"" + str4 + "\"") + "&notify_url=\"" + str5 + "\"") + "&service=\"mobile.securitypay.pay\"") + "&payment_type=\"1\"") + "&_input_charset=\"utf-8\"") + "&it_b_pay=\"30m\"") + "&return_url=\"EVENT_PONG.alipay.com\"";
    }

    public String a(String str) {
        return cd.e.a(str, cd.c.c);
    }

    public String a() {
        return "sign_type=\"RSA\"";
    }
}
