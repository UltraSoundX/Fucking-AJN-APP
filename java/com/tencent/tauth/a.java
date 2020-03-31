package com.tencent.tauth;

import com.tencent.open.d.a.C0080a;
import com.tencent.open.d.a.b;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public interface a {
    void a(C0080a aVar);

    void a(b bVar);

    void a(IOException iOException);

    void a(Exception exc);

    void a(MalformedURLException malformedURLException);

    void a(SocketTimeoutException socketTimeoutException);

    void a(ConnectTimeoutException connectTimeoutException);

    void a(JSONException jSONException);

    void a(JSONObject jSONObject);
}
