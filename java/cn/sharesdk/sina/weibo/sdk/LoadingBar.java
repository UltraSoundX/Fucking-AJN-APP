package cn.sharesdk.sina.weibo.sdk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

public class LoadingBar extends TextView {
    /* access modifiers changed from: private */
    public int a;
    private int b;
    private Paint c;
    private Handler d;
    private Runnable e;

    public LoadingBar(Context context) {
        super(context);
        this.e = new Runnable() {
            public void run() {
                LoadingBar.this.a = LoadingBar.this.a + 1;
                LoadingBar.this.a(LoadingBar.this.a);
            }
        };
        a(context);
    }

    public LoadingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public LoadingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.d = new Handler();
        this.c = new Paint();
        a();
    }

    public void a() {
        this.b = -11693826;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.c.setColor(this.b);
        canvas.drawRect(b(), this.c);
    }

    private Rect b() {
        return new Rect(0, 0, (getLeft() + (((getRight() - getLeft()) * this.a) / 100)) - getLeft(), getBottom() - getTop());
    }

    public void a(int i) {
        if (i < 7) {
            this.d.postDelayed(this.e, 70);
        } else {
            this.d.removeCallbacks(this.e);
            this.a = i;
        }
        invalidate();
    }
}
