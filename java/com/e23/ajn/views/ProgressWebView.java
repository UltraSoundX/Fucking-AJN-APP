package com.e23.ajn.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.ProgressBar;
import com.e23.ajn.R;

public class ProgressWebView extends WebView {
    private ProgressBar a;

    public ProgressWebView(Context context) {
        super(context);
        this.a = new ProgressBar(context, null, 16842872);
        this.a.setMax(100);
        this.a.setLayoutParams(new LayoutParams(-1, 8, 0, 0));
        this.a.setProgressDrawable(context.getResources().getDrawable(R.drawable.f61ssdk_weibo_empty_failed));
        addView(this.a);
        setWebChromeClient(new l(this.a));
    }

    public ProgressWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new ProgressBar(context, null, 16842872);
        this.a.setMax(100);
        this.a.setLayoutParams(new LayoutParams(-1, 8, 0, 0));
        addView(this.a);
        setWebChromeClient(new l(this.a));
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        layoutParams.x = i;
        layoutParams.y = i2;
        this.a.setLayoutParams(layoutParams);
        super.onScrollChanged(i, i2, i3, i4);
    }

    public ProgressBar getBar() {
        return this.a;
    }
}
