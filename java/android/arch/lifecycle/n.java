package android.arch.lifecycle;

/* compiled from: ViewModelProvider */
public class n {
    private final a a;
    private final o b;

    /* compiled from: ViewModelProvider */
    public interface a {
        <T extends m> T create(Class<T> cls);
    }

    public n(o oVar, a aVar) {
        this.a = aVar;
        this.b = oVar;
    }

    public <T extends m> T a(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return a("android.arch.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public <T extends m> T a(String str, Class<T> cls) {
        T a2 = this.b.a(str);
        if (cls.isInstance(a2)) {
            return a2;
        }
        if (a2 != null) {
        }
        T create = this.a.create(cls);
        this.b.a(str, create);
        return create;
    }
}
