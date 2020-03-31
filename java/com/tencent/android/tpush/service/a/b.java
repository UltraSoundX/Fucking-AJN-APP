package com.tencent.android.tpush.service.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.util.Log;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.channel.c.d;
import com.tencent.android.tpush.service.e.i;
import com.tencent.mid.sotrage.StorageInterface;

/* compiled from: ProGuard */
public class b {
    public static void a(Context context, String str) {
        SharedPreferences defaultSharedPreferences;
        if (context != null && !i.b(str)) {
            for (String split : str.split(";;")) {
                try {
                    String[] split2 = split.split(StorageInterface.KEY_SPLITER);
                    String str2 = split2[0];
                    String str3 = split2[1];
                    if (split2.length == 4 && str3.length() == 1) {
                        String str4 = split2[2];
                        String str5 = split2[3];
                        if (i.b(str2)) {
                            defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        } else if (VERSION.SDK_INT >= 11) {
                            defaultSharedPreferences = context.getSharedPreferences(str2, 4);
                        } else {
                            defaultSharedPreferences = context.getSharedPreferences(str2, 0);
                        }
                        Editor edit = defaultSharedPreferences.edit();
                        if (!i.b(str4) && !i.b(str5)) {
                            if ("S".equals(str3)) {
                                edit.putString(str4, str5);
                            } else if ("L".equals(str3)) {
                                edit.putLong(str4, Long.valueOf(str5).longValue());
                            } else if ("I".equals(str3)) {
                                edit.putInt(str4, Integer.valueOf(str5).intValue());
                            } else if ("F".equals(str3)) {
                                edit.putFloat(str4, Float.valueOf(str5).floatValue());
                            } else if ("B".equals(str3)) {
                                edit.putBoolean(str4, Boolean.valueOf(str5).booleanValue());
                            }
                        }
                        edit.commit();
                        Log.e(Constants.LogTag, defaultSharedPreferences + StorageInterface.KEY_SPLITER + str4 + StorageInterface.KEY_SPLITER + str5);
                    }
                } catch (Throwable th) {
                    Log.e(Constants.LogTag, "eeee", th);
                }
            }
        }
    }

    public static void b(Context context, String str) {
        if (context != null && !i.b(str)) {
            for (String split : str.split(";;")) {
                try {
                    String[] split2 = split.split(StorageInterface.KEY_SPLITER);
                    String str2 = split2[0];
                    if (split2.length == 3 && str2.length() == 1) {
                        String str3 = split2[1];
                        String str4 = split2[2];
                        if (!i.b(str3) && !i.b(str4)) {
                            if ("S".equals(str2)) {
                                d.a(context).a(str3, str4);
                            } else if ("L".equals(str2)) {
                                d.a(context).a(str3, Long.valueOf(str4).longValue());
                            } else if ("I".equals(str2)) {
                                d.a(context).a(str3, Integer.valueOf(str4).intValue());
                            } else if ("F".equals(str2)) {
                                d.a(context).a(str3, Float.valueOf(str4).floatValue());
                            }
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }
    }
}
