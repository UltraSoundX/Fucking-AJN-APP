package com.e23.ajn.ccb.d;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import com.e23.ajn.R;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: LoadingDialogUtils */
public class i {
    /* access modifiers changed from: private */
    public static final String a = i.class.getSimpleName();
    private static i b = null;
    /* access modifiers changed from: private */
    public static Queue<Context> e;
    private Dialog c = null;
    private Handler d = new Handler(Looper.getMainLooper());
    private Context f;
    private OnKeyListener g = new OnKeyListener() {
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i != 4) {
                return false;
            }
            i.this.e();
            return true;
        }
    };

    private i() {
    }

    public static synchronized i a() {
        i iVar;
        synchronized (i.class) {
            if (b == null) {
                b = new i();
                e = new LinkedBlockingQueue();
            }
            iVar = b;
        }
        return iVar;
    }

    /* access modifiers changed from: private */
    public void b(Context context) {
        e.offer(context);
        Log.d(a, String.format("=======================%s request add=====================", new Object[]{context.toString()}));
        g();
        this.c = new Builder(context, R.style.Ccb_Theme_Dialog).create();
        this.c.show();
        View inflate = LayoutInflater.from(context).inflate(R.layout.f194pltome, null);
        this.c.setOnKeyListener(this.g);
        this.c.setCancelable(false);
        this.c.setCanceledOnTouchOutside(true);
        this.c.getWindow().setLayout(-1, -2);
        LayoutParams layoutParams = new LayoutParams(new ViewGroup.LayoutParams(-1, -2));
        layoutParams.gravity = 1;
        this.c.getWindow().setContentView(inflate, layoutParams);
        Log.d(a, "======================= show loading dialog=====================");
    }

    public synchronized void a(final Context context) {
        if (context != null) {
            if (this.f != context) {
                e();
            }
            this.f = context;
            if (this.c == null || !this.c.isShowing()) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    public void run() {
                        i.this.b(context);
                    }
                });
            } else {
                Log.d(a, String.format("=======================%s has loading no handle=====================", new Object[]{context.toString()}));
                e.offer(context);
                Log.d(a, String.format("=======================%s request add=====================", new Object[]{context.toString()}));
                g();
            }
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        this.d.removeCallbacksAndMessages(null);
        f();
        e.clear();
    }

    public synchronized boolean b() {
        boolean z = false;
        synchronized (this) {
            if (e.size() > 0) {
                Context context = (Context) e.poll();
                Log.d(a, String.format("=======================%s request remove=====================", new Object[]{context.toString()}));
                z = true;
            }
            this.d.postDelayed(new Runnable() {
                public void run() {
                    Log.d(i.a, "=======================current queue size=====================" + i.e.size());
                    i.this.g();
                    if (i.e.size() <= 0) {
                        i.this.f();
                    }
                }
            }, 100);
        }
        return z;
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.c != null) {
            this.c.dismiss();
            this.c.cancel();
            this.c = null;
            Log.d(a, "======================= dismiss loading dialog=====================");
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        for (Context obj : e) {
            Log.d(a, "======mQueue======" + obj.toString());
        }
    }
}
