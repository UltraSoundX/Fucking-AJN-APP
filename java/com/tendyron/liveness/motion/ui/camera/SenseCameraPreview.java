package com.tendyron.liveness.motion.ui.camera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.Size;
import com.sensetime.senseid.sdk.liveness.interactive.type.BoundInfo;
import java.io.IOException;

public class SenseCameraPreview extends ViewGroup {
    private static final int g = 0;
    public Rect a = null;
    /* access modifiers changed from: private */
    public Context b;
    private SurfaceView c;
    private boolean d;
    /* access modifiers changed from: private */
    public boolean e;
    private a f;

    private class a implements Callback {
        private a() {
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            SenseCameraPreview.this.e = true;
            try {
                SenseCameraPreview.this.c();
            } catch (Exception e) {
                ((Activity) SenseCameraPreview.this.b).setResult(3);
                ((Activity) SenseCameraPreview.this.b).finish();
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            SenseCameraPreview.this.e = false;
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }
    }

    public SenseCameraPreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        this.d = false;
        this.e = false;
        this.c = new SurfaceView(context);
        this.c.getHolder().addCallback(new a());
        addView(this.c);
    }

    public void a(a aVar) throws IOException, RuntimeException {
        if (aVar == null) {
            a();
        }
        this.f = aVar;
        if (this.f != null) {
            this.d = true;
            c();
        }
    }

    public void a() {
        if (this.f != null) {
            this.f.b();
        }
    }

    public void b() {
        if (this.f != null) {
            this.f.a();
            this.f = null;
        }
    }

    /* access modifiers changed from: private */
    public void c() throws IOException, RuntimeException {
        if (this.d && this.e) {
            this.f.a(this.c.getHolder());
            requestLayout();
            this.d = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (this.f != null) {
            Size c2 = this.f.c();
            if (c2 != null) {
                int width = c2.getWidth();
                int height = c2.getHeight();
                if (!d()) {
                    int i7 = height;
                    height = width;
                    width = i7;
                }
                int i8 = i3 - i;
                int i9 = i4 - i2;
                float f2 = ((float) height) / ((float) width);
                if (Float.compare(((float) i8) / ((float) i9), f2) <= 0) {
                    i6 = (int) (f2 * ((float) i9));
                    i5 = i9;
                } else {
                    i5 = (int) (((float) i8) / f2);
                    i6 = i8;
                }
                for (int i10 = 0; i10 < getChildCount(); i10++) {
                    getChildAt(i10).layout(0, 0, i6, i5);
                }
                try {
                    c();
                } catch (Exception e2) {
                    ((Activity) this.b).setResult(3);
                    ((Activity) this.b).finish();
                }
            }
        }
    }

    private boolean d() {
        int i = this.b.getResources().getConfiguration().orientation;
        if (i == 2 || i != 1) {
            return false;
        }
        return true;
    }

    public BoundInfo a(BoundInfo boundInfo) {
        BoundInfo boundInfo2;
        int e2 = this.f.e();
        int width = this.f.c().getWidth();
        int height = this.f.c().getHeight();
        float scaleToConvert = getScaleToConvert();
        BoundInfo boundInfo3 = new BoundInfo((int) ((((float) boundInfo.getX()) * scaleToConvert) + 0.5f), (int) ((((float) boundInfo.getY()) * scaleToConvert) + 0.5f), (int) ((scaleToConvert * ((float) boundInfo.getRadius())) + 0.5f));
        switch (e2) {
            case 0:
                boundInfo2 = boundInfo3;
                break;
            case 90:
                boundInfo2 = new BoundInfo(boundInfo3.getY(), height - boundInfo3.getX(), boundInfo3.getRadius());
                break;
            case 180:
                boundInfo2 = new BoundInfo(width - boundInfo3.getX(), height - boundInfo3.getY(), boundInfo3.getRadius());
                break;
            case 270:
                boundInfo2 = new BoundInfo(width - boundInfo3.getY(), boundInfo3.getX(), boundInfo3.getRadius());
                break;
            default:
                boundInfo2 = boundInfo3;
                break;
        }
        if (this.f.d() != 1) {
            return new BoundInfo(boundInfo2.getX() + 0, boundInfo2.getY(), boundInfo2.getRadius());
        }
        switch (e2) {
            case 0:
            case 180:
                return new BoundInfo(width - boundInfo2.getX(), boundInfo2.getY(), boundInfo2.getRadius());
            case 90:
            case 270:
                return new BoundInfo(boundInfo2.getX(), height - boundInfo2.getY(), boundInfo2.getRadius());
            default:
                return boundInfo2;
        }
    }

    public Rect a(Rect rect) {
        int e2 = this.f.e();
        int width = this.f.c().getWidth();
        int height = this.f.c().getHeight();
        float scaleToConvert = getScaleToConvert();
        this.a = new Rect((int) ((((float) rect.left) * scaleToConvert) + 0.5f), (int) ((((float) rect.top) * scaleToConvert) + 0.5f), (int) ((((float) rect.right) * scaleToConvert) + 0.5f), (int) ((scaleToConvert * ((float) rect.bottom)) + 0.5f));
        Rect rect2 = new Rect(this.a);
        switch (e2) {
            case 90:
                rect2 = new Rect(this.a.top, height - this.a.right, this.a.bottom, height - this.a.left);
                break;
            case 180:
                rect2 = new Rect(width - this.a.right, height - this.a.bottom, width - this.a.left, height - this.a.top);
                break;
            case 270:
                rect2 = new Rect(width - this.a.bottom, this.a.left, width - this.a.top, this.a.right);
                break;
        }
        Rect rect3 = new Rect(rect2);
        if (this.f.d() != 1) {
            return new Rect(rect2.left + 0, rect2.top, rect2.right + 0, rect2.bottom);
        }
        switch (e2) {
            case 0:
            case 180:
                return new Rect(width - rect2.right, rect2.top, width - rect2.left, rect2.bottom);
            case 90:
            case 270:
                return new Rect(rect2.left, height - rect2.bottom, rect2.right, height - rect2.top);
            default:
                return rect3;
        }
    }

    public float getScaleToConvert() {
        float f2;
        float f3;
        int width = getWidth();
        int height = getHeight();
        int e2 = this.f.e();
        int width2 = this.f.c().getWidth();
        int height2 = this.f.c().getHeight();
        switch (e2) {
            case 90:
            case 270:
                f2 = ((float) height2) / ((float) width);
                f3 = ((float) width2) / ((float) height);
                break;
            default:
                f2 = ((float) width2) / ((float) width);
                f3 = ((float) height2) / ((float) height);
                break;
        }
        if (f2 < f3) {
            return f2;
        }
        return f3;
    }
}
