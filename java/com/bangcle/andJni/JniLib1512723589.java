package com.bangcle.andJni;

import android.app.Application;
import android.content.Context;
import com.stub.StubApp;
import java.io.File;
import java.lang.reflect.Method;

/* compiled from: JniLib */
public class JniLib1512723589 extends Application {
    static final boolean a;
    private static boolean b = false;

    public static native void a(Class cls, int i);

    public static native byte cB(Object[] objArr);

    public static native char cC(Object[] objArr);

    public static native double cD(Object[] objArr);

    public static native float cF(Object[] objArr);

    public static native int cI(Object[] objArr);

    public static native long cJ(Object[] objArr);

    public static native Object cL(Object[] objArr);

    public static native short cS(Object[] objArr);

    public static native void cV(Object[] objArr);

    public static native boolean cZ(Object[] objArr);

    static {
        boolean z = false;
        if (!JniLib1512723589.class.desiredAssertionStatus()) {
            z = true;
        }
        a = z;
        try {
            System.loadLibrary("dexvmp");
        } catch (UnsatisfiedLinkError e) {
            try {
                a(StubApp.getOrigApplicationContext(getApplicationUsingReflection().getApplicationContext()), "dexvmp");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean InvokeBoolean(Object[] objArr) throws Exception {
        Object obj = objArr[0];
        Method method = objArr[1];
        Object[] objArr2 = new Object[(objArr.length - 2)];
        for (int i = 0; i < objArr.length - 2; i++) {
            objArr2[i] = objArr[i + 2];
        }
        return ((Boolean) method.invoke(obj, objArr2)).booleanValue();
    }

    public static byte InvokeByte(Object[] objArr) throws Exception {
        Object obj = objArr[0];
        Method method = objArr[1];
        Object[] objArr2 = new Object[(objArr.length - 2)];
        for (int i = 0; i < objArr.length - 2; i++) {
            objArr2[i] = objArr[i + 2];
        }
        return ((Byte) method.invoke(obj, objArr2)).byteValue();
    }

    public static char InvokeChar(Object[] objArr) throws Exception {
        Object obj = objArr[0];
        Method method = objArr[1];
        Object[] objArr2 = new Object[(objArr.length - 2)];
        for (int i = 0; i < objArr.length - 2; i++) {
            objArr2[i] = objArr[i + 2];
        }
        return ((Character) method.invoke(obj, objArr2)).charValue();
    }

    public static double InvokeDouble(Object[] objArr) throws Exception {
        Object obj = objArr[0];
        Method method = objArr[1];
        Object[] objArr2 = new Object[(objArr.length - 2)];
        for (int i = 0; i < objArr.length - 2; i++) {
            objArr2[i] = objArr[i + 2];
        }
        return ((Double) method.invoke(obj, objArr2)).doubleValue();
    }

    public static float InvokeFloat(Object[] objArr) throws Exception {
        Object obj = objArr[0];
        Method method = objArr[1];
        Object[] objArr2 = new Object[(objArr.length - 2)];
        for (int i = 0; i < objArr.length - 2; i++) {
            objArr2[i] = objArr[i + 2];
        }
        return ((Float) method.invoke(obj, objArr2)).floatValue();
    }

    public static int InvokeInt(Object[] objArr) throws Exception {
        Object obj = objArr[0];
        Method method = objArr[1];
        Object[] objArr2 = new Object[(objArr.length - 2)];
        for (int i = 0; i < objArr.length - 2; i++) {
            objArr2[i] = objArr[i + 2];
        }
        return ((Integer) method.invoke(obj, objArr2)).intValue();
    }

    public static long InvokeLong(Object[] objArr) throws Exception {
        Object obj = objArr[0];
        Method method = objArr[1];
        Object[] objArr2 = new Object[(objArr.length - 2)];
        for (int i = 0; i < objArr.length - 2; i++) {
            objArr2[i] = objArr[i + 2];
        }
        return ((Long) method.invoke(obj, objArr2)).longValue();
    }

    public static Object InvokeObject(Object[] objArr) throws Exception {
        Object obj = objArr[0];
        Method method = objArr[1];
        Object[] objArr2 = new Object[(objArr.length - 2)];
        for (int i = 0; i < objArr.length - 2; i++) {
            objArr2[i] = objArr[i + 2];
        }
        return method.invoke(obj, objArr2);
    }

    public static short InvokeShort(Object[] objArr) throws Exception {
        Object obj = objArr[0];
        Method method = objArr[1];
        Object[] objArr2 = new Object[(objArr.length - 2)];
        for (int i = 0; i < objArr.length - 2; i++) {
            objArr2[i] = objArr[i + 2];
        }
        return ((Short) method.invoke(obj, objArr2)).shortValue();
    }

    public static void InvokeVoid(Object[] objArr) throws Exception {
        Object obj = objArr[0];
        Method method = objArr[1];
        Object[] objArr2 = new Object[(objArr.length - 2)];
        for (int i = 0; i < objArr.length - 2; i++) {
            objArr2[i] = objArr[i + 2];
        }
        method.invoke(obj, objArr2);
    }

    public static void b() {
        cV(new Object[]{Integer.valueOf(0)});
    }

    public static Application getApplicationUsingReflection() throws Exception {
        return (Application) cL(new Object[]{Integer.valueOf(2)});
    }

    public static File getWorkaroundLibDir(Context context) {
        return (File) cL(new Object[]{context, Integer.valueOf(5)});
    }

    static boolean a(Context context, String str) {
        return cZ(new Object[]{context, str, Integer.valueOf(8)});
    }
}
