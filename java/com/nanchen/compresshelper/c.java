package com.nanchen.compresshelper;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/* compiled from: FileUtil */
public class c {
    static String[] a(String str) {
        String str2 = "";
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf != -1) {
            String substring = str.substring(0, lastIndexOf);
            str2 = str.substring(lastIndexOf);
            str = substring;
        }
        return new String[]{str, str2};
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String a(android.content.Context r6, android.net.Uri r7) {
        /*
            r2 = 0
            java.lang.String r0 = r7.getScheme()
            java.lang.String r1 = "content"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0059
            android.content.ContentResolver r0 = r6.getContentResolver()
            r1 = r7
            r3 = r2
            r4 = r2
            r5 = r2
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)
            if (r1 == 0) goto L_0x002b
            boolean r0 = r1.moveToFirst()     // Catch:{ Exception -> 0x0047 }
            if (r0 == 0) goto L_0x002b
            java.lang.String r0 = "_display_name"
            int r0 = r1.getColumnIndex(r0)     // Catch:{ Exception -> 0x0047 }
            java.lang.String r2 = r1.getString(r0)     // Catch:{ Exception -> 0x0047 }
        L_0x002b:
            if (r1 == 0) goto L_0x005b
            r1.close()
            r0 = r2
        L_0x0031:
            if (r0 != 0) goto L_0x0046
            java.lang.String r0 = r7.getPath()
            java.lang.String r1 = java.io.File.separator
            int r1 = r0.lastIndexOf(r1)
            r2 = -1
            if (r1 == r2) goto L_0x0046
            int r1 = r1 + 1
            java.lang.String r0 = r0.substring(r1)
        L_0x0046:
            return r0
        L_0x0047:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0059
            r1.close()
            r0 = r2
            goto L_0x0031
        L_0x0052:
            r0 = move-exception
            if (r1 == 0) goto L_0x0058
            r1.close()
        L_0x0058:
            throw r0
        L_0x0059:
            r0 = r2
            goto L_0x0031
        L_0x005b:
            r0 = r2
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nanchen.compresshelper.c.a(android.content.Context, android.net.Uri):java.lang.String");
    }

    static String b(Context context, Uri uri) {
        Cursor query = context.getContentResolver().query(uri, null, null, null, null);
        if (query == null) {
            return uri.getPath();
        }
        query.moveToFirst();
        String string = query.getString(query.getColumnIndex("_data"));
        query.close();
        return string;
    }
}
