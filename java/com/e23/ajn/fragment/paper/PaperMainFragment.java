package com.e23.ajn.fragment.paper;

import android.app.Activity;
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
import com.e23.ajn.adapter.PaperMainAdapter;
import com.e23.ajn.c.b;
import com.e23.ajn.d.e;
import com.e23.ajn.model.PaperMainResponse;
import com.e23.ajn.model.PaperMainResponse.DataBean;
import com.e23.ajn.views.k;
import com.jaeger.library.a;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.util.Collection;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.d;
import okhttp3.Call;
import okhttp3.Request;

public class PaperMainFragment extends SupportFragment {
    protected View a;
    private Toolbar d;
    private TextView e;
    private ImageView f;
    /* access modifiers changed from: private */
    public RecyclerView g;
    private View h;
    /* access modifiers changed from: private */
    public PaperMainAdapter i;

    public static PaperMainFragment g() {
        Bundle bundle = new Bundle();
        PaperMainFragment paperMainFragment = new PaperMainFragment();
        paperMainFragment.setArguments(bundle);
        return paperMainFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.h = layoutInflater.inflate(R.layout.mobilelogin, viewGroup, false);
        a(this.h);
        return this.h;
    }

    private void a(View view) {
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        this.d = (Toolbar) view.findViewById(R.id.toolbar);
        this.e = (TextView) this.d.findViewById(R.id.toolbar_center_title);
        this.f = (ImageView) this.d.findViewById(R.id.toolbar_left_back);
        this.f.setVisibility(0);
        this.f.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PaperMainFragment.this.c.onBackPressed();
            }
        });
        this.e.setText(getString(R.string.paper_read));
        this.g = (RecyclerView) view.findViewById(R.id.message_list_rv);
        this.g.setLayoutManager(new LinearLayoutManager(this.c));
        this.a = this.c.getLayoutInflater().inflate(R.layout.design_text_input_password_icon, (ViewGroup) this.g.getParent(), false);
        this.a.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                k.a(PaperMainFragment.this.c);
                PaperMainFragment.this.h();
            }
        });
        this.i = new PaperMainAdapter(this.c, null);
        this.i.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DataBean dataBean = (DataBean) PaperMainFragment.this.i.getData().get(i);
                PaperMainFragment.this.a((d) PaperBmPicFragment.a(dataBean.getPaperid(), dataBean.getPapername(), dataBean.getPapertype(), dataBean.getPaperdate()));
            }
        });
        h();
    }

    /* access modifiers changed from: private */
    public void h() {
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=paper&a=getPaperIndexList")).params(b.a(null)).build().execute(new com.e23.ajn.c.a<PaperMainResponse>() {
            public void onBefore(Request request, int i) {
            }

            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(PaperMainResponse paperMainResponse, int i) {
                if (paperMainResponse == null || paperMainResponse.getCode() != 200) {
                    PaperMainFragment.this.i.setEmptyView(PaperMainFragment.this.a);
                } else if (e.b(paperMainResponse.getData())) {
                    PaperMainFragment.this.g.setAdapter(PaperMainFragment.this.i);
                    PaperMainFragment.this.i.addData((Collection<? extends T>) paperMainResponse.getData());
                    PaperMainFragment.this.i.notifyDataSetChanged();
                }
            }
        });
    }
}
