package bu;

import android.net.Uri;
import android.provider.BaseColumns;
import org.kymjs.kjframe.http.Request.HttpMethod;
//import u.WXEmojiObject;

public final class a {

    public static final class aResovler {
        public static Object a(int i, String str) {
            switch (i) {
                case 1:
                    return Integer.valueOf(str);
                case 2:
                    return Long.valueOf(str);
                case 3:
                    return str;
                case 4:
                    return Boolean.valueOf(str);
                case 5:
                    return Float.valueOf(str);
                case 6:
                    return Double.valueOf(str);
                default:
                    try {
                        bt.a.a("MicroMsg.SDK.PluginProvider.Resolver", "unknown type");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
            }
        }
    }

    public static final class b implements BaseColumns {
        public static final Uri a = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref");
    }
}
