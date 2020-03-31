package okhttp3.internal.http;

import com.zhy.http.okhttp.OkHttpUtils.METHOD;

public final class HttpMethod {
    public static boolean invalidatesCache(String str) {
        return str.equals("POST") || str.equals(METHOD.PATCH) || str.equals(METHOD.PUT) || str.equals(METHOD.DELETE) || str.equals("MOVE");
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals("POST") || str.equals(METHOD.PUT) || str.equals(METHOD.PATCH) || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    public static boolean permitsRequestBody(String str) {
        if (requiresRequestBody(str) || str.equals("OPTIONS") || str.equals(METHOD.DELETE) || str.equals("PROPFIND") || str.equals("MKCOL") || str.equals("LOCK")) {
            return true;
        }
        return false;
    }

    public static boolean redirectsToGet(String str) {
        return !str.equals("PROPFIND");
    }

    private HttpMethod() {
    }
}
