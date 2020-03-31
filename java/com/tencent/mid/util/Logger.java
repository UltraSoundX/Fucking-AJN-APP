package com.tencent.mid.util;

import android.util.Log;
import com.baidu.mobstat.Config;

public final class Logger {
    private static boolean debugEnable = false;
    private int logLevel = 2;
    private String tag = "default";

    public boolean isDebugEnable() {
        return debugEnable;
    }

    public void setDebugEnable(boolean z) {
        debugEnable = z;
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(int i) {
        this.logLevel = i;
    }

    public Logger() {
    }

    public Logger(String str) {
        this.tag = str;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    private String getLoggerClassInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(getClass().getName())) {
                return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + "): " + stackTraceElement.getFileName() + Config.TRACE_TODAY_VISIT_SPLIT + stackTraceElement.getLineNumber() + "]";
            }
        }
        return null;
    }

    public void info(Object obj) {
        if (this.logLevel <= 4) {
            String loggerClassInfo = getLoggerClassInfo();
            Log.i(this.tag, loggerClassInfo == null ? obj.toString() : loggerClassInfo + " - " + obj);
        }
    }

    public void i(Object obj) {
        if (isDebugEnable()) {
            info(obj);
        }
    }

    public void verbose(Object obj) {
        if (this.logLevel <= 2) {
            String loggerClassInfo = getLoggerClassInfo();
            Log.v(this.tag, loggerClassInfo == null ? obj.toString() : loggerClassInfo + " - " + obj);
        }
    }

    public void v(Object obj) {
        if (isDebugEnable()) {
            verbose(obj);
        }
    }

    public void warn(Object obj) {
        if (this.logLevel <= 5) {
            String loggerClassInfo = getLoggerClassInfo();
            Log.w(this.tag, loggerClassInfo == null ? obj.toString() : loggerClassInfo + " - " + obj);
        }
    }

    public void w(Object obj) {
        if (isDebugEnable()) {
            warn(obj);
        }
    }

    public void error(Object obj) {
        if (this.logLevel <= 6) {
            String loggerClassInfo = getLoggerClassInfo();
            Log.e(this.tag, loggerClassInfo == null ? obj.toString() : loggerClassInfo + " - " + obj);
        }
    }

    public void error(Exception exc) {
        if (this.logLevel <= 6) {
            StringBuffer stringBuffer = new StringBuffer();
            String loggerClassInfo = getLoggerClassInfo();
            StackTraceElement[] stackTrace = exc.getStackTrace();
            if (loggerClassInfo != null) {
                stringBuffer.append(loggerClassInfo + " - " + exc + "\r\n");
            } else {
                stringBuffer.append(exc + "\r\n");
            }
            if (stackTrace != null && stackTrace.length > 0) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    if (stackTraceElement != null) {
                        stringBuffer.append("[ " + stackTraceElement.getFileName() + Config.TRACE_TODAY_VISIT_SPLIT + stackTraceElement.getLineNumber() + " ]\r\n");
                    }
                }
            }
            Log.e(this.tag, stringBuffer.toString());
        }
    }

    public void e(Object obj) {
        if (isDebugEnable()) {
            error(obj);
        }
    }

    public void e(Exception exc) {
        if (isDebugEnable()) {
            error(exc);
        }
    }

    public void debug(Object obj) {
        if (this.logLevel <= 3) {
            String loggerClassInfo = getLoggerClassInfo();
            Log.d(this.tag, loggerClassInfo == null ? obj.toString() : loggerClassInfo + " - " + obj);
        }
    }

    public void d(Object obj) {
        if (isDebugEnable()) {
            debug(obj);
        }
    }
}
