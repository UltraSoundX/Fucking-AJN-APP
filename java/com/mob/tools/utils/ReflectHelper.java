package com.mob.tools.utils;

import com.mob.tools.gui.CachePool;
import com.tencent.android.tpush.SettingsContentProvider;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ReflectHelper {
    private static CachePool<String, Constructor<?>> cachedConstr = new CachePool<>(5);
    private static CachePool<String, Method> cachedMethod = new CachePool<>(25);
    private static HashMap<String, Class<?>> classMap = new HashMap<>();
    private static HashMap<Class<?>, String> nameMap = new HashMap<>();
    private static HashSet<String> packageSet = new HashSet<>();

    public interface ReflectRunnable<ArgType, RetType> {
        RetType run(ArgType argtype);
    }

    static {
        packageSet.add("java.lang");
        packageSet.add("java.io");
        packageSet.add("java.nio");
        packageSet.add("java.net");
        packageSet.add("java.util");
        packageSet.add("com.mob.tools");
        packageSet.add("com.mob.tools.gui");
        packageSet.add("com.mob.tools.log");
        packageSet.add("com.mob.tools.network");
        packageSet.add("com.mob.tools.utils");
        classMap.put("double", Double.TYPE);
        classMap.put("float", Float.TYPE);
        classMap.put(SettingsContentProvider.LONG_TYPE, Long.TYPE);
        classMap.put("int", Integer.TYPE);
        classMap.put("short", Short.TYPE);
        classMap.put("byte", Byte.TYPE);
        classMap.put("char", Character.TYPE);
        classMap.put("boolean", Boolean.TYPE);
        classMap.put("Object", Object.class);
        classMap.put("String", String.class);
        classMap.put("Thread", Thread.class);
        classMap.put("Runnable", Runnable.class);
        classMap.put("System", System.class);
        classMap.put("double", Double.class);
        classMap.put("Float", Float.class);
        classMap.put("Long", Long.class);
        classMap.put("Integer", Integer.class);
        classMap.put("Short", Short.class);
        classMap.put("Byte", Byte.class);
        classMap.put("Character", Character.class);
        classMap.put("Boolean", Boolean.class);
        for (Entry entry : classMap.entrySet()) {
            nameMap.put(entry.getValue(), entry.getKey());
        }
    }

    public static String importClass(String str) throws Throwable {
        return importClass(null, str);
    }

    public static synchronized String importClass(String str, String str2) throws Throwable {
        String str3;
        synchronized (ReflectHelper.class) {
            if (str2.endsWith(".*")) {
                packageSet.add(str2.substring(0, str2.length() - 2));
                str3 = "*";
            } else {
                Class cls = Class.forName(str2);
                if (str == null) {
                    str3 = cls.getSimpleName();
                } else {
                    str3 = str;
                }
                if (classMap.containsKey(str3)) {
                    nameMap.remove(classMap.get(str3));
                }
                classMap.put(str3, cls);
                nameMap.put(cls, str3);
            }
        }
        return str3;
    }

    private static synchronized Class<?> getImportedClass(String str) {
        Class<?> cls;
        synchronized (ReflectHelper.class) {
            cls = (Class) classMap.get(str);
            if (cls == null) {
                Iterator it = packageSet.iterator();
                while (it.hasNext()) {
                    try {
                        importClass(((String) it.next()) + "." + str);
                    } catch (Throwable th) {
                    }
                    cls = (Class) classMap.get(str);
                    if (cls != null) {
                        break;
                    }
                }
            }
        }
        return cls;
    }

    private static Class<?>[] getTypes(Object[] objArr) {
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i] == null ? null : objArr[i].getClass();
        }
        return clsArr;
    }

    private static boolean primitiveEquals(Class<?> cls, Class<?> cls2) {
        return (cls == Byte.TYPE && cls2 == Byte.class) || (cls == Short.TYPE && (cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Character.TYPE && (cls2 == Character.class || cls2 == Short.class || cls2 == Byte.class)) || ((cls == Integer.TYPE && (cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Long.TYPE && (cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Float.TYPE && (cls2 == Float.class || cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Double.TYPE && (cls2 == Double.class || cls2 == Float.class || cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || (cls == Boolean.TYPE && cls2 == Boolean.class))))));
    }

    private static boolean matchParams(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr2.length; i++) {
            if (clsArr2[i] != null && !primitiveEquals(clsArr[i], clsArr2[i]) && !clsArr[i].isAssignableFrom(clsArr2[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean tryMatchParams(Class<?>[] clsArr, Class<?>[] clsArr2) {
        boolean z;
        if (clsArr.length - clsArr2.length != 1) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i < clsArr2.length) {
                if (clsArr2[i] != null && !primitiveEquals(clsArr[i], clsArr2[i]) && !clsArr[i].isAssignableFrom(clsArr2[i])) {
                    z = false;
                    break;
                }
                i++;
            } else {
                z = true;
                break;
            }
        }
        if (!z || !clsArr[clsArr.length - 1].isArray()) {
            return false;
        }
        return true;
    }

    public static Object newInstance(String str, Object... objArr) throws Throwable {
        try {
            return onNewInstance(str, objArr);
        } catch (Throwable th) {
            if (th instanceof NoSuchMethodException) {
                throw th;
            }
            throw new Throwable("className: " + str + ", methodName: <init>", th);
        }
    }

    private static Object onNewInstance(String str, Object... objArr) throws Throwable {
        boolean z;
        if (str.startsWith("[")) {
            return newArray(str, objArr);
        }
        Class importedClass = getImportedClass(str);
        String str2 = importedClass.getName() + "#" + objArr.length;
        Constructor constructor = (Constructor) cachedConstr.get(str2);
        Class[] types = getTypes(objArr);
        if (constructor == null || !matchParams(constructor.getParameterTypes(), types)) {
            Constructor[] declaredConstructors = importedClass.getDeclaredConstructors();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Constructor constructor2 : declaredConstructors) {
                Class[] parameterTypes = constructor2.getParameterTypes();
                if (matchParams(parameterTypes, types)) {
                    cachedConstr.put(str2, constructor2);
                    constructor2.setAccessible(true);
                    return constructor2.newInstance(objArr);
                }
                if (parameterTypes.length > 0 && parameterTypes[parameterTypes.length - 1].isArray() && types.length >= parameterTypes.length - 1) {
                    arrayList.add(constructor2);
                    arrayList2.add(parameterTypes);
                }
            }
            for (int i = 0; i < arrayList2.size(); i++) {
                Class[] clsArr = (Class[]) arrayList2.get(i);
                Class componentType = clsArr[clsArr.length - 1].getComponentType();
                if (tryMatchParams(clsArr, types)) {
                    Object[] objArr2 = new Object[(objArr.length + 1)];
                    System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
                    objArr2[objArr.length] = Array.newInstance(componentType, 0);
                    Constructor constructor3 = (Constructor) arrayList.get(i);
                    constructor3.setAccessible(true);
                    return constructor3.newInstance(objArr);
                }
                int length = clsArr.length - 1;
                while (true) {
                    if (length >= types.length) {
                        z = true;
                        break;
                    } else if (!types[length].equals(componentType)) {
                        z = false;
                        break;
                    } else {
                        length++;
                    }
                }
                if (z) {
                    int length2 = (types.length - clsArr.length) + 1;
                    Object newInstance = Array.newInstance(componentType, length2);
                    for (int i2 = 0; i2 < length2; i2++) {
                        Array.set(newInstance, i2, objArr[(clsArr.length - 1) + i2]);
                    }
                    Object[] objArr3 = new Object[(objArr.length + 1)];
                    System.arraycopy(objArr, 0, objArr3, 0, objArr.length);
                    objArr3[objArr.length] = newInstance;
                    Constructor constructor4 = (Constructor) arrayList.get(i);
                    constructor4.setAccessible(true);
                    return constructor4.newInstance(objArr);
                }
            }
            throw new NoSuchMethodException("className: " + str + ", methodName: <init>");
        }
        constructor.setAccessible(true);
        return constructor.newInstance(objArr);
    }

    private static Object newArray(String str, Object... objArr) throws Throwable {
        int[] iArr;
        Class importedClass;
        int i = 0;
        int i2 = 0;
        String str2 = str;
        while (str2.startsWith("[")) {
            i2++;
            str2 = str2.substring(1);
        }
        if (i2 == objArr.length) {
            int[] iArr2 = new int[i2];
            while (i < i2) {
                try {
                    iArr2[i] = Integer.parseInt(String.valueOf(objArr[i]));
                    i++;
                } catch (Throwable th) {
                    iArr = null;
                }
            }
            iArr = iArr2;
        } else {
            iArr = null;
        }
        if (iArr != null) {
            if ("B".equals(str2)) {
                importedClass = Byte.TYPE;
            } else if ("S".equals(str2)) {
                importedClass = Short.TYPE;
            } else if ("I".equals(str2)) {
                importedClass = Integer.TYPE;
            } else if ("J".equals(str2)) {
                importedClass = Long.TYPE;
            } else if ("F".equals(str2)) {
                importedClass = Float.TYPE;
            } else if ("D".equals(str2)) {
                importedClass = Double.TYPE;
            } else if ("Z".equals(str2)) {
                importedClass = Boolean.TYPE;
            } else if ("C".equals(str2)) {
                importedClass = Character.TYPE;
            } else {
                importedClass = getImportedClass(str2);
            }
            if (importedClass != null) {
                return Array.newInstance(importedClass, iArr);
            }
        }
        throw new NoSuchMethodException("className: [" + str + ", methodName: <init>");
    }

    public static <T> T invokeStaticMethod(String str, String str2, Object... objArr) throws Throwable {
        try {
            return invokeMethod(str, null, str2, objArr);
        } catch (Throwable th) {
            if (th instanceof NoSuchMethodException) {
                throw th;
            }
            throw new Throwable("className: " + str + ", methodName: " + str2, th);
        }
    }

    private static <T> T invokeMethod(String str, Object obj, String str2, Object... objArr) throws Throwable {
        Class cls;
        boolean z;
        if (obj == null) {
            cls = getImportedClass(str);
        } else {
            cls = obj.getClass();
        }
        String str3 = cls.getName() + "#" + str2 + "#" + objArr.length;
        Method method = (Method) cachedMethod.get(str3);
        Class[] types = getTypes(objArr);
        if (method != null) {
            boolean isStatic = Modifier.isStatic(method.getModifiers());
            if (obj != null) {
                isStatic = !isStatic;
            }
            if (isStatic && matchParams(method.getParameterTypes(), types)) {
                method.setAccessible(true);
                if (method.getReturnType() != Void.TYPE) {
                    return method.invoke(obj, objArr);
                }
                method.invoke(obj, objArr);
                return null;
            }
        }
        ArrayList arrayList = new ArrayList();
        while (cls != null) {
            arrayList.add(cls);
            cls = cls.getSuperclass();
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Method[] declaredMethods = ((Class) it.next()).getDeclaredMethods();
            int length = declaredMethods.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    Method method2 = declaredMethods[i];
                    boolean isStatic2 = Modifier.isStatic(method2.getModifiers());
                    if (obj != null) {
                        isStatic2 = !isStatic2;
                    }
                    if (method2.getName().equals(str2) && isStatic2) {
                        Class[] parameterTypes = method2.getParameterTypes();
                        if (matchParams(parameterTypes, types)) {
                            cachedMethod.put(str3, method2);
                            method2.setAccessible(true);
                            if (method2.getReturnType() != Void.TYPE) {
                                return method2.invoke(obj, objArr);
                            }
                            method2.invoke(obj, objArr);
                            return null;
                        } else if (parameterTypes.length > 0 && parameterTypes[parameterTypes.length - 1].isArray() && types.length >= parameterTypes.length - 1) {
                            arrayList2.add(method2);
                            arrayList3.add(parameterTypes);
                        }
                    }
                    i++;
                }
            }
        }
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < arrayList3.size()) {
                Class[] clsArr = (Class[]) arrayList3.get(i3);
                Class componentType = clsArr[clsArr.length - 1].getComponentType();
                if (tryMatchParams(clsArr, types)) {
                    Object[] objArr2 = new Object[(objArr.length + 1)];
                    System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
                    objArr2[objArr.length] = Array.newInstance(componentType, 0);
                    Method method3 = (Method) arrayList2.get(i3);
                    method3.setAccessible(true);
                    if (method3.getReturnType() != Void.TYPE) {
                        return method3.invoke(obj, objArr2);
                    }
                    method3.invoke(obj, objArr2);
                    return null;
                }
                int length2 = clsArr.length - 1;
                while (true) {
                    if (length2 >= types.length) {
                        z = true;
                        break;
                    } else if (!types[length2].equals(componentType)) {
                        z = false;
                        break;
                    } else {
                        length2++;
                    }
                }
                if (z) {
                    int length3 = (types.length - clsArr.length) + 1;
                    Object newInstance = Array.newInstance(componentType, length3);
                    for (int i4 = 0; i4 < length3; i4++) {
                        Array.set(newInstance, i4, objArr[(clsArr.length - 1) + i4]);
                    }
                    Object[] objArr3 = new Object[clsArr.length];
                    System.arraycopy(objArr, 0, objArr3, 0, clsArr.length - 1);
                    objArr3[clsArr.length - 1] = newInstance;
                    Method method4 = (Method) arrayList2.get(i3);
                    method4.setAccessible(true);
                    if (method4.getReturnType() != Void.TYPE) {
                        return method4.invoke(obj, objArr3);
                    }
                    method4.invoke(obj, objArr3);
                    return null;
                }
                i2 = i3 + 1;
            } else {
                throw new NoSuchMethodException("className: " + obj.getClass() + ", methodName: " + str2);
            }
        }
    }

    public static <T> T invokeInstanceMethod(Object obj, String str, Object... objArr) throws Throwable {
        try {
            return invokeMethod(null, obj, str, objArr);
        } catch (Throwable th) {
            if (th instanceof NoSuchMethodException) {
                throw th;
            }
            throw new Throwable("className: " + obj.getClass() + ", methodName: " + str, th);
        }
    }

    public static <T> T getStaticField(String str, String str2) throws Throwable {
        try {
            return onGetStaticField(str, str2);
        } catch (Throwable th) {
            if (th instanceof NoSuchFieldException) {
                throw th;
            }
            throw new Throwable("className: " + str + ", fieldName: " + str2, th);
        }
    }

    private static <T> T onGetStaticField(String str, String str2) throws Throwable {
        Field field;
        ArrayList arrayList = new ArrayList();
        for (Class importedClass = getImportedClass(str); importedClass != null; importedClass = importedClass.getSuperclass()) {
            arrayList.add(importedClass);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                field = ((Class) it.next()).getDeclaredField(str2);
            } catch (Throwable th) {
                field = null;
            }
            if (field != null && Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                return field.get(null);
            }
        }
        throw new NoSuchFieldException("className: " + str + ", fieldName: " + str2);
    }

    public static void setStaticField(String str, String str2, Object obj) throws Throwable {
        try {
            onSetStaticField(str, str2, obj);
        } catch (Throwable th) {
            if (th instanceof NoSuchFieldException) {
                throw th;
            }
            throw new Throwable("className: " + str + ", fieldName: " + str2 + ", value: " + String.valueOf(obj), th);
        }
    }

    private static void onSetStaticField(String str, String str2, Object obj) throws Throwable {
        Field field;
        ArrayList arrayList = new ArrayList();
        for (Class importedClass = getImportedClass(str); importedClass != null; importedClass = importedClass.getSuperclass()) {
            arrayList.add(importedClass);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                field = ((Class) it.next()).getDeclaredField(str2);
            } catch (Throwable th) {
                field = null;
            }
            if (field != null && Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                field.set(null, obj);
                return;
            }
        }
        throw new NoSuchFieldException("className: " + str + ", fieldName: " + str2 + ", value: " + String.valueOf(obj));
    }

    public static <T> T getInstanceField(Object obj, String str) throws Throwable {
        try {
            return onGetInstanceField(obj, str);
        } catch (Throwable th) {
            if (th instanceof NoSuchFieldException) {
                throw th;
            }
            throw new Throwable("className: " + obj.getClass() + ", fieldName: " + str, th);
        }
    }

    private static <T> T onGetInstanceField(Object obj, String str) throws Throwable {
        Field field;
        if ((obj instanceof List) || obj.getClass().isArray()) {
            return onGetElement(obj, str);
        }
        if (obj instanceof Map) {
            return ((Map) obj).get(str);
        }
        ArrayList arrayList = new ArrayList();
        for (Class cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            arrayList.add(cls);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                field = ((Class) it.next()).getDeclaredField(str);
            } catch (Throwable th) {
                field = null;
            }
            if (field != null && !Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                return field.get(obj);
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str);
    }

    private static Object onGetElement(Object obj, String str) throws Throwable {
        int i;
        int i2;
        if (obj instanceof List) {
            if (str.startsWith("[") && str.endsWith("]")) {
                try {
                    i2 = Integer.parseInt(str.substring(1, str.length() - 1));
                } catch (Throwable th) {
                    i2 = -1;
                }
                if (i2 != -1) {
                    return ((List) obj).get(i2);
                }
            }
        } else if ("length".equals(str)) {
            return Integer.valueOf(Array.getLength(obj));
        } else {
            if (str.startsWith("[") && str.endsWith("]")) {
                try {
                    i = Integer.parseInt(str.substring(1, str.length() - 1));
                } catch (Throwable th2) {
                    i = -1;
                }
                if (i != -1) {
                    return Array.get(obj, i);
                }
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str);
    }

    public static void setInstanceField(Object obj, String str, Object obj2) throws Throwable {
        try {
            onSetInstanceField(obj, str, obj2);
        } catch (Throwable th) {
            if (th instanceof NoSuchFieldException) {
                throw th;
            }
            throw new Throwable("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2), th);
        }
    }

    private static void onSetInstanceField(Object obj, String str, Object obj2) throws Throwable {
        Field field;
        if ((obj instanceof List) || obj.getClass().isArray()) {
            onSetElement(obj, str, obj2);
        } else if (obj instanceof Map) {
            ((Map) obj).put(str, obj2);
        } else {
            ArrayList arrayList = new ArrayList();
            for (Class cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
                arrayList.add(cls);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                try {
                    field = ((Class) it.next()).getDeclaredField(str);
                } catch (Throwable th) {
                    field = null;
                }
                if (field != null && !Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    field.set(obj, obj2);
                    return;
                }
            }
            throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2));
        }
    }

    private static void onSetElement(Object obj, String str, Object obj2) throws Throwable {
        int i;
        Object obj3;
        int i2;
        Object obj4 = null;
        if (obj instanceof List) {
            if (str.startsWith("[") && str.endsWith("]")) {
                try {
                    i2 = Integer.parseInt(str.substring(1, str.length() - 1));
                } catch (Throwable th) {
                    i2 = -1;
                }
                if (i2 != -1) {
                    ((List) obj).set(i2, obj2);
                    return;
                }
            }
        } else if (str.startsWith("[") && str.endsWith("]")) {
            try {
                i = Integer.parseInt(str.substring(1, str.length() - 1));
            } catch (Throwable th2) {
                i = -1;
            }
            if (i != -1) {
                String name = obj.getClass().getName();
                while (name.startsWith("[")) {
                    name = name.substring(1);
                }
                Class<Byte> cls = obj2.getClass();
                if ("B".equals(name)) {
                    if (cls == Byte.class) {
                        Array.set(obj, i, obj2);
                        return;
                    }
                } else if ("S".equals(name)) {
                    if (cls == Short.class) {
                        obj4 = obj2;
                    } else if (cls == Byte.class) {
                        obj4 = Short.valueOf((short) ((Byte) obj2).byteValue());
                    }
                    if (obj4 != null) {
                        Array.set(obj, i, obj4);
                        return;
                    }
                } else if ("I".equals(name)) {
                    if (cls == Integer.class) {
                        obj4 = obj2;
                    } else if (cls == Short.class) {
                        obj4 = Integer.valueOf(((Short) obj2).shortValue());
                    } else if (cls == Byte.class) {
                        obj4 = Integer.valueOf(((Byte) obj2).byteValue());
                    }
                    if (obj4 != null) {
                        Array.set(obj, i, obj4);
                        return;
                    }
                } else if ("J".equals(name)) {
                    if (cls == Long.class) {
                        obj4 = obj2;
                    } else if (cls == Integer.class) {
                        obj4 = Long.valueOf((long) ((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        obj4 = Long.valueOf((long) ((Short) obj2).shortValue());
                    } else if (cls == Byte.class) {
                        obj4 = Long.valueOf((long) ((Byte) obj2).byteValue());
                    }
                    if (obj4 != null) {
                        Array.set(obj, i, obj4);
                        return;
                    }
                } else if ("F".equals(name)) {
                    if (cls == Float.class) {
                        obj4 = obj2;
                    } else if (cls == Long.class) {
                        obj4 = Float.valueOf((float) ((Long) obj2).longValue());
                    } else if (cls == Integer.class) {
                        obj4 = Float.valueOf((float) ((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        obj4 = Float.valueOf((float) ((Short) obj2).shortValue());
                    } else if (cls == Byte.class) {
                        obj4 = Float.valueOf((float) ((Byte) obj2).byteValue());
                    }
                    if (obj4 != null) {
                        Array.set(obj, i, obj4);
                        return;
                    }
                } else if ("D".equals(name)) {
                    if (cls == Double.class) {
                        obj3 = obj2;
                    } else if (cls == Float.class) {
                        obj3 = Double.valueOf((double) ((Float) obj2).floatValue());
                    } else if (cls == Long.class) {
                        obj3 = Double.valueOf((double) ((Long) obj2).longValue());
                    } else if (cls == Integer.class) {
                        obj3 = Double.valueOf((double) ((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        obj3 = Double.valueOf((double) ((Short) obj2).shortValue());
                    } else if (cls == Byte.class) {
                        obj3 = Double.valueOf((double) ((Byte) obj2).byteValue());
                    } else {
                        obj3 = null;
                    }
                    if (obj3 != null) {
                        Array.set(obj, i, obj3);
                        return;
                    }
                } else if ("Z".equals(name)) {
                    if (cls == Boolean.class) {
                        Array.set(obj, i, obj2);
                        return;
                    }
                } else if ("C".equals(name)) {
                    if (cls == Character.class) {
                        Array.set(obj, i, obj2);
                        return;
                    }
                } else if (name.equals(cls.getName())) {
                    Array.set(obj, i, obj2);
                    return;
                }
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2));
    }

    public static Class<?> getClass(String str) throws Throwable {
        Class<?> importedClass = getImportedClass(str);
        if (importedClass == null) {
            try {
                importedClass = Class.forName(str);
                if (importedClass != null) {
                    classMap.put(str, importedClass);
                }
            } catch (Throwable th) {
            }
        }
        return importedClass;
    }

    public static String getName(Class<?> cls) throws Throwable {
        String str = (String) nameMap.get(cls);
        if (str == null) {
            str = cls.getSimpleName();
            if (classMap.containsKey(str)) {
                nameMap.remove(classMap.get(str));
            }
            classMap.put(str, cls);
            nameMap.put(cls, str);
        }
        return str;
    }

    public static Object createProxy(HashMap<String, ReflectRunnable<Object, Object[]>> hashMap, Class<?>... clsArr) throws Throwable {
        HashMap hashMap2 = new HashMap();
        for (final Entry entry : hashMap.entrySet()) {
            hashMap2.put(entry.getKey(), new ReflectRunnable<Object[], Object>() {
                public Object run(Object[] objArr) {
                    return ((Object[]) ((ReflectRunnable) entry.getValue()).run(objArr))[0];
                }
            });
        }
        return createProxy((Map<String, ReflectRunnable<Object[], Object>>) hashMap2, clsArr);
    }

    public static Object createProxy(final Map<String, ReflectRunnable<Object[], Object>> map, Class<?>... clsArr) throws Throwable {
        if (clsArr.length == 0) {
            return null;
        }
        return Proxy.newProxyInstance(clsArr[0].getClassLoader(), clsArr, new InvocationHandler() {
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                ReflectRunnable reflectRunnable = (ReflectRunnable) map.get(method.getName());
                if (reflectRunnable != null) {
                    return reflectRunnable.run(objArr);
                }
                throw new NoSuchMethodException();
            }
        });
    }
}
