package com.tencent.android.otherPush.a;

import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

/* compiled from: ProGuard */
public class a extends ClassLoader {
    private final C0056a a;

    /* renamed from: com.tencent.android.otherPush.a.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    private static class C0056a extends BaseDexClassLoader {
        private C0056a(String str, File file, String str2, ClassLoader classLoader) {
            super(str, file, str2, classLoader);
        }

        public Class<?> findClass(String str) {
            return super.findClass(str);
        }
    }

    public a(ClassLoader classLoader, String str, File file, String str2, List<String> list) {
        super(classLoader.getParent());
        this.a = a(file, str2, list, classLoader);
    }

    public Class<?> findClass(String str) {
        return this.a.findClass(str);
    }

    private static C0056a a(File file, String str, List<String> list, ClassLoader classLoader) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str2 : list) {
            if (z) {
                z = false;
            } else {
                sb.append(File.pathSeparator);
            }
            sb.append(str2);
        }
        Log.v("IncrementalClassLoader", "Incremental dex path is " + sb);
        Log.v("IncrementalClassLoader", "Native lib dir is " + str);
        return new C0056a(sb.toString(), file, str, classLoader);
    }

    private static void a(ClassLoader classLoader, ClassLoader classLoader2) {
        try {
            Field declaredField = ClassLoader.class.getDeclaredField("parent");
            declaredField.setAccessible(true);
            declaredField.set(classLoader, classLoader2);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static void a(ClassLoader classLoader, String str, File file, String str2, List<String> list) {
        a(classLoader, new a(classLoader, str, file, str2, list));
    }
}
