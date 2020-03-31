package com.tencent.open;

import android.net.Uri;
import android.webkit.WebView;
import com.tencent.open.a.f;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import net.sf.json.util.JSONUtils;

/* compiled from: ProGuard */
public class b {
    protected HashMap<String, C0079b> a = new HashMap<>();

    /* compiled from: ProGuard */
    public static class a {
        protected WeakReference<WebView> a;
        protected long b;
        protected String c;

        public a(WebView webView, long j, String str) {
            this.a = new WeakReference<>(webView);
            this.b = j;
            this.c = str;
        }

        public void a(Object obj) {
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                String str = "'undefined'";
                if (obj instanceof String) {
                    str = JSONUtils.SINGLE_QUOTE + ((String) obj).replace("\\", "\\\\").replace(JSONUtils.SINGLE_QUOTE, "\\'") + JSONUtils.SINGLE_QUOTE;
                } else if ((obj instanceof Number) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Double) || (obj instanceof Float)) {
                    str = obj.toString();
                } else if (obj instanceof Boolean) {
                    str = obj.toString();
                }
                webView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':0,'result':" + str + "});");
            }
        }

        public void a() {
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                webView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':1,'result':'no such method'})");
            }
        }

        public void a(String str) {
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                webView.loadUrl("javascript:" + str);
            }
        }
    }

    /* renamed from: com.tencent.open.b$b reason: collision with other inner class name */
    /* compiled from: ProGuard */
    public static class C0079b {
        public void a(String str, List<String> list, a aVar) {
            Object invoke;
            Method[] declaredMethods = getClass().getDeclaredMethods();
            Method method = null;
            int length = declaredMethods.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Method method2 = declaredMethods[i];
                if (method2.getName().equals(str) && method2.getParameterTypes().length == list.size()) {
                    method = method2;
                    break;
                }
                i++;
            }
            if (method != null) {
                try {
                    switch (list.size()) {
                        case 0:
                            invoke = method.invoke(this, new Object[0]);
                            break;
                        case 1:
                            invoke = method.invoke(this, new Object[]{list.get(0)});
                            break;
                        case 2:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1)});
                            break;
                        case 3:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2)});
                            break;
                        case 4:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3)});
                            break;
                        case 5:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3), list.get(4)});
                            break;
                        default:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5)});
                            break;
                    }
                    Class<Void> returnType = method.getReturnType();
                    f.b("openSDK_LOG.JsBridge", "-->call, result: " + invoke + " | ReturnType: " + returnType.getName());
                    if ("void".equals(returnType.getName()) || returnType == Void.class) {
                        if (aVar != null) {
                            aVar.a((Object) null);
                        }
                    } else if (aVar != null && a()) {
                        aVar.a(invoke != null ? invoke.toString() : null);
                    }
                } catch (Exception e) {
                    f.a("openSDK_LOG.JsBridge", "-->handler call mehtod ex. targetMethod: " + method, e);
                    if (aVar != null) {
                        aVar.a();
                    }
                }
            } else if (aVar != null) {
                aVar.a();
            }
        }

        public boolean a() {
            return false;
        }
    }

    public void a(C0079b bVar, String str) {
        this.a.put(str, bVar);
    }

    public void a(String str, String str2, List<String> list, a aVar) {
        f.a("openSDK_LOG.JsBridge", "getResult---objName = " + str + " methodName = " + str2);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.set(i, URLDecoder.decode((String) list.get(i), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        C0079b bVar = (C0079b) this.a.get(str);
        if (bVar != null) {
            f.b("openSDK_LOG.JsBridge", "call----");
            bVar.a(str2, list, aVar);
            return;
        }
        f.b("openSDK_LOG.JsBridge", "not call----objName NOT FIND");
        if (aVar != null) {
            aVar.a();
        }
    }

    public boolean a(WebView webView, String str) {
        f.a("openSDK_LOG.JsBridge", "-->canHandleUrl---url = " + str);
        if (str == null || !Uri.parse(str).getScheme().equals("jsbridge")) {
            return false;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList((str + "/#").split("/")));
        if (arrayList.size() < 6) {
            return false;
        }
        String str2 = (String) arrayList.get(2);
        String str3 = (String) arrayList.get(3);
        List subList = arrayList.subList(4, arrayList.size() - 1);
        a aVar = new a(webView, 4, str);
        webView.getUrl();
        a(str2, str3, subList, aVar);
        return true;
    }
}
