package net.sf.json.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import net.sf.json.JSONObject;

public abstract class NewBeanInstanceStrategy {
    public static final NewBeanInstanceStrategy DEFAULT = new DefaultNewBeanInstanceStrategy(null);

    /* renamed from: net.sf.json.util.NewBeanInstanceStrategy$1 reason: invalid class name */
    static class AnonymousClass1 {
    }

    private static final class DefaultNewBeanInstanceStrategy extends NewBeanInstanceStrategy {
        private static final Object[] EMPTY_ARGS = new Object[0];
        private static final Class[] EMPTY_PARAM_TYPES = new Class[0];

        private DefaultNewBeanInstanceStrategy() {
        }

        DefaultNewBeanInstanceStrategy(AnonymousClass1 r1) {
            this();
        }

        public Object newInstance(Class cls, JSONObject jSONObject) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, InvocationTargetException {
            if (cls == null) {
                return null;
            }
            Constructor declaredConstructor = cls.getDeclaredConstructor(EMPTY_PARAM_TYPES);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(EMPTY_ARGS);
        }
    }

    public abstract Object newInstance(Class cls, JSONObject jSONObject) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, InvocationTargetException;
}
