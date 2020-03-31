package com.bangcle.everisk.activity;

import android.app.Activity;
import android.os.Bundle;
import com.bangcle.andJni.JniLib1512723589;

public class AlertActivity extends Activity {
    public static void setMessage(String str) {
        JniLib1512723589.cV(new Object[]{str, Integer.valueOf(2)});
    }

    public static void setTitle(String str) {
        JniLib1512723589.cV(new Object[]{str, Integer.valueOf(3)});
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        JniLib1512723589.cV(new Object[]{this, bundle, Integer.valueOf(1)});
    }
}
