package com.zhy.http.okhttp.cookie.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import com.tencent.mid.sotrage.StorageInterface;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class PersistentCookieStore implements CookieStore {
    private static final String COOKIE_NAME_PREFIX = "cookie_";
    private static final String COOKIE_PREFS = "CookiePrefsFile";
    private static final String LOG_TAG = "PersistentCookieStore";
    private final SharedPreferences cookiePrefs;
    private final HashMap<String, ConcurrentHashMap<String, Cookie>> cookies = new HashMap<>();

    public PersistentCookieStore(Context context) {
        String[] split;
        this.cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
        for (Entry entry : this.cookiePrefs.getAll().entrySet()) {
            if (((String) entry.getValue()) != null && !((String) entry.getValue()).startsWith(COOKIE_NAME_PREFIX)) {
                for (String str : TextUtils.split((String) entry.getValue(), StorageInterface.KEY_SPLITER)) {
                    String string = this.cookiePrefs.getString(COOKIE_NAME_PREFIX + str, null);
                    if (string != null) {
                        Cookie decodeCookie = decodeCookie(string);
                        if (decodeCookie != null) {
                            if (!this.cookies.containsKey(entry.getKey())) {
                                this.cookies.put(entry.getKey(), new ConcurrentHashMap());
                            }
                            ((ConcurrentHashMap) this.cookies.get(entry.getKey())).put(str, decodeCookie);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void add(HttpUrl httpUrl, Cookie cookie) {
        String cookieToken = getCookieToken(cookie);
        if (!cookie.persistent()) {
            if (!this.cookies.containsKey(httpUrl.host())) {
                this.cookies.put(httpUrl.host(), new ConcurrentHashMap());
            }
            ((ConcurrentHashMap) this.cookies.get(httpUrl.host())).put(cookieToken, cookie);
        } else if (this.cookies.containsKey(httpUrl.host())) {
            ((ConcurrentHashMap) this.cookies.get(httpUrl.host())).remove(cookieToken);
        } else {
            return;
        }
        Editor edit = this.cookiePrefs.edit();
        edit.putString(httpUrl.host(), TextUtils.join(StorageInterface.KEY_SPLITER, ((ConcurrentHashMap) this.cookies.get(httpUrl.host())).keySet()));
        edit.putString(COOKIE_NAME_PREFIX + cookieToken, encodeCookie(new SerializableHttpCookie(cookie)));
        edit.apply();
    }

    /* access modifiers changed from: protected */
    public String getCookieToken(Cookie cookie) {
        return cookie.name() + cookie.domain();
    }

    public void add(HttpUrl httpUrl, List<Cookie> list) {
        for (Cookie add : list) {
            add(httpUrl, add);
        }
    }

    public List<Cookie> get(HttpUrl httpUrl) {
        ArrayList arrayList = new ArrayList();
        if (this.cookies.containsKey(httpUrl.host())) {
            for (Cookie cookie : ((ConcurrentHashMap) this.cookies.get(httpUrl.host())).values()) {
                if (isCookieExpired(cookie)) {
                    remove(httpUrl, cookie);
                } else {
                    arrayList.add(cookie);
                }
            }
        }
        return arrayList;
    }

    private static boolean isCookieExpired(Cookie cookie) {
        return cookie.expiresAt() < System.currentTimeMillis();
    }

    public boolean removeAll() {
        Editor edit = this.cookiePrefs.edit();
        edit.clear();
        edit.apply();
        this.cookies.clear();
        return true;
    }

    public boolean remove(HttpUrl httpUrl, Cookie cookie) {
        String cookieToken = getCookieToken(cookie);
        if (!this.cookies.containsKey(httpUrl.host()) || !((ConcurrentHashMap) this.cookies.get(httpUrl.host())).containsKey(cookieToken)) {
            return false;
        }
        ((ConcurrentHashMap) this.cookies.get(httpUrl.host())).remove(cookieToken);
        Editor edit = this.cookiePrefs.edit();
        if (this.cookiePrefs.contains(COOKIE_NAME_PREFIX + cookieToken)) {
            edit.remove(COOKIE_NAME_PREFIX + cookieToken);
        }
        edit.putString(httpUrl.host(), TextUtils.join(StorageInterface.KEY_SPLITER, ((ConcurrentHashMap) this.cookies.get(httpUrl.host())).keySet()));
        edit.apply();
        return true;
    }

    public List<Cookie> getCookies() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.cookies.keySet()) {
            arrayList.addAll(((ConcurrentHashMap) this.cookies.get(str)).values());
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String encodeCookie(SerializableHttpCookie serializableHttpCookie) {
        if (serializableHttpCookie == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(serializableHttpCookie);
            return byteArrayToHexString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in encodeCookie", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Cookie decodeCookie(String str) {
        try {
            return ((SerializableHttpCookie) new ObjectInputStream(new ByteArrayInputStream(hexStringToByteArray(str))).readObject()).getCookie();
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in decodeCookie", e);
            return null;
        } catch (ClassNotFoundException e2) {
            Log.d(LOG_TAG, "ClassNotFoundException in decodeCookie", e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            byte b2 = b & DeviceInfos.NETWORK_TYPE_UNCONNECTED;
            if (b2 < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(b2));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    /* access modifiers changed from: protected */
    public byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
