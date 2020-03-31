package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.database.DataSetObserver;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
    private static final int[] d = {16843505};
    b a;
    int b;
    final Rect c;
    private final d e;
    private final Context f;
    private z g;
    private SpinnerAdapter h;
    private final boolean i;

    private static class a implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter a;
        private ListAdapter b;

        public a(SpinnerAdapter spinnerAdapter, Theme theme) {
            this.a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.b = (ListAdapter) spinnerAdapter;
            }
            if (theme == null) {
                return;
            }
            if (VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                    themedSpinnerAdapter.setDropDownViewTheme(theme);
                }
            } else if (spinnerAdapter instanceof ap) {
                ap apVar = (ap) spinnerAdapter;
                if (apVar.a() == null) {
                    apVar.a(theme);
                }
            }
        }

        public int getCount() {
            if (this.a == null) {
                return 0;
            }
            return this.a.getCount();
        }

        public Object getItem(int i) {
            if (this.a == null) {
                return null;
            }
            return this.a.getItem(i);
        }

        public long getItemId(int i) {
            if (this.a == null) {
                return -1;
            }
            return this.a.getItemId(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            if (this.a == null) {
                return null;
            }
            return this.a.getDropDownView(i, view, viewGroup);
        }

        public boolean hasStableIds() {
            return this.a != null && this.a.hasStableIds();
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.a != null) {
                this.a.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.a != null) {
                this.a.unregisterDataSetObserver(dataSetObserver);
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean isEmpty() {
            return getCount() == 0;
        }
    }

    private class b extends ListPopupWindow {
        ListAdapter a;
        private CharSequence h;
        private final Rect i = new Rect();

        public b(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            b((View) AppCompatSpinner.this);
            a(true);
            a(0);
            a((OnItemClickListener) new OnItemClickListener(AppCompatSpinner.this) {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    AppCompatSpinner.this.setSelection(i);
                    if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                        AppCompatSpinner.this.performItemClick(view, i, b.this.a.getItemId(i));
                    }
                    b.this.e();
                }
            });
        }

        public void a(ListAdapter listAdapter) {
            super.a(listAdapter);
            this.a = listAdapter;
        }

        public CharSequence a() {
            return this.h;
        }

        public void a(CharSequence charSequence) {
            this.h = charSequence;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            int i2;
            int i3;
            Drawable h2 = h();
            if (h2 != null) {
                h2.getPadding(AppCompatSpinner.this.c);
                i2 = bb.a(AppCompatSpinner.this) ? AppCompatSpinner.this.c.right : -AppCompatSpinner.this.c.left;
            } else {
                Rect rect = AppCompatSpinner.this.c;
                AppCompatSpinner.this.c.right = 0;
                rect.left = 0;
                i2 = 0;
            }
            int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
            int paddingRight = AppCompatSpinner.this.getPaddingRight();
            int width = AppCompatSpinner.this.getWidth();
            if (AppCompatSpinner.this.b == -2) {
                int a2 = AppCompatSpinner.this.a((SpinnerAdapter) this.a, h());
                int i4 = (AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels - AppCompatSpinner.this.c.left) - AppCompatSpinner.this.c.right;
                if (a2 <= i4) {
                    i4 = a2;
                }
                g(Math.max(i4, (width - paddingLeft) - paddingRight));
            } else if (AppCompatSpinner.this.b == -1) {
                g((width - paddingLeft) - paddingRight);
            } else {
                g(AppCompatSpinner.this.b);
            }
            if (bb.a(AppCompatSpinner.this)) {
                i3 = ((width - paddingRight) - l()) + i2;
            } else {
                i3 = i2 + paddingLeft;
            }
            c(i3);
        }

        public void d() {
            boolean f = f();
            b();
            h(2);
            super.d();
            g().setChoiceMode(1);
            i(AppCompatSpinner.this.getSelectedItemPosition());
            if (!f) {
                ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    final AnonymousClass2 r1 = new OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            if (!b.this.a((View) AppCompatSpinner.this)) {
                                b.this.e();
                                return;
                            }
                            b.this.b();
                            b.super.d();
                        }
                    };
                    viewTreeObserver.addOnGlobalLayoutListener(r1);
                    a((OnDismissListener) new OnDismissListener() {
                        public void onDismiss() {
                            ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                            if (viewTreeObserver != null) {
                                viewTreeObserver.removeGlobalOnLayoutListener(r1);
                            }
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean a(View view) {
            return ViewCompat.isAttachedToWindow(view) && view.getGlobalVisibleRect(this.i);
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.spinnerStyle);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, -1);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i2, int i3) {
        this(context, attributeSet, i2, i3, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00db  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppCompatSpinner(android.content.Context r9, android.util.AttributeSet r10, int r11, int r12, android.content.res.Resources.Theme r13) {
        /*
            r8 = this;
            r7 = 1
            r1 = 0
            r6 = 0
            r8.<init>(r9, r10, r11)
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r8.c = r0
            int[] r0 = android.support.v7.appcompat.R.styleable.Spinner
            android.support.v7.widget.at r3 = android.support.v7.widget.at.a(r9, r10, r0, r11, r6)
            android.support.v7.widget.d r0 = new android.support.v7.widget.d
            r0.<init>(r8)
            r8.e = r0
            if (r13 == 0) goto L_0x00a9
            android.support.v7.view.d r0 = new android.support.v7.view.d
            r0.<init>(r9, r13)
            r8.f = r0
        L_0x0023:
            android.content.Context r0 = r8.f
            if (r0 == 0) goto L_0x007b
            r0 = -1
            if (r12 != r0) goto L_0x0043
            int[] r0 = d     // Catch:{ Exception -> 0x00c7, all -> 0x00d7 }
            r2 = 0
            android.content.res.TypedArray r2 = r9.obtainStyledAttributes(r10, r0, r11, r2)     // Catch:{ Exception -> 0x00c7, all -> 0x00d7 }
            r0 = 0
            boolean r0 = r2.hasValue(r0)     // Catch:{ Exception -> 0x00e1 }
            if (r0 == 0) goto L_0x003e
            r0 = 0
            r4 = 0
            int r12 = r2.getInt(r0, r4)     // Catch:{ Exception -> 0x00e1 }
        L_0x003e:
            if (r2 == 0) goto L_0x0043
            r2.recycle()
        L_0x0043:
            if (r12 != r7) goto L_0x007b
            android.support.v7.widget.AppCompatSpinner$b r0 = new android.support.v7.widget.AppCompatSpinner$b
            android.content.Context r2 = r8.f
            r0.<init>(r2, r10, r11)
            android.content.Context r2 = r8.f
            int[] r4 = android.support.v7.appcompat.R.styleable.Spinner
            android.support.v7.widget.at r2 = android.support.v7.widget.at.a(r2, r10, r4, r11, r6)
            int r4 = android.support.v7.appcompat.R.styleable.Spinner_android_dropDownWidth
            r5 = -2
            int r4 = r2.f(r4, r5)
            r8.b = r4
            int r4 = android.support.v7.appcompat.R.styleable.Spinner_android_popupBackground
            android.graphics.drawable.Drawable r4 = r2.a(r4)
            r0.a(r4)
            int r4 = android.support.v7.appcompat.R.styleable.Spinner_android_prompt
            java.lang.String r4 = r3.d(r4)
            r0.a(r4)
            r2.a()
            r8.a = r0
            android.support.v7.widget.AppCompatSpinner$1 r2 = new android.support.v7.widget.AppCompatSpinner$1
            r2.<init>(r8, r0)
            r8.g = r2
        L_0x007b:
            int r0 = android.support.v7.appcompat.R.styleable.Spinner_android_entries
            java.lang.CharSequence[] r0 = r3.f(r0)
            if (r0 == 0) goto L_0x0093
            android.widget.ArrayAdapter r2 = new android.widget.ArrayAdapter
            r4 = 17367048(0x1090008, float:2.5162948E-38)
            r2.<init>(r9, r4, r0)
            int r0 = android.support.v7.appcompat.R.layout.support_simple_spinner_dropdown_item
            r2.setDropDownViewResource(r0)
            r8.setAdapter(r2)
        L_0x0093:
            r3.a()
            r8.i = r7
            android.widget.SpinnerAdapter r0 = r8.h
            if (r0 == 0) goto L_0x00a3
            android.widget.SpinnerAdapter r0 = r8.h
            r8.setAdapter(r0)
            r8.h = r1
        L_0x00a3:
            android.support.v7.widget.d r0 = r8.e
            r0.a(r10, r11)
            return
        L_0x00a9:
            int r0 = android.support.v7.appcompat.R.styleable.Spinner_popupTheme
            int r0 = r3.g(r0, r6)
            if (r0 == 0) goto L_0x00ba
            android.support.v7.view.d r2 = new android.support.v7.view.d
            r2.<init>(r9, r0)
            r8.f = r2
            goto L_0x0023
        L_0x00ba:
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 23
            if (r0 >= r2) goto L_0x00c5
            r0 = r9
        L_0x00c1:
            r8.f = r0
            goto L_0x0023
        L_0x00c5:
            r0 = r1
            goto L_0x00c1
        L_0x00c7:
            r0 = move-exception
            r2 = r1
        L_0x00c9:
            java.lang.String r4 = "AppCompatSpinner"
            java.lang.String r5 = "Could not read android:spinnerMode"
            android.util.Log.i(r4, r5, r0)     // Catch:{ all -> 0x00df }
            if (r2 == 0) goto L_0x0043
            r2.recycle()
            goto L_0x0043
        L_0x00d7:
            r0 = move-exception
            r2 = r1
        L_0x00d9:
            if (r2 == 0) goto L_0x00de
            r2.recycle()
        L_0x00de:
            throw r0
        L_0x00df:
            r0 = move-exception
            goto L_0x00d9
        L_0x00e1:
            r0 = move-exception
            goto L_0x00c9
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }

    public Context getPopupContext() {
        if (this.a != null) {
            return this.f;
        }
        if (VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }
        return null;
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        if (this.a != null) {
            this.a.a(drawable);
        } else if (VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    public void setPopupBackgroundResource(int i2) {
        setPopupBackgroundDrawable(android.support.v7.a.a.a.b(getPopupContext(), i2));
    }

    public Drawable getPopupBackground() {
        if (this.a != null) {
            return this.a.h();
        }
        if (VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }

    public void setDropDownVerticalOffset(int i2) {
        if (this.a != null) {
            this.a.d(i2);
        } else if (VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(i2);
        }
    }

    public int getDropDownVerticalOffset() {
        if (this.a != null) {
            return this.a.k();
        }
        if (VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    public void setDropDownHorizontalOffset(int i2) {
        if (this.a != null) {
            this.a.c(i2);
        } else if (VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(i2);
        }
    }

    public int getDropDownHorizontalOffset() {
        if (this.a != null) {
            return this.a.j();
        }
        if (VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    public void setDropDownWidth(int i2) {
        if (this.a != null) {
            this.b = i2;
        } else if (VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(i2);
        }
    }

    public int getDropDownWidth() {
        if (this.a != null) {
            return this.b;
        }
        if (VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.i) {
            this.h = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.a != null) {
            this.a.a((ListAdapter) new a(spinnerAdapter, (this.f == null ? getContext() : this.f).getTheme()));
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null && this.a.f()) {
            this.a.e();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.g == null || !this.g.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.a != null && MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), MeasureSpec.getSize(i2)), getMeasuredHeight());
        }
    }

    public boolean performClick() {
        if (this.a == null) {
            return super.performClick();
        }
        if (!this.a.f()) {
            this.a.d();
        }
        return true;
    }

    public void setPrompt(CharSequence charSequence) {
        if (this.a != null) {
            this.a.a(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public CharSequence getPrompt() {
        return this.a != null ? this.a.a() : super.getPrompt();
    }

    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        if (this.e != null) {
            this.e.a(i2);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.e != null) {
            this.e.a(drawable);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.e != null) {
            this.e.a(colorStateList);
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        if (this.e != null) {
            return this.e.a();
        }
        return null;
    }

    public void setSupportBackgroundTintMode(Mode mode) {
        if (this.e != null) {
            this.e.a(mode);
        }
    }

    public Mode getSupportBackgroundTintMode() {
        if (this.e != null) {
            return this.e.b();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.e != null) {
            this.e.c();
        }
    }

    /* access modifiers changed from: 0000 */
    public int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        View view;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        int max2 = Math.max(0, max - (15 - (min - max)));
        View view2 = null;
        int i2 = 0;
        int i3 = 0;
        while (max2 < min) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i3) {
                view = null;
            } else {
                itemViewType = i3;
                view = view2;
            }
            view2 = spinnerAdapter.getView(max2, view, this);
            if (view2.getLayoutParams() == null) {
                view2.setLayoutParams(new LayoutParams(-2, -2));
            }
            view2.measure(makeMeasureSpec, makeMeasureSpec2);
            i2 = Math.max(i2, view2.getMeasuredWidth());
            max2++;
            i3 = itemViewType;
        }
        if (drawable == null) {
            return i2;
        }
        drawable.getPadding(this.c);
        return this.c.left + this.c.right + i2;
    }
}
