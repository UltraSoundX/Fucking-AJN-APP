package com.e23.ajn.fragment.interaction.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.i;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.adapter.ReporterListAdapter;
import com.e23.ajn.c.a;
import com.e23.ajn.d.e;
import com.e23.ajn.fragment.BaseListFragment;
import com.e23.ajn.model.ReporterListResponse;
import com.e23.ajn.model.ReporterListResponse.DataBean;
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.k;
import com.zhy.http.okhttp.callback.Callback;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

public class ReporterListFragment extends BaseListFragment {
    private String o;
    private String p;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public ReporterListAdapter f412q;

    public static ReporterListFragment a(String str, String str2) {
        ReporterListFragment reporterListFragment = new ReporterListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cat_id", str);
        bundle.putString("cat_name", str2);
        reporterListFragment.setArguments(bundle);
        return reporterListFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.o = getArguments().getString("cat_id");
            this.p = getArguments().getString("cat_name");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        r();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void r() {
        this.f412q = new ReporterListAdapter(this.c, null);
    }

    public boolean h() {
        return false;
    }

    public boolean o() {
        return true;
    }

    public String i() {
        return "";
    }

    public BaseQuickAdapter j() {
        return this.f412q;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent = new Intent();
        intent.setClass(this.c, SwipeBackCommonActivity.class);
        intent.putExtra(SwipeBackCommonActivity.TAG, 36);
        intent.putExtra("userid", ((DataBean) this.f412q.getData().get(i)).getUserid());
        this.c.startActivity(intent);
    }

    public String k() {
        return "http://appc.e23.cn/index.php?m=api&c=reporter&a=reporter_list_pub";
    }

    public Map<String, String> b(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("r_type", this.o);
        return hashMap;
    }

    public Callback c(boolean z) {
        return new a<ReporterListResponse>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                ReporterListFragment.this.f.setRefreshing(true);
                ReporterListFragment.this.f412q.setEnableLoadMore(false);
            }

            public void onAfter(int i) {
                super.onAfter(i);
                ReporterListFragment.this.f.setRefreshing(false);
                ReporterListFragment.this.f412q.setEnableLoadMore(false);
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                ReporterListFragment.this.f412q.setNewData(null);
                ReporterListFragment.this.f412q.setEmptyView(ReporterListFragment.this.h);
            }

            /* renamed from: a */
            public void onResponse(ReporterListResponse reporterListResponse, int i) {
                if (reporterListResponse == null || reporterListResponse.getCode() != 200) {
                    ReporterListFragment.this.f412q.setNewData(null);
                    ReporterListFragment.this.f412q.setEmptyView(ReporterListFragment.this.h);
                } else if (e.b(reporterListResponse.getData())) {
                    ReporterListFragment.this.f412q.setNewData(reporterListResponse.getData());
                } else {
                    ReporterListFragment.this.f412q.setNewData(null);
                    ReporterListFragment.this.f412q.setEmptyView(ReporterListFragment.this.g);
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
