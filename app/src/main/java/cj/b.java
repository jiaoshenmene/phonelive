package cj;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ck.ChatProcess;

import com.lzfutil.util.w;
import com.google.gson.Gson;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.ShowLiveActivityBase;
import com.weilian.phonelive.bean.PrivateChatUserBean;
import com.weilian.phonelive.bean.UserAlertInfoBean;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.ui.c;
import com.weilian.phonelive.widget.AvatarView;
import com.weilian.phonelive.widget.BottomMenuView;
import com.weilian.phonelive.widget.CircleImageView;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;
import org.kymjs.kjframe.Core;

public class b {
    public static void a(final ShowLiveActivityBase showLiveActivityBase, final UserBean userBean, final UserBean userBean2, int i, ChatProcess aVar) {
        final View inflate = View.inflate(showLiveActivityBase, R.layout.dialog_show_user_info_detail, null);
        final Dialog dialog = new Dialog(showLiveActivityBase, R.style.dialog);
        final TextView textView = (TextView) inflate.findViewById(R.id.tv_live_manage_or_report);
        dialog.setContentView(inflate);
        inflate.findViewById(R.id.ib_show_dialog_back).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.tv_show_dialog_u_private_chat_btn).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                b.a(showLiveActivityBase, userBean.getId(), userBean2.getId());
            }
        });
        inflate.findViewById(R.id.tv_show_dialog_u_reply_btn).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                showLiveActivityBase.a(userBean2);
                dialog.dismiss();
            }
        });
        final ShowLiveActivityBase showLiveActivityBase2 = showLiveActivityBase;
        final UserBean userBean3 = userBean;
        final UserBean userBean4 = userBean2;
        final ChatProcess aVar2 = aVar;
        ce.b.h(i, userBean.getId(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }
            public void a(String str) {
                String a = ce.a.a(str, showLiveActivityBase2);
                if (a != null) {
                    userBean3.setuType(Integer.parseInt(a));
                    textView.setText(userBean3.getuType() == 40 ? "\u7ba1\u7406" : "\u4e3e\u62a5");
                    textView.setOnClickListener(new OnClickListener() {

                        public void onClick(View view) {
                            if (userBean3.getuType() == 40) {
                                b.a(showLiveActivityBase2, userBean3, userBean4, aVar2, false);
                                dialog.dismiss();
                                return;
                            }
                            AppContext.a(showLiveActivityBase2, showLiveActivityBase2.getString(R.string.reportsuccess));
                        }
                    });
                }
            }
        });
        inflate.findViewById(R.id.tv_show_dialog_u_home_btn).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                w.b(showLiveActivityBase, userBean2.getId());
            }
        });
        ((AvatarView) inflate.findViewById(R.id.av_show_dialog_u_head)).setAvatarUrl(userBean2.getAvatar());
        ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_name)).setText(userBean2.getUser_nicename());
        ((ImageView) inflate.findViewById(R.id.iv_show_dialog_level)).setImageResource(c.a[userBean2.getLevel()]);
        ((ImageView) inflate.findViewById(R.id.iv_show_dialog_sex)).setImageResource(userBean2.getSex() == 1 ? R.drawable.global_male : R.drawable.global_female);
        ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_sign)).setText(userBean2.getSignature());
        ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_address)).setText(userBean.getCity() == null ? "\u5b9a\u4f4d\u9ed1\u6d1e" : userBean.getCity());
        ce.b.d(userBean2.getId(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

            public void a(String str) {
                String a = ce.a.a(str, showLiveActivityBase);
                if (a != null) {
                    UserAlertInfoBean userAlertInfoBean = (UserAlertInfoBean) new Gson().fromJson(a, UserAlertInfoBean.class);
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_fllow_num)).setText("\u5173\u6ce8" + userAlertInfoBean.getAttention());
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_fans)).setText("\u7c89\u4e1d:" + userAlertInfoBean.getFans());
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_send_num)).setText("\u9001\u51fa:" + userAlertInfoBean.getConsumption());
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_ticket)).setText("\u6620\u7968:" + userAlertInfoBean.getVotestotal());
                    if (userAlertInfoBean.getVotestotal() > 0) {
                        ((AvatarView) inflate.findViewById(R.id.av_show_dialog_order_first_u_head)).setAvatarUrl(userAlertInfoBean.getContribute().getAvatar());
                    } else {
                        ((AvatarView) inflate.findViewById(R.id.av_show_dialog_order_first_u_head)).setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        ce.b.a(AppContext.c().i(), userBean2.getId(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

//            public /* synthetic */ void onResponse(Object obj) {
//                aVal((String) obj);
//            }
//
//            public void onError(Call call, Exception exception) {
//            }

            public void a(String str) {
                String a = ce.a.a(str, showLiveActivityBase);
                if (a != null) {
                    final TextView textView = (TextView) inflate.findViewById(R.id.tv_show_dialog_u_fllow_btn);
                    if (a.equals("0")) {
                        textView.setText(showLiveActivityBase.getString(R.string.follow2));
                        textView.setOnClickListener(new OnClickListener() {

                            public void onClick(View view) {
                                ce.b.b(AppContext.c().i(), userBean2.getId(), null);
                                textView.setEnabled(false);
                                textView.setTextColor(showLiveActivityBase.getResources().getColor(R.color.gray));
                                textView.setText(showLiveActivityBase.getString(R.string.alreadyfollow));
                            }
                        });
                        return;
                    }
                    textView.setText(showLiveActivityBase.getString(R.string.alreadyfollow));
                    textView.setEnabled(false);
                    textView.setTextColor(showLiveActivityBase.getResources().getColor(R.color.gray));
                }
            }
        });
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public static void a(final ShowLiveActivityBase showLiveActivityBase, final UserBean userBean, int i) {
        final View inflate = View.inflate(showLiveActivityBase, R.layout.dialog_show_own_info_detail, null);
        final Dialog dialog = new Dialog(showLiveActivityBase, R.style.dialog);
        dialog.setContentView(inflate);
        inflate.findViewById(R.id.ib_show_dialog_back).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.tv_show_dialog_u_home_btn).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                w.b(showLiveActivityBase, userBean.getId());
            }
        });
        ((AvatarView) inflate.findViewById(R.id.av_show_dialog_u_head)).setAvatarUrl(userBean.getAvatar());
        ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_name)).setText(userBean.getUser_nicename());
        ((ImageView) inflate.findViewById(R.id.iv_show_dialog_level)).setImageResource(c.a[userBean.getLevel()]);
        ((ImageView) inflate.findViewById(R.id.iv_show_dialog_sex)).setImageResource(userBean.getSex() == 1 ? R.drawable.global_male : R.drawable.global_female);
        ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_sign)).setText(userBean.getSignature());
        ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_address)).setText(userBean.getCity() == null ? "\u5b9a\u4f4d\u9ed1\u6d1e" : userBean.getCity());
        ce.b.d(userBean.getId(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }
//
//            public /* synthetic */ void onResponse(Object obj) {
//                aVal((String) obj);
//            }
//
//            public void onError(Call call, Exception exception) {
//            }

            public void a(String str) {
                String a = ce.a.a(str, showLiveActivityBase);
                if (a != null) {
                    UserAlertInfoBean userAlertInfoBean = (UserAlertInfoBean) new Gson().fromJson(a, UserAlertInfoBean.class);
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_fllow_num)).setText("\u5173\u6ce8" + userAlertInfoBean.getAttention());
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_fans)).setText("\u7c89\u4e1d:" + userAlertInfoBean.getFans());
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_send_num)).setText("\u9001\u51fa:" + userAlertInfoBean.getConsumption());
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_ticket)).setText(AppContext.c().getString(R.string.yingpiao) + ":" + userAlertInfoBean.getVotestotal());
                    if (userAlertInfoBean.getVotestotal() > 0) {
                        ((AvatarView) inflate.findViewById(R.id.av_show_dialog_order_first_u_head)).setAvatarUrl(userAlertInfoBean.getContribute().getAvatar());
                    } else {
                        ((AvatarView) inflate.findViewById(R.id.av_show_dialog_order_first_u_head)).setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public static void a(final ShowLiveActivityBase showLiveActivityBase, final UserBean userBean, final UserBean userBean2, ChatProcess aVar) {
        final View inflate = View.inflate(showLiveActivityBase, R.layout.dialog_show_user_info_detail_emcee, null);
        final Dialog dialog = new Dialog(showLiveActivityBase, R.style.dialog);
        dialog.setContentView(inflate);
        inflate.findViewById(R.id.ib_show_dialog_back).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.bt_show_dialog_u_private_chat_btn).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                b.a(showLiveActivityBase, userBean2.getId(), userBean.getId());
            }
        });
        final UserBean userBean3 = userBean2;
        final UserBean userBean4 = userBean;
        final ShowLiveActivityBase showLiveActivityBase2 = showLiveActivityBase;
        final ChatProcess aVar2 = aVar;
        inflate.findViewById(R.id.tv_live_manage).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ce.b.h(userBean3.getId(), userBean4.getId(), new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        a(response);
                    }


                    public void a(String str) {
                        String a = ce.a.a(str, showLiveActivityBase2);
                        if (a != null) {
                            userBean4.setuType(Integer.parseInt(a));
                            b.a(showLiveActivityBase2, userBean3, userBean4, aVar2, true);
                        }
                    }
                });
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.bt_show_dialog_u_reply_btn).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                showLiveActivityBase.a(userBean);
                dialog.dismiss();
            }
        });
        ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_name)).setText(userBean.getUser_nicename());
        ((ImageView) inflate.findViewById(R.id.iv_show_dialog_level)).setImageResource(c.a[userBean.getLevel()]);
        ((ImageView) inflate.findViewById(R.id.iv_show_dialog_sex)).setImageResource(userBean.getSex() == 1 ? R.drawable.global_male : R.drawable.global_female);
        ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_sign)).setText(userBean.getSignature());
        Core.getKJBitmap().display((CircleImageView) inflate.findViewById(R.id.av_show_dialog_u_head), userBean.getAvatar());
        ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_address)).setText(userBean2.getCity() == null ? "\u5b9a\u4f4d\u9ed1\u6d1e" : userBean2.getCity());
        ce.b.d(userBean.getId(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }


            public void a(String str) {
                String a = ce.a.a(str, showLiveActivityBase);
                if (a != null) {
                    UserAlertInfoBean userAlertInfoBean = (UserAlertInfoBean) new Gson().fromJson(a, UserAlertInfoBean.class);
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_fllow_num)).setText("\u5173\u6ce8" + userAlertInfoBean.getAttention());
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_fans)).setText("\u7c89\u4e1d:" + userAlertInfoBean.getFans());
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_send_num)).setText("\u9001\u51fa:" + userAlertInfoBean.getConsumption());
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_ticket)).setText("\u6620\u7968:" + userAlertInfoBean.getVotestotal());
                }
            }
        });
        ce.b.a(AppContext.c().i(), userBean.getId(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

            public void a(String str) {
                String a = ce.a.a(str, showLiveActivityBase);
                if (a != null) {
                    final Button button = (Button) inflate.findViewById(R.id.bt_show_dialog_u_fllow_btn);
                    if (a.equals("0")) {
                        button.setText(showLiveActivityBase.getString(R.string.follow2));
                        button.setOnClickListener(new OnClickListener() {

                            public void onClick(View view) {
                                ce.b.b(AppContext.c().i(), userBean.getId(), null);
                                button.setEnabled(false);
                                button.setTextColor(showLiveActivityBase.getResources().getColor(R.color.gray));
                                button.setBackgroundResource(R.drawable.room_pop_up_graybutton);
                                button.setText(showLiveActivityBase.getString(R.string.alreadyfollow));
                            }
                        });
                        return;
                    }
                    button.setText(showLiveActivityBase.getString(R.string.alreadyfollow));
                    button.setEnabled(false);
                    button.setTextColor(showLiveActivityBase.getResources().getColor(R.color.gray));
                    button.setBackgroundResource(R.drawable.room_pop_up_graybutton);
                }
            }
        });
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public static void a(final ShowLiveActivityBase showLiveActivityBase, UserBean userBean) {
        final View inflate = View.inflate(showLiveActivityBase, R.layout.dialog_show_own_info_detail_emcee, null);
        final Dialog dialog = new Dialog(showLiveActivityBase, R.style.dialog);
        dialog.setContentView(inflate);
        inflate.findViewById(R.id.ib_show_dialog_back).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_name)).setText(userBean.getUser_nicename());
        ((ImageView) inflate.findViewById(R.id.iv_show_dialog_level)).setImageResource(c.a[userBean.getLevel()]);
        ((ImageView) inflate.findViewById(R.id.iv_show_dialog_sex)).setImageResource(userBean.getSex() == 1 ? R.drawable.global_male : R.drawable.global_female);
        ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_sign)).setText(userBean.getSignature());
        Core.getKJBitmap().display((CircleImageView) inflate.findViewById(R.id.av_show_dialog_u_head), userBean.getAvatar());
        ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_address)).setText(userBean.getCity() == null ? "\u5b9a\u4f4d\u9ed1\u6d1e" : userBean.getCity());
        ce.b.d(userBean.getId(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

            public void a(String str) {
                String a = ce.a.a(str, showLiveActivityBase);
                if (a != null) {
                    UserAlertInfoBean userAlertInfoBean = (UserAlertInfoBean) new Gson().fromJson(a, UserAlertInfoBean.class);
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_fllow_num)).setText("\u5173\u6ce8" + userAlertInfoBean.getAttention());
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_fans)).setText("\u7c89\u4e1d:" + userAlertInfoBean.getFans());
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_send_num)).setText("\u9001\u51fa:" + userAlertInfoBean.getConsumption());
                    ((TextView) inflate.findViewById(R.id.tv_show_dialog_u_ticket)).setText("\u6620\u7968:" + userAlertInfoBean.getVotestotal());
                }
            }
        });
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public static void a(Activity activity, UserBean userBean, UserBean userBean2, ChatProcess aVar, boolean z) {
        BottomMenuView bottomMenuView = new BottomMenuView(activity);
        bo.a aVar2 = new bo.a((Context) activity, (int) R.style.BottomViewTheme_Transparent, bottomMenuView);
        bottomMenuView.a(userBean, userBean2, userBean.getId(), activity, aVar, aVar2);
        bottomMenuView.setIsEmcee(z);
        aVar2.a((int) R.style.BottomToTopAnim);
        aVar2.a(false);
    }

    public static void a(final Activity activity, int i, int i2) {
        ce.b.f(i, i2, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

            public void a(String str) {
                String a = ce.a.a(str, activity);
                if (a != null) {
                    w.a(activity, (PrivateChatUserBean) new Gson().fromJson(a, PrivateChatUserBean.class));
                }
            }
        });
    }
}
