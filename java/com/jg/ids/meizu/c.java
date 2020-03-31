package com.jg.ids.meizu;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import com.jg.ids.a;

public final class c extends a implements b {
    public c(Context context) {
        super(context, "meizu_thread");
        a.a().a((b) this);
    }

    /* access modifiers changed from: protected */
    public final void a(Message message) {
        if (message != null) {
            try {
                int i = message.getData().getInt("type", -1);
                String str = "";
                switch (i) {
                    case 0:
                        str = a.a().c(this.a);
                        break;
                    case 1:
                        str = a.a().d(this.a);
                        break;
                    case 2:
                        str = a.a().b(this.a);
                        break;
                }
                a(i, str);
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void b() {
        b(2);
        b(0);
        b(1);
    }

    private void b(int i) {
        try {
            Message a = a();
            a.what = 0;
            Bundle bundle = new Bundle();
            bundle.putInt("type", i);
            a.setData(bundle);
            b(a);
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: protected */
    public final boolean d(Context context) {
        return a.a().a(context);
    }

    public final void a(int i) {
        switch (i) {
            case 0:
                b(0);
                return;
            case 1:
                b(1);
                return;
            case 2:
                b(2);
                return;
            default:
                return;
        }
    }
}
