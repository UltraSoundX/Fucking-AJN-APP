package cn.sharesdk.framework.utils.QRCodeUtil;

import java.util.Map;

public interface Writer {
    c encode(String str, a aVar, int i, int i2) throws WriterException;

    c encode(String str, a aVar, int i, int i2, Map<e, ?> map) throws WriterException;
}
