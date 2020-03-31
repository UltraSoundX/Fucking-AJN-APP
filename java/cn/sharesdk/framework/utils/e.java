package cn.sharesdk.framework.utils;

/* compiled from: ShareBypassApproval */
public class e {
    private String a;
    private String b;
    private boolean c = true;

    public void a(String str) {
        this.a = str;
        this.b = "";
    }

    public void a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x013c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(cn.sharesdk.framework.Platform.ShareParams r12, cn.sharesdk.framework.Platform r13) throws java.lang.Throwable {
        /*
            r11 = this;
            r10 = 0
            android.content.Intent r3 = new android.content.Intent
            r3.<init>()
            java.lang.String r1 = r12.getImagePath()
            java.lang.String r2 = r12.getImageUrl()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String[] r4 = r12.getImageArray()
            if (r4 != 0) goto L_0x0122
        L_0x0019:
            java.lang.String r4 = r12.getText()
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L_0x0034
            java.lang.String r4 = r13.getShortLintk(r4, r10)
            r12.setText(r4)
            java.lang.String r5 = "android.intent.extra.TEXT"
            r3.putExtra(r5, r4)
            java.lang.String r5 = "Kdescription"
            r3.putExtra(r5, r4)
        L_0x0034:
            if (r0 == 0) goto L_0x003c
            int r4 = r0.size()
            if (r4 > 0) goto L_0x009e
        L_0x003c:
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 != 0) goto L_0x004d
            java.io.File r4 = new java.io.File
            r4.<init>(r1)
            boolean r4 = r4.exists()
            if (r4 != 0) goto L_0x0095
        L_0x004d:
            android.graphics.Bitmap r4 = r12.getImageData()
            if (r4 == 0) goto L_0x012c
            boolean r5 = r4.isRecycled()
            if (r5 != 0) goto L_0x012c
            android.content.Context r1 = com.mob.MobSDK.getContext()
            java.lang.String r2 = "images"
            java.lang.String r1 = com.mob.tools.utils.ResHelper.getCachePath(r1, r2)
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            long r6 = java.lang.System.currentTimeMillis()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = ".png"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r2.<init>(r1, r5)
            java.io.FileOutputStream r1 = new java.io.FileOutputStream
            r1.<init>(r2)
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.PNG
            r6 = 100
            r4.compress(r5, r6, r1)
            r1.flush()
            r1.close()
            java.lang.String r1 = r2.getAbsolutePath()
        L_0x0095:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x009e
            r0.add(r1)
        L_0x009e:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r5 = r0.iterator()
        L_0x00a7:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0145
            java.lang.Object r0 = r5.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "http"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x00c3
            android.content.Context r1 = com.mob.MobSDK.getContext()
            java.lang.String r0 = com.mob.tools.utils.BitmapHelper.downloadBitmap(r1, r0)
        L_0x00c3:
            java.io.File r2 = new java.io.File
            r2.<init>(r0)
            boolean r1 = r2.exists()
            if (r1 == 0) goto L_0x00a7
            java.lang.String r1 = "/data/"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x01cd
            android.content.Context r1 = com.mob.MobSDK.getContext()
            java.lang.String r6 = "images"
            java.lang.String r6 = com.mob.tools.utils.ResHelper.getCachePath(r1, r6)
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            long r8 = java.lang.System.currentTimeMillis()
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = r2.getName()
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r1.<init>(r6, r7)
            java.lang.String r6 = r1.getAbsolutePath()
            r1.createNewFile()
            boolean r0 = com.mob.tools.utils.ResHelper.copyFile(r0, r6)
            if (r0 == 0) goto L_0x01cd
            r0 = r1
        L_0x010c:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 24
            if (r1 < r2) goto L_0x013c
            android.content.Context r1 = com.mob.MobSDK.getContext()
            java.lang.String r0 = r0.getAbsolutePath()
            android.net.Uri r0 = com.mob.tools.utils.ResHelper.pathToContentUri(r1, r0)
            r4.add(r0)
            goto L_0x00a7
        L_0x0122:
            java.lang.String[] r0 = r12.getImageArray()
            java.util.List r0 = java.util.Arrays.asList(r0)
            goto L_0x0019
        L_0x012c:
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto L_0x0095
            android.content.Context r1 = com.mob.MobSDK.getContext()
            java.lang.String r1 = com.mob.tools.utils.BitmapHelper.downloadBitmap(r1, r2)
            goto L_0x0095
        L_0x013c:
            android.net.Uri r0 = android.net.Uri.fromFile(r0)
            r4.add(r0)
            goto L_0x00a7
        L_0x0145:
            int r0 = r4.size()
            if (r0 > 0) goto L_0x016f
            java.lang.String r0 = "android.intent.action.SEND"
            r3.setAction(r0)
            java.lang.String r0 = "text/plain"
            r3.setType(r0)
        L_0x0155:
            java.lang.String r0 = r11.b
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x01bc
            java.lang.String r0 = r11.a
            r3.setPackage(r0)
        L_0x0162:
            r0 = 335544320(0x14000000, float:6.4623485E-27)
            r3.addFlags(r0)
            android.content.Context r0 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x01c4 }
            r0.startActivity(r3)     // Catch:{ Throwable -> 0x01c4 }
        L_0x016e:
            return
        L_0x016f:
            int r0 = r4.size()
            r1 = 1
            if (r0 != r1) goto L_0x01ac
            java.lang.Object r0 = r4.get(r10)
            if (r0 == 0) goto L_0x01ac
            java.lang.String r0 = "android.intent.action.SEND"
            r3.setAction(r0)
            java.lang.String r1 = "android.intent.extra.STREAM"
            java.lang.Object r0 = r4.get(r10)
            android.os.Parcelable r0 = (android.os.Parcelable) r0
            r3.putExtra(r1, r0)
            java.net.FileNameMap r1 = java.net.URLConnection.getFileNameMap()
            java.lang.Object r0 = r4.get(r10)
            android.net.Uri r0 = (android.net.Uri) r0
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r1.getContentTypeFor(r0)
            if (r0 == 0) goto L_0x01a6
            int r1 = r0.length()
            if (r1 > 0) goto L_0x01a8
        L_0x01a6:
            java.lang.String r0 = "image/*"
        L_0x01a8:
            r3.setType(r0)
            goto L_0x0155
        L_0x01ac:
            java.lang.String r0 = "android.intent.action.SEND_MULTIPLE"
            r3.setAction(r0)
            java.lang.String r0 = "android.intent.extra.STREAM"
            r3.putParcelableArrayListExtra(r0, r4)
            java.lang.String r0 = "image/*"
            r3.setType(r0)
            goto L_0x0155
        L_0x01bc:
            java.lang.String r0 = r11.a
            java.lang.String r1 = r11.b
            r3.setClassName(r0, r1)
            goto L_0x0162
        L_0x01c4:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = cn.sharesdk.framework.utils.SSDKLog.b()
            r1.d(r0)
            goto L_0x016e
        L_0x01cd:
            r0 = r2
            goto L_0x010c
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.framework.utils.e.a(cn.sharesdk.framework.Platform$ShareParams, cn.sharesdk.framework.Platform):void");
    }
}
