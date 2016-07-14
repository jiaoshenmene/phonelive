package cc;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class i extends PagerAdapter {
    private List<View> a = new ArrayList();

    public i(List<View> list) {
        this.a = list;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (((View) this.a.get(i)).getParent() != null) {
            ((ViewGroup) ((View) this.a.get(i)).getParent()).removeView((View) this.a.get(i));
        }
        viewGroup.addView((View) this.a.get(i));
        return this.a.get(i);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
