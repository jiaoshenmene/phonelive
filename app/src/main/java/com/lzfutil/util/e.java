package com.lzfutil.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog.Builder;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.weilian.phonelive.R;

public class e {
    public static Builder a(Context context) {
        return new Builder(context);
    }

    public static ProgressDialog a(Context context, String str) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        if (!TextUtils.isEmpty(str)) {
            progressDialog.setMessage(str);
        }
        return progressDialog;
    }

    public static Builder a(Context context, String str, OnClickListener onClickListener) {
        Builder a = a(context);
        a.setMessage((CharSequence) str);
        a.setPositiveButton((CharSequence) "\u786e\u5b9a", onClickListener);
        return a;
    }

    public static Builder b(Context context, String str) {
        return a(context, str, null);
    }

    public static Builder b(Context context, String str, OnClickListener onClickListener) {
        Builder a = a(context);
        a.setMessage(Html.fromHtml(str));
        a.setPositiveButton((CharSequence) "\u786e\u5b9a", onClickListener);
        a.setNegativeButton((CharSequence) "\u53d6\u6d88", null);
        return a;
    }

    public static Builder a(Context context, String str, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Builder a = a(context);
        a.setMessage((CharSequence) str);
        a.setPositiveButton((CharSequence) "\u786e\u5b9a", onClickListener);
        a.setNegativeButton((CharSequence) "\u53d6\u6d88", onClickListener2);
        return a;
    }

    public static Builder a(Context context, String str, String[] strArr, OnClickListener onClickListener) {
        Builder a = a(context);
        a.setItems((CharSequence[]) strArr, onClickListener);
        if (!TextUtils.isEmpty(str)) {
            a.setTitle((CharSequence) str);
        }
        a.setPositiveButton((CharSequence) "\u53d6\u6d88", null);
        return a;
    }

    public static Builder a(Context context, String[] strArr, OnClickListener onClickListener) {
        return a(context, "", strArr, onClickListener);
    }

    public static Builder a(Context context, String str, String[] strArr, int i, OnClickListener onClickListener) {
        Builder a = a(context);
        a.setSingleChoiceItems((CharSequence[]) strArr, i, onClickListener);
        if (!TextUtils.isEmpty(str)) {
            a.setTitle((CharSequence) str);
        }
        a.setNegativeButton((CharSequence) "\u53d6\u6d88", null);
        return a;
    }

    public static Builder a(Context context, String[] strArr, int i, OnClickListener onClickListener) {
        return a(context, "", strArr, i, onClickListener);
    }

    public static void aInflate(LayoutInflater layoutInflater, Context context, String str, final ch.e eVar) {
        View inflate = layoutInflater.inflate(R.layout.dialog_show_onback_view, null);
        final Dialog dialog = new Dialog(context, R.style.dialog);
        dialog.setContentView(inflate);
        dialog.show();
        TextView textView = (TextView) inflate.findViewById(R.id.tv_dialog_msg);
        Button button = (Button) inflate.findViewById(R.id.btn_dialog_cancel);
        Button button2 = (Button) inflate.findViewById(R.id.btn_dialog_determine);
        textView.setText(str);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                eVar.a(view, dialog);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                eVar.b(view, dialog);
            }
        });
    }

    public static void aInit(Context context, String str, final ch.e eVar) {
        View inflate = View.inflate(context, R.layout.dialog_show_onback_view, null);
        final Dialog dialog = new Dialog(context, R.style.dialog);
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        dialog.show();
        TextView textView = (TextView) inflate.findViewById(R.id.tv_dialog_msg);
        Button button = (Button) inflate.findViewById(R.id.btn_dialog_cancel);
        Button button2 = (Button) inflate.findViewById(R.id.btn_dialog_determine);
        textView.setText(str);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                eVar.a(view, dialog);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                eVar.b(view, dialog);
            }
        });
    }

    public static void b(LayoutInflater layoutInflater, Context context, String str, final ch.e eVar) {
        View inflate = layoutInflater.inflate(R.layout.dialog_show_prompt_view, null);
        final Dialog dialog = new Dialog(context, R.style.dialog);
        dialog.setContentView(inflate);
        dialog.show();
        TextView textView = (TextView) inflate.findViewById(R.id.tv_dialog_msg);
        Button button = (Button) inflate.findViewById(R.id.btn_dialog_determine);
        textView.setText(str);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                eVar.b(view, dialog);
            }
        });
    }

    public static void b(Context context, String str, final ch.e eVar) {
        View inflate = View.inflate(context, R.layout.dialog_show_prompt_view, null);
        final Dialog dialog = new Dialog(context, R.style.dialog);
        dialog.setContentView(inflate);
        dialog.show();
        TextView textView = (TextView) inflate.findViewById(R.id.tv_dialog_msg);
        Button button = (Button) inflate.findViewById(R.id.btn_dialog_determine);
        textView.setText(str);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                eVar.b(view, dialog);
            }
        });
    }
}
