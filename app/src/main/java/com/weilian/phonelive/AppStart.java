package com.weilian.phonelive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.lzfutil.util.s;
import cn.jpush.android.api.JPushInterface;
import com.hyphenate.chat.EMClient;
import com.weilian.phonelive.ui.LiveLoginSelectActivity;
import com.weilian.phonelive.ui.MainActivity;
import java.io.File;
import org.kymjs.kjframe.http.KJAsyncTask;
import org.kymjs.kjframe.utils.FileUtils;
import org.kymjs.kjframe.utils.PreferenceHelper;

public class AppStart extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Activity b = ActivityManager.find(MainActivity.class);
        if (!(b == null || b.isFinishing())) {
            finish();
        }
        View inflate = View.inflate(this, R.layout.app_start, null);
        setContentView(inflate);
        a((Context) this);
        Animation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
        alphaAnimation.setDuration(800);
        inflate.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new AnimationListener() {

            public void onAnimationEnd(Animation animation) {
                b();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
    }

    protected void onResume() {
        super.onResume();
        int readInt = PreferenceHelper.readInt(this, "first_install", "first_install", -1);
        int q = s.q();
        if (readInt < q) {
            PreferenceHelper.write((Context) this, "first_install", "first_install", q);
            a();
        }
        JPushInterface.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    private void a() {
        final File saveFolder = FileUtils.getSaveFolder("phoneLive/imagecache");
        KJAsyncTask.execute(new Runnable() {

            public void run() {
                for (File delete : saveFolder.listFiles()) {
                    delete.delete();
                }
            }
        });
    }

    private void b() {
        if (AppContext.c().k()) {
            EMClient.getInstance().groupManager().loadAllGroups();
            EMClient.getInstance().chatManager().loadAllConversations();
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
        startActivity(new Intent(this, LiveLoginSelectActivity.class));
        finish();
    }

    int a(Context context) {
        try {
            Log.i("myhashcode", "hashCode : " + context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES).signatures[0].hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
