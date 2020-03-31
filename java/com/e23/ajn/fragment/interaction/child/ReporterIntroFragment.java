package com.e23.ajn.fragment.interaction.child;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.d.p;
import com.e23.ajn.views.k;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class ReporterIntroFragment extends BaseSupportFragment {
    /* access modifiers changed from: private */
    public ImageView a;
    private String d;
    /* access modifiers changed from: private */
    public String e;
    private View f;

    public static ReporterIntroFragment a(String str, String str2) {
        ReporterIntroFragment reporterIntroFragment = new ReporterIntroFragment();
        Bundle bundle = new Bundle();
        bundle.putString("reporter_intro", str);
        bundle.putString("user_id", str2);
        reporterIntroFragment.setArguments(bundle);
        return reporterIntroFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.d = getArguments().getString("reporter_intro");
            this.e = getArguments().getString("user_id");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f = layoutInflater.inflate(R.layout.f152jinanhao_content_list_act, viewGroup, false);
        h();
        return this.f;
    }

    private void h() {
        k.a(this.c);
        WebView webView = (WebView) this.f.findViewById(R.id.reporter_intro);
        this.d = "<p style=\"text-indent:2em;\">" + this.d + "</p>";
        webView.loadDataWithBaseURL(null, this.d, "text/html", "utf-8", null);
        this.a = (ImageView) this.f.findViewById(R.id.fragment_reporter_detail_post);
        this.a.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ReporterIntroFragment.this.c, SwipeBackCommonActivity.class);
                if (p.a("is_logined", false)) {
                    intent.putExtra("catid", "373");
                    intent.putExtra("reporter_id", ReporterIntroFragment.this.e);
                    intent.putExtra(SwipeBackCommonActivity.TAG, 38);
                } else {
                    intent.putExtra(SwipeBackCommonActivity.TAG, 21);
                }
                ReporterIntroFragment.this.startActivity(intent);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                ReporterIntroFragment.this.a.setVisibility(0);
                k.a();
            }
        });
    }
}
