package com.e23.ajn.fragment.mine.child;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.b.d;
import com.e23.ajn.b.f;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.d.m;
import com.e23.ajn.d.p;
import com.e23.ajn.d.q;
import com.e23.ajn.d.w;
import com.e23.ajn.model.BaseResponse;
import com.e23.ajn.model.LoginResponse;
import com.e23.ajn.model.LoginResponse.DataBean;
import com.e23.ajn.views.CircularImage;
import com.e23.ajn.views.g;
import com.jaeger.library.a;
import com.tencent.android.tpush.common.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import okhttp3.Call;
import okhttp3.Request;
import org.greenrobot.eventbus.j;
import q.rorbin.badgeview.e;

public class MineFragment extends BaseSupportFragment implements OnClickListener {
    private e A;
    private e B;
    /* access modifiers changed from: private */
    public CircularImage a;
    /* access modifiers changed from: private */
    public TextView d;
    /* access modifiers changed from: private */
    public TextView e;
    /* access modifiers changed from: private */
    public TextView f;
    private TextView g;
    /* access modifiers changed from: private */
    public TextView h;
    /* access modifiers changed from: private */
    public TextView i;
    private TextView j;
    private LinearLayout k;
    private LinearLayout l;
    private LinearLayout m;
    private RelativeLayout n;
    private RelativeLayout o;
    private RelativeLayout p;

    /* renamed from: q reason: collision with root package name */
    private RelativeLayout f413q;
    private RelativeLayout r;
    private RelativeLayout s;
    private RelativeLayout t;
    /* access modifiers changed from: private */
    public RelativeLayout u;
    private g v;
    private String w;
    private int x;
    private int y;
    /* access modifiers changed from: private */
    public DataBean z;

    public static MineFragment h() {
        Bundle bundle = new Bundle();
        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(bundle);
        return mineFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f138goodsshow, viewGroup, false);
        this.x = q.b(this.c);
        this.y = (int) (((double) this.x) * 0.54d);
        a(inflate);
        com.e23.ajn.b.e.a(this.c).a((Object) this);
        return inflate;
    }

    private void a(View view) {
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        this.a = (CircularImage) view.findViewById(R.id.fragment_mine_avatar);
        this.a.setOnClickListener(this);
        this.d = (TextView) view.findViewById(R.id.fragment_mine_name);
        this.d.setOnClickListener(this);
        this.n = (RelativeLayout) view.findViewById(R.id.fragment_mine_rl_head);
        this.n.setOnClickListener(this);
        this.e = (TextView) view.findViewById(R.id.fragment_mine_tv_inputyqm);
        this.e.setOnClickListener(this);
        this.f = (TextView) view.findViewById(R.id.fragment_mine_tv_points);
        this.g = (TextView) view.findViewById(R.id.fragment_mine_tv_message);
        this.k = (LinearLayout) view.findViewById(R.id.fragment_mine_ll_views);
        this.k.setOnClickListener(this);
        this.l = (LinearLayout) view.findViewById(R.id.fragment_mine_ll_cell);
        this.l.setOnClickListener(this);
        this.m = (LinearLayout) view.findViewById(R.id.fragment_mine_ll_message);
        this.m.setOnClickListener(this);
        this.p = (RelativeLayout) view.findViewById(R.id.fragment_mine_rl_posts);
        this.p.setOnClickListener(this);
        this.f413q = (RelativeLayout) view.findViewById(R.id.fragment_mine_rl_reply);
        this.f413q.setOnClickListener(this);
        this.r = (RelativeLayout) view.findViewById(R.id.fragment_mine_rl_comment);
        this.r.setOnClickListener(this);
        this.u = (RelativeLayout) view.findViewById(R.id.fragment_mine_rl_clue);
        this.u.setOnClickListener(this);
        this.s = (RelativeLayout) view.findViewById(R.id.fragment_mine_rl_set);
        this.s.setOnClickListener(this);
        this.h = (TextView) view.findViewById(R.id.fragment_mine_tv_daka);
        this.h.setOnClickListener(this);
        this.i = (TextView) view.findViewById(R.id.fragment_mine_tv_yqm);
        i.a(this.c).a(p.b("user_avatar", "")).b(b.ALL).d((int) R.mipmap.f239fatieimg).c((int) R.mipmap.f239fatieimg).a((ImageView) this.a);
        this.o = (RelativeLayout) view.findViewById(R.id.fragment_mine_rl_yqm);
        this.o.setOnClickListener(this);
        this.t = (RelativeLayout) view.findViewById(R.id.fragment_mine_rl_interact);
        this.t.setOnClickListener(this);
        this.j = (TextView) view.findViewById(R.id.fragment_mine_tv_interact);
        this.A = new e(this.c);
        this.A.a((View) this.g).b(this.c.getResources().getColor(R.color.colorRed2)).d(8388661).a(0.0f, 0.0f, true).a(true).c(this.g.getContext().getResources().getColor(R.color.colorWhite)).b(2.0f, true).a(10.0f, true);
        this.A.c(true);
        this.B = new e(this.c);
        this.B.a((View) this.j).b(this.c.getResources().getColor(R.color.colorRed2)).d(8388661).a(0.0f, 0.0f, true).a(true).c(this.j.getContext().getResources().getColor(R.color.colorWhite)).b(2.0f, true).a(10.0f, true);
        this.B.c(true);
    }

    public void c() {
        super.c();
        if (p.a("is_logined", false)) {
            this.d.setText(p.b("user_name", ""));
            i();
        }
    }

    @j
    public void onEditMeEvent(d dVar) {
        if (!TextUtils.isEmpty(dVar.b) && this.d != null) {
            this.d.setText(dVar.b);
        }
        if (!TextUtils.isEmpty(dVar.a) && this.a != null) {
            i.a(this.c).a(dVar.a).b(b.ALL).d((int) R.mipmap.f239fatieimg).c((int) R.mipmap.f239fatieimg).a((ImageView) this.a);
        }
    }

    @j
    public void onRefreshMineInfoEvent(com.e23.ajn.b.i iVar) {
        this.d.setText(p.b("user_name", ""));
        i.a(this.c).a(p.b("user_avatar", "")).b(b.ALL).d((int) R.mipmap.f239fatieimg).c((int) R.mipmap.f239fatieimg).a((ImageView) this.a);
        i();
    }

    @j
    public void onInputYqmEvent(f fVar) {
        if (this.e != null) {
            this.e.setVisibility(8);
        }
    }

    @j
    public void onLogoutEvent(com.e23.ajn.b.g gVar) {
        this.a.setImageBitmap(m.a(this.c, R.mipmap.f239fatieimg));
        this.d.setText(getString(R.string.clicklogin));
        this.n.setBackground(getResources().getDrawable(R.mipmap.f243hottc));
        this.i.setText("");
        this.f.setText("");
        this.u.setVisibility(8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_mine_avatar /*2131820893*/:
                a(22);
                return;
            case R.id.fragment_mine_name /*2131820894*/:
                a(22);
                return;
            case R.id.fragment_mine_ll_views /*2131820896*/:
                a(27);
                return;
            case R.id.fragment_mine_ll_cell /*2131820897*/:
                a(26);
                return;
            case R.id.fragment_mine_ll_message /*2131820898*/:
                a(29);
                return;
            case R.id.fragment_mine_tv_inputyqm /*2131820900*/:
                if (!p.a("is_logined", false)) {
                    Intent intent = new Intent(this.c, SwipeBackCommonActivity.class);
                    intent.putExtra(SwipeBackCommonActivity.TAG, 21);
                    startActivity(intent);
                    return;
                } else if (this.v != null) {
                    this.v.show();
                    return;
                } else {
                    this.v = new g(this.c);
                    this.v.show();
                    return;
                }
            case R.id.fragment_mine_tv_daka /*2131820901*/:
                if (!p.a("is_logined", false)) {
                    Intent intent2 = new Intent(this.c, SwipeBackCommonActivity.class);
                    intent2.putExtra(SwipeBackCommonActivity.TAG, 21);
                    startActivity(intent2);
                    return;
                }
                j();
                return;
            case R.id.fragment_mine_rl_yqm /*2131820902*/:
                a(24);
                return;
            case R.id.fragment_mine_rl_posts /*2131820908*/:
                a(33);
                return;
            case R.id.fragment_mine_rl_interact /*2131820909*/:
                a(28);
                return;
            case R.id.fragment_mine_rl_reply /*2131820911*/:
                a(34);
                return;
            case R.id.fragment_mine_rl_clue /*2131820912*/:
                a(37);
                return;
            case R.id.fragment_mine_rl_comment /*2131820913*/:
                a(28);
                return;
            case R.id.fragment_mine_rl_set /*2131820914*/:
                Intent intent3 = new Intent(this.c, SwipeBackCommonActivity.class);
                intent3.putExtra(SwipeBackCommonActivity.TAG, 25);
                startActivity(intent3);
                return;
            default:
                return;
        }
    }

    private void j() {
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=user&a=userSignIn")).addParams(Constants.FLAG_TOKEN, p.b(Constants.FLAG_TOKEN, "")).build().execute(new com.e23.ajn.c.a<BaseResponse>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                MineFragment.this.h.setEnabled(false);
            }

            public void onError(Call call, Exception exc, int i) {
                w.a(MineFragment.this.getString(R.string.mine_daka_fail));
                MineFragment.this.h.setEnabled(true);
            }

            /* renamed from: a */
            public void onResponse(BaseResponse baseResponse, int i) {
                w.a(baseResponse.getMsg());
                if (baseResponse.getCode() == 200) {
                    MineFragment.this.h.setText(MineFragment.this.getString(R.string.mine_daka_ok));
                    MineFragment.this.h.setEnabled(false);
                }
            }
        });
    }

    private void a(int i2) {
        if (!p.a("is_logined", false)) {
            Intent intent = new Intent(this.c, SwipeBackCommonActivity.class);
            intent.putExtra(SwipeBackCommonActivity.TAG, 21);
            startActivity(intent);
        } else if (this.z != null) {
            Intent intent2 = new Intent(this.c, SwipeBackCommonActivity.class);
            intent2.putExtra(SwipeBackCommonActivity.TAG, i2);
            if (i2 == 24) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("yqm", this.w);
                intent2.putExtras(bundle);
            }
            if (i2 == 22) {
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("user", this.z);
                intent2.putExtras(bundle2);
            }
            if (i2 == 29 || i2 == 37) {
                intent2.putExtra("userid", this.z.getUserid());
            }
            if (i2 == 24) {
                Bundle bundle3 = new Bundle();
                bundle3.putString("yqm", this.z.getPromocode());
                intent2.putExtras(bundle3);
            }
            startActivity(intent2);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        com.e23.ajn.b.e.a(this.c).b(this);
    }

    public void i() {
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=user&a=getUserInfo_pub")).addParams(Constants.FLAG_TOKEN, p.b(Constants.FLAG_TOKEN, "")).build().execute(new com.e23.ajn.c.a<LoginResponse>() {
            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(LoginResponse loginResponse, int i) {
                if (loginResponse.getCode() == 200) {
                    MineFragment.this.z = loginResponse.getData();
                    MineFragment.this.d.setText(loginResponse.getData().getUsername());
                    p.a("user_id", loginResponse.getData().getUserid());
                    p.a("user_name", loginResponse.getData().getUsername());
                    p.a("user_avatar", loginResponse.getData().getAvatar());
                    i.a(MineFragment.this.c).a(loginResponse.getData().getAvatar()).b(b.ALL).d((int) R.mipmap.f239fatieimg).c((int) R.mipmap.f239fatieimg).a((ImageView) MineFragment.this.a);
                    MineFragment.this.f.setText(loginResponse.getData().getTruejifen());
                    MineFragment.this.i.setText(loginResponse.getData().getPromocode());
                    if (loginResponse.getData().getPromofix().equals("1")) {
                        MineFragment.this.e.setVisibility(8);
                    }
                    if (loginResponse.getData().getQiandao().equals("1")) {
                        MineFragment.this.h.setText(MineFragment.this.getString(R.string.mine_daka_ok));
                        MineFragment.this.h.setEnabled(false);
                    }
                    if (!loginResponse.getData().getReporter_type().equals("0")) {
                        MineFragment.this.u.setVisibility(0);
                    }
                    MineFragment.this.k();
                }
            }
        });
    }

    @j
    public void onRefreshRedPointEvent(com.e23.ajn.b.j jVar) {
        k();
    }

    /* access modifiers changed from: private */
    public void k() {
        if (com.e23.ajn.a.a.b > 0) {
            this.A.a(com.e23.ajn.a.a.b);
        } else {
            this.A.a(0);
        }
        if (com.e23.ajn.a.a.c > 0) {
            this.B.a(-1);
        } else {
            this.B.a(0);
        }
    }
}
