package com.e23.ajn.fragment.service.child;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.e23.ajn.R;
import com.e23.ajn.activity.OutUrlActivity;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.c.a;
import com.e23.ajn.ccb.CCBMainActivity;
import com.e23.ajn.d.p;
import com.e23.ajn.model.CCBParamResponse;
import com.e23.ajn.views.ProgressX5WebView;
import com.e23.ajn.views.k;
import com.tencent.android.tpush.common.Constants;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import okhttp3.Call;

public class ServiceFragment extends BaseSupportFragment {
    private ProgressX5WebView a;
    /* access modifiers changed from: private */
    public boolean d = false;

    public static ServiceFragment h() {
        Bundle bundle = new Bundle();
        ServiceFragment serviceFragment = new ServiceFragment();
        serviceFragment.setArguments(bundle);
        return serviceFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f156main, viewGroup, false);
        a(inflate);
        return inflate;
    }

    private void a(View view) {
        this.a = (ProgressX5WebView) view.findViewById(R.id.fragment_service_web);
        WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(true);
        this.a.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.contains("ijn_dubao://")) {
                    Intent intent = new Intent(ServiceFragment.this.c, SwipeBackCommonActivity.class);
                    intent.putExtra(SwipeBackCommonActivity.TAG, 51);
                    ServiceFragment.this.startActivity(intent);
                } else if (str.contains("ijn_ccb://")) {
                    if (p.a("is_logined", false)) {
                        ServiceFragment.this.i();
                    } else {
                        Intent intent2 = new Intent(ServiceFragment.this.c, SwipeBackCommonActivity.class);
                        intent2.putExtra(SwipeBackCommonActivity.TAG, 21);
                        ServiceFragment.this.startActivity(intent2);
                    }
                } else if (str.startsWith("http:") || str.startsWith("https:")) {
                    Intent intent3 = new Intent(ServiceFragment.this.c, OutUrlActivity.class);
                    intent3.putExtra("url", str);
                    ServiceFragment.this.startActivity(intent3);
                } else {
                    try {
                        ServiceFragment.this.d = true;
                        ServiceFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    } catch (ActivityNotFoundException e) {
                        if (str.startsWith("alipays")) {
                            Toast.makeText(ServiceFragment.this.getActivity(), "未安装支付宝", 1).show();
                        } else {
                            Toast.makeText(ServiceFragment.this.getActivity(), "未安装应用", 1).show();
                        }
                        e.getMessage();
                    }
                }
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                webView.getSettings().setBlockNetworkImage(false);
            }
        });
        this.a.setScrollBarStyle(33554432);
        this.a.loadUrl("http://appc.e23.cn/fuwu/index-8.1.php");
    }

    public void onResume() {
        super.onResume();
        this.a.onResume();
        if (this.d && this.a.canGoBack()) {
            this.a.goBack();
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        k.b(this.c, getString(R.string.waiting));
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=ccb&a=doEncrypt")).addParams(Constants.FLAG_TOKEN, p.b(Constants.FLAG_TOKEN, "")).addParams("nuserid", p.b("user_id", "")).build().execute(new a<CCBParamResponse>() {
            public void onError(Call call, Exception exc, int i) {
                k.a(ServiceFragment.this.c, "获取失败");
            }

            public void onAfter(int i) {
                super.onAfter(i);
                k.b();
            }

            /* renamed from: a */
            public void onResponse(CCBParamResponse cCBParamResponse, int i) {
                if (cCBParamResponse.getCode() == 200 && !TextUtils.isEmpty(cCBParamResponse.getData()) && !TextUtils.isEmpty(cCBParamResponse.getUrl())) {
                    Intent intent = new Intent(ServiceFragment.this.c, CCBMainActivity.class);
                    intent.putExtra("posturl", com.e23.ajn.ccb.b.a.h + cCBParamResponse.getData() + "&" + cCBParamResponse.getUrl());
                    ServiceFragment.this.startActivity(intent);
                } else if (TextUtils.isEmpty(cCBParamResponse.getMsg())) {
                    k.a(ServiceFragment.this.c, "获取失败");
                } else {
                    k.a(ServiceFragment.this.c, cCBParamResponse.getMsg());
                }
            }
        });
    }
}
