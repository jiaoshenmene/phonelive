package com.lzfutil.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import com.ksyun.media.player.stats.StatConstant;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class h {
    public static final String a = "/mnt/sdcard";
    public static final String b = "/sdcard";
    public static final int c = 0;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 3;
    static Bitmap g = null;

    public static void a(Context context, String str, Bitmap bitmap) throws IOException {
        a(context, str, bitmap, 100);
    }

    public static void a(Context context, String str, Bitmap bitmap, int i) throws IOException {
        if (bitmap != null && str != null && context != null) {
            FileOutputStream openFileOutput = context.openFileOutput(str, c);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
            byteArrayOutputStream.writeTo(openFileOutput);
            openFileOutput.close();
        }
    }

    public static void b(Context context, String str, Bitmap bitmap, int i) throws IOException {
        if (bitmap != null) {
            File file = new File(str.substring(c, str.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }
            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
            bitmap.compress(CompressFormat.JPEG, i, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            if (context != null) {
                b(context, str);
            }
        }
    }

    public static void c(Context context, String str, Bitmap bitmap, int i) throws IOException {
        if (bitmap != null) {
            File file = new File(str.substring(c, str.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }
            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
            bitmap.compress(CompressFormat.PNG, i, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            if (context != null) {
                b(context, str);
            }
        }
    }

    private static void b(Context context, String str) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(str)));
        context.sendBroadcast(intent);
    }

    public static Bitmap a(Context context, String str) {
        FileInputStream openFileInput = null;
        Bitmap bitmap = null;
        try {
            openFileInput = context.openFileInput(str);
            bitmap = BitmapFactory.decodeStream(openFileInput);
            openFileInput.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
               if(openFileInput!=null) openFileInput.close();
            } catch (Exception e5) {
            }
        }
        return bitmap;
    }

    public static Bitmap a(String str) {
        return a(str, null);
    }

    public static Bitmap a(String str, Options options) {
        FileInputStream fileInputStream = null;
        Bitmap bitmap = null;
        try {
            fileInputStream = new FileInputStream(new File(str));
            bitmap = BitmapFactory.decodeStream(fileInputStream, null, options);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if(fileInputStream!=null) fileInputStream.close();
            } catch (Exception e5) {
            }
        }
        return bitmap;
    }

    public static Bitmap a(File file) {
        FileInputStream fileInputStream = null;
        Bitmap bitmap = null;
        try {
            fileInputStream = new FileInputStream(file);
                bitmap = BitmapFactory.decodeStream(fileInputStream);
                fileInputStream.close();
            } catch (Exception e) {
                    e.printStackTrace();
                try {
                    if(fileInputStream!=null) fileInputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        return bitmap;
    }

    public static String a() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SS").format(new Timestamp(System.currentTimeMillis()));
    }

    public static String b() {
        return Environment.getExternalStorageDirectory() + File.separator + "FounderNews" + File.separator;
    }

    public static String a(Uri uri) {
        String decode = Uri.decode(uri.toString());
        String str = "file:///sdcard" + File.separator;
        String str2 = "file:///mnt/sdcard" + File.separator;
        if (decode.startsWith(str)) {
            return Environment.getExternalStorageDirectory().getPath() + File.separator + decode.substring(str.length());
        }
        if (decode.startsWith(str2)) {
            return Environment.getExternalStorageDirectory().getPath() + File.separator + decode.substring(str2.length());
        }
        return null;
    }

    public static String a(Activity activity, Uri uri) {
        String str = "";
        String[] strArr = new String[d];
        strArr[c] = "_data";
        Cursor managedQuery = activity.managedQuery(uri, strArr, null, null, null);
        if (managedQuery != null) {
            int columnIndexOrThrow = managedQuery.getColumnIndexOrThrow("_data");
            if (managedQuery.getCount() > 0 && managedQuery.moveToFirst()) {
                return managedQuery.getString(columnIndexOrThrow);
            }
        }
        return str;
    }

    public static Bitmap a(Activity activity, String str, int i) {
        String[] strArr = new String[e];
        strArr[c] = StatConstant.PLAYER_ID;
        strArr[d] = "_display_name";
        Cursor managedQuery = activity.managedQuery(Media.EXTERNAL_CONTENT_URI, strArr, "_display_name='" + str + "'", null, null);
        if (managedQuery == null || managedQuery.getCount() <= 0 || !managedQuery.moveToFirst()) {
            return null;
        }
        ContentResolver contentResolver = activity.getContentResolver();
        Options options = new Options();
        options.inSampleSize = d;
        return l.a(contentResolver, (long) managedQuery.getInt(c), i, options);
    }

    public static Bitmap a(String str, int i, int i2) {
        return a(a(str), i, i2);
    }

    public static String a(Activity activity) {
        String[] strArr = new String[e];
        strArr[c] = StatConstant.PLAYER_ID;
        strArr[d] = "_data";
        Cursor managedQuery = activity.managedQuery(Media.EXTERNAL_CONTENT_URI, strArr, null, null, "_id desc");
        if (managedQuery == null || managedQuery.getCount() <= 0) {
            return null;
        }
        managedQuery.moveToFirst();
        managedQuery.moveToFirst();
        if (managedQuery.isAfterLast()) {
            return null;
        }
        return managedQuery.getString(d);
    }

    public static int[] a(int[] iArr, int i) {
        if (iArr[c] <= i && iArr[d] <= i) {
            return iArr;
        }
        double max = ((double) i) / ((double) Math.max(iArr[c], iArr[d]));
        int[] iArr2 = new int[e];
        iArr2[c] = (int) (((double) iArr[c]) * max);
        iArr2[d] = (int) (max * ((double) iArr[d]));
        return iArr2;
    }

    public static void a(Context context, String str, String str2, int i, int i2) throws IOException {
        Options options = new Options();
        options.inSampleSize = d;
        Bitmap a = a(str, options);
        if (a != null) {
            int[] iArr = new int[e];
            iArr[c] = a.getWidth();
            iArr[d] = a.getHeight();
            iArr = a(iArr, i);
            b(null, str2, a(a, iArr[c], iArr[d]), i2);
        }
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap, c, c, width, height, matrix, true);
    }

    public static Bitmap a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = ((float) 200) / ((float) width);
        float f2 = ((float) 200) / ((float) height);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(bitmap, c, c, width, height, matrix, true);
    }

    public static Bitmap a(Activity activity, Bitmap bitmap) {
        float f;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        bitmap.getHeight();
        i = bitmap.getWidth();
        if (i >= i2) {
            f = ((float) i2) / ((float) i);
        } else {
            f = 1.0f;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, c, c, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap a(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(c, c, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, float f) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(c, c, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(c, c, c, c);
        paint.setColor(0x424242);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static Bitmap b(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, c, height / e, width, height / e, matrix, false);
        Bitmap createBitmap2 = Bitmap.createBitmap(width, (height / e) + height, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
        float f = 0.0f;
        canvas.drawRect(f, (float) height, (float) width, (float) (height + 4), new Paint());
        canvas.drawBitmap(createBitmap, 0.0f, (float) (height + 4), null);
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0.0f, (float) bitmap.getHeight(), 0.0f, (float) (createBitmap2.getHeight() + 4), 1895825407, ViewCompat.MEASURED_SIZE_MASK, TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        canvas.drawRect(0.0f, (float) height, (float) width, (float) (createBitmap2.getHeight() + 4), paint);
        return createBitmap2;
    }

    public static Drawable c(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    public static String b(File file) {
        InputStream fileInputStream = null;
        String str = null;
        if (file != null && file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                str = a(fileInputStream);
                fileInputStream.close();
            } catch (IOException e2) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                    }
                }
            }
        }
        return str;
    }

    public static String a(InputStream inputStream) {
        String str = null;
        if (inputStream != null) {
            try {
                byte[] bArr = new byte[8];
                inputStream.read(bArr);
                str = a(bArr);
            } catch (IOException e) {
            }
        }
        return str;
    }

    public static String a(byte[] bArr) {
        if (b(bArr)) {
            return "image/jpeg";
        }
        if (c(bArr)) {
            return "image/gif";
        }
        if (d(bArr)) {
            return "image/png";
        }
        if (e(bArr)) {
            return "application/socket-bmp";
        }
        return null;
    }

    private static boolean b(byte[] bArr) {
        boolean z = true;
        if (bArr.length < e) {
            return false;
        }
        if (!(bArr[c] == (byte) -1 && bArr[d] == (byte) -40)) {
            z = false;
        }
        return z;
    }

    private static boolean c(byte[] bArr) {
        boolean z = true;
        if (bArr.length < 6) {
            return false;
        }
        if (!(bArr[c] == (byte) 71 && bArr[d] == (byte) 73 && bArr[e] == (byte) 70 && bArr[f] == (byte) 56 && ((bArr[4] == (byte) 55 || bArr[4] == (byte) 57) && bArr[5] == (byte) 97))) {
            z = false;
        }
        return z;
    }

    private static boolean d(byte[] bArr) {
        boolean z = true;
        if (bArr.length < 8) {
            return false;
        }
        if (!(bArr[c] == (byte) -119 && bArr[d] == (byte) 80 && bArr[e] == (byte) 78 && bArr[f] == (byte) 71 && bArr[4] == (byte) 13 && bArr[5] == (byte) 10 && bArr[6] == (byte) 26 && bArr[7] == (byte) 10)) {
            z = false;
        }
        return z;
    }

    private static boolean e(byte[] bArr) {
        boolean z = true;
        if (bArr.length < e) {
            return false;
        }
        if (!(bArr[c] == (byte) 66 && bArr[d] == (byte) 77)) {
            z = false;
        }
        return z;
    }

    public static String a(Uri uri, Activity activity) {
        String[] strArr = new String[d];
        strArr[c] = "_data";
        Cursor query = activity.getContentResolver().query(uri, strArr, null, null, null);
        if (query == null) {
            return uri.toString();
        }
        query.moveToFirst();
        String string = query.getString(query.getColumnIndexOrThrow("_data"));
        query.close();
        return string;
    }

    public static Bitmap b(final Uri uri, final Activity activity) {
        String[] strArr = new String[e];
        strArr[c] = "_data";
        strArr[d] = "_display_name";
        Cursor query = activity.getContentResolver().query(uri, strArr, null, null, null);
        if (query == null) {
            return null;
        }
        query.moveToFirst();
        if (query.getColumnIndex("_display_name") != -1) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        h.g = Media.getBitmap(activity.getContentResolver(), uri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }).start();
        }
        query.close();
        return g;
    }
}
