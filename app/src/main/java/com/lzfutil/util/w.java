package com.lzfutil.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ksyun.media.player.stats.StatConstant;
import com.weilian.phonelive.R;
import com.weilian.phonelive.bean.PrivateChatUserBean;
import com.weilian.phonelive.bean.SimpleBackPage;
import com.weilian.phonelive.ui.ActionBarSimpleBackActivity;
import com.weilian.phonelive.ui.AttentionActivity;
import com.weilian.phonelive.ui.DedicateOrderActivity;
import com.weilian.phonelive.ui.EditInfoActivity;
import com.weilian.phonelive.ui.FansActivity;
import com.weilian.phonelive.ui.HomePageActivity;
import com.weilian.phonelive.ui.LiveLoginSelectActivity;
import com.weilian.phonelive.ui.LiveRecordActivity;
import com.weilian.phonelive.ui.MainActivity;
import com.weilian.phonelive.ui.MyDiamondsActivity;
import com.weilian.phonelive.ui.MyInfoDetailActivity;
import com.weilian.phonelive.ui.MyLevelActivity;
import com.weilian.phonelive.ui.PhoneLoginActivity;
import com.weilian.phonelive.ui.ProfitActivity;
import com.weilian.phonelive.ui.SelectAvatarActivity;
import com.weilian.phonelive.ui.SettingActivity;
import com.weilian.phonelive.ui.SimpleBackActivity;
import com.weilian.phonelive.ui.StartLiveActivity;
import com.weilian.phonelive.ui.VideoPlayerActivity;
import com.weilian.phonelive.ui.WebViewActivity;
import com.weilian.phonelive.ui.b;

public class w {
    public static void a(Context context) {
    }

    public static void b(Context context) {
        context.startActivity(new Intent(context, PhoneLoginActivity.class));
    }

    public static void c(Context context) {
        context.startActivity(new Intent(context, LiveLoginSelectActivity.class));
    }

    public static void d(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public static void e(Context context) {
        context.startActivity(new Intent(context, MyInfoDetailActivity.class));
    }

    public static void a(MyInfoDetailActivity myInfoDetailActivity, String str, String str2, String str3, b bVar) {
        Intent intent = new Intent(myInfoDetailActivity, EditInfoActivity.class);
        intent.putExtra(EditInfoActivity.e, str);
        intent.putExtra(EditInfoActivity.g, str3);
        intent.putExtra(EditInfoActivity.f, str2);
        intent.putExtra(EditInfoActivity.d, bVar.a());
        myInfoDetailActivity.startActivity(intent);
        myInfoDetailActivity.overridePendingTransition(R.anim.activity_open_start, 0);
    }

    public static void a(MyInfoDetailActivity myInfoDetailActivity, String str) {
        Intent intent = new Intent(myInfoDetailActivity, SelectAvatarActivity.class);
        intent.putExtra("uhead", str);
        myInfoDetailActivity.startActivity(intent);
        myInfoDetailActivity.overridePendingTransition(R.anim.activity_open_start, 0);
    }

    public static WebViewClient a() {
        return new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return true;
            }
        };
    }

    public static void a(Context context, int i) {
        Intent intent = new Intent(context, MyLevelActivity.class);
        intent.putExtra("USER_ID", String.valueOf(i));
        context.startActivity(intent);
    }

    public static void a(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MyDiamondsActivity.class);
        intent.putExtra("USERINFO", bundle);
        context.startActivity(intent);
    }

    public static void b(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ProfitActivity.class);
        intent.putExtra("USERINFO", bundle);
        context.startActivity(intent);
    }

    public static void f(Context context) {
        context.startActivity(new Intent(context, SettingActivity.class));
    }

    public static void c(Context context, Bundle bundle) {
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra(VideoPlayerActivity.C, bundle);
        context.startActivity(intent);
    }

    public static void g(Context context) {
        context.startActivity(new Intent(context, StartLiveActivity.class));
    }

    public static void b(Context context, int i) {
        Intent intent = new Intent(context, HomePageActivity.class);
        intent.putExtra("uid", i);
        context.startActivity(intent);
    }

    public static void c(Context context, int i) {
        Intent intent = new Intent(context, FansActivity.class);
        intent.putExtra("uid", i);
        context.startActivity(intent);
    }

    public static void d(Context context, int i) {
        Intent intent = new Intent(context, AttentionActivity.class);
        intent.putExtra("uid", i);
        context.startActivity(intent);
    }

    public static void e(Context context, int i) {
        Intent intent = new Intent(context, DedicateOrderActivity.class);
        intent.putExtra("uid", i);
        context.startActivity(intent);
    }

    public static void f(Context context, int i) {
        Intent intent = new Intent(context, LiveRecordActivity.class);
        intent.putExtra("uid", i);
        context.startActivity(intent);
    }

    public static void g(Context context, int i) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra("uid", i);
        intent.putExtra(SimpleBackActivity.d, SimpleBackPage.USER_PRIVATECORE.getValue());
        context.startActivity(intent);
    }

    public static void a(Context context, PrivateChatUserBean privateChatUserBean) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra("user", privateChatUserBean);
        intent.putExtra(SimpleBackActivity.d, SimpleBackPage.USER_PRIVATECORE_MESSAGE.getValue());
        context.startActivity(intent);
    }

    public static void h(Context context) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.d, SimpleBackPage.AREA_SELECT.getValue());
        context.startActivity(intent);
    }

    public static void i(Context context) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.d, SimpleBackPage.INDEX_SECREEN.getValue());
        context.startActivity(intent);
    }

    public static void a(Context context, String str, String str2) {
        Intent intent = new Intent(context, WebViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(StatConstant.PLAY_URL, str);
        bundle.putString("title", str2);
        intent.putExtra("URL_INFO", bundle);
        context.startActivity(intent);
    }

    public static void j(Context context) {
        Intent intent = new Intent(context, ActionBarSimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.d, SimpleBackPage.USER_BLACK_LIST.getValue());
        context.startActivity(intent);
    }

    public static void k(Context context) {
        Intent intent = new Intent(context, ActionBarSimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.d, SimpleBackPage.USER_PUSH_MANAGE.getValue());
        context.startActivity(intent);
    }

    public static void a(Activity activity) {
        Intent intent = new Intent(activity, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.d, SimpleBackPage.LIVE_START_MUSIC.getValue());
        activity.startActivityForResult(intent, 1);
    }
}
