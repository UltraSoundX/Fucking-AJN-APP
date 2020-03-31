package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import org.xmlpull.v1.XmlPullParser;

public class Slide extends Visibility {
    private static final TimeInterpolator a = new DecelerateInterpolator();
    private static final TimeInterpolator i = new AccelerateInterpolator();
    private static final a l = new b() {
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationX() - ((float) viewGroup.getWidth());
        }
    };
    private static final a m = new b() {
        public float a(ViewGroup viewGroup, View view) {
            boolean z = true;
            if (ViewCompat.getLayoutDirection(viewGroup) != 1) {
                z = false;
            }
            if (z) {
                return view.getTranslationX() + ((float) viewGroup.getWidth());
            }
            return view.getTranslationX() - ((float) viewGroup.getWidth());
        }
    };
    private static final a n = new c() {
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationY() - ((float) viewGroup.getHeight());
        }
    };
    private static final a o = new b() {
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationX() + ((float) viewGroup.getWidth());
        }
    };
    private static final a p = new b() {
        public float a(ViewGroup viewGroup, View view) {
            boolean z = true;
            if (ViewCompat.getLayoutDirection(viewGroup) != 1) {
                z = false;
            }
            if (z) {
                return view.getTranslationX() - ((float) viewGroup.getWidth());
            }
            return view.getTranslationX() + ((float) viewGroup.getWidth());
        }
    };

    /* renamed from: q reason: collision with root package name */
    private static final a f333q = new c() {
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationY() + ((float) viewGroup.getHeight());
        }
    };
    private a j = f333q;
    private int k = 80;

    private interface a {
        float a(ViewGroup viewGroup, View view);

        float b(ViewGroup viewGroup, View view);
    }

    private static abstract class b implements a {
        private b() {
        }

        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationY();
        }
    }

    private static abstract class c implements a {
        private c() {
        }

        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationX();
        }
    }

    public Slide() {
        a(80);
    }

    public Slide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.h);
        int namedInt = TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlPullParser) attributeSet, "slideEdge", 0, 80);
        obtainStyledAttributes.recycle();
        a(namedInt);
    }

    private void d(v vVar) {
        int[] iArr = new int[2];
        vVar.b.getLocationOnScreen(iArr);
        vVar.a.put("android:slide:screenPosition", iArr);
    }

    public void a(v vVar) {
        super.a(vVar);
        d(vVar);
    }

    public void b(v vVar) {
        super.b(vVar);
        d(vVar);
    }

    public void a(int i2) {
        switch (i2) {
            case 3:
                this.j = l;
                break;
            case 5:
                this.j = o;
                break;
            case 48:
                this.j = n;
                break;
            case 80:
                this.j = f333q;
                break;
            case GravityCompat.START /*8388611*/:
                this.j = m;
                break;
            case GravityCompat.END /*8388613*/:
                this.j = p;
                break;
            default:
                throw new IllegalArgumentException("Invalid slide direction");
        }
        this.k = i2;
        o oVar = new o();
        oVar.a(i2);
        a((t) oVar);
    }

    public Animator a(ViewGroup viewGroup, View view, v vVar, v vVar2) {
        if (vVar2 == null) {
            return null;
        }
        int[] iArr = (int[]) vVar2.a.get("android:slide:screenPosition");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        return x.a(view, vVar2, iArr[0], iArr[1], this.j.a(viewGroup, view), this.j.b(viewGroup, view), translationX, translationY, a);
    }

    public Animator b(ViewGroup viewGroup, View view, v vVar, v vVar2) {
        if (vVar == null) {
            return null;
        }
        int[] iArr = (int[]) vVar.a.get("android:slide:screenPosition");
        return x.a(view, vVar, iArr[0], iArr[1], view.getTranslationX(), view.getTranslationY(), this.j.a(viewGroup, view), this.j.b(viewGroup, view), i);
    }
}
