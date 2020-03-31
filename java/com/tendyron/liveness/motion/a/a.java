package com.tendyron.liveness.motion.a;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import com.stub.StubApp;
import com.tendyron.liveness.R;

/* compiled from: MediaController */
public final class a {
    private MediaPlayer a;

    /* renamed from: com.tendyron.liveness.motion.a.a$a reason: collision with other inner class name */
    /* compiled from: MediaController */
    private static class C0082a {
        /* access modifiers changed from: private */
        public static final a a = new a();

        private C0082a() {
        }
    }

    private a() {
        this.a = null;
    }

    public static a a() {
        return C0082a.a;
    }

    public void b() {
        if (this.a != null) {
            this.a.setOnPreparedListener(null);
            this.a.stop();
            this.a.reset();
            this.a.release();
            this.a = null;
        }
    }

    public void a(Context context, int i) {
        switch (i) {
            case 0:
                b(context, R.raw.common_notice_blink);
                return;
            case 1:
                b(context, R.raw.common_notice_mouth);
                return;
            case 2:
                b(context, R.raw.common_notice_yaw);
                return;
            case 3:
                b(context, R.raw.common_notice_nod);
                return;
            default:
                return;
        }
    }

    private void b(Context context, int i) {
        if (this.a != null) {
            this.a.stop();
            this.a.release();
            this.a = null;
        }
        ((AudioManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("audio")).requestAudioFocus(new OnAudioFocusChangeListener() {
            public void onAudioFocusChange(int i) {
            }
        }, 3, 1);
        this.a = MediaPlayer.create(context, i);
        this.a.setLooping(true);
        this.a.start();
    }
}
