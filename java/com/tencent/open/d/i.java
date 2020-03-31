package com.tencent.open.d;

import android.annotation.TargetApi;
import android.net.SSLCertificateSocketFactory;
import android.os.Build.VERSION;
import com.tencent.open.a.f;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.params.HttpParams;

@TargetApi(17)
/* compiled from: ProGuard */
public class i implements LayeredSocketFactory {
    static final HostnameVerifier a = new StrictHostnameVerifier();
    SSLCertificateSocketFactory b = ((SSLCertificateSocketFactory) SSLCertificateSocketFactory.getInsecure(0, null));

    public Socket connectSocket(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams) throws IOException {
        socket.connect(new InetSocketAddress(str, i));
        return socket;
    }

    public Socket createSocket() {
        return new Socket();
    }

    public boolean isSecure(Socket socket) throws IllegalArgumentException {
        if (socket instanceof SSLSocket) {
            return ((SSLSocket) socket).isConnected();
        }
        return false;
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        f.a("SNISocketFactory", "createSocket " + socket.toString() + " host:" + str + " port:" + i + " autoClose:" + z);
        SSLSocket sSLSocket = (SSLSocket) this.b.createSocket(socket, str, i, z);
        sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
        if (VERSION.SDK_INT >= 17) {
            f.a("SNISocketFactory", "Setting SNI hostname");
            this.b.setHostname(sSLSocket, str);
        } else {
            f.a("SNISocketFactory", "No documented SNI support on Android <4.2, trying with reflection");
            try {
                sSLSocket.getClass().getMethod("setHostname", new Class[]{String.class}).invoke(sSLSocket, new Object[]{str});
            } catch (Exception e) {
                f.a("SNISocketFactory", "SNI not useable");
            }
        }
        if (a.verify(str, sSLSocket.getSession())) {
            return sSLSocket;
        }
        throw new SSLPeerUnverifiedException("Cannot verify hostname: " + str);
    }
}
