package com.e23.ajn.fragment.first_page.child;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.i;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.d.b.b;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.SpanSizeLookup;
import com.e23.ajn.R;
import com.e23.ajn.adapter.NewsItemAdapter;
import com.e23.ajn.c.a;
import com.e23.ajn.d.e;
import com.e23.ajn.d.q;
import com.e23.ajn.d.v;
import com.e23.ajn.fragment.BaseListFragment;
import com.e23.ajn.model.NewsBean;
import com.e23.ajn.model.NewsFoundationBean;
import com.e23.ajn.model.TopicContentResponseModel;
import com.e23.ajn.model.TopicContentResponseModel.DataBean;
import com.e23.ajn.views.k;
import com.e23.ajn.views.o;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

public class TopicContentFragment extends BaseListFragment {
    /* access modifiers changed from: private */
    public String o;
    private boolean p;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public NewsItemAdapter f407q;
    private View r;
    private ImageView s;
    private TextView t;
    /* access modifiers changed from: private */
    public String u = "";
    /* access modifiers changed from: private */
    public o v;

    public static TopicContentFragment a(String str, boolean z) {
        TopicContentFragment topicContentFragment = new TopicContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cat_id", str);
        bundle.putBoolean("has_bar", z);
        topicContentFragment.setArguments(bundle);
        return topicContentFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.o = getArguments().getString("cat_id");
            this.p = getArguments().getBoolean("has_bar", false);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        r();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void r() {
        this.f407q = new NewsItemAdapter(this.c, null, false, true);
        this.f407q.setSpanSizeLookup(new SpanSizeLookup() {
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                return ((NewsBean) TopicContentFragment.this.f407q.getData().get(i)).getSpanSize();
            }
        });
    }

    public boolean o() {
        return false;
    }

    public boolean m() {
        return false;
    }

    public boolean h() {
        return this.p;
    }

    public String i() {
        return "";
    }

    public i n() {
        return new GridLayoutManager(this.c, 2);
    }

    public BaseQuickAdapter j() {
        return this.f407q;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        v.a(this.c, (NewsFoundationBean) this.f407q.getItem(i), this.f407q);
    }

    public String k() {
        return "http://appc.e23.cn/index.php?m=api&c=content&a=ztContentList_android";
    }

    public Map<String, String> b(boolean z) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(this.o)) {
            hashMap.put("catid", this.o);
        }
        return hashMap;
    }

    /* renamed from: d */
    public a c(boolean z) {
        return new a<TopicContentResponseModel>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                TopicContentFragment.this.f.setRefreshing(true);
                TopicContentFragment.this.f407q.setEnableLoadMore(false);
            }

            public void onAfter(int i) {
                super.onAfter(i);
                TopicContentFragment.this.f.setRefreshing(false);
                TopicContentFragment.this.f407q.setEnableLoadMore(false);
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                TopicContentFragment.this.f407q.setNewData(null);
                TopicContentFragment.this.f407q.setEmptyView(TopicContentFragment.this.h);
                TopicContentFragment.this.f407q.setEnableLoadMore(false);
            }

            /* renamed from: a */
            public void onResponse(TopicContentResponseModel topicContentResponseModel, int i) {
                if (topicContentResponseModel == null || topicContentResponseModel.getCode() != 200) {
                    TopicContentFragment.this.f407q.setNewData(null);
                    TopicContentFragment.this.f407q.setEmptyView(TopicContentFragment.this.h);
                    return;
                }
                TopicContentFragment.this.u = topicContentResponseModel.getData().getSharedomain();
                DataBean data = topicContentResponseModel.getData();
                if (data != null) {
                    List catContentList = data.getCatContentList();
                    TopicContentFragment.this.a(data);
                    TopicContentFragment.this.f407q.setNewData(catContentList);
                    if (e.a(catContentList) && TextUtils.isEmpty(data.getCatname()) && TextUtils.isEmpty(data.getDescription()) && TextUtils.isEmpty(data.getThumb())) {
                        TopicContentFragment.this.f407q.setEmptyView(TopicContentFragment.this.g);
                        return;
                    }
                    return;
                }
                TopicContentFragment.this.f407q.setNewData(null);
                TopicContentFragment.this.f407q.setEmptyView(TopicContentFragment.this.g);
            }
        };
    }

    public void l() {
        this.f407q.setEnableLoadMore(false);
        onRefresh();
    }

    /* access modifiers changed from: private */
    public void a(final DataBean dataBean) {
        this.v = new o(this.c);
        RelativeLayout relativeLayout = (RelativeLayout) super.p().findViewById(R.id.toolbar_share_layout);
        relativeLayout.setVisibility(0);
        relativeLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (TopicContentFragment.this.v != null && !TopicContentFragment.this.v.isShowing()) {
                    String str = TopicContentFragment.this.u + "/index.php?m=content&c=index&a=special_show&catid=" + TopicContentFragment.this.o;
                    if (TextUtils.isEmpty(dataBean.getThumb())) {
                        TopicContentFragment.this.v.a(dataBean.getCatname(), dataBean.getDescription(), "http://appc.e23.cn/ajnapi/ic_launcher.png", str);
                    } else {
                        TopicContentFragment.this.v.a(dataBean.getCatname(), dataBean.getDescription(), dataBean.getThumb(), str);
                    }
                    TopicContentFragment.this.v.show();
                }
            }
        });
        if (dataBean != null) {
            if (this.r == null) {
                this.r = this.c.getLayoutInflater().inflate(R.layout.f191npl_item, (ViewGroup) this.e.getParent(), false);
                this.s = (ImageView) this.r.findViewById(R.id.topic_content_header_img);
                this.s.setLayoutParams(new LayoutParams(-1, q.b(this.c) / 2));
                this.t = (TextView) this.r.findViewById(R.id.topic_content_header_content);
            } else {
                ViewParent parent = this.r.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.r);
                }
            }
            this.f407q.addHeaderView(this.r, 0);
            this.i.setText(dataBean.getCatname());
            this.t.setText(dataBean.getDescription());
            if (TextUtils.isEmpty(dataBean.getCatname())) {
                this.i.setVisibility(8);
            } else {
                this.i.setVisibility(0);
            }
            if (TextUtils.isEmpty(dataBean.getDescription())) {
                this.t.setVisibility(8);
            } else {
                this.t.setVisibility(0);
            }
            if (TextUtils.isEmpty(dataBean.getThumb())) {
                this.s.setVisibility(8);
            } else {
                this.s.setVisibility(0);
            }
            if (dataBean.getThumb().contains(".gif")) {
                com.bumptech.glide.i.a(this.c).a(dataBean.getThumb()).i().b(b.SOURCE).d((int) R.mipmap.f275plimg).c((int) R.mipmap.f275plimg).a(this.s);
            } else {
                com.bumptech.glide.i.a(this.c).a(dataBean.getThumb()).b(b.ALL).d((int) R.mipmap.f275plimg).c((int) R.mipmap.f275plimg).a(this.s);
            }
        } else if (this.r != null) {
            this.f407q.removeHeaderView(this.r);
        }
    }
}
