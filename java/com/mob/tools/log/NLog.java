package com.mob.tools.log;

import android.content.Context;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;

public abstract class NLog {
    private static LogCollector defaultCollector;
    private static boolean disable;
    private static HashMap<String, NLog> loggers = new HashMap<>();
    private LogCollector collector;

    /* access modifiers changed from: protected */
    public abstract String getSDKTag();

    public static void disable() {
        disable = true;
    }

    static {
        MobUncaughtExceptionHandler.register();
    }

    public static void setContext(Context context) {
    }

    public static void setCollector(String str, LogCollector logCollector) {
        getInstance(str).setCollector(logCollector);
    }

    protected static NLog getInstanceForSDK(String str, boolean z) {
        return getInstance(str);
    }

    public static NLog getInstance(final String str) {
        NLog nLog;
        synchronized (loggers) {
            nLog = (NLog) loggers.get(str);
            if (nLog == null) {
                nLog = new NLog() {
                    /* access modifiers changed from: protected */
                    public String getSDKTag() {
                        return str;
                    }
                };
                loggers.put(str, nLog);
            }
        }
        return nLog;
    }

    public static <Collector extends LogCollector> Collector setDefaultCollector(Collector collector2) {
        defaultCollector = collector2;
        return collector2;
    }

    public NLog setCollector(LogCollector logCollector) {
        this.collector = logCollector;
        return this;
    }

    public final int v(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(2, 0, getStackTraceString(th));
    }

    public final int v(Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(2, 0, obj2);
    }

    public final int v(Throwable th, Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(2, 0, sb.append(obj2).append(10).append(getStackTraceString(th)).toString());
    }

    public final int d(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(3, 0, getStackTraceString(th));
    }

    public final int d(Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(3, 0, obj2);
    }

    public final int d(Throwable th, Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(3, 0, sb.append(obj2).append(10).append(getStackTraceString(th)).toString());
    }

    public final int i(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(4, 0, getStackTraceString(th));
    }

    public final int i(Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(4, 0, obj2);
    }

    public final int i(Throwable th, Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(4, 0, sb.append(obj2).append(10).append(getStackTraceString(th)).toString());
    }

    public final int w(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(5, 0, getStackTraceString(th));
    }

    public final int w(Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(5, 0, obj2);
    }

    public final int w(Throwable th, Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(5, 0, sb.append(obj2).append(10).append(getStackTraceString(th)).toString());
    }

    public final int w(String str) {
        if (disable) {
            return 0;
        }
        return println(5, 0, str);
    }

    public final int e(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(6, 0, getStackTraceString(th));
    }

    public final int e(Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(6, 0, obj2);
    }

    public final int e(Throwable th, Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(6, 0, sb.append(obj2).append(10).append(getStackTraceString(th)).toString());
    }

    public int wtf(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(6, 0, getStackTraceString(th));
    }

    public final int crash(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(6, 1, getStackTraceString(th));
    }

    private String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        Throwable th2 = th;
        while (th2 != null) {
            try {
                if (th2 instanceof UnknownHostException) {
                    return "";
                }
                th2 = th2.getCause();
            } catch (Throwable th3) {
                if (th3 instanceof OutOfMemoryError) {
                    return "getStackTraceString oom";
                }
                if (th3 != null) {
                    return th3.getMessage();
                }
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        String stringWriter2 = stringWriter.toString();
        stringWriter.close();
        return stringWriter2;
    }

    private int println(int i, int i2, String str) {
        String str2 = Thread.currentThread().getName() + " " + str;
        String sDKTag = getSDKTag();
        LogCollector logCollector = this.collector == null ? defaultCollector : this.collector;
        if (logCollector != null) {
            logCollector.log(sDKTag, i, i2, null, str2);
        }
        return 0;
    }
}
