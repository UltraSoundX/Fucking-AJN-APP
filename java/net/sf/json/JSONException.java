package net.sf.json;

import org.apache.commons.lang.exception.NestableRuntimeException;

public class JSONException extends NestableRuntimeException {
    private static final long serialVersionUID = 6995087065217051815L;

    public JSONException() {
    }

    public JSONException(String str) {
        super(str, null);
    }

    public JSONException(String str, Throwable th) {
        super(str, th);
    }

    public JSONException(Throwable th) {
        super(th == null ? null : th.toString(), th);
    }
}
