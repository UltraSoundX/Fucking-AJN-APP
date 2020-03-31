package com.e23.ajn.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.ProgressBar;
import com.e23.ajn.R;
import com.tencent.smtt.sdk.WebView;

public class ProgressX5WebView extends WebView {
    private ProgressBar b;

    public ProgressX5WebView(Context context) {
        super(context);
        b(context);
    }

    public ProgressX5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b(context);
    }

    private void b(Context context) {
        this.b = new ProgressBar(context, null, 16842872);
        this.b.setMax(100);
        this.b.setLayoutParams(new LayoutParams(-1, 8, 0, 0));
        this.b.setProgressDrawable(context.getResources().getDrawable(R.drawable.f61ssdk_weibo_empty_failed));
        addView(this.b);
        setWebChromeClient(new m(this.b));
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
        layoutParams.x = i;
        layoutParams.y = i2;
        this.b.setLayoutParams(layoutParams);
        super.onScrollChanged(i, i2, i3, i4);
    }

    public ProgressBar getBar() {
        return this.b;
    }
}
