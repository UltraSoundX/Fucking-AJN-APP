package com.mob.tools.java8;

import java.util.Iterator;
import java.util.LinkedList;

public class Commands {

    public static class When<T> {
        private LinkedList<MapItem<WhenCondition<T>, Then>> cases;
        private T target;

        public interface Then {
            void then();
        }

        public interface WhenCondition<T> {
            boolean when(T t);
        }

        private When(T t) {
            this.target = t;
            this.cases = new LinkedList<>();
        }

        public When<T> meetCondition(WhenCondition<T> whenCondition, Then then) {
            this.cases.add(MapItem.of(whenCondition, then));
            return this;
        }

        public When<T> meetCondition(final boolean z, Then then) {
            return meetCondition((WhenCondition<T>) new WhenCondition<T>() {
                public boolean when(T t) {
                    return z;
                }
            }, then);
        }

        public When<T> is(final Object obj, Then then) {
            return meetCondition((WhenCondition<T>) new WhenCondition<T>() {
                public boolean when(T t) {
                    return (t == null && obj == null) || (t != null && t.equals(obj));
                }
            }, then);
        }

        public When<T> isNot(final Object obj, Then then) {
            return meetCondition((WhenCondition<T>) new WhenCondition<T>() {
                public boolean when(T t) {
                    return (t == null && obj != null) || (t != null && !t.equals(obj));
                }
            }, then);
        }

        public When<T> isNull(Then then) {
            return is(null, then);
        }

        public When<T> isNotNull(Then then) {
            return isNot(null, then);
        }

        public When<T> isInstanceOf(final Class<?> cls, Then then) {
            return meetCondition((WhenCondition<T>) new WhenCondition<T>() {
                public boolean when(T t) {
                    return cls != null && cls.isInstance(t);
                }
            }, then);
        }

        public When<T> isNotInstanceOf(final Class<?> cls, Then then) {
            return meetCondition((WhenCondition<T>) new WhenCondition<T>() {
                public boolean when(T t) {
                    return cls == null || !cls.isInstance(t);
                }
            }, then);
        }

        public When<T> isInRange(double d, double d2, Then then) {
            final double d3 = d;
            final double d4 = d2;
            return meetCondition((WhenCondition<T>) new WhenCondition<T>() {
                public boolean when(T t) {
                    try {
                        double doubleValue = Double.valueOf(String.valueOf(t)).doubleValue();
                        if (doubleValue < d3 || doubleValue > d4) {
                            return false;
                        }
                        return true;
                    } catch (Throwable th) {
                        return false;
                    }
                }
            }, then);
        }

        public When<T> isNotInRage(double d, double d2, Then then) {
            final double d3 = d;
            final double d4 = d2;
            return meetCondition((WhenCondition<T>) new WhenCondition<T>() {
                public boolean when(T t) {
                    try {
                        double doubleValue = Double.valueOf(String.valueOf(t)).doubleValue();
                        if (doubleValue < d3 || doubleValue > d4) {
                            return true;
                        }
                        return false;
                    } catch (Throwable th) {
                        return false;
                    }
                }
            }, then);
        }

        public When<T> isGreaterThan(final double d, Then then) {
            return meetCondition((WhenCondition<T>) new WhenCondition<T>() {
                public boolean when(T t) {
                    try {
                        if (Double.valueOf(String.valueOf(t)).doubleValue() > d) {
                            return true;
                        }
                        return false;
                    } catch (Throwable th) {
                        return false;
                    }
                }
            }, then);
        }

        public When<T> isLessThan(final double d, Then then) {
            return meetCondition((WhenCondition<T>) new WhenCondition<T>() {
                public boolean when(T t) {
                    try {
                        if (Double.valueOf(String.valueOf(t)).doubleValue() < d) {
                            return true;
                        }
                        return false;
                    } catch (Throwable th) {
                        return false;
                    }
                }
            }, then);
        }

        public void orElse(Then then) {
            evaluate(then);
        }

        private void evaluate(Then then) {
            Iterator it = this.cases.iterator();
            while (it.hasNext()) {
                MapItem mapItem = (MapItem) it.next();
                if (((WhenCondition) mapItem.key).when(this.target)) {
                    ((Then) mapItem.value).then();
                    return;
                }
            }
            if (then != null) {
                then.then();
            }
        }

        public void evaluate() {
            evaluate(null);
        }
    }

    public static <T> When<T> when(T t) {
        return new When<>(t);
    }
}
