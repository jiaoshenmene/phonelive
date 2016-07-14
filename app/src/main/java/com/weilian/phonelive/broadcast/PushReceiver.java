package com.weilian.phonelive.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.lzfutil.util.t;
import cn.jpush.android.api.JPushInterface;
import com.google.gson.Gson;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.ui.VideoPlayerActivity;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class PushReceiver extends BroadcastReceiver {
    private static final String a = "PushReceiver";

    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        t.c("[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + a(extras));
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            t.c("[MyReceiver] \u63a5\u6536Registration Id : " + extras.getString(JPushInterface.EXTRA_REGISTRATION_ID));
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            t.c("[MyReceiver] \u63a5\u6536\u5230\u63a8\u9001\u4e0b\u6765\u7684\u81ea\u5b9a\u4e49\u6d88\u606f: " + extras.getString(JPushInterface.EXTRA_MESSAGE));
            a(context, extras);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            t.c("[MyReceiver] \u63a5\u6536\u5230\u63a8\u9001\u4e0b\u6765\u7684\u901a\u77e5");
            t.c("[MyReceiver] \u63a5\u6536\u5230\u63a8\u9001\u4e0b\u6765\u7684\u901a\u77e5\u7684ID: " + extras.getInt(JPushInterface.EXTRA_NOTIFICATION_ID));
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            t.c(extras.getString(JPushInterface.EXTRA_EXTRA));
            try {
                UserBean userBean = (UserBean) new Gson().fromJson(new JSONObject(extras.getString(JPushInterface.EXTRA_EXTRA)).getString("userinfo"), UserBean.class);
                Intent intent2 = new Intent(context, VideoPlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(VideoPlayerActivity.C, userBean);
                intent2.putExtra(VideoPlayerActivity.C, bundle);
                intent2.putExtras(extras);
                intent2.setFlags(268435456);
                intent2.setFlags(335544320);
                context.startActivity(intent2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            t.a(a, "[MyReceiver] \u7528\u6237\u6536\u5230\u5230RICH PUSH CALLBACK: " + extras.getString(JPushInterface.EXTRA_EXTRA));
        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            t.a(a, "[MyReceiver]" + intent.getAction() + " connected state change to " + intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false));
        } else {
            t.a(a, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    private static String a(Bundle bundle) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : bundle.keySet()) {
            if (str.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                stringBuilder.append("\nkey:" + str + ", value:" + bundle.getInt(str));
            } else if (str.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                stringBuilder.append("\nkey:" + str + ", value:" + bundle.getBoolean(str));
            } else if (!str.equals(JPushInterface.EXTRA_EXTRA)) {
                stringBuilder.append("\nkey:" + str + ", value:" + bundle.getString(str));
            } else if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                Log.i(a, "This message has no Extra data");
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String str2 = ((String) keys.next()).toString();
                        stringBuilder.append("\nkey:" + str + ", value: [" + str2 + " - " + jSONObject.optString(str2) + "]");
                    }
                } catch (JSONException e) {
                    t.a(a, "Get message extra JSON error!");
                }
            }
        }
        return stringBuilder.toString();
    }

    private void a(Context context, Bundle bundle) {
    }
}
