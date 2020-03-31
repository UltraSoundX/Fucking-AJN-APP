package com.e23.ajn.fragment.mine.child;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView.i;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.e23.ajn.R;
import com.e23.ajn.adapter.NewsItemAdapter;
import com.e23.ajn.c.a;
import com.e23.ajn.d.e;
import com.e23.ajn.d.v;
import com.e23.ajn.fragment.BaseListFragment;
import com.e23.ajn.model.HistoryResponseModel;
import com.e23.ajn.model.NewsFoundationBean;
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.k;
import com.zhy.http.okhttp.callback.Callback;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

public class MineNewsListFragment extends BaseListFragment {
    private int o;
    /* access modifiers changed from: private */
    public NewsItemAdapter p;

    public static MineNewsListFragment a(int i) {
        MineNewsListFragment mineNewsListFragment = new MineNewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        mineNewsListFragment.setArguments(bundle);
        return mineNewsListFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.o = getArguments().getInt("type");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        r();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void r() {
        this.p = new NewsItemAdapter(this.c, null, false, false);
    }

    public boolean h() {
        return true;
    }

    public boolean o() {
        return false;
    }

    public String i() {
        if (this.o == 1) {
            return getString(R.string.fragment_mine_history);
        }
        return getString(R.string.fragment_mine_collect);
    }

    public BaseQuickAdapter j() {
        return this.p;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        v.a(this.c, (NewsFoundationBean) this.p.getData().get(i), null);
    }

    public String k() {
        if (this.o == 1) {
            return "http://appc.e23.cn/index.php?m=api&c=contentc&a=getMyHistory";
        }
        return "http://appc.e23.cn/index.php?m=api&c=contentc&a=getMyCell";
    }

    public Map<String, String> b(boolean z) {
        return new HashMap();
    }

    public Callback c(final boolean z) {
        return new a<HistoryResponseModel>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                if (z) {
                    MineNewsListFragment.this.f.setEnabled(false);
                } else {
                    MineNewsListFragment.this.p.setEnableLoadMore(false);
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z) {
                    MineNewsListFragment.this.f.setEnabled(true);
                } else {
                    MineNewsListFragment.this.f.setRefreshing(false);
                    if (MineNewsListFragment.this.p.getData().size() >= 20) {
                        MineNewsListFragment.this.p.setEnableLoadMore(true);
                    }
                }
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                if (z) {
                    MineNewsListFragment.this.p.loadMoreFail();
                    return;
                }
                MineNewsListFragment.this.p.setNewData(null);
                MineNewsListFragment.this.p.setEmptyView(MineNewsListFragment.this.h);
            }

            /* renamed from: a */
            public void onResponse(HistoryResponseModel historyResponseModel, int i) {
                if (historyResponseModel == null || historyResponseModel.getCode() != 200) {
                    if (z) {
                        MineNewsListFragment.this.p.loadMoreFail();
                        return;
                    }
                    MineNewsListFragment.this.p.setNewData(null);
                    MineNewsListFragment.this.p.setEmptyView(MineNewsListFragment.this.h);
                } else if (e.b(historyResponseModel.getData().getNews())) {
                    if (z) {
                        MineNewsListFragment.this.p.addData((Collection<? extends T>) historyResponseModel.getData().getNews());
                        MineNewsListFragment.this.p.loadMoreComplete();
                    } else {
                        MineNewsListFragment.this.p.setNewData(historyResponseModel.getData().getNews());
                    }
                    if (historyResponseModel.getData().getNews().size() < 20) {
                        MineNewsListFragment.this.p.loadMoreEnd(MineNewsListFragment.this.k);
                    } else {
                        MineNewsListFragment.this.n = MineNewsListFragment.this.n + 1;
                    }
                } else if (z) {
                    MineNewsListFragment.this.p.loadMoreEnd(MineNewsListFragment.this.k);
                } else {
                    MineNewsListFragment.this.p.setNewData(null);
                    MineNewsListFragment.this.p.setEmptyView(MineNewsListFragment.this.g);
                }
            }
        };
    }

    public void l() {
        onRefresh();
    }

    public boolean m() {
        return false;
    }

    public i n() {
        return new WrapContentLinearLayoutManager(this.c);
    }
}
