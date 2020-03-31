package com.zhy.http.okhttp.https;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class HttpsUtils {

    private static class MyTrustManager implements X509TrustManager {
        private X509TrustManager defaultTrustManager;
        private X509TrustManager localTrustManager;

        public MyTrustManager(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyStoreException {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init(null);
            this.defaultTrustManager = HttpsUtils.chooseTrustManager(instance.getTrustManagers());
            this.localTrustManager = x509TrustManager;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            try {
                this.defaultTrustManager.checkServerTrusted(x509CertificateArr, str);
            } catch (CertificateException e) {
                this.localTrustManager.checkServerTrusted(x509CertificateArr, str);
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static class SSLParams {
        public SSLSocketFactory sSLSocketFactory;
        public X509TrustManager trustManager;
    }

    private class UnSafeHostnameVerifier implements HostnameVerifier {
        private UnSafeHostnameVerifier() {
        }

        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    private static class UnSafeTrustManager implements X509TrustManager {
        private UnSafeTrustManager() {
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static SSLParams getSslSocketFactory(InputStream[] inputStreamArr, InputStream inputStream, String str) {
        X509TrustManager unSafeTrustManager;
        SSLParams sSLParams = new SSLParams();
        try {
            TrustManager[] prepareTrustManager = prepareTrustManager(inputStreamArr);
            KeyManager[] prepareKeyManager = prepareKeyManager(inputStream, str);
            SSLContext instance = SSLContext.getInstance("TLS");
            if (prepareTrustManager != null) {
                unSafeTrustManager = new MyTrustManager(chooseTrustManager(prepareTrustManager));
            } else {
                unSafeTrustManager = new UnSafeTrustManager();
            }
            instance.init(prepareKeyManager, new TrustManager[]{unSafeTrustManager}, null);
            sSLParams.sSLSocketFactory = instance.getSocketFactory();
            sSLParams.trustManager = unSafeTrustManager;
            return sSLParams;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyManagementException e2) {
            throw new AssertionError(e2);
        } catch (KeyStoreException e3) {
            throw new AssertionError(e3);
        }
    }

    private static TrustManager[] prepareTrustManager(InputStream... inputStreamArr) {
        int i = 0;
        TrustManager[] trustManagerArr = null;
        if (inputStreamArr == null || inputStreamArr.length <= 0) {
            return trustManagerArr;
        }
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            KeyStore instance2 = KeyStore.getInstance(KeyStore.getDefaultType());
            instance2.load(null);
            int length = inputStreamArr.length;
            int i2 = 0;
            while (i < length) {
                InputStream inputStream = inputStreamArr[i];
                int i3 = i2 + 1;
                instance2.setCertificateEntry(Integer.toString(i2), instance.generateCertificate(inputStream));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                    }
                }
                i++;
                i2 = i3;
            }
            TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance3.init(instance2);
            return instance3.getTrustManagers();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return trustManagerArr;
        } catch (CertificateException e3) {
            e3.printStackTrace();
            return trustManagerArr;
        } catch (KeyStoreException e4) {
            e4.printStackTrace();
            return trustManagerArr;
        } catch (Exception e5) {
            e5.printStackTrace();
            return trustManagerArr;
        }
    }

    private static KeyManager[] prepareKeyManager(InputStream inputStream, String str) {
        KeyManager[] keyManagerArr = null;
        if (inputStream == null || str == null) {
            return keyManagerArr;
        }
        try {
            KeyStore instance = KeyStore.getInstance("BKS");
            instance.load(inputStream, str.toCharArray());
            KeyManagerFactory instance2 = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            instance2.init(instance, str.toCharArray());
            return instance2.getKeyManagers();
        } catch (KeyStoreException e) {
            e.printStackTrace();
            return keyManagerArr;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return keyManagerArr;
        } catch (UnrecoverableKeyException e3) {
            e3.printStackTrace();
            return keyManagerArr;
        } catch (CertificateException e4) {
            e4.printStackTrace();
            return keyManagerArr;
        } catch (IOException e5) {
            e5.printStackTrace();
            return keyManagerArr;
        } catch (Exception e6) {
            e6.printStackTrace();
            return keyManagerArr;
        }
    }

    /* access modifiers changed from: private */
    public static X509TrustManager chooseTrustManager(TrustManager[] trustManagerArr) {
        for (X509TrustManager x509TrustManager : trustManagerArr) {
            if (x509TrustManager instanceof X509TrustManager) {
                return x509TrustManager;
            }
        }
        return null;
    }
}
