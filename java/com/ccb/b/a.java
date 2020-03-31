package com.ccb.b;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import com.ccb.b.a.b;
import com.ccb.b.a.c;
import com.ccb.b.a.e;
import com.ccb.b.a.f;
import com.ccb.crypto.tp.tool.d;
import com.stub.StubApp;
import java.lang.reflect.Method;

public class a implements l {
    public static int a = 0;
    public static int b = 0;
    public static int c = 0;
    /* access modifiers changed from: private */
    public static EditText l = null;
    /* access modifiers changed from: private */
    public static boolean o = false;

    /* renamed from: q reason: collision with root package name */
    private static int f395q = 0;
    private static DisplayMetrics r;
    private String A = "";
    /* access modifiers changed from: private */
    public String B;
    private boolean C = false;
    private boolean D;
    /* access modifiers changed from: private */
    public String E;
    private b F;
    private f G = null;
    private OnTouchListener H = new g(this);
    private OnKeyListener I = new h(this);
    private Context d;
    private m e = null;
    private StringBuffer f = null;
    private StringBuffer g = new StringBuffer();
    /* access modifiers changed from: private */
    public c h;
    /* access modifiers changed from: private */
    public boolean i = true;
    /* access modifiers changed from: private */
    public boolean j = true;
    /* access modifiers changed from: private */
    public int k = 100;
    private b m = null;
    private boolean n = true;
    /* access modifiers changed from: private */
    public PopupWindow p = null;
    private d s;
    private boolean t = true;
    @Deprecated
    private boolean u = false;
    private int v = 1;
    /* access modifiers changed from: private */
    public boolean w = false;
    private boolean x = false;
    private boolean y = false;
    /* access modifiers changed from: private */
    public String z;

    public a(Context context, com.ccb.crypto.tp.tool.a aVar) {
        this.d = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.f = new StringBuffer("");
        this.s = (d) aVar;
        this.h = new e((d) aVar);
        b(true);
        a(true);
        d(true);
        if (this.e == null) {
            a(context);
            this.e = new m();
        }
    }

    public String a() {
        return this.B;
    }

    public void a(boolean z2) {
        this.D = z2;
    }

    public void b(boolean z2) {
        this.i = z2;
    }

    public void c(boolean z2) {
        this.j = z2;
    }

    public void a(int i2) {
        this.k = i2;
    }

    public void b(int i2) {
        this.v = i2;
    }

    public void d(boolean z2) {
        this.x = z2;
    }

    public void a(EditText editText, f fVar) {
        this.G = fVar;
        b(editText);
    }

    private void b(EditText editText) {
        editText.setOnFocusChangeListener(c(editText));
        editText.setOnTouchListener(this.H);
        editText.setOnKeyListener(this.I);
    }

    private OnFocusChangeListener c(EditText editText) {
        return new f(this, editText.getOnFocusChangeListener());
    }

    /* access modifiers changed from: private */
    public void d(EditText editText) {
        this.w = false;
        com.ccb.b.b.b.setOnKeysListener(this.m);
        this.m.setNormalArrange(this.x);
        this.m.setKeyboardLocked(this.y);
        this.z = "";
        this.A = "";
        c();
        this.m.setKeybordType(this.v);
        g(editText);
        l = editText;
        if (o && this.p != null) {
            this.p.dismiss();
        }
        if (this.p == null) {
            this.p = d();
        }
        a = i(editText);
        a = a < 0 ? 0 : a;
        if (editText.getVisibility() == 0) {
            this.p.showAtLocation(editText, 81, 0, 0);
            b((View) l, a);
        }
        this.p.setOnDismissListener(new i(this));
        if (this.G != null) {
            this.G.a(true);
        }
        try {
            if (this.i && this.h != null) {
                this.B = "";
                this.h.a();
                editText.setText("");
            } else if (this.j && !this.i) {
                editText.setText("");
            }
            this.e.a(new j(this, editText));
        } catch (Exception e2) {
        }
        o = true;
    }

    private void e(EditText editText) {
        if (!this.w) {
            this.w = true;
            if (this.i && this.h != null) {
                com.ccb.b.a.d dVar = new com.ccb.b.a.d();
                if ("".equals(editText.getText().toString())) {
                    this.B = "";
                    this.E = "";
                    c(0);
                    if (this.F != null) {
                        dVar.a(0);
                        dVar.a("");
                        dVar.b("");
                        dVar.b(0);
                        this.F.a(dVar);
                    }
                } else if (!this.C) {
                    com.ccb.b.a.a b2 = this.h.b();
                    this.B = b2.a();
                    this.E = b2.b();
                    c(b2.c());
                    dVar.a(b2.c());
                    dVar.a(b2.a());
                    dVar.b(b2.b());
                    dVar.b(editText.getText().toString().length());
                    if (this.F != null) {
                        this.F.a(dVar);
                    }
                }
            }
        }
    }

    public void e(boolean z2) {
        if (l == null) {
            return;
        }
        if (z2) {
            h(l);
            return;
        }
        if (this.i && this.h != null) {
            this.B = "";
            this.E = "";
            l.setText("");
            this.h.a();
        }
        h(l);
    }

    /* access modifiers changed from: private */
    public void f(EditText editText) {
        ((InputMethodManager) this.d.getSystemService("input_method")).showSoftInput(editText, 2);
        h(editText);
    }

    /* access modifiers changed from: private */
    public void g(EditText editText) {
        Context context = editText.getContext();
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isChild()) {
                while (activity.isChild()) {
                    activity = activity.getParent();
                }
                context = activity;
            }
        }
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
        if (VERSION.SDK_INT > 10) {
            try {
                Method method = EditText.class.getMethod("setSoftInputShownOnFocus", new Class[]{Boolean.TYPE});
                method.setAccessible(true);
                method.invoke(editText, new Object[]{Boolean.valueOf(false)});
            } catch (Exception e2) {
            }
            try {
                Method method2 = EditText.class.getMethod("setShowSoftInputOnFocus", new Class[]{Boolean.TYPE});
                method2.setAccessible(true);
                method2.invoke(editText, new Object[]{Boolean.valueOf(false)});
            } catch (Exception e3) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void h(EditText editText) {
        if (this.p != null && this.p.isShowing()) {
            a((View) editText, a);
            a = 0;
            this.p.dismiss();
            o = false;
            if (this.G != null) {
                this.G.a(false);
            }
            c();
        }
        if (this.i && this.h != null && !this.w) {
            e(editText);
        }
    }

    private static void c() {
        l = null;
    }

    private static void b(View view, int i2) {
        View findViewById = b(view.getContext()).getWindow().getDecorView().findViewById(16908290);
        if (VERSION.SDK_INT >= 11) {
            String str = "TranslationY";
            float[] fArr = new float[1];
            fArr[0] = i2 <= 0 ? (float) (-i2) : (float) ((-i2) - f395q);
            ObjectAnimator.ofFloat(findViewById, str, fArr).setDuration(0).start();
            return;
        }
        if (i2 > 0) {
            i2 += f395q;
        }
        findViewById.scrollBy(0, i2);
    }

    static void a(View view, int i2) {
        View findViewById = b(view.getContext()).getWindow().getDecorView().findViewById(16908290);
        if (VERSION.SDK_INT >= 11) {
            ObjectAnimator.ofFloat(findViewById, "TranslationY", new float[]{0.0f}).setDuration(0).start();
        } else {
            findViewById.scrollBy(0, i2 <= 0 ? -i2 : (-i2) - f395q);
        }
    }

    private static int i(EditText editText) {
        return b - (((ViewGroup) b(editText.getContext()).getWindow().getDecorView().findViewById(16908290)).getHeight() - (editText.getHeight() + a((View) editText)));
    }

    private static int a(View view) {
        return b(view) - b(((ViewGroup) b(view.getContext()).getWindow().getDecorView().findViewById(16908290)).getChildAt(0));
    }

    private static int b(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr[1];
    }

    private void a(Context context) {
        r = new DisplayMetrics();
        r = StubApp.getOrigApplicationContext(context.getApplicationContext()).getResources().getDisplayMetrics();
        c = r.widthPixels;
        b = (r.heightPixels * 9) / 20;
        this.m = new b(this.d, r);
    }

    /* access modifiers changed from: private */
    public PopupWindow d() {
        return new PopupWindow(this.m, c, b);
    }

    public void c(int i2) {
        if (i2 < 10) {
            this.A = "AQTDAQJP000" + i2;
        } else {
            this.A = "AQTDAQJP00" + i2;
        }
    }

    private static Activity b(Context context) {
        for (Context context2 = context; context2 instanceof ContextWrapper; context2 = ((ContextWrapper) context2).getBaseContext()) {
            if (context2 instanceof Activity) {
                return (Activity) context2;
            }
        }
        return null;
    }

    public void a(EditText editText) {
        a(editText, (f) null);
    }
}
