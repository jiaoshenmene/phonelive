package com.weilian.phonelive.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cc.g;
import com.lzfutil.util.t;
import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.PrivateChatPageBase;
import com.weilian.phonelive.bean.PrivateChatUserBean;
import com.weilian.phonelive.bean.UserBean;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.Map;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;

public class NotFollowPrivateChatFragment extends PrivateChatPageBase {
    private int h;
    private ArrayList<PrivateChatUserBean> iObj = new ArrayList();
    private ListView jObj;
    private int k;
    private g l;
    private UserBean m;
    private Map<String, EMConversation> n;
    private Gson o = new Gson();

    protected void a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
    }

    public void a(View view) {
        this.jObj = (ListView) view.findViewById(R.id.lv_privatechat);
        this.jObj.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                t.c("position");
                k = i;
                ((PrivateChatUserBean) iObj.get(i)).setUnreadMessage(false);
                w.a(getActivity(), (PrivateChatUserBean) iObj.get(i));
            }
        });
    }

    protected void a(final EMMessage eMMessage) {
        try {
            if (eMMessage.getIntAttribute("isfollow") == 0) {
                if (this.n.containsKey(eMMessage.getFrom())) {
                    t.c("in conversations not follow");
                    if (this.iObj != null) {
                        t.c("not null");
                        for (int i = 0; i < this.iObj.size(); i++) {
                            PrivateChatUserBean privateChatUserBean = (PrivateChatUserBean) this.iObj.get(i);
                            t.c("uid:" + privateChatUserBean.getId() + "fromid:" + eMMessage.getFrom());
                            if (privateChatUserBean.getId() == Integer.parseInt(eMMessage.getFrom())) {
                                privateChatUserBean.setLastMessage(((EMTextMessageBody) eMMessage.getBody()).getMessage());
                                privateChatUserBean.setUnreadMessage(true);
                                this.iObj.set(i, privateChatUserBean);
                                this.l.a(this.iObj);
                                this.l.notifyDataSetChanged();
                            }
                        }
                        return;
                    }
                    return;
                }
                t.c("not in conversations not follow");
                this.n = EMClient.getInstance().chatManager().getAllConversations();
                ce.b.f(this.m.getId(), Integer.parseInt(eMMessage.getFrom()), new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        a(response);
                    }


                    public void a(String str) {
                        String a = ce.a.a(str, getActivity());
                        if (a != null) {
                            PrivateChatUserBean privateChatUserBean = (PrivateChatUserBean) o.fromJson(a, PrivateChatUserBean.class);
                            privateChatUserBean.setLastMessage(((EMTextMessageBody) eMMessage.getBody()).getMessage());
                            privateChatUserBean.setUnreadMessage(true);
                            iObj.add(privateChatUserBean);
                            l.a(iObj);
                            jObj.setAdapter(l);
                        }
                    }
                });
            }
        } catch (HyphenateException e) {
            t.c("\u6ca1\u6709\u4f20\u9001\u662f\u5426\u5173\u6ce8\u6807\u8bb0");
            e.printStackTrace();
        }
    }

    public void initData() {
        this.m = AppContext.c().g();
        this.h = getArguments().getInt("ACTION");
        this.l = new g(this.iObj);
        f();
    }

    public void onResume() {
        super.onResume();
        if (this.iObj != null && this.iObj.size() != 0) {
            try {
                EMConversation conversation = EMClient.getInstance().chatManager().getConversation(String.valueOf(((PrivateChatUserBean) this.iObj.get(this.k)).getId()));
                conversation.markAllMessagesAsRead();
                ((PrivateChatUserBean) this.iObj.get(this.k)).setLastMessage(((EMTextMessageBody) conversation.getLastMessage().getBody()).getMessage());
                this.l.a(this.iObj);
            } catch (Exception e) {
            }
            this.l.notifyDataSetChanged();
        }
    }

    private void f() {
        this.n = EMClient.getInstance().chatManager().getAllConversations();
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.n.keySet()) {
            stringBuilder.append(str + ",");
        }
        if (stringBuilder.toString().length() != 0) {
            ce.b.a(this.h, this.m.getId(), stringBuilder.toString().substring(0, stringBuilder.length() - 1), new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    a(response);
                }


                public void a(String str) {
                    String a = ce.a.a(str, getActivity());
                    if (a != null) {
                        try {
                            JSONArray jSONArray = new JSONArray(a);
                            if (jSONArray.length() > 0) {
                                Gson gson = new Gson();
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    PrivateChatUserBean privateChatUserBean = (PrivateChatUserBean) gson.fromJson(jSONArray.getString(i), PrivateChatUserBean.class);
                                    EMConversation conversation = EMClient.getInstance().chatManager().getConversation(String.valueOf(privateChatUserBean.getId()));
                                    try {
                                        boolean z;
                                        privateChatUserBean.setLastMessage(((EMTextMessageBody) conversation.getLastMessage().getBody()).getMessage());
                                        if (conversation.getUnreadMsgCount() > 0) {
                                            z = true;
                                        } else {
                                            z = false;
                                        }
                                        privateChatUserBean.setUnreadMessage(z);
                                    } catch (Exception e) {
                                    }
                                    iObj.add(privateChatUserBean);
                                }
                                g();
                            }
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    private void g() {
        if (this.iObj != null) {
            this.jObj.setAdapter(this.l);
        }
    }
}
