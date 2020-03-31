package cn.sharesdk.tencent.qq;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.LinearLayout;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.tauth.b;
import com.tencent.tauth.c;
import com.tencent.tauth.d;
import java.io.File;
import java.util.HashMap;

/* compiled from: ShareActivity */
public class e extends FakeActivity {
    b a = new b() {
        public void onCancel() {
            if (e.this.d != null) {
                e.this.d.onCancel(e.this.b, 9);
            }
        }

        public void onComplete(Object obj) {
            try {
                if (e.this.d != null) {
                    e.this.d.onComplete(e.this.b, 9, new Hashon().fromJson(obj.toString()));
                }
            } catch (Throwable th) {
                SSDKLog.b().d("qqShareListener onComplete catch " + th, new Object[0]);
            }
        }

        public void onError(d dVar) {
            try {
                if (dVar.b != null) {
                    String valueOf = String.valueOf(dVar.b);
                    if (e.this.d != null) {
                        e.this.d.onError(e.this.b, 9, new Throwable(valueOf));
                    }
                }
            } catch (Throwable th) {
                SSDKLog.b().d("qqShareListener onError catch " + th, new Object[0]);
            }
        }
    };
    /* access modifiers changed from: private */
    public Platform b;
    private String c;
    /* access modifiers changed from: private */
    public PlatformActionListener d;
    private c e;

    public void a(Platform platform, PlatformActionListener platformActionListener) {
        this.b = platform;
        this.d = platformActionListener;
    }

    public void a(String str) {
        this.c = "tencent" + str;
    }

    public void onCreate() {
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
        } catch (Exception e2) {
            SSDKLog.b().d(e2);
        }
        Bundle extras = this.activity.getIntent().getExtras();
        String string = extras.getString("title");
        String string2 = extras.getString("titleUrl");
        String string3 = extras.getString("summary");
        String string4 = extras.getString("imageUrl");
        final String string5 = extras.getString("musicUrl");
        final String appName = DeviceHelper.getInstance(this.activity).getAppName();
        String string6 = extras.getString("appId");
        final int i = extras.getInt("hidden");
        String string7 = extras.getString("imagePath");
        String string8 = extras.getString("mini_program_appid");
        String string9 = extras.getString("mini_program_path");
        String string10 = extras.getString("mini_program_type");
        int i2 = extras.getInt("share_type");
        if (i2 == 15) {
            a(string6, i2, string, string3, string2, string4, string8, string9, string10);
            if (this.d != null) {
                this.d.onComplete(this.b, 9, new HashMap());
            }
        } else if (!TextUtils.isEmpty(string8) || !TextUtils.isEmpty(string9) || !TextUtils.isEmpty(string10) || !TextUtils.isEmpty(string) || !TextUtils.isEmpty(string3) || !TextUtils.isEmpty(string2) || ((!TextUtils.isEmpty(string7) && new File(string7).exists()) || TextUtils.isEmpty(string4))) {
            a(string, string2, string3, string4, string7, string5, appName, string6, i, string8, string9, string10, i2);
        } else {
            final String str = string4;
            final String str2 = string;
            final String str3 = string2;
            final String str4 = string3;
            final String str5 = string6;
            final String str6 = string8;
            final String str7 = string9;
            final String str8 = string10;
            final int i3 = i2;
            new Thread() {
                /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r14 = this;
                        cn.sharesdk.tencent.qq.e r0 = cn.sharesdk.tencent.qq.e.this     // Catch:{ Throwable -> 0x002a }
                        android.app.Activity r0 = r0.activity     // Catch:{ Throwable -> 0x002a }
                        java.lang.String r1 = r13     // Catch:{ Throwable -> 0x002a }
                        java.lang.String r5 = com.mob.tools.utils.BitmapHelper.downloadBitmap(r0, r1)     // Catch:{ Throwable -> 0x002a }
                    L_0x000c:
                        cn.sharesdk.tencent.qq.e r0 = cn.sharesdk.tencent.qq.e.this     // Catch:{ Throwable -> 0x0034 }
                        java.lang.String r1 = r14     // Catch:{ Throwable -> 0x0034 }
                        java.lang.String r2 = r15     // Catch:{ Throwable -> 0x0034 }
                        java.lang.String r3 = r16     // Catch:{ Throwable -> 0x0034 }
                        java.lang.String r4 = r13     // Catch:{ Throwable -> 0x0034 }
                        java.lang.String r6 = r17     // Catch:{ Throwable -> 0x0034 }
                        java.lang.String r7 = r18     // Catch:{ Throwable -> 0x0034 }
                        java.lang.String r8 = r19     // Catch:{ Throwable -> 0x0034 }
                        int r9 = r20     // Catch:{ Throwable -> 0x0034 }
                        java.lang.String r10 = r21     // Catch:{ Throwable -> 0x0034 }
                        java.lang.String r11 = r22     // Catch:{ Throwable -> 0x0034 }
                        java.lang.String r12 = r23     // Catch:{ Throwable -> 0x0034 }
                        int r13 = r24     // Catch:{ Throwable -> 0x0034 }
                        r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ Throwable -> 0x0034 }
                    L_0x0029:
                        return
                    L_0x002a:
                        r0 = move-exception
                        com.mob.tools.log.NLog r1 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ Throwable -> 0x0034 }
                        r1.d(r0)     // Catch:{ Throwable -> 0x0034 }
                        r5 = 0
                        goto L_0x000c
                    L_0x0034:
                        r0 = move-exception
                        com.mob.tools.log.NLog r1 = cn.sharesdk.framework.utils.SSDKLog.b()
                        r1.d(r0)
                        goto L_0x0029
                    */
                    throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.tencent.qq.e.AnonymousClass1.run():void");
                }
            }.start();
        }
    }

    private void a(String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.e = c.a(str, (Context) this.activity);
        Bundle bundle = new Bundle();
        bundle.putString("title", str2);
        bundle.putString("summary", str3);
        bundle.putString("targetUrl", str4);
        bundle.putString("imageUrl", str5);
        bundle.putString("mini_program_appid", str6);
        bundle.putString("mini_program_path", str7);
        bundle.putString("mini_program_type", str8);
        bundle.putInt("req_type", 7);
        if (this.e != null) {
            this.e.a(this.activity, bundle, this.a);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, String str9, String str10, String str11, int i2) {
        String b2 = b(str, str2, str3, str4, str5, str6, str7, str8, i, str9, str10, str11, i2);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(b2));
        try {
            int[] a2 = a();
            ReceiveActivity.setUriScheme(this.c);
            ReceiveActivity.setPlatformActionListener(this.d);
            if (a2.length <= 1 || (a2[0] < 4 && a2[1] < 6)) {
                intent.putExtra("key_request_code", 0);
            }
            intent.putExtra("pkg_name", this.activity.getPackageName());
            this.activity.startActivityForResult(intent, 256);
            if (VERSION.SDK_INT >= 28) {
                this.activity.finish();
            }
        } catch (Throwable th) {
            if (this.d != null) {
                this.d.onError(this.b, 9, th);
            }
            this.activity.finish();
        }
    }

    private String b(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, String str9, String str10, String str11, int i2) {
        String str12;
        String str13;
        String str14 = "mqqapi://share/to_fri?src_type=app&version=1&file_type=news";
        if (!TextUtils.isEmpty(str4)) {
            if (!TextUtils.isEmpty(str5)) {
                str4 = "";
            } else if (str4.startsWith("https")) {
                try {
                    str5 = BitmapHelper.downloadBitmap(this.activity, str4);
                    str4 = "";
                } catch (Throwable th) {
                    SSDKLog.b().d(th);
                    str5 = null;
                }
            }
        }
        if (!TextUtils.isEmpty(str4)) {
            str14 = str14 + "&image_url=" + Base64.encodeToString(str4.getBytes(), 2);
        }
        if (!TextUtils.isEmpty(str5)) {
            str14 = str14 + "&file_data=" + Base64.encodeToString(str5.getBytes(), 2);
        }
        if (!TextUtils.isEmpty(str)) {
            str14 = str14 + "&title=" + Base64.encodeToString(str.getBytes(), 2);
        }
        if (!TextUtils.isEmpty(str3)) {
            str14 = str14 + "&description=" + Base64.encodeToString(str3.getBytes(), 2);
        }
        if (!TextUtils.isEmpty(str7)) {
            if (str7.length() > 20) {
                str7 = str7.substring(0, 20) + "...";
            }
            str14 = str14 + "&app_name=" + Base64.encodeToString(str7.getBytes(), 2);
        }
        if (!TextUtils.isEmpty(str8)) {
            str14 = str14 + "&share_id=" + str8;
        }
        if (!TextUtils.isEmpty(str2)) {
            str14 = str14 + "&url=" + Base64.encodeToString(str2.getBytes(), 2);
        }
        if (!TextUtils.isEmpty(str3)) {
            str14 = str14 + "&share_qq_ext_str=" + Base64.encodeToString(str3.getBytes(), 2);
        }
        String str15 = "00";
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str5)) {
            str12 = str14 + "&req_type=" + Base64.encodeToString("5".getBytes(), 2);
        } else if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3) && TextUtils.isEmpty(str2)) {
            str12 = str14 + "&req_type=" + Base64.encodeToString("6".getBytes(), 2);
        } else if (!TextUtils.isEmpty(str6)) {
            str12 = (str14 + "&req_type=" + Base64.encodeToString("2".getBytes(), 2)) + "&audioUrl=" + Base64.encodeToString(str6.getBytes(), 2);
        } else {
            str12 = str14 + "&req_type=" + Base64.encodeToString("1".getBytes(), 2);
        }
        if (i == 1) {
            str13 = "10";
        } else {
            str13 = str15;
        }
        return str12 + "&cflag=" + Base64.encodeToString(str13.getBytes(), 2);
    }

    private int[] a() {
        String str;
        try {
            str = MobSDK.getContext().getPackageManager().getPackageInfo(TbsConfig.APP_QQ, 0).versionName;
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            str = "0";
        }
        String[] split = str.split("\\.");
        int[] iArr = new int[split.length];
        for (int i = 0; i < iArr.length; i++) {
            try {
                iArr[i] = ResHelper.parseInt(split[i]);
            } catch (Throwable th2) {
                SSDKLog.b().d(th2);
                iArr[i] = 0;
            }
        }
        return iArr;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 256 && i2 == 0 && VERSION.SDK_INT < 28) {
            this.d.onCancel(this.b, 9);
        }
        this.activity.finish();
    }
}
