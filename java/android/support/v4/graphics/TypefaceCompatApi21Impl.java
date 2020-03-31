package android.support.v4.graphics;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;

class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
    }

    private File getFile(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
            return null;
        } catch (ErrnoException e) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0043, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r5.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0050, code lost:
        if (r2 != null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x005c, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x005d, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0073, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0074, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0093, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0094, code lost:
        r2.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0098, code lost:
        r3.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x005c A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x0019] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r8, android.os.CancellationSignal r9, android.support.v4.provider.FontsContractCompat.FontInfo[] r10, int r11) {
        /*
            r7 = this;
            r0 = 0
            int r1 = r10.length
            r2 = 1
            if (r1 >= r2) goto L_0x0006
        L_0x0005:
            return r0
        L_0x0006:
            android.support.v4.provider.FontsContractCompat$FontInfo r1 = r7.findBestInfo(r10, r11)
            android.content.ContentResolver r2 = r8.getContentResolver()
            android.net.Uri r1 = r1.getUri()     // Catch:{ IOException -> 0x0056 }
            java.lang.String r3 = "r"
            android.os.ParcelFileDescriptor r3 = r2.openFileDescriptor(r1, r3, r9)     // Catch:{ IOException -> 0x0056 }
            r2 = 0
            java.io.File r1 = r7.getFile(r3)     // Catch:{ Throwable -> 0x0048, all -> 0x005c }
            if (r1 == 0) goto L_0x0025
            boolean r4 = r1.canRead()     // Catch:{ Throwable -> 0x0048, all -> 0x005c }
            if (r4 != 0) goto L_0x007c
        L_0x0025:
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0048, all -> 0x005c }
            java.io.FileDescriptor r1 = r3.getFileDescriptor()     // Catch:{ Throwable -> 0x0048, all -> 0x005c }
            r4.<init>(r1)     // Catch:{ Throwable -> 0x0048, all -> 0x005c }
            r5 = 0
            android.graphics.Typeface r1 = super.createFromInputStream(r8, r4)     // Catch:{ Throwable -> 0x0068, all -> 0x009c }
            if (r4 == 0) goto L_0x003a
            if (r0 == 0) goto L_0x0058
            r4.close()     // Catch:{ Throwable -> 0x0043, all -> 0x005c }
        L_0x003a:
            if (r3 == 0) goto L_0x0041
            if (r0 == 0) goto L_0x0064
            r3.close()     // Catch:{ Throwable -> 0x005f }
        L_0x0041:
            r0 = r1
            goto L_0x0005
        L_0x0043:
            r4 = move-exception
            r5.addSuppressed(r4)     // Catch:{ Throwable -> 0x0048, all -> 0x005c }
            goto L_0x003a
        L_0x0048:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x004a }
        L_0x004a:
            r2 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L_0x004e:
            if (r3 == 0) goto L_0x0055
            if (r2 == 0) goto L_0x0098
            r3.close()     // Catch:{ Throwable -> 0x0093 }
        L_0x0055:
            throw r1     // Catch:{ IOException -> 0x0056 }
        L_0x0056:
            r1 = move-exception
            goto L_0x0005
        L_0x0058:
            r4.close()     // Catch:{ Throwable -> 0x0048, all -> 0x005c }
            goto L_0x003a
        L_0x005c:
            r1 = move-exception
            r2 = r0
            goto L_0x004e
        L_0x005f:
            r3 = move-exception
            r2.addSuppressed(r3)     // Catch:{ IOException -> 0x0056 }
            goto L_0x0041
        L_0x0064:
            r3.close()     // Catch:{ IOException -> 0x0056 }
            goto L_0x0041
        L_0x0068:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x006a }
        L_0x006a:
            r1 = move-exception
        L_0x006b:
            if (r4 == 0) goto L_0x0072
            if (r2 == 0) goto L_0x0078
            r4.close()     // Catch:{ Throwable -> 0x0073, all -> 0x005c }
        L_0x0072:
            throw r1     // Catch:{ Throwable -> 0x0048, all -> 0x005c }
        L_0x0073:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ Throwable -> 0x0048, all -> 0x005c }
            goto L_0x0072
        L_0x0078:
            r4.close()     // Catch:{ Throwable -> 0x0048, all -> 0x005c }
            goto L_0x0072
        L_0x007c:
            android.graphics.Typeface r1 = android.graphics.Typeface.createFromFile(r1)     // Catch:{ Throwable -> 0x0048, all -> 0x005c }
            if (r3 == 0) goto L_0x0087
            if (r0 == 0) goto L_0x008f
            r3.close()     // Catch:{ Throwable -> 0x008a }
        L_0x0087:
            r0 = r1
            goto L_0x0005
        L_0x008a:
            r3 = move-exception
            r2.addSuppressed(r3)     // Catch:{ IOException -> 0x0056 }
            goto L_0x0087
        L_0x008f:
            r3.close()     // Catch:{ IOException -> 0x0056 }
            goto L_0x0087
        L_0x0093:
            r3 = move-exception
            r2.addSuppressed(r3)     // Catch:{ IOException -> 0x0056 }
            goto L_0x0055
        L_0x0098:
            r3.close()     // Catch:{ IOException -> 0x0056 }
            goto L_0x0055
        L_0x009c:
            r1 = move-exception
            r2 = r0
            goto L_0x006b
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
