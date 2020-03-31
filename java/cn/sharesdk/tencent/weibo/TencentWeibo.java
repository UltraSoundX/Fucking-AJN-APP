package cn.sharesdk.tencent.weibo;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.b.b.f.a;
import cn.sharesdk.framework.utils.SSDKLog;
import com.baidu.mobstat.Config;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.smtt.export.external.TbsCoreSettings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TencentWeibo extends Platform {
    public static final String NAME = TencentWeibo.class.getSimpleName();
    private String a;
    private String b;
    private String c;

    /* access modifiers changed from: protected */
    public void initDevInfo(String str) {
        this.a = getDevinfo(TbsCoreSettings.TBS_SETTINGS_APP_KEY);
        this.b = getDevinfo("AppSecret");
        this.c = getDevinfo("RedirectUri");
        if (this.c == null || this.c.length() <= 0) {
            this.c = getDevinfo("RedirectUrl");
        }
    }

    public int getPlatformId() {
        return 2;
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public void setNetworkDevinfo() {
        this.a = getNetworkDevinfo("app_key", TbsCoreSettings.TBS_SETTINGS_APP_KEY);
        this.b = getNetworkDevinfo("app_secret", "AppSecret");
        this.c = getNetworkDevinfo("redirect_uri", "RedirectUri");
        if (this.c == null || this.c.length() <= 0) {
            this.c = getDevinfo("RedirectUrl");
        }
    }

    public boolean isClientValid() {
        return f.a((Platform) this).a();
    }

    /* access modifiers changed from: protected */
    public void doAuthorize(String[] strArr) {
        final f a2 = f.a((Platform) this);
        a2.a(this.a, this.b);
        a2.a(this.c);
        a2.a((AuthorizeListener) new AuthorizeListener() {
            public void onError(Throwable th) {
                if (TencentWeibo.this.listener != null) {
                    TencentWeibo.this.listener.onError(TencentWeibo.this, 1, th);
                }
            }

            public void onComplete(Bundle bundle) {
                int i;
                TencentWeibo.this.db.putToken(bundle.getString("access_token"));
                TencentWeibo.this.db.putTokenSecret("");
                try {
                    i = ResHelper.parseInt(bundle.getString("expires_in"));
                } catch (Throwable th) {
                    i = 0;
                }
                TencentWeibo.this.db.putExpiresIn((long) i);
                TencentWeibo.this.db.putUserId(bundle.getString("openid"));
                TencentWeibo.this.db.put("name", bundle.getString("name"));
                TencentWeibo.this.db.put("nick", bundle.getString("nick"));
                TencentWeibo.this.db.put("openid", bundle.getString("openid"));
                TencentWeibo.this.db.put("openkey", bundle.getString("openkey"));
                a2.a(TencentWeibo.this.db.getToken(), TencentWeibo.this.db.get("name"), TencentWeibo.this.db.getUserId(), TencentWeibo.this.db.get("openkey"));
                TencentWeibo.this.afterRegister(1, null);
            }

            public void onCancel() {
                if (TencentWeibo.this.listener != null) {
                    TencentWeibo.this.listener.onCancel(TencentWeibo.this, 1);
                }
            }
        }, isSSODisable());
    }

    /* access modifiers changed from: protected */
    public boolean checkAuthorize(int i, Object obj) {
        if (isAuthValid()) {
            f a2 = f.a((Platform) this);
            a2.a(this.a, this.b);
            a2.a(this.c);
            a2.a(this.db.getToken(), this.db.get("name"), this.db.getUserId(), this.db.get("openkey"));
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARNING: type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r4v3 */
    /* JADX WARNING: type inference failed for: r4v4, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARNING: type inference failed for: r4v5, types: [java.lang.CharSequence] */
    /* JADX WARNING: type inference failed for: r2v23, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v6 */
    /* JADX WARNING: type inference failed for: r4v7, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r4v9 */
    /* JADX WARNING: type inference failed for: r4v10, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v20, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r4v21 */
    /* JADX WARNING: type inference failed for: r4v25, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r4v27 */
    /* JADX WARNING: type inference failed for: r4v28 */
    /* JADX WARNING: type inference failed for: r4v29 */
    /* JADX WARNING: type inference failed for: r4v30 */
    /* JADX WARNING: type inference failed for: r4v31 */
    /* JADX WARNING: type inference failed for: r4v32 */
    /* JADX WARNING: type inference failed for: r4v33 */
    /* JADX WARNING: type inference failed for: r4v34 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r4v21
  assigns: []
  uses: []
  mth insns count: 213
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 8 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doShare(cn.sharesdk.framework.Platform.ShareParams r21) {
        /*
            r20 = this;
            java.lang.String r2 = r21.getText()
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L_0x0023
            android.content.Context r3 = com.mob.MobSDK.getContext()
            java.lang.String r4 = "ssdk_weibo_upload_content"
            int r3 = com.mob.tools.utils.ResHelper.getStringRes(r3, r4)
            if (r3 <= 0) goto L_0x0023
            android.content.Context r2 = com.mob.MobSDK.getContext()
            java.lang.String r2 = r2.getString(r3)
            r0 = r21
            r0.setText(r2)
        L_0x0023:
            cn.sharesdk.tencent.weibo.f r9 = cn.sharesdk.tencent.weibo.f.a(r20)
            r3 = 0
            r0 = r20
            java.lang.String r10 = r0.getShortLintk(r2, r3)
            r0 = r21
            r0.setText(r10)
            java.lang.String r5 = r21.getImageUrl()
            float r11 = r21.getLatitude()
            float r12 = r21.getLongitude()
            java.lang.String r13 = r21.getImagePath()
            java.lang.String[] r2 = r21.getImageArray()
            if (r2 != 0) goto L_0x004c
            r2 = 0
            java.lang.String[] r2 = new java.lang.String[r2]
        L_0x004c:
            java.lang.String r7 = ""
            java.lang.String r3 = ""
            r6 = 0
            long r14 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x013d }
            int r0 = r2.length     // Catch:{ Throwable -> 0x013d }
            r16 = r0
            r4 = 0
            r8 = r4
            r4 = r7
            r19 = r3
            r3 = r6
            r6 = r19
        L_0x0060:
            r0 = r16
            if (r8 >= r0) goto L_0x006a
            r17 = r2[r8]     // Catch:{ Throwable -> 0x0238 }
            r7 = 9
            if (r3 < r7) goto L_0x00bf
        L_0x006a:
            com.mob.tools.log.NLog r2 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ Throwable -> 0x0238 }
            java.lang.String r3 = "upload pic use total time ===>>> %s "
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ Throwable -> 0x0238 }
            r8 = 0
            long r16 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0238 }
            long r14 = r16 - r14
            java.lang.Long r14 = java.lang.Long.valueOf(r14)     // Catch:{ Throwable -> 0x0238 }
            r7[r8] = r14     // Catch:{ Throwable -> 0x0238 }
            r2.i(r3, r7)     // Catch:{ Throwable -> 0x0238 }
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x0238 }
            if (r2 != 0) goto L_0x0111
            r2 = 1
            java.lang.String r4 = r4.substring(r2)     // Catch:{ Throwable -> 0x0238 }
            java.util.HashMap r2 = r9.a(r10, r4, r11, r12)     // Catch:{ Throwable -> 0x0238 }
            r3 = r2
        L_0x0093:
            if (r3 != 0) goto L_0x0171
            r0 = r20
            cn.sharesdk.framework.PlatformActionListener r2 = r0.listener
            if (r2 == 0) goto L_0x00be
            r0 = r20
            cn.sharesdk.framework.PlatformActionListener r2 = r0.listener
            r3 = 9
            java.lang.Throwable r4 = new java.lang.Throwable
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = " response is null\n"
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            r0 = r20
            r2.onError(r0, r3, r4)
        L_0x00be:
            return
        L_0x00bf:
            r7 = 0
            r0 = r17
            java.lang.String r7 = r9.d(r0)     // Catch:{ Throwable -> 0x00ee }
        L_0x00c6:
            boolean r17 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Throwable -> 0x0238 }
            if (r17 != 0) goto L_0x00e9
            int r3 = r3 + 1
            java.lang.StringBuilder r17 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0238 }
            r17.<init>()     // Catch:{ Throwable -> 0x0238 }
            r0 = r17
            java.lang.StringBuilder r4 = r0.append(r4)     // Catch:{ Throwable -> 0x0238 }
            java.lang.String r17 = ","
            r0 = r17
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ Throwable -> 0x0238 }
            java.lang.StringBuilder r4 = r4.append(r7)     // Catch:{ Throwable -> 0x0238 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0238 }
        L_0x00e9:
            int r7 = r8 + 1
            r8 = r7
            goto L_0x0060
        L_0x00ee:
            r17 = move-exception
            java.lang.StringBuilder r18 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0238 }
            r18.<init>()     // Catch:{ Throwable -> 0x0238 }
            r0 = r18
            java.lang.StringBuilder r18 = r0.append(r6)     // Catch:{ Throwable -> 0x0238 }
            java.lang.String r17 = r17.getMessage()     // Catch:{ Throwable -> 0x0238 }
            r0 = r18
            r1 = r17
            java.lang.StringBuilder r17 = r0.append(r1)     // Catch:{ Throwable -> 0x0238 }
            java.lang.String r18 = "\n"
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ Throwable -> 0x0238 }
            java.lang.String r6 = r17.toString()     // Catch:{ Throwable -> 0x0238 }
            goto L_0x00c6
        L_0x0111:
            boolean r2 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x0238 }
            if (r2 != 0) goto L_0x011e
            java.util.HashMap r2 = r9.a(r10, r5, r11, r12)     // Catch:{ Throwable -> 0x0238 }
            r3 = r2
            goto L_0x0093
        L_0x011e:
            boolean r2 = android.text.TextUtils.isEmpty(r13)     // Catch:{ Throwable -> 0x0238 }
            if (r2 != 0) goto L_0x012f
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x0238 }
            r2.<init>(r13)     // Catch:{ Throwable -> 0x0238 }
            boolean r2 = r2.exists()     // Catch:{ Throwable -> 0x0238 }
            if (r2 != 0) goto L_0x0136
        L_0x012f:
            java.util.HashMap r2 = r9.a(r10, r11, r12)     // Catch:{ Throwable -> 0x0238 }
            r3 = r2
            goto L_0x0093
        L_0x0136:
            java.util.HashMap r2 = r9.b(r10, r13, r11, r12)     // Catch:{ Throwable -> 0x0238 }
            r3 = r2
            goto L_0x0093
        L_0x013d:
            r2 = move-exception
        L_0x013e:
            r0 = r20
            cn.sharesdk.framework.PlatformActionListener r4 = r0.listener
            if (r4 == 0) goto L_0x00be
            r0 = r20
            cn.sharesdk.framework.PlatformActionListener r4 = r0.listener
            r5 = 9
            java.lang.Throwable r6 = new java.lang.Throwable
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r2 = r2.getMessage()
            java.lang.StringBuilder r2 = r7.append(r2)
            java.lang.String r7 = "\n"
            java.lang.StringBuilder r2 = r2.append(r7)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r6.<init>(r2)
            r0 = r20
            r4.onError(r0, r5, r6)
            goto L_0x00be
        L_0x0171:
            java.lang.String r2 = "errcode"
            boolean r2 = r3.containsKey(r2)
            if (r2 == 0) goto L_0x01da
            java.lang.String r2 = "errcode"
            java.lang.Object r2 = r3.get(r2)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            if (r2 == 0) goto L_0x01da
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "msg"
            java.lang.Object r5 = r3.get(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = "("
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r4 = ")"
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            com.mob.tools.log.NLog r4 = cn.sharesdk.framework.utils.SSDKLog.b()
            java.lang.String r5 = "tecent weibo error %s"
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r7 = 0
            r6[r7] = r2
            r4.i(r5, r6)
            r0 = r20
            cn.sharesdk.framework.PlatformActionListener r2 = r0.listener
            if (r2 == 0) goto L_0x00be
            com.mob.tools.utils.Hashon r2 = new com.mob.tools.utils.Hashon
            r2.<init>()
            java.lang.String r2 = r2.fromHashMap(r3)
            r0 = r20
            cn.sharesdk.framework.PlatformActionListener r3 = r0.listener
            r4 = 9
            java.lang.Throwable r5 = new java.lang.Throwable
            r5.<init>(r2)
            r0 = r20
            r3.onError(r0, r4, r5)
            goto L_0x00be
        L_0x01da:
            java.lang.String r2 = "imgurl"
            java.lang.Object r2 = r3.get(r2)
            if (r2 != 0) goto L_0x020a
            boolean r7 = android.text.TextUtils.isEmpty(r4)
            if (r7 != 0) goto L_0x020a
        L_0x01e8:
            java.lang.String r2 = "data"
            java.lang.Object r2 = r3.get(r2)
            java.util.HashMap r2 = (java.util.HashMap) r2
            if (r2 != 0) goto L_0x0219
            r0 = r20
            cn.sharesdk.framework.PlatformActionListener r2 = r0.listener
            if (r2 == 0) goto L_0x00be
            r0 = r20
            cn.sharesdk.framework.PlatformActionListener r2 = r0.listener
            r3 = 9
            java.lang.Throwable r4 = new java.lang.Throwable
            r4.<init>(r6)
            r0 = r20
            r2.onError(r0, r3, r4)
            goto L_0x00be
        L_0x020a:
            if (r2 != 0) goto L_0x0214
            boolean r4 = android.text.TextUtils.isEmpty(r5)
            if (r4 != 0) goto L_0x0214
            r4 = r5
            goto L_0x01e8
        L_0x0214:
            if (r2 != 0) goto L_0x023c
            java.lang.String r4 = ""
            goto L_0x01e8
        L_0x0219:
            java.lang.String r3 = "imgurl"
            r2.put(r3, r4)
            java.lang.String r3 = "ShareParams"
            r0 = r21
            r2.put(r3, r0)
            r0 = r20
            cn.sharesdk.framework.PlatformActionListener r3 = r0.listener
            if (r3 == 0) goto L_0x00be
            r0 = r20
            cn.sharesdk.framework.PlatformActionListener r3 = r0.listener
            r4 = 9
            r0 = r20
            r3.onComplete(r0, r4, r2)
            goto L_0x00be
        L_0x0238:
            r2 = move-exception
            r3 = r6
            goto L_0x013e
        L_0x023c:
            r4 = r2
            goto L_0x01e8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.tencent.weibo.TencentWeibo.doShare(cn.sharesdk.framework.Platform$ShareParams):void");
    }

    /* access modifiers changed from: protected */
    public void follow(String str) {
        try {
            HashMap e = f.a((Platform) this).e(str);
            if (e == null) {
                if (this.listener != null) {
                    this.listener.onError(this, 6, new Throwable(" response is null"));
                }
            } else if (!e.containsKey("errcode") || ((Integer) e.get("errcode")).intValue() == 0) {
                HashMap hashMap = (HashMap) e.get("data");
                if (hashMap == null) {
                    hashMap = e;
                }
                if (this.listener != null) {
                    this.listener.onComplete(this, 6, hashMap);
                }
            } else {
                String fromHashMap = new Hashon().fromHashMap(e);
                if (this.listener != null) {
                    this.listener.onError(this, 6, new Throwable(fromHashMap));
                }
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 6, th);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void timeline(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 7);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void userInfor(java.lang.String r11) {
        /*
            r10 = this;
            r9 = 8
            r3 = 0
            cn.sharesdk.tencent.weibo.f r0 = cn.sharesdk.tencent.weibo.f.a(r10)
            java.util.HashMap r1 = r0.c(r11)     // Catch:{ Throwable -> 0x0050 }
            if (r1 != 0) goto L_0x0020
            cn.sharesdk.framework.PlatformActionListener r0 = r10.listener     // Catch:{ Throwable -> 0x0050 }
            if (r0 == 0) goto L_0x001f
            cn.sharesdk.framework.PlatformActionListener r0 = r10.listener     // Catch:{ Throwable -> 0x0050 }
            r1 = 8
            java.lang.Throwable r2 = new java.lang.Throwable     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r3 = " response is null"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0050 }
            r0.onError(r10, r1, r2)     // Catch:{ Throwable -> 0x0050 }
        L_0x001f:
            return
        L_0x0020:
            java.lang.String r0 = "errcode"
            boolean r0 = r1.containsKey(r0)     // Catch:{ Throwable -> 0x0050 }
            if (r0 == 0) goto L_0x005b
            java.lang.String r0 = "errcode"
            java.lang.Object r0 = r1.get(r0)     // Catch:{ Throwable -> 0x0050 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0050 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0050 }
            if (r0 == 0) goto L_0x005b
            cn.sharesdk.framework.PlatformActionListener r0 = r10.listener     // Catch:{ Throwable -> 0x0050 }
            if (r0 == 0) goto L_0x001f
            com.mob.tools.utils.Hashon r0 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x0050 }
            r0.<init>()     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r0 = r0.fromHashMap(r1)     // Catch:{ Throwable -> 0x0050 }
            cn.sharesdk.framework.PlatformActionListener r1 = r10.listener     // Catch:{ Throwable -> 0x0050 }
            r2 = 8
            java.lang.Throwable r3 = new java.lang.Throwable     // Catch:{ Throwable -> 0x0050 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0050 }
            r1.onError(r10, r2, r3)     // Catch:{ Throwable -> 0x0050 }
            goto L_0x001f
        L_0x0050:
            r0 = move-exception
            cn.sharesdk.framework.PlatformActionListener r1 = r10.listener
            if (r1 == 0) goto L_0x001f
            cn.sharesdk.framework.PlatformActionListener r1 = r10.listener
            r1.onError(r10, r9, r0)
            goto L_0x001f
        L_0x005b:
            java.lang.String r0 = "data"
            java.lang.Object r0 = r1.get(r0)     // Catch:{ Throwable -> 0x0050 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x0050 }
            if (r0 != 0) goto L_0x0076
            cn.sharesdk.framework.PlatformActionListener r0 = r10.listener     // Catch:{ Throwable -> 0x0050 }
            if (r0 == 0) goto L_0x001f
            cn.sharesdk.framework.PlatformActionListener r0 = r10.listener     // Catch:{ Throwable -> 0x0050 }
            r1 = 8
            java.lang.Throwable r2 = new java.lang.Throwable     // Catch:{ Throwable -> 0x0050 }
            r2.<init>()     // Catch:{ Throwable -> 0x0050 }
            r0.onError(r10, r1, r2)     // Catch:{ Throwable -> 0x0050 }
            goto L_0x001f
        L_0x0076:
            if (r11 != 0) goto L_0x0341
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "nickname"
            java.lang.String r4 = "nick"
            java.lang.Object r4 = r0.get(r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Throwable -> 0x0050 }
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "icon"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0050 }
            r4.<init>()     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r5 = "head"
            java.lang.Object r5 = r0.get(r5)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Throwable -> 0x0050 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r5 = "/100"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0050 }
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "isvip"
            java.lang.Object r1 = r0.get(r1)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x0050 }
            cn.sharesdk.framework.PlatformDb r2 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = "secretType"
            r2.put(r4, r1)     // Catch:{ Throwable -> 0x0050 }
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "secret"
            java.lang.String r4 = "verifyinfo"
            java.lang.Object r4 = r0.get(r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Throwable -> 0x0050 }
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "sex"
            java.lang.Object r1 = r0.get(r1)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "1"
            boolean r2 = r2.equals(r1)     // Catch:{ Throwable -> 0x0050 }
            if (r2 == 0) goto L_0x023f
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "gender"
            java.lang.String r4 = "0"
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
        L_0x00ea:
            java.lang.String r1 = "birth_year"
            java.lang.Object r1 = r0.get(r1)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "birth_month"
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = "birth_day"
            java.lang.Object r4 = r0.get(r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Throwable -> 0x0050 }
            boolean r5 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x0050 }
            if (r5 != 0) goto L_0x014a
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x0050 }
            if (r5 != 0) goto L_0x014a
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x0050 }
            if (r5 != 0) goto L_0x014a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0050 }
            r5.<init>()     // Catch:{ Throwable -> 0x0050 }
            java.lang.StringBuilder r1 = r5.append(r1)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r5 = "-"
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ Throwable -> 0x0050 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "-"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x0050 }
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0050 }
            cn.sharesdk.framework.PlatformDb r2 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = "birthday"
            long r6 = com.mob.tools.utils.ResHelper.dateStrToLong(r1)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = java.lang.String.valueOf(r6)     // Catch:{ Throwable -> 0x0050 }
            r2.put(r4, r1)     // Catch:{ Throwable -> 0x0050 }
        L_0x014a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0050 }
            r1.<init>()     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "http://t.qq.com/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "name"
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x0050 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0050 }
            cn.sharesdk.framework.PlatformDb r2 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = "snsUserUrl"
            r2.put(r4, r1)     // Catch:{ Throwable -> 0x0050 }
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "resume"
            java.lang.String r4 = "introduction"
            java.lang.Object r4 = r0.get(r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Throwable -> 0x0050 }
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "followerCount"
            java.lang.String r4 = "fansnum"
            java.lang.Object r4 = r0.get(r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Throwable -> 0x0050 }
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "favouriteCount"
            java.lang.String r4 = "idolnum"
            java.lang.Object r4 = r0.get(r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Throwable -> 0x0050 }
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "shareCount"
            java.lang.String r4 = "tweetnum"
            java.lang.Object r4 = r0.get(r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Throwable -> 0x0050 }
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "snsregat"
            java.lang.String r4 = "regtime"
            java.lang.Object r4 = r0.get(r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Throwable -> 0x0050 }
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "snsUserLevel"
            java.lang.String r4 = "level"
            java.lang.Object r4 = r0.get(r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Throwable -> 0x0050 }
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "edu"
            java.lang.Object r1 = r0.get(r1)     // Catch:{ Throwable -> 0x0050 }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ Throwable -> 0x0050 }
            org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ Throwable -> 0x0050 }
            r5.<init>()     // Catch:{ Throwable -> 0x0050 }
            if (r1 == 0) goto L_0x0286
            r4 = r3
        L_0x01e4:
            int r2 = r1.size()     // Catch:{ Throwable -> 0x0050 }
            if (r4 >= r2) goto L_0x0275
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0050 }
            r6.<init>()     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "school_type"
            r7 = 0
            r6.put(r2, r7)     // Catch:{ JSONException -> 0x026c }
            java.lang.String r7 = "school"
            java.lang.Object r2 = r1.get(r4)     // Catch:{ JSONException -> 0x026c }
            java.util.HashMap r2 = (java.util.HashMap) r2     // Catch:{ JSONException -> 0x026c }
            java.lang.String r8 = "schoolid"
            java.lang.Object r2 = r2.get(r8)     // Catch:{ JSONException -> 0x026c }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ JSONException -> 0x026c }
            r6.put(r7, r2)     // Catch:{ JSONException -> 0x026c }
            java.lang.String r7 = "classes"
            java.lang.Object r2 = r1.get(r4)     // Catch:{ JSONException -> 0x026c }
            java.util.HashMap r2 = (java.util.HashMap) r2     // Catch:{ JSONException -> 0x026c }
            java.lang.String r8 = "departmentid"
            java.lang.Object r2 = r2.get(r8)     // Catch:{ JSONException -> 0x026c }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ JSONException -> 0x026c }
            r6.put(r7, r2)     // Catch:{ JSONException -> 0x026c }
            java.lang.String r2 = "background"
            r7 = 0
            r6.put(r2, r7)     // Catch:{ JSONException -> 0x026c }
            java.lang.String r7 = "year"
            java.lang.Object r2 = r1.get(r4)     // Catch:{ Throwable -> 0x025d }
            java.util.HashMap r2 = (java.util.HashMap) r2     // Catch:{ Throwable -> 0x025d }
            java.lang.String r8 = "year"
            java.lang.Object r2 = r2.get(r8)     // Catch:{ Throwable -> 0x025d }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x025d }
            r6.put(r7, r2)     // Catch:{ Throwable -> 0x025d }
        L_0x0238:
            r5.put(r6)     // Catch:{ Throwable -> 0x0050 }
            int r2 = r4 + 1
            r4 = r2
            goto L_0x01e4
        L_0x023f:
            java.lang.String r2 = "2"
            boolean r1 = r2.equals(r1)     // Catch:{ Throwable -> 0x0050 }
            if (r1 == 0) goto L_0x0252
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "gender"
            java.lang.String r4 = "1"
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
            goto L_0x00ea
        L_0x0252:
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "gender"
            java.lang.String r4 = "2"
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
            goto L_0x00ea
        L_0x025d:
            r2 = move-exception
            com.mob.tools.log.NLog r7 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ JSONException -> 0x026c }
            r7.d(r2)     // Catch:{ JSONException -> 0x026c }
            java.lang.String r2 = "year"
            r7 = 0
            r6.put(r2, r7)     // Catch:{ JSONException -> 0x026c }
            goto L_0x0238
        L_0x026c:
            r2 = move-exception
            com.mob.tools.log.NLog r7 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ Throwable -> 0x0050 }
            r7.d(r2)     // Catch:{ Throwable -> 0x0050 }
            goto L_0x0238
        L_0x0275:
            int r1 = r5.length()     // Catch:{ Throwable -> 0x0050 }
            if (r1 <= 0) goto L_0x0286
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "educationJSONArrayStr"
            java.lang.String r4 = r5.toString()     // Catch:{ Throwable -> 0x0050 }
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x0050 }
        L_0x0286:
            org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ Throwable -> 0x0050 }
            r5.<init>()     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "comp"
            java.lang.Object r1 = r0.get(r1)     // Catch:{ Throwable -> 0x0050 }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ Throwable -> 0x0050 }
            if (r1 == 0) goto L_0x0341
            r4 = r3
        L_0x0296:
            int r2 = r1.size()     // Catch:{ Throwable -> 0x0050 }
            if (r4 >= r2) goto L_0x0330
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0050 }
            r6.<init>()     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r7 = "company"
            java.lang.Object r2 = r1.get(r4)     // Catch:{ JSONException -> 0x0318 }
            java.util.HashMap r2 = (java.util.HashMap) r2     // Catch:{ JSONException -> 0x0318 }
            java.lang.String r8 = "company_name"
            java.lang.Object r2 = r2.get(r8)     // Catch:{ JSONException -> 0x0318 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ JSONException -> 0x0318 }
            r6.put(r7, r2)     // Catch:{ JSONException -> 0x0318 }
            java.lang.String r7 = "dept"
            java.lang.Object r2 = r1.get(r4)     // Catch:{ JSONException -> 0x0318 }
            java.util.HashMap r2 = (java.util.HashMap) r2     // Catch:{ JSONException -> 0x0318 }
            java.lang.String r8 = "department_name"
            java.lang.Object r2 = r2.get(r8)     // Catch:{ JSONException -> 0x0318 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ JSONException -> 0x0318 }
            r6.put(r7, r2)     // Catch:{ JSONException -> 0x0318 }
            java.lang.String r7 = "start_date"
            java.lang.Object r2 = r1.get(r4)     // Catch:{ Throwable -> 0x0309 }
            java.util.HashMap r2 = (java.util.HashMap) r2     // Catch:{ Throwable -> 0x0309 }
            java.lang.String r8 = "begin_year"
            java.lang.Object r2 = r2.get(r8)     // Catch:{ Throwable -> 0x0309 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0309 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0309 }
            int r2 = r2 * 100
            r6.put(r7, r2)     // Catch:{ Throwable -> 0x0309 }
        L_0x02e4:
            java.lang.Object r2 = r1.get(r4)     // Catch:{ Throwable -> 0x0321 }
            java.util.HashMap r2 = (java.util.HashMap) r2     // Catch:{ Throwable -> 0x0321 }
            java.lang.String r7 = "end_year"
            java.lang.Object r2 = r2.get(r7)     // Catch:{ Throwable -> 0x0321 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0321 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0321 }
            r7 = 9999(0x270f, float:1.4012E-41)
            if (r2 < r7) goto L_0x02fb
            r2 = r3
        L_0x02fb:
            java.lang.String r7 = "end_date"
            int r2 = r2 * 100
            r6.put(r7, r2)     // Catch:{ Throwable -> 0x0321 }
        L_0x0302:
            r5.put(r6)     // Catch:{ Throwable -> 0x0050 }
            int r2 = r4 + 1
            r4 = r2
            goto L_0x0296
        L_0x0309:
            r2 = move-exception
            com.mob.tools.log.NLog r7 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ JSONException -> 0x0318 }
            r7.d(r2)     // Catch:{ JSONException -> 0x0318 }
            java.lang.String r2 = "start_date"
            r7 = 0
            r6.put(r2, r7)     // Catch:{ JSONException -> 0x0318 }
            goto L_0x02e4
        L_0x0318:
            r2 = move-exception
            com.mob.tools.log.NLog r7 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ Throwable -> 0x0050 }
            r7.d(r2)     // Catch:{ Throwable -> 0x0050 }
            goto L_0x0302
        L_0x0321:
            r2 = move-exception
            com.mob.tools.log.NLog r7 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ JSONException -> 0x0318 }
            r7.d(r2)     // Catch:{ JSONException -> 0x0318 }
            java.lang.String r2 = "end_date"
            r7 = 0
            r6.put(r2, r7)     // Catch:{ JSONException -> 0x0318 }
            goto L_0x0302
        L_0x0330:
            int r1 = r5.length()     // Catch:{ Throwable -> 0x0050 }
            if (r1 <= 0) goto L_0x0341
            cn.sharesdk.framework.PlatformDb r1 = r10.db     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r2 = "workJSONArrayStr"
            java.lang.String r3 = r5.toString()     // Catch:{ Throwable -> 0x0050 }
            r1.put(r2, r3)     // Catch:{ Throwable -> 0x0050 }
        L_0x0341:
            cn.sharesdk.framework.PlatformActionListener r1 = r10.listener     // Catch:{ Throwable -> 0x0050 }
            if (r1 == 0) goto L_0x001f
            cn.sharesdk.framework.PlatformActionListener r1 = r10.listener     // Catch:{ Throwable -> 0x0050 }
            r2 = 8
            r1.onComplete(r10, r2, r0)     // Catch:{ Throwable -> 0x0050 }
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.tencent.weibo.TencentWeibo.userInfor(java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    public void getFriendList(int i, int i2, String str) {
        f a2 = f.a((Platform) this);
        if (str == null) {
            try {
                str = this.db.get("name");
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 2, th);
                    return;
                }
                return;
            }
        }
        HashMap a3 = a2.a(i, i * i2, str);
        if (a3 == null) {
            if (this.listener != null) {
                this.listener.onError(this, 2, new Throwable("response is null"));
            }
        } else if (!a3.containsKey("errcode") || ((Integer) a3.get("errcode")).intValue() == 0) {
            HashMap hashMap = (HashMap) a3.get("data");
            if (hashMap == null) {
                if (this.listener != null) {
                    this.listener.onError(this, 2, new Throwable());
                }
            } else if (this.listener != null) {
                this.listener.onComplete(this, 2, hashMap);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 2, new Throwable(new Hashon().fromHashMap(a3)));
        }
    }

    /* access modifiers changed from: protected */
    public void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        HashMap a2 = f.a((Platform) this).a(str, str2, hashMap, hashMap2);
        if (a2 == null) {
            if (this.listener != null) {
                this.listener.onError(this, i, new Throwable("response is null"));
            }
        } else if (!a2.containsKey("errcode") || ((Integer) a2.get("errcode")).intValue() == 0) {
            if (this.listener != null) {
                this.listener.onComplete(this, i, a2);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, i, new Throwable(new Hashon().fromHashMap(a2)));
        }
    }

    /* access modifiers changed from: protected */
    public a filterShareContent(ShareParams shareParams, HashMap<String, Object> hashMap) {
        a aVar = new a();
        aVar.b = shareParams.getText();
        if (hashMap != null) {
            String str = (String) hashMap.get("imgurl");
            if (!TextUtils.isEmpty(str)) {
                aVar.d.add(str);
            }
            aVar.a = String.valueOf(hashMap.get(Config.FEED_LIST_ITEM_CUSTOM_ID));
        }
        aVar.g = hashMap;
        return aVar;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getFollowings(int i, int i2, String str) {
        f a2 = f.a((Platform) this);
        if (str == null) {
            try {
                str = this.db.get("name");
            } catch (Throwable th) {
                SSDKLog.b().d(th);
                return null;
            }
        }
        HashMap a3 = a2.a(i, i2, str);
        if (a3 == null) {
            return null;
        }
        if (a3.containsKey("errcode") && ((Integer) a3.get("errcode")).intValue() != 0) {
            SSDKLog.b().d(new Throwable(new Hashon().fromHashMap(a3)));
        }
        HashMap hashMap = (HashMap) a3.get("data");
        if (hashMap == null) {
            return null;
        }
        hashMap.put("current_cursor", Integer.valueOf(i2));
        return filterFriendshipInfo(2, hashMap);
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getFollowers(int i, int i2, String str) {
        f a2 = f.a((Platform) this);
        if (str == null) {
            try {
                str = this.db.get("name");
            } catch (Throwable th) {
                SSDKLog.b().d(th);
                return null;
            }
        }
        HashMap b2 = a2.b(i, i2, str);
        if (b2 == null) {
            return null;
        }
        if (b2.containsKey("errcode") && ((Integer) b2.get("errcode")).intValue() != 0) {
            SSDKLog.b().d(new Throwable(new Hashon().fromHashMap(b2)));
        }
        HashMap hashMap = (HashMap) b2.get("data");
        if (hashMap == null) {
            return null;
        }
        hashMap.put("current_cursor", Integer.valueOf(i2));
        return filterFriendshipInfo(11, hashMap);
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        int i2;
        int i3;
        HashMap hashMap2 = new HashMap();
        switch (i) {
            case 2:
                hashMap2.put("type", "FOLLOWING");
                break;
            case 10:
                hashMap2.put("type", "FRIENDS");
                break;
            case 11:
                hashMap2.put("type", "FOLLOWERS");
                break;
            default:
                return null;
        }
        hashMap2.put("snsplat", Integer.valueOf(getPlatformId()));
        hashMap2.put("snsuid", this.db.get("name"));
        if (hashMap.containsKey("curnum")) {
            i2 = Integer.parseInt(String.valueOf(hashMap.get("curnum")));
        } else {
            i2 = 0;
        }
        if (hashMap.containsKey("nextstartpos")) {
            i3 = Integer.parseInt(String.valueOf(hashMap.get("nextstartpos")));
        } else {
            i3 = 0;
        }
        if (i2 == 0) {
            return null;
        }
        Object obj = hashMap.get(Config.LAUNCH_INFO);
        if (obj == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) obj;
        if (arrayList2.size() <= 0) {
            return null;
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            HashMap hashMap3 = (HashMap) it.next();
            if (hashMap3 != null) {
                HashMap hashMap4 = new HashMap();
                hashMap4.put("snsuid", String.valueOf(hashMap3.get("name")));
                hashMap4.put("nickname", String.valueOf(hashMap3.get("nick")));
                String str = hashMap3.containsKey("head") ? String.valueOf(hashMap3.get("head")) : null;
                if (!TextUtils.isEmpty(str)) {
                    hashMap4.put(MessageKey.MSG_ICON, str + "/100");
                }
                hashMap4.put("secretType", String.valueOf(hashMap3.get("isvip")));
                String valueOf = String.valueOf(hashMap3.get("sex"));
                if ("1".equals(valueOf)) {
                    hashMap4.put("gender", "0");
                } else if ("2".equals(valueOf)) {
                    hashMap4.put("gender", "1");
                } else {
                    hashMap4.put("gender", "2");
                }
                hashMap4.put("snsUserUrl", "http://t.qq.com/" + String.valueOf(hashMap3.get("name")));
                hashMap4.put("followerCount", String.valueOf(hashMap3.get("fansnum")));
                hashMap4.put("favouriteCount", String.valueOf(hashMap3.get("idolnum")));
                arrayList.add(hashMap4);
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        String str2 = i3 + "_false";
        if (i3 == 0) {
            str2 = "0_true";
        }
        hashMap2.put("nextCursor", str2);
        hashMap2.put("list", arrayList);
        return hashMap2;
    }

    public boolean hasShareCallback() {
        return true;
    }
}
