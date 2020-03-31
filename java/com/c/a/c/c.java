package com.c.a.c;

import com.c.a.c.b.b.a.b;
import com.c.a.c.b.b.a.d;
import com.c.a.c.b.b.a.e;
import com.c.a.c.b.b.g;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/* compiled from: RequestParams */
public class c {
    private String a = "UTF-8";
    private List<a> b;
    private List<NameValuePair> c;
    private HttpEntity d;
    private List<NameValuePair> e;
    private HashMap<String, b> f;
    private com.c.a.d.b g;

    /* compiled from: RequestParams */
    public class a {
        public final boolean a;
        public final Header b;
    }

    public com.c.a.d.b a() {
        return this.g;
    }

    public String b() {
        return this.a;
    }

    public void a(String str, String str2) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.e.add(new BasicNameValuePair(str, str2));
    }

    public void a(String str, File file, String str2) {
        if (this.f == null) {
            this.f = new HashMap<>();
        }
        this.f.put(str, new d(file, str2));
    }

    public HttpEntity c() {
        if (this.d != null) {
            return this.d;
        }
        if (this.f != null && !this.f.isEmpty()) {
            g gVar = new g(com.c.a.c.b.b.c.STRICT, null, Charset.forName(this.a));
            if (this.e != null && !this.e.isEmpty()) {
                for (NameValuePair nameValuePair : this.e) {
                    try {
                        gVar.a(nameValuePair.getName(), (b) new e(nameValuePair.getValue()));
                    } catch (UnsupportedEncodingException e2) {
                        com.c.a.e.c.a(e2.getMessage(), e2);
                    }
                }
            }
            for (Entry entry : this.f.entrySet()) {
                gVar.a((String) entry.getKey(), (b) entry.getValue());
            }
            return gVar;
        } else if (this.e == null || this.e.isEmpty()) {
            return null;
        } else {
            return new com.c.a.c.b.a.a(this.e, this.a);
        }
    }

    public List<NameValuePair> d() {
        return this.c;
    }

    public List<a> e() {
        return this.b;
    }
}
