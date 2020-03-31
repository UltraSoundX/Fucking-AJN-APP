package com.c.a.c.b.c;

import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.c.a.e.c;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.conn.util.InetAddressUtils;
import org.apache.http.message.BasicNameValuePair;

/* compiled from: URIBuilder */
public class a {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    private String h;
    private String i;
    private String j;
    private List<NameValuePair> k;
    private String l;
    private String m;

    public a() {
        this.g = -1;
    }

    public a(String str) {
        try {
            a(new URI(str));
        } catch (URISyntaxException e2) {
            c.a(e2.getMessage(), e2);
        }
    }

    public a(URI uri) {
        a(uri);
    }

    private void a(URI uri) {
        this.a = uri.getScheme();
        this.b = uri.getRawSchemeSpecificPart();
        this.c = uri.getRawAuthority();
        this.f = uri.getHost();
        this.g = uri.getPort();
        this.e = uri.getRawUserInfo();
        this.d = uri.getUserInfo();
        this.i = uri.getRawPath();
        this.h = uri.getPath();
        this.j = uri.getRawQuery();
        this.k = a(uri.getRawQuery());
        this.m = uri.getRawFragment();
        this.l = uri.getFragment();
    }

    private List<NameValuePair> a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return b.a(str);
        }
        return null;
    }

    public URI a(Charset charset) throws URISyntaxException {
        return new URI(b(charset));
    }

    private String b(Charset charset) {
        StringBuilder sb = new StringBuilder();
        if (this.a != null) {
            sb.append(this.a).append(':');
        }
        if (this.b != null) {
            sb.append(this.b);
        } else {
            if (this.c != null) {
                sb.append("//").append(this.c);
            } else if (this.f != null) {
                sb.append("//");
                if (this.e != null) {
                    sb.append(this.e).append("@");
                } else if (this.d != null) {
                    sb.append(a(this.d, charset)).append("@");
                }
                if (InetAddressUtils.isIPv6Address(this.f)) {
                    sb.append("[").append(this.f).append("]");
                } else {
                    sb.append(this.f);
                }
                if (this.g >= 0) {
                    sb.append(Config.TRACE_TODAY_VISIT_SPLIT).append(this.g);
                }
            }
            if (this.i != null) {
                sb.append(b(this.i));
            } else if (this.h != null) {
                sb.append(b(b(this.h), charset));
            }
            if (this.j != null) {
                sb.append("?").append(this.j);
            } else if (this.k != null) {
                sb.append("?").append(a(this.k, charset));
            }
        }
        if (this.m != null) {
            sb.append("#").append(this.m);
        } else if (this.l != null) {
            sb.append("#").append(c(this.l, charset));
        }
        return sb.toString();
    }

    private String a(String str, Charset charset) {
        return b.a(str, charset);
    }

    private String b(String str, Charset charset) {
        return b.c(str, charset).replace("+", "20%");
    }

    private String a(List<NameValuePair> list, Charset charset) {
        return b.a((Iterable<? extends NameValuePair>) list, charset);
    }

    private String c(String str, Charset charset) {
        return b.b(str, charset);
    }

    public a a(String str, String str2) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(new BasicNameValuePair(str, str2));
        this.j = null;
        this.b = null;
        return this;
    }

    private static String b(String str) {
        if (str == null) {
            return null;
        }
        int i2 = 0;
        while (i2 < str.length() && str.charAt(i2) == '/') {
            i2++;
        }
        if (i2 > 1) {
            return str.substring(i2 - 1);
        }
        return str;
    }
}
