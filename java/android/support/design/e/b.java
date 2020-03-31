package android.support.design.e;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.design.R;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.ResourcesCompat.FontCallback;
import android.text.TextPaint;
import android.util.Log;

/* compiled from: TextAppearance */
public class b {
    public final float a;
    public final ColorStateList b;
    public final ColorStateList c;
    public final ColorStateList d;
    public final int e;
    public final int f;
    public final String g;
    public final boolean h;
    public final ColorStateList i;
    public final float j;
    public final float k;
    public final float l;
    private final int m;
    /* access modifiers changed from: private */
    public boolean n = false;
    /* access modifiers changed from: private */
    public Typeface o;

    public b(Context context, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, R.styleable.TextAppearance);
        this.a = obtainStyledAttributes.getDimension(R.styleable.TextAppearance_android_textSize, 0.0f);
        this.b = a.a(context, obtainStyledAttributes, R.styleable.TextAppearance_android_textColor);
        this.c = a.a(context, obtainStyledAttributes, R.styleable.TextAppearance_android_textColorHint);
        this.d = a.a(context, obtainStyledAttributes, R.styleable.TextAppearance_android_textColorLink);
        this.e = obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_textStyle, 0);
        this.f = obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_typeface, 1);
        int a2 = a.a(obtainStyledAttributes, R.styleable.TextAppearance_fontFamily, R.styleable.TextAppearance_android_fontFamily);
        this.m = obtainStyledAttributes.getResourceId(a2, 0);
        this.g = obtainStyledAttributes.getString(a2);
        this.h = obtainStyledAttributes.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        this.i = a.a(context, obtainStyledAttributes, R.styleable.TextAppearance_android_shadowColor);
        this.j = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.k = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.l = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public Typeface a(Context context) {
        if (this.n) {
            return this.o;
        }
        if (!context.isRestricted()) {
            try {
                this.o = ResourcesCompat.getFont(context, this.m);
                if (this.o != null) {
                    this.o = Typeface.create(this.o, this.e);
                }
            } catch (NotFoundException | UnsupportedOperationException e2) {
            } catch (Exception e3) {
                Log.d("TextAppearance", "Error loading font " + this.g, e3);
            }
        }
        a();
        this.n = true;
        return this.o;
    }

    public void a(Context context, final TextPaint textPaint, final FontCallback fontCallback) {
        if (this.n) {
            a(textPaint, this.o);
            return;
        }
        a();
        if (context.isRestricted()) {
            this.n = true;
            a(textPaint, this.o);
            return;
        }
        try {
            ResourcesCompat.getFont(context, this.m, new FontCallback() {
                public void onFontRetrieved(Typeface typeface) {
                    b.this.o = Typeface.create(typeface, b.this.e);
                    b.this.a(textPaint, typeface);
                    b.this.n = true;
                    fontCallback.onFontRetrieved(typeface);
                }

                public void onFontRetrievalFailed(int i) {
                    b.this.a();
                    b.this.n = true;
                    fontCallback.onFontRetrievalFailed(i);
                }
            }, null);
        } catch (NotFoundException | UnsupportedOperationException e2) {
        } catch (Exception e3) {
            Log.d("TextAppearance", "Error loading font " + this.g, e3);
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.o == null) {
            this.o = Typeface.create(this.g, this.e);
        }
        if (this.o == null) {
            switch (this.f) {
                case 1:
                    this.o = Typeface.SANS_SERIF;
                    break;
                case 2:
                    this.o = Typeface.SERIF;
                    break;
                case 3:
                    this.o = Typeface.MONOSPACE;
                    break;
                default:
                    this.o = Typeface.DEFAULT;
                    break;
            }
            if (this.o != null) {
                this.o = Typeface.create(this.o, this.e);
            }
        }
    }

    public void b(Context context, TextPaint textPaint, FontCallback fontCallback) {
        int i2;
        c(context, textPaint, fontCallback);
        textPaint.setColor(this.b != null ? this.b.getColorForState(textPaint.drawableState, this.b.getDefaultColor()) : -16777216);
        float f2 = this.l;
        float f3 = this.j;
        float f4 = this.k;
        if (this.i != null) {
            i2 = this.i.getColorForState(textPaint.drawableState, this.i.getDefaultColor());
        } else {
            i2 = 0;
        }
        textPaint.setShadowLayer(f2, f3, f4, i2);
    }

    public void c(Context context, TextPaint textPaint, FontCallback fontCallback) {
        if (c.a()) {
            a(textPaint, a(context));
            return;
        }
        a(context, textPaint, fontCallback);
        if (!this.n) {
            a(textPaint, this.o);
        }
    }

    public void a(TextPaint textPaint, Typeface typeface) {
        textPaint.setTypeface(typeface);
        int style = (typeface.getStyle() ^ -1) & this.e;
        textPaint.setFakeBoldText((style & 1) != 0);
        textPaint.setTextSkewX((style & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.a);
    }
}
