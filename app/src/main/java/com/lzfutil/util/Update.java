package com.lzfutil.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.weilian.phonelive.AppConfig;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.File;
import okhttp3.Call;
import org.json.JSONException;
import org.json.JSONObject;

public class Update {
    private Context a;
    private boolean b = false;
    private ProgressDialog c;
    private StringCallback d = new StringCallback() {

        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {
            a(response);
        }

        public void a(String str) {
            String b = ce.a.b(str);
            if (b != null) {
                try {
                    JSONObject jSONObject = new JSONObject(b);
                    if (!String.valueOf(AppContext.c().f().versionName).equals(jSONObject.getString("apk_ver"))) {
                        a(jSONObject.getString("apk_url"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public Update(Context context, boolean z) {
        this.a = context;
        this.b = z;
    }

    public void a() {
        if (this.b) {
            b();
        }
        ce.b.g(this.d);
    }

    private void b() {
        if (this.c == null) {
            this.c = com.lzfutil.util.e.a((Activity) this.a, "\u6b63\u5728\u83b7\u53d6\u65b0\u7248\u672c\u4fe1\u606f...");
        }
        this.c.show();
    }

    private void c() {
        if (this.c != null) {
            this.c.dismiss();
        }
    }

    private void a(final String str) {
        com.lzfutil.util.e.aInit(this.a, "\u53d1\u73b0\u65b0\u7248\u672c\u662f\u5426\u66f4\u65b0", new ch.e() {

            public void a(View view, Dialog dialog) {
                dialog.dismiss();
            }

            public void b(View view, Dialog dialog) {
                View inflate = View.inflate(a, R.layout.dialog_show_download_view, null);
                final NumberProgressBar numberProgressBar = (NumberProgressBar) inflate.findViewById(R.id.np_download);
                dialog.setContentView(inflate);
                final Dialog dialog2 = dialog;
                ce.b.c(str, new FileCallBack( AppConfig.s, "app.apk") {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(a, "\u5b89\u88c5\u5305\u4e0b\u8f7d\u5931\u8d25!", Toast.LENGTH_SHORT).show();
                        dialog2.dismiss();
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        a(response);
                    }

                    public void a(File file) {
                        d();
                        dialog2.dismiss();
                    }

                    public void inProgress(float f, long j) {
                        numberProgressBar.setProgress((int) (100.0f * f));
                    }
                });
            }
        });
    }

    private void d() {
        File file = new File(AppConfig.s + "app.apk");
        if (file.exists()) {
            s.a(this.a, file);
        }
    }

    private void e() {
        com.lzfutil.util.e.b(this.a, "\u5df2\u7ecf\u662f\u65b0\u7248\u672c\u4e86").show();
    }

    private void f() {
        com.lzfutil.util.e.b(this.a, "\u7f51\u7edc\u5f02\u5e38\uff0c\u65e0\u6cd5\u83b7\u53d6\u65b0\u7248\u672c\u4fe1\u606f").show();
    }
}
