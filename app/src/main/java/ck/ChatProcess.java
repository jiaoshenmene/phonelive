package ck;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatDelegate;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;

import ch.c;

import com.alipay.sdk.cons.MiniDefine;
import com.google.gson.Gson;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.util.EMPrivateConstant.EMMultiUserConstant;
import com.ksyun.media.player.stats.StatConstant;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.bean.ChatBean;
import com.weilian.phonelive.bean.SendGiftBean;
import com.weilian.phonelive.bean.UserBean;
import com.zhy.http.okhttp.callback.StringCallback;

import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.socket.emitter.Emitter;
import okhttp3.Call;
import com.alipay.sdk.cons.GlobalConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatProcess {
    public static int aVal = 0;
    private static final String g = "broadcast";
    private static final int h = 2;
    private static final int iVal = 1;
    private static final int jVal = 0;
    private static final int k = 4;
    private String b;
    private Socket c;
    private Context d;
    private int e;
    private c f;
    private Gson l;
    private Emitter.Listener m = new Emitter.Listener() {

        public void call(Object... objArr) {
            boolean z = false;
            c a = f;
            if (objArr[jVal].toString().equals(StatConstant.PLAY_STATUS_OK)) {
                z = true;
            }
            a.a(z);
            ce.b.b(e, o);
        }
    };
    private Emitter.Listener n = new Emitter.Listener() {

        public void call(Object... objArr) {
            int i = iVal;
            try {
                String obj = objArr[jVal].toString();
                if (obj.equals("stopplay")) {
                    f.a((int) iVal);
                    return;
                }
                JSONObject jSONObject = new JSONObject(obj).getJSONArray(MiniDefine.b).getJSONObject(jVal);
                int i2 = jSONObject.getInt("msgtype");
                int i3 = jSONObject.getInt(MessageEncoder.ATTR_ACTION);
                SpannableStringBuilder spannableStringBuilder;
                int[] iArr;
                Drawable drawable;
                SpannableStringBuilder spannableStringBuilder2;
                SpannableStringBuilder spannableStringBuilder3;
                ChatBean chatBean;
                switch (i2) {
                    case 0:
                        if (i3 == 0) {
                            jSONObject = jSONObject.getJSONObject("io/socket/engineio/client");
                            aVal += iVal;
                            f.a((UserBean) l.fromJson(jSONObject.toString(), UserBean.class), true);
                            return;
                        } else if (i3 == iVal) {
                            jSONObject = jSONObject.getJSONObject("io/socket/engineio/client");
                            aVal--;
                            f.a((UserBean) l.fromJson(jSONObject.toString(), UserBean.class), false);
                            return;
                        } else if (i3 == h) {
                            f.a();
                            return;
                        } else if (i3 == 3) {
                            aVal += 3;
                            f.a(jSONObject.getString("io/socket/engineio/client"));
                            return;
                        } else {
                            return;
                        }
                    case 1:
                        if (i3 == 0) {
                            ChatBean chatBean2 = new ChatBean();
                            chatBean2.setId(jSONObject.getInt("uid"));
                            chatBean2.setSignature(jSONObject.getString("usign"));
                            chatBean2.setLevel(jSONObject.getInt("level"));
                            chatBean2.setUser_nicename(jSONObject.getString("uname"));
                            chatBean2.setCity(jSONObject.getString("city"));
                            chatBean2.setAvatar(jSONObject.getString("uhead"));
                            jSONObject.getJSONObject("io/socket/engineio/client").put("evensend", jSONObject.getString("evensend"));
                            SendGiftBean sendGiftBean = (SendGiftBean) l.fromJson(jSONObject.getJSONObject("io/socket/engineio/client").toString(), SendGiftBean.class);
                            i3 = chatBean2.getLevel();
                            CharSequence charSequence = "_" + chatBean2.getUser_nicename() + ":";
                            SpannableStringBuilder spannableStringBuilder4 = new SpannableStringBuilder("\u6211\u9001\u4e86" + sendGiftBean.getGiftcount() + "\u4e2a" + sendGiftBean.getGiftname());
                            spannableStringBuilder = new SpannableStringBuilder(charSequence);
                            Resources resources = d.getResources();
                            iArr = com.weilian.phonelive.ui.c.a;
                            if (i3 != 0) {
                                i = i3 - 1;
                            }
                            drawable = resources.getDrawable(iArr[i]);
                            drawable.setBounds(jVal, jVal, 60, 30);
                            ImageSpan imageSpan = new ImageSpan(drawable, iVal);
                            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.rgb(MotionEventCompat.ACTION_MASK, 165, jVal)), iVal, spannableStringBuilder.length(), 33);
                            spannableStringBuilder.setSpan(imageSpan, jVal, iVal, 33);
                            spannableStringBuilder4.setSpan(new ForegroundColorSpan(Color.rgb(232, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, TransportMediator.KEYCODE_MEDIA_RECORD)), jVal, spannableStringBuilder4.length(), 33);
                            chatBean2.setSendChatMsg(spannableStringBuilder4);
                            chatBean2.setUserNick(spannableStringBuilder);
                            f.a(sendGiftBean, chatBean2);
                            return;
                        } else if (i3 == 18) {
                            f.a((int) jVal);
                            return;
                        } else if (i3 == 13) {
                            spannableStringBuilder2 = new SpannableStringBuilder(jSONObject.getString("io/socket/engineio/client"));
                            spannableStringBuilder3 = new SpannableStringBuilder("\u7cfb\u7edf\u6d88\u606f:");
                            spannableStringBuilder3.setSpan(new ForegroundColorSpan(Color.rgb(MotionEventCompat.ACTION_MASK, 165, jVal)), jVal, spannableStringBuilder3.length(), 33);
                            chatBean = new ChatBean();
                            chatBean.setSendChatMsg(spannableStringBuilder2);
                            chatBean.setUserNick(spannableStringBuilder3);
                            f.a(jSONObject, chatBean);
                            return;
                        } else {
                            return;
                        }
                    case 2:
                        if (i3 == 0) {
                            CharSequence string = jSONObject.getString("io/socket/engineio/client");
                            chatBean = new ChatBean();
                            chatBean.setId(jSONObject.getInt("uid"));
                            chatBean.setSignature(jSONObject.getString("usign"));
                            chatBean.setLevel(jSONObject.getInt("level"));
                            chatBean.setUser_nicename(jSONObject.getString("uname"));
                            chatBean.setCity(jSONObject.getString("city"));
                            chatBean.setAvatar(jSONObject.getString("uhead"));
                            int level = chatBean.getLevel();
                            CharSequence charSequence2 = "_" + chatBean.getUser_nicename() + ":";
                            spannableStringBuilder = new SpannableStringBuilder(string);
                            spannableStringBuilder3 = new SpannableStringBuilder(charSequence2);
                            Resources resources2 = d.getResources();
                            iArr = com.weilian.phonelive.ui.c.a;
                            if (level != 0) {
                                i = level - 1;
                            }
                            drawable = resources2.getDrawable(iArr[i]);
                            drawable.setBounds(jVal, jVal, 60, 30);
                            ImageSpan imageSpan2 = new ImageSpan(drawable, iVal);
                            spannableStringBuilder3.setSpan(new ForegroundColorSpan(Color.rgb(MotionEventCompat.ACTION_MASK, 165, jVal)), iVal, spannableStringBuilder3.length(), 33);
                            spannableStringBuilder3.setSpan(imageSpan2, jVal, iVal, 33);
                            obj = jSONObject.getString("touid");
                            if (!obj.equals("0") && (Integer.parseInt(obj) == AppContext.c().i() || chatBean.getId() == AppContext.c().i())) {
                                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.rgb(232, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, TransportMediator.KEYCODE_MEDIA_RECORD)), jVal, spannableStringBuilder.length(), 33);
                            }
                            chatBean.setSendChatMsg(spannableStringBuilder);
                            chatBean.setUserNick(spannableStringBuilder3);
                            f.a(chatBean);
                            return;
                        }
                        return;
                    case 4:
                        spannableStringBuilder2 = new SpannableStringBuilder(jSONObject.getString("io/socket/engineio/client"));
                        spannableStringBuilder3 = new SpannableStringBuilder("\u7cfb\u7edf\u6d88\u606f:");
                        spannableStringBuilder3.setSpan(new ForegroundColorSpan(Color.rgb(MotionEventCompat.ACTION_MASK, 165, jVal)), jVal, spannableStringBuilder3.length(), 33);
                        chatBean = new ChatBean();
                        chatBean.setSendChatMsg(spannableStringBuilder2);
                        chatBean.setUserNick(spannableStringBuilder3);
                        f.a(chatBean, jSONObject);
                        return;
                    default:
                        return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    private StringCallback o = new StringCallback() {

        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {
            a(response);
        }


        public void a(String str) {
            String a = ce.a.a(str);
            if (a != null) {
                try {
                    JSONObject jSONObject = new JSONObject(a);
                    JSONArray jSONArray = jSONObject.getJSONArray("list");
                    String string = jSONObject.getString("votestotal");
                    List arrayList = new ArrayList();
                    for (int i = jVal; i < jSONArray.length(); i += iVal) {
                        arrayList.add((UserBean) l.fromJson(jSONArray.getString(i), UserBean.class));
                    }
                    f.a(arrayList, string);
                    aVal = jSONArray.length();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public ChatProcess(String str, c cVar, Context context, int i) throws URISyntaxException {
        this.b = str;
        this.f = cVar;
        this.d = context;
        this.e = i;
        this.l = new Gson();
        this.c = IO.socket(this.b);
    }

    public void a(String str, String str2, int i) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (this.c != null) {
                this.c.connect();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("uid", jSONObject.getString(EMMultiUserConstant.ROOM_ID));
                jSONObject2.put("token", str2);
                jSONObject2.put("roomnum", i);
                Object[] objArr = new Object[iVal];
                objArr[jVal] = jSONObject2;
                this.c.emit("conn", objArr);
                this.c.on("conn", this.m);
                this.c.on("broadcastingListen", this.n);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a(UserBean userBean, int i) {
        try {
            this.c.connect();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("uid", userBean.getId());
            jSONObject.put("token", userBean.getToken());
            jSONObject.put("roomnum", userBean.getId());
            Object[] objArr = new Object[iVal];
            objArr[jVal] = jSONObject;
            this.c.emit("conn", objArr);
            this.c.on("conn", this.m);
            this.c.on("broadcastingListen", this.n);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a() {
        if (this.c != null) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                jSONObject2.put("_method_", "StartEndLive");
                jSONObject2.put(MessageEncoder.ATTR_ACTION, "18");
                jSONObject2.put("io/socket/engineio/client", this.d.getString(R.string.livestart));
                jSONObject2.put("msgtype", GlobalConstants.e);
                jSONObject2.put("timestamp", a(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
                jSONObject2.put("tougood", "");
                jSONObject2.put("touid", "");
                jSONObject2.put("touname", "");
                jSONObject2.put("ugood", "");
                jSONArray.put(jVal, jSONObject2);
                jSONObject.put(MiniDefine.b, jSONArray);
                jSONObject.put("retcode", "000000");
                jSONObject.put("retmsg", StatConstant.PLAY_STATUS_OK);
                Socket socketVar = this.c;
                String str = g;
                Object[] objArr = new Object[iVal];
                objArr[jVal] = jSONObject;
                socketVar.emit(str, objArr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void a(String str, UserBean userBean, String str2) {
        if (this.c != null) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                jSONObject2.put("_method_", "SendGift");
                jSONObject2.put("evensend", str2);
                jSONObject2.put(MessageEncoder.ATTR_ACTION, "0");
                jSONObject2.put("io/socket/engineio/client", str);
                jSONObject2.put("msgtype", GlobalConstants.e);
                jSONObject2.put("level", userBean.getLevel());
                jSONObject2.put("uid", userBean.getId());
                jSONObject2.put("uname", userBean.getUser_nicename());
                jSONObject2.put("uhead", userBean.getAvatar());
                jSONObject2.put("usign", userBean.getSignature());
                jSONObject2.put("city", AppContext.a);
                jSONArray.put(jVal, jSONObject2);
                jSONObject.put(MiniDefine.b, jSONArray);
                jSONObject.put("retcode", "000000");
                jSONObject.put("retmsg", StatConstant.PLAY_STATUS_OK);
                Socket socketVar = this.c;
                String str3 = g;
                Object[] objArr = new Object[iVal];
                objArr[jVal] = jSONObject;
                socketVar.emit(str3, objArr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void a(UserBean userBean, UserBean userBean2) {
        if (this.c != null) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                jSONObject2.put("_method_", "ShutUpUser");
                jSONObject2.put(MessageEncoder.ATTR_ACTION, GlobalConstants.e);
                jSONObject2.put("io/socket/engineio/client", userBean2.getUser_nicename() + "\u88ab\u7981\u8a00");
                jSONObject2.put("msgtype", "4");
                jSONObject2.put("uid", userBean.getId());
                jSONObject2.put("uname", userBean.getUser_nicename());
                jSONObject2.put("touid", userBean2.getId());
                jSONObject2.put("touname", userBean2.getUser_nicename());
                jSONArray.put(jVal, jSONObject2);
                jSONObject.put(MiniDefine.b, jSONArray);
                jSONObject.put("retcode", "000000");
                jSONObject.put("retmsg", StatConstant.PLAY_STATUS_OK);
                Socket socketVar = this.c;
                String str = g;
                Object[] objArr = new Object[iVal];
                objArr[jVal] = jSONObject;
                socketVar.emit(str, objArr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void b(UserBean userBean, UserBean userBean2) {
        if (this.c != null) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                jSONObject2.put("_method_", "SystemNot");
                jSONObject2.put(MessageEncoder.ATTR_ACTION, "13");
                jSONObject2.put("io/socket/engineio/client", userBean2.getUser_nicename() + "\u88ab\u8bbe\u4e3a\u7ba1\u7406\u5458");
                jSONObject2.put("msgtype", GlobalConstants.e);
                jSONObject2.put("uid", userBean.getId());
                jSONObject2.put("uname", userBean.getUser_nicename());
                jSONObject2.put("touid", userBean2.getId());
                jSONObject2.put("touname", userBean2.getUser_nicename());
                jSONArray.put(jVal, jSONObject2);
                jSONObject.put(MiniDefine.b, jSONArray);
                jSONObject.put("retcode", "000000");
                jSONObject.put("retmsg", StatConstant.PLAY_STATUS_OK);
                Socket socketVar = this.c;
                String str = g;
                Object[] objArr = new Object[iVal];
                objArr[jVal] = jSONObject;
                socketVar.emit(str, objArr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void a(String str, UserBean userBean, int i) {
        if (this.c != null) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                jSONObject2.put("_method_", "SendMsg");
                jSONObject2.put(MessageEncoder.ATTR_ACTION, "0");
                jSONObject2.put("io/socket/engineio/client", str);
                jSONObject2.put("msgtype", "2");
                jSONObject2.put("timestamp", a(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
                jSONObject2.put("tougood", "");
                jSONObject2.put("touid", i);
                jSONObject2.put("touname", "");
                jSONObject2.put("ugood", "");
                jSONObject2.put("city", AppContext.a);
                jSONObject2.put("level", userBean.getLevel());
                jSONObject2.put("uid", userBean.getId());
                jSONObject2.put("uname", userBean.getUser_nicename());
                jSONObject2.put("uhead", userBean.getAvatar());
                jSONObject2.put("usign", userBean.getSignature());
                jSONArray.put(jVal, jSONObject2);
                jSONObject.put(MiniDefine.b, jSONArray);
                jSONObject.put("retcode", "000000");
                jSONObject.put("retmsg", StatConstant.PLAY_STATUS_OK);
                Socket socketVar = this.c;
                String str2 = g;
                Object[] objArr = new Object[iVal];
                objArr[jVal] = jSONObject;
                socketVar.emit(str2, objArr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void b() {
        if (this.c != null) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                jSONObject2.put("_method_", "requestFans");
                jSONObject2.put("timestamp", a(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
                jSONArray.put(jVal, jSONObject2);
                jSONObject.put(MiniDefine.b, jSONArray);
                jSONObject.put("retcode", "000000");
                jSONObject.put("retmsg", StatConstant.PLAY_STATUS_OK);
                Socket socketVar = this.c;
                String str = g;
                Object[] objArr = new Object[iVal];
                objArr[jVal] = jSONObject;
                socketVar.emit(str, objArr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void c() {
        if (this.c != null) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                jSONObject2.put("_method_", "light");
                jSONObject2.put(MessageEncoder.ATTR_ACTION, "2");
                jSONObject2.put("msgtype", "0");
                jSONObject2.put("timestamp", a(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
                jSONObject2.put("tougood", "");
                jSONObject2.put("touname", "");
                jSONObject2.put("ugood", "");
                jSONArray.put(jVal, jSONObject2);
                jSONObject.put(MiniDefine.b, jSONArray);
                jSONObject.put("retcode", "000000");
                jSONObject.put("retmsg", StatConstant.PLAY_STATUS_OK);
                Socket socketVar = this.c;
                String str = g;
                Object[] objArr = new Object[iVal];
                objArr[jVal] = jSONObject;
                socketVar.emit(str, objArr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static String a(long j, String str) {
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date(j));
    }

    public void d() {
        if (this.c != null) {
            this.c.disconnect();
            this.c.off("conn");
            this.c.off("broadcastingListen");
            this.c.close();
            this.c = null;
        }
    }
}
