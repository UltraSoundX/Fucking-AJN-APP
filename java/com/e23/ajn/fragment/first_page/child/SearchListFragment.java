package com.e23.ajn.fragment.first_page.child;

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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

public class SearchListFragment extends BaseListFragment {
    /* access modifiers changed from: private */
    public NewsItemAdapter o;
    private String p;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public String f405q;

    public static SearchListFragment r() {
        SearchListFragment searchListFragment = new SearchListFragment();
        searchListFragment.setArguments(new Bundle());
        return searchListFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        u();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void u() {
        this.o = new NewsItemAdapter(this.c, null, false, true);
        this.o.setSpanSizeLookup(new SpanSizeLookup() {
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                return ((NewsBean) SearchListFragment.this.o.getData().get(i)).getSpanSize();
            }
        });
    }

    public boolean m() {
        return false;
    }

    public boolean h() {
        return false;
    }

    public String i() {
        return null;
    }

    public i n() {
        return new GridLayoutManager(this.c, 2);
    }

    /* renamed from: s */
    public BaseMultiItemQuickAdapter j() {
        return this.o;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        v.a(this.c, (NewsFoundationBean) this.o.getItem(i), this.o);
    }

    public String k() {
        return "http://appc.e23.cn/index.php?m=api&c=content&a=newsSearch";
    }

    public void a(String str) {
        this.p = str;
    }

    public Map<String, String> b(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("q", this.p);
        if (!z) {
            this.f405q = "";
        } else if (!TextUtils.isEmpty(this.f405q)) {
            hashMap.put("lastid", this.f405q);
        }
        return hashMap;
    }

    /* renamed from: d */
    public a c(final boolean z) {
        return new a<NewsListResponse>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                if (z) {
                    SearchListFragment.this.f.setEnabled(false);
                } else {
                    SearchListFragment.this.o.setEnableLoadMore(false);
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z) {
                    SearchListFragment.this.f.setEnabled(true);
                } else {
                    SearchListFragment.this.f.setRefreshing(false);
                    if (SearchListFragment.this.o.getData().size() >= 20) {
                        SearchListFragment.this.o.setEnableLoadMore(true);
                    }
                }
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                if (z) {
                    SearchListFragment.this.o.loadMoreFail();
                    return;
                }
                SearchListFragment.this.o.setNewData(null);
                SearchListFragment.this.o.setEmptyView(SearchListFragment.this.h);
            }

            /* renamed from: a */
            public void onResponse(NewsListResponse newsListResponse, int i) {
                if (newsListResponse != null && newsListResponse.getCode() == 200) {
                    NewsData data = newsListResponse.getData();
                    if (data != null) {
                        SearchListFragment.this.f405q = data.getLastid();
                        List news = data.getNews();
                        if (e.b(news)) {
                            if (z) {
                                SearchListFragment.this.o.addData((Collection<? extends T>) news);
                                SearchListFragment.this.o.loadMoreComplete();
                            } else {
                                SearchListFragment.this.o.setNewData(news);
                            }
                            if (news.size() < 20) {
                                SearchListFragment.this.o.loadMoreEnd(SearchListFragment.this.k);
                            } else {
                                SearchListFragment.this.n = SearchListFragment.this.n + 1;
                            }
                        } else if (z) {
                            SearchListFragment.this.o.loadMoreEnd(SearchListFragment.this.k);
                        } else {
                            SearchListFragment.this.o.setNewData(null);
                            SearchListFragment.this.o.setEmptyView(SearchListFragment.this.g);
                        }
                    } else if (z) {
                        SearchListFragment.this.o.loadMoreEnd(SearchListFragment.this.k);
                    } else {
                        SearchListFragment.this.o.setNewData(null);
                        SearchListFragment.this.o.setEmptyView(SearchListFragment.this.g);
                    }
                } else if (z) {
                    SearchListFragment.this.o.loadMoreFail();
                } else {
                    SearchListFragment.this.o.setNewData(null);
                    SearchListFragment.this.o.setEmptyView(SearchListFragment.this.h);
                }
            }
        };
    }

    public void t() {
        k.a(this.c);
        this.o.setNewData(null);
        onRefresh();
    }

    public void l() {
    }

    public boolean o() {
        return false;
    }
}
