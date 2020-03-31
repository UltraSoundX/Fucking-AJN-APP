package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.appcompat.R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;

/* compiled from: AlertDialog */
public class a extends d implements DialogInterface {
    final AlertController a = new AlertController(getContext(), this, getWindow());

    /* renamed from: android.support.v7.app.a$a reason: collision with other inner class name */
    /* compiled from: AlertDialog */
    public static class C0012a {
        private final android.support.v7.app.AlertController.a a;
        private final int b;

        public C0012a(Context context) {
            this(context, a.a(context, 0));
        }

        public C0012a(Context context, int i) {
            this.a = new android.support.v7.app.AlertController.a(new ContextThemeWrapper(context, a.a(context, i)));
            this.b = i;
        }

        public Context a() {
            return this.a.a;
        }

        public C0012a a(CharSequence charSequence) {
            this.a.f = charSequence;
            return this;
        }

        public C0012a a(View view) {
            this.a.g = view;
            return this;
        }

        public C0012a b(CharSequence charSequence) {
            this.a.h = charSequence;
            return this;
        }

        public C0012a a(Drawable drawable) {
            this.a.d = drawable;
            return this;
        }

        public C0012a a(int i, OnClickListener onClickListener) {
            this.a.i = this.a.a.getText(i);
            this.a.k = onClickListener;
            return this;
        }

        public C0012a a(CharSequence charSequence, OnClickListener onClickListener) {
            this.a.i = charSequence;
            this.a.k = onClickListener;
            return this;
        }

        public C0012a b(CharSequence charSequence, OnClickListener onClickListener) {
            this.a.l = charSequence;
            this.a.n = onClickListener;
            return this;
        }

        public C0012a a(boolean z) {
            this.a.r = z;
            return this;
        }

        public C0012a a(OnKeyListener onKeyListener) {
            this.a.u = onKeyListener;
            return this;
        }

        public C0012a a(ListAdapter listAdapter, OnClickListener onClickListener) {
            this.a.w = listAdapter;
            this.a.x = onClickListener;
            return this;
        }

        public C0012a b(View view) {
            this.a.z = view;
            this.a.y = 0;
            this.a.E = false;
            return this;
        }

        public a b() {
            a aVar = new a(this.a.a, this.b);
            this.a.a(aVar.a);
            aVar.setCancelable(this.a.r);
            if (this.a.r) {
                aVar.setCanceledOnTouchOutside(true);
            }
            aVar.setOnCancelListener(this.a.s);
            aVar.setOnDismissListener(this.a.t);
            if (this.a.u != null) {
                aVar.setOnKeyListener(this.a.u);
            }
            return aVar;
        }

        public a c() {
            a b2 = b();
            b2.show();
            return b2;
        }
    }

    protected a(Context context, int i) {
        super(context, a(context, i));
    }

    static int a(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.a.a(charSequence);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a.a();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.a.a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.a.b(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }
}
