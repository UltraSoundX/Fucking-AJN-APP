package com.tencent.android.tpush.horse;

import android.text.TextUtils;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MobileType;
import com.tencent.android.tpush.horse.data.ServerItem;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.exception.NullReturnException;
import com.tencent.android.tpush.service.channel.protocol.ApList;
import com.tencent.android.tpush.service.e.i;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* compiled from: ProGuard */
public class DefaultServer {
    public static String a;
    public static ArrayList<Integer> b = new ArrayList<>(Arrays.asList(new Integer[]{Integer.valueOf(443), Integer.valueOf(8080), Integer.valueOf(80), Integer.valueOf(14000)}));
    public static String[] c;
    public static String[] d;
    public static String[] e;
    public static final ENV f = ENV.RELEASE;
    public static final ArrayList<ServerItem> g = new ArrayList<>();
    private static ArrayList<Integer> h = new ArrayList<>();

    /* compiled from: ProGuard */
    public enum ENV {
        RELEASE
    }

    static {
        a = "tpns.qq.com";
        c = new String[]{"183.232.98.178"};
        d = new String[]{"58.251.139.182"};
        e = new String[]{"183.61.46.193"};
        Collections.shuffle(b);
        a = "tpns.qq.com";
        c = new String[]{"183.232.98.178", "111.30.131.23"};
        d = new String[]{"58.251.139.182", "125.39.240.55"};
        e = new String[]{"183.61.46.193", "123.151.152.50"};
        g.add(new ServerItem("183.61.46.193", 443, 0));
    }

    public static int a() {
        try {
            if (h.isEmpty()) {
                h.addAll(b);
                Collections.shuffle(h);
            }
            if (!h.isEmpty()) {
                return ((Integer) h.remove(0)).intValue();
            }
        } catch (Exception e2) {
        }
        return 80;
    }

    public static ArrayList<ServerItem> a(String str) {
        String str2;
        if (str == null) {
            throw new NullReturnException("createDefaultItems return null,because key is null");
        }
        ArrayList<ServerItem> arrayList = new ArrayList<>();
        if (str.equals(String.valueOf(MobileType.CHINAMOBILE.getType()))) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                int intValue = ((Integer) it.next()).intValue();
                for (String serverItem : c) {
                    arrayList.add(new ServerItem(serverItem, intValue, (int) MobileType.CHINAMOBILE.getType()));
                }
            }
        } else if (str.equals(String.valueOf(MobileType.TELCOM.getType()))) {
            Iterator it2 = b.iterator();
            while (it2.hasNext()) {
                int intValue2 = ((Integer) it2.next()).intValue();
                for (String serverItem2 : e) {
                    arrayList.add(new ServerItem(serverItem2, intValue2, (int) MobileType.TELCOM.getType()));
                }
            }
        } else if (str.equals(String.valueOf(MobileType.UNICOM.getType()))) {
            Iterator it3 = b.iterator();
            while (it3.hasNext()) {
                int intValue3 = ((Integer) it3.next()).intValue();
                for (String serverItem3 : d) {
                    arrayList.add(new ServerItem(serverItem3, intValue3, (int) MobileType.UNICOM.getType()));
                }
            }
        } else {
            String domain = CacheManager.getDomain(b.f());
            if (TextUtils.isEmpty(domain)) {
                domain = a;
            }
            try {
                str2 = InetAddress.getByName(domain).getHostAddress();
            } catch (Exception e2) {
                a.d(Constants.ServiceLogTag, "", e2);
                str2 = c[0];
            }
            Iterator it4 = b.iterator();
            while (it4.hasNext()) {
                arrayList.add(new ServerItem(str2, ((Integer) it4.next()).intValue(), (int) MobileType.UNKNOWN.getType()));
            }
        }
        return arrayList;
    }

    public static void a(ApList apList) {
        Map<Byte, Long> map = apList.primary;
        Map<Byte, Long> map2 = apList.secondary;
        ArrayList<Integer> arrayList = apList.portList;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (Byte b2 : map.keySet()) {
            String a2 = i.a(((Long) map.get(b2)).longValue());
            if (!TextUtils.isEmpty(a2)) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Integer num = (Integer) it.next();
                    ServerItem serverItem = new ServerItem(a2, num.intValue(), b2.intValue());
                    a.c(Constants.LogTag, "apList.primary serverItem ip:" + a2 + ",port:" + num);
                    if (b2.byteValue() == MobileType.CHINAMOBILE.getType()) {
                        arrayList2.add(serverItem);
                    }
                    if (b2.byteValue() == MobileType.TELCOM.getType()) {
                        arrayList3.add(serverItem);
                    }
                    if (b2.byteValue() == MobileType.UNICOM.getType()) {
                        arrayList4.add(serverItem);
                    }
                }
            }
        }
        for (Byte b3 : map2.keySet()) {
            String a3 = i.a(((Long) map2.get(b3)).longValue());
            if (!TextUtils.isEmpty(a3)) {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    Integer num2 = (Integer) it2.next();
                    ServerItem serverItem2 = new ServerItem(a3, num2.intValue(), b3.intValue());
                    a.c(Constants.LogTag, "apList.secondary serverItem ip:" + a3 + ",port:" + num2);
                    if (b3.byteValue() == MobileType.CHINAMOBILE.getType()) {
                        arrayList2.add(serverItem2);
                    }
                    if (b3.byteValue() == MobileType.TELCOM.getType()) {
                        arrayList3.add(serverItem2);
                    }
                    if (b3.byteValue() == MobileType.UNICOM.getType()) {
                        arrayList4.add(serverItem2);
                    }
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            CacheManager.addServerItems(b.f(), "" + MobileType.CHINAMOBILE.getType(), arrayList2);
        }
        if (!arrayList3.isEmpty()) {
            CacheManager.addServerItems(b.f(), "" + MobileType.TELCOM.getType(), arrayList3);
        }
        if (!arrayList4.isEmpty()) {
            CacheManager.addServerItems(b.f(), "" + MobileType.UNICOM.getType(), arrayList4);
        }
        ArrayList<Long> arrayList5 = apList.speedTestIpList;
        ArrayList arrayList6 = new ArrayList();
        Iterator it3 = arrayList5.iterator();
        while (it3.hasNext()) {
            Long l = (Long) it3.next();
            Iterator it4 = arrayList.iterator();
            while (it4.hasNext()) {
                Integer num3 = (Integer) it4.next();
                a.c(Constants.LogTag, "apList.speedTestIpList serverItem ip:" + l + ",port:" + num3);
                arrayList6.add(new ServerItem(l.longValue(), num3.intValue(), 0));
            }
        }
        CacheManager.saveSpeedTestList(b.f(), arrayList6);
        String str = apList.domain;
        if (!TextUtils.isEmpty(str) && !str.equals(CacheManager.getDomain(b.f()))) {
            CacheManager.clearDomainServerItem(b.f());
            CacheManager.saveDomain(b.f(), str);
        }
    }

    public static ArrayList<ServerItem> b() {
        ArrayList<ServerItem> arrayList = new ArrayList<>();
        Iterator it = b.iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            for (String serverItem : c) {
                arrayList.add(new ServerItem(serverItem, intValue, (int) MobileType.CHINAMOBILE.getType()));
            }
            for (String serverItem2 : e) {
                arrayList.add(new ServerItem(serverItem2, intValue, (int) MobileType.TELCOM.getType()));
            }
            for (String serverItem3 : d) {
                arrayList.add(new ServerItem(serverItem3, intValue, (int) MobileType.UNICOM.getType()));
            }
        }
        String domain = CacheManager.getDomain(b.f());
        if (TextUtils.isEmpty(domain)) {
            domain = a;
        }
        try {
            String hostAddress = InetAddress.getByName(domain).getHostAddress();
            Iterator it2 = b.iterator();
            while (it2.hasNext()) {
                arrayList.add(new ServerItem(hostAddress, ((Integer) it2.next()).intValue(), (int) MobileType.UNKNOWN.getType()));
            }
        } catch (Exception e2) {
            a.i(Constants.ServiceLogTag, ">> Dns resolve err : " + e2.getMessage());
        }
        return arrayList;
    }
}
