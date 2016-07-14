package com.weilian.phonelive.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.lzfutil.util.q;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseActivity;
import com.weilian.phonelive.base.BaseApplication;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.widget.LoadUrlImageView;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import okhttp3.Call;

public class SelectAvatarActivity extends BaseActivity {
    private static final int d = 200;
    private static final String e = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/PhoneLive/Portrait/");
    private Uri f;
    private Uri g;
    private File h;
    private Bitmap i;
    private String j;
    private String k;
    @BindView(R.id.av_edit_head)
    LoadUrlImageView mUHead;

    public void initView() {
    }

    public void initData() {
        this.mUHead.setImageLoadUrl(getIntent().getStringExtra("uhead"));
    }

    @OnClick({R.id.btn_avator_from_album, R.id.btn_avator_photograph})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_avator_from_album:
                h();
                return;
            case R.id.btn_avator_photograph:
                i();
                return;
            default:
                return;
        }
    }

    private void h() {
        Intent intent = new Intent();
        if (VERSION.SDK_INT < 19) {
            intent.setAction("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "\u9009\u62e9\u56fe\u7247"), 2);
            return;
        }
        intent = new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "\u9009\u62e9\u56fe\u7247"), 2);
    }

    private Uri a(Uri uri) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(e);
            if (!file.exists()) {
                file.mkdirs();
            }
            String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String a = com.lzfutil.util.h.a(uri);
            if (q.f(a)) {
                a = com.lzfutil.util.h.a((Activity) this, uri);
            }
            a = com.lzfutil.util.g.c(a);
            if (q.f(a)) {
                a = "jpg";
            }
            this.j = e + ("osc_crop_" + format + "." + a);
            this.h = new File(this.j);
            this.g = Uri.fromFile(this.h);
            return this.g;
        }
        AppContext.a((Activity) this, "\u65e0\u6cd5\u4fdd\u5b58\u4e0a\u4f20\u7684\u5934\u50cf\uff0c\u8bf7\u68c0\u67e5SD\u5361\u662f\u5426\u6302\u8f7d");
        return null;
    }

    private void i() {
        String str = "";
        if (Environment.getExternalStorageState().equals("mounted")) {
            str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/oschina/Camera/";
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        if (q.f(str)) {
            BaseApplication.g("\u65e0\u6cd5\u4fdd\u5b58\u7167\u7247\uff0c\u8bf7\u68c0\u67e5SD\u5361\u662f\u5426\u6302\u8f7d");
            return;
        }
        String str2 = "osc_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";
        Uri fromFile = Uri.fromFile(new File(str, str2));
        this.f = fromFile;
        this.k = str + str2;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", fromFile);
        startActivityForResult(intent, 1);
    }

    private void b(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("output", a(uri));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", d);
        intent.putExtra("outputY", d);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 0:
                    j();
                    return;
                case 1:
                    b(this.f);
                    return;
                case 2:
                    b(intent.getData());
                    return;
                default:
                    return;
            }
        }
    }

    private void j() {
        c("\u6b63\u5728\u4e0a\u4f20\u5934\u50cf...");
        if (q.f(this.j) || !this.h.exists()) {
            AppContext.a((Activity) this, "\u56fe\u50cf\u4e0d\u5b58\u5728\uff0c\u4e0a\u4f20\u5931\u8d25");
        } else {
            this.i = com.lzfutil.util.h.a(this.j, (int) d, (int) d);
        }
        if (this.i != null) {
            try {
                ce.b.a(AppContext.c().i(), AppContext.c().j(), this.h, new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        AppContext.a(SelectAvatarActivity.this, "\u4e0a\u4f20\u5934\u50cf\u5931\u8d25");
                        g();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        a(response);
                    }

                    public void a(String str) {
                        String a = ce.a.a(str, SelectAvatarActivity.this);
                        if (a != null) {
                            AppContext.a(SelectAvatarActivity.this, "\u5934\u50cf\u4fee\u6539\u6210\u529f");
                            UserBean g = AppContext.c().g();
                            g.setAvatar(a);
                            mUHead.setImageLoadUrl(a);
                            AppContext.c().b(g);
                        }
                        g();
                    }
                });
            } catch (Exception e) {
                BaseApplication.f("\u56fe\u50cf\u4e0d\u5b58\u5728\uff0c\u4e0a\u4f20\u5931\u8d25");
            }
        }
    }

    protected int c() {
        return R.layout.activity_edit_head;
    }

    protected boolean b() {
        return false;
    }
}
