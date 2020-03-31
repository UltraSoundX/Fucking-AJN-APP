package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import com.baidu.mobstat.Config;
import com.stub.StubApp;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.smtt.sdk.a.c;
import com.tencent.smtt.utils.Apn;

public class TbsReaderView extends FrameLayout {
    public static final String IS_BAR_ANIMATING = "is_bar_animating";
    public static final String IS_BAR_SHOWING = "is_bar_show";
    public static final String IS_INTO_DOWNLOADING = "into_downloading";
    public static final String KEY_FILE_PATH = "filePath";
    public static final String KEY_TEMP_PATH = "tempPath";
    public static final int READER_CHANNEL_DOC_ID = 10965;
    public static final int READER_CHANNEL_PDF_ID = 10834;
    public static final int READER_CHANNEL_PPT_ID = 10833;
    public static final int READER_CHANNEL_TXT_ID = 10835;
    public static final String READER_STATISTICS_COUNT_CANCEL_LOADED_BTN = "AHNG802";
    public static final String READER_STATISTICS_COUNT_CLICK_LOADED_BTN = "AHNG801";
    public static final String READER_STATISTICS_COUNT_DOC_INTO_BROWSER = "AHNG829";
    public static final String READER_STATISTICS_COUNT_DOC_INTO_DIALOG = "AHNG827";
    public static final String READER_STATISTICS_COUNT_DOC_INTO_DOWNLOAD = "AHNG828";
    public static final String READER_STATISTICS_COUNT_DOC_SEARCH_BTN = "AHNG826";
    public static final String READER_STATISTICS_COUNT_PDF_FOLDER_BTN = "AHNG810";
    public static final String READER_STATISTICS_COUNT_PDF_INTO_BROWSER = "AHNG813";
    public static final String READER_STATISTICS_COUNT_PDF_INTO_DIALOG = "AHNG811";
    public static final String READER_STATISTICS_COUNT_PDF_INTO_DOWNLOAD = "AHNG812";
    public static final String READER_STATISTICS_COUNT_PPT_INTO_BROWSER = "AHNG809";
    public static final String READER_STATISTICS_COUNT_PPT_INTO_DIALOG = "AHNG807";
    public static final String READER_STATISTICS_COUNT_PPT_INTO_DOWNLOAD = "AHNG808";
    public static final String READER_STATISTICS_COUNT_PPT_PLAY_BTN = "AHNG806";
    public static final String READER_STATISTICS_COUNT_RETRY_BTN = "AHNG803";
    public static final String READER_STATISTICS_COUNT_TXT_INTO_BROWSER = "AHNG817";
    public static final String READER_STATISTICS_COUNT_TXT_INTO_DIALOG = "AHNG815";
    public static final String READER_STATISTICS_COUNT_TXT_INTO_DOWNLOAD = "AHNG816";
    public static final String READER_STATISTICS_COUNT_TXT_NOVEL_BTN = "AHNG814";
    public static final String TAG = "TbsReaderView";
    static boolean f = false;
    public static String gReaderPackName = "";
    public static String gReaderPackVersion = "";
    Context a = null;
    ReaderWizard b = null;
    Object c = null;
    ReaderCallback d = null;
    ReaderCallback e = null;

    public interface ReaderCallback {
        public static final int COPY_SELECT_TEXT = 5003;
        public static final int GET_BAR_ANIMATING = 5000;
        public static final int GET_BAR_ISSHOWING = 5024;
        public static final int HIDDEN_BAR = 5001;
        public static final int INSTALL_QB = 5011;
        public static final int NOTIFY_CANDISPLAY = 12;
        public static final int NOTIFY_ERRORCODE = 19;
        public static final int READER_OPEN_QQ_FILE_LIST = 5031;
        public static final int READER_PDF_LIST = 5008;
        public static final int READER_PLUGIN_ACTIVITY_PAUSE = 5032;
        public static final int READER_PLUGIN_CANLOAD = 5013;
        public static final int READER_PLUGIN_COMMAND_FIXSCREEN = 5015;
        public static final int READER_PLUGIN_COMMAND_PDF_LIST = 5036;
        public static final int READER_PLUGIN_COMMAND_PPT_PLAYER = 5035;
        public static final int READER_PLUGIN_COMMAND_ROTATESCREEN = 5018;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND = 5038;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND_CLEAR = 5041;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND_NEXT = 5039;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND_PREV = 5040;
        public static final int READER_PLUGIN_DOWNLOADING = 5014;
        public static final int READER_PLUGIN_RES_DOC_GUIDE = 5029;
        public static final int READER_PLUGIN_RES_FIXSCREEN_NORMAL = 5016;
        public static final int READER_PLUGIN_RES_FIXSCREEN_PRESS = 5017;
        public static final int READER_PLUGIN_RES_PDF_GUIDE = 5023;
        public static final int READER_PLUGIN_RES_PPT_GUIDE = 5021;
        public static final int READER_PLUGIN_RES_ROTATESCREEN_NORMAL = 5019;
        public static final int READER_PLUGIN_RES_ROTATESCREEN_PRESS = 5020;
        public static final int READER_PLUGIN_RES_TXT_GUIDE = 5022;
        public static final int READER_PLUGIN_SO_ERR = 5025;
        public static final int READER_PLUGIN_SO_INTO_START = 5027;
        public static final int READER_PLUGIN_SO_PROGRESS = 5028;
        public static final int READER_PLUGIN_SO_VERSION = 5030;
        public static final int READER_PLUGIN_STATUS = 5012;
        public static final int READER_PLUGIN_TEXT_FIND_RESULT = 5042;
        public static final int READER_PPT_PLAY_MODEL = 5009;
        public static final int READER_SEARCH_IN_DOCUMENT = 5026;
        public static final int READER_TOAST = 5005;
        public static final int READER_TXT_READING_MODEL = 5010;
        public static final int SEARCH_SELECT_TEXT = 5004;
        public static final int SHOW_BAR = 5002;
        public static final int SHOW_DIALOG = 5006;

        void onCallBackAction(Integer num, Object obj, Object obj2);
    }

    public TbsReaderView(Context context, ReaderCallback readerCallback) throws RuntimeException {
        super(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        if (!(context instanceof Activity)) {
            throw new RuntimeException("error: unexpect context(none Activity)");
        }
        this.d = readerCallback;
        this.a = context;
        this.e = new ReaderCallback() {
            public void onCallBackAction(Integer num, Object obj, Object obj2) {
                Bundle bundle = null;
                boolean z = false;
                switch (num.intValue()) {
                    case ReaderCallback.READER_PDF_LIST /*5008*/:
                        if (c.c(TbsReaderView.this.a)) {
                            String str = "";
                            if (obj != 0) {
                                Bundle bundle2 = (Bundle) obj;
                                str = bundle2.getString("docpath");
                                bundle = bundle2;
                            }
                            QbSdk.startQBForDoc(TbsReaderView.this.a, str, 4, 0, "pdf", bundle);
                            TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_PDF_INTO_BROWSER);
                            z = true;
                            break;
                        } else {
                            num = Integer.valueOf(ReaderCallback.INSTALL_QB);
                            String resString = TbsReaderView.getResString(TbsReaderView.this.a, ReaderCallback.READER_PLUGIN_RES_PDF_GUIDE);
                            r9 = new Bundle();
                            r9.putString("tip", resString);
                            r9.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_PDF_INTO_DOWNLOAD);
                            r9.putInt(MessageKey.MSG_CHANNEL_ID, TbsReaderView.READER_CHANNEL_PDF_ID);
                            TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_PDF_INTO_DIALOG);
                            obj = r9;
                            break;
                        }
                    case ReaderCallback.READER_PPT_PLAY_MODEL /*5009*/:
                        if (c.c(TbsReaderView.this.a)) {
                            String str2 = "";
                            if (obj != 0) {
                                Bundle bundle3 = (Bundle) obj;
                                str2 = bundle3.getString("docpath");
                                bundle = bundle3;
                            }
                            QbSdk.startQBForDoc(TbsReaderView.this.a, str2, 4, 0, "", bundle);
                            TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_PPT_INTO_BROWSER);
                            z = true;
                            break;
                        } else {
                            num = Integer.valueOf(ReaderCallback.INSTALL_QB);
                            String resString2 = TbsReaderView.getResString(TbsReaderView.this.a, ReaderCallback.READER_PLUGIN_RES_PPT_GUIDE);
                            r9 = new Bundle();
                            r9.putString("tip", resString2);
                            r9.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_PPT_INTO_DOWNLOAD);
                            r9.putInt(MessageKey.MSG_CHANNEL_ID, TbsReaderView.READER_CHANNEL_PPT_ID);
                            TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_PPT_INTO_DIALOG);
                            obj = r9;
                            break;
                        }
                    case ReaderCallback.READER_TXT_READING_MODEL /*5010*/:
                        if (c.c(TbsReaderView.this.a)) {
                            String str3 = "";
                            if (obj != 0) {
                                Bundle bundle4 = (Bundle) obj;
                                str3 = bundle4.getString("docpath");
                                bundle = bundle4;
                            }
                            QbSdk.startQBForDoc(TbsReaderView.this.a, str3, 4, 0, "txt", bundle);
                            z = true;
                            break;
                        } else {
                            num = Integer.valueOf(ReaderCallback.INSTALL_QB);
                            String resString3 = TbsReaderView.getResString(TbsReaderView.this.a, ReaderCallback.READER_PLUGIN_RES_TXT_GUIDE);
                            r9 = new Bundle();
                            r9.putString("tip", resString3);
                            r9.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_TXT_INTO_DOWNLOAD);
                            r9.putInt(MessageKey.MSG_CHANNEL_ID, TbsReaderView.READER_CHANNEL_TXT_ID);
                            TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_TXT_INTO_DIALOG);
                            obj = r9;
                            break;
                        }
                    case ReaderCallback.READER_SEARCH_IN_DOCUMENT /*5026*/:
                        if (c.c(TbsReaderView.this.a)) {
                            String str4 = "";
                            if (obj != 0) {
                                Bundle bundle5 = (Bundle) obj;
                                str4 = bundle5.getString("docpath");
                                bundle = bundle5;
                            }
                            QbSdk.startQBForDoc(TbsReaderView.this.a, str4, 4, 0, "doc", bundle);
                            TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_DOC_INTO_BROWSER);
                            z = true;
                            break;
                        } else {
                            num = Integer.valueOf(ReaderCallback.INSTALL_QB);
                            String resString4 = TbsReaderView.getResString(TbsReaderView.this.a, ReaderCallback.READER_PLUGIN_RES_DOC_GUIDE);
                            r9 = new Bundle();
                            r9.putString("tip", resString4);
                            r9.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_DOC_INTO_DOWNLOAD);
                            r9.putInt(MessageKey.MSG_CHANNEL_ID, TbsReaderView.READER_CHANNEL_DOC_ID);
                            TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_DOC_INTO_DIALOG);
                            obj = r9;
                            break;
                        }
                    case ReaderCallback.READER_PLUGIN_SO_VERSION /*5030*/:
                        if (obj != 0) {
                            Bundle bundle6 = (Bundle) obj;
                            TbsReaderView.gReaderPackName = bundle6.getString("name");
                            TbsReaderView.gReaderPackVersion = bundle6.getString(Config.INPUT_DEF_VERSION);
                        }
                        z = true;
                        break;
                }
                if (TbsReaderView.this.d != null && !z) {
                    TbsReaderView.this.d.onCallBackAction(num, obj, obj2);
                }
            }
        };
    }

    static boolean a(Context context) {
        if (!f) {
            d.a(true).a(StubApp.getOrigApplicationContext(context.getApplicationContext()), true, false);
            f = d.a(false).b();
        }
        return f;
    }

    public static boolean isSupportExt(Context context, String str) {
        if (!a(context) || !ReaderWizard.isSupportCurrentPlatform(context) || !ReaderWizard.isSupportExt(str)) {
            return false;
        }
        return true;
    }

    public boolean preOpen(String str, boolean z) {
        boolean z2 = false;
        if (!isSupportExt(this.a, str)) {
            Log.e(TAG, "not supported by:" + str);
            return false;
        }
        boolean a2 = a(this.a);
        if (a2) {
            a2 = a();
            if (z && a2) {
                if (Apn.getApnType(this.a) == 3) {
                    z2 = true;
                }
                return this.b.checkPlugin(this.c, this.a, str, z2);
            }
        }
        return a2;
    }

    public boolean downloadPlugin(String str) {
        if (this.c != null) {
            return this.b.checkPlugin(this.c, this.a, str, true);
        }
        Log.e(TAG, "downloadPlugin failed!");
        return false;
    }

    public static Drawable getResDrawable(Context context, int i) {
        if (a(context)) {
            return ReaderWizard.getResDrawable(i);
        }
        return null;
    }

    public static String getResString(Context context, int i) {
        String str = "";
        if (a(context)) {
            return ReaderWizard.getResString(i);
        }
        return str;
    }

    public void openFile(Bundle bundle) {
        boolean z = true;
        if (this.c == null || bundle == null) {
            Log.e(TAG, "init failed!");
            return;
        }
        bundle.putBoolean("browser6.0", (!c.b(this.a)) | c.c(this.a));
        boolean a2 = c.a(this.a, 6101625, 610000);
        if (c.b(this.a)) {
            z = false;
        }
        bundle.putBoolean("browser6.1", a2 | z);
        if (!this.b.openFile(this.c, this.a, bundle, this)) {
            Log.e(TAG, "OpenFile failed!");
        }
    }

    public void doCommand(Integer num, Object obj, Object obj2) {
        if (this.b != null && this.c != null) {
            this.b.doCommand(this.c, num, obj, obj2);
        }
    }

    public void onSizeChanged(int i, int i2) {
        if (this.b != null && this.c != null) {
            this.b.onSizeChanged(this.c, i, i2);
        }
    }

    public void onStop() {
        if (this.b != null) {
            this.b.destroy(this.c);
            this.c = null;
        }
        this.a = null;
        f = false;
    }

    public void userStatistics(String str) {
        if (this.b != null) {
            this.b.userStatistics(this.c, str);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        try {
            if (this.b == null) {
                this.b = new ReaderWizard(this.e);
            }
            if (this.c == null) {
                this.c = this.b.getTbsReader();
            }
            if (this.c != null) {
                return this.b.initTbsReader(this.c, this.a);
            }
            return false;
        } catch (NullPointerException e2) {
            Log.e(TAG, "Unexpect null object!");
            return false;
        }
    }
}
