package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.StaticLayout.Builder;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: AppCompatTextViewAutoSizeHelper */
class l {
    private static final RectF a = new RectF();
    private static ConcurrentHashMap<String, Method> b = new ConcurrentHashMap<>();
    private int c = 0;
    private boolean d = false;
    private float e = -1.0f;
    private float f = -1.0f;
    private float g = -1.0f;
    private int[] h = new int[0];
    private boolean i = false;
    private TextPaint j;
    private final TextView k;
    private final Context l;

    l(TextView textView) {
        this.k = textView;
        this.l = this.k.getContext();
    }

    /* access modifiers changed from: 0000 */
    public void a(AttributeSet attributeSet, int i2) {
        float f2;
        float f3;
        float f4;
        TypedArray obtainStyledAttributes = this.l.obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, i2, 0);
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizeTextType)) {
            this.c = obtainStyledAttributes.getInt(R.styleable.AppCompatTextView_autoSizeTextType, 0);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizeStepGranularity)) {
            f2 = obtainStyledAttributes.getDimension(R.styleable.AppCompatTextView_autoSizeStepGranularity, -1.0f);
        } else {
            f2 = -1.0f;
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizeMinTextSize)) {
            f3 = obtainStyledAttributes.getDimension(R.styleable.AppCompatTextView_autoSizeMinTextSize, -1.0f);
        } else {
            f3 = -1.0f;
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizeMaxTextSize)) {
            f4 = obtainStyledAttributes.getDimension(R.styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0f);
        } else {
            f4 = -1.0f;
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizePresetSizes)) {
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_autoSizePresetSizes, 0);
            if (resourceId > 0) {
                TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
                a(obtainTypedArray);
                obtainTypedArray.recycle();
            }
        }
        obtainStyledAttributes.recycle();
        if (!k()) {
            this.c = 0;
        } else if (this.c == 1) {
            if (!this.i) {
                DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
                if (f3 == -1.0f) {
                    f3 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (f4 == -1.0f) {
                    f4 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (f2 == -1.0f) {
                    f2 = 1.0f;
                }
                a(f3, f4, f2);
            }
            i();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        if (k()) {
            switch (i2) {
                case 0:
                    j();
                    return;
                case 1:
                    DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
                    a(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
                    if (i()) {
                        f();
                        return;
                    }
                    return;
                default:
                    throw new IllegalArgumentException("Unknown auto-size text type: " + i2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3, int i4, int i5) throws IllegalArgumentException {
        if (k()) {
            DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
            a(TypedValue.applyDimension(i5, (float) i2, displayMetrics), TypedValue.applyDimension(i5, (float) i3, displayMetrics), TypedValue.applyDimension(i5, (float) i4, displayMetrics));
            if (i()) {
                f();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int[] iArr, int i2) throws IllegalArgumentException {
        if (k()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i2 == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
                    for (int i3 = 0; i3 < length; i3++) {
                        iArr2[i3] = Math.round(TypedValue.applyDimension(i2, (float) iArr[i3], displayMetrics));
                    }
                }
                this.h = a(iArr2);
                if (!h()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.i = false;
            }
            if (i()) {
                f();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int a() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public int b() {
        return Math.round(this.e);
    }

    /* access modifiers changed from: 0000 */
    public int c() {
        return Math.round(this.f);
    }

    /* access modifiers changed from: 0000 */
    public int d() {
        return Math.round(this.g);
    }

    /* access modifiers changed from: 0000 */
    public int[] e() {
        return this.h;
    }

    private void a(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = typedArray.getDimensionPixelSize(i2, -1);
            }
            this.h = a(iArr);
            h();
        }
    }

    private boolean h() {
        int length = this.h.length;
        this.i = length > 0;
        if (this.i) {
            this.c = 1;
            this.f = (float) this.h[0];
            this.g = (float) this.h[length - 1];
            this.e = -1.0f;
        }
        return this.i;
    }

    private int[] a(int[] iArr) {
        if (r2 != 0) {
            Arrays.sort(iArr);
            ArrayList arrayList = new ArrayList();
            for (int i2 : iArr) {
                if (i2 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i2)) < 0) {
                    arrayList.add(Integer.valueOf(i2));
                }
            }
            if (r2 != arrayList.size()) {
                int size = arrayList.size();
                iArr = new int[size];
                for (int i3 = 0; i3 < size; i3++) {
                    iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
                }
            }
        }
        return iArr;
    }

    private void a(float f2, float f3, float f4) throws IllegalArgumentException {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f2 + "px) is less or equal to (0px)");
        } else if (f3 <= f2) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f3 + "px) is less or equal to minimum auto-size " + "text size (" + f2 + "px)");
        } else if (f4 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f4 + "px) is less or equal to (0px)");
        } else {
            this.c = 1;
            this.f = f2;
            this.g = f3;
            this.e = f4;
            this.i = false;
        }
    }

    private boolean i() {
        if (!k() || this.c != 1) {
            this.d = false;
        } else {
            if (!this.i || this.h.length == 0) {
                float round = (float) Math.round(this.f);
                int i2 = 1;
                while (Math.round(this.e + round) <= Math.round(this.g)) {
                    i2++;
                    round += this.e;
                }
                int[] iArr = new int[i2];
                float f2 = this.f;
                for (int i3 = 0; i3 < i2; i3++) {
                    iArr[i3] = Math.round(f2);
                    f2 += this.e;
                }
                this.h = a(iArr);
            }
            this.d = true;
        }
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        int measuredWidth;
        if (g()) {
            if (this.d) {
                if (this.k.getMeasuredHeight() > 0 && this.k.getMeasuredWidth() > 0) {
                    if (((Boolean) a((Object) this.k, "getHorizontallyScrolling", (T) Boolean.valueOf(false))).booleanValue()) {
                        measuredWidth = 1048576;
                    } else {
                        measuredWidth = (this.k.getMeasuredWidth() - this.k.getTotalPaddingLeft()) - this.k.getTotalPaddingRight();
                    }
                    int height = (this.k.getHeight() - this.k.getCompoundPaddingBottom()) - this.k.getCompoundPaddingTop();
                    if (measuredWidth > 0 && height > 0) {
                        synchronized (a) {
                            a.setEmpty();
                            a.right = (float) measuredWidth;
                            a.bottom = (float) height;
                            float a2 = (float) a(a);
                            if (a2 != this.k.getTextSize()) {
                                a(0, a2);
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.d = true;
        }
    }

    private void j() {
        this.c = 0;
        this.f = -1.0f;
        this.g = -1.0f;
        this.e = -1.0f;
        this.h = new int[0];
        this.d = false;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, float f2) {
        Resources resources;
        if (this.l == null) {
            resources = Resources.getSystem();
        } else {
            resources = this.l.getResources();
        }
        a(TypedValue.applyDimension(i2, f2, resources.getDisplayMetrics()));
    }

    private void a(float f2) {
        boolean z;
        if (f2 != this.k.getPaint().getTextSize()) {
            this.k.getPaint().setTextSize(f2);
            if (VERSION.SDK_INT >= 18) {
                z = this.k.isInLayout();
            } else {
                z = false;
            }
            if (this.k.getLayout() != null) {
                this.d = false;
                String str = "nullLayouts";
                try {
                    Method a2 = a("nullLayouts");
                    if (a2 != null) {
                        a2.invoke(this.k, new Object[0]);
                    }
                } catch (Exception e2) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e2);
                }
                if (!z) {
                    this.k.requestLayout();
                } else {
                    this.k.forceLayout();
                }
                this.k.invalidate();
            }
        }
    }

    private int a(RectF rectF) {
        int length = this.h.length;
        if (length == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int i2 = 0;
        int i3 = 1;
        int i4 = length - 1;
        while (i3 <= i4) {
            int i5 = (i3 + i4) / 2;
            if (a(this.h[i5], rectF)) {
                int i6 = i5 + 1;
                i2 = i3;
                i3 = i6;
            } else {
                i4 = i5 - 1;
                i2 = i4;
            }
        }
        return this.h[i2];
    }

    private boolean a(int i2, RectF rectF) {
        StaticLayout a2;
        CharSequence text = this.k.getText();
        TransformationMethod transformationMethod = this.k.getTransformationMethod();
        if (transformationMethod != null) {
            CharSequence transformation = transformationMethod.getTransformation(text, this.k);
            if (transformation != null) {
                text = transformation;
            }
        }
        int i3 = VERSION.SDK_INT >= 16 ? this.k.getMaxLines() : -1;
        if (this.j == null) {
            this.j = new TextPaint();
        } else {
            this.j.reset();
        }
        this.j.set(this.k.getPaint());
        this.j.setTextSize((float) i2);
        Alignment alignment = (Alignment) a((Object) this.k, "getLayoutAlignment", (T) Alignment.ALIGN_NORMAL);
        if (VERSION.SDK_INT >= 23) {
            a2 = a(text, alignment, Math.round(rectF.right), i3);
        } else {
            a2 = a(text, alignment, Math.round(rectF.right));
        }
        if (i3 != -1 && (a2.getLineCount() > i3 || a2.getLineEnd(a2.getLineCount() - 1) != text.length())) {
            return false;
        }
        if (((float) a2.getHeight()) > rectF.bottom) {
            return false;
        }
        return true;
    }

    private StaticLayout a(CharSequence charSequence, Alignment alignment, int i2, int i3) {
        TextDirectionHeuristic textDirectionHeuristic = (TextDirectionHeuristic) a((Object) this.k, "getTextDirectionHeuristic", (T) TextDirectionHeuristics.FIRSTSTRONG_LTR);
        Builder hyphenationFrequency = Builder.obtain(charSequence, 0, charSequence.length(), this.j, i2).setAlignment(alignment).setLineSpacing(this.k.getLineSpacingExtra(), this.k.getLineSpacingMultiplier()).setIncludePad(this.k.getIncludeFontPadding()).setBreakStrategy(this.k.getBreakStrategy()).setHyphenationFrequency(this.k.getHyphenationFrequency());
        if (i3 == -1) {
            i3 = Integer.MAX_VALUE;
        }
        return hyphenationFrequency.setMaxLines(i3).setTextDirection(textDirectionHeuristic).build();
    }

    private StaticLayout a(CharSequence charSequence, Alignment alignment, int i2) {
        float floatValue;
        float floatValue2;
        boolean booleanValue;
        if (VERSION.SDK_INT >= 16) {
            floatValue = this.k.getLineSpacingMultiplier();
            floatValue2 = this.k.getLineSpacingExtra();
            booleanValue = this.k.getIncludeFontPadding();
        } else {
            floatValue = ((Float) a((Object) this.k, "getLineSpacingMultiplier", (T) Float.valueOf(1.0f))).floatValue();
            floatValue2 = ((Float) a((Object) this.k, "getLineSpacingExtra", (T) Float.valueOf(0.0f))).floatValue();
            booleanValue = ((Boolean) a((Object) this.k, "getIncludeFontPadding", (T) Boolean.valueOf(true))).booleanValue();
        }
        return new StaticLayout(charSequence, this.j, i2, alignment, floatValue, floatValue2, booleanValue);
    }

    private <T> T a(Object obj, String str, T t) {
        try {
            T invoke = a(str).invoke(obj, new Object[0]);
            if (invoke == null) {
            }
            return invoke;
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e2);
            if (0 != 0) {
                return null;
            }
            return t;
        } catch (Throwable th) {
            if (!(0 == 0 && 1 == 0)) {
            }
            throw th;
        }
    }

    private Method a(String str) {
        try {
            Method method = (Method) b.get(str);
            if (method != null) {
                return method;
            }
            Method declaredMethod = TextView.class.getDeclaredMethod(str, new Class[0]);
            if (declaredMethod == null) {
                return declaredMethod;
            }
            declaredMethod.setAccessible(true);
            b.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e2);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean g() {
        return k() && this.c != 0;
    }

    private boolean k() {
        return !(this.k instanceof AppCompatEditText);
    }
}
