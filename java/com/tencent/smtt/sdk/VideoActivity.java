package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.stub.StubApp;

public class VideoActivity extends Activity {
    static {
        StubApp.interface11(5579);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        o.a(this).a(this, 2);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        o.a(this).a(this, 1);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        o.a(this).a(this, 3);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        o.a(this).a(this, 4);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        o.a(this).a(i, i2, intent);
    }
}
