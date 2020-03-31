package android.support.v4.media;

import android.os.Bundle;
import androidx.versionedparcelable.c;

interface AudioAttributesImpl extends c {
    Object getAudioAttributes();

    int getContentType();

    int getFlags();

    int getLegacyStreamType();

    int getRawLegacyStreamType();

    int getUsage();

    int getVolumeControlStream();

    Bundle toBundle();
}
