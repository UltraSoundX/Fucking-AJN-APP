package com.b.a.b.a;

import com.b.a.b.f;
import com.b.a.d.b;
import com.b.a.d.c;
import com.b.a.e;
import com.b.a.g;
import com.b.a.j;
import com.b.a.k;
import com.b.a.l;
import com.b.a.m;
import com.b.a.o;
import com.b.a.r;
import com.b.a.t;
import com.b.a.u;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* compiled from: TypeAdapters */
public final class n {
    public static final t<String> A = new t<String>() {
        /* renamed from: a */
        public String b(com.b.a.d.a aVar) throws IOException {
            b f = aVar.f();
            if (f == b.NULL) {
                aVar.j();
                return null;
            } else if (f == b.BOOLEAN) {
                return Boolean.toString(aVar.i());
            } else {
                return aVar.h();
            }
        }

        public void a(c cVar, String str) throws IOException {
            cVar.b(str);
        }
    };
    public static final t<BigDecimal> B = new t<BigDecimal>() {
        /* renamed from: a */
        public BigDecimal b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return new BigDecimal(aVar.h());
            } catch (NumberFormatException e) {
                throw new r((Throwable) e);
            }
        }

        public void a(c cVar, BigDecimal bigDecimal) throws IOException {
            cVar.a((Number) bigDecimal);
        }
    };
    public static final t<BigInteger> C = new t<BigInteger>() {
        /* renamed from: a */
        public BigInteger b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return new BigInteger(aVar.h());
            } catch (NumberFormatException e) {
                throw new r((Throwable) e);
            }
        }

        public void a(c cVar, BigInteger bigInteger) throws IOException {
            cVar.a((Number) bigInteger);
        }
    };
    public static final u D = a(String.class, A);
    public static final t<StringBuilder> E = new t<StringBuilder>() {
        /* renamed from: a */
        public StringBuilder b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return new StringBuilder(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, StringBuilder sb) throws IOException {
            cVar.b(sb == null ? null : sb.toString());
        }
    };
    public static final u F = a(StringBuilder.class, E);
    public static final t<StringBuffer> G = new t<StringBuffer>() {
        /* renamed from: a */
        public StringBuffer b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return new StringBuffer(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, StringBuffer stringBuffer) throws IOException {
            cVar.b(stringBuffer == null ? null : stringBuffer.toString());
        }
    };
    public static final u H = a(StringBuffer.class, G);
    public static final t<URL> I = new t<URL>() {
        /* renamed from: a */
        public URL b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            String h = aVar.h();
            if (!"null".equals(h)) {
                return new URL(h);
            }
            return null;
        }

        public void a(c cVar, URL url) throws IOException {
            cVar.b(url == null ? null : url.toExternalForm());
        }
    };
    public static final u J = a(URL.class, I);
    public static final t<URI> K = new t<URI>() {
        /* renamed from: a */
        public URI b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                String h = aVar.h();
                if (!"null".equals(h)) {
                    return new URI(h);
                }
                return null;
            } catch (URISyntaxException e) {
                throw new k((Throwable) e);
            }
        }

        public void a(c cVar, URI uri) throws IOException {
            cVar.b(uri == null ? null : uri.toASCIIString());
        }
    };
    public static final u L = a(URI.class, K);
    public static final t<InetAddress> M = new t<InetAddress>() {
        /* renamed from: a */
        public InetAddress b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return InetAddress.getByName(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, InetAddress inetAddress) throws IOException {
            cVar.b(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    };
    public static final u N = b(InetAddress.class, M);
    public static final t<UUID> O = new t<UUID>() {
        /* renamed from: a */
        public UUID b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return UUID.fromString(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, UUID uuid) throws IOException {
            cVar.b(uuid == null ? null : uuid.toString());
        }
    };
    public static final u P = a(UUID.class, O);
    public static final t<Currency> Q = new t<Currency>() {
        /* renamed from: a */
        public Currency b(com.b.a.d.a aVar) throws IOException {
            return Currency.getInstance(aVar.h());
        }

        public void a(c cVar, Currency currency) throws IOException {
            cVar.b(currency.getCurrencyCode());
        }
    }.a();
    public static final u R = a(Currency.class, Q);
    public static final u S = new u() {
        public <T> t<T> a(e eVar, com.b.a.c.a<T> aVar) {
            if (aVar.a() != Timestamp.class) {
                return null;
            }
            final t a = eVar.a(Date.class);
            return new t<Timestamp>() {
                /* renamed from: a */
                public Timestamp b(com.b.a.d.a aVar) throws IOException {
                    Date date = (Date) a.b(aVar);
                    if (date != null) {
                        return new Timestamp(date.getTime());
                    }
                    return null;
                }

                public void a(c cVar, Timestamp timestamp) throws IOException {
                    a.a(cVar, timestamp);
                }
            };
        }
    };
    public static final t<Calendar> T = new t<Calendar>() {
        /* renamed from: a */
        public Calendar b(com.b.a.d.a aVar) throws IOException {
            int i = 0;
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            aVar.c();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (aVar.f() != b.END_OBJECT) {
                String g = aVar.g();
                int m = aVar.m();
                if ("year".equals(g)) {
                    i6 = m;
                } else if ("month".equals(g)) {
                    i5 = m;
                } else if ("dayOfMonth".equals(g)) {
                    i4 = m;
                } else if ("hourOfDay".equals(g)) {
                    i3 = m;
                } else if ("minute".equals(g)) {
                    i2 = m;
                } else if ("second".equals(g)) {
                    i = m;
                }
            }
            aVar.d();
            return new GregorianCalendar(i6, i5, i4, i3, i2, i);
        }

        public void a(c cVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                cVar.f();
                return;
            }
            cVar.d();
            cVar.a("year");
            cVar.a((long) calendar.get(1));
            cVar.a("month");
            cVar.a((long) calendar.get(2));
            cVar.a("dayOfMonth");
            cVar.a((long) calendar.get(5));
            cVar.a("hourOfDay");
            cVar.a((long) calendar.get(11));
            cVar.a("minute");
            cVar.a((long) calendar.get(12));
            cVar.a("second");
            cVar.a((long) calendar.get(13));
            cVar.e();
        }
    };
    public static final u U = b(Calendar.class, GregorianCalendar.class, T);
    public static final t<Locale> V = new t<Locale>() {
        /* renamed from: a */
        public Locale b(com.b.a.d.a aVar) throws IOException {
            String str;
            String str2;
            String str3;
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.h(), "_");
            if (stringTokenizer.hasMoreElements()) {
                str = stringTokenizer.nextToken();
            } else {
                str = null;
            }
            if (stringTokenizer.hasMoreElements()) {
                str2 = stringTokenizer.nextToken();
            } else {
                str2 = null;
            }
            if (stringTokenizer.hasMoreElements()) {
                str3 = stringTokenizer.nextToken();
            } else {
                str3 = null;
            }
            if (str2 == null && str3 == null) {
                return new Locale(str);
            }
            if (str3 == null) {
                return new Locale(str, str2);
            }
            return new Locale(str, str2, str3);
        }

        public void a(c cVar, Locale locale) throws IOException {
            cVar.b(locale == null ? null : locale.toString());
        }
    };
    public static final u W = a(Locale.class, V);
    public static final t<j> X = new t<j>() {
        /* renamed from: a */
        public j b(com.b.a.d.a aVar) throws IOException {
            switch (AnonymousClass29.a[aVar.f().ordinal()]) {
                case 1:
                    return new o((Number) new f(aVar.h()));
                case 2:
                    return new o(Boolean.valueOf(aVar.i()));
                case 3:
                    return new o(aVar.h());
                case 4:
                    aVar.j();
                    return l.a;
                case 5:
                    g gVar = new g();
                    aVar.a();
                    while (aVar.e()) {
                        gVar.a(b(aVar));
                    }
                    aVar.b();
                    return gVar;
                case 6:
                    m mVar = new m();
                    aVar.c();
                    while (aVar.e()) {
                        mVar.a(aVar.g(), b(aVar));
                    }
                    aVar.d();
                    return mVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public void a(c cVar, j jVar) throws IOException {
            if (jVar == null || jVar.j()) {
                cVar.f();
            } else if (jVar.i()) {
                o m = jVar.m();
                if (m.p()) {
                    cVar.a(m.a());
                } else if (m.o()) {
                    cVar.a(m.f());
                } else {
                    cVar.b(m.b());
                }
            } else if (jVar.g()) {
                cVar.b();
                Iterator it = jVar.l().iterator();
                while (it.hasNext()) {
                    a(cVar, (j) it.next());
                }
                cVar.c();
            } else if (jVar.h()) {
                cVar.d();
                for (Entry entry : jVar.k().o()) {
                    cVar.a((String) entry.getKey());
                    a(cVar, (j) entry.getValue());
                }
                cVar.e();
            } else {
                throw new IllegalArgumentException("Couldn't write " + jVar.getClass());
            }
        }
    };
    public static final u Y = b(j.class, X);
    public static final u Z = new u() {
        public <T> t<T> a(e eVar, com.b.a.c.a<T> aVar) {
            Class<Enum> a = aVar.a();
            if (!Enum.class.isAssignableFrom(a) || a == Enum.class) {
                return null;
            }
            if (!a.isEnum()) {
                a = a.getSuperclass();
            }
            return new a(a);
        }
    };
    public static final t<Class> a = new t<Class>() {
        public void a(c cVar, Class cls) throws IOException {
            if (cls == null) {
                cVar.f();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        /* renamed from: a */
        public Class b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    };
    public static final u b = a(Class.class, a);
    public static final t<BitSet> c = new t<BitSet>() {
        /* renamed from: a */
        public BitSet b(com.b.a.d.a aVar) throws IOException {
            boolean z;
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            BitSet bitSet = new BitSet();
            aVar.a();
            b f = aVar.f();
            int i = 0;
            while (f != b.END_ARRAY) {
                switch (AnonymousClass29.a[f.ordinal()]) {
                    case 1:
                        if (aVar.m() == 0) {
                            z = false;
                            break;
                        } else {
                            z = true;
                            break;
                        }
                    case 2:
                        z = aVar.i();
                        break;
                    case 3:
                        String h = aVar.h();
                        try {
                            if (Integer.parseInt(h) == 0) {
                                z = false;
                                break;
                            } else {
                                z = true;
                                break;
                            }
                        } catch (NumberFormatException e) {
                            throw new r("Error: Expecting: bitset number value (1, 0), Found: " + h);
                        }
                    default:
                        throw new r("Invalid bitset value type: " + f);
                }
                if (z) {
                    bitSet.set(i);
                }
                i++;
                f = aVar.f();
            }
            aVar.b();
            return bitSet;
        }

        public void a(c cVar, BitSet bitSet) throws IOException {
            int i;
            if (bitSet == null) {
                cVar.f();
                return;
            }
            cVar.b();
            for (int i2 = 0; i2 < bitSet.length(); i2++) {
                if (bitSet.get(i2)) {
                    i = 1;
                } else {
                    i = 0;
                }
                cVar.a((long) i);
            }
            cVar.c();
        }
    };
    public static final u d = a(BitSet.class, c);
    public static final t<Boolean> e = new t<Boolean>() {
        /* renamed from: a */
        public Boolean b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            } else if (aVar.f() == b.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(aVar.h()));
            } else {
                return Boolean.valueOf(aVar.i());
            }
        }

        public void a(c cVar, Boolean bool) throws IOException {
            cVar.a(bool);
        }
    };
    public static final t<Boolean> f = new t<Boolean>() {
        /* renamed from: a */
        public Boolean b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return Boolean.valueOf(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, Boolean bool) throws IOException {
            cVar.b(bool == null ? "null" : bool.toString());
        }
    };
    public static final u g = a(Boolean.TYPE, Boolean.class, e);
    public static final t<Number> h = new t<Number>() {
        /* renamed from: a */
        public Number b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.m());
            } catch (NumberFormatException e) {
                throw new r((Throwable) e);
            }
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final u i = a(Byte.TYPE, Byte.class, h);
    public static final t<Number> j = new t<Number>() {
        /* renamed from: a */
        public Number b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.m());
            } catch (NumberFormatException e) {
                throw new r((Throwable) e);
            }
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final u k = a(Short.TYPE, Short.class, j);
    public static final t<Number> l = new t<Number>() {
        /* renamed from: a */
        public Number b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Integer.valueOf(aVar.m());
            } catch (NumberFormatException e) {
                throw new r((Throwable) e);
            }
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final u m = a(Integer.TYPE, Integer.class, l);
    public static final t<AtomicInteger> n = new t<AtomicInteger>() {
        /* renamed from: a */
        public AtomicInteger b(com.b.a.d.a aVar) throws IOException {
            try {
                return new AtomicInteger(aVar.m());
            } catch (NumberFormatException e) {
                throw new r((Throwable) e);
            }
        }

        public void a(c cVar, AtomicInteger atomicInteger) throws IOException {
            cVar.a((long) atomicInteger.get());
        }
    }.a();
    public static final u o = a(AtomicInteger.class, n);
    public static final t<AtomicBoolean> p = new t<AtomicBoolean>() {
        /* renamed from: a */
        public AtomicBoolean b(com.b.a.d.a aVar) throws IOException {
            return new AtomicBoolean(aVar.i());
        }

        public void a(c cVar, AtomicBoolean atomicBoolean) throws IOException {
            cVar.a(atomicBoolean.get());
        }
    }.a();

    /* renamed from: q reason: collision with root package name */
    public static final u f366q = a(AtomicBoolean.class, p);
    public static final t<AtomicIntegerArray> r = new t<AtomicIntegerArray>() {
        /* renamed from: a */
        public AtomicIntegerArray b(com.b.a.d.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.a();
            while (aVar.e()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.m()));
                } catch (NumberFormatException e) {
                    throw new r((Throwable) e);
                }
            }
            aVar.b();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        public void a(c cVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
            cVar.b();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                cVar.a((long) atomicIntegerArray.get(i));
            }
            cVar.c();
        }
    }.a();
    public static final u s = a(AtomicIntegerArray.class, r);
    public static final t<Number> t = new t<Number>() {
        /* renamed from: a */
        public Number b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Long.valueOf(aVar.l());
            } catch (NumberFormatException e) {
                throw new r((Throwable) e);
            }
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final t<Number> u = new t<Number>() {
        /* renamed from: a */
        public Number b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return Float.valueOf((float) aVar.k());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final t<Number> v = new t<Number>() {
        /* renamed from: a */
        public Number b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return Double.valueOf(aVar.k());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final t<Number> w = new t<Number>() {
        /* renamed from: a */
        public Number b(com.b.a.d.a aVar) throws IOException {
            b f = aVar.f();
            switch (f) {
                case NUMBER:
                    return new f(aVar.h());
                case NULL:
                    aVar.j();
                    return null;
                default:
                    throw new r("Expecting number, got: " + f);
            }
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final u x = a(Number.class, w);
    public static final t<Character> y = new t<Character>() {
        /* renamed from: a */
        public Character b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            String h = aVar.h();
            if (h.length() == 1) {
                return Character.valueOf(h.charAt(0));
            }
            throw new r("Expecting character, got: " + h);
        }

        public void a(c cVar, Character ch) throws IOException {
            cVar.b(ch == null ? null : String.valueOf(ch));
        }
    };
    public static final u z = a(Character.TYPE, Character.class, y);

    /* compiled from: TypeAdapters */
    private static final class a<T extends Enum<T>> extends t<T> {
        private final Map<String, T> a = new HashMap();
        private final Map<T, String> b = new HashMap();

        public a(Class<T> cls) {
            Enum[] enumArr;
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    String name = enumR.name();
                    com.b.a.a.c cVar = (com.b.a.a.c) cls.getField(name).getAnnotation(com.b.a.a.c.class);
                    if (cVar != null) {
                        name = cVar.a();
                        for (String put : cVar.b()) {
                            this.a.put(put, enumR);
                        }
                    }
                    String str = name;
                    this.a.put(str, enumR);
                    this.b.put(enumR, str);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        /* renamed from: a */
        public T b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return (Enum) this.a.get(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, T t) throws IOException {
            cVar.b(t == null ? null : (String) this.b.get(t));
        }
    }

    public static <TT> u a(final Class<TT> cls, final t<TT> tVar) {
        return new u() {
            public <T> t<T> a(e eVar, com.b.a.c.a<T> aVar) {
                if (aVar.a() == cls) {
                    return tVar;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + ",adapter=" + tVar + "]";
            }
        };
    }

    public static <TT> u a(final Class<TT> cls, final Class<TT> cls2, final t<? super TT> tVar) {
        return new u() {
            public <T> t<T> a(e eVar, com.b.a.c.a<T> aVar) {
                Class a2 = aVar.a();
                if (a2 == cls || a2 == cls2) {
                    return tVar;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls2.getName() + "+" + cls.getName() + ",adapter=" + tVar + "]";
            }
        };
    }

    public static <TT> u b(final Class<TT> cls, final Class<? extends TT> cls2, final t<? super TT> tVar) {
        return new u() {
            public <T> t<T> a(e eVar, com.b.a.c.a<T> aVar) {
                Class a2 = aVar.a();
                if (a2 == cls || a2 == cls2) {
                    return tVar;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + "+" + cls2.getName() + ",adapter=" + tVar + "]";
            }
        };
    }

    public static <T1> u b(final Class<T1> cls, final t<T1> tVar) {
        return new u() {
            public <T2> t<T2> a(e eVar, com.b.a.c.a<T2> aVar) {
                final Class a2 = aVar.a();
                if (!cls.isAssignableFrom(a2)) {
                    return null;
                }
                return new t<T1>() {
                    public void a(c cVar, T1 t1) throws IOException {
                        tVar.a(cVar, t1);
                    }

                    public T1 b(com.b.a.d.a aVar) throws IOException {
                        T1 b2 = tVar.b(aVar);
                        if (b2 == null || a2.isInstance(b2)) {
                            return b2;
                        }
                        throw new r("Expected a " + a2.getName() + " but was " + b2.getClass().getName());
                    }
                };
            }

            public String toString() {
                return "Factory[typeHierarchy=" + cls.getName() + ",adapter=" + tVar + "]";
            }
        };
    }
}
