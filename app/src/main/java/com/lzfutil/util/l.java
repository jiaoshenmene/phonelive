package com.lzfutil.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.provider.MediaStore.Images.Thumbnails;
import android.view.View;
import android.view.Window;
import java.io.File;

public class l {
    @TargetApi(5)
    public static void a(Activity activity, int i, int i2) {
        activity.overridePendingTransition(i, i2);
    }

    @TargetApi(7)
    public static Bitmap a(ContentResolver contentResolver, long j, int i, Options options) {
        return Thumbnails.getThumbnail(contentResolver, j, i, options);
    }

    @TargetApi(8)
    public static File a(Context context) {
        return context.getExternalCacheDir();
    }

    @TargetApi(11)
    public static void a(Activity activity) {
        if (VERSION.SDK_INT >= 11) {
            activity.recreate();
        }
    }

    @TargetApi(11)
    public static void a(View view, int i, Paint paint) {
        if (VERSION.SDK_INT >= 11) {
            view.setLayerType(i, paint);
        }
    }

    @TargetApi(14)
    public static void a(Window window, int i) {
        if (VERSION.SDK_INT >= 14) {
            window.setUiOptions(i);
        }
    }
}
