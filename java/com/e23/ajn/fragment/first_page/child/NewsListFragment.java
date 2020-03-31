package com.e23.ajn.fragment.first_page.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.i;
import android.support.v7.widget.RecyclerView.l;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.SpanSizeLookup;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.adapter.CatItemAdapter;
import com.e23.ajn.adapter.DistrictAdapter;
import com.e23.ajn.adapter.NewsItemAdapter;
import com.e23.ajn.c.a;
import com.e23.ajn.d.e;
import com.e23.ajn.d.p;
import com.e23.ajn.d.q;
import com.e23.ajn.d.v;
import com.e23.ajn.fragment.BaseListFragment;
import com.e23.ajn.model.BroadcastBean;
import com.e23.ajn.model.ChildCatBean;
import com.e23.ajn.model.DistrictBean;
import com.e23.ajn.model.NewsBean;
import com.e23.ajn.model.NewsData;
import com.e23.ajn.model.NewsFoundationBean;
import com.e23.ajn.model.NewsListResponse;
import com.e23.ajn.model.ThumbBean;
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.c;
import com.e23.ajn.views.k;
import com.zhouwei.mzbanner.MZBannerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;
import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

public class NewsListFragment extends BaseListFragment {
    private MZBannerView A;
    private View B;
    private ViewFlipper C;
    private View D;
    private View E;
    private RecyclerView F;
    /* access modifiers changed from: private */
    public CatItemAdapter G;
    private RecyclerView H;
    /* access modifiers changed from: private */
    public DistrictAdapter I;
    /* access modifiers changed from: private */
    public String J;
    private boolean K = false;
    /* access modifiers changed from: private */
    public String o;
    /* access modifiers changed from: private */
    public int p;

    /* renamed from: q reason: collision with root package name */
    private String f403q;
    private boolean r;
    private boolean s;
    /* access modifiers changed from: private */
    public NewsItemAdapter t;
    /* access modifiers changed from: private */
    public List<NewsBean> u;
    /* access modifiers changed from: private */
    public List<ThumbBean> v;
    /* access modifiers changed from: private */
    public List<BroadcastBean> w;
    /* access modifiers changed from: private */
    public List<DistrictBean> x;
    /* access modifiers changed from: private */
    public List<ChildCatBean> y;
    private View z;

    public static NewsListFragment a(String str, int i, String str2, boolean z2, boolean z3) {
        NewsListFragment newsListFragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cat_id", str);
        bundle.putInt("f_type", i);
        bundle.putBoolean("has_bar", z2);
        bundle.putString("title", str2);
        bundle.putBoolean("first_page", z3);
        newsListFragment.setArguments(bundle);
        return newsListFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.o = getArguments().getString("cat_id");
            this.r = getArguments().getBoolean("has_bar", false);
            this.p = getArguments().getInt("f_type");
            this.f403q = getArguments().getString("title");
            this.s = getArguments().getBoolean("first_page");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        s();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void s() {
        if (this.o.equals("12")) {
            this.t = new NewsItemAdapter(this.c, null, true, true);
        } else {
            this.t = new NewsItemAdapter(this.c, null, false, true);
        }
        this.t.setSpanSizeLookup(new SpanSizeLookup() {
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                return ((NewsBean) NewsListFragment.this.t.getData().get(i)).getSpanSize();
            }
        });
    }

    public boolean m() {
        return false;
    }

    public boolean h() {
        return this.r;
    }

    public String i() {
        return this.f403q;
    }

    public i n() {
        return new GridLayoutManager(this.c, 2);
    }

    /* renamed from: r */
    public BaseMultiItemQuickAdapter j() {
        return this.t;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        this.K = true;
        v.a(this.c, (NewsFoundationBean) this.t.getItem(i), this.t);
    }

    public String k() {
        if (this.p == 2) {
            return "http://appc.e23.cn/index.php?m=api&c=content&a=getQuxian";
        }
        if (this.p == 999) {
            return "http://appc.e23.cn/index.php?m=api&c=content&a=ztList";
        }
        if (this.p == 4) {
            return "http://appc.e23.cn/index.php?m=api&c=subscribe&a=dyhCList";
        }
        return "http://appc.e23.cn/index.php?m=api&c=content&a=newsList_test";
    }

    public Map<String, String> b(boolean z2) {
        HashMap hashMap = new HashMap();
        if (this.p != 2) {
            if (z2 && !TextUtils.isEmpty(this.J)) {
                hashMap.put("lastid", this.J);
            }
            if (!TextUtils.isEmpty(this.o)) {
                if (this.p == 4) {
                    hashMap.put("uname", this.o);
                } else {
                    hashMap.put("catid", this.o);
                }
            }
        }
        return hashMap;
    }

    /* renamed from: d */
    public a c(final boolean z2) {
        return new a<NewsListResponse>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                if (z2) {
                    NewsListFragment.this.f.setEnabled(false);
                    return;
                }
                NewsListFragment.this.f.setRefreshing(true);
                NewsListFragment.this.t.setEnableLoadMore(false);
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z2) {
                    NewsListFragment.this.f.setEnabled(true);
                } else {
                    NewsListFragment.this.f.setRefreshing(false);
                    if (NewsListFragment.this.t.getData().size() >= 20) {
                        NewsListFragment.this.t.setEnableLoadMore(true);
                    }
                }
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                if (z2) {
                    NewsListFragment.this.t.loadMoreFail();
                    return;
                }
                NewsListFragment.this.t.setNewData(null);
                NewsListFragment.this.t.setEmptyView(NewsListFragment.this.h);
            }

            /* renamed from: a */
            public void onResponse(NewsListResponse newsListResponse, int i) {
                if (newsListResponse != null && newsListResponse.getCode() == 200) {
                    NewsData data = newsListResponse.getData();
                    if (data != null) {
                        final List news = data.getNews();
                        final List thumb = data.getThumb();
                        final List abobao = data.getAbobao();
                        final List qxdata = data.getQxdata();
                        final List topcats = data.getTopcats();
                        NewsListFragment.this.J = data.getLastid();
                        if (!z2) {
                            if (NewsListFragment.this.u == null) {
                                NewsListFragment.this.u = new ArrayList();
                            } else {
                                NewsListFragment.this.u.clear();
                            }
                            if (NewsListFragment.this.v == null) {
                                NewsListFragment.this.v = new ArrayList();
                            } else {
                                NewsListFragment.this.v.clear();
                            }
                            if (NewsListFragment.this.w == null) {
                                NewsListFragment.this.w = new ArrayList();
                            } else {
                                NewsListFragment.this.w.clear();
                            }
                            if (NewsListFragment.this.x == null) {
                                NewsListFragment.this.x = new ArrayList();
                            } else {
                                NewsListFragment.this.x.clear();
                            }
                            if (NewsListFragment.this.y == null) {
                                NewsListFragment.this.y = new ArrayList();
                            } else {
                                NewsListFragment.this.y.clear();
                            }
                            if (e.b(thumb)) {
                                NewsListFragment.this.v.addAll(thumb);
                            }
                            if (e.b(abobao)) {
                                NewsListFragment.this.w.addAll(abobao);
                            }
                            if (e.b(qxdata)) {
                                NewsListFragment.this.x.addAll(qxdata);
                            }
                            if (e.b(topcats)) {
                                NewsListFragment.this.y.addAll(topcats);
                            }
                            if (e.b(news)) {
                                NewsListFragment.this.u.addAll(news);
                            }
                            NewsListFragment.this.a(topcats);
                            NewsListFragment.this.c(abobao);
                            NewsListFragment.this.b(qxdata);
                            NewsListFragment.this.d(thumb);
                            NewsListFragment.this.t.setNewData(news);
                            DataSupport.deleteAllAsync(NewsData.class, "catid = ?", NewsListFragment.this.o).listen(new UpdateOrDeleteCallback() {
                                public void onFinish(int i) {
                                    NewsData newsData = new NewsData();
                                    newsData.setCatid(NewsListFragment.this.o);
                                    newsData.setLastUpDate((int) (System.currentTimeMillis() / 1000));
                                    newsData.setLastid(NewsListFragment.this.J);
                                    for (NewsBean newsBean : news) {
                                        newsBean.setZu(e.b((List) newsBean.getThumbs()) ? new com.b.a.e().a((Object) newsBean.getThumbs()) : "");
                                        newsBean.save();
                                        newsData.getNews().add(newsBean);
                                    }
                                    for (ThumbBean thumbBean : thumb) {
                                        thumbBean.setZu(e.b((List) thumbBean.getThumbs()) ? new com.b.a.e().a((Object) thumbBean.getThumbs()) : "");
                                        thumbBean.save();
                                        newsData.getThumb().add(thumbBean);
                                    }
                                    for (BroadcastBean broadcastBean : abobao) {
                                        broadcastBean.setZu(e.b((List) broadcastBean.getThumbs()) ? new com.b.a.e().a((Object) broadcastBean.getThumbs()) : "");
                                        broadcastBean.save();
                                        newsData.getAbobao().add(broadcastBean);
                                    }
                                    for (ChildCatBean childCatBean : topcats) {
                                        childCatBean.save();
                                        newsData.getTopcats().add(childCatBean);
                                    }
                                    for (DistrictBean districtBean : qxdata) {
                                        districtBean.save();
                                        newsData.getQxdata().add(districtBean);
                                    }
                                    newsData.saveAsync().listen(new SaveCallback() {
                                        public void onFinish(boolean z) {
                                        }
                                    });
                                }
                            });
                            if (e.b(news)) {
                                if (news.size() < 20) {
                                    NewsListFragment.this.t.loadMoreEnd(NewsListFragment.this.k);
                                } else {
                                    NewsListFragment.this.n = NewsListFragment.this.n + 1;
                                }
                            } else if (e.a(thumb) && e.a(abobao) && e.a(qxdata)) {
                                NewsListFragment.this.t.setEmptyView(NewsListFragment.this.g);
                            }
                        } else if (e.b(news)) {
                            NewsListFragment.this.t.addData((Collection<? extends T>) news);
                            NewsListFragment.this.t.loadMoreComplete();
                            if (news.size() < 20) {
                                NewsListFragment.this.t.loadMoreEnd(NewsListFragment.this.k);
                            } else {
                                NewsListFragment.this.n = NewsListFragment.this.n + 1;
                            }
                        } else {
                            NewsListFragment.this.t.loadMoreEnd(NewsListFragment.this.k);
                        }
                    } else if (z2) {
                        NewsListFragment.this.t.loadMoreEnd(NewsListFragment.this.k);
                    } else {
                        NewsListFragment.this.t.setNewData(null);
                        NewsListFragment.this.t.setEmptyView(NewsListFragment.this.g);
                    }
                } else if (z2) {
                    NewsListFragment.this.t.loadMoreFail();
                } else {
                    NewsListFragment.this.t.setNewData(null);
                    if (NewsListFragment.this.p != 2) {
                        NewsListFragment.this.t.setEmptyView(NewsListFragment.this.h);
                    }
                }
            }
        };
    }

    public void l() {
        if (this.K) {
            this.K = false;
            return;
        }
        this.n = 1;
        List find = DataSupport.where("catid = ?", this.o).find(NewsData.class, true);
        if (e.b(find)) {
            this.u = ((NewsData) find.get(0)).getNews();
            this.v = ((NewsData) find.get(0)).getThumb();
            this.w = ((NewsData) find.get(0)).getAbobao();
            this.y = ((NewsData) find.get(0)).getTopcats();
            this.x = ((NewsData) find.get(0)).getQxdata();
            int lastUpDate = ((NewsData) find.get(0)).getLastUpDate();
            this.J = ((NewsData) find.get(0)).getLastid();
            a(this.y);
            c(this.w);
            b(this.x);
            d(this.v);
            if (e.b((List) this.u)) {
                this.t.setNewData(this.u);
                if (this.u.size() < 20) {
                    this.t.loadMoreEnd(this.k);
                } else {
                    this.n++;
                }
                if ((System.currentTimeMillis() / 1000) - ((long) lastUpDate) > 180) {
                    onRefresh();
                }
            } else if (e.a((List) this.v) && e.a((List) this.w) && e.a((List) this.x)) {
                onRefresh();
            } else if ((System.currentTimeMillis() / 1000) - ((long) lastUpDate) > 180) {
                onRefresh();
            }
        } else {
            onRefresh();
        }
    }

    /* access modifiers changed from: private */
    public void a(List<ChildCatBean> list) {
        if (e.b((List) list)) {
            if (this.E == null) {
                this.E = this.c.getLayoutInflater().inflate(R.layout.f175mythreads, (ViewGroup) this.e.getParent(), false);
                this.F = (RecyclerView) this.E.findViewById(R.id.child_cate_list);
                this.G = new CatItemAdapter(this.c, null);
                this.F.setHasFixedSize(true);
                WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this.c);
                wrapContentLinearLayoutManager.b(0);
                this.F.setLayoutManager(wrapContentLinearLayoutManager);
                this.F.setAdapter(this.G);
                this.F.a((l) new OnItemClickListener() {
                    public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        Intent intent = new Intent(NewsListFragment.this.c, SwipeBackCommonActivity.class);
                        intent.putExtra(SwipeBackCommonActivity.TAG, 9);
                        intent.putExtra(SwipeBackCommonActivity.TITLE, ((ChildCatBean) NewsListFragment.this.G.getData().get(i)).getCname());
                        intent.putExtra(SwipeBackCommonActivity.URL, ((ChildCatBean) NewsListFragment.this.G.getData().get(i)).getCatid());
                        NewsListFragment.this.startActivity(intent);
                    }
                });
            } else {
                ViewParent parent = this.E.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.E);
                }
            }
            this.t.addHeaderView(this.E, 0);
            this.G.setNewData(list);
        } else if (this.E != null) {
            this.t.removeHeaderView(this.E);
        }
    }

    /* access modifiers changed from: private */
    public void b(List<DistrictBean> list) {
        if (e.b((List) list)) {
            if (this.D == null) {
                this.D = this.c.getLayoutInflater().inflate(R.layout.f179nav_radiogroup_item_news, (ViewGroup) this.e.getParent(), false);
                this.H = (RecyclerView) this.D.findViewById(R.id.district_list);
                this.I = new DistrictAdapter(this.c, null);
                this.I.setSpanSizeLookup(new SpanSizeLookup() {
                    public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                        return 1;
                    }
                });
                this.H.setHasFixedSize(true);
                this.H.setNestedScrollingEnabled(false);
                this.H.setLayoutManager(new GridLayoutManager(this.c, 4));
                this.H.setAdapter(this.I);
                this.H.a((l) new OnItemClickListener() {
                    public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        if (NewsListFragment.this.I.getData() != null && NewsListFragment.this.I.getData().get(i) != null) {
                            Intent intent = new Intent(NewsListFragment.this.c, SwipeBackCommonActivity.class);
                            intent.putExtra(SwipeBackCommonActivity.TAG, 9);
                            intent.putExtra(SwipeBackCommonActivity.TITLE, ((DistrictBean) NewsListFragment.this.I.getData().get(i)).getCatname());
                            intent.putExtra(SwipeBackCommonActivity.URL, ((DistrictBean) NewsListFragment.this.I.getData().get(i)).getCatid());
                            NewsListFragment.this.startActivity(intent);
                        }
                    }
                });
            } else {
                ViewParent parent = this.D.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.D);
                }
            }
            this.t.addHeaderView(this.D, 0);
            this.I.setNewData(list);
        } else if (this.D != null) {
            this.t.removeHeaderView(this.D);
        }
    }

    /* access modifiers changed from: private */
    public void c(List<BroadcastBean> list) {
        if (e.b((List) list)) {
            if (this.B == null) {
                this.B = this.c.getLayoutInflater().inflate(R.layout.f181newscontent, (ViewGroup) this.e.getParent(), false);
                this.C = (ViewFlipper) this.B.findViewById(R.id._flipper);
            } else {
                ViewParent parent = this.B.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.B);
                }
            }
            this.t.addHeaderView(this.B, 0);
            this.C.removeAllViews();
            for (final BroadcastBean broadcastBean : list) {
                View inflate = LayoutInflater.from(this.c).inflate(R.layout.f168myfriends_item, null);
                TextView textView = (TextView) inflate.findViewById(R.id.tv_content);
                textView.setText(broadcastBean.getTitle());
                textView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        v.a(NewsListFragment.this.c, broadcastBean, null);
                    }
                });
                this.C.addView(inflate);
            }
        } else if (this.B != null) {
            this.t.removeHeaderView(this.B);
        }
    }

    /* access modifiers changed from: private */
    public void d(final List<ThumbBean> list) {
        if (e.b((List) list)) {
            if (this.z == null) {
                this.z = this.c.getLayoutInflater().inflate(R.layout.f170myorder_item, (ViewGroup) this.e.getParent(), false);
                this.A = (MZBannerView) this.z.findViewById(R.id.banner_);
                this.A.setIndicatorVisible(false);
                this.A.setLayoutParams(new LayoutParams(-1, q.b(this.c) / 2));
            } else {
                ViewParent parent = this.z.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.z);
                }
            }
            if (!this.s || !p.a("is_red", false)) {
                this.z.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            } else {
                this.z.setBackgroundColor(getResources().getColor(R.color.colorRed));
            }
            this.t.addHeaderView(this.z, 0);
            this.A.a(list, (com.zhouwei.mzbanner.a.a) new com.zhouwei.mzbanner.a.a<c>() {
                /* renamed from: a */
                public c b() {
                    return new c();
                }
            });
            this.A.setBannerPageClickListener(new MZBannerView.a() {
                public void a(View view, int i) {
                    v.a(NewsListFragment.this.c, (NewsFoundationBean) list.get(i), null);
                }
            });
            this.A.setDelayedTime(8000);
            this.A.a();
        } else if (this.z != null) {
            this.t.removeHeaderView(this.z);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.A != null) {
            this.A.b();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.A != null) {
            this.A.a();
        }
    }

    public boolean o() {
        return true;
    }
}
