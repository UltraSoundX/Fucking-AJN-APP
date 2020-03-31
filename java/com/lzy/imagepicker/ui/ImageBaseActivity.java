package com.lzy.imagepicker.ui;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;
import com.lzy.imagepicker.b;
import com.lzy.imagepicker.view.c;
import com.stub.StubApp;

public class ImageBaseActivity extends AppCompatActivity {
    protected c a;

    static {
        StubApp.interface11(4190);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    @TargetApi(19)
    private void a(boolean z) {
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= 67108864;
        } else {
            attributes.flags &= -67108865;
        }
        window.setAttributes(attributes);
    }

    public boolean checkPermission(String str) {
        return ActivityCompat.checkSelfPermission(this, str) == 0;
    }

    public void showToast(String str) {
        Toast.makeText(StubApp.getOrigApplicationContext(getApplicationContext()), str, 0).show();
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        b.a().a(bundle);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        b.a().b(bundle);
    }
}
