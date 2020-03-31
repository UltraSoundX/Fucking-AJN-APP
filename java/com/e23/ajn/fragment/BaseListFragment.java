package com.e23.ajn.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.i;
import android.support.v7.widget.RecyclerView.l;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.e23.ajn.R;
import com.e23.ajn.base.BaseSwipeBackFragment;
import com.e23.ajn.c.b;
import com.e23.ajn.views.k;
import com.jaeger.library.a;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;
import java.util.Map;

public abstract class BaseListFragment extends BaseSwipeBackFragment implements OnRefreshListener, RequestLoadMoreListener {
    protected RelativeLayout d;
    protected RecyclerView e;
    protected SwipeRefreshLayout f;
    protected View g;
    protected View h;
    protected TextView i;
    protected ImageView j;
    protected boolean k = false;
    protected LinearLayout l;
    protected Toolbar m;
    protected int n = 1;
    private View o;

    public abstract void a(BaseQuickAdapter baseQuickAdapter, View view, int i2);

    public abstract Map<String, String> b(boolean z);

    public abstract Callback c(boolean z);

    public abstract boolean h();

    public abstract String i();

    public abstract BaseQuickAdapter j();

    public abstract String k();

    public abstract void l();

    public abstract boolean m();

    public abstract i n();

    public abstract boolean o();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.o = layoutInflater.inflate(R.layout.f130fragment_dingyue, viewGroup, false);
        q();
        return a(this.o);
    }

    public View p() {
        return this.o;
    }

    /* access modifiers changed from: protected */
    public void q() {
        this.l = (LinearLayout) this.o.findViewById(R.id.toolbar_layout);
        this.m = (Toolbar) this.o.findViewById(R.id.toolbar);
        this.i = (TextView) this.m.findViewById(R.id.toolbar_center_title);
        this.j = (ImageView) this.m.findViewById(R.id.toolbar_left_back);
        this.j.setVisibility(0);
        this.j.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseListFragment.this.c.onBackPressed();
            }
        });
        a(m());
        if (h()) {
            a.b(this.c, 0, null);
            a.a((Activity) this.c);
            this.l.setVisibility(0);
            if (!TextUtils.isEmpty(i())) {
                this.i.setVisibility(0);
                this.i.setText(i());
            }
        } else {
            this.l.setVisibility(8);
        }
        this.d = (RelativeLayout) this.o.findViewById(R.id.fragment_base_list_ll);
        this.f = (SwipeRefreshLayout) this.o.findViewById(R.id.fragment_base_list_swipeLayout);
        this.f.setOnRefreshListener(this);
        this.f.setColorSchemeColors(Color.rgb(47, ErrorCode.EXCEED_LZMA_RETRY_NUM, 189));
        this.e = (RecyclerView) this.o.findViewById(R.id.fragment_base_list);
        this.e.setHasFixedSize(true);
        this.e.setLayoutManager(n());
        this.g = this.c.getLayoutInflater().inflate(R.layout.design_text_input_password_icon, (ViewGroup) this.e.getParent(), false);
        this.g.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                k.a(BaseListFragment.this.c);
                BaseListFragment.this.onRefresh();
            }
        });
        this.h = this.c.getLayoutInflater().inflate(R.layout.f125dingyue_header, (ViewGroup) this.e.getParent(), false);
        this.h.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                k.a(BaseListFragment.this.c);
                BaseListFragment.this.onRefresh();
            }
        });
        j().setOnLoadMoreListener(this, this.e);
        this.e.setAdapter(j());
        this.e.a((l) new OnItemClickListener() {
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BaseListFragment.this.a(baseQuickAdapter, view, i);
            }
        });
        if (!o()) {
            l();
        }
    }

    public void c() {
        super.c();
        if (o()) {
            l();
        }
    }

    public void onRefresh() {
        d(false);
    }

    public void onLoadMoreRequested() {
        if (j() == null) {
            return;
        }
        if (j().getData().size() >= 20) {
            d(true);
        } else {
            j().loadMoreEnd(this.k);
        }
    }

    private void d(boolean z) {
        if (!z) {
            this.n = 1;
        }
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url(k())).tag(this)).params(b.a(b(z))).addParams("page", Integer.toString(this.n)).addParams("num", Integer.toString(20)).build().execute(c(z));
    }
}
