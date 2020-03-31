package com.d.a.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.d.a.a.c.b;
import com.mob.tools.gui.AsyncImageView;
import com.mob.tools.gui.BitmapProcessor;
import com.mob.tools.utils.ResHelper;

/* compiled from: FriendListItem */
public class d extends LinearLayout {
    private ImageView a;
    private AsyncImageView b;
    private TextView c;
    private Bitmap d;
    private Bitmap e;

    public d(Context context, float f) {
        super(context);
        int i = (int) (20.0f * f);
        setPadding(i, 0, i, 0);
        setMinimumHeight((int) (96.0f * f));
        setBackgroundColor(-1);
        this.a = new ImageView(context);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        addView(this.a, layoutParams);
        this.b = new AsyncImageView(context);
        int i2 = (int) (64.0f * f);
        LayoutParams layoutParams2 = new LayoutParams(i2, i2);
        layoutParams2.gravity = 16;
        int i3 = (int) (24.0f * f);
        layoutParams2.setMargins(i3, 0, i3, 0);
        addView(this.b, layoutParams2);
        this.c = new TextView(context);
        this.c.setTextColor(-16777216);
        this.c.setTextSize(2, 18.0f);
        this.c.setSingleLine();
        LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        layoutParams3.gravity = 16;
        layoutParams3.weight = 1.0f;
        addView(this.c, layoutParams3);
        int bitmapRes = ResHelper.getBitmapRes(context, "ssdk_oks_classic_check_checked");
        if (bitmapRes > 0) {
            this.d = BitmapFactory.decodeResource(context.getResources(), bitmapRes);
        }
        int bitmapRes2 = ResHelper.getBitmapRes(getContext(), "ssdk_oks_classic_check_default");
        if (bitmapRes2 > 0) {
            this.e = BitmapFactory.decodeResource(context.getResources(), bitmapRes2);
        }
    }

    public void a(b bVar, boolean z) {
        this.c.setText(bVar.b);
        this.a.setImageBitmap(bVar.a ? this.d : this.e);
        if (this.b == null) {
            return;
        }
        if (z) {
            Bitmap bitmapFromCache = BitmapProcessor.getBitmapFromCache(bVar.e);
            if (bitmapFromCache == null || bitmapFromCache.isRecycled()) {
                this.b.execute((String) null, 0);
            } else {
                this.b.setImageBitmap(bitmapFromCache);
            }
        } else {
            this.b.execute(bVar.e, 0);
        }
    }
}
