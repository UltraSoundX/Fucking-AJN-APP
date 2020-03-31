package com.mob.tools.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.baidu.mobstat.Config;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.zhy.http.okhttp.OkHttpUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import net.sf.json.xml.JSONTypes;

public class ResHelper {
    private static float density;
    private static int deviceWidth;
    /* access modifiers changed from: private */
    public static Uri mediaUri;
    private static Object rp;

    public static int dipToPx(Context context, int i) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((((float) i) * density) + 0.5f);
    }

    public static int pxToDip(Context context, int i) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((((float) i) / density) + 0.5f);
    }

    public static int designToDevice(Context context, int i, int i2) {
        if (deviceWidth == 0) {
            int[] screenSize = getScreenSize(context);
            deviceWidth = screenSize[0] < screenSize[1] ? screenSize[0] : screenSize[1];
        }
        return (int) (((((float) i2) * ((float) deviceWidth)) / ((float) i)) + 0.5f);
    }

    public static int designToDevice(Context context, float f, int i) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return (int) (((((float) i) * density) / f) + 0.5f);
    }

    public static int getDensityDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static float[] getDensityXYDpi(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new float[]{displayMetrics.xdpi, displayMetrics.ydpi};
    }

    public static float getScaledDensity(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static float getDensity(Context context) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return density;
    }

    public static int[] getScreenSize(Context context) {
        WindowManager windowManager;
        Display display;
        try {
            windowManager = (WindowManager) DeviceHelper.getInstance(context).getSystemServiceSafe("window");
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            windowManager = null;
        }
        if (windowManager == null) {
            return new int[]{0, 0};
        }
        try {
            display = windowManager.getDefaultDisplay();
        } catch (Throwable th2) {
            MobLog.getInstance().w(th2);
            display = null;
        }
        if (display == null) {
            try {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
            } catch (Throwable th3) {
                MobLog.getInstance().w(th3);
                return new int[]{0, 0};
            }
        } else if (VERSION.SDK_INT < 13) {
            try {
                DisplayMetrics displayMetrics2 = new DisplayMetrics();
                display.getMetrics(displayMetrics2);
                return new int[]{displayMetrics2.widthPixels, displayMetrics2.heightPixels};
            } catch (Throwable th4) {
                MobLog.getInstance().w(th4);
                return new int[]{0, 0};
            }
        } else {
            try {
                Point point = new Point();
                Method method = display.getClass().getMethod("getRealSize", new Class[]{Point.class});
                method.setAccessible(true);
                method.invoke(display, new Object[]{point});
                return new int[]{point.x, point.y};
            } catch (Throwable th5) {
                MobLog.getInstance().w(th5);
                return new int[]{0, 0};
            }
        }
    }

    public static int getScreenWidth(Context context) {
        return getScreenSize(context)[0];
    }

    public static int getScreenHeight(Context context) {
        return getScreenSize(context)[1];
    }

    public static void setResourceProvider(Object obj) {
        try {
            if (obj.getClass().getMethod("getResId", new Class[]{Context.class, String.class, String.class}) != null) {
                rp = obj;
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getResId(android.content.Context r6, java.lang.String r7, java.lang.String r8) {
        /*
            r1 = 0
            if (r6 == 0) goto L_0x000f
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 != 0) goto L_0x000f
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 == 0) goto L_0x0011
        L_0x000f:
            r0 = r1
        L_0x0010:
            return r0
        L_0x0011:
            java.lang.Object r0 = rp
            if (r0 == 0) goto L_0x00aa
            java.lang.Object r0 = rp     // Catch:{ Throwable -> 0x00a2 }
            java.lang.Class r0 = r0.getClass()     // Catch:{ Throwable -> 0x00a2 }
            java.lang.String r2 = "getResId"
            r3 = 3
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x00a2 }
            r4 = 0
            java.lang.Class<android.content.Context> r5 = android.content.Context.class
            r3[r4] = r5     // Catch:{ Throwable -> 0x00a2 }
            r4 = 1
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r3[r4] = r5     // Catch:{ Throwable -> 0x00a2 }
            r4 = 2
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r3[r4] = r5     // Catch:{ Throwable -> 0x00a2 }
            java.lang.reflect.Method r0 = r0.getMethod(r2, r3)     // Catch:{ Throwable -> 0x00a2 }
            r2 = 1
            r0.setAccessible(r2)     // Catch:{ Throwable -> 0x00a2 }
            java.lang.Object r2 = rp     // Catch:{ Throwable -> 0x00a2 }
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x00a2 }
            r4 = 0
            r3[r4] = r6     // Catch:{ Throwable -> 0x00a2 }
            r4 = 1
            r3[r4] = r7     // Catch:{ Throwable -> 0x00a2 }
            r4 = 2
            r3[r4] = r8     // Catch:{ Throwable -> 0x00a2 }
            java.lang.Object r0 = r0.invoke(r2, r3)     // Catch:{ Throwable -> 0x00a2 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x00a2 }
            int r1 = r0.intValue()     // Catch:{ Throwable -> 0x00a2 }
            r0 = r1
        L_0x0050:
            if (r0 > 0) goto L_0x0010
            java.lang.String r1 = r6.getPackageName()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x0010
            if (r0 > 0) goto L_0x0074
            android.content.res.Resources r0 = r6.getResources()
            int r0 = r0.getIdentifier(r8, r7, r1)
            if (r0 > 0) goto L_0x0074
            android.content.res.Resources r0 = r6.getResources()
            java.lang.String r2 = r8.toLowerCase()
            int r0 = r0.getIdentifier(r2, r7, r1)
        L_0x0074:
            if (r0 > 0) goto L_0x0010
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "failed to parse "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r7)
            java.lang.String r3 = " resource \""
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r8)
            java.lang.String r3 = "\""
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.w(r2)
            goto L_0x0010
        L_0x00a2:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.d(r0)
        L_0x00aa:
            r0 = r1
            goto L_0x0050
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ResHelper.getResId(android.content.Context, java.lang.String, java.lang.String):int");
    }

    public static int getBitmapRes(Context context, String str) {
        int resId = getResId(context, "drawable", str);
        if (resId <= 0) {
            return getResId(context, "mipmap", str);
        }
        return resId;
    }

    public static int getStringRes(Context context, String str) {
        return getResId(context, "string", str);
    }

    public static int getStringArrayRes(Context context, String str) {
        return getResId(context, JSONTypes.ARRAY, str);
    }

    public static int getLayoutRes(Context context, String str) {
        return getResId(context, "layout", str);
    }

    public static int getStyleRes(Context context, String str) {
        return getResId(context, "style", str);
    }

    public static int getIdRes(Context context, String str) {
        return getResId(context, Config.FEED_LIST_ITEM_CUSTOM_ID, str);
    }

    public static int getColorRes(Context context, String str) {
        return getResId(context, "color", str);
    }

    public static int getRawRes(Context context, String str) {
        return getResId(context, "raw", str);
    }

    public static int getPluralsRes(Context context, String str) {
        return getResId(context, "plurals", str);
    }

    public static int getAnimRes(Context context, String str) {
        return getResId(context, "anim", str);
    }

    public static int[] getStyleableRes(Context context, String str) {
        try {
            Object staticField = ReflectHelper.getStaticField(ReflectHelper.importClass(context.getPackageName() + ".R$styleable"), str);
            if (staticField == null) {
                return new int[0];
            }
            if (staticField.getClass().isArray()) {
                return (int[]) staticField;
            }
            return new int[]{((Integer) staticField).intValue()};
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return new int[0];
        }
    }

    public static File getCacheRootFile(Context context, String str) {
        try {
            String cacheRoot = getCacheRoot(context);
            if (cacheRoot != null) {
                File file = new File(cacheRoot, str);
                if (file.getParentFile().exists()) {
                    return file;
                }
                file.getParentFile().mkdirs();
                return file;
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return null;
    }

    public static String getCacheRoot(Context context) {
        try {
            String str = context.getFilesDir().getAbsolutePath() + "/Mob/";
            DeviceHelper instance = DeviceHelper.getInstance(context);
            if (instance.getSdcardState()) {
                String sdcardPath = instance.getSdcardPath();
                if (sdcardPath != null) {
                    str = sdcardPath + "/Mob/";
                }
            }
            File file = new File(str);
            if (file.exists()) {
                return str;
            }
            file.mkdirs();
            return str;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    public static String getDataCache(Context context) {
        String str = context.getFilesDir().getAbsolutePath() + "/Mob/";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static String getCachePath(Context context, String str) {
        String str2 = context.getFilesDir().getAbsolutePath() + "/Mob/cache/";
        DeviceHelper instance = DeviceHelper.getInstance(context);
        try {
            if (instance.getSdcardState()) {
                String sdcardPath = instance.getSdcardPath();
                if (sdcardPath != null) {
                    str2 = sdcardPath + "/Mob/" + instance.getPackageName() + "/cache/";
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        if (!TextUtils.isEmpty(str)) {
            str2 = str2 + str + "/";
        }
        File file = new File(str2);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return str2;
    }

    public static String getImageCachePath(Context context) {
        return getCachePath(context, "images");
    }

    public static void clearCache(Context context) throws Throwable {
        deleteFileAndFolder(new File(getCachePath(context, null)));
    }

    public static void deleteFilesInFolder(File file) throws Throwable {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list != null && list.length > 0) {
                for (String file2 : list) {
                    File file3 = new File(file, file2);
                    if (file3.isDirectory()) {
                        deleteFilesInFolder(file3);
                    } else {
                        file3.delete();
                    }
                }
            }
        }
    }

    public static void deleteFileAndFolder(File file) throws Throwable {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list == null || list.length <= 0) {
                file.delete();
                return;
            }
            for (String file2 : list) {
                File file3 = new File(file, file2);
                if (file3.isDirectory()) {
                    deleteFileAndFolder(file3);
                } else {
                    file3.delete();
                }
            }
            file.delete();
        }
    }

    public static String toWordText(String str, int i) {
        char[] charArray = str.toCharArray();
        int i2 = i * 2;
        StringBuilder sb = new StringBuilder();
        int length = charArray.length;
        int i3 = i2;
        for (int i4 = 0; i4 < length; i4++) {
            char c = charArray[i4];
            i3 -= c < 256 ? 1 : 2;
            if (i3 < 0) {
                return sb.toString();
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static int getTextLengthInWord(String str) {
        char[] charArray = str == null ? new char[0] : str.toCharArray();
        int i = 0;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            i += charArray[i2] < 256 ? 1 : 2;
        }
        return i;
    }

    public static long strToDate(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str, new ParsePosition(0)).getTime();
    }

    public static long dateStrToLong(String str) {
        return new SimpleDateFormat("yyyy-MM-dd").parse(str, new ParsePosition(0)).getTime();
    }

    public static Date longToDate(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.getTime();
    }

    public static String longToTime(long j, int i) {
        String str = "yyyy-MM-dd kk:mm:ss";
        switch (i) {
            case 1:
                str = "yyyy";
                break;
            case 2:
                str = "yyyy-MM";
                break;
            case 5:
                str = "yyyy-MM-dd";
                break;
            case 10:
                str = "yyyy-MM-dd kk";
                break;
            case 12:
                str = "yyyy-MM-dd kk:mm";
                break;
        }
        return new SimpleDateFormat(str).format(Long.valueOf(j));
    }

    public static long dateToLong(String str) {
        try {
            Date date = new Date(str);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            return instance.getTimeInMillis();
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return 0;
        }
    }

    public static int[] covertTimeInYears(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis <= 0) {
            return new int[]{0, 0};
        }
        long j2 = currentTimeMillis / 1000;
        if (j2 < 60) {
            return new int[]{(int) j2, 0};
        }
        long j3 = j2 / 60;
        if (j3 < 60) {
            return new int[]{(int) j3, 1};
        }
        long j4 = j3 / 60;
        if (j4 < 24) {
            return new int[]{(int) j4, 2};
        }
        long j5 = j4 / 24;
        if (j5 < 30) {
            return new int[]{(int) j5, 3};
        }
        long j6 = j5 / 30;
        if (j6 < 12) {
            return new int[]{(int) j6, 4};
        }
        return new int[]{(int) (j6 / 12), 5};
    }

    public static Uri pathToContentUri(Context context, String str) {
        try {
            if (DeviceHelper.getInstance(context).checkPermission("android.permission.READ_EXTERNAL_STORAGE")) {
                Cursor query = context.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, null);
                if (query != null && query.moveToFirst()) {
                    return Uri.withAppendedPath(Uri.parse("content://media/external/images/media"), "" + query.getInt(query.getColumnIndex("_id")));
                } else if (new File(str).exists()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_data", str);
                    return context.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, contentValues);
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0098 A[Catch:{ Throwable -> 0x00be }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00a8 A[Catch:{ Throwable -> 0x00be }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String contentUriToPath(android.content.Context r7, android.net.Uri r8) {
        /*
            r6 = 0
            if (r8 != 0) goto L_0x0004
        L_0x0003:
            return r6
        L_0x0004:
            java.io.File r0 = new java.io.File
            java.lang.String r1 = r8.getPath()
            r0.<init>(r1)
            boolean r0 = r0.exists()
            if (r0 == 0) goto L_0x0018
            java.lang.String r6 = r8.getPath()
            goto L_0x0003
        L_0x0018:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00be }
            r1 = 19
            if (r0 < r1) goto L_0x00ce
            java.lang.String r0 = "android.provider.DocumentsContract"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Throwable -> 0x00be }
            java.lang.String r1 = "isDocumentUri"
            r2 = 2
            java.lang.Class[] r2 = new java.lang.Class[r2]     // Catch:{ Throwable -> 0x00be }
            r3 = 0
            java.lang.Class<android.content.Context> r4 = android.content.Context.class
            r2[r3] = r4     // Catch:{ Throwable -> 0x00be }
            r3 = 1
            java.lang.Class<android.net.Uri> r4 = android.net.Uri.class
            r2[r3] = r4     // Catch:{ Throwable -> 0x00be }
            java.lang.reflect.Method r1 = r0.getMethod(r1, r2)     // Catch:{ Throwable -> 0x00be }
            r2 = 1
            r1.setAccessible(r2)     // Catch:{ Throwable -> 0x00be }
            java.lang.Boolean r2 = java.lang.Boolean.TRUE     // Catch:{ Throwable -> 0x00be }
            r3 = 0
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x00be }
            r5 = 0
            r4[r5] = r7     // Catch:{ Throwable -> 0x00be }
            r5 = 1
            r4[r5] = r8     // Catch:{ Throwable -> 0x00be }
            java.lang.Object r1 = r1.invoke(r3, r4)     // Catch:{ Throwable -> 0x00be }
            boolean r1 = r2.equals(r1)     // Catch:{ Throwable -> 0x00be }
            if (r1 == 0) goto L_0x00ce
            java.lang.String r1 = "getDocumentId"
            r2 = 1
            java.lang.Class[] r2 = new java.lang.Class[r2]     // Catch:{ Throwable -> 0x00be }
            r3 = 0
            java.lang.Class<android.net.Uri> r4 = android.net.Uri.class
            r2[r3] = r4     // Catch:{ Throwable -> 0x00be }
            java.lang.reflect.Method r0 = r0.getMethod(r1, r2)     // Catch:{ Throwable -> 0x00be }
            r1 = 1
            r0.setAccessible(r1)     // Catch:{ Throwable -> 0x00be }
            r1 = 0
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00be }
            r3 = 0
            r2[r3] = r8     // Catch:{ Throwable -> 0x00be }
            java.lang.Object r0 = r0.invoke(r1, r2)     // Catch:{ Throwable -> 0x00be }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x00be }
            java.lang.String r1 = ":"
            java.lang.String[] r0 = r0.split(r1)     // Catch:{ Throwable -> 0x00be }
            r1 = 1
            r0 = r0[r1]     // Catch:{ Throwable -> 0x00be }
            r1 = 1
            java.lang.String[] r2 = new java.lang.String[r1]     // Catch:{ Throwable -> 0x00be }
            r1 = 0
            java.lang.String r3 = "_data"
            r2[r1] = r3     // Catch:{ Throwable -> 0x00be }
            java.lang.String r3 = "_id=?"
            r1 = 1
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch:{ Throwable -> 0x00be }
            r1 = 0
            r4[r1] = r0     // Catch:{ Throwable -> 0x00be }
            android.content.ContentResolver r0 = r7.getContentResolver()     // Catch:{ Throwable -> 0x00be }
            android.net.Uri r1 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch:{ Throwable -> 0x00be }
            r5 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x00be }
        L_0x0096:
            if (r0 != 0) goto L_0x00cc
            android.content.ContentResolver r0 = r7.getContentResolver()     // Catch:{ Throwable -> 0x00be }
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r1 = r8
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x00be }
            r1 = r0
        L_0x00a6:
            if (r1 == 0) goto L_0x00ca
            boolean r0 = r1.moveToFirst()     // Catch:{ Throwable -> 0x00be }
            if (r0 == 0) goto L_0x00c8
            java.lang.String r0 = "_data"
            int r0 = r1.getColumnIndex(r0)     // Catch:{ Throwable -> 0x00be }
            java.lang.String r0 = r1.getString(r0)     // Catch:{ Throwable -> 0x00be }
        L_0x00b8:
            r1.close()     // Catch:{ Throwable -> 0x00be }
        L_0x00bb:
            r6 = r0
            goto L_0x0003
        L_0x00be:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.w(r0)
            r0 = r6
            goto L_0x00bb
        L_0x00c8:
            r0 = r6
            goto L_0x00b8
        L_0x00ca:
            r0 = r6
            goto L_0x00bb
        L_0x00cc:
            r1 = r0
            goto L_0x00a6
        L_0x00ce:
            r0 = r6
            goto L_0x0096
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ResHelper.contentUriToPath(android.content.Context, android.net.Uri):java.lang.String");
    }

    public static Uri videoPathToContentUri(Context context, String str) {
        try {
            if (DeviceHelper.getInstance(context).checkPermission("android.permission.READ_EXTERNAL_STORAGE")) {
                Cursor query = context.getContentResolver().query(Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, null);
                if (query != null && query.moveToFirst()) {
                    return Uri.withAppendedPath(Uri.parse("content://media/external/video/media"), "" + query.getInt(query.getColumnIndex("_id")));
                } else if (new File(str).exists()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_data", str);
                    return context.getContentResolver().insert(Video.Media.EXTERNAL_CONTENT_URI, contentValues);
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return null;
    }

    public static synchronized Uri getMediaUri(Context context, String str, String str2) {
        Uri uri;
        synchronized (ResHelper.class) {
            final Object obj = new Object();
            mediaUri = null;
            MediaScannerConnection.scanFile(context, new String[]{str}, new String[]{str2}, new OnScanCompletedListener() {
                public void onScanCompleted(String str, Uri uri) {
                    ResHelper.mediaUri = uri;
                    synchronized (obj) {
                        obj.notifyAll();
                    }
                }
            });
            try {
                if (mediaUri == null) {
                    synchronized (obj) {
                        obj.wait(OkHttpUtils.DEFAULT_MILLISECONDS);
                    }
                }
            } catch (Throwable th) {
            }
            uri = mediaUri;
            mediaUri = null;
        }
        return uri;
    }

    public static String encodeUrl(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj == null) {
                obj = "";
            }
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(Data.urlEncode(str) + "=" + Data.urlEncode(String.valueOf(obj)));
        }
        return sb.toString();
    }

    public static String encodeUrl(ArrayList<KVPair<String>> arrayList) {
        if (arrayList == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            KVPair kVPair = (KVPair) it.next();
            if (i > 0) {
                sb.append('&');
            }
            String str = kVPair.name;
            String str2 = (String) kVPair.value;
            if (str != null) {
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(Data.urlEncode(str) + "=" + Data.urlEncode(str2));
                i++;
            }
        }
        return sb.toString();
    }

    public static Bundle urlToBundle(String str) {
        String str2;
        int indexOf = str.indexOf("://");
        if (indexOf >= 0) {
            str2 = "http://" + str.substring(indexOf + 1);
        } else {
            str2 = "http://" + str;
        }
        try {
            URL url = new URL(str2);
            Bundle decodeUrl = decodeUrl(url.getQuery());
            decodeUrl.putAll(decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return new Bundle();
        }
    }

    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                if (split2.length < 2 || split2[1] == null) {
                    bundle.putString(URLDecoder.decode(split2[0]), "");
                } else {
                    bundle.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
                }
            }
        }
        return bundle;
    }

    public static int parseInt(String str) throws Throwable {
        return parseInt(str, 10);
    }

    public static int parseInt(String str, int i) throws Throwable {
        return Integer.parseInt(str, i);
    }

    public static long parseLong(String str) throws Throwable {
        return parseLong(str, 10);
    }

    public static long parseLong(String str, int i) throws Throwable {
        return Long.parseLong(str, i);
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static <T> T forceCast(Object obj) {
        return forceCast(obj, null);
    }

    public static <T> T forceCast(Object obj, T t) {
        boolean z = true;
        if (obj == null) {
            return t;
        }
        if (obj instanceof Byte) {
            byte byteValue = ((Byte) obj).byteValue();
            if (t instanceof Boolean) {
                return Boolean.valueOf(byteValue != 0);
            } else if (t instanceof Short) {
                return Short.valueOf((short) byteValue);
            } else {
                if (t instanceof Character) {
                    return Character.valueOf((char) byteValue);
                }
                if (t instanceof Integer) {
                    return Integer.valueOf(byteValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) byteValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) byteValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) byteValue);
                }
            }
        } else if (obj instanceof Character) {
            char charValue = ((Character) obj).charValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) charValue);
            }
            if (t instanceof Boolean) {
                if (charValue == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Short) {
                return Short.valueOf((short) charValue);
            } else {
                if (t instanceof Integer) {
                    return Integer.valueOf(charValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) charValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) charValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) charValue);
                }
            }
        } else if (obj instanceof Short) {
            short shortValue = ((Short) obj).shortValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) shortValue);
            }
            if (t instanceof Boolean) {
                if (shortValue == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) shortValue);
            } else {
                if (t instanceof Integer) {
                    return Integer.valueOf(shortValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) shortValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) shortValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) shortValue);
                }
            }
        } else if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) intValue);
            }
            if (t instanceof Boolean) {
                if (intValue == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) intValue);
            } else {
                if (t instanceof Short) {
                    return Short.valueOf((short) intValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) intValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) intValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) intValue);
                }
            }
        } else if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) ((int) floatValue));
            }
            if (t instanceof Boolean) {
                if (floatValue == 0.0f) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) ((int) floatValue));
            } else {
                if (t instanceof Short) {
                    return Short.valueOf((short) ((int) floatValue));
                }
                if (t instanceof Integer) {
                    return Integer.valueOf((int) floatValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) floatValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) floatValue);
                }
            }
        } else if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) ((int) longValue));
            }
            if (t instanceof Boolean) {
                if (longValue == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) ((int) longValue));
            } else {
                if (t instanceof Short) {
                    return Short.valueOf((short) ((int) longValue));
                }
                if (t instanceof Integer) {
                    return Integer.valueOf((int) longValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) longValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) longValue);
                }
            }
        } else if (obj instanceof Double) {
            double doubleValue = ((Double) obj).doubleValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) ((int) doubleValue));
            }
            if (t instanceof Boolean) {
                if (doubleValue == 0.0d) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) ((int) doubleValue));
            } else {
                if (t instanceof Short) {
                    return Short.valueOf((short) ((int) doubleValue));
                }
                if (t instanceof Integer) {
                    return Integer.valueOf((int) doubleValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) doubleValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) doubleValue);
                }
            }
        }
        return obj;
    }

    public static boolean isLeapYear(int i) {
        return i % ErrorCode.INFO_CODE_BASE == 0 || (i % 4 == 0 && i % 100 != 0);
    }

    public static <T> boolean isEqual(T t, T t2) {
        boolean z;
        if ((t != null || t2 == null) && (t == null || t.equals(t2))) {
            z = false;
        } else {
            z = true;
        }
        return !z;
    }

    public static boolean copyFile(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !new File(str).exists()) {
            return false;
        }
        try {
            copyFile(new FileInputStream(str), new FileOutputStream(str2));
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static void copyFile(FileInputStream fileInputStream, FileOutputStream fileOutputStream) throws Throwable {
        byte[] bArr = new byte[65536];
        int read = fileInputStream.read(bArr);
        while (read > 0) {
            fileOutputStream.write(bArr, 0, read);
            read = fileInputStream.read(bArr);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static long getFileSize(String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return getFileSize(new File(str));
    }

    public static long getFileSize(File file) throws Throwable {
        if (!file.exists()) {
            return 0;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        int i = 0;
        for (String file2 : file.list()) {
            i = (int) (((long) i) + getFileSize(new File(file, file2)));
        }
        return (long) i;
    }

    public static boolean saveObjectToFile(String str, Object obj) {
        File file;
        if (!TextUtils.isEmpty(str)) {
            try {
                file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
                file = null;
            }
            if (file != null) {
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
                    objectOutputStream.writeObject(obj);
                    objectOutputStream.flush();
                    objectOutputStream.close();
                    return true;
                } catch (Throwable th2) {
                    MobLog.getInstance().d(th2);
                }
            }
        }
        return false;
    }

    public static Object readObjectFromFile(String str) {
        File file;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            file = new File(str);
            if (!file.exists()) {
                file = null;
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            file = null;
        }
        if (file == null) {
            return null;
        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            return readObject;
        } catch (Throwable th2) {
            MobLog.getInstance().d(th2);
            return null;
        }
    }

    public static <T> T getStringValue(Context context, String str, T t) {
        try {
            String trim = context.getString(getStringRes(context, str)).trim();
            if (t == null) {
                return String.valueOf(trim);
            }
            Class cls = t.getClass();
            if (cls.equals(String.class)) {
                return String.valueOf(trim);
            }
            if (cls.equals(Integer.TYPE) || cls.equals(Integer.class)) {
                return Integer.valueOf(String.valueOf(trim));
            }
            if (cls.equals(Long.TYPE) || cls.equals(Long.class)) {
                return Long.valueOf(String.valueOf(trim));
            }
            if (cls.equals(Boolean.TYPE) || cls.equals(Boolean.class)) {
                return Boolean.valueOf(trim);
            }
            if (cls.equals(Float.TYPE) || cls.equals(Float.class)) {
                return Float.valueOf(String.valueOf(trim));
            }
            if (cls.equals(Double.TYPE) || cls.equals(Double.class)) {
                return Double.valueOf(String.valueOf(trim));
            }
            if (cls.equals(Character.TYPE) || cls.equals(Character.class)) {
                return Character.valueOf(String.valueOf(trim).charAt(0));
            }
            if (cls.equals(Byte.TYPE) || cls.equals(Byte.class)) {
                return Byte.valueOf(String.valueOf(trim));
            }
            if (cls.equals(Short.TYPE) || cls.equals(Short.class)) {
                return Short.valueOf(String.valueOf(trim));
            }
            return new Hashon().fromJson(trim, cls);
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return t;
        }
    }
}
