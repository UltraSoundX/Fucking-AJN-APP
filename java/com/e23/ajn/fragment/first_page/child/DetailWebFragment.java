package com.e23.ajn.fragment.first_page.child;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.l;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener;
import com.chad.library.adapter.base.BaseQuickAdapter.SpanSizeLookup;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.e23.ajn.R;
import com.e23.ajn.activity.ImageShowActivity;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.adapter.DetailPicAdapter;
import com.e23.ajn.adapter.NewsCommentListAdapter;
import com.e23.ajn.b.c;
import com.e23.ajn.b.h;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.d.f;
import com.e23.ajn.d.o;
import com.e23.ajn.d.p;
import com.e23.ajn.d.q;
import com.e23.ajn.model.CommentBean;
import com.e23.ajn.model.CommentListResponseModel;
import com.e23.ajn.model.DetailStatusResponseModel;
import com.e23.ajn.model.NewsFoundationBean;
import com.e23.ajn.views.CirleImageView;
import com.e23.ajn.views.ProgressWebView;
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.e;
import com.e23.ajn.views.k;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import me.yokeyword.fragmentation.d;
import okhttp3.Call;
import okhttp3.Request;
import org.greenrobot.eventbus.j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class DetailWebFragment extends BaseSupportFragment implements OnRefreshListener, OnClickListener, RequestLoadMoreListener {
    protected static final LayoutParams d = new LayoutParams(-1, -1);
    /* access modifiers changed from: private */
    public int A;
    private e B;
    /* access modifiers changed from: private */
    public RelativeLayout C;
    /* access modifiers changed from: private */
    public RelativeLayout D;
    private RelativeLayout E;
    /* access modifiers changed from: private */
    public boolean F = false;
    /* access modifiers changed from: private */
    public int G = 1;
    /* access modifiers changed from: private */
    public View H;
    /* access modifiers changed from: private */
    public TextView I;
    private o J;
    private View K;
    private FrameLayout L;
    private CustomViewCallback M;
    /* access modifiers changed from: private */
    public ArrayList<String> N = new ArrayList<>();
    /* access modifiers changed from: private */
    public String O;
    protected View a;
    private View e;
    private Toolbar f;
    /* access modifiers changed from: private */
    public DetailStatusResponseModel g;
    private String h;
    /* access modifiers changed from: private */
    public TextView i;
    private RelativeLayout j;
    /* access modifiers changed from: private */
    public ProgressWebView k;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout l;
    private RecyclerView m;
    /* access modifiers changed from: private */
    public RelativeLayout n;
    private RelativeLayout o;
    /* access modifiers changed from: private */
    public NewsCommentListAdapter p;

    /* renamed from: q reason: collision with root package name */
    private com.e23.ajn.views.o f402q;
    private ImageView r;
    /* access modifiers changed from: private */
    public ImageView s;
    private ImageView t;
    /* access modifiers changed from: private */
    public ImageView u;
    private ImageView v;
    private TextView w;
    /* access modifiers changed from: private */
    public boolean x = false;
    /* access modifiers changed from: private */
    public boolean y = false;
    /* access modifiers changed from: private */
    public int z;

    static class a extends FrameLayout {
        public a(Context context) {
            super(context);
            setBackgroundColor(context.getResources().getColor(17170444));
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            return true;
        }
    }

    final class b {
        b() {
        }

        @JavascriptInterface
        public String getJson() {
            return DetailWebFragment.this.O;
        }

        @JavascriptInterface
        public void showSource(String str) {
            System.out.println(str);
            Iterator it = Jsoup.parse(str).select("img").iterator();
            while (it.hasNext()) {
                DetailWebFragment.this.N.add(((Element) it.next()).absUrl("data-original"));
            }
        }

        @JavascriptInterface
        public void openImage(String str) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("imgs", DetailWebFragment.this.N);
            bundle.putString("cimg", str);
            if (DetailWebFragment.this.g == null || DetailWebFragment.this.g.getNews() == null) {
                bundle.putString("title", "");
            } else {
                bundle.putString("title", DetailWebFragment.this.g.getNews().getTitle());
            }
            Intent intent = new Intent();
            intent.setClass(DetailWebFragment.this.c, ImageShowActivity.class);
            intent.putExtras(bundle);
            DetailWebFragment.this.startActivity(intent);
        }
    }

    public static DetailWebFragment a(String str) {
        DetailWebFragment detailWebFragment = new DetailWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NEWS_ID", str);
        detailWebFragment.setArguments(bundle);
        return detailWebFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.h = getArguments().getString("NEWS_ID");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.e = layoutInflater.inflate(R.layout.f131fragment_dingyue_header, viewGroup, false);
        i();
        com.e23.ajn.b.e.a(this.c).a((Object) this);
        l();
        return this.e;
    }

    private void i() {
        com.jaeger.library.a.b(this.c, 0, null);
        com.jaeger.library.a.a((Activity) this.c);
        this.f = (Toolbar) this.e.findViewById(R.id.toolbar);
        this.t = (ImageView) this.e.findViewById(R.id.toolbar_left_back);
        this.t.setVisibility(0);
        this.t.setOnClickListener(this);
        this.j = (RelativeLayout) this.e.findViewById(R.id.toolbar_share_layout);
        this.j.setVisibility(0);
        this.j.setOnClickListener(this);
        this.n = (RelativeLayout) this.e.findViewById(R.id.detail_web_bottom_layout);
        this.o = (RelativeLayout) this.e.findViewById(R.id.fragment_detail_web_layout);
        this.i = (TextView) this.f.findViewById(R.id.toolbar_center_title);
        this.i.setVisibility(0);
        this.s = (ImageView) this.e.findViewById(R.id.detail_web_collect);
        this.s.setOnClickListener(this);
        this.r = (ImageView) this.f.findViewById(R.id.toolbar_text_setting);
        this.f402q = new com.e23.ajn.views.o(this.c);
        this.D = (RelativeLayout) this.e.findViewById(R.id.detail_web_comment_layout);
        this.v = (ImageView) this.e.findViewById(R.id.detail_web_comment);
        this.v.setOnClickListener(this);
        this.w = (TextView) this.e.findViewById(R.id.detail_web_input_comment);
        this.w.setOnClickListener(this);
        this.C = (RelativeLayout) this.e.findViewById(R.id.detail_web_praise_layout);
        this.u = (ImageView) this.e.findViewById(R.id.detail_web_praise);
        this.u.setOnClickListener(this);
        this.k = new ProgressWebView(this.c);
        this.o.setVisibility(0);
        this.E = (RelativeLayout) this.e.findViewById(R.id.detail_web_share_layout);
        this.E.setOnClickListener(this);
        WebSettings settings = this.k.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setSupportZoom(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setCacheMode(2);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        this.k.addJavascriptInterface(new b(), "local_obj");
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        this.k.setWebChromeClient(new WebChromeClient() {
            public View getVideoLoadingProgressView() {
                FrameLayout frameLayout = new FrameLayout(DetailWebFragment.this.c);
                frameLayout.setLayoutParams(new GridLayoutManager.LayoutParams(-1, -1));
                return frameLayout;
            }

            public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
                DetailWebFragment.this.a(view, customViewCallback);
            }

            public void onHideCustomView() {
                DetailWebFragment.this.n();
            }
        });
        this.k.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Intent intent = new Intent(DetailWebFragment.this.c, SwipeBackCommonActivity.class);
                intent.putExtra(SwipeBackCommonActivity.TAG, 1);
                intent.putExtra(SwipeBackCommonActivity.URL, str);
                DetailWebFragment.this.startActivity(intent);
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                webView.getSettings().setBlockNetworkImage(false);
                webView.loadUrl("javascript:window.local_obj.showSource(''+document.getElementById('content').innerHTML+'');");
                webView.loadUrl("javascript:(function(){var objs = document.getElementById('content').getElementsByTagName(\"img\"); for(var i=0;i<objs.length;i++)  {    objs[i].onclick=function()      {          window.local_obj.openImage(this.src);      }  }})()");
                try {
                    if (DetailWebFragment.this.g.getNews().getNorb().equals(NewsFoundationBean.BBS)) {
                        ((TextView) DetailWebFragment.this.H.findViewById(R.id.bbs_detail_title)).setText(DetailWebFragment.this.g.getNews().getTitle());
                        i.a(DetailWebFragment.this.c).a(DetailWebFragment.this.g.getMore().getPosteravatar()).h().b(com.bumptech.glide.d.b.b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) (CirleImageView) DetailWebFragment.this.H.findViewById(R.id.bbs_detail_avatar));
                        ((TextView) DetailWebFragment.this.H.findViewById(R.id.bbs_detail_username)).setText(DetailWebFragment.this.g.getNews().getPostername());
                        ((TextView) DetailWebFragment.this.H.findViewById(R.id.bbs_detail_time)).setText(DetailWebFragment.this.g.getNews().getInputtime());
                        if (DetailWebFragment.this.g.getNews().getThumbs().size() > 0) {
                            RecyclerView recyclerView = (RecyclerView) DetailWebFragment.this.H.findViewById(R.id.bbs_detail_pic);
                            recyclerView.setVisibility(0);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setNestedScrollingEnabled(false);
                            recyclerView.setLayoutManager(new GridLayoutManager(DetailWebFragment.this.c, 6));
                            DetailPicAdapter detailPicAdapter = new DetailPicAdapter(DetailWebFragment.this.c, DetailWebFragment.this.g.getNews().getThumbs(), true, DetailWebFragment.this.g.getNews().getTitle());
                            detailPicAdapter.setSpanSizeLookup(new SpanSizeLookup() {
                                public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                                    if (DetailWebFragment.this.g.getNews().getThumbs().size() == 1) {
                                        return 6;
                                    }
                                    if (DetailWebFragment.this.g.getNews().getThumbs().size() == 2 || DetailWebFragment.this.g.getNews().getThumbs().size() == 4) {
                                        return 3;
                                    }
                                    return 2;
                                }
                            });
                            recyclerView.setAdapter(detailPicAdapter);
                        }
                        DetailWebFragment.this.p.addHeaderView(DetailWebFragment.this.H, 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DetailWebFragment.this.k.setFocusable(false);
                DetailWebFragment.this.onRefresh();
            }
        });
        this.k.setScrollBarStyle(33554432);
        this.l = (SwipeRefreshLayout) this.e.findViewById(R.id.fragment_detail_web_swipeLayout);
        this.m = (RecyclerView) this.e.findViewById(R.id.fragment_detail_web_list);
        this.l.setOnRefreshListener(this);
        this.m.setFocusableInTouchMode(false);
        this.l.setColorSchemeColors(Color.rgb(47, ErrorCode.EXCEED_LZMA_RETRY_NUM, 189));
        this.m.setHasFixedSize(true);
        this.m.setLayoutManager(new WrapContentLinearLayoutManager(this.c));
        this.p = new NewsCommentListAdapter(this.c, null);
        this.p.setOnLoadMoreListener(this, this.m);
        this.m.setAdapter(this.p);
        this.m.a((l) new OnItemClickListener() {
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (!((CommentBean) DetailWebFragment.this.p.getData().get(i)).getReplysum().equals("0")) {
                    DetailWebFragment.this.a((d) CommentDetailFragment.a((CommentBean) DetailWebFragment.this.p.getData().get(i)));
                } else if (DetailWebFragment.this.g != null && DetailWebFragment.this.g.getNews() != null) {
                    DetailWebFragment.this.a(new String[]{DetailWebFragment.this.g.getNews().getId(), DetailWebFragment.this.g.getNews().getThumb(), DetailWebFragment.this.g.getNews().getTitle(), DetailWebFragment.this.g.getNews().getPosterid(), DetailWebFragment.this.g.getNews().getNewstype(), ((CommentBean) DetailWebFragment.this.p.getData().get(i)).getId(), ((CommentBean) DetailWebFragment.this.p.getData().get(i)).getUserid(), ((CommentBean) DetailWebFragment.this.p.getData().get(i)).getUsername(), f.d(((CommentBean) DetailWebFragment.this.p.getData().get(i)).getContent())});
                }
            }
        });
        this.p.addHeaderView(this.k);
        this.H = this.c.getLayoutInflater().inflate(R.layout.f177myvisit, (ViewGroup) this.m.getParent(), false);
        this.I = (TextView) this.H.findViewById(R.id.comment_head_count);
        this.a = this.c.getLayoutInflater().inflate(R.layout.design_text_input_password_icon, (ViewGroup) this.m.getParent(), false);
        TextView textView = (TextView) this.a.findViewById(R.id.empty_view_tip);
        ImageView imageView = (ImageView) this.a.findViewById(R.id.empty_view_img);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.f56ssdk_oks_classic_tencentweibo));
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = (int) (((double) q.b(this.c)) + 0.75d);
        layoutParams.height = (int) (((double) q.b(this.c)) + 0.56d);
        imageView.setLayoutParams(layoutParams);
        textView.setVisibility(8);
    }

    public void onResume() {
        super.onResume();
        this.k.onResume();
    }

    public void onRefresh() {
        b(false);
    }

    public void onLoadMoreRequested() {
        if (this.p.getData().size() >= 20) {
            b(true);
        } else {
            this.p.loadMoreEnd(this.F);
        }
    }

    public void onClick(View view) {
        boolean z2 = true;
        switch (view.getId()) {
            case R.id.detail_web_input_comment /*2131820875*/:
                if (this.g != null && this.g.getNews() != null) {
                    a(new String[]{this.g.getNews().getId(), this.g.getNews().getThumb(), this.g.getNews().getTitle(), this.g.getNews().getPosterid(), this.g.getNews().getNewstype(), "0", "0", "", ""});
                    return;
                }
                return;
            case R.id.detail_web_comment /*2131820877*/:
                if (this.A == 0) {
                    k.a(this.c, getString(R.string.no_comment));
                    return;
                } else {
                    this.m.a(1);
                    return;
                }
            case R.id.detail_web_praise /*2131820879*/:
                if (this.y) {
                    k.a(this.c, "您已赞过！");
                    return;
                } else {
                    m();
                    return;
                }
            case R.id.detail_web_collect /*2131820880*/:
                if (!p.a("is_logined", false)) {
                    h();
                    return;
                }
                if (this.x) {
                    z2 = false;
                }
                a(z2);
                return;
            case R.id.detail_web_share_layout /*2131820881*/:
                j();
                return;
            case R.id.toolbar_left_back /*2131820915*/:
                this.c.onBackPressed();
                return;
            case R.id.toolbar_share_layout /*2131821138*/:
                j();
                return;
            default:
                return;
        }
    }

    private void j() {
        try {
            if (this.f402q != null && !this.f402q.isShowing()) {
                String str = this.g.getMore().getSharedomain() + "/index.php?m=content&c=index&a=show&catid=" + this.g.getNews().getCatid() + "&id=" + this.g.getNews().getId() + "&fx=1";
                if (this.g.getNews().getNorb().equals(NewsFoundationBean.ZHI_BO)) {
                    str = this.g.getNews().getUrl();
                }
                this.f402q.a(this.g.getNews().getTitle(), this.g.getNews().getDescription(), this.g.getNews().getThumb(), str);
                this.f402q.show();
            }
        } catch (Exception e2) {
        }
    }

    /* access modifiers changed from: private */
    public void a(String[] strArr) {
        if (this.B == null) {
            this.B = new e(this.c, strArr, new com.e23.ajn.views.e.a() {
                public void a(CommentBean commentBean) {
                    DetailWebFragment.this.A = DetailWebFragment.this.A + 1;
                    int i = 0;
                    Iterator it = DetailWebFragment.this.p.getData().iterator();
                    while (true) {
                        int i2 = i;
                        if (!it.hasNext()) {
                            DetailWebFragment.this.p.addData(i2, commentBean);
                            DetailWebFragment.this.p.notifyDataSetChanged();
                            return;
                        } else if (((CommentBean) it.next()).getHot().equals("1")) {
                            i = i2 + 1;
                        } else {
                            i = i2;
                        }
                    }
                }
            });
        } else {
            this.B.a(strArr);
        }
        this.B.show();
    }

    /* access modifiers changed from: private */
    public void k() {
        if (this.g != null && this.g.getNews() != null) {
            com.e23.ajn.d.b.a(this.c, this.g.getNews().getId(), this.g.getNews().getCatid());
        }
    }

    private void l() {
        this.l.setRefreshing(true);
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=content&a=show")).params(com.e23.ajn.c.b.a(null)).addParams(Config.FEED_LIST_ITEM_CUSTOM_ID, this.h).tag(this)).build().execute(new com.e23.ajn.c.a<DetailStatusResponseModel>() {
            public void onError(Call call, Exception exc, int i) {
                DetailWebFragment.this.l.setRefreshing(false);
                DetailWebFragment.this.p.setEmptyView(DetailWebFragment.this.a);
                DetailWebFragment.this.n.setVisibility(8);
            }

            /* renamed from: a */
            public void onResponse(DetailStatusResponseModel detailStatusResponseModel, int i) {
                if (detailStatusResponseModel != null) {
                    try {
                        if (detailStatusResponseModel.getNews() != null && detailStatusResponseModel.getCode() == 200) {
                            DetailWebFragment.this.g = detailStatusResponseModel;
                            DetailWebFragment.this.O = com.e23.ajn.d.l.a(detailStatusResponseModel);
                            if (DetailWebFragment.this.g.getNews().getNorb().equals(NewsFoundationBean.NEWS)) {
                                DetailWebFragment.this.k.loadUrl("file:///android_asset/html/index.html");
                            } else if (DetailWebFragment.this.g.getNews().getNorb().equals(NewsFoundationBean.ZHI_BO)) {
                                DetailWebFragment.this.k.loadUrl(DetailWebFragment.this.g.getNews().getUrl());
                            } else {
                                DetailWebFragment.this.k.loadUrl("file:///android_asset/html/hd.html");
                            }
                            DetailWebFragment.this.i.setText(detailStatusResponseModel.getMore().getCatname());
                            DetailWebFragment.this.y = detailStatusResponseModel.getMore().getIsding().equals("1");
                            DetailWebFragment.this.x = detailStatusResponseModel.getMore().getIscell().equals("1");
                            if (DetailWebFragment.this.y) {
                                DetailWebFragment.this.u.setBackgroundResource(R.mipmap.f228dingimg2_b);
                            } else {
                                DetailWebFragment.this.u.setBackgroundResource(R.mipmap.f227dingimg2);
                            }
                            if (DetailWebFragment.this.x) {
                                DetailWebFragment.this.s.setBackgroundResource(R.mipmap.f268menu_right_nav_btn);
                            } else {
                                DetailWebFragment.this.s.setBackgroundResource(R.mipmap.f267mall_sum_decrease_selected);
                            }
                            DetailWebFragment.this.A = Integer.parseInt(detailStatusResponseModel.getNews().getPlsum());
                            DetailWebFragment.this.z = Integer.parseInt(detailStatusResponseModel.getNews().getDing());
                            DetailWebFragment.this.a((View) DetailWebFragment.this.D, DetailWebFragment.this.A);
                            DetailWebFragment.this.I.setText("全部评论（" + DetailWebFragment.this.A + "条）");
                            DetailWebFragment.this.a((View) DetailWebFragment.this.C, DetailWebFragment.this.z);
                            if (p.a("is_logined", false)) {
                                DetailWebFragment.this.k();
                                return;
                            }
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                DetailWebFragment.this.l.setRefreshing(false);
                DetailWebFragment.this.p.setEmptyView(DetailWebFragment.this.a);
                DetailWebFragment.this.n.setVisibility(8);
            }

            public void onAfter(int i) {
                super.onAfter(i);
            }

            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(View view, int i2) {
        new q.rorbin.badgeview.e(view.getContext()).a(view).b(this.c.getResources().getColor(R.color.colorRed2)).d(8388661).a(0.0f, 8.0f, true).a(true).c(view.getContext().getResources().getColor(R.color.colorWhite)).b(2.0f, true).a(10.0f, true).a(i2);
    }

    private void m() {
        if (this.J == null) {
            this.J = new o();
        }
        if (this.g != null && this.g.getNews() != null) {
            this.J.b(this.c, this.h, this.g.getNews().getCatid(), "ding", new com.e23.ajn.d.o.a() {
                public void a(boolean z, String str) {
                    DetailWebFragment.this.u.setBackgroundResource(R.mipmap.f228dingimg2_b);
                    DetailWebFragment.this.y = !DetailWebFragment.this.y;
                    if (DetailWebFragment.this.y) {
                        DetailWebFragment.this.z = DetailWebFragment.this.z + 1;
                    }
                    DetailWebFragment.this.a((View) DetailWebFragment.this.C, DetailWebFragment.this.z);
                    if (p.a("is_logined", false)) {
                        com.e23.ajn.d.a.a("zan", DetailWebFragment.this.c);
                    }
                }
            });
        }
    }

    public void h() {
        Intent intent = new Intent(this.c, SwipeBackCommonActivity.class);
        intent.putExtra(SwipeBackCommonActivity.TAG, 21);
        startActivity(intent);
    }

    private void a(boolean z2) {
        if (!p.a("is_logined", false)) {
            h();
            return;
        }
        if (this.J == null) {
            this.J = new o();
        }
        if (this.g != null && this.g.getNews() != null) {
            this.J.a(this.c, this.h, this.g.getNews().getCatid(), z2 ? "addtocell" : "cancelcell", new com.e23.ajn.d.o.a() {
                public void a(boolean z, String str) {
                    if (z) {
                        DetailWebFragment.this.s.setBackgroundResource(R.mipmap.f268menu_right_nav_btn);
                    } else {
                        DetailWebFragment.this.s.setBackgroundResource(R.mipmap.f267mall_sum_decrease_selected);
                    }
                    DetailWebFragment.this.x = z;
                    com.e23.ajn.b.e.a(DetailWebFragment.this.c).c(new c());
                }
            });
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.k.destroy();
        com.e23.ajn.b.e.a(this.c).b(this);
    }

    @j
    public void onTextSizeChangedEvent(com.e23.ajn.b.p pVar) {
        if (this.k != null) {
            this.k.loadUrl("javascript:set_font_size(\"" + pVar.a() + "\")");
        }
    }

    @j
    public void onRefreshDetailEvent(h hVar) {
        l();
        k();
    }

    private void b(final boolean z2) {
        if (!z2) {
            this.G = 1;
        }
        try {
            ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=comment&a=getcomment_pub")).tag(this)).params(com.e23.ajn.c.b.a(null)).addParams("catid", this.g.getNews().getCatid()).addParams("newsid", this.h).addParams("page", Integer.toString(this.G)).addParams("num", Integer.toString(20)).build().execute(new com.e23.ajn.c.a<CommentListResponseModel>() {
                public void onBefore(Request request, int i) {
                    super.onBefore(request, i);
                    if (z2) {
                        DetailWebFragment.this.l.setEnabled(false);
                    } else {
                        DetailWebFragment.this.p.setEnableLoadMore(false);
                    }
                }

                public void onAfter(int i) {
                    super.onAfter(i);
                    if (z2) {
                        DetailWebFragment.this.l.setEnabled(true);
                        return;
                    }
                    DetailWebFragment.this.l.setRefreshing(false);
                    if (DetailWebFragment.this.p.getData().size() >= 20) {
                        DetailWebFragment.this.p.setEnableLoadMore(true);
                    }
                }

                public void onError(Call call, Exception exc, int i) {
                    if (z2) {
                        DetailWebFragment.this.p.loadMoreFail();
                    } else {
                        DetailWebFragment.this.p.setNewData(null);
                    }
                }

                /* renamed from: a */
                public void onResponse(CommentListResponseModel commentListResponseModel, int i) {
                    int i2;
                    if (commentListResponseModel == null || commentListResponseModel.getCode() != 200) {
                        if (z2) {
                            DetailWebFragment.this.p.loadMoreFail();
                        } else {
                            DetailWebFragment.this.p.setNewData(null);
                        }
                    } else if (com.e23.ajn.d.e.b(commentListResponseModel.getData())) {
                        if (z2) {
                            DetailWebFragment.this.p.addData((Collection<? extends T>) commentListResponseModel.getData());
                            DetailWebFragment.this.p.loadMoreComplete();
                        } else {
                            int i3 = 0;
                            Iterator it = commentListResponseModel.getData().iterator();
                            while (true) {
                                i2 = i3;
                                if (!it.hasNext()) {
                                    break;
                                } else if (((CommentBean) it.next()).getHot().equals("1")) {
                                    i3 = i2 + 1;
                                } else {
                                    i3 = i2;
                                }
                            }
                            DetailWebFragment.this.p.a(i2);
                            DetailWebFragment.this.p.setNewData(commentListResponseModel.getData());
                        }
                        if (commentListResponseModel.getData().size() < 20) {
                            DetailWebFragment.this.p.loadMoreEnd(DetailWebFragment.this.F);
                        } else {
                            DetailWebFragment.this.G = DetailWebFragment.this.G + 1;
                        }
                    } else if (z2) {
                        DetailWebFragment.this.p.loadMoreEnd(DetailWebFragment.this.F);
                    } else {
                        DetailWebFragment.this.p.setNewData(null);
                    }
                }
            });
        } catch (Exception e2) {
        }
    }

    /* access modifiers changed from: private */
    public void a(View view, CustomViewCallback customViewCallback) {
        if (this.K != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        this.c.getWindow().getDecorView();
        FrameLayout frameLayout = (FrameLayout) this.c.getWindow().getDecorView();
        this.L = new a(this.c);
        this.L.addView(view, d);
        frameLayout.addView(this.L, d);
        this.K = view;
        c(false);
        this.M = customViewCallback;
    }

    /* access modifiers changed from: private */
    public void n() {
        if (this.K != null) {
            c(true);
            ((FrameLayout) this.c.getWindow().getDecorView()).removeView(this.L);
            this.L = null;
            this.K = null;
            this.M.onCustomViewHidden();
            this.k.setVisibility(0);
        }
    }

    private void c(boolean z2) {
        int i2;
        if (z2) {
            i2 = 0;
        } else {
            i2 = 1024;
        }
        this.c.getWindow().setFlags(i2, 1024);
    }
}
