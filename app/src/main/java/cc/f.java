package cc;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzfutil.util.t;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.ui.c;
import com.weilian.phonelive.widget.CircleImageView;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.List;
import okhttp3.Call;
import org.kymjs.kjframe.Core;

public class f extends BaseAdapter {
    private List<UserBean> a;

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

    public f(List<UserBean> list) {
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
        if (view == null) {
            view = View.inflate(AppContext.c(), R.layout.item_attention_fans, null);
            aHolder aHolderVar = new aHolder();
            aHolderVar.a = (CircleImageView) view.findViewById(R.id.cv_userHead);
            aHolderVar.b = (ImageView) view.findViewById(R.id.tv_item_usex);
            aHolderVar.c = (ImageView) view.findViewById(R.id.tv_item_ulevel);
            aHolderVar.e = (TextView) view.findViewById(R.id.tv_item_uname);
            aHolderVar.f = (TextView) view.findViewById(R.id.tv_item_usign);
            aHolderVar.d = (ImageView) view.findViewById(R.id.iv_item_attention);
            view.setTag(aHolderVar);
        }
        aHolder aHolderVar2 = (aHolder) view.getTag();
        final UserBean userBean = (UserBean) this.a.get(i);
        Core.getKJBitmap().display(aHolderVar2.a, userBean.getAvatar());
        aHolderVar2.b.setImageResource(userBean.getSex() == 1 ? R.drawable.global_male : R.drawable.global_female);
        aHolderVar2.d.setImageResource(userBean.getIsattention() == 1 ? R.drawable.me_following : R.drawable.me_follow);
        aHolderVar2.c.setImageResource(c.a[userBean.getLevel() - 1]);
        aHolderVar2.e.setText(userBean.getUser_nicename());
        aHolderVar2.f.setText(userBean.getSignature());
        aHolderVar2.d.setOnClickListener(new OnClickListener() {

            public void onClick(final View view) {
                ce.b.b(AppContext.c().i(), userBean.getId(), new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        a(response);
                    }

                    public void a(String str) {
                        t.c(userBean.getUser_nicename());
                        if (userBean.getIsattention() == 1) {
                            userBean.setIsattention(0);
                            ((ImageView) view.findViewById(R.id.iv_item_attention)).setImageResource(R.drawable.me_follow);
                            return;
                        }
                        userBean.setIsattention(1);
                        ((ImageView) view.findViewById(R.id.iv_item_attention)).setImageResource(R.drawable.me_following);
                    }
                });
            }
        });
        return view;
    }
}
