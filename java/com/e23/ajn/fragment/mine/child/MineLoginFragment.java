package com.e23.ajn.fragment.mine.child;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.b.e;
import com.e23.ajn.b.h;
import com.e23.ajn.b.i;
import com.e23.ajn.b.j;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.c.b;
import com.e23.ajn.d.n;
import com.e23.ajn.d.p;
import com.e23.ajn.d.w;
import com.e23.ajn.model.GetCodeResponse;
import com.e23.ajn.model.LoginResponse;
import com.e23.ajn.model.Result;
import com.e23.ajn.views.k;
import com.jaeger.library.a;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.tencent.android.tpush.SettingsContentProvider;
import com.tencent.android.tpush.common.Constants;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.HashMap;
import okhttp3.Call;
import org.json.JSONException;
import org.json.JSONObject;

public class MineLoginFragment extends BaseSupportFragment implements Callback, OnClickListener {
    Button a;
    PlatformActionListener d = new PlatformActionListener() {
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            if (i == 8) {
                Message message = new Message();
                message.what = 4;
                message.obj = new Object[]{platform.getName()};
                MineLoginFragment.this.k.sendMessage(message);
            }
        }

        public void onError(Platform platform, int i, Throwable th) {
            if (i == 8) {
                MineLoginFragment.this.k.sendEmptyMessage(3);
                th.printStackTrace();
            }
        }

        public void onCancel(Platform platform, int i) {
            if (i == 8) {
                MineLoginFragment.this.k.sendEmptyMessage(2);
            }
        }
    };
    private EditText e;
    /* access modifiers changed from: private */
    public EditText f;
    /* access modifiers changed from: private */
    public TextView g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    /* access modifiers changed from: private */
    public Handler k;
    private String l;
    private CheckBox m;
    private TextView n;
    /* access modifiers changed from: private */
    public boolean o = true;
    private TextWatcher p = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 4) {
                MineLoginFragment.this.a.getBackground().setAlpha(255);
                MineLoginFragment.this.a.setEnabled(true);
                return;
            }
            MineLoginFragment.this.a.getBackground().setAlpha(ErrorCode.DOWNLOAD_THROWABLE);
            MineLoginFragment.this.a.setEnabled(false);
        }
    };

    /* renamed from: q reason: collision with root package name */
    private CountDownTimer f414q = new CountDownTimer(60000, 1000) {
        public void onTick(long j) {
            MineLoginFragment.this.g.setText((j / 1000) + MineLoginFragment.this.getString(R.string.fragment_mine_login_miaohouchongfa));
        }

        public void onFinish() {
            MineLoginFragment.this.g.setEnabled(true);
            MineLoginFragment.this.g.setText(MineLoginFragment.this.getString(R.string.fragment_mine_login_et_postyzm_text));
        }
    };

    public static MineLoginFragment h() {
        Bundle bundle = new Bundle();
        MineLoginFragment mineLoginFragment = new MineLoginFragment();
        mineLoginFragment.setArguments(bundle);
        return mineLoginFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f143hdcontentheader, viewGroup, false);
        this.k = new Handler(this);
        a(inflate);
        return inflate;
    }

    private void a(View view) {
        a.b(this.c, 0, null);
        ((ImageView) view.findViewById(R.id.toolbar_left_back)).setOnClickListener(this);
        this.e = (EditText) view.findViewById(R.id.fragment_mine_login_et_phoneno);
        this.f = (EditText) view.findViewById(R.id.fragment_mine_login_et_yanzhengma);
        this.f.addTextChangedListener(this.p);
        this.g = (TextView) view.findViewById(R.id.fragment_mine_login_tv_postyzm);
        this.g.setOnClickListener(this);
        this.a = (Button) view.findViewById(R.id.fragment_mine_login_btn_login);
        this.a.setOnClickListener(this);
        this.a.getBackground().setAlpha(ErrorCode.DOWNLOAD_THROWABLE);
        this.a.setEnabled(false);
        this.h = (ImageView) view.findViewById(R.id.fragment_mine_login_iv_wechat);
        this.h.setOnClickListener(this);
        this.i = (ImageView) view.findViewById(R.id.fragment_mine_login_iv_qq);
        this.i.setOnClickListener(this);
        this.j = (ImageView) view.findViewById(R.id.fragment_mine_login_iv_sinaweibo);
        this.j.setOnClickListener(this);
        this.m = (CheckBox) view.findViewById(R.id.fragment_mine_login_cbx);
        this.m.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MineLoginFragment.this.o = z;
            }
        });
        this.m.setChecked(true);
        this.n = (TextView) view.findViewById(R.id.fragment_mine_login_agree);
        this.n.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_back /*2131820915*/:
                this.c.onBackPressed();
                return;
            case R.id.fragment_mine_login_tv_postyzm /*2131820937*/:
                a(this.e.getText().toString());
                return;
            case R.id.fragment_mine_login_btn_login /*2131820939*/:
                if (this.o) {
                    a(this.e.getText().toString(), this.f.getText().toString());
                    return;
                } else {
                    k.a(this.c, "请同意并认真阅读《用户协议和隐私政策》");
                    return;
                }
            case R.id.fragment_mine_login_agree /*2131820941*/:
                Intent intent = new Intent(this.c, SwipeBackCommonActivity.class);
                intent.putExtra(SwipeBackCommonActivity.TAG, 1);
                intent.putExtra(SwipeBackCommonActivity.URL, "http://appc.e23.cn/secret/");
                startActivity(intent);
                return;
            case R.id.fragment_mine_login_iv_qq /*2131820942*/:
                if (this.o) {
                    k.b(this.c, getString(R.string.waiting));
                    Platform platform = ShareSDK.getPlatform(QQ.NAME);
                    this.l = "qq";
                    a(platform);
                    return;
                }
                k.a(this.c, "请同意并认真阅读《用户协议和隐私政策》");
                return;
            case R.id.fragment_mine_login_iv_wechat /*2131820943*/:
                if (this.o) {
                    k.b(this.c, getString(R.string.waiting));
                    Platform platform2 = ShareSDK.getPlatform(Wechat.NAME);
                    this.l = "wechat";
                    a(platform2);
                    return;
                }
                k.a(this.c, "请同意并认真阅读《用户协议和隐私政策》");
                return;
            case R.id.fragment_mine_login_iv_sinaweibo /*2131820944*/:
                if (this.o) {
                    k.b(this.c, getString(R.string.waiting));
                    Platform platform3 = ShareSDK.getPlatform(SinaWeibo.NAME);
                    this.l = "sina";
                    a(platform3);
                    return;
                }
                k.a(this.c, "请同意并认真阅读《用户协议和隐私政策》");
                return;
            default:
                return;
        }
    }

    private void a(String str) {
        if ("".equals(str)) {
            w.b(getString(R.string.fragment_mine_login_inputphoneno));
            return;
        }
        this.g.setEnabled(false);
        this.f414q.start();
        b(str);
    }

    private void b(final String str) {
        k.b(this.c, getString(R.string.waiting));
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/ajnapi/rodomcode.php")).addParams(NetworkUtil.NETWORK_MOBILE, str).build().execute(new com.e23.ajn.c.a<GetCodeResponse>() {
            public void onError(Call call, Exception exc, int i) {
                MineLoginFragment.this.i();
            }

            /* renamed from: a */
            public void onResponse(GetCodeResponse getCodeResponse, int i) {
                if (getCodeResponse.getCode() == null || getCodeResponse.getCode().equals("")) {
                    MineLoginFragment.this.i();
                    return;
                }
                k.b();
                MineLoginFragment.this.a(getCodeResponse.getUrl(), str, getCodeResponse.getCode());
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, final String str3) {
        ((PostFormBuilder) OkHttpUtils.post().url(str)).addParams("phone", str2).addParams("an", str3).addParams(SettingsContentProvider.KEY, n.a(str2 + str3 + "ajnKey123")).build().execute(new StringCallback() {
            public void onError(Call call, Exception exc, int i) {
                MineLoginFragment.this.i();
            }

            /* renamed from: a */
            public void onResponse(String str, int i) {
                try {
                    if (new JSONObject(str).getString("succ").equals("1")) {
                        p.a("yzm_code", str3);
                        MineLoginFragment.this.f.requestFocus();
                        w.b(MineLoginFragment.this.getString(R.string.fragment_mine_login_yzmpostScc));
                        return;
                    }
                    MineLoginFragment.this.i();
                } catch (JSONException e) {
                    MineLoginFragment.this.i();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void i() {
        k.b();
        w.b(getString(R.string.fragment_mine_login_yzmpostfail));
        this.g.setEnabled(true);
        this.f414q.cancel();
        this.g.setText(getString(R.string.fragment_mine_login_et_postyzm_text));
    }

    private void a(String str, String str2) {
        if ("".equals(str)) {
            w.b(getString(R.string.fragment_mine_login_inputphoneno));
        } else if ("".equals(str2)) {
            w.b(getString(R.string.fragment_mine_login_inputyzm));
        } else {
            if (!str2.equals(p.b("yzm_code", ""))) {
            }
            k.b(this.c, getString(R.string.waiting));
            ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=user&a=login")).addParams(SettingsContentProvider.KEY, str).addParams("userfrom", NetworkUtil.NETWORK_MOBILE).addParams("code", str2).build().execute(new com.e23.ajn.c.a<LoginResponse>() {
                public void onError(Call call, Exception exc, int i) {
                    k.b();
                    w.b(MineLoginFragment.this.getString(R.string.fragment_mine_login_fail));
                }

                /* renamed from: a */
                public void onResponse(LoginResponse loginResponse, int i) {
                    k.b();
                    if (loginResponse.getCode() != 200) {
                        w.b(loginResponse.getMessage());
                        return;
                    }
                    p.a("yzm_code", "");
                    p.b("is_logined", true);
                    p.a(Constants.FLAG_TOKEN, loginResponse.getData().getToken());
                    p.a("user_name", loginResponse.getData().getUsername());
                    p.a("user_avatar", loginResponse.getData().getAvatar());
                    p.a("user_id", loginResponse.getData().getUserid());
                    e.a(MineLoginFragment.this.c).c(new h());
                    e.a(MineLoginFragment.this.c).c(new i());
                    e.a(MineLoginFragment.this.c).c(new com.e23.ajn.b.k());
                    MineLoginFragment.this.j();
                    MineLoginFragment.this.c.finish();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        if (p.a("is_logined", false) && !DateUtils.isToday(p.a(p.b(Constants.FLAG_TOKEN, "") + "open_time", 0))) {
            ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=user&a=openAppTodayFirst")).params(b.a(null)).tag(this)).build().execute(new com.e23.ajn.c.a<Result>() {
                public void onError(Call call, Exception exc, int i) {
                }

                /* renamed from: a */
                public void onResponse(Result result, int i) {
                    if (result != null && result.getCode() == 200) {
                        p.a(p.b(Constants.FLAG_TOKEN, "") + "open_time", System.currentTimeMillis());
                    }
                }
            });
        }
    }

    private void a(Platform platform) {
        platform.setPlatformActionListener(this.d);
        platform.SSOSetting(false);
        platform.showUser(null);
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 2:
                w.b(getString(R.string.auth_cancel));
                k.b();
                break;
            case 3:
                w.b(getString(R.string.auth_error));
                k.b();
                break;
            case 4:
                Platform platform = ShareSDK.getPlatform((String) ((Object[]) message.obj)[0]);
                b(platform.getDb().getUserId(), platform.getDb().getUserName(), platform.getDb().getUserIcon());
                break;
        }
        return false;
    }

    private void b(String str, String str2, String str3) {
        k.b(this.c, getString(R.string.waiting));
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=user&a=login")).params(b.a(null)).addParams(SettingsContentProvider.KEY, str).addParams("userfrom", this.l).addParams("nickname", str2).addParams("headimg", str3).build().execute(new com.e23.ajn.c.a<LoginResponse>() {
            public void onError(Call call, Exception exc, int i) {
                k.b();
                w.b(MineLoginFragment.this.getString(R.string.fragment_mine_login_fail));
            }

            /* renamed from: a */
            public void onResponse(LoginResponse loginResponse, int i) {
                k.b();
                if (loginResponse.getCode() != 200) {
                    w.b(loginResponse.getMessage());
                    return;
                }
                p.b("is_logined", true);
                p.a("user_name", loginResponse.getData().getUsername());
                p.a("user_id", loginResponse.getData().getUserid());
                p.a(Constants.FLAG_TOKEN, loginResponse.getData().getToken());
                p.a("user_avatar", loginResponse.getData().getAvatar());
                e.a(MineLoginFragment.this.c).c(new i());
                e.a(MineLoginFragment.this.c).c(new j(1));
                e.a(MineLoginFragment.this.c).c(new h());
                e.a(MineLoginFragment.this.c).c(new com.e23.ajn.b.k());
                MineLoginFragment.this.j();
                MineLoginFragment.this.c.finish();
            }
        });
    }

    public void onDestroy() {
        if (this.f414q != null) {
            this.f414q.cancel();
            this.f414q = null;
        }
        super.onDestroy();
    }
}
