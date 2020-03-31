package com.e23.ajn.fragment.interaction.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.i;
import android.support.v7.widget.RecyclerView.l;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout.LayoutParams;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.SpanSizeLookup;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.adapter.InteractionCatItemAdapter;
import com.e23.ajn.adapter.NewsItemAdapter;
import com.e23.ajn.c.a;
import com.e23.ajn.d.e;
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
import com.tencent.mid.api.MidEntity;
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

public class InteractionFragment extends BaseListFragment {
    private View A;
    private RecyclerView B;
    /* access modifiers changed from: private */
    public InteractionCatItemAdapter C;
    /* access modifiers changed from: private */
    public String D;
    private boolean E = false;
    /* access modifiers changed from: private */
    public String o;
    /* access modifiers changed from: private */
    public int p;

    /* renamed from: q reason: collision with root package name */
    private String f409q;
    private boolean r;
    /* access modifiers changed from: private */
    public NewsItemAdapter s;
    /* access modifiers changed from: private */
    public List<NewsBean> t;
    /* access modifiers changed from: private */
    public List<ThumbBean> u;
    /* access modifiers changed from: private */
    public List<BroadcastBean> v;
    /* access modifiers changed from: private */
    public List<DistrictBean> w;
    /* access modifiers changed from: private */
    public List<ChildCatBean> x;
    private View y;
    private MZBannerView z;

    public static InteractionFragment a(String str, int i, String str2, boolean z2) {
        InteractionFragment interactionFragment = new InteractionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cat_id", str);
        bundle.putInt("f_type", i);
        bundle.putBoolean("has_bar", z2);
        bundle.putString("title", str2);
        interactionFragment.setArguments(bundle);
        return interactionFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.o = getArguments().getString("cat_id");
            this.r = getArguments().getBoolean("has_bar", false);
            this.p = getArguments().getInt("f_type");
            this.f409q = getArguments().getString("title");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        s();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void s() {
        if (this.o.equals("12")) {
            this.s = new NewsItemAdapter(this.c, null, true, true);
        } else {
            this.s = new NewsItemAdapter(this.c, null, false, true);
        }
        this.s.a(true);
        this.s.setSpanSizeLookup(new SpanSizeLookup() {
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                return ((NewsBean) InteractionFragment.this.s.getData().get(i)).getSpanSize();
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
        return this.f409q;
    }

    public i n() {
        return new GridLayoutManager(this.c, 2);
    }

    /* renamed from: r */
    public BaseMultiItemQuickAdapter j() {
        return this.s;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        this.E = true;
        v.a(this.c, (NewsFoundationBean) this.s.getItem(i), this.s);
    }

    public String k() {
        return "http://appc.e23.cn/index.php?m=api&c=content&a=newsList_test";
    }

    public Map<String, String> b(boolean z2) {
        HashMap hashMap = new HashMap();
        if (this.p != 2) {
            if (z2 && !TextUtils.isEmpty(this.D)) {
                hashMap.put("lastid", this.D);
            }
            if (!TextUtils.isEmpty(this.o)) {
                if (this.p == 4) {
                    hashMap.put("uname", this.o);
                } else {
                    hashMap.put("catid", this.o);
                    hashMap.put(MidEntity.TAG_VER, "807");
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
                    InteractionFragment.this.f.setEnabled(false);
                    return;
                }
                InteractionFragment.this.f.setRefreshing(true);
                InteractionFragment.this.s.setEnableLoadMore(false);
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z2) {
                    InteractionFragment.this.f.setEnabled(true);
                } else {
                    InteractionFragment.this.f.setRefreshing(false);
                    if (InteractionFragment.this.s.getData().size() >= 20) {
                        InteractionFragment.this.s.setEnableLoadMore(true);
                    }
                }
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                if (z2) {
                    InteractionFragment.this.s.loadMoreFail();
                    return;
                }
                InteractionFragment.this.s.setNewData(null);
                InteractionFragment.this.s.setEmptyView(InteractionFragment.this.h);
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
                        InteractionFragment.this.D = data.getLastid();
                        if (!z2) {
                            if (InteractionFragment.this.t == null) {
                                InteractionFragment.this.t = new ArrayList();
                            } else {
                                InteractionFragment.this.t.clear();
                            }
                            if (InteractionFragment.this.u == null) {
                                InteractionFragment.this.u = new ArrayList();
                            } else {
                                InteractionFragment.this.u.clear();
                            }
                            if (InteractionFragment.this.v == null) {
                                InteractionFragment.this.v = new ArrayList();
                            } else {
                                InteractionFragment.this.v.clear();
                            }
                            if (InteractionFragment.this.w == null) {
                                InteractionFragment.this.w = new ArrayList();
                            } else {
                                InteractionFragment.this.w.clear();
                            }
                            if (InteractionFragment.this.x == null) {
                                InteractionFragment.this.x = new ArrayList();
                            } else {
                                InteractionFragment.this.x.clear();
                            }
                            if (e.b(thumb)) {
                                InteractionFragment.this.u.addAll(thumb);
                            }
                            if (e.b(abobao)) {
                                InteractionFragment.this.v.addAll(abobao);
                            }
                            if (e.b(qxdata)) {
                                InteractionFragment.this.w.addAll(qxdata);
                            }
                            if (e.b(topcats)) {
                                InteractionFragment.this.x.addAll(topcats);
                            }
                            if (e.b(news)) {
                                InteractionFragment.this.t.addAll(news);
                            }
                            InteractionFragment.this.a(topcats);
                            InteractionFragment.this.b(thumb);
                            InteractionFragment.this.s.setNewData(news);
                            DataSupport.deleteAllAsync(NewsData.class, "catid = ?", InteractionFragment.this.o).listen(new UpdateOrDeleteCallback() {
                                public void onFinish(int i) {
                                    NewsData newsData = new NewsData();
                                    newsData.setCatid(InteractionFragment.this.o);
                                    newsData.setLastUpDate((int) (System.currentTimeMillis() / 1000));
                                    newsData.setLastid(InteractionFragment.this.D);
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
                                    InteractionFragment.this.s.loadMoreEnd(InteractionFragment.this.k);
                                } else {
                                    InteractionFragment.this.n = InteractionFragment.this.n + 1;
                                }
                            } else if (e.a(thumb) && e.a(abobao) && e.a(qxdata)) {
                                InteractionFragment.this.s.setEmptyView(InteractionFragment.this.g);
                            }
                        } else if (e.b(news)) {
                            InteractionFragment.this.s.addData((Collection<? extends T>) news);
                            InteractionFragment.this.s.loadMoreComplete();
                            if (news.size() < 20) {
                                InteractionFragment.this.s.loadMoreEnd(InteractionFragment.this.k);
                            } else {
                                InteractionFragment.this.n = InteractionFragment.this.n + 1;
                            }
                        } else {
                            InteractionFragment.this.s.loadMoreEnd(InteractionFragment.this.k);
                        }
                    } else if (z2) {
                        InteractionFragment.this.s.loadMoreEnd(InteractionFragment.this.k);
                    } else {
                        InteractionFragment.this.s.setNewData(null);
                        InteractionFragment.this.s.setEmptyView(InteractionFragment.this.g);
                    }
                } else if (z2) {
                    InteractionFragment.this.s.loadMoreFail();
                } else {
                    InteractionFragment.this.s.setNewData(null);
                    if (InteractionFragment.this.p != 2) {
                        InteractionFragment.this.s.setEmptyView(InteractionFragment.this.h);
                    }
                }
            }
        };
    }

    public void l() {
        if (this.E) {
            this.E = false;
            return;
        }
        this.n = 1;
        List find = DataSupport.where("catid = ?", this.o).find(NewsData.class, true);
        if (e.b(find)) {
            this.t = ((NewsData) find.get(0)).getNews();
            this.u = ((NewsData) find.get(0)).getThumb();
            this.v = ((NewsData) find.get(0)).getAbobao();
            this.x = ((NewsData) find.get(0)).getTopcats();
            this.w = ((NewsData) find.get(0)).getQxdata();
            int lastUpDate = ((NewsData) find.get(0)).getLastUpDate();
            this.D = ((NewsData) find.get(0)).getLastid();
            a(this.x);
            b(this.u);
            if (e.b((List) this.t)) {
                this.s.setNewData(this.t);
                if (this.t.size() < 20) {
                    this.s.loadMoreEnd(this.k);
                } else {
                    this.n++;
                }
                if ((System.currentTimeMillis() / 1000) - ((long) lastUpDate) > 180) {
                    onRefresh();
                }
            } else if (e.a((List) this.u) && e.a((List) this.v) && e.a((List) this.w)) {
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
            if (this.A == null) {
                this.A = this.c.getLayoutInflater().inflate(R.layout.f175mythreads, (ViewGroup) this.e.getParent(), false);
                this.B = (RecyclerView) this.A.findViewById(R.id.child_cate_list);
                this.C = new InteractionCatItemAdapter(this.c, null);
                this.B.setHasFixedSize(true);
                WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this.c);
                wrapContentLinearLayoutManager.b(0);
                this.B.setLayoutManager(wrapContentLinearLayoutManager);
                this.B.setAdapter(this.C);
                this.B.a((l) new OnItemClickListener() {
                    public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        if (i != 0) {
                            if (i == 2) {
                                Intent intent = new Intent(InteractionFragment.this.c, SwipeBackCommonActivity.class);
                                intent.putExtra("cat_name", ((ChildCatBean) InteractionFragment.this.C.getData().get(i)).getCname());
                                intent.putExtra(SwipeBackCommonActivity.TAG, 35);
                                InteractionFragment.this.startActivity(intent);
                            } else if (i == 1 || i == 3) {
                                Intent intent2 = new Intent(InteractionFragment.this.c, SwipeBackCommonActivity.class);
                                intent2.putExtra(SwipeBackCommonActivity.TAG, 91);
                                intent2.putExtra(SwipeBackCommonActivity.TITLE, ((ChildCatBean) InteractionFragment.this.C.getData().get(i)).getCname());
                                intent2.putExtra(SwipeBackCommonActivity.URL, ((ChildCatBean) InteractionFragment.this.C.getData().get(i)).getCatid());
                                InteractionFragment.this.startActivity(intent2);
                            } else {
                                Intent intent3 = new Intent(InteractionFragment.this.c, SwipeBackCommonActivity.class);
                                intent3.putExtra(SwipeBackCommonActivity.TAG, 9);
                                intent3.putExtra(SwipeBackCommonActivity.TITLE, ((ChildCatBean) InteractionFragment.this.C.getData().get(i)).getCname());
                                intent3.putExtra(SwipeBackCommonActivity.URL, ((ChildCatBean) InteractionFragment.this.C.getData().get(i)).getCatid());
                                InteractionFragment.this.startActivity(intent3);
                            }
                        }
                    }
                });
            } else {
                ViewParent parent = this.A.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.A);
                }
            }
            this.s.addHeaderView(this.A, 0);
            this.C.setNewData(list);
        } else if (this.A != null) {
            this.s.removeHeaderView(this.A);
        }
    }

    /* access modifiers changed from: private */
    public void b(final List<ThumbBean> list) {
        if (e.b((List) list)) {
            if (this.y == null) {
                this.y = this.c.getLayoutInflater().inflate(R.layout.f170myorder_item, (ViewGroup) this.e.getParent(), false);
                this.z = (MZBannerView) this.y.findViewById(R.id.banner_);
                this.z.setIndicatorVisible(false);
                this.z.setLayoutParams(new LayoutParams(-1, q.b(this.c) / 2));
            } else {
                ViewParent parent = this.y.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.y);
                }
            }
            this.s.addHeaderView(this.y, 0);
            this.z.a(list, (com.zhouwei.mzbanner.a.a) new com.zhouwei.mzbanner.a.a<c>() {
                /* renamed from: a */
                public c b() {
                    return new c();
                }
            });
            this.z.setBannerPageClickListener(new MZBannerView.a() {
                public void a(View view, int i) {
                    v.a(InteractionFragment.this.c, (NewsFoundationBean) list.get(i), null);
                }
            });
            this.z.setDelayedTime(8000);
            this.z.a();
        } else if (this.y != null) {
            this.s.removeHeaderView(this.y);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.z != null) {
            this.z.b();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.z != null) {
            this.z.a();
        }
    }

    public boolean o() {
        return true;
    }
}
