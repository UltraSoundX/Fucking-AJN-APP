package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.ResourcesCompat.FontCallback;
import android.support.v7.a.a.a;
import android.util.AttributeSet;
import android.util.TypedValue;

/* compiled from: TintTypedArray */
public class at {
    private final Context a;
    private final TypedArray b;
    private TypedValue c;

    public static at a(Context context, AttributeSet attributeSet, int[] iArr) {
        return new at(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static at a(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new at(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public static at a(Context context, int i, int[] iArr) {
        return new at(context, context.obtainStyledAttributes(i, iArr));
    }

    private at(Context context, TypedArray typedArray) {
        this.a = context;
        this.b = typedArray;
    }

    public Drawable a(int i) {
        if (this.b.hasValue(i)) {
            int resourceId = this.b.getResourceId(i, 0);
            if (resourceId != 0) {
                return a.b(this.a, resourceId);
            }
        }
        return this.b.getDrawable(i);
    }

    public Drawable b(int i) {
        if (this.b.hasValue(i)) {
            int resourceId = this.b.getResourceId(i, 0);
            if (resourceId != 0) {
                return f.a().a(this.a, resourceId, true);
            }
        }
        return null;
    }

    public Typeface a(int i, int i2, FontCallback fontCallback) {
        int resourceId = this.b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.c == null) {
            this.c = new TypedValue();
        }
        return ResourcesCompat.getFont(this.a, resourceId, this.c, i2, fontCallback);
    }

    public CharSequence c(int i) {
        return this.b.getText(i);
    }

    public String d(int i) {
        return this.b.getString(i);
    }

    public boolean a(int i, boolean z) {
        return this.b.getBoolean(i, z);
    }

    public int a(int i, int i2) {
        return this.b.getInt(i, i2);
    }

    public float a(int i, float f) {
        return this.b.getFloat(i, f);
    }

    public int b(int i, int i2) {
        return this.b.getColor(i, i2);
    }

    public ColorStateList e(int i) {
        if (this.b.hasValue(i)) {
            int resourceId = this.b.getResourceId(i, 0);
            if (resourceId != 0) {
                ColorStateList a2 = a.a(this.a, resourceId);
                if (a2 != null) {
                    return a2;
                }
            }
        }
        return this.b.getColorStateList(i);
    }

    public int c(int i, int i2) {
        return this.b.getInteger(i, i2);
    }

    public float b(int i, float f) {
        return this.b.getDimension(i, f);
    }

    public int d(int i, int i2) {
        return this.b.getDimensionPixelOffset(i, i2);
    }

    public int e(int i, int i2) {
        return this.b.getDimensionPixelSize(i, i2);
    }

    public int f(int i, int i2) {
        return this.b.getLayoutDimension(i, i2);
    }

    public int g(int i, int i2) {
        return this.b.getResourceId(i, i2);
    }

    public CharSequence[] f(int i) {
        return this.b.getTextArray(i);
    }

    public boolean g(int i) {
        return this.b.hasValue(i);
    }

    public void a() {
        this.b.recycle();
    }
}
