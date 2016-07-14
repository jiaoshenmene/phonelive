package ce;

import android.app.Activity;
import android.widget.Toast;
import com.lzfutil.util.w;
import com.weilian.phonelive.ActivityManager;
import com.weilian.phonelive.AppContext;

import org.json.JSONException;
import org.json.JSONObject;

import com.alipay.sdk.cons.MiniDefine;

public class a {
    public static final int a = 200;
    public static final String retCode = "700";

    public static String a(String str, Activity activity) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (Integer.parseInt(jSONObject.getString("ret")) == a) {
                jSONObject = jSONObject.getJSONObject("data");
                String string = jSONObject.getString("code");
                if (string.equals(retCode)) {
                    ActivityManager.getAppManager().finishAllActivity();
                    w.c(activity);
                    return null;
                } else if (string.equals("0")) {
                    return jSONObject.get("info").toString();
                } else {
                    AppContext.a(activity, jSONObject.getString(MiniDefine.b));
                    return null;
                }
            }
            AppContext.a(activity, jSONObject.getString(MiniDefine.b));
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(String str) {
        String str2 = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (Integer.parseInt(jSONObject.getString("ret")) == a) {
                jSONObject = jSONObject.getJSONObject("data");
                String string = jSONObject.getString("code");
                if (string.equals(retCode)) {
                    ActivityManager.getAppManager().finishAllActivity();
                    w.c(AppContext.c());
                } else if (string.equals("0")) {
                    str2 = jSONObject.get("info").toString();
                } else {
                    Toast.makeText(AppContext.c(), jSONObject.get("info").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static String b(String str) {
        String str2 = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (Integer.parseInt(jSONObject.getString("ret")) == a) {
                jSONObject = jSONObject.getJSONObject("data");
                String string = jSONObject.getString("code");
                if (string.equals(retCode)) {
                    ActivityManager.getAppManager().finishAllActivity();
                    w.c(AppContext.c());
                } else if (string.equals("0")) {
                    str2 = jSONObject.get("info").toString();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str2;
    }
}
