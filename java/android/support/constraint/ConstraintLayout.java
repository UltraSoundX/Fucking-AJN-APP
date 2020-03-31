package android.support.constraint;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.constraint.a.a.c;
import android.support.constraint.a.a.c.a;
import android.support.constraint.a.a.d;
import android.support.constraint.a.a.e;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;

public class ConstraintLayout extends ViewGroup {
    SparseArray<View> a = new SparseArray<>();
    d b = new d();
    int c = -1;
    int d = -1;
    int e = -1;
    int f = -1;
    private final ArrayList<c> g = new ArrayList<>(100);
    private int h = 0;
    private int i = 0;
    private int j = Integer.MAX_VALUE;
    private int k = Integer.MAX_VALUE;
    private boolean l = true;
    private int m = 2;
    private a n = null;

    public static class LayoutParams extends MarginLayoutParams {
        int A = 1;
        public float B = 0.0f;
        public float C = 0.0f;
        public int D = 0;
        public int E = 0;
        public int F = 0;
        public int G = 0;
        public int H = 0;
        public int I = 0;
        public int J = 0;
        public int K = 0;
        public int L = -1;
        public int M = -1;
        public int N = -1;
        boolean O = true;
        boolean P = true;
        boolean Q = false;
        boolean R = false;
        int S = -1;
        int T = -1;
        int U = -1;
        int V = -1;
        int W = -1;
        int X = -1;
        float Y = 0.5f;
        c Z = new c();
        public int a = -1;
        public int b = -1;
        public float c = -1.0f;
        public int d = -1;
        public int e = -1;
        public int f = -1;
        public int g = -1;
        public int h = -1;
        public int i = -1;
        public int j = -1;
        public int k = -1;
        public int l = -1;
        public int m = -1;
        public int n = -1;
        public int o = -1;
        public int p = -1;

        /* renamed from: q reason: collision with root package name */
        public int f313q = -1;
        public int r = -1;
        public int s = -1;
        public int t = -1;
        public int u = -1;
        public int v = -1;
        public float w = 0.5f;
        public float x = 0.5f;
        public String y = null;
        float z = 0.0f;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            int i2;
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                if (index == R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf) {
                    this.d = obtainStyledAttributes.getResourceId(index, this.d);
                    if (this.d == -1) {
                        this.d = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf) {
                    this.e = obtainStyledAttributes.getResourceId(index, this.e);
                    if (this.e == -1) {
                        this.e = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf) {
                    this.f = obtainStyledAttributes.getResourceId(index, this.f);
                    if (this.f == -1) {
                        this.f = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf) {
                    this.g = obtainStyledAttributes.getResourceId(index, this.g);
                    if (this.g == -1) {
                        this.g = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf) {
                    this.h = obtainStyledAttributes.getResourceId(index, this.h);
                    if (this.h == -1) {
                        this.h = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf) {
                    this.i = obtainStyledAttributes.getResourceId(index, this.i);
                    if (this.i == -1) {
                        this.i = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf) {
                    this.j = obtainStyledAttributes.getResourceId(index, this.j);
                    if (this.j == -1) {
                        this.j = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf) {
                    this.k = obtainStyledAttributes.getResourceId(index, this.k);
                    if (this.k == -1) {
                        this.k = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf) {
                    this.l = obtainStyledAttributes.getResourceId(index, this.l);
                    if (this.l == -1) {
                        this.l = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX) {
                    this.L = obtainStyledAttributes.getDimensionPixelOffset(index, this.L);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY) {
                    this.M = obtainStyledAttributes.getDimensionPixelOffset(index, this.M);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin) {
                    this.a = obtainStyledAttributes.getDimensionPixelOffset(index, this.a);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end) {
                    this.b = obtainStyledAttributes.getDimensionPixelOffset(index, this.b);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent) {
                    this.c = obtainStyledAttributes.getFloat(index, this.c);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_orientation) {
                    this.N = obtainStyledAttributes.getInt(index, this.N);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf) {
                    this.m = obtainStyledAttributes.getResourceId(index, this.m);
                    if (this.m == -1) {
                        this.m = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf) {
                    this.n = obtainStyledAttributes.getResourceId(index, this.n);
                    if (this.n == -1) {
                        this.n = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf) {
                    this.o = obtainStyledAttributes.getResourceId(index, this.o);
                    if (this.o == -1) {
                        this.o = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf) {
                    this.p = obtainStyledAttributes.getResourceId(index, this.p);
                    if (this.p == -1) {
                        this.p = obtainStyledAttributes.getInt(index, -1);
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft) {
                    this.f313q = obtainStyledAttributes.getDimensionPixelSize(index, this.f313q);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_goneMarginTop) {
                    this.r = obtainStyledAttributes.getDimensionPixelSize(index, this.r);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_goneMarginRight) {
                    this.s = obtainStyledAttributes.getDimensionPixelSize(index, this.s);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom) {
                    this.t = obtainStyledAttributes.getDimensionPixelSize(index, this.t);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_goneMarginStart) {
                    this.u = obtainStyledAttributes.getDimensionPixelSize(index, this.u);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd) {
                    this.v = obtainStyledAttributes.getDimensionPixelSize(index, this.v);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias) {
                    this.w = obtainStyledAttributes.getFloat(index, this.w);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias) {
                    this.x = obtainStyledAttributes.getFloat(index, this.x);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio) {
                    this.y = obtainStyledAttributes.getString(index);
                    this.z = Float.NaN;
                    this.A = -1;
                    if (this.y != null) {
                        int length = this.y.length();
                        int indexOf = this.y.indexOf(44);
                        if (indexOf <= 0 || indexOf >= length - 1) {
                            i2 = 0;
                        } else {
                            String substring = this.y.substring(0, indexOf);
                            if (substring.equalsIgnoreCase("W")) {
                                this.A = 0;
                            } else if (substring.equalsIgnoreCase("H")) {
                                this.A = 1;
                            }
                            i2 = indexOf + 1;
                        }
                        int indexOf2 = this.y.indexOf(58);
                        if (indexOf2 < 0 || indexOf2 >= length - 1) {
                            String substring2 = this.y.substring(i2);
                            if (substring2.length() > 0) {
                                try {
                                    this.z = Float.parseFloat(substring2);
                                } catch (NumberFormatException e2) {
                                }
                            }
                        } else {
                            String substring3 = this.y.substring(i2, indexOf2);
                            String substring4 = this.y.substring(indexOf2 + 1);
                            if (substring3.length() > 0 && substring4.length() > 0) {
                                try {
                                    float parseFloat = Float.parseFloat(substring3);
                                    float parseFloat2 = Float.parseFloat(substring4);
                                    if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                                        if (this.A == 1) {
                                            this.z = Math.abs(parseFloat2 / parseFloat);
                                        } else {
                                            this.z = Math.abs(parseFloat / parseFloat2);
                                        }
                                    }
                                } catch (NumberFormatException e3) {
                                }
                            }
                        }
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight) {
                    this.B = obtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight) {
                    this.C = obtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle) {
                    this.D = obtainStyledAttributes.getInt(index, 0);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle) {
                    this.E = obtainStyledAttributes.getInt(index, 0);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default) {
                    this.F = obtainStyledAttributes.getInt(index, 0);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default) {
                    this.G = obtainStyledAttributes.getInt(index, 0);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min) {
                    this.H = obtainStyledAttributes.getDimensionPixelSize(index, this.H);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max) {
                    this.J = obtainStyledAttributes.getDimensionPixelSize(index, this.J);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min) {
                    this.I = obtainStyledAttributes.getDimensionPixelSize(index, this.I);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max) {
                    this.K = obtainStyledAttributes.getDimensionPixelSize(index, this.K);
                } else if (!(index == R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator || index == R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator || index == R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator || index == R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator || index == R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator)) {
                    Log.w("ConstraintLayout", "Unknown attribute 0x" + Integer.toHexString(index));
                }
            }
            obtainStyledAttributes.recycle();
            a();
        }

        public void a() {
            this.R = false;
            this.O = true;
            this.P = true;
            if (this.width == -1 || this.height == -1) {
                throw new IllegalStateException("MATCH_PARENT is not supported in ConstraintLayout");
            }
            if (this.width == 0 || this.width == -1) {
                this.O = false;
            }
            if (this.height == 0 || this.height == -1) {
                this.P = false;
            }
            if (this.c != -1.0f || this.a != -1 || this.b != -1) {
                this.R = true;
                this.O = true;
                this.P = true;
                if (!(this.Z instanceof e)) {
                    this.Z = new e();
                }
                ((e) this.Z).k(this.N);
            }
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        @TargetApi(17)
        public void resolveLayoutDirection(int i2) {
            boolean z2 = true;
            super.resolveLayoutDirection(i2);
            this.U = -1;
            this.V = -1;
            this.S = -1;
            this.T = -1;
            this.W = -1;
            this.X = -1;
            this.W = this.f313q;
            this.X = this.s;
            this.Y = this.w;
            if (1 != getLayoutDirection()) {
                z2 = false;
            }
            if (z2) {
                if (this.m != -1) {
                    this.U = this.m;
                } else if (this.n != -1) {
                    this.V = this.n;
                }
                if (this.o != -1) {
                    this.T = this.o;
                }
                if (this.p != -1) {
                    this.S = this.p;
                }
                if (this.u != -1) {
                    this.X = this.u;
                }
                if (this.v != -1) {
                    this.W = this.v;
                }
                this.Y = 1.0f - this.w;
            } else {
                if (this.m != -1) {
                    this.T = this.m;
                }
                if (this.n != -1) {
                    this.S = this.n;
                }
                if (this.o != -1) {
                    this.U = this.o;
                }
                if (this.p != -1) {
                    this.V = this.p;
                }
                if (this.u != -1) {
                    this.W = this.u;
                }
                if (this.v != -1) {
                    this.X = this.v;
                }
            }
            if (this.o == -1 && this.p == -1) {
                if (this.f != -1) {
                    this.U = this.f;
                } else if (this.g != -1) {
                    this.V = this.g;
                }
            }
            if (this.n != -1 || this.m != -1) {
                return;
            }
            if (this.d != -1) {
                this.S = this.d;
            } else if (this.e != -1) {
                this.T = this.e;
            }
        }
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b(attributeSet);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        b(attributeSet);
    }

    private void b(AttributeSet attributeSet) {
        this.b.a((Object) this);
        this.a.put(getId(), this);
        this.n = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.h = obtainStyledAttributes.getDimensionPixelOffset(index, this.h);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.i = obtainStyledAttributes.getDimensionPixelOffset(index, this.i);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.j = obtainStyledAttributes.getDimensionPixelOffset(index, this.j);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.k = obtainStyledAttributes.getDimensionPixelOffset(index, this.k);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.m = obtainStyledAttributes.getInt(index, this.m);
                } else if (index == R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    this.n = new a();
                    this.n.a(getContext(), resourceId);
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.b.k(this.m);
    }

    public void addView(View view, int i2, android.view.ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        if (VERSION.SDK_INT < 14) {
            onViewAdded(view);
        }
    }

    public void removeView(View view) {
        super.removeView(view);
        if (VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    public void onViewAdded(View view) {
        if (VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        c a2 = a(view);
        if ((view instanceof Guideline) && !(a2 instanceof e)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.Z = new e();
            layoutParams.R = true;
            ((e) layoutParams.Z).k(layoutParams.N);
            c cVar = layoutParams.Z;
        }
        this.a.put(view.getId(), view);
        this.l = true;
    }

    public void onViewRemoved(View view) {
        if (VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.a.remove(view.getId());
        this.b.d(a(view));
        this.l = true;
    }

    public void setMinWidth(int i2) {
        if (i2 != this.h) {
            this.h = i2;
            requestLayout();
        }
    }

    public void setMinHeight(int i2) {
        if (i2 != this.i) {
            this.i = i2;
            requestLayout();
        }
    }

    public int getMinWidth() {
        return this.h;
    }

    public int getMinHeight() {
        return this.i;
    }

    public void setMaxWidth(int i2) {
        if (i2 != this.j) {
            this.j = i2;
            requestLayout();
        }
    }

    public void setMaxHeight(int i2) {
        if (i2 != this.k) {
            this.k = i2;
            requestLayout();
        }
    }

    public int getMaxWidth() {
        return this.j;
    }

    public int getMaxHeight() {
        return this.k;
    }

    private void c() {
        boolean z = false;
        int childCount = getChildCount();
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            } else if (getChildAt(i2).isLayoutRequested()) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            this.g.clear();
            d();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x01ba  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01d5  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0207  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x024c  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x02a9  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02b5  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0145  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d() {
        /*
            r13 = this;
            android.support.constraint.a r0 = r13.n
            if (r0 == 0) goto L_0x0009
            android.support.constraint.a r0 = r13.n
            r0.a(r13)
        L_0x0009:
            int r12 = r13.getChildCount()
            android.support.constraint.a.a.d r0 = r13.b
            r0.G()
            r0 = 0
            r11 = r0
        L_0x0014:
            if (r11 >= r12) goto L_0x02c1
            android.view.View r2 = r13.getChildAt(r11)
            android.support.constraint.a.a.c r0 = r13.a(r2)
            if (r0 != 0) goto L_0x0024
        L_0x0020:
            int r0 = r11 + 1
            r11 = r0
            goto L_0x0014
        L_0x0024:
            android.view.ViewGroup$LayoutParams r1 = r2.getLayoutParams()
            r8 = r1
            android.support.constraint.ConstraintLayout$LayoutParams r8 = (android.support.constraint.ConstraintLayout.LayoutParams) r8
            r0.a()
            int r1 = r2.getVisibility()
            r0.a(r1)
            r0.a(r2)
            android.support.constraint.a.a.d r1 = r13.b
            r1.c(r0)
            boolean r1 = r8.P
            if (r1 == 0) goto L_0x0045
            boolean r1 = r8.O
            if (r1 != 0) goto L_0x004a
        L_0x0045:
            java.util.ArrayList<android.support.constraint.a.a.c> r1 = r13.g
            r1.add(r0)
        L_0x004a:
            boolean r1 = r8.R
            if (r1 == 0) goto L_0x0072
            android.support.constraint.a.a.e r0 = (android.support.constraint.a.a.e) r0
            int r1 = r8.a
            r2 = -1
            if (r1 == r2) goto L_0x005a
            int r1 = r8.a
            r0.l(r1)
        L_0x005a:
            int r1 = r8.b
            r2 = -1
            if (r1 == r2) goto L_0x0064
            int r1 = r8.b
            r0.m(r1)
        L_0x0064:
            float r1 = r8.c
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 == 0) goto L_0x0020
            float r1 = r8.c
            r0.e(r1)
            goto L_0x0020
        L_0x0072:
            int r1 = r8.S
            r2 = -1
            if (r1 != r2) goto L_0x00a9
            int r1 = r8.T
            r2 = -1
            if (r1 != r2) goto L_0x00a9
            int r1 = r8.U
            r2 = -1
            if (r1 != r2) goto L_0x00a9
            int r1 = r8.V
            r2 = -1
            if (r1 != r2) goto L_0x00a9
            int r1 = r8.h
            r2 = -1
            if (r1 != r2) goto L_0x00a9
            int r1 = r8.i
            r2 = -1
            if (r1 != r2) goto L_0x00a9
            int r1 = r8.j
            r2 = -1
            if (r1 != r2) goto L_0x00a9
            int r1 = r8.k
            r2 = -1
            if (r1 != r2) goto L_0x00a9
            int r1 = r8.l
            r2 = -1
            if (r1 != r2) goto L_0x00a9
            int r1 = r8.L
            r2 = -1
            if (r1 != r2) goto L_0x00a9
            int r1 = r8.M
            r2 = -1
            if (r1 == r2) goto L_0x0020
        L_0x00a9:
            int r7 = r8.S
            int r6 = r8.T
            int r4 = r8.U
            int r3 = r8.V
            int r5 = r8.W
            int r2 = r8.X
            float r1 = r8.Y
            int r9 = android.os.Build.VERSION.SDK_INT
            r10 = 17
            if (r9 >= r10) goto L_0x02c2
            int r7 = r8.d
            int r6 = r8.e
            int r4 = r8.f
            int r3 = r8.g
            int r5 = r8.f313q
            int r2 = r8.s
            float r1 = r8.w
            r9 = -1
            if (r7 != r9) goto L_0x00d8
            r9 = -1
            if (r6 != r9) goto L_0x00d8
            int r9 = r8.n
            r10 = -1
            if (r9 == r10) goto L_0x0234
            int r7 = r8.n
        L_0x00d8:
            r9 = -1
            if (r4 != r9) goto L_0x02c2
            r9 = -1
            if (r3 != r9) goto L_0x02c2
            int r9 = r8.o
            r10 = -1
            if (r9 == r10) goto L_0x023d
            int r4 = r8.o
            r9 = r1
            r10 = r4
            r1 = r6
            r6 = r2
            r2 = r7
            r7 = r3
        L_0x00eb:
            r3 = -1
            if (r2 == r3) goto L_0x024c
            android.support.constraint.a.a.c r2 = r13.a(r2)
            if (r2 == 0) goto L_0x00fd
            android.support.constraint.a.a.b$c r1 = android.support.constraint.a.a.b.c.LEFT
            android.support.constraint.a.a.b$c r3 = android.support.constraint.a.a.b.c.LEFT
            int r4 = r8.leftMargin
            r0.a(r1, r2, r3, r4, r5)
        L_0x00fd:
            r1 = -1
            if (r10 == r1) goto L_0x0260
            android.support.constraint.a.a.c r2 = r13.a(r10)
            if (r2 == 0) goto L_0x0110
            android.support.constraint.a.a.b$c r1 = android.support.constraint.a.a.b.c.RIGHT
            android.support.constraint.a.a.b$c r3 = android.support.constraint.a.a.b.c.LEFT
            int r4 = r8.rightMargin
            r5 = r6
            r0.a(r1, r2, r3, r4, r5)
        L_0x0110:
            int r1 = r8.h
            r2 = -1
            if (r1 == r2) goto L_0x0275
            int r1 = r8.h
            android.support.constraint.a.a.c r2 = r13.a(r1)
            if (r2 == 0) goto L_0x0128
            android.support.constraint.a.a.b$c r1 = android.support.constraint.a.a.b.c.TOP
            android.support.constraint.a.a.b$c r3 = android.support.constraint.a.a.b.c.TOP
            int r4 = r8.topMargin
            int r5 = r8.r
            r0.a(r1, r2, r3, r4, r5)
        L_0x0128:
            int r1 = r8.j
            r2 = -1
            if (r1 == r2) goto L_0x028f
            int r1 = r8.j
            android.support.constraint.a.a.c r2 = r13.a(r1)
            if (r2 == 0) goto L_0x0140
            android.support.constraint.a.a.b$c r1 = android.support.constraint.a.a.b.c.BOTTOM
            android.support.constraint.a.a.b$c r3 = android.support.constraint.a.a.b.c.TOP
            int r4 = r8.bottomMargin
            int r5 = r8.t
            r0.a(r1, r2, r3, r4, r5)
        L_0x0140:
            int r1 = r8.l
            r2 = -1
            if (r1 == r2) goto L_0x0194
            android.util.SparseArray<android.view.View> r1 = r13.a
            int r2 = r8.l
            java.lang.Object r1 = r1.get(r2)
            android.view.View r1 = (android.view.View) r1
            int r2 = r8.l
            android.support.constraint.a.a.c r2 = r13.a(r2)
            if (r2 == 0) goto L_0x0194
            if (r1 == 0) goto L_0x0194
            android.view.ViewGroup$LayoutParams r3 = r1.getLayoutParams()
            boolean r3 = r3 instanceof android.support.constraint.ConstraintLayout.LayoutParams
            if (r3 == 0) goto L_0x0194
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.constraint.ConstraintLayout$LayoutParams r1 = (android.support.constraint.ConstraintLayout.LayoutParams) r1
            r3 = 1
            r8.Q = r3
            r3 = 1
            r1.Q = r3
            android.support.constraint.a.a.b$c r1 = android.support.constraint.a.a.b.c.BASELINE
            android.support.constraint.a.a.b r1 = r0.a(r1)
            android.support.constraint.a.a.b$c r3 = android.support.constraint.a.a.b.c.BASELINE
            android.support.constraint.a.a.b r2 = r2.a(r3)
            r3 = 0
            r4 = -1
            android.support.constraint.a.a.b$b r5 = android.support.constraint.a.a.b.C0004b.STRONG
            r6 = 0
            r7 = 1
            r1.a(r2, r3, r4, r5, r6, r7)
            android.support.constraint.a.a.b$c r1 = android.support.constraint.a.a.b.c.TOP
            android.support.constraint.a.a.b r1 = r0.a(r1)
            r1.i()
            android.support.constraint.a.a.b$c r1 = android.support.constraint.a.a.b.c.BOTTOM
            android.support.constraint.a.a.b r1 = r0.a(r1)
            r1.i()
        L_0x0194:
            r1 = 0
            int r1 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r1 < 0) goto L_0x01a2
            r1 = 1056964608(0x3f000000, float:0.5)
            int r1 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x01a2
            r0.a(r9)
        L_0x01a2:
            float r1 = r8.x
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 < 0) goto L_0x01b6
            float r1 = r8.x
            r2 = 1056964608(0x3f000000, float:0.5)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 == 0) goto L_0x01b6
            float r1 = r8.x
            r0.b(r1)
        L_0x01b6:
            boolean r1 = r8.O
            if (r1 != 0) goto L_0x02a9
            android.support.constraint.a.a.c$a r1 = android.support.constraint.a.a.c.a.MATCH_CONSTRAINT
            r0.a(r1)
            r1 = 0
            r0.d(r1)
            int r1 = r8.width
            r2 = -1
            if (r1 != r2) goto L_0x01d1
            android.support.constraint.a.a.d r1 = r13.b
            int r1 = r1.l()
            r0.d(r1)
        L_0x01d1:
            boolean r1 = r8.P
            if (r1 != 0) goto L_0x02b5
            android.support.constraint.a.a.c$a r1 = android.support.constraint.a.a.c.a.MATCH_CONSTRAINT
            r0.b(r1)
            r1 = 0
            r0.e(r1)
            int r1 = r8.height
            r2 = -1
            if (r1 != r2) goto L_0x01ec
            android.support.constraint.a.a.d r1 = r13.b
            int r1 = r1.m()
            r0.d(r1)
        L_0x01ec:
            boolean r1 = r13.isInEditMode()
            if (r1 == 0) goto L_0x0203
            int r1 = r8.L
            r2 = -1
            if (r1 != r2) goto L_0x01fc
            int r1 = r8.M
            r2 = -1
            if (r1 == r2) goto L_0x0203
        L_0x01fc:
            int r1 = r8.L
            int r2 = r8.M
            r0.a(r1, r2)
        L_0x0203:
            java.lang.String r1 = r8.y
            if (r1 == 0) goto L_0x020c
            java.lang.String r1 = r8.y
            r0.a(r1)
        L_0x020c:
            float r1 = r8.B
            r0.c(r1)
            float r1 = r8.C
            r0.d(r1)
            int r1 = r8.D
            r0.i(r1)
            int r1 = r8.E
            r0.j(r1)
            int r1 = r8.F
            int r2 = r8.H
            int r3 = r8.J
            r0.a(r1, r2, r3)
            int r1 = r8.G
            int r2 = r8.I
            int r3 = r8.K
            r0.b(r1, r2, r3)
            goto L_0x0020
        L_0x0234:
            int r9 = r8.m
            r10 = -1
            if (r9 == r10) goto L_0x00d8
            int r6 = r8.m
            goto L_0x00d8
        L_0x023d:
            int r9 = r8.p
            r10 = -1
            if (r9 == r10) goto L_0x02c2
            int r3 = r8.p
            r9 = r1
            r10 = r4
            r1 = r6
            r6 = r2
            r2 = r7
            r7 = r3
            goto L_0x00eb
        L_0x024c:
            r2 = -1
            if (r1 == r2) goto L_0x00fd
            android.support.constraint.a.a.c r2 = r13.a(r1)
            if (r2 == 0) goto L_0x00fd
            android.support.constraint.a.a.b$c r1 = android.support.constraint.a.a.b.c.LEFT
            android.support.constraint.a.a.b$c r3 = android.support.constraint.a.a.b.c.RIGHT
            int r4 = r8.leftMargin
            r0.a(r1, r2, r3, r4, r5)
            goto L_0x00fd
        L_0x0260:
            r1 = -1
            if (r7 == r1) goto L_0x0110
            android.support.constraint.a.a.c r2 = r13.a(r7)
            if (r2 == 0) goto L_0x0110
            android.support.constraint.a.a.b$c r1 = android.support.constraint.a.a.b.c.RIGHT
            android.support.constraint.a.a.b$c r3 = android.support.constraint.a.a.b.c.RIGHT
            int r4 = r8.rightMargin
            r5 = r6
            r0.a(r1, r2, r3, r4, r5)
            goto L_0x0110
        L_0x0275:
            int r1 = r8.i
            r2 = -1
            if (r1 == r2) goto L_0x0128
            int r1 = r8.i
            android.support.constraint.a.a.c r2 = r13.a(r1)
            if (r2 == 0) goto L_0x0128
            android.support.constraint.a.a.b$c r1 = android.support.constraint.a.a.b.c.TOP
            android.support.constraint.a.a.b$c r3 = android.support.constraint.a.a.b.c.BOTTOM
            int r4 = r8.topMargin
            int r5 = r8.r
            r0.a(r1, r2, r3, r4, r5)
            goto L_0x0128
        L_0x028f:
            int r1 = r8.k
            r2 = -1
            if (r1 == r2) goto L_0x0140
            int r1 = r8.k
            android.support.constraint.a.a.c r2 = r13.a(r1)
            if (r2 == 0) goto L_0x0140
            android.support.constraint.a.a.b$c r1 = android.support.constraint.a.a.b.c.BOTTOM
            android.support.constraint.a.a.b$c r3 = android.support.constraint.a.a.b.c.BOTTOM
            int r4 = r8.bottomMargin
            int r5 = r8.t
            r0.a(r1, r2, r3, r4, r5)
            goto L_0x0140
        L_0x02a9:
            android.support.constraint.a.a.c$a r1 = android.support.constraint.a.a.c.a.FIXED
            r0.a(r1)
            int r1 = r8.width
            r0.d(r1)
            goto L_0x01d1
        L_0x02b5:
            android.support.constraint.a.a.c$a r1 = android.support.constraint.a.a.c.a.FIXED
            r0.b(r1)
            int r1 = r8.height
            r0.e(r1)
            goto L_0x01ec
        L_0x02c1:
            return
        L_0x02c2:
            r9 = r1
            r10 = r4
            r1 = r6
            r6 = r2
            r2 = r7
            r7 = r3
            goto L_0x00eb
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.d():void");
    }

    private final c a(int i2) {
        if (i2 == 0) {
            return this.b;
        }
        View view = (View) this.a.get(i2);
        if (view == this) {
            return this.b;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).Z;
    }

    private final c a(View view) {
        if (view == this) {
            return this.b;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).Z;
    }

    private void a(int i2, int i3) {
        int childMeasureSpec;
        int childMeasureSpec2;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                c cVar = layoutParams.Z;
                if (!layoutParams.R) {
                    int i5 = layoutParams.width;
                    int i6 = layoutParams.height;
                    if (layoutParams.O || layoutParams.P || (!layoutParams.O && layoutParams.F == 1) || (!layoutParams.P && layoutParams.G == 1)) {
                        if (i5 == 0) {
                            childMeasureSpec = getChildMeasureSpec(i2, paddingLeft, -2);
                        } else {
                            childMeasureSpec = getChildMeasureSpec(i2, paddingLeft, i5);
                        }
                        if (i6 == 0) {
                            childMeasureSpec2 = getChildMeasureSpec(i3, paddingTop, -2);
                        } else {
                            childMeasureSpec2 = getChildMeasureSpec(i3, paddingTop, i6);
                        }
                        childAt.measure(childMeasureSpec, childMeasureSpec2);
                        i5 = childAt.getMeasuredWidth();
                        i6 = childAt.getMeasuredHeight();
                    }
                    cVar.d(i5);
                    cVar.e(i6);
                    if (layoutParams.Q) {
                        int baseline = childAt.getBaseline();
                        if (baseline != -1) {
                            cVar.h(baseline);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int makeMeasureSpec;
        int makeMeasureSpec2;
        boolean z;
        int i4;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        if (this.c == -1 || this.d == -1 || this.f == -1 || this.e == -1 || this.c != paddingLeft || this.d != paddingTop || this.e != i2 || this.f != i3) {
            this.b.b(paddingLeft);
            this.b.c(paddingTop);
            b(i2, i3);
        }
        if (this.l) {
            this.l = false;
            c();
        }
        this.c = paddingLeft;
        this.d = paddingTop;
        this.e = i2;
        this.f = i3;
        a(i2, i3);
        if (getChildCount() > 0) {
            a();
        }
        int i5 = 0;
        int size = this.g.size();
        int paddingBottom = paddingTop + getPaddingBottom();
        int paddingRight = paddingLeft + getPaddingRight();
        if (size > 0) {
            boolean z2 = false;
            int i6 = 0;
            while (i6 < size) {
                c cVar = (c) this.g.get(i6);
                if (cVar instanceof e) {
                    i4 = i5;
                } else {
                    View view = (View) cVar.x();
                    if (view == null) {
                        i4 = i5;
                    } else if (view.getVisibility() == 8) {
                        i4 = i5;
                    } else {
                        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                        if (layoutParams.width == -2) {
                            makeMeasureSpec = getChildMeasureSpec(i2, paddingRight, layoutParams.width);
                        } else {
                            makeMeasureSpec = MeasureSpec.makeMeasureSpec(cVar.l(), 1073741824);
                        }
                        if (layoutParams.height == -2) {
                            makeMeasureSpec2 = getChildMeasureSpec(i3, paddingBottom, layoutParams.height);
                        } else {
                            makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(cVar.m(), 1073741824);
                        }
                        view.measure(makeMeasureSpec, makeMeasureSpec2);
                        int measuredWidth = view.getMeasuredWidth();
                        int measuredHeight = view.getMeasuredHeight();
                        if (measuredWidth != cVar.l()) {
                            cVar.d(measuredWidth);
                            z = true;
                        } else {
                            z = z2;
                        }
                        if (measuredHeight != cVar.m()) {
                            cVar.e(measuredHeight);
                            z = true;
                        }
                        if (layoutParams.Q) {
                            int baseline = view.getBaseline();
                            if (!(baseline == -1 || baseline == cVar.w())) {
                                cVar.h(baseline);
                                z = true;
                            }
                        }
                        if (VERSION.SDK_INT >= 11) {
                            i4 = combineMeasuredStates(i5, view.getMeasuredState());
                            z2 = z;
                        } else {
                            z2 = z;
                            i4 = i5;
                        }
                    }
                }
                i6++;
                i5 = i4;
            }
            if (z2) {
                a();
            }
        }
        int l2 = this.b.l() + paddingRight;
        int m2 = this.b.m() + paddingBottom;
        if (VERSION.SDK_INT >= 11) {
            int resolveSizeAndState = resolveSizeAndState(l2, i2, i5);
            int resolveSizeAndState2 = resolveSizeAndState(m2, i3, i5 << 16);
            setMeasuredDimension(Math.min(this.j, resolveSizeAndState) & ViewCompat.MEASURED_SIZE_MASK, Math.min(this.k, resolveSizeAndState2) & ViewCompat.MEASURED_SIZE_MASK);
            return;
        }
        setMeasuredDimension(l2, m2);
    }

    private void b(int i2, int i3) {
        a aVar;
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i3);
        int size2 = MeasureSpec.getSize(i3);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        a aVar2 = a.FIXED;
        a aVar3 = a.FIXED;
        android.view.ViewGroup.LayoutParams layoutParams = getLayoutParams();
        switch (mode) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                aVar2 = a.WRAP_CONTENT;
                break;
            case 0:
                if (layoutParams.width <= 0) {
                    aVar2 = a.WRAP_CONTENT;
                    size = 0;
                    break;
                } else {
                    size = Math.min(this.j, layoutParams.width);
                    break;
                }
            case 1073741824:
                size = Math.min(this.j, size) - paddingLeft;
                break;
            default:
                size = 0;
                break;
        }
        switch (mode2) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                aVar = a.WRAP_CONTENT;
                break;
            case 0:
                if (layoutParams.height <= 0) {
                    aVar = a.WRAP_CONTENT;
                    size2 = 0;
                    break;
                } else {
                    size2 = Math.min(this.k, layoutParams.height);
                    aVar = aVar3;
                    break;
                }
            case 1073741824:
                size2 = Math.min(this.k, size2) - paddingTop;
                aVar = aVar3;
                break;
            default:
                size2 = 0;
                aVar = aVar3;
                break;
        }
        this.b.f(this.h);
        this.b.g(this.i);
        this.b.a(aVar2);
        this.b.d(size);
        this.b.b(aVar);
        this.b.e(size2);
    }

    /* access modifiers changed from: protected */
    public void a() {
        android.support.constraint.a.a.a.a(false);
        this.b.D();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (childAt.getVisibility() != 8 || layoutParams.R || isInEditMode) {
                c cVar = layoutParams.Z;
                int n2 = cVar.n();
                int o = cVar.o();
                childAt.layout(n2, o, cVar.l() + n2, cVar.m() + o);
            }
        }
    }

    public void setOptimizationLevel(int i2) {
        this.b.k(i2);
    }

    /* renamed from: a */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void setConstraintSet(a aVar) {
        this.n = aVar;
    }

    public void requestLayout() {
        super.requestLayout();
        this.l = true;
    }
}
