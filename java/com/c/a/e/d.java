package com.c.a.e;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

/* compiled from: OtherUtils */
public class d {
    public static String a(Context context) {
        String str;
        if (context != null) {
            try {
                str = context.getString(((Integer) Class.forName("com.android.internal.R$string").getDeclaredField("web_user_agent").get(null)).intValue());
            } catch (Throwable th) {
                str = null;
            }
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            str = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 %sSafari/533.1";
        }
        Locale locale = Locale.getDefault();
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = VERSION.RELEASE;
        if (str2.length() > 0) {
            stringBuffer.append(str2);
        } else {
            stringBuffer.append("1.0");
        }
        stringBuffer.append("; ");
        String language = locale.getLanguage();
        if (language != null) {
            stringBuffer.append(language.toLowerCase());
            String country = locale.getCountry();
            if (country != null) {
                stringBuffer.append("-");
                stringBuffer.append(country.toLowerCase());
            }
        } else {
            stringBuffer.append("en");
        }
        if ("REL".equals(VERSION.CODENAME)) {
            String str3 = Build.MODEL;
            if (str3.length() > 0) {
                stringBuffer.append("; ");
                stringBuffer.append(str3);
            }
        }
        String str4 = Build.ID;
        if (str4.length() > 0) {
            stringBuffer.append(" Build/");
            stringBuffer.append(str4);
        }
        return String.format(str, new Object[]{stringBuffer, "Mobile "});
    }

    public static boolean a(HttpResponse httpResponse) {
        if (httpResponse == null) {
            return false;
        }
        Header firstHeader = httpResponse.getFirstHeader("Accept-Ranges");
        if (firstHeader != null) {
            return "bytes".equals(firstHeader.getValue());
        }
        Header firstHeader2 = httpResponse.getFirstHeader("Content-Range");
        if (firstHeader2 == null) {
            return false;
        }
        String value = firstHeader2.getValue();
        if (value == null || !value.startsWith("bytes")) {
            return false;
        }
        return true;
    }

    public static String b(HttpResponse httpResponse) {
        if (httpResponse == null) {
            return null;
        }
        Header firstHeader = httpResponse.getFirstHeader("Content-Disposition");
        if (firstHeader == null) {
            return null;
        }
        for (HeaderElement parameterByName : firstHeader.getElements()) {
            NameValuePair parameterByName2 = parameterByName.getParameterByName(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME);
            if (parameterByName2 != null) {
                String value = parameterByName2.getValue();
                return a.a(value, "UTF-8", value.length());
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001c A[SYNTHETIC, Splitter:B:9:0x001c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.charset.Charset a(org.apache.http.client.methods.HttpRequestBase r7) {
        /*
            r2 = 0
            r1 = 0
            if (r7 != 0) goto L_0x0005
        L_0x0004:
            return r1
        L_0x0005:
            java.lang.String r0 = "Content-Type"
            org.apache.http.Header r0 = r7.getFirstHeader(r0)
            if (r0 == 0) goto L_0x0015
            org.apache.http.HeaderElement[] r3 = r0.getElements()
            int r4 = r3.length
            r0 = r2
        L_0x0013:
            if (r0 < r4) goto L_0x0027
        L_0x0015:
            r0 = r1
        L_0x0016:
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L_0x0020
            boolean r2 = java.nio.charset.Charset.isSupported(r0)     // Catch:{ Throwable -> 0x0039 }
        L_0x0020:
            if (r2 == 0) goto L_0x0004
            java.nio.charset.Charset r1 = java.nio.charset.Charset.forName(r0)
            goto L_0x0004
        L_0x0027:
            r5 = r3[r0]
            java.lang.String r6 = "charset"
            org.apache.http.NameValuePair r5 = r5.getParameterByName(r6)
            if (r5 == 0) goto L_0x0036
            java.lang.String r0 = r5.getValue()
            goto L_0x0016
        L_0x0036:
            int r0 = r0 + 1
            goto L_0x0013
        L_0x0039:
            r3 = move-exception
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: com.c.a.e.d.a(org.apache.http.client.methods.HttpRequestBase):java.nio.charset.Charset");
    }

    public static long a(String str, String str2) throws UnsupportedEncodingException {
        long j = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int length = str.length();
        if (length < 100) {
            return (long) str.getBytes(str2).length;
        }
        for (int i = 0; i < length; i += 100) {
            int i2 = i + 100;
            if (i2 >= length) {
                i2 = length;
            }
            j += (long) a(str, i, i2).getBytes(str2).length;
        }
        return j;
    }

    public static String a(String str, int i, int i2) {
        return new String(str.substring(i, i2));
    }

    public static StackTraceElement a() {
        return Thread.currentThread().getStackTrace()[4];
    }
}
