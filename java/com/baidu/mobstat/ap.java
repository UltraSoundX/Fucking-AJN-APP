package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ListView;
import com.baidu.mobstat.ag.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ap {
    public static View a(Activity activity) {
        if (activity == null) {
            return null;
        }
        Window window = activity.getWindow();
        if (window != null) {
            return window.getDecorView();
        }
        return null;
    }

    public static String a(View view) {
        String str = "";
        if (view instanceof ListView) {
            str = ListView.class.getSimpleName();
        } else if (view instanceof WebView) {
            str = WebView.class.getSimpleName();
        }
        if (TextUtils.isEmpty(str)) {
            String a = a(view.getClass());
            if (!"android.widget".equals(a) && !"android.view".equals(a)) {
                Class cls = null;
                try {
                    cls = Class.forName("android.support.v7.widget.RecyclerView");
                } catch (Exception e) {
                }
                if (cls != null && cls.isAssignableFrom(view.getClass())) {
                    str = "RecyclerView";
                }
            }
        }
        if (TextUtils.isEmpty(str)) {
            str = c(view.getClass());
        }
        if (TextUtils.isEmpty(str)) {
            return "Object";
        }
        return str;
    }

    private static String c(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        String a = a(cls);
        if ("android.widget".equals(a) || "android.view".equals(a)) {
            return d(cls);
        }
        return c(cls.getSuperclass());
    }

    public static String a(Class<?> cls) {
        String str = "";
        if (cls == null) {
            return str;
        }
        Package packageR = cls.getPackage();
        if (packageR != null) {
            str = packageR.getName();
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String a(View view, View view2) {
        if (view == null) {
            return String.valueOf(0);
        }
        if (view == view2) {
            return String.valueOf(0);
        }
        ViewParent parent = view.getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            return String.valueOf(0);
        }
        Class cls = view.getClass();
        if (cls == null) {
            return String.valueOf(0);
        }
        String b = b(cls);
        if (TextUtils.isEmpty(b)) {
            return String.valueOf(0);
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        int i = 0;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt != null) {
                if (childAt == view) {
                    break;
                } else if (childAt.getClass() != null && b.equals(b(childAt.getClass()))) {
                    i++;
                }
            }
        }
        return String.valueOf(i);
    }

    public static String a(View view, String str) {
        String str2;
        String str3 = "";
        if (TextUtils.isEmpty(str) || view == null) {
            return str3;
        }
        ViewParent parent = view.getParent();
        if (parent == null || !(parent instanceof View)) {
            return str3;
        }
        View view2 = (View) parent;
        if (ListView.class.getSimpleName().equals(str)) {
            try {
                if (!(view2 instanceof ListView) || view.getParent() == null) {
                    str2 = str3;
                } else {
                    str2 = String.valueOf(((ListView) view2).getPositionForView(view));
                }
                return str2;
            } catch (Throwable th) {
                return str3;
            }
        } else if (GridView.class.getSimpleName().equals(str)) {
            try {
                if (!(view2 instanceof GridView) || view.getParent() == null) {
                    return str3;
                }
                return String.valueOf(((GridView) view2).getPositionForView(view));
            } catch (Throwable th2) {
                return str3;
            }
        } else if (!"RecyclerView".equals(str)) {
            return str3;
        } else {
            try {
                return String.valueOf(((RecyclerView) view2).g(view));
            } catch (Throwable th3) {
                return str3;
            }
        }
    }

    public static String b(View view) {
        String str;
        int i;
        int i2 = 0;
        String str2 = "";
        if (view == null) {
            return str2;
        }
        ViewParent parent = view.getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            return str2;
        }
        String a = a(parent.getClass());
        if ("android.widget".equals(a) || "android.view".equals(a)) {
            return str2;
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        Class cls = null;
        try {
            cls = Class.forName("android.support.v4.view.ViewPager");
        } catch (ClassNotFoundException e) {
        }
        if (cls == null || !cls.isAssignableFrom(viewGroup.getClass())) {
            return str2;
        }
        try {
            ViewPager viewPager = (ViewPager) viewGroup;
            ArrayList arrayList = new ArrayList();
            int childCount = viewPager.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = viewPager.getChildAt(i3);
                arrayList.add(childAt);
                if (c(childAt) != null) {
                    i2++;
                }
            }
            if (arrayList.size() < 2 || i2 < 2) {
                str = String.valueOf(viewPager.getCurrentItem());
                return str;
            }
            try {
                Collections.sort(arrayList, new Comparator<View>() {
                    /* renamed from: a */
                    public int compare(View view, View view2) {
                        return view.getLeft() - view2.getLeft();
                    }
                });
            } catch (Exception e2) {
            }
            int left = view.getLeft() / Math.abs(((View) arrayList.get(1)).getLeft() - ((View) arrayList.get(0)).getLeft());
            int count = viewPager.getAdapter().getCount();
            if (count != 0) {
                i = left % count;
            } else {
                i = left;
            }
            str = String.valueOf(i);
            return str;
        } catch (Throwable th) {
            str = str2;
        }
    }

    public static Rect c(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        Rect rect = new Rect();
        if (!a(view, rect) || rect.right <= rect.left || rect.bottom <= rect.top) {
            return null;
        }
        return rect;
    }

    private static boolean a(View view, Rect rect) {
        boolean z = false;
        if (view == null || rect == null) {
            return z;
        }
        try {
            return view.getGlobalVisibleRect(rect);
        } catch (Exception e) {
            return z;
        }
    }

    public static String d(View view) {
        String str = null;
        try {
            if (view.getId() != 0) {
                str = view.getResources().getResourceName(view.getId());
            }
        } catch (Exception e) {
        }
        String str2 = ":id/";
        if (!TextUtils.isEmpty(str) && str.contains(str2)) {
            int lastIndexOf = str.lastIndexOf(str2);
            if (lastIndexOf != -1) {
                int length = str2.length() + lastIndexOf;
                if (length < str.length()) {
                    str = str.substring(length);
                }
            }
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    public static JSONArray a(Activity activity, View view) {
        View view2;
        JSONArray jSONArray;
        String str;
        JSONArray jSONArray2 = new JSONArray();
        if (!(activity == null || view == null)) {
            View view3 = null;
            try {
                view2 = a(activity);
            } catch (Exception e) {
                view2 = view3;
            }
            if (view2 != null) {
                while (view != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("p", f(view));
                        String b = b(view);
                        if (TextUtils.isEmpty(b)) {
                            String str2 = "";
                            ViewParent parent = view.getParent();
                            if (parent == null || !(parent instanceof View)) {
                                str = str2;
                            } else {
                                str = a((View) parent);
                            }
                            b = a(view, str);
                            if (TextUtils.isEmpty(b)) {
                                b = a(view, view2);
                            }
                        }
                        jSONObject.put("i", b);
                        jSONObject.put("t", a(view));
                        jSONArray2.put(jSONObject);
                        ViewParent parent2 = view.getParent();
                        if (parent2 != null && view != view2) {
                            if (!(parent2 instanceof View) || g(view) || jSONArray2.length() > 1000) {
                                break;
                            }
                            view = (View) parent2;
                        } else {
                            break;
                        }
                    } catch (Exception e2) {
                        jSONArray = new JSONArray();
                    }
                }
                jSONArray = jSONArray2;
                jSONArray2 = new JSONArray();
                try {
                    for (int length = jSONArray.length() - 1; length >= 0; length--) {
                        jSONArray2.put(jSONArray.get(length));
                    }
                } catch (Exception e3) {
                }
            }
        }
        return jSONArray2;
    }

    public static Map<String, String> e(View view) {
        Map<String, String> map;
        Object tag = view.getTag(-96000);
        if (tag == null || !(tag instanceof Map)) {
            return null;
        }
        try {
            map = (Map) tag;
        } catch (Exception e) {
            map = null;
        }
        if (map == null || map.size() == 0) {
            return null;
        }
        return map;
    }

    public static String a(JSONArray jSONArray) {
        String str = "";
        if (jSONArray == null || jSONArray.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String string = jSONObject.getString("p");
                sb.append("/" + string + "[" + jSONObject.getString("i") + "]");
                i++;
            } catch (Exception e) {
                return str;
            }
        }
        return sb.toString();
    }

    public static String b(JSONArray jSONArray) {
        String str = "";
        if (jSONArray == null || jSONArray.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String string = jSONObject.getString("p");
                sb.append("/" + string + "[" + jSONObject.getString("i") + "]");
                String optString = jSONObject.optString("d");
                if (!TextUtils.isEmpty(optString)) {
                    sb.append("#" + optString);
                }
                i++;
            } catch (Exception e) {
                return str;
            }
        }
        return sb.toString();
    }

    public static String c(JSONArray jSONArray) {
        String str = "";
        if (jSONArray == null || jSONArray.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String b = b(jSONObject.getString("p"));
                sb.append("/" + b + "[" + jSONObject.getString("i") + "]");
                i++;
            } catch (Exception e) {
                return str;
            }
        }
        return sb.toString();
    }

    public static String d(JSONArray jSONArray) {
        String str = "";
        if (jSONArray == null || jSONArray.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String b = b(jSONObject.getString("p"));
                sb.append("/" + b + "[" + jSONObject.getString("i") + "]");
                String optString = jSONObject.optString("d");
                if (!TextUtils.isEmpty(optString)) {
                    sb.append("#" + optString);
                }
                i++;
            } catch (Exception e) {
                return str;
            }
        }
        return sb.toString();
    }

    private static String b(String str) {
        String a = aj.a().a(str);
        if (TextUtils.isEmpty(a)) {
            a = ag.a().a(str, a.a);
        }
        if (a == null) {
            return "";
        }
        return a;
    }

    public static String a(String str) {
        String a = ag.a().a(str, a.b);
        if (a == null) {
            return "";
        }
        return a;
    }

    public static String b(Class<?> cls) {
        String str = "";
        if (cls == null) {
            return str;
        }
        String a = a(cls, false);
        if (!TextUtils.isEmpty(a) && cls.isAnonymousClass()) {
            a = a + "$";
        }
        if (a == null) {
            return "";
        }
        return a;
    }

    public static String f(View view) {
        String str = "";
        if (view == null) {
            return str;
        }
        Class cls = view.getClass();
        if (cls == null) {
            return str;
        }
        String d = d(cls);
        if (!TextUtils.isEmpty(d) && cls.isAnonymousClass()) {
            d = d + "$";
        }
        if (d == null) {
            return "";
        }
        return d;
    }

    private static String a(Class<?> cls, boolean z) {
        String str = "";
        if (!cls.isAnonymousClass()) {
            return z ? cls.getSimpleName() : cls.getName();
        }
        Class superclass = cls.getSuperclass();
        if (superclass != null) {
            return z ? superclass.getSimpleName() : superclass.getName();
        }
        return str;
    }

    private static String d(Class<?> cls) {
        return a(cls, true);
    }

    private static boolean g(View view) {
        if (view != null && "com.android.internal.policy".equals(a(view.getClass())) && "DecorView".equals(f(view))) {
            return true;
        }
        return false;
    }

    public static String a(Context context) {
        String str = "";
        if (context == null) {
            return str;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return str;
        }
        ResolveInfo resolveInfo = null;
        try {
            resolveInfo = packageManager.resolveActivity(intent, 0);
        } catch (Exception e) {
        }
        if (resolveInfo == null) {
            return str;
        }
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        if (activityInfo == null) {
            return str;
        }
        String str2 = activityInfo.packageName;
        if ("android".equals(str2)) {
            return str;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = str;
        }
        return str2;
    }

    public static boolean a(Context context, String str) {
        boolean z;
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List list = null;
        try {
            list = packageManager.queryIntentActivities(intent, 65536);
        } catch (Exception e) {
        }
        if (list != null) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityInfo activityInfo = ((ResolveInfo) it.next()).activityInfo;
                if (activityInfo != null && str.equals(activityInfo.packageName)) {
                    z = true;
                    break;
                }
            }
            return z;
        }
        z = false;
        return z;
    }
}
