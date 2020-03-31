package cn.sharesdk.framework.b.b;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: ShareEvent */
public class f extends c {
    private static int o;
    private static long p;
    public int a;
    public String b;
    public String c;
    public a d = new a();
    public String m;
    public String[] n;

    /* compiled from: ShareEvent */
    public static class a {
        public String a = "";
        public String b;
        public ArrayList<String> c = new ArrayList<>();
        public ArrayList<String> d = new ArrayList<>();
        public ArrayList<String> e = new ArrayList<>();
        public ArrayList<Bitmap> f = new ArrayList<>();
        public HashMap<String, Object> g;

        public String toString() {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(this.b)) {
                this.b = this.b.trim().replaceAll("\r", "");
                this.b = this.b.trim().replaceAll("\n", "");
                this.b = this.b.trim().replaceAll("\r\n", "");
            }
            hashMap.put("text", this.b);
            hashMap.put("url", this.c);
            if (this.d != null && this.d.size() > 0) {
                hashMap.put("imgs", this.d);
            }
            if (this.g != null) {
                hashMap.put("attch", new Hashon().fromHashMap(this.g));
            }
            return new Hashon().fromHashMap(hashMap);
        }
    }

    /* access modifiers changed from: protected */
    public String a() {
        return "[SHR]";
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('|').append(this.a);
        sb.append('|').append(this.b);
        sb.append('|').append(TextUtils.isEmpty(this.c) ? "" : this.c);
        String str = "";
        if (this.n != null && this.n.length > 0) {
            str = "[\"" + TextUtils.join("\",\"", this.n) + "\"]";
        }
        sb.append('|').append(str);
        sb.append('|');
        if (this.d != null) {
            try {
                String encodeToString = Base64.encodeToString(Data.AES128Encode(this.f.substring(0, 16), this.d.toString()), 0);
                if (encodeToString.contains("\n")) {
                    encodeToString = encodeToString.replace("\n", "");
                }
                sb.append(encodeToString);
            } catch (Throwable th) {
                SSDKLog.b().d(th);
            }
        }
        sb.append('|');
        if (!TextUtils.isEmpty(this.l)) {
            sb.append(this.l);
        }
        sb.append('|');
        if (!TextUtils.isEmpty(this.m)) {
            try {
                String encodeToString2 = Base64.encodeToString(Data.AES128Encode(this.f.substring(0, 16), this.m), 0);
                if (!TextUtils.isEmpty(encodeToString2) && encodeToString2.contains("\n")) {
                    encodeToString2 = encodeToString2.replace("\n", "");
                }
                sb.append(encodeToString2);
            } catch (Throwable th2) {
                SSDKLog.b().w(th2);
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public int b() {
        return ReaderCallback.GET_BAR_ANIMATING;
    }

    /* access modifiers changed from: protected */
    public int c() {
        return 30;
    }

    /* access modifiers changed from: protected */
    public long d() {
        return (long) o;
    }

    /* access modifiers changed from: protected */
    public long e() {
        return p;
    }

    /* access modifiers changed from: protected */
    public void f() {
        o++;
    }

    /* access modifiers changed from: protected */
    public void a(long j) {
        p = j;
    }
}
