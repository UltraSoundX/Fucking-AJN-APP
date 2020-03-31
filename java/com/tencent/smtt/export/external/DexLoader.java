package com.tencent.smtt.export.external;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.mid.sotrage.StorageInterface;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import net.sf.json.util.JSONUtils;

public class DexLoader {
    private static final String JAVACORE_PACKAGE_PREFIX = "org.chromium";
    private static final String TAF_PACKAGE_PREFIX = "com.taf";
    private static final String TAG = "DexLoader";
    private static final String TBS_FUSION_DEX = "tbs_jars_fusion_dex";
    private static final String TBS_WEBVIEW_DEX = "webview_dex";
    private static final String TENCENT_PACKAGE_PREFIX = "com.tencent";
    static boolean mCanUseDexLoaderProviderService = true;
    /* access modifiers changed from: private */
    public static boolean mMttClassUseCorePrivate = false;
    private static boolean mUseSpeedyClassLoader = false;
    private static boolean mUseTbsCorePrivateClassLoader = false;
    private DexClassLoader mClassLoader;

    private static class TbsCorePrivateClassLoader extends DexClassLoader {
        public TbsCorePrivateClassLoader(String str, String str2, String str3, ClassLoader classLoader) {
            super(str, str2, str3, classLoader);
        }

        /* access modifiers changed from: protected */
        public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
            if (str == null) {
                return super.loadClass(str, z);
            }
            boolean startsWith = str.startsWith(DexLoader.JAVACORE_PACKAGE_PREFIX);
            if (DexLoader.mMttClassUseCorePrivate) {
                startsWith = startsWith || str.startsWith(DexLoader.TENCENT_PACKAGE_PREFIX) || str.startsWith(DexLoader.TAF_PACKAGE_PREFIX);
            }
            if (!startsWith) {
                return super.loadClass(str, z);
            }
            Class<?> findLoadedClass = findLoadedClass(str);
            if (findLoadedClass != null) {
                return findLoadedClass;
            }
            try {
                Log.d(DexLoader.TAG, "WebCoreClassLoader - loadClass(" + str + StorageInterface.KEY_SPLITER + z + ")...");
                findLoadedClass = findClass(str);
            } catch (ClassNotFoundException e) {
            }
            if (findLoadedClass != null) {
                return findLoadedClass;
            }
            ClassLoader parent = getParent();
            if (parent != null) {
                return parent.loadClass(str);
            }
            return findLoadedClass;
        }
    }

    public static void initTbsSettings(Map<String, Object> map) {
        Log.d(TAG, "initTbsSettings - " + map);
        if (map != null) {
            try {
                Object obj = map.get(TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER);
                if (obj instanceof Boolean) {
                    mUseTbsCorePrivateClassLoader = ((Boolean) obj).booleanValue();
                }
                Object obj2 = map.get(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER);
                if (obj2 instanceof Boolean) {
                    mUseSpeedyClassLoader = ((Boolean) obj2).booleanValue();
                }
                Object obj3 = map.get(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE);
                if (obj3 instanceof Boolean) {
                    mCanUseDexLoaderProviderService = ((Boolean) obj3).booleanValue();
                }
                Object obj4 = map.get(TbsCoreSettings.TBS_SETTINGS_PRAVITE_MTT_CLASSES);
                if (obj4 instanceof Boolean) {
                    mMttClassUseCorePrivate = ((Boolean) obj4).booleanValue();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private boolean shouldUseTbsCorePrivateClassLoader(String str) {
        if (!mUseTbsCorePrivateClassLoader) {
            return false;
        }
        if (str.contains(TBS_FUSION_DEX) || str.contains(TBS_WEBVIEW_DEX)) {
            return true;
        }
        return false;
    }

    public DexLoader(String str, Context context, String[] strArr, String str2) {
        this(str, context, strArr, str2, null);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.ClassLoader] */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v0 */
    /* JADX WARNING: type inference failed for: r4v1, types: [java.lang.ClassLoader] */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.ClassLoader] */
    /* JADX WARNING: type inference failed for: r4v3 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DexLoader(java.lang.String r8, android.content.Context r9, java.lang.String[] r10, java.lang.String r11, java.util.Map<java.lang.String, java.lang.Object> r12) {
        /*
            r7 = this;
            r7.<init>()
            initTbsSettings(r12)
            java.lang.ClassLoader r0 = dalvik.system.VMStack.getCallingClassLoader()
            if (r0 != 0) goto L_0x0010
            java.lang.ClassLoader r0 = r9.getClassLoader()
        L_0x0010:
            java.lang.String r1 = "dexloader"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Set base classLoader for DexClassLoader: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r1, r2)
            r1 = 0
            r6 = r1
            r4 = r0
        L_0x002b:
            int r0 = r10.length
            if (r6 >= r0) goto L_0x003e
            r1 = r10[r6]
            r0 = r7
            r2 = r11
            r3 = r8
            r5 = r9
            dalvik.system.DexClassLoader r4 = r0.createDexClassLoader(r1, r2, r3, r4, r5)
            r7.mClassLoader = r4
            int r0 = r6 + 1
            r6 = r0
            goto L_0x002b
        L_0x003e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.export.external.DexLoader.<init>(java.lang.String, android.content.Context, java.lang.String[], java.lang.String, java.util.Map):void");
    }

    public DexLoader(Context context, String[] strArr, String str) {
        this((String) null, context, strArr, str);
    }

    public DexLoader(Context context, String[] strArr, String str, String str2) {
        ClassLoader classLoader = context.getClassLoader();
        String str3 = context.getApplicationInfo().nativeLibraryDir;
        if (!TextUtils.isEmpty(str2)) {
            str3 = str3 + File.pathSeparator + str2;
        }
        int i = 0;
        ClassLoader classLoader2 = classLoader;
        while (true) {
            int i2 = i;
            if (i2 < strArr.length) {
                DexClassLoader createDexClassLoader = createDexClassLoader(strArr[i2], str, str3, classLoader2, context);
                this.mClassLoader = createDexClassLoader;
                i = i2 + 1;
                classLoader2 = createDexClassLoader;
            } else {
                return;
            }
        }
    }

    public DexLoader(Context context, String[] strArr, String str, DexLoader dexLoader) {
        DexClassLoader classLoader = dexLoader.getClassLoader();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < strArr.length) {
                classLoader = createDexClassLoader(strArr[i2], str, context.getApplicationInfo().nativeLibraryDir, classLoader, context);
                this.mClassLoader = classLoader;
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public DexLoader(Context context, String str, String str2) {
        this(context, new String[]{str}, str2);
    }

    private DexClassLoader createDexClassLoader(String str, String str2, String str3, ClassLoader classLoader, Context context) {
        DexClassLoader dexClassLoader;
        Log.d("dexloader", "createDexClassLoader: " + str);
        if (shouldUseTbsCorePrivateClassLoader(str)) {
            dexClassLoader = new TbsCorePrivateClassLoader(str, str2, str3, classLoader);
        } else if (VERSION.SDK_INT < 21 || VERSION.SDK_INT > 25 || !mUseSpeedyClassLoader) {
            Log.d("dexloader", "sync odex...new DexClassLoader");
            dexClassLoader = new DexClassLoader(str, str2, str3, classLoader);
        } else {
            Log.d("dexloader", "async odex...DexClassLoaderProvider.createDexClassLoader");
            try {
                dexClassLoader = DexClassLoaderProvider.createDexClassLoader(str, str2, str3, classLoader, context);
            } catch (Throwable th) {
                Log.e("dexloader", "createDexClassLoader exception: " + th);
                Log.d("dexloader", "sync odex...new DexClassLoader#2");
                dexClassLoader = new DexClassLoader(str, str2, str3, classLoader);
            }
        }
        Log.d("dexloader", "createDexClassLoader result: " + dexClassLoader);
        return dexClassLoader;
    }

    public DexClassLoader getClassLoader() {
        return this.mClassLoader;
    }

    public Object newInstance(String str) {
        try {
            return this.mClassLoader.loadClass(str).newInstance();
        } catch (Throwable th) {
            Log.e(getClass().getSimpleName(), "create " + str + " instance failed", th);
            return null;
        }
    }

    public Object newInstance(String str, Class<?>[] clsArr, Object... objArr) {
        try {
            return this.mClassLoader.loadClass(str).getConstructor(clsArr).newInstance(objArr);
        } catch (Throwable th) {
            if ("com.tencent.smtt.webkit.adapter.X5WebViewAdapter".equalsIgnoreCase(str)) {
                Log.e(getClass().getSimpleName(), "'newInstance " + str + " failed", th);
                return th;
            }
            Log.e(getClass().getSimpleName(), "create '" + str + "' instance failed", th);
            return null;
        }
    }

    public Class<?> loadClass(String str) {
        try {
            return this.mClassLoader.loadClass(str);
        } catch (Throwable th) {
            Log.e(getClass().getSimpleName(), "loadClass '" + str + "' failed", th);
            return null;
        }
    }

    public Object invokeStaticMethod(String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            Method method = this.mClassLoader.loadClass(str).getMethod(str2, clsArr);
            method.setAccessible(true);
            return method.invoke(null, objArr);
        } catch (Throwable th) {
            if (str2 == null || !str2.equalsIgnoreCase("initTesRuntimeEnvironment")) {
                Log.e(getClass().getSimpleName(), JSONUtils.SINGLE_QUOTE + str + "' invoke static method '" + str2 + "' failed", th);
                return null;
            }
            Log.e(getClass().getSimpleName(), JSONUtils.SINGLE_QUOTE + str + "' invoke static method '" + str2 + "' failed", th);
            return th;
        }
    }

    public Object invokeMethod(Object obj, String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            Method method = this.mClassLoader.loadClass(str).getMethod(str2, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (Throwable th) {
            Log.e(getClass().getSimpleName(), JSONUtils.SINGLE_QUOTE + str + "' invoke method '" + str2 + "' failed", th);
            return null;
        }
    }

    public Object getStaticField(String str, String str2) {
        boolean z = false;
        try {
            Field field = this.mClassLoader.loadClass(str).getField(str2);
            field.setAccessible(true);
            return field.get(null);
        } catch (Throwable th) {
            Log.e(getClass().getSimpleName(), JSONUtils.SINGLE_QUOTE + str + "' get field '" + str2 + "' failed", th);
            return z;
        }
    }

    public void setStaticField(String str, String str2, Object obj) {
        try {
            Field field = this.mClassLoader.loadClass(str).getField(str2);
            field.setAccessible(true);
            field.set(null, obj);
        } catch (Throwable th) {
            Log.e(getClass().getSimpleName(), JSONUtils.SINGLE_QUOTE + str + "' set field '" + str2 + "' failed", th);
        }
    }
}
