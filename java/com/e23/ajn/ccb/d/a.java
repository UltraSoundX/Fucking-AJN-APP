package com.e23.ajn.ccb.d;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.content.FileProvider;
import android.util.Log;
import com.stub.StubApp;
import java.io.File;

/* compiled from: CameraApi */
public class a {
    public static void a(Activity activity, int i, Uri uri) {
        activity.startActivityForResult(a(uri), i);
    }

    private static Intent a(Uri uri) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", uri);
        return intent;
    }

    public static Uri a(Context context, File file) {
        Uri fromFile;
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        if (VERSION.SDK_INT >= 24) {
            String str = context.getPackageName() + ".fileprovider";
            Log.d("TAG:", "packname =" + context.getPackageName());
            fromFile = FileProvider.getUriForFile(StubApp.getOrigApplicationContext(context.getApplicationContext()), str, file);
        } else {
            fromFile = Uri.fromFile(file);
        }
        Log.d("TAG:", "FileProvider get uri= " + fromFile);
        return fromFile;
    }
}
