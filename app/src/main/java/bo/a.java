package bo;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

public class a {
    private View a;
    private Context b;
    private int c;
    private Dialog d;
    private int e;
    private boolean f = false;

    public a(Context context, int i, View view) {
        this.c = i;
        this.b = context;
        this.a = view;
    }

    public a(Context context, int i, int i2) {
        this.c = i;
        this.b = context;
        this.a = View.inflate(context, i2, null);
    }

    public void a(boolean z) {
        if (this.c == 0) {
            this.d = new Dialog(this.b);
        } else {
            this.d = new Dialog(this.b, this.c);
        }
        this.d.setCanceledOnTouchOutside(z);
        this.d.getWindow().requestFeature(1);
        this.d.setContentView(this.a);
        Window window = this.d.getWindow();
        Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
        LayoutParams attributes = window.getAttributes();
        attributes.width = defaultDisplay.getWidth() * 1;
        if (this.f) {
            attributes.gravity = 48;
        } else {
            attributes.gravity = 80;
        }
        if (this.e != 0) {
            window.setWindowAnimations(this.e);
        }
        window.setAttributes(attributes);
        this.d.show();
    }

    public void a() {
        this.f = true;
    }

    public void a(int i) {
        this.e = i;
    }

    public View b() {
        return this.a;
    }

    public void c() {
        if (this.d != null) {
            this.d.dismiss();
        }
    }
}
