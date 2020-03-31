package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.KeyEventDispatcher;
import android.support.v4.view.KeyEventDispatcher.Component;
import android.support.v7.appcompat.R;
import android.support.v7.view.b;
import android.support.v7.view.b.a;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/* compiled from: AppCompatDialog */
public class d extends Dialog implements b {
    private c a;
    private final Component b = new Component() {
        public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
            return d.this.a(keyEvent);
        }
    };

    public d(Context context, int i) {
        super(context, a(context, i));
        b().a((Bundle) null);
        b().j();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        b().i();
        super.onCreate(bundle);
        b().a(bundle);
    }

    public void setContentView(int i) {
        b().b(i);
    }

    public void setContentView(View view) {
        b().a(view);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        b().a(view, layoutParams);
    }

    public <T extends View> T findViewById(int i) {
        return b().a(i);
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        b().a(charSequence);
    }

    public void setTitle(int i) {
        super.setTitle(i);
        b().a((CharSequence) getContext().getString(i));
    }

    public void addContentView(View view, LayoutParams layoutParams) {
        b().b(view, layoutParams);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        b().d();
    }

    public boolean a(int i) {
        return b().c(i);
    }

    public void invalidateOptionsMenu() {
        b().f();
    }

    public c b() {
        if (this.a == null) {
            this.a = c.a((Dialog) this, (b) this);
        }
        return this.a;
    }

    private static int a(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public void onSupportActionModeStarted(b bVar) {
    }

    public void onSupportActionModeFinished(b bVar) {
    }

    public b onWindowStartingSupportActionMode(a aVar) {
        return null;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return KeyEventDispatcher.dispatchKeyEvent(this.b, getWindow().getDecorView(), this, keyEvent);
    }
}
