package com.c.a.c.b.b;

import com.c.a.c.b.b.a.b;
import net.sf.json.util.JSONUtils;

/* compiled from: FormBodyPart */
public class a {
    private final String a;
    private final f b;
    private final b c;

    public a(String str, b bVar) {
        if (str == null) {
            throw new IllegalArgumentException("Name may not be null");
        } else if (bVar == null) {
            throw new IllegalArgumentException("Body may not be null");
        } else {
            this.a = str;
            this.c = bVar;
            this.b = new f();
            a(bVar);
            b(bVar);
            c(bVar);
        }
    }

    public String a() {
        return this.a;
    }

    public b b() {
        return this.c;
    }

    public f c() {
        return this.b;
    }

    public void a(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Field name may not be null");
        }
        this.b.a(new e(str, str2));
    }

    /* access modifiers changed from: protected */
    public void a(b bVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("form-data; name=\"");
        sb.append(a());
        sb.append(JSONUtils.DOUBLE_QUOTE);
        if (bVar.b() != null) {
            sb.append("; filename=\"");
            sb.append(bVar.b());
            sb.append(JSONUtils.DOUBLE_QUOTE);
        }
        a("Content-Disposition", sb.toString());
    }

    /* access modifiers changed from: protected */
    public void b(b bVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(bVar.a());
        if (bVar.c() != null) {
            sb.append("; charset=");
            sb.append(bVar.c());
        }
        a("Content-Type", sb.toString());
    }

    /* access modifiers changed from: protected */
    public void c(b bVar) {
        a("Content-Transfer-Encoding", bVar.d());
    }
}
