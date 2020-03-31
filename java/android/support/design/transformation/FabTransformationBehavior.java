package android.support.design.transformation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.design.R;
import android.support.design.a.b;
import android.support.design.a.e;
import android.support.design.a.h;
import android.support.design.a.i;
import android.support.design.a.j;
import android.support.design.circularreveal.c;
import android.support.design.circularreveal.c.C0005c;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.k;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    private final Rect a = new Rect();
    private final RectF b = new RectF();
    private final RectF c = new RectF();
    private final int[] d = new int[2];

    protected static class a {
        public h a;
        public j b;

        protected a() {
        }
    }

    /* access modifiers changed from: protected */
    public abstract a a(Context context, boolean z);

    public FabTransformationBehavior() {
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean a(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        } else if (!(view2 instanceof FloatingActionButton)) {
            return false;
        } else {
            int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
            if (expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId()) {
                return true;
            }
            return false;
        }
    }

    public void a(d dVar) {
        if (dVar.h == 0) {
            dVar.h = 80;
        }
    }

    /* access modifiers changed from: protected */
    public AnimatorSet b(View view, View view2, boolean z, boolean z2) {
        a a2 = a(view2.getContext(), z);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (VERSION.SDK_INT >= 21) {
            a(view, view2, z, z2, a2, arrayList, arrayList2);
        }
        RectF rectF = this.b;
        a(view, view2, z, z2, a2, (List<Animator>) arrayList, (List<AnimatorListener>) arrayList2, rectF);
        float width = rectF.width();
        float height = rectF.height();
        b(view, view2, z, z2, a2, arrayList, arrayList2);
        a(view, view2, z, z2, a2, width, height, (List<Animator>) arrayList, (List<AnimatorListener>) arrayList2);
        c(view, view2, z, z2, a2, arrayList, arrayList2);
        d(view, view2, z, z2, a2, arrayList, arrayList2);
        AnimatorSet animatorSet = new AnimatorSet();
        b.a(animatorSet, arrayList);
        final boolean z3 = z;
        final View view3 = view2;
        final View view4 = view;
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                if (z3) {
                    view3.setVisibility(0);
                    view4.setAlpha(0.0f);
                    view4.setVisibility(4);
                }
            }

            public void onAnimationEnd(Animator animator) {
                if (!z3) {
                    view3.setVisibility(4);
                    view4.setAlpha(1.0f);
                    view4.setVisibility(0);
                }
            }
        });
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            animatorSet.addListener((AnimatorListener) arrayList2.get(i));
        }
        return animatorSet;
    }

    @TargetApi(21)
    private void a(View view, View view2, boolean z, boolean z2, a aVar, List<Animator> list, List<AnimatorListener> list2) {
        ObjectAnimator ofFloat;
        float elevation = ViewCompat.getElevation(view2) - ViewCompat.getElevation(view);
        if (z) {
            if (!z2) {
                view2.setTranslationZ(-elevation);
            }
            ofFloat = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Z, new float[]{0.0f});
        } else {
            ofFloat = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Z, new float[]{-elevation});
        }
        aVar.a.b("elevation").a((Animator) ofFloat);
        list.add(ofFloat);
    }

    private void a(View view, View view2, boolean z, boolean z2, a aVar, List<Animator> list, List<AnimatorListener> list2, RectF rectF) {
        i iVar;
        i iVar2;
        ObjectAnimator ofFloat;
        ObjectAnimator ofFloat2;
        float a2 = a(view, view2, aVar.b);
        float b2 = b(view, view2, aVar.b);
        if (a2 == 0.0f || b2 == 0.0f) {
            iVar2 = aVar.a.b("translationXLinear");
            iVar = aVar.a.b("translationYLinear");
        } else if ((!z || b2 >= 0.0f) && (z || b2 <= 0.0f)) {
            iVar2 = aVar.a.b("translationXCurveDownwards");
            iVar = aVar.a.b("translationYCurveDownwards");
        } else {
            iVar2 = aVar.a.b("translationXCurveUpwards");
            iVar = aVar.a.b("translationYCurveUpwards");
        }
        if (z) {
            if (!z2) {
                view2.setTranslationX(-a2);
                view2.setTranslationY(-b2);
            }
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view2, View.TRANSLATION_X, new float[]{0.0f});
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Y, new float[]{0.0f});
            a(view2, aVar, iVar2, iVar, -a2, -b2, 0.0f, 0.0f, rectF);
            ofFloat2 = ofFloat4;
            ofFloat = ofFloat3;
        } else {
            ofFloat = ObjectAnimator.ofFloat(view2, View.TRANSLATION_X, new float[]{-a2});
            ofFloat2 = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Y, new float[]{-b2});
        }
        iVar2.a((Animator) ofFloat);
        iVar.a((Animator) ofFloat2);
        list.add(ofFloat);
        list.add(ofFloat2);
    }

    private void b(View view, final View view2, boolean z, boolean z2, a aVar, List<Animator> list, List<AnimatorListener> list2) {
        ObjectAnimator ofInt;
        if ((view2 instanceof c) && (view instanceof ImageView)) {
            final c cVar = (c) view2;
            final Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable != null) {
                drawable.mutate();
                if (z) {
                    if (!z2) {
                        drawable.setAlpha(255);
                    }
                    ofInt = ObjectAnimator.ofInt(drawable, e.a, new int[]{0});
                } else {
                    ofInt = ObjectAnimator.ofInt(drawable, e.a, new int[]{255});
                }
                ofInt.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        view2.invalidate();
                    }
                });
                aVar.a.b("iconFade").a((Animator) ofInt);
                list.add(ofInt);
                list2.add(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        cVar.setCircularRevealOverlayDrawable(drawable);
                    }

                    public void onAnimationEnd(Animator animator) {
                        cVar.setCircularRevealOverlayDrawable(null);
                    }
                });
            }
        }
    }

    private void a(View view, View view2, boolean z, boolean z2, a aVar, float f, float f2, List<Animator> list, List<AnimatorListener> list2) {
        Animator animator;
        if (view2 instanceof c) {
            c cVar = (c) view2;
            float c2 = c(view, view2, aVar.b);
            float d2 = d(view, view2, aVar.b);
            ((FloatingActionButton) view).a(this.a);
            float width = ((float) this.a.width()) / 2.0f;
            i b2 = aVar.a.b("expansion");
            if (z) {
                if (!z2) {
                    cVar.setRevealInfo(new c.d(c2, d2, width));
                }
                float f3 = z2 ? cVar.getRevealInfo().c : width;
                Animator a2 = android.support.design.circularreveal.a.a(cVar, c2, d2, k.a(c2, d2, 0.0f, 0.0f, f, f2));
                final c cVar2 = cVar;
                a2.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        c.d revealInfo = cVar2.getRevealInfo();
                        revealInfo.c = Float.MAX_VALUE;
                        cVar2.setRevealInfo(revealInfo);
                    }
                });
                a(view2, b2.a(), (int) c2, (int) d2, f3, list);
                animator = a2;
            } else {
                float f4 = cVar.getRevealInfo().c;
                Animator a3 = android.support.design.circularreveal.a.a(cVar, c2, d2, width);
                a(view2, b2.a(), (int) c2, (int) d2, f4, list);
                a(view2, b2.a(), b2.b(), aVar.a.a(), (int) c2, (int) d2, width, list);
                animator = a3;
            }
            b2.a(animator);
            list.add(animator);
            list2.add(android.support.design.circularreveal.a.a(cVar));
        }
    }

    private void c(View view, View view2, boolean z, boolean z2, a aVar, List<Animator> list, List<AnimatorListener> list2) {
        ObjectAnimator ofInt;
        if (view2 instanceof c) {
            c cVar = (c) view2;
            int c2 = c(view);
            int i = 16777215 & c2;
            if (z) {
                if (!z2) {
                    cVar.setCircularRevealScrimColor(c2);
                }
                ofInt = ObjectAnimator.ofInt(cVar, C0005c.a, new int[]{i});
            } else {
                ofInt = ObjectAnimator.ofInt(cVar, C0005c.a, new int[]{c2});
            }
            ofInt.setEvaluator(android.support.design.a.c.a());
            aVar.a.b("color").a((Animator) ofInt);
            list.add(ofInt);
        }
    }

    private void d(View view, View view2, boolean z, boolean z2, a aVar, List<Animator> list, List<AnimatorListener> list2) {
        ObjectAnimator ofFloat;
        if (view2 instanceof ViewGroup) {
            if (!(view2 instanceof c) || android.support.design.circularreveal.b.a != 0) {
                ViewGroup a2 = a(view2);
                if (a2 != null) {
                    if (z) {
                        if (!z2) {
                            android.support.design.a.d.a.set(a2, Float.valueOf(0.0f));
                        }
                        ofFloat = ObjectAnimator.ofFloat(a2, android.support.design.a.d.a, new float[]{1.0f});
                    } else {
                        ofFloat = ObjectAnimator.ofFloat(a2, android.support.design.a.d.a, new float[]{0.0f});
                    }
                    aVar.a.b("contentFade").a((Animator) ofFloat);
                    list.add(ofFloat);
                }
            }
        }
    }

    private float a(View view, View view2, j jVar) {
        RectF rectF = this.b;
        RectF rectF2 = this.c;
        a(view, rectF);
        a(view2, rectF2);
        float f = 0.0f;
        switch (jVar.a & 7) {
            case 1:
                f = rectF2.centerX() - rectF.centerX();
                break;
            case 3:
                f = rectF2.left - rectF.left;
                break;
            case 5:
                f = rectF2.right - rectF.right;
                break;
        }
        return f + jVar.b;
    }

    private float b(View view, View view2, j jVar) {
        RectF rectF = this.b;
        RectF rectF2 = this.c;
        a(view, rectF);
        a(view2, rectF2);
        float f = 0.0f;
        switch (jVar.a & 112) {
            case 16:
                f = rectF2.centerY() - rectF.centerY();
                break;
            case 48:
                f = rectF2.top - rectF.top;
                break;
            case 80:
                f = rectF2.bottom - rectF.bottom;
                break;
        }
        return f + jVar.c;
    }

    private void a(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        int[] iArr = this.d;
        view.getLocationInWindow(iArr);
        rectF.offsetTo((float) iArr[0], (float) iArr[1]);
        rectF.offset((float) ((int) (-view.getTranslationX())), (float) ((int) (-view.getTranslationY())));
    }

    private float c(View view, View view2, j jVar) {
        RectF rectF = this.b;
        RectF rectF2 = this.c;
        a(view, rectF);
        a(view2, rectF2);
        rectF2.offset(-a(view, view2, jVar), 0.0f);
        return rectF.centerX() - rectF2.left;
    }

    private float d(View view, View view2, j jVar) {
        RectF rectF = this.b;
        RectF rectF2 = this.c;
        a(view, rectF);
        a(view2, rectF2);
        rectF2.offset(0.0f, -b(view, view2, jVar));
        return rectF.centerY() - rectF2.top;
    }

    private void a(View view, a aVar, i iVar, i iVar2, float f, float f2, float f3, float f4, RectF rectF) {
        float a2 = a(aVar, iVar, f, f3);
        float a3 = a(aVar, iVar2, f2, f4);
        Rect rect = this.a;
        view.getWindowVisibleDisplayFrame(rect);
        RectF rectF2 = this.b;
        rectF2.set(rect);
        RectF rectF3 = this.c;
        a(view, rectF3);
        rectF3.offset(a2, a3);
        rectF3.intersect(rectF2);
        rectF.set(rectF3);
    }

    private float a(a aVar, i iVar, float f, float f2) {
        long a2 = iVar.a();
        long b2 = iVar.b();
        i b3 = aVar.a.b("expansion");
        return android.support.design.a.a.a(f, f2, iVar.c().getInterpolation(((float) (((b3.b() + b3.a()) + 17) - a2)) / ((float) b2)));
    }

    private ViewGroup a(View view) {
        View findViewById = view.findViewById(R.id.mtrl_child_content_container);
        if (findViewById != null) {
            return b(findViewById);
        }
        if ((view instanceof TransformationChildLayout) || (view instanceof TransformationChildCard)) {
            return b(((ViewGroup) view).getChildAt(0));
        }
        return b(view);
    }

    private ViewGroup b(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    private int c(View view) {
        ColorStateList backgroundTintList = ViewCompat.getBackgroundTintList(view);
        if (backgroundTintList != null) {
            return backgroundTintList.getColorForState(view.getDrawableState(), backgroundTintList.getDefaultColor());
        }
        return 0;
    }

    private void a(View view, long j, int i, int i2, float f, List<Animator> list) {
        if (VERSION.SDK_INT >= 21 && j > 0) {
            Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, i, i2, f, f);
            createCircularReveal.setStartDelay(0);
            createCircularReveal.setDuration(j);
            list.add(createCircularReveal);
        }
    }

    private void a(View view, long j, long j2, long j3, int i, int i2, float f, List<Animator> list) {
        if (VERSION.SDK_INT >= 21 && j + j2 < j3) {
            Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, i, i2, f, f);
            createCircularReveal.setStartDelay(j + j2);
            createCircularReveal.setDuration(j3 - (j + j2));
            list.add(createCircularReveal);
        }
    }
}
