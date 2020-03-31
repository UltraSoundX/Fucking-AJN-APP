package com.tencent.android.tpush.service;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.h;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.data.MessageId;
import com.tencent.android.tpush.data.RegisterEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.encrypt.a;
import com.tencent.android.tpush.horse.DefaultServer;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.b;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.protocol.AppInfo;
import com.tencent.android.tpush.service.channel.protocol.DeviceInfo;
import com.tencent.android.tpush.service.channel.protocol.MutableInfo;
import com.tencent.android.tpush.service.channel.protocol.NetworkInfo;
import com.tencent.android.tpush.service.channel.protocol.TpnsClickClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClickReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushCommReportReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsTokenTagReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsUpdateTokenReq;
import com.tencent.android.tpush.service.channel.protocol.UnregInfo;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.e.f;
import com.tencent.android.tpush.service.e.i;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import com.tencent.mid.api.MidService;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class c {
    private static c a = new c();
    private static JSONArray b = new JSONArray();
    private static final String c = a.a("com.tencent.tpush.last_wifi_ts");
    private com.tencent.android.tpush.service.channel.c.a d = new com.tencent.android.tpush.service.channel.c.a() {
        public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
            com.tencent.android.tpush.b.a.c("PushServiceNetworkHandler", "reconnCallback onResponse request:" + jceStruct + ", responseCode:" + i + ", response:" + jceStruct2);
            if (i == 0) {
                if (jceStruct != null) {
                    com.tencent.android.tpush.b.a.a(7, (List<TpnsPushClientReport>) ((TpnsReconnectReq) jceStruct).recvMsgList);
                    CacheManager.updateUnregUninList(b.f(), ((TpnsReconnectReq) jceStruct).unregInfoList);
                    com.tencent.android.tpush.service.c.a.a().d(b.f(), (List<TpnsPushClientReport>) ((TpnsReconnectReq) jceStruct).recvMsgList);
                    com.tencent.android.tpush.service.c.a.a().b(b.f(), ((TpnsReconnectReq) jceStruct).msgClickList);
                }
                TpnsReconnectRsp tpnsReconnectRsp = (TpnsReconnectRsp) jceStruct2;
                if (!(tpnsReconnectRsp == null || tpnsReconnectRsp.appOfflinePushMsgList == null || tpnsReconnectRsp.appOfflinePushMsgList.size() <= 0)) {
                    com.tencent.android.tpush.service.c.a.a().a(tpnsReconnectRsp.appOfflinePushMsgList, tpnsReconnectRsp.timeUs, aVar);
                }
                com.tencent.android.tpush.b.a.c("PushServiceNetworkHandler", "reconnCallback onResponse rsp==null?:" + (tpnsReconnectRsp == null));
                if (tpnsReconnectRsp != null) {
                    c.this.a(aVar.b(), tpnsReconnectRsp.confVersion);
                    return;
                }
                return;
            }
            com.tencent.android.tpush.b.a.i("PushServiceNetworkHandler", ">> reconn failed responseCode=" + i);
        }

        public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
        }

        public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
        }
    };

    public static c a() {
        return a;
    }

    public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
        if (jceStruct != null) {
            if (jceStruct instanceof TpnsPushClientReq) {
                TpnsPushClientReq tpnsPushClientReq = (TpnsPushClientReq) jceStruct;
                com.tencent.android.tpush.service.c.a.a().a(tpnsPushClientReq.msgList, tpnsPushClientReq.timeUs, aVar);
                return;
            }
            com.tencent.android.tpush.b.a.i("PushServiceNetworkHandler", "onReceivedServicePush unhandle message type" + jceStruct.getClass().getName());
        }
    }

    public com.tencent.android.tpush.service.channel.c b() {
        if (!i.j(b.f())) {
            com.tencent.android.tpush.b.a.i("PushServiceNetworkHandler", ">> no app registered!");
            return null;
        }
        TpnsReconnectReq tpnsReconnectReq = new TpnsReconnectReq();
        tpnsReconnectReq.deviceId = com.tencent.android.tpush.service.e.c.a();
        tpnsReconnectReq.networkType = (short) DeviceInfos.getNetworkType(b.f());
        tpnsReconnectReq.token = CacheManager.getToken(b.f());
        tpnsReconnectReq.unregInfoList = CacheManager.getUninstallAndUnregisterInfo(b.f());
        tpnsReconnectReq.recvMsgList = com.tencent.android.tpush.service.c.a.a().c(b.f(), (List<MessageId>) com.tencent.android.tpush.service.c.a.a().b(b.f()));
        tpnsReconnectReq.msgClickList = com.tencent.android.tpush.service.c.a.a().a(b.f());
        tpnsReconnectReq.sdkVersion = String.valueOf(4.03f);
        tpnsReconnectReq.connVersion = 4;
        tpnsReconnectReq.guid = CacheManager.getGuid(b.f());
        tpnsReconnectReq.configVersion = (long) h.a(b.f(), "GroupKeysConfigVersion", -1);
        Context f = b.f();
        MutableInfo mutableInfo = new MutableInfo();
        if (DeviceInfos.isNetworkAvailable(f) && DeviceInfos.isWifiNet(f)) {
            mutableInfo.bssid = CustomDeviceInfos.getWiFiBBSID(f);
            mutableInfo.ssid = CustomDeviceInfos.getWiFiSSID(f);
        }
        mutableInfo.mac = CustomDeviceInfos.getMacAddress(f);
        try {
            mutableInfo.wflist = b(f);
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.i("PushServiceNetworkHandler", ">> getWifiList(" + f + ")" + e);
        }
        JSONObject jSONObject = new JSONObject();
        String newMid = MidService.getNewMid(f);
        if (newMid != null && newMid.trim().length() == 40) {
            try {
                jSONObject.put("new_mid", newMid);
                jSONObject.put("new_mid_v", String.valueOf(4.07f));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (jSONObject != null && jSONObject.length() > 0) {
            tpnsReconnectReq.reserved = jSONObject.toString();
        }
        tpnsReconnectReq.mutableInfo = mutableInfo;
        return new com.tencent.android.tpush.service.channel.c(tpnsReconnectReq, this.d);
    }

    private String b(Context context) {
        int i;
        boolean z = false;
        String str = "";
        if (context != null) {
            JSONArray wifiTopN = CustomDeviceInfos.getWifiTopN(context, 10);
            if (wifiTopN != null && wifiTopN.length() > 0) {
                long b2 = f.b(context, c, 0);
                if (b == null || b.length() <= 0) {
                    i = 0;
                } else if (b.toString().equalsIgnoreCase(wifiTopN.toString())) {
                    return "";
                } else {
                    i = Math.abs(b.length() - wifiTopN.length());
                }
                long currentTimeMillis = System.currentTimeMillis();
                if ((i >= 3) || Math.abs(currentTimeMillis - b2) > 1800000) {
                    z = true;
                }
                if (z) {
                    f.a(context, c, currentTimeMillis);
                    String jSONArray = wifiTopN.toString();
                    b = wifiTopN;
                    return jSONArray;
                }
            }
        }
        return str;
    }

    public static DeviceInfo a(Context context) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.apiLevel = "" + i.e();
        deviceInfo.imei = CustomDeviceInfos.getDeviceId(context);
        deviceInfo.model = DeviceInfos.getDeviceModel(context);
        deviceInfo.manu = Build.MANUFACTURER;
        deviceInfo.model = Build.MODEL;
        deviceInfo.network = DeviceInfos.getLinkedWay(context);
        deviceInfo.os = "android";
        DisplayMetrics displayMetrics = DeviceInfos.getDisplayMetrics(context);
        deviceInfo.resolution = displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
        deviceInfo.apiLevel = "" + VERSION.SDK_INT;
        deviceInfo.sdCard = DeviceInfos.getExternalStorageInfo(context);
        deviceInfo.sdDouble = CustomDeviceInfos.getSimOperator(context);
        deviceInfo.sdkVersion = String.valueOf(4.03f);
        deviceInfo.sdkVersionName = VERSION.RELEASE;
        deviceInfo.isRooted = (long) DeviceInfos.hasRootAccess(context);
        deviceInfo.language = Locale.getDefault().getLanguage();
        deviceInfo.timezone = TimeZone.getDefault().getID();
        deviceInfo.launcherName = i.n(context);
        return deviceInfo;
    }

    public void a(long j, String str, String str2, String str3, String str4, int i, String str5, String str6, String str7, long j2, String str8, String str9, String str10, long j3, long j4, int i2, com.tencent.android.tpush.service.channel.c.a aVar) {
        TpnsRegisterReq tpnsRegisterReq = new TpnsRegisterReq();
        tpnsRegisterReq.accessId = j;
        tpnsRegisterReq.accessKey = str;
        tpnsRegisterReq.deviceId = str2;
        tpnsRegisterReq.appCert = str5;
        tpnsRegisterReq.account = str3;
        tpnsRegisterReq.ticket = str4;
        tpnsRegisterReq.ticketType = (short) i;
        tpnsRegisterReq.deviceInfo = a(b.f());
        tpnsRegisterReq.token = CacheManager.getToken(b.f());
        tpnsRegisterReq.version = 4;
        tpnsRegisterReq.appVersion = str6;
        tpnsRegisterReq.reserved = str7;
        tpnsRegisterReq.otherPushTokenType = j2;
        tpnsRegisterReq.otherPushToken = str10;
        tpnsRegisterReq.otherPushTokenOpType = j3;
        tpnsRegisterReq.channelId = j4;
        tpnsRegisterReq.accountType = (long) i2;
        if (!l.c(str8)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("url", str8);
                if (!l.c(str9)) {
                    jSONObject.put("payload", str9);
                }
            } catch (JSONException e) {
            }
            tpnsRegisterReq.otherPushData = jSONObject.toString();
        }
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.b("PushServiceNetworkHandler", "Register(" + j + StorageInterface.KEY_SPLITER + str2 + StorageInterface.KEY_SPLITER + str3 + StorageInterface.KEY_SPLITER + str4 + StorageInterface.KEY_SPLITER + i + "),token: " + tpnsRegisterReq.token + ",payload: " + tpnsRegisterReq.otherPushData + " channel id" + tpnsRegisterReq.channelId);
        }
        int i3 = 0;
        tpnsRegisterReq.guid = CacheManager.getGuid(b.f());
        while ("0".equals(CacheManager.getToken(b.f())) && !com.tencent.android.tpush.stat.b.c.a()) {
            int i4 = i3 + 1;
            if (i3 >= 8) {
                break;
            }
            try {
                Thread.sleep(500);
                i3 = i4;
            } catch (Exception e2) {
                i3 = i4;
            }
        }
        tpnsRegisterReq.token = CacheManager.getToken(b.f());
        tpnsRegisterReq.guid = CacheManager.getGuid(b.f());
        b.a().a((JceStruct) tpnsRegisterReq, aVar);
        if (!"0".equals(CacheManager.getToken(b.f()))) {
            com.tencent.android.tpush.stat.b.c.b();
        }
    }

    public void a(String str, String str2, long j, String str3, String str4, com.tencent.android.tpush.service.channel.c.a aVar) {
        TpnsUnregisterReq tpnsUnregisterReq = new TpnsUnregisterReq();
        String str5 = "";
        try {
            str5 = TpnsSecurity.getEncryptAPKSignature(b.f().createPackageContext(str4, 0));
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("PushServiceNetworkHandler", ">> create context [for: " + str4 + "] fail.", e);
        }
        tpnsUnregisterReq.unregInfo = new UnregInfo(new AppInfo(j, str3, str5, 0), 0, 0);
        b.a().a((JceStruct) tpnsUnregisterReq, aVar);
    }

    public void a(long j) {
        b.a().a((JceStruct) new TpnsConfigReq(j), (com.tencent.android.tpush.service.channel.c.a) new com.tencent.android.tpush.service.channel.c.a() {
            public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                if (i == 0) {
                    com.tencent.android.tpush.service.a.a.a(b.f()).a(((TpnsConfigRsp) jceStruct2).confContent);
                    return;
                }
                com.tencent.android.tpush.b.a.i("PushServiceNetworkHandler", ">> loadConfig fail responseCode=" + i);
                c.this.a(i, "", aVar);
            }

            public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                com.tencent.android.tpush.b.a.i("PushServiceNetworkHandler", "@@ loadConfiguration.onMessageSendFailed " + channelException.errorCode + StorageInterface.KEY_SPLITER + channelException.getMessage());
                c.this.a(channelException.errorCode, channelException.getMessage(), aVar);
            }

            public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(int i, String str, com.tencent.android.tpush.service.channel.a aVar) {
        com.tencent.android.tpush.b.a.i("PushServiceNetworkHandler", "@@ loadConfiguraionFailHandler(" + i + StorageInterface.KEY_SPLITER + str + ")");
    }

    public void a(final String str) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.c("PushServiceNetworkHandler", "Action uninstallReport : pkgName = " + str);
        }
        RegisterEntity registerInfoByPkgName = CacheManager.getRegisterInfoByPkgName(str);
        if (registerInfoByPkgName != null) {
            TpnsUnregisterReq tpnsUnregisterReq = new TpnsUnregisterReq();
            tpnsUnregisterReq.unregInfo = new UnregInfo(new AppInfo(registerInfoByPkgName.accessId, registerInfoByPkgName.accessKey, "", 0), 1, System.currentTimeMillis());
            CacheManager.UninstallInfoByPkgName(str);
            b.a().a((JceStruct) tpnsUnregisterReq, (com.tencent.android.tpush.service.channel.c.a) new com.tencent.android.tpush.service.channel.c.a() {
                public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                    if (XGPushConfig.enableDebug) {
                        com.tencent.android.tpush.b.a.c("PushServiceNetworkHandler", "Report uninstall with pkgName = " + str + ", reponseCode = " + i);
                    }
                    if (i == 0) {
                        CacheManager.UninstallInfoSuccessByPkgName(str);
                        return;
                    }
                    com.tencent.android.tpush.b.a.i("PushServiceNetworkHandler", " uninstall report fail responseCode=" + i);
                    c.this.a(i, "服务器处理失败，返回错误", str, (TpnsUnregisterReq) jceStruct, aVar);
                }

                public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                    c.this.a(channelException.errorCode, channelException.getMessage(), str, (TpnsUnregisterReq) jceStruct, aVar);
                }

                public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
                }
            });
            return;
        }
        com.tencent.android.tpush.b.a.c("PushServiceNetworkHandler", "The RegisterEntity entity is null, PkgName = " + str);
    }

    /* access modifiers changed from: private */
    public void a(int i, String str, String str2, TpnsUnregisterReq tpnsUnregisterReq, com.tencent.android.tpush.service.channel.a aVar) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.a(Constants.ServiceLogTag, "@@ uninstallReportFailedHandler(" + i + StorageInterface.KEY_SPLITER + str + StorageInterface.KEY_SPLITER + str2 + StorageInterface.KEY_SPLITER + tpnsUnregisterReq + ")");
        }
    }

    public void a(ArrayList<TpnsPushClientReport> arrayList, com.tencent.android.tpush.service.channel.c.a aVar) {
        if (arrayList != null && arrayList.size() > 0) {
            b.a().a((JceStruct) new TpnsPushVerifyReq(arrayList), aVar);
        }
    }

    public void a(long j, String str, int i, String str2, com.tencent.android.tpush.service.channel.c.a aVar) {
        TpnsTokenTagReq tpnsTokenTagReq = new TpnsTokenTagReq();
        tpnsTokenTagReq.accessId = j;
        tpnsTokenTagReq.flag = i;
        tpnsTokenTagReq.tag = str2;
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.c("PushServiceNetworkHandler", "Action -> sendTag to server (" + j + StorageInterface.KEY_SPLITER + str + ")");
        }
        b.a().a((JceStruct) tpnsTokenTagReq, aVar);
    }

    public void b(ArrayList<TpnsClickClientReport> arrayList, com.tencent.android.tpush.service.channel.c.a aVar) {
        if (arrayList != null && arrayList.size() != 0) {
            TpnsPushClickReq tpnsPushClickReq = new TpnsPushClickReq();
            tpnsPushClickReq.msgClickList = arrayList;
            b.a().a((JceStruct) tpnsPushClickReq, aVar);
        }
    }

    public void a(boolean z, long j) {
        com.tencent.android.tpush.b.a.c("PushServiceNetworkHandler", "loadIPList :" + j);
        long lastLoadIpTime = CacheManager.getLastLoadIpTime(b.f());
        if (z) {
            if (System.currentTimeMillis() - lastLoadIpTime > ((long) com.tencent.android.tpush.service.a.a.a(b.f()).n) && com.tencent.android.tpush.service.a.a.a(b.f()).b() != j) {
                a().a(j);
            }
        } else if (com.tencent.android.tpush.service.a.a.a(b.f()).b() != j) {
            a().a(j);
        }
        if (System.currentTimeMillis() - lastLoadIpTime >= ((long) com.tencent.android.tpush.service.a.a.a(b.f()).n)) {
            TpnsGetApListReq tpnsGetApListReq = new TpnsGetApListReq();
            NetworkInfo networkInfo = new NetworkInfo();
            networkInfo.network = DeviceInfos.getNetworkType(b.f());
            networkInfo.op = i.k(b.f());
            tpnsGetApListReq.netInfo = networkInfo;
            com.tencent.android.tpush.b.a.c("PushServiceNetworkHandler", "sendMessage TpnsGetApListReq:" + tpnsGetApListReq);
            b.a().a((JceStruct) tpnsGetApListReq, (com.tencent.android.tpush.service.channel.c.a) new com.tencent.android.tpush.service.channel.c.a() {
                public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                    if (i == 0) {
                        DefaultServer.a(((TpnsGetApListRsp) jceStruct2).apList);
                        CacheManager.saveLoadIpTime(b.f(), System.currentTimeMillis());
                        return;
                    }
                    com.tencent.android.tpush.b.a.i("PushServiceNetworkHandler", ">> loadIPList fail responseCode=" + i);
                }

                public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                    com.tencent.android.tpush.b.a.i("PushServiceNetworkHandler", "@@ loadIPList.onMessageSendFailed " + channelException.errorCode + StorageInterface.KEY_SPLITER + channelException.getMessage());
                }

                public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
                }
            });
        }
    }

    public static byte a(boolean z) {
        if (z) {
            return 1;
        }
        return 0;
    }

    public void a(long j, String str, String str2, String str3, com.tencent.android.tpush.service.channel.c.a aVar) {
        b.a().a((JceStruct) new TpnsUpdateTokenReq(j, str, str2, str3), aVar);
    }

    public void a(Intent intent, com.tencent.android.tpush.service.channel.c.a aVar) {
        TpnsPushCommReportReq tpnsPushCommReportReq = new TpnsPushCommReportReq();
        tpnsPushCommReportReq.type = intent.getLongExtra("type", 0);
        try {
            tpnsPushCommReportReq.accessId = Long.parseLong(Rijndael.decrypt(intent.getStringExtra("accessId")));
        } catch (NumberFormatException e) {
            com.tencent.android.tpush.b.a.i("PushServiceNetworkHandler", "sendCommReportMessage NumberFormatException");
        }
        tpnsPushCommReportReq.msgId = intent.getLongExtra(MessageKey.MSG_ID, 0);
        tpnsPushCommReportReq.broadcastId = intent.getLongExtra("broadcastId", 0);
        tpnsPushCommReportReq.msgTimestamp = intent.getLongExtra("msgTimestamp", 0);
        tpnsPushCommReportReq.clientTimestamp = intent.getLongExtra("clientTimestamp", 0);
        tpnsPushCommReportReq.pkgName = intent.getStringExtra("pkgName");
        String decrypt = Rijndael.decrypt(intent.getStringExtra(NotificationCompat.CATEGORY_MESSAGE));
        if (decrypt != null) {
            tpnsPushCommReportReq.msg = decrypt;
        }
        String decrypt2 = Rijndael.decrypt(intent.getStringExtra("ext"));
        if (decrypt2 != null) {
            tpnsPushCommReportReq.ext = decrypt2;
        }
        b.a().a((JceStruct) tpnsPushCommReportReq, aVar);
    }
}
