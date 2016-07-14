package com.weilian.phonelive.ui;

import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import cc.i;
import com.lzfutil.util.m;
import com.lzfutil.util.n;

import com.google.gson.Gson;
import com.hyphenate.util.HanziToPinyin.Token;
import com.ksyun.media.player.IMediaPlayer;
import com.ksyun.media.player.IMediaPlayer.OnBufferingUpdateListener;
import com.ksyun.media.player.IMediaPlayer.OnCompletionListener;
import com.ksyun.media.player.IMediaPlayer.OnErrorListener;
import com.ksyun.media.player.IMediaPlayer.OnInfoListener;
import com.ksyun.media.player.IMediaPlayer.OnPreparedListener;
import com.ksyun.media.player.IMediaPlayer.OnSeekCompleteListener;
import com.ksyun.media.player.IMediaPlayer.OnVideoSizeChangedListener;
import com.ksyun.media.player.KSYMediaPlayer;
import com.ksyun.media.player.KSYMediaPlayer.Builder;
import com.weilian.phonelive.AppConfig;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.ShowLiveActivityBase;
import com.weilian.phonelive.bean.ChatBean;
import com.weilian.phonelive.bean.GiftBean;
import com.weilian.phonelive.bean.SendGiftBean;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.widget.VideoSurfaceView;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import ck.ChatProcess;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoPlayerActivity extends ShowLiveActivityBase {
    public static final String C = "USER_INFO";
    public static final int D = 0;
    public static final int E = 1;
    public static final int F = 2;
    private static final String H = "VideoPlayerActivity";
    public OnInfoListener G = new OnInfoListener() {

        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            Log.d(VideoPlayerActivity.H, "onInfo, what:" + i + ",extra:" + i2);
            return false;
        }
    };
    private Context I;
    private KSYMediaPlayer J;
    private n K;
    private Surface L = null;
    private SurfaceHolder M = null;
    private Handler N;
    private boolean O = false;
    private boolean P = false;
    private long Q = 0;
    private long lR = 0;
    private long S = 0;
    private int T = D;
    private int U = D;
    private List<GiftBean> V = new ArrayList();
    private i W;
    private ViewPager X;
    private String Y;
    private GiftBean Z;
    private Button aa;
    private int ab = 5;
    private RelativeLayout ac;
    private TextView ad;
    private UserBean ae;
    private bo.a af;
    private boolean ag = false;
    private long ah = 0;
    private View ai;
    private OnPreparedListener aj = new OnPreparedListener() {


        public void onPrepared(IMediaPlayer iMediaPlayer) {
            if (ai != null) {
                mRoot.removeView(ai);
                ai = null;
            }
            mLayoutLoading.setVisibility(View.GONE);
            J.start();
        }
    };
    private OnBufferingUpdateListener ak = new OnBufferingUpdateListener() {

        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            long duration = (J.getDuration() * ((long) i)) / 100;
        }
    };
    private OnVideoSizeChangedListener al = new OnVideoSizeChangedListener() {

        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
            if (T > 0 && U > 0) {
                if (i != T || i2 != U) {
                    T = iMediaPlayer.getVideoWidth();
                    U = iMediaPlayer.getVideoHeight();
                    if (mVideoSurfaceView != null) {
                        mVideoSurfaceView.a(T, U);
                        mVideoSurfaceView.requestLayout();
                    }
                }
            }
        }
    };
    private OnSeekCompleteListener am = new OnSeekCompleteListener() {

        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            Log.e(VideoPlayerActivity.H, "onSeekComplete...............");
        }
    };
    private OnCompletionListener an = new OnCompletionListener() {

        public void onCompletion(IMediaPlayer iMediaPlayer) {
            mButtonMenuFrame.setVisibility(View.GONE);
            mLayoutLiveStop.setVisibility(View.VISIBLE);
            v();
            t();
        }
    };
    private OnErrorListener ao = new OnErrorListener() {

        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            switch (i) {
                case VideoPlayerActivity.E /*1*/:
                    Log.e(VideoPlayerActivity.H, "OnErrorListener, Error Unknown:" + i + ",extra:" + i2);
                    break;
                default:
                    Log.e(VideoPlayerActivity.H, "OnErrorListener, Error:" + i + ",extra:" + i2);
                    break;
            }
            return false;
        }
    };
    private OnClickListener ap = new OnClickListener() {

        public void onClick(View view) {
            P = !P;
            if (P) {
                J.pause();
                lR = System.currentTimeMillis();
                return;
            }
            J.start();
            VideoPlayerActivity.b(VideoPlayerActivity.this, System.currentTimeMillis() - lR);
            lR = 0;
        }
    };
    private int aq = D;
    private OnSeekBarChangeListener ar = new OnSeekBarChangeListener() {

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                aq = i;
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            J.seekTo((long) aq);
            e(aq);
        }
    };
    private OnTouchListener as = new OnTouchListener() {

        public boolean onTouch(View view, MotionEvent motionEvent) {
            k();
            return false;
        }
    };
    private final Callback at = new Callback() {

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (J != null) {
                Surface surface = surfaceHolder.getSurface();
                J.setDisplay(surfaceHolder);
                J.setScreenOnWhilePlaying(true);
                if (L != surface) {
                    L = surface;
                    J.setSurface(L);
                }
            }
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            Log.d(VideoPlayerActivity.H, "surfaceDestroyed");
            if (J != null) {
                L = null;
            }
        }
    };
    @BindView(R.id.btn_follow)
    Button mFollowEmcee;
    @BindView(R.id.rl_loading)
    RelativeLayout mLayoutLoading;
    @BindView(R.id.video_view)
    VideoSurfaceView mVideoSurfaceView = null;

    private class chatImpl implements ch.c {

        private chatImpl() {
        }

        public void a(final ChatBean chatBean) {
            runOnUiThread(new Runnable() {

                public void run() {
                    VideoPlayerActivity.this.a(chatBean);
                }
            });
        }

        public void a(final boolean z) {
            runOnUiThread(new Runnable() {

                public void run() {
                    r = true;
                    AppContext.a(VideoPlayerActivity.this, z ? "\u8fde\u63a5\u6210\u529f" : "\u8fde\u63a5\u5931\u8d25");
                }
            });
        }

        public void a(List<UserBean> list, String str) {
            v = list;
            if (mUserList != null) {
                mLiveNum.setText(v.size() + "");
                mYpNum.setText(str);
                mUserList.setAdapter(t);
                mUserList.setOnItemClickListener(new OnItemClickListener() {


                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        if (y.getId() == ((UserBean) v.get(i)).getId()) {
                            cj.b.a(VideoPlayerActivity.this, (UserBean) v.get(i), ae.getId());
                        } else {
                            cj.b.a(VideoPlayerActivity.this, y, (UserBean) v.get(i), ae.getId(),  x);
                        }
                    }
                });
            }
        }

        public void a(final UserBean userBean, final boolean z) {
            runOnUiThread(new Runnable() {

                public void run() {
                    mLiveNum.setText(String.valueOf(ChatProcess.aVal));
                    if (z) {
                        v.add(userBean);
                        t.notifyDataSetChanged();
                        return;
                    }
                    for (int i = 0; i < v.size(); i += 1) {
                        if (userBean.getId() == ((UserBean) v.get(i)).getId()) {
                            v.remove(i);
                            t.notifyDataSetChanged();
                            return;
                        }
                    }
                }
            });
        }

        public void a(final int i) {
            runOnUiThread(new Runnable() {

                public void run() {
                    if (i == VideoPlayerActivity.E) {
                        com.lzfutil.util.e.b(getLayoutInflater(), VideoPlayerActivity.this, "\u76f4\u64ad\u5185\u5bb9\u6d89\u5acc\u8fdd\u89c4", new ch.e() {

                            public void a(View view, Dialog dialog) {
                            }

                            public void b(View view, Dialog dialog) {
                                dialog.dismiss();
                            }
                        });
                    }
                    if (mLayoutLiveStop != null) {
                        mLayoutLiveStop.setVisibility(View.VISIBLE);
                    }
                    t();
                    v();
                }
            });
        }

        public void a(final SendGiftBean sendGiftBean, final ChatBean chatBean) {
            runOnUiThread(new Runnable() {

                public void run() {
                    g(sendGiftBean);
                    a(chatBean);
                }
            });
        }

        public void a(final JSONObject jSONObject, final ChatBean chatBean) {
            runOnUiThread(new Runnable() {

                public void run() {
                    try {
                        if (jSONObject.getInt("touid") == y.getId()) {
                            AppContext.a(VideoPlayerActivity.this, "\u60a8\u5df2\u88ab\u8bbe\u4e3a\u7ba1\u7406\u5458");
                        }
                        a(chatBean);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void a(final ChatBean chatBean, final JSONObject jSONObject) {
            runOnUiThread(new Runnable() {

                public void run() {
                    try {
                        if (jSONObject.getInt("touid") == y.getId()) {
                            AppContext.a(VideoPlayerActivity.this, "\u60a8\u5df2\u88ab\u7981\u8a00");
                            ag = true;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    a(chatBean);
                }
            });
        }

        public void a() {
            i();
        }

        public void a(final String str) {
            runOnUiThread(new Runnable() {

                public void run() {
                    d(str);
                }
            });
        }
    }

    private class b extends Handler {
        VideoPlayerActivity a;
        final /* synthetic */ VideoPlayerActivity b;

        public b(VideoPlayerActivity videoPlayerActivity, VideoPlayerActivity videoPlayerActivity2) {
            this.b = videoPlayerActivity;
            this.a = videoPlayerActivity2;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (this.a != null) {
                        e((int) VideoPlayerActivity.D);
                        return;
                    }
                    return;
                case 1:
                    if (this.a != null) {
                        O = false;
                        return;
                    }
                    return;
                case 2:
                    if (this.a != null && (message.obj instanceof m)) {
                        a((m) message.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    static /* synthetic */ long b(VideoPlayerActivity videoPlayerActivity, long j) {
        long j2 = videoPlayerActivity.S + j;
        videoPlayerActivity.S = j2;
        return j2;
    }

    protected int c() {
        return R.layout.activity_look_live;
    }

    public void initView() {
        l();
        this.mLiveChat.setVisibility(View.VISIBLE);
        this.mLvChatList.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                b((ChatBean) u.get(i));
            }
        });
    }

    private void b(ChatBean chatBean) {
        if (chatBean.getId() == this.y.getId()) {
            cj.b.a((ShowLiveActivityBase) this, (UserBean) chatBean, this.ae.getId());
        } else {
            cj.b.a((ShowLiveActivityBase) this, this.y, (UserBean) chatBean, this.ae.getId(), this.x);
        }
    }

    private void l() {
        this.ai = getLayoutInflater().inflate(R.layout.view_live_loading, null);
        ImageView imageView = (ImageView) this.ai.findViewById(R.id.iv_live_loading_img);
        ImageView imageView2 = (ImageView) this.ai.findViewById(R.id.iv_live_loading_img2);
        this.mRoot.addView(this.ai);
        LayoutParams layoutParams = imageView.getLayoutParams();
        int height = getWindowManager().getDefaultDisplay().getHeight() / F;
        layoutParams.height = height;
        imageView.setLayoutParams(layoutParams);
        layoutParams = imageView2.getLayoutParams();
        layoutParams.height = height;
        imageView2.setLayoutParams(layoutParams);
        imageView2.setX((float) getWindowManager().getDefaultDisplay().getWidth());
        float[] fArr = new float[E];
        fArr[D] = (float) (-getWindowManager().getDefaultDisplay().getWidth());
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, "translationX", fArr);
        ofFloat.setDuration(1500);
        ofFloat.setRepeatMode(E);
        ofFloat.setRepeatCount(-1);
        fArr = new float[E];
        fArr[D] = 0.0f;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView2, "translationX", fArr);
        ofFloat2.setDuration(1500);
        ofFloat2.setRepeatMode(E);
        ofFloat2.setRepeatCount(-1);
        ofFloat.start();
        ofFloat2.start();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            if (this.ah == 0 || System.currentTimeMillis() - this.ah > 500) {
                if (this.ah == 0) {
                    ce.b.a(this.y.getId());
                    this.x.a("\u6211\u70b9\u4eae\u4e86\u2665", this.y, (int) D);
                }
                com.lzfutil.util.t.c("\u901a\u77e5");
                this.ah = System.currentTimeMillis();
                this.x.c();
            } else {
                com.lzfutil.util.t.c("\u672c\u5730");
                i();
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void initData() {
        this.d = new Gson();
        this.I = getApplicationContext();
        Bundle bundleExtra = getIntent().getBundleExtra(C);
        this.y = AppContext.c().g();
        this.ae = (UserBean) bundleExtra.getSerializable(C);
        n();
        this.mEmceeHead.setAvatarUrl(this.ae.getAvatar());
        try {
            this.x = new ChatProcess(AppConfig.CHAT_URL, new chatImpl(), this, this.ae.getId());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            com.lzfutil.util.t.c("connect error");
        }
        r();
    }

    private void m() {
        ce.b.c(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

//            public /* synthetic */ void onResponse(Object obj) {
//                AppConfig((String) obj);
//            }
//
//            public void onError(Call call, Exception exception) {
//            }

            public void a(String str) {
                Y = ce.a.a(str, VideoPlayerActivity.this);
            }
        });
    }

    private void n() {
        this.M = this.mVideoSurfaceView.getHolder();
        this.M.addCallback(this.at);
        this.mVideoSurfaceView.setOnTouchListener(this.as);
        this.mVideoSurfaceView.setKeepScreenOn(true);
        setVolumeControlStream(3);
        this.N = new b(this, this);
        this.K = new n((ActivityManager) getSystemService(ACTIVITY_SERVICE), this.N);
        String str = AppConfig.b + this.ae.getId();
        this.J = new Builder(this.I).build();
        this.J.setOnBufferingUpdateListener(this.ak);
        this.J.setOnCompletionListener(this.an);
        this.J.setOnPreparedListener(this.aj);
        this.J.setOnInfoListener(this.G);
        this.J.setOnVideoSizeChangedListener(this.al);
        this.J.setOnErrorListener(this.ao);
        this.J.setOnSeekCompleteListener(this.am);
        this.J.setScreenOnWhilePlaying(true);
        this.J.setBufferTimeMax(5.0f);
        try {
            this.J.setDataSource(str);
            this.J.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick({ R.id.ll_live_room_info, R.id.ll_yp_labe, R.id.btn_back_index, R.id.rl_loading,
            R.id.btn_follow, R.id.iv_live_chat, R.id.iv_live_privatechat, R.id.iv_live_shar,
            R.id.iv_live_gift, R.id.iv_live_back, R.id.bt_send_chat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_live_room_info:
                cj.b.a((ShowLiveActivityBase) this, this.y, this.ae, this.ae.getId(), this.x);
                return;
            case R.id.ll_yp_labe:
                com.lzfutil.util.w.e(this, this.ae.getId());
                return;
            case R.id.btn_back_index:
                v();
                finish();
                return;
            case R.id.rl_loading:
                k();
                return;
            case R.id.btn_follow:
                a(this.y.getId(), this.ae.getId());
                return;
            case R.id.iv_live_chat:
                if (this.ag) {
                    w();
                    return;
                } else {
                    j();
                    return;
                }
            case R.id.iv_live_privatechat:
                com.lzfutil.util.w.g(this, this.y.getId());
                return;
            case R.id.iv_live_shar:
                a(view);
                return;
            case R.id.iv_live_gift:
                p();
                return;
            case R.id.iv_live_back:
                finish();
                return;
            case R.id.bt_send_chat:
                h();
                return;
            default:
                return;
        }
    }

    public void share(View view) {
        com.lzfutil.util.o.share(this, view.getId(), this.ae);
    }

    private void a(View view) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.pop_view_share, null);
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        inflate.measure(D, D);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        int[] iArr = new int[F];
        view.getLocationOnScreen(iArr);
        popupWindow.showAtLocation(view, D, (iArr[D] + (view.getWidth() / F)) - (inflate.getMeasuredWidth() / F), iArr[E] - inflate.getMeasuredHeight());
    }

    private void a(int i, int i2) {
        ce.b.b(i, i2, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                
            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }

            public void a(String str) {
                ce.a.a(str, VideoPlayerActivity.this);
                if (mFollowEmcee.getText().toString().equals(getResources().getString(R.string.follow))) {
                    mFollowEmcee.setText(getResources().getString(R.string.alreadyfollow));
                } else {
                    mFollowEmcee.setText(getResources().getString(R.string.follow));
                }
            }
        });
    }

    private void o() {
        ((TextView) this.ac.findViewById(R.id.tv_show_gift_outtime)).setText("");
        this.ac.setVisibility(View.GONE);
        this.aa.setVisibility(View.VISIBLE);
        this.ab = 5;
    }

    private void p() {
        if (this.mYpNum != null) {
            this.af = new bo.a((Context) this, (int) R.style.BottomViewTheme_Transparent, (int) R.layout.view_show_viewpager);
            this.af.a((int) R.style.BottomToTopAnim);
            this.af.a(true);
            View b = this.af.b();
            this.ad = (TextView) b.findViewById(R.id.tv_show_select_user_coin);
            this.ad.setText(this.y.getCoin());
            this.X = (ViewPager) b.findViewById(R.id.vp_gift_page);
            this.ac = (RelativeLayout) b.findViewById(R.id.iv_show_send_gift_lian);
            this.ac.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    e("y");
                    ab = 5;
                    ((TextView) ac.findViewById(R.id.tv_show_gift_outtime)).setText(String.valueOf(ab));
                }
            });
            this.aa = (Button) b.findViewById(R.id.btn_show_send_gift);
            this.aa.setOnClickListener(new OnClickListener() {

                public void onClick(View view) {
                    b(view);
                }
            });
            if (this.w != null) {
                q();
            }
        }
    }

    private void b(View view) {
        if (!this.r) {
            return;
        }
        if (this.Z == null || this.Z.getType() != E) {
            e("EVENT_TRANSPORT");
            return;
        }
        view.setVisibility(View.GONE);
        this.N.postDelayed(new Runnable() {

            public void run() {
                if (ab == VideoPlayerActivity.E) {
                    o();
                    N.removeCallbacks(this);
                    return;
                }
                N.postDelayed(this, 1000);
                ab = ab - 1;
                ((TextView) ac.findViewById(R.id.tv_show_gift_outtime)).setText(String.valueOf(ab));
            }
        }, 1000);
        e("y");
    }

    private void q() {
        if (this.W == null && this.Y != null) {
            int i;
            if (this.V.size() == 0) {
                try {
                    JSONArray jSONArray = new JSONArray(this.Y);
                    for (i = D; i < jSONArray.length(); i += E) {
                        this.V.add(this.d.fromJson(jSONArray.getString(i), GiftBean.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            List arrayList = new ArrayList();
            int i2 = D;
            i = D;
            while (i2 < 3) {
                View inflate = getLayoutInflater().inflate(R.layout.view_show_gifts_gv, null);
                arrayList.add(inflate);
                List arrayList2 = new ArrayList();
                int i3 = i;
                for (i = D; i < 8 && i3 < this.V.size(); i += E) {
                    arrayList2.add(this.V.get(i3));
                    i3 += E;
                }
                this.w.add((GridView) inflate.findViewById(R.id.gv_gift_list));
                ((GridView) this.w.get(i2)).setAdapter(new cc.b(arrayList2));
                ((GridView) this.w.get(i2)).setOnItemClickListener(new OnItemClickListener() {

                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        a(adapterView, view, i);
                    }
                });
                i2 += E;
                i = i3;
            }
            this.W = new i(arrayList);
        }
        this.X.setAdapter(this.W);
    }

    private void a(AdapterView<?> adapterView, View view, int i) {
        if (((GiftBean) adapterView.getItemAtPosition(i)) != this.Z) {
            o();
            this.Z = (GiftBean) adapterView.getItemAtPosition(i);
            a(true);
            for (int i2 = D; i2 < this.w.size(); i2 += E) {
                for (int i3 = D; i3 < ((GridView) this.w.get(i2)).getChildCount(); i3 += E) {
                    if (((GiftBean) ((GridView) this.w.get(i2)).getItemAtPosition(i3)).getType() == E) {
                        ((GridView) this.w.get(i2)).getChildAt(i3).findViewById(R.id.iv_show_gift_selected).setBackgroundResource(R.drawable.icon_continue_gift);
                    } else {
                        ((GridView) this.w.get(i2)).getChildAt(i3).findViewById(R.id.iv_show_gift_selected).setBackgroundResource(D);
                    }
                }
            }
            view.findViewById(R.id.iv_show_gift_selected).setBackgroundResource(R.drawable.icon_continue_gift_chosen);
            return;
        }
        if (((GiftBean) adapterView.getItemAtPosition(i)).getType() == E) {
            view.findViewById(R.id.iv_show_gift_selected).setBackgroundResource(R.drawable.icon_continue_gift);
        } else {
            view.findViewById(R.id.iv_show_gift_selected).setBackgroundResource(D);
        }
        this.Z = null;
        a(false);
    }

    private void a(boolean z) {
        if (z) {
            this.aa.setBackgroundColor(getResources().getColor(R.color.global));
            this.aa.setEnabled(true);
            return;
        }
        this.aa.setBackgroundColor(getResources().getColor(R.color.light_gray2));
        this.aa.setEnabled(false);
    }

    private void e(final String str) {
        if (this.Z != null) {
            if (this.Z.getType() == E) {
                this.ac.setVisibility(View.VISIBLE);
            } else {
                a(true);
            }
            ce.b.a(this.y, this.Z, this.ae.getId(), new StringCallback() {

                public /* synthetic */ void onResponse(String obj, int code) {
                    a((String) obj);
                }

                public void onError(Call call, Exception exception, int code) {
                    AppContext.a(VideoPlayerActivity.this, getString(R.string.senderror));
                }

                public void a(String str) {
                    String a = ce.a.a(str, VideoPlayerActivity.this);
                    if (a != null) {
                        try {
                            ((TextView) ac.findViewById(R.id.tv_show_gift_outtime)).setText(String.valueOf(ab));
                            JSONObject jSONObject = new JSONObject(a);
                            y.setCoin(String.valueOf(Integer.parseInt(ad.getText().toString()) - Z.getNeedcoin()));
                            ad.setText(y.getCoin());
                            x.a(jSONObject.getString("gifttoken"), y, str);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    private void r() {
        ce.b.a(AppContext.c().i(), this.ae.getId(), AppContext.c().j(), AppContext.a, new StringCallback() {

            public /* synthetic */ void onResponse(String obj, int code) {
                a((String) obj);
            }

            public void onError(Call call, Exception exception, int code) {
                AppContext.a(VideoPlayerActivity.this, getString(R.string.initDataError));
            }

            public void a(String str) {
                String a = ce.a.a(str, VideoPlayerActivity.this);
                if (a != null) {
                    UserBean userBean = (UserBean) d.fromJson(a, UserBean.class);
                    y.setCoin(userBean.getCoin());
                    y.setLevel(userBean.getLevel());
                    x.a(a, AppContext.c().j(), ae.getId());
                    s();
                }
            }
        });
        m();
    }

    private void s() {
        this.mLvChatList.setAdapter(this.s);
    }

    private void t() {
        ce.b.a(this.y.getId(), this.ae.getId(), new StringCallback() {

            public /* synthetic */ void onResponse(String obj, int code) {
                a((String) obj);
            }

            public void onError(Call call, Exception exception, int code) {
            }

            public void a(String str) {
                String a = ce.a.a(str, VideoPlayerActivity.this);
                if (a == null) {
                    return;
                }
                if (a.equals("0")) {
                    mFollowEmcee.setText(getString(R.string.follow));
                } else {
                    mFollowEmcee.setText(getString(R.string.alreadyfollow));
                }
            }
        });
    }

    public void a(UserBean userBean) {
        if (this.ag) {
            w();
            return;
        }
        this.z = userBean.getId();
        this.mChatInput.setText("@" + userBean.getUser_nicename() + Token.SEPARATOR);
        j();
    }

    protected void onDestroy() {
        v();
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        if (this.J != null) {
            this.J.pause();
            this.P = true;
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.J != null) {
            this.J.start();
            this.P = false;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            v();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    private void u() {
        if (this.J != null && this.J.getVideoHeight() > 0 && this.mVideoSurfaceView != null) {
            WindowManager windowManager = getWindowManager();
            int width = windowManager.getDefaultDisplay().getWidth();
            int height = windowManager.getDefaultDisplay().getHeight();
            int i = this.T;
            int i2 = this.U;
            if (getResources().getConfiguration().orientation == E) {
                if (width <= height) {
                    height = width;
                }
                width = height;
                height = (int) Math.ceil((double) ((height * i2) / i));
            } else if (getResources().getConfiguration().orientation != F) {
                height = D;
                width = D;
            } else if (i2 * width > i * height) {
                width = (int) Math.ceil((double) ((i * height) / i2));
            } else {
                height = (int) Math.ceil((double) ((width * i2) / i));
            }
            LayoutParams layoutParams = this.mVideoSurfaceView.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = height;
            this.mVideoSurfaceView.setLayoutParams(layoutParams);
            this.mVideoSurfaceView.invalidate();
        }
    }

    public int e(int i) {
        if (this.J == null) {
            return -1;
        }
        long currentPosition = i > 0 ? (long) i : this.J.getCurrentPosition();
        long duration = this.J.getDuration();
        if (currentPosition >= 0) {
            new StringBuilder().append(com.lzfutil.util.r.a(currentPosition)).append("/").append(com.lzfutil.util.r.a(duration)).toString();

           // socket.Editor(currentPosition) + "/" + socket.Editor(duration);
        }
        Message message = new Message();
        message.what = D;
        if (this.N != null) {
            this.N.sendMessageDelayed(message, 1000);
        }
        return (int) currentPosition;
    }

    private void a(m mVar) {
        if (this.J != null) {
            long decodedDataSize = (8 * this.J.getDecodedDataSize()) / (this.P ? (this.lR - this.S) - this.Q : (System.currentTimeMillis() - this.S) - this.Q);
        }
    }

    private void v() {
        this.mButtonMenuFrame.setVisibility(View.GONE);
        this.mLvChatList.setVisibility(View.GONE);
        this.x.d();
        if (this.J != null) {
            this.J.release();
            this.J = null;
        }
        if (this.N != null) {
            this.N.removeCallbacksAndMessages(null);
        }
        if (this.K != null) {
            this.K.a();
            this.K = null;
        }
    }

    private void w() {
        if (this.ag) {
            ce.b.i(this.y.getId(), this.ae.getId(), new StringCallback() {

                public /* synthetic */ void onResponse(String obj, int code) {
                    a((String) obj);
                }

                public void onError(Call call, Exception exception, int code) {
                }

                public void a(String str) {
                    String a = ce.a.a(str, VideoPlayerActivity.this);
                    if (a != null && Integer.parseInt(a) == 0) {
                        ag = false;
                        j();
                    }
                }
            });
        }
    }
}
