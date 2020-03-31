package com.mob.commons.eventrecoder;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.LockAction;
import com.mob.commons.d;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.FileLocker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public final class EventRecorder implements PublicMemberKeeper {
    /* access modifiers changed from: private */
    public static File a;
    /* access modifiers changed from: private */
    public static FileOutputStream b;

    public static final synchronized void prepare() {
        synchronized (EventRecorder.class) {
            a((LockAction) new LockAction() {
                public boolean run(FileLocker fileLocker) {
                    try {
                        EventRecorder.a = new File(MobSDK.getContext().getFilesDir(), ".mrecord");
                        if (!EventRecorder.a.exists()) {
                            EventRecorder.a.createNewFile();
                        }
                        EventRecorder.b = new FileOutputStream(EventRecorder.a, true);
                    } catch (Throwable th) {
                        MobLog.getInstance().w(th);
                    }
                    return false;
                }
            });
        }
    }

    public static final synchronized void addBegin(String str, String str2) {
        synchronized (EventRecorder.class) {
            a(str + " " + str2 + " 0\n");
        }
    }

    private static final void a(LockAction lockAction) {
        d.a(new File(MobSDK.getContext().getFilesDir(), "comm/locks/.mrlock"), lockAction);
    }

    public static final synchronized void addEnd(String str, String str2) {
        synchronized (EventRecorder.class) {
            a(str + " " + str2 + " 1\n");
        }
    }

    private static final void a(final String str) {
        a((LockAction) new LockAction() {
            public boolean run(FileLocker fileLocker) {
                try {
                    EventRecorder.b.write(str.getBytes("utf-8"));
                    EventRecorder.b.flush();
                } catch (Throwable th) {
                    MobLog.getInstance().w(th);
                }
                return false;
            }
        });
    }

    public static final synchronized String checkRecord(final String str) {
        String str2;
        synchronized (EventRecorder.class) {
            final LinkedList linkedList = new LinkedList();
            a((LockAction) new LockAction() {
                public boolean run(FileLocker fileLocker) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(EventRecorder.a), "utf-8"));
                        for (String readLine = bufferedReader.readLine(); !TextUtils.isEmpty(readLine); readLine = bufferedReader.readLine()) {
                            String[] split = readLine.split(" ");
                            if (str.equals(split[0])) {
                                if ("0".equals(split[2])) {
                                    linkedList.add(split[1]);
                                } else if ("1".equals(split[2])) {
                                    int indexOf = linkedList.indexOf(split[1]);
                                    if (indexOf != -1) {
                                        linkedList.remove(indexOf);
                                    }
                                }
                            }
                        }
                        bufferedReader.close();
                    } catch (Throwable th) {
                        MobLog.getInstance().d(th);
                    }
                    return false;
                }
            });
            if (linkedList.size() > 0) {
                str2 = (String) linkedList.get(0);
            } else {
                str2 = null;
            }
        }
        return str2;
    }

    public static final synchronized void clear() {
        synchronized (EventRecorder.class) {
            a((LockAction) new LockAction() {
                public boolean run(FileLocker fileLocker) {
                    try {
                        EventRecorder.b.close();
                        EventRecorder.a.delete();
                        EventRecorder.a = new File(MobSDK.getContext().getFilesDir(), ".mrecord");
                        EventRecorder.a.createNewFile();
                        EventRecorder.b = new FileOutputStream(EventRecorder.a, true);
                    } catch (Throwable th) {
                        MobLog.getInstance().w(th);
                    }
                    return false;
                }
            });
        }
    }
}
