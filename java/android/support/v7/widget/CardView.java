package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.cardview.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

public class CardView extends FrameLayout {
    private static final int[] e = {16842801};
    private static final q f;
    int a;
    int b;
    final Rect c;
    final Rect d;
    private boolean g;
    private boolean h;
    private final p i;

    static {
        if (VERSION.SDK_INT >= 21) {
            f = new n();
        } else if (VERSION.SDK_INT >= 17) {
            f = new m();
        } else {
            f = new o();
        }
        f.a();
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.cardViewStyle);
    }

    public CardView(Context context, AttributeSet attributeSet, int i2) {
        int color;
        ColorStateList valueOf;
        super(context, attributeSet, i2);
        this.c = new Rect();
        this.d = new Rect();
        this.i = new p() {
            private Drawable b;

            public void a(Drawable drawable) {
                this.b = drawable;
                CardView.this.setBackgroundDrawable(drawable);
            }

            public boolean a() {
                return CardView.this.getUseCompatPadding();
            }

            public boolean b() {
                return CardView.this.getPreventCornerOverlap();
            }

            public void a(int i, int i2, int i3, int i4) {
                CardView.this.d.set(i, i2, i3, i4);
                CardView.super.setPadding(CardView.this.c.left + i, CardView.this.c.top + i2, CardView.this.c.right + i3, CardView.this.c.bottom + i4);
            }

            public void a(int i, int i2) {
                if (i > CardView.this.a) {
                    CardView.super.setMinimumWidth(i);
                }
                if (i2 > CardView.this.b) {
                    CardView.super.setMinimumHeight(i2);
                }
            }

            public Drawable c() {
                return this.b;
            }

            public View d() {
                return CardView.this;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CardView, i2, R.style.CardView);
        if (obtainStyledAttributes.hasValue(R.styleable.CardView_cardBackgroundColor)) {
            valueOf = obtainStyledAttributes.getColorStateList(R.styleable.CardView_cardBackgroundColor);
        } else {
            TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(e);
            int color2 = obtainStyledAttributes2.getColor(0, 0);
            obtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color2, fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(R.color.cardview_light_background);
            } else {
                color = getResources().getColor(R.color.cardview_dark_background);
            }
            valueOf = ColorStateList.valueOf(color);
        }
        float dimension = obtainStyledAttributes.getDimension(R.styleable.CardView_cardCornerRadius, 0.0f);
        float dimension2 = obtainStyledAttributes.getDimension(R.styleable.CardView_cardElevation, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(R.styleable.CardView_cardMaxElevation, 0.0f);
        this.g = obtainStyledAttributes.getBoolean(R.styleable.CardView_cardUseCompatPadding, false);
        this.h = obtainStyledAttributes.getBoolean(R.styleable.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPadding, 0);
        this.c.left = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPaddingLeft, dimensionPixelSize);
        this.c.top = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPaddingTop, dimensionPixelSize);
        this.c.right = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPaddingRight, dimensionPixelSize);
        this.c.bottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPaddingBottom, dimensionPixelSize);
        if (dimension2 > dimension3) {
            dimension3 = dimension2;
        }
        this.a = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_android_minWidth, 0);
        this.b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_android_minHeight, 0);
        obtainStyledAttributes.recycle();
        f.a(this.i, context, valueOf, dimension, dimension2, dimension3);
    }

    public void setPadding(int i2, int i3, int i4, int i5) {
    }

    public void setPaddingRelative(int i2, int i3, int i4, int i5) {
    }

    public boolean getUseCompatPadding() {
        return this.g;
    }

    public void setUseCompatPadding(boolean z) {
        if (this.g != z) {
            this.g = z;
            f.g(this.i);
        }
    }

    public void a(int i2, int i3, int i4, int i5) {
        this.c.set(i2, i3, i4, i5);
        f.f(this.i);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (!(f instanceof n)) {
            int mode = MeasureSpec.getMode(i2);
            switch (mode) {
                case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                case 1073741824:
                    i2 = MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil((double) f.b(this.i)), MeasureSpec.getSize(i2)), mode);
                    break;
            }
            int mode2 = MeasureSpec.getMode(i3);
            switch (mode2) {
                case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                case 1073741824:
                    i3 = MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil((double) f.c(this.i)), MeasureSpec.getSize(i3)), mode2);
                    break;
            }
            super.onMeasure(i2, i3);
            return;
        }
        super.onMeasure(i2, i3);
    }

    public void setMinimumWidth(int i2) {
        this.a = i2;
        super.setMinimumWidth(i2);
    }

    public void setMinimumHeight(int i2) {
        this.b = i2;
        super.setMinimumHeight(i2);
    }

    public void setCardBackgroundColor(int i2) {
        f.a(this.i, ColorStateList.valueOf(i2));
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        f.a(this.i, colorStateList);
    }

    public ColorStateList getCardBackgroundColor() {
        return f.i(this.i);
    }

    public int getContentPaddingLeft() {
        return this.c.left;
    }

    public int getContentPaddingRight() {
        return this.c.right;
    }

    public int getContentPaddingTop() {
        return this.c.top;
    }

    public int getContentPaddingBottom() {
        return this.c.bottom;
    }

    public void setRadius(float f2) {
        f.a(this.i, f2);
    }

    public float getRadius() {
        return f.d(this.i);
    }

    public void setCardElevation(float f2) {
        f.c(this.i, f2);
    }

    public float getCardElevation() {
        return f.e(this.i);
    }

    public void setMaxCardElevation(float f2) {
        f.b(this.i, f2);
    }

    public float getMaxCardElevation() {
        return f.a(this.i);
    }

    public boolean getPreventCornerOverlap() {
        return this.h;
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.h) {
            this.h = z;
            f.h(this.i);
        }
    }
}
