package com.b.a.b.a;

import com.b.a.c.a;
import com.b.a.d.b;
import com.b.a.e;
import com.b.a.r;
import com.b.a.t;
import com.b.a.u;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

/* compiled from: DateTypeAdapter */
public final class c extends t<Date> {
    public static final u a = new u() {
        public <T> t<T> a(e eVar, a<T> aVar) {
            if (aVar.a() == Date.class) {
                return new c();
            }
            return null;
        }
    };
    private final DateFormat b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat c = DateFormat.getDateTimeInstance(2, 2);

    /* renamed from: a */
    public Date b(com.b.a.d.a aVar) throws IOException {
        if (aVar.f() != b.NULL) {
            return a(aVar.h());
        }
        aVar.j();
        return null;
    }

    private synchronized Date a(String str) {
        Date a2;
        try {
            a2 = this.c.parse(str);
        } catch (ParseException e) {
            try {
                a2 = this.b.parse(str);
            } catch (ParseException e2) {
                try {
                    a2 = com.b.a.b.a.a.a.a(str, new ParsePosition(0));
                } catch (ParseException e3) {
                    throw new r(str, e3);
                }
            }
        }
        return a2;
    }

    public synchronized void a(com.b.a.d.c cVar, Date date) throws IOException {
        if (date == null) {
            cVar.f();
        } else {
            cVar.b(this.b.format(date));
        }
    }
}
