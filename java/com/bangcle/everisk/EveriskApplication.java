package com.bangcle.everisk;

import android.app.Application;
import com.bangcle.andJni.JniLib1512723589;

public class EveriskApplication extends Application {
    public void onCreate() {
        JniLib1512723589.cV(new Object[]{this, Integer.valueOf(0)});
    }
}
