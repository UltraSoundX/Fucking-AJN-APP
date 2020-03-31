package com.common.busi;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.stub.StubApp;

@QVMProtect
public final class f extends Thread {
    private a a = null;
    private Context b;
    private String c = "";

    static {
        StubApp.interface11(6722);
    }

    private native void a(int i, String str, String str2, Intent intent);

    private static native void a(Context context, Intent intent, int i);

    private static native void a(Context context, String str, Uri uri, ContentValues contentValues);

    private native void a(String str);

    public final native void run();

    public f(Context context, String str) {
        this.b = context;
        this.c = str;
    }
}
