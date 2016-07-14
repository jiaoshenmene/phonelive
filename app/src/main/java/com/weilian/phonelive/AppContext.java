package com.weilian.phonelive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Parcelable;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.lzfutil.util.q;
import com.lzfutil.util.t;
import com.lzfutil.util.w;
import cn.jpush.android.api.JPushInterface;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.util.NetUtils;
import com.weilian.phonelive.base.BaseApplication;
import com.weilian.phonelive.bean.UserBean;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import org.kymjs.kjframe.Core;
import org.kymjs.kjframe.http.HttpStatus;

public class AppContext extends BaseApplication {
    public static String a = "\u597d\u50cf\u5728\u9ed1\u6d1e";
    public static String b;
    private static AppContext h;
    public AMapLocationClient mLocationClient = null;
    public com.amap.api.location.AMapLocationClientOption mLocationOption = null;
    public AMapLocationListener e = new AMapLocationListener() {

        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation == null) {
                return;
            }
            if (aMapLocation.getErrorCode() == 0) {
                AppContext.b = aMapLocation.getProvince();
                AppContext.a = aMapLocation.getCity();
                return;
            }
            t.a("AmapError", "location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getCity());
        }
    };
    private int i;
    private boolean j;
    private String k;
    private EMMessageListener l = new EMMessageListener() {

        public void onMessageReceived(List<EMMessage> list) {
            t.c("\u6536\u5230\u6d88\u606f:" + list);
            Intent intent = new Intent("com.weilian.phonelive");
            intent.putExtra("cmd_value", (Parcelable) list.get(0));
            AppContext.this.sendBroadcast(intent, null);
        }

        public void onCmdMessageReceived(List<EMMessage> list) {
            t.c("\u6536\u5230\u900f\u4f20\u6d88\u606f:" + list);
        }

        public void onMessageReadAckReceived(List<EMMessage> list) {
            t.c("\u6536\u5230\u5df2\u8bfb\u56de\u6267:" + list);
        }

        public void onMessageDeliveryAckReceived(List<EMMessage> list) {
            t.c("\u6536\u5230\u5df2\u9001\u8fbe\u56de\u6267:" + list);
        }

        public void onMessageChanged(EMMessage eMMessage, Object obj) {
            t.c("\u6d88\u606f\u72b6\u6001\u53d8\u52a8:" + eMMessage);
        }
    };

    private class ea implements EMConnectionListener {
        final /* synthetic */ AppContext a;

        private ea(AppContext appContext) {
            this.a = appContext;
        }

        public void onConnected() {
        }

        public void onDisconnected(int i) {
            if (i == HttpStatus.SC_MULTI_STATUS) {
                t.c("\u663e\u793a\u5e10\u53f7\u5df2\u7ecf\u88ab\u79fb\u9664");
            } else if (i == HttpStatus.SC_PARTIAL_CONTENT) {
                t.c("\u663e\u793a\u5e10\u53f7\u5728\u5176\u4ed6\u8bbe\u5907\u767b\u9646");
            } else if (NetUtils.hasNetwork(AppContext.c())) {
                t.c("\u8fde\u63a5\u4e0d\u5230\u804a\u5929\u670d\u52a1\u5668");
            } else {
                t.c("\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u68c0\u67e5\u7f51\u7edc\u8bbe\u7f6e");
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        h = this;
        v();
        x();
        w.a((Context) this);
    }

    private void v() {
        EMOptions eMOptions = new EMOptions();
        eMOptions.setAcceptInvitationAlways(false);
        EMClient.getInstance().init(this, eMOptions);
        EMClient.getInstance().setDebugMode(true);
        b();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        w();
    }

    private void w() {

        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(e);

        AMapLocationClientOption mOption = new AMapLocationClientOption();
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
//设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
//设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);

        if(mLocationOption.isOnceLocationLatest()){
            mLocationOption.setOnceLocationLatest(true);
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
//如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会。
        }

//设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
//设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();

    }


    protected void b() {
        EMClient.getInstance().addConnectionListener(new ea(this));
        EMClient.getInstance().chatManager().addMessageListener(this.l);
    }

    private void x() {
        UserBean g = g();
        if (g == null || g.getId() <= 0) {
            h();
            return;
        }
        this.j = true;
        this.i = g.getId();
        this.k = g.getToken();
    }

    public static AppContext c() {
        return h;
    }

    public boolean a(String str) {
        return d().containsKey(str);
    }

    public void a(Properties properties) {
        AppConfig.a((Context) this).a(properties);
    }

    public Properties d() {
        return AppConfig.a((Context) this).a();
    }

    public void a(String str, String str2) {
        AppConfig.a((Context) this).a(str, str2);
    }

    public String b(String str) {
        return AppConfig.a((Context) this).a(str);
    }

    public void a(String... strArr) {
        AppConfig.a((Context) this).a(strArr);
    }

    public String e() {
        String b = b(AppConfig.e);
        if (!q.f(b)) {
            return b;
        }
        b = UUID.randomUUID().toString();
        a(AppConfig.e, b);
        return b;
    }

    public PackageInfo f() {
        PackageInfo packageInfo;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
            packageInfo = null;
        }
        if (packageInfo == null) {
            return new PackageInfo();
        }
        return packageInfo;
    }

    public void a(final UserBean userBean) {
        this.i = userBean.getId();
        this.k = userBean.getToken();
        this.j = true;
        Properties p = new Properties();
        p.put("user.uid", String.valueOf(i));
        p.put("user.token", k);

        p.put("user.city", userBean.getCity());
        p.put("user.avatar", userBean.getAvatar());
        p.put("user.coin", userBean.getCoin());
        p.put("user.name", userBean.getUser_nicename());
        p.put("user.sign", userBean.getSignature());
        p.put("user.sex", String.valueOf(userBean.getSex()));
        p.put("user.login", userBean.getUser_login());
        p.put("user.pwd", userBean.getUser_pass());
        p.put("user.signature",userBean.getSignature());

        a(p);
    }

    public void b(final UserBean userBean) {
        Properties p = new Properties();
        p.put("user.avatar", userBean.getAvatar());
        p.put("user.coin", userBean.getCoin());
        p.put("user.level", String.valueOf(userBean.getLevel()));
        p.put("user.name", userBean.getUser_nicename());
        p.put("user.sign",userBean.getSignature());
        p.put("user.signature",userBean.getSignature());
        a(p);
    }

    public UserBean g() {
        UserBean userBean = new UserBean();
        userBean.setId(q.a(b("user.uid"), 0));
        userBean.setAvatar(b("user.avatar"));
        userBean.setUser_nicename(b("user.name"));
        userBean.setUser_pass(b("user.pwd"));
        userBean.setSignature(b("user.sign"));
        userBean.setToken(b("user.token"));
        userBean.setCity(b("user.city"));
        userBean.setCoin(b("user.coin"));
        String b = b("user.sex");
        if (b == null) {
            b = "0";
        }
        userBean.setSex(Integer.parseInt(b));
        userBean.setCoin(b("user.signature"));
        userBean.setSignature(b("user.signature"));
        userBean.setAvatar(b("user.avatar"));
        b = b("user.level");
        if (b == null) {
            b = "0";
        }
        userBean.setLevel(Integer.parseInt(b));
        return userBean;
    }

    public void h() {
        this.i = 0;
        this.j = false;
        a("user.uid", "user.token", "user.name", "user.pwd", "user.avatar,user.sign,user.city,user.coin,user.sex,user.signature,user.signature,user.avatar,user.level");
    }

    public int i() {
        return this.i;
    }

    public String j() {
        return this.k;
    }

    public boolean k() {
        return this.j;
    }

    public void l() {
        h();
        this.j = false;
        this.i = 0;
        this.k = "";
    }

    public void m() {
        cf.b.b((Context) this);
        cf.b.a((Context) this);
        if (a(8)) {
            cf.b.a(com.lzfutil.util.l.a((Context) this));
        }
        for (Object obj : d().keySet()) {
            if (obj.toString().startsWith("temp")) {
                a(obj.toString());
            }
        }
        Core.getKJBitmap().cleanCache();
    }

    public static boolean a(int i) {
        return VERSION.SDK_INT >= i;
    }

    public static String n() {
        return BaseApplication.t().getString(AppConfig.m + c().i(), "");
    }

    public static void c(String str) {
        BaseApplication.b(AppConfig.m + c().i(), str);
    }

    public static String o() {
        return BaseApplication.t().getString(AppConfig.n + c().i(), "");
    }

    public static void d(String str) {
        BaseApplication.b(AppConfig.n + c().i(), str);
    }

    public static boolean p() {
        return BaseApplication.t().getBoolean(AppConfig.o, true);
    }

    public static void a(boolean z) {
        BaseApplication.a(AppConfig.o, z);
    }

    public static boolean q() {
        return BaseApplication.t().getBoolean(AppConfig.p, false);
    }

    public static void b(boolean z) {
        BaseApplication.a(AppConfig.p, z);
    }

    public static void a(Activity activity, String str) {
        com.devspark.appmsg.AppMsg.makeText(activity, (CharSequence) str, new com.devspark.appmsg.AppMsg.Style(1000, R.drawable.toast_background)).show();
    }
}
