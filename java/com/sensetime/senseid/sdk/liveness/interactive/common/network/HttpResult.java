package com.sensetime.senseid.sdk.liveness.interactive.common.network;

import com.sensetime.senseid.sdk.liveness.interactive.common.type.AbstractContentType;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.ResultCode;
import java.util.List;
import java.util.Map;

public final class HttpResult {
    private ResultCode a;
    private int b;
    private String c;
    private Map<String, List<String>> d;
    private AbstractContentType e;

    HttpResult(ResultCode resultCode, AbstractContentType abstractContentType) {
        this(resultCode, abstractContentType, 0);
    }

    private HttpResult(ResultCode resultCode, AbstractContentType abstractContentType, byte b2) {
        this(resultCode, null, null, abstractContentType, -1);
    }

    HttpResult(ResultCode resultCode, String str, Map<String, List<String>> map, AbstractContentType abstractContentType, int i) {
        this.a = null;
        this.b = -1;
        this.c = null;
        this.d = null;
        this.e = null;
        this.a = resultCode;
        this.c = str;
        this.d = map;
        this.e = abstractContentType;
        this.b = i;
    }

    public final AbstractContentType getContentType() {
        return this.e;
    }

    public final String getHeaderField(String str) {
        if (this.d == null || this.d.size() <= 0) {
            return null;
        }
        List list = (List) this.d.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    public final Map<String, List<String>> getHeaderFields() {
        return this.d;
    }

    public final int getHttpStatusCode() {
        return this.b;
    }

    public final ResultCode getResultCode() {
        return this.a;
    }

    public final String getResultData() {
        return this.c;
    }
}
