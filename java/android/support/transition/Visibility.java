package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.transition.Transition.c;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public abstract class Visibility extends Transition {
    private static final String[] a = {"android:visibility:visibility", "android:visibility:parent"};
    private int i = 3;

    private static class a extends AnimatorListenerAdapter implements c, C0009a {
        boolean a = false;
        private final View b;
        private final int c;
        private final ViewGroup d;
        private final boolean e;
        private boolean f;

        a(View view, int i, boolean z) {
            this.b = view;
            this.c = i;
            this.d = (ViewGroup) view.getParent();
            this.e = z;
            a(true);
        }

        public void onAnimationPause(Animator animator) {
            if (!this.a) {
                ah.a(this.b, this.c);
            }
        }

        public void onAnimationResume(Animator animator) {
            if (!this.a) {
                ah.a(this.b, 0);
            }
        }

        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            a();
        }

        public void d(Transition transition) {
        }

        public void a(Transition transition) {
            a();
            transition.b((c) this);
        }

        public void b(Transition transition) {
            a(false);
        }

        public void c(Transition transition) {
            a(true);
        }

        private void a() {
            if (!this.a) {
                ah.a(this.b, this.c);
                if (this.d != null) {
                    this.d.invalidate();
                }
            }
            a(false);
        }

        private void a(boolean z) {
            if (this.e && this.f != z && this.d != null) {
                this.f = z;
                ab.a(this.d, z);
            }
        }
    }

    private static class b {
        boolean a;
        boolean b;
        int c;
        int d;
        ViewGroup e;
        ViewGroup f;

        b() {
        }
    }

    public Visibility() {
    }

    public Visibility(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.e);
        int namedInt = TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionVisibilityMode", 0, 0);
        obtainStyledAttributes.recycle();
        if (namedInt != 0) {
            b(namedInt);
        }
    }

    public void b(int i2) {
        if ((i2 & -4) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.i = i2;
    }

    public int r() {
        return this.i;
    }

    public String[] a() {
        return a;
    }

    private void d(v vVar) {
        vVar.a.put("android:visibility:visibility", Integer.valueOf(vVar.b.getVisibility()));
        vVar.a.put("android:visibility:parent", vVar.b.getParent());
        int[] iArr = new int[2];
        vVar.b.getLocationOnScreen(iArr);
        vVar.a.put("android:visibility:screenLocation", iArr);
    }

    public void a(v vVar) {
        d(vVar);
    }

    public void b(v vVar) {
        d(vVar);
    }

    private b b(v vVar, v vVar2) {
        b bVar = new b();
        bVar.a = false;
        bVar.b = false;
        if (vVar == null || !vVar.a.containsKey("android:visibility:visibility")) {
            bVar.c = -1;
            bVar.e = null;
        } else {
            bVar.c = ((Integer) vVar.a.get("android:visibility:visibility")).intValue();
            bVar.e = (ViewGroup) vVar.a.get("android:visibility:parent");
        }
        if (vVar2 == null || !vVar2.a.containsKey("android:visibility:visibility")) {
            bVar.d = -1;
            bVar.f = null;
        } else {
            bVar.d = ((Integer) vVar2.a.get("android:visibility:visibility")).intValue();
            bVar.f = (ViewGroup) vVar2.a.get("android:visibility:parent");
        }
        if (vVar == null || vVar2 == null) {
            if (vVar == null && bVar.d == 0) {
                bVar.b = true;
                bVar.a = true;
            } else if (vVar2 == null && bVar.c == 0) {
                bVar.b = false;
                bVar.a = true;
            }
        } else if (bVar.c == bVar.d && bVar.e == bVar.f) {
            return bVar;
        } else {
            if (bVar.c != bVar.d) {
                if (bVar.c == 0) {
                    bVar.b = false;
                    bVar.a = true;
                } else if (bVar.d == 0) {
                    bVar.b = true;
                    bVar.a = true;
                }
            } else if (bVar.f == null) {
                bVar.b = false;
                bVar.a = true;
            } else if (bVar.e == null) {
                bVar.b = true;
                bVar.a = true;
            }
        }
        return bVar;
    }

    public Animator a(ViewGroup viewGroup, v vVar, v vVar2) {
        b b2 = b(vVar, vVar2);
        if (!b2.a || (b2.e == null && b2.f == null)) {
            return null;
        }
        if (b2.b) {
            return a(viewGroup, vVar, b2.c, vVar2, b2.d);
        }
        return b(viewGroup, vVar, b2.c, vVar2, b2.d);
    }

    public Animator a(ViewGroup viewGroup, v vVar, int i2, v vVar2, int i3) {
        if ((this.i & 1) != 1 || vVar2 == null) {
            return null;
        }
        if (vVar == null) {
            View view = (View) vVar2.b.getParent();
            if (b(b(view, false), a(view, false)).a) {
                return null;
            }
        }
        return a(viewGroup, vVar2.b, vVar, vVar2);
    }

    public Animator a(ViewGroup viewGroup, View view, v vVar, v vVar2) {
        return null;
    }

    public Animator b(ViewGroup viewGroup, v vVar, int i2, v vVar2, int i3) {
        View view;
        View view2;
        Animator animator = null;
        if ((this.i & 2) == 2) {
            final View view3 = vVar != null ? vVar.b : null;
            if (vVar2 != null) {
                view = vVar2.b;
            } else {
                view = null;
            }
            if (view == null || view.getParent() == null) {
                if (view != null) {
                    view3 = view;
                    view = null;
                } else {
                    if (view3 != null) {
                        if (view3.getParent() == null) {
                            view = null;
                        } else if (view3.getParent() instanceof View) {
                            View view4 = (View) view3.getParent();
                            if (!b(a(view4, true), b(view4, true)).a) {
                                view2 = u.a(viewGroup, view3, view4);
                            } else {
                                if (view4.getParent() == null) {
                                    int id = view4.getId();
                                    if (!(id == -1 || viewGroup.findViewById(id) == null || !this.f)) {
                                        view2 = view3;
                                    }
                                }
                                view2 = null;
                            }
                            view3 = view2;
                            view = null;
                        }
                    }
                    view = null;
                    view3 = null;
                }
            } else if (i3 == 4) {
                view3 = null;
            } else if (view3 == view) {
                view3 = null;
            } else if (this.f) {
                view = null;
            } else {
                view3 = u.a(viewGroup, view3, (View) view3.getParent());
                view = null;
            }
            if (view3 != null && vVar != null) {
                int[] iArr = (int[]) vVar.a.get("android:visibility:screenLocation");
                int i4 = iArr[0];
                int i5 = iArr[1];
                int[] iArr2 = new int[2];
                viewGroup.getLocationOnScreen(iArr2);
                view3.offsetLeftAndRight((i4 - iArr2[0]) - view3.getLeft());
                view3.offsetTopAndBottom((i5 - iArr2[1]) - view3.getTop());
                final aa a2 = ab.a(viewGroup);
                a2.a(view3);
                animator = b(viewGroup, view3, vVar, vVar2);
                if (animator == null) {
                    a2.b(view3);
                } else {
                    animator.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            a2.b(view3);
                        }
                    });
                }
            } else if (view != null) {
                int visibility = view.getVisibility();
                ah.a(view, 0);
                animator = b(viewGroup, view, vVar, vVar2);
                if (animator != null) {
                    a aVar = new a(view, i3, true);
                    animator.addListener(aVar);
                    a.a(animator, aVar);
                    a((c) aVar);
                } else {
                    ah.a(view, visibility);
                }
            }
        }
        return animator;
    }

    public Animator b(ViewGroup viewGroup, View view, v vVar, v vVar2) {
        return null;
    }

    public boolean a(v vVar, v vVar2) {
        if (vVar == null && vVar2 == null) {
            return false;
        }
        if (vVar != null && vVar2 != null && vVar2.a.containsKey("android:visibility:visibility") != vVar.a.containsKey("android:visibility:visibility")) {
            return false;
        }
        b b2 = b(vVar, vVar2);
        if (!b2.a) {
            return false;
        }
        if (b2.c == 0 || b2.d == 0) {
            return true;
        }
        return false;
    }
}
