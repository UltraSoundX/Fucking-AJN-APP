package com.tencent.smtt.utils;

import android.os.Build.VERSION;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: TbsCopyVerify */
public class p {
    private b a = null;
    private b b = null;

    /* compiled from: TbsCopyVerify */
    class a {
        private String b;
        private long c;
        private long d;

        a(String str, long j, long j2) {
            this.b = str;
            this.c = j;
            this.d = j2;
        }

        /* access modifiers changed from: 0000 */
        public long a() {
            return this.c;
        }

        /* access modifiers changed from: 0000 */
        public long b() {
            return this.d;
        }
    }

    /* compiled from: TbsCopyVerify */
    class b {
        private Map<String, a> b = new HashMap();

        /* access modifiers changed from: 0000 */
        public Map<String, a> a() {
            return this.b;
        }

        b(File file) {
            this.b.clear();
            a(file);
        }

        private void a(File file) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null || VERSION.SDK_INT < 26) {
                    for (File a2 : listFiles) {
                        a(a2);
                    }
                }
            } else if (file.isFile()) {
                a(file.getName(), file.length(), file.lastModified());
            }
        }

        private void a(String str, long j, long j2) {
            if (str != null && str.length() > 0 && j > 0 && j2 > 0) {
                a aVar = new a(str, j, j2);
                if (!this.b.containsKey(str)) {
                    this.b.put(str, aVar);
                }
            }
        }
    }

    public void a(File file) {
        this.a = new b(file);
    }

    public void b(File file) {
        this.b = new b(file);
    }

    public boolean a() {
        if (this.b == null || this.a == null || this.b.a().size() != this.a.a().size() || !a(this.a, this.b)) {
            return false;
        }
        return true;
    }

    private boolean a(b bVar, b bVar2) {
        if (bVar == null || bVar.a() == null || bVar2 == null || bVar2.a() == null) {
            return false;
        }
        Map a2 = bVar.a();
        Map a3 = bVar2.a();
        for (Entry entry : a2.entrySet()) {
            String str = (String) entry.getKey();
            a aVar = (a) entry.getValue();
            if (!a3.containsKey(str)) {
                return false;
            }
            a aVar2 = (a) a3.get(str);
            if (aVar.a() == aVar2.a()) {
                if (aVar.b() != aVar2.b()) {
                }
            }
            return false;
        }
        return true;
    }
}
