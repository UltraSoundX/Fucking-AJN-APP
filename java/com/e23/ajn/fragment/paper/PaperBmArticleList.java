package com.e23.ajn.fragment.paper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.e23.ajn.R;
import com.e23.ajn.activity.OutUrlActivity;
import com.e23.ajn.adapter.PaperBmArticleListAdapter;
import com.e23.ajn.base.BaseSwipeBackFragment;
import com.e23.ajn.c.b;
import com.e23.ajn.d.e;
import com.e23.ajn.model.PaperBmArticleListResponse;
import com.e23.ajn.model.PaperBmArticleListResponse.DataBean;
import com.e23.ajn.views.k;
import com.jaeger.library.a;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.util.Collection;
import okhttp3.Call;
import okhttp3.Request;

public class PaperBmArticleList extends BaseSwipeBackFragment {
    protected View d;
    private String e;
    private String f;
    private String g;
    private String h;
    private Toolbar i;
    private TextView j;
    private ImageView k;
    /* access modifiers changed from: private */
    public RecyclerView l;
    private View m;
    /* access modifiers changed from: private */
    public PaperBmArticleListAdapter n;

    public static PaperBmArticleList a(String str, String str2, String str3, String str4) {
        PaperBmArticleList paperBmArticleList = new PaperBmArticleList();
        Bundle bundle = new Bundle();
        bundle.putString("paper_pid", str);
        bundle.putString("paper_name", str2);
        bundle.putString("paper_type", str3);
        bundle.putString("paper_date", str4);
        paperBmArticleList.setArguments(bundle);
        return paperBmArticleList;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.e = getArguments().getString("paper_pid");
            this.f = getArguments().getString("paper_name");
            this.g = getArguments().getString("paper_type");
            this.h = getArguments().getString("paper_date");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.m = layoutInflater.inflate(R.layout.mobilelogin, viewGroup, false);
        b(this.m);
        h();
        return this.m;
    }

    private void b(View view) {
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        this.i = (Toolbar) view.findViewById(R.id.toolbar);
        this.j = (TextView) this.i.findViewById(R.id.toolbar_center_title);
        this.k = (ImageView) this.i.findViewById(R.id.toolbar_left_back);
        this.k.setVisibility(0);
        this.k.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PaperBmArticleList.this.c.onBackPressed();
            }
        });
        this.j.setText(this.f);
        this.l = (RecyclerView) view.findViewById(R.id.message_list_rv);
        this.l.setLayoutManager(new LinearLayoutManager(this.c));
        this.d = this.c.getLayoutInflater().inflate(R.layout.design_text_input_password_icon, (ViewGroup) this.l.getParent(), false);
        this.d.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                k.a(PaperBmArticleList.this.c);
                PaperBmArticleList.this.h();
            }
        });
        this.n = new PaperBmArticleListAdapter(this.c, null);
        this.n.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(PaperBmArticleList.this.c, OutUrlActivity.class);
                intent.putExtra("title", ((DataBean) PaperBmArticleList.this.n.getData().get(i)).getTitle());
                intent.putExtra(OutUrlActivity.ARG_THUMB, "");
                intent.putExtra(OutUrlActivity.ARG_DESC, "");
                intent.putExtra("url", ((DataBean) PaperBmArticleList.this.n.getData().get(i)).getUrl());
                PaperBmArticleList.this.c.startActivity(intent);
            }
        });
        h();
    }

    /* access modifiers changed from: private */
    public void h() {
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=paper&a=getBmArticleList")).params(b.a(null)).addParams("baozhi", this.h).addParams("pid", this.e).addParams("verorder", this.g).build().execute(new com.e23.ajn.c.a<PaperBmArticleListResponse>() {
            public void onBefore(Request request, int i) {
            }

            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(PaperBmArticleListResponse paperBmArticleListResponse, int i) {
                if (paperBmArticleListResponse == null || paperBmArticleListResponse.getCode() != 200) {
                    PaperBmArticleList.this.n.setEmptyView(PaperBmArticleList.this.d);
                } else if (e.b(paperBmArticleListResponse.getData())) {
                    PaperBmArticleList.this.l.setAdapter(PaperBmArticleList.this.n);
                    PaperBmArticleList.this.n.addData((Collection<? extends T>) paperBmArticleListResponse.getData());
                    PaperBmArticleList.this.n.notifyDataSetChanged();
                }
            }
        });
    }
}
