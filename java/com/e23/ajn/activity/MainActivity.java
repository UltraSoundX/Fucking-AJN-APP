package com.e23.ajn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.format.DateUtils;
import android.widget.Toast;
import com.baidu.mobstat.StatService;
import com.e23.ajn.R;
import com.e23.ajn.b.e;
import com.e23.ajn.b.o;
import com.e23.ajn.base.BaseMainFragment.a;
import com.e23.ajn.base.BaseSupportActivity;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.c.b;
import com.e23.ajn.d.p;
import com.e23.ajn.fragment.first_page.FirstPageMainFragment;
import com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment;
import com.e23.ajn.fragment.interaction.InteractionMainFragment;
import com.e23.ajn.fragment.live.LiveMainFragment;
import com.e23.ajn.fragment.mine.MineMainFragment;
import com.e23.ajn.fragment.mine.child.MineFragment;
import com.e23.ajn.fragment.service.ServiceMainFragment;
import com.e23.ajn.fragment.service.child.ServiceFragment;
import com.e23.ajn.model.RedPointResponse;
import com.e23.ajn.model.Result;
import com.e23.ajn.views.BottomBar;
import com.e23.ajn.views.d;
import com.e23.ajn.views.r;
import com.stub.StubApp;
import com.tencent.android.tpush.common.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import okhttp3.Call;
import org.greenrobot.eventbus.j;

public class MainActivity extends BaseSupportActivity implements a {
    public static final int FIRST_PAGE = 0;
    public static final int INTERACTION = 3;
    public static final int LIVE = 2;
    public static final int MINE = 4;
    public static final int SERVICE = 1;
    public long TOUCH_TIME = 0;
    /* access modifiers changed from: private */
    public BaseSupportFragment[] b = new BaseSupportFragment[5];
    /* access modifiers changed from: private */
    public BottomBar c;
    private r d;

    static {
        StubApp.interface11(3488);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        StatService.onPause(this);
    }

    private void a() {
        this.c = (BottomBar) findViewById(R.id.bottomBar);
        this.c.a(new d(this, R.mipmap.f301sexright_male, R.mipmap.f302shareimg, getString(R.string.first_page))).a(new d(this, R.mipmap.f309sign_in_0, R.mipmap.f310sign_in_1, getString(R.string.service))).a(new d(this, R.mipmap.f305shequmore, R.mipmap.f306shoujihao, getString(R.string.live))).a(new d(this, R.mipmap.f303sharesinaweibo, R.mipmap.f304shareweixin, getString(R.string.interaction))).a(new d(this, R.mipmap.f307shoujileft, R.mipmap.f308shuoliangju, getString(R.string.mine)));
        this.c.setOnTabSelectedListener(new BottomBar.a() {
            public void a(int i, int i2) {
                MainActivity.this.showHideFragment(MainActivity.this.b[i], MainActivity.this.b[i2]);
            }

            public void a(int i) {
            }

            public void b(int i) {
                BaseSupportFragment baseSupportFragment = MainActivity.this.b[i];
                int backStackEntryCount = baseSupportFragment.getChildFragmentManager().getBackStackEntryCount();
                if (backStackEntryCount > 1) {
                    if (baseSupportFragment instanceof FirstPageMainFragment) {
                        baseSupportFragment.a(FirstPageViewPagerFragment.class, false);
                    } else if (baseSupportFragment instanceof InteractionMainFragment) {
                        baseSupportFragment.a(FirstPageViewPagerFragment.class, false);
                    } else if (baseSupportFragment instanceof ServiceMainFragment) {
                        baseSupportFragment.a(ServiceFragment.class, false);
                    } else if (baseSupportFragment instanceof LiveMainFragment) {
                        baseSupportFragment.a(LiveMainFragment.class, false);
                    } else if (baseSupportFragment instanceof MineMainFragment) {
                        baseSupportFragment.a(MineFragment.class, false);
                    }
                } else if (backStackEntryCount == 1) {
                    e.a(MainActivity.this).c(new o(i));
                }
            }
        });
    }

    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else if (System.currentTimeMillis() - this.TOUCH_TIME < 2000) {
            ActivityCompat.finishAfterTransition(this);
        } else {
            this.TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(this, R.string.press_again_exit, 0).show();
        }
    }

    public void onBackToFirstFragment() {
        this.c.setCurrentItem(0);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: private */
    public void b() {
        if (p.a("is_logined", false)) {
            ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=user&a=getRedPointSum")).params(b.a(null)).tag(this)).build().execute(new com.e23.ajn.c.a<RedPointResponse>() {
                public void onError(Call call, Exception exc, int i) {
                }

                /* renamed from: a */
                public void onResponse(RedPointResponse redPointResponse, int i) {
                    if (redPointResponse != null && redPointResponse.getCode() == 200 && MainActivity.this.c.a(3) != null) {
                        com.e23.ajn.a.a.b = redPointResponse.getData().getMessagesum();
                        com.e23.ajn.a.a.c = redPointResponse.getData().getPltomesum();
                        if (com.e23.ajn.a.a.b > 0 || com.e23.ajn.a.a.c > 0) {
                            MainActivity.this.c.a(3).setShowOrHideCount(0);
                        }
                    }
                }
            });
        }
    }

    @j
    public void onRefreshRedPointEvent(com.e23.ajn.b.j jVar) {
        if (jVar.a == 1) {
            b();
        } else if (com.e23.ajn.a.a.b > 0 || com.e23.ajn.a.a.c > 0) {
            this.c.a(3).setShowOrHideCount(0);
        } else {
            this.c.a(3).setShowOrHideCount(8);
        }
    }

    private void c() {
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

    public void onDestroy() {
        super.onDestroy();
        e.a(this).b(this);
    }
}
