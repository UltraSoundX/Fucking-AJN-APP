package com.e23.ajn.fragment.first_page.child;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.X5WebView;
import com.e23.ajn.views.e;
import com.e23.ajn.views.k;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebSettings.PluginState;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
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

public class TestDetailWebFragment extends BaseSupportFragment implements OnRefreshListener, OnClickListener, RequestLoadMoreListener {
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
    public X5WebView k;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout l;
    private RecyclerView m;
    /* access modifiers changed from: private */
    public RelativeLayout n;
    private RelativeLayout o;
    /* access modifiers changed from: private */
    public NewsCommentListAdapter p;

    /* renamed from: q reason: collision with root package name */
    private com.e23.ajn.views.o f406q;
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
            return TestDetailWebFragment.this.O;
        }

        @JavascriptInterface
        public void showSource(String str) {
            Iterator it = Jsoup.parse(str).select("img").iterator();
            while (it.hasNext()) {
                TestDetailWebFragment.this.N.add(((Element) it.next()).absUrl("data-original"));
            }
        }

        @JavascriptInterface
        public void openImage(String str) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("imgs", TestDetailWebFragment.this.N);
            bundle.putString("cimg", str);
            bundle.putString("title", TestDetailWebFragment.this.g.getNews().getTitle());
            Intent intent = new Intent();
            intent.setClass(TestDetailWebFragment.this.c, ImageShowActivity.class);
            intent.putExtras(bundle);
            TestDetailWebFragment.this.startActivity(intent);
        }
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
        this.f406q = new com.e23.ajn.views.o(this.c);
        this.D = (RelativeLayout) this.e.findViewById(R.id.detail_web_comment_layout);
        this.v = (ImageView) this.e.findViewById(R.id.detail_web_comment);
        this.v.setOnClickListener(this);
        this.w = (TextView) this.e.findViewById(R.id.detail_web_input_comment);
        this.w.setOnClickListener(this);
        this.C = (RelativeLayout) this.e.findViewById(R.id.detail_web_praise_layout);
        this.u = (ImageView) this.e.findViewById(R.id.detail_web_praise);
        this.u.setOnClickListener(this);
        this.k = new X5WebView(this.c);
        this.o.setVisibility(0);
        this.E = (RelativeLayout) this.e.findViewById(R.id.detail_web_share_layout);
        this.E.setOnClickListener(this);
        WebSettings settings = this.k.getSettings();
        settings.setAllowFileAccess(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setSupportMultipleWindows(false);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setPluginState(PluginState.ON_DEMAND);
        this.k.addJavascriptInterface(new b(), "local_obj");
        this.k.setWebChromeClient(new WebChromeClient() {
            public void onHideCustomView() {
                TestDetailWebFragment.this.n();
            }

            public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
                TestDetailWebFragment.this.a(view, customViewCallback);
            }

            public View getVideoLoadingProgressView() {
                FrameLayout frameLayout = new FrameLayout(TestDetailWebFragment.this.c);
                frameLayout.setLayoutParams(new GridLayoutManager.LayoutParams(-1, -1));
                return frameLayout;
            }
        });
        this.k.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Intent intent = new Intent(TestDetailWebFragment.this.c, SwipeBackCommonActivity.class);
                intent.putExtra(SwipeBackCommonActivity.TAG, 1);
                intent.putExtra(SwipeBackCommonActivity.URL, str);
                TestDetailWebFragment.this.startActivity(intent);
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                webView.getSettings().setBlockNetworkImage(false);
                webView.loadUrl("javascript:window.local_obj.showSource(''+document.getElementById('content').innerHTML+'');");
                webView.loadUrl("javascript:(function(){var objs = document.getElementById('content').getElementsByTagName(\"img\"); for(var i=0;i<objs.length;i++)  {    objs[i].onclick=function()      {          window.local_obj.openImage(this.src);      }  }})()");
                if (TestDetailWebFragment.this.g.getNews().getNorb().equals(NewsFoundationBean.BBS)) {
                    ((TextView) TestDetailWebFragment.this.H.findViewById(R.id.bbs_detail_title)).setText(TestDetailWebFragment.this.g.getNews().getTitle());
                    i.a(TestDetailWebFragment.this.c).a(TestDetailWebFragment.this.g.getMore().getPosteravatar()).h().b(com.bumptech.glide.d.b.b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) (CirleImageView) TestDetailWebFragment.this.H.findViewById(R.id.bbs_detail_avatar));
                    ((TextView) TestDetailWebFragment.this.H.findViewById(R.id.bbs_detail_username)).setText(TestDetailWebFragment.this.g.getNews().getPostername());
                    ((TextView) TestDetailWebFragment.this.H.findViewById(R.id.bbs_detail_time)).setText(TestDetailWebFragment.this.g.getNews().getInputtime());
                    if (TestDetailWebFragment.this.g.getNews().getThumbs().size() > 0) {
                        RecyclerView recyclerView = (RecyclerView) TestDetailWebFragment.this.H.findViewById(R.id.bbs_detail_pic);
                        recyclerView.setVisibility(0);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setNestedScrollingEnabled(false);
                        recyclerView.setLayoutManager(new GridLayoutManager(TestDetailWebFragment.this.c, 6));
                        DetailPicAdapter detailPicAdapter = new DetailPicAdapter(TestDetailWebFragment.this.c, TestDetailWebFragment.this.g.getNews().getThumbs(), true, TestDetailWebFragment.this.g.getNews().getTitle());
                        detailPicAdapter.setSpanSizeLookup(new SpanSizeLookup() {
                            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                                if (TestDetailWebFragment.this.g.getNews().getThumbs().size() == 1) {
                                    return 6;
                                }
                                if (TestDetailWebFragment.this.g.getNews().getThumbs().size() == 2 || TestDetailWebFragment.this.g.getNews().getThumbs().size() == 4) {
                                    return 3;
                                }
                                return 2;
                            }
                        });
                        recyclerView.setAdapter(detailPicAdapter);
                    }
                    TestDetailWebFragment.this.p.addHeaderView(TestDetailWebFragment.this.H, 0);
                }
                TestDetailWebFragment.this.k.setFocusable(false);
                TestDetailWebFragment.this.onRefresh();
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
                if (((CommentBean) TestDetailWebFragment.this.p.getData().get(i)).getReplysum().equals("0")) {
                    TestDetailWebFragment.this.a(new String[]{TestDetailWebFragment.this.g.getNews().getId(), TestDetailWebFragment.this.g.getNews().getThumb(), TestDetailWebFragment.this.g.getNews().getTitle(), TestDetailWebFragment.this.g.getNews().getPosterid(), TestDetailWebFragment.this.g.getNews().getNewstype(), ((CommentBean) TestDetailWebFragment.this.p.getData().get(i)).getId(), ((CommentBean) TestDetailWebFragment.this.p.getData().get(i)).getUserid(), ((CommentBean) TestDetailWebFragment.this.p.getData().get(i)).getUsername(), f.d(((CommentBean) TestDetailWebFragment.this.p.getData().get(i)).getContent())});
                    return;
                }
                TestDetailWebFragment.this.a((d) CommentDetailFragment.a((CommentBean) TestDetailWebFragment.this.p.getData().get(i)));
            }
        });
        this.p.addHeaderView(this.k, 0, 1);
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
        if (this.f406q != null && !this.f406q.isShowing()) {
            String str = "http://" + this.g.getMore().getSharedomain() + "/index.php?m=content&c=index&a=show&catid=" + this.g.getNews().getCatid() + "&id=" + this.g.getNews().getId() + "&fx=1";
            if (this.g.getNews().getNorb().equals(NewsFoundationBean.ZHI_BO)) {
                str = this.g.getNews().getUrl();
            }
            this.f406q.a(this.g.getNews().getTitle(), this.g.getNews().getDescription(), this.g.getNews().getThumb(), str);
            this.f406q.show();
        }
    }

    /* access modifiers changed from: private */
    public void a(String[] strArr) {
        if (this.B == null) {
            this.B = new e(this.c, strArr, new com.e23.ajn.views.e.a() {
                public void a(CommentBean commentBean) {
                    TestDetailWebFragment.this.A = TestDetailWebFragment.this.A + 1;
                    int i = 0;
                    Iterator it = TestDetailWebFragment.this.p.getData().iterator();
                    while (true) {
                        int i2 = i;
                        if (!it.hasNext()) {
                            TestDetailWebFragment.this.p.addData(i2, commentBean);
                            TestDetailWebFragment.this.p.notifyDataSetChanged();
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
        com.e23.ajn.d.b.a(this.c, this.g.getNews().getId(), this.g.getNews().getCatid());
    }

    private void l() {
        this.l.setRefreshing(true);
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=content&a=show")).params(com.e23.ajn.c.b.a(null)).addParams(Config.FEED_LIST_ITEM_CUSTOM_ID, this.h).tag(this)).build().execute(new com.e23.ajn.c.a<DetailStatusResponseModel>() {
            public void onError(Call call, Exception exc, int i) {
                TestDetailWebFragment.this.l.setRefreshing(false);
                TestDetailWebFragment.this.p.setEmptyView(TestDetailWebFragment.this.a);
                TestDetailWebFragment.this.n.setVisibility(8);
            }

            /* renamed from: a */
            public void onResponse(DetailStatusResponseModel detailStatusResponseModel, int i) {
                if (detailStatusResponseModel == null || detailStatusResponseModel.getNews() == null || detailStatusResponseModel.getCode() != 200) {
                    TestDetailWebFragment.this.l.setRefreshing(false);
                    TestDetailWebFragment.this.p.setEmptyView(TestDetailWebFragment.this.a);
                    TestDetailWebFragment.this.n.setVisibility(8);
                    return;
                }
                TestDetailWebFragment.this.g = detailStatusResponseModel;
                TestDetailWebFragment.this.O = com.e23.ajn.d.l.a(detailStatusResponseModel);
                if (TestDetailWebFragment.this.g.getNews().getNorb().equals(NewsFoundationBean.NEWS)) {
                    TestDetailWebFragment.this.k.loadUrl("file:///android_asset/html/index.html");
                } else if (TestDetailWebFragment.this.g.getNews().getNorb().equals(NewsFoundationBean.ZHI_BO)) {
                    TestDetailWebFragment.this.k.loadUrl(TestDetailWebFragment.this.g.getNews().getUrl());
                } else {
                    TestDetailWebFragment.this.k.loadUrl("file:///android_asset/html/hd.html");
                }
                TestDetailWebFragment.this.i.setText(detailStatusResponseModel.getMore().getCatname());
                TestDetailWebFragment.this.y = detailStatusResponseModel.getMore().getIsding().equals("1");
                TestDetailWebFragment.this.x = detailStatusResponseModel.getMore().getIscell().equals("1");
                if (TestDetailWebFragment.this.y) {
                    TestDetailWebFragment.this.u.setBackgroundResource(R.mipmap.f228dingimg2_b);
                } else {
                    TestDetailWebFragment.this.u.setBackgroundResource(R.mipmap.f227dingimg2);
                }
                if (TestDetailWebFragment.this.x) {
                    TestDetailWebFragment.this.s.setBackgroundResource(R.mipmap.f268menu_right_nav_btn);
                } else {
                    TestDetailWebFragment.this.s.setBackgroundResource(R.mipmap.f267mall_sum_decrease_selected);
                }
                TestDetailWebFragment.this.A = Integer.parseInt(detailStatusResponseModel.getNews().getPlsum());
                TestDetailWebFragment.this.z = Integer.parseInt(detailStatusResponseModel.getNews().getDing());
                TestDetailWebFragment.this.a((View) TestDetailWebFragment.this.D, TestDetailWebFragment.this.A);
                TestDetailWebFragment.this.I.setText("全部评论（" + TestDetailWebFragment.this.A + "条）");
                TestDetailWebFragment.this.a((View) TestDetailWebFragment.this.C, TestDetailWebFragment.this.z);
                if (p.a("is_logined", false)) {
                    TestDetailWebFragment.this.k();
                }
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
        this.J.b(this.c, this.h, this.g.getNews().getCatid(), "ding", new com.e23.ajn.d.o.a() {
            public void a(boolean z, String str) {
                TestDetailWebFragment.this.u.setBackgroundResource(R.mipmap.f228dingimg2_b);
                TestDetailWebFragment.this.y = !TestDetailWebFragment.this.y;
                if (TestDetailWebFragment.this.y) {
                    TestDetailWebFragment.this.z = TestDetailWebFragment.this.z + 1;
                }
                TestDetailWebFragment.this.a((View) TestDetailWebFragment.this.C, TestDetailWebFragment.this.z);
                if (p.a("is_logined", false)) {
                    com.e23.ajn.d.a.a("zan", TestDetailWebFragment.this.c);
                }
            }
        });
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
        this.J.a(this.c, this.h, this.g.getNews().getCatid(), z2 ? "addtocell" : "cancelcell", new com.e23.ajn.d.o.a() {
            public void a(boolean z, String str) {
                if (z) {
                    TestDetailWebFragment.this.s.setBackgroundResource(R.mipmap.f268menu_right_nav_btn);
                } else {
                    TestDetailWebFragment.this.s.setBackgroundResource(R.mipmap.f267mall_sum_decrease_selected);
                }
                TestDetailWebFragment.this.x = z;
                com.e23.ajn.b.e.a(TestDetailWebFragment.this.c).c(new c());
            }
        });
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
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=comment&a=getcomment_pub")).tag(this)).params(com.e23.ajn.c.b.a(null)).addParams("catid", this.g.getNews().getCatid()).addParams("newsid", this.h).addParams("page", Integer.toString(this.G)).addParams("num", Integer.toString(20)).build().execute(new com.e23.ajn.c.a<CommentListResponseModel>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                if (z2) {
                    TestDetailWebFragment.this.l.setEnabled(false);
                } else {
                    TestDetailWebFragment.this.p.setEnableLoadMore(false);
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z2) {
                    TestDetailWebFragment.this.l.setEnabled(true);
                    return;
                }
                TestDetailWebFragment.this.l.setRefreshing(false);
                if (TestDetailWebFragment.this.p.getData().size() >= 20) {
                    TestDetailWebFragment.this.p.setEnableLoadMore(true);
                }
            }

            public void onError(Call call, Exception exc, int i) {
                if (z2) {
                    TestDetailWebFragment.this.p.loadMoreFail();
                } else {
                    TestDetailWebFragment.this.p.setNewData(null);
                }
            }

            /* renamed from: a */
            public void onResponse(CommentListResponseModel commentListResponseModel, int i) {
                int i2;
                if (commentListResponseModel == null || commentListResponseModel.getCode() != 200) {
                    if (z2) {
                        TestDetailWebFragment.this.p.loadMoreFail();
                    } else {
                        TestDetailWebFragment.this.p.setNewData(null);
                    }
                } else if (com.e23.ajn.d.e.b(commentListResponseModel.getData())) {
                    if (z2) {
                        TestDetailWebFragment.this.p.addData((Collection<? extends T>) commentListResponseModel.getData());
                        TestDetailWebFragment.this.p.loadMoreComplete();
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
                        TestDetailWebFragment.this.p.a(i2);
                        TestDetailWebFragment.this.p.setNewData(commentListResponseModel.getData());
                    }
                    if (commentListResponseModel.getData().size() < 20) {
                        TestDetailWebFragment.this.p.loadMoreEnd(TestDetailWebFragment.this.F);
                    } else {
                        TestDetailWebFragment.this.G = TestDetailWebFragment.this.G + 1;
                    }
                } else if (z2) {
                    TestDetailWebFragment.this.p.loadMoreEnd(TestDetailWebFragment.this.F);
                } else {
                    TestDetailWebFragment.this.p.setNewData(null);
                }
            }
        });
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
