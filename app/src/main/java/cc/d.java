package cc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.Type;
import com.weilian.phonelive.widget.PhoneLiveChatRow;
import com.weilian.phonelive.widget.PhoneLiveChatRowText;
import java.util.ArrayList;
import java.util.List;
import s.a;

public class d extends BaseAdapter {
    private List<EMMessage> a = new ArrayList();
    private LayoutInflater b;
    private Context c;
    private int d;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Type.values().length];

        static {
            try {
                a[Type.TXT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public d(int i, Context context) {
        this.d = i;
        this.c = context;
        this.b = LayoutInflater.from(context);
    }

    public void a(List<EMMessage> list) {
        this.a = list;
    }

    public void a(EMMessage eMMessage) {
        this.a.add(eMMessage);
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
        View a;
        EMMessage eMMessage = (EMMessage) this.a.get(i);
        if (view == null) {
            a = a(this.c, eMMessage, i);
        } else {
            a = view;
        }
        ((PhoneLiveChatRow) a).a(eMMessage, i);
        return a;
    }

    private View a(Context context, EMMessage eMMessage, int i) {
        switch (AnonymousClass1.a[eMMessage.getType().ordinal()]) {
            case 1:
                return new PhoneLiveChatRowText(context, eMMessage, i, this);
            default:
                return null;
        }
    }
}
