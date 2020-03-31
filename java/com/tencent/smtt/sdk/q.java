package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import com.stub.StubApp;
import com.tencent.smtt.export.external.DexLoader;

/* compiled from: TbsVideoView */
class q extends FrameLayout implements OnErrorListener {
    private Object a;
    private s b;
    private VideoView c;
    private Context d = null;
    private String e;

    public q(Context context) {
        super(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        this.d = context;
    }

    /* access modifiers changed from: 0000 */
    public void a(Bundle bundle, Object obj) {
        b(bundle, obj);
    }

    private void b(Bundle bundle, Object obj) {
        a();
        boolean z = false;
        if (b()) {
            bundle.putInt("callMode", bundle.getInt("callMode"));
            z = this.b.a(this.a, bundle, this, obj);
        }
        if (!z) {
            if (this.c != null) {
                this.c.stopPlayback();
            }
            if (this.c == null) {
                this.c = new VideoView(getContext());
            }
            this.e = bundle.getString("videoUrl");
            this.c.setVideoURI(Uri.parse(this.e));
            this.c.setOnErrorListener(this);
            Intent intent = new Intent("com.tencent.smtt.tbs.video.PLAY");
            intent.addFlags(268435456);
            Context origApplicationContext = StubApp.getOrigApplicationContext(getContext().getApplicationContext());
            intent.setPackage(origApplicationContext.getPackageName());
            origApplicationContext.startActivity(intent);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        setBackgroundColor(-16777216);
        if (this.b == null) {
            d.a(true).a(StubApp.getOrigApplicationContext(getContext().getApplicationContext()), false, false);
            r a2 = d.a(true).a();
            DexLoader dexLoader = null;
            if (a2 != null) {
                dexLoader = a2.b();
            }
            if (dexLoader != null && QbSdk.canLoadVideo(getContext())) {
                this.b = new s(dexLoader);
            }
        }
        if (this.b != null && this.a == null) {
            this.a = this.b.a(StubApp.getOrigApplicationContext(getContext().getApplicationContext()));
        }
    }

    public boolean b() {
        return (this.b == null || this.a == null) ? false : true;
    }

    public void a(Activity activity) {
        if (!b() && this.c != null) {
            if (this.c.getParent() == null) {
                Window window = activity.getWindow();
                FrameLayout frameLayout = (FrameLayout) window.getDecorView();
                window.addFlags(1024);
                window.addFlags(128);
                frameLayout.setBackgroundColor(-16777216);
                MediaController mediaController = new MediaController(activity);
                mediaController.setMediaPlayer(this.c);
                this.c.setMediaController(mediaController);
                LayoutParams layoutParams = new LayoutParams(-1, -1);
                layoutParams.gravity = 17;
                frameLayout.addView(this.c, layoutParams);
            }
            if (VERSION.SDK_INT >= 8) {
                this.c.start();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Activity activity, int i) {
        if (i == 3 && !b() && this.c != null) {
            this.c.pause();
        }
        if (i == 4) {
            this.d = null;
            if (!b() && this.c != null) {
                this.c.stopPlayback();
                this.c = null;
            }
        }
        if (i == 2 && !b()) {
            this.d = activity;
            a(activity);
        }
        if (b()) {
            this.b.a(this.a, activity, i);
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        try {
            if (this.d instanceof Activity) {
                Activity activity = (Activity) this.d;
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
            Context context = getContext();
            if (context != null) {
                Toast.makeText(context, "播放失败，请选择其它播放器播放", 1).show();
                Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(268435456);
                intent.setDataAndType(Uri.parse(this.e), "video/*");
                origApplicationContext.startActivity(intent);
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public void c() {
        if (b()) {
            this.b.a(this.a);
        }
    }
}
