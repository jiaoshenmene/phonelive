package cc;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.lzfutil.util.d;
import com.dd.CircularProgressButton;
import com.weilian.phonelive.AppConfig;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.bean.MusicBean;
import com.weilian.phonelive.fragment.SearchMusicFragment;
import java.io.File;
import java.util.List;

public class e extends BaseAdapter {
    private List<MusicBean> a;
    private SearchMusicFragment b;
    private d c;

    class a {
        TextView a;
        TextView b;
        CircularProgressButton c;
        final /* synthetic */ e d;

        a(e eVar) {
            this.d = eVar;
        }
    }

    public e(List<MusicBean> list, SearchMusicFragment searchMusicFragment, d dVar) {
        this.a = list;
        this.b = searchMusicFragment;
        this.c = dVar;
    }

    public void a(List<MusicBean> list) {
        this.a = list;
        notifyDataSetChanged();
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
        a aVar = new a(this);
        View inflate = View.inflate(AppContext.c(), R.layout.item_search_music, null);
        aVar.a = (TextView) inflate.findViewById(R.id.item_tv_search_music_name);
        aVar.b = (TextView) inflate.findViewById(R.id.item_tv_search_music_author);
        aVar.c = (CircularProgressButton) inflate.findViewById(R.id.item_btn_search_music_download);
        final MusicBean musicBean = (MusicBean) this.a.get(i);
        aVar.a.setText(musicBean.getSongname());
        aVar.b.setText(musicBean.getArtistname());
        final File file = new File(AppConfig.t + musicBean.getSongname() + ".mp3");
        if (file.exists()) {
            aVar.c.setText(R.string.select);
        }
        aVar.c.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                if (file.exists()) {
                    b.getActivity().setResult(1, new Intent().putExtra("filepath", file.getPath()));
                    b.getActivity().finish();
                    return;
                }
                b.a(musicBean, (CircularProgressButton) view);
            }
        });
        return inflate;
    }
}
