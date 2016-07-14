package com.weilian.phonelive.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;
import cc.d;
import com.lzfutil.util.w;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.ChatType;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.BaseFragment;
import com.weilian.phonelive.bean.PrivateChatUserBean;
import com.weilian.phonelive.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class MessageDetailFragment extends BaseFragment {
    EMMessageListener h = new EMMessageListener() {

        public void onMessageReceived(List<EMMessage> list) {
            for (final EMMessage eMMessage : list) {
                if (eMMessage.getChatType() == ChatType.GroupChat || eMMessage.getChatType() == ChatType.ChatRoom) {
                    eMMessage.getTo();
                } else {
                    eMMessage.getFrom();
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            a(eMMessage);
                        }
                    });
                }
            }
        }

        public void onCmdMessageReceived(List<EMMessage> list) {
        }

        public void onMessageReadAckReceived(List<EMMessage> list) {
        }

        public void onMessageDeliveryAckReceived(List<EMMessage> list) {
        }

        public void onMessageChanged(EMMessage eMMessage, Object obj) {
        }
    };
    private List<EMMessage> i = new ArrayList();
    private PrivateChatUserBean j;
    private d k;
    private UserBean l;
    @BindView(R.id.lv_message)
    ListView mChatMessageListView;
    @BindView(R.id.et_private_chat_message)
    EditText mMessageInput;
    @BindView(R.id.iv_private_chat_send)
    Button mSendChatBtn;
    @BindView(R.id.iv_private_chat_gift)
    ImageView mShowGiftBtn;
    @BindView(R.id.tv_private_chat_title)
    TextView mTitle;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_private_chat_message, null);
        ButterKnife.bind((Object) this, inflate);
        a(inflate);
        initData();
        return inflate;
    }

    @OnClick({R.id.iv_private_chat_send, R.id.et_private_chat_message, R.id.iv_private_chat_back, R.id.iv_private_chat_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_private_chat_back:
                getActivity().finish();
                return;
            case R.id.iv_private_chat_user:
                w.b(getActivity(), this.j.getId());
                return;
            case R.id.iv_private_chat_send:
                if (this.mMessageInput.getText().toString().equals("")) {
                    AppContext.a(getActivity(), "\u5185\u5bb9\u4e3a\u7a7a");
                }
                a(com.weilian.phonelive.ui.e.a(this.mMessageInput.getText().toString(), this.j, this.l));
                this.mMessageInput.setText("");
                return;
            default:
                return;
        }
    }

    public void initData() {
        EMClient.getInstance().chatManager().addMessageListener(this.h);
        this.l = AppContext.c().g();
        this.j = (PrivateChatUserBean) getActivity().getIntent().getSerializableExtra("user");
        this.mTitle.setText(this.j.getUser_nicename());
        f();
        this.k = new d(AppContext.c().i(), getActivity());
        this.k.a(this.i);
        this.mChatMessageListView.setAdapter(this.k);
        this.mChatMessageListView.setSelection(this.i.size() - 1);
    }

    private void f() {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(String.valueOf(this.j.getId()));
        EMClient.getInstance().chatManager().markAllConversationsAsRead();
        try {
            this.i = conversation.getAllMessages();
        } catch (Exception e) {
        }
    }

    public void a(View view) {
        this.mMessageInput.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (mMessageInput.getText().toString().equals("")) {
                    mSendChatBtn.setVisibility(View.GONE);
                    return;
                }
                mSendChatBtn.setVisibility(View.VISIBLE);
                mShowGiftBtn.setVisibility(View.GONE);
            }

            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void a(EMMessage eMMessage) {
        this.k.a(eMMessage);
        this.mChatMessageListView.setAdapter(this.k);
        this.mChatMessageListView.setSelection(this.k.getCount() - 1);
    }

    public void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(this.h);
    }

    public void onStop() {
        super.onStop();
        EMClient.getInstance().chatManager().removeMessageListener(this.h);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.h != null) {
            EMClient.getInstance().chatManager().removeMessageListener(this.h);
        }
    }
}
