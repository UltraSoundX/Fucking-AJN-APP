package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import com.tencent.smtt.export.external.DexLoader;

public class TbsVideoCacheTask {
    public static final String KEY_VIDEO_CACHE_PARAM_FILENAME = "filename";
    public static final String KEY_VIDEO_CACHE_PARAM_FOLDERPATH = "folderPath";
    public static final String KEY_VIDEO_CACHE_PARAM_HEADER = "header";
    public static final String KEY_VIDEO_CACHE_PARAM_URL = "url";
    Context a = null;
    TbsVideoCacheListener b = null;
    private boolean c = false;
    private n d = null;
    private String e;
    private String f;
    private Object g = null;

    public TbsVideoCacheTask(Context context, Bundle bundle, TbsVideoCacheListener tbsVideoCacheListener) {
        this.a = context;
        this.b = tbsVideoCacheListener;
        if (bundle != null) {
            this.e = bundle.getString("taskId");
            this.f = bundle.getString("url");
        }
        a(bundle);
    }

    public String getTaskID() {
        return this.e;
    }

    public String getTaskUrl() {
        return this.f;
    }

    private void a(Bundle bundle) {
        DexLoader dexLoader;
        if (this.d == null) {
            d.a(true).a(this.a, false, false);
            r a2 = d.a(true).a();
            if (a2 != null) {
                dexLoader = a2.b();
            } else {
                this.b.onVideoDownloadError(this, -1, "init engine error!", null);
                dexLoader = null;
            }
            if (dexLoader != null) {
                this.d = new n(dexLoader);
            } else {
                this.b.onVideoDownloadError(this, -1, "Java dexloader invalid!", null);
            }
        }
        if (this.d != null) {
            this.g = this.d.a(this.a, this, bundle);
            if (this.g == null) {
                this.b.onVideoDownloadError(this, -1, "init task error!", null);
            }
        } else if (this.b != null) {
            this.b.onVideoDownloadError(this, -1, "init error!", null);
        }
    }

    public void pauseTask() {
        if (this.d != null && this.g != null) {
            this.d.a();
        } else if (this.b != null) {
            this.b.onVideoDownloadError(this, -1, "pauseTask failed, init uncompleted!", null);
        }
    }

    public void stopTask() {
        if (this.d != null && this.g != null) {
            this.d.c();
        } else if (this.b != null) {
            this.b.onVideoDownloadError(this, -1, "stopTask failed, init uncompleted!", null);
        }
    }

    public void resumeTask() {
        if (this.d != null && this.g != null) {
            this.d.b();
        } else if (this.b != null) {
            this.b.onVideoDownloadError(this, -1, "resumeTask failed, init uncompleted!", null);
        }
    }

    public void removeTask(boolean z) {
        if (this.d != null && this.g != null) {
            this.d.a(z);
        } else if (this.b != null) {
            this.b.onVideoDownloadError(this, -1, "removeTask failed, init uncompleted!", null);
        }
    }

    public long getContentLength() {
        if (this.d != null && this.g != null) {
            return this.d.d();
        }
        if (this.b != null) {
            this.b.onVideoDownloadError(this, -1, "getContentLength failed, init uncompleted!", null);
        }
        return 0;
    }

    public int getDownloadedSize() {
        if (this.d != null && this.g != null) {
            return this.d.e();
        }
        if (this.b != null) {
            this.b.onVideoDownloadError(this, -1, "getDownloadedSize failed, init uncompleted!", null);
        }
        return 0;
    }

    public int getProgress() {
        if (this.d != null && this.g != null) {
            return this.d.f();
        }
        if (this.b != null) {
            this.b.onVideoDownloadError(this, -1, "getProgress failed, init uncompleted!", null);
        }
        return 0;
    }
}
