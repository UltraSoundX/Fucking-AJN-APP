package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.LruCache;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: AppCompatDrawableManager */
public final class f {
    private static final Mode a = Mode.SRC_IN;
    private static f b;
    private static final c c = new c(6);
    private static final int[] d = {R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
    private static final int[] e = {R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
    private static final int[] f = {R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl_dark, R.drawable.abc_text_select_handle_middle_mtrl_dark, R.drawable.abc_text_select_handle_right_mtrl_dark, R.drawable.abc_text_select_handle_left_mtrl_light, R.drawable.abc_text_select_handle_middle_mtrl_light, R.drawable.abc_text_select_handle_right_mtrl_light};
    private static final int[] g = {R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
    private static final int[] h = {R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};
    private static final int[] i = {R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material};
    private WeakHashMap<Context, SparseArrayCompat<ColorStateList>> j;
    private ArrayMap<String, d> k;
    private SparseArrayCompat<String> l;
    private final WeakHashMap<Context, LongSparseArray<WeakReference<ConstantState>>> m = new WeakHashMap<>(0);
    private TypedValue n;
    private boolean o;

    /* compiled from: AppCompatDrawableManager */
    static class a implements d {
        a() {
        }

        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) {
            try {
                return android.support.v7.b.a.a.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }

    /* compiled from: AppCompatDrawableManager */
    private static class b implements d {
        b() {
        }

        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) {
            try {
                return AnimatedVectorDrawableCompat.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    /* compiled from: AppCompatDrawableManager */
    private static class c extends LruCache<Integer, PorterDuffColorFilter> {
        public c(int i) {
            super(i);
        }

        /* access modifiers changed from: 0000 */
        public PorterDuffColorFilter a(int i, Mode mode) {
            return (PorterDuffColorFilter) get(Integer.valueOf(b(i, mode)));
        }

        /* access modifiers changed from: 0000 */
        public PorterDuffColorFilter a(int i, Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) put(Integer.valueOf(b(i, mode)), porterDuffColorFilter);
        }

        private static int b(int i, Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }
    }

    /* compiled from: AppCompatDrawableManager */
    private interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme);
    }

    /* compiled from: AppCompatDrawableManager */
    private static class e implements d {
        e() {
        }

        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) {
            try {
                return VectorDrawableCompat.a(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (b == null) {
                b = new f();
                a(b);
            }
            fVar = b;
        }
        return fVar;
    }

    private static void a(f fVar) {
        if (VERSION.SDK_INT < 24) {
            fVar.a("vector", (d) new e());
            fVar.a("animated-vector", (d) new b());
            fVar.a("animated-selector", (d) new a());
        }
    }

    public synchronized Drawable a(Context context, int i2) {
        return a(context, i2, false);
    }

    /* access modifiers changed from: 0000 */
    public synchronized Drawable a(Context context, int i2, boolean z) {
        Drawable d2;
        f(context);
        d2 = d(context, i2);
        if (d2 == null) {
            d2 = c(context, i2);
        }
        if (d2 == null) {
            d2 = ContextCompat.getDrawable(context, i2);
        }
        if (d2 != null) {
            d2 = a(context, i2, z, d2);
        }
        if (d2 != null) {
            v.b(d2);
        }
        return d2;
    }

    public synchronized void a(Context context) {
        LongSparseArray longSparseArray = (LongSparseArray) this.m.get(context);
        if (longSparseArray != null) {
            longSparseArray.clear();
        }
    }

    private static long a(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    private Drawable c(Context context, int i2) {
        if (this.n == null) {
            this.n = new TypedValue();
        }
        TypedValue typedValue = this.n;
        context.getResources().getValue(i2, typedValue, true);
        long a2 = a(typedValue);
        Drawable a3 = a(context, a2);
        if (a3 == null) {
            if (i2 == R.drawable.abc_cab_background_top_material) {
                a3 = new LayerDrawable(new Drawable[]{a(context, R.drawable.abc_cab_background_internal_bg), a(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
            }
            if (a3 != null) {
                a3.setChangingConfigurations(typedValue.changingConfigurations);
                a(context, a2, a3);
            }
        }
        return a3;
    }

    private Drawable a(Context context, int i2, boolean z, Drawable drawable) {
        ColorStateList b2 = b(context, i2);
        if (b2 != null) {
            if (v.c(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable wrap = DrawableCompat.wrap(drawable);
            DrawableCompat.setTintList(wrap, b2);
            Mode a2 = a(i2);
            if (a2 == null) {
                return wrap;
            }
            DrawableCompat.setTintMode(wrap, a2);
            return wrap;
        } else if (i2 == R.drawable.abc_seekbar_track_material) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            a(layerDrawable.findDrawableByLayerId(16908288), ao.a(context, R.attr.colorControlNormal), a);
            a(layerDrawable.findDrawableByLayerId(16908303), ao.a(context, R.attr.colorControlNormal), a);
            a(layerDrawable.findDrawableByLayerId(16908301), ao.a(context, R.attr.colorControlActivated), a);
            return drawable;
        } else if (i2 == R.drawable.abc_ratingbar_material || i2 == R.drawable.abc_ratingbar_indicator_material || i2 == R.drawable.abc_ratingbar_small_material) {
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            a(layerDrawable2.findDrawableByLayerId(16908288), ao.c(context, R.attr.colorControlNormal), a);
            a(layerDrawable2.findDrawableByLayerId(16908303), ao.a(context, R.attr.colorControlActivated), a);
            a(layerDrawable2.findDrawableByLayerId(16908301), ao.a(context, R.attr.colorControlActivated), a);
            return drawable;
        } else if (a(context, i2, drawable) || !z) {
            return drawable;
        } else {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable d(android.content.Context r10, int r11) {
        /*
            r9 = this;
            r1 = 0
            r8 = 2
            r7 = 1
            android.support.v4.util.ArrayMap<java.lang.String, android.support.v7.widget.f$d> r0 = r9.k
            if (r0 == 0) goto L_0x00bf
            android.support.v4.util.ArrayMap<java.lang.String, android.support.v7.widget.f$d> r0 = r9.k
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00bf
            android.support.v4.util.SparseArrayCompat<java.lang.String> r0 = r9.l
            if (r0 == 0) goto L_0x002f
            android.support.v4.util.SparseArrayCompat<java.lang.String> r0 = r9.l
            java.lang.Object r0 = r0.get(r11)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r2 = "appcompat_skip_skip"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x002d
            if (r0 == 0) goto L_0x0036
            android.support.v4.util.ArrayMap<java.lang.String, android.support.v7.widget.f$d> r2 = r9.k
            java.lang.Object r0 = r2.get(r0)
            if (r0 != 0) goto L_0x0036
        L_0x002d:
            r0 = r1
        L_0x002e:
            return r0
        L_0x002f:
            android.support.v4.util.SparseArrayCompat r0 = new android.support.v4.util.SparseArrayCompat
            r0.<init>()
            r9.l = r0
        L_0x0036:
            android.util.TypedValue r0 = r9.n
            if (r0 != 0) goto L_0x0041
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            r9.n = r0
        L_0x0041:
            android.util.TypedValue r2 = r9.n
            android.content.res.Resources r0 = r10.getResources()
            r0.getValue(r11, r2, r7)
            long r4 = a(r2)
            android.graphics.drawable.Drawable r1 = r9.a(r10, r4)
            if (r1 == 0) goto L_0x0056
            r0 = r1
            goto L_0x002e
        L_0x0056:
            java.lang.CharSequence r3 = r2.string
            if (r3 == 0) goto L_0x008a
            java.lang.CharSequence r3 = r2.string
            java.lang.String r3 = r3.toString()
            java.lang.String r6 = ".xml"
            boolean r3 = r3.endsWith(r6)
            if (r3 == 0) goto L_0x008a
            android.content.res.XmlResourceParser r3 = r0.getXml(r11)     // Catch:{ Exception -> 0x0082 }
            android.util.AttributeSet r6 = android.util.Xml.asAttributeSet(r3)     // Catch:{ Exception -> 0x0082 }
        L_0x0070:
            int r0 = r3.next()     // Catch:{ Exception -> 0x0082 }
            if (r0 == r8) goto L_0x0078
            if (r0 != r7) goto L_0x0070
        L_0x0078:
            if (r0 == r8) goto L_0x0095
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ Exception -> 0x0082 }
            java.lang.String r2 = "No start tag found"
            r0.<init>(r2)     // Catch:{ Exception -> 0x0082 }
            throw r0     // Catch:{ Exception -> 0x0082 }
        L_0x0082:
            r0 = move-exception
            java.lang.String r2 = "AppCompatDrawableManag"
            java.lang.String r3 = "Exception while inflating drawable"
            android.util.Log.e(r2, r3, r0)
        L_0x008a:
            r0 = r1
        L_0x008b:
            if (r0 != 0) goto L_0x002e
            android.support.v4.util.SparseArrayCompat<java.lang.String> r1 = r9.l
            java.lang.String r2 = "appcompat_skip_skip"
            r1.append(r11, r2)
            goto L_0x002e
        L_0x0095:
            java.lang.String r0 = r3.getName()     // Catch:{ Exception -> 0x0082 }
            android.support.v4.util.SparseArrayCompat<java.lang.String> r7 = r9.l     // Catch:{ Exception -> 0x0082 }
            r7.append(r11, r0)     // Catch:{ Exception -> 0x0082 }
            android.support.v4.util.ArrayMap<java.lang.String, android.support.v7.widget.f$d> r7 = r9.k     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Exception -> 0x0082 }
            android.support.v7.widget.f$d r0 = (android.support.v7.widget.f.d) r0     // Catch:{ Exception -> 0x0082 }
            if (r0 == 0) goto L_0x00b0
            android.content.res.Resources$Theme r7 = r10.getTheme()     // Catch:{ Exception -> 0x0082 }
            android.graphics.drawable.Drawable r1 = r0.a(r10, r3, r6, r7)     // Catch:{ Exception -> 0x0082 }
        L_0x00b0:
            if (r1 == 0) goto L_0x00bd
            int r0 = r2.changingConfigurations     // Catch:{ Exception -> 0x0082 }
            r1.setChangingConfigurations(r0)     // Catch:{ Exception -> 0x0082 }
            boolean r0 = r9.a(r10, r4, r1)     // Catch:{ Exception -> 0x0082 }
            if (r0 == 0) goto L_0x00bd
        L_0x00bd:
            r0 = r1
            goto L_0x008b
        L_0x00bf:
            r0 = r1
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.f.d(android.content.Context, int):android.graphics.drawable.Drawable");
    }

    private synchronized Drawable a(Context context, long j2) {
        Drawable drawable;
        LongSparseArray longSparseArray = (LongSparseArray) this.m.get(context);
        if (longSparseArray == null) {
            drawable = null;
        } else {
            WeakReference weakReference = (WeakReference) longSparseArray.get(j2);
            if (weakReference != null) {
                ConstantState constantState = (ConstantState) weakReference.get();
                if (constantState != null) {
                    drawable = constantState.newDrawable(context.getResources());
                } else {
                    longSparseArray.delete(j2);
                }
            }
            drawable = null;
        }
        return drawable;
    }

    private synchronized boolean a(Context context, long j2, Drawable drawable) {
        boolean z;
        ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            LongSparseArray longSparseArray = (LongSparseArray) this.m.get(context);
            if (longSparseArray == null) {
                longSparseArray = new LongSparseArray();
                this.m.put(context, longSparseArray);
            }
            longSparseArray.put(j2, new WeakReference(constantState));
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public synchronized Drawable a(Context context, ay ayVar, int i2) {
        Drawable drawable;
        Drawable d2 = d(context, i2);
        if (d2 == null) {
            d2 = ayVar.a(i2);
        }
        if (d2 != null) {
            drawable = a(context, i2, false, d2);
        } else {
            drawable = null;
        }
        return drawable;
    }

    static boolean a(Context context, int i2, Drawable drawable) {
        int i3;
        int i4;
        Mode mode;
        boolean z;
        Mode mode2 = a;
        if (a(d, i2)) {
            i4 = R.attr.colorControlNormal;
            mode = mode2;
            z = true;
            i3 = -1;
        } else if (a(f, i2)) {
            i4 = R.attr.colorControlActivated;
            mode = mode2;
            z = true;
            i3 = -1;
        } else if (a(g, i2)) {
            z = true;
            mode = Mode.MULTIPLY;
            i4 = 16842801;
            i3 = -1;
        } else if (i2 == R.drawable.abc_list_divider_mtrl_alpha) {
            i4 = 16842800;
            i3 = Math.round(40.8f);
            mode = mode2;
            z = true;
        } else if (i2 == R.drawable.abc_dialog_material_background) {
            i4 = 16842801;
            mode = mode2;
            z = true;
            i3 = -1;
        } else {
            i3 = -1;
            i4 = 0;
            mode = mode2;
            z = false;
        }
        if (!z) {
            return false;
        }
        if (v.c(drawable)) {
            drawable = drawable.mutate();
        }
        drawable.setColorFilter(a(ao.a(context, i4), mode));
        if (i3 == -1) {
            return true;
        }
        drawable.setAlpha(i3);
        return true;
    }

    private void a(String str, d dVar) {
        if (this.k == null) {
            this.k = new ArrayMap<>();
        }
        this.k.put(str, dVar);
    }

    private static boolean a(int[] iArr, int i2) {
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    static Mode a(int i2) {
        if (i2 == R.drawable.abc_switch_thumb_material) {
            return Mode.MULTIPLY;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public synchronized ColorStateList b(Context context, int i2) {
        ColorStateList e2;
        e2 = e(context, i2);
        if (e2 == null) {
            if (i2 == R.drawable.abc_edit_text_material) {
                e2 = android.support.v7.a.a.a.a(context, R.color.abc_tint_edittext);
            } else if (i2 == R.drawable.abc_switch_track_mtrl_alpha) {
                e2 = android.support.v7.a.a.a.a(context, R.color.abc_tint_switch_track);
            } else if (i2 == R.drawable.abc_switch_thumb_material) {
                e2 = e(context);
            } else if (i2 == R.drawable.abc_btn_default_mtrl_shape) {
                e2 = b(context);
            } else if (i2 == R.drawable.abc_btn_borderless_material) {
                e2 = c(context);
            } else if (i2 == R.drawable.abc_btn_colored_material) {
                e2 = d(context);
            } else if (i2 == R.drawable.abc_spinner_mtrl_am_alpha || i2 == R.drawable.abc_spinner_textfield_background_material) {
                e2 = android.support.v7.a.a.a.a(context, R.color.abc_tint_spinner);
            } else if (a(e, i2)) {
                e2 = ao.b(context, R.attr.colorControlNormal);
            } else if (a(h, i2)) {
                e2 = android.support.v7.a.a.a.a(context, R.color.abc_tint_default);
            } else if (a(i, i2)) {
                e2 = android.support.v7.a.a.a.a(context, R.color.abc_tint_btn_checkable);
            } else if (i2 == R.drawable.abc_seekbar_thumb_material) {
                e2 = android.support.v7.a.a.a.a(context, R.color.abc_tint_seek_thumb);
            }
            if (e2 != null) {
                a(context, i2, e2);
            }
        }
        return e2;
    }

    private ColorStateList e(Context context, int i2) {
        if (this.j == null) {
            return null;
        }
        SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) this.j.get(context);
        if (sparseArrayCompat != null) {
            return (ColorStateList) sparseArrayCompat.get(i2);
        }
        return null;
    }

    private void a(Context context, int i2, ColorStateList colorStateList) {
        if (this.j == null) {
            this.j = new WeakHashMap<>();
        }
        SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) this.j.get(context);
        if (sparseArrayCompat == null) {
            sparseArrayCompat = new SparseArrayCompat();
            this.j.put(context, sparseArrayCompat);
        }
        sparseArrayCompat.append(i2, colorStateList);
    }

    private ColorStateList b(Context context) {
        return f(context, ao.a(context, R.attr.colorButtonNormal));
    }

    private ColorStateList c(Context context) {
        return f(context, 0);
    }

    private ColorStateList d(Context context) {
        return f(context, ao.a(context, R.attr.colorAccent));
    }

    private ColorStateList f(Context context, int i2) {
        int a2 = ao.a(context, R.attr.colorControlHighlight);
        return new ColorStateList(new int[][]{ao.a, ao.d, ao.b, ao.h}, new int[]{ao.c(context, R.attr.colorButtonNormal), ColorUtils.compositeColors(a2, i2), ColorUtils.compositeColors(a2, i2), i2});
    }

    private ColorStateList e(Context context) {
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        ColorStateList b2 = ao.b(context, R.attr.colorSwitchThumbNormal);
        if (b2 == null || !b2.isStateful()) {
            iArr[0] = ao.a;
            iArr2[0] = ao.c(context, R.attr.colorSwitchThumbNormal);
            iArr[1] = ao.e;
            iArr2[1] = ao.a(context, R.attr.colorControlActivated);
            iArr[2] = ao.h;
            iArr2[2] = ao.a(context, R.attr.colorSwitchThumbNormal);
        } else {
            iArr[0] = ao.a;
            iArr2[0] = b2.getColorForState(iArr[0], 0);
            iArr[1] = ao.e;
            iArr2[1] = ao.a(context, R.attr.colorControlActivated);
            iArr[2] = ao.h;
            iArr2[2] = b2.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    static void a(Drawable drawable, ar arVar, int[] iArr) {
        if (!v.c(drawable) || drawable.mutate() == drawable) {
            if (arVar.d || arVar.c) {
                drawable.setColorFilter(a(arVar.d ? arVar.a : null, arVar.c ? arVar.b : a, iArr));
            } else {
                drawable.clearColorFilter();
            }
            if (VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
                return;
            }
            return;
        }
        Log.d("AppCompatDrawableManag", "Mutated drawable is not the same instance as the input.");
    }

    private static PorterDuffColorFilter a(ColorStateList colorStateList, Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return a(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized PorterDuffColorFilter a(int i2, Mode mode) {
        PorterDuffColorFilter a2;
        synchronized (f.class) {
            a2 = c.a(i2, mode);
            if (a2 == null) {
                a2 = new PorterDuffColorFilter(i2, mode);
                c.a(i2, mode, a2);
            }
        }
        return a2;
    }

    private static void a(Drawable drawable, int i2, Mode mode) {
        if (v.c(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = a;
        }
        drawable.setColorFilter(a(i2, mode));
    }

    private void f(Context context) {
        if (!this.o) {
            this.o = true;
            Drawable a2 = a(context, R.drawable.abc_vector_test);
            if (a2 == null || !a(a2)) {
                this.o = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
        }
    }

    private static boolean a(Drawable drawable) {
        return (drawable instanceof VectorDrawableCompat) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }
}
