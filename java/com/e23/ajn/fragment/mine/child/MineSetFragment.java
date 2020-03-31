package com.e23.ajn.fragment.mine.child;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.app.App;
import com.e23.ajn.b.b;
import com.e23.ajn.b.e;
import com.e23.ajn.b.g;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.d.c;
import com.e23.ajn.d.h;
import com.e23.ajn.d.j;
import com.e23.ajn.d.p;
import com.e23.ajn.views.f;
import com.e23.ajn.views.k;
import com.jaeger.library.a;
import com.stub.StubApp;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;
import me.yokeyword.fragmentation.d;

public class MineSetFragment extends BaseSupportFragment implements OnClickListener {
    private RelativeLayout a;
    /* access modifiers changed from: private */
    public TextView d;
    /* access modifiers changed from: private */
    public TextView e;
    private RelativeLayout f;
    private RelativeLayout g;
    private RelativeLayout h;
    private TextView i;
    private ToggleButton j;

    public static MineSetFragment h() {
        Bundle bundle = new Bundle();
        MineSetFragment mineSetFragment = new MineSetFragment();
        mineSetFragment.setArguments(bundle);
        return mineSetFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f146hdlist_item, viewGroup, false);
        e.a(this.c).a((Object) this);
        a(inflate);
        return inflate;
    }

    private void a(View view) {
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        ImageView imageView = (ImageView) view.findViewById(R.id.toolbar_left_back);
        imageView.setOnClickListener(this);
        imageView.setVisibility(0);
        TextView textView = (TextView) view.findViewById(R.id.toolbar_center_title);
        textView.setText(getString(R.string.fragment_mine_set));
        textView.setVisibility(0);
        this.a = (RelativeLayout) view.findViewById(R.id.fragment_mime_set_rl_clearCache);
        this.a.setOnClickListener(this);
        this.d = (TextView) view.findViewById(R.id.fragment_mime_set_tv_clearCache);
        try {
            this.d.setText(h.i());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f = (RelativeLayout) view.findViewById(R.id.fragment_mime_set_rl_aboutUs);
        this.f.setOnClickListener(this);
        this.e = (TextView) view.findViewById(R.id.mine_btn_quit);
        this.e.setOnClickListener(this);
        this.i = (TextView) view.findViewById(R.id.setting_check_update_version);
        this.i.setText(App.getInstance().getCurrentVersionName());
        this.g = (RelativeLayout) view.findViewById(R.id.setting_check_update);
        this.g.setOnClickListener(this);
        if (p.a("is_logined", false)) {
            this.e.setVisibility(0);
        }
        this.j = (ToggleButton) view.findViewById(R.id.fragment_mine_tgb_switch);
        this.j.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                p.b("is_push_open", z);
                if (z) {
                    XGPushConfig.setMiPushAppId(StubApp.getOrigApplicationContext(MineSetFragment.this.c.getApplicationContext()), "2882303761517262064");
                    XGPushConfig.setMiPushAppKey(StubApp.getOrigApplicationContext(MineSetFragment.this.c.getApplicationContext()), "5241726247064");
                    XGPushConfig.enableOtherPush(StubApp.getOrigApplicationContext(MineSetFragment.this.c.getApplicationContext()), true);
                    XGPushManager.registerPush(StubApp.getOrigApplicationContext(MineSetFragment.this.c.getApplicationContext()));
                    return;
                }
                XGPushManager.unregisterPush(StubApp.getOrigApplicationContext(MineSetFragment.this.c.getApplicationContext()), new XGIOperateCallback() {
                    public void onSuccess(Object obj, int i) {
                    }

                    public void onFail(Object obj, int i, String str) {
                    }
                });
            }
        });
        this.j.setChecked(p.a("is_push_open", true));
        this.h = (RelativeLayout) view.findViewById(R.id.fragment_mime_set_rl_user_agreement);
        this.h.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_back /*2131820915*/:
                this.c.onBackPressed();
                return;
            case R.id.fragment_mime_set_rl_clearCache /*2131820952*/:
                new f(this.c, getResources().getString(R.string.confirm_clear), new f.a() {
                    public void a() {
                        MineSetFragment.this.i();
                    }
                }).show();
                return;
            case R.id.fragment_mime_set_rl_aboutUs /*2131820955*/:
                a((d) MineAboutUsFragment.h());
                return;
            case R.id.setting_check_update /*2131820958*/:
                com.e23.ajn.d.d.a(this.c, true, true);
                return;
            case R.id.fragment_mime_set_rl_user_agreement /*2131820960*/:
                Intent intent = new Intent(this.c, SwipeBackCommonActivity.class);
                intent.putExtra(SwipeBackCommonActivity.TAG, 1);
                intent.putExtra(SwipeBackCommonActivity.URL, "http://appc.e23.cn/secret/");
                startActivity(intent);
                return;
            case R.id.mine_btn_quit /*2131820961*/:
                j();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        k.b(this.c, "");
        new c() {
            public void a() {
                j.a().c(MineSetFragment.this.c);
                e.a(MineSetFragment.this.c).c(new b(h.i()));
            }
        }.start();
    }

    @org.greenrobot.eventbus.j
    public void onClearCaheEvent(b bVar) {
        k.b();
        this.c.runOnUiThread(new Runnable() {
            public void run() {
                MineSetFragment.this.d.setText(h.i());
            }
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
        e.a(this.c).b(this);
    }

    private void j() {
        new f(this.c, getResources().getString(R.string.confirm_exit), new f.a() {
            public void a() {
                Platform platform = ShareSDK.getPlatform(QQ.NAME);
                if (platform != null && platform.isAuthValid()) {
                    platform.removeAccount(true);
                }
                Platform platform2 = ShareSDK.getPlatform(Wechat.NAME);
                if (platform2 != null && platform2.isAuthValid()) {
                    platform2.removeAccount(true);
                }
                Platform platform3 = ShareSDK.getPlatform(SinaWeibo.NAME);
                if (platform3 != null && platform3.isAuthValid()) {
                    platform3.removeAccount(true);
                }
                p.b("is_logined", false);
                p.a("user_id");
                p.a("user_name");
                p.a("nick_name");
                p.a(Constants.FLAG_TOKEN);
                p.a("mine_head_bgimg");
                p.a("user_avatar");
                e.a(MineSetFragment.this.c).c(new g());
                e.a(MineSetFragment.this.c).c(new com.e23.ajn.b.k());
                MineSetFragment.this.e.setVisibility(8);
                MineSetFragment.this.c.onBackPressed();
            }
        }).show();
    }
}
