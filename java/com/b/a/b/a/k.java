package com.b.a.b.a;

import com.b.a.c.a;
import com.b.a.d.b;
import com.b.a.d.c;
import com.b.a.e;
import com.b.a.r;
import com.b.a.t;
import com.b.a.u;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* compiled from: TimeTypeAdapter */
public final class k extends t<Time> {
    public static final u a = new u() {
        public <T> t<T> a(e eVar, a<T> aVar) {
            if (aVar.a() == Time.class) {
                return new k();
            }
            return null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

    /* renamed from: a */
    public synchronized Time b(com.b.a.d.a aVar) throws IOException {
        Time time;
        if (aVar.f() == b.NULL) {
            aVar.j();
            time = null;
        } else {
            try {
                time = new Time(this.b.parse(aVar.h()).getTime());
            } catch (ParseException e) {
                throw new r((Throwable) e);
            }
        }
        return time;
    }

    public synchronized void a(c cVar, Time time) throws IOException {
        cVar.b(time == null ? null : this.b.format(time));
    }
}
