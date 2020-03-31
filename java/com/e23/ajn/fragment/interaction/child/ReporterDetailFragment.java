package com.e23.ajn.fragment.interaction.child;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.b;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CollapsingToolbarLayout.LayoutParams;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.i;
import com.e23.ajn.R;
import com.e23.ajn.adapter.ReporterPagerAdapter;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.base.BaseSwipeBackFragment;
import com.e23.ajn.d.q;
import com.e23.ajn.model.ReporterDetailResponseModel;
import com.e23.ajn.model.TabEntity;
import com.e23.ajn.views.CircularImage;
import com.e23.ajn.views.k;
import com.flyco.tablayout.CommonTabLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.util.ArrayList;
import me.yokeyword.fragmentation.d;
import okhttp3.Call;
import okhttp3.Request;

public class ReporterDetailFragment extends BaseSwipeBackFragment implements OnPageChangeListener, OnClickListener {
    private int A = 0;
    private String d;
    private View e;
    private ImageView f;
    /* access modifiers changed from: private */
    public TextView g;
    /* access modifiers changed from: private */
    public TextView h;
    /* access modifiers changed from: private */
    public TextView i;
    private Toolbar j;
    /* access modifiers changed from: private */
    public ImageView k;
    /* access modifiers changed from: private */
    public RelativeLayout l;
    private NestedScrollView m;
    private AppBarLayout n;
    /* access modifiers changed from: private */
    public CollapsingToolbarLayout o;
    /* access modifiers changed from: private */
    public a p;

    /* renamed from: q reason: collision with root package name */
    private String[] f411q = {"记者简介", "报料内容"};
    /* access modifiers changed from: private */
    public ArrayList<com.flyco.tablayout.a.a> r = new ArrayList<>();
    /* access modifiers changed from: private */
    public CommonTabLayout s;
    /* access modifiers changed from: private */
    public ViewPager t;
    /* access modifiers changed from: private */
    public ReporterPagerAdapter u;
    /* access modifiers changed from: private */
    public ArrayList<BaseSupportFragment> v;
    /* access modifiers changed from: private */
    public boolean w;
    /* access modifiers changed from: private */
    public CircularImage x;
    private int y = 0;
    private int z = 0;

    private enum a {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    public static ReporterDetailFragment a(String str) {
        Bundle bundle = new Bundle();
        ReporterDetailFragment reporterDetailFragment = new ReporterDetailFragment();
        bundle.putString("user_id", str);
        reporterDetailFragment.setArguments(bundle);
        return reporterDetailFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.d = getArguments().getString("user_id");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        com.jaeger.library.a.b(this.c, 0, null);
        com.jaeger.library.a.a((Activity) this.c);
        this.e = layoutInflater.inflate(R.layout.f150item_popupwindows, viewGroup, false);
        h();
        return a(this.e);
    }

    private void h() {
        for (String tabEntity : this.f411q) {
            this.r.add(new TabEntity(tabEntity, 0, 0));
        }
        this.v = new ArrayList<>();
        a(false);
        this.k = (ImageView) this.e.findViewById(R.id.fragment_reporter_detail_img);
        this.l = (RelativeLayout) this.e.findViewById(R.id.fragment_reporter_detail_img_layout);
        this.k.setImageDrawable(getResources().getDrawable(R.mipmap.f250indicatorfour));
        this.x = (CircularImage) this.e.findViewById(R.id.fragment_reporter_avatar);
        this.m = (NestedScrollView) this.e.findViewById(R.id.fragment_reporter_detail_behavior);
        this.f = (ImageView) this.e.findViewById(R.id.toolbar_left_back);
        this.f.setOnClickListener(this);
        this.j = (Toolbar) this.e.findViewById(R.id.fragment_reporter_detail_toolbar);
        ((AppCompatActivity) this.c).setSupportActionBar(this.j);
        this.n = (AppBarLayout) this.e.findViewById(R.id.app_bar);
        this.o = (CollapsingToolbarLayout) this.e.findViewById(R.id.toolbar_layout);
        this.g = (TextView) this.e.findViewById(R.id.toolbar_center_title);
        this.h = (TextView) this.e.findViewById(R.id.fragment_reporter_detail_tv_name);
        this.i = (TextView) this.e.findViewById(R.id.fragment_reporter_detail_tv_type);
        this.s = (CommonTabLayout) this.e.findViewById(R.id.fragment_reporter_detail_tab);
        this.t = (ViewPager) this.e.findViewById(R.id.fragment_reporter_detail_pager);
        this.n.a((b) new b() {
            public void a(AppBarLayout appBarLayout, int i) {
                if (i == 0) {
                    if (ReporterDetailFragment.this.p != a.EXPANDED) {
                        ReporterDetailFragment.this.p = a.EXPANDED;
                        ReporterDetailFragment.this.o.setTitle("");
                    }
                } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                    if (ReporterDetailFragment.this.p != a.COLLAPSED) {
                        ReporterDetailFragment.this.g.setVisibility(0);
                        ReporterDetailFragment.this.o.setTitle("");
                        ReporterDetailFragment.this.l.setVisibility(4);
                        ReporterDetailFragment.this.k.setVisibility(4);
                        ReporterDetailFragment.this.p = a.COLLAPSED;
                    }
                } else if (ReporterDetailFragment.this.p != a.INTERNEDIATE) {
                    if (ReporterDetailFragment.this.p == a.COLLAPSED) {
                        ReporterDetailFragment.this.g.setVisibility(8);
                    }
                    ReporterDetailFragment.this.k.setVisibility(0);
                    ReporterDetailFragment.this.l.setVisibility(0);
                    ReporterDetailFragment.this.o.setTitle("");
                    ReporterDetailFragment.this.p = a.INTERNEDIATE;
                }
            }
        });
        i();
    }

    private void i() {
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=reporter&a=reporter_pub")).params(com.e23.ajn.c.b.a(null)).addParams("userid", this.d).tag(this)).build().execute(new com.e23.ajn.c.a<ReporterDetailResponseModel>() {
            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(final ReporterDetailResponseModel reporterDetailResponseModel, int i) {
                if (reporterDetailResponseModel != null && reporterDetailResponseModel.getCode() == 200 && reporterDetailResponseModel.getData() != null && reporterDetailResponseModel.getData().getUser() != null) {
                    ReporterDetailFragment.this.g.setText(reporterDetailResponseModel.getData().getUser().getTruename());
                    ReporterDetailFragment.this.i.setText(reporterDetailResponseModel.getData().getUser().getReporter_type());
                    ReporterDetailFragment.this.h.setText(reporterDetailResponseModel.getData().getUser().getTruename());
                    if (!TextUtils.isEmpty(reporterDetailResponseModel.getData().getUser().getProreporter_avatar())) {
                        i.a(ReporterDetailFragment.this.c).a(reporterDetailResponseModel.getData().getUser().getProreporter_avatar()).b(com.bumptech.glide.d.b.b.ALL).d((int) R.mipmap.f276plimg_s).c((int) R.mipmap.f276plimg_s).a((ImageView) ReporterDetailFragment.this.x);
                        ReporterDetailFragment.this.k.setVisibility(0);
                        ReporterDetailFragment.this.l.setVisibility(0);
                        ReporterDetailFragment.this.w = true;
                    } else {
                        ReporterDetailFragment.this.k.setVisibility(4);
                        ReporterDetailFragment.this.l.setVisibility(4);
                        ReporterDetailFragment.this.w = false;
                    }
                    ReporterDetailFragment.this.j();
                    ReporterDetailFragment.this.v.clear();
                    ReporterDetailFragment.this.v.add(ReporterIntroFragment.a(reporterDetailResponseModel.getData().getUser().getReporter_intro(), reporterDetailResponseModel.getData().getUser().getUserid()));
                    ReporterDetailFragment.this.v.add(ReporterContentListFragment.a(reporterDetailResponseModel.getData().getUser().getUserid()));
                    ReporterDetailFragment.this.u = new ReporterPagerAdapter(ReporterDetailFragment.this.getChildFragmentManager(), ReporterDetailFragment.this.v);
                    ReporterDetailFragment.this.t.setAdapter(ReporterDetailFragment.this.u);
                    ReporterDetailFragment.this.s.setTabData(ReporterDetailFragment.this.r);
                    ReporterDetailFragment.this.s.setOnTabSelectListener(new com.flyco.tablayout.a.b() {
                        public void a(int i) {
                            ReporterDetailFragment.this.t.setCurrentItem(i);
                        }

                        public void b(int i) {
                        }
                    });
                    ReporterDetailFragment.this.t.setOnPageChangeListener(ReporterDetailFragment.this);
                    ReporterDetailFragment.this.x.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            ReporterDetailFragment.this.a((d) ReporterHeadShowFragment.a(reporterDetailResponseModel.getData().getUser().getProreporter_avatar()));
                        }
                    });
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                k.a();
            }

            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                k.a(ReporterDetailFragment.this.c);
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_back /*2131820915*/:
                this.c.onBackPressed();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        TypedValue typedValue = new TypedValue();
        if (this.c.getTheme().resolveAttribute(16843499, typedValue, true)) {
            this.A = TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
        }
        this.z = this.A + ((int) getResources().getDimension(R.dimen.statusbar_view_height));
        if (this.w) {
            this.y = (q.b(this.c) * 7) / 10;
        } else {
            this.y = this.A + ((int) getResources().getDimension(R.dimen.statusbar_view_height));
        }
        k();
    }

    private void k() {
        int i2 = this.y;
        Log.d("高度", "imgHeight：" + this.y + "，actionBarHeight：" + this.z + "，appBarHeight：" + i2);
        LayoutParams layoutParams = new LayoutParams(-1, this.y);
        layoutParams.a(2);
        layoutParams.a(0.7f);
        this.l.setLayoutParams(layoutParams);
        this.k.setLayoutParams(new RelativeLayout.LayoutParams(-1, this.y));
        LayoutParams layoutParams2 = new LayoutParams(-1, this.z);
        layoutParams2.a(1);
        this.j.setLayoutParams(layoutParams2);
        this.n.setLayoutParams(new CoordinatorLayout.d(-1, i2));
        this.n.setVisibility(0);
        this.m.setVisibility(0);
    }

    public void onPageScrolled(int i2, float f2, int i3) {
    }

    public void onPageSelected(int i2) {
        this.s.setCurrentTab(i2);
    }

    public void onPageScrollStateChanged(int i2) {
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
