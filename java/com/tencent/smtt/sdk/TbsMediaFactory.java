package com.tencent.smtt.sdk;

import android.content.Context;
import android.util.Log;
import com.stub.StubApp;
import com.tencent.smtt.export.external.DexLoader;

public class TbsMediaFactory {
    private Context a = null;
    private r b = null;
    private DexLoader c = null;

    public TbsMediaFactory(Context context) {
        this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        a();
    }

    private void a() {
        if (this.a == null) {
            Log.e("TbsVideo", "TbsVideo needs context !!");
            return;
        }
        if (this.b == null) {
            d.a(true).a(this.a, false, false);
            this.b = d.a(true).a();
            if (this.b != null) {
                this.c = this.b.b();
            }
        }
        if (this.b == null || this.c == null) {
            throw new RuntimeException("tbs core dex(s) load failure !!!");
        }
    }

    public TbsMediaPlayer createPlayer() {
        if (this.b != null && this.c != null) {
            return new TbsMediaPlayer(new m(this.c, this.a));
        }
        throw new RuntimeException("tbs core dex(s) did not loaded !!!");
    }
}
