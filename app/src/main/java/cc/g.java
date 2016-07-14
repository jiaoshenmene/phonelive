package cc;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.bean.PrivateChatUserBean;
import com.weilian.phonelive.ui.c;
import com.weilian.phonelive.widget.CircleImageView;
import java.util.ArrayList;
import java.util.List;
import org.kymjs.kjframe.Core;

public class g extends BaseAdapter {
    private List<PrivateChatUserBean> a;

    private class aHolder {
        public CircleImageView a;
        public ImageView b;
        public ImageView c;
        public ImageView d;
        public TextView e;
        public TextView f;

        private aHolder() {
        }
    }

    public g(List<PrivateChatUserBean> list) {
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
        int i2 = View.VISIBLE;
        if (view == null) {
            view = View.inflate(AppContext.c(), R.layout.item_private_chat, null);
            aVar = new aHolder();
            aVar.a = (CircleImageView) view.findViewById(R.id.cv_userHead);
            aVar.b = (ImageView) view.findViewById(R.id.tv_item_usex);
            aVar.c = (ImageView) view.findViewById(R.id.tv_item_ulevel);
            aVar.e = (TextView) view.findViewById(R.id.tv_item_uname);
            aVar.f = (TextView) view.findViewById(R.id.tv_item_last_msg);
            aVar.d = (ImageView) view.findViewById(R.id.iv_unread_dot);
            view.setTag(aVar);
        } else {
            aVar = (aHolder) view.getTag();
        }
        PrivateChatUserBean privateChatUserBean = (PrivateChatUserBean) this.a.get(i);
        Core.getKJBitmap().display(aVar.a, privateChatUserBean.getAvatar());
        aVar.b.setImageResource(privateChatUserBean.getSex() == 1 ? R.drawable.global_male : R.drawable.global_female);
        aVar.c.setImageResource(c.a[privateChatUserBean.getLevel() + -1 < 0 ? 0 : privateChatUserBean.getLevel() - 1]);
        aVar.e.setText(privateChatUserBean.getUser_nicename());
        aVar.f.setText(privateChatUserBean.getLastMessage());
        ImageView imageView = aVar.d;
        if (!privateChatUserBean.isUnreadMessage()) {
            i2 = View.GONE;
        }
        imageView.setVisibility(i2);
        return view;
    }

    public void a(ArrayList<PrivateChatUserBean> arrayList) {
        this.a = arrayList;
    }
}
