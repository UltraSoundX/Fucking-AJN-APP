package com.e23.ajn.fragment.mine.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.i;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.adapter.MinePostsListAdapter;
import com.e23.ajn.c.a;
import com.e23.ajn.d.e;
import com.e23.ajn.fragment.BaseListFragment;
import com.e23.ajn.model.MinePostResponse;
import com.e23.ajn.model.MinePostResponse.DataBean.NewsBean;
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.k;
import com.zhy.http.okhttp.callback.Callback;
import java.util.Collection;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

public class MinePostFragment extends BaseListFragment {
    /* access modifiers changed from: private */
    public MinePostsListAdapter o;

    public static MinePostFragment r() {
        MinePostFragment minePostFragment = new MinePostFragment();
        minePostFragment.setArguments(new Bundle());
        return minePostFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        s();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void s() {
        this.o = new MinePostsListAdapter(this.c, null);
    }

    public boolean h() {
        return true;
    }

    public boolean o() {
        return false;
    }

    public String i() {
        return getString(R.string.fragment_mine_tv_posts);
    }

    public BaseQuickAdapter j() {
        return this.o;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent = new Intent();
        intent.setClass(this.c, SwipeBackCommonActivity.class);
        intent.putExtra(SwipeBackCommonActivity.TAG, SwipeBackCommonActivity.NEWS_DETAIL_FROM_MINE);
        intent.putExtra("newsid", ((NewsBean) this.o.getData().get(i)).getId());
        this.c.startActivity(intent);
    }

    public String k() {
        return "http://appc.e23.cn/index.php?m=api&c=contentc&a=getMyPosts";
    }

    public Map<String, String> b(boolean z) {
        return null;
    }

    public Callback c(final boolean z) {
        return new a<MinePostResponse>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                if (z) {
                    MinePostFragment.this.f.setEnabled(false);
                    return;
                }
                MinePostFragment.this.f.setRefreshing(true);
                MinePostFragment.this.o.setEnableLoadMore(false);
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z) {
                    MinePostFragment.this.f.setEnabled(true);
                } else {
                    MinePostFragment.this.f.setRefreshing(false);
                    if (MinePostFragment.this.o.getData().size() >= 20) {
                        MinePostFragment.this.o.setEnableLoadMore(true);
                    }
                }
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                if (z) {
                    MinePostFragment.this.o.loadMoreFail();
                    return;
                }
                MinePostFragment.this.o.setNewData(null);
                MinePostFragment.this.o.setEmptyView(MinePostFragment.this.h);
            }

            /* renamed from: a */
            public void onResponse(MinePostResponse minePostResponse, int i) {
                if (minePostResponse == null || minePostResponse.getCode() != 200) {
                    if (z) {
                        MinePostFragment.this.o.loadMoreFail();
                        return;
                    }
                    MinePostFragment.this.o.setNewData(null);
                    MinePostFragment.this.o.setEmptyView(MinePostFragment.this.h);
                } else if (e.b(minePostResponse.getData().getNews())) {
                    if (z) {
                        MinePostFragment.this.o.addData((Collection<? extends T>) minePostResponse.getData().getNews());
                        MinePostFragment.this.o.loadMoreComplete();
                    } else {
                        MinePostFragment.this.o.setNewData(minePostResponse.getData().getNews());
                    }
                    if (minePostResponse.getData().getNews().size() < 20) {
                        MinePostFragment.this.o.loadMoreEnd(MinePostFragment.this.k);
                    } else {
                        MinePostFragment.this.n = MinePostFragment.this.n + 1;
                    }
                } else if (z) {
                    MinePostFragment.this.o.loadMoreEnd(MinePostFragment.this.k);
                } else {
                    MinePostFragment.this.o.setNewData(null);
                    MinePostFragment.this.o.setEmptyView(MinePostFragment.this.g);
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
