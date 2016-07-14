package ce;

import cn.sharesdk.framework.PlatformDb;
import com.ksyun.media.player.stats.StatConstant;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.bean.GiftBean;
import com.weilian.phonelive.bean.UserBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.File;

public class b {
    public static final String a = "http://www.51rexiu.com/";

    public static void a(String str, String str2, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "user.userlogin").addParams("user_login", str).addParams("code", str2).build().execute(stringCallback);
    }

    public static void a(int i, String str, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getBaseInfo").addParams("uid", String.valueOf(i)).addParams("token", str).build().execute(stringCallback);
    }

    public static void a(int i, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getUserInfo").addParams("uid", String.valueOf(i)).build().execute(stringCallback);
    }

    public static void a(String str, String str2, int i, String str3, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.userUpdate").addParams("field", str).addParams("value", str2).addParams("uid", String.valueOf(i)).addParams("token", str3).build().execute(stringCallback);
    }

    public static void a(StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getSlide").build().execute(stringCallback);
    }

    public static void b(StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.searchArea").build().execute(stringCallback);
    }

    public static void a(int i, int i2, String str, String str2, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.setNodejsInfo").addParams("uid", String.valueOf(i)).addParams("showid", String.valueOf(i2)).addParams("token", str).addParams("city", str2).build().execute(stringCallback);
    }

    public static void b(int i, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getRedislist").addParams("showid", String.valueOf(i)).build().execute(stringCallback);
    }

    public static void a(int i, String str, StringCallback stringCallback, String str2) {
        OkHttpUtils.get().url(a).addParams("service", "User.createRoom").addParams("uid", String.valueOf(i)).addParams("title", str).addParams("city", AppContext.a).addParams("province", AppContext.b).addParams("token", str2).build().execute(stringCallback);
    }

    public static void c(int i, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.stopRoom").addParams("uid", String.valueOf(i)).build().execute(stringCallback);
    }

    public static void c(StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getGifts").build().execute(stringCallback);
    }

    public static void a(UserBean userBean, GiftBean giftBean, int i, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.sendGift").addParams("token", userBean.getToken()).addParams("uid", String.valueOf(userBean.getId())).addParams("touid", String.valueOf(i)).addParams("giftid", String.valueOf(giftBean.getId())).addParams("giftcount", "1").build().execute(stringCallback);
    }

    public static void d(int i, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getPopup").addParams("uid", String.valueOf(i)).build().execute(stringCallback);
    }

    public static void a(int i, int i2, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.isAttention").addParams("uid", String.valueOf(i)).addParams("touid", String.valueOf(i2)).build().execute(stringCallback);
    }

    public static void b(int i, int i2, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.setAttention").addParams("uid", String.valueOf(i)).addParams("showid", String.valueOf(i2)).build().execute(stringCallback);
    }

    public static void c(int i, int i2, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getUserHome").addParams("uid", String.valueOf(i)).addParams("ucuid", String.valueOf(i2)).build().execute(stringCallback);
    }

    public static void d(int i, int i2, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getFans").addParams("uid", String.valueOf(i)).addParams("ucuid", String.valueOf(i2)).build().execute(stringCallback);
    }

    public static void e(int i, int i2, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getAttention").addParams("uid", String.valueOf(i)).addParams("ucuid", String.valueOf(i2)).build().execute(stringCallback);
    }

    public static void e(int i, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getCoinRecord").addParams("uid", String.valueOf(i)).build().execute(stringCallback);
    }

    public static void b(int i, String str, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getWithdraw").addParams("uid", String.valueOf(i)).addParams("token", str).build().execute(stringCallback);
    }

    public static void d(StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getNew").build().execute(stringCallback);
    }

    public static void a(int i, String str, File file, StringCallback stringCallback) {
        OkHttpUtils.post().addFile("file", "wp.png", file).addParams("uid", String.valueOf(i)).addParams("token", str).url("http://www.51rexiu.com/appapi/?service=User.upload").build().execute(stringCallback);
    }

    public static void c(int i, String str, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.userCash").addParams("uid", String.valueOf(i)).addParams("token", str).build().execute(stringCallback);
    }

    public static void f(int i, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getLiveRecord").addParams("uid", String.valueOf(i)).build().execute(stringCallback);
    }

    public static void g(int i, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getAliOrderId").addParams("uid", String.valueOf(i)).addParams("money", "1").build().execute(stringCallback);
    }

    public static void e(StringCallback stringCallback) {
        OkHttpUtils.get().url("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json").build().execute(stringCallback);
    }

    public static void a(String str, StringCallback stringCallback, int i) {
        OkHttpUtils.get().url(a).addParams("service", "User.search").addParams("key", str).addParams("uid", String.valueOf(i)).build().execute(stringCallback);
    }

    public static void f(StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getArea").build().execute(stringCallback);
    }

    public static void d(int i, String str, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.searchArea").addParams("sex", String.valueOf(i)).addParams("key", str).build().execute(stringCallback);
    }

    public static void a(int i, int i2, String str, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getMultiBaseInfo").addParams("uids", str).addParams(StatConstant.LOG_TYPE, String.valueOf(i)).addParams("uid", String.valueOf(i2)).build().execute(stringCallback);
    }

    public static void h(int i, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.attentionLive").addParams("uid", String.valueOf(i)).build().execute(stringCallback);
    }

    public static void f(int i, int i2, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getPmUserInfo").addParams("uid", String.valueOf(i)).addParams("ucuid", String.valueOf(i2)).build().execute(stringCallback);
    }

    public static void e(int i, String str, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getAliOrderId").addParams("uid", String.valueOf(i)).addParams("money", "1").build().execute(stringCallback);
    }

    public static void a(String str, PlatformDb platformDb, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.userLoginByThird").addParams("openid", platformDb.getUserId()).addParams("nicename", platformDb.getUserName()).addParams(StatConstant.LOG_TYPE, str).addParams("avatar", platformDb.getUserIcon()).build().execute(stringCallback);
    }

    public static void g(int i, int i2, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.setAdmin").addParams("showid", String.valueOf(i)).addParams("uid", String.valueOf(i2)).build().execute(stringCallback);
    }

    public static void h(int i, int i2, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getIsAdmin").addParams("showid", String.valueOf(i)).addParams("uid", String.valueOf(i2)).build().execute(stringCallback);
    }

    public static void a(int i, int i2, int i3, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.setShutUp").addParams("showid", String.valueOf(i)).addParams("touid", String.valueOf(i2)).addParams("uid", String.valueOf(i3)).build().execute(stringCallback);
    }

    public static void i(int i, int i2, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.isShutUp").addParams("showid", String.valueOf(i2)).addParams("uid", String.valueOf(i)).build().execute(stringCallback);
    }

    public static void f(int i, String str, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.iftoken").addParams("uid", String.valueOf(i)).addParams("token", str).build().execute(stringCallback);
    }

    public static void j(int i, int i2, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.setBlackList").addParams("uid", String.valueOf(i)).addParams("showid", String.valueOf(i2)).build().execute(stringCallback);
    }

    public static void i(int i, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getBlackList").addParams("uid", String.valueOf(i)).build().execute(stringCallback);
    }

    public static void a(String str) {
        OkHttpUtils.get().url(a).addParams("service", "User.getCode").addParams("mobile", str).build().execute(null);
    }

    public static void j(int i, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getUserPrivateInfo").addParams("uid", String.valueOf(i)).build().execute(stringCallback);
    }

    public static void a(int i) {
        OkHttpUtils.get().url(a).addParams("service", "User.setLight").addParams("uid", String.valueOf(i)).build().execute(null);
    }

    public static void a(String str, StringCallback stringCallback) {
        OkHttpUtils.get().url("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.catalogSug&query=" + str).build().execute(stringCallback);
    }

    public static void b(String str, StringCallback stringCallback) {
        OkHttpUtils.get().url("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.downWeb&songid=" + str + "&bit=24&_t=" + System.currentTimeMillis()).build().execute(stringCallback);
    }

    public static void a(String str, FileCallBack fileCallBack) {
        OkHttpUtils.get().url(str).build().execute(fileCallBack);
    }

    public static void k(int i, StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getLevel").addParams("uid", String.valueOf(i)).build().execute(stringCallback);
    }

    public static void g(StringCallback stringCallback) {
        OkHttpUtils.get().url(a).addParams("service", "User.getVersion").build().execute(stringCallback);
    }

    public static void b(String str, FileCallBack fileCallBack) {
        OkHttpUtils.get().url(str).build().execute(fileCallBack);
    }

    public static void c(String str, FileCallBack fileCallBack) {
        OkHttpUtils.get().url(str).build().execute(fileCallBack);
    }
}
