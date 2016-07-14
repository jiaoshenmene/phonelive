package cc;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.bean.BlackBean;
import com.weilian.phonelive.ui.c;
import com.weilian.phonelive.widget.CircleImageView;
import java.util.List;
import org.kymjs.kjframe.Core;

public class a extends BaseAdapter {
    private List<BlackBean> a;

    private class aHolder {
        public CircleImageView a;
        public ImageView b;
        public ImageView c;
        public TextView d;
        public TextView e;
        final /* synthetic */ a f;

        private aHolder(a aVar) {
            this.f = aVar;
        }
    }

    public a(List<BlackBean> list) {
        this.a = list;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        aHolder aVar;
        if (view == null) {
            view = View.inflate(AppContext.c(), R.layout.item_black_info, null);
            aVar = new aHolder(a.this);
            aVar.a = (CircleImageView) view.findViewById(R.id.cv_userHead);
            aVar.b = (ImageView) view.findViewById(R.id.tv_item_usex);
            aVar.c = (ImageView) view.findViewById(R.id.tv_item_ulevel);
            aVar.d = (TextView) view.findViewById(R.id.tv_item_uname);
            aVar.e = (TextView) view.findViewById(R.id.tv_item_usign);
            view.setTag(aVar);
        } else {
            aVar = (aHolder) view.getTag();
        }
        BlackBean blackBean = (BlackBean) this.a.get(i);
        Core.getKJBitmap().display(aVar.a, blackBean.getAvatar());
        aVar.b.setImageResource(blackBean.getSex() == 1 ? R.drawable.global_male : R.drawable.global_female);
        aVar.c.setImageResource(c.a[blackBean.getLevel() - 1]);
        aVar.d.setText(blackBean.getUser_nicename());
        aVar.e.setText(blackBean.getSignature());
        return view;
    }
}
