package android.support.v7.b.a;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.util.StateSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: AnimatedStateListDrawableCompat */
public class a extends e {
    private static final String a = a.class.getSimpleName();
    private b b;
    private f c;
    private int d;
    private int e;
    private boolean f;

    /* renamed from: android.support.v7.b.a.a$a reason: collision with other inner class name */
    /* compiled from: AnimatedStateListDrawableCompat */
    private static class C0013a extends f {
        private final Animatable a;

        C0013a(Animatable animatable) {
            super();
            this.a = animatable;
        }

        public void a() {
            this.a.start();
        }

        public void b() {
            this.a.stop();
        }
    }

    /* compiled from: AnimatedStateListDrawableCompat */
    static class b extends a {
        LongSparseArray<Long> a;
        SparseArrayCompat<Integer> b;

        b(b bVar, a aVar, Resources resources) {
            super(bVar, aVar, resources);
            if (bVar != null) {
                this.a = bVar.a;
                this.b = bVar.b;
                return;
            }
            this.a = new LongSparseArray<>();
            this.b = new SparseArrayCompat<>();
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.a = this.a.clone();
            this.b = this.b.clone();
        }

        /* access modifiers changed from: 0000 */
        public int a(int i, int i2, Drawable drawable, boolean z) {
            int a2 = super.a(drawable);
            long f = f(i, i2);
            long j = 0;
            if (z) {
                j = 8589934592L;
            }
            this.a.append(f, Long.valueOf(((long) a2) | j));
            if (z) {
                this.a.append(f(i2, i), Long.valueOf(j | ((long) a2) | 4294967296L));
            }
            return a2;
        }

        /* access modifiers changed from: 0000 */
        public int a(int[] iArr, Drawable drawable, int i) {
            int a2 = super.a(iArr, drawable);
            this.b.put(a2, Integer.valueOf(i));
            return a2;
        }

        /* access modifiers changed from: 0000 */
        public int a(int[] iArr) {
            int b2 = super.b(iArr);
            return b2 >= 0 ? b2 : super.b(StateSet.WILD_CARD);
        }

        /* access modifiers changed from: 0000 */
        public int a(int i) {
            if (i < 0) {
                return 0;
            }
            return ((Integer) this.b.get(i, Integer.valueOf(0))).intValue();
        }

        /* access modifiers changed from: 0000 */
        public int a(int i, int i2) {
            return (int) ((Long) this.a.get(f(i, i2), Long.valueOf(-1))).longValue();
        }

        /* access modifiers changed from: 0000 */
        public boolean b(int i, int i2) {
            return (((Long) this.a.get(f(i, i2), Long.valueOf(-1))).longValue() & 4294967296L) != 0;
        }

        /* access modifiers changed from: 0000 */
        public boolean c(int i, int i2) {
            return (((Long) this.a.get(f(i, i2), Long.valueOf(-1))).longValue() & 8589934592L) != 0;
        }

        public Drawable newDrawable() {
            return new a(this, null);
        }

        public Drawable newDrawable(Resources resources) {
            return new a(this, resources);
        }

        private static long f(int i, int i2) {
            return (((long) i) << 32) | ((long) i2);
        }
    }

    /* compiled from: AnimatedStateListDrawableCompat */
    private static class c extends f {
        private final AnimatedVectorDrawableCompat a;

        c(AnimatedVectorDrawableCompat animatedVectorDrawableCompat) {
            super();
            this.a = animatedVectorDrawableCompat;
        }

        public void a() {
            this.a.start();
        }

        public void b() {
            this.a.stop();
        }
    }

    /* compiled from: AnimatedStateListDrawableCompat */
    private static class d extends f {
        private final ObjectAnimator a;
        private final boolean b;

        d(AnimationDrawable animationDrawable, boolean z, boolean z2) {
            super();
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i = z ? numberOfFrames - 1 : 0;
            int i2 = z ? 0 : numberOfFrames - 1;
            e eVar = new e(animationDrawable, z);
            ObjectAnimator ofInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", new int[]{i, i2});
            if (VERSION.SDK_INT >= 18) {
                ofInt.setAutoCancel(true);
            }
            ofInt.setDuration((long) eVar.a());
            ofInt.setInterpolator(eVar);
            this.b = z2;
            this.a = ofInt;
        }

        public boolean c() {
            return this.b;
        }

        public void a() {
            this.a.start();
        }

        public void d() {
            this.a.reverse();
        }

        public void b() {
            this.a.cancel();
        }
    }

    /* compiled from: AnimatedStateListDrawableCompat */
    private static class e implements TimeInterpolator {
        private int[] a;
        private int b;
        private int c;

        e(AnimationDrawable animationDrawable, boolean z) {
            a(animationDrawable, z);
        }

        /* access modifiers changed from: 0000 */
        public int a(AnimationDrawable animationDrawable, boolean z) {
            int i;
            int i2 = 0;
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.b = numberOfFrames;
            if (this.a == null || this.a.length < numberOfFrames) {
                this.a = new int[numberOfFrames];
            }
            int[] iArr = this.a;
            int i3 = 0;
            while (i2 < numberOfFrames) {
                if (z) {
                    i = (numberOfFrames - i2) - 1;
                } else {
                    i = i2;
                }
                int duration = animationDrawable.getDuration(i);
                iArr[i2] = duration;
                i2++;
                i3 = duration + i3;
            }
            this.c = i3;
            return i3;
        }

        /* access modifiers changed from: 0000 */
        public int a() {
            return this.c;
        }

        public float getInterpolation(float f) {
            float f2;
            int i = (int) ((((float) this.c) * f) + 0.5f);
            int i2 = this.b;
            int[] iArr = this.a;
            int i3 = i;
            int i4 = 0;
            while (i4 < i2 && i3 >= iArr[i4]) {
                int i5 = i3 - iArr[i4];
                i4++;
                i3 = i5;
            }
            if (i4 < i2) {
                f2 = ((float) i3) / ((float) this.c);
            } else {
                f2 = 0.0f;
            }
            return f2 + (((float) i4) / ((float) i2));
        }
    }

    /* compiled from: AnimatedStateListDrawableCompat */
    private static abstract class f {
        public abstract void a();

        public abstract void b();

        private f() {
        }

        public void d() {
        }

        public boolean c() {
            return false;
        }
    }

    public /* bridge */ /* synthetic */ void applyTheme(Theme theme) {
        super.applyTheme(theme);
    }

    public /* bridge */ /* synthetic */ boolean canApplyTheme() {
        return super.canApplyTheme();
    }

    public /* bridge */ /* synthetic */ void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    public /* bridge */ /* synthetic */ int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public /* bridge */ /* synthetic */ void getHotspotBounds(Rect rect) {
        super.getHotspotBounds(rect);
    }

    public /* bridge */ /* synthetic */ int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    public /* bridge */ /* synthetic */ int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    public /* bridge */ /* synthetic */ void getOutline(Outline outline) {
        super.getOutline(outline);
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public /* bridge */ /* synthetic */ void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
    }

    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    public /* bridge */ /* synthetic */ boolean onLayoutDirectionChanged(int i) {
        return super.onLayoutDirectionChanged(i);
    }

    public /* bridge */ /* synthetic */ void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        super.scheduleDrawable(drawable, runnable, j);
    }

    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z) {
        super.setAutoMirrored(z);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    public /* bridge */ /* synthetic */ void setDither(boolean z) {
        super.setDither(z);
    }

    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    public /* bridge */ /* synthetic */ void setTintMode(Mode mode) {
        super.setTintMode(mode);
    }

    public /* bridge */ /* synthetic */ void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        super.unscheduleDrawable(drawable, runnable);
    }

    public a() {
        this(null, null);
    }

    a(b bVar, Resources resources) {
        super(null);
        this.d = -1;
        this.e = -1;
        a((C0014b) new b(bVar, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }

    public static a a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws IOException, XmlPullParserException {
        String name = xmlPullParser.getName();
        if (!name.equals("animated-selector")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid animated-selector tag " + name);
        }
        a aVar = new a();
        aVar.b(context, resources, xmlPullParser, attributeSet, theme);
        return aVar;
    }

    public void b(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, R.styleable.AnimatedStateListDrawableCompat);
        setVisible(obtainAttributes.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
        a(obtainAttributes);
        a(resources);
        obtainAttributes.recycle();
        c(context, resources, xmlPullParser, attributeSet, theme);
        e();
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (this.c != null && (visible || z2)) {
            if (z) {
                this.c.a();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    public boolean isStateful() {
        return true;
    }

    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        if (this.c != null) {
            this.c.b();
            this.c = null;
            a(this.d);
            this.d = -1;
            this.e = -1;
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int a2 = this.b.a(iArr);
        boolean z = a2 != d() && (b(a2) || a(a2));
        Drawable current = getCurrent();
        if (current != null) {
            return z | current.setState(iArr);
        }
        return z;
    }

    private boolean b(int i) {
        int d2;
        f aVar;
        f fVar = this.c;
        if (fVar == null) {
            d2 = d();
        } else if (i == this.d) {
            return true;
        } else {
            if (i != this.e || !fVar.c()) {
                int i2 = this.d;
                fVar.b();
                d2 = i2;
            } else {
                fVar.d();
                this.d = this.e;
                this.e = i;
                return true;
            }
        }
        this.c = null;
        this.e = -1;
        this.d = -1;
        b bVar = this.b;
        int a2 = bVar.a(d2);
        int a3 = bVar.a(i);
        if (a3 == 0 || a2 == 0) {
            return false;
        }
        int a4 = bVar.a(a2, a3);
        if (a4 < 0) {
            return false;
        }
        boolean c2 = bVar.c(a2, a3);
        a(a4);
        Drawable current = getCurrent();
        if (current instanceof AnimationDrawable) {
            aVar = new d((AnimationDrawable) current, bVar.b(a2, a3), c2);
        } else if (current instanceof AnimatedVectorDrawableCompat) {
            aVar = new c((AnimatedVectorDrawableCompat) current);
        } else if (!(current instanceof Animatable)) {
            return false;
        } else {
            aVar = new C0013a((Animatable) current);
        }
        aVar.a();
        this.c = aVar;
        this.e = d2;
        this.d = i;
        return true;
    }

    private void a(TypedArray typedArray) {
        b bVar = this.b;
        if (VERSION.SDK_INT >= 21) {
            bVar.f |= typedArray.getChangingConfigurations();
        }
        bVar.a(typedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_variablePadding, bVar.k));
        bVar.b(typedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_constantSize, bVar.n));
        bVar.c(typedArray.getInt(R.styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, bVar.C));
        bVar.d(typedArray.getInt(R.styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, bVar.D));
        setDither(typedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_dither, bVar.z));
    }

    private void e() {
        onStateChange(getState());
    }

    private void c(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next != 1) {
                int depth2 = xmlPullParser.getDepth();
                if (depth2 < depth && next == 3) {
                    return;
                }
                if (next == 2 && depth2 <= depth) {
                    if (xmlPullParser.getName().equals("item")) {
                        e(context, resources, xmlPullParser, attributeSet, theme);
                    } else if (xmlPullParser.getName().equals("transition")) {
                        d(context, resources, xmlPullParser, attributeSet, theme);
                    }
                }
            } else {
                return;
            }
        }
    }

    private int d(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, R.styleable.AnimatedStateListDrawableTransition);
        int resourceId = obtainAttributes.getResourceId(R.styleable.AnimatedStateListDrawableTransition_android_fromId, -1);
        int resourceId2 = obtainAttributes.getResourceId(R.styleable.AnimatedStateListDrawableTransition_android_toId, -1);
        Drawable drawable = null;
        int resourceId3 = obtainAttributes.getResourceId(R.styleable.AnimatedStateListDrawableTransition_android_drawable, -1);
        if (resourceId3 > 0) {
            drawable = android.support.v7.a.a.a.b(context, resourceId3);
        }
        boolean z = obtainAttributes.getBoolean(R.styleable.AnimatedStateListDrawableTransition_android_reversible, false);
        obtainAttributes.recycle();
        if (drawable == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
            } else if (xmlPullParser.getName().equals("animated-vector")) {
                drawable = AnimatedVectorDrawableCompat.a(context, resources, xmlPullParser, attributeSet, theme);
            } else if (VERSION.SDK_INT >= 21) {
                drawable = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
            } else {
                drawable = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
            }
        }
        if (drawable == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        } else if (resourceId != -1 && resourceId2 != -1) {
            return this.b.a(resourceId, resourceId2, drawable, z);
        } else {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires 'fromId' & 'toId' attributes");
        }
    }

    private int e(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, R.styleable.AnimatedStateListDrawableItem);
        int resourceId = obtainAttributes.getResourceId(R.styleable.AnimatedStateListDrawableItem_android_id, 0);
        Drawable drawable = null;
        int resourceId2 = obtainAttributes.getResourceId(R.styleable.AnimatedStateListDrawableItem_android_drawable, -1);
        if (resourceId2 > 0) {
            drawable = android.support.v7.a.a.a.b(context, resourceId2);
        }
        obtainAttributes.recycle();
        int[] a2 = a(attributeSet);
        if (drawable == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
            } else if (xmlPullParser.getName().equals("vector")) {
                drawable = VectorDrawableCompat.a(resources, xmlPullParser, attributeSet, theme);
            } else if (VERSION.SDK_INT >= 21) {
                drawable = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
            } else {
                drawable = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
            }
        }
        if (drawable != null) {
            return this.b.a(a2, drawable, resourceId);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
    }

    public Drawable mutate() {
        if (!this.f && super.mutate() == this) {
            this.b.a();
            this.f = true;
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public b c() {
        return new b(this.b, this, null);
    }

    /* access modifiers changed from: protected */
    public void a(C0014b bVar) {
        super.a(bVar);
        if (bVar instanceof b) {
            this.b = (b) bVar;
        }
    }
}
