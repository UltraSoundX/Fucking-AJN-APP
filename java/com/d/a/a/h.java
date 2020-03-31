package com.d.a.a;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView.ScaleType;
import com.d.a.c;
import com.d.a.e;
import com.mob.tools.gui.ScaledImageView;

/* compiled from: PicViewerPage */
public class h extends c implements OnGlobalLayoutListener {
    /* access modifiers changed from: private */
    public Bitmap a;
    /* access modifiers changed from: private */
    public ScaledImageView b;

    public h(e eVar) {
        super(eVar);
    }

    public void a(Bitmap bitmap) {
        this.a = bitmap;
    }

    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(1275068416));
        this.b = new ScaledImageView(this.activity);
        this.b.setScaleType(ScaleType.MATRIX);
        this.activity.setContentView(this.b);
        if (this.a != null) {
            this.b.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
    }

    public void onGlobalLayout() {
        this.b.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        this.b.post(new Runnable() {
            public void run() {
                h.this.b.setBitmap(h.this.a);
            }
        });
    }
}
