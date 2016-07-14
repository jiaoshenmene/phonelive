package com.weilian.phonelive.ui;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;

import com.hyphenate.util.HanziToPinyin.Token;
import com.ksy.recordlib.service.core.KSYStreamer;
import com.ksy.recordlib.service.core.KSYStreamerConfig.Builder;
import com.ksy.recordlib.service.core.KSYStreamerConfig.ENCODE_METHOD;
import com.ksy.recordlib.service.streamer.OnStatusListener;
import com.ksy.recordlib.service.streamer.RecorderConstants;
import com.ksy.recordlib.service.util.audio.OnProgressListener;
import com.ksy.recordlib.service.view.CameraGLSurfaceView;
import com.ksyun.media.player.KSYMediaCodecInfo;
import com.ksyun.media.player.stats.StatConstant;
import com.weilian.phonelive.AppConfig;
import com.weilian.phonelive.AppContext;
import com.weilian.phonelive.R;
import com.weilian.phonelive.base.ShowLiveActivityBase;
import com.weilian.phonelive.bean.ChatBean;
import com.weilian.phonelive.bean.SendGiftBean;
import com.weilian.phonelive.bean.UserBean;
import com.weilian.phonelive.widget.music.DefaultLrcBuilder;
import com.weilian.phonelive.widget.music.LrcView;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ck.ChatProcess;
import okhttp3.Call;
import org.json.JSONException;
import org.json.JSONObject;

public class StartLiveActivity extends ShowLiveActivityBase {
    private static final String I = "StartLiveActivity";
    public OnProgressListener C = new OnProgressListener() {

        public void onMusicProgress(long j, long j2) {
        }

        public void onMusicStopped() {
        }

        public void onMusicError(int i) {
        }
    };
    public OnStatusListener D = new OnStatusListener() {

        public void onStatus(int i, int i2, int i3, String str) {
            switch (i) {
                case -1008:
                case 3002:
                case 3003:
                    return;
                case -1003:
                    Log.e(StartLiveActivity.I, "---------KSYVIDEO_ENCODED_FRAMES_FAILED");
                    return;
                case -1002:
                    Log.d(StartLiveActivity.I, "KSYVIDEO_ENCODED_FRAME_THRESHOLD");
                    B.obtainMessage(i, "KSYVIDEO_ENCODED_FRAME_THRESHOLD").sendToTarget();
                    return;
                case -1001:
                    Log.d(StartLiveActivity.I, "KSYVIDEO_AUTH_ERROR");
                    return;
                case 0:
                    Log.d("TAG", "KSYVIDEO_OPEN_STREAM_SUCC");
                    B.obtainMessage(i, "start stream succ").sendToTarget();
                    return;
                case 1000:
                    B.obtainMessage(i, "init done").sendToTarget();
                    return;
                case 3001:
                    if (B != null) {
                        B.obtainMessage(i, "\u7f51\u592a\u6413~").sendToTarget();
                        return;
                    }
                    return;
                default:
                    if (str != null) {
                    }
                    if (B != null) {
                        B.obtainMessage(i, str).sendToTarget();
                        return;
                    }
                    return;
            }
        }
    };
    private Timer E;
    private TimerTask F;
    private boolean G = true;
    private KSYStreamer H;
    private boolean J = false;
    private int K = 1;
    private MediaPlayer L;
    private int M = 1000;
    @BindView(R.id.camera_preview)
    CameraGLSurfaceView mCameraPreview;
    @BindView(R.id.tv_live_end_num)
    TextView mLiveEndNum;
    @BindView(R.id.lcv_live_start)
    LrcView mLrcView;
    @BindView(R.id.btn_start_live)
    Button mStartLive;
    @BindView(R.id.rl_start_live_bg)
    RelativeLayout mStartLiveBg;
    @BindView(R.id.et_start_live_title)
    EditText mStartLiveTitle;
    @BindView(R.id.rl_live_music)
    LinearLayout mViewShowLiveMusicLrc;

    private class aList implements ch.c {
        final /* synthetic */ StartLiveActivity aIns;

        private aList(StartLiveActivity startLiveActivity) {
            this.aIns = startLiveActivity;
        }

        public void a(final ChatBean chatBean) {
            this.aIns.runOnUiThread(new Runnable() {

                public void run() {
                    aIns.a(chatBean);
                }
            });
        }

        public void a(final boolean z) {
            this.aIns.runOnUiThread(new Runnable() {

                public void run() {
                    aIns.r = true;
                    AppContext.a(aIns, z ? "\u8fde\u63a5\u6210\u529f" : "\u8fde\u63a5\u5931\u8d25");
                }
            });
        }

        public void a(List<UserBean> list, String str) {
            this.aIns.v = list;
            if (this.aIns.mUserList != null) {
                this.aIns.mLiveNum.setText(this.aIns.v.size() + "");
                this.aIns.mYpNum.setText(str);
                this.aIns.mUserList.setAdapter(this.aIns.t);
                this.aIns.mUserList.setOnItemClickListener(new OnItemClickListener() {

                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        if (y.getId() == ((UserBean) v.get(i)).getId()) {
                            cj.b.a(StartLiveActivity.this, (UserBean) v.get(i));
                        } else {
                            cj.b.a(StartLiveActivity.this, (UserBean) v.get(i), y, x);
                        }
                    }
                });
            }
        }

        public void a(final UserBean userBean, final boolean z) {
            this.aIns.runOnUiThread(new Runnable() {

                public void run() {
                    aIns.mLiveNum.setText(String.valueOf(ChatProcess.aVal));
                    if (z) {
                        aIns.v.add(userBean);
                        aIns.t.notifyDataSetChanged();
                        return;
                    }
                    for (int i = 0; i < aIns.v.size(); i++) {
                        if (userBean.getId() == ((UserBean) aIns.v.get(i)).getId()) {
                            aIns.v.remove(i);
                            aIns.t.notifyDataSetChanged();
                            return;
                        }
                    }
                }
            });
        }

        public void a(final int i) {
            this.aIns.runOnUiThread(new Runnable() {

                public void run() {
                    if (i == 1) {
                        com.lzfutil.util.e.b(getLayoutInflater(), aIns, "\u76f4\u64ad\u5185\u5bb9\u6d89\u5acc\u8fdd\u89c4", new ch.e() {

                            public void a(View view, Dialog dialog) {
                            }

                            public void b(View view, Dialog dialog) {
                                dialog.dismiss();
                            }
                        });
                        u();
                    }
                }
            });
        }

        public void a(final SendGiftBean sendGiftBean, final ChatBean chatBean) {
            this.aIns.runOnUiThread(new Runnable() {

                public void run() {
                    g(sendGiftBean);
                    a(chatBean);
                }
            });
        }

        public void a(final JSONObject jSONObject, final ChatBean chatBean) {
            this.aIns.runOnUiThread(new Runnable() {

                public void run() {
                    try {
                        if (jSONObject.getInt("touid") == y.getId()) {
                            AppContext.a(aIns, "\u60a8\u5df2\u88ab\u8bbe\u4e3a\u7ba1\u7406\u5458");
                        }
                        a(chatBean);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void a(final ChatBean chatBean, JSONObject jSONObject) {
            this.aIns.runOnUiThread(new Runnable() {

                public void run() {
                    a(chatBean);
                }
            });
        }

        public void a() {
            this.aIns.i();
        }

        public void a(final String str) {
            this.aIns.runOnUiThread(new Runnable() {

                public void run() {
                    d(str);
                }
            });
        }
    }

    class b extends TimerTask {
        long a = -1;
        final /* synthetic */ StartLiveActivity b;

        b(StartLiveActivity startLiveActivity) {
            this.b = startLiveActivity;
        }

        public void run() {
            if (this.a == -1) {
                this.a = System.currentTimeMillis();
            }
            if (this.b.L != null) {
                final long currentPosition = (long) this.b.L.getCurrentPosition();
                this.b.runOnUiThread(new Runnable() {

                    public void run() {
                        mLrcView.a(currentPosition);
                    }
                });
            }
        }
    }

    protected int c() {
        return R.layout.activity_live_show;
    }

    public void initView() {
        this.mButtonMenu.setVisibility(View.GONE);
        this.mLvChatList.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                b((ChatBean) u.get(i));
            }
        });
        ((ImageView) findViewById(R.id.iv_live_share_weibo)).setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                int i = 0;
                a(view, 0);
                StartLiveActivity startLiveActivity = StartLiveActivity.this;
                if (K == 0) {
                    i = 7;
                }
                startLiveActivity.K = i;
            }
        });
        findViewById(R.id.iv_live_share_timeline).setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                int i = 1;
                a(view, 1);
                StartLiveActivity startLiveActivity = StartLiveActivity.this;
                if (1 == K) {
                    i = 7;
                }
                startLiveActivity.K = i;
            }
        });
        findViewById(R.id.iv_live_share_wechat).setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                int i = 2;
                a(view, 2);
                StartLiveActivity startLiveActivity = StartLiveActivity.this;
                if (2 == K) {
                    i = 7;
                }
                startLiveActivity.K = i;
            }
        });
        findViewById(R.id.iv_live_share_qq).setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                int i = 3;
                a(view, 3);
                StartLiveActivity startLiveActivity = StartLiveActivity.this;
                if (3 == K) {
                    i = 7;
                }
                startLiveActivity.K = i;
            }
        });
        findViewById(R.id.iv_live_share_qqzone).setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                int i = 4;
                a(view, 4);
                StartLiveActivity startLiveActivity = StartLiveActivity.this;
                if (4 == K) {
                    i = 7;
                }
                startLiveActivity.K = i;
            }
        });
    }

    private void b(ChatBean chatBean) {
        if (chatBean.getId() == this.y.getId()) {
            cj.b.a(this, chatBean);
        } else {
            cj.b.a(this, chatBean, this.y, this.x);
        }
    }

    private void a(View view, int i) {
        CharSequence charSequence = null;
        String str = "";
        if (i == this.K) {
            charSequence = getResources().getStringArray(R.array.live_start_share_close)[i];
        } else {
            charSequence = getResources().getStringArray(R.array.live_start_share_open)[i];
        }
        View inflate = getLayoutInflater().inflate(R.layout.pop_view_share_start_live, null);
        ((TextView) inflate.findViewById(R.id.tv_pop_share_start_live_prompt)).setText(charSequence);
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        inflate.measure(0, 0);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        popupWindow.setFocusable(false);
        popupWindow.showAtLocation(view, 0, (iArr[0] + (view.getWidth() / 2)) - (inflate.getMeasuredWidth() / 2), iArr[1] - inflate.getMeasuredHeight());
    }

    private void e(final int i) {
        final TextView textView = new TextView(this);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setText(i + "");
        textView.setTextSize(30.0f);
        this.mRoot.addView(textView);
        LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
        layoutParams.addRule(13);
        textView.setLayoutParams(layoutParams);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, "scaleX", new float[]{5.0f, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(textView, "scaleY", new float[]{5.0f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.addListener(new AnimatorListener() {

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                int i = 1;
                mRoot.removeView(textView);
                if (i == 1) {
                    H.startStream();
                    H.setEnableReverb(true);
                    return;
                }
                StartLiveActivity startLiveActivity = StartLiveActivity.this;
                if (i == 3) {
                    i = 2;
                }
                startLiveActivity.e(i);
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    public void initData() {
        this.q = ((PowerManager) getSystemService(Service.POWER_SERVICE)).newWakeLock(10, "MyTag");
        this.y = AppContext.c().g();
        o();
        n();
        m();
    }

    public String e(String str) {
        try {
            InputStream fileInputStream = new FileInputStream(str);
            Reader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str2 = "";
            str2 = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    fileInputStream.close();
                    inputStreamReader.close();
                    bufferedReader.close();
                    return str2;
                } else if (!readLine.trim().equals("")) {
                    str2 = str2 + readLine + "\r\n";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void m() {
        com.lzfutil.util.u.a().a(new Runnable() {

            public void run() {
                B.postDelayed(new Runnable() {

                    public void run() {
                        if (ChatProcess.aVal > 36) {
                            B.removeCallbacks(this);
                            return;
                        }
                        B.postDelayed(this, 2000);
                        x.b();
                    }
                }, 2000);
            }
        });
    }

    private void n() {
        try {
            this.x = new ChatProcess(AppConfig.CHAT_URL, new aList(StartLiveActivity.this), this, this.y.getId());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void o() {
        Builder builder = new Builder();
        builder.setmUrl(AppConfig.b + this.y.getId());
        builder.setFrameRate(15);
        builder.setMaxAverageVideoBitrate(KSYMediaCodecInfo.RANK_TESTED);
        builder.setMinAverageVideoBitrate(RecorderConstants.TARGET_VIDEO_WIDTH);
        builder.setInitAverageVideoBitrate(RecorderConstants.RESOLUTION_LOW_WIDTH);
        builder.setAudioBitrate(48);
        builder.setVideoResolution(1);
        builder.setEncodeMethod(ENCODE_METHOD.SOFTWARE);
        builder.setSampleAudioRateInHz(RecorderConstants.DEFAULT_SAMPLE_RATE);
//        builder.setMuteAudio(false);
        builder.setFrontCameraMirror(false);
        String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
        String a = com.lzfutil.util.k.a("s6539d4f91a16ed6ba27db3ea863b943" + valueOf);
//        builder.setAppId("QYA0ABFDF283A98F4837");
//        builder.setAccessKey("a7164872c44510ae32ffbe7c3b589e35");
//        builder.setSecretKeySign(AppConfig);
//        builder.setTimeSecond(valueOf);
        builder.setDefaultLandscape(false);
        this.H = new KSYStreamer(this);
        this.H.setConfig(builder.build());
        this.H.setDisplayPreview(this.mCameraPreview);
        this.H.setOnStatusListener(this.D);
        this.H.enableDebugLog(false);
        this.mEmceeHead.setAvatarUrl(this.y.getAvatar());
        this.H.switchCamera();
    }

    @OnClick({R.id.camera_preview,R.id.ll_yp_labe,R.id.iv_live_exit,R.id.btn_start_live,R.id.btn_back_index,R.id.btn_live_end_music,R.id.iv_live_chat,R.id.iv_live_privatechat,R.id.iv_live_back,R.id.bt_send_chat,R.id.iv_live_music,R.id.iv_live_meiyan,R.id.iv_live_switch_camera})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_preview:
                k();
                return;
            case R.id.ll_yp_labe:
                com.lzfutil.util.w.e(this, this.y.getId());
                return;
            case R.id.iv_live_exit:
                finish();
                return;
            case R.id.btn_start_live:
                p();
                return;
            case R.id.btn_back_index:
                finish();
                return;
            case R.id.btn_live_end_music:
                s();
                return;
            case R.id.iv_live_chat:
                j();
                return;
            case R.id.iv_live_privatechat:
                com.lzfutil.util.w.g(this, this.y.getId());
                return;
            case R.id.iv_live_back:
                t();
                return;
            case R.id.bt_send_chat:
                h();
                return;
            case R.id.iv_live_music:
                com.lzfutil.util.w.a((Activity) this);
                return;
            case R.id.iv_live_meiyan:
                if (this.J) {
                    this.J = false;
                    this.H.setBeautyFilter(0);
                    return;
                }
                this.J = true;
                this.H.setBeautyFilter(19);
                return;
            case R.id.iv_live_switch_camera:
                this.H.switchCamera();
                return;
            default:
                return;
        }
    }

    private void p() {
        q();
        com.lzfutil.util.i.a((Activity) this);
        this.mStartLive.setEnabled(false);
        this.mStartLive.setTextColor(getResources().getColor(R.color.white));
    }

    private void q() {
        ce.b.a(this.y.getId(), this.mStartLiveTitle.getText().toString(), new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

                AppContext.a(StartLiveActivity.this, "\u5f00\u542f\u76f4\u64ad\u5931\u8d25,\u8bf7\u9000\u51fa\u91cd\u8bd5- -!");
            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }


            public void a(String str) {
                if (ce.a.a(str, StartLiveActivity.this) != null) {
                    mStartLiveBg.setVisibility(View.GONE);
                    mButtonMenu.setVisibility(View.VISIBLE);
                    v();
                }
            }
        }, this.y.getToken());
    }

    public void a(UserBean userBean) {
        this.z = userBean.getId();
        this.mChatInput.setText("@" + userBean.getUser_nicename() + Token.SEPARATOR);
        j();
    }

    private void r() {
        this.mLvChatList.setAdapter(this.s);
    }

    public void onResume() {
        super.onResume();
        this.q.acquire();
        this.H.onResume();
    }

    public void onPause() {
        super.onPause();
        this.q.release();
        this.H.onPause();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i2) {
            case 1:
                a(intent);
                break;
        }
        super.onActivityResult(i, i2, intent);
    }

    private void a(Intent intent) {
        if (this.L != null) {
            this.L.stop();
        }
        this.H.stopMixMusic();
        this.mViewShowLiveMusicLrc.setVisibility(View.VISIBLE);
        String stringExtra = intent.getStringExtra("filepath");
//        this.H.startMixMusic(stringExtra, this.C, true);
        this.H.startMixMusic(stringExtra,  true);
        this.mLrcView.setLrc(new DefaultLrcBuilder().getLrcRows(e(stringExtra.substring(0, stringExtra.length() - 3) + "lrc")));
        this.L = new MediaPlayer();
        try {
            this.L.setDataSource(stringExtra);
            this.L.setLooping(true);
            this.L.setOnPreparedListener(new OnPreparedListener() {

                public void onPrepared(MediaPlayer mediaPlayer) {
                    Log.d(StartLiveActivity.I, StatConstant.BODY_TYPE_ONPREPARED);
                    mediaPlayer.start();
                    if (E == null) {
                        E = new Timer();
                        F = new b(StartLiveActivity.this);
                        E.scheduleAtFixedRate(F, 0, (long) M);
                    }
                }
            });
            this.L.setOnCompletionListener(new OnCompletionListener() {

                public void onCompletion(MediaPlayer mediaPlayer) {
                    l();
                }
            });
            this.L.prepare();
            this.L.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public void l() {
        if (this.E != null) {
            this.E.cancel();
            this.E = null;
        }
    }

    private void s() {
        if (this.L != null && this.H != null) {
            this.L.stop();
            this.L = null;
            this.H.stopMixMusic();
            this.mViewShowLiveMusicLrc.setVisibility(View.GONE);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (!this.G || this.mStartLiveBg.getVisibility() + 7 == 7) {
            return super.onKeyDown(i, keyEvent);
        }
        t();
        return true;
    }

    private void t() {
        com.lzfutil.util.e.aInflate(getLayoutInflater(), (Context) this, getString(R.string.iscloselive), new ch.e() {

            public void a(View view, Dialog dialog) {
                dialog.dismiss();
            }

            public void b(View view, Dialog dialog) {
                dialog.dismiss();
                u();
            }
        });
    }

    private void u() {
        this.G = false;
        this.mLvChatList.setVisibility(View.GONE);
        ce.b.c(this.y.getId(), new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                a(response);
            }


            public void a(String str) {
                x.a();
            }
        });
        this.H.stopStream();
        this.H.stopMixMusic();
        if (this.L != null) {
            this.L.stop();
        }
        if (this.B != null) {
            this.B.removeCallbacksAndMessages(null);
            this.B = null;
        }
        this.mLayoutLiveStop.setVisibility(View.VISIBLE);
        this.mLiveChatEdit.setVisibility(View.VISIBLE);
        this.mLiveEndNum.setText(this.v.size() + "\u4eba\u89c2\u770b");
        this.mButtonMenuFrame.setVisibility(View.GONE);
    }

    private void v() {
        e(3);
        this.x.a(this.y, this.y.getId());
        r();
    }

    protected void onDestroy() {
        this.x.d();
        this.H.stopStream();
        this.H.stopMixMusic();
        l();
        this.H.onDestroy();
        super.onDestroy();
    }
}
