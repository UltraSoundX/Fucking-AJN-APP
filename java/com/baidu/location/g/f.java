package com.baidu.location.g;

class f extends Thread {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0089 A[SYNTHETIC, Splitter:B:24:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008e A[SYNTHETIC, Splitter:B:27:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0107 A[SYNTHETIC, Splitter:B:61:0x0107] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x010c A[SYNTHETIC, Splitter:B:64:0x010c] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x011a A[LOOP:0: B:1:0x001b->B:71:0x011a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0095 A[EDGE_INSN: B:89:0x0095->B:31:0x0095 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r13 = this;
            r2 = 0
            r6 = 1
            r5 = 0
            com.baidu.location.g.e r0 = r13.a
            java.lang.String r1 = com.baidu.location.g.j.d()
            r0.h = r1
            com.baidu.location.g.e r0 = r13.a
            r0.b()
            com.baidu.location.g.e r0 = r13.a
            r0.a()
            com.baidu.location.g.e r0 = r13.a
            int r0 = r0.i
            r1 = r2
            r4 = r0
        L_0x001b:
            if (r4 <= 0) goto L_0x0095
            java.net.URL r0 = new java.net.URL     // Catch:{ Exception -> 0x0144, all -> 0x0135 }
            com.baidu.location.g.e r3 = r13.a     // Catch:{ Exception -> 0x0144, all -> 0x0135 }
            java.lang.String r3 = r3.h     // Catch:{ Exception -> 0x0144, all -> 0x0135 }
            r0.<init>(r3)     // Catch:{ Exception -> 0x0144, all -> 0x0135 }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ Exception -> 0x0144, all -> 0x0135 }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Exception -> 0x0144, all -> 0x0135 }
            java.lang.String r1 = "GET"
            r0.setRequestMethod(r1)     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            r1 = 1
            r0.setDoInput(r1)     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            r1 = 1
            r0.setDoOutput(r1)     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            r1 = 0
            r0.setUseCaches(r1)     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            int r1 = com.baidu.location.g.a.b     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            r0.setConnectTimeout(r1)     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            int r1 = com.baidu.location.g.a.b     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            r0.setReadTimeout(r1)     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            java.lang.String r1 = "Content-Type"
            java.lang.String r3 = "application/x-www-form-urlencoded; charset=utf-8"
            r0.setRequestProperty(r1, r3)     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            java.lang.String r1 = "Accept-Charset"
            java.lang.String r3 = "UTF-8"
            r0.setRequestProperty(r1, r3)     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            int r1 = r0.getResponseCode()     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            r3 = 200(0xc8, float:2.8E-43)
            if (r1 != r3) goto L_0x00db
            java.io.InputStream r3 = r0.getInputStream()     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x013e, all -> 0x0122 }
            r1.<init>()     // Catch:{ Exception -> 0x013e, all -> 0x0122 }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
        L_0x006a:
            int r8 = r3.read(r7)     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
            r9 = -1
            if (r8 == r9) goto L_0x00a7
            r9 = 0
            r1.write(r7, r9, r8)     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
            goto L_0x006a
        L_0x0076:
            r7 = move-exception
            r11 = r1
            r1 = r3
            r3 = r0
            r0 = r11
        L_0x007b:
            java.lang.String r7 = com.baidu.location.g.a.a     // Catch:{ all -> 0x012d }
            java.lang.String r8 = "NetworkCommunicationException!"
            android.util.Log.d(r7, r8)     // Catch:{ all -> 0x012d }
            if (r3 == 0) goto L_0x0087
            r3.disconnect()
        L_0x0087:
            if (r1 == 0) goto L_0x008c
            r1.close()     // Catch:{ Exception -> 0x00ef }
        L_0x008c:
            if (r0 == 0) goto L_0x014a
            r0.close()     // Catch:{ Exception -> 0x00f4 }
            r0 = r5
            r1 = r3
        L_0x0093:
            if (r0 == 0) goto L_0x011a
        L_0x0095:
            if (r4 > 0) goto L_0x011f
            int r0 = com.baidu.location.g.e.p
            int r0 = r0 + 1
            com.baidu.location.g.e.p = r0
            com.baidu.location.g.e r0 = r13.a
            r0.j = r2
            com.baidu.location.g.e r0 = r13.a
            r0.a(r5)
        L_0x00a6:
            return
        L_0x00a7:
            r3.close()     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
            r1.close()     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
            com.baidu.location.g.e r7 = r13.a     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
            java.lang.String r8 = new java.lang.String     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
            byte[] r9 = r1.toByteArray()     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
            java.lang.String r10 = "utf-8"
            r8.<init>(r9, r10)     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
            r7.j = r8     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
            com.baidu.location.g.e r7 = r13.a     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
            r8 = 1
            r7.a(r8)     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
            r0.disconnect()     // Catch:{ Exception -> 0x0076, all -> 0x0127 }
            r7 = r3
            r3 = r1
            r1 = r6
        L_0x00c8:
            if (r0 == 0) goto L_0x00cd
            r0.disconnect()
        L_0x00cd:
            if (r7 == 0) goto L_0x00d2
            r7.close()     // Catch:{ Exception -> 0x00e2 }
        L_0x00d2:
            if (r3 == 0) goto L_0x014e
            r3.close()     // Catch:{ Exception -> 0x00e7 }
            r11 = r1
            r1 = r0
            r0 = r11
            goto L_0x0093
        L_0x00db:
            r0.disconnect()     // Catch:{ Exception -> 0x0138, all -> 0x00fb }
            r1 = r5
            r3 = r2
            r7 = r2
            goto L_0x00c8
        L_0x00e2:
            r7 = move-exception
            r7.printStackTrace()
            goto L_0x00d2
        L_0x00e7:
            r3 = move-exception
            r3.printStackTrace()
            r11 = r1
            r1 = r0
            r0 = r11
            goto L_0x0093
        L_0x00ef:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x008c
        L_0x00f4:
            r0 = move-exception
            r0.printStackTrace()
            r0 = r5
            r1 = r3
            goto L_0x0093
        L_0x00fb:
            r1 = move-exception
            r3 = r2
            r11 = r1
            r1 = r0
            r0 = r11
        L_0x0100:
            if (r1 == 0) goto L_0x0105
            r1.disconnect()
        L_0x0105:
            if (r3 == 0) goto L_0x010a
            r3.close()     // Catch:{ Exception -> 0x0110 }
        L_0x010a:
            if (r2 == 0) goto L_0x010f
            r2.close()     // Catch:{ Exception -> 0x0115 }
        L_0x010f:
            throw r0
        L_0x0110:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x010a
        L_0x0115:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x010f
        L_0x011a:
            int r0 = r4 + -1
            r4 = r0
            goto L_0x001b
        L_0x011f:
            com.baidu.location.g.e.p = r5
            goto L_0x00a6
        L_0x0122:
            r1 = move-exception
            r11 = r1
            r1 = r0
            r0 = r11
            goto L_0x0100
        L_0x0127:
            r2 = move-exception
            r11 = r2
            r2 = r1
            r1 = r0
            r0 = r11
            goto L_0x0100
        L_0x012d:
            r2 = move-exception
            r11 = r2
            r2 = r0
            r0 = r11
            r12 = r1
            r1 = r3
            r3 = r12
            goto L_0x0100
        L_0x0135:
            r0 = move-exception
            r3 = r2
            goto L_0x0100
        L_0x0138:
            r1 = move-exception
            r1 = r2
            r3 = r0
            r0 = r2
            goto L_0x007b
        L_0x013e:
            r1 = move-exception
            r1 = r3
            r3 = r0
            r0 = r2
            goto L_0x007b
        L_0x0144:
            r0 = move-exception
            r0 = r2
            r3 = r1
            r1 = r2
            goto L_0x007b
        L_0x014a:
            r0 = r5
            r1 = r3
            goto L_0x0093
        L_0x014e:
            r11 = r1
            r1 = r0
            r0 = r11
            goto L_0x0093
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.g.f.run():void");
    }
}
