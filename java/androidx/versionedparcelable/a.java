package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;

/* compiled from: VersionedParcel */
public abstract class a {
    /* access modifiers changed from: protected */
    public abstract void a(int i);

    /* access modifiers changed from: protected */
    public abstract void a(Parcelable parcelable);

    /* access modifiers changed from: protected */
    public abstract void a(String str);

    /* access modifiers changed from: protected */
    public abstract void a(byte[] bArr);

    /* access modifiers changed from: protected */
    public abstract void b();

    /* access modifiers changed from: protected */
    public abstract boolean b(int i);

    /* access modifiers changed from: protected */
    public abstract a c();

    /* access modifiers changed from: protected */
    public abstract void c(int i);

    /* access modifiers changed from: protected */
    public abstract int d();

    /* access modifiers changed from: protected */
    public abstract String e();

    /* access modifiers changed from: protected */
    public abstract byte[] f();

    /* access modifiers changed from: protected */
    public abstract <T extends Parcelable> T g();

    public boolean a() {
        return false;
    }

    public void a(boolean z, boolean z2) {
    }

    public void a(byte[] bArr, int i) {
        c(i);
        a(bArr);
    }

    public void a(int i, int i2) {
        c(i2);
        a(i);
    }

    public void a(String str, int i) {
        c(i);
        a(str);
    }

    public void a(Parcelable parcelable, int i) {
        c(i);
        a(parcelable);
    }

    public int b(int i, int i2) {
        return !b(i2) ? i : d();
    }

    public String b(String str, int i) {
        return !b(i) ? str : e();
    }

    public byte[] b(byte[] bArr, int i) {
        return !b(i) ? bArr : f();
    }

    public <T extends Parcelable> T b(T t, int i) {
        return !b(i) ? t : g();
    }

    public void a(c cVar, int i) {
        c(i);
        a(cVar);
    }

    /* access modifiers changed from: protected */
    public void a(c cVar) {
        if (cVar == null) {
            a((String) null);
            return;
        }
        b(cVar);
        a c = c();
        a((T) cVar, c);
        c.b();
    }

    private void b(c cVar) {
        try {
            a(a(cVar.getClass()).getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(cVar.getClass().getSimpleName() + " does not have a Parcelizer", e);
        }
    }

    public <T extends c> T b(T t, int i) {
        return !b(i) ? t : h();
    }

    /* access modifiers changed from: protected */
    public <T extends c> T h() {
        String e = e();
        if (e == null) {
            return null;
        }
        return a(e, c());
    }

    protected static <T extends c> T a(String str, a aVar) {
        try {
            return (c) Class.forName(str, true, a.class.getClassLoader()).getDeclaredMethod("read", new Class[]{a.class}).invoke(null, new Object[]{aVar});
        } catch (IllegalAccessException e) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e4);
        }
    }

    protected static <T extends c> void a(T t, a aVar) {
        try {
            c(t).getDeclaredMethod("write", new Class[]{t.getClass(), a.class}).invoke(null, new Object[]{t, aVar});
        } catch (IllegalAccessException e) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e4);
        }
    }

    private static <T extends c> Class c(T t) throws ClassNotFoundException {
        return a(t.getClass());
    }

    private static Class a(Class<? extends c> cls) throws ClassNotFoundException {
        return Class.forName(String.format("%s.%sParcelizer", new Object[]{cls.getPackage().getName(), cls.getSimpleName()}), false, cls.getClassLoader());
    }
}
