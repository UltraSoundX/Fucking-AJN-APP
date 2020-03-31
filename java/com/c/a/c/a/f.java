package com.c.a.c.a;

import com.c.a.e.b;
import com.c.a.e.d;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;

/* compiled from: StringDownloadHandler */
public class f {
    public String a(HttpEntity httpEntity, e eVar, String str) throws IOException {
        if (httpEntity == null) {
            return null;
        }
        long j = 0;
        long contentLength = httpEntity.getContentLength();
        if (eVar != null && !eVar.a(contentLength, 0, true)) {
            return null;
        }
        InputStream inputStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            InputStream content = httpEntity.getContent();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content, str));
                String str2 = "";
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine).append(10);
                        j += d.a(readLine, str);
                        if (eVar != null && !eVar.a(contentLength, j, false)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (eVar != null) {
                    eVar.a(contentLength, j, true);
                }
                b.a(content);
                return sb.toString().trim();
            } catch (Throwable th) {
                th = th;
                inputStream = content;
                b.a(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
