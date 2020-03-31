package com.jg.ids.g;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import com.jg.ids.IdsHelper;

public final class a extends com.jg.ids.a {
    private String b;
    private ContentObserver c = null;
    private ContentObserver d = null;
    private ContentObserver e = null;

    public a(Context context, String str) {
        super(context, "vivo_thread");
        this.b = str;
        try {
            this.c = new b(this, null, str, 0);
            this.d = new b(this, null, str, 1);
            this.e = new b(this, null, str, 2);
            context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), true, this.c);
            context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/VAID_" + str), true, this.d);
            context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/AAID_" + str), true, this.e);
        } catch (Throwable th) {
        }
    }

    public final String a(Context context) {
        String a = super.a(context);
        if (TextUtils.isEmpty(a)) {
            b(1, this.b);
        }
        return a;
    }

    public final String b(Context context) {
        String b2 = super.b(context);
        if (TextUtils.isEmpty(b2)) {
            b(0, this.b);
        }
        return b2;
    }

    public final String c(Context context) {
        String c2 = super.c(context);
        if (TextUtils.isEmpty(c2)) {
            b(2, this.b);
        }
        return c2;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        b(1, this.b);
        b(2, this.b);
        b(0, this.b);
    }

    /* access modifiers changed from: protected */
    public final void a(Message message) {
        if (message != null) {
            try {
                if (message.what == 0) {
                    int i = message.getData().getInt("type", -1);
                    a(i, a(IdsHelper.CONTEXT, i, message.getData().getString("appid", "")));
                }
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(int i, String str) {
        try {
            Message a = a();
            a.what = 0;
            Bundle bundle = new Bundle();
            bundle.putInt("type", i);
            bundle.putString("appid", str);
            a.setData(bundle);
            b(a);
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(android.content.Context r8, int r9, java.lang.String r10) {
        /*
            r7 = 0
            java.lang.String r6 = ""
            switch(r9) {
                case 0: goto L_0x000c;
                case 1: goto L_0x0013;
                case 2: goto L_0x0027;
                default: goto L_0x0006;
            }
        L_0x0006:
            r1 = r7
        L_0x0007:
            if (r1 != 0) goto L_0x003b
            java.lang.String r0 = ""
        L_0x000b:
            return r0
        L_0x000c:
            java.lang.String r0 = "content://com.vivo.vms.IdProvider/IdentifierId/OAID"
            android.net.Uri r1 = android.net.Uri.parse(r0)     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            goto L_0x0007
        L_0x0013:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            java.lang.String r1 = "content://com.vivo.vms.IdProvider/IdentifierId/VAID_"
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            java.lang.StringBuilder r0 = r0.append(r10)     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            android.net.Uri r1 = android.net.Uri.parse(r0)     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            goto L_0x0007
        L_0x0027:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            java.lang.String r1 = "content://com.vivo.vms.IdProvider/IdentifierId/AAID_"
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            java.lang.StringBuilder r0 = r0.append(r10)     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            android.net.Uri r1 = android.net.Uri.parse(r0)     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            goto L_0x0007
        L_0x003b:
            android.content.ContentResolver r0 = r8.getContentResolver()     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0060, all -> 0x0069 }
            if (r1 == 0) goto L_0x0078
            boolean r0 = r1.moveToNext()     // Catch:{ Throwable -> 0x0073, all -> 0x0070 }
            if (r0 == 0) goto L_0x0078
            java.lang.String r0 = "value"
            int r0 = r1.getColumnIndex(r0)     // Catch:{ Throwable -> 0x0073, all -> 0x0070 }
            java.lang.String r6 = r1.getString(r0)     // Catch:{ Throwable -> 0x0073, all -> 0x0070 }
            r0 = r6
        L_0x005a:
            if (r1 == 0) goto L_0x000b
            r1.close()
            goto L_0x000b
        L_0x0060:
            r0 = move-exception
            r0 = r7
        L_0x0062:
            if (r0 == 0) goto L_0x0076
            r0.close()
            r0 = r6
            goto L_0x000b
        L_0x0069:
            r0 = move-exception
        L_0x006a:
            if (r7 == 0) goto L_0x006f
            r7.close()
        L_0x006f:
            throw r0
        L_0x0070:
            r0 = move-exception
            r7 = r1
            goto L_0x006a
        L_0x0073:
            r0 = move-exception
            r0 = r1
            goto L_0x0062
        L_0x0076:
            r0 = r6
            goto L_0x000b
        L_0x0078:
            r0 = r6
            goto L_0x005a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jg.ids.g.a.a(android.content.Context, int, java.lang.String):java.lang.String");
    }
}
