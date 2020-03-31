package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.stub.StubApp;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;
import java.util.LinkedList;

public class TbsReaderPredownload {
    public static final int READER_SO_SUCCESS = 2;
    public static final int READER_WAIT_IN_QUEUE = 3;
    static final String[] b = {"docx", "pptx", "xlsx", "pdf", "epub", "txt"};
    Handler a = null;
    LinkedList<String> c = new LinkedList<>();
    boolean d = false;
    ReaderWizard e = null;
    ReaderCallback f = null;
    Object g = null;
    Context h = null;
    ReaderPreDownloadCallback i = null;
    String j = "";

    public interface ReaderPreDownloadCallback {
        public static final int NOTIFY_PLUGIN_FAILED = -1;
        public static final int NOTIFY_PLUGIN_SUCCESS = 0;

        void onEvent(String str, int i, boolean z);
    }

    public TbsReaderPredownload(ReaderPreDownloadCallback readerPreDownloadCallback) {
        this.i = readerPreDownloadCallback;
        for (String add : b) {
            this.c.add(add);
        }
        a();
    }

    public boolean init(Context context) {
        if (context == null) {
            return false;
        }
        this.h = StubApp.getOrigApplicationContext(context.getApplicationContext());
        boolean a2 = TbsReaderView.a(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        this.f = new ReaderCallback() {
            public void onCallBackAction(Integer num, Object obj, Object obj2) {
                switch (num.intValue()) {
                    case ReaderCallback.READER_PLUGIN_STATUS /*5012*/:
                        int intValue = ((Integer) obj).intValue();
                        if (5014 != intValue) {
                            if (5013 == intValue) {
                                TbsReaderPredownload.this.a(0);
                            } else if (intValue == 0) {
                                TbsReaderPredownload.this.a(0);
                            } else {
                                TbsReaderPredownload.this.a(-1);
                            }
                            TbsReaderPredownload.this.j = "";
                            TbsReaderPredownload.this.a(3, 100);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        try {
            if (this.e == null) {
                this.e = new ReaderWizard(this.f);
            }
            if (this.g == null) {
                this.g = this.e.getTbsReader();
            }
            if (this.g != null) {
                a2 = this.e.initTbsReader(this.g, StubApp.getOrigApplicationContext(context.getApplicationContext()));
            }
        } catch (NullPointerException e2) {
            Log.e("TbsReaderPredownload", "Unexpect null object!");
            a2 = false;
        }
        return a2;
    }

    public void startAll() {
        this.d = false;
        if (!c(3) && !false) {
            a(3, 100);
        }
    }

    public void start(String str) {
        this.d = false;
        b(3);
        this.c.add(str);
        a(3, 100);
    }

    public void pause() {
        this.d = true;
    }

    public void shutdown() {
        this.i = null;
        this.d = false;
        this.c.clear();
        b();
        if (this.e != null) {
            this.e.destroy(this.g);
            this.g = null;
        }
        this.h = null;
    }

    private void b() {
        b(3);
    }

    /* access modifiers changed from: 0000 */
    public boolean a(String str) {
        if (this.g == null || this.e == null || !ReaderWizard.isSupportExt(str)) {
            return false;
        }
        return this.e.checkPlugin(this.g, this.h, str, true);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        if (this.i != null) {
            this.i.onEvent(this.j, i2, this.c.isEmpty());
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.a = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 3:
                        if (!TbsReaderPredownload.this.c.isEmpty() && !TbsReaderPredownload.this.d) {
                            String str = (String) TbsReaderPredownload.this.c.removeFirst();
                            TbsReaderPredownload.this.j = str;
                            if (!TbsReaderPredownload.this.a(str)) {
                                TbsReaderPredownload.this.a(-1);
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2) {
        this.a.removeMessages(i2);
    }

    /* access modifiers changed from: 0000 */
    public boolean c(int i2) {
        return this.a.hasMessages(i2);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3) {
        this.a.sendMessageDelayed(this.a.obtainMessage(i2), (long) i3);
    }
}
