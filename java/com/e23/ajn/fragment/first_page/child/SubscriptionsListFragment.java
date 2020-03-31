package com.e23.ajn.fragment.first_page.child;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView.i;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.e23.ajn.adapter.SubscriptionsListAdapter;
import com.e23.ajn.b.e;
import com.e23.ajn.c.a;
import com.e23.ajn.fragment.BaseListFragment;
import com.e23.ajn.model.SubscriptionsResponse;
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.k;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;
import org.greenrobot.eventbus.j;

public class SubscriptionsListFragment extends BaseListFragment {
    /* access modifiers changed from: private */
    public SubscriptionsListAdapter o;

    public static SubscriptionsListFragment r() {
        SubscriptionsListFragment subscriptionsListFragment = new SubscriptionsListFragment();
        subscriptionsListFragment.setArguments(new Bundle());
        return subscriptionsListFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        e.a(this.c).a((Object) this);
        s();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void s() {
        this.o = new SubscriptionsListAdapter(this.c, null);
    }

    public boolean m() {
        return false;
    }

    public boolean h() {
        return false;
    }

    public String i() {
        return "";
    }

    public i n() {
        return new WrapContentLinearLayoutManager(this.c);
    }

    public BaseQuickAdapter j() {
        return this.o;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public String k() {
        return "http://appc.e23.cn/index.php?m=api&c=subscribe&a=mySubscribeList";
    }

    public Map<String, String> b(boolean z) {
        return new HashMap();
    }

    /* renamed from: d */
    public a c(final boolean z) {
        return new a<SubscriptionsResponse>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                if (z) {
                    SubscriptionsListFragment.this.f.setEnabled(false);
                    return;
                }
                SubscriptionsListFragment.this.f.setRefreshing(true);
                SubscriptionsListFragment.this.o.setEnableLoadMore(false);
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z) {
                    SubscriptionsListFragment.this.f.setEnabled(true);
                } else {
                    SubscriptionsListFragment.this.f.setRefreshing(false);
                    if (SubscriptionsListFragment.this.o.getData().size() >= 20) {
                        SubscriptionsListFragment.this.o.setEnableLoadMore(true);
                    }
                }
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                if (z) {
                    SubscriptionsListFragment.this.o.loadMoreFail();
                    return;
                }
                SubscriptionsListFragment.this.o.setNewData(null);
                SubscriptionsListFragment.this.o.setEmptyView(SubscriptionsListFragment.this.h);
            }

            /* renamed from: a */
            public void onResponse(SubscriptionsResponse subscriptionsResponse, int i) {
                if (subscriptionsResponse != null && subscriptionsResponse.getCode() == 200) {
                    List dy = subscriptionsResponse.getDy();
                    if (!z) {
                        SubscriptionsListFragment.this.o.setNewData(dy);
                        if (!com.e23.ajn.d.e.b(dy)) {
                            SubscriptionsListFragment.this.o.setEmptyView(SubscriptionsListFragment.this.g);
                        } else if (dy.size() < 20) {
                            SubscriptionsListFragment.this.o.loadMoreEnd(SubscriptionsListFragment.this.k);
                        } else {
                            SubscriptionsListFragment.this.n = SubscriptionsListFragment.this.n + 1;
                        }
                    } else if (com.e23.ajn.d.e.b(dy)) {
                        SubscriptionsListFragment.this.o.addData((Collection<? extends T>) dy);
                        SubscriptionsListFragment.this.o.loadMoreComplete();
                    } else {
                        SubscriptionsListFragment.this.o.loadMoreEnd(SubscriptionsListFragment.this.k);
                    }
                } else if (z) {
                    SubscriptionsListFragment.this.o.loadMoreFail();
                } else {
                    SubscriptionsListFragment.this.o.setNewData(null);
                    SubscriptionsListFragment.this.o.setEmptyView(SubscriptionsListFragment.this.h);
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

    public void onDestroyView() {
        super.onDestroyView();
        e.a(this.c).b(this);
    }

    @j
    public void onRefreshEvent(com.e23.ajn.b.k kVar) {
        onRefresh();
    }
}
