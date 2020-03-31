package com.baidu.location.d;

class e extends Thread {
    final /* synthetic */ C0028c a;

    e(C0028c cVar) {
        this.a = cVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x0339 A[Catch:{ Exception -> 0x024f, all -> 0x02c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0358 A[Catch:{ Exception -> 0x024f, all -> 0x02c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0377 A[Catch:{ Exception -> 0x024f, all -> 0x02c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x03c3 A[Catch:{ Exception -> 0x024f, all -> 0x02c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x04dc A[Catch:{ Exception -> 0x024f, all -> 0x02c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x04fb A[Catch:{ Exception -> 0x024f, all -> 0x02c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x051a A[Catch:{ Exception -> 0x024f, all -> 0x02c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0150 A[Catch:{ Exception -> 0x024f, all -> 0x02c3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r21 = this;
            super.run()
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this
            android.database.sqlite.SQLiteDatabase r2 = r2.h
            if (r2 == 0) goto L_0x003b
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this
            android.database.sqlite.SQLiteDatabase r2 = r2.i
            if (r2 == 0) goto L_0x003b
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this
            android.database.sqlite.SQLiteDatabase r2 = r2.h
            boolean r2 = r2.isOpen()
            if (r2 == 0) goto L_0x003b
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this
            android.database.sqlite.SQLiteDatabase r2 = r2.i
            boolean r2 = r2.isOpen()
            if (r2 != 0) goto L_0x0044
        L_0x003b:
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a
            r3 = 0
            r2.f = r3
        L_0x0043:
            return
        L_0x0044:
            r5 = 0
            r4 = 0
            r2 = 0
            r0 = r21
            com.baidu.location.d.c$c r3 = r0.a     // Catch:{ Exception -> 0x023e }
            java.lang.String r3 = r3.j     // Catch:{ Exception -> 0x023e }
            if (r3 == 0) goto L_0x0692
            r0 = r21
            com.baidu.location.d.c$c r3 = r0.a     // Catch:{ Exception -> 0x023e }
            java.lang.String r6 = r3.j     // Catch:{ Exception -> 0x023e }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x023e }
            r3.<init>(r6)     // Catch:{ Exception -> 0x023e }
            java.lang.String r4 = "model"
            boolean r4 = r3.has(r4)     // Catch:{ Exception -> 0x067e }
            if (r4 == 0) goto L_0x068f
            java.lang.String r4 = "model"
            org.json.JSONObject r4 = r3.getJSONObject(r4)     // Catch:{ Exception -> 0x067e }
        L_0x0068:
            java.lang.String r5 = "rgc"
            boolean r5 = r3.has(r5)     // Catch:{ Exception -> 0x0686 }
            if (r5 == 0) goto L_0x0076
            java.lang.String r5 = "rgc"
            org.json.JSONObject r2 = r3.getJSONObject(r5)     // Catch:{ Exception -> 0x0686 }
        L_0x0076:
            r9 = r4
        L_0x0077:
            r0 = r21
            com.baidu.location.d.c$c r4 = r0.a     // Catch:{ Exception -> 0x067b }
            com.baidu.location.d.c r4 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x067b }
            android.database.sqlite.SQLiteDatabase r4 = r4.h     // Catch:{ Exception -> 0x067b }
            r4.beginTransaction()     // Catch:{ Exception -> 0x067b }
            r0 = r21
            com.baidu.location.d.c$c r4 = r0.a     // Catch:{ Exception -> 0x067b }
            com.baidu.location.d.c r4 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x067b }
            android.database.sqlite.SQLiteDatabase r4 = r4.i     // Catch:{ Exception -> 0x067b }
            r4.beginTransaction()     // Catch:{ Exception -> 0x067b }
        L_0x0091:
            if (r2 == 0) goto L_0x00a4
            r0 = r21
            com.baidu.location.d.c$c r4 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r4 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.g r4 = r4.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.k r4 = r4.k()     // Catch:{ Exception -> 0x024f }
            r4.a(r2)     // Catch:{ Exception -> 0x024f }
        L_0x00a4:
            if (r3 == 0) goto L_0x00c7
            java.lang.String r2 = "type"
            boolean r2 = r3.has(r2)     // Catch:{ Exception -> 0x024f }
            if (r2 == 0) goto L_0x00c7
            java.lang.String r2 = "type"
            java.lang.String r2 = r3.getString(r2)     // Catch:{ Exception -> 0x024f }
            java.lang.String r4 = "0"
            boolean r2 = r2.equals(r4)     // Catch:{ Exception -> 0x024f }
            if (r2 == 0) goto L_0x00c7
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x024f }
            r2.s = r4     // Catch:{ Exception -> 0x024f }
        L_0x00c7:
            if (r3 == 0) goto L_0x00e8
            java.lang.String r2 = "bdlist"
            boolean r2 = r3.has(r2)     // Catch:{ Exception -> 0x024f }
            if (r2 == 0) goto L_0x00e8
            java.lang.String r2 = "bdlist"
            java.lang.String r2 = r3.getString(r2)     // Catch:{ Exception -> 0x024f }
            java.lang.String r4 = ";"
            java.lang.String[] r2 = r2.split(r4)     // Catch:{ Exception -> 0x024f }
            r0 = r21
            com.baidu.location.d.c$c r4 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r4 = r4.e     // Catch:{ Exception -> 0x024f }
            r4.a(r2)     // Catch:{ Exception -> 0x024f }
        L_0x00e8:
            if (r3 == 0) goto L_0x0121
            java.lang.String r2 = "loadurl"
            boolean r2 = r3.has(r2)     // Catch:{ Exception -> 0x024f }
            if (r2 == 0) goto L_0x0121
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = r2.e     // Catch:{ Exception -> 0x024f }
            java.lang.String r4 = "loadurl"
            org.json.JSONObject r4 = r3.getJSONObject(r4)     // Catch:{ Exception -> 0x024f }
            java.lang.String r5 = "host"
            java.lang.String r4 = r4.getString(r5)     // Catch:{ Exception -> 0x024f }
            java.lang.String r5 = "loadurl"
            org.json.JSONObject r5 = r3.getJSONObject(r5)     // Catch:{ Exception -> 0x024f }
            java.lang.String r6 = "module"
            java.lang.String r5 = r5.getString(r6)     // Catch:{ Exception -> 0x024f }
            java.lang.String r6 = "loadurl"
            org.json.JSONObject r3 = r3.getJSONObject(r6)     // Catch:{ Exception -> 0x024f }
            java.lang.String r6 = "req"
            java.lang.String r3 = r3.getString(r6)     // Catch:{ Exception -> 0x024f }
            r2.a(r4, r5, r3)     // Catch:{ Exception -> 0x024f }
        L_0x0121:
            if (r9 == 0) goto L_0x0394
            java.lang.String r2 = "cell"
            boolean r2 = r9.has(r2)     // Catch:{ Exception -> 0x024f }
            if (r2 == 0) goto L_0x0394
            java.lang.String r2 = "cell"
            org.json.JSONObject r10 = r9.getJSONObject(r2)     // Catch:{ Exception -> 0x024f }
            java.util.Iterator r11 = r10.keys()     // Catch:{ Exception -> 0x024f }
            java.lang.StringBuffer r12 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x024f }
            r12.<init>()     // Catch:{ Exception -> 0x024f }
            java.lang.StringBuffer r13 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x024f }
            r13.<init>()     // Catch:{ Exception -> 0x024f }
            java.lang.StringBuffer r14 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x024f }
            r14.<init>()     // Catch:{ Exception -> 0x024f }
            r8 = 1
            r7 = 1
            r5 = 1
            r3 = 0
            r6 = 0
            r4 = 0
        L_0x014a:
            boolean r2 = r11.hasNext()     // Catch:{ Exception -> 0x024f }
            if (r2 == 0) goto L_0x0337
            java.lang.Object r2 = r11.next()     // Catch:{ Exception -> 0x024f }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x024f }
            java.lang.String r15 = r10.getString(r2)     // Catch:{ Exception -> 0x024f }
            java.lang.String r16 = ","
            java.lang.String[] r16 = r15.split(r16)     // Catch:{ Exception -> 0x024f }
            r17 = 3
            r16 = r16[r17]     // Catch:{ Exception -> 0x024f }
            java.lang.Double r16 = java.lang.Double.valueOf(r16)     // Catch:{ Exception -> 0x024f }
            if (r7 == 0) goto L_0x0246
            r7 = 0
        L_0x016b:
            r13.append(r2)     // Catch:{ Exception -> 0x024f }
            int r6 = r6 + 1
            double r16 = r16.doubleValue()     // Catch:{ Exception -> 0x024f }
            r18 = 0
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 <= 0) goto L_0x0326
            if (r5 == 0) goto L_0x02ba
            r5 = 0
        L_0x017d:
            r16 = 40
            r0 = r16
            java.lang.StringBuffer r16 = r14.append(r0)     // Catch:{ Exception -> 0x024f }
            r0 = r16
            java.lang.StringBuffer r2 = r0.append(r2)     // Catch:{ Exception -> 0x024f }
            r16 = 44
            r0 = r16
            java.lang.StringBuffer r2 = r2.append(r0)     // Catch:{ Exception -> 0x024f }
            java.lang.StringBuffer r2 = r2.append(r15)     // Catch:{ Exception -> 0x024f }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x024f }
            r15.<init>()     // Catch:{ Exception -> 0x024f }
            java.lang.String r16 = ","
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x024f }
            long r16 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x024f }
            r18 = 1000(0x3e8, double:4.94E-321)
            long r16 = r16 / r18
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x024f }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x024f }
            java.lang.StringBuffer r2 = r2.append(r15)     // Catch:{ Exception -> 0x024f }
            r15 = 41
            r2.append(r15)     // Catch:{ Exception -> 0x024f }
            int r4 = r4 + 1
            r2 = r3
            r3 = r8
        L_0x01bf:
            r8 = 100
            if (r6 < r8) goto L_0x01e8
            r0 = r21
            com.baidu.location.d.c$c r7 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r7 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r7 = r7.i     // Catch:{ Exception -> 0x024f }
            java.lang.String r8 = "DELETE FROM CL WHERE id IN (%s);"
            r15 = 1
            java.lang.Object[] r15 = new java.lang.Object[r15]     // Catch:{ Exception -> 0x024f }
            r16 = 0
            java.lang.String r17 = r13.toString()     // Catch:{ Exception -> 0x024f }
            r15[r16] = r17     // Catch:{ Exception -> 0x024f }
            java.lang.String r8 = java.lang.String.format(r8, r15)     // Catch:{ Exception -> 0x024f }
            r7.execSQL(r8)     // Catch:{ Exception -> 0x024f }
            r7 = 1
            r8 = 0
            r13.setLength(r8)     // Catch:{ Exception -> 0x024f }
            int r6 = r6 + -100
        L_0x01e8:
            r8 = 100
            if (r4 < r8) goto L_0x0211
            r0 = r21
            com.baidu.location.d.c$c r5 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r5 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r5 = r5.h     // Catch:{ Exception -> 0x024f }
            java.lang.String r8 = "INSERT OR REPLACE INTO CL (id,x,y,r,cl,timestamp) VALUES %s;"
            r15 = 1
            java.lang.Object[] r15 = new java.lang.Object[r15]     // Catch:{ Exception -> 0x024f }
            r16 = 0
            java.lang.String r17 = r14.toString()     // Catch:{ Exception -> 0x024f }
            r15[r16] = r17     // Catch:{ Exception -> 0x024f }
            java.lang.String r8 = java.lang.String.format(r8, r15)     // Catch:{ Exception -> 0x024f }
            r5.execSQL(r8)     // Catch:{ Exception -> 0x024f }
            r5 = 1
            r8 = 0
            r14.setLength(r8)     // Catch:{ Exception -> 0x024f }
            int r4 = r4 + -100
        L_0x0211:
            r8 = 100
            if (r2 < r8) goto L_0x023a
            r0 = r21
            com.baidu.location.d.c$c r3 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r3 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r3 = r3.h     // Catch:{ Exception -> 0x024f }
            java.lang.String r8 = "DELETE FROM CL WHERE id IN (%s);"
            r15 = 1
            java.lang.Object[] r15 = new java.lang.Object[r15]     // Catch:{ Exception -> 0x024f }
            r16 = 0
            java.lang.String r17 = r12.toString()     // Catch:{ Exception -> 0x024f }
            r15[r16] = r17     // Catch:{ Exception -> 0x024f }
            java.lang.String r8 = java.lang.String.format(r8, r15)     // Catch:{ Exception -> 0x024f }
            r3.execSQL(r8)     // Catch:{ Exception -> 0x024f }
            r3 = 1
            r8 = 0
            r12.setLength(r8)     // Catch:{ Exception -> 0x024f }
            int r2 = r2 + -100
        L_0x023a:
            r8 = r3
            r3 = r2
            goto L_0x014a
        L_0x023e:
            r3 = move-exception
        L_0x023f:
            r3.printStackTrace()
            r3 = r4
            r9 = r5
            goto L_0x0077
        L_0x0246:
            r17 = 44
            r0 = r17
            r13.append(r0)     // Catch:{ Exception -> 0x024f }
            goto L_0x016b
        L_0x024f:
            r2 = move-exception
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ all -> 0x02c3 }
            r2.c()     // Catch:{ all -> 0x02c3 }
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x0676 }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0676 }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x0676 }
            if (r2 == 0) goto L_0x0280
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x0676 }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0676 }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x0676 }
            boolean r2 = r2.isOpen()     // Catch:{ Exception -> 0x0676 }
            if (r2 == 0) goto L_0x0280
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x0676 }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0676 }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x0676 }
            r2.endTransaction()     // Catch:{ Exception -> 0x0676 }
        L_0x0280:
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x0676 }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0676 }
            android.database.sqlite.SQLiteDatabase r2 = r2.i     // Catch:{ Exception -> 0x0676 }
            if (r2 == 0) goto L_0x02a9
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x0676 }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0676 }
            android.database.sqlite.SQLiteDatabase r2 = r2.i     // Catch:{ Exception -> 0x0676 }
            boolean r2 = r2.isOpen()     // Catch:{ Exception -> 0x0676 }
            if (r2 == 0) goto L_0x02a9
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x0676 }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0676 }
            android.database.sqlite.SQLiteDatabase r2 = r2.i     // Catch:{ Exception -> 0x0676 }
            r2.endTransaction()     // Catch:{ Exception -> 0x0676 }
        L_0x02a9:
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a
            r3 = 0
            r2.j = r3
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a
            r3 = 0
            r2.f = r3
            goto L_0x0043
        L_0x02ba:
            r16 = 44
            r0 = r16
            r14.append(r0)     // Catch:{ Exception -> 0x024f }
            goto L_0x017d
        L_0x02c3:
            r2 = move-exception
            r0 = r21
            com.baidu.location.d.c$c r3 = r0.a     // Catch:{ Exception -> 0x0673 }
            com.baidu.location.d.c r3 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0673 }
            android.database.sqlite.SQLiteDatabase r3 = r3.h     // Catch:{ Exception -> 0x0673 }
            if (r3 == 0) goto L_0x02ed
            r0 = r21
            com.baidu.location.d.c$c r3 = r0.a     // Catch:{ Exception -> 0x0673 }
            com.baidu.location.d.c r3 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0673 }
            android.database.sqlite.SQLiteDatabase r3 = r3.h     // Catch:{ Exception -> 0x0673 }
            boolean r3 = r3.isOpen()     // Catch:{ Exception -> 0x0673 }
            if (r3 == 0) goto L_0x02ed
            r0 = r21
            com.baidu.location.d.c$c r3 = r0.a     // Catch:{ Exception -> 0x0673 }
            com.baidu.location.d.c r3 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0673 }
            android.database.sqlite.SQLiteDatabase r3 = r3.h     // Catch:{ Exception -> 0x0673 }
            r3.endTransaction()     // Catch:{ Exception -> 0x0673 }
        L_0x02ed:
            r0 = r21
            com.baidu.location.d.c$c r3 = r0.a     // Catch:{ Exception -> 0x0673 }
            com.baidu.location.d.c r3 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0673 }
            android.database.sqlite.SQLiteDatabase r3 = r3.i     // Catch:{ Exception -> 0x0673 }
            if (r3 == 0) goto L_0x0316
            r0 = r21
            com.baidu.location.d.c$c r3 = r0.a     // Catch:{ Exception -> 0x0673 }
            com.baidu.location.d.c r3 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0673 }
            android.database.sqlite.SQLiteDatabase r3 = r3.i     // Catch:{ Exception -> 0x0673 }
            boolean r3 = r3.isOpen()     // Catch:{ Exception -> 0x0673 }
            if (r3 == 0) goto L_0x0316
            r0 = r21
            com.baidu.location.d.c$c r3 = r0.a     // Catch:{ Exception -> 0x0673 }
            com.baidu.location.d.c r3 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0673 }
            android.database.sqlite.SQLiteDatabase r3 = r3.i     // Catch:{ Exception -> 0x0673 }
            r3.endTransaction()     // Catch:{ Exception -> 0x0673 }
        L_0x0316:
            r0 = r21
            com.baidu.location.d.c$c r3 = r0.a
            r4 = 0
            r3.j = r4
            r0 = r21
            com.baidu.location.d.c$c r3 = r0.a
            r4 = 0
            r3.f = r4
            throw r2
        L_0x0326:
            if (r8 == 0) goto L_0x0331
            r8 = 0
        L_0x0329:
            r12.append(r2)     // Catch:{ Exception -> 0x024f }
            int r2 = r3 + 1
            r3 = r8
            goto L_0x01bf
        L_0x0331:
            r15 = 44
            r12.append(r15)     // Catch:{ Exception -> 0x024f }
            goto L_0x0329
        L_0x0337:
            if (r6 <= 0) goto L_0x0356
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.i     // Catch:{ Exception -> 0x024f }
            java.lang.String r5 = "DELETE FROM CL WHERE id IN (%s);"
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x024f }
            r7 = 0
            java.lang.String r8 = r13.toString()     // Catch:{ Exception -> 0x024f }
            r6[r7] = r8     // Catch:{ Exception -> 0x024f }
            java.lang.String r5 = java.lang.String.format(r5, r6)     // Catch:{ Exception -> 0x024f }
            r2.execSQL(r5)     // Catch:{ Exception -> 0x024f }
        L_0x0356:
            if (r4 <= 0) goto L_0x0375
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x024f }
            java.lang.String r4 = "INSERT OR REPLACE INTO CL (id,x,y,r,cl,timestamp) VALUES %s;"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x024f }
            r6 = 0
            java.lang.String r7 = r14.toString()     // Catch:{ Exception -> 0x024f }
            r5[r6] = r7     // Catch:{ Exception -> 0x024f }
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch:{ Exception -> 0x024f }
            r2.execSQL(r4)     // Catch:{ Exception -> 0x024f }
        L_0x0375:
            if (r3 <= 0) goto L_0x0394
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = "DELETE FROM CL WHERE id IN (%s);"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x024f }
            r5 = 0
            java.lang.String r6 = r12.toString()     // Catch:{ Exception -> 0x024f }
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ Exception -> 0x024f }
            r2.execSQL(r3)     // Catch:{ Exception -> 0x024f }
        L_0x0394:
            if (r9 == 0) goto L_0x0537
            java.lang.String r2 = "ap"
            boolean r2 = r9.has(r2)     // Catch:{ Exception -> 0x024f }
            if (r2 == 0) goto L_0x0537
            java.lang.String r2 = "ap"
            org.json.JSONObject r10 = r9.getJSONObject(r2)     // Catch:{ Exception -> 0x024f }
            java.util.Iterator r11 = r10.keys()     // Catch:{ Exception -> 0x024f }
            r8 = 0
            r5 = 0
            r7 = 0
            r6 = 1
            r4 = 1
            r3 = 1
            java.lang.StringBuffer r12 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x024f }
            r12.<init>()     // Catch:{ Exception -> 0x024f }
            java.lang.StringBuffer r13 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x024f }
            r13.<init>()     // Catch:{ Exception -> 0x024f }
            java.lang.StringBuffer r14 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x024f }
            r14.<init>()     // Catch:{ Exception -> 0x024f }
        L_0x03bd:
            boolean r2 = r11.hasNext()     // Catch:{ Exception -> 0x024f }
            if (r2 == 0) goto L_0x04da
            java.lang.Object r2 = r11.next()     // Catch:{ Exception -> 0x024f }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x024f }
            java.lang.String r15 = r10.getString(r2)     // Catch:{ Exception -> 0x024f }
            java.lang.String r16 = ","
            java.lang.String[] r16 = r15.split(r16)     // Catch:{ Exception -> 0x024f }
            r17 = 3
            r16 = r16[r17]     // Catch:{ Exception -> 0x024f }
            java.lang.Double r16 = java.lang.Double.valueOf(r16)     // Catch:{ Exception -> 0x024f }
            if (r4 == 0) goto L_0x04b2
            r4 = 0
        L_0x03de:
            r13.append(r2)     // Catch:{ Exception -> 0x024f }
            int r5 = r5 + 1
            double r16 = r16.doubleValue()     // Catch:{ Exception -> 0x024f }
            r18 = 0
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 <= 0) goto L_0x04c4
            if (r3 == 0) goto L_0x04bb
            r3 = 0
        L_0x03f0:
            r16 = 40
            r0 = r16
            java.lang.StringBuffer r16 = r14.append(r0)     // Catch:{ Exception -> 0x024f }
            r0 = r16
            java.lang.StringBuffer r2 = r0.append(r2)     // Catch:{ Exception -> 0x024f }
            r16 = 44
            r0 = r16
            java.lang.StringBuffer r2 = r2.append(r0)     // Catch:{ Exception -> 0x024f }
            java.lang.StringBuffer r2 = r2.append(r15)     // Catch:{ Exception -> 0x024f }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x024f }
            r15.<init>()     // Catch:{ Exception -> 0x024f }
            java.lang.String r16 = ","
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x024f }
            long r16 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x024f }
            r18 = 1000(0x3e8, double:4.94E-321)
            long r16 = r16 / r18
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x024f }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x024f }
            java.lang.StringBuffer r2 = r2.append(r15)     // Catch:{ Exception -> 0x024f }
            r15 = 41
            r2.append(r15)     // Catch:{ Exception -> 0x024f }
            int r2 = r7 + 1
            r7 = r8
            r20 = r2
            r2 = r3
            r3 = r20
        L_0x0436:
            r8 = 100
            if (r5 < r8) goto L_0x045f
            r0 = r21
            com.baidu.location.d.c$c r4 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r4 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r4 = r4.i     // Catch:{ Exception -> 0x024f }
            java.lang.String r8 = "DELETE FROM AP WHERE id IN (%s);"
            r15 = 1
            java.lang.Object[] r15 = new java.lang.Object[r15]     // Catch:{ Exception -> 0x024f }
            r16 = 0
            java.lang.String r17 = r13.toString()     // Catch:{ Exception -> 0x024f }
            r15[r16] = r17     // Catch:{ Exception -> 0x024f }
            java.lang.String r8 = java.lang.String.format(r8, r15)     // Catch:{ Exception -> 0x024f }
            r4.execSQL(r8)     // Catch:{ Exception -> 0x024f }
            r4 = 1
            r8 = 0
            r13.setLength(r8)     // Catch:{ Exception -> 0x024f }
            int r5 = r5 + -100
        L_0x045f:
            r8 = 100
            if (r3 < r8) goto L_0x0488
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x024f }
            java.lang.String r8 = "INSERT OR REPLACE INTO AP (id,x,y,r,cl,timestamp) VALUES %s;"
            r15 = 1
            java.lang.Object[] r15 = new java.lang.Object[r15]     // Catch:{ Exception -> 0x024f }
            r16 = 0
            java.lang.String r17 = r14.toString()     // Catch:{ Exception -> 0x024f }
            r15[r16] = r17     // Catch:{ Exception -> 0x024f }
            java.lang.String r8 = java.lang.String.format(r8, r15)     // Catch:{ Exception -> 0x024f }
            r2.execSQL(r8)     // Catch:{ Exception -> 0x024f }
            r2 = 1
            r8 = 0
            r14.setLength(r8)     // Catch:{ Exception -> 0x024f }
            int r3 = r3 + -100
        L_0x0488:
            if (r7 <= 0) goto L_0x04ad
            r0 = r21
            com.baidu.location.d.c$c r8 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r8 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r8 = r8.h     // Catch:{ Exception -> 0x024f }
            java.lang.String r15 = "DELETE FROM AP WHERE id IN (%s);"
            r16 = 1
            r0 = r16
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x024f }
            r16 = r0
            r17 = 0
            java.lang.String r18 = r12.toString()     // Catch:{ Exception -> 0x024f }
            r16[r17] = r18     // Catch:{ Exception -> 0x024f }
            java.lang.String r15 = java.lang.String.format(r15, r16)     // Catch:{ Exception -> 0x024f }
            r8.execSQL(r15)     // Catch:{ Exception -> 0x024f }
        L_0x04ad:
            r8 = r7
            r7 = r3
            r3 = r2
            goto L_0x03bd
        L_0x04b2:
            r17 = 44
            r0 = r17
            r13.append(r0)     // Catch:{ Exception -> 0x024f }
            goto L_0x03de
        L_0x04bb:
            r16 = 44
            r0 = r16
            r14.append(r0)     // Catch:{ Exception -> 0x024f }
            goto L_0x03f0
        L_0x04c4:
            if (r6 == 0) goto L_0x04d4
            r6 = 0
        L_0x04c7:
            r12.append(r2)     // Catch:{ Exception -> 0x024f }
            int r2 = r8 + 1
            r20 = r3
            r3 = r7
            r7 = r2
            r2 = r20
            goto L_0x0436
        L_0x04d4:
            r15 = 44
            r12.append(r15)     // Catch:{ Exception -> 0x024f }
            goto L_0x04c7
        L_0x04da:
            if (r5 <= 0) goto L_0x04f9
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.i     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = "DELETE FROM AP WHERE id IN (%s);"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x024f }
            r5 = 0
            java.lang.String r6 = r13.toString()     // Catch:{ Exception -> 0x024f }
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ Exception -> 0x024f }
            r2.execSQL(r3)     // Catch:{ Exception -> 0x024f }
        L_0x04f9:
            if (r7 <= 0) goto L_0x0518
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = "INSERT OR REPLACE INTO AP (id,x,y,r,cl,timestamp) VALUES %s;"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x024f }
            r5 = 0
            java.lang.String r6 = r14.toString()     // Catch:{ Exception -> 0x024f }
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ Exception -> 0x024f }
            r2.execSQL(r3)     // Catch:{ Exception -> 0x024f }
        L_0x0518:
            if (r8 <= 0) goto L_0x0537
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = "DELETE FROM AP WHERE id IN (%s);"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x024f }
            r5 = 0
            java.lang.String r6 = r12.toString()     // Catch:{ Exception -> 0x024f }
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ Exception -> 0x024f }
            r2.execSQL(r3)     // Catch:{ Exception -> 0x024f }
        L_0x0537:
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = "DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY timestamp DESC, frequency DESC LIMIT %d);"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x024f }
            r5 = 0
            java.lang.String r6 = "AP"
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            r5 = 1
            java.lang.String r6 = "AP"
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            r5 = 2
            r6 = 200000(0x30d40, float:2.8026E-40)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x024f }
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ Exception -> 0x024f }
            r2.execSQL(r3)     // Catch:{ Exception -> 0x024f }
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = "DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY timestamp DESC, frequency DESC LIMIT %d);"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x024f }
            r5 = 0
            java.lang.String r6 = "CL"
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            r5 = 1
            java.lang.String r6 = "CL"
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            r5 = 2
            r6 = 200000(0x30d40, float:2.8026E-40)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x024f }
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ Exception -> 0x024f }
            r2.execSQL(r3)     // Catch:{ Exception -> 0x024f }
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.i     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = "DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY frequency DESC LIMIT %d);"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x024f }
            r5 = 0
            java.lang.String r6 = "AP"
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            r5 = 1
            java.lang.String r6 = "AP"
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            r5 = 2
            r6 = 10000(0x2710, float:1.4013E-41)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x024f }
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ Exception -> 0x024f }
            r2.execSQL(r3)     // Catch:{ Exception -> 0x024f }
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.i     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = "DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY frequency DESC LIMIT %d);"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x024f }
            r5 = 0
            java.lang.String r6 = "CL"
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            r5 = 1
            java.lang.String r6 = "CL"
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            r5 = 2
            r6 = 10000(0x2710, float:1.4013E-41)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x024f }
            r4[r5] = r6     // Catch:{ Exception -> 0x024f }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ Exception -> 0x024f }
            r2.execSQL(r3)     // Catch:{ Exception -> 0x024f }
            if (r9 == 0) goto L_0x05f6
            java.lang.String r2 = "ap"
            boolean r2 = r9.has(r2)     // Catch:{ Exception -> 0x024f }
            if (r2 != 0) goto L_0x05f6
            java.lang.String r2 = "cell"
            boolean r2 = r9.has(r2)     // Catch:{ Exception -> 0x024f }
            if (r2 != 0) goto L_0x05f6
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            r2.c()     // Catch:{ Exception -> 0x024f }
        L_0x05f6:
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x024f }
            r2.setTransactionSuccessful()     // Catch:{ Exception -> 0x024f }
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x024f }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x024f }
            android.database.sqlite.SQLiteDatabase r2 = r2.i     // Catch:{ Exception -> 0x024f }
            r2.setTransactionSuccessful()     // Catch:{ Exception -> 0x024f }
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x0679 }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0679 }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x0679 }
            if (r2 == 0) goto L_0x0639
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x0679 }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0679 }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x0679 }
            boolean r2 = r2.isOpen()     // Catch:{ Exception -> 0x0679 }
            if (r2 == 0) goto L_0x0639
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x0679 }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0679 }
            android.database.sqlite.SQLiteDatabase r2 = r2.h     // Catch:{ Exception -> 0x0679 }
            r2.endTransaction()     // Catch:{ Exception -> 0x0679 }
        L_0x0639:
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x0679 }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0679 }
            android.database.sqlite.SQLiteDatabase r2 = r2.i     // Catch:{ Exception -> 0x0679 }
            if (r2 == 0) goto L_0x0662
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x0679 }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0679 }
            android.database.sqlite.SQLiteDatabase r2 = r2.i     // Catch:{ Exception -> 0x0679 }
            boolean r2 = r2.isOpen()     // Catch:{ Exception -> 0x0679 }
            if (r2 == 0) goto L_0x0662
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a     // Catch:{ Exception -> 0x0679 }
            com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch:{ Exception -> 0x0679 }
            android.database.sqlite.SQLiteDatabase r2 = r2.i     // Catch:{ Exception -> 0x0679 }
            r2.endTransaction()     // Catch:{ Exception -> 0x0679 }
        L_0x0662:
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a
            r3 = 0
            r2.j = r3
            r0 = r21
            com.baidu.location.d.c$c r2 = r0.a
            r3 = 0
            r2.f = r3
            goto L_0x0043
        L_0x0673:
            r3 = move-exception
            goto L_0x0316
        L_0x0676:
            r2 = move-exception
            goto L_0x02a9
        L_0x0679:
            r2 = move-exception
            goto L_0x0662
        L_0x067b:
            r4 = move-exception
            goto L_0x0091
        L_0x067e:
            r4 = move-exception
            r20 = r4
            r4 = r3
            r3 = r20
            goto L_0x023f
        L_0x0686:
            r5 = move-exception
            r20 = r5
            r5 = r4
            r4 = r3
            r3 = r20
            goto L_0x023f
        L_0x068f:
            r4 = r5
            goto L_0x0068
        L_0x0692:
            r3 = r4
            r4 = r5
            goto L_0x0076
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.e.run():void");
    }
}
