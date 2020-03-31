package com.lzy.imagepicker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.a.a.C0015a;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.ImageView.ScaleType;
import com.lzy.imagepicker.R;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CropImageView extends AppCompatImageView {
    private static Handler E = new a();
    /* access modifiers changed from: private */
    public static b F;
    private int A;
    private float B;
    private boolean C;
    private boolean D;
    private c[] a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private c h;
    private Paint i;
    private Path j;
    private RectF k;
    private int l;
    private int m;
    private int n;
    private int o;
    private Matrix p;

    /* renamed from: q reason: collision with root package name */
    private Matrix f424q;
    private PointF r;
    private PointF s;
    private PointF t;
    private PointF u;
    private PointF v;
    private int w;
    private long x;
    private double y;
    private float z;

    private static class a extends Handler {
        public a() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            File file = (File) message.obj;
            switch (message.what) {
                case PointerIconCompat.TYPE_CONTEXT_MENU /*1001*/:
                    if (CropImageView.F != null) {
                        CropImageView.F.onBitmapSaveSuccess(file);
                        return;
                    }
                    return;
                case PointerIconCompat.TYPE_HAND /*1002*/:
                    if (CropImageView.F != null) {
                        CropImageView.F.onBitmapSaveError(file);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public interface b {
        void onBitmapSaveError(File file);

        void onBitmapSaveSuccess(File file);
    }

    public enum c {
        RECTANGLE,
        CIRCLE
    }

    public CropImageView(Context context) {
        this(context, null);
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = new c[]{c.RECTANGLE, c.CIRCLE};
        this.b = -1358954496;
        this.c = -1434419072;
        this.d = 1;
        this.e = C0015a.DEFAULT_SWIPE_ANIMATION_DURATION;
        this.f = C0015a.DEFAULT_SWIPE_ANIMATION_DURATION;
        this.g = 0;
        this.h = this.a[this.g];
        this.i = new Paint();
        this.j = new Path();
        this.k = new RectF();
        this.p = new Matrix();
        this.f424q = new Matrix();
        this.r = new PointF();
        this.s = new PointF();
        this.t = new PointF();
        this.u = new PointF();
        this.v = new PointF();
        this.w = 0;
        this.x = 0;
        this.y = 0.0d;
        this.z = 1.0f;
        this.A = 0;
        this.B = 4.0f;
        this.C = false;
        this.D = false;
        this.e = (int) TypedValue.applyDimension(1, (float) this.e, getResources().getDisplayMetrics());
        this.f = (int) TypedValue.applyDimension(1, (float) this.f, getResources().getDisplayMetrics());
        this.d = (int) TypedValue.applyDimension(1, (float) this.d, getResources().getDisplayMetrics());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CropImageView);
        this.b = obtainStyledAttributes.getColor(R.styleable.CropImageView_cropMaskColor, this.b);
        this.c = obtainStyledAttributes.getColor(R.styleable.CropImageView_cropBorderColor, this.c);
        this.d = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CropImageView_cropBorderWidth, this.d);
        this.e = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CropImageView_cropFocusWidth, this.e);
        this.f = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CropImageView_cropFocusHeight, this.f);
        this.g = obtainStyledAttributes.getInteger(R.styleable.CropImageView_cropStyle, this.g);
        this.h = this.a[this.g];
        obtainStyledAttributes.recycle();
        setScaleType(ScaleType.MATRIX);
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        b();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        b();
    }

    public void setImageResource(int i2) {
        super.setImageResource(i2);
        b();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        b();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.C = true;
        b();
    }

    private void b() {
        Drawable drawable = getDrawable();
        if (this.C && drawable != null) {
            this.w = 0;
            this.p = getImageMatrix();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            this.n = intrinsicWidth;
            this.l = intrinsicWidth;
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.o = intrinsicHeight;
            this.m = intrinsicHeight;
            int width = getWidth();
            int height = getHeight();
            this.v = new PointF((float) (width / 2), (float) (height / 2));
            if (this.h == c.CIRCLE) {
                int min = Math.min(this.e, this.f);
                this.e = min;
                this.f = min;
            }
            this.k.left = this.v.x - ((float) (this.e / 2));
            this.k.right = this.v.x + ((float) (this.e / 2));
            this.k.top = this.v.y - ((float) (this.f / 2));
            this.k.bottom = this.v.y + ((float) (this.f / 2));
            float a2 = a(this.l, this.m, this.e, this.f, true);
            this.B = 4.0f * a2;
            float a3 = a(this.l, this.m, width, height, false);
            if (a3 <= a2) {
                a3 = a2;
            }
            this.p.setScale(a3, a3, (float) (this.l / 2), (float) (this.m / 2));
            float[] fArr = new float[9];
            this.p.getValues(fArr);
            this.p.postTranslate(this.v.x - (fArr[2] + ((((float) this.l) * fArr[0]) / 2.0f)), this.v.y - (((fArr[4] * ((float) this.m)) / 2.0f) + fArr[5]));
            setImageMatrix(this.p);
            invalidate();
        }
    }

    private float a(int i2, int i3, int i4, int i5, boolean z2) {
        float f2 = ((float) i4) / ((float) i2);
        float f3 = ((float) i5) / ((float) i3);
        if (z2) {
            return f2 > f3 ? f2 : f3;
        }
        if (f2 >= f3) {
            return f3;
        }
        return f2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (c.RECTANGLE == this.h) {
            this.j.addRect(this.k, Direction.CCW);
            canvas.save();
            canvas.clipRect(0, 0, getWidth(), getHeight());
            canvas.clipPath(this.j, Op.DIFFERENCE);
            canvas.drawColor(this.b);
            canvas.restore();
        } else if (c.CIRCLE == this.h) {
            this.j.addCircle(this.v.x, this.v.y, Math.min((this.k.right - this.k.left) / 2.0f, (this.k.bottom - this.k.top) / 2.0f), Direction.CCW);
            canvas.save();
            canvas.clipRect(0, 0, getWidth(), getHeight());
            canvas.clipPath(this.j, Op.DIFFERENCE);
            canvas.drawColor(this.b);
            canvas.restore();
        }
        this.i.setColor(this.c);
        this.i.setStyle(Style.STROKE);
        this.i.setStrokeWidth((float) this.d);
        this.i.setAntiAlias(true);
        canvas.drawPath(this.j, this.i);
        this.j.reset();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.D || getDrawable() == null) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.f424q.set(this.p);
                this.r.set(motionEvent.getX(), motionEvent.getY());
                this.s.set(motionEvent.getX(), motionEvent.getY());
                this.w = 1;
                break;
            case 1:
            case 6:
                if (this.w == 1) {
                    if (a(this.r, this.s) < 50.0f) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - this.x < 500 && a(this.r, this.u) < 50.0f) {
                            a(this.r.x, this.r.y);
                            currentTimeMillis = 0;
                        }
                        this.u.set(this.r);
                        this.x = currentTimeMillis;
                    }
                } else if (this.w == 3) {
                    int floor = (int) Math.floor((this.y + 0.7853981633974483d) / 1.5707963267948966d);
                    if (floor == 4) {
                        floor = 0;
                    }
                    this.p.set(this.f424q);
                    this.p.postRotate((float) (floor * 90), this.t.x, this.t.y);
                    if (floor == 1 || floor == 3) {
                        int i2 = this.n;
                        this.n = this.o;
                        this.o = i2;
                    }
                    c();
                    d();
                    setImageMatrix(this.p);
                    this.A = floor + this.A;
                }
                this.w = 0;
                break;
            case 2:
                if (this.w == 4) {
                    PointF pointF = new PointF((motionEvent.getX(1) - motionEvent.getX(0)) + this.r.x, (motionEvent.getY(1) - motionEvent.getY(0)) + this.r.y);
                    double a2 = (double) a(this.s.x, this.s.y, pointF.x, pointF.y);
                    double a3 = (double) a(this.r.x, this.r.y, pointF.x, pointF.y);
                    double a4 = (double) a(this.r.x, this.r.y, this.s.x, this.s.y);
                    if (a2 >= 10.0d) {
                        double acos = Math.acos((((a2 * a2) + (a4 * a4)) - (a3 * a3)) / ((a2 * 2.0d) * a4));
                        if (acos <= 0.7853981633974483d || acos >= 0.7853981633974483d * 3.0d) {
                            this.w = 2;
                        } else {
                            this.w = 3;
                        }
                    }
                }
                if (this.w != 1) {
                    if (this.w != 2) {
                        if (this.w == 3) {
                            PointF pointF2 = new PointF((motionEvent.getX(1) - motionEvent.getX(0)) + this.r.x, (motionEvent.getY(1) - motionEvent.getY(0)) + this.r.y);
                            double a5 = (double) a(this.s.x, this.s.y, pointF2.x, pointF2.y);
                            double a6 = (double) a(this.r.x, this.r.y, pointF2.x, pointF2.y);
                            double a7 = (double) a(this.r.x, this.r.y, this.s.x, this.s.y);
                            if (a6 > 10.0d) {
                                double acos2 = Math.acos((((a6 * a6) + (a7 * a7)) - (a5 * a5)) / ((a6 * 2.0d) * a7));
                                if ((((double) pointF2.y) * ((double) (this.r.x - this.s.x))) + (((double) (this.s.y - this.r.y)) * ((double) pointF2.x)) + ((double) ((this.s.x * this.r.y) - (this.r.x * this.s.y))) > 0.0d) {
                                    acos2 = 6.283185307179586d - acos2;
                                }
                                this.y = acos2;
                                this.p.set(this.f424q);
                                this.p.postRotate((float) ((this.y * 180.0d) / 3.141592653589793d), this.t.x, this.t.y);
                                setImageMatrix(this.p);
                                break;
                            }
                        }
                    } else {
                        float a8 = a(motionEvent.getX(0), motionEvent.getY(0), motionEvent.getX(1), motionEvent.getY(1));
                        if (a8 > 10.0f) {
                            this.p.set(this.f424q);
                            float min = Math.min(a8 / this.z, e());
                            if (min != 0.0f) {
                                this.p.postScale(min, min, this.t.x, this.t.y);
                                c();
                                d();
                                setImageMatrix(this.p);
                                break;
                            }
                        }
                    }
                } else {
                    this.p.set(this.f424q);
                    this.p.postTranslate(motionEvent.getX() - this.r.x, motionEvent.getY() - this.r.y);
                    d();
                    setImageMatrix(this.p);
                    break;
                }
                break;
            case 5:
                if (motionEvent.getActionIndex() <= 1) {
                    this.r.set(motionEvent.getX(0), motionEvent.getY(0));
                    this.s.set(motionEvent.getX(1), motionEvent.getY(1));
                    this.t.set((this.r.x + this.s.x) / 2.0f, (this.r.y + this.s.y) / 2.0f);
                    this.z = a(this.r, this.s);
                    this.f424q.set(this.p);
                    if (this.z > 10.0f) {
                        this.w = 4;
                        break;
                    }
                }
                break;
        }
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    private void c() {
        float[] fArr = new float[9];
        this.p.getValues(fArr);
        float abs = Math.abs(fArr[0]) + Math.abs(fArr[1]);
        float a2 = a(this.n, this.o, this.e, this.f, true);
        this.B = 4.0f * a2;
        if (abs < a2) {
            float f2 = a2 / abs;
            this.p.postScale(f2, f2);
        } else if (abs > this.B) {
            float f3 = this.B / abs;
            this.p.postScale(f3, f3);
        }
    }

    private void d() {
        float f2;
        float f3 = 0.0f;
        RectF rectF = new RectF(0.0f, 0.0f, (float) this.l, (float) this.m);
        this.p.mapRect(rectF);
        if (rectF.left > this.k.left) {
            f2 = (-rectF.left) + this.k.left;
        } else if (rectF.right < this.k.right) {
            f2 = (-rectF.right) + this.k.right;
        } else {
            f2 = 0.0f;
        }
        if (rectF.top > this.k.top) {
            f3 = (-rectF.top) + this.k.top;
        } else if (rectF.bottom < this.k.bottom) {
            f3 = (-rectF.bottom) + this.k.bottom;
        }
        this.p.postTranslate(f2, f3);
    }

    private float e() {
        float[] fArr = new float[9];
        this.p.getValues(fArr);
        return this.B / (Math.abs(fArr[1]) + Math.abs(fArr[0]));
    }

    private float a(float f2, float f3, float f4, float f5) {
        float f6 = f2 - f4;
        float f7 = f3 - f5;
        return (float) Math.sqrt((double) ((f6 * f6) + (f7 * f7)));
    }

    private float a(PointF pointF, PointF pointF2) {
        return a(pointF.x, pointF.y, pointF2.x, pointF2.y);
    }

    private void a(float f2, float f3) {
        float[] fArr = new float[9];
        this.p.getValues(fArr);
        float abs = Math.abs(fArr[0]) + Math.abs(fArr[1]);
        float a2 = a(this.n, this.o, this.e, this.f, true);
        if (abs < this.B) {
            float min = Math.min(a2 + abs, this.B) / abs;
            this.p.postScale(min, min, f2, f3);
        } else {
            float f4 = a2 / abs;
            this.p.postScale(f4, f4, f2, f3);
            d();
        }
        setImageMatrix(this.p);
    }

    public Bitmap a(int i2, int i3, boolean z2) {
        if (i2 <= 0 || i3 < 0) {
            return null;
        }
        return a(a(((BitmapDrawable) getDrawable()).getBitmap(), this.A * 90), this.k, getImageMatrixRect(), i2, i3, z2);
    }

    public Bitmap a(Bitmap bitmap, int i2) {
        if (i2 == 0 || bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i2, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (bitmap != createBitmap) {
                return createBitmap;
            }
            return bitmap;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            return bitmap;
        }
    }

    private RectF getImageMatrixRect() {
        RectF rectF = new RectF();
        rectF.set(0.0f, 0.0f, (float) getDrawable().getIntrinsicWidth(), (float) getDrawable().getIntrinsicHeight());
        this.p.mapRect(rectF);
        return rectF;
    }

    private Bitmap a(Bitmap bitmap, RectF rectF, RectF rectF2, int i2, int i3, boolean z2) {
        int i4;
        int i5;
        OutOfMemoryError e2;
        Bitmap bitmap2;
        int i6 = 0;
        if (rectF2 == null || bitmap == null) {
            return null;
        }
        float width = rectF2.width() / ((float) bitmap.getWidth());
        int i7 = (int) ((rectF.left - rectF2.left) / width);
        int i8 = (int) ((rectF.top - rectF2.top) / width);
        int width2 = (int) (rectF.width() / width);
        int height = (int) (rectF.height() / width);
        if (i7 < 0) {
            i7 = 0;
        }
        if (i8 >= 0) {
            i6 = i8;
        }
        if (i7 + width2 > bitmap.getWidth()) {
            i4 = bitmap.getWidth() - i7;
        } else {
            i4 = width2;
        }
        if (i6 + height > bitmap.getHeight()) {
            i5 = bitmap.getHeight() - i6;
        } else {
            i5 = height;
        }
        try {
            bitmap2 = Bitmap.createBitmap(bitmap, i7, i6, i4, i5);
            if (i2 == i4 && i3 == i5) {
                return bitmap2;
            }
            try {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, i2, i3, true);
                try {
                    if (this.h != c.CIRCLE || z2) {
                        return createScaledBitmap;
                    }
                    int min = Math.min(i2, i3);
                    int i9 = min / 2;
                    Bitmap createBitmap = Bitmap.createBitmap(min, min, Config.ARGB_8888);
                    Canvas canvas = new Canvas(createBitmap);
                    BitmapShader bitmapShader = new BitmapShader(createScaledBitmap, TileMode.CLAMP, TileMode.CLAMP);
                    Paint paint = new Paint();
                    paint.setShader(bitmapShader);
                    canvas.drawCircle(((float) i2) / 2.0f, ((float) i3) / 2.0f, (float) i9, paint);
                    return createBitmap;
                } catch (OutOfMemoryError e3) {
                    OutOfMemoryError outOfMemoryError = e3;
                    bitmap2 = createScaledBitmap;
                    e2 = outOfMemoryError;
                    e2.printStackTrace();
                    return bitmap2;
                }
            } catch (OutOfMemoryError e4) {
                e2 = e4;
            }
        } catch (OutOfMemoryError e5) {
            e2 = e5;
            bitmap2 = bitmap;
        }
    }

    public void a(File file, int i2, int i3, boolean z2) {
        if (!this.D) {
            this.D = true;
            final Bitmap a2 = a(i2, i3, z2);
            final CompressFormat compressFormat = CompressFormat.JPEG;
            final File a3 = a(file, "IMG_", ".jpg");
            if (this.h == c.CIRCLE && !z2) {
                compressFormat = CompressFormat.PNG;
                a3 = a(file, "IMG_", ".png");
            }
            new Thread() {
                public void run() {
                    CropImageView.this.a(a2, compressFormat, a3);
                }
            }.start();
        }
    }

    private File a(File file, String str, String str2) {
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        try {
            File file2 = new File(file, ".nomedia");
            if (!file2.exists()) {
                file2.createNewFile();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return new File(file, str + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date(System.currentTimeMillis())) + str2);
    }

    /* access modifiers changed from: private */
    public void a(Bitmap bitmap, CompressFormat compressFormat, File file) {
        OutputStream outputStream = null;
        try {
            outputStream = getContext().getContentResolver().openOutputStream(Uri.fromFile(file));
            if (outputStream != null) {
                bitmap.compress(compressFormat, 90, outputStream);
            }
            Message.obtain(E, PointerIconCompat.TYPE_CONTEXT_MENU, file).sendToTarget();
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            Message.obtain(E, PointerIconCompat.TYPE_HAND, file).sendToTarget();
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
        }
        this.D = false;
        bitmap.recycle();
    }

    public void setOnBitmapSaveCompleteListener(b bVar) {
        F = bVar;
    }

    public int getFocusWidth() {
        return this.e;
    }

    public void setFocusWidth(int i2) {
        this.e = i2;
        b();
    }

    public int getFocusHeight() {
        return this.f;
    }

    public void setFocusHeight(int i2) {
        this.f = i2;
        b();
    }

    public int getMaskColor() {
        return this.b;
    }

    public void setMaskColor(int i2) {
        this.b = i2;
        invalidate();
    }

    public int getFocusColor() {
        return this.c;
    }

    public void setBorderColor(int i2) {
        this.c = i2;
        invalidate();
    }

    public float getBorderWidth() {
        return (float) this.d;
    }

    public void setBorderWidth(int i2) {
        this.d = i2;
        invalidate();
    }

    public void setFocusStyle(c cVar) {
        this.h = cVar;
        invalidate();
    }

    public c getFocusStyle() {
        return this.h;
    }
}
