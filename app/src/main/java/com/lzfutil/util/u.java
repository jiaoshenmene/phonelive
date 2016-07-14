package com.lzfutil.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

public class u {
    private static a a;

    public static class a {
        private int a;
        private int b;
        private long c;
        private ThreadPoolExecutor d;

        private a(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = (long) i3;
        }

        public void a(Runnable runnable) {
            if (this.d == null) {
                this.d = new ThreadPoolExecutor(this.a, this.b, this.c, TimeUnit.SECONDS, new LinkedBlockingQueue(), Executors.defaultThreadFactory(), new AbortPolicy());
            }
            this.d.execute(runnable);
        }
    }

    public static a a() {
        if (a == null) {
            synchronized (u.class) {
                if (a == null) {
                    int availableProcessors = (Runtime.getRuntime().availableProcessors() * 2) + 1;
                    a = new a(availableProcessors, availableProcessors, 0);
                }
            }
        }
        return a;
    }
}
