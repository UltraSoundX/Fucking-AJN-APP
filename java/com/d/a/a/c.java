package com.d.a.a;

import android.graphics.drawable.ColorDrawable;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import com.baidu.mobstat.Config;
import com.mob.tools.gui.PullToRequestListAdapter;
import com.mob.tools.gui.PullToRequestView;
import com.mob.tools.utils.UIHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: FriendAdapter */
public class c extends PullToRequestListAdapter implements PlatformActionListener {
    /* access modifiers changed from: private */
    public e a;
    private boolean b;
    private Platform c;
    private final int d = 15;
    /* access modifiers changed from: private */
    public int e;
    /* access modifiers changed from: private */
    public ArrayList<b> f;
    private HashMap<String, Boolean> g;
    private g h;
    private float i;

    /* compiled from: FriendAdapter */
    private static class a {
        public ArrayList<b> a;
        public boolean b;

        private a() {
            this.b = false;
        }
    }

    /* compiled from: FriendAdapter */
    public static class b {
        public boolean a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
    }

    public c(e eVar, PullToRequestView pullToRequestView) {
        super(pullToRequestView);
        this.a = eVar;
        this.e = -1;
        this.b = true;
        this.g = new HashMap<>();
        this.f = new ArrayList<>();
        getListView().setDivider(new ColorDrawable(-1381654));
    }

    public void a(float f2) {
        this.i = f2;
        ListView listView = getListView();
        if (f2 < 1.0f) {
            f2 = 1.0f;
        }
        listView.setDividerHeight((int) f2);
    }

    public void a(OnItemClickListener onItemClickListener) {
        getListView().setOnItemClickListener(onItemClickListener);
    }

    public void a(Platform platform) {
        this.c = platform;
        platform.setPlatformActionListener(this);
    }

    private void a() {
        if (this.b) {
            this.c.listFriend(15, this.e + 1, null);
        }
    }

    public void onComplete(Platform platform, int i2, HashMap<String, Object> hashMap) {
        final a a2 = a(this.c.getName(), hashMap, this.g);
        if (a2 == null) {
            UIHandler.sendEmptyMessage(0, new Callback() {
                public boolean handleMessage(Message message) {
                    c.this.notifyDataSetChanged();
                    return false;
                }
            });
            return;
        }
        this.b = a2.b;
        if (a2.a != null && a2.a.size() > 0) {
            this.e++;
            Message message = new Message();
            message.what = 1;
            message.obj = a2.a;
            UIHandler.sendMessage(message, new Callback() {
                public boolean handleMessage(Message message) {
                    if (c.this.e <= 0) {
                        c.this.f.clear();
                    }
                    c.this.f.addAll(a2.a);
                    c.this.notifyDataSetChanged();
                    return false;
                }
            });
        }
    }

    private a a(String str, HashMap<String, Object> hashMap, HashMap<String, Boolean> hashMap2) {
        boolean z;
        boolean z2 = false;
        if (hashMap == null || hashMap.size() <= 0) {
            return null;
        }
        ArrayList<b> arrayList = new ArrayList<>();
        if ("SinaWeibo".equals(str)) {
            Iterator it = ((ArrayList) hashMap.get("users")).iterator();
            while (it.hasNext()) {
                HashMap hashMap3 = (HashMap) it.next();
                String valueOf = String.valueOf(hashMap3.get(Config.FEED_LIST_ITEM_CUSTOM_ID));
                if (!hashMap2.containsKey(valueOf)) {
                    b bVar = new b();
                    bVar.d = valueOf;
                    bVar.b = String.valueOf(hashMap3.get("name"));
                    bVar.c = String.valueOf(hashMap3.get("description"));
                    bVar.e = String.valueOf(hashMap3.get("profile_image_url"));
                    bVar.f = bVar.b;
                    hashMap2.put(bVar.d, Boolean.valueOf(true));
                    arrayList.add(bVar);
                }
            }
            if (((Integer) hashMap.get("total_number")).intValue() > hashMap2.size()) {
                z = true;
            } else {
                z = false;
            }
            z2 = z;
        } else if ("TencentWeibo".equals(str)) {
            boolean z3 = ((Integer) hashMap.get("hasnext")).intValue() == 0;
            Iterator it2 = ((ArrayList) hashMap.get(Config.LAUNCH_INFO)).iterator();
            while (it2.hasNext()) {
                HashMap hashMap4 = (HashMap) it2.next();
                String valueOf2 = String.valueOf(hashMap4.get("name"));
                if (!hashMap2.containsKey(valueOf2)) {
                    b bVar2 = new b();
                    bVar2.b = String.valueOf(hashMap4.get("nick"));
                    bVar2.d = valueOf2;
                    bVar2.f = valueOf2;
                    Iterator it3 = ((ArrayList) hashMap4.get("tweet")).iterator();
                    if (it3.hasNext()) {
                        bVar2.c = String.valueOf(((HashMap) it3.next()).get("text"));
                    }
                    bVar2.e = String.valueOf(hashMap4.get("head")) + "/100";
                    hashMap2.put(bVar2.d, Boolean.valueOf(true));
                    arrayList.add(bVar2);
                }
            }
            z2 = z3;
        } else if ("Facebook".equals(str)) {
            Iterator it4 = ((ArrayList) hashMap.get("data")).iterator();
            while (it4.hasNext()) {
                HashMap hashMap5 = (HashMap) it4.next();
                String valueOf3 = String.valueOf(hashMap5.get(Config.FEED_LIST_ITEM_CUSTOM_ID));
                if (!hashMap2.containsKey(valueOf3)) {
                    b bVar3 = new b();
                    bVar3.d = valueOf3;
                    bVar3.f = "[" + valueOf3 + "]";
                    bVar3.b = String.valueOf(hashMap5.get("name"));
                    HashMap hashMap6 = (HashMap) hashMap5.get("picture");
                    if (hashMap6 != null) {
                        bVar3.e = String.valueOf(((HashMap) hashMap6.get("data")).get("url"));
                    }
                    hashMap2.put(bVar3.d, Boolean.valueOf(true));
                    arrayList.add(bVar3);
                }
            }
            z2 = ((HashMap) hashMap.get("paging")).containsKey("next");
        } else if ("Twitter".equals(str)) {
            Iterator it5 = ((ArrayList) hashMap.get("users")).iterator();
            while (it5.hasNext()) {
                HashMap hashMap7 = (HashMap) it5.next();
                String valueOf4 = String.valueOf(hashMap7.get("screen_name"));
                if (!hashMap2.containsKey(valueOf4)) {
                    b bVar4 = new b();
                    bVar4.d = valueOf4;
                    bVar4.f = valueOf4;
                    bVar4.b = String.valueOf(hashMap7.get("name"));
                    bVar4.c = String.valueOf(hashMap7.get("description"));
                    bVar4.e = String.valueOf(hashMap7.get("profile_image_url"));
                    hashMap2.put(bVar4.d, Boolean.valueOf(true));
                    arrayList.add(bVar4);
                }
            }
        }
        a aVar = new a();
        aVar.a = arrayList;
        aVar.b = z2;
        return aVar;
    }

    public void onError(Platform platform, int i2, Throwable th) {
        th.printStackTrace();
    }

    public void onCancel(Platform platform, int i2) {
        UIHandler.sendEmptyMessage(0, new Callback() {
            public boolean handleMessage(Message message) {
                c.this.a.finish();
                return false;
            }
        });
    }

    /* renamed from: a */
    public b getItem(int i2) {
        return (b) this.f.get(i2);
    }

    public long getItemId(int i2) {
        return (long) i2;
    }

    public int getCount() {
        if (this.f == null) {
            return 0;
        }
        return this.f.size();
    }

    public View getHeaderView() {
        if (this.h == null) {
            this.h = new g(getContext());
        }
        return this.h;
    }

    public void onPullDown(int i2) {
        this.h.a(i2);
    }

    public void onRefresh() {
        this.h.a();
        this.e = -1;
        this.b = true;
        this.g.clear();
        a();
    }

    public void onReversed() {
        this.h.b();
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        View view2;
        if (view == null) {
            view2 = new d(viewGroup.getContext(), this.i);
        } else {
            view2 = view;
        }
        ((d) view2).a(getItem(i2), isFling());
        if (i2 == getCount() - 1) {
            a();
        }
        return view2;
    }

    public View getFooterView() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setMinimumHeight(10);
        return linearLayout;
    }
}
