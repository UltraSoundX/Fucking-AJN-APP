package q.rorbin.badgeview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Random;

/* compiled from: BadgeAnimator */
public class b extends ValueAnimator {
    private a[][] a;
    /* access modifiers changed from: private */
    public WeakReference<e> b;

    /* compiled from: BadgeAnimator */
    private class a {
        Random a;
        float b;
        float c;
        float d;
        int e;
        int f;
        Paint g = new Paint();

        public a() {
            this.g.setAntiAlias(true);
            this.g.setStyle(Style.FILL);
            this.a = new Random();
        }

        public void a(float f2, Canvas canvas) {
            this.g.setColor(this.e);
            this.b += ((float) this.a.nextInt(this.f)) * 0.1f * (this.a.nextFloat() - 0.5f);
            this.c += ((float) this.a.nextInt(this.f)) * 0.1f * (this.a.nextFloat() - 0.5f);
            canvas.drawCircle(this.b, this.c, this.d - (this.d * f2), this.g);
        }
    }

    public b(Bitmap bitmap, PointF pointF, e eVar) {
        this.b = new WeakReference<>(eVar);
        setFloatValues(new float[]{0.0f, 1.0f});
        setDuration(500);
        this.a = a(bitmap, pointF);
        addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                e eVar = (e) b.this.b.get();
                if (eVar == null || !eVar.isShown()) {
                    b.this.cancel();
                } else {
                    eVar.invalidate();
                }
            }
        });
        addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                e eVar = (e) b.this.b.get();
                if (eVar != null) {
                    eVar.b();
                }
            }
        });
    }

    public void a(Canvas canvas) {
        for (int i = 0; i < this.a.length; i++) {
            for (a a2 : this.a[i]) {
                a2.a(Float.parseFloat(getAnimatedValue().toString()), canvas);
            }
        }
    }

    private a[][] a(Bitmap bitmap, PointF pointF) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float min = ((float) Math.min(width, height)) / 6.0f;
        float width2 = pointF.x - (((float) bitmap.getWidth()) / 2.0f);
        float height2 = pointF.y - (((float) bitmap.getHeight()) / 2.0f);
        a[][] aVarArr = (a[][]) Array.newInstance(a.class, new int[]{(int) (((float) height) / min), (int) (((float) width) / min)});
        for (int i = 0; i < aVarArr.length; i++) {
            for (int i2 = 0; i2 < aVarArr[i].length; i2++) {
                a aVar = new a();
                aVar.e = bitmap.getPixel((int) (((float) i2) * min), (int) (((float) i) * min));
                aVar.b = (((float) i2) * min) + width2;
                aVar.c = (((float) i) * min) + height2;
                aVar.d = min;
                aVar.f = Math.max(width, height);
                aVarArr[i][i2] = aVar;
            }
        }
        bitmap.recycle();
        return aVarArr;
    }
}
