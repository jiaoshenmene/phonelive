package cc;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.bean.GiftBean;
import com.weilian.phonelive.widget.LoadUrlImageView;
import java.util.List;

public class b extends BaseAdapter {
    private List<GiftBean> a;

    private class aHolder {
        public LoadUrlImageView a;
        public TextView b;
        public TextView c;
        final /* synthetic */ b d;

        private aHolder(b bVar) {
            this.d = bVar;
        }
    }

    public b(List<GiftBean> list) {
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
            view = View.inflate(AppContext.c(), R.layout.item_show_gift, null);
            aVar = new aHolder(b.this);
            aVar.a = (LoadUrlImageView) view.findViewById(R.id.iv_show_gift_img);
            aVar.b = (TextView) view.findViewById(R.id.tv_show_gift_price);
            aVar.c = (TextView) view.findViewById(R.id.tv_show_gift_experience);
            view.setTag(aVar);
        } else {
            aVar = (aHolder) view.getTag();
        }
        GiftBean giftBean = (GiftBean) this.a.get(i);
        aVar.a.setImageLoadUrl(giftBean.getGifticon());
        aVar.c.setText("+" + (giftBean.getNeedcoin() * 10) + "\u7ecf\u9a8c\u503c");
        aVar.b.setText(giftBean.getNeedcoin() + "");
        if (giftBean.getType() == 1) {
            view.findViewById(R.id.iv_show_gift_selected).setBackgroundResource(R.drawable.icon_continue_gift);
        }
        return view;
    }
}
