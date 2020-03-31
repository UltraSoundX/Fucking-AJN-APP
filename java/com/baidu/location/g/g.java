package com.baidu.location.g;

class g extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;
    final /* synthetic */ e c;

    g(e eVar, String str, boolean z) {
        this.c = eVar;
        this.a = str;
        this.b = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:111:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0205 A[SYNTHETIC, Splitter:B:113:0x0205] */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x020a A[SYNTHETIC, Splitter:B:116:0x020a] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x020f A[SYNTHETIC, Splitter:B:119:0x020f] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x022e A[LOOP:0: B:1:0x001b->B:128:0x022e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0071 A[SYNTHETIC, Splitter:B:14:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0082 A[EDGE_INSN: B:162:0x0082->B:24:0x0082 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0076 A[SYNTHETIC, Splitter:B:17:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007b A[SYNTHETIC, Splitter:B:20:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01c4  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01c9 A[SYNTHETIC, Splitter:B:93:0x01c9] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01ce A[SYNTHETIC, Splitter:B:96:0x01ce] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01d3 A[SYNTHETIC, Splitter:B:99:0x01d3] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r14 = this;
            r7 = 1
            r2 = 0
            r6 = 0
            com.baidu.location.g.e r0 = r14.c
            java.lang.String r1 = com.baidu.location.g.j.d()
            r0.h = r1
            com.baidu.location.g.e r0 = r14.c
            r0.b()
            com.baidu.location.g.e r0 = r14.c
            r0.a()
            com.baidu.location.g.e r0 = r14.c
            int r0 = r0.i
            r3 = r2
            r5 = r0
        L_0x001b:
            if (r5 <= 0) goto L_0x0082
            java.net.URL r4 = new java.net.URL     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            com.baidu.location.g.e r0 = r14.c     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.lang.String r0 = r0.h     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            r4.<init>(r0)     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.lang.StringBuffer r8 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            r8.<init>()     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            com.baidu.location.g.e r0 = r14.c     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.k     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.util.Set r0 = r0.entrySet()     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.util.Iterator r9 = r0.iterator()     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
        L_0x0037:
            boolean r0 = r9.hasNext()     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            if (r0 == 0) goto L_0x0094
            java.lang.Object r0 = r9.next()     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.lang.Object r1 = r0.getKey()     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            r8.append(r1)     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.lang.String r1 = "="
            r8.append(r1)     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.lang.Object r0 = r0.getValue()     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            r8.append(r0)     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.lang.String r0 = "&"
            r8.append(r0)     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            goto L_0x0037
        L_0x005e:
            r0 = move-exception
            r0 = r2
            r1 = r2
            r4 = r3
            r3 = r2
        L_0x0063:
            java.lang.String r8 = com.baidu.location.g.a.a     // Catch:{ all -> 0x0254 }
            java.lang.String r9 = "NetworkCommunicationException!"
            android.util.Log.d(r8, r9)     // Catch:{ all -> 0x0254 }
            if (r4 == 0) goto L_0x006f
            r4.disconnect()
        L_0x006f:
            if (r0 == 0) goto L_0x0074
            r0.close()     // Catch:{ Exception -> 0x0197 }
        L_0x0074:
            if (r3 == 0) goto L_0x0079
            r3.close()     // Catch:{ Exception -> 0x01a1 }
        L_0x0079:
            if (r1 == 0) goto L_0x007e
            r1.close()     // Catch:{ Exception -> 0x01ab }
        L_0x007e:
            r0 = r6
            r3 = r4
        L_0x0080:
            if (r0 == 0) goto L_0x022e
        L_0x0082:
            if (r5 > 0) goto L_0x0233
            int r0 = com.baidu.location.g.e.p
            int r0 = r0 + 1
            com.baidu.location.g.e.p = r0
            com.baidu.location.g.e r0 = r14.c
            r0.j = r2
            com.baidu.location.g.e r0 = r14.c
            r0.a(r6)
        L_0x0093:
            return
        L_0x0094:
            int r0 = r8.length()     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            if (r0 <= 0) goto L_0x00a3
            int r0 = r8.length()     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            int r0 = r0 + -1
            r8.deleteCharAt(r0)     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
        L_0x00a3:
            java.net.URLConnection r0 = r4.openConnection()     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Exception -> 0x005e, Error -> 0x0273, all -> 0x025d }
            java.lang.String r1 = "POST"
            r0.setRequestMethod(r1)     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            r1 = 1
            r0.setDoInput(r1)     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            r1 = 1
            r0.setDoOutput(r1)     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            r1 = 0
            r0.setUseCaches(r1)     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            int r1 = com.baidu.location.g.a.b     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            r0.setConnectTimeout(r1)     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            int r1 = com.baidu.location.g.a.b     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            r0.setReadTimeout(r1)     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            java.lang.String r1 = "Content-Type"
            java.lang.String r3 = "application/x-www-form-urlencoded; charset=utf-8"
            r0.setRequestProperty(r1, r3)     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            java.lang.String r1 = "Accept-Charset"
            java.lang.String r3 = "UTF-8"
            r0.setRequestProperty(r1, r3)     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            java.lang.String r1 = "Accept-Encoding"
            java.lang.String r3 = "gzip"
            r0.setRequestProperty(r1, r3)     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            java.lang.String r1 = r14.a     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            if (r1 != 0) goto L_0x00e8
            java.lang.String r1 = "Host"
            java.lang.String r3 = r14.a     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            r0.setRequestProperty(r1, r3)     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
        L_0x00e8:
            java.io.OutputStream r1 = r0.getOutputStream()     // Catch:{ Exception -> 0x027a, Error -> 0x01b7, all -> 0x01f8 }
            java.lang.String r3 = r8.toString()     // Catch:{ Exception -> 0x0281, Error -> 0x0262, all -> 0x0237 }
            byte[] r3 = r3.getBytes()     // Catch:{ Exception -> 0x0281, Error -> 0x0262, all -> 0x0237 }
            r1.write(r3)     // Catch:{ Exception -> 0x0281, Error -> 0x0262, all -> 0x0237 }
            r1.flush()     // Catch:{ Exception -> 0x0281, Error -> 0x0262, all -> 0x0237 }
            int r3 = r0.getResponseCode()     // Catch:{ Exception -> 0x0281, Error -> 0x0262, all -> 0x0237 }
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 != r4) goto L_0x0298
            java.io.InputStream r3 = r0.getInputStream()     // Catch:{ Exception -> 0x0281, Error -> 0x0262, all -> 0x0237 }
            java.lang.String r4 = r0.getContentEncoding()     // Catch:{ Exception -> 0x0288, Error -> 0x0267, all -> 0x023f }
            if (r4 == 0) goto L_0x0295
            java.lang.String r8 = "gzip"
            boolean r4 = r4.contains(r8)     // Catch:{ Exception -> 0x0288, Error -> 0x0267, all -> 0x023f }
            if (r4 == 0) goto L_0x0295
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch:{ Exception -> 0x0288, Error -> 0x0267, all -> 0x023f }
            java.io.BufferedInputStream r8 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0288, Error -> 0x0267, all -> 0x023f }
            r8.<init>(r3)     // Catch:{ Exception -> 0x0288, Error -> 0x0267, all -> 0x023f }
            r4.<init>(r8)     // Catch:{ Exception -> 0x0288, Error -> 0x0267, all -> 0x023f }
        L_0x011e:
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x028e, Error -> 0x026c, all -> 0x0247 }
            r3.<init>()     // Catch:{ Exception -> 0x028e, Error -> 0x026c, all -> 0x0247 }
            r8 = 1024(0x400, float:1.435E-42)
            byte[] r8 = new byte[r8]     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
        L_0x0127:
            int r9 = r4.read(r8)     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
            r10 = -1
            if (r9 == r10) goto L_0x013b
            r10 = 0
            r3.write(r8, r10, r9)     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
            goto L_0x0127
        L_0x0133:
            r8 = move-exception
            r12 = r1
            r1 = r3
            r3 = r4
            r4 = r0
            r0 = r12
            goto L_0x0063
        L_0x013b:
            com.baidu.location.g.e r8 = r14.c     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
            java.lang.String r9 = new java.lang.String     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
            byte[] r10 = r3.toByteArray()     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
            java.lang.String r11 = "utf-8"
            r9.<init>(r10, r11)     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
            r8.j = r9     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
            boolean r8 = r14.b     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
            if (r8 == 0) goto L_0x0156
            com.baidu.location.g.e r8 = r14.c     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
            byte[] r9 = r3.toByteArray()     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
            r8.m = r9     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
        L_0x0156:
            com.baidu.location.g.e r8 = r14.c     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
            r9 = 1
            r8.a(r9)     // Catch:{ Exception -> 0x0133, Error -> 0x0270 }
            r8 = r4
            r4 = r3
            r3 = r7
        L_0x015f:
            if (r0 == 0) goto L_0x0164
            r0.disconnect()
        L_0x0164:
            if (r1 == 0) goto L_0x0169
            r1.close()     // Catch:{ Exception -> 0x0178 }
        L_0x0169:
            if (r8 == 0) goto L_0x016e
            r8.close()     // Catch:{ Exception -> 0x0181 }
        L_0x016e:
            if (r4 == 0) goto L_0x0173
            r4.close()     // Catch:{ Exception -> 0x018a }
        L_0x0173:
            r12 = r3
            r3 = r0
            r0 = r12
            goto L_0x0080
        L_0x0178:
            r1 = move-exception
            java.lang.String r1 = com.baidu.location.g.a.a
            java.lang.String r9 = "close os IOException!"
            android.util.Log.d(r1, r9)
            goto L_0x0169
        L_0x0181:
            r1 = move-exception
            java.lang.String r1 = com.baidu.location.g.a.a
            java.lang.String r8 = "close is IOException!"
            android.util.Log.d(r1, r8)
            goto L_0x016e
        L_0x018a:
            r1 = move-exception
            java.lang.String r1 = com.baidu.location.g.a.a
            java.lang.String r4 = "close baos IOException!"
            android.util.Log.d(r1, r4)
            r12 = r3
            r3 = r0
            r0 = r12
            goto L_0x0080
        L_0x0197:
            r0 = move-exception
            java.lang.String r0 = com.baidu.location.g.a.a
            java.lang.String r8 = "close os IOException!"
            android.util.Log.d(r0, r8)
            goto L_0x0074
        L_0x01a1:
            r0 = move-exception
            java.lang.String r0 = com.baidu.location.g.a.a
            java.lang.String r3 = "close is IOException!"
            android.util.Log.d(r0, r3)
            goto L_0x0079
        L_0x01ab:
            r0 = move-exception
            java.lang.String r0 = com.baidu.location.g.a.a
            java.lang.String r1 = "close baos IOException!"
            android.util.Log.d(r0, r1)
            r0 = r6
            r3 = r4
            goto L_0x0080
        L_0x01b7:
            r1 = move-exception
            r1 = r2
            r3 = r2
            r4 = r2
        L_0x01bb:
            java.lang.String r8 = com.baidu.location.g.a.a     // Catch:{ all -> 0x024e }
            java.lang.String r9 = "NetworkCommunicationError!"
            android.util.Log.d(r8, r9)     // Catch:{ all -> 0x024e }
            if (r0 == 0) goto L_0x01c7
            r0.disconnect()
        L_0x01c7:
            if (r1 == 0) goto L_0x01cc
            r1.close()     // Catch:{ Exception -> 0x01da }
        L_0x01cc:
            if (r4 == 0) goto L_0x01d1
            r4.close()     // Catch:{ Exception -> 0x01e3 }
        L_0x01d1:
            if (r3 == 0) goto L_0x01d6
            r3.close()     // Catch:{ Exception -> 0x01ec }
        L_0x01d6:
            r3 = r0
            r0 = r6
            goto L_0x0080
        L_0x01da:
            r1 = move-exception
            java.lang.String r1 = com.baidu.location.g.a.a
            java.lang.String r8 = "close os IOException!"
            android.util.Log.d(r1, r8)
            goto L_0x01cc
        L_0x01e3:
            r1 = move-exception
            java.lang.String r1 = com.baidu.location.g.a.a
            java.lang.String r4 = "close is IOException!"
            android.util.Log.d(r1, r4)
            goto L_0x01d1
        L_0x01ec:
            r1 = move-exception
            java.lang.String r1 = com.baidu.location.g.a.a
            java.lang.String r3 = "close baos IOException!"
            android.util.Log.d(r1, r3)
            r3 = r0
            r0 = r6
            goto L_0x0080
        L_0x01f8:
            r1 = move-exception
            r3 = r2
            r4 = r2
            r12 = r1
            r1 = r0
            r0 = r12
        L_0x01fe:
            if (r1 == 0) goto L_0x0203
            r1.disconnect()
        L_0x0203:
            if (r2 == 0) goto L_0x0208
            r2.close()     // Catch:{ Exception -> 0x0213 }
        L_0x0208:
            if (r4 == 0) goto L_0x020d
            r4.close()     // Catch:{ Exception -> 0x021c }
        L_0x020d:
            if (r3 == 0) goto L_0x0212
            r3.close()     // Catch:{ Exception -> 0x0225 }
        L_0x0212:
            throw r0
        L_0x0213:
            r1 = move-exception
            java.lang.String r1 = com.baidu.location.g.a.a
            java.lang.String r2 = "close os IOException!"
            android.util.Log.d(r1, r2)
            goto L_0x0208
        L_0x021c:
            r1 = move-exception
            java.lang.String r1 = com.baidu.location.g.a.a
            java.lang.String r2 = "close is IOException!"
            android.util.Log.d(r1, r2)
            goto L_0x020d
        L_0x0225:
            r1 = move-exception
            java.lang.String r1 = com.baidu.location.g.a.a
            java.lang.String r2 = "close baos IOException!"
            android.util.Log.d(r1, r2)
            goto L_0x0212
        L_0x022e:
            int r0 = r5 + -1
            r5 = r0
            goto L_0x001b
        L_0x0233:
            com.baidu.location.g.e.p = r6
            goto L_0x0093
        L_0x0237:
            r3 = move-exception
            r4 = r2
            r12 = r1
            r1 = r0
            r0 = r3
            r3 = r2
            r2 = r12
            goto L_0x01fe
        L_0x023f:
            r4 = move-exception
            r12 = r4
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r12
            goto L_0x01fe
        L_0x0247:
            r3 = move-exception
            r12 = r3
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r12
            goto L_0x01fe
        L_0x024e:
            r2 = move-exception
            r12 = r2
            r2 = r1
            r1 = r0
            r0 = r12
            goto L_0x01fe
        L_0x0254:
            r2 = move-exception
            r12 = r2
            r2 = r0
            r0 = r12
            r13 = r1
            r1 = r4
            r4 = r3
            r3 = r13
            goto L_0x01fe
        L_0x025d:
            r0 = move-exception
            r4 = r2
            r1 = r3
            r3 = r2
            goto L_0x01fe
        L_0x0262:
            r3 = move-exception
            r3 = r2
            r4 = r2
            goto L_0x01bb
        L_0x0267:
            r4 = move-exception
            r4 = r3
            r3 = r2
            goto L_0x01bb
        L_0x026c:
            r3 = move-exception
            r3 = r2
            goto L_0x01bb
        L_0x0270:
            r8 = move-exception
            goto L_0x01bb
        L_0x0273:
            r0 = move-exception
            r1 = r2
            r4 = r2
            r0 = r3
            r3 = r2
            goto L_0x01bb
        L_0x027a:
            r1 = move-exception
            r1 = r2
            r3 = r2
            r4 = r0
            r0 = r2
            goto L_0x0063
        L_0x0281:
            r3 = move-exception
            r3 = r2
            r4 = r0
            r0 = r1
            r1 = r2
            goto L_0x0063
        L_0x0288:
            r4 = move-exception
            r4 = r0
            r0 = r1
            r1 = r2
            goto L_0x0063
        L_0x028e:
            r3 = move-exception
            r3 = r4
            r4 = r0
            r0 = r1
            r1 = r2
            goto L_0x0063
        L_0x0295:
            r4 = r3
            goto L_0x011e
        L_0x0298:
            r3 = r6
            r4 = r2
            r8 = r2
            goto L_0x015f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.g.g.run():void");
    }
}
