package com.b.a.b.a;

import com.b.a.c.a;
import com.b.a.d.b;
import com.b.a.d.c;
import com.b.a.e;
import com.b.a.r;
import com.b.a.t;
import com.b.a.u;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* compiled from: SqlDateTypeAdapter */
public final class j extends t<Date> {
    public static final u a = new u() {
        public <T> t<T> a(e eVar, a<T> aVar) {
            if (aVar.a() == Date.class) {
                return new j();
            }
            return null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

    /* renamed from: a */
    public synchronized Date b(com.b.a.d.a aVar) throws IOException {
        Date date;
        if (aVar.f() == b.NULL) {
            aVar.j();
            date = null;
        } else {
            try {
                date = new Date(this.b.parse(aVar.h()).getTime());
            } catch (ParseException e) {
                throw new r((Throwable) e);
            }
        }
        return date;
    }

    public synchronized void a(c cVar, Date date) throws IOException {
        cVar.b(date == null ? null : this.b.format(date));
    }
}
