package com.lzfutil.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Handler;

public class n extends Thread {
    private Context a;
    private Handler b;
    private b c = new b();
    private ActivityManager d;
    private MemoryInfo e;
    private m f;
    private boolean g;

    public n(ActivityManager activityManager, Handler handler) {
        this.b = handler;
        this.d = activityManager;
        this.e = new MemoryInfo();
        this.g = true;
        this.f = new m();
    }

    public void run() {
        while (this.g) {
            this.c.a();
            Debug.getMemoryInfo(this.e);
            if (this.b != null) {
                this.f.a = this.c.f();
                this.f.b = this.e.getTotalPss();
                this.f.c = this.e.getTotalPrivateDirty();
                this.b.obtainMessage(2, this.f).sendToTarget();
            }
            try {
                sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void a() {
        this.g = false;
    }
}
