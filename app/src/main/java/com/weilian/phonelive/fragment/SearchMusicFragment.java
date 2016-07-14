package com.weilian.phonelive.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.BindView;
import cc.e;
import com.lzfutil.util.d;
import com.lzfutil.util.t;
import com.dd.CircularProgressButton;
import com.google.gson.Gson;
import com.ksyun.media.player.KSYMediaMeta;
import com.weilian.phonelive.AppConfig;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseFragment;
import com.weilian.phonelive.bean.MusicBean;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchMusicFragment extends BaseFragment {
    List<MusicBean> h = new ArrayList();
    private e iObj;
    private d jObj;
    @BindView(R.id.et_search_input)
    EditText mInputEdit;
    @BindView(R.id.iv_search_music_back)
    ImageView mSearchBack;
    @BindView(R.id.tv_search_btn)
    TextView mSearchBtn;
    @BindView(R.id.lv_search_music)
    ListView mSearchListView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_search_music, null);
        ButterKnife.bind((Object) this, inflate);
        a(inflate);
        initData();
        return inflate;
    }

    public void a(View view) {
        this.mSearchBack.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                getActivity().finish();
            }
        });
        this.mSearchBtn.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                f();
            }
        });
        this.mSearchListView.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                File file = new File(AppConfig.t + ((MusicBean) h.get(i)).getSongname() + ".mp3");
                if (file.exists()) {
                    file.delete();
                    jObj.a((MusicBean) h.get(i));
                    h.remove(i);
                    iObj.notifyDataSetChanged();
                }
                return false;
            }
        });
    }

    private void f() {
        String trim = this.mInputEdit.getText().toString().trim();
        if (trim.equals("")) {
            AppContext.a(getActivity(), "\u8bf7\u8f93\u5165\u6709\u6548\u7684\u5173\u952e\u8bcd~");
        } else {
            ce.b.a(trim, new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    AppContext.a(getActivity(), "\u67e5\u8be2\u5931\u8d25,\u8bf7\u6362\u9996\u6b4c\u8bd5\u8bd5~");
                }

                @Override
                public void onResponse(String response, int id) {
                    a(response);
                }

                public void a(String str) {
                    try {
                        h.clear();
                        JSONObject jSONObject = new JSONObject(str.toString());
                        if (jSONObject.getInt("error_code") != 22000) {
                            AppContext.a(getActivity(), "\u67e5\u8be2\u5931\u8d25,\u8bf7\u6362\u9996\u6b4c\u8bd5\u8bd5~");
                            return;
                        }
                        JSONArray jSONArray = jSONObject.getJSONArray("song");
                        Gson gson = new Gson();
                        if (jSONArray.length() > 0) {
                            for (int i = 0; i < jSONArray.length(); i++) {
                                h.add(gson.fromJson(jSONArray.getString(i), MusicBean.class));
                            }
                        }
                        g();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void g() {
        this.iObj.a(this.h);
    }

    public void initData() {
        this.jObj = new d(getActivity());
        this.h = this.jObj.a();
        this.iObj = new e(this.h, this, this.jObj);
        this.mSearchListView.setAdapter(this.iObj);
        AppContext.a(getActivity(), "\u957f\u6309\u5220\u9664\u6b4c\u66f2~");
    }

    public void a(final MusicBean musicBean, final CircularProgressButton circularProgressButton) {
        ce.b.b(musicBean.getSongid(), new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

            public void a(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str.toString());
                    if (jSONObject.getInt("error_code") == 22000) {
                        String string = jSONObject.getJSONArray(KSYMediaMeta.IJKM_KEY_BITRATE).getJSONObject(0).getString("file_link");
                        String string2 = jSONObject.getJSONObject("songinfo").getString("lrclink");
                        ce.b.a(string, new FileCallBack( AppConfig.t, musicBean.getSongname() + ".mp3") {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                circularProgressButton.setErrorText("\u4e0b\u8f7d\u5931\u8d25");
                            }

                            @Override
                            public void onResponse(File response, int id) {
                                a(response);
                            }


                            public void a(File file) {
                                List arrayList = new ArrayList();
                                arrayList.add(musicBean);
                                jObj.a(arrayList);
                                t.c(file.getAbsolutePath());
                            }

                            public void inProgress(float f, long j) {
                                circularProgressButton.setProgress((int) (100.0f * f));
                            }
                        });
                        ce.b.b(string2, new FileCallBack( AppConfig.t, musicBean.getSongname() + ".lrc") {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                AppContext.a(getActivity(), "\u6ca1\u6709\u627e\u5230\u76f8\u5e94\u7684\u6b4c\u8bcd~");
                            }

                            @Override
                            public void onResponse(File response, int id) {
                                a(response);
                            }


                            public void a(File file) {
                                t.c(file.getPath());
                            }

                            public void inProgress(float f, long j) {
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
