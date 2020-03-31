package cn.sharesdk.framework.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class ShareSDKFileProvider extends ContentProvider {
    private static final String[] a = {"_display_name", "_size"};
    private static final File b = new File("/");
    private static HashMap<String, PathStrategy> c = new HashMap<>();
    private PathStrategy d;

    interface PathStrategy {
        File getFileForUri(Uri uri);

        Uri getUriForFile(File file);
    }

    static class a implements PathStrategy {
        private final String a;
        private final HashMap<String, File> b = new HashMap<>();

        public a(String str) {
            this.a = str;
        }

        public void a(String str, File file) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("Name must not be empty");
            }
            try {
                File canonicalFile = file.getCanonicalFile();
                SSDKLog.b().d("ShareSDKFileProvider addRoot name ===> " + str + " root===> " + canonicalFile, new Object[0]);
                this.b.put(str, canonicalFile);
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file, e);
            }
        }

        public Uri getUriForFile(File file) {
            String substring;
            try {
                SSDKLog.b().d("ShareSDKFileProvider !!! getUriForFile !!! file " + file, new Object[0]);
                String canonicalPath = file.getCanonicalPath();
                SSDKLog.b().d("ShareSDKFileProvider getUriForFile path " + canonicalPath, new Object[0]);
                Entry entry = null;
                for (Entry entry2 : this.b.entrySet()) {
                    String path = ((File) entry2.getValue()).getPath();
                    if (!canonicalPath.startsWith(path) || (entry != null && path.length() <= ((File) entry.getValue()).getPath().length())) {
                        entry2 = entry;
                    } else {
                        SSDKLog.b().d("ShareSDKFileProvider getUriForFile mostSpecific " + entry2, new Object[0]);
                    }
                    entry = entry2;
                }
                if (entry == null) {
                    SSDKLog.b().d("ShareSDKFileProvider Failed to find configured root that contains " + canonicalPath, new Object[0]);
                    throw new IllegalArgumentException("Failed to find configured root that contains " + canonicalPath);
                }
                String path2 = ((File) entry.getValue()).getPath();
                if (path2.endsWith("/")) {
                    substring = canonicalPath.substring(path2.length());
                } else {
                    substring = canonicalPath.substring(path2.length() + 1);
                }
                return new Builder().scheme("content").authority(this.a).encodedPath(Uri.encode((String) entry.getKey()) + '/' + Uri.encode(substring, "/")).build();
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }
        }

        public File getFileForUri(Uri uri) {
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = (File) this.b.get(decode);
            if (file == null) {
                throw new IllegalArgumentException("Unable to find configured root for " + uri);
            }
            File file2 = new File(file, decode2);
            try {
                File canonicalFile = file2.getCanonicalFile();
                if (canonicalFile.getPath().startsWith(file.getPath())) {
                    return canonicalFile;
                }
                throw new SecurityException("Resolved path jumped beyond configured root");
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
            }
        }
    }

    public boolean onCreate() {
        SSDKLog.b().d("ShareSDKFileProvider onCreate ", new Object[0]);
        return true;
    }

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            SSDKLog.b().d("ShareSDKFileProvider attachInfo ===> Provider must not be exported", new Object[0]);
            throw new SecurityException("Provider must not be exported");
        } else if (!providerInfo.grantUriPermissions) {
            SSDKLog.b().d("ShareSDKFileProvider attachInfo ===> Provider must grant uri permissions", new Object[0]);
            throw new SecurityException("Provider must grant uri permissions");
        } else {
            SSDKLog.b().d("ShareSDKFileProvider attachInfo ", new Object[0]);
            this.d = a(context, providerInfo.authority);
            SSDKLog.b().d("ShareSDKFileProvider attachInfo mStrategy===> " + this.d, new Object[0]);
        }
    }

    public static Uri a(Context context, String str, File file) {
        return a(context, str).getUriForFile(file);
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i;
        File fileForUri = this.d.getFileForUri(uri);
        if (strArr == null) {
            strArr = a;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int length = strArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            String str3 = strArr[i2];
            if ("_display_name".equals(str3)) {
                strArr3[i3] = "_display_name";
                i = i3 + 1;
                objArr[i3] = fileForUri.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i3] = "_size";
                i = i3 + 1;
                objArr[i3] = Long.valueOf(fileForUri.length());
            } else {
                i = i3;
            }
            i2++;
            i3 = i;
        }
        String[] a2 = a(strArr3, i3);
        Object[] a3 = a(objArr, i3);
        MatrixCursor matrixCursor = new MatrixCursor(a2, 1);
        matrixCursor.addRow(a3);
        return matrixCursor;
    }

    public String getType(Uri uri) {
        File fileForUri = this.d.getFileForUri(uri);
        int lastIndexOf = fileForUri.getName().lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileForUri.getName().substring(lastIndexOf + 1));
            if (mimeTypeFromExtension != null) {
                return mimeTypeFromExtension;
            }
        }
        return "application/octet-stream";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return this.d.getFileForUri(uri).delete() ? 1 : 0;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        return ParcelFileDescriptor.open(this.d.getFileForUri(uri), a(str));
    }

    private static PathStrategy a(Context context, String str) {
        PathStrategy pathStrategy;
        synchronized (c) {
            pathStrategy = (PathStrategy) c.get(str);
            if (pathStrategy == null) {
                pathStrategy = b(context, str);
                c.put(str, pathStrategy);
            }
        }
        SSDKLog.b().d("ShareSDKFileProvider getPathStrategy strat " + pathStrategy, new Object[0]);
        return pathStrategy;
    }

    private static PathStrategy b(Context context, String str) {
        File file;
        File file2;
        a aVar = new a(str);
        String str2 = "imageNameFilesDir";
        String str3 = "Mob/cache/images";
        String str4 = "videoNameFilesDir";
        String str5 = "Mob/cache/videos";
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            aVar.a(str2, a(filesDir, str3));
            aVar.a(str4, a(filesDir, str5));
            SSDKLog.b().d("ShareSDKFileProvider target " + filesDir, new Object[0]);
        }
        String str6 = "imageNameExternal";
        String str7 = "Mob/" + MobSDK.getContext().getPackageName() + "/cache/images";
        String str8 = "videoNameExternal";
        String str9 = "Mob/" + MobSDK.getContext().getPackageName() + "/cache/videos";
        File[] a2 = a(MobSDK.getContext());
        if (a2.length > 0) {
            file = a2[0];
        } else {
            file = null;
        }
        if (file != null) {
            aVar.a(str6, a(file, str7));
            aVar.a(str8, a(file, str9));
            SSDKLog.b().d("ShareSDKFileProvider target " + file, new Object[0]);
        }
        String str10 = "imageNameEtc";
        String str11 = "Mob/" + MobSDK.getContext().getPackageName() + "/cache/images";
        String str12 = "videoNameEtc";
        String str13 = "Mob/" + MobSDK.getContext().getPackageName() + "/cache/videos";
        File[] b2 = b(MobSDK.getContext());
        if (b2.length > 0) {
            file2 = b2[0];
        } else {
            file2 = null;
        }
        if (file2 != null) {
            aVar.a(str10, a(file2, str11));
            aVar.a(str12, a(file2, str13));
            SSDKLog.b().d("ShareSDKFileProvider target " + file2, new Object[0]);
        }
        String str14 = "imageNameRoot";
        String str15 = "Mob/cache/images";
        String str16 = "videoNameRoot";
        String str17 = "Mob/cache/videos";
        File file3 = b;
        if (file3 != null) {
            aVar.a(str14, a((File) null, str15));
            aVar.a(str16, a((File) null, str17));
        }
        SSDKLog.b().d("ShareSDKFileProvider !!! target===> " + file3, new Object[0]);
        return aVar;
    }

    public static File[] a(Context context) {
        if (VERSION.SDK_INT >= 19) {
            SSDKLog.b().d("ShareSDKFileProvider api >= 19", new Object[0]);
            return context.getExternalFilesDirs(null);
        }
        SSDKLog.b().d("ShareSDKFileProvider api <= 19", new Object[0]);
        return new File[]{context.getExternalFilesDir(null)};
    }

    public static File[] b(Context context) {
        if (VERSION.SDK_INT >= 19) {
            SSDKLog.b().d("ShareSDKFileProvider api >= 19", new Object[0]);
            return context.getExternalCacheDirs();
        }
        SSDKLog.b().d("ShareSDKFileProvider api <= 19", new Object[0]);
        return new File[]{context.getExternalCacheDir()};
    }

    private static int a(String str) {
        if ("r".equals(str)) {
            return 268435456;
        }
        if (Config.DEVICE_WIDTH.equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + str);
    }

    private static File a(File file, String... strArr) {
        File file2;
        int length = strArr.length;
        int i = 0;
        File file3 = file;
        while (i < length) {
            String str = strArr[i];
            if (str != null) {
                file2 = new File(file3, str);
            } else {
                file2 = file3;
            }
            i++;
            file3 = file2;
        }
        return file3;
    }

    private static String[] a(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }

    private static Object[] a(Object[] objArr, int i) {
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }
}
