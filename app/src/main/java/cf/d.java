package cf;

import android.content.Context;
import android.os.Environment;
import android.support.v4.view.MotionEventCompat;
import cf.dlc.Editor;
import com.lzfutil.util.s;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.kymjs.kjframe.utils.FileUtils;

public class d {
    public static final String a1 = "object";
    private static int b = s.q();
    private static int c = 1;
    private static int d = 10485760;

    public static void a(Context context, Serializable serializable, String str) {
        Object obj;
        IOException iOException;
        Throwable th;
        Closeable closeable = null;
        try {
            ObjectOutputStream objectOutputStream;
            Editor a = a(context, a1, str);
            if (a != null) {
                objectOutputStream = new ObjectOutputStream(a.c(0));
                objectOutputStream.writeObject(serializable);
                objectOutputStream.flush();
                a.a();
                FileUtils.closeIO(closeable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.closeIO(closeable);
        }
    }

    public static Serializable a(Context context, String str) {
        ObjectInputStream objectInputStream;
        Closeable closeable = null;
        Serializable serializable = null;
        try {
            objectInputStream = new ObjectInputStream(a(context, a1, str).a(0));
            serializable = (Serializable) objectInputStream.readObject();
            FileUtils.closeIO(objectInputStream);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.closeIO(closeable);
        }
        return serializable;
    }

    public static Editor a(Context context, String str, String str2) throws IOException {
        return dlc.a(b(context, str), b, c, (long) d).b(a(str2));
    }

    public static File b(Context context, String str) {
        String path;
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            path = context.getExternalCacheDir().getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        return new File(path + File.separator + str);
    }

    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return a(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(str.hashCode());
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & MotionEventCompat.ACTION_MASK);
            if (toHexString.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }
}
