package com.weilian.phonelive.base;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import ck.ChatProcess;

import com.google.gson.Gson;
import com.weilian.phonelive.R;
import com.weilian.phonelive.bean.ChatBean;
import com.weilian.phonelive.bean.SendGiftBean;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.widget.AvatarView;
import com.weilian.phonelive.widget.HorizontalListView;
import com.weilian.phonelive.widget.StrokeTextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.kymjs.kjframe.http.HttpStatus;

public abstract class ShowLiveActivityBase extends BaseActivity {
    protected AnimationDrawable A;
    protected Handler B = new Handler();
    protected Gson d = new Gson();
    protected Map<Integer, View> e = new HashMap();
    protected Map<Integer, SendGiftBean> f = new HashMap();
    protected List<SendGiftBean> g = new ArrayList();
    protected List<SendGiftBean> h = new ArrayList();
    protected List<SendGiftBean> i = new ArrayList();
    protected List<SendGiftBean> j = new ArrayList();
    protected boolean k = true;
    protected boolean l = true;
    protected boolean m = true;
    @BindView(R.id.ll_bottom_menu)
    protected RelativeLayout mButtonMenu;
    @BindView(R.id.fl_bottom_menu)
    protected FrameLayout mButtonMenuFrame;
    @BindView(R.id.et_live_chat_input)
    protected EditText mChatInput;
    @BindView(R.id.iv_live_emcee_head)
    protected AvatarView mEmceeHead;
    @BindView(R.id.rl_livestop)
    protected RelativeLayout mLayoutLiveStop;
    @BindView(R.id.iv_live_chat)
    protected ImageView mLiveChat;
    @BindView(R.id.ll_live_chat_edit)
    protected LinearLayout mLiveChatEdit;
    @BindView(R.id.tv_live_num)
    protected TextView mLiveNum;
    @BindView(R.id.lv_live_room)
    protected ListView mLvChatList;
    @BindView(R.id.rl_live_root)
    protected RelativeLayout mRoot;
    @BindView(R.id.ll_show_gift_animator)
    protected LinearLayout mShowGiftAnimator;
    @BindView(R.id.hl_room_user_list)
    protected HorizontalListView mUserList;
    @BindView(R.id.ll_yp_labe)
    LinearLayout mYpLabe;
    @BindView(R.id.tv_yingpiao_num)
    protected TextView mYpNum;
    protected boolean n = true;
    protected int o = 0;
    protected int p = 0;
    protected WakeLock q;
    protected boolean r = false;
    protected a s = new a(this);
    protected b t = new b(this);
    protected List<ChatBean> u = new ArrayList();
    protected List<UserBean> v = new ArrayList();
    protected List<GridView> w = new ArrayList();
    protected ChatProcess x;
    protected UserBean y;
    protected int z = 0;

    protected class a extends BaseAdapter {
        final /* synthetic */ ShowLiveActivityBase a;

        protected a(ShowLiveActivityBase showLiveActivityBase) {
            this.a = showLiveActivityBase;
        }

        public int getCount() {
            return this.a.u.size();
        }

        public Object getItem(int i) {
            return this.a.u.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar;
            if (view == null) {
                view = this.a.getLayoutInflater().inflate(R.layout.item_live_chat, null);
                cVar = new c(this.a);
                cVar.a = (TextView) view.findViewById(R.id.tv_chat_1);
                cVar.b = (TextView) view.findViewById(R.id.tv_chat_2);
                view.setTag(cVar);
            } else {
                cVar = (c) view.getTag();
            }
            ChatBean chatBean = (ChatBean) this.a.u.get(i);
            cVar.a.setText(chatBean.getUserNick());
            cVar.b.setText(chatBean.getSendChatMsg());
            return view;
        }
    }

    protected class b extends BaseAdapter {
        final /* synthetic */ ShowLiveActivityBase a;

        protected b(ShowLiveActivityBase showLiveActivityBase) {
            this.a = showLiveActivityBase;
        }

        public int getCount() {
            return this.a.v.size();
        }

        public Object getItem(int i) {
            return this.a.v.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            d dVar;
            if (view == null) {
                view = this.a.getLayoutInflater().inflate(R.layout.item_live_user_list, null);
                dVar = new d(this.a);
                dVar.a = (AvatarView) view.findViewById(R.id.av_userHead);
                view.setTag(dVar);
            } else {
                dVar = (d) view.getTag();
            }
            dVar.a.setAvatarUrl(((UserBean) this.a.v.get(i)).getAvatar());
            return view;
        }
    }

    protected class c {
        TextView a;
        TextView b;
        final /* synthetic */ ShowLiveActivityBase c;

        protected c(ShowLiveActivityBase showLiveActivityBase) {
            this.c = showLiveActivityBase;
        }
    }

    protected class d {
        public AvatarView a;
        final /* synthetic */ ShowLiveActivityBase b;

        protected d(ShowLiveActivityBase showLiveActivityBase) {
            this.b = showLiveActivityBase;
        }
    }

    public abstract void a(UserBean userBean);

    protected void h() {
        String obj = this.mChatInput.getText().toString();
        if (this.r && !obj.equals("")) {
            this.mChatInput.setText("");
            this.x.a(obj, this.y, this.z);
            k();
        }
    }

    protected boolean b() {
        return false;
    }

    protected View a(SendGiftBean sendGiftBean) {
        View inflate = getLayoutInflater().inflate(R.layout.item_show_gift_animator, null);
        if (this.o == 0) {
            this.o = sendGiftBean.getUid();
        } else {
            this.p = sendGiftBean.getUid();
        }
        this.e.put(Integer.valueOf(sendGiftBean.getUid()), inflate);
        return inflate;
    }

    protected boolean d(int i) {
        int i2 = i == 1 ? this.o : this.p;
        SendGiftBean sendGiftBean = (SendGiftBean) this.f.get(Integer.valueOf(i2));
        if (sendGiftBean == null) {
            return true;
        }
        if (System.currentTimeMillis() - sendGiftBean.getSendTime() <= 4000 || this.mShowGiftAnimator == null) {
            return true;
        }
        this.mShowGiftAnimator.removeView((View) this.e.get(Integer.valueOf(i2)));
        this.f.remove(Integer.valueOf(i2));
        if (i == 1) {
            this.o = 0;
        } else {
            this.p = 0;
        }
        this.e.remove(Integer.valueOf(i2));
        if (this.f.size() != 0) {
            for (Entry value : this.f.entrySet()) {
                sendGiftBean = (SendGiftBean) value.getValue();
                if (this.o != sendGiftBean.getUid() && this.p != sendGiftBean.getUid()) {
                    a(a(sendGiftBean), sendGiftBean, 1);
                    break;
                }
            }
        }
        return false;
    }

    protected void i() {
        com.lzfutil.util.u.a().a(new Runnable() {
            final ShowLiveActivityBase a  = ShowLiveActivityBase.this;

            public void run() {
                int[] iArr = new int[]{R.drawable.plane_heart_cyan, R.drawable.plane_heart_pink, R.drawable.plane_heart_red, R.drawable.plane_heart_yellow};
                final Random random = new Random();
                final ImageView imageView = new ImageView(this.a);
                imageView.setBackgroundResource(iArr[random.nextInt(4)]);
                imageView.setLayoutParams(new LayoutParams(com.lzfutil.util.s.a(this.a, 100.0f), com.lzfutil.util.s.a(this.a, 100.0f)));
                final int width = this.a.getWindowManager().getDefaultDisplay().getWidth();
                imageView.setX((float) (width - (width / 3)));
                imageView.setY((float) (this.a.getWindowManager().getDefaultDisplay().getHeight() - 200));
                this.a.runOnUiThread(new Runnable() {

                    public void run() {
                        a.mRoot.addView(imageView);
                        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, "translationX", new float[]{(float) ((random.nextInt(HttpStatus.SC_INTERNAL_SERVER_ERROR) + (width - 200)) - (width / 3))});
                        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, "translationY", new float[]{(float) (random.nextInt(a.getWindowManager().getDefaultDisplay().getHeight() / 2) + HttpStatus.SC_OK)});
                        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageView, "alpha", new float[]{0.0f});
                        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(imageView, "scaleX", new float[]{0.8f, 1.0f});
                        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(imageView, "scaleY", new float[]{0.8f, 1.0f});
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5});
                        animatorSet.setDuration(5000);
                        animatorSet.addListener(new AnimatorListener() {
                            public void onAnimationStart(Animator animator) {
                            }

                            public void onAnimationEnd(Animator animator) {
                                if (a.mRoot != null) {
                                    a.mRoot.removeView(imageView);
                                }
                            }

                            public void onAnimationCancel(Animator animator) {
                            }

                            public void onAnimationRepeat(Animator animator) {
                            }
                        });
                        animatorSet.start();
                    }
                });
            }
        });
    }

    protected void b(SendGiftBean sendGiftBean) {
        if (this.n) {
            this.n = false;
            final int[] iArr = new int[]{R.color.red, R.color.yellow, R.color.blue, R.color.lite_blue, R.color.orange, R.color.pink};
            final View inflate = getLayoutInflater().inflate(R.layout.view_live_gift_animation_plain, null);
            this.mRoot.addView(inflate);
            final RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.rl_animation_flower);
            ((AnimationDrawable) ((ImageView) inflate.findViewById(R.id.iv_animation_plain)).getBackground()).start();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(inflate, "translationX", new float[]{(float) getWindowManager().getDefaultDisplay().getWidth(), 0.0f});
            ofFloat.setDuration(3000);
            ofFloat.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    Random random = new Random();
                    int nextInt = random.nextInt(50) + 10;
                    int width = relativeLayout.getWidth();
                    int height = relativeLayout.getHeight();
                    for (int i = 0; i < nextInt; i++) {
                        int nextInt2 = random.nextInt(5);
                        int nextInt3 = random.nextInt(50);
                        int nextInt4 = random.nextInt(width);
                        int nextInt5 = random.nextInt(height);
                        TextView textView = new TextView(ShowLiveActivityBase.this);
                        textView.setX((float) nextInt3);
                        textView.setText("\u2740");
                        textView.setTextColor(ShowLiveActivityBase.this.getResources().getColor(iArr[nextInt2]));
                        textView.setTextSize(50.0f);
                        relativeLayout.addView(textView);
                        textView.animate().alpha(0.0f)
                                .translationX((float) nextInt4).translationY((float) nextInt5)
                                .setDuration(5000).start();
                    }
                    ShowLiveActivityBase.this.B.postDelayed(new Runnable() {

                        public void run() {
                            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(inflate, "translationX", new float[]{(float) (-inflate.getWidth())});
                            ofFloat.setDuration(2000);
                            ofFloat.addListener(new AnimatorListener() {
                                public void onAnimationStart(Animator animator) {
                                }

                                public void onAnimationEnd(Animator animator) {
                                    if (inflate != null) {
                                        if (ShowLiveActivityBase.this.mRoot != null) {
                                            ShowLiveActivityBase.this.mRoot.removeView(inflate);
                                        }
                                        ShowLiveActivityBase.this.j.remove(0);
                                        ShowLiveActivityBase.this.n = true;
                                        if (ShowLiveActivityBase.this.j.size() > 0) {
                                            ShowLiveActivityBase.this.b((SendGiftBean) ShowLiveActivityBase.this.j.get(0));
                                        }
                                    }
                                }

                                public void onAnimationCancel(Animator animator) {
                                }

                                public void onAnimationRepeat(Animator animator) {
                                }
                            });
                            ofFloat.start();
                        }
                    }, 4000);
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            });
            ofFloat.start();
        }
    }

    protected void c(SendGiftBean sendGiftBean) {
        if (this.m) {
            this.m = false;
            final View inflate = getLayoutInflater().inflate(R.layout.view_live_gift_animation_car_general, null);
            this.mRoot.addView(inflate);
            final ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_animation_red_car);
            this.A = (AnimationDrawable) imageView.getBackground();
            this.A.start();
            final int width = getWindowManager().getDefaultDisplay().getWidth();
            final int height = getWindowManager().getDefaultDisplay().getHeight();
            final int i = imageView.getLayoutParams().width;
            ObjectAnimator r0 = ObjectAnimator.ofFloat(inflate, "translationX", new float[]{(float) (width + i), (float) ((width / 2) - (i / 2))}).setDuration(1500);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(inflate, "translationY", new float[]{(float) (height >> 2)});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{r0, ofFloat});
            animatorSet.setDuration(1500);
            animatorSet.addListener(new AnimatorListener() {

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    imageView.setBackgroundResource(R.drawable.gift_car_red_animation2);
                    ShowLiveActivityBase.this.A = (AnimationDrawable) imageView.getBackground();
                    ShowLiveActivityBase.this.A.start();
                    ShowLiveActivityBase.this.B.postDelayed(new Runnable() {

                        public void run() {
                            imageView.setBackgroundResource(R.drawable.gift_car_red_animation);
                            ShowLiveActivityBase.this.A = (AnimationDrawable) imageView.getBackground();
                            ShowLiveActivityBase.this.A.start();
                            inflate.animate().translationX((float) (i ^ -1)).withEndAction(new Runnable() {
                                public void run() {
                                    imageView.setBackgroundResource(R.drawable.gift_car_red_animation3);
                                    ShowLiveActivityBase.this.A = (AnimationDrawable) imageView.getBackground();
                                    ShowLiveActivityBase.this.A.start();
                                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(inflate, "translationX", new float[]{(float) width, (float) ((width / 2) - (i / 2))});
                                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(inflate, "translationY", new float[]{(float) (height / 2), (float) (height >> 2)});
                                    AnimatorSet animatorSet = new AnimatorSet();
                                    animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
                                    animatorSet.setDuration(2000);
                                    animatorSet.addListener(new AnimatorListener() {
                                        public void onAnimationStart(Animator animator) {
                                        }

                                        public void onAnimationEnd(Animator animator) {
                                            imageView.setBackgroundResource(R.drawable.gift_car_red_animation4);
                                            ShowLiveActivityBase.this.A = (AnimationDrawable) imageView.getBackground();
                                            ShowLiveActivityBase.this.A.start();
                                            inflate.animate().translationX((float) (-i)).translationY(0.0f).setDuration(1000).withEndAction(new Runnable() {
                                                public void run() {
                                                    if (inflate != null) {
                                                        ShowLiveActivityBase.this.mRoot.removeView(inflate);
                                                        ShowLiveActivityBase.this.i.remove(0);
                                                        ShowLiveActivityBase.this.A = null;
                                                        ShowLiveActivityBase.this.m = true;
                                                        if (ShowLiveActivityBase.this.i.size() > 0) {
                                                            ShowLiveActivityBase.this.c((SendGiftBean) ShowLiveActivityBase.this.i.get(0));
                                                        }
                                                    }
                                                }
                                            }).start();
                                        }

                                        public void onAnimationCancel(Animator animator) {
                                        }

                                        public void onAnimationRepeat(Animator animator) {
                                        }
                                    });
                                    animatorSet.start();
                                }
                            }).setDuration(1000).start();
                        }
                    }, 500);
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            });
            animatorSet.start();
        }
    }

    protected void d(SendGiftBean sendGiftBean) {
        if (this.l) {
            this.l = false;
            final View inflate = getLayoutInflater().inflate(R.layout.view_live_gift_animation_cruises, null);
            final int width = getWindowManager().getDefaultDisplay().getWidth();
            AvatarView avatarView = (AvatarView) inflate.findViewById(R.id.live_cruises_uhead);
            ((TextView) inflate.findViewById(R.id.tv_live_cruises_uname)).setText(sendGiftBean.getNicename());
            avatarView.setAvatarUrl(sendGiftBean.getAvatar());
            this.mRoot.addView(inflate);
            LayoutParams layoutParams = (LayoutParams) inflate.getLayoutParams();
            layoutParams.addRule(12);
            inflate.setLayoutParams(layoutParams);
            final RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.rl_live_cruises);
            relativeLayout.animate().translationX((float) ((width / 2) + (width / 3))).translationY(120.0f).withEndAction(new Runnable() {

                public void run() {
                    ShowLiveActivityBase.this.B.postDelayed(new Runnable() {
                        public void run() {
                            relativeLayout.animate().translationX((float) (width * 2)).translationY(200.0f).setDuration(3000).withEndAction(new Runnable() {
                                public void run() {
                                    if (ShowLiveActivityBase.this.mRoot != null) {
                                        ShowLiveActivityBase.this.mRoot.removeView(inflate);
                                        ShowLiveActivityBase.this.h.remove(0);
                                        ShowLiveActivityBase.this.l = true;
                                        if (ShowLiveActivityBase.this.h.size() > 0) {
                                            ShowLiveActivityBase.this.d((SendGiftBean) ShowLiveActivityBase.this.h.get(0));
                                        }
                                    }
                                }
                            }).start();
                        }
                    }, 2000);
                }
            }).setDuration(3000).start();
            ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_live_water_one2);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat((ImageView) inflate.findViewById(R.id.iv_live_water_one), "translationX", new float[]{-50.0f, 50.0f});
            ofFloat.setDuration(1000);
            ofFloat.setRepeatCount(-1);
            ofFloat.setRepeatMode(2);
            ofFloat.start();
            ofFloat = ObjectAnimator.ofFloat(imageView, "translationX", new float[]{50.0f, -50.0f});
            ofFloat.setDuration(1000);
            ofFloat.setRepeatCount(-1);
            ofFloat.setRepeatMode(2);
            ofFloat.start();
        }
    }

    protected void e(SendGiftBean sendGiftBean) {
        int i = 0;
        if (this.k) {
            this.k = false;
            final ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.gift_fireworks_heart_animation);
            AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
            animationDrawable.setOneShot(true);
            this.mRoot.addView(imageView);
            LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
            layoutParams.width = HttpStatus.SC_BAD_REQUEST;
            layoutParams.height = -2;
            layoutParams.addRule(13);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            imageView.setLayoutParams(layoutParams);
            animationDrawable.start();
            for (int i2 = 0; i2 < animationDrawable.getNumberOfFrames(); i2++) {
                i += animationDrawable.getDuration(i2);
            }
            this.B.postDelayed(new Runnable() {

                public void run() {
                    if (ShowLiveActivityBase.this.mRoot != null) {
                        ShowLiveActivityBase.this.mRoot.removeView(imageView);
                        ShowLiveActivityBase.this.g.remove(0);
                        ShowLiveActivityBase.this.k = true;
                        if (ShowLiveActivityBase.this.g.size() > 0) {
                            ShowLiveActivityBase.this.e((SendGiftBean) ShowLiveActivityBase.this.g.get(0));
                        }
                    }
                }
            }, (long) i);
        }
    }

    protected void a(View view, SendGiftBean sendGiftBean, int i) {
        final AvatarView avatarView = (AvatarView) view.findViewById(R.id.av_gift_icon);
        final StrokeTextView strokeTextView = (StrokeTextView) view.findViewById(R.id.tv_show_gift_num);
        ((AvatarView) view.findViewById(R.id.av_gift_uhead)).setAvatarUrl(sendGiftBean.getAvatar());
        ((TextView) view.findViewById(R.id.tv_gift_uname)).setText(sendGiftBean.getNicename());
        ((TextView) view.findViewById(R.id.tv_gift_gname)).setText(sendGiftBean.getGiftname());
        avatarView.setAvatarUrl(sendGiftBean.getGifticon());
        if (this.mShowGiftAnimator != null) {
            this.mShowGiftAnimator.addView(view);
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", new float[]{-340.0f, 0.0f});
        ofFloat.setDuration(300);
        ofFloat.start();
        final SendGiftBean sendGiftBean2 = sendGiftBean;
        final View view2 = view;
        ofFloat.addListener(new AnimatorListener() {
            int j;

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                j = 1;
                ShowLiveActivityBase.this.a(strokeTextView, sendGiftBean2.getUid());
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(avatarView, "translationX", new float[]{-40.0f, (float) (view2.getRight() - (avatarView.getWidth() * 2))});
                ofFloat.setDuration(500);
                ofFloat.start();
                if (ShowLiveActivityBase.this.o != sendGiftBean2.getUid()) {
                    j = 2;
                }
                if (ShowLiveActivityBase.this.B != null) {
                    ShowLiveActivityBase.this.B.postDelayed(new Runnable() {
                        public void run() {
                            if (!ShowLiveActivityBase.this.d(j)) {
                                ShowLiveActivityBase.this.B.removeCallbacks(this);
                            } else if (ShowLiveActivityBase.this.B != null) {
                                ShowLiveActivityBase.this.B.postDelayed(this, 1000);
                            }
                        }
                    }, 1000);
                }
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    protected void a(TextView textView, int i) {
        textView.setText("X" + ((SendGiftBean) this.f.get(Integer.valueOf(i))).getGiftcount());
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("scaleX", new float[]{1.0f, 0.2f, 1.0f});
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("scaleY", new float[]{1.0f, 0.2f, 1.0f});
        ObjectAnimator.ofPropertyValuesHolder(textView, new PropertyValuesHolder[]{ofFloat, ofFloat2}).setDuration(200).start();
        ((SendGiftBean) this.f.get(Integer.valueOf(i))).setSendTime(System.currentTimeMillis());
    }

    protected void f(SendGiftBean sendGiftBean) {
        View view;
        int i;
        int i2;
        View view2 = (View) this.e.get(Integer.valueOf(sendGiftBean.getUid()));
        sendGiftBean.setSendTime(System.currentTimeMillis());
        int i3;
        if (this.f.get(Integer.valueOf(sendGiftBean.getUid())) == null) {
            this.f.put(Integer.valueOf(sendGiftBean.getUid()), sendGiftBean);
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i4 = sendGiftBean.getGiftid() != ((SendGiftBean) this.f.get(Integer.valueOf(sendGiftBean.getUid()))).getGiftid() ? 1 : 0;
        if (this.e.size() >= 2 || view2 != null) {
            view = view2;
            i = 0;
        } else {
            view = a(sendGiftBean);
            i = 1;
        }
        if (view != null) {
            i2 = 1;
        } else {
            i2 = i;
        }
        if (i4 != 0) {
            ((AvatarView) view.findViewById(R.id.av_gift_icon)).setAvatarUrl(sendGiftBean.getGifticon());
            ((StrokeTextView) view.findViewById(R.id.tv_show_gift_num)).setText("X1");
            ((TextView) view.findViewById(R.id.tv_gift_gname)).setText(sendGiftBean.getGiftname());
            this.f.put(Integer.valueOf(sendGiftBean.getUid()), sendGiftBean);
        }
        if (sendGiftBean.getEvensend().equals("y") && i2 == 0 && i4 == 0) {
            ((SendGiftBean) ShowLiveActivityBase.this.f.get(Integer.valueOf(sendGiftBean.getUid()))).setGiftcount(((SendGiftBean)
                    ShowLiveActivityBase.this.f.get(Integer.valueOf(sendGiftBean.getUid()))).getGiftcount() + 1);
        }
        if (i2 != 0 && i4 != 0) {
            a(view, sendGiftBean, 1);
        } else if (i2 != 0 && i4 == 0) {
            a((StrokeTextView) view.findViewById(R.id.tv_show_gift_num), sendGiftBean.getUid());
        }
    }

    protected void g(SendGiftBean sendGiftBean) {
        if (this.mYpNum != null && !this.mYpNum.getText().toString().equals("") && sendGiftBean != null) {
            this.mYpNum.setText(String.valueOf(Integer.parseInt(this.mYpNum.getText().toString()) + sendGiftBean.getTotalcoin()));
        } else if (this.mYpNum != null && this.mYpNum.getText().toString().equals("")) {
            this.mYpNum.setText(String.valueOf(sendGiftBean.getTotalcoin()));
        }
        if (sendGiftBean.getGiftid() == 22) {
            this.g.add(sendGiftBean);
            e(sendGiftBean);
        } else if (sendGiftBean.getGiftid() == 21) {
            this.h.add(sendGiftBean);
            d(sendGiftBean);
        } else if (sendGiftBean.getGiftid() == 9) {
            this.i.add(sendGiftBean);
            c(sendGiftBean);
        } else if (sendGiftBean.getGiftid() == 19) {
            this.j.add(sendGiftBean);
            b(sendGiftBean);
        } else {
            f(sendGiftBean);
        }
    }

    protected void j() {
        this.mChatInput.setFocusable(true);
        this.mChatInput.setFocusableInTouchMode(true);
        this.mChatInput.requestFocus();
        com.lzfutil.util.i.a((Context) this);
        this.mLiveChatEdit.setVisibility(View.VISIBLE);
        this.mButtonMenu.setVisibility(View.GONE);
    }

    protected void k() {
        if (this.mLiveChatEdit.getVisibility() != View.GONE && com.lzfutil.util.i.b(this)) {
            com.lzfutil.util.i.a((Activity) this);
            this.mButtonMenu.setVisibility(View.VISIBLE);
            this.mLiveChatEdit.setVisibility(View.GONE);
            this.z = 0;
        }
    }

    protected void a(ChatBean chatBean) {
        if (this.u.size() > 30) {
            this.u.remove(0);
        }
        this.u.add(chatBean);
        this.s.notifyDataSetChanged();
        this.mLvChatList.setSelection(this.u.size() - 1);
    }

    protected void d(String str) {
        String a = ce.a.a(str);
        if (a != null) {
            try {
                this.mLiveNum.setText(String.valueOf(ChatProcess.aVal));
                JSONArray jSONArray = new JSONArray(a);
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        this.v.add(this.d.fromJson(jSONArray.getString(i), UserBean.class));
                    }
                    this.t.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
