package androidx.media;

import android.support.v4.media.AudioAttributesCompat;
import androidx.versionedparcelable.a;
import androidx.versionedparcelable.c;

public final class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(a aVar) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        audioAttributesCompat.mImpl = (AudioAttributesImpl) aVar.b(audioAttributesCompat.mImpl, 1);
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, a aVar) {
        aVar.a(false, false);
        aVar.a((c) audioAttributesCompat.mImpl, 1);
    }
}
