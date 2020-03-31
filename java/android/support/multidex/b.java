package android.support.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import net.sf.json.util.JSONUtils;

/* compiled from: MultiDexExtractor */
final class b {
    private static Method a;

    static List<File> a(Context context, ApplicationInfo applicationInfo, File file, boolean z) throws IOException {
        List<File> a2;
        Log.i("MultiDex", "MultiDexExtractor.load(" + applicationInfo.sourceDir + ", " + z + ")");
        File file2 = new File(applicationInfo.sourceDir);
        long c = c(file2);
        if (z || a(context, file2, c)) {
            Log.i("MultiDex", "Detected that extraction must be performed.");
            a2 = a(file2, file);
            a(context, b(file2), c, a2.size() + 1);
        } else {
            try {
                a2 = a(context, file2, file);
            } catch (IOException e) {
                Log.w("MultiDex", "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", e);
                a2 = a(file2, file);
                a(context, b(file2), c, a2.size() + 1);
            }
        }
        Log.i("MultiDex", "load found " + a2.size() + " secondary dex files");
        return a2;
    }

    private static List<File> a(Context context, File file, File file2) throws IOException {
        Log.i("MultiDex", "loading existing secondary dex files");
        String str = file.getName() + ".classes";
        int i = a(context).getInt("dex.number", 1);
        ArrayList arrayList = new ArrayList(i);
        int i2 = 2;
        while (i2 <= i) {
            File file3 = new File(file2, str + i2 + ".zip");
            if (file3.isFile()) {
                arrayList.add(file3);
                if (!a(file3)) {
                    Log.i("MultiDex", "Invalid zip file: " + file3);
                    throw new IOException("Invalid ZIP file.");
                }
                i2++;
            } else {
                throw new IOException("Missing extracted secondary dex file '" + file3.getPath() + JSONUtils.SINGLE_QUOTE);
            }
        }
        return arrayList;
    }

    private static boolean a(Context context, File file, long j) {
        SharedPreferences a2 = a(context);
        return (a2.getLong("timestamp", -1) == b(file) && a2.getLong("crc", -1) == j) ? false : true;
    }

    private static long b(File file) {
        long lastModified = file.lastModified();
        if (lastModified == -1) {
            return lastModified - 1;
        }
        return lastModified;
    }

    private static long c(File file) throws IOException {
        long a2 = c.a(file);
        if (a2 == -1) {
            return a2 - 1;
        }
        return a2;
    }

    private static List<File> a(File file, File file2) throws IOException {
        String str = file.getName() + ".classes";
        a(file2, str);
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        try {
            ZipEntry entry = zipFile.getEntry("classes" + 2 + ".dex");
            int i = 2;
            while (entry != null) {
                File file3 = new File(file2, str + i + ".zip");
                arrayList.add(file3);
                Log.i("MultiDex", "Extraction is needed for file " + file3);
                boolean z = false;
                int i2 = 0;
                while (i2 < 3 && !z) {
                    int i3 = i2 + 1;
                    a(zipFile, entry, file3, str);
                    boolean a2 = a(file3);
                    Log.i("MultiDex", "Extraction " + (a2 ? "success" : "failed") + " - length " + file3.getAbsolutePath() + ": " + file3.length());
                    if (!a2) {
                        file3.delete();
                        if (file3.exists()) {
                            Log.w("MultiDex", "Failed to delete corrupted secondary dex '" + file3.getPath() + JSONUtils.SINGLE_QUOTE);
                            z = a2;
                            i2 = i3;
                        }
                    }
                    z = a2;
                    i2 = i3;
                }
                if (!z) {
                    throw new IOException("Could not create zip file " + file3.getAbsolutePath() + " for secondary dex (" + i + ")");
                }
                int i4 = i + 1;
                entry = zipFile.getEntry("classes" + i4 + ".dex");
                i = i4;
            }
            try {
            } catch (IOException e) {
                Log.w("MultiDex", "Failed to close resource", e);
            }
            return arrayList;
        } finally {
            try {
                zipFile.close();
            } catch (IOException e2) {
                Log.w("MultiDex", "Failed to close resource", e2);
            }
        }
    }

    private static void a(Context context, long j, long j2, int i) {
        Editor edit = a(context).edit();
        edit.putLong("timestamp", j);
        edit.putLong("crc", j2);
        edit.putInt("dex.number", i);
        a(edit);
    }

    private static SharedPreferences a(Context context) {
        return context.getSharedPreferences("multidex.version", VERSION.SDK_INT < 11 ? 0 : 4);
    }

    private static void a(File file, final String str) throws IOException {
        d(file.getParentFile());
        d(file);
        File[] listFiles = file.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return !file.getName().startsWith(str);
            }
        });
        if (listFiles == null) {
            Log.w("MultiDex", "Failed to list secondary dex dir content (" + file.getPath() + ").");
            return;
        }
        for (File file2 : listFiles) {
            Log.i("MultiDex", "Trying to delete old file " + file2.getPath() + " of size " + file2.length());
            if (!file2.delete()) {
                Log.w("MultiDex", "Failed to delete old file " + file2.getPath());
            } else {
                Log.i("MultiDex", "Deleted old file " + file2.getPath());
            }
        }
    }

    private static void d(File file) throws IOException {
        file.mkdir();
        if (!file.isDirectory()) {
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                Log.e("MultiDex", "Failed to create dir " + file.getPath() + ". Parent file is null.");
            } else {
                Log.e("MultiDex", "Failed to create dir " + file.getPath() + ". parent file is a dir " + parentFile.isDirectory() + ", a file " + parentFile.isFile() + ", exists " + parentFile.exists() + ", readable " + parentFile.canRead() + ", writable " + parentFile.canWrite());
            }
            throw new IOException("Failed to create cache directory " + file.getPath());
        }
    }

    private static void a(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException, FileNotFoundException {
        ZipOutputStream zipOutputStream;
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        File createTempFile = File.createTempFile(str, ".zip", file.getParentFile());
        Log.i("MultiDex", "Extracting " + createTempFile.getPath());
        try {
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(createTempFile)));
            ZipEntry zipEntry2 = new ZipEntry("classes.dex");
            zipEntry2.setTime(zipEntry.getTime());
            zipOutputStream.putNextEntry(zipEntry2);
            byte[] bArr = new byte[16384];
            for (int read = inputStream.read(bArr); read != -1; read = inputStream.read(bArr)) {
                zipOutputStream.write(bArr, 0, read);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            Log.i("MultiDex", "Renaming to " + file.getPath());
            if (!createTempFile.renameTo(file)) {
                throw new IOException("Failed to rename \"" + createTempFile.getAbsolutePath() + "\" to \"" + file.getAbsolutePath() + JSONUtils.DOUBLE_QUOTE);
            }
            a((Closeable) inputStream);
            createTempFile.delete();
        } catch (Throwable th) {
            a((Closeable) inputStream);
            createTempFile.delete();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        android.util.Log.w("MultiDex", "File " + r4.getAbsolutePath() + " is not a valid zip file.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        android.util.Log.w("MultiDex", "Failed to close zip file: " + r4.getAbsolutePath());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0029 A[ExcHandler: ZipException (r0v1 'e' java.util.zip.ZipException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean a(java.io.File r4) {
        /*
            java.util.zip.ZipFile r0 = new java.util.zip.ZipFile     // Catch:{ ZipException -> 0x0029, IOException -> 0x004d }
            r0.<init>(r4)     // Catch:{ ZipException -> 0x0029, IOException -> 0x004d }
            r0.close()     // Catch:{ IOException -> 0x000a, ZipException -> 0x0029 }
            r0 = 1
        L_0x0009:
            return r0
        L_0x000a:
            r0 = move-exception
            java.lang.String r0 = "MultiDex"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ ZipException -> 0x0029, IOException -> 0x004d }
            r1.<init>()     // Catch:{ ZipException -> 0x0029, IOException -> 0x004d }
            java.lang.String r2 = "Failed to close zip file: "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ ZipException -> 0x0029, IOException -> 0x004d }
            java.lang.String r2 = r4.getAbsolutePath()     // Catch:{ ZipException -> 0x0029, IOException -> 0x004d }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ ZipException -> 0x0029, IOException -> 0x004d }
            java.lang.String r1 = r1.toString()     // Catch:{ ZipException -> 0x0029, IOException -> 0x004d }
            android.util.Log.w(r0, r1)     // Catch:{ ZipException -> 0x0029, IOException -> 0x004d }
        L_0x0027:
            r0 = 0
            goto L_0x0009
        L_0x0029:
            r0 = move-exception
            java.lang.String r1 = "MultiDex"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "File "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = r4.getAbsolutePath()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = " is not a valid zip file."
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.w(r1, r2, r0)
            goto L_0x0027
        L_0x004d:
            r0 = move-exception
            java.lang.String r1 = "MultiDex"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Got an IOException trying to open zip file: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = r4.getAbsolutePath()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.w(r1, r2, r0)
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.multidex.b.a(java.io.File):boolean");
    }

    private static void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w("MultiDex", "Failed to close resource", e);
        }
    }

    static {
        try {
            a = Editor.class.getMethod("apply", new Class[0]);
        } catch (NoSuchMethodException e) {
            a = null;
        }
    }

    private static void a(Editor editor) {
        if (a != null) {
            try {
                a.invoke(editor, new Object[0]);
                return;
            } catch (IllegalAccessException | InvocationTargetException e) {
            }
        }
        editor.commit();
    }
}
