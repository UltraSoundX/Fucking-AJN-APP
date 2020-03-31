package com.c.a.c.a;

import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.http.HttpEntity;

/* compiled from: FileDownloadHandler */
public class b {
    public File a(HttpEntity httpEntity, e eVar, String str, boolean z, String str2) throws IOException {
        BufferedInputStream bufferedInputStream;
        FileOutputStream fileOutputStream;
        if (httpEntity == null || TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile.exists() || parentFile.mkdirs()) {
                file.createNewFile();
            }
        }
        long j = 0;
        BufferedOutputStream bufferedOutputStream = null;
        if (z) {
            try {
                j = file.length();
                fileOutputStream = new FileOutputStream(str, true);
            } catch (Throwable th) {
                th = th;
                bufferedInputStream = null;
            }
        } else {
            fileOutputStream = new FileOutputStream(str);
        }
        long contentLength = httpEntity.getContentLength() + j;
        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(httpEntity.getContent());
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
            if (eVar != null) {
                try {
                    if (!eVar.a(contentLength, j, true)) {
                        com.c.a.e.b.a(bufferedInputStream2);
                        com.c.a.e.b.a(bufferedOutputStream2);
                        return file;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedOutputStream = bufferedOutputStream2;
                    bufferedInputStream = bufferedInputStream2;
                }
            }
            byte[] bArr = new byte[4096];
            while (true) {
                int read = bufferedInputStream2.read(bArr);
                if (read == -1) {
                    bufferedOutputStream2.flush();
                    if (eVar != null) {
                        eVar.a(contentLength, j, true);
                    }
                    com.c.a.e.b.a(bufferedInputStream2);
                    com.c.a.e.b.a(bufferedOutputStream2);
                    if (!file.exists() || TextUtils.isEmpty(str2)) {
                        return file;
                    }
                    File file2 = new File(file.getParent(), str2);
                    while (file2.exists()) {
                        file2 = new File(file.getParent(), System.currentTimeMillis() + str2);
                    }
                    if (!file.renameTo(file2)) {
                        file2 = file;
                    }
                    return file2;
                }
                bufferedOutputStream2.write(bArr, 0, read);
                j += (long) read;
                if (eVar != null && !eVar.a(contentLength, j, false)) {
                    com.c.a.e.b.a(bufferedInputStream2);
                    com.c.a.e.b.a(bufferedOutputStream2);
                    return file;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = bufferedInputStream2;
            com.c.a.e.b.a(bufferedInputStream);
            com.c.a.e.b.a(bufferedOutputStream);
            throw th;
        }
    }
}
