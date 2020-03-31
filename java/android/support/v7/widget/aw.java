package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnHoverListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

/* compiled from: TooltipCompatHandler */
class aw implements OnAttachStateChangeListener, OnHoverListener, OnLongClickListener {
    private static aw j;
    private static aw k;
    private final View a;
    private final CharSequence b;
    private final int c;
    private final Runnable d = new Runnable() {
        public void run() {
            aw.this.a(false);
        }
    };
    private final Runnable e = new Runnable() {
        public void run() {
            aw.this.a();
        }
    };
    private int f;
    private int g;
    private ax h;
    private boolean i;

    public static void a(View view, CharSequence charSequence) {
        if (j != null && j.a == view) {
            a((aw) null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            if (k != null && k.a == view) {
                k.a();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        new aw(view, charSequence);
    }

    private aw(View view, CharSequence charSequence) {
        this.a = view;
        this.b = charSequence;
        this.c = ViewConfigurationCompat.getScaledHoverSlop(ViewConfiguration.get(this.a.getContext()));
        d();
        this.a.setOnLongClickListener(this);
        this.a.setOnHoverListener(this);
    }

    public boolean onLongClick(View view) {
        this.f = view.getWidth() / 2;
        this.g = view.getHeight() / 2;
        a(true);
        return true;
    }

    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.h == null || !this.i) {
            AccessibilityManager accessibilityManager = (AccessibilityManager) this.a.getContext().getSystemService("accessibility");
            if (!accessibilityManager.isEnabled() || !accessibilityManager.isTouchExplorationEnabled()) {
                switch (motionEvent.getAction()) {
                    case 7:
                        if (this.a.isEnabled() && this.h == null && a(motionEvent)) {
                            a(this);
                            break;
                        }
                    case 10:
                        d();
                        a();
                        break;
                }
            }
        }
        return false;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        a();
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        long longPressTimeout;
        if (ViewCompat.isAttachedToWindow(this.a)) {
            a((aw) null);
            if (k != null) {
                k.a();
            }
            k = this;
            this.i = z;
            this.h = new ax(this.a.getContext());
            this.h.a(this.a, this.f, this.g, this.i, this.b);
            this.a.addOnAttachStateChangeListener(this);
            if (this.i) {
                longPressTimeout = 2500;
            } else if ((ViewCompat.getWindowSystemUiVisibility(this.a) & 1) == 1) {
                longPressTimeout = 3000 - ((long) ViewConfiguration.getLongPressTimeout());
            } else {
                longPressTimeout = 15000 - ((long) ViewConfiguration.getLongPressTimeout());
            }
            this.a.removeCallbacks(this.e);
            this.a.postDelayed(this.e, longPressTimeout);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        if (k == this) {
            k = null;
            if (this.h != null) {
                this.h.a();
                this.h = null;
                d();
                this.a.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (j == this) {
            a((aw) null);
        }
        this.a.removeCallbacks(this.e);
    }

    private static void a(aw awVar) {
        if (j != null) {
            j.c();
        }
        j = awVar;
        if (j != null) {
            j.b();
        }
    }

    private void b() {
        this.a.postDelayed(this.d, (long) ViewConfiguration.getLongPressTimeout());
    }

    private void c() {
        this.a.removeCallbacks(this.d);
    }

    private boolean a(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.f) <= this.c && Math.abs(y - this.g) <= this.c) {
            return false;
        }
        this.f = x;
        this.g = y;
        return true;
    }

    private void d() {
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
    }
}
