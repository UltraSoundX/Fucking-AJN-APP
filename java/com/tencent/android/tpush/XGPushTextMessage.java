package com.tencent.android.tpush;

import android.content.Intent;
import java.io.Serializable;

/* compiled from: ProGuard */
public class XGPushTextMessage implements Serializable {
    private static final long serialVersionUID = -1854661081378847806L;
    String content = "";
    String customContent = "";
    int pushChannel = 0;
    private Intent simpleIntent = null;
    String title = "";

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getCustomContent() {
        return this.customContent;
    }

    public int getPushChannel() {
        return this.pushChannel;
    }

    /* access modifiers changed from: 0000 */
    public void setSimpleIntent(Intent intent) {
        this.simpleIntent = intent;
        if (intent != null) {
            this.simpleIntent.removeExtra("content");
        }
    }

    /* access modifiers changed from: 0000 */
    public Intent getSimpleIntent() {
        return this.simpleIntent;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("XGPushShowedResult [title=").append(this.title).append(", content=").append(this.content).append(", customContent=").append(this.customContent).append("]");
        return sb.toString();
    }
}
