package okhttp3.internal.tls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;

public abstract class CertificateChainCleaner {

    static final class AndroidCertificateChainCleaner extends CertificateChainCleaner {
        private final Method checkServerTrusted;
        private final Object x509TrustManagerExtensions;

        AndroidCertificateChainCleaner(Object obj, Method method) {
            this.x509TrustManagerExtensions = obj;
            this.checkServerTrusted = method;
        }

        public List<Certificate> clean(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
            try {
                X509Certificate[] x509CertificateArr = (X509Certificate[]) list.toArray(new X509Certificate[list.size()]);
                return (List) this.checkServerTrusted.invoke(this.x509TrustManagerExtensions, new Object[]{x509CertificateArr, "RSA", str});
            } catch (InvocationTargetException e) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e.getMessage());
                sSLPeerUnverifiedException.initCause(e);
                throw sSLPeerUnverifiedException;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    static final class BasicCertificateChainCleaner extends CertificateChainCleaner {
        private static final int MAX_SIGNERS = 9;
        private final TrustRootIndex trustRootIndex;

        public BasicCertificateChainCleaner(TrustRootIndex trustRootIndex2) {
            this.trustRootIndex = trustRootIndex2;
        }

        public List<Certificate> clean(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
            boolean z = false;
            ArrayDeque arrayDeque = new ArrayDeque(list);
            ArrayList arrayList = new ArrayList();
            arrayList.add(arrayDeque.removeFirst());
            int i = 0;
            while (true) {
                boolean z2 = z;
                if (i < 9) {
                    X509Certificate x509Certificate = (X509Certificate) arrayList.get(arrayList.size() - 1);
                    X509Certificate findByIssuerAndSignature = this.trustRootIndex.findByIssuerAndSignature(x509Certificate);
                    if (findByIssuerAndSignature != null) {
                        if (arrayList.size() > 1 || !x509Certificate.equals(findByIssuerAndSignature)) {
                            arrayList.add(findByIssuerAndSignature);
                        }
                        if (verifySignature(findByIssuerAndSignature, findByIssuerAndSignature)) {
                            return arrayList;
                        }
                        z = true;
                    } else {
                        Iterator it = arrayDeque.iterator();
                        while (it.hasNext()) {
                            X509Certificate x509Certificate2 = (X509Certificate) it.next();
                            if (verifySignature(x509Certificate, x509Certificate2)) {
                                it.remove();
                                arrayList.add(x509Certificate2);
                                z = z2;
                            }
                        }
                        if (z2) {
                            return arrayList;
                        }
                        throw new SSLPeerUnverifiedException("Failed to find a trusted cert that signed " + x509Certificate);
                    }
                    i++;
                } else {
                    throw new SSLPeerUnverifiedException("Certificate chain too long: " + arrayList);
                }
            }
        }

        private boolean verifySignature(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
            if (!x509Certificate.getIssuerDN().equals(x509Certificate2.getSubjectDN())) {
                return false;
            }
            try {
                x509Certificate.verify(x509Certificate2.getPublicKey());
                return true;
            } catch (GeneralSecurityException e) {
                return false;
            }
        }
    }

    public abstract List<Certificate> clean(List<Certificate> list, String str) throws SSLPeerUnverifiedException;

    public static CertificateChainCleaner get(X509TrustManager x509TrustManager) {
        try {
            Class cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new AndroidCertificateChainCleaner(cls.getConstructor(new Class[]{X509TrustManager.class}).newInstance(new Object[]{x509TrustManager}), cls.getMethod("checkServerTrusted", new Class[]{X509Certificate[].class, String.class, String.class}));
        } catch (Exception e) {
            return new BasicCertificateChainCleaner(TrustRootIndex.get(x509TrustManager));
        }
    }

    public static CertificateChainCleaner get(X509Certificate... x509CertificateArr) {
        return new BasicCertificateChainCleaner(TrustRootIndex.get(x509CertificateArr));
    }
}
