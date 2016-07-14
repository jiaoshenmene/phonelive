package cc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weilian.phonelive.R;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.widget.AvatarView;
import com.weilian.phonelive.widget.LoadUrlImageView;
import java.util.List;

public class c extends BaseAdapter {
    private List<UserBean> a;
    private LayoutInflater b;

    private class aHolder {
        public TextView a;
        public TextView b;
        public TextView c;
        public TextView d;
        public LoadUrlImageView e;
        public AvatarView f;

        private aHolder() {
        }
    }

    public c(LayoutInflater layoutInflater, List<UserBean> list) {
        this.a = list;
        this.b = layoutInflater;
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
            view = this.b.inflate(R.layout.item_hot_user, null);
            aVar = new aHolder();
            aVar.a = (TextView) view.findViewById(R.id.tv_live_nick);
            aVar.b = (TextView) view.findViewById(R.id.tv_live_local);
            aVar.c = (TextView) view.findViewById(R.id.tv_live_usernum);
            aVar.f = (AvatarView) view.findViewById(R.id.iv_live_user_head);
            aVar.e = (LoadUrlImageView) view.findViewById(R.id.iv_live_user_pic);
            aVar.d = (TextView) view.findViewById(R.id.tv_hot_room_title);
            view.setTag(aVar);
        }
        UserBean userBean = (UserBean) this.a.get(i);
        aVar = (aHolder) view.getTag();
        aVar.a.setText(userBean.getUser_nicename());
        aVar.b.setText(userBean.getCity());
        aVar.e.setImageLoadUrl(userBean.getAvatar());
        aVar.f.setAvatarUrl(userBean.getAvatar());
        aVar.c.setText(String.valueOf(userBean.getNums()));
        if (userBean.getTitle() != null) {
            aVar.d.setVisibility(View.VISIBLE);
            aVar.d.setText(userBean.getTitle());
        }
        return view;
    }
}
