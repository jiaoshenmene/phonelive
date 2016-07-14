package cc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import java.util.ArrayList;

@SuppressLint({"Recycle"})
public class h extends FragmentStatePagerAdapter {
    private final Context a;
    private final ArrayList<j> b = new ArrayList();

    public h(FragmentManager fragmentManager, ViewPager viewPager) {
        super(fragmentManager);
        this.a = viewPager.getContext();
    }

    public void a(String str, String str2, Class<?> cls, Bundle bundle) {
        this.b.add(new j(str, str2, cls, bundle));
    }

    public int getCount() {
        return this.b.size();
    }

    public int getItemPosition(Object obj) {
        return -2;
    }

    public Fragment getItem(int i) {
        j jVar = (j) this.b.get(i);
        return Fragment.instantiate(this.a, jVar.b.getName(), jVar.c);
    }

    public CharSequence getPageTitle(int i) {
        return ((j) this.b.get(i)).d;
    }
}
