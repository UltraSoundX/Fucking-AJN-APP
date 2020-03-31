package com.e23.ajn.fragment.first_page.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.b.c;
import com.e23.ajn.b.h;
import com.e23.ajn.b.i;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.c.b;
import com.e23.ajn.d.p;
import com.e23.ajn.d.s;
import com.e23.ajn.model.DetailStatusResponseModel;
import com.e23.ajn.model.Result;
import com.e23.ajn.model.ThumbZu;
import com.e23.ajn.views.e;
import com.e23.ajn.views.k;
import com.e23.ajn.views.o;
import com.jaeger.library.a;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.util.ArrayList;
import java.util.List;
import me.yokeyword.fragmentation.d;
import okhttp3.Call;
import okhttp3.Request;
import org.greenrobot.eventbus.j;

public class PicDetailFragment extends BaseSupportFragment implements OnClickListener {
    /* access modifiers changed from: private */
    public int A;
    /* access modifiers changed from: private */
    public ArrayList<ThumbZu> B;
    private FragmentStatePagerAdapter C;
    private View a;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private ViewPager k;
    /* access modifiers changed from: private */
    public TextView l;
    private LinearLayout m;
    private LinearLayout n;
    private RelativeLayout o;
    /* access modifiers changed from: private */
    public TextView p;

    /* renamed from: q reason: collision with root package name */
    private LinearLayout f404q;
    /* access modifiers changed from: private */
    public ImageView r;
    private LinearLayout s;
    private o t;
    private e u;
    /* access modifiers changed from: private */
    public boolean v = false;
    /* access modifiers changed from: private */
    public boolean w = false;
    /* access modifiers changed from: private */
    public RelativeLayout x;
    /* access modifiers changed from: private */
    public ImageView y;
    /* access modifiers changed from: private */
    public int z;

    public static PicDetailFragment a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        PicDetailFragment picDetailFragment = new PicDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SwipeBackCommonActivity.TITLE, str4);
        bundle.putString(SwipeBackCommonActivity.URL, str3);
        bundle.putString("DES", str5);
        bundle.putString("THUMB", str6);
        bundle.putString("NEWS_ID", str2);
        bundle.putString("CAT_ID", str);
        bundle.putString("THUMBS", str7);
        picDetailFragment.setArguments(bundle);
        return picDetailFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.i = getArguments().getString("NEWS_ID");
            this.d = getArguments().getString(SwipeBackCommonActivity.URL);
            this.e = getArguments().getString(SwipeBackCommonActivity.TITLE);
            this.f = getArguments().getString("DES");
            this.g = getArguments().getString("THUMB");
            this.j = getArguments().getString("CAT_ID");
            this.h = getArguments().getString("THUMBS");
            try {
                this.B = s.a(this.h, ThumbZu.class);
            } catch (Exception e2) {
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = layoutInflater.inflate(R.layout.mycell_item, viewGroup, false);
        h();
        com.e23.ajn.b.e.a(this.c).a((Object) this);
        i();
        k();
        if (p.a("is_logined", false)) {
            j();
        }
        return this.a;
    }

    private void h() {
        a.b(this.c, 0, null);
        this.t = new o(this.c);
        this.k = (ViewPager) this.a.findViewById(R.id.pics_detail_view_page);
        this.l = (TextView) this.a.findViewById(R.id.pics_detail_tv_content);
        this.m = (LinearLayout) this.a.findViewById(R.id.pics_detail_ll_back);
        this.m.setOnClickListener(this);
        this.n = (LinearLayout) this.a.findViewById(R.id.pics_detail_ll_input);
        this.n.setOnClickListener(this);
        this.o = (RelativeLayout) this.a.findViewById(R.id.pics_detail_ll_comment);
        this.o.setOnClickListener(this);
        this.p = (TextView) this.a.findViewById(R.id.pics_detail_tv_comment_num);
        this.f404q = (LinearLayout) this.a.findViewById(R.id.pics_detail_ll_favorite);
        this.f404q.setOnClickListener(this);
        this.r = (ImageView) this.a.findViewById(R.id.pics_detail_iv_favorite);
        this.s = (LinearLayout) this.a.findViewById(R.id.pics_detail_ll_share);
        this.s.setOnClickListener(this);
        this.x = (RelativeLayout) this.a.findViewById(R.id.pics_detail_praise_layout);
        this.y = (ImageView) this.a.findViewById(R.id.pics_detail_praise);
        this.y.setOnClickListener(this);
    }

    private void i() {
        if (com.e23.ajn.d.e.b((List) this.B)) {
            this.C = new FragmentStatePagerAdapter(this.c.getSupportFragmentManager()) {
                public int getCount() {
                    return PicDetailFragment.this.B.size();
                }

                public Fragment getItem(int i) {
                    return EnlargeFragment.a(((ThumbZu) PicDetailFragment.this.B.get(i)).getUrl());
                }
            };
            this.k.setAdapter(this.C);
            this.k.setOnPageChangeListener(new OnPageChangeListener() {
                public void onPageSelected(int i) {
                    PicDetailFragment.this.l.setText((i + 1) + "/" + PicDetailFragment.this.B.size() + "  " + ((ThumbZu) PicDetailFragment.this.B.get(i)).getAlt());
                }

                public void onPageScrolled(int i, float f, int i2) {
                }

                public void onPageScrollStateChanged(int i) {
                }
            });
            this.l.setText("1/" + this.B.size() + "  " + ((ThumbZu) this.B.get(0)).getAlt());
        }
    }

    public void onClick(View view) {
        boolean z2 = false;
        switch (view.getId()) {
            case R.id.pics_detail_ll_back /*2131820974*/:
                this.c.onBackPressed();
                return;
            case R.id.pics_detail_ll_input /*2131820975*/:
                if (this.u == null) {
                }
                this.u.show();
                return;
            case R.id.pics_detail_ll_comment /*2131820976*/:
                if (this.z == 0) {
                    k.a(this.c, getString(R.string.no_comment));
                    return;
                } else {
                    a((d) CommentListFragment.a(this.i, this.j, this.z));
                    return;
                }
            case R.id.pics_detail_ll_favorite /*2131820978*/:
                if (!p.a("is_logined", false)) {
                    Intent intent = new Intent(this.c, SwipeBackCommonActivity.class);
                    intent.putExtra(SwipeBackCommonActivity.TAG, 21);
                    startActivity(intent);
                    return;
                }
                if (!this.v) {
                    z2 = true;
                }
                a(z2);
                return;
            case R.id.pics_detail_ll_share /*2131820980*/:
                if (this.t != null && !this.t.isShowing()) {
                    this.t.a(this.e, this.f, this.g, this.d);
                    this.t.show();
                    return;
                }
                return;
            case R.id.pics_detail_praise /*2131820982*/:
                if (!p.a("is_logined", false)) {
                    Intent intent2 = new Intent(this.c, SwipeBackCommonActivity.class);
                    intent2.putExtra(SwipeBackCommonActivity.TAG, 21);
                    startActivity(intent2);
                    return;
                } else if (this.w) {
                    k.a(this.c, "您已赞过！");
                    return;
                } else {
                    l();
                    return;
                }
            default:
                return;
        }
    }

    private void j() {
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=contentc&a=addvisite")).params(b.a(null)).addParams("newsid", this.i).addParams("catid", this.j).tag(this)).build().execute(new com.e23.ajn.c.a<Result>() {
            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(Result result, int i) {
            }
        });
    }

    private void k() {
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=contentc&a=newsstatus_pub")).params(b.a(null)).addParams("newsid", this.i).addParams("catid", this.j).tag(this)).build().execute(new com.e23.ajn.c.a<DetailStatusResponseModel>() {
            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(DetailStatusResponseModel detailStatusResponseModel, int i) {
                if (detailStatusResponseModel != null && detailStatusResponseModel.getNews() != null && detailStatusResponseModel.getCode() == 200) {
                    PicDetailFragment.this.w = detailStatusResponseModel.getMore().getIsding().equals("1");
                    PicDetailFragment.this.v = detailStatusResponseModel.getMore().getIscell().equals("1");
                    if (PicDetailFragment.this.v) {
                        PicDetailFragment.this.r.setBackgroundResource(R.mipmap.f212caiimg2);
                    } else {
                        PicDetailFragment.this.r.setBackgroundResource(R.mipmap.f211caiimg);
                    }
                    if (PicDetailFragment.this.w) {
                        PicDetailFragment.this.y.setBackgroundResource(R.mipmap.f228dingimg2_b);
                    } else {
                        PicDetailFragment.this.y.setBackgroundResource(R.mipmap.f291rb_select_arrow);
                    }
                    PicDetailFragment.this.z = Integer.parseInt(detailStatusResponseModel.getNews().getPlsum());
                    PicDetailFragment.this.A = Integer.parseInt(detailStatusResponseModel.getNews().getDing());
                    PicDetailFragment.this.p.setText(PicDetailFragment.this.z + "");
                    new q.rorbin.badgeview.e(PicDetailFragment.this.c).a((View) PicDetailFragment.this.x).b(PicDetailFragment.this.c.getResources().getColor(R.color.colorRed2)).d(8388661).a(0.0f, 8.0f, true).a(true).c(PicDetailFragment.this.c.getResources().getColor(R.color.colorWhite)).b(2.0f, true).a(10.0f, true).a(PicDetailFragment.this.A);
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                k.a();
            }

            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                k.a(PicDetailFragment.this.c);
            }
        });
    }

    private void l() {
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=content&a=dingcai")).params(b.a(null)).addParams("newsid", this.i).addParams("catid", this.j).tag(this)).build().execute(new com.e23.ajn.c.a<Result>() {
            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(Result result, int i) {
                if (result != null && result.getCode() == 200) {
                    PicDetailFragment.this.y.setBackgroundResource(R.mipmap.f228dingimg2_b);
                    PicDetailFragment.this.w = !PicDetailFragment.this.w;
                    if (PicDetailFragment.this.w) {
                        PicDetailFragment.this.A = PicDetailFragment.this.A + 1;
                        k.a(PicDetailFragment.this.c, "点赞成功 +1分");
                        com.e23.ajn.b.e.a(PicDetailFragment.this.c).c(new i());
                    }
                    new q.rorbin.badgeview.e(PicDetailFragment.this.c).a((View) PicDetailFragment.this.x).b(PicDetailFragment.this.c.getResources().getColor(R.color.colorRed2)).d(8388661).a(0.0f, 8.0f, true).a(true).c(PicDetailFragment.this.c.getResources().getColor(R.color.colorWhite)).b(2.0f, true).a(10.0f, true).a(PicDetailFragment.this.A);
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                k.a();
            }

            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                k.a(PicDetailFragment.this.c);
            }
        });
    }

    private void a(final boolean z2) {
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=contentc")).params(b.a(null)).addParams("newsid", this.i).addParams("catid", this.j).addParams(Config.APP_VERSION_CODE, z2 ? "addtocell" : "cancelcell").tag(this)).build().execute(new com.e23.ajn.c.a<Result>() {
            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(Result result, int i) {
                if (result != null && result.getCode() == 200) {
                    if (z2) {
                        k.a(PicDetailFragment.this.c, "收藏成功");
                        PicDetailFragment.this.r.setBackgroundResource(R.mipmap.f212caiimg2);
                    } else {
                        k.a(PicDetailFragment.this.c, "取消收藏");
                        PicDetailFragment.this.r.setBackgroundResource(R.mipmap.f211caiimg);
                    }
                    PicDetailFragment.this.v = z2;
                    com.e23.ajn.b.e.a(PicDetailFragment.this.c).c(new c());
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                k.a();
            }

            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                k.a(PicDetailFragment.this.c);
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        com.e23.ajn.b.e.a(this.c).b(this);
    }

    @j
    public void onRefreshDetailEvent(h hVar) {
        k();
        j();
    }
}
