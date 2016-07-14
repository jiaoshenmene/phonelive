package com.lzfutil.util;

import com.hyphenate.util.HanziToPinyin.Token;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b {
    static final String a = "((?ProcessAndDisplayImageTask)(use?socket\\hostnameVerifier+[0-9]{1,2}(\\.[0-9]{1,2})?\\%))|([0-9]{1,2}\\.[0-9]{1,2}\\%\\hostnameVerifier+(?ProcessAndDisplayImageTask)(use?socket))";
    static final String b = "((?ProcessAndDisplayImageTask)(sys(tem)?\\hostnameVerifier+[0-9]{1,2}(\\.[0-9]{1,2})?\\%))|([0-9]{1,2}\\.[0-9]{1,2}\\%\\hostnameVerifier+(?ProcessAndDisplayImageTask)(sys(tem)?))";
    private String c = null;
    private float d = 0.0f;
    private float e = 0.0f;
    private float f = 0.0f;
    private String g;

    private void g() {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        this.c = "";
        try {
            String readLine = "";
            Process exec = Runtime.getRuntime().exec("top -EVENT_TRANSPORT 1 -ImageLoader 1");
            String str = new String();
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                } catch (IOException e) {
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    bufferedReader2 = bufferedReader;
                    th = th3;
                }
            } while (readLine.indexOf("qyvideo") < 0);
            this.c = readLine;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                }
            }
        } catch (IOException e3) {
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e4) {
                }
            }
        } catch (Throwable th4) {
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e5) {
                }
            }
        }
    }

    public void a() {
        g();
        if (this.c != null) {
            String[] split = this.c.split(Token.SEPARATOR);
            if (split != null && split.length > 0) {
                for (String str : split) {
                    if (str.indexOf("%") > 0) {
                        this.g = str;
                        return;
                    }
                }
            }
        }
    }

    public String b() {
        return ((("CPU Information: \n") + "User CPU utilized: " + this.d + "%\n") + "System CPU utilized: " + this.e + "%\n") + "Idle CPU: " + this.f + "%\n";
    }

    public float c() {
        return this.e;
    }

    public float d() {
        return this.d;
    }

    public float e() {
        return this.f;
    }

    public String f() {
        return this.g;
    }
}
