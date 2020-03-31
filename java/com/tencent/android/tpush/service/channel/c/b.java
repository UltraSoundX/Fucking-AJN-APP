package com.tencent.android.tpush.service.channel.c;

import android.util.SparseArray;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.service.channel.exception.CommandMappingException;
import com.tencent.android.tpush.service.channel.protocol.TpnsClientReportReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsClientReportRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClickReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClickRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushCommReportReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushCommReportRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsRedirectReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRedirectRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsTokenTagReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsTokenTagRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsUpdateTokenReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsUpdateTokenRsp;
import java.util.HashMap;

/* compiled from: ProGuard */
public class b {
    public static final Integer a = Integer.valueOf(0);
    public static final Integer b = Integer.valueOf(128);
    public static final SparseArray<Class<?>> c = new SparseArray<>();
    public static final HashMap<Class<?>, Integer> d = new HashMap<>();

    static {
        a(a, Byte.valueOf(1), TpnsPushClientReq.class);
        a(a, Byte.valueOf(2), TpnsGetApListReq.class);
        a(b, Byte.valueOf(2), TpnsGetApListRsp.class);
        a(a, Byte.valueOf(3), TpnsConfigReq.class);
        a(b, Byte.valueOf(3), TpnsConfigRsp.class);
        a(a, Byte.valueOf(4), TpnsRegisterReq.class);
        a(b, Byte.valueOf(4), TpnsRegisterRsp.class);
        a(a, Byte.valueOf(5), TpnsUnregisterReq.class);
        a(b, Byte.valueOf(5), TpnsUnregisterRsp.class);
        a(a, Byte.valueOf(6), TpnsReconnectReq.class);
        a(b, Byte.valueOf(6), TpnsReconnectRsp.class);
        a(a, Byte.valueOf(9), TpnsClientReportReq.class);
        a(b, Byte.valueOf(9), TpnsClientReportRsp.class);
        a(a, Byte.valueOf(10), TpnsRedirectReq.class);
        a(b, Byte.valueOf(10), TpnsRedirectRsp.class);
        a(a, Byte.valueOf(JceStruct.STRUCT_END), TpnsPushVerifyReq.class);
        a(b, Byte.valueOf(JceStruct.STRUCT_END), TpnsPushVerifyRsp.class);
        a(a, Byte.valueOf(15), TpnsTokenTagReq.class);
        a(b, Byte.valueOf(15), TpnsTokenTagRsp.class);
        a(a, Byte.valueOf(16), TpnsPushClickReq.class);
        a(b, Byte.valueOf(16), TpnsPushClickRsp.class);
        a(a, Byte.valueOf(17), TpnsUpdateTokenReq.class);
        a(b, Byte.valueOf(17), TpnsUpdateTokenRsp.class);
        a(a, Byte.valueOf(18), TpnsPushCommReportReq.class);
        a(b, Byte.valueOf(18), TpnsPushCommReportRsp.class);
    }

    public static void a(Integer num, Byte b2, Class<?> cls) {
        c.put(num.intValue() | b2.byteValue(), cls);
        d.put(cls, Integer.valueOf(num.intValue() | b2.byteValue()));
    }

    public static short a(Class<?> cls) {
        return ((Integer) d.get(cls)).shortValue();
    }

    public static Class<?> a(short s) {
        return (Class) c.get(s);
    }

    public static JceStruct a(short s, byte[] bArr) {
        Class a2 = a(s);
        if (a2 == null || bArr == null) {
            return null;
        }
        try {
            JceStruct jceStruct = (JceStruct) a2.newInstance();
            JceInputStream jceInputStream = new JceInputStream(bArr);
            jceInputStream.setServerEncoding("UTF-8");
            jceStruct.readFrom(jceInputStream);
            return jceStruct;
        } catch (Exception e) {
            throw new CommandMappingException(e.getMessage(), e);
        }
    }
}
