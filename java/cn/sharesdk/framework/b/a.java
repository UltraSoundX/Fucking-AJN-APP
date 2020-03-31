package cn.sharesdk.framework.b;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.b.a.e;
import cn.sharesdk.framework.b.b.b;
import cn.sharesdk.framework.b.b.c;
import cn.sharesdk.framework.b.b.f;
import cn.sharesdk.framework.utils.SSDKLog;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import com.tencent.android.tpush.common.Constants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

/* compiled from: EventManager */
public class a {
    private static a a;
    private c b = new c();
    private DeviceHelper c = DeviceHelper.getInstance(MobSDK.getContext());
    private e d = e.a();
    private boolean e = true;

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    private a() {
    }

    public void a(String str) {
        if (this.b != null && !TextUtils.isEmpty(str)) {
            this.b.a(str);
        }
    }

    public void b() {
        try {
            String networkType = this.c.getNetworkType();
            if (!"none".equals(networkType) && !TextUtils.isEmpty(networkType)) {
                long longValue = this.d.l().longValue();
                long currentTimeMillis = System.currentTimeMillis();
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(longValue);
                int i = instance.get(5);
                instance.setTimeInMillis(currentTimeMillis);
                int i2 = instance.get(5);
                if (currentTimeMillis - longValue >= 86400000 || i != i2) {
                    HashMap a2 = this.b.a();
                    this.d.a(a2.containsKey("res") ? "true".equals(String.valueOf(a2.get("res"))) : true);
                    if (a2 != null && a2.size() > 0) {
                        this.d.b(System.currentTimeMillis());
                    }
                }
            }
        } catch (Throwable th) {
            SSDKLog.b().d(th);
        }
    }

    public void c() {
        try {
            String networkType = this.c.getNetworkType();
            if (!"none".equals(networkType) && !TextUtils.isEmpty(networkType) && this.d.k()) {
                this.d.a(System.currentTimeMillis());
                HashMap c2 = this.b.c();
                if (!c2.containsKey(NotificationCompat.CATEGORY_STATUS) || ResHelper.parseInt(String.valueOf(c2.get(NotificationCompat.CATEGORY_STATUS))) != -200) {
                    if (c2.containsKey("timestamp")) {
                        this.d.a("service_time", Long.valueOf(System.currentTimeMillis() - ResHelper.parseLong(String.valueOf(c2.get("timestamp")))));
                    }
                    if (c2.containsKey("switchs")) {
                        HashMap hashMap = (HashMap) c2.get("switchs");
                        if (hashMap != null) {
                            String valueOf = String.valueOf(hashMap.get(Config.DEVICE_PART));
                            String valueOf2 = String.valueOf(hashMap.get("share"));
                            String valueOf3 = String.valueOf(hashMap.get("auth"));
                            String valueOf4 = String.valueOf(hashMap.get("backflow"));
                            String valueOf5 = String.valueOf(hashMap.get("loginplus"));
                            String valueOf6 = String.valueOf(hashMap.get("linkcard"));
                            this.d.b(valueOf);
                            this.d.d(valueOf2);
                            this.d.c(valueOf3);
                            this.d.a(valueOf4);
                            this.d.e(valueOf5);
                            this.d.f(valueOf6);
                        }
                    }
                    if (c2.containsKey("serpaths")) {
                        HashMap hashMap2 = (HashMap) c2.get("serpaths");
                        if (hashMap2 != null) {
                            String valueOf7 = String.valueOf(hashMap2.get("defhost"));
                            String valueOf8 = String.valueOf(hashMap2.get("defport"));
                            if (!TextUtils.isEmpty(valueOf7) && !TextUtils.isEmpty(valueOf8)) {
                                if ("443".equals(valueOf8) || Constants.UNSTALL_PORT.equals(valueOf8)) {
                                    this.b.c(MobSDK.checkRequestUrl(valueOf7));
                                } else {
                                    this.b.c(MobSDK.checkRequestUrl(valueOf7) + Config.TRACE_TODAY_VISIT_SPLIT + valueOf8);
                                }
                            }
                            HashMap hashMap3 = new HashMap();
                            if (hashMap2.containsKey("assigns")) {
                                HashMap hashMap4 = (HashMap) hashMap2.get("assigns");
                                if (hashMap4 == null || hashMap4.size() == 0) {
                                    this.b.a(null);
                                    return;
                                }
                                for (String str : hashMap4.keySet()) {
                                    HashMap hashMap5 = (HashMap) hashMap4.get(str);
                                    String valueOf9 = String.valueOf(hashMap5.get("host"));
                                    String valueOf10 = String.valueOf(hashMap5.get("port"));
                                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(valueOf9) && !TextUtils.isEmpty(valueOf10)) {
                                        hashMap3.put(str, "http://" + valueOf9 + Config.TRACE_TODAY_VISIT_SPLIT + valueOf10);
                                    }
                                }
                                this.b.a(hashMap3);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                SSDKLog.b().d((String) c2.get("error"), new Object[0]);
            }
        } catch (Throwable th) {
            SSDKLog.b().d(th);
        }
    }

    public void a(c cVar) {
        try {
            if (this.d.k()) {
                if (cVar instanceof b) {
                    a((b) cVar);
                } else if (cVar instanceof f) {
                    a((f) cVar);
                }
                if (!this.d.c()) {
                    cVar.l = null;
                }
                long b2 = this.d.b();
                if (b2 == 0) {
                    b2 = this.b.b();
                }
                cVar.e = System.currentTimeMillis() - b2;
                this.b.a(cVar);
            }
        } catch (Throwable th) {
            SSDKLog.b().d(th);
        }
    }

    private void a(b bVar) throws Throwable {
        boolean d2 = this.d.d();
        String str = bVar.c;
        if (!d2 || TextUtils.isEmpty(str)) {
            bVar.d = null;
            bVar.c = null;
            return;
        }
        bVar.c = Data.Base64AES(str, bVar.f.substring(0, 16));
    }

    private void a(f fVar) throws Throwable {
        int f = this.d.f();
        boolean d2 = this.d.d();
        cn.sharesdk.framework.b.b.f.a aVar = fVar.d;
        if (f == 1) {
            int size = (aVar == null || aVar.e == null) ? 0 : aVar.e.size();
            for (int i = 0; i < size; i++) {
                String a2 = a((String) aVar.e.get(i), b.FINISH_SHARE);
                if (!TextUtils.isEmpty(a2)) {
                    aVar.d.add(a2);
                }
            }
            int size2 = (aVar == null || aVar.f == null) ? 0 : aVar.f.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String a3 = a((Bitmap) aVar.f.get(i2), b.FINISH_SHARE);
                if (!TextUtils.isEmpty(a3)) {
                    aVar.d.add(a3);
                }
            }
        } else {
            fVar.d = null;
        }
        if (!d2) {
            fVar.m = null;
        }
    }

    private String a(String str, b bVar) throws Throwable {
        int ceil;
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return null;
        }
        String networkType = this.c.getNetworkType();
        if ("none".equals(networkType) || TextUtils.isEmpty(networkType)) {
            return null;
        }
        CompressFormat bmpFormat = BitmapHelper.getBmpFormat(str);
        float f = 200.0f;
        if (bVar == b.BEFORE_SHARE) {
            f = 600.0f;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (i >= i2 && ((float) i2) > f) {
            ceil = (int) Math.ceil((double) (((float) options.outHeight) / f));
        } else if (i >= i2 || ((float) i) <= f) {
            return d(str);
        } else {
            ceil = (int) Math.ceil((double) (((float) options.outWidth) / f));
        }
        if (ceil <= 0) {
            ceil = 1;
        }
        Options options2 = new Options();
        options2.inSampleSize = ceil;
        options2.inPurgeable = true;
        options2.inInputShareable = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options2);
        decodeFile.getHeight();
        decodeFile.getWidth();
        File createTempFile = File.createTempFile("bm_tmp2", "." + bmpFormat.name().toLowerCase());
        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
        decodeFile.compress(bmpFormat, 80, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return d(createTempFile.getAbsolutePath());
    }

    private String d(String str) throws Throwable {
        HashMap d2 = this.b.d(str);
        if (d2 == null || d2.size() <= 0 || !d2.containsKey(NotificationCompat.CATEGORY_STATUS) || ResHelper.parseInt(String.valueOf(d2.get(NotificationCompat.CATEGORY_STATUS))) != 200 || !d2.containsKey("url")) {
            return null;
        }
        return (String) d2.get("url");
    }

    private String a(Bitmap bitmap, b bVar) throws Throwable {
        File createTempFile = File.createTempFile("bm_tmp", ".png");
        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
        bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return a(createTempFile.getAbsolutePath(), bVar);
    }

    public void d() {
        boolean a2;
        try {
            String networkType = this.c.getNetworkType();
            if (!"none".equals(networkType) && !TextUtils.isEmpty(networkType) && this.d.k()) {
                ArrayList e2 = this.b.e();
                for (int i = 0; i < e2.size(); i++) {
                    cn.sharesdk.framework.b.a.c cVar = (cn.sharesdk.framework.b.a.c) e2.get(i);
                    if (cVar.b.size() == 1) {
                        a2 = a(cVar.a, false);
                    } else {
                        a2 = a(e(cVar.a), true);
                    }
                    if (a2) {
                        this.b.a(cVar.b);
                    }
                }
            }
        } catch (Throwable th) {
            SSDKLog.b().d(th);
        }
    }

    private String e(String str) throws Throwable {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = byteArrayInputStream.read(bArr, 0, 1024);
            if (read != -1) {
                gZIPOutputStream.write(bArr, 0, read);
            } else {
                gZIPOutputStream.flush();
                gZIPOutputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                byteArrayInputStream.close();
                return Base64.encodeToString(byteArray, 2);
            }
        }
    }

    private boolean a(String str, boolean z) throws Throwable {
        return this.b.a(str, z);
    }

    public String a(String str, int i, boolean z, String str2) {
        try {
            if (!this.d.k() || !this.d.e()) {
                return str;
            }
            String networkType = this.c.getNetworkType();
            if ("none".equals(networkType) || TextUtils.isEmpty(networkType)) {
                return str;
            }
            if (z) {
                String a2 = a(str, "<a[^>]*?href[\\s]*=[\\s]*[\"']?([^'\">]+?)['\"]?>", i, str2);
                if (a2 != null && !a2.equals(str)) {
                    return a2;
                }
            }
            String a3 = a(str, "(http://|https://){1}[\\w\\.\\-/:\\?&%=,;\\[\\]\\{\\}`~!@#\\$\\^\\*\\(\\)_\\+\\\\\\|]+", i, str2);
            if (a3 == null || a3.equals(str)) {
                return str;
            }
            return a3;
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return str;
        }
    }

    private String a(String str, String str2, int i, String str3) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        ArrayList arrayList = new ArrayList();
        Pattern compile = Pattern.compile(str2);
        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            if (group != null && group.length() > 0) {
                arrayList.add(group);
            }
        }
        if (arrayList.size() == 0) {
            return str;
        }
        HashMap a2 = this.b.a(str, arrayList, i, str3);
        if (a2 == null || a2.size() <= 0 || !a2.containsKey("data")) {
            return str;
        }
        ArrayList arrayList2 = (ArrayList) a2.get("data");
        HashMap hashMap = new HashMap();
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            HashMap hashMap2 = (HashMap) it.next();
            hashMap.put(String.valueOf(hashMap2.get("source")), String.valueOf(hashMap2.get("surl")));
        }
        Matcher matcher2 = compile.matcher(str);
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (matcher2.find()) {
            sb.append(str.substring(i2, matcher2.start()));
            sb.append((String) hashMap.get(matcher2.group()));
            i2 = matcher2.end();
        }
        sb.append(str.substring(i2, str.length()));
        String sb2 = sb.toString();
        SSDKLog.b().i("> SERVER_SHORT_LINK_URL content after replace link ===  %s", sb2);
        return sb2;
    }

    public String b(String str) {
        String str2 = null;
        if (!this.d.k()) {
            return str2;
        }
        try {
            return a(str, b.BEFORE_SHARE);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return str2;
        }
    }

    public String a(Bitmap bitmap) {
        String str = null;
        if (!this.d.k()) {
            return str;
        }
        try {
            return a(bitmap, b.BEFORE_SHARE);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return str;
        }
    }

    public HashMap<String, Object> e() {
        try {
            return this.b.f();
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return new HashMap<>();
        }
    }

    public HashMap<String, Object> f() {
        if (!this.d.k() && this.d.m()) {
            return new HashMap<>();
        }
        try {
            HashMap<String, Object> d2 = this.b.d();
            this.d.b(true);
            return d2;
        } catch (Throwable th) {
            this.d.b(false);
            SSDKLog.b().d(th);
            return new HashMap<>();
        }
    }

    public void a(HashMap<String, Object> hashMap) {
        try {
            this.b.b(hashMap);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
        }
    }

    public HashMap<String, Object> c(String str) {
        try {
            return this.b.e(str);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return null;
        }
    }
}
