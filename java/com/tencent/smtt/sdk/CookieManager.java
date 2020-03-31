package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.sdk.TbsLogReport.EventType;
import com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.k;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class CookieManager {
    public static String LOGTAG = "CookieManager";
    private static CookieManager d;
    CopyOnWriteArrayList<b> a;
    String b;
    a c = a.MODE_NONE;
    private boolean e = false;
    private boolean f = false;

    public enum a {
        MODE_NONE,
        MODE_KEYS,
        MODE_ALL
    }

    class b {
        int a;
        String b;
        String c;
        ValueCallback<Boolean> d;

        b() {
        }
    }

    private CookieManager() {
    }

    public static CookieManager getInstance() {
        if (d == null) {
            synchronized (CookieManager.class) {
                if (d == null) {
                    d = new CookieManager();
                }
            }
        }
        return d;
    }

    public void removeSessionCookie() {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().removeSessionCookie();
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookies(ValueCallback<Boolean> valueCallback) {
        t a2 = t.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookies", new Class[]{ValueCallback.class}, valueCallback);
        } else if (VERSION.SDK_INT >= 21) {
            k.a((Object) android.webkit.CookieManager.getInstance(), "removeSessionCookies", (Class<?>[]) new Class[]{ValueCallback.class}, valueCallback);
        }
    }

    public void removeAllCookie() {
        if (this.a != null) {
            this.a.clear();
        }
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().removeAllCookie();
        } else {
            a2.c().e();
        }
    }

    public void removeAllCookies(ValueCallback<Boolean> valueCallback) {
        if (this.a != null) {
            this.a.clear();
        }
        t a2 = t.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookies", new Class[]{ValueCallback.class}, valueCallback);
        } else if (VERSION.SDK_INT >= 21) {
            k.a((Object) android.webkit.CookieManager.getInstance(), "removeAllCookies", (Class<?>[]) new Class[]{ValueCallback.class}, valueCallback);
        }
    }

    public void flush() {
        t a2 = t.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_flush", new Class[0], new Object[0]);
        } else if (VERSION.SDK_INT >= 21) {
            k.a((Object) android.webkit.CookieManager.getInstance(), "flush", (Class<?>[]) new Class[0], new Object[0]);
        }
    }

    public void removeExpiredCookie() {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().removeExpiredCookie();
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeExpiredCookie", new Class[0], new Object[0]);
        }
    }

    public synchronized void setAcceptCookie(boolean z) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            try {
                android.webkit.CookieManager.getInstance().setAcceptCookie(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptCookie", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
        return;
    }

    public synchronized void setAcceptThirdPartyCookies(WebView webView, boolean z) {
        t a2 = t.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptThirdPartyCookies", new Class[]{Object.class, Boolean.TYPE}, webView.getView(), Boolean.valueOf(z));
        } else if (VERSION.SDK_INT >= 21) {
            k.a((Object) android.webkit.CookieManager.getInstance(), "setAcceptThirdPartyCookies", (Class<?>[]) new Class[]{WebView.class, Boolean.TYPE}, webView.getView(), Boolean.valueOf(z));
        }
    }

    public synchronized boolean acceptThirdPartyCookies(WebView webView) {
        boolean z;
        t a2 = t.a();
        if (a2 != null && a2.b()) {
            Object invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptThirdPartyCookies", new Class[]{Object.class}, webView.getView());
            z = invokeStaticMethod != null ? ((Boolean) invokeStaticMethod).booleanValue() : true;
        } else if (VERSION.SDK_INT < 21) {
            z = true;
        } else {
            Object a3 = k.a((Object) android.webkit.CookieManager.getInstance(), "acceptThirdPartyCookies", (Class<?>[]) new Class[]{WebView.class}, webView.getView());
            z = a3 != null ? ((Boolean) a3).booleanValue() : false;
        }
        return z;
    }

    public synchronized void setCookie(String str, String str2) {
        setCookie(str, str2, false);
    }

    public synchronized void setCookie(String str, String str2, boolean z) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            if (this.f || z) {
                android.webkit.CookieManager.getInstance().setCookie(str, str2);
            }
            if (!t.a().d()) {
                b bVar = new b();
                bVar.a = 2;
                bVar.b = str;
                bVar.c = str2;
                bVar.d = null;
                if (this.a == null) {
                    this.a = new CopyOnWriteArrayList<>();
                }
                this.a.add(bVar);
            }
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class}, str, str2);
        }
    }

    public synchronized void setCookie(String str, String str2, ValueCallback<Boolean> valueCallback) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            if (!t.a().d()) {
                b bVar = new b();
                bVar.a = 1;
                bVar.b = str;
                bVar.c = str2;
                bVar.d = valueCallback;
                if (this.a == null) {
                    this.a = new CopyOnWriteArrayList<>();
                }
                this.a.add(bVar);
            }
            if (this.f && VERSION.SDK_INT >= 21) {
                k.a((Object) android.webkit.CookieManager.getInstance(), "setCookie", (Class<?>[]) new Class[]{String.class, String.class, ValueCallback.class}, str, str2, valueCallback);
            }
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class, ValueCallback.class}, str, str2, valueCallback);
        }
    }

    public boolean hasCookies() {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            return android.webkit.CookieManager.getInstance().hasCookies();
        }
        return a2.c().h();
    }

    public boolean acceptCookie() {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            return android.webkit.CookieManager.getInstance().acceptCookie();
        }
        return a2.c().d();
    }

    public String getCookie(String str) {
        t a2 = t.a();
        if (a2 != null && a2.b()) {
            return a2.c().a(str);
        }
        boolean z = false;
        try {
            return android.webkit.CookieManager.getInstance().getCookie(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return z;
        }
    }

    public void setCookies(Map<String, String[]> map) {
        boolean z;
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            z = false;
        } else {
            z = a2.c().a(map);
        }
        if (!z) {
            for (String str : map.keySet()) {
                for (String cookie : (String[]) map.get(str)) {
                    setCookie(str, cookie);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public synchronized void a() {
        this.f = true;
        if (!(this.a == null || this.a.size() == 0)) {
            t a2 = t.a();
            if (a2 != null && a2.b()) {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    b bVar = (b) it.next();
                    switch (bVar.a) {
                        case 1:
                            setCookie(bVar.b, bVar.c, bVar.d);
                            break;
                        case 2:
                            setCookie(bVar.b, bVar.c);
                            break;
                    }
                }
            } else {
                Iterator it2 = this.a.iterator();
                while (it2.hasNext()) {
                    b bVar2 = (b) it2.next();
                    switch (bVar2.a) {
                        case 1:
                            if (VERSION.SDK_INT < 21) {
                                break;
                            } else {
                                k.a((Object) android.webkit.CookieManager.getInstance(), "setCookie", (Class<?>[]) new Class[]{String.class, String.class, ValueCallback.class}, bVar2.b, bVar2.c, bVar2.d);
                                break;
                            }
                        case 2:
                            android.webkit.CookieManager.getInstance().setCookie(bVar2.b, bVar2.c);
                            break;
                    }
                }
            }
            this.a.clear();
        }
    }

    public boolean setCookieCompatialbeMode(Context context, a aVar, String str, boolean z) {
        System.currentTimeMillis();
        if (context == null || !TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.COOKIE_SWITCH_FILE_NAME)) {
            return false;
        }
        this.c = aVar;
        if (str != null) {
            this.b = str;
        }
        if (this.c != a.MODE_NONE && z && !t.a().d()) {
            t.a().a(context);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public synchronized void a(Context context, boolean z, boolean z2) {
        int i;
        int i2;
        boolean z3;
        int i3 = 0;
        synchronized (this) {
            if (this.c != a.MODE_NONE && context != null && TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.COOKIE_SWITCH_FILE_NAME) && !this.e) {
                long currentTimeMillis = System.currentTimeMillis();
                long j = 0;
                TbsLog.i(LOGTAG, "compatiableCookieDatabaseIfNeed,isX5Inited:" + z + ",useX5:" + z2);
                if (z || QbSdk.getIsSysWebViewForcedByOuter() || QbSdk.a) {
                    if (QbSdk.getIsSysWebViewForcedByOuter() || QbSdk.a) {
                        z2 = false;
                    }
                    boolean canUseFunction = TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.USEX5_FILE_NAME);
                    TbsLog.i(LOGTAG, "usex5 : mUseX5LastProcess->" + canUseFunction + ",useX5:" + z2);
                    TbsExtensionFunctionManager.getInstance().setFunctionEnable(context, TbsExtensionFunctionManager.USEX5_FILE_NAME, z2);
                    if (canUseFunction != z2) {
                        TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
                        if (TextUtils.isEmpty(this.b)) {
                            tbsLogInfo.setErrorCode(701);
                            i = 0;
                        } else if (l.a().i(context) <= 0 || l.a().i(context) >= 36001) {
                            if (canUseFunction) {
                                i2 = h.d(context);
                                if (i2 > 0) {
                                    int rOMCookieDBVersion = getROMCookieDBVersion(context);
                                    if (rOMCookieDBVersion <= 0) {
                                        i3 = rOMCookieDBVersion;
                                        i = i2;
                                        z3 = true;
                                    } else {
                                        i3 = rOMCookieDBVersion;
                                        i = i2;
                                        z3 = false;
                                    }
                                }
                                i = i2;
                                z3 = false;
                            } else {
                                i2 = h.d(context);
                                if (i2 > 0) {
                                    String d2 = l.a().d(context, "cookies_database_version");
                                    if (!TextUtils.isEmpty(d2)) {
                                        try {
                                            i3 = Integer.parseInt(d2);
                                            i = i2;
                                            z3 = false;
                                        } catch (Exception e2) {
                                            i = i2;
                                            z3 = false;
                                        }
                                    }
                                }
                                i = i2;
                                z3 = false;
                            }
                            if (!z3 && (i <= 0 || i3 <= 0)) {
                                tbsLogInfo.setErrorCode(702);
                            } else if (i3 >= i) {
                                tbsLogInfo.setErrorCode(703);
                            } else {
                                h.a(context, this.c, this.b, z3, z2);
                                tbsLogInfo.setErrorCode(ErrorCode.INFO_COOKIE_SWITCH_TRANSFER);
                                j = System.currentTimeMillis() - currentTimeMillis;
                            }
                        }
                        tbsLogInfo.setFailDetail("x5->sys:" + canUseFunction + " from:" + i + " to:" + i3 + ",timeused:" + j);
                        TbsLogReport.getInstance(context).eventReport(EventType.TYPE_COOKIE_DB_SWITCH, tbsLogInfo);
                    }
                } else {
                    t.a().a(context);
                }
            }
        }
    }

    public static int getROMCookieDBVersion(Context context) {
        SharedPreferences sharedPreferences;
        if (VERSION.SDK_INT >= 11) {
            sharedPreferences = context.getSharedPreferences("cookiedb_info", 4);
        } else {
            sharedPreferences = context.getSharedPreferences("cookiedb_info", 0);
        }
        return sharedPreferences.getInt("db_version", -1);
    }

    public static void setROMCookieDBVersion(Context context, int i) {
        SharedPreferences sharedPreferences;
        if (VERSION.SDK_INT >= 11) {
            sharedPreferences = context.getSharedPreferences("cookiedb_info", 4);
        } else {
            sharedPreferences = context.getSharedPreferences("cookiedb_info", 0);
        }
        Editor edit = sharedPreferences.edit();
        edit.putInt("db_version", i);
        edit.commit();
    }
}
