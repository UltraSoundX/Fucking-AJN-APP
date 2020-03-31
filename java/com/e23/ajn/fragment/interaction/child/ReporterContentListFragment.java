package com.e23.ajn.fragment.interaction.child;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.i;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.SpanSizeLookup;
import com.e23.ajn.adapter.NewsItemAdapter;
import com.e23.ajn.c.a;
import com.e23.ajn.d.e;
import com.e23.ajn.d.v;
import com.e23.ajn.fragment.BaseListFragment;
import com.e23.ajn.model.NewsBean;
import com.e23.ajn.model.NewsData;
import com.e23.ajn.model.NewsFoundationBean;
import com.e23.ajn.model.NewsListResponse;
import com.e23.ajn.views.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

public class ReporterContentListFragment extends BaseListFragment {
    private String o;
    /* access modifiers changed from: private */
    public NewsItemAdapter p;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public List<NewsBean> f410q;
    /* access modifiers changed from: private */
    public String r;
    private String s;
    private boolean t;

    public static ReporterContentListFragment a(String str) {
        ReporterContentListFragment reporterContentListFragment = new ReporterContentListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("reporter_id", str);
        bundle.putString("toolbar_title", "");
        bundle.putBoolean("toolbar_show", false);
        reporterContentListFragment.setArguments(bundle);
        return reporterContentListFragment;
    }

    public static ReporterContentListFragment a(String str, String str2, boolean z) {
        ReporterContentListFragment reporterContentListFragment = new ReporterContentListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("reporter_id", str);
        bundle.putString("toolbar_title", str2);
        bundle.putBoolean("toolbar_show", z);
        reporterContentListFragment.setArguments(bundle);
        return reporterContentListFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.o = getArguments().getString("reporter_id");
            this.s = getArguments().getString("toolbar_title");
            this.t = getArguments().getBoolean("toolbar_show");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        s();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void s() {
        this.p = new NewsItemAdapter(this.c, null, false, true);
        this.p.setSpanSizeLookup(new SpanSizeLookup() {
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                return ((NewsBean) ReporterContentListFragment.this.p.getData().get(i)).getSpanSize();
            }
        });
    }

    public boolean m() {
        return false;
    }

    public boolean h() {
        return this.t;
    }

    public String i() {
        return this.s;
    }

    public i n() {
        return new GridLayoutManager(this.c, 2);
    }

    /* renamed from: r */
    public BaseMultiItemQuickAdapter j() {
        return this.p;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        v.a(this.c, (NewsFoundationBean) this.p.getItem(i), this.p);
    }

    public String k() {
        return "http://appc.e23.cn/index.php?m=api&c=reporter&a=reporter_content_list_pub";
    }

    public Map<String, String> b(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("userid", this.o);
        if (z && !TextUtils.isEmpty(this.r)) {
            hashMap.put("lastid", this.r);
        }
        return hashMap;
    }

    /* renamed from: d */
    public a c(final boolean z) {
        return new a<NewsListResponse>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                if (z) {
                    ReporterContentListFragment.this.f.setEnabled(false);
                    return;
                }
                ReporterContentListFragment.this.f.setRefreshing(true);
                ReporterContentListFragment.this.p.setEnableLoadMore(false);
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z) {
                    ReporterContentListFragment.this.f.setEnabled(true);
                } else {
                    ReporterContentListFragment.this.f.setRefreshing(false);
                    if (ReporterContentListFragment.this.p.getData().size() >= 20) {
                        ReporterContentListFragment.this.p.setEnableLoadMore(true);
                    }
                }
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                if (z) {
                    ReporterContentListFragment.this.p.loadMoreFail();
                    return;
                }
                ReporterContentListFragment.this.p.setNewData(null);
                ReporterContentListFragment.this.p.setEmptyView(ReporterContentListFragment.this.h);
            }

            /* renamed from: a */
            public void onResponse(NewsListResponse newsListResponse, int i) {
                if (newsListResponse != null && newsListResponse.getCode() == 200) {
                    NewsData data = newsListResponse.getData();
                    if (data != null) {
                        List news = data.getNews();
                        ReporterContentListFragment.this.r = data.getLastid();
                        if (!z) {
                            if (ReporterContentListFragment.this.f410q == null) {
                                ReporterContentListFragment.this.f410q = new ArrayList();
                            } else {
                                ReporterContentListFragment.this.f410q.clear();
                            }
                            ReporterContentListFragment.this.p.setNewData(news);
                            if (!e.b(news)) {
                                ReporterContentListFragment.this.p.setEmptyView(ReporterContentListFragment.this.g);
                            } else if (news.size() < 20) {
                                ReporterContentListFragment.this.p.loadMoreEnd(ReporterContentListFragment.this.k);
                            } else {
                                ReporterContentListFragment.this.n = ReporterContentListFragment.this.n + 1;
                            }
                        } else if (e.b(news)) {
                            ReporterContentListFragment.this.p.addData((Collection<? extends T>) news);
                            ReporterContentListFragment.this.p.loadMoreComplete();
                            if (news.size() < 20) {
                                ReporterContentListFragment.this.p.loadMoreEnd(ReporterContentListFragment.this.k);
                            } else {
                                ReporterContentListFragment.this.n = ReporterContentListFragment.this.n + 1;
                            }
                        } else {
                            ReporterContentListFragment.this.p.loadMoreEnd(ReporterContentListFragment.this.k);
                        }
                    } else if (z) {
                        ReporterContentListFragment.this.p.loadMoreEnd(ReporterContentListFragment.this.k);
                    } else {
                        ReporterContentListFragment.this.p.setNewData(null);
                        ReporterContentListFragment.this.p.setEmptyView(ReporterContentListFragment.this.g);
                    }
                } else if (z) {
                    ReporterContentListFragment.this.p.loadMoreFail();
                } else {
                    ReporterContentListFragment.this.p.setNewData(null);
                    ReporterContentListFragment.this.p.setEmptyView(ReporterContentListFragment.this.h);
                }
            }
        };
    }

    public void l() {
        onRefresh();
    }

    public boolean o() {
        return false;
    }
}
