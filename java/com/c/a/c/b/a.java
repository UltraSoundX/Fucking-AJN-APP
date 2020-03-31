package com.c.a.c.b;

import com.c.a.e.c;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* compiled from: DefaultSSLSocketFactory */
public class a extends SSLSocketFactory {
    private static KeyStore b;
    private static a c;
    private SSLContext a = SSLContext.getInstance("TLS");

    static {
        try {
            b = KeyStore.getInstance(KeyStore.getDefaultType());
            b.load(null, null);
        } catch (Throwable th) {
            c.a(th.getMessage(), th);
        }
    }

    public static a a() {
        if (c == null) {
            try {
                c = new a();
            } catch (Throwable th) {
                c.a(th.getMessage(), th);
            }
        }
        return c;
    }

    private a() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        super(b);
        AnonymousClass1 r0 = new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }
        };
        this.a.init(null, new TrustManager[]{r0}, null);
        setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return this.a.getSocketFactory().createSocket(socket, str, i, z);
    }

    public Socket createSocket() throws IOException {
        return this.a.getSocketFactory().createSocket();
    }
}
