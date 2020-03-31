package android.support.design.chip;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.design.R;
import android.support.design.a.h;
import android.support.design.e.b;
import android.support.v4.content.res.ResourcesCompat.FontCallback;
import android.support.v4.text.BidiFormatter;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewOutlineProvider;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView.BufferType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Chip extends AppCompatCheckBox implements android.support.design.chip.ChipDrawable.a {
    /* access modifiers changed from: private */
    public static final Rect a = new Rect();
    private static final int[] b = {16842913};
    /* access modifiers changed from: private */
    public ChipDrawable c;
    private RippleDrawable d;
    private OnClickListener e;
    private OnCheckedChangeListener f;
    private boolean g;
    private int h;
    private boolean i;
    private boolean j;
    private boolean k;
    private final a l;
    private final Rect m;
    private final RectF n;
    private final FontCallback o;

    private class a extends ExploreByTouchHelper {
        a(Chip chip) {
            super(chip);
        }

        /* access modifiers changed from: protected */
        public int getVirtualViewAt(float f, float f2) {
            return (!Chip.this.h() || !Chip.this.getCloseIconTouchBounds().contains(f, f2)) ? -1 : 0;
        }

        /* access modifiers changed from: protected */
        public void getVisibleVirtualViews(List<Integer> list) {
            if (Chip.this.h()) {
                list.add(Integer.valueOf(0));
            }
        }

        /* access modifiers changed from: protected */
        public void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (Chip.this.h()) {
                CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
                if (closeIconContentDescription != null) {
                    accessibilityNodeInfoCompat.setContentDescription(closeIconContentDescription);
                } else {
                    CharSequence text = Chip.this.getText();
                    Context context = Chip.this.getContext();
                    int i2 = R.string.mtrl_chip_close_icon_content_description;
                    Object[] objArr = new Object[1];
                    if (TextUtils.isEmpty(text)) {
                        text = "";
                    }
                    objArr[0] = text;
                    accessibilityNodeInfoCompat.setContentDescription(context.getString(i2, objArr).trim());
                }
                accessibilityNodeInfoCompat.setBoundsInParent(Chip.this.getCloseIconTouchBoundsInt());
                accessibilityNodeInfoCompat.addAction(AccessibilityActionCompat.ACTION_CLICK);
                accessibilityNodeInfoCompat.setEnabled(Chip.this.isEnabled());
                return;
            }
            accessibilityNodeInfoCompat.setContentDescription("");
            accessibilityNodeInfoCompat.setBoundsInParent(Chip.a);
        }

        /* access modifiers changed from: protected */
        public void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.setCheckable(Chip.this.c != null && Chip.this.c.w());
            accessibilityNodeInfoCompat.setClassName(Chip.class.getName());
            CharSequence text = Chip.this.getText();
            if (VERSION.SDK_INT >= 23) {
                accessibilityNodeInfoCompat.setText(text);
            } else {
                accessibilityNodeInfoCompat.setContentDescription(text);
            }
        }

        /* access modifiers changed from: protected */
        public boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
            if (i2 == 16 && i == 0) {
                return Chip.this.b();
            }
            return false;
        }
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.chipStyle);
    }

    public Chip(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.h = ExploreByTouchHelper.INVALID_ID;
        this.m = new Rect();
        this.n = new RectF();
        this.o = new FontCallback() {
            public void onFontRetrieved(Typeface typeface) {
                Chip.this.setText(Chip.this.getText());
                Chip.this.requestLayout();
                Chip.this.invalidate();
            }

            public void onFontRetrievalFailed(int i) {
            }
        };
        a(attributeSet);
        ChipDrawable a2 = ChipDrawable.a(context, attributeSet, i2, R.style.Widget_MaterialComponents_Chip_Action);
        setChipDrawable(a2);
        this.l = new a(this);
        ViewCompat.setAccessibilityDelegate(this, this.l);
        e();
        setChecked(this.g);
        a2.f(false);
        setText(a2.k());
        setEllipsize(a2.m());
        setIncludeFontPadding(false);
        if (getTextAppearance() != null) {
            a(getTextAppearance());
        }
        setSingleLine();
        setGravity(8388627);
        d();
    }

    private void d() {
        if (!TextUtils.isEmpty(getText()) && this.c != null) {
            float B = this.c.B() + this.c.I() + this.c.E() + this.c.F();
            if ((this.c.n() && this.c.o() != null) || (this.c.y() != null && this.c.x() && isChecked())) {
                B += this.c.C() + this.c.D() + this.c.q();
            }
            if (this.c.r() && this.c.s() != null) {
                B += this.c.G() + this.c.H() + this.c.u();
            }
            if (((float) ViewCompat.getPaddingEnd(this)) != B) {
                ViewCompat.setPaddingRelative(this, ViewCompat.getPaddingStart(this), getPaddingTop(), (int) B, getPaddingBottom());
            }
        }
    }

    private void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "background") != null) {
                throw new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable.");
            } else if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") != null) {
                throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
            } else if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") != null) {
                throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
            } else if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") != null) {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            } else if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") != null) {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            } else if (!attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) != 1) {
                throw new UnsupportedOperationException("Chip does not support multi-line text");
            } else if (attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627) != 8388627) {
                Log.w("Chip", "Chip text must be vertically center and start aligned");
            }
        }
    }

    private void e() {
        if (VERSION.SDK_INT >= 21) {
            setOutlineProvider(new ViewOutlineProvider() {
                @TargetApi(21)
                public void getOutline(View view, Outline outline) {
                    if (Chip.this.c != null) {
                        Chip.this.c.getOutline(outline);
                    } else {
                        outline.setAlpha(0.0f);
                    }
                }
            });
        }
    }

    public Drawable getChipDrawable() {
        return this.c;
    }

    public void setChipDrawable(ChipDrawable chipDrawable) {
        if (this.c != chipDrawable) {
            a(this.c);
            this.c = chipDrawable;
            b(this.c);
            if (android.support.design.f.a.a) {
                this.d = new RippleDrawable(android.support.design.f.a.a(this.c.j()), this.c, null);
                this.c.a(false);
                ViewCompat.setBackground(this, this.d);
                return;
            }
            this.c.a(true);
            ViewCompat.setBackground(this, this.c);
        }
    }

    private void a(ChipDrawable chipDrawable) {
        if (chipDrawable != null) {
            chipDrawable.a((android.support.design.chip.ChipDrawable.a) null);
        }
    }

    private void b(ChipDrawable chipDrawable) {
        chipDrawable.a((android.support.design.chip.ChipDrawable.a) this);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, b);
        }
        return onCreateDrawableState;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (TextUtils.isEmpty(getText()) || this.c == null || this.c.J()) {
            super.onDraw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.translate(c(this.c), 0.0f);
        super.onDraw(canvas);
        canvas.restoreToCount(save);
    }

    public void setGravity(int i2) {
        if (i2 != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i2);
        }
    }

    private float c(ChipDrawable chipDrawable) {
        float chipStartPadding = getChipStartPadding() + chipDrawable.b() + getTextStartPadding();
        return ViewCompat.getLayoutDirection(this) == 0 ? chipStartPadding : -chipStartPadding;
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {
        throw new UnsupportedOperationException("Do not set the background tint list; Chip manages its own background drawable.");
    }

    public void setBackgroundTintMode(Mode mode) {
        throw new UnsupportedOperationException("Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setBackgroundColor(int i2) {
        throw new UnsupportedOperationException("Do not set the background color; Chip manages its own background drawable.");
    }

    public void setBackgroundResource(int i2) {
        throw new UnsupportedOperationException("Do not set the background resource; Chip manages its own background drawable.");
    }

    public void setBackground(Drawable drawable) {
        if (drawable == this.c || drawable == this.d) {
            super.setBackground(drawable);
            return;
        }
        throw new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable.");
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == this.c || drawable == this.d) {
            super.setBackgroundDrawable(drawable);
            return;
        }
        throw new UnsupportedOperationException("Do not set the background drawable; Chip manages its own background drawable.");
    }

    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        } else {
            super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        }
    }

    public void setCompoundDrawablesWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        if (i2 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (i4 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        } else {
            super.setCompoundDrawablesWithIntrinsicBounds(i2, i3, i4, i5);
        }
    }

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        } else if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
        } else {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }
    }

    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        } else {
            super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        }
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        if (i2 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (i4 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        } else {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(i2, i3, i4, i5);
        }
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        } else {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }
    }

    public TruncateAt getEllipsize() {
        if (this.c != null) {
            return this.c.m();
        }
        return null;
    }

    public void setEllipsize(TruncateAt truncateAt) {
        if (this.c != null) {
            if (truncateAt == TruncateAt.MARQUEE) {
                throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
            }
            super.setEllipsize(truncateAt);
            if (this.c != null) {
                this.c.a(truncateAt);
            }
        }
    }

    public void setSingleLine(boolean z) {
        if (!z) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z);
    }

    public void setLines(int i2) {
        if (i2 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i2);
    }

    public void setMinLines(int i2) {
        if (i2 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i2);
    }

    public void setMaxLines(int i2) {
        if (i2 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i2);
    }

    public void setMaxWidth(int i2) {
        super.setMaxWidth(i2);
        if (this.c != null) {
            this.c.C(i2);
        }
    }

    public void a() {
        d();
        requestLayout();
        if (VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setChecked(boolean z) {
        if (this.c == null) {
            this.g = z;
        } else if (this.c.w()) {
            boolean isChecked = isChecked();
            super.setChecked(z);
            if (isChecked != z && this.f != null) {
                this.f.onCheckedChanged(this, z);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setOnCheckedChangeListenerInternal(OnCheckedChangeListener onCheckedChangeListener) {
        this.f = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(OnClickListener onClickListener) {
        this.e = onClickListener;
    }

    public boolean b() {
        boolean z;
        playSoundEffect(0);
        if (this.e != null) {
            this.e.onClick(this);
            z = true;
        } else {
            z = false;
        }
        this.l.sendEventForVirtualView(0, 1);
        return z;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            r2 = 0
            r1 = 1
            int r0 = r7.getActionMasked()
            android.graphics.RectF r3 = r6.getCloseIconTouchBounds()
            float r4 = r7.getX()
            float r5 = r7.getY()
            boolean r3 = r3.contains(r4, r5)
            switch(r0) {
                case 0: goto L_0x0024;
                case 1: goto L_0x0036;
                case 2: goto L_0x002b;
                case 3: goto L_0x0042;
                default: goto L_0x0019;
            }
        L_0x0019:
            r0 = r2
        L_0x001a:
            if (r0 != 0) goto L_0x0022
            boolean r0 = super.onTouchEvent(r7)
            if (r0 == 0) goto L_0x0023
        L_0x0022:
            r2 = r1
        L_0x0023:
            return r2
        L_0x0024:
            if (r3 == 0) goto L_0x0019
            r6.setCloseIconPressed(r1)
            r0 = r1
            goto L_0x001a
        L_0x002b:
            boolean r0 = r6.i
            if (r0 == 0) goto L_0x0019
            if (r3 != 0) goto L_0x0034
            r6.setCloseIconPressed(r2)
        L_0x0034:
            r0 = r1
            goto L_0x001a
        L_0x0036:
            boolean r0 = r6.i
            if (r0 == 0) goto L_0x0042
            r6.b()
            r0 = r1
        L_0x003e:
            r6.setCloseIconPressed(r2)
            goto L_0x001a
        L_0x0042:
            r0 = r2
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 7:
                setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
                break;
            case 10:
                setCloseIconHovered(false);
                break;
        }
        return super.onHoverEvent(motionEvent);
    }

    @SuppressLint({"PrivateApi"})
    private boolean a(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 10) {
            try {
                Field declaredField = ExploreByTouchHelper.class.getDeclaredField("mHoveredVirtualViewId");
                declaredField.setAccessible(true);
                if (((Integer) declaredField.get(this.l)).intValue() != Integer.MIN_VALUE) {
                    Method declaredMethod = ExploreByTouchHelper.class.getDeclaredMethod("updateHoveredVirtualView", new Class[]{Integer.TYPE});
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(this.l, new Object[]{Integer.valueOf(ExploreByTouchHelper.INVALID_ID)});
                    return true;
                }
            } catch (NoSuchMethodException e2) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e2);
            } catch (IllegalAccessException e3) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e3);
            } catch (InvocationTargetException e4) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e4);
            } catch (NoSuchFieldException e5) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e5);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return a(motionEvent) || this.l.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.l.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i2, Rect rect) {
        if (z) {
            setFocusedVirtualView(-1);
        } else {
            setFocusedVirtualView(ExploreByTouchHelper.INVALID_ID);
        }
        invalidate();
        super.onFocusChanged(z, i2, rect);
        this.l.onFocusChanged(z, i2, rect);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onKeyDown(int r7, android.view.KeyEvent r8) {
        /*
            r6 = this;
            r0 = 0
            r1 = 1
            int r2 = r8.getKeyCode()
            switch(r2) {
                case 21: goto L_0x000f;
                case 22: goto L_0x001e;
                case 23: goto L_0x0030;
                case 61: goto L_0x003e;
                case 66: goto L_0x0030;
                default: goto L_0x0009;
            }
        L_0x0009:
            if (r0 == 0) goto L_0x0069
            r6.invalidate()
        L_0x000e:
            return r1
        L_0x000f:
            boolean r2 = r8.hasNoModifiers()
            if (r2 == 0) goto L_0x0009
            boolean r0 = android.support.design.internal.d.a(r6)
            boolean r0 = r6.a(r0)
            goto L_0x0009
        L_0x001e:
            boolean r2 = r8.hasNoModifiers()
            if (r2 == 0) goto L_0x0009
            boolean r2 = android.support.design.internal.d.a(r6)
            if (r2 != 0) goto L_0x002b
            r0 = r1
        L_0x002b:
            boolean r0 = r6.a(r0)
            goto L_0x0009
        L_0x0030:
            int r2 = r6.h
            switch(r2) {
                case -1: goto L_0x0036;
                case 0: goto L_0x003a;
                default: goto L_0x0035;
            }
        L_0x0035:
            goto L_0x0009
        L_0x0036:
            r6.performClick()
            goto L_0x000e
        L_0x003a:
            r6.b()
            goto L_0x000e
        L_0x003e:
            boolean r2 = r8.hasNoModifiers()
            if (r2 == 0) goto L_0x0061
            r2 = 2
            r3 = r2
        L_0x0046:
            if (r3 == 0) goto L_0x0009
            android.view.ViewParent r4 = r6.getParent()
            r2 = r6
        L_0x004d:
            android.view.View r2 = r2.focusSearch(r3)
            if (r2 == 0) goto L_0x005b
            if (r2 == r6) goto L_0x005b
            android.view.ViewParent r5 = r2.getParent()
            if (r5 == r4) goto L_0x004d
        L_0x005b:
            if (r2 == 0) goto L_0x0009
            r2.requestFocus()
            goto L_0x000e
        L_0x0061:
            boolean r2 = r8.hasModifiers(r1)
            if (r2 == 0) goto L_0x006e
            r3 = r1
            goto L_0x0046
        L_0x0069:
            boolean r1 = super.onKeyDown(r7, r8)
            goto L_0x000e
        L_0x006e:
            r3 = r0
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.chip.Chip.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    private boolean a(boolean z) {
        f();
        if (z) {
            if (this.h == -1) {
                setFocusedVirtualView(0);
                return true;
            }
        } else if (this.h == 0) {
            setFocusedVirtualView(-1);
            return true;
        }
        return false;
    }

    private void f() {
        if (this.h == Integer.MIN_VALUE) {
            setFocusedVirtualView(-1);
        }
    }

    public void getFocusedRect(Rect rect) {
        if (this.h == 0) {
            rect.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(rect);
        }
    }

    private void setFocusedVirtualView(int i2) {
        if (this.h != i2) {
            if (this.h == 0) {
                setCloseIconFocused(false);
            }
            this.h = i2;
            if (i2 == 0) {
                setCloseIconFocused(true);
            }
        }
    }

    private void setCloseIconPressed(boolean z) {
        if (this.i != z) {
            this.i = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconHovered(boolean z) {
        if (this.j != z) {
            this.j = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconFocused(boolean z) {
        if (this.k != z) {
            this.k = z;
            refreshDrawableState();
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        boolean z = false;
        if (this.c != null && this.c.c()) {
            z = this.c.a(g());
        }
        if (z) {
            invalidate();
        }
    }

    private int[] g() {
        int i2;
        int i3 = 1;
        if (isEnabled()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (this.k) {
            i2++;
        }
        if (this.j) {
            i2++;
        }
        if (this.i) {
            i2++;
        }
        if (isChecked()) {
            i2++;
        }
        int[] iArr = new int[i2];
        if (isEnabled()) {
            iArr[0] = 16842910;
        } else {
            i3 = 0;
        }
        if (this.k) {
            iArr[i3] = 16842908;
            i3++;
        }
        if (this.j) {
            iArr[i3] = 16843623;
            i3++;
        }
        if (this.i) {
            iArr[i3] = 16842919;
            i3++;
        }
        if (isChecked()) {
            iArr[i3] = 16842913;
            int i4 = i3 + 1;
        }
        return iArr;
    }

    /* access modifiers changed from: private */
    public boolean h() {
        return (this.c == null || this.c.s() == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        this.n.setEmpty();
        if (h()) {
            this.c.a(this.n);
        }
        return this.n;
    }

    /* access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.m.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.m;
    }

    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i2) {
        if (!getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) || !isEnabled()) {
            return null;
        }
        return PointerIcon.getSystemIcon(getContext(), PointerIconCompat.TYPE_HAND);
    }

    public ColorStateList getChipBackgroundColor() {
        if (this.c != null) {
            return this.c.e();
        }
        return null;
    }

    public void setChipBackgroundColorResource(int i2) {
        if (this.c != null) {
            this.c.a(i2);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        if (this.c != null) {
            this.c.a(colorStateList);
        }
    }

    public float getChipMinHeight() {
        if (this.c != null) {
            return this.c.f();
        }
        return 0.0f;
    }

    public void setChipMinHeightResource(int i2) {
        if (this.c != null) {
            this.c.b(i2);
        }
    }

    public void setChipMinHeight(float f2) {
        if (this.c != null) {
            this.c.a(f2);
        }
    }

    public float getChipCornerRadius() {
        if (this.c != null) {
            return this.c.g();
        }
        return 0.0f;
    }

    public void setChipCornerRadiusResource(int i2) {
        if (this.c != null) {
            this.c.c(i2);
        }
    }

    public void setChipCornerRadius(float f2) {
        if (this.c != null) {
            this.c.b(f2);
        }
    }

    public ColorStateList getChipStrokeColor() {
        if (this.c != null) {
            return this.c.h();
        }
        return null;
    }

    public void setChipStrokeColorResource(int i2) {
        if (this.c != null) {
            this.c.d(i2);
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        if (this.c != null) {
            this.c.b(colorStateList);
        }
    }

    public float getChipStrokeWidth() {
        if (this.c != null) {
            return this.c.i();
        }
        return 0.0f;
    }

    public void setChipStrokeWidthResource(int i2) {
        if (this.c != null) {
            this.c.e(i2);
        }
    }

    public void setChipStrokeWidth(float f2) {
        if (this.c != null) {
            this.c.c(f2);
        }
    }

    public ColorStateList getRippleColor() {
        if (this.c != null) {
            return this.c.j();
        }
        return null;
    }

    public void setRippleColorResource(int i2) {
        if (this.c != null) {
            this.c.f(i2);
        }
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.c != null) {
            this.c.c(colorStateList);
        }
    }

    public CharSequence getText() {
        return this.c != null ? this.c.k() : "";
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        if (this.c != null) {
            if (charSequence == null) {
                charSequence = "";
            }
            CharSequence unicodeWrap = BidiFormatter.getInstance().unicodeWrap(charSequence);
            if (this.c.J()) {
                unicodeWrap = null;
            }
            super.setText(unicodeWrap, bufferType);
            if (this.c != null) {
                this.c.a(charSequence);
            }
        }
    }

    @Deprecated
    public void setChipTextResource(int i2) {
        setText(getResources().getString(i2));
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    private b getTextAppearance() {
        if (this.c != null) {
            return this.c.l();
        }
        return null;
    }

    private void a(b bVar) {
        TextPaint paint = getPaint();
        paint.drawableState = this.c.getState();
        bVar.b(getContext(), paint, this.o);
    }

    public void setTextAppearanceResource(int i2) {
        if (this.c != null) {
            this.c.g(i2);
        }
        setTextAppearance(getContext(), i2);
    }

    public void setTextAppearance(b bVar) {
        if (this.c != null) {
            this.c.a(bVar);
        }
        if (getTextAppearance() != null) {
            getTextAppearance().c(getContext(), getPaint(), this.o);
            a(bVar);
        }
    }

    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        if (this.c != null) {
            this.c.g(i2);
        }
        if (getTextAppearance() != null) {
            getTextAppearance().c(context, getPaint(), this.o);
            a(getTextAppearance());
        }
    }

    public void setTextAppearance(int i2) {
        super.setTextAppearance(i2);
        if (this.c != null) {
            this.c.g(i2);
        }
        if (getTextAppearance() != null) {
            getTextAppearance().c(getContext(), getPaint(), this.o);
            a(getTextAppearance());
        }
    }

    public void setChipIconVisible(int i2) {
        if (this.c != null) {
            this.c.h(i2);
        }
    }

    public void setChipIconVisible(boolean z) {
        if (this.c != null) {
            this.c.b(z);
        }
    }

    @Deprecated
    public void setChipIconEnabledResource(int i2) {
        setChipIconVisible(i2);
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    public Drawable getChipIcon() {
        if (this.c != null) {
            return this.c.o();
        }
        return null;
    }

    public void setChipIconResource(int i2) {
        if (this.c != null) {
            this.c.i(i2);
        }
    }

    public void setChipIcon(Drawable drawable) {
        if (this.c != null) {
            this.c.a(drawable);
        }
    }

    public ColorStateList getChipIconTint() {
        if (this.c != null) {
            return this.c.p();
        }
        return null;
    }

    public void setChipIconTintResource(int i2) {
        if (this.c != null) {
            this.c.j(i2);
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        if (this.c != null) {
            this.c.d(colorStateList);
        }
    }

    public float getChipIconSize() {
        if (this.c != null) {
            return this.c.q();
        }
        return 0.0f;
    }

    public void setChipIconSizeResource(int i2) {
        if (this.c != null) {
            this.c.k(i2);
        }
    }

    public void setChipIconSize(float f2) {
        if (this.c != null) {
            this.c.d(f2);
        }
    }

    public void setCloseIconVisible(int i2) {
        if (this.c != null) {
            this.c.l(i2);
        }
    }

    public void setCloseIconVisible(boolean z) {
        if (this.c != null) {
            this.c.c(z);
        }
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i2) {
        setCloseIconVisible(i2);
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    public Drawable getCloseIcon() {
        if (this.c != null) {
            return this.c.s();
        }
        return null;
    }

    public void setCloseIconResource(int i2) {
        if (this.c != null) {
            this.c.m(i2);
        }
    }

    public void setCloseIcon(Drawable drawable) {
        if (this.c != null) {
            this.c.b(drawable);
        }
    }

    public ColorStateList getCloseIconTint() {
        if (this.c != null) {
            return this.c.t();
        }
        return null;
    }

    public void setCloseIconTintResource(int i2) {
        if (this.c != null) {
            this.c.n(i2);
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        if (this.c != null) {
            this.c.e(colorStateList);
        }
    }

    public float getCloseIconSize() {
        if (this.c != null) {
            return this.c.u();
        }
        return 0.0f;
    }

    public void setCloseIconSizeResource(int i2) {
        if (this.c != null) {
            this.c.o(i2);
        }
    }

    public void setCloseIconSize(float f2) {
        if (this.c != null) {
            this.c.e(f2);
        }
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        if (this.c != null) {
            this.c.b(charSequence);
        }
    }

    public CharSequence getCloseIconContentDescription() {
        if (this.c != null) {
            return this.c.v();
        }
        return null;
    }

    public void setCheckableResource(int i2) {
        if (this.c != null) {
            this.c.p(i2);
        }
    }

    public void setCheckable(boolean z) {
        if (this.c != null) {
            this.c.d(z);
        }
    }

    public void setCheckedIconVisible(int i2) {
        if (this.c != null) {
            this.c.q(i2);
        }
    }

    public void setCheckedIconVisible(boolean z) {
        if (this.c != null) {
            this.c.e(z);
        }
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i2) {
        setCheckedIconVisible(i2);
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    public Drawable getCheckedIcon() {
        if (this.c != null) {
            return this.c.y();
        }
        return null;
    }

    public void setCheckedIconResource(int i2) {
        if (this.c != null) {
            this.c.r(i2);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        if (this.c != null) {
            this.c.c(drawable);
        }
    }

    public h getShowMotionSpec() {
        if (this.c != null) {
            return this.c.z();
        }
        return null;
    }

    public void setShowMotionSpecResource(int i2) {
        if (this.c != null) {
            this.c.s(i2);
        }
    }

    public void setShowMotionSpec(h hVar) {
        if (this.c != null) {
            this.c.a(hVar);
        }
    }

    public h getHideMotionSpec() {
        if (this.c != null) {
            return this.c.A();
        }
        return null;
    }

    public void setHideMotionSpecResource(int i2) {
        if (this.c != null) {
            this.c.t(i2);
        }
    }

    public void setHideMotionSpec(h hVar) {
        if (this.c != null) {
            this.c.b(hVar);
        }
    }

    public float getChipStartPadding() {
        if (this.c != null) {
            return this.c.B();
        }
        return 0.0f;
    }

    public void setChipStartPaddingResource(int i2) {
        if (this.c != null) {
            this.c.u(i2);
        }
    }

    public void setChipStartPadding(float f2) {
        if (this.c != null) {
            this.c.f(f2);
        }
    }

    public float getIconStartPadding() {
        if (this.c != null) {
            return this.c.C();
        }
        return 0.0f;
    }

    public void setIconStartPaddingResource(int i2) {
        if (this.c != null) {
            this.c.v(i2);
        }
    }

    public void setIconStartPadding(float f2) {
        if (this.c != null) {
            this.c.g(f2);
        }
    }

    public float getIconEndPadding() {
        if (this.c != null) {
            return this.c.D();
        }
        return 0.0f;
    }

    public void setIconEndPaddingResource(int i2) {
        if (this.c != null) {
            this.c.w(i2);
        }
    }

    public void setIconEndPadding(float f2) {
        if (this.c != null) {
            this.c.h(f2);
        }
    }

    public float getTextStartPadding() {
        if (this.c != null) {
            return this.c.E();
        }
        return 0.0f;
    }

    public void setTextStartPaddingResource(int i2) {
        if (this.c != null) {
            this.c.x(i2);
        }
    }

    public void setTextStartPadding(float f2) {
        if (this.c != null) {
            this.c.i(f2);
        }
    }

    public float getTextEndPadding() {
        if (this.c != null) {
            return this.c.F();
        }
        return 0.0f;
    }

    public void setTextEndPaddingResource(int i2) {
        if (this.c != null) {
            this.c.y(i2);
        }
    }

    public void setTextEndPadding(float f2) {
        if (this.c != null) {
            this.c.j(f2);
        }
    }

    public float getCloseIconStartPadding() {
        if (this.c != null) {
            return this.c.G();
        }
        return 0.0f;
    }

    public void setCloseIconStartPaddingResource(int i2) {
        if (this.c != null) {
            this.c.z(i2);
        }
    }

    public void setCloseIconStartPadding(float f2) {
        if (this.c != null) {
            this.c.k(f2);
        }
    }

    public float getCloseIconEndPadding() {
        if (this.c != null) {
            return this.c.H();
        }
        return 0.0f;
    }

    public void setCloseIconEndPaddingResource(int i2) {
        if (this.c != null) {
            this.c.A(i2);
        }
    }

    public void setCloseIconEndPadding(float f2) {
        if (this.c != null) {
            this.c.l(f2);
        }
    }

    public float getChipEndPadding() {
        if (this.c != null) {
            return this.c.I();
        }
        return 0.0f;
    }

    public void setChipEndPaddingResource(int i2) {
        if (this.c != null) {
            this.c.B(i2);
        }
    }

    public void setChipEndPadding(float f2) {
        if (this.c != null) {
            this.c.m(f2);
        }
    }
}
