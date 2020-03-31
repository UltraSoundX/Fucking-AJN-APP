package com.e23.ajn.fragment.first_page.child;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.e23.ajn.R;
import com.e23.ajn.b.m;
import com.e23.ajn.b.n;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.c.a;
import com.e23.ajn.c.b;
import com.e23.ajn.d.e;
import com.e23.ajn.model.HotKeyWordBean;
import com.e23.ajn.model.HotKeyWordsResponseModel;
import com.e23.ajn.views.k;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Request;

public class SearchKeyWordFragment extends BaseSupportFragment {
    private View a;
    /* access modifiers changed from: private */
    public TagFlowLayout d;

    public static SearchKeyWordFragment h() {
        SearchKeyWordFragment searchKeyWordFragment = new SearchKeyWordFragment();
        searchKeyWordFragment.setArguments(new Bundle());
        return searchKeyWordFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = layoutInflater.inflate(R.layout.f155login, viewGroup, false);
        i();
        j();
        return this.a;
    }

    private void i() {
        this.d = (TagFlowLayout) this.a.findViewById(R.id.fragment_search_keyword);
    }

    private void j() {
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=content&a=hotKeyWords")).params(b.a(null)).tag(this)).build().execute(new a<HotKeyWordsResponseModel>() {
            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(HotKeyWordsResponseModel hotKeyWordsResponseModel, int i) {
                if (hotKeyWordsResponseModel != null && hotKeyWordsResponseModel.getCode() == 200 && e.b(hotKeyWordsResponseModel.getData())) {
                    final ArrayList arrayList = new ArrayList();
                    for (String str : hotKeyWordsResponseModel.getData()) {
                        HotKeyWordBean hotKeyWordBean = new HotKeyWordBean();
                        hotKeyWordBean.setKeyword(str);
                        arrayList.add(hotKeyWordBean);
                    }
                    SearchKeyWordFragment.this.d.setAdapter(new com.zhy.view.flowlayout.a<HotKeyWordBean>(arrayList) {
                        public View a(FlowLayout flowLayout, int i, HotKeyWordBean hotKeyWordBean) {
                            TextView textView = (TextView) LayoutInflater.from(SearchKeyWordFragment.this.c).inflate(R.layout.f202zhanji, flowLayout, false);
                            textView.setText(hotKeyWordBean.getKeyword());
                            return textView;
                        }

                        public boolean a(int i, HotKeyWordBean hotKeyWordBean) {
                            return super.a(i, (Object) hotKeyWordBean);
                        }
                    });
                    SearchKeyWordFragment.this.d.setOnTagClickListener(new TagFlowLayout.b() {
                        public boolean a(View view, int i, FlowLayout flowLayout) {
                            com.e23.ajn.b.e.a(SearchKeyWordFragment.this.c).c(new m(((HotKeyWordBean) arrayList.get(i)).getKeyword()));
                            return true;
                        }
                    });
                    com.e23.ajn.b.e.a(SearchKeyWordFragment.this.c).c(new n());
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                k.a();
            }

            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                k.a(SearchKeyWordFragment.this.c);
            }
        });
    }
}
