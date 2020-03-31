package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.design.R;
import android.support.design.a.a;
import android.support.design.internal.c;
import android.support.design.internal.d;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.at;
import android.support.v7.widget.f;
import android.support.v7.widget.v;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextInputLayout extends LinearLayout {
    private int A;
    private int B;
    private Drawable C;
    private final Rect D;
    private final RectF E;
    private Typeface F;
    private boolean G;
    private Drawable H;
    private CharSequence I;
    private CheckableImageButton J;
    private boolean K;
    private Drawable L;
    private Drawable M;
    private ColorStateList N;
    private boolean O;
    private Mode P;
    private boolean Q;
    private ColorStateList R;
    private ColorStateList S;
    private final int T;
    private final int U;
    private int V;
    private final int W;
    EditText a;
    private boolean aa;
    private boolean ab;
    private ValueAnimator ac;
    private boolean ad;
    private boolean ae;
    /* access modifiers changed from: private */
    public boolean af;
    boolean b;
    final d c;
    private final FrameLayout d;
    private CharSequence e;
    private final j f;
    private int g;
    private boolean h;
    private TextView i;
    private final int j;
    private final int k;
    private boolean l;
    private CharSequence m;
    private boolean n;
    private GradientDrawable o;
    private final int p;

    /* renamed from: q reason: collision with root package name */
    private final int f326q;
    private int r;
    private final int s;
    private float t;
    private float u;
    private float v;
    private float w;
    private int x;
    private final int y;
    private final int z;

    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final TextInputLayout a;

        public AccessibilityDelegate(TextInputLayout textInputLayout) {
            this.a = textInputLayout;
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4;
            CharSequence charSequence;
            boolean z5 = false;
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            EditText editText = this.a.getEditText();
            Editable editable = editText != null ? editText.getText() : null;
            CharSequence hint = this.a.getHint();
            CharSequence error = this.a.getError();
            CharSequence counterOverflowDescription = this.a.getCounterOverflowDescription();
            if (!TextUtils.isEmpty(editable)) {
                z = true;
            } else {
                z = false;
            }
            if (!TextUtils.isEmpty(hint)) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!TextUtils.isEmpty(error)) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3 || !TextUtils.isEmpty(counterOverflowDescription)) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (z) {
                accessibilityNodeInfoCompat.setText(editable);
            } else if (z2) {
                accessibilityNodeInfoCompat.setText(hint);
            }
            if (z2) {
                accessibilityNodeInfoCompat.setHintText(hint);
                if (!z && z2) {
                    z5 = true;
                }
                accessibilityNodeInfoCompat.setShowingHintText(z5);
            }
            if (z4) {
                if (z3) {
                    charSequence = error;
                } else {
                    charSequence = counterOverflowDescription;
                }
                accessibilityNodeInfoCompat.setError(charSequence);
                accessibilityNodeInfoCompat.setContentInvalid(true);
            }
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            EditText editText = this.a.getEditText();
            CharSequence charSequence = editText != null ? editText.getText() : null;
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = this.a.getHint();
            }
            if (!TextUtils.isEmpty(charSequence)) {
                accessibilityEvent.getText().add(charSequence);
            }
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        CharSequence a;
        boolean b;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.b = parcel.readInt() == 1;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.a, parcel, i);
            parcel.writeInt(this.b ? 1 : 0);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + this.a + "}";
        }
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textInputStyle);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f = new j(this);
        this.D = new Rect();
        this.E = new RectF();
        this.c = new d(this);
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        this.d = new FrameLayout(context);
        this.d.setAddStatesFromChildren(true);
        addView(this.d);
        this.c.a(a.a);
        this.c.b(a.a);
        this.c.b(8388659);
        at b2 = c.b(context, attributeSet, R.styleable.TextInputLayout, i2, R.style.Widget_Design_TextInputLayout, new int[0]);
        this.l = b2.a(R.styleable.TextInputLayout_hintEnabled, true);
        setHint(b2.c(R.styleable.TextInputLayout_android_hint));
        this.ab = b2.a(R.styleable.TextInputLayout_hintAnimationEnabled, true);
        this.p = context.getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_box_bottom_offset);
        this.f326q = context.getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_box_label_cutout_padding);
        this.s = b2.d(R.styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.t = b2.b(R.styleable.TextInputLayout_boxCornerRadiusTopStart, 0.0f);
        this.u = b2.b(R.styleable.TextInputLayout_boxCornerRadiusTopEnd, 0.0f);
        this.v = b2.b(R.styleable.TextInputLayout_boxCornerRadiusBottomEnd, 0.0f);
        this.w = b2.b(R.styleable.TextInputLayout_boxCornerRadiusBottomStart, 0.0f);
        this.B = b2.b(R.styleable.TextInputLayout_boxBackgroundColor, 0);
        this.V = b2.b(R.styleable.TextInputLayout_boxStrokeColor, 0);
        this.y = context.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_default);
        this.z = context.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_focused);
        this.x = this.y;
        setBoxBackgroundMode(b2.a(R.styleable.TextInputLayout_boxBackgroundMode, 0));
        if (b2.g(R.styleable.TextInputLayout_android_textColorHint)) {
            ColorStateList e2 = b2.e(R.styleable.TextInputLayout_android_textColorHint);
            this.S = e2;
            this.R = e2;
        }
        this.T = ContextCompat.getColor(context, R.color.mtrl_textinput_default_box_stroke_color);
        this.W = ContextCompat.getColor(context, R.color.mtrl_textinput_disabled_color);
        this.U = ContextCompat.getColor(context, R.color.mtrl_textinput_hovered_box_stroke_color);
        if (b2.g(R.styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
            setHintTextAppearance(b2.g(R.styleable.TextInputLayout_hintTextAppearance, 0));
        }
        int g2 = b2.g(R.styleable.TextInputLayout_errorTextAppearance, 0);
        boolean a2 = b2.a(R.styleable.TextInputLayout_errorEnabled, false);
        int g3 = b2.g(R.styleable.TextInputLayout_helperTextTextAppearance, 0);
        boolean a3 = b2.a(R.styleable.TextInputLayout_helperTextEnabled, false);
        CharSequence c2 = b2.c(R.styleable.TextInputLayout_helperText);
        boolean a4 = b2.a(R.styleable.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(b2.a(R.styleable.TextInputLayout_counterMaxLength, -1));
        this.k = b2.g(R.styleable.TextInputLayout_counterTextAppearance, 0);
        this.j = b2.g(R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        this.G = b2.a(R.styleable.TextInputLayout_passwordToggleEnabled, false);
        this.H = b2.a(R.styleable.TextInputLayout_passwordToggleDrawable);
        this.I = b2.c(R.styleable.TextInputLayout_passwordToggleContentDescription);
        if (b2.g(R.styleable.TextInputLayout_passwordToggleTint)) {
            this.O = true;
            this.N = b2.e(R.styleable.TextInputLayout_passwordToggleTint);
        }
        if (b2.g(R.styleable.TextInputLayout_passwordToggleTintMode)) {
            this.Q = true;
            this.P = d.a(b2.a(R.styleable.TextInputLayout_passwordToggleTintMode, -1), null);
        }
        b2.a();
        setHelperTextEnabled(a3);
        setHelperText(c2);
        setHelperTextTextAppearance(g3);
        setErrorEnabled(a2);
        setErrorTextAppearance(g2);
        setCounterEnabled(a4);
        s();
        ViewCompat.setImportantForAccessibility(this, 2);
    }

    public void addView(View view, int i2, LayoutParams layoutParams) {
        if (view instanceof EditText) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & -113) | 16;
            this.d.addView(view, layoutParams2);
            this.d.setLayoutParams(layoutParams);
            g();
            setEditText((EditText) view);
            return;
        }
        super.addView(view, i2, layoutParams);
    }

    private Drawable getBoxBackground() {
        if (this.r == 1 || this.r == 2) {
            return this.o;
        }
        throw new IllegalStateException();
    }

    public void setBoxBackgroundMode(int i2) {
        if (i2 != this.r) {
            this.r = i2;
            e();
        }
    }

    private void e() {
        f();
        if (this.r != 0) {
            g();
        }
        h();
    }

    private void f() {
        if (this.r == 0) {
            this.o = null;
        } else if (this.r == 2 && this.l && !(this.o instanceof e)) {
            this.o = new e();
        } else if (!(this.o instanceof GradientDrawable)) {
            this.o = new GradientDrawable();
        }
    }

    public void setBoxStrokeColor(int i2) {
        if (this.V != i2) {
            this.V = i2;
            d();
        }
    }

    public int getBoxStrokeColor() {
        return this.V;
    }

    public void setBoxBackgroundColorResource(int i2) {
        setBoxBackgroundColor(ContextCompat.getColor(getContext(), i2));
    }

    public void setBoxBackgroundColor(int i2) {
        if (this.B != i2) {
            this.B = i2;
            n();
        }
    }

    public int getBoxBackgroundColor() {
        return this.B;
    }

    public float getBoxCornerRadiusTopStart() {
        return this.t;
    }

    public float getBoxCornerRadiusTopEnd() {
        return this.u;
    }

    public float getBoxCornerRadiusBottomEnd() {
        return this.v;
    }

    public float getBoxCornerRadiusBottomStart() {
        return this.w;
    }

    private float[] getCornerRadiiAsArray() {
        if (!d.a(this)) {
            return new float[]{this.t, this.t, this.u, this.u, this.v, this.v, this.w, this.w};
        }
        return new float[]{this.u, this.u, this.t, this.t, this.w, this.w, this.v, this.v};
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.F) {
            this.F = typeface;
            this.c.c(typeface);
            this.f.a(typeface);
            if (this.i != null) {
                this.i.setTypeface(typeface);
            }
        }
    }

    public Typeface getTypeface() {
        return this.F;
    }

    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i2) {
        if (this.e == null || this.a == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i2);
            return;
        }
        boolean z2 = this.n;
        this.n = false;
        CharSequence hint = this.a.getHint();
        this.a.setHint(this.e);
        try {
            super.dispatchProvideAutofillStructure(viewStructure, i2);
        } finally {
            this.a.setHint(hint);
            this.n = z2;
        }
    }

    private void setEditText(EditText editText) {
        if (this.a != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (!(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.a = editText;
        e();
        setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
        if (!q()) {
            this.c.c(this.a.getTypeface());
        }
        this.c.a(this.a.getTextSize());
        int gravity = this.a.getGravity();
        this.c.b((gravity & -113) | 48);
        this.c.a(gravity);
        this.a.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                TextInputLayout.this.a(!TextInputLayout.this.af);
                if (TextInputLayout.this.b) {
                    TextInputLayout.this.a(editable.length());
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        if (this.R == null) {
            this.R = this.a.getHintTextColors();
        }
        if (this.l) {
            if (TextUtils.isEmpty(this.m)) {
                this.e = this.a.getHint();
                setHint(this.e);
                this.a.setHint(null);
            }
            this.n = true;
        }
        if (this.i != null) {
            a(this.a.getText().length());
        }
        this.f.d();
        p();
        a(false, true);
    }

    private void g() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.d.getLayoutParams();
        int j2 = j();
        if (j2 != layoutParams.topMargin) {
            layoutParams.topMargin = j2;
            this.d.requestLayout();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z2) {
        a(z2, false);
    }

    private void a(boolean z2, boolean z3) {
        boolean z4 = true;
        boolean isEnabled = isEnabled();
        boolean z5 = this.a != null && !TextUtils.isEmpty(this.a.getText());
        if (this.a == null || !this.a.hasFocus()) {
            z4 = false;
        }
        boolean g2 = this.f.g();
        if (this.R != null) {
            this.c.a(this.R);
            this.c.b(this.R);
        }
        if (!isEnabled) {
            this.c.a(ColorStateList.valueOf(this.W));
            this.c.b(ColorStateList.valueOf(this.W));
        } else if (g2) {
            this.c.a(this.f.k());
        } else if (this.h && this.i != null) {
            this.c.a(this.i.getTextColors());
        } else if (z4 && this.S != null) {
            this.c.a(this.S);
        }
        if (z5 || (isEnabled() && (z4 || g2))) {
            if (z3 || this.aa) {
                c(z2);
            }
        } else if (z3 || !this.aa) {
            d(z2);
        }
    }

    public EditText getEditText() {
        return this.a;
    }

    public void setHint(CharSequence charSequence) {
        if (this.l) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    private void setHintInternal(CharSequence charSequence) {
        if (!TextUtils.equals(charSequence, this.m)) {
            this.m = charSequence;
            this.c.a(charSequence);
            if (!this.aa) {
                u();
            }
        }
    }

    public CharSequence getHint() {
        if (this.l) {
            return this.m;
        }
        return null;
    }

    public void setHintEnabled(boolean z2) {
        if (z2 != this.l) {
            this.l = z2;
            if (!this.l) {
                this.n = false;
                if (!TextUtils.isEmpty(this.m) && TextUtils.isEmpty(this.a.getHint())) {
                    this.a.setHint(this.m);
                }
                setHintInternal(null);
            } else {
                CharSequence hint = this.a.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.m)) {
                        setHint(hint);
                    }
                    this.a.setHint(null);
                }
                this.n = true;
            }
            if (this.a != null) {
                g();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        return this.n;
    }

    public void setHintTextAppearance(int i2) {
        this.c.c(i2);
        this.S = this.c.m();
        if (this.a != null) {
            a(false);
            g();
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.R = colorStateList;
        this.S = colorStateList;
        if (this.a != null) {
            a(false);
        }
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.R;
    }

    public void setErrorEnabled(boolean z2) {
        this.f.a(z2);
    }

    public void setErrorTextAppearance(int i2) {
        this.f.b(i2);
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        this.f.a(colorStateList);
    }

    public int getErrorCurrentTextColors() {
        return this.f.j();
    }

    public void setHelperTextTextAppearance(int i2) {
        this.f.c(i2);
    }

    public void setHelperTextEnabled(boolean z2) {
        this.f.b(z2);
    }

    public void setHelperText(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (!b()) {
                setHelperTextEnabled(true);
            }
            this.f.a(charSequence);
        } else if (b()) {
            setHelperTextEnabled(false);
        }
    }

    public boolean b() {
        return this.f.f();
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        this.f.b(colorStateList);
    }

    public int getHelperTextCurrentTextColor() {
        return this.f.l();
    }

    public void setError(CharSequence charSequence) {
        if (!this.f.e()) {
            if (!TextUtils.isEmpty(charSequence)) {
                setErrorEnabled(true);
            } else {
                return;
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            this.f.b(charSequence);
        } else {
            this.f.b();
        }
    }

    public void setCounterEnabled(boolean z2) {
        if (this.b != z2) {
            if (z2) {
                this.i = new AppCompatTextView(getContext());
                this.i.setId(R.id.textinput_counter);
                if (this.F != null) {
                    this.i.setTypeface(this.F);
                }
                this.i.setMaxLines(1);
                a(this.i, this.k);
                this.f.a(this.i, 2);
                if (this.a == null) {
                    a(0);
                } else {
                    a(this.a.getText().length());
                }
            } else {
                this.f.b(this.i, 2);
                this.i = null;
            }
            this.b = z2;
        }
    }

    public void setCounterMaxLength(int i2) {
        if (this.g != i2) {
            if (i2 > 0) {
                this.g = i2;
            } else {
                this.g = -1;
            }
            if (this.b) {
                a(this.a == null ? 0 : this.a.getText().length());
            }
        }
    }

    public void setEnabled(boolean z2) {
        a((ViewGroup) this, z2);
        super.setEnabled(z2);
    }

    private static void a(ViewGroup viewGroup, boolean z2) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            childAt.setEnabled(z2);
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt, z2);
            }
        }
    }

    public int getCounterMaxLength() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public CharSequence getCounterOverflowDescription() {
        if (!this.b || !this.h || this.i == null) {
            return null;
        }
        return this.i.getContentDescription();
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        boolean z2 = this.h;
        if (this.g == -1) {
            this.i.setText(String.valueOf(i2));
            this.i.setContentDescription(null);
            this.h = false;
        } else {
            if (ViewCompat.getAccessibilityLiveRegion(this.i) == 1) {
                ViewCompat.setAccessibilityLiveRegion(this.i, 0);
            }
            this.h = i2 > this.g;
            if (z2 != this.h) {
                a(this.i, this.h ? this.j : this.k);
                if (this.h) {
                    ViewCompat.setAccessibilityLiveRegion(this.i, 1);
                }
            }
            this.i.setText(getContext().getString(R.string.character_counter_pattern, new Object[]{Integer.valueOf(i2), Integer.valueOf(this.g)}));
            this.i.setContentDescription(getContext().getString(R.string.character_counter_content_description, new Object[]{Integer.valueOf(i2), Integer.valueOf(this.g)}));
        }
        if (this.a != null && z2 != this.h) {
            a(false);
            d();
            c();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(TextView textView, int i2) {
        boolean z2 = true;
        try {
            TextViewCompat.setTextAppearance(textView, i2);
            if (VERSION.SDK_INT < 23 || textView.getTextColors().getDefaultColor() != -65281) {
                z2 = false;
            }
        } catch (Exception e2) {
        }
        if (z2) {
            TextViewCompat.setTextAppearance(textView, R.style.TextAppearance_AppCompat_Caption);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.design_error));
        }
    }

    private void h() {
        if (this.r != 0 && this.o != null && this.a != null && getRight() != 0) {
            int left = this.a.getLeft();
            int i2 = i();
            int right = this.a.getRight();
            int bottom = this.a.getBottom() + this.p;
            if (this.r == 2) {
                left += this.z / 2;
                i2 -= this.z / 2;
                right -= this.z / 2;
                bottom += this.z / 2;
            }
            this.o.setBounds(left, i2, right, bottom);
            n();
            l();
        }
    }

    private int i() {
        if (this.a == null) {
            return 0;
        }
        switch (this.r) {
            case 1:
                return this.a.getTop();
            case 2:
                return this.a.getTop() + j();
            default:
                return 0;
        }
    }

    private int j() {
        if (!this.l) {
            return 0;
        }
        switch (this.r) {
            case 0:
            case 1:
                return (int) this.c.b();
            case 2:
                return (int) (this.c.b() / 2.0f);
            default:
                return 0;
        }
    }

    private int k() {
        switch (this.r) {
            case 1:
                return getBoxBackground().getBounds().top + this.s;
            case 2:
                return getBoxBackground().getBounds().top - j();
            default:
                return getPaddingTop();
        }
    }

    private void l() {
        if (this.a != null) {
            Drawable background = this.a.getBackground();
            if (background != null) {
                if (v.c(background)) {
                    background = background.mutate();
                }
                f.b(this, this.a, new Rect());
                Rect bounds = background.getBounds();
                if (bounds.left != bounds.right) {
                    Rect rect = new Rect();
                    background.getPadding(rect);
                    background.setBounds(bounds.left - rect.left, bounds.top, (rect.right * 2) + bounds.right, this.a.getBottom());
                }
            }
        }
    }

    private void m() {
        switch (this.r) {
            case 1:
                this.x = 0;
                return;
            case 2:
                if (this.V == 0) {
                    this.V = this.S.getColorForState(getDrawableState(), this.S.getDefaultColor());
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void n() {
        if (this.o != null) {
            m();
            if (this.a != null && this.r == 2) {
                if (this.a.getBackground() != null) {
                    this.C = this.a.getBackground();
                }
                ViewCompat.setBackground(this.a, null);
            }
            if (!(this.a == null || this.r != 1 || this.C == null)) {
                ViewCompat.setBackground(this.a, this.C);
            }
            if (this.x > -1 && this.A != 0) {
                this.o.setStroke(this.x, this.A);
            }
            this.o.setCornerRadii(getCornerRadiiAsArray());
            this.o.setColor(this.B);
            invalidate();
        }
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        if (this.a != null) {
            Drawable background = this.a.getBackground();
            if (background != null) {
                o();
                if (v.c(background)) {
                    background = background.mutate();
                }
                if (this.f.g()) {
                    background.setColorFilter(f.a(this.f.j(), Mode.SRC_IN));
                } else if (!this.h || this.i == null) {
                    DrawableCompat.clearColorFilter(background);
                    this.a.refreshDrawableState();
                } else {
                    background.setColorFilter(f.a(this.i.getCurrentTextColor(), Mode.SRC_IN));
                }
            }
        }
    }

    private void o() {
        int i2 = VERSION.SDK_INT;
        if (i2 == 21 || i2 == 22) {
            Drawable background = this.a.getBackground();
            if (background != null && !this.ad) {
                Drawable newDrawable = background.getConstantState().newDrawable();
                if (background instanceof DrawableContainer) {
                    this.ad = g.a((DrawableContainer) background, newDrawable.getConstantState());
                }
                if (!this.ad) {
                    ViewCompat.setBackground(this.a, newDrawable);
                    this.ad = true;
                    e();
                }
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.f.g()) {
            savedState.a = getError();
        }
        savedState.b = this.K;
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setError(savedState.a);
        if (savedState.b) {
            b(true);
        }
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.af = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.af = false;
    }

    public CharSequence getError() {
        if (this.f.e()) {
            return this.f.h();
        }
        return null;
    }

    public CharSequence getHelperText() {
        if (this.f.f()) {
            return this.f.i();
        }
        return null;
    }

    public void setHintAnimationEnabled(boolean z2) {
        this.ab = z2;
    }

    public void draw(Canvas canvas) {
        if (this.o != null) {
            this.o.draw(canvas);
        }
        super.draw(canvas);
        if (this.l) {
            this.c.a(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        p();
        super.onMeasure(i2, i3);
    }

    private void p() {
        if (this.a != null) {
            if (r()) {
                if (this.J == null) {
                    this.J = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_password_icon, this.d, false);
                    this.J.setImageDrawable(this.H);
                    this.J.setContentDescription(this.I);
                    this.d.addView(this.J);
                    this.J.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            TextInputLayout.this.b(false);
                        }
                    });
                }
                if (this.a != null && ViewCompat.getMinimumHeight(this.a) <= 0) {
                    this.a.setMinimumHeight(ViewCompat.getMinimumHeight(this.J));
                }
                this.J.setVisibility(0);
                this.J.setChecked(this.K);
                if (this.L == null) {
                    this.L = new ColorDrawable();
                }
                this.L.setBounds(0, 0, this.J.getMeasuredWidth(), 1);
                Drawable[] compoundDrawablesRelative = TextViewCompat.getCompoundDrawablesRelative(this.a);
                if (compoundDrawablesRelative[2] != this.L) {
                    this.M = compoundDrawablesRelative[2];
                }
                TextViewCompat.setCompoundDrawablesRelative(this.a, compoundDrawablesRelative[0], compoundDrawablesRelative[1], this.L, compoundDrawablesRelative[3]);
                this.J.setPadding(this.a.getPaddingLeft(), this.a.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
                return;
            }
            if (this.J != null && this.J.getVisibility() == 0) {
                this.J.setVisibility(8);
            }
            if (this.L != null) {
                Drawable[] compoundDrawablesRelative2 = TextViewCompat.getCompoundDrawablesRelative(this.a);
                if (compoundDrawablesRelative2[2] == this.L) {
                    TextViewCompat.setCompoundDrawablesRelative(this.a, compoundDrawablesRelative2[0], compoundDrawablesRelative2[1], this.M, compoundDrawablesRelative2[3]);
                    this.L = null;
                }
            }
        }
    }

    public void setPasswordVisibilityToggleDrawable(int i2) {
        setPasswordVisibilityToggleDrawable(i2 != 0 ? android.support.v7.a.a.a.b(getContext(), i2) : null);
    }

    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.H = drawable;
        if (this.J != null) {
            this.J.setImageDrawable(drawable);
        }
    }

    public void setPasswordVisibilityToggleContentDescription(int i2) {
        setPasswordVisibilityToggleContentDescription(i2 != 0 ? getResources().getText(i2) : null);
    }

    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.I = charSequence;
        if (this.J != null) {
            this.J.setContentDescription(charSequence);
        }
    }

    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.H;
    }

    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.I;
    }

    public void setPasswordVisibilityToggleEnabled(boolean z2) {
        if (this.G != z2) {
            this.G = z2;
            if (!z2 && this.K && this.a != null) {
                this.a.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            this.K = false;
            p();
        }
    }

    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        this.N = colorStateList;
        this.O = true;
        s();
    }

    public void setPasswordVisibilityToggleTintMode(Mode mode) {
        this.P = mode;
        this.Q = true;
        s();
    }

    public void b(boolean z2) {
        if (this.G) {
            int selectionEnd = this.a.getSelectionEnd();
            if (q()) {
                this.a.setTransformationMethod(null);
                this.K = true;
            } else {
                this.a.setTransformationMethod(PasswordTransformationMethod.getInstance());
                this.K = false;
            }
            this.J.setChecked(this.K);
            if (z2) {
                this.J.jumpDrawablesToCurrentState();
            }
            this.a.setSelection(selectionEnd);
        }
    }

    public void setTextInputAccessibilityDelegate(AccessibilityDelegate accessibilityDelegate) {
        if (this.a != null) {
            ViewCompat.setAccessibilityDelegate(this.a, accessibilityDelegate);
        }
    }

    private boolean q() {
        return this.a != null && (this.a.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    private boolean r() {
        return this.G && (q() || this.K);
    }

    private void s() {
        if (this.H == null) {
            return;
        }
        if (this.O || this.Q) {
            this.H = DrawableCompat.wrap(this.H).mutate();
            if (this.O) {
                DrawableCompat.setTintList(this.H, this.N);
            }
            if (this.Q) {
                DrawableCompat.setTintMode(this.H, this.P);
            }
            if (this.J != null && this.J.getDrawable() != this.H) {
                this.J.setImageDrawable(this.H);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        if (this.o != null) {
            h();
        }
        if (this.l && this.a != null) {
            Rect rect = this.D;
            f.b(this, this.a, rect);
            int compoundPaddingLeft = rect.left + this.a.getCompoundPaddingLeft();
            int compoundPaddingRight = rect.right - this.a.getCompoundPaddingRight();
            int k2 = k();
            this.c.a(compoundPaddingLeft, rect.top + this.a.getCompoundPaddingTop(), compoundPaddingRight, rect.bottom - this.a.getCompoundPaddingBottom());
            this.c.b(compoundPaddingLeft, k2, compoundPaddingRight, (i5 - i3) - getPaddingBottom());
            this.c.k();
            if (t() && !this.aa) {
                u();
            }
        }
    }

    private void c(boolean z2) {
        if (this.ac != null && this.ac.isRunning()) {
            this.ac.cancel();
        }
        if (!z2 || !this.ab) {
            this.c.b(1.0f);
        } else {
            a(1.0f);
        }
        this.aa = false;
        if (t()) {
            u();
        }
    }

    private boolean t() {
        return this.l && !TextUtils.isEmpty(this.m) && (this.o instanceof e);
    }

    private void u() {
        if (t()) {
            RectF rectF = this.E;
            this.c.a(rectF);
            a(rectF);
            ((e) this.o).a(rectF);
        }
    }

    private void v() {
        if (t()) {
            ((e) this.o).b();
        }
    }

    private void a(RectF rectF) {
        rectF.left -= (float) this.f326q;
        rectF.top -= (float) this.f326q;
        rectF.right += (float) this.f326q;
        rectF.bottom += (float) this.f326q;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        boolean z2;
        boolean z3 = true;
        if (!this.ae) {
            this.ae = true;
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            if (!ViewCompat.isLaidOut(this) || !isEnabled()) {
                z3 = false;
            }
            a(z3);
            c();
            h();
            d();
            if (this.c != null) {
                z2 = this.c.a(drawableState) | false;
            } else {
                z2 = false;
            }
            if (z2) {
                invalidate();
            }
            this.ae = false;
        }
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        boolean z2 = true;
        if (this.o != null && this.r != 0) {
            boolean z3 = this.a != null && this.a.hasFocus();
            if (this.a == null || !this.a.isHovered()) {
                z2 = false;
            }
            if (this.r == 2) {
                if (!isEnabled()) {
                    this.A = this.W;
                } else if (this.f.g()) {
                    this.A = this.f.j();
                } else if (this.h && this.i != null) {
                    this.A = this.i.getCurrentTextColor();
                } else if (z3) {
                    this.A = this.V;
                } else if (z2) {
                    this.A = this.U;
                } else {
                    this.A = this.T;
                }
                if ((z2 || z3) && isEnabled()) {
                    this.x = this.z;
                } else {
                    this.x = this.y;
                }
                n();
            }
        }
    }

    private void d(boolean z2) {
        if (this.ac != null && this.ac.isRunning()) {
            this.ac.cancel();
        }
        if (!z2 || !this.ab) {
            this.c.b(0.0f);
        } else {
            a(0.0f);
        }
        if (t() && ((e) this.o).a()) {
            v();
        }
        this.aa = true;
    }

    /* access modifiers changed from: 0000 */
    public void a(float f2) {
        if (this.c.i() != f2) {
            if (this.ac == null) {
                this.ac = new ValueAnimator();
                this.ac.setInterpolator(a.b);
                this.ac.setDuration(167);
                this.ac.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        TextInputLayout.this.c.b(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                });
            }
            this.ac.setFloatValues(new float[]{this.c.i(), f2});
            this.ac.start();
        }
    }

    /* access modifiers changed from: 0000 */
    public final int getHintCurrentCollapsedTextColor() {
        return this.c.j();
    }

    /* access modifiers changed from: 0000 */
    public final float getHintCollapsedTextHeight() {
        return this.c.b();
    }

    /* access modifiers changed from: 0000 */
    public final int getErrorTextCurrentColor() {
        return this.f.j();
    }
}
