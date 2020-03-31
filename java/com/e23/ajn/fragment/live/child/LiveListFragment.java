package com.e23.ajn.fragment.live.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.i;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.e23.ajn.activity.OutUrlActivity;
import com.e23.ajn.adapter.LiveListAdapter;
import com.e23.ajn.c.a;
import com.e23.ajn.d.e;
import com.e23.ajn.fragment.BaseListFragment;
import com.e23.ajn.model.LiveListResponseModel;
import com.e23.ajn.model.LiveListResponseModel.LiveBean;
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.k;
import com.zhy.http.okhttp.callback.Callback;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

public class LiveListFragment extends BaseListFragment {
    /* access modifiers changed from: private */
    public LiveListAdapter o;
    private String p;

    public static LiveListFragment a(String str) {
        Bundle bundle = new Bundle();
        LiveListFragment liveListFragment = new LiveListFragment();
        bundle.putSerializable("cid", str);
        liveListFragment.setArguments(bundle);
        return liveListFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (getArguments() != null) {
            this.p = getArguments().getString("cid", "");
        }
        r();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onResume() {
        super.onResume();
    }

    private void r() {
        this.o = new LiveListAdapter(this.c, null);
    }

    public boolean h() {
        return false;
    }

    public String i() {
        return null;
    }

    public BaseQuickAdapter j() {
        return this.o;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent = new Intent(this.c, OutUrlActivity.class);
        intent.putExtra("url", ((LiveBean) this.o.getData().get(i)).getUrl());
        intent.putExtra("title", ((LiveBean) this.o.getData().get(i)).getTitle());
        intent.putExtra(OutUrlActivity.ARG_THUMB, ((LiveBean) this.o.getData().get(i)).getThumb());
        intent.putExtra(OutUrlActivity.ARG_DESC, ((LiveBean) this.o.getData().get(i)).getTitle());
        this.c.startActivity(intent);
    }

    public String k() {
        return "http://appc.e23.cn/index.php?m=api&c=live&a=getLiveList";
    }

    public Map<String, String> b(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("cid", this.p);
        return hashMap;
    }

    public Callback c(final boolean z) {
        return new a<LiveListResponseModel>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                if (z) {
                    LiveListFragment.this.f.setEnabled(false);
                    return;
                }
                LiveListFragment.this.f.setRefreshing(true);
                LiveListFragment.this.o.setEnableLoadMore(false);
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z) {
                    LiveListFragment.this.f.setEnabled(true);
                } else {
                    LiveListFragment.this.f.setRefreshing(false);
                    if (LiveListFragment.this.o.getData().size() >= 20) {
                        LiveListFragment.this.o.setEnableLoadMore(true);
                    }
                }
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                if (z) {
                    LiveListFragment.this.o.loadMoreFail();
                    return;
                }
                LiveListFragment.this.o.setNewData(null);
                LiveListFragment.this.o.setEmptyView(LiveListFragment.this.h);
            }

            /* renamed from: a */
            public void onResponse(LiveListResponseModel liveListResponseModel, int i) {
                if (liveListResponseModel == null || liveListResponseModel.getCode() != 200) {
                    if (z) {
                        LiveListFragment.this.o.loadMoreFail();
                        return;
                    }
                    LiveListFragment.this.o.setNewData(null);
                    LiveListFragment.this.o.setEmptyView(LiveListFragment.this.h);
                } else if (e.b(liveListResponseModel.getData())) {
                    if (z) {
                        LiveListFragment.this.o.addData((Collection<? extends T>) liveListResponseModel.getData());
                        LiveListFragment.this.o.loadMoreComplete();
                    } else {
                        LiveListFragment.this.o.setNewData(liveListResponseModel.getData());
                    }
                    if (liveListResponseModel.getData().size() < 20) {
                        LiveListFragment.this.o.loadMoreEnd(LiveListFragment.this.k);
                    } else {
                        LiveListFragment.this.n = LiveListFragment.this.n + 1;
                    }
                } else if (z) {
                    LiveListFragment.this.o.loadMoreEnd(LiveListFragment.this.k);
                } else {
                    LiveListFragment.this.o.setNewData(null);
                    LiveListFragment.this.o.setEmptyView(LiveListFragment.this.g);
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

    public boolean o() {
        return true;
    }
}
