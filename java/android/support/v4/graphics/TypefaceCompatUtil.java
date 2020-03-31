package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    public static File getTempFile(Context context) {
        String str = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 100) {
                return null;
            }
            File file = new File(context.getCacheDir(), str + i2);
            try {
                if (file.createNewFile()) {
                    return file;
                }
                i = i2 + 1;
            } catch (IOException e) {
            }
        }
    }

    private static ByteBuffer mmap(File file) {
        Throwable th;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Throwable th2 = null;
            try {
                FileChannel channel = fileInputStream.getChannel();
                MappedByteBuffer map = channel.map(MapMode.READ_ONLY, 0, channel.size());
                if (fileInputStream == null) {
                    return map;
                }
                if (0 != 0) {
                    try {
                        fileInputStream.close();
                        return map;
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                        return map;
                    }
                } else {
                    fileInputStream.close();
                    return map;
                }
            } catch (Throwable th4) {
                Throwable th5 = th4;
                th = r0;
                th = th5;
            }
            if (fileInputStream != null) {
                if (th != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th6) {
                        th.addSuppressed(th6);
                    }
                } else {
                    fileInputStream.close();
                }
            }
            throw th;
            throw th;
        } catch (IOException e) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0058, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0059, code lost:
        r11 = r1;
        r1 = r0;
        r0 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0068, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0069, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x007d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x007e, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0068 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:16:0x0023] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.ByteBuffer mmap(android.content.Context r12, android.os.CancellationSignal r13, android.net.Uri r14) {
        /*
            r6 = 0
            android.content.ContentResolver r0 = r12.getContentResolver()
            java.lang.String r1 = "r"
            android.os.ParcelFileDescriptor r7 = r0.openFileDescriptor(r14, r1, r13)     // Catch:{ IOException -> 0x001c }
            r8 = 0
            if (r7 != 0) goto L_0x0023
            if (r7 == 0) goto L_0x0015
            if (r6 == 0) goto L_0x001f
            r7.close()     // Catch:{ Throwable -> 0x0017 }
        L_0x0015:
            r0 = r6
        L_0x0016:
            return r0
        L_0x0017:
            r0 = move-exception
            r8.addSuppressed(r0)     // Catch:{ IOException -> 0x001c }
            goto L_0x0015
        L_0x001c:
            r0 = move-exception
            r0 = r6
            goto L_0x0016
        L_0x001f:
            r7.close()     // Catch:{ IOException -> 0x001c }
            goto L_0x0015
        L_0x0023:
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0056, all -> 0x0068 }
            java.io.FileDescriptor r0 = r7.getFileDescriptor()     // Catch:{ Throwable -> 0x0056, all -> 0x0068 }
            r9.<init>(r0)     // Catch:{ Throwable -> 0x0056, all -> 0x0068 }
            r10 = 0
            java.nio.channels.FileChannel r0 = r9.getChannel()     // Catch:{ Throwable -> 0x006f, all -> 0x008f }
            long r4 = r0.size()     // Catch:{ Throwable -> 0x006f, all -> 0x008f }
            java.nio.channels.FileChannel$MapMode r1 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ Throwable -> 0x006f, all -> 0x008f }
            r2 = 0
            java.nio.MappedByteBuffer r0 = r0.map(r1, r2, r4)     // Catch:{ Throwable -> 0x006f, all -> 0x008f }
            if (r9 == 0) goto L_0x0044
            if (r6 == 0) goto L_0x0064
            r9.close()     // Catch:{ Throwable -> 0x0051, all -> 0x0068 }
        L_0x0044:
            if (r7 == 0) goto L_0x0016
            if (r6 == 0) goto L_0x006b
            r7.close()     // Catch:{ Throwable -> 0x004c }
            goto L_0x0016
        L_0x004c:
            r1 = move-exception
            r8.addSuppressed(r1)     // Catch:{ IOException -> 0x001c }
            goto L_0x0016
        L_0x0051:
            r1 = move-exception
            r10.addSuppressed(r1)     // Catch:{ Throwable -> 0x0056, all -> 0x0068 }
            goto L_0x0044
        L_0x0056:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0058 }
        L_0x0058:
            r1 = move-exception
            r11 = r1
            r1 = r0
            r0 = r11
        L_0x005c:
            if (r7 == 0) goto L_0x0063
            if (r1 == 0) goto L_0x008b
            r7.close()     // Catch:{ Throwable -> 0x0086 }
        L_0x0063:
            throw r0     // Catch:{ IOException -> 0x001c }
        L_0x0064:
            r9.close()     // Catch:{ Throwable -> 0x0056, all -> 0x0068 }
            goto L_0x0044
        L_0x0068:
            r0 = move-exception
            r1 = r6
            goto L_0x005c
        L_0x006b:
            r7.close()     // Catch:{ IOException -> 0x001c }
            goto L_0x0016
        L_0x006f:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0071 }
        L_0x0071:
            r1 = move-exception
            r11 = r1
            r1 = r0
            r0 = r11
        L_0x0075:
            if (r9 == 0) goto L_0x007c
            if (r1 == 0) goto L_0x0082
            r9.close()     // Catch:{ Throwable -> 0x007d, all -> 0x0068 }
        L_0x007c:
            throw r0     // Catch:{ Throwable -> 0x0056, all -> 0x0068 }
        L_0x007d:
            r2 = move-exception
            r1.addSuppressed(r2)     // Catch:{ Throwable -> 0x0056, all -> 0x0068 }
            goto L_0x007c
        L_0x0082:
            r9.close()     // Catch:{ Throwable -> 0x0056, all -> 0x0068 }
            goto L_0x007c
        L_0x0086:
            r2 = move-exception
            r1.addSuppressed(r2)     // Catch:{ IOException -> 0x001c }
            goto L_0x0063
        L_0x008b:
            r7.close()     // Catch:{ IOException -> 0x001c }
            goto L_0x0063
        L_0x008f:
            r0 = move-exception
            r1 = r6
            goto L_0x0075
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    public static ByteBuffer copyToDirectBuffer(Context context, Resources resources, int i) {
        ByteBuffer byteBuffer = null;
        File tempFile = getTempFile(context);
        if (tempFile != null) {
            try {
                if (copyToFile(tempFile, resources, i)) {
                    byteBuffer = mmap(tempFile);
                    tempFile.delete();
                }
            } finally {
                tempFile.delete();
            }
        }
        return byteBuffer;
    }

    public static boolean copyToFile(File file, InputStream inputStream) {
        FileOutputStream fileOutputStream;
        ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            fileOutputStream = new FileOutputStream(file, false);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        closeQuietly(fileOutputStream);
                        StrictMode.setThreadPolicy(allowThreadDiskWrites);
                        return true;
                    }
                }
            } catch (IOException e) {
                e = e;
                try {
                    Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
                    closeQuietly(fileOutputStream);
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    closeQuietly(fileOutputStream);
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            fileOutputStream = null;
            Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
            closeQuietly(fileOutputStream);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            closeQuietly(fileOutputStream);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            throw th;
        }
    }

    public static boolean copyToFile(File file, Resources resources, int i) {
        InputStream inputStream = null;
        try {
            inputStream = resources.openRawResource(i);
            return copyToFile(file, inputStream);
        } finally {
            closeQuietly(inputStream);
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
