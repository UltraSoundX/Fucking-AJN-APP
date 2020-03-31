package com.tencent.connect.c;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.b.b;
import com.tencent.open.a.f;
import com.tencent.open.d.c;
import com.tencent.open.d.g;
import com.tencent.open.d.j;
import com.tencent.tauth.d;
import java.io.File;
import java.util.ArrayList;

/* compiled from: ProGuard */
public class a extends com.tencent.connect.common.a {
    public String a = "";

    public a(Context context, b bVar) {
        super(bVar);
    }

    public void a(Activity activity, Bundle bundle, com.tencent.tauth.b bVar) {
        f.c("openSDK_LOG.QQShare", "shareToQQ() -- start.");
        String string = bundle.getString("imageUrl");
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("summary");
        String string4 = bundle.getString("targetUrl");
        String string5 = bundle.getString("imageLocalUrl");
        String string6 = bundle.getString("mini_program_appid");
        String string7 = bundle.getString("mini_program_path");
        int i = bundle.getInt("req_type", 1);
        f.c("openSDK_LOG.QQShare", "shareToQQ -- type: " + i);
        switch (i) {
            case 1:
                this.a = "1";
                break;
            case 2:
                this.a = "3";
                break;
            case 5:
                this.a = "2";
                break;
            case 6:
                this.a = "4";
                break;
            case 7:
                this.a = "9";
                break;
        }
        if (i == 6) {
            if (j.f(activity, "5.0.0")) {
                bVar.onError(new d(-15, "手Q版本过低，应用分享只支持手Q5.0及其以上版本", null));
                f.e("openSDK_LOG.QQShare", "shareToQQ, app share is not support below qq5.0.");
                com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, app share is not support below qq5.0.");
                return;
            }
            string4 = String.format("http://fusion.qq.com/cgi-bin/qzapps/unified_jump?appid=%1$s&from=%2$s&isOpenAppID=1", new Object[]{this.d.b(), "mqq"});
            bundle.putString("targetUrl", string4);
        }
        if (j.a() || !j.f(activity, "4.5.0")) {
            if (i == 5) {
                if (j.f(activity, "4.3.0")) {
                    bVar.onError(new d(-6, "低版本手Q不支持该项功能!", null));
                    f.e("openSDK_LOG.QQShare", "shareToQQ, version below 4.3 is not support.");
                    com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, version below 4.3 is not support.");
                    return;
                } else if (!j.g(string5)) {
                    bVar.onError(new d(-6, "非法的图片地址!", null));
                    f.e("openSDK_LOG.QQShare", "shareToQQ -- error: 非法的图片地址!");
                    com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "非法的图片地址!");
                    return;
                }
            }
            if (!(i == 5 || i == 7)) {
                if (TextUtils.isEmpty(string4) || (!string4.startsWith("http://") && !string4.startsWith("https://"))) {
                    bVar.onError(new d(-6, "传入参数有误!", null));
                    f.e("openSDK_LOG.QQShare", "shareToQQ, targetUrl is empty or illegal..");
                    com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, targetUrl is empty or illegal..");
                    return;
                } else if (TextUtils.isEmpty(string2)) {
                    bVar.onError(new d(-6, "title不能为空!", null));
                    f.e("openSDK_LOG.QQShare", "shareToQQ, title is empty.");
                    com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, title is empty.");
                    return;
                }
            }
            if (i == 7) {
                if (TextUtils.isEmpty(string6) || TextUtils.isEmpty(string7) || TextUtils.isEmpty(string4) || TextUtils.isEmpty(this.d.b())) {
                    bVar.onError(new d(-5, "传入参数有误!", "appid || path || url empty."));
                    return;
                } else if (g.a((Context) activity, "com.tencent.qqlite") == null && g.b(activity, "8.0.8") < 0) {
                    bVar.onError(new d(-5, "低版本手Q不支持该项功能!", "808以下不支持分享小程序"));
                    return;
                }
            }
            if (TextUtils.isEmpty(string) || string.startsWith("http://") || string.startsWith("https://") || new File(string).exists()) {
                if (!TextUtils.isEmpty(string2) && string2.length() > 128) {
                    bundle.putString("title", j.a(string2, 128, (String) null, (String) null));
                }
                if (!TextUtils.isEmpty(string3) && string3.length() > 512) {
                    bundle.putString("summary", j.a(string3, 512, (String) null, (String) null));
                }
                if (j.a((Context) activity, bundle.getInt("cflag", 0) == 1)) {
                    f.c("openSDK_LOG.QQShare", "shareToQQ, support share");
                    b(activity, bundle, bVar);
                } else {
                    try {
                        f.d("openSDK_LOG.QQShare", "shareToQQ, don't support share, will show download dialog");
                        new com.tencent.open.a(activity, "", a(""), null, this.d).show();
                    } catch (RuntimeException e) {
                        f.a("openSDK_LOG.QQShare", " shareToQQ, TDialog.show not in main thread", e);
                        e.printStackTrace();
                        bVar.onError(new d(-6, "没有在主线程调用！", null));
                    }
                }
                f.c("openSDK_LOG.QQShare", "shareToQQ() -- end.");
                return;
            }
            bVar.onError(new d(-6, "非法的图片地址!", null));
            f.e("openSDK_LOG.QQShare", "shareToQQ, image url is emprty or illegal.");
            com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, image url is emprty or illegal.");
            return;
        }
        bVar.onError(new d(-6, "分享图片失败，检测不到SD卡!", null));
        f.e("openSDK_LOG.QQShare", "shareToQQ sdcard is null--end");
        com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ sdcard is null");
    }

    private void b(Activity activity, Bundle bundle, com.tencent.tauth.b bVar) {
        f.c("openSDK_LOG.QQShare", "shareToMobileQQ() -- start.");
        String string = bundle.getString("imageUrl");
        final String string2 = bundle.getString("title");
        final String string3 = bundle.getString("summary");
        f.a("openSDK_LOG.QQShare", "shareToMobileQQ -- imageUrl: " + string);
        if (TextUtils.isEmpty(string)) {
            c(activity, bundle, bVar);
        } else if (!j.f(string)) {
            bundle.putString("imageUrl", null);
            if (j.f(activity, "4.3.0")) {
                f.b("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is < 4.3.0 ");
                c(activity, bundle, bVar);
            } else {
                f.b("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is > 4.3.0 ");
                final Bundle bundle2 = bundle;
                final com.tencent.tauth.b bVar2 = bVar;
                final Activity activity2 = activity;
                c.a((Context) activity, string, (c) new c() {
                    public void a(int i, String str) {
                        if (i == 0) {
                            bundle2.putString("imageLocalUrl", str);
                        } else if (TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
                            if (bVar2 != null) {
                                bVar2.onError(new d(-6, "获取分享图片失败!", null));
                                f.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: 获取分享图片失败!");
                            }
                            com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", a.this.d.b(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "获取分享图片失败!");
                            return;
                        }
                        a.this.c(activity2, bundle2, bVar2);
                    }

                    public void a(int i, ArrayList<String> arrayList) {
                    }
                });
            }
        } else if (!j.f(activity, "4.3.0")) {
            c(activity, bundle, bVar);
        } else {
            final Bundle bundle3 = bundle;
            final com.tencent.tauth.b bVar3 = bVar;
            final Activity activity3 = activity;
            new com.tencent.open.d.b(activity).a(string, (c) new c() {
                public void a(int i, String str) {
                    if (i == 0) {
                        bundle3.putString("imageLocalUrl", str);
                    } else if (TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
                        if (bVar3 != null) {
                            bVar3.onError(new d(-6, "获取分享图片失败!", null));
                            f.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: 获取分享图片失败!");
                        }
                        com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", a.this.d.b(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "获取分享图片失败!");
                        return;
                    }
                    a.this.c(activity3, bundle3, bVar3);
                }

                public void a(int i, ArrayList<String> arrayList) {
                }
            });
        }
        f.c("openSDK_LOG.QQShare", "shareToMobileQQ() -- end");
    }

    /* access modifiers changed from: private */
    public void c(Activity activity, Bundle bundle, com.tencent.tauth.b bVar) {
        f.c("openSDK_LOG.QQShare", "doShareToQQ() -- start");
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_fri?src_type=app&version=1&file_type=news");
        String string = bundle.getString("imageUrl");
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("summary");
        String string4 = bundle.getString("targetUrl");
        String string5 = bundle.getString("audio_url");
        int i = bundle.getInt("req_type", 1);
        String string6 = bundle.getString("share_to_qq_ark_info");
        String string7 = bundle.getString("mini_program_appid");
        String string8 = bundle.getString("mini_program_path");
        String string9 = bundle.getString("mini_program_type");
        int i2 = bundle.getInt("cflag", 0);
        String string10 = bundle.getString("share_qq_ext_str");
        String a2 = j.a((Context) activity);
        if (a2 == null) {
            a2 = bundle.getString("appName");
        }
        String string11 = bundle.getString("imageLocalUrl");
        String b = this.d.b();
        String d = this.d.d();
        f.a("openSDK_LOG.QQShare", "doShareToQQ -- openid: " + d);
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&image_url=" + Base64.encodeToString(j.h(string), 2));
        }
        if (!TextUtils.isEmpty(string11)) {
            stringBuffer.append("&file_data=" + Base64.encodeToString(j.h(string11), 2));
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&title=" + Base64.encodeToString(j.h(string2), 2));
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&description=" + Base64.encodeToString(j.h(string3), 2));
        }
        if (!TextUtils.isEmpty(b)) {
            stringBuffer.append("&share_id=" + b);
        }
        if (!TextUtils.isEmpty(string4)) {
            stringBuffer.append("&url=" + Base64.encodeToString(j.h(string4), 2));
        }
        if (!TextUtils.isEmpty(a2)) {
            if (a2.length() > 20) {
                a2 = a2.substring(0, 20) + "...";
            }
            stringBuffer.append("&app_name=" + Base64.encodeToString(j.h(a2), 2));
        }
        if (!TextUtils.isEmpty(d)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(j.h(d), 2));
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append("&audioUrl=" + Base64.encodeToString(j.h(string5), 2));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(j.h(String.valueOf(i)), 2));
        if (!TextUtils.isEmpty(string7)) {
            stringBuffer.append("&mini_program_appid=" + Base64.encodeToString(j.h(String.valueOf(string7)), 2));
        }
        if (!TextUtils.isEmpty(string8)) {
            stringBuffer.append("&mini_program_path=" + Base64.encodeToString(j.h(String.valueOf(string8)), 2));
        }
        if (!TextUtils.isEmpty(string9)) {
            stringBuffer.append("&mini_program_type=" + Base64.encodeToString(j.h(String.valueOf(string9)), 2));
        }
        if (!TextUtils.isEmpty(string6)) {
            stringBuffer.append("&share_to_qq_ark_info=" + Base64.encodeToString(j.h(string6), 2));
        }
        if (!TextUtils.isEmpty(string10)) {
            stringBuffer.append("&share_qq_ext_str=" + Base64.encodeToString(j.h(string10), 2));
        }
        stringBuffer.append("&cflag=" + Base64.encodeToString(j.h(String.valueOf(i2)), 2));
        f.a("openSDK_LOG.QQShare", "doShareToQQ -- url: " + stringBuffer.toString());
        com.tencent.connect.a.a.a(com.tencent.open.d.d.a(), this.d, "requireApi", "shareToNativeQQ");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (j.f(activity, "4.6.0")) {
            f.c("openSDK_LOG.QQShare", "doShareToQQ, qqver below 4.6.");
            if (a(intent)) {
                com.tencent.connect.common.b.a().a(11103, bVar);
                a(activity, intent, 11103);
            }
        } else {
            f.c("openSDK_LOG.QQShare", "doShareToQQ, qqver greater than 4.6.");
            if (com.tencent.connect.common.b.a().a("shareToQQ", bVar) != null) {
                f.c("openSDK_LOG.QQShare", "doShareToQQ, last listener is not null, cancel it.");
            }
            if (a(intent)) {
                a(activity, 10103, intent, true);
            }
        }
        String str = "10";
        if (i2 == 1) {
            str = "11";
        }
        if (a(intent)) {
            com.tencent.open.b.d.a().a(this.d.d(), this.d.b(), "ANDROIDQQ.SHARETOQQ.XX", str, "3", "0", this.a, "0", "1", "0");
            com.tencent.open.b.d.a().a(0, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
        } else {
            com.tencent.open.b.d.a().a(this.d.d(), this.d.b(), "ANDROIDQQ.SHARETOQQ.XX", str, "3", "1", this.a, "0", "1", "0");
            com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
        }
        f.c("openSDK_LOG.QQShare", "doShareToQQ() --end");
    }
}
