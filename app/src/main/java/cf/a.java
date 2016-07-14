package cf;

import android.content.Context;
import com.lzfutil.util.s;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class a {
    private static long a = 300000;
    private static long b = 3600000;

    public static boolean a(Context context, Serializable serializable, String str) {
        FileOutputStream openFileOutput = null;
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        boolean z = false;
        try {
            openFileOutput = context.openFileOutput(str, 0);
            objectOutputStream = new ObjectOutputStream(openFileOutput);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.flush();
            z = true;
            objectOutputStream.close();
            openFileOutput.close();
        } catch (Exception e3) {
            try {
                if (objectOutputStream != null) objectOutputStream.close();
                if (openFileOutput != null) openFileOutput.close();
            }catch (Exception e) {

            }
        }
        return z;
    }

    public static Serializable a(Context context, String str) {
        FileInputStream openFileInput = null;
        ObjectInputStream objectInputStream = null;

        Serializable serializable;
        if (!b(context, str)) {
            return null;
        }
        try {
            openFileInput = context.openFileInput(str);
            objectInputStream = new ObjectInputStream(openFileInput);
            serializable = (Serializable) objectInputStream.readObject();
        } catch (Exception e) {
            if (e instanceof InvalidClassException) {
                context.getFileStreamPath(str).delete();
            }
            try {
                if(objectInputStream!=null) objectInputStream.close();
                if(openFileInput!=null) openFileInput.close();
            }catch (Exception e2) {

            }
            return null;
        }
        return serializable;
    }

    public static boolean b(Context context, String str) {
        if (context != null && context.getFileStreamPath(str).exists()) {
            return true;
        }
        return false;
    }

    public static boolean c(Context context, String str) {
        boolean z = true;
        File fileStreamPath = context.getFileStreamPath(str);
        if (!fileStreamPath.exists()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() - fileStreamPath.lastModified();
        if (s.x() == 1) {
            if (currentTimeMillis <= a) {
                z = false;
            }
        } else if (currentTimeMillis <= b) {
            z = false;
        }
        return z;
    }
}
