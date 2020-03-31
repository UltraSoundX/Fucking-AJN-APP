package com.mob.tools.utils;

import android.os.Handler.Callback;
import android.os.Message;
import com.baidu.mobstat.Config;
import com.mob.tools.MobLog;
import java.util.HashSet;

public class ApplicationTracker {
    /* access modifiers changed from: private */
    public static HashSet<Tracker> trackers = new HashSet<>();

    public static abstract class Tracker {
        /* access modifiers changed from: protected */
        public void onLaunchActivity(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onPauseActivity(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onPauseActivityFinishing(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onStopActivityShow(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onStopActivityHide(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onShowWindow(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onHideWindow(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onResumeActivity(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onSendResult(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDestroyActivity(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onBindApplication(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onExitApplication(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onNewIntent(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onReceiver(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onCreateService(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onServiceArgs(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onStopService(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onConfigurationChanged(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onCleanUpContext(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onGcWhenIdle(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onBindService(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onUnbindService(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDumpService(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onLowMemory(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onActivityConfigurationChanged(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onRelaunchActivity(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onProfilerControl(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onCreateBackupAgent(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDestroyBackupAgent(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onSuicide(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onRemoveProvider(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onEnableJit(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDispatchPackageBroadcast(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onScheduleCrash(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDumpHeap(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDumpActivity(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onSleeping(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onSetCoreSettings(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onUpdatePackageCompatibilityInfo(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onTrimMemory(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDumpProvider(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onUnstableProviderDied(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onRequestAssistContextExtras(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onTranslucentConversionComplete(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onInstallProvider(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onOnNewActivityOptions(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onCancelVisibleBehind(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onBackgroundVisibleBehindChanged(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onEnterAnimationComplete(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onStartBinderTracking(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onStopBinderTrackingAndDump(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onMultiWindowModeChanged(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onPictureInPictureModeChanged(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onLocalVoiceInteractionStarted(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onAttachAgent(Object obj, Message message) {
        }
    }

    static {
        try {
            final Object currentActivityThread = DeviceHelper.currentActivityThread();
            Object instanceField = ReflectHelper.getInstanceField(currentActivityThread, "mH");
            StringBuilder sb = new StringBuilder();
            sb.append("mC");
            sb.append("al");
            sb.append("lb");
            sb.append("ac");
            sb.append(Config.APP_KEY);
            String sb2 = sb.toString();
            final Callback callback = (Callback) ReflectHelper.getInstanceField(instanceField, sb2);
            ReflectHelper.setInstanceField(instanceField, sb2, new Callback() {
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public boolean handleMessage(android.os.Message r4) {
                    /*
                        r3 = this;
                        java.util.HashSet r0 = com.mob.tools.utils.ApplicationTracker.trackers
                        java.util.Iterator r1 = r0.iterator()
                    L_0x0008:
                        boolean r0 = r1.hasNext()
                        if (r0 == 0) goto L_0x0193
                        java.lang.Object r0 = r1.next()
                        com.mob.tools.utils.ApplicationTracker$Tracker r0 = (com.mob.tools.utils.ApplicationTracker.Tracker) r0
                        int r2 = r4.what     // Catch:{ Throwable -> 0x0020 }
                        switch(r2) {
                            case 100: goto L_0x001a;
                            case 101: goto L_0x0029;
                            case 102: goto L_0x002f;
                            case 103: goto L_0x0035;
                            case 104: goto L_0x003b;
                            case 105: goto L_0x0041;
                            case 106: goto L_0x0047;
                            case 107: goto L_0x004d;
                            case 108: goto L_0x0053;
                            case 109: goto L_0x0059;
                            case 110: goto L_0x005f;
                            case 111: goto L_0x0065;
                            case 112: goto L_0x006b;
                            case 113: goto L_0x0071;
                            case 114: goto L_0x0077;
                            case 115: goto L_0x007d;
                            case 116: goto L_0x0083;
                            case 117: goto L_0x0019;
                            case 118: goto L_0x0089;
                            case 119: goto L_0x0090;
                            case 120: goto L_0x0097;
                            case 121: goto L_0x009e;
                            case 122: goto L_0x00a5;
                            case 123: goto L_0x00ac;
                            case 124: goto L_0x00b3;
                            case 125: goto L_0x00ba;
                            case 126: goto L_0x00c1;
                            case 127: goto L_0x00c8;
                            case 128: goto L_0x00cf;
                            case 129: goto L_0x00d6;
                            case 130: goto L_0x00dd;
                            case 131: goto L_0x00e4;
                            case 132: goto L_0x00eb;
                            case 133: goto L_0x00f2;
                            case 134: goto L_0x00f9;
                            case 135: goto L_0x0100;
                            case 136: goto L_0x0107;
                            case 137: goto L_0x010e;
                            case 138: goto L_0x0115;
                            case 139: goto L_0x011c;
                            case 140: goto L_0x0123;
                            case 141: goto L_0x012a;
                            case 142: goto L_0x0131;
                            case 143: goto L_0x0138;
                            case 144: goto L_0x013f;
                            case 145: goto L_0x0146;
                            case 146: goto L_0x014d;
                            case 147: goto L_0x0154;
                            case 148: goto L_0x015b;
                            case 149: goto L_0x0162;
                            case 150: goto L_0x0169;
                            case 151: goto L_0x0170;
                            case 152: goto L_0x0177;
                            case 153: goto L_0x017e;
                            case 154: goto L_0x0185;
                            case 155: goto L_0x018c;
                            default: goto L_0x0019;
                        }     // Catch:{ Throwable -> 0x0020 }
                    L_0x0019:
                        goto L_0x0008
                    L_0x001a:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onLaunchActivity(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0020:
                        r0 = move-exception
                        com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
                        r2.w(r0)
                        goto L_0x0008
                    L_0x0029:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onPauseActivity(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x002f:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onPauseActivityFinishing(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0035:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onStopActivityShow(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x003b:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onStopActivityHide(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0041:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onShowWindow(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0047:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onHideWindow(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x004d:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onResumeActivity(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0053:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onSendResult(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0059:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onDestroyActivity(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x005f:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onBindApplication(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0065:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onExitApplication(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x006b:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onNewIntent(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0071:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onReceiver(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0077:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onCreateService(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x007d:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onServiceArgs(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0083:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onStopService(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0089:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onConfigurationChanged(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0090:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onCleanUpContext(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0097:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onGcWhenIdle(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x009e:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onBindService(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00a5:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onUnbindService(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00ac:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onDumpService(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00b3:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onLowMemory(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00ba:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onActivityConfigurationChanged(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00c1:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onRelaunchActivity(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00c8:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onProfilerControl(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00cf:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onCreateBackupAgent(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00d6:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onDestroyBackupAgent(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00dd:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onSuicide(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00e4:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onRemoveProvider(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00eb:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onEnableJit(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00f2:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onDispatchPackageBroadcast(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x00f9:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onScheduleCrash(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0100:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onDumpHeap(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0107:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onDumpActivity(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x010e:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onSleeping(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0115:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onSetCoreSettings(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x011c:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onUpdatePackageCompatibilityInfo(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0123:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onTrimMemory(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x012a:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onDumpProvider(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0131:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onUnstableProviderDied(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0138:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onRequestAssistContextExtras(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x013f:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onTranslucentConversionComplete(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0146:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onInstallProvider(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x014d:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onOnNewActivityOptions(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0154:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onCancelVisibleBehind(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x015b:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onBackgroundVisibleBehindChanged(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0162:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onEnterAnimationComplete(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0169:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onStartBinderTracking(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0170:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onStopBinderTrackingAndDump(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0177:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onMultiWindowModeChanged(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x017e:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onPictureInPictureModeChanged(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0185:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onLocalVoiceInteractionStarted(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x018c:
                        java.lang.Object r2 = r1     // Catch:{ Throwable -> 0x0020 }
                        r0.onAttachAgent(r2, r4)     // Catch:{ Throwable -> 0x0020 }
                        goto L_0x0008
                    L_0x0193:
                        android.os.Handler$Callback r0 = r0
                        if (r0 == 0) goto L_0x01a1
                        android.os.Handler$Callback r0 = r0
                        boolean r0 = r0.handleMessage(r4)
                        if (r0 == 0) goto L_0x01a1
                        r0 = 1
                    L_0x01a0:
                        return r0
                    L_0x01a1:
                        r0 = 0
                        goto L_0x01a0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ApplicationTracker.AnonymousClass1.handleMessage(android.os.Message):boolean");
                }
            });
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    public static void addTracker(Tracker tracker) {
        trackers.add(tracker);
    }

    public static void removeTracker(Tracker tracker) {
        trackers.remove(tracker);
    }
}
