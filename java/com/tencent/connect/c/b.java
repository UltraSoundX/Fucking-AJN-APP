package com.tencent.connect.c;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tencent.connect.common.a;
import com.tencent.open.a.f;
import com.tencent.open.d.c;
import com.tencent.open.d.g;
import com.tencent.open.d.j;
import com.tencent.tauth.d;
import java.util.ArrayList;

/* compiled from: ProGuard */
public class b extends a {
    public String a = "";
    private boolean b = true;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;

    public b(Context context, com.tencent.connect.b.b bVar) {
        super(bVar);
    }

    public void a(final Activity activity, final Bundle bundle, final com.tencent.tauth.b bVar) {
        String str;
        String str2;
        f.c("openSDK_LOG.QzoneShare", "shareToQzone() -- start");
        if (bundle == null) {
            bVar.onError(new d(-6, "传入参数不可以为空", null));
            f.e("openSDK_LOG.QzoneShare", "shareToQzone() params is null");
            com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "传入参数不可以为空");
            return;
        }
        String string = bundle.getString("title");
        String string2 = bundle.getString("summary");
        String string3 = bundle.getString("targetUrl");
        String string4 = bundle.getString("mini_program_appid");
        String string5 = bundle.getString("mini_program_path");
        ArrayList stringArrayList = bundle.getStringArrayList("imageUrl");
        String a2 = j.a((Context) activity);
        if (a2 == null) {
            a2 = bundle.getString("appName");
        } else if (a2.length() > 20) {
            a2 = a2.substring(0, 20) + "...";
        }
        int i2 = bundle.getInt("req_type");
        f.e("openSDK_LOG.QzoneShare", "shareToQzone() get SHARE_TO_QZONE_KEY_TYPE: " + i2);
        switch (i2) {
            case 1:
                this.a = "1";
                break;
            case 5:
                this.a = "2";
                break;
            case 6:
                this.a = "4";
                break;
            default:
                this.a = "1";
                break;
        }
        switch (i2) {
            case 1:
                f.e("openSDK_LOG.QzoneShare", "-->shareToQzone, SHARE_TO_QZONE_TYPE_IMAGE_TEXT needTitle = true");
                this.b = true;
                this.i = false;
                this.j = true;
                this.k = false;
                String str3 = string3;
                str = string;
                str2 = str3;
                break;
            case 5:
                bVar.onError(new d(-5, "请选择支持的分享类型", null));
                f.e("openSDK_LOG.QzoneShare", "shareToQzone() error--end请选择支持的分享类型");
                com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() 请选择支持的分享类型");
                return;
            case 6:
                if (!j.g(activity, "5.0.0")) {
                    String format = String.format("http://fusion.qq.com/cgi-bin/qzapps/unified_jump?appid=%1$s&from=%2$s&isOpenAppID=1", new Object[]{this.d.b(), "mqq"});
                    bundle.putString("targetUrl", format);
                    String str4 = format;
                    str = string;
                    str2 = str4;
                    break;
                } else {
                    bVar.onError(new d(-15, "手Q版本过低，应用分享只支持手Q5.0及其以上版本", null));
                    f.e("openSDK_LOG.QzoneShare", "-->shareToQzone, app share is not support below qq5.0.");
                    com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone, app share is not support below qq5.0.");
                    return;
                }
            case 7:
                if (TextUtils.isEmpty(string4) || TextUtils.isEmpty(string5)) {
                    bVar.onError(new d(-5, "传入参数有误!", "appid or path empty."));
                }
                this.j = false;
                this.k = false;
                this.b = false;
                String str5 = string3;
                str = string;
                str2 = str5;
                break;
            default:
                if (!j.d(string) || !j.d(string2)) {
                    this.b = true;
                } else if (stringArrayList == null || stringArrayList.size() == 0) {
                    string = "来自" + a2 + "的分享";
                    this.b = true;
                } else {
                    this.b = false;
                }
                this.i = false;
                f.e("openSDK_LOG.QzoneShare", "-->shareToQzone, default needTitle = true, shareType = " + i2);
                this.j = true;
                this.k = false;
                String str6 = string3;
                str = string;
                str2 = str6;
                break;
        }
        if (j.a() || !j.g(activity, "4.5.0")) {
            if (this.b) {
                if (TextUtils.isEmpty(str2)) {
                    bVar.onError(new d(-5, "targetUrl为必填项，请补充后分享", null));
                    f.e("openSDK_LOG.QzoneShare", "shareToQzone() targetUrl null error--end");
                    com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "targetUrl为必填项，请补充后分享");
                    return;
                } else if (!j.f(str2)) {
                    bVar.onError(new d(-5, "targetUrl有误", null));
                    f.e("openSDK_LOG.QzoneShare", "shareToQzone() targetUrl error--end");
                    com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "targetUrl有误");
                    return;
                }
            }
            if (this.i) {
                bundle.putString("title", "");
                bundle.putString("summary", "");
            } else if (!this.j || !j.d(str)) {
                if (!j.d(str) && str.length() > 200) {
                    bundle.putString("title", j.a(str, 200, (String) null, (String) null));
                }
                if (!j.d(string2) && string2.length() > 600) {
                    bundle.putString("summary", j.a(string2, 600, (String) null, (String) null));
                }
            } else {
                bVar.onError(new d(-6, "title不能为空!", null));
                f.e("openSDK_LOG.QzoneShare", "shareToQzone() title is null--end");
                com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() title is null");
                return;
            }
            if (!TextUtils.isEmpty(a2)) {
                bundle.putString("appName", a2);
            }
            if (stringArrayList != null && (stringArrayList == null || stringArrayList.size() != 0)) {
                int i3 = 0;
                while (true) {
                    int i4 = i3;
                    if (i4 < stringArrayList.size()) {
                        String str7 = (String) stringArrayList.get(i4);
                        if (!j.f(str7) && !j.g(str7)) {
                            stringArrayList.remove(i4);
                            i4--;
                        }
                        i3 = i4 + 1;
                    } else if (stringArrayList.size() == 0) {
                        bVar.onError(new d(-6, "非法的图片地址!", null));
                        f.e("openSDK_LOG.QzoneShare", "shareToQzone() MSG_PARAM_IMAGE_URL_FORMAT_ERROR--end");
                        com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() 非法的图片地址!");
                        return;
                    } else {
                        bundle.putStringArrayList("imageUrl", stringArrayList);
                    }
                }
            } else if (this.k) {
                bVar.onError(new d(-6, "纯图分享，imageUrl 不能为空", null));
                f.e("openSDK_LOG.QzoneShare", "shareToQzone() imageUrl is null -- end");
                com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() imageUrl is null");
                return;
            }
            if (!j.g(activity, "4.6.0")) {
                f.c("openSDK_LOG.QzoneShare", "shareToQzone() qqver greater than 4.6.0");
                c.a((Context) activity, stringArrayList, (c) new c() {
                    public void a(int i, String str) {
                        bVar.onError(new d(-6, "非法的图片地址!", null));
                    }

                    public void a(int i, ArrayList<String> arrayList) {
                        if (i == 0) {
                            bundle.putStringArrayList("imageUrl", arrayList);
                        }
                        b.this.b(activity, bundle, bVar);
                    }
                });
            } else if (g.b(activity, "4.2.0") < 0 || g.b(activity, "4.6.0") >= 0) {
                f.d("openSDK_LOG.QzoneShare", "shareToQzone() qqver below 4.2.0, will show download dialog");
                new com.tencent.open.a(activity, "", a(""), null, this.d).show();
            } else {
                f.d("openSDK_LOG.QzoneShare", "shareToQzone() qqver between 4.2.0 and 4.6.0, will use qqshare");
                a aVar = new a(activity, this.d);
                if (stringArrayList != null && stringArrayList.size() > 0) {
                    String str8 = (String) stringArrayList.get(0);
                    if (i2 != 5 || j.g(str8)) {
                        bundle.putString("imageLocalUrl", str8);
                    } else {
                        bVar.onError(new d(-6, "手Q版本过低，纯图分享不支持网路图片", null));
                        f.e("openSDK_LOG.QzoneShare", "shareToQzone()手Q版本过低，纯图分享不支持网路图片");
                        com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone()手Q版本过低，纯图分享不支持网路图片");
                        return;
                    }
                }
                if (!j.g(activity, "4.5.0")) {
                    bundle.putInt("cflag", 1);
                }
                aVar.a(activity, bundle, bVar);
            }
            f.c("openSDK_LOG.QzoneShare", "shareToQzone() --end");
            return;
        }
        bVar.onError(new d(-6, "分享图片失败，检测不到SD卡!", null));
        f.e("openSDK_LOG.QzoneShare", "shareToQzone() sdcard is null--end");
        com.tencent.open.b.d.a().a(1, "SHARE_CHECK_SDK", "1000", this.d.b(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "分享图片失败，检测不到SD卡!");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0219  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x028a  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x02b3  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x02dc  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0301  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0399  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x03c4  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x041a  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x044a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.app.Activity r23, android.os.Bundle r24, com.tencent.tauth.b r25) {
        /*
            r22 = this;
            java.lang.String r2 = "openSDK_LOG.QzoneShare"
            java.lang.String r3 = "doshareToQzone() --start"
            com.tencent.open.a.f.c(r2, r3)
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            java.lang.String r2 = "mqqapi://share/to_qzone?src_type=app&version=1&file_type=news"
            r6.<init>(r2)
            java.lang.String r2 = "imageUrl"
            r0 = r24
            java.util.ArrayList r7 = r0.getStringArrayList(r2)
            java.lang.String r2 = "title"
            r0 = r24
            java.lang.String r8 = r0.getString(r2)
            java.lang.String r2 = "summary"
            r0 = r24
            java.lang.String r9 = r0.getString(r2)
            java.lang.String r2 = "targetUrl"
            r0 = r24
            java.lang.String r10 = r0.getString(r2)
            java.lang.String r2 = "audio_url"
            r0 = r24
            java.lang.String r11 = r0.getString(r2)
            java.lang.String r2 = "req_type"
            r3 = 1
            r0 = r24
            int r12 = r0.getInt(r2, r3)
            java.lang.String r2 = "appName"
            r0 = r24
            java.lang.String r13 = r0.getString(r2)
            java.lang.String r2 = "mini_program_appid"
            r0 = r24
            java.lang.String r14 = r0.getString(r2)
            java.lang.String r2 = "mini_program_path"
            r0 = r24
            java.lang.String r15 = r0.getString(r2)
            java.lang.String r2 = "mini_program_type"
            r0 = r24
            java.lang.String r16 = r0.getString(r2)
            java.lang.String r2 = "cflag"
            r3 = 0
            r0 = r24
            int r17 = r0.getInt(r2, r3)
            java.lang.String r2 = "share_qq_ext_str"
            r0 = r24
            java.lang.String r18 = r0.getString(r2)
            java.lang.String r3 = ""
            java.lang.String r2 = "extMap"
            r0 = r24
            android.os.Bundle r4 = r0.getBundle(r2)     // Catch:{ Exception -> 0x00a1 }
            if (r4 == 0) goto L_0x049a
            java.util.Set r5 = r4.keySet()     // Catch:{ Exception -> 0x00a1 }
            org.json.JSONObject r19 = new org.json.JSONObject     // Catch:{ Exception -> 0x00a1 }
            r19.<init>()     // Catch:{ Exception -> 0x00a1 }
            java.util.Iterator r20 = r5.iterator()     // Catch:{ Exception -> 0x00a1 }
        L_0x0089:
            boolean r2 = r20.hasNext()     // Catch:{ Exception -> 0x00a1 }
            if (r2 == 0) goto L_0x0107
            java.lang.Object r2 = r20.next()     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00a1 }
            java.lang.Object r21 = r4.get(r2)     // Catch:{ Exception -> 0x00a1 }
            r0 = r19
            r1 = r21
            r0.put(r2, r1)     // Catch:{ Exception -> 0x00a1 }
            goto L_0x0089
        L_0x00a1:
            r2 = move-exception
            java.lang.String r4 = "openSDK_LOG.QzoneShare"
            java.lang.String r5 = "ShareToQzone()  --error parse extmap"
            com.tencent.open.a.f.a(r4, r5, r2)
        L_0x00a9:
            r0 = r22
            com.tencent.connect.b.b r2 = r0.d
            java.lang.String r19 = r2.b()
            r0 = r22
            com.tencent.connect.b.b r2 = r0.d
            java.lang.String r20 = r2.d()
            java.lang.String r2 = "openSDK_LOG.QzoneShare"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "openId:"
            java.lang.StringBuilder r4 = r4.append(r5)
            r0 = r20
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.tencent.open.a.f.a(r2, r4)
            if (r7 == 0) goto L_0x013c
            java.lang.StringBuffer r21 = new java.lang.StringBuffer
            r21.<init>()
            int r2 = r7.size()
            r4 = 9
            if (r2 <= r4) goto L_0x0113
            r2 = 9
            r4 = r2
        L_0x00e5:
            r2 = 0
            r5 = r2
        L_0x00e7:
            if (r5 >= r4) goto L_0x0119
            java.lang.Object r2 = r7.get(r5)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r2 = java.net.URLEncoder.encode(r2)
            r0 = r21
            r0.append(r2)
            int r2 = r4 + -1
            if (r5 == r2) goto L_0x0103
            java.lang.String r2 = ";"
            r0 = r21
            r0.append(r2)
        L_0x0103:
            int r2 = r5 + 1
            r5 = r2
            goto L_0x00e7
        L_0x0107:
            int r2 = r5.size()     // Catch:{ Exception -> 0x00a1 }
            if (r2 <= 0) goto L_0x049a
            java.lang.String r2 = r19.toString()     // Catch:{ Exception -> 0x00a1 }
        L_0x0111:
            r3 = r2
            goto L_0x00a9
        L_0x0113:
            int r2 = r7.size()
            r4 = r2
            goto L_0x00e5
        L_0x0119:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&image_url="
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = r21.toString()
            byte[] r4 = com.tencent.open.d.j.h(r4)
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x013c:
            boolean r2 = android.text.TextUtils.isEmpty(r8)
            if (r2 != 0) goto L_0x0161
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&title="
            java.lang.StringBuilder r2 = r2.append(r4)
            byte[] r4 = com.tencent.open.d.j.h(r8)
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x0161:
            boolean r2 = android.text.TextUtils.isEmpty(r9)
            if (r2 != 0) goto L_0x0186
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&description="
            java.lang.StringBuilder r2 = r2.append(r4)
            byte[] r4 = com.tencent.open.d.j.h(r9)
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x0186:
            boolean r2 = android.text.TextUtils.isEmpty(r19)
            if (r2 != 0) goto L_0x01a4
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&share_id="
            java.lang.StringBuilder r2 = r2.append(r4)
            r0 = r19
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x01a4:
            boolean r2 = android.text.TextUtils.isEmpty(r10)
            if (r2 != 0) goto L_0x01c9
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&url="
            java.lang.StringBuilder r2 = r2.append(r4)
            byte[] r4 = com.tencent.open.d.j.h(r10)
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x01c9:
            boolean r2 = android.text.TextUtils.isEmpty(r13)
            if (r2 != 0) goto L_0x01ee
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&app_name="
            java.lang.StringBuilder r2 = r2.append(r4)
            byte[] r4 = com.tencent.open.d.j.h(r13)
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x01ee:
            boolean r2 = com.tencent.open.d.j.d(r20)
            if (r2 != 0) goto L_0x0213
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&open_id="
            java.lang.StringBuilder r2 = r2.append(r4)
            byte[] r4 = com.tencent.open.d.j.h(r20)
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x0213:
            boolean r2 = com.tencent.open.d.j.d(r11)
            if (r2 != 0) goto L_0x0238
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&audioUrl="
            java.lang.StringBuilder r2 = r2.append(r4)
            byte[] r4 = com.tencent.open.d.j.h(r11)
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x0238:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&req_type="
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = java.lang.String.valueOf(r12)
            byte[] r4 = com.tencent.open.d.j.h(r4)
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
            boolean r2 = android.text.TextUtils.isEmpty(r14)
            if (r2 != 0) goto L_0x0284
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&mini_program_appid="
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = java.lang.String.valueOf(r14)
            byte[] r4 = com.tencent.open.d.j.h(r4)
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x0284:
            boolean r2 = android.text.TextUtils.isEmpty(r15)
            if (r2 != 0) goto L_0x02ad
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&mini_program_path="
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = java.lang.String.valueOf(r15)
            byte[] r4 = com.tencent.open.d.j.h(r4)
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x02ad:
            boolean r2 = android.text.TextUtils.isEmpty(r16)
            if (r2 != 0) goto L_0x02d6
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&mini_program_type="
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = java.lang.String.valueOf(r16)
            byte[] r4 = com.tencent.open.d.j.h(r4)
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x02d6:
            boolean r2 = com.tencent.open.d.j.d(r18)
            if (r2 != 0) goto L_0x02fb
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&share_qq_ext_str="
            java.lang.StringBuilder r2 = r2.append(r4)
            byte[] r4 = com.tencent.open.d.j.h(r18)
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x02fb:
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            if (r2 != 0) goto L_0x0320
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "&share_qzone_ext_str="
            java.lang.StringBuilder r2 = r2.append(r4)
            byte[] r3 = com.tencent.open.d.j.h(r3)
            r4 = 2
            java.lang.String r3 = android.util.Base64.encodeToString(r3, r4)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x0320:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "&cflag="
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = java.lang.String.valueOf(r17)
            byte[] r3 = com.tencent.open.d.j.h(r3)
            r4 = 2
            java.lang.String r3 = android.util.Base64.encodeToString(r3, r4)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r6.append(r2)
            java.lang.String r2 = "openSDK_LOG.QzoneShare"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "doshareToQzone, url: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = r6.toString()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.tencent.open.a.f.a(r2, r3)
            android.content.Context r2 = com.tencent.open.d.d.a()
            r0 = r22
            com.tencent.connect.b.b r3 = r0.d
            java.lang.String r4 = "requireApi"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]
            r7 = 0
            java.lang.String r8 = "shareToNativeQQ"
            r5[r7] = r8
            com.tencent.connect.a.a.a(r2, r3, r4, r5)
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r3 = "android.intent.action.VIEW"
            r2.<init>(r3)
            java.lang.String r3 = r6.toString()
            android.net.Uri r3 = android.net.Uri.parse(r3)
            r2.setData(r3)
            java.lang.String r3 = "pkg_name"
            java.lang.String r4 = r23.getPackageName()
            r2.putExtra(r3, r4)
            java.lang.String r3 = "4.6.0"
            r0 = r23
            boolean r3 = com.tencent.open.d.j.g(r0, r3)
            if (r3 == 0) goto L_0x041a
            r0 = r22
            boolean r3 = r0.a(r2)
            if (r3 == 0) goto L_0x03b5
            com.tencent.connect.common.b r3 = com.tencent.connect.common.b.a()
            r4 = 11104(0x2b60, float:1.556E-41)
            r0 = r25
            r3.a(r4, r0)
            r3 = 11104(0x2b60, float:1.556E-41)
            r0 = r22
            r1 = r23
            r0.a(r1, r2, r3)
        L_0x03b5:
            java.lang.String r3 = "openSDK_LOG.QzoneShare"
            java.lang.String r4 = "doShareToQzone() -- QQ Version is < 4.6.0"
            com.tencent.open.a.f.c(r3, r4)
        L_0x03bc:
            r0 = r22
            boolean r2 = r0.a(r2)
            if (r2 == 0) goto L_0x044a
            com.tencent.open.b.d r2 = com.tencent.open.b.d.a()
            r0 = r22
            com.tencent.connect.b.b r3 = r0.d
            java.lang.String r3 = r3.d()
            r0 = r22
            com.tencent.connect.b.b r4 = r0.d
            java.lang.String r4 = r4.b()
            java.lang.String r5 = "ANDROIDQQ.SHARETOQZ.XX"
            java.lang.String r6 = "11"
            java.lang.String r7 = "3"
            java.lang.String r8 = "0"
            r0 = r22
            java.lang.String r9 = r0.a
            java.lang.String r10 = "0"
            java.lang.String r11 = "1"
            java.lang.String r12 = "0"
            r2.a(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            com.tencent.open.b.d r2 = com.tencent.open.b.d.a()
            r3 = 0
            java.lang.String r4 = "SHARE_CHECK_SDK"
            java.lang.String r5 = "1000"
            r0 = r22
            com.tencent.connect.b.b r6 = r0.d
            java.lang.String r6 = r6.b()
            r7 = 4
            java.lang.String r7 = java.lang.String.valueOf(r7)
            long r8 = android.os.SystemClock.elapsedRealtime()
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r9 = 0
            r10 = 1
            java.lang.String r11 = ""
            r2.a(r3, r4, r5, r6, r7, r8, r9, r10, r11)
        L_0x0412:
            java.lang.String r2 = "openSDK_LOG"
            java.lang.String r3 = "doShareToQzone() --end"
            com.tencent.open.a.f.c(r2, r3)
            return
        L_0x041a:
            java.lang.String r3 = "openSDK_LOG.QzoneShare"
            java.lang.String r4 = "doShareToQzone() -- QQ Version is > 4.6.0"
            com.tencent.open.a.f.c(r3, r4)
            com.tencent.connect.common.b r3 = com.tencent.connect.common.b.a()
            java.lang.String r4 = "shareToQzone"
            r0 = r25
            java.lang.Object r3 = r3.a(r4, r0)
            if (r3 == 0) goto L_0x0436
            java.lang.String r3 = "openSDK_LOG.QzoneShare"
            java.lang.String r4 = "doShareToQzone() -- do listener onCancel()"
            com.tencent.open.a.f.c(r3, r4)
        L_0x0436:
            r0 = r22
            boolean r3 = r0.a(r2)
            if (r3 == 0) goto L_0x03bc
            r3 = 10104(0x2778, float:1.4159E-41)
            r4 = 0
            r0 = r22
            r1 = r23
            r0.a(r1, r3, r2, r4)
            goto L_0x03bc
        L_0x044a:
            com.tencent.open.b.d r2 = com.tencent.open.b.d.a()
            r0 = r22
            com.tencent.connect.b.b r3 = r0.d
            java.lang.String r3 = r3.d()
            r0 = r22
            com.tencent.connect.b.b r4 = r0.d
            java.lang.String r4 = r4.b()
            java.lang.String r5 = "ANDROIDQQ.SHARETOQZ.XX"
            java.lang.String r6 = "11"
            java.lang.String r7 = "3"
            java.lang.String r8 = "1"
            r0 = r22
            java.lang.String r9 = r0.a
            java.lang.String r10 = "0"
            java.lang.String r11 = "1"
            java.lang.String r12 = "0"
            r2.a(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            com.tencent.open.b.d r2 = com.tencent.open.b.d.a()
            r3 = 1
            java.lang.String r4 = "SHARE_CHECK_SDK"
            java.lang.String r5 = "1000"
            r0 = r22
            com.tencent.connect.b.b r6 = r0.d
            java.lang.String r6 = r6.b()
            r7 = 4
            java.lang.String r7 = java.lang.String.valueOf(r7)
            long r8 = android.os.SystemClock.elapsedRealtime()
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r9 = 0
            r10 = 1
            java.lang.String r11 = "hasActivityForIntent fail"
            r2.a(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            goto L_0x0412
        L_0x049a:
            r2 = r3
            goto L_0x0111
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.c.b.b(android.app.Activity, android.os.Bundle, com.tencent.tauth.b):void");
    }
}
