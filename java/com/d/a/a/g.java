package com.d.a.a;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mob.tools.utils.ResHelper;

/* compiled from: PRTHeader */
public class g extends LinearLayout {
    private TextView a;
    private k b;
    private ProgressBar c;

    public g(Context context) {
        super(context);
        int[] screenSize = ResHelper.getScreenSize(context);
        float f = (screenSize[0] < screenSize[1] ? (float) screenSize[0] : (float) screenSize[1]) / 720.0f;
        setOrientation(1);
        LinearLayout linearLayout = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        addView(linearLayout, layoutParams);
        this.b = new k(context);
        int bitmapRes = ResHelper.getBitmapRes(context, "ssdk_oks_ptr_ptr");
        if (bitmapRes > 0) {
            this.b.setImageResource(bitmapRes);
        }
        int i = (int) (64.0f * f);
        LayoutParams layoutParams2 = new LayoutParams(i, i);
        layoutParams2.gravity = 16;
        int i2 = (int) (f * 24.0f);
        layoutParams2.bottomMargin = i2;
        layoutParams2.topMargin = i2;
        linearLayout.addView(this.b, layoutParams2);
        this.c = new ProgressBar(context);
        this.c.setIndeterminateDrawable(context.getResources().getDrawable(ResHelper.getBitmapRes(context, "ssdk_oks_classic_progressbar")));
        linearLayout.addView(this.c, layoutParams2);
        this.c.setVisibility(8);
        this.a = new TextView(getContext());
        this.a.setTextSize(2, 18.0f);
        this.a.setPadding(i2, 0, i2, 0);
        this.a.setTextColor(-16139513);
        LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        layoutParams3.gravity = 16;
        linearLayout.addView(this.a, layoutParams3);
    }

    public void a(int i) {
        int i2 = 180;
        if (i > 100) {
            int i3 = ((i - 100) * 180) / 20;
            if (i3 <= 180) {
                i2 = i3;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            this.b.setRotation((float) i2);
        } else {
            this.b.setRotation(0.0f);
        }
        if (i < 100) {
            int stringRes = ResHelper.getStringRes(getContext(), "ssdk_oks_pull_to_refresh");
            if (stringRes > 0) {
                this.a.setText(stringRes);
                return;
            }
            return;
        }
        int stringRes2 = ResHelper.getStringRes(getContext(), "ssdk_oks_release_to_refresh");
        if (stringRes2 > 0) {
            this.a.setText(stringRes2);
        }
    }

    public void a() {
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        int stringRes = ResHelper.getStringRes(getContext(), "ssdk_oks_refreshing");
        if (stringRes > 0) {
            this.a.setText(stringRes);
        }
    }

    public void b() {
        this.c.setVisibility(8);
        this.b.setRotation(180.0f);
        this.b.setVisibility(0);
    }
}
