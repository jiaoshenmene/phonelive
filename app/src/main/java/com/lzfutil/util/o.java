package com.lzfutil.util;

import android.app.Activity;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

import com.weilian.phonelive.R;
import com.weilian.phonelive.bean.UserBean;
import org.kymjs.kjframe.http.Request.HttpMethod;
import s.a;
//import u.WXEmojiObject;

public class o {
    public static void share(Activity activity, int i, UserBean userBean) {
        String str = userBean.getUser_nicename() + "\u6b63\u5728\u76f4\u64ad,\u5feb\u6765\u4e00\u8d77\u770b\u5427~";
        switch (i) {
            case R.id.ll_live_shar_qqzone:
                c(activity, false, str, userBean.getAvatar(), null);
                return;
            case R.id.ll_live_shar_qq:
                b(activity, false, str, userBean.getAvatar(), null);
                return;
            case R.id.ll_live_shar_sinna:
                d(activity, false, str, userBean.getAvatar(), null);
                return;
            case R.id.ll_live_shar_pyq:
                e(activity, false, str, userBean.getAvatar(), null);
                return;
            case R.id.ll_live_shar_wechat:
                a(activity, false, str, userBean.getAvatar(), null);
                return;
            default:
                return;
        }
    }

    public static void share(Activity activity, int i, UserBean userBean, PlatformActionListener platformActionListener) {
        String str = userBean.getUser_nicename() + "\u6b63\u5728\u76f4\u64ad,\u5feb\u6765\u4e00\u8d77\u770b\u5427~";
        switch (i) {
            case 0:
                d(activity, false, str, userBean.getAvatar(), platformActionListener);
                return;
            case 1:
                a(activity, false, str, userBean.getAvatar(), platformActionListener);
                return;
            case 2:
                e(activity, false, str, userBean.getAvatar(), platformActionListener);
                return;
            case 3:
                b(activity, false, str, userBean.getAvatar(), platformActionListener);
                return;
            case 4:
                c(activity, false, str, userBean.getAvatar(), platformActionListener);
                return;
            default:
                return;
        }
    }

    public static void a(Activity activity, boolean z, String str, String str2, PlatformActionListener platformActionListener) {
        ShareSDK.initSDK(activity);
        ShareParams shareParams = new Wechat.ShareParams();
        shareParams.setShareType(4);
        shareParams.setTitle(activity.getString(R.string.shartitle));
        shareParams.setTitleUrl("https://www.pgyer.com/phoneLive");
        shareParams.setText(str);
        shareParams.setUrl("https://www.pgyer.com/phoneLive");
        shareParams.setImageUrl(str2);
        shareParams.setSite("https://www.pgyer.com/phoneLive");
        shareParams.setSiteUrl("https://www.pgyer.com/phoneLive");
        Platform platform = ShareSDK.getPlatform(Wechat.NAME);
        platform.setPlatformActionListener(platformActionListener);
        platform.share(shareParams);
    }

    public static void b(Activity activity, boolean z, String str, String str2, PlatformActionListener platformActionListener) {
        ShareSDK.initSDK(activity);
        ShareParams shareParams = new QQ.ShareParams();
        shareParams.setTitle(activity.getString(R.string.shartitle));
        shareParams.setTitleUrl("https://www.pgyer.com/phoneLive");
        shareParams.setText(str);
        shareParams.setImageUrl(str2);
        shareParams.setSite("https://www.pgyer.com/phoneLive");
        shareParams.setSiteUrl("https://www.pgyer.com/phoneLive");
        Platform platform = ShareSDK.getPlatform(QQ.NAME);
        platform.setPlatformActionListener(platformActionListener);
        platform.share(shareParams);
    }

    public static void c(Activity activity, boolean z, String str, String str2, PlatformActionListener platformActionListener) {
        ShareSDK.initSDK(activity);
        ShareParams shareParams = new QZone.ShareParams();
        shareParams.setTitle(activity.getString(R.string.shartitle));
        shareParams.setTitleUrl("https://www.pgyer.com/phoneLive");
        shareParams.setText(str);
        shareParams.setImageUrl(str2);
        shareParams.setSite("https://www.pgyer.com/phoneLive");
        shareParams.setSiteUrl("https://www.pgyer.com/phoneLive");
        Platform platform = ShareSDK.getPlatform(QZone.NAME);
        platform.setPlatformActionListener(platformActionListener);
        platform.share(shareParams);
    }

    public static void d(Activity activity, boolean z, String str, String str2, PlatformActionListener platformActionListener) {
        ShareSDK.initSDK(activity);
        OnekeyShare onekeyShare = new OnekeyShare();
        onekeyShare.setSilent(true);
        onekeyShare.disableSSOWhenAuthorize();
        onekeyShare.setPlatform(SinaWeibo.NAME);
        onekeyShare.setTitle(activity.getString(R.string.shartitle));
        onekeyShare.setTitleUrl("https://www.pgyer.com/phoneLive");
        onekeyShare.setText(str);
        onekeyShare.setImageUrl(str2);
        onekeyShare.setUrl("https://www.pgyer.com/phoneLive");
        onekeyShare.setSite(activity.getString(R.string.app_name));
        onekeyShare.setCallback(platformActionListener);
        onekeyShare.setSiteUrl("https://www.pgyer.com/phoneLive");
        onekeyShare.show(activity);
    }

    public static void e(Activity activity, boolean z, String str, String str2, PlatformActionListener platformActionListener) {
        ShareSDK.initSDK(activity);
        ShareParams shareParams = new WechatMoments.ShareParams();
        shareParams.setShareType(4);
        shareParams.setUrl("https://www.pgyer.com/phoneLive");
        shareParams.setTitle(activity.getString(R.string.shartitle));
        shareParams.setTitleUrl("https://www.pgyer.com/phoneLive");
        shareParams.setText(str);
        shareParams.setImageUrl(str2);
        shareParams.setSite("https://www.pgyer.com/phoneLive");
        shareParams.setSiteUrl("https://www.pgyer.com/phoneLive");
        Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);
        platform.setPlatformActionListener(platformActionListener);
        platform.share(shareParams);
    }
}
