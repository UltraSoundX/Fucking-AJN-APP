package com.common.busi;

import java.util.ArrayList;

@QVMProtect
public final class a {
    public ArrayList<e> a = null;
    private String b = null;

    /* JADX WARNING: Removed duplicated region for block: B:157:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007b A[SYNTHETIC, Splitter:B:26:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b8 A[SYNTHETIC, Splitter:B:42:0x00b8] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public a(org.json.JSONObject r9) {
        /*
            r8 = this;
            r3 = 0
            r1 = 1
            r8.<init>()
            r8.a = r3
            r8.b = r3
            java.lang.String r0 = "rc"
            java.lang.String r0 = r9.optString(r0)
            r8.b = r0
            java.lang.String r0 = r8.b
            if (r0 != 0) goto L_0x0016
        L_0x0015:
            return
        L_0x0016:
            java.lang.String r0 = r8.b
            java.lang.String r2 = "0000"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0015
            java.lang.String r0 = r8.b
            java.lang.String r2 = "9000"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0015
            java.lang.String r0 = r8.b
            java.lang.String r2 = "1000"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0015
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r8.a = r0
            java.lang.String r0 = "wk"
            java.lang.String r0 = r9.optString(r0)
            java.io.StringReader r4 = new java.io.StringReader     // Catch:{ IOException -> 0x024a, all -> 0x0241 }
            r4.<init>(r0)     // Catch:{ IOException -> 0x024a, all -> 0x0241 }
            android.util.JsonReader r2 = new android.util.JsonReader     // Catch:{ IOException -> 0x024f, all -> 0x0246 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x024f, all -> 0x0246 }
            r2.beginArray()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
        L_0x004e:
            boolean r0 = r2.hasNext()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r0 == 0) goto L_0x022e
            r0 = 0
            com.common.busi.e r3 = new com.common.busi.e     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r3.<init>()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r2.beginObject()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
        L_0x005d:
            boolean r5 = r2.hasNext()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r5 == 0) goto L_0x01df
            java.lang.String r5 = r2.nextName()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r6 = "t"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x0084
            int r5 = r2.nextInt()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r3.a = r5     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x005d
        L_0x0076:
            r0 = move-exception
            r0 = r2
            r1 = r4
        L_0x0079:
            if (r0 == 0) goto L_0x007e
            r0.close()     // Catch:{ IOException -> 0x023b }
        L_0x007e:
            if (r1 == 0) goto L_0x0015
            r1.close()
            goto L_0x0015
        L_0x0084:
            java.lang.String r6 = "i"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x01c6
            r2.beginObject()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
        L_0x008f:
            boolean r5 = r2.hasNext()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r5 == 0) goto L_0x0113
            java.lang.String r5 = r2.nextName()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r6 = "a"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x00c1
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.<init>()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r6 = r2.nextString()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.add(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r6 = r3.b     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r7 = "a"
            r6.put(r7, r5)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x008f
        L_0x00b5:
            r0 = move-exception
        L_0x00b6:
            if (r2 == 0) goto L_0x00bb
            r2.close()     // Catch:{ IOException -> 0x023e }
        L_0x00bb:
            if (r4 == 0) goto L_0x00c0
            r4.close()
        L_0x00c0:
            throw r0
        L_0x00c1:
            java.lang.String r6 = "d"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x00dd
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.<init>()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r6 = r2.nextString()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.add(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r6 = r3.b     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r7 = "d"
            r6.put(r7, r5)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x008f
        L_0x00dd:
            java.lang.String r6 = "t"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x00f9
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.<init>()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r6 = r2.nextString()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.add(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r6 = r3.b     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r7 = "t"
            r6.put(r7, r5)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x008f
        L_0x00f9:
            java.lang.String r6 = "pk"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x0124
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.<init>()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r6 = r2.nextString()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r7 = ""
            boolean r7 = r6.equals(r7)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r7 == 0) goto L_0x0118
            r0 = r1
        L_0x0113:
            r2.endObject()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x005d
        L_0x0118:
            r5.add(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r6 = r3.b     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r7 = "pk"
            r6.put(r7, r5)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x008f
        L_0x0124:
            java.lang.String r6 = "cp"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x014e
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.<init>()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r2.beginArray()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
        L_0x0134:
            boolean r6 = r2.hasNext()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x0142
            java.lang.String r6 = r2.nextString()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.add(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x0134
        L_0x0142:
            r2.endArray()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r6 = r3.b     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r7 = "cp"
            r6.put(r7, r5)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x008f
        L_0x014e:
            java.lang.String r6 = "f"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x016f
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.<init>()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            int r6 = r2.nextInt()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.add(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r6 = r3.b     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r7 = "f"
            r6.put(r7, r5)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x008f
        L_0x016f:
            java.lang.String r6 = "ct"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x0199
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.<init>()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r2.beginArray()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
        L_0x017f:
            boolean r6 = r2.hasNext()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x018d
            java.lang.String r6 = r2.nextString()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.add(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x017f
        L_0x018d:
            r2.endArray()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r6 = r3.b     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r7 = "ct"
            r6.put(r7, r5)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x008f
        L_0x0199:
            java.lang.String r6 = "et"
            boolean r5 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r5 == 0) goto L_0x01c3
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.<init>()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r2.beginArray()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
        L_0x01a9:
            boolean r6 = r2.hasNext()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x01b7
            java.lang.String r6 = r2.nextString()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r5.add(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x01a9
        L_0x01b7:
            r2.endArray()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r6 = r3.b     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r7 = "et"
            r6.put(r7, r5)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x008f
        L_0x01c3:
            r0 = r1
            goto L_0x008f
        L_0x01c6:
            java.lang.String r6 = "dn"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x01eb
            java.lang.String r5 = r2.nextString()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r3.c = r5     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r5 = r3.c     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            java.lang.String r6 = ""
            boolean r5 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r5 == 0) goto L_0x005d
            r0 = r1
        L_0x01df:
            r2.endObject()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r0 != 0) goto L_0x004e
            java.util.ArrayList<com.common.busi.e> r0 = r8.a     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r0.add(r3)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x004e
        L_0x01eb:
            java.lang.String r6 = "s"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x01fb
            int r5 = r2.nextInt()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r3.d = r5     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x005d
        L_0x01fb:
            java.lang.String r6 = "jg"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x020b
            int r5 = r2.nextInt()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r3.e = r5     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x005d
        L_0x020b:
            java.lang.String r6 = "sig"
            boolean r6 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r6 == 0) goto L_0x021b
            java.lang.String r5 = r2.nextString()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r3.f = r5     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x005d
        L_0x021b:
            java.lang.String r6 = "vl"
            boolean r5 = r5.equals(r6)     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            if (r5 == 0) goto L_0x022b
            java.lang.String r5 = r2.nextString()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r3.g = r5     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            goto L_0x005d
        L_0x022b:
            r0 = r1
            goto L_0x005d
        L_0x022e:
            r2.endArray()     // Catch:{ IOException -> 0x0076, all -> 0x00b5 }
            r2.close()     // Catch:{ IOException -> 0x0239 }
        L_0x0234:
            r4.close()
            goto L_0x0015
        L_0x0239:
            r0 = move-exception
            goto L_0x0234
        L_0x023b:
            r0 = move-exception
            goto L_0x007e
        L_0x023e:
            r1 = move-exception
            goto L_0x00bb
        L_0x0241:
            r0 = move-exception
            r2 = r3
            r4 = r3
            goto L_0x00b6
        L_0x0246:
            r0 = move-exception
            r2 = r3
            goto L_0x00b6
        L_0x024a:
            r0 = move-exception
            r0 = r3
            r1 = r3
            goto L_0x0079
        L_0x024f:
            r0 = move-exception
            r0 = r3
            r1 = r4
            goto L_0x0079
        */
        throw new UnsupportedOperationException("Method not decompiled: com.common.busi.a.<init>(org.json.JSONObject):void");
    }
}
