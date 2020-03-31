package com.baidu.location.a;

import android.os.Bundle;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;

public class b {
    private static Object a = new Object();
    private static b b = null;
    private int c = -1;

    public static b a() {
        b bVar;
        synchronized (a) {
            if (b == null) {
                b = new b();
            }
            bVar = b;
        }
        return bVar;
    }

    public void a(int i, int i2, String str) {
        if (i2 != this.c) {
            this.c = i2;
            Bundle bundle = new Bundle();
            bundle.putInt("loctype", i);
            bundle.putInt("diagtype", i2);
            bundle.putByteArray("diagmessage", str.getBytes());
            a.a().a(bundle, (int) ErrorCode.ERROR_UNMATCH_TBSCORE_VER);
        }
    }

    public void b() {
        this.c = -1;
    }
}
