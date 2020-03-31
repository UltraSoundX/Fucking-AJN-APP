package com.e23.ajn.views;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.e23.ajn.R;
import com.stub.StubApp;

/* compiled from: MyDialog */
public class k {
    public static i a;
    public static p b;
    public static Dialog c;
    private static Toast d;

    public static void a(Context context, String str) {
        try {
            if (d != null) {
                d.cancel();
                d = null;
            }
            d = Toast.makeText(StubApp.getOrigApplicationContext(context.getApplicationContext()), str, 0);
            d.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context) {
        a();
        if (a == null) {
            a = new i(context, R.style.MyDialog);
        }
        if (a != null && !a.isShowing()) {
            try {
                a.setCancelable(false);
                a.show();
            } catch (Exception e) {
            }
        }
    }

    public static void a() {
        if (a != null) {
            try {
                a.dismiss();
                a = null;
            } catch (Exception e) {
            } finally {
                a = null;
            }
        }
    }

    public static void b(Context context, String str) {
        a();
        if (b == null) {
            b = new p(context, R.style.MyDialog, str);
        }
        if (b != null && !b.isShowing()) {
            try {
                b.setCancelable(false);
                b.show();
            } catch (Exception e) {
            }
        }
    }

    public static void b() {
        if (b != null) {
            try {
                b.dismiss();
                b = null;
            } catch (Exception e) {
            } finally {
                b = null;
            }
        }
    }

    public static void c(Context context, String str) {
        c();
        if (c == null) {
            c = new Dialog(context, R.style.dm_alert_dialog);
            c.setContentView(R.layout.tip_dialog);
        }
        ((TextView) c.findViewById(R.id.mess)).setText(str);
        ((TextView) c.findViewById(R.id.dialog_title)).getPaint().setFakeBoldText(true);
        ((Button) c.findViewById(R.id.dia_bt_login)).getPaint().setFakeBoldText(true);
        c.findViewById(R.id.dia_bt_login).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                k.c.dismiss();
            }
        });
        c.setCancelable(true);
        c.setCanceledOnTouchOutside(true);
        c.show();
    }

    public static void c() {
        if (c != null) {
            try {
                c.dismiss();
                c = null;
            } catch (Exception e) {
            } finally {
                c = null;
            }
        }
    }
}
