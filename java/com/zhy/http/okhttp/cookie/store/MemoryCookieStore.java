package com.zhy.http.okhttp.cookie.store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class MemoryCookieStore implements CookieStore {
    private final HashMap<String, List<Cookie>> allCookies = new HashMap<>();

    public void add(HttpUrl httpUrl, List<Cookie> list) {
        List list2 = (List) this.allCookies.get(httpUrl.host());
        if (list2 != null) {
            Iterator it = list2.iterator();
            for (Cookie name : list) {
                String name2 = name.name();
                while (name2 != null && it.hasNext()) {
                    String name3 = ((Cookie) it.next()).name();
                    if (name3 != null && name2.equals(name3)) {
                        it.remove();
                    }
                }
            }
            list2.addAll(list);
            return;
        }
        this.allCookies.put(httpUrl.host(), list);
    }

    public List<Cookie> get(HttpUrl httpUrl) {
        List<Cookie> list = (List) this.allCookies.get(httpUrl.host());
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        this.allCookies.put(httpUrl.host(), arrayList);
        return arrayList;
    }

    public boolean removeAll() {
        this.allCookies.clear();
        return true;
    }

    public List<Cookie> getCookies() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.allCookies.keySet()) {
            arrayList.addAll((Collection) this.allCookies.get(str));
        }
        return arrayList;
    }

    public boolean remove(HttpUrl httpUrl, Cookie cookie) {
        List list = (List) this.allCookies.get(httpUrl.host());
        if (cookie != null) {
            return list.remove(cookie);
        }
        return false;
    }
}
