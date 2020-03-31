package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.horse.data.ServerItem;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.exception.NullReturnException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProGuard */
public class f {
    private static StrategyItem a(String str, int i, int i2) {
        if (str == null || i == 0) {
            return null;
        }
        return new StrategyItem(str, i, "", 80, i2, 0);
    }

    private static List<StrategyItem> a(List<ServerItem> list, short s, String str) {
        StrategyItem strategyItem;
        if (list == null) {
            throw new NullReturnException("getStrategyItems return null, because [items] is null");
        }
        ArrayList arrayList = new ArrayList();
        try {
            StrategyItem optStrategyItem = CacheManager.getOptStrategyList(b.f(), str).getOptStrategyItem();
            optStrategyItem.setRedirect(0);
            if (optStrategyItem.getProtocolType() == s) {
                arrayList.add(optStrategyItem);
            }
            strategyItem = optStrategyItem;
        } catch (Exception e) {
            a.i(Constants.ServiceLogTag, "getStrategyItems is null");
            strategyItem = null;
        }
        for (int i = 0; i < list.size(); i++) {
            StrategyItem a = a(((ServerItem) list.get(i)).getServerIp(), ((ServerItem) list.get(i)).getServerPort(), (int) s);
            if (a != null && !a.equals(strategyItem)) {
                arrayList.add(a);
            }
        }
        return arrayList;
    }

    public static List<StrategyItem> a(List<ServerItem> list, String str) {
        return a(list, 0, str);
    }

    public static List<StrategyItem> b(List<ServerItem> list, String str) {
        return a(list, 1, str);
    }
}
