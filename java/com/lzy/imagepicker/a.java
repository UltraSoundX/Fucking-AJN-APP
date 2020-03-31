package com.lzy.imagepicker;

import com.lzy.imagepicker.bean.ImageItem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: DataHolder */
public class a {
    private static a a;
    private Map<String, List<ImageItem>> b = new HashMap();

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    private a() {
    }

    public void a(String str, List<ImageItem> list) {
        if (this.b != null) {
            this.b.put(str, list);
        }
    }

    public Object a(String str) {
        if (this.b != null && a != null) {
            return this.b.get(str);
        }
        throw new RuntimeException("你必须先初始化");
    }
}
