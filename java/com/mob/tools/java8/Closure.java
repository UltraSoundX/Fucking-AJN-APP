package com.mob.tools.java8;

public class Closure {

    public interface IClosure<R> {
        R call() throws Throwable;
    }

    public interface IClosure1V<R> {
        void call(R r) throws Throwable;
    }

    public interface IClosure2V<R, U> {
        void call(R r, U u) throws Throwable;
    }

    public interface IClosureV {
        void call() throws Throwable;
    }

    public static class Result<R> {
        /* access modifiers changed from: private */
        public R res;
        /* access modifiers changed from: private */
        public Throwable t;

        public R result() {
            return this.res;
        }

        public Throwable error() {
            return this.t;
        }

        public void throwError() {
            if (this.t != null) {
                throw new RuntimeException(this.t);
            }
        }

        public void printError() {
            if (this.t != null) {
                this.t.printStackTrace();
            }
        }

        public void onError(IClosure1V<Throwable> iClosure1V) {
            if (this.t != null && iClosure1V != null) {
                try {
                    iClosure1V.call(this.t);
                } catch (Throwable th) {
                }
            }
        }
    }

    public static <R> Result<R> call(IClosure<R> iClosure) {
        Result<R> result = new Result<>();
        try {
            result.res = iClosure.call();
        } catch (Throwable th) {
            result.t = th;
        }
        return result;
    }

    public static Result<Void> call(IClosureV iClosureV) {
        Result<Void> result = new Result<>();
        try {
            iClosureV.call();
        } catch (Throwable th) {
            result.t = th;
        }
        return result;
    }

    public static <R> R uncheck(IClosure<R> iClosure) {
        try {
            return iClosure.call();
        } catch (Throwable th) {
            return null;
        }
    }

    public static void uncheck(IClosureV iClosureV) {
        try {
            iClosureV.call();
        } catch (Throwable th) {
        }
    }

    public static void asyncCall(final IClosureV iClosureV) {
        new Thread() {
            public void run() {
                Closure.uncheck(iClosureV);
            }
        }.start();
    }
}
