package com.weilian.phonelive.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import ch.a;
import ch.d;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;

public class BaseFragment extends Fragment implements OnClickListener, a {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static int f = a;
    protected LayoutInflater g;

    public AppContext a() {
        return (AppContext) getActivity().getApplication();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.g = layoutInflater;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    protected int b() {
        return a;
    }

    protected View a(int i) {
        return this.g.inflate(i, null);
    }

    public boolean c() {
        return false;
    }

    protected void d() {
        FragmentActivity activity = getActivity();
        if (activity instanceof d) {
            ((d) activity).g();
        }
    }

    protected ProgressDialog e() {
        return b(R.string.loading);
    }

    protected ProgressDialog b(int i) {
        FragmentActivity activity = getActivity();
        if (activity instanceof d) {
            return ((d) activity).c(i);
        }
        return null;
    }

    protected ProgressDialog a(String str) {
        FragmentActivity activity = getActivity();
        if (activity instanceof d) {
            return ((d) activity).c(str);
        }
        return null;
    }

    public void a(View view) {
    }

    public void initData() {
    }

    public void onClick(View view) {
    }

    protected void a(boolean z) {
    }
}
